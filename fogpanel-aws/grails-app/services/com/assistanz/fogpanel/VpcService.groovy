package com.assistanz.fogpanel

import grails.transaction.Transactional
import com.amazonaws.services.ec2.model.DescribeRegionsResult;
import com.amazonaws.services.ec2.model.DescribeVpcsResult;
import com.amazonaws.services.ec2.AmazonEC2;
import com.assistanz.fogpanel.PullVpcJob
import com.amazonaws.services.ec2.model.Region as AwsRegion;
import com.amazonaws.services.ec2.model.Vpc as AwsVpc
import com.amazonaws.services.ec2.model.DescribeSubnetsRequest;
import com.amazonaws.services.ec2.model.DescribeSubnetsResult;
import com.amazonaws.services.ec2.model.DescribeInternetGatewaysResult;
import com.amazonaws.services.ec2.model.Filter;
import com.amazonaws.services.ec2.model.Subnet as AwsSubnet;
import com.amazonaws.services.ec2.model.DescribeRouteTablesResult;
import com.amazonaws.services.ec2.model.RouteTable as AwsRouteTable;
import com.amazonaws.services.ec2.model.DescribeSecurityGroupsRequest;
import com.amazonaws.services.ec2.model.DescribeSecurityGroupsResult;

@Transactional
class VpcService {
    
    def springSecurityService
    AwsAuthService awsAuthService;
    
    
    def pullVpcJobStart() {
        PullVpcJob.triggerNow([id:"vpc"])
        return "OK"
    }
    
