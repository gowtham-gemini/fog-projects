package com.assistanz.fogpanel

import com.assistanz.cloud.cloudstack.CloudStackServer
import com.assistanz.cloud.cloudstack.CloudStackServer.CloudStackServerException
import com.assistanz.cloud.cloudstack.ServiceResponse
import com.assistanz.cloud.cloudstack.address.CSAddressService
import com.assistanz.cloud.cloudstack.address.IpAddressResponse
import com.assistanz.cloud.cloudstack.vpc.CSVPCService
import com.assistanz.cloud.cloudstack.vpc.VpcOfferingResponse
import com.assistanz.cloud.cloudstack.vpc.VpcResponse
import com.assistanz.cloud.cloudstack.vpn.CSVPNService
import com.assistanz.cloud.cloudstack.vpn.VpnGatewayResponse
import org.codehaus.groovy.grails.commons.ApplicationHolder
import com.assistanz.cloud.cloudstack.asyncjob.CSAsyncJobService
import com.assistanz.cloud.cloudstack.networkacl.CSNetworkACLService
import com.assistanz.cloud.cloudstack.networkacl.NetworkAclListResponse
import com.assistanz.cloud.cloudstack.network.CSNetworkService
import com.assistanz.cloud.cloudstack.network.NetworkResponse
import com.assistanz.cloud.cloudstack.nic.NicSecondaryIPResponse
import org.apache.commons.logging.LogFactory;
import grails.transaction.Transactional
import java.sql.Timestamp
import com.assistanz.fogpanel.PullVPCOfferingJob;
import com.assistanz.fogpanel.PullVPCJob;

@Transactional
class VpcService {
    
    private static final log = LogFactory.getLog(this)
    def springSecurityService;
    
     def networkServer() {
        
        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret
    
        CloudStackServer server =
        new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);
                
        CSNetworkService networkService = new CSNetworkService(server);
        
