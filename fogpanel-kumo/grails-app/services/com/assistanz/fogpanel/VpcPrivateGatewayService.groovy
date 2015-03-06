package com.assistanz.fogpanel

import com.assistanz.cloud.cloudstack.CloudStackServer
import com.assistanz.cloud.cloudstack.CloudStackServer.CloudStackServerException
import com.assistanz.cloud.cloudstack.ServiceResponse
import com.assistanz.cloud.cloudstack.address.CSAddressService
import com.assistanz.cloud.cloudstack.address.IpAddressResponse
import com.assistanz.cloud.cloudstack.vpc.CSVPCService
import com.assistanz.cloud.cloudstack.vpc.PrivateGatewayResponse
import com.assistanz.cloud.cloudstack.vpc.StaticRouteResponse
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
import com.assistanz.cloud.cloudstack.network.PhysicalNetworkResponse
import com.assistanz.cloud.cloudstack.nic.NicSecondaryIPResponse
import org.apache.commons.logging.LogFactory;
import grails.transaction.Transactional

@Transactional
class VpcPrivateGatewayService {
    
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

    def list(vpcId, referenceId, zoneReferenceId) {
        
        try {
                        
            HashMap<String,String> optional = new HashMap<String,String>();

            def user = springSecurityService.currentUser
            def role = springSecurityService.getPrincipal().getAuthorities()   
            if(role.iterator().next() == "ROLE_ADMIN" ) {     
                optional.put("listAll", "true");
                if(vpcId) {
                    optional.put("vpcid", vpcId);                    
                }            
                if(referenceId) {
                    optional.put("id", referenceId);
                }
            } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {
                optional.put("listAll", "true");
                if(vpcId) {
                    optional.put("vpcid", vpcId);                    
                }            
                if(referenceId) {
                    optional.put("id", referenceId);
                }
                optional.put("account", new String(user.account.userName));
                optional.put("domainid", new String(user.account.domain.referenceId));
            }            

            def response = vpcServer().listPrivateGateways(optional);

            for(Iterator <PrivateGatewayResponse> iter = response.privateGateways.iterator(); iter.hasNext();) { 
                def data = iter.next();

                VPCGateways vpcGateways = VPCGateways.findByReferenceId(data.privateGatewayId)
                if(!vpcGateways) {
                    vpcGateways = new VPCGateways()
                    vpcGateways.referenceId = data.privateGatewayId  
                    vpcGateways.ipAddress = data.ipAddress   
                    vpcGateways.netmask = data.netmask   
                    vpcGateways.gateway = data.gateway   
                    vpcGateways.vlanTag = data.vlan  
                    vpcGateways.vpc = VPC.findByReferenceId(data.vpcId) 
                    vpcGateways.networkReferenceId = data.physicalNetworkId      
//                  vpcGateways.network = data.physicalNetworkId      // issystem = true find all netowk for this account
                    vpcGateways.zone = vpcGateways.vpc.zone   
                    vpcGateways.account = vpcGateways.vpc.account  
                    vpcGateways.state = data.state  
                    vpcGateways.type = "Private"   
                    vpcGateways.isStaticNat = Boolean.parseBoolean(data.sourceNatSupported)
                    vpcGateways.networkAclRuleId = data.aclId   
                    vpcGateways.created = new Date()   
                    vpcGateways.save(flush: true)
                    if (!vpcGateways.save()) {
                        vpcGateways.errors.allErrors.each { println it }
                    }
                } else if(vpcGateways) {
                    vpcGateways.networkAclRuleId = data.aclId   
                    vpcGateways.state = data.state  
                    vpcGateways.save(flush: true)
                }
            }
            
            ArrayList<ArrayList<String>> vpcPrivateGatewayResponse = new ArrayList<ArrayList<String>>();  
            
            def vpnPrivateGatewayList = ""
            
            if(role.iterator().next() == "ROLE_ADMIN" ) {     
                vpnPrivateGatewayList = VPCGateways.withCriteria {
                    eq("deleted", false)   
                    if(vpcId) {
                        eq("vpc", VPC.findByReferenceId(vpcId))   
                    } else {
                        isNotNull("vpc")   
                    } 
                    if(zoneReferenceId && zoneReferenceId != "All") {
                        eq("zone", Zone.findByReferenceZoneId(zoneReferenceId))
                    } 
                    if(referenceId) {
                        eq("referenceId", referenceId)
                    }
                }
            } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {
                vpnPrivateGatewayList = VPCGateways.withCriteria {
                    eq("deleted", false)   
                    eq("account", user.account)                       
                    if(vpcId) {
                        eq("vpc", VPC.findByReferenceId(vpcId))   
                    } else {
                        isNotNull("vpc")   
                    } 
                    if(zoneReferenceId && zoneReferenceId != "All") {
                        eq("zone", Zone.findByReferenceZoneId(zoneReferenceId))
                    } 
                    if(referenceId) {
                        eq("referenceId", referenceId)
                    }
                }
            }             
            
            for(def vpnPrivateGateway: vpnPrivateGatewayList) {
                
                HashMap<String,String> item = new HashMap<String,String>();
                item.put("referenceId", vpnPrivateGateway.referenceId);
                item.put("ip", vpnPrivateGateway.ipAddress);
                item.put("vlan", vpnPrivateGateway.vlanTag);
                item.put("account", vpnPrivateGateway.account.firstName);
                
                item.put("gateway", vpnPrivateGateway.gateway);
                item.put("netmask", vpnPrivateGateway.netmask);
                item.put("state", vpnPrivateGateway.state);
                item.put("zone", vpnPrivateGateway.vpc.zone.aliasName);
                item.put("vpc", vpnPrivateGateway.vpc.name);
                item.put("vpcId", vpnPrivateGateway.vpc.referenceId);
                item.put("aclId", vpnPrivateGateway.networkAclRuleId);
                vpcPrivateGatewayResponse.add(item);
            }
            return vpcPrivateGatewayResponse;
        
        } catch (Exception ex) {
           throw ex;
        } 
    }
    