    // Pull Vpc,Subnet and Route Table with Routes
    def pullVpcAndSubnetFromAws(jobId) {
        def job = AsynchronousJobs.get(jobId);
        
        try {
            
            job.status = JobStatus.valueOf("RUNNING")
            job.startedAt = new Date()
            job.save(flush: true)
            
            AmazonEC2 ec2 = awsAuthService.authenticateEC2();
            HashMap<String,String> awsVpcList = new HashMap<String,String>();
            ArrayList<String> list = new ArrayList<String>(); 
            DescribeRegionsResult describeRegionResult = ec2.describeRegions();
            List<AwsRegion> regions = describeRegionResult.getRegions();
            def endPoint;
            for (AwsRegion region : regions) {
                endPoint = "https://" + region.getEndpoint();
                def regionName = region.getRegionName()
                ec2.setEndpoint(endPoint);
                def dbRegion = Region.findByName(regionName)
                if(dbRegion != null) {
                    DescribeVpcsResult result = ec2.describeVpcs();
                    for (AwsVpc awsVpc : result.getVpcs()) {
                        awsVpcList.put(awsVpc.getVpcId(), "vpcId");
                        def existVpc = Vpc.findByReferenceId(awsVpc.getVpcId())
                        if(!existVpc) {
                            def adminAccount = Account.findByAccountType("ADMIN")
                            def userAccount = User.findByAccount(adminAccount)
                            def vpc = new Vpc()
                            vpc.referenceId = awsVpc.getVpcId()
                            vpc.state = awsVpc.getState()
                            vpc.dhcpOptionsId = awsVpc.getDhcpOptionsId()
                            vpc.cidrBlock = awsVpc.getCidrBlock()
                            vpc.isDefault = awsVpc.isDefault()
                            vpc.instanceTenancy = awsVpc.getInstanceTenancy()
                            vpc.account = adminAccount
                            vpc.user = userAccount
                            vpc.region = dbRegion
                            DescribeSubnetsRequest describeSubnetsRequest = new DescribeSubnetsRequest();
                            List<Filter> filters=new LinkedList<Filter>();
                            filters.add(new Filter().withName("vpcId").withValues(awsVpc.getVpcId()));
                            
                            DescribeSubnetsResult subnetResult = ec2.describeSubnets(describeSubnetsRequest);
                            for(AwsSubnet subnet : subnetResult.getSubnets()) {
                                vpc.addToSubnets('referenceId':subnet.getSubnetId(),
                                    'cidr':subnet.getCidrBlock(),
                                    'state':subnet.getState(),
                                    'availabilityZone':subnet.getAvailabilityZone(),
                                    'regionId': dbRegion.id
                                )
                            }
                                                        
                            DescribeSecurityGroupsResult  describeSecurityGroupsResult = ec2.describeSecurityGroups();
                            def securityGroups=  describeSecurityGroupsResult.getSecurityGroups();
                            for(def securityGroup : securityGroups){            
                                vpc.addToSecurityGroup('referenceId':securityGroup.getGroupId(),
                                    'name':securityGroup.getGroupName(),
                                    'description':securityGroup.getDescription(),
                                    'regionId':dbRegion.id,
                                    'account':adminAccount
                                )                                                                
                            }
                            vpc.save(flush:true)
                            if (!vpc.save()) {
                                vpc.errors.allErrors.each { println it }
                            }
                                
                            DescribeSecurityGroupsResult describeSecurity = ec2.describeSecurityGroups();
                            def awsSecurityGroups=  describeSecurity.getSecurityGroups();
                            for(def securityGroup : awsSecurityGroups){                                            
                                def currentSecurityGroup = SecurityGroup.findByReferenceId(securityGroup.getGroupId());
                    
                                for(def ip : securityGroup.getIpPermissions()) {
                                    def securityGroupRule = new SecurityGroupRules();
                                    securityGroupRule.securityGroup = currentSecurityGroup;
                                    securityGroupRule.protocol = ip.getIpProtocol() == null ? "Any" : ip.getIpProtocol();
                                    securityGroupRule.direction = "Ingress";
                                    securityGroupRule.fromPort = ip.getFromPort();
                                    securityGroupRule.toPort = ip.getToPort();
                                    securityGroupRule.referenceId = "1";

                                    securityGroupRule.cidr = ip.getIpRanges() + " (CIDR)";
                                    securityGroupRule.createdAt = new Date();
                                    securityGroupRule.save(flush: true)
                                }
                                for(def ip : securityGroup.getIpPermissionsEgress()) {    
                                    def securityGroupRule = new SecurityGroupRules();
                                    securityGroupRule.securityGroup = currentSecurityGroup;
                                    securityGroupRule.protocol = ip.getIpProtocol() == null ? "Any" : ip.getIpProtocol();
                                    securityGroupRule.direction = "Egress";
                                    securityGroupRule.fromPort = ip.getFromPort();
                                    securityGroupRule.toPort = ip.getToPort();
                                    securityGroupRule.referenceId = "1";

                                    securityGroupRule.cidr = ip.getIpRanges() + " (CIDR)";
                                    securityGroupRule.createdAt = new Date();
                                    securityGroupRule.save(flush: true)
                                } 
                            }
                            
                            DescribeRouteTablesResult routeTablesResult = ec2.describeRouteTables();
                                for(AwsRouteTable awsRouteTable : routeTablesResult.getRouteTables()) {
                                    def routeTable = new RouteTable();
                                    routeTable.referenceId = awsRouteTable.getRouteTableId()
                                    routeTable.vpcId = awsRouteTable.getVpcId()
                                    routeTable.regionId = dbRegion.id
                                    for(def routs : awsRouteTable.getRoutes()) {
                                        routeTable.addToRoutes('destinationCidrBlock':routs.getDestinationCidrBlock(),
                                            'gatewayId': routs.getGatewayId(),
                                            'state': routs.getState(),
                                            'origin': routs.getOrigin())
                                    }
                                routeTable.save(flush:true)    
                            }

                        }
                    }
                    
                    DescribeInternetGatewaysResult describeInternetGatewaysResult = ec2.describeInternetGateways();
                    for (def gateway: describeInternetGatewaysResult.getInternetGateways()) {
                        def dbInternetGateway = new InternetGateway()
                        dbInternetGateway.referenceId = gateway.getInternetGatewayId()
                        dbInternetGateway.regionId = dbRegion.id
                        for(def internetAttachments : gateway.getAttachments()) {
                            dbInternetGateway.vpcId = internetAttachments.getVpcId()
                            dbInternetGateway.state = internetAttachments.getState()
                        }
                        
                        for(def tag : gateway.getTags()) {
                            dbInternetGateway.name = tag.getValue()
                        }
                        
                        dbInternetGateway.save(flush:true)
                    }
                    
                }
                
            }
            
            def dbVpcs = Vpc.findAllWhere(deleted: null);
            
                for(def dbVpc :dbVpcs) {
                    boolean blnExists = awsVpcList.containsKey(dbVpc.referenceId);

                    if(blnExists.toString() == "false" || blnExists == false) {
                       dbVpc.deleted = true; 
                       dbVpc.deletedAt =  new Date();
                       dbVpc.save(flush: true); 
                    }
                }
            Console.print("Finished")    
            job.status = JobStatus.valueOf("COMPLETED")
            job.completedAt = new Date()
            job.save(flush: true)
            
        } catch(Exception ex) {
            
            job.status = JobStatus.valueOf("ERROR")
            job.completedAt = new Date()
            job.save(flush: true)
            throw ex
        }
    }
    
