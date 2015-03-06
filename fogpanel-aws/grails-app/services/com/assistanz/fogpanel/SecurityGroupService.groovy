package com.assistanz.fogpanel

import grails.transaction.Transactional
import com.assistanz.openstack.OpenStackServer;
import java.util.Arrays;
import java.util.List;
import com.assistanz.cloud.config.ConfigLoader
import grails.plugin.springsecurity.SpringSecurityService;
import com.amazonaws.services.ec2.model.CreateSecurityGroupRequest;
import com.amazonaws.services.ec2.model.CreateSecurityGroupResult;
import com.amazonaws.services.ec2.model.DescribeSecurityGroupsRequest;
import com.amazonaws.services.ec2.model.DescribeSecurityGroupsResult;
import com.amazonaws.services.ec2.model.DeleteSecurityGroupRequest;
import com.amazonaws.services.ec2.model.AuthorizeSecurityGroupIngressRequest;
import com.amazonaws.services.ec2.model.AuthorizeSecurityGroupEgressRequest;
import com.amazonaws.services.ec2.model.AuthorizeSecurityGroupIngressRequest;
import com.amazonaws.services.ec2.model.RevokeSecurityGroupIngressRequest;
import com.amazonaws.services.ec2.model.RevokeSecurityGroupEgressRequest;
import com.amazonaws.services.ec2.model.IpPermission;
import com.amazonaws.services.ec2.model.Filter;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.AmazonClientException;

@Transactional
class SecurityGroupService {
    
    def springSecurityService;
    
    AwsAuthService awsAuthService;

    def listSecurityGroup(currentRegionId) {

        def user = springSecurityService.currentUser;      
        
        ArrayList<ArrayList<String>> securityGroups = new ArrayList<ArrayList<String>>();
         
        def regionId = Integer.parseInt(currentRegionId)
        def securityGroupList = SecurityGroup.withCriteria {
            eq('deleted', false)
            if(regionId) {
                eq('regionId', regionId) 
            }
        }
        for (def securityGroup : securityGroupList) {
            
            HashMap item = new HashMap();
            item.put("referenceId", securityGroup.referenceId);
            item.put("name", securityGroup.name);
            item.put("description", securityGroup.description);
            securityGroups.add(item);
            
        }
        
        return securityGroups;
        
    }
    