    def createVPCPrivateGateway(requestData) {
        
        try {
            
            HashMap<String,String> optional = new HashMap<String,String>();
            optional.put("aclid", requestData.aclId);
            optional.put("physicalnetworkid", requestData.physicalNetworkId);
            optional.put("sourcenatsupported", Boolean.toString(requestData.sourceNat));
                                 
            def response = vpcServer().createPrivateGateway(requestData.gateway, requestData.ipAddress, requestData.netmask, 
                requestData.vlan, requestData.vpcId, optional);
            
            ArrayList<ArrayList<String>> createVpcPrivateGatewayResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();     
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            item.put("jobId", response.jobId);
            createVpcPrivateGatewayResponse.add(item)

            return  createVpcPrivateGatewayResponse   
            
        
        } catch (CloudStackServerException ex) {

            ArrayList<ArrayList<String>> errorResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_FAILURE);
            item.put("message", ex.localizedMessage.substring(4));
            errorResponse.add(item)

            return errorResponse    

        } catch (Exception ex) {
            
            ArrayList<ArrayList<String>> errorResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_FAILURE);
            item.put("message", ex.localizedMessage);
            errorResponse.add(item)

            return errorResponse    
        } 
        
    }
    
    def createVPCPrivateGatewayJob(jobId) {
        
        try {
            
            ArrayList<ArrayList<String>> jobResult = new ArrayList<ArrayList<String>>();  
            def jobResponse = asyncJobServer().queryAsyncJobResult(jobId)
            if(jobResponse.asychronousJobStatus == "0") {
                
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.JOB_RESULT_PENDING);
                jobResult.add(item);
                
                return jobResult
                
            } else if(jobResponse.asychronousJobStatus == "1") {
                                
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.RESULT_SUCCESS);
                jobResult.add(item);
                
                return jobResult
                
            } else if(jobResponse.asychronousJobStatus == "2") {
                
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.RESULT_FAILURE);
                jobResult.add(item);
                
                return jobResult  
            }
        
        
        } catch (CloudStackServerException ex) {

            ArrayList<ArrayList<String>> errorResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_FAILURE);
            item.put("message", ex.localizedMessage.substring(4));
            errorResponse.add(item)

            return errorResponse    

        } catch (Exception ex) {
            
            ArrayList<ArrayList<String>> errorResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_FAILURE);
            item.put("message", ex.localizedMessage);
            errorResponse.add(item)

            return errorResponse    
        } 
        
    }
    
    def deleteVPCPrivateGatewayJob(jobId) {
        
        try {
        
            ArrayList<ArrayList<String>> jobResult = new ArrayList<ArrayList<String>>();  
            def jobResponse = asyncJobServer().queryAsyncJobResult(jobId)
            if(jobResponse.asychronousJobStatus == "0") {
                
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.JOB_RESULT_PENDING);
                jobResult.add(item);
                
                return jobResult
                
            } else if(jobResponse.asychronousJobStatus == "1") {
                
                def vpcGateways = VPCGateways.findByJobId(jobId)
                vpcGateways.deleted = true;
                vpcGateways.removed = new Date();
                vpcGateways.save(flush:true)
                                
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.RESULT_SUCCESS);
                jobResult.add(item);
                
                return jobResult
                
            } else if(jobResponse.asychronousJobStatus == "2") {
                
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.RESULT_FAILURE);
                jobResult.add(item);
                
                return jobResult  
            }
        
        } catch (CloudStackServerException ex) {

            ArrayList<ArrayList<String>> errorResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_FAILURE);
            item.put("message", ex.localizedMessage.substring(4));
            errorResponse.add(item)

            return errorResponse    

        } catch (Exception ex) {
            
            ArrayList<ArrayList<String>> errorResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_FAILURE);
            item.put("message", ex.localizedMessage);
            errorResponse.add(item)

            return errorResponse    
        } 
        
    }
    
    def createStaticRouteJob(jobId) {
        
        try {
        
            ArrayList<ArrayList<String>> jobResult = new ArrayList<ArrayList<String>>();  
            def jobResponse = asyncJobServer().queryAsyncJobResult(jobId)
            if(jobResponse.asychronousJobStatus == "0") {
                
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.JOB_RESULT_PENDING);
                jobResult.add(item);
                
                return jobResult
                
            } else if(jobResponse.asychronousJobStatus == "1") {
                                
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.RESULT_SUCCESS);
                jobResult.add(item);
                
                return jobResult
                
            } else if(jobResponse.asychronousJobStatus == "2") {
                
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.RESULT_FAILURE);
                jobResult.add(item);
                
                return jobResult  
            }
        
        } catch (CloudStackServerException ex) {

            ArrayList<ArrayList<String>> errorResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_FAILURE);
            item.put("message", ex.localizedMessage.substring(4));
            errorResponse.add(item)

            return errorResponse    

        } catch (Exception ex) {
            
            ArrayList<ArrayList<String>> errorResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_FAILURE);
            item.put("message", ex.localizedMessage);
            errorResponse.add(item)

            return errorResponse    
        } 
    }
    
    def deleteVPCPrivateGateway(requestData) {
        
        try {
            
            def response = vpcServer().deletePrivateGateway(requestData.privateGatewayId);
                
            def vpcGateways = VPCGateways.findByReferenceId(requestData.privateGatewayId)
            vpcGateways.jobId = response.jobId;
            vpcGateways.save(flush:true)
            
            ArrayList<ArrayList<String>> deleteVpcPrivateGatewayResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            item.put("jobId", response.jobId);
            deleteVpcPrivateGatewayResponse.add(item)

            return  deleteVpcPrivateGatewayResponse
                
        } catch (CloudStackServerException ex) {

            ArrayList<ArrayList<String>> errorResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_FAILURE);
            item.put("message", ex.localizedMessage.substring(4));
            errorResponse.add(item)

            return errorResponse    

        } catch (Exception ex) {
            
            ArrayList<ArrayList<String>> errorResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_FAILURE);
            item.put("message", ex.localizedMessage);
            errorResponse.add(item)

            return errorResponse    
        } 
        
    }
    
    
    def getPhysicalNetwork(vpcId)  {
        
         try {
             
           def vpc = VPC.findByReferenceId(vpcId)  
           
            HashMap<String,String> optional = new HashMap<String,String>();
            optional.put("zoneid", vpc.zone.referenceZoneId); 
            
            def response = networkServer().listPhysicalNetworks(optional) 
            
            ArrayList<ArrayList<String>> physicalNetworkArray = new ArrayList<ArrayList<String>>();
            
            for(Iterator <PhysicalNetworkResponse> iter = response.physicalNetworks.iterator(); iter.hasNext();) {
                def data = iter.next();
                
                    HashMap item = new HashMap();                         
                    item.put("name", data.physicalNetworkName);
                    item.put("referenceId", data.physicalNetworkId);                    
                   
                    physicalNetworkArray.add(item)
                
            }
            return physicalNetworkArray   
            
            
            
         } catch (Exception ex) {
           throw ex;
        } 
    }
    
    def createStaticRoute(requestData) {
        
        try {
                                                         
            def response = vpcServer().createStaticRoute(requestData.cidr,  requestData.privateGatewayId);
            
            ArrayList<ArrayList<String>> createVpcPrivateGatewayResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();     
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            item.put("jobId", response.jobId);
            createVpcPrivateGatewayResponse.add(item)

            return  createVpcPrivateGatewayResponse   
            
        
        } catch (CloudStackServerException ex) {

            ArrayList<ArrayList<String>> errorResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_FAILURE);
            item.put("message", ex.localizedMessage.substring(4));
            errorResponse.add(item)

            return errorResponse    

        } catch (Exception ex) {
            
            ArrayList<ArrayList<String>> errorResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_FAILURE);
            item.put("message", ex.localizedMessage);
            errorResponse.add(item)

            return errorResponse    
        } 
    }
    
    def getStaticRouteList(vpcPrivateGatewayId)  {
        
         try {
                                
            HashMap<String,String> optional = new HashMap<String,String>();
            optional.put("gatewayid", vpcPrivateGatewayId); 
            optional.put("listAll", "true"); 
            
            def response = vpcServer().listStaticRoutes(optional) 
            
            def vpcPrivateGateway = VPCGateways.findByReferenceId(vpcPrivateGatewayId)
            
            ArrayList<ArrayList<String>> staticRouteArray = new ArrayList<ArrayList<String>>();
            
            for(Iterator <StaticRouteResponse> iter = response.staticRoutes.iterator(); iter.hasNext();) {
                def data = iter.next();
                    
                
                StaticRoutes staticRoutes = StaticRoutes.findByReferenceId(data.staticRouteId)
                if(!staticRoutes) {
                    staticRoutes = new StaticRoutes()
                    staticRoutes.referenceId = data.staticRouteId
                    staticRoutes.cidr = data.cidr
                    staticRoutes.vpc = vpcPrivateGateway.vpc
                    staticRoutes.zone = vpcPrivateGateway.zone
                    staticRoutes.created = new Date()
                    staticRoutes.state = data.state
                    staticRoutes.vpcGateways = vpcPrivateGateway
                    staticRoutes.account = vpcPrivateGateway.account
                    staticRoutes.save(flush: true)
                    
                } else {
                    staticRoutes.state = data.state
                    staticRoutes.save(flush: true)
                }
            }
                        
            def staticRoutesList = StaticRoutes.findAllByVpcGateways(vpcPrivateGateway)
            
            for(def staticRoute: staticRoutesList) {
                HashMap item = new HashMap();                         
                item.put("state", staticRoute.state);
                item.put("referenceId", staticRoute.referenceId);                    
                item.put("cidr", staticRoute.cidr);                    
                item.put("vpc", staticRoute.vpc.name);                    
                item.put("zone", staticRoute.zone.aliasName);         
                item.put("vpcGateways", staticRoute.vpcGateways.ipAddress);         
                
                item.put("account", staticRoute.account.email);                    

                staticRouteArray.add(item)
            }
                        
            return staticRouteArray   
            
         } catch (Exception ex) {
           throw ex;
        } 
    }
    
    def deleteStaticRouteJob(jobId) {
        
        try {
        
            ArrayList<ArrayList<String>> jobResult = new ArrayList<ArrayList<String>>();  
            def jobResponse = asyncJobServer().queryAsyncJobResult(jobId)
            if(jobResponse.asychronousJobStatus == "0") {
                
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.JOB_RESULT_PENDING);
                jobResult.add(item);
                
                return jobResult
                
            } else if(jobResponse.asychronousJobStatus == "1") {
                              
                def staticRoute = StaticRoutes.findByJobId(jobId)
                staticRoute.delete(flush:true)

                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.RESULT_SUCCESS);
                jobResult.add(item);
                
                return jobResult
                
            } else if(jobResponse.asychronousJobStatus == "2") {
                
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.RESULT_FAILURE);
                jobResult.add(item);
                
                return jobResult  
            }
        
        } catch (CloudStackServerException ex) {

            ArrayList<ArrayList<String>> errorResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_FAILURE);
            item.put("message", ex.localizedMessage.substring(4));
            errorResponse.add(item)

            return errorResponse    

        } catch (Exception ex) {
            
            ArrayList<ArrayList<String>> errorResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_FAILURE);
            item.put("message", ex.localizedMessage);
            errorResponse.add(item)

            return errorResponse    
        } 
        
    }
    
    def deleteStaticRoute(requestData) {
        
        try {
            
            def response = vpcServer().deleteStaticRoute(requestData.staticRouteId);
                
            def staticRoute = StaticRoutes.findByReferenceId(requestData.staticRouteId)
            staticRoute.jobId = response.jobId; 
            staticRoute.save(flush:true)
            
            ArrayList<ArrayList<String>> deleteVpcPrivateGatewayResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            item.put("jobId", response.jobId);
            deleteVpcPrivateGatewayResponse.add(item)

            return  deleteVpcPrivateGatewayResponse
                
        } catch (CloudStackServerException ex) {

            ArrayList<ArrayList<String>> errorResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_FAILURE);
            item.put("message", ex.localizedMessage.substring(4));
            errorResponse.add(item)

            return errorResponse    

        } catch (Exception ex) {
            
            ArrayList<ArrayList<String>> errorResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_FAILURE);
            item.put("message", ex.localizedMessage);
            errorResponse.add(item)

            return errorResponse    
        } 
        
    }
  
}