    def getVpcList(requestData) {
        try {
            
            def region = Region.findById(requestData)
            def vpcs = Vpc.findAllByRegion(region)
            ArrayList vpcList = new ArrayList()
            if(vpcs) {
                for(def vpc : vpcs) {
                    HashMap map = new HashMap()
                    map.put("id",vpc.id)
                    map.put("vpcId",vpc.referenceId)
                    map.put("state",vpc.state)
                    map.put("cidrBlock",vpc.cidrBlock)
                    map.put("dhcpOptionsId",vpc.dhcpOptionsId)
                    map.put("instanceTenancy",vpc.instanceTenancy)
                    map.put("isDefault",vpc.isDefault)
                    map.put("region",vpc.region.name)
                    vpcList.add(map)
                }
            }
            return vpcList
            
        } catch(Exception ex){
            throw ex
        }
    }
    
    def getVpcStat(requestData) {
        
        Console.print(requestData)
        HashMap dashBoardDetails = new HashMap()
        if(requestData == null) {
            
            def region = Region.findById(1)
        
            def vpcs = Vpc.withCriteria {
                eq('region',region)
            }.size()

            def subnets = Subnet.findAllByRegionId(region.id)
            def routeTables = RouteTable.findAllByRegionId(region.id.toString())
            def securityGroups = SecurityGroup.findAllByRegionId(region.id)
            def internetGateways = InternetGateway.findAllByRegionId(region.id)
            
            dashBoardDetails.put("vpcs",vpcs)
            dashBoardDetails.put("subnets",subnets.size())
            dashBoardDetails.put("routeTables",routeTables.size())
            dashBoardDetails.put("securityGroups",securityGroups.size())
            dashBoardDetails.put("internetGateways",internetGateways.size())
            
        } else {
            def region = Region.findById(requestData.toInteger())
        
            def vpcs = Vpc.withCriteria {
                eq('region',region)
            }.size()

            def subnets = Subnet.findAllByRegionId(region.id)
            def routeTables = RouteTable.findAllByRegionId(region.id.toString())
            def securityGroups = SecurityGroup.findAllByRegionId(region.id)
            def internetGateways = InternetGateway.findAllByRegionId(region.id)
            
            dashBoardDetails.put("vpcs",vpcs)
            dashBoardDetails.put("subnets",subnets.size())
            dashBoardDetails.put("routeTables",routeTables.size())
            dashBoardDetails.put("securityGroups",securityGroups.size())
            dashBoardDetails.put("internetGateways",internetGateways.size())
        }
        
        return dashBoardDetails
    }
    
    def viewVpc(id) {
        
        def vpc = Vpc.findByReferenceId(id)
        
        HashMap map = new HashMap()
        map.put("id",vpc.id)
        map.put("cidrBlock",vpc.cidrBlock)
        map.put("dhcpOptionsId",vpc.dhcpOptionsId)
        map.put("instanceTenancy",vpc.instanceTenancy)
        map.put("isDefault",(vpc.isDefault) == true ? "Yes" : "No")
        map.put("referenceId",vpc.referenceId)
        map.put("state",vpc.state)
        map.put("region",Region.findById(vpc.regionId)?.aliasName)
        return map
    }
               
}