    def createSecurityGroup(requestData) {
        
        try { 
             
            def user = springSecurityService.currentUser;
            
            ArrayList<ArrayList<String>> createSecurityGroupResponse = new ArrayList<ArrayList<String>>();        
            HashMap<String,String> item = new HashMap<String,String>(); 
            
            def securityGroupName = SecurityGroup.findWhere(name: requestData.name, user: user, deleted: false);
            
            if(securityGroupName) {
                
                item.put("result",  "Exists");
                createSecurityGroupResponse.add(item);   
                 
                return createSecurityGroupResponse;
            } else {               
                
                AmazonEC2 ec2 = awsAuthService.authenticateEC2();
                
                def region = Region.findById(requestData.regionId)
                def vpc = Vpc.findById(requestData.vpcId)
                def endPoint = "https://" + region.getEndPoint();            
                ec2.setEndpoint(endPoint); 
                
                CreateSecurityGroupRequest csgr = new CreateSecurityGroupRequest();
                csgr.setGroupName(requestData.name);
                csgr.setDescription(requestData.description);
                csgr.setVpcId(vpc.getReferenceId());
        
                CreateSecurityGroupResult securityGroupResult = ec2.createSecurityGroup(csgr);
        
                SecurityGroup securityGroup = new SecurityGroup();
                securityGroup.referenceId = securityGroupResult.getGroupId();
                securityGroup.name = requestData.name;
                securityGroup.description = requestData.description;
                securityGroup.account = Account.findById(user.getAccountId());
                securityGroup.user = user;
                securityGroup.vpc = vpc;
                securityGroup.regionId = region.id;
                securityGroup.createdAt = new Date();
                securityGroup.save(flush: true);                
                if (!securityGroup.save()) {
                    securityGroup.errors.allErrors.each { println it }
                }
                
                DescribeSecurityGroupsRequest securityRequest = new DescribeSecurityGroupsRequest();
                securityRequest.setGroupNames(Arrays.asList(requestData.name));
                DescribeSecurityGroupsResult securityDescription = ec2.describeSecurityGroups(securityRequest);
                
                //set default IP permissions
                for (def awsSecurityGroup : securityDescription.getSecurityGroups()) {
                                        
                    def currentSecurityGroup = SecurityGroup.findByReferenceId(awsSecurityGroup.getGroupId());
                    
                    for(IpPermission ip : awsSecurityGroup.getIpPermissions()) {
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
                    for(IpPermission ip : awsSecurityGroup.getIpPermissionsEgress()) {    
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
        
                item.put("result", GeneralConstants.RESULT_SUCCESS);
                createSecurityGroupResponse.add(item)
        
                return createSecurityGroupResponse
            }
        
        } catch (AmazonClientException ex) {

            ArrayList<ArrayList<String>> createSecurityGroupResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_FAILURE);
            item.put("message", ex.localizedMessage);
            createSecurityGroupResponse.add(item)

            return createSecurityGroupResponse    
            
        } catch (Exception ex) {
            throw ex;
        }
        
    }      
    
    def deleteSecurityGroup(referenceId) {
        try {
            
            def user = springSecurityService.currentUser;
            
            AmazonEC2 ec2 = awsAuthService.authenticateEC2();
            
            def securityGroup = SecurityGroup.findByReferenceId(referenceId)
            
            def region = Region.findById(securityGroup.regionId)
            def endPoint = "https://" + region.getEndPoint();            
            ec2.setEndpoint(endPoint); 

            DeleteSecurityGroupRequest dsgr = new DeleteSecurityGroupRequest();
            dsgr.withGroupId(referenceId);
            ec2.deleteSecurityGroup(dsgr);

            
            securityGroup.deleted = true;
            securityGroup.deletedAt = new Date();
            securityGroup.save(flush: true);

            //Delete all mapped rules 
            def securityGroupRules = SecurityGroupRules.withCriteria {
                eq('deleted', false)
                eq('securityGroup', securityGroup)
            } 

            for(def rule : securityGroupRules) {

                rule.deleted = true;
                rule.deletedAt = new Date();
                rule.save(flush: true)
            }

            ["OK"]
                
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
    
    def getSecurityGroupRules(referenceId) {
        
        try {
            
            def user = springSecurityService.currentUser;
            
            def currentSecurityGroup = SecurityGroup.findByReferenceId(referenceId);
           
            ArrayList<ArrayList<String>> securityGroupRulesList = new ArrayList<ArrayList<String>>();
            
            def securityGroupRules = SecurityGroupRules.withCriteria {
                
               eq('deleted', false)
               eq('securityGroup', currentSecurityGroup) 

            }
          
            for (def rule : securityGroupRules) {

                def portRange;
                def fromPort = rule.fromPort;
                def toPort = rule.toPort;
                def cidr = rule.cidr;
                def remote;
                def direction;
                def protocol;
                
                //Rule direction
                if(rule.direction == "Ingress" || rule.direction == "ingress") {
                    
                    direction = GeneralConstants.DIRECTION_INGRESS;
                } else {
                    direction = GeneralConstants.DIRECTION_EGRESS;
                }
                
                //Rule protocol
                if(rule.protocol == "tcp") {
                    protocol = GeneralConstants.PROTOCOL_TCP;
                } else if(rule.protocol == "udp") {
                    protocol = GeneralConstants.PROTOCOL_UDP;
                } else if(rule.protocol == "icmp") {
                    protocol = GeneralConstants.PROTOCOL_ICMP;
                } else {
                    protocol = rule.protocol;
                }
                
                //Rule range
                if(fromPort != toPort) {
                    portRange = fromPort + "-" + toPort;
                } else {
                    portRange = fromPort;
                }

                if(cidr) {
                    remote = cidr;
                } 
                
                if(rule.remoteGroupId) {
                    
                    def securityGroup = SecurityGroup.findByReferenceId(rule.remoteGroupId);
                    remote = securityGroup.name; 
                }

                HashMap item = new HashMap();
                item.put("id", rule.id);
                item.put("direction", direction);
                item.put("etherType", rule.etherType);
                item.put("ipProtocol", protocol);
                item.put("portRange", portRange);
                item.put("remote", remote);
                securityGroupRulesList.add(item);

                
            }  
            return securityGroupRulesList;
        
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
    
    def createSecurityGroupRule(requestData) {
        
        try { 
            
            def protocol;
            def fromPort;
            def toPort;
            def cidr;

            def port = requestData.port;
            def fromPortValue = requestData.fromPort;
            def toPortValue = requestData.toPort;
            def direction = requestData.direction;
            def icmpCode = requestData.icmpCode;
            def icmpType = requestData.icmpType;

            if(requestData.rule == "tcp") {
                protocol = "tcp";
                
                if(!port.equals(null)) {

                    if(requestData.port == 0){
                        fromPort = 0;
                        toPort = 0;

                    } else if(requestData.port == -1){
                        fromPort = -1;
                        toPort = -1;
                    } else {
                        fromPort = requestData.port;
                        toPort = requestData.port;
                    }
                } else{
                    
                    if((! fromPortValue.equals(null)) && (! toPortValue.equals(null)) ) {
                        fromPort = requestData.fromPort;
                        toPort = requestData.toPort;
                    }
                }
                
            } else if(requestData.rule == "udp") {
                protocol = "udp";
                                         
                if(!port.equals(null)) {

                    if(requestData.port == 0){
                        fromPort = 0;
                        toPort = 0;

                    } else if(requestData.port == -1){
                        fromPort = -1;
                        toPort = -1;
                    } else {
                        fromPort = requestData.port;
                        toPort = requestData.port;
                    }
                } else{
                    
                    if((! fromPortValue.equals(null)) && (! toPortValue.equals(null)) ) {
                        fromPort = requestData.fromPort;
                        toPort = requestData.toPort;
                    }
                }
                
            } else if(requestData.rule == "icmp") {
                protocol = "icmp";
             
                if((! icmpCode.equals(null)) && (! icmpType.equals(null)) ) {
                    fromPort = icmpType;
                    toPort = icmpCode;
                }
                 
                
            } else if(requestData.rule == "custom") {
                def ipProtocol = requestData.ipProtocol;
                fromPort = 0;
                toPort = 0;
                protocol = ipProtocol.toString();
                
            } else if(requestData.rule == "all_tcp") {
                protocol = "tcp";
                fromPort = 1;
                toPort = 65535;
                
            } else if(requestData.rule == "all_udp") {
                protocol = "udp";
                fromPort = 1;
                toPort = 65535;
                
            } else if(requestData.rule == "all_icmp") {
                protocol = "icmp";
                fromPort = 0;
                toPort = 0;
                
            } else if(requestData.rule == "dns") {
                direction = "ingress";
                protocol = "tcp";
                fromPort = 53;
                toPort = 53;
                
            } else if(requestData.rule == "http") {
                direction = "ingress";
                protocol = "tcp";
                fromPort = 80;
                toPort = 80;
                
            } else if(requestData.rule == "https") {
                direction = "ingress";
                protocol = "tcp";
                fromPort = 443;
                toPort = 443;
                
            } else if(requestData.rule == "imap") {
                direction = "ingress";
                protocol = "tcp";
                fromPort = 143;
                toPort = 143;
                
            } else if(requestData.rule == "imaps") {
                direction = "ingress";
                protocol = "tcp";
                fromPort = 993;
                toPort = 993;
                
            } else if(requestData.rule == "ldap") {
                direction = "ingress";
                protocol = "tcp";
                fromPort = 389;
                toPort = 389;
                
            } else if(requestData.rule == "ms_sql") {
                direction = "ingress";
                protocol = "tcp";
                fromPort = 1443;
                toPort = 1443;
                
            } else if(requestData.rule == "mysql") {
                direction = "ingress";
                protocol = "tcp";
                fromPort = 3306;
                toPort = 3306;
                
            } else if(requestData.rule == "pop3") {
                direction = "ingress";
                protocol = "tcp";
                fromPort = 110;
                toPort = 110;
                
            } else if(requestData.rule == "pop3s") {
                direction = "ingress";
                protocol = "tcp";
                fromPort = 995;
                toPort = 995;
                
            } else if(requestData.rule == "rdp") {
                direction = "ingress";
                protocol = "tcp";
                fromPort = 3389;
                toPort = 3389;
                
            } else if(requestData.rule == "smtp") {
                direction = "ingress";
                protocol = "tcp";
                fromPort = 25;
                toPort = 25;
                
            } else if(requestData.rule == "smtps") {
                direction = "ingress";
                protocol = "tcp";
                fromPort = 465;
                toPort = 465;
                
            } else if(requestData.rule == "ssh") {
                direction = "ingress";
                protocol = "tcp";
                fromPort = 22;
                toPort = 22;
                
            }
                                  
            if(requestData.cidr == ""){
                cidr = "0.0.0.0/0";
            } else {
                cidr = requestData.cidr;
            }
                
            def user = springSecurityService.currentUser;
            
            println("sec id"+ requestData.securityGroupId)
            println("sec direction"+ direction)
            println("sec protocol"+ protocol)
            println("sec fromPort"+ fromPort)
            println("sec toPort"+ toPort)
            println("sec cidr"+ cidr)
                        
            def currentSecurityGroup = SecurityGroup.findByReferenceId(requestData.securityGroupId);
           
            IpPermission ipPermission = new IpPermission();    	
            ipPermission.withIpRanges(cidr)
            .withIpProtocol(protocol)
            .withFromPort(fromPort)
            .withToPort(toPort);
            
            AmazonEC2 ec2 = awsAuthService.authenticateEC2();
            def region = Region.findById(requestData.regionId)
            def endPoint = "https://" + region.getEndPoint();            
            ec2.setEndpoint(endPoint); 

            if(direction == "ingress") {

                println("Ingress ");
                AuthorizeSecurityGroupIngressRequest asgir = new AuthorizeSecurityGroupIngressRequest();    	
                asgir.withGroupId(requestData.securityGroupId).withIpPermissions(ipPermission);
                ec2.authorizeSecurityGroupIngress(asgir);

            } else {
                println("Egress ");
               AuthorizeSecurityGroupEgressRequest asgir = new AuthorizeSecurityGroupEgressRequest();    	
               asgir.withGroupId(requestData.securityGroupId).withIpPermissions(ipPermission);
               ec2.authorizeSecurityGroupEgress(asgir); 

            }

            def securityGroupRule = new SecurityGroupRules();
            securityGroupRule.referenceId = "1";
            securityGroupRule.securityGroup = currentSecurityGroup;
            securityGroupRule.protocol = protocol;
            securityGroupRule.direction = direction;
            securityGroupRule.fromPort = fromPort;
            securityGroupRule.toPort = toPort;
            securityGroupRule.cidr = cidr + " (CIDR)";
            securityGroupRule.createdAt = new Date();
            securityGroupRule.save(flush:true)

            ArrayList<ArrayList<String>> securityGroupRuleResponse = new ArrayList<ArrayList<String>>();        
            HashMap<String,String> item = new HashMap<String,String>(); 
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            securityGroupRuleResponse.add(item)
        
            return securityGroupRuleResponse
        
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    def deleteSecurityGroupRule(id) {
        
        try {
            
            def user = springSecurityService.currentUser;
            
            def securityGroupRule = SecurityGroupRules.findById(id)
            
            AmazonEC2 ec2 = awsAuthService.authenticateEC2();
            
            def region = Region.findById(securityGroupRule.securityGroup.regionId)
            def endPoint = "https://" + region.getEndPoint();            
            ec2.setEndpoint(endPoint); 
                
            IpPermission ipPermission = new IpPermission();    	
            ipPermission.withIpRanges(securityGroupRule.getCidr().replace(" (CIDR)", ""))
            .withIpProtocol(securityGroupRule.getProtocol())
            .withFromPort(securityGroupRule.getFromPort())
            .withToPort(securityGroupRule.getToPort());

            if(securityGroupRule.getDirection() == "ingress") {

                println("Ingress ");
                RevokeSecurityGroupIngressRequest rcsgir = new RevokeSecurityGroupIngressRequest();    
                rcsgir.withGroupId(securityGroupRule.getSecurityGroup().getReferenceId()).withIpPermissions(ipPermission);
                ec2.revokeSecurityGroupIngress(rcsgir); 
            } else {
                println("Egress ");
                RevokeSecurityGroupEgressRequest rcsgir = new RevokeSecurityGroupEgressRequest();    	
                rcsgir.withGroupId(securityGroupRule.getSecurityGroup().getReferenceId()).withIpPermissions(ipPermission);
                ec2.revokeSecurityGroupEgress(rcsgir);  

            }
            
            securityGroupRule.deleted = true;
            securityGroupRule.deletedAt = new Date();
            securityGroupRule.save(flush: true);
            
            ["OK"]    
            
        } catch (Exception ex) {
            println("Exception " + ex);
            throw ex;
        }
        
    }    
    
    def getSecurityGroupListByRegionAndVPC(currentRegionId, vpcId) {
        try {
            
            def vpc = Vpc.findById(vpcId);            
            def securityGroups = SecurityGroup.findAllByRegionIdAndVpc(currentRegionId, vpc)
            ArrayList securityGroupList = new ArrayList()
            if(securityGroups) {
                for(def securityGroup : securityGroups) {
                    HashMap map = new HashMap()
                    map.put("id",securityGroup.id)
                    map.put("name",securityGroup.name)                                     
                    securityGroupList.add(map)
                }
            }
            return securityGroupList
            
        } catch(Exception ex){
            throw ex
        }
    }
}