        return networkService;
        
    }
    
    def addressServer() {
        
        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret
    
        CloudStackServer server =
        new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);
                
        CSAddressService addressService = new CSAddressService(server);
        
        return addressService;
        
    }
    
    def vpcServer() {
        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret

        CloudStackServer server =
                 new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);
                
        CSVPCService csVPCService = new CSVPCService(server);
        
        return csVPCService;
    }
    
    def vpnServer() {
        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret

        CloudStackServer server =
                 new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);
                
        CSVPNService csVPNService = new CSVPNService(server);
        
        return csVPNService;
    }
    
    def asyncJobServer() {
        
        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret
    
        CloudStackServer server =
        new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);
                
        CSAsyncJobService jobService = new CSAsyncJobService(server);
        
        return jobService; 
        
    }
    
    def networkAclServer() {
        
        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret
    
        CloudStackServer server =
        new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);
        
        CSNetworkACLService csNetworkACLService = new CSNetworkACLService(server);
        
        return csNetworkACLService;
        
    }
    
    def listVpcBySite2SiteGateway (zoneReferenceId, referenceId) {
        try {
            def user = springSecurityService.currentUser
            def role = springSecurityService.getPrincipal().getAuthorities()  
            def vpcList = ""
            if(role.iterator().next() == "ROLE_ADMIN" ) {               
            } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {
                HashMap<String,String> optional = new HashMap<String,String>();
                optional.put("account", new String(user.account.accountIdentifier));
                optional.put("domainid", new String(user.account.domain.referenceId));
                def response = vpcServer().listVPCs(optional);        
                for(Iterator <VpcResponse> iter = response.vpcs.iterator(); iter.hasNext();) { 
                    def data = iter.next();
                    VPC vpc = VPC.findByReferenceId(data.id)
                    if(vpc) {
                        vpc.state = data.state
                        vpc.networkDomain = data.networkDomain
                        vpc.save(flush: true)
                    }
                }                
                vpcList = VPC.withCriteria {
                    eq("deleted", false)           
                    eq("account", user.account) 
                    if(zoneReferenceId && zoneReferenceId != "All") {
                        eq("zone", Zone.findByReferenceZoneId(zoneReferenceId))
                    }
                    if(referenceId) {
                        eq("referenceId", referenceId)
                    }
                }
            }
            ArrayList<ArrayList<String>> vpcListArray = new ArrayList<ArrayList<String>>();        
            for(def vpc: vpcList) {
                HashMap<String,String> item = new HashMap<String,String>();
                def site2siteGateway = S2SVPNGateways.findWhere(vpc: VPC.findByReferenceId(vpc.referenceId), deleted: false);
                if(!site2siteGateway) { 
                    Console.print("1" + site2siteGateway)
                    item.put("name", vpc.name);
                    item.put("description", vpc.description);
                    item.put("cidr", vpc.cidr);
                    item.put("account", vpc.account.firstName);            
                    item.put("state", vpc.state);
                    item.put("zone", vpc.zone.aliasName);
                    item.put("zoneReferenceId", vpc.zone.referenceZoneId);      
                    item.put("zoneId", vpc.zone.id);                  
                    item.put("networkDomain", vpc.networkDomain);
                    item.put("referenceId", vpc.referenceId);
                    item.put("vpcOffering", vpc.vpcOffering.name);
                    item.put("vpcRestartRequred", vpc.restartRequired);
                    def vpnOffer = MiscellaneousOffer.get(7);
                    def offerCost = MiscellaneousOfferZoneCost.findWhere(miscellaneousOffer :vpnOffer, zone :vpc.zone)
                    item.put("cost", offerCost ? offerCost.cost : 0.0);
                    item.put("currency", ApplicationHolder.getApplication().config.billing.defaultCurrency);                                              
                    vpcListArray.add(item) 
                }  else {
                    Console.print("2" + site2siteGateway)
                }              
            }
            return vpcListArray
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
    }
    
    
    def pullVPCFromCloudStackJobStart() {
        
        PullVPCJob.triggerNow([id:"vpc_offering"])
        return "OK"
    }
    
    def pullVPCFromCloudStack(jobid) {
        
        
        def job = AsynchronousJobs.get(jobid)
        try { 
                        
            job.status = JobStatus.valueOf("RUNNING")
            job.startedAt = new Date()
            job.save(flush: true)
            
            HashMap<String,String> optional = new HashMap<String,String>();
            optional.put("listall", "true");
              
            def response = vpcServer().listVPCs(optional);   
            Console.print("response 1" + response)
            for(Iterator <VpcResponse> iter = response.vpcs.iterator(); iter.hasNext();) { 
                def data = iter.next();                
                VPC vpc = VPC.findByReferenceId(data.id)
                if(!vpc) {
                    println("domain 1" + data.domainId + "account 1" + data.account)
                    Domain domain = Domain.findByReferenceId(data.domainId);
                    vpc = new VPC()
                    vpc.referenceId = data.id
                    vpc.name = data.name
                    vpc.description = data.displayText
                    vpc.cidr = data.cidr
                    vpc.vpcOffering = VPCOffering.findByReferenceId(data.vpcOfferingId)
                    vpc.zone = Zone.findByReferenceZoneId(data.zoneId) 
                    vpc.account = Account.findWhere(accountIdentifier : data.account, domain : domain );                    
                    
                       
//                    // convert string date to date object. 
//                    Calendar createdDate = DatatypeConverter.parseDateTime(data.created.replace("'T'", 'T'));
//
//                    // create timestamp object for start date and end date.
//                    Timestamp createdTimestamp = new Timestamp(createdDate.getTimeInMillis()); 


                    vpc.created = new Date()
                    vpc.deleted = false
                    vpc.restartRequired = Boolean.parseBoolean(data.restartRequired)
                    vpc.state = data.state
                    vpc.networkDomain = data.networkDomain
                    vpc.save(flush: true)
                                        
                    if (!vpc.save()) {
                        vpc.errors.allErrors.each { println it }
                    } else {
                        def serviceList = VPCOfferingServiceList.findAllByVpcOffering(vpc.vpcOffering)

                        for(def service: serviceList) {
                            def vpcServiceList = new VPCServiceList()
                            vpcServiceList.service = service.service
                            vpcServiceList.provider = service.provider
                            vpcServiceList.vpc = vpc
                            vpcServiceList.created = new Date()
                            vpcServiceList.save(flush: true)
                        } 
                    }
                   
                } else if(vpc) {
                    vpc.state = data.state
                    vpc.networkDomain = data.networkDomain
                    vpc.save(flush: true)
                }
            }
            
            job.status = JobStatus.valueOf("COMPLETED")
            job.completedAt = new Date()
            job.save(flush: true)
            
        
        }catch (Exception ex) {
            
            job.status = JobStatus.valueOf("ERROR")
            job.completedAt = new Date()
            job.save(flush: true)
            
            ex.printStackTrace(System.err);
            throw ex;
        }
        
        
            
        
        
        
    }
    
    
    
    def list(zoneReferenceId, referenceId) {
        
        def user = springSecurityService.currentUser
        def role = springSecurityService.getPrincipal().getAuthorities()  
        def vpcList = ""
        if(role.iterator().next() == "ROLE_ADMIN" ) {
            def response = vpcServer().listVPCs();   
            for(Iterator <VpcResponse> iter = response.vpcs.iterator(); iter.hasNext();) { 
                def data = iter.next();                
                VPC vpc = VPC.findByReferenceId(data.id)
                if(vpc) {
                    vpc.state = data.state
                    vpc.networkDomain = data.networkDomain
                    vpc.save(flush: true)
                }
            }
            vpcList = VPC.withCriteria {
               eq("deleted", false)   
               if(referenceId) {
                   eq("referenceId", referenceId)
               }
           }
        } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {
            HashMap<String,String> optional = new HashMap<String,String>();
            optional.put("account", new String(user.account.accountIdentifier));
            optional.put("domainid", new String(user.account.domain.referenceId));
            def response = vpcServer().listVPCs(optional);        
            for(Iterator <VpcResponse> iter = response.vpcs.iterator(); iter.hasNext();) { 
                def data = iter.next();
                
                VPC vpc = VPC.findByReferenceId(data.id)
                if(vpc) {
                    vpc.state = data.state
                    vpc.networkDomain = data.networkDomain
                    vpc.save(flush: true)
                }
            }
        
            vpcList = VPC.withCriteria {
               eq("deleted", false)           
               eq("account", user.account) 
               if(zoneReferenceId && zoneReferenceId != "All") {
                   eq("zone", Zone.findByReferenceZoneId(zoneReferenceId))
               }
               if(referenceId) {
                   eq("referenceId", referenceId)
               }
           }
        }
        ArrayList<ArrayList<String>> vpcListArray = new ArrayList<ArrayList<String>>();
        
        for(def vpc: vpcList) {
            HashMap<String,String> item = new HashMap<String,String>();
            item.put("name", vpc.name);
            item.put("description", vpc.description);
            item.put("cidr", vpc.cidr);
            item.put("account", vpc.account.firstName);            
            item.put("state", vpc.state);
            item.put("zone", vpc.zone.aliasName);
            item.put("zoneReferenceId", vpc.zone.referenceZoneId);      
            item.put("zoneId", vpc.zone.id);                  
            item.put("networkDomain", vpc.networkDomain);
            item.put("referenceId", vpc.referenceId);
            item.put("vpcOffering", vpc.vpcOffering.name);
            item.put("vpcRestartRequred", vpc.restartRequired);
            def vpnOffer = MiscellaneousOffer.get(7);
            def offerCost = MiscellaneousOfferZoneCost.findWhere(miscellaneousOffer :vpnOffer, zone :vpc.zone)
            item.put("cost", offerCost ? offerCost.cost : 0.0);
            item.put("currency", ApplicationHolder.getApplication().config.billing.defaultCurrency);                                            
            vpcListArray.add(item)
        }
        
        return vpcListArray
    }
    
    def editVpc(requestData) {
        try {
                
            HashMap<String,String> optional = new HashMap<String,String>();
            optional.put("displaytext", requestData.description); 
                        
            def vpcEditResponse = vpcServer().updateVPC(requestData.id, requestData.name, optional)
            
             VPC vpc = VPC.findByReferenceId(requestData.id)
            vpc.name = requestData.name
            vpc.description = requestData.description
            vpc.save(flush: true);
            ArrayList<ArrayList<String>> vpcEditArrayList = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            vpcEditArrayList.add(item)
           
            return vpcEditArrayList     
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
    }
    
    
    def getVPCStats(zoneReferenceId, vpcId) {        
        def user = springSecurityService.currentUser
        def role = springSecurityService.getPrincipal().getAuthorities()  
        def aclCount = 0;
        def vmCount = 0;
        def tierCount = 0;
        def publicIPCount = 0;
        def staticNatCount = 0;    
        def vpnConnectionCount = 0;
        def s2sGatewayCount = 0;
        def vpcPrivateGatewayCount = 0;
        def s2sCustomerGatewayCount = 0; 
        def vpcList = VPC.withCriteria {
            eq("deleted", false)           
            eq("account", user.account) 
            if(zoneReferenceId && zoneReferenceId != "All") {
                eq("zone", Zone.findByReferenceZoneId(zoneReferenceId))
            }            
            if(vpcId) {
                eq("referenceId", vpcId)
            } 
            projections {
                count("id")
            }
        }     
        def vmList = VirtualMachine.withCriteria {
            eq("deleted", false)   
            eq("owner", user.account)
            if(zoneReferenceId && zoneReferenceId != "All") {
                eq("zone", Zone.findByReferenceZoneId(zoneReferenceId))
            }            
            if(vpcId && vpcId != "option") {
                eq("vpc", VPC.findByReferenceId(vpcId))
            } else {
                isNotNull("vpc")
            }
            
        }
        def tierList = Network.withCriteria {
            eq("deleted", false)   
            eq("account", user.account)
            if(zoneReferenceId && zoneReferenceId != "All") {
                eq("zone", Zone.findByReferenceZoneId(zoneReferenceId))
            }
            
            if(vpcId && vpcId != "option") {
                eq("vpc", VPC.findByReferenceId(vpcId))
            } else {
                isNotNull("vpc")
            }   
            
        }       
        def publicIPList = UserIPAddress.withCriteria {
            eq("account", user.account)
            if(zoneReferenceId && zoneReferenceId != "All") {
                eq("zone", Zone.findByReferenceZoneId(zoneReferenceId))
            }
            
            if(vpcId && vpcId != "option") {
                eq("vpc", VPC.findByReferenceId(vpcId))
            } else {
                isNotNull("vpc")
            }               
        }
        
        def staticNatList = UserIPAddress.withCriteria {
            eq("account", user.account)
            eq("removed", false)                        
            if(zoneReferenceId && zoneReferenceId != "All") {
                eq("zone", Zone.findByReferenceZoneId(zoneReferenceId))
            }
            
            if(vpcId && vpcId != "option") {
                eq("vpc", VPC.findByReferenceId(vpcId))
            } else {
                isNotNull("vpc")   
            } 
            and {
                eq("isStaticNat", true)
            }
                     
        }    
        
        def publicLBList = LoadBalancer.withCriteria {
            eq("account", user.account)
            if(zoneReferenceId && zoneReferenceId != "All") {
                eq("zone", Zone.findByReferenceZoneId(zoneReferenceId))
            }                          
            and {
                eq("scheme", "Public")
            }                
        } 
        def internalLBList = LoadBalancer.withCriteria {
            eq("account", user.account)
            if(zoneReferenceId && zoneReferenceId != "All") {
                eq("zone", Zone.findByReferenceZoneId(zoneReferenceId))
            }                          
            and {
                eq("scheme", "Internal")
            }                
        } 
        def publicLBCount = 0;
        def internalLBCount = 0;
        
        def vpnConnectionList = VPNConnection.withCriteria {
            eq("account", user.account)
            eq("deleted", false)
            if(zoneReferenceId && zoneReferenceId != "All") {
                eq("zone", Zone.findByReferenceZoneId(zoneReferenceId))
            }                          
            if(vpcId && vpcId != "option") {
                eq("vpc", VPC.findByReferenceId(vpcId))
            } else {
                isNotNull("vpc")   
            }                
        } 
        
        def s2sGatewayList = S2SVPNGateways.withCriteria {
            eq("account", user.account)
            eq("deleted", false)
            if(zoneReferenceId && zoneReferenceId != "All") {
                eq("zone", Zone.findByReferenceZoneId(zoneReferenceId))
            }                          
            if(vpcId && vpcId != "option") {
                eq("vpc", VPC.findByReferenceId(vpcId))
            } else {
                isNotNull("vpc")   
            } 
        }
        
        def vpcPrivateGWList = VPCGateways.withCriteria {
            eq("account", user.account)
            eq("deleted", false)            
            if(zoneReferenceId && zoneReferenceId != "All") {
                eq("zone", Zone.findByReferenceZoneId(zoneReferenceId))
            }                          
            if(vpcId && vpcId != "option") {
                eq("vpc", VPC.findByReferenceId(vpcId))
            } else {
                isNotNull("vpc")   
            } 
        }
        
        def s2sCustomerGatewayList =  S2SCustomerGateway.withCriteria {
            eq("account", user.account)
            eq("deleted", false)            
        }
        for(int i = 0 ; i < publicLBList.size(); i ++ ) {
            def currentPublicLB = publicLBList[i];
            if(vpcId == null || vpcId == "null" || vpcId == "") {
                if(currentPublicLB.netwotk.vpc) {
                    publicLBCount = publicLBCount + 1;
                } 
            } else {
                if((currentPublicLB.netwotk.vpc) && (currentPublicLB.netwotk.vpc.referenceId == vpcId)) {
                    publicLBCount = publicLBCount + 1;
                }
            } 
        }
        
        for(int i = 0 ; i < internalLBList.size(); i ++ ) {
            def currentInternalLB = internalLBList[i];
            if(vpcId == null || vpcId == "null" || vpcId == "") {
                if(currentInternalLB.netwotk.vpc) {
                    internalLBCount = internalLBCount + 1;
                }
            } else {
                if(currentInternalLB.netwotk.vpc.referenceId == vpcId) {
                    internalLBCount = internalLBCount + 1;
                }
            }
        }
        s2sCustomerGatewayCount = s2sCustomerGatewayList.size();
        vpcPrivateGatewayCount = vpcPrivateGWList.size();
        vpnConnectionCount = vpnConnectionList.size();
        s2sGatewayCount = s2sGatewayList.size();
        vmCount = vmList.size();
        tierCount = tierList.size();  
        publicIPCount = publicIPList.size();
        staticNatCount =  staticNatList.size();
        HashMap<String,String> optional = new HashMap<String,String>();
        optional.put("account", new String(user.account.accountIdentifier));
        optional.put("domainid", new String(user.account.domain.referenceId));
        if(vpcId) {
            optional.put("vpcid", vpcId);
        }         
        def response = networkAclServer().listNetworkACLLists(optional)      
        if(zoneReferenceId == null || zoneReferenceId == "null" || zoneReferenceId == "All") {
            aclCount = response.networkAclLists.size()                                    
        } else {          
            for(Iterator <NetworkAclListResponse> iter = response.networkAclLists.iterator(); iter.hasNext();) {                                
                def data = iter.next();
                def vpc = VPC.findByReferenceId(data.vpcId);
                if(vpc) {
                    if(vpc.zone.referenceZoneId == zoneReferenceId) {
                        aclCount = aclCount + 1;
                    }
                }                
            }                         
            aclCount = aclCount + 2;                                    
        }                 
        ArrayList<ArrayList<String>> vpcStatCount = new ArrayList<ArrayList<String>>();
        HashMap<String,String> item = new HashMap<String,String>();             
        item.put("vpc", vpcList[0]);
        item.put("networkAcl", aclCount);       
        item.put("vpcInstance", vmCount);       
        item.put("tier", tierCount);               
        item.put("publicIP", publicIPCount);               
        item.put("staticNat", staticNatCount);                      
        item.put("publicLB", publicLBCount);     
        item.put("internalLB", internalLBCount);     
        item.put("vpnConnection", vpnConnectionCount);             
        item.put("s2sGateway", s2sGatewayCount);     
        item.put("vpcPrivateGw", vpcPrivateGatewayCount);  
        item.put("s2sCustomerGateway", s2sCustomerGatewayCount);                                  
        vpcStatCount.add(item)        
        return vpcStatCount                
    }
    
    def listVPCOffering() {
                
        try {
            
            HashMap<String,String> optional = new HashMap<String,String>();
                       
            def response = vpcServer().listVPCOfferings(optional);
            
            for(Iterator <VpcOfferingResponse> iter = response.vpcOfferings.iterator(); iter.hasNext();) {
                def data = iter.next();
                
                VPCOffering vpcOffering = VPCOffering.findByReferenceId(data.id)
                if(!vpcOffering) {
                    vpcOffering = new VPCOffering()
                    vpcOffering.referenceId = data.id 
                    vpcOffering.name = data.name
                    vpcOffering.description = data.displayText 
                    vpcOffering.state = data.state
                    vpcOffering.isDefault = Boolean.parseBoolean(data.isDefault)
                    vpcOffering.save(flush: true)
                }
                
                for(Iterator <ServiceResponse> serviceIter = data.services.iterator(); serviceIter.hasNext();) {
                    def serviceData = serviceIter.next();                    
                    
                    def vpcOfferServiceList = new VPCOfferingServiceList()
                    vpcOfferServiceList.service = serviceData.name
                    
                    vpcOfferServiceList.provider = serviceData.serviceProvider
                    vpcOfferServiceList.vpcOffering = vpcOffering
                    vpcOfferServiceList.created = new Date()
                    vpcOfferServiceList.save(flush: true)
                }
            }
            ArrayList<ArrayList<String>> vpcOfferListArray = new ArrayList<ArrayList<String>>();     

            def vpcOfferList = VPCOffering.findAll()
            for(def vpcOffer: vpcOfferList) {

                    HashMap<String,String> item = new HashMap<String,String>();                
                    item.put("id", vpcOffer.id);
                    item.put("referenceId", vpcOffer.referenceId);
                    item.put("name", vpcOffer.name == "Default VPC offering" ? "Vpc VirtualRouter" : "Netscaler");                
                    vpcOfferListArray.add(item)
            } 
            return vpcOfferListArray; 
            
        } catch (Exception ex){
            throw ex
        }
    }
    
    
    def createVPC(requestData) {
        
        try {
        
            def user = springSecurityService.currentUser
            def role = springSecurityService.getPrincipal().getAuthorities()   


            HashMap<String,String> optional = new HashMap<String,String>();
            optional.put("account", new String(user.account.accountIdentifier));
            optional.put("domainid", new String(user.account.domain.referenceId));

    //        createVPC(String cidr, String displayText, String name,
    //            String vpcOfferingId, String zoneId, HashMap<String, String> optional)

            def response = vpcServer().createVPC(requestData.cidr, requestData.desc, requestData.name, requestData.vpcOffering, requestData.zone, optional);
             
            VPC vpc = new VPC()
            vpc.referenceId = response.id
            vpc.name = requestData.name
            vpc.description = requestData.desc
            vpc.state = "Creating"
            vpc.cidr = requestData.cidr
            vpc.vpcOffering = VPCOffering.findByReferenceId(requestData.vpcOffering) 
            vpc.zone = Zone.findByReferenceZoneId(requestData.zone) 
            vpc.account = user.account
            vpc.created = new Date()
            vpc.jobId = response.jobId
            vpc.networkDomain = requestData.networkDomain
            vpc.save(flush: true)
            
            def serviceList = VPCOfferingServiceList.findAllByVpcOffering(vpc.vpcOffering)
            
            for(def service: serviceList) {
                def vpcServiceList = new VPCServiceList()
                vpcServiceList.service = service.service
                vpcServiceList.provider = service.provider
                vpcServiceList.vpc = vpc
                vpcServiceList.created = new Date()
                vpcServiceList.save(flush: true)
            }
                
            ArrayList<ArrayList<String>> vpcResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            item.put("jobId", response.jobId);
            vpcResponse.add(item)

//            log.info("VPC: ${network.referenceId} Created added for account: ${user.account}") 

            return  vpcResponse
            
            
        } catch (CloudStackServerException ex) {

                ArrayList<ArrayList<String>> vpcResponse = new ArrayList<ArrayList<String>>();
                HashMap item = new HashMap();    
                item.put("result", GeneralConstants.RESULT_FAILURE);
                item.put("message", ex.localizedMessage.substring(4));
                vpcResponse.add(item)

                return vpcResponse    

        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }  
    }
    
    
    def getVPCCreateJob(jobId) {
        
        try {            
                        
            ArrayList<ArrayList<String>> jobResult = new ArrayList<ArrayList<String>>();  
            def jobResponse = asyncJobServer().queryAsyncJobResult(jobId)
            if(jobResponse.jobStatus == "0") {
                 
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.JOB_RESULT_PENDING);
                jobResult.add(item);
                
                return jobResult
                
            } else if(jobResponse.jobStatus == "1") {
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.RESULT_SUCCESS);
                jobResult.add(item);
                 
                return jobResult
                
            } else if(jobResponse.jobStatus == "2") {
                
                def vpc = VPC.findByJobId(jobId)
                def vpcServiceMapList = VPCServiceList.findAllByVpc(vpc) 
                for(def vpcService: vpcServiceMapList) {
                    vpcService.delete(flush:true)
                }
                vpc.delete(flush:true)
                
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.RESULT_FAILURE);
                item.put("message", jobResponse.errorText);  
                jobResult.add(item);
                
                return jobResult  
            }
                
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
    
    def restartVPC(requestData) {
                
        try {
           
            def response = vpcServer().restartVPC(requestData.vpcId);
                          
            ArrayList<ArrayList<String>> restartVpcResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            item.put("jobId", response.jobId);
            restartVpcResponse.add(item)

            return  restartVpcResponse
            
        } catch (CloudStackServerException ex) {

            ArrayList<ArrayList<String>> restartVpcResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_FAILURE);
            item.put("message", ex.localizedMessage.substring(4));
            restartVpcResponse.add(item)

            return restartVpcResponse    

        } catch (Exception ex) {
            
            ArrayList<ArrayList<String>> restartVpcResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_FAILURE);
            item.put("message", ex.localizedMessage);
            restartVpcResponse.add(item)

            return restartVpcResponse    
        } 
    }
    
    def restartVPCJob(jobId) {
        try {            
                        
            ArrayList<ArrayList<String>> jobResult = new ArrayList<ArrayList<String>>();  
            def jobResponse = asyncJobServer().queryAsyncJobResult(jobId)
            if(jobResponse.jobStatus == "0") {
                
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.JOB_RESULT_PENDING);
                jobResult.add(item);
                
                return jobResult
                
            } else if(jobResponse.jobStatus == "1") {
                                
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.RESULT_SUCCESS);
                jobResult.add(item);
                
                return jobResult
                
            } else if(jobResponse.jobStatus == "2") {
                
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.RESULT_FAILURE);
                jobResult.add(item);
                
                return jobResult  
            }
                
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
    
    def deleteVPC(requestData) {
        
        try {
           
            def response = vpcServer().deleteVPC(requestData.vpcId);
                          
            
            def vpc = VPC.findByReferenceId(requestData.vpcId)
            vpc.jobId = response.jobId;
            vpc.save(flush:true)
            ArrayList<ArrayList<String>> deleteVpcResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            item.put("jobId", response.jobId);
            deleteVpcResponse.add(item)

//            log.info("VPC: ${network.referenceId} Created added for account: ${user.account}") 

            return  deleteVpcResponse
            
        } catch (CloudStackServerException ex) {

            ArrayList<ArrayList<String>> deleteVpcResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_FAILURE);
            item.put("message", ex.localizedMessage.substring(4));
            deleteVpcResponse.add(item)

            return deleteVpcResponse    

        } catch (Exception ex) {
            
            ArrayList<ArrayList<String>> deleteVpcResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_FAILURE);
            item.put("message", ex.localizedMessage);
            deleteVpcResponse.add(item)

            return deleteVpcResponse    
        }  
    }
    
    def deleteVPCJob(jobId) {
        try {            
                        
            ArrayList<ArrayList<String>> jobResult = new ArrayList<ArrayList<String>>();  
            def jobResponse = asyncJobServer().queryAsyncJobResult(jobId)
            if(jobResponse.jobStatus == "0") {
                
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.JOB_RESULT_PENDING);
                jobResult.add(item);
                
                return jobResult
                
            } else if(jobResponse.jobStatus == "1") {
                                
                def vpc = VPC.findByJobId(jobId)
                vpc.deleted = true;
                def vpcServiceMapList = VPCServiceList.findAllByVpc(vpc) 
                for(def vpcService: vpcServiceMapList) {
                    vpcService.delete(flush:true)
                }
                vpc.save(flush:true)
                
                def vpcTierList = Network.findAllByVpc(vpc)
                for(def vpcTier: vpcTierList) {
                       
                    vpcTier.deleted = true
                    vpcTier.removedDate = new Date()
                    vpcTier.save(flush: true)  
                }
                
                def vpcIpList = UserIPAddress.findAllByVpc(vpc)
                for(def userIPAddress : vpcIpList) {
                    
                    userIPAddress.virtualMachine = null
                    userIPAddress.network = null
                    userIPAddress.account = null
                    userIPAddress.user = null
                    userIPAddress.staticNatVirtualMachine = null
                    userIPAddress.isSourceNat =  false
                    userIPAddress.isStaticNat = false
                    userIPAddress.removed = true
                    userIPAddress.vpc = null
                    userIPAddress.state = "Free"
                    userIPAddress.revokeDate = new Date()
                    userIPAddress.save(flush: true);  

                    def loadBalancerList = LoadBalancer.findAllByUserIPAddress(userIPAddress)
                    for(def loadBalancer: loadBalancerList) {
                        def lbVmList = LoadBalancerVmMap.findAllWhere(loadBalancer: loadBalancer)
                        for(def lbVm : lbVmList) {
                           lbVm.delete(flush: true)
                        }
                        loadBalancer.delete(flush: true)
                        loadBalancer.firewallRules?.delete(flush: true)
                    }

                    def portForwardingList = PortForwarding.findAllByUserIPAddress(userIPAddress)
                    for(def portForwarding: portForwardingList) {
                        portForwarding.delete(flush: true)
                        portForwarding.firewallRules?.delete(flush: true)
                    }

                    def vpnList = RemoteAccessVpn.findAllByPublicIPAddress(userIPAddress)
                    for(def vpn: vpnList) {
                        vpn.delete(flush: true)                                        
                    }
                }
                
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.RESULT_SUCCESS);
                jobResult.add(item);
                
                return jobResult
                
            } else if(jobResponse.jobStatus == "2") {
                
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.RESULT_FAILURE);
                jobResult.add(item);
                
                return jobResult  
            }
                
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
    
    def createTier(requestData) {
     
        try {
            def user = springSecurityService.currentUser
            def role = springSecurityService.getPrincipal().getAuthorities()   
 
            HashMap<String,String> optional = new HashMap<String,String>();
            optional.put("account", new String(user.account.accountIdentifier));
            optional.put("domainid", new String(user.account.domain.referenceId));
            optional.put("vpcid", requestData.vpcId);
            optional.put("netmask", requestData.netmask);
            optional.put("gateway", requestData.gateway);
            if(requestData.aclid) {
                optional.put("aclid", requestData.aclid);
            }
            def vpc = VPC.findByReferenceId(requestData.vpcId)

            def response = networkServer().createNetwork(requestData.name ,requestData.name, requestData.networkOffer, vpc.zone.referenceZoneId, optional) 

            def network = new Network()
            network.referenceId = response.id
            network.account = user.account
            network.createdDate = new Date()
            network.hasFirstIp = false
            network.description = requestData.name
            network.name = requestData.name
            network.networkOffer = NetworkOffer.findByNetworkReferenceId(requestData.networkOffer)
            network.type = NetworkOffer.findByNetworkReferenceId(requestData.networkOffer)?.guestIpType
            network.zone = vpc.zone
            network.vpc = vpc
            
            
            def tierType = "";                    
            def web = NetworkOfferServiceList.withCriteria {
                eq('networkOffer', network.networkOffer)
                'in'("service",["Lb"])
            }

            def app = NetworkOfferServiceList.withCriteria {
                eq('networkOffer', network.networkOffer)
                'in'("provider",["InternalLbVm"])
            }

            if(app) {
               tierType = "APP" 
            } else if(web) {
                tierType = "WEB"
            } else {
                tierType = "DB"
            }

            network.tierType = tierType
            
            network.save(flush: true)

            ArrayList<ArrayList<String>> networkResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            networkResponse.add(item)

            log.info("Tier: ${network.referenceId} Created added for account: ${user.account}") 

            return  networkResponse
        } catch (CloudStackServerException ex) {
            
            ArrayList<ArrayList<String>> networkResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_FAILURE);
            item.put("message", ex.localizedMessage.substring(4));
            networkResponse.add(item)

            return networkResponse    
            
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }  
        
    }
    
    
    def listTier(vpcId) {
        try {
               
            def user = springSecurityService.currentUser
            def role = springSecurityService.getPrincipal().getAuthorities()   

            def networkList;
            ArrayList<ArrayList<String>> networkListResponse = new ArrayList<ArrayList<String>>();
            if(role.iterator().next() == "ROLE_ADMIN" ) {
                networkList = Network.withCriteria {
                    eq("deleted", false)           
                    if(vpcId) {
                        eq("vpc", VPC.findByReferenceId(vpcId))
                    } else {
                        isNotNull("vpc")
                    }
                }

                for(int i = 0; i < networkList.size(); i++) {
                    def network = networkList[i];                
                    HashMap item = new HashMap();    
                    item.put("referenceId", network.referenceId);
                    item.put("id", network.id);
                    item.put("name", network.name);
                    item.put("description", network.description);
                    item.put("networkOffer", network.networkOffer.name);
                    item.put("networkOfferId", network.networkOffer.networkReferenceId);
                    item.put("zone", network.zone.aliasName);

                    def supportedService = "";

                    def networkOfferServiceList = NetworkOfferServiceList.findAllWhere(networkOffer: network.networkOffer)
                    for(def service : networkOfferServiceList) {
                        supportedService += supportedService.size() == 0 ? service.service : ","+service.service
                    }
                    def web = NetworkOfferServiceList.withCriteria {
                        eq('networkOffer', network.networkOffer)
                        'in'("service",["Lb"])
                    }

                    def app = NetworkOfferServiceList.withCriteria {
                        eq('networkOffer', network.networkOffer)
                        'in'("provider",["InternalLbVm"])
                    }
                    def tierType = "";
                    if(app) {
                       tierType = "APP" 
                    } else if(web) {
                        tierType = "WEB"
                    } else {
                        tierType = "DB"
                    }
                    item.put("tierType", tierType);
                    item.put("user", network.account.firstName);                
                    item.put("supportedService", supportedService);
    //                item.put("networkDomain", data.networkDomain);
                    item.put("gateway", network.gateway);
                    item.put("cidr", network.cidr);
                    item.put("state", network.state);
                    item.put("type", network.type);
                    item.put("account", network.account.email);
                    item.put("trafficType", network.trafficType);
    //                item.put("aclId", data.aclId); 
    //                item.put("isPersistent", data.isPersistent);
    //                item.put("restartRequired", data.networkRestartRequired);
                    item.put("vpcId", network?.vpc.referenceId);
                    item.put("vpcName", network?.vpc.name);
                    networkListResponse.add(item);  
                }

            } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {
                Console.print("Network idd 7")    
                HashMap<String,String> optional = new HashMap<String,String>();
                optional.put("vpcid", vpcId);                  
                def response = networkServer().listNetworks(optional);                
                
                for(Iterator <NetworkResponse> iter = response.networks.iterator(); iter.hasNext();) {
                    def data = iter.next();
                    Console.print("Network id 6   :" + data.id)
                    def network = Network.findByReferenceId(data.id)
                    if(network) {
                        network.cidr = data.cidr
                        network.gateway = data.gateway
                        network.state = data.state
                        network.type = data.type
                        network.trafficType = data.trafficType
                        network.name = data.name;
                        network.description = data.displayText;                    

                        def tierType = "";

                        def web = NetworkOfferServiceList.withCriteria {
                            eq('networkOffer', network.networkOffer)
                            'in'("service",["Lb"])
                        }

                        def app = NetworkOfferServiceList.withCriteria {
                            eq('networkOffer', network.networkOffer)
                            'in'("provider",["InternalLbVm"])
                        }

                        if(app) {
                           tierType = "APP" 
                        } else if(web) {
                            tierType = "WEB"
                        } else {
                            tierType = "DB"
                        }                                                            



                        network.tierType = tierType
                        network.networkOffer = NetworkOffer.findByNetworkReferenceId(data.networkOfferingId)
                        network.networkDomain = data.domain
                        network.save(flush: true)


                        HashMap item = new HashMap();    
                        item.put("referenceId", network.referenceId);
                        item.put("id", network.id);
                        item.put("name", network.name);
                        item.put("description", network.description);
                        item.put("networkOffer", network.networkOffer.name);
                        item.put("networkOfferId", network.networkOffer.networkReferenceId);
                        item.put("zone", network.zone.aliasName);
                        item.put("tierType", tierType);
                        def supportedService = "";

                        def networkOfferServiceList = NetworkOfferServiceList.findAllWhere(networkOffer: network.networkOffer)
                        for(def service : networkOfferServiceList) {

                            supportedService += supportedService.size() == 0 ? service.service : ","+service.service
                        }

                        item.put("supportedService", supportedService);
                        item.put("networkDomain", data.domain);
                        item.put("gateway", network.gateway);
                        item.put("cidr", network.cidr);
                        item.put("state", network.state);
                        item.put("type", network.type);
                        item.put("account", network.account.email);
                        item.put("trafficType", network.trafficType);
    //                    item.put("aclName", data.networkTrafficType);
                        item.put("aclId", data.aclId);
    //                    item.put("vlan", data.networkTrafficType);
                        item.put("isPersistent", data.isPersistent);
                        item.put("restartRequired", data.restartRequired);
                        item.put("vpcId", network?.vpc.referenceId);
                        item.put("vpcName", network?.vpc.name);
                        networkListResponse.add(item); 
                    }
                }  
            }
            return networkListResponse
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }  
    }
    
    def getVpcIpAddressesList(zoneReferenceId, vpcId) {
        try {
            
            def vpc = VPC.findByReferenceId(vpcId)
            
            HashMap<String,String> optional = new HashMap<String,String>();
            optional.put("forvirtualnetwork", "true");
            optional.put("listAll", "true"); 
            optional.put("vpcid", vpcId);
            
            def response = addressServer().listPublicIpAddresses(optional);       
            
            ArrayList<ArrayList<String>> networkIPListResponse = new ArrayList<ArrayList<String>>();
                        
            for(Iterator <IpAddressResponse> iter = response.ipAddresses.iterator(); iter.hasNext();) { 
                def data = iter.next();                
                UserIPAddress userIPAddress = UserIPAddress.findByIpReferenceId(data.id)
                if(!userIPAddress) {
                    userIPAddress = new UserIPAddress(); 

                    userIPAddress.account = Account.findByAccountIdentifier(data.account)
                    userIPAddress.user = User.findByUsername(data.account)
                    userIPAddress.ipReferenceId = data.id
                    userIPAddress.publicIPAddress = data.ipAddress
                    userIPAddress.acquireDate = new Date()
                    userIPAddress.virtualMachine = null
                    userIPAddress.network = Network.findByReferenceId(data.associatedNetworkid)
                    userIPAddress.state = data.state
                    userIPAddress.staticNatVirtualMachine = VirtualMachine.findByReferenceId(data.virtualMachineId)
                    userIPAddress.isSourceNat =  Boolean.parseBoolean(data.isSourceNat)
                    userIPAddress.isStaticNat = Boolean.parseBoolean(data.isStaticNat)
                    userIPAddress.vpc = VPC.findByReferenceId(data.vpcId)
                    if(userIPAddress.isSourceNat == true) {
                        userIPAddress.isFirstIp = true
                    }
                    userIPAddress.zone = Zone.findByReferenceZoneId(data.zoneId)
                    userIPAddress.save(flush: true)
                    if (!userIPAddress.save()) {
                        userIPAddress.errors.allErrors.each { println it }
                    }
                    
                } else { 
                    userIPAddress.removed = false
                    userIPAddress.virtualMachine = null

                    userIPAddress.network = Network.findByReferenceId(data.associatedNetworkid)
                    userIPAddress.account = Account.findByAccountIdentifier(data.account)
                    userIPAddress.user = User.findByUsername(data.account)
                    userIPAddress.state = data.state
                    userIPAddress.staticNatVirtualMachine = VirtualMachine.findByReferenceId(data.virtualMachineId)
                    userIPAddress.isSourceNat =  Boolean.parseBoolean(data.isSourceNat)
                    userIPAddress.isStaticNat = Boolean.parseBoolean(data.isStaticNat)

                    userIPAddress.vpc = VPC.findByReferenceId(data.vpcId)
                    if(userIPAddress.isSourceNat == true) {
                        userIPAddress.isFirstIp = true
                    }
                    userIPAddress.save(flush: true)
                }               
                def isEnabledVPN = RemoteAccessVpn.findByNetwork(Network.findByReferenceId(data.associatedNetworkid))? true: false;
                HashMap item = new HashMap();                     
                 
                def vmList = "" 
                HashMap<String,String> vmListMap = new HashMap<String,String>(); 
                def loadBalancerList = LoadBalancer.findAllByUserIPAddress(UserIPAddress.findByIpReferenceId(data.id))
                def pfList = PortForwarding.findAllByUserIPAddress(UserIPAddress.findByIpReferenceId(data.id))                                
                def vmString = ""
                for(def lb: loadBalancerList) {     
                    def lbVmList = LoadBalancerVmMap.findAllWhere(loadBalancer: lb)
                    for(def lbVm : lbVmList) {   
                        boolean blnExists = vmListMap.containsKey(lbVm.virtualMachine.referenceId);
                        if(blnExists.toString() == "false" || blnExists == false) {
                            vmList += vmList.size() == 0 ? vmList : ",";
                            vmList += lbVm.virtualMachine.displayName
                        }                                                
                        vmListMap.put(lbVm.virtualMachine.referenceId ,"referenceId");
                    }                    
                }                                                                               
                for(def pf: pfList) {       
                    boolean blnExists = vmListMap.containsKey(pf.virtualMachine.referenceId);
                    if(blnExists.toString() == "false" || blnExists == false) {
                        vmList += vmList.size() == 0 ? vmList : ",";
                        vmList += pf.virtualMachine.displayName    
                    }                         
                    vmListMap.put(pf.virtualMachine.referenceId ,"referenceId");
                } 
                if(data.isStaticNat == true || data.isStaticNat == "true") {
                    boolean blnExists = vmListMap.containsKey(data.virtualMachineId);
                    def staticVm = VirtualMachine.findByReferenceId(data.virtualMachineId)
                    if(blnExists.toString() == "false" || blnExists == false) {                        
                        vmList = staticVm.displayName
                    }
                    vmListMap.put(data.virtualMachineId ,"referenceId");
                }
                item.put("vms", vmList);                                
                item.put("id", data.id);
                item.put("result", "OK");                
                item.put("referenceId", data.id);                 
                item.put("associateNeworkReferenceId", data.associatedNetworkid);
                item.put("network", Network.findByReferenceId(data.associatedNetworkid)?.name);
                item.put("ip", data.ipAddress);
                item.put("isSourceNat", data.isSourceNat);
                item.put("isStaticNat", data.isStaticNat);
                item.put("networkReferenceId", data.networkId);
                item.put("state", data.state);
                item.put("vmDisplayName", data.virtualMachineDisplayName);
                item.put("vmReferenceId", data.virtualMachineId);
                item.put("vlanId", data.vlanId);
                item.put("vlanName", data.vlanName);
                item.put("vpc", VPC.findByReferenceId(data.vpcId)?.name);
                item.put("account", userIPAddress.account.firstName);                                
                item.put("zone", Zone.findByReferenceZoneId(data.zoneId)?.aliasName);
                item.put("vpnEnabled", RemoteAccessVpn.findByPublicIPAddress(UserIPAddress.findByIpReferenceId(data.id))? true: false);
                item.put("isEnabledVPN", isEnabledVPN);      
                
                item.put("isVPCLBAdded", LoadBalancer.findAllByUserIPAddress(UserIPAddress.findByIpReferenceId(data.id)) ? true : false);
                item.put("isVPCPFAdded", PortForwarding.findAllByUserIPAddress(UserIPAddress.findByIpReferenceId(data.id)) ? true : false);
                
                
                networkIPListResponse.add(item)                
            }
            return networkIPListResponse
        } catch(Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    } 
    
    
    def restartTier(requestData) {
                
        try {
           
           
            HashMap<String,String> optional = new HashMap<String,String>();
            optional.put("cleanup", Boolean.toString(requestData.cleanup));
            
            def response = networkServer().restartNetwork(requestData.tierId, optional);
                          
            ArrayList<ArrayList<String>> restartTierResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            item.put("jobId", response.jobId);
            restartTierResponse.add(item) 

            return  restartTierResponse
            
        } catch (CloudStackServerException ex) {

            ArrayList<ArrayList<String>> restartTierResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_FAILURE);
            item.put("message", ex.localizedMessage.substring(4));
            restartTierResponse.add(item)

            return restartTierResponse    

        } catch (Exception ex) {
            
            ArrayList<ArrayList<String>> restartTierResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_FAILURE);
            item.put("message", ex.localizedMessage);
            restartTierResponse.add(item)

            return restartTierResponse    
        } 
    }
    
    def restartTierJob(jobId) {
        try {            
                        
            ArrayList<ArrayList<String>> jobResult = new ArrayList<ArrayList<String>>();  
            def jobResponse = asyncJobServer().queryAsyncJobResult(jobId)
            if(jobResponse.jobStatus == "0") {
                
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.JOB_RESULT_PENDING);
                jobResult.add(item);
                
                return jobResult
                
            } else if(jobResponse.jobStatus == "1") {
                                
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.RESULT_SUCCESS);
                jobResult.add(item);
                
                return jobResult
                
            } else if(jobResponse.jobStatus == "2") {
                
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.RESULT_FAILURE);
                jobResult.add(item);
                
                return jobResult  
            }
                
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
    
    def removeTier(requestData) {
        
        try {
           
            def response = networkServer().deleteNetwork(requestData.tierId)
                     
            def tier = Network.findByReferenceId(requestData.tierId)
            
            ArrayList<ArrayList<String>> deleteTierResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            item.put("jobId", response.jobId);
            item.put("tierId", tier.referenceId);
            deleteTierResponse.add(item)

//            log.info("VPC: ${network.referenceId} Created added for account: ${user.account}") 

            return  deleteTierResponse
            
        } catch (CloudStackServerException ex) {

            ArrayList<ArrayList<String>> deleteTierResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_FAILURE);
            item.put("message", ex.localizedMessage.substring(4));
            deleteTierResponse.add(item)

            return deleteTierResponse    

        } catch (Exception ex) {
            
            ArrayList<ArrayList<String>> deleteTierResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_FAILURE);
            item.put("message", ex.localizedMessage);
            deleteTierResponse.add(item)

            return deleteTierResponse    
        }  
    }
    
    def deleteTierJob(requestData) {
        try {            
                        
            ArrayList<ArrayList<String>> jobResult = new ArrayList<ArrayList<String>>();  
            def jobResponse = asyncJobServer().queryAsyncJobResult(requestData.jobId)
            if(jobResponse.jobStatus == "0") {
                
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.JOB_RESULT_PENDING);
                jobResult.add(item);
                
                return jobResult
                
            } else if(jobResponse.jobStatus == "1") {
                                
                def tier = Network.findByReferenceId(requestData.tierId)
                tier.deleted = true;
                tier.removedDate = new Date();
                tier.save(flush:true)
                
                def internalLbList = LoadBalancer.findAllWhere(netwotk: tier, scheme:"Internal")
                for(def internalLb: internalLbList) {
                    def lbVmList = LoadBalancerVmMap.findAllWhere(loadBalancer: internalLb)
                    for(def lbVm : lbVmList) {
                       lbVm.delete(flush: true)
                    }
                    internalLb.delete(flush: true)
                    internalLb.firewallRules?.delete(flush: true)
                }
                                                
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.RESULT_SUCCESS);
                jobResult.add(item);
                
                return jobResult
                
            } else if(jobResponse.jobStatus == "2") {
                
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.RESULT_FAILURE);
                jobResult.add(item);
                
                return jobResult  
            }
                
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
    
    def createS2SVPN(requestData) {
        
        try {   
            
            def user = springSecurityService.currentUser
                        
            def vpc = VPC.findByReferenceId(requestData.vpcId)
                
            def response = vpnServer().createVpnGateway( vpc.referenceId);     

            ArrayList<ArrayList<String>> s2svpnResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            item.put("jobId", response.jobId);
            s2svpnResponse.add(item)

            return s2svpnResponse;
            
        } catch (CloudStackServerException ex) {
            
            ArrayList<ArrayList<String>> s2svpnResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_FAILURE);
            item.put("message", ex.localizedMessage.substring(4));
            s2svpnResponse.add(item)

            return s2svpnResponse    
            
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
    }
    
    def removeS2SVPN(requestData) {
        
        try {   
                                
            def response = vpnServer().deleteVpnGateway(requestData.vpnId);     

            ArrayList<ArrayList<String>> s2svpnResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            item.put("jobId", response.jobId);
            s2svpnResponse.add(item)

            return s2svpnResponse;
            
        } catch (CloudStackServerException ex) {
            
            ArrayList<ArrayList<String>> s2svpnResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_FAILURE);
            item.put("message", ex.localizedMessage.substring(4));
            s2svpnResponse.add(item)

            return s2svpnResponse    
            
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
    }
    
    
    def listS2SVPN(zoneReferenceId, vpcId) {
        
         try {   
            def vpc = VPC.findByReferenceId(vpcId)       
            def user = springSecurityService.currentUser
            def role = springSecurityService.getPrincipal().getAuthorities()  

            HashMap<String,String> optional = new HashMap<String,String>();
            def response = "";
            
            if(role.iterator().next() == "ROLE_ADMIN" ) {            
                if(vpcId) {
                    optional.put("vpcId", vpcId);
                }
            } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") { 
                optional.put("account", new String(user.account.accountIdentifier));
                optional.put("domainid", new String(user.account.domain.referenceId));
                if(vpcId) {
                    optional.put("vpcId", vpcId);
                }  
            }                                  

            response = vpnServer().listVpnGateways(optional);
            ArrayList<ArrayList<String>> listVPNResponse = new ArrayList<ArrayList<String>>();
            for(Iterator <VpnGatewayResponse> iter = response.vpnGateways.iterator(); iter.hasNext();) { 
                def data = iter.next();                
                S2SVPNGateways s2svpn = S2SVPNGateways.findByReferenceId(data.id)
                if(!s2svpn) {
                    s2svpn = new S2SVPNGateways()
                    s2svpn.vpc = VPC.findByReferenceId(data.vpcId)          
                    s2svpn.referenceId = data.id.toString()
                    s2svpn.userIPAddress = UserIPAddress.findByPublicIPAddress(data.publicIp.toString())
                    s2svpn.createdDate = new Date()
                    s2svpn.zone = s2svpn.vpc.zone
                    s2svpn.account = user.account
                    s2svpn.save(flush:true)
                    if (!s2svpn.save()) {
                        s2svpn.errors.allErrors.each { println it }
                    }                    
                }
            }
            
            def vpnList = ""
            
            if(role.iterator().next() == "ROLE_ADMIN" ) {            
                 vpnList = S2SVPNGateways.withCriteria {                
                     eq("deleted", false)                
                     if(zoneReferenceId && zoneReferenceId != "All") {
                         eq("zone", Zone.findByReferenceZoneId(zoneReferenceId))
                     }                          
                     if(vpcId && vpcId != "option") {
                         eq("vpc", VPC.findByReferenceId(vpcId))
                     } else {
                         isNotNull("vpc")   
                     } 
                 } 
            } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {                 
                vpnList = S2SVPNGateways.withCriteria {
                    eq("account", user.account)
                    eq("deleted", false)                
                    if(zoneReferenceId && zoneReferenceId != "All") {
                        eq("zone", Zone.findByReferenceZoneId(zoneReferenceId))
                    }                          
                    if(vpcId && vpcId != "option") {
                        eq("vpc", VPC.findByReferenceId(vpcId))
                    } else {
                        isNotNull("vpc")   
                    }             
                } 
            }                               
            for(def s2svpn: vpnList) {
                HashMap<String,String> item = new HashMap<String,String>();                
                if(s2svpn.vpc.deleted == false || s2svpn.vpc.deleted == "false") {
                    item.put("id", s2svpn.referenceId);
                    item.put("ip", s2svpn.userIPAddress.publicIPAddress);
                    item.put("vpc", s2svpn.vpc.name);
                    item.put("zone", s2svpn.vpc.zone.aliasName);
                    item.put("account", s2svpn.account.firstName);                
                    listVPNResponse.add(item);
                }                
            }            
            return listVPNResponse;
            
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
        
    }
    
    def getS2SVPNJob(jobId) {
        
        try {            
                        
            ArrayList<ArrayList<String>> jobResult = new ArrayList<ArrayList<String>>();  
            def jobResponse = asyncJobServer().queryAsyncJobResult(jobId)
            if(jobResponse.jobStatus == "0") {
                
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.JOB_RESULT_PENDING);
                jobResult.add(item);
                
                return jobResult
                
            } else if(jobResponse.jobStatus == "1") {
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.RESULT_SUCCESS);
                jobResult.add(item);
                
                return jobResult
                
            } else if(jobResponse.jobStatus == "2") {
                               
                
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.RESULT_FAILURE);
                jobResult.add(item);
                
                return jobResult  
            }
                
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
    def getRemoveS2SVPNJob(jobId, vpnId) {
        
        try {            
                        
            ArrayList<ArrayList<String>> jobResult = new ArrayList<ArrayList<String>>();  
            def jobResponse = asyncJobServer().queryAsyncJobResult(jobId)
            if(jobResponse.jobStatus == "0") {
                
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.JOB_RESULT_PENDING);
                jobResult.add(item);
                
                return jobResult
                
            } else if(jobResponse.jobStatus == "1") {
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.RESULT_SUCCESS);
                jobResult.add(item);
                
                S2SVPNGateways s2svpn = S2SVPNGateways.findByReferenceId(vpnId)
                s2svpn.deleted = true;
                s2svpn.save(flush: true)
                return jobResult
                
            } else if(jobResponse.jobStatus == "2") {
                               
                
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.RESULT_FAILURE);
                jobResult.add(item);
                
                return jobResult  
            }
                
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
    
    
    
    def acquireIpForVpc(requestData) {
        
        try {   
            
            def user = springSecurityService.currentUser
                        
            def vpc = VPC.findByReferenceId(requestData.vpcId)
            
            def invoice = Invoice.findWhere(account: user.account, status: InvoiceStatus.values()[6])
            
            def userip = UserIPAddress.findWhere(vpc:vpc, isSourceNat:true)
            
            if(userip && !invoice) {
                
                ArrayList<ArrayList<String>> vpcIPAcquireResponse = new ArrayList<ArrayList<String>>();
                HashMap item = new HashMap();    
                item.put("result", "HASFIRSTIP");
                vpcIPAcquireResponse.add(item)

                return vpcIPAcquireResponse;
                
            } else {
                HashMap<String,String> optional = new HashMap<String,String>();
                optional.put("vpcid", vpc.referenceId);

                def response = addressServer().associateIpAddress(optional);     

                ArrayList<ArrayList<String>> vpcIPAcquireResponse = new ArrayList<ArrayList<String>>();
                HashMap item = new HashMap();    
                item.put("result", GeneralConstants.RESULT_SUCCESS);
                item.put("jobId", response.jobId);
                item.put("vpcId", vpc.referenceId);
                item.put("ipId", response.id);
                
                vpcIPAcquireResponse.add(item)

                return vpcIPAcquireResponse;

                log.info("IP Acquired for Vpc: ${vpc.referenceId}") 
            }
            
            
        } catch (CloudStackServerException ex) {
            
            ArrayList<ArrayList<String>> vpcIPAcquireResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_FAILURE);
            item.put("message", ex.localizedMessage.substring(4));
            vpcIPAcquireResponse.add(item)

            return vpcIPAcquireResponse    
            
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
    }
    
    def releaseIpForNetwork(requestData) {
        try {
                                    
            def response;
            
            def userIPAddress = UserIPAddress.findByIpReferenceId(requestData.ipId)   
            
            if(userIPAddress.zone.networkType == "Basic") {
                response = nicServer().removeIpFromNic(requestData.ipId) 
            } else  {
               response = addressServer().disassociateIpAddress(requestData.ipId);   
            }
                               
            userIPAddress.jobId = response.jobId
            userIPAddress.save(flush: true);    
            
            ArrayList<ArrayList<String>> releseIPResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            item.put("jobId", response.jobId);
            releseIPResponse.add(item)

            return releseIPResponse 
            
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
    }
        
    def releaseIpJobForNetwork(jobId) {
        try {            
            
                       
            ArrayList<ArrayList<String>> jobResult = new ArrayList<ArrayList<String>>();  
            def jobResponse = asyncJobServer().queryAsyncJobResult(jobId)
            
            def userIPAddress = UserIPAddress.findByJobId(jobId)
            
            if(jobResponse.jobStatus == "0") {
                        
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.JOB_RESULT_PENDING);
                jobResult.add(item);
                
                return jobResult
                
            } else if(jobResponse.jobStatus == "1") {
                
                userIPAddress.virtualMachine = null
                userIPAddress.network = null
                userIPAddress.account = null
                userIPAddress.user = null
                userIPAddress.staticNatVirtualMachine = null
                userIPAddress.isSourceNat =  false
                userIPAddress.isStaticNat = false
                userIPAddress.removed = true
                userIPAddress.vpc = null
                userIPAddress.state = "Free"
                userIPAddress.revokeDate = new Date()
                userIPAddress.save(flush: true);  
                
                def loadBalancerList = LoadBalancer.findAllByUserIPAddress(userIPAddress)
                for(def loadBalancer: loadBalancerList) {
                    def lbVmList = LoadBalancerVmMap.findAllWhere(loadBalancer: loadBalancer)
                    for(def lbVm : lbVmList) {
                       lbVm.delete(flush: true)
                    }
                    loadBalancer.delete(flush: true)
                    loadBalancer.firewallRules.delete(flush: true)
                }
                
                def portForwardingList = PortForwarding.findAllByUserIPAddress(userIPAddress)
                for(def portForwarding: portForwardingList) {
                    portForwarding.delete(flush: true)
                    portForwarding.firewallRules.delete(flush: true)
                }
                
                def vpnList = RemoteAccessVpn.findAllByPublicIPAddress(userIPAddress)
                for(def vpn: vpnList) {
                    vpn.delete(flush: true)                                        
                }
                
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.RESULT_SUCCESS);
                jobResult.add(item);

                return jobResult
                
            } else if(jobResponse.jobStatus == "2") {
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.RESULT_FAILURE);
                jobResult.add(item);
                
                return jobResult  
            }
                
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
    }
    
    
    def acquireIpJobForVpc(requestData) {
        try {            
            
            def user = springSecurityService.currentUser
                      
            ArrayList<ArrayList<String>> jobResult = new ArrayList<ArrayList<String>>();  
            def jobResponse = addressServer().addIpJobResult(requestData.jobId)
            if(jobResponse.jobStatus == "0") {
                
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.JOB_RESULT_PENDING);
                jobResult.add(item);
                
                return jobResult
                
            } else if(jobResponse.jobStatus == "1") {
                
                def vpc = VPC.findByReferenceId(requestData.vpcId)
                
                //                Console.print("jobResponse.secondaryIpId" + jobResponse.secondaryIpId)
                //                Console.print("jobResponse.secondaryIp" + jobResponse.secondaryIp)
               
                def invoice = Invoice.findWhere(account: user.account, status: InvoiceStatus.values()[6])
                def userip = UserIPAddress.findWhere(vpc:vpc, isSourceNat:true)
                
                if(userip && invoice) {

                    def ipOffer = MiscellaneousOffer.get(2);
                    def ipBillableItem = BillableItem.get(12)
                    if(ipBillableItem.enabled == true || ipBillableItem.enabled == "true") {

                        def newInvoiceItem = new InvoiceItem()
                        newInvoiceItem.billableItem = ipBillableItem
                        newInvoiceItem.invoice = invoice
                        newInvoiceItem.taxPercent = ipBillableItem.tax.percentage
                        newInvoiceItem.zone = vpc.zone
                        newInvoiceItem.usageUnitPrice = MiscellaneousOfferZoneCost.findWhere(miscellaneousOffer :ipOffer, zone :vpc.zone).cost;
                        newInvoiceItem.usageUnits = 1.0;
                        double usageAmount = newInvoiceItem.usageUnitPrice                
                        newInvoiceItem.usageAmount = Math.ceil(usageAmount * 100d) / 100d;   
                        double taxAmount = (newInvoiceItem.usageAmount/100)* newInvoiceItem.taxPercent
                        newInvoiceItem.taxAmount = Math.ceil(taxAmount * 100d) / 100d;     
                        newInvoiceItem.totalAmount = Math.ceil((newInvoiceItem.usageAmount + newInvoiceItem.taxAmount) * 100d) / 100d;     
                        newInvoiceItem.referenceItemName = "SecondaryIPCost"
                        newInvoiceItem.referenceItemId = jobResponse.secondaryIp
                        newInvoiceItem.save(flush: true); 

                    }
                }
                               
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.RESULT_SUCCESS);
                jobResult.add(item);

                return jobResult
                
            } else if(jobResponse.jobStatus == "2") {
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.RESULT_FAILURE);
                jobResult.add(item);
                
                return jobResult  
            }
                
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
    }
    def updateTier(requestData) {
        
        try {      
            
            def user = springSecurityService.currentUser
            def role = springSecurityService.getPrincipal().getAuthorities()  

            def network = Network.get(requestData.id)

            HashMap<String,String> optional = new HashMap<String,String>();
            optional.put("displaytext", requestData.name);
            optional.put("name", requestData.desc);
            optional.put("networkdomain", requestData.NWDomain);                                    
            
            if(network.networkOffer.networkReferenceId != requestData.networkOffer) {
               optional.put("networkofferingid", requestData.networkOffer); 
               optional.put("changecidr", Boolean.toString(requestData.cirdchange)); 
            }

            def response = networkServer().updateNetwork(network.referenceId, optional);

            
            ArrayList<ArrayList<String>> networkResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            item.put("jobId", response.jobId);
            networkResponse.add(item)

            return networkResponse;
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
    }
    
    
    def getVPCTopology(zoneReferenceId, vpcId) {
        
        def user = springSecurityService.currentUser
        def role = springSecurityService.getPrincipal().getAuthorities()  
        
        ArrayList<ArrayList<String>> vpcTopologyRespose = new ArrayList<ArrayList<String>>();
        HashMap vpcTopologyItem = new HashMap();    
        def vpcList = VPC.withCriteria {
            eq("deleted", false)           
            eq("account", user.account) 
            if(zoneReferenceId && zoneReferenceId != "All") {
                eq("zone", Zone.findByReferenceZoneId(zoneReferenceId))
            }
            if(vpcId) {
                eq("referenceId", vpcId)
            }
        }
        
        for(def vpc: vpcList) {
            
            vpcTopologyItem.put("name", vpc.name)
            vpcTopologyItem.put("name", vpc.referenceId)
            vpcTopologyItem.put("name", vpc.name) 
            vpcTopologyItem.put("description", vpc.description);
            vpcTopologyItem.put("cidr", vpc.cidr);
            vpcTopologyItem.put("account", vpc.account.firstName);            
            vpcTopologyItem.put("state", vpc.state);
            vpcTopologyItem.put("zone", vpc.zone.aliasName);
            vpcTopologyItem.put("networkDomain", vpc.networkDomain);
            vpcTopologyItem.put("referenceId", vpc.referenceId);
            vpcTopologyItem.put("vpcOffering", vpc.vpcOffering.name);
            vpcTopologyItem.put("vpcRestartRequred", vpc.restartRequired);
            
            vpcTopologyItem.put("privateGateway", VPCGateways.findAllWhere(vpc:vpc, deleted: false).size());
            vpcTopologyItem.put("publicIp", UserIPAddress.findAllWhere(vpc:vpc, removed: false).size());
            vpcTopologyItem.put("s2svpn", S2SVPNGateways.findAllWhere(vpc:vpc, deleted: false).size());
                         
            HashMap<String,String> acloptional = new HashMap<String,String>(); 
            acloptional.put("account", new String(user.account.accountIdentifier));
            acloptional.put("domainid", new String(user.account.domain.referenceId));
            acloptional.put("vpcid", vpc.referenceId);
            
            def aclresponse = networkAclServer().listNetworkACLLists(acloptional) 
                        
            vpcTopologyItem.put("networkAcl", aclresponse.count);                        
            
            def webTier = Network.withCriteria {
                eq("deleted", false)   
                eq("vpc", vpc)
                eq("tierType", "WEB")
            }
            
            def AppTier = Network.withCriteria {
                eq("deleted", false)   
                eq("vpc", vpc)
                eq("tierType", "APP")
            }
            
            def dbTier = Network.withCriteria {
                eq("deleted", false)   
                eq("vpc", vpc)
                eq("tierType", "DB")
            }            
            ArrayList<ArrayList<String>> webListArray = new ArrayList<ArrayList<String>>();
            for(def network : webTier) {
                HashMap webTierItem = new HashMap();    
                webTierItem.put("referenceId", network.referenceId);
                webTierItem.put("id", network.id);
                webTierItem.put("name", network.name);
                webTierItem.put("description", network.description);
                webTierItem.put("networkOffer", network.networkOffer.name);
                webTierItem.put("networkOfferId", network.networkOffer.networkReferenceId);
                webTierItem.put("zone", network.zone.aliasName);
                def supportedService = "";
                def networkOfferServiceList = NetworkOfferServiceList.findAllWhere(networkOffer: network.networkOffer)
                for(def service : networkOfferServiceList) {
                    supportedService += supportedService.size() == 0 ? service.service : ","+service.service
                }                
                webTierItem.put("tierType", network.tierType);
                webTierItem.put("user", network.account.firstName);                
                webTierItem.put("supportedService", supportedService);
                webTierItem.put("gateway", network.gateway);
                webTierItem.put("cidr", network.cidr);
                webTierItem.put("state", network.state);
                webTierItem.put("type", network.type);
                webTierItem.put("account", network.account.email);
                webTierItem.put("trafficType", network.trafficType);
                webTierItem.put("vpcId", network?.vpc.referenceId);
                webTierItem.put("vpcName", network?.vpc.name);                
                webTierItem.put("portForwarding", UserIPAddress.findAllWhere(isVPCPFAdded: true, network:network).size()); 
                webTierItem.put("internalLb", LoadBalancer.findAllWhere(scheme: "Internal", netwotk:network).size()); 
                webTierItem.put("publicLbIp", UserIPAddress.findAllWhere(isVPCLBAdded: true, network:network).size()); 
                webTierItem.put("staticNat", UserIPAddress.findAllWhere(isStaticNat: true, network:network).size());
                webTierItem.put("vm", Nic.findAllWhere(deleted: false, network:network).size()); 
                webListArray.add(webTierItem);                                
            }
            
            ArrayList<ArrayList<String>> appTierListArray = new ArrayList<ArrayList<String>>();
            for(def network : AppTier) {
                HashMap appTierItem = new HashMap();    
                appTierItem.put("referenceId", network.referenceId);
                appTierItem.put("id", network.id);
                appTierItem.put("name", network.name);
                appTierItem.put("description", network.description);
                appTierItem.put("networkOffer", network.networkOffer.name);
                appTierItem.put("networkOfferId", network.networkOffer.networkReferenceId);
                appTierItem.put("zone", network.zone.aliasName);
                def supportedService = "";
                def networkOfferServiceList = NetworkOfferServiceList.findAllWhere(networkOffer: network.networkOffer)
                for(def service : networkOfferServiceList) {
                    supportedService += supportedService.size() == 0 ? service.service : ","+service.service
                }                
                appTierItem.put("tierType", network.tierType);
                appTierItem.put("user", network.account.firstName);                
                appTierItem.put("supportedService", supportedService);
                appTierItem.put("gateway", network.gateway);
                appTierItem.put("cidr", network.cidr);
                appTierItem.put("state", network.state);
                appTierItem.put("type", network.type);
                appTierItem.put("account", network.account.email);
                appTierItem.put("trafficType", network.trafficType);
                appTierItem.put("vpcId", network?.vpc.referenceId);
                appTierItem.put("vpcName", network?.vpc.name);                
                appTierItem.put("portForwarding", UserIPAddress.findAllWhere(isVPCPFAdded: true, network:network).size()); 
                appTierItem.put("internalLb", LoadBalancer.findAllWhere(scheme: "Internal", netwotk:network).size()); 
                appTierItem.put("publicLbIp", UserIPAddress.findAllWhere(isVPCLBAdded: true, network:network).size()); 
                appTierItem.put("staticNat", UserIPAddress.findAllWhere(isStaticNat: true, network:network).size());
                appTierItem.put("vm", Nic.findAllWhere(deleted: false, network:network).size()); 
                appTierListArray.add(appTierItem);                                
            }
            
            ArrayList<ArrayList<String>> dbTierListArray = new ArrayList<ArrayList<String>>();
            for(def network : dbTier) {
                Console.print("db tier" + network.name)
                HashMap dbTierItem = new HashMap();    
                dbTierItem.put("referenceId", network.referenceId);
                dbTierItem.put("id", network.id);
                dbTierItem.put("name", network.name);
                dbTierItem.put("description", network.description);
                dbTierItem.put("networkOffer", network.networkOffer.name);
                dbTierItem.put("networkOfferId", network.networkOffer.networkReferenceId);
                dbTierItem.put("zone", network.zone.aliasName);
                def supportedService = "";
                def networkOfferServiceList = NetworkOfferServiceList.findAllWhere(networkOffer: network.networkOffer)
                for(def service : networkOfferServiceList) {
                    supportedService += supportedService.size() == 0 ? service.service : ","+service.service
                }                
                dbTierItem.put("tierType", network.tierType);
                dbTierItem.put("user", network.account.firstName);                
                dbTierItem.put("supportedService", supportedService);
                dbTierItem.put("gateway", network.gateway);
                dbTierItem.put("cidr", network.cidr);
                dbTierItem.put("state", network.state);
                dbTierItem.put("type", network.type);
                dbTierItem.put("account", network.account.email);
                dbTierItem.put("trafficType", network.trafficType);
                dbTierItem.put("vpcId", network?.vpc.referenceId);
                dbTierItem.put("vpcName", network?.vpc.name);                
                dbTierItem.put("portForwarding", UserIPAddress.findAllWhere(isVPCPFAdded: true, network:network).size()); 
                dbTierItem.put("internalLb", LoadBalancer.findAllWhere(scheme: "Internal", netwotk:network).size()); 
                dbTierItem.put("publicLbIp", UserIPAddress.findAllWhere(isVPCLBAdded: true, network:network).size()); 
                dbTierItem.put("staticNat", UserIPAddress.findAllWhere(isStaticNat: true, network:network).size());
                dbTierItem.put("vm", Nic.findAllWhere(deleted: false, network:network).size()); 
                dbTierListArray.add(dbTierItem);                                
            }
            
            vpcTopologyItem.put("webTier", webListArray);  
            vpcTopologyItem.put("appTier", appTierListArray); 
            vpcTopologyItem.put("dbTier", dbTierListArray); 
            
        }           
        vpcTopologyRespose.add(vpcTopologyItem)  
                        
        return vpcTopologyRespose;
        
    }
    def pullVPCOfferingFromCloudStackJobStart() {
        
        PullVPCOfferingJob.triggerNow([id:"vpc_offering"])
        return "OK"
    }
    def pullVPCOfferingFromCloudStack(def jobid) {
        
        def job = AsynchronousJobs.get(jobid)
        try { 
                        
            job.status = JobStatus.valueOf("RUNNING")
            job.startedAt = new Date()
            job.save(flush: true)
            
            HashMap<String,String> optional = new HashMap<String,String>();
            optional.put("listall", "true");

            def response = vpcServer().listVPCOfferings(optional);  
                
            for(Iterator<VpcOfferingResponse> iter = response.vpcOfferings.iterator(); iter.hasNext();) {
                
                def data = iter.next();
                VPCOffering vpcOffering = VPCOffering.findByReferenceId(data.id);
                
                // only create no edit for this pull
                if(!vpcOffering) {
                    
                    vpcOffering = new VPCOffering();
                    vpcOffering.referenceId = data.id;
                    vpcOffering.name = data.name;
                    vpcOffering.state = data.state;
                    vpcOffering.description = data.displayText;
                    vpcOffering.isDefault = data.isDefault;
                   
                    
                    if (!vpcOffering.save(flush: true)) {
                        vpcOffering.errors.allErrors.each { println it }
                    } else {
                         
                         for(Iterator<ServiceResponse> iterService = data.services.iterator(); iterService.hasNext();) {
                             
                            def serviceData = iterService.next();
                            
                            VPCOfferingServiceList vPCOfferingServiceList = new VPCOfferingServiceList();
                            
                            vPCOfferingServiceList.vpcOffering = vpcOffering;
                            vPCOfferingServiceList.service = serviceData.name;
                            vPCOfferingServiceList.provider = serviceData.serviceProvider;
                            vPCOfferingServiceList.created = new Date();
                            
                            
                            if (!vPCOfferingServiceList.save(flush: true)) {
                                vPCOfferingServiceList.errors.allErrors.each { println it }
                            }
                            
                         }
                
                    }
                    
                } 
            }
            
            job.status = JobStatus.valueOf("COMPLETED")
            job.completedAt = new Date()
            job.save(flush: true)
            
        
        }catch (Exception ex) {
            
            job.status = JobStatus.valueOf("ERROR")
            job.completedAt = new Date()
            job.save(flush: true)
            
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
}
