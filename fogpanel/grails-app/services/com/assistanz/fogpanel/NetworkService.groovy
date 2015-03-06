package com.assistanz.fogpanel

import com.assistanz.cloud.cloudstack.CloudStackServer
import com.assistanz.cloud.cloudstack.CloudStackServer.CloudStackServerException
import com.assistanz.cloud.cloudstack.address.CSAddressService
import com.assistanz.cloud.cloudstack.address.IpAddressResponse
import com.assistanz.cloud.cloudstack.asyncjob.CSAsyncJobService
import com.assistanz.cloud.cloudstack.firewall.CSFirewallService
import com.assistanz.cloud.cloudstack.firewall.EgressRuleResponse
import com.assistanz.cloud.cloudstack.firewall.PortForwardingRules
import com.assistanz.cloud.cloudstack.loadbalancer.CSLoadBalancerService
import com.assistanz.cloud.cloudstack.loadbalancer.LoadBalancerRuleInstanceResponse
import com.assistanz.cloud.cloudstack.loadbalancer.LoadBalancerRuleResponse
import com.assistanz.cloud.cloudstack.loadbalancer.LoadBalancersResponse
import com.assistanz.cloud.cloudstack.loadbalancer.LoadBalancerResponse
import com.assistanz.cloud.cloudstack.loadbalancer.LoadBalancerInstanceResponse
import com.assistanz.cloud.cloudstack.nat.CSNATService
import com.assistanz.cloud.cloudstack.network.CSNetworkService
import com.assistanz.cloud.cloudstack.network.NetworkResponse
import com.assistanz.cloud.cloudstack.nic.CSNicService
import com.assistanz.cloud.cloudstack.nic.NicResponse
import com.assistanz.cloud.cloudstack.nic.NicSecondaryIPResponse
import org.codehaus.groovy.grails.commons.ApplicationHolder
import grails.transaction.Transactional
import groovy.json.*
import grails.converters.deep.JSON
import org.apache.commons.logging.LogFactory;

@Transactional
class NetworkService {
    
    def springSecurityService;
    private static final log = LogFactory.getLog(this)
    
    def networkServer() {
        
        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret
    
        CloudStackServer server =
        new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);
                
        CSNetworkService networkService = new CSNetworkService(server);
        
        return networkService;
        
    }
    
    def networkTypeCount(zoneReferenceId) {
        
        def user = springSecurityService.currentUser
        def role = springSecurityService.getPrincipal().getAuthorities()  
        
        def networkList
        def networkListTotal
        
        if(role.iterator().next() == "ROLE_ADMIN" ) {
            
            networkList = Network.withCriteria {
                eq("deleted", false)           
                ne("account", user.account) 
                
                projections {
                    groupProperty("type")
                    count("id")
                }
            }
            
            networkListTotal = Network.withCriteria {
                eq("deleted", false)           
                ne("account", user.account) 
            }
            
            
        } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {
            
            networkList = Network.withCriteria {
                if(zoneReferenceId != "All") {
                    eq("zone", Zone.findByReferenceZoneId(zoneReferenceId))
                }
                eq("account", user.account)
                eq("deleted", false) 
                isNull("vpc") 
                projections {
                    groupProperty("type")
                    count("id")
                }
            }
            
            networkListTotal = Network.withCriteria {
                eq("account", user.account)
                eq("deleted", false) 
                isNull("vpc") 
            }
            
        }
        ArrayList<ArrayList<String>> netwotkTypeCount = new ArrayList<ArrayList<String>>();
        HashMap<String,String> item = new HashMap<String,String>();
        item.put("total", networkListTotal.size());
        for(def networkType: networkList) {
            item.put(networkType[0], networkType[1]);
        }
        netwotkTypeCount.add(item)
        
        return netwotkTypeCount
        
    }
    
    def firewallServer() {
        
        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret
    
        CloudStackServer server =
        new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);
                
        CSFirewallService firewallService = new CSFirewallService(server);
        
        return firewallService;
        
    }
    
    def loadBalancerServer() {
        
        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret
    
        CloudStackServer server =
        new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);
                
        CSLoadBalancerService loadBalancerService = new CSLoadBalancerService(server);
        
        return loadBalancerService;
    }
    
    def NATServer() {
        
        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret
    
        CloudStackServer server =
        new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);
                
        CSNATService natService = new CSNATService(server);
        
        return natService;
    }
    
    def nicServer() {
        
        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret
        CloudStackServer server = new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);                
        CSNicService csNicService = new CSNicService(server);        
        return csNicService;   
    }  
    
    def enableStaticNat(requestBody) {   
        try {           
            def requestData = JSON.parse(requestBody)
            
            HashMap<String,String> optional = new HashMap<String,String>();
            if(requestData.vmIpId && requestData.vmIpId != "primary") {
                optional.put("vmguestip", requestData.vmIpId);
            }   
            
            if(requestData.networkId) { 
                optional.put("networkid", requestData.networkId);
            }
            
            def enableStaticNatResponse = NATServer().enableStaticNat(requestData.ipId, requestData.vmId, optional)

            ArrayList<ArrayList<String>> enableStaticResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            
            if(enableStaticNatResponse.success) {
                item.put("result", enableStaticNatResponse.success);
            } else {
                item.put("result", GeneralConstants.RESULT_SUCCESS);
            }
            item.put("jobId", enableStaticNatResponse.jobId);
            enableStaticResponse.add(item)

            return enableStaticResponse     
        }  catch (CloudStackServerException ex) {
            
            ArrayList<ArrayList<String>> enableStaticResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_FAILURE);
            item.put("message", ex.localizedMessage.substring(4));
            enableStaticResponse.add(item)

            return enableStaticResponse    
            
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
    }
    def listPublicLB(zoneReferenceId, vpcId) {
        try { 
            
            def user = springSecurityService.currentUser
            
            HashMap<String,String> optional = new HashMap<String,String>();
            optional.put("forvirtualnetwork", "true");
            optional.put("listAll", "true");
            if(vpcId) {
                optional.put("vpcid", vpcId);
            }        
            optional.put("account", new String(user.account.accountIdentifier));
            optional.put("domainid", new String(user.account.domain.referenceId));
            
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
                    
                    userIPAddress.isVPCLBAdded = LoadBalancer.findAllWhere(account: userIPAddress.account, userIPAddress:userIPAddress) ? true : false
                    userIPAddress.isVPCPFAdded = PortForwarding.findAllWhere(account: userIPAddress.account, userIPAddress:userIPAddress) ? true : false
                   
                    if(userIPAddress.isSourceNat == true) {
                        userIPAddress.isFirstIp = true
                    }
                    userIPAddress.save(flush: true)
                }
            
            }
            def puplicIpList = UserIPAddress.withCriteria {
                if(zoneReferenceId && zoneReferenceId != "All") {
                    eq("zone", Zone.findByReferenceZoneId(zoneReferenceId))
                }                
                if(vpcId && vpcId != "option") {
                    eq("vpc", VPC.findByReferenceId(vpcId))
                } else {
                    isNotNull("vpc")
                }  
                eq("account", user.account)
                eq("removed", false)                   
                eq("isVPCLBAdded", true)                   
                
            }            
            ArrayList<ArrayList<String>> publicIPListResponse = new ArrayList<ArrayList<String>>();                
            for(def puplicIp: puplicIpList) {            
                HashMap item = new HashMap();                
                def isEnabledVPN = RemoteAccessVpn.findByNetwork(puplicIp.network)? true: false;            
                item.put("id", puplicIp.ipReferenceId);
                item.put("vpc", puplicIp.vpc.name);                                
                item.put("networkReferenceId", puplicIp.network?.referenceId);
                item.put("networkId", puplicIp.network?.id);
                item.put("ip", puplicIp.publicIPAddress);
                item.put("isSourceNat", puplicIp.isSourceNat);
                item.put("isStaticNat", puplicIp.isStaticNat);
                item.put("network", puplicIp.network?.name);
                item.put("networkType", puplicIp.network?.type);
                item.put("vpnEnabled", RemoteAccessVpn.findByPublicIPAddress(puplicIp)? true: false);
                item.put("isEnabledVPN", isEnabledVPN);
                if(puplicIp.virtualMachine) {
                    item.put("vm", puplicIp.virtualMachine?.displayName);
                } else {
                    item.put("vm", puplicIp.staticNatVirtualMachine?.displayName);
                }
                item.put("lbNo", LoadBalancer.findAllByUserIPAddress(puplicIp).size());
                item.put("pfNo", PortForwarding.findAllByUserIPAddress(puplicIp).size());
                item.put("state", puplicIp.state);
                item.put("zone", puplicIp.zone.aliasName);
                item.put("zoneType", puplicIp.zone.networkType);
                item.put("account", puplicIp.account?.accountIdentifier);
                publicIPListResponse.add(item)
            }        
            return publicIPListResponse
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }    
    }
    def listAllLBs(zoneReferenceId, vpcId, scheme) {
        try {
            def user = springSecurityService.currentUser
            def role = springSecurityService.getPrincipal().getAuthorities()                  
            HashMap<String,String> optional = new HashMap<String,String>();
                optional.put("account", new String(user.account.accountIdentifier));
                optional.put("domainid", new String(user.account.domain.referenceId));                                               
                def response = loadBalancerServer().listLoadBalancers(optional)
               
                for(Iterator <LoadBalancersResponse> iter = response.loadBalancerLists.iterator(); iter.hasNext();) { 
                    def data = iter.next();                                                                                                                                               
                    def firewallRules = FirewallRules.findByReferenceId(data.id)
                    def loadBalancer = LoadBalancer.findByReferenceId(data.id)
                    if(!firewallRules && !loadBalancer) {
                        firewallRules = new FirewallRules()
                        firewallRules.account = user.account
                        firewallRules.createdDate = new Date()
                        firewallRules.referenceId = data.id                    
                        firewallRules.netwotk = Network.findByReferenceId(data.networkId)                             
                        firewallRules.startPort = "internallb"
                        firewallRules.endPort = "internallb"
                        firewallRules.state = "internallb"
                        firewallRules.protocol = "internallb" 
                        firewallRules.zone = firewallRules.netwotk.zone  
                        firewallRules.type = "User"
                        firewallRules.purpose = "Internal LoadBalancer"
                        firewallRules.purposeId = "creating"
                        firewallRules.save(flush: true)    
                        if (!firewallRules.save()) {
                            firewallRules.errors.allErrors.each { println it }
                        }

                        loadBalancer = new LoadBalancer()
                        loadBalancer.referenceId = data.id
                        loadBalancer.description = data.description
                        loadBalancer.createdDate = new Date()
                        loadBalancer.name = data.name
                        loadBalancer.algorithm = data.algorithm
                        for(Iterator <LoadBalancerResponse> lbruleiter = data.loadBalancers.iterator(); lbruleiter.hasNext();) { 
                            def lbrule = lbruleiter.next();

                            loadBalancer.privatePort = lbrule.instancePort
                            loadBalancer.publicPort = lbrule.sourcePort

                        }

                        loadBalancer.userIPAddress = firewallRules.userIPAddress
                        loadBalancer.netwotk = firewallRules.netwotk

                        loadBalancer.scheme = "Internal"
                        loadBalancer.firewallRules = firewallRules
                        loadBalancer.zone = firewallRules.zone
                        loadBalancer.account = user.account
                        loadBalancer.save(flush:true)

                        firewallRules.purposeId = loadBalancer.id
                        firewallRules.save(flush: true)
                      
                        for(Iterator <LoadBalancerInstanceResponse> vmlbitter = data.loadBalancerInstances.iterator(); vmlbitter.hasNext();) { 
                            def vmlb = vmlbitter.next();
                            def vm = VirtualMachine.findByReferenceId(vmlb.id)

                            def loadbalancervmmap = LoadBalancerVmMap.findAllWhere(loadBalancer : loadBalancer, virtualMachine:VirtualMachine.findByReferenceId(vmlb.id))

                            if(!loadbalancervmmap) {
                                def loadBalancerVmMap = new LoadBalancerVmMap()
                                loadBalancerVmMap.loadBalancer = loadBalancer
                                loadBalancerVmMap.virtualMachine = VirtualMachine.findByReferenceId(vmlb.id)
                                loadBalancerVmMap.save(flush: true); 
                            }                        
                        }   

                    } else {                        
                        loadBalancer.description = data.description
                        loadBalancer.save(flush: true);
                         for(Iterator <LoadBalancerInstanceResponse> vmlbitter = data.loadBalancerInstances.iterator(); vmlbitter.hasNext();) { 
                        def vmlb = vmlbitter.next();
                        def vm = VirtualMachine.findByReferenceId(vmlb.id)
                        
                        def loadbalancervmmap = LoadBalancerVmMap.findAllWhere(loadBalancer : loadBalancer, virtualMachine:VirtualMachine.findByReferenceId(vmlb.id))
                        
                        if(!loadbalancervmmap) {
                            def loadBalancerVmMap = new LoadBalancerVmMap()
                            loadBalancerVmMap.loadBalancer = loadBalancer
                            loadBalancerVmMap.virtualMachine = VirtualMachine.findByReferenceId(vmlb.id)
                            loadBalancerVmMap.save(flush: true); 
                        }                        
                    }                    
                }                
            }                        
            
            ArrayList<ArrayList<String>> lbArrayList = new ArrayList<ArrayList<String>>();
            
            def lbList = LoadBalancer.withCriteria {
                eq("account", user.account)
                if(zoneReferenceId && zoneReferenceId != "All") {
                    eq("zone", Zone.findByReferenceZoneId(zoneReferenceId))
                }                
                if(scheme) {
                    eq("scheme", scheme)
                }                           
            }            
            for(int i = 0 ; i < lbList.size(); i ++ ) {
                def data = lbList[i];
                def vmString = "";
                def vmList = LoadBalancerVmMap.findAllWhere(loadBalancer : LoadBalancer.findByReferenceId(data.referenceId))
                    for(def vm: vmList) {                        
                        vmString += vmString.size() == 0 ? vmString : ",";                                    
                        vmString += vm.virtualMachine.displayName
                    }                                
                if(vpcId == null || vpcId == "null" || vpcId == "") {
                    if(data.netwotk.vpc) {
                        HashMap item = new HashMap();
                        item.put("id", data.referenceId);
                        item.put("tierId", data.netwotk.id);  
                        item.put("vpcName", data.netwotk.vpc.name);                          
                        item.put("tierName", data.netwotk.name);                          
                        item.put("instancePort", data.privatePort); 
                        item.put("sourcePort", data.publicPort);                         
                        item.put("algorithm", data.algorithm);
                        item.put("description", data.description);
                        item.put("name", data.name);
                        item.put("scheme", data.scheme);                        
                        item.put("vmList", vmString);                                                
                        if(data.userIPAddress) {
                            item.put("userIPAddress", data.userIPAddress.publicIPAddress);   
                        }
                        
                        lbArrayList.add(item)                        
                    } 
                } else {
                    if((data.netwotk.vpc) && (data.netwotk.vpc.referenceId == vpcId)) {
                        HashMap item = new HashMap();
                        item.put("id", data.referenceId);
                        item.put("tierId", data.netwotk.id);      
                        item.put("vpcName", data.netwotk.vpc.name);          
                        item.put("tierName", data.netwotk.name);                               
                        item.put("algorithm", data.algorithm);
                        item.put("description", data.description);
                        item.put("name", data.name);
                        item.put("scheme", data.scheme);
                        item.put("instancePort", data.privatePort); 
                        item.put("sourcePort", data.publicPort);
                        item.put("vmList", vmString);      
                        if(data.userIPAddress) {
                            item.put("userIPAddress", data.userIPAddress.publicIPAddress);   
                        }
                        lbArrayList.add(item)   
                    }
                } 
            }
            return lbArrayList
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }                                 
    }
    def listAllIps(zoneReferenceId, vpcId, isStatic) {
        try {                        
            HashMap<String,String> optional = new HashMap<String,String>();
            optional.put("forvirtualnetwork", "true");
            optional.put("listAll", "true");
            if(vpcId) {
                optional.put("vpcid", vpcId);
            }            
            
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
                    if (userIPAddress.hasErrors()) {
                        throw new ValidationException(userIPAddress.errors.allErrors);
                    } 
                    
                }         
            
            }                         
            
            def user = springSecurityService.currentUser
            ArrayList<ArrayList<String>> ipArrayList = new ArrayList<ArrayList<String>>();
            def vpcIpList = UserIPAddress.withCriteria {
                eq("account", user.account)
                if(zoneReferenceId && zoneReferenceId != "All") {
                    eq("zone", Zone.findByReferenceZoneId(zoneReferenceId))
                }

                if(vpcId && vpcId != "option") {
                    eq("vpc", VPC.findByReferenceId(vpcId))
                } else {
                    isNotNull("vpc")            
                } 
                
                if(isStatic) {
                    eq("isStaticNat", Boolean.parseBoolean(isStatic))
                }                
                
            }   
            
            for(int i = 0; i < vpcIpList.size(); i++) {
                def data = vpcIpList[i];
                HashMap item = new HashMap();                       
                item.put("referenceId", data.ipReferenceId);  
                
                item.put("id", data.ipReferenceId);                  
                if(data.network) {
                    item.put("network", Network.findByReferenceId(data.network.referenceId)?data.network.name: "");
                    item.put("networkReferenceId", Network.findByReferenceId(data.network.referenceId)?data.network.referenceId: "");
                }                                                                            
                
                def vmList = "" 
                HashMap<String,String> vmListMap = new HashMap<String,String>(); 
                def loadBalancerList = LoadBalancer.findAllByUserIPAddress(UserIPAddress.findByIpReferenceId(data.ipReferenceId))
                def pfList = PortForwarding.findAllByUserIPAddress(UserIPAddress.findByIpReferenceId(data.ipReferenceId))                                
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
                if(data.isStaticNat == true) {
                    boolean blnExists = vmListMap.containsKey(data.staticNatVirtualMachine.referenceId);
                    if(blnExists.toString() == "false" || blnExists == false) {
                        vmList = data.staticNatVirtualMachine.displayName
                    }
                    vmListMap.put(data.staticNatVirtualMachine.referenceId ,"referenceId");
                }
                item.put("vms", vmList);
                item.put("ip", data.publicIPAddress);
                item.put("isSourceNat", data.isSourceNat);
                item.put("isStaticNat", data.isStaticNat);                
                item.put("state", data.state);
                item.put("vmDisplayName", VirtualMachine.findByReferenceId(data.vpc.referenceId)?data.vpc.displayName: "");
                item.put("vmReferenceId", VirtualMachine.findByReferenceId(data.vpc.referenceId)?data.vpc.referenceId: "");                
                item.put("vpc", VPC.findByReferenceId(data.vpc.referenceId)?data.vpc.name: "");
                item.put("vpcId", VPC.findByReferenceId(data.vpc.referenceId)?data.vpc.referenceId: "");
                
                item.put("zone", data.zone.aliasName);
                item.put("vpnEnabled", RemoteAccessVpn.findByPublicIPAddress(UserIPAddress.findByIpReferenceId(data.ipReferenceId))? true: false);
                
                item.put("isVPCLBAdded", LoadBalancer.findAllByUserIPAddress(UserIPAddress.findByIpReferenceId(data.ipReferenceId)) ? true : false);
                item.put("isVPCPFAdded", PortForwarding.findAllByUserIPAddress(UserIPAddress.findByIpReferenceId(data.ipReferenceId)) ? true : false);
                
                
                ipArrayList.add(item) 
            }
            return ipArrayList
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
    }
    
    def listAllVM(zoneReferenceId, vpcId) {
        try {
            def user = springSecurityService.currentUser
            ArrayList<ArrayList<String>> vmArrayList = new ArrayList<ArrayList<String>>();
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
            
            for(int i=0 ; i < vmList.size(); i++) {
                def item = vmList[i];
                if(item) {                            
                    HashMap currentItem = new HashMap();                        
                    def hasVPC = item.vpc?true:false;  
                    def vpcName = item.vpc?item.vpc.name:"";       
                    def nicList = Nic.findAllWhere(virtualMachine: VirtualMachine.findByReferenceId(item.referenceId));
                    def tierString = ""
                    for(def nic: nicList) {                        
                        tierString += tierString.size() == 0 ? tierString : ",";                                    
                        tierString += nic.network.name
                    }                    
                    currentItem.put("referenceId", item.referenceId);
                    currentItem.put("state", item.state);
                    currentItem.put("tierList", tierString);                    
                    currentItem.put("name", item.displayName);
                    currentItem.put("zone", item.zone.aliasName);
                    currentItem.put("osCategory", item.template.osCategory.name);
                    currentItem.put("vmId", item.id);
                    currentItem.put("hasVPC", hasVPC);     
                    currentItem.put("vpc", vpcName);     
                    vmArrayList.add(currentItem); 
                }
            }
            return vmArrayList;
            
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
    }
    def getVPCTier(zoneReferenceId, vpcId) {
        try {
            def user = springSecurityService.currentUser
            def role = springSecurityService.getPrincipal().getAuthorities()  
            ArrayList<ArrayList<String>> tierArrayList = new ArrayList<ArrayList<String>>();
            
            HashMap<String,String> optional = new HashMap<String,String>();
            if(vpcId) {
                optional.put("vpcid", vpcId);         
            }                 
            def response = networkServer().listNetworks(optional);
            for(Iterator <NetworkResponse> iter = response.networks.iterator(); iter.hasNext();) {
                def data = iter.next();                                             
                def network = Network.findByReferenceId(data.id)
                if(network) {   
                    network.cidr = data.networkCidr
                    network.gateway = data.gateway
                    network.state = data.state
                    network.type = data.type
                    network.trafficType = data.trafficType
                    network.name = data.name;
                    network.description = data.displayText;                    
                    network.networkDomain = data.domain                    
                    
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
                    network.save(flush: true)                                                            
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
            
            for(int i=0 ; i < tierList.size(); i++) {
                def network = tierList[i];
                if(network) {                                         
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
                    
                    HashMap item = new HashMap();    
                    item.put("referenceId", network.referenceId);
                    item.put("id", network.id);
                    item.put("name", network.name);
                    item.put("description", network.description);
                    item.put("networkOffer", network.networkOffer.name);
                    item.put("networkOfferId", network.networkOffer.networkReferenceId);
                    item.put("zone", network.zone.aliasName);
                    item.put("tierType", tierType);
                    item.put("networkDomain", network.networkDomain);
                    
                    
                    def supportedService = "";
                    
                    def networkOfferServiceList = NetworkOfferServiceList.findAllWhere(networkOffer: network.networkOffer)
                    for(def service : networkOfferServiceList) {                        
                        supportedService += supportedService.size() == 0 ? service.service : ","+service.service
                    }
                    
                    item.put("supportedService", supportedService);                    
                    item.put("gateway", network.gateway);
                    item.put("cidr", network.cidr);
                    item.put("state", network.state);
                    item.put("type", network.type);
                    item.put("account", network.account.email);
                    item.put("trafficType", network.trafficType);                    
                    item.put("vpcId", network?.vpc.referenceId);
                    item.put("vpcName", network?.vpc.name);                    
                    tierArrayList.add(item); 
                }
            }
            return tierArrayList;
             
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
    }
    
    def enableVpn(requestBody) {
        try {
             
            def requestData = JSON.parse(requestBody)
                                    
            def enableVpnResponse = NATServer().enableStaticNat(requestData.ipId)

            ArrayList<ArrayList<String>> enableVpnResponseResult = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            item.put("jobId", enableVpnResponse.jobId);
            enableVpnResponseResult.add(item)

            return enableVpnResponseResult     
        }  catch (CloudStackServerException ex) {
            
            ArrayList<ArrayList<String>> enableStaticResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_FAILURE);
            item.put("message", ex.localizedMessage.substring(4));
            enableStaticResponse.add(item)

            return enableStaticResponse    
            
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
    }
    
    def getEnableStaticNatJob(jobId) {
        
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
    
    def listPortForwardingRules(ipId) {
        
        def user = springSecurityService.currentUser
        def role = springSecurityService.getPrincipal().getAuthorities()  
        
        def pfOffer = MiscellaneousOffer.get(6);
        def pfBillableItem = BillableItem.get(19)
        def invoice = Invoice.findWhere(account: user.account, status: InvoiceStatus.values()[6])
        
        HashMap<String,String> optional = new HashMap<String,String>();
        optional.put("account", new String(user.account.accountIdentifier));
        optional.put("domainid", new String(user.account.domain.referenceId));
        optional.put("ipaddressid", ipId);
        optional.put("listAll", "true");
                
        HashMap<String,String> ipoptional = new HashMap<String,String>(); 
        ipoptional.put("id", UserIPAddress.findByIpReferenceId(ipId).ipReferenceId); 

        def ipresponse = addressServer().listPublicIpAddresses(ipoptional);    
                
        for(Iterator <IpAddressResponse> iter = ipresponse.ipAddresses.iterator(); iter.hasNext();) {
            def data = iter.next();
            def userIpAddress = UserIPAddress.findByIpReferenceId(data.id) 
            userIpAddress.network = Network.findByReferenceId(data.associatedNetworkid)
            userIpAddress.save(flush: true);   
        }        
        def response = firewallServer().listPortForwardingRules(optional) 
        
        ArrayList<ArrayList<String>> portForwadRuleListResponse = new ArrayList<ArrayList<String>>();    
        
        for(Iterator <PortForwardingRules> iter = response.portForwardingRules.iterator(); iter.hasNext();) { 
            def data = iter.next();
                        
            def firewallRules = FirewallRules.findByReferenceId(data.id)
            def portForwarding = PortForwarding.findByReferenceId(data.id)
            if(!firewallRules && !portForwarding) {
                
                def userIP = UserIPAddress.findByIpReferenceId(ipId)
                userIP.isVPCLBAdded = false;
                userIP.isVPCPFAdded = true;        
                userIP.save(flush: true)    
                
                
                firewallRules = new FirewallRules()
                firewallRules.account = user.account
                firewallRules.createdDate = new Date()
                firewallRules.referenceId = data.id
                firewallRules.userIPAddress = UserIPAddress.findByIpReferenceId(ipId)
                firewallRules.netwotk = firewallRules.userIPAddress.network
                firewallRules.startPort = data.publicPort  
                firewallRules.endPort = data.publicEndPort
                firewallRules.state = data.state
                firewallRules.protocol = data.protocol
                 if(firewallRules.userIPAddress.vpc) {
                    firewallRules.zone = firewallRules.userIPAddress.vpc.zone 
                } else {
                   firewallRules.zone = firewallRules.userIPAddress.network.zone  
                }
                firewallRules.type = "User"
                firewallRules.purpose = "PortForwarding"
                firewallRules.purposeId = "creating"
                firewallRules.save(flush: true)        
                                  
                portForwarding = new PortForwarding()
                portForwarding.referenceId = data.id
                portForwarding.createdDate = new Date()
                portForwarding.privateStartPort = data.privatePort
                portForwarding.privateEndPort = data.privateEndPort
                portForwarding.virtualMachine = VirtualMachine.findByReferenceId(data.virtualMachineId) 
                portForwarding.destIpAddress = data.vmGuestIp
                                
                portForwarding.userIPAddress = firewallRules.userIPAddress
                portForwarding.netwotk = firewallRules.netwotk
                portForwarding.name = firewallRules.netwotk.name +"/"+firewallRules.userIPAddress.publicIPAddress
                                
                portForwarding.firewallRules = firewallRules
                portForwarding.zone = firewallRules.zone
                portForwarding.account = user.account
                portForwarding.save(flush:true)
                
                if (!portForwarding.save()) {
                    portForwarding.errors.allErrors.each { println it }
                }
                
                
                firewallRules.purposeId = portForwarding.id
                firewallRules.save(flush: true)
                                
                if(pfBillableItem.enabled == true || pfBillableItem.enabled == "true") {

                    def newInvoiceItem = new InvoiceItem()
                    newInvoiceItem.billableItem = pfBillableItem
                    newInvoiceItem.invoice = invoice
                    newInvoiceItem.taxPercent = pfBillableItem.tax.percentage
                    newInvoiceItem.zone = portForwarding.zone
                    def offerCost = MiscellaneousOfferZoneCost.findWhere(miscellaneousOffer :pfOffer, zone :portForwarding.zone)
                    newInvoiceItem.usageUnitPrice = offerCost ? offerCost.cost : 0.0;
                    newInvoiceItem.usageUnits = 1.0;
                    double usageAmount = newInvoiceItem.usageUnitPrice                
                    newInvoiceItem.usageAmount = Math.ceil(usageAmount * 100d) / 100d;   
                    double taxAmount = (newInvoiceItem.usageAmount/100)* newInvoiceItem.taxPercent
                    newInvoiceItem.taxAmount = Math.ceil(taxAmount * 100d) / 100d;     
                    newInvoiceItem.totalAmount = Math.ceil((newInvoiceItem.usageAmount + newInvoiceItem.taxAmount) * 100d) / 100d;     
                    newInvoiceItem.referenceItemName = portForwarding.name
                    newInvoiceItem.referenceItemId = portForwarding.referenceId
                    newInvoiceItem.save(flush: true); 

                }
               
            } else {
                
                def userIP = UserIPAddress.findByIpReferenceId(ipId)
                userIP.isVPCLBAdded = false; 
                userIP.isVPCPFAdded = true; 
                userIP.save(flush: true) 
                
                def invoiceItem = InvoiceItem.findWhere(billableItem: pfBillableItem, referenceItemId:data.id, invoice:invoice)
                
                if(!invoiceItem) {
                    if(pfBillableItem.enabled == true || pfBillableItem.enabled == "true") {
                          
                        def newInvoiceItem = new InvoiceItem()
                        newInvoiceItem.billableItem = pfBillableItem
                        newInvoiceItem.invoice = invoice
                        newInvoiceItem.taxPercent = pfBillableItem.tax.percentage
                        newInvoiceItem.zone = portForwarding.zone
                        def offerCost = MiscellaneousOfferZoneCost.findWhere(miscellaneousOffer :pfOffer, zone :portForwarding.zone)
                        newInvoiceItem.usageUnitPrice = offerCost ? offerCost.cost : 0.0;
                        newInvoiceItem.usageUnits = 1.0;
                        double usageAmount = newInvoiceItem.usageUnitPrice                
                        newInvoiceItem.usageAmount = Math.ceil(usageAmount * 100d) / 100d;   
                        double taxAmount = (newInvoiceItem.usageAmount/100)* newInvoiceItem.taxPercent
                        newInvoiceItem.taxAmount = Math.ceil(taxAmount * 100d) / 100d;     
                        newInvoiceItem.totalAmount = Math.ceil((newInvoiceItem.usageAmount + newInvoiceItem.taxAmount) * 100d) / 100d;     
                        newInvoiceItem.referenceItemName = portForwarding.name
                        newInvoiceItem.referenceItemId = portForwarding.referenceId
                        newInvoiceItem.save(flush: true); 

                    }
                }
            }
        } 
                
        def portForwardingList = PortForwarding.findAllWhere(account: user.account, userIPAddress: UserIPAddress.findByIpReferenceId(ipId))
        for(def portForwarding: portForwardingList) {
            
            HashMap item = new HashMap();    
            item.put("referenceId", portForwarding.referenceId);
            item.put("privatePort", portForwarding.privateStartPort + "-" + portForwarding.privateEndPort);
            item.put("publicPort", portForwarding.firewallRules.startPort + "-" + portForwarding.firewallRules.endPort);
            item.put("protocol", portForwarding.firewallRules.protocol);
            item.put("instanceName", portForwarding.virtualMachine.displayName);
            item.put("ip", portForwarding.destIpAddress);                        
            item.put("vmName", "VM: " + portForwarding.virtualMachine.displayName + "  IP: " + portForwarding.destIpAddress);
            portForwadRuleListResponse.add(item); 
                        
        }
        
        return portForwadRuleListResponse;
    }
    
    def addPortForwardingRuleForIp(requestBody) {
        
        try {
             
            def requestData = JSON.parse(requestBody)
            
            HashMap<String,String> optional = new HashMap<String,String>();
            optional.put("networkid", Network.get(requestData.networkId).referenceId);
            optional.put("openfirewall", "false");
            
            optional.put("privateendport", Integer.toString(requestData.privateEndPort));
            optional.put("publicendport", Integer.toString(requestData.publicEndPort));
            
            if(requestData.vmIp && requestData.vmIp != "primary") {
                optional.put("vmguestip", requestData.vmIp);
            } 
            
            def portForwardingResponse = firewallServer().createPortForwardingRule(requestData.ipId, Integer.toString(requestData.privateStartPort), 
                requestData.protocol, Integer.toString(requestData.publicStartPort), requestData.vmId, optional)

            ArrayList<ArrayList<String>> addPortForwardingResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            item.put("jobId", portForwardingResponse.jobId);
            addPortForwardingResponse.add(item)

            return addPortForwardingResponse     
        } catch (CloudStackServerException ex) {
            
            ArrayList<ArrayList<String>> addPortForwardingResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_FAILURE);
            item.put("message", ex.localizedMessage.substring(4));
            addPortForwardingResponse.add(item)

            return addPortForwardingResponse    
            
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
    }
    
    def getPortForwardingJob(jobId) {
        
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
                item.put("message", jobResponse.errorText);  
                jobResult.add(item);
                
                return jobResult  
            }
                
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
        
    }
    
    def listLoadBalancingRules(ipId) {
        
        def user = springSecurityService.currentUser
        def role = springSecurityService.getPrincipal().getAuthorities()  
        
        HashMap<String,String> optional = new HashMap<String,String>();
        optional.put("account", new String(user.account.accountIdentifier));
        optional.put("domainid", new String(user.account.domain.referenceId));
        optional.put("publicipid", ipId);
        optional.put("listAll", "true");
        
        def invoice = Invoice.findWhere(account: user.account, status: InvoiceStatus.values()[6])

        def lbOffer = MiscellaneousOffer.get(5);
        def lbBillableItem = BillableItem.get(18)
        
        def response = loadBalancerServer().listLoadBalancerRules(optional)
        
        ArrayList<ArrayList<String>> LBRuleListResponse = new ArrayList<ArrayList<String>>();    
        
        
        
        for(Iterator <LoadBalancerRuleResponse> iter = response.loadBalancerRules.iterator(); iter.hasNext();) { 
            def data = iter.next();
              
            def firewallRules = FirewallRules.findByReferenceId(data.id)
            def loadBalancer = LoadBalancer.findByReferenceId(data.id)
            if(!firewallRules && !loadBalancer) {
                
                
                def userIP = UserIPAddress.findByIpReferenceId(ipId)
                userIP.isVPCLBAdded = true;
                userIP.isVPCPFAdded = false;
                userIP.save(flush: true)
    
                firewallRules = new FirewallRules()
                firewallRules.account = user.account
                firewallRules.createdDate = new Date()
                firewallRules.referenceId = data.id
                firewallRules.userIPAddress = UserIPAddress.findByIpReferenceId(ipId)
                
                if(firewallRules.userIPAddress.network) {
                    firewallRules.netwotk = firewallRules.userIPAddress.network
                } else {
                    firewallRules.netwotk = Network.findByReferenceId(data.networkId)
                }                
                firewallRules.startPort = data.privatePort
                firewallRules.endPort = data.publicPort
                firewallRules.state = data.state
                firewallRules.protocol = "tcp" 
                if(firewallRules.userIPAddress.vpc) {
                    firewallRules.zone = firewallRules.userIPAddress.vpc.zone 
                } else {
                   firewallRules.zone = firewallRules.userIPAddress.network.zone  
                }
                firewallRules.type = "User"
                firewallRules.purpose = "LoadBalancer"
                firewallRules.purposeId = "creating"
                firewallRules.save(flush: true)    
                if (!firewallRules.save()) {
                    firewallRules.errors.allErrors.each { println it }
                }
                                
                loadBalancer = new LoadBalancer()
                loadBalancer.referenceId = data.id
                loadBalancer.createdDate = new Date()
                loadBalancer.name = data.name
                loadBalancer.algorithm = data.algorithm
                loadBalancer.privatePort = data.privatePort
                loadBalancer.publicPort = data.publicPort
                
                loadBalancer.userIPAddress = firewallRules.userIPAddress
                loadBalancer.netwotk = firewallRules.netwotk
                                
                loadBalancer.scheme = "Public"
                loadBalancer.firewallRules = firewallRules
                loadBalancer.zone = firewallRules.zone
                loadBalancer.account = user.account
                loadBalancer.save(flush:true)
                
                firewallRules.purposeId = loadBalancer.id
                firewallRules.save(flush: true)
                
                HashMap<String,String> vmOptional = new HashMap<String,String>();
                vmOptional.put("listAll", "true");
                def vmListResponse = loadBalancerServer().listLoadBalancerRuleInstances(data.id, vmOptional)
                for(Iterator <LoadBalancerRuleInstanceResponse> vmListIter = vmListResponse.loadBalancerRuleInstances.iterator(); vmListIter.hasNext();) {  
                    def VMData = vmListIter.next();
                    
                    def loadBalancerVmMap = new LoadBalancerVmMap()
                    loadBalancerVmMap.loadBalancer = loadBalancer
                    loadBalancerVmMap.virtualMachine = VirtualMachine.findByReferenceId(VMData.virtualMachineId)
                    loadBalancerVmMap.save(flush: true);
                }
                
                 if(lbBillableItem.enabled == true || lbBillableItem.enabled == "true") {

                    def newInvoiceItem = new InvoiceItem()
                    newInvoiceItem.billableItem = lbBillableItem
                    newInvoiceItem.invoice = invoice
                    newInvoiceItem.taxPercent = lbBillableItem.tax.percentage
                    newInvoiceItem.zone = loadBalancer.zone
                    def offerCost = MiscellaneousOfferZoneCost.findWhere(miscellaneousOffer :lbOffer, zone :loadBalancer.zone)
                    newInvoiceItem.usageUnitPrice = offerCost ? offerCost.cost : 0.0;
                    newInvoiceItem.usageUnits = 1.0;
                    double usageAmount = newInvoiceItem.usageUnitPrice                
                    newInvoiceItem.usageAmount = Math.ceil(usageAmount * 100d) / 100d;   
                    double taxAmount = (newInvoiceItem.usageAmount/100)* newInvoiceItem.taxPercent
                    newInvoiceItem.taxAmount = Math.ceil(taxAmount * 100d) / 100d;     
                    newInvoiceItem.totalAmount = Math.ceil((newInvoiceItem.usageAmount + newInvoiceItem.taxAmount) * 100d) / 100d;     
                    newInvoiceItem.referenceItemName = data.name
                    newInvoiceItem.referenceItemId = data.id
                    newInvoiceItem.save(flush: true); 

                }
               
            } else {
                
                def userIP = UserIPAddress.findByIpReferenceId(ipId)
                userIP.isVPCLBAdded = true;
                userIP.isVPCPFAdded = false;
                userIP.save(flush: true)
                
                def invoiceItem = InvoiceItem.findWhere(billableItem: lbBillableItem, referenceItemId:data.id, invoice:invoice)
                
                if(!invoiceItem) {
                    if(lbBillableItem.enabled == true || lbBillableItem.enabled == "true") {

                        def newInvoiceItem = new InvoiceItem()
                        newInvoiceItem.billableItem = lbBillableItem
                        newInvoiceItem.invoice = invoice
                        newInvoiceItem.taxPercent = lbBillableItem.tax.percentage
                        newInvoiceItem.zone = loadBalancer.zone
                        def offerCost = MiscellaneousOfferZoneCost.findWhere(miscellaneousOffer :lbOffer, zone :loadBalancer.zone)
                        newInvoiceItem.usageUnitPrice = offerCost ? offerCost.cost : 0.0;
                        newInvoiceItem.usageUnits = 1.0;
                        double usageAmount = newInvoiceItem.usageUnitPrice                
                        newInvoiceItem.usageAmount = Math.ceil(usageAmount * 100d) / 100d;   
                        double taxAmount = (newInvoiceItem.usageAmount/100)* newInvoiceItem.taxPercent
                        newInvoiceItem.taxAmount = Math.ceil(taxAmount * 100d) / 100d;     
                        newInvoiceItem.totalAmount = Math.ceil((newInvoiceItem.usageAmount + newInvoiceItem.taxAmount) * 100d) / 100d;     
                        newInvoiceItem.referenceItemName = data.name
                        newInvoiceItem.referenceItemId = data.id
                        newInvoiceItem.save(flush: true); 

                    }
                }
            }
        }
       
        def loadBalancerList = LoadBalancer.findAllWhere(account: user.account, userIPAddress: UserIPAddress.findByIpReferenceId(ipId))
        for(def lb: loadBalancerList) {

            HashMap item = new HashMap();    
            item.put("referenceId", lb.referenceId);
            item.put("state", lb.firewallRules.state);
            item.put("name", lb.name);
            item.put("algorithm", lb.algorithm);
            item.put("privatePort", lb.privatePort);
            item.put("publicPort", lb.publicPort);
            item.put("networkId", lb.netwotk?.referenceId);
            item.put("networkName", lb.netwotk?.name);

            String vmListString  = "";

            def lbVmList = LoadBalancerVmMap.findAllWhere(loadBalancer: lb)

            for(def lbVm : lbVmList) {
                vmListString += vmListString.size() == 0 ? vmListString : ",";
                vmListString += lbVm.virtualMachine.displayName
            }
   
            item.put("vmList", vmListString);
            LBRuleListResponse.add(item);  
        }
        
        return LBRuleListResponse;
    }
    
    def editLoadBalancingRule(requestBody) {
        try {
             
            def user = springSecurityService.currentUser
            def role = springSecurityService.getPrincipal().getAuthorities()  
            
            def requestData = JSON.parse(requestBody)
            
            HashMap<String,String> optional = new HashMap<String,String>();
            optional.put("name", requestData.name);
            optional.put("algorithm", requestData.algorithm);
            
            def loadBalancerRuleDeleteResponse = loadBalancerServer().updateLoadBalancerRule(requestData.id, optional)

            ArrayList<ArrayList<String>> loadBalancerEditResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            loadBalancerEditResponse.add(item)
            
            def lb = LoadBalancer.findByReferenceId(requestData.id)
            lb.name = requestData.name
            lb.algorithm = requestData.algorithm
            lb.save(flush: true);
            
            def invoice = Invoice.findWhere(account: user.account, status: InvoiceStatus.values()[6])
            def invoiceItem = InvoiceItem.findWhere(referenceItemId:requestData.id, invoice:invoice)
            invoiceItem.referenceItemName = requestData.name;
            invoiceItem.save(flush: true);

            return loadBalancerEditResponse     
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
    }
    
    def addVMToLoadBalancerRule(requestBody) {
        
        try {
            
            def requestData = JSON.parse(requestBody)
            
            def loadBalancerResponse = loadBalancerServer().assignToLoadBalancerRule(requestData.lbId, requestData.vmList)
                              
            ArrayList<ArrayList<String>> lbAddVMResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            item.put("jobId", loadBalancerResponse.jobId);
            lbAddVMResponse.add(item) 

            return lbAddVMResponse  
            
        }catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
        
    }
    
    def addVMToLoadBalancerRuleJob(requestBody) {
        
         try {            
             
            def requestData = JSON.parse(requestBody)
            
            ArrayList<ArrayList<String>> jobResult = new ArrayList<ArrayList<String>>();  
            def jobResponse = asyncJobServer().queryAsyncJobResult(requestData.jobId)
            if(jobResponse.jobStatus == "0") {
                
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.JOB_RESULT_PENDING);
                jobResult.add(item);
                
                return jobResult
                
            } else if(jobResponse.jobStatus == "1") {
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.RESULT_SUCCESS);
                jobResult.add(item);
                
                def vmList = requestData.vmList.split(",")
                for(def vmId: vmList) {

                    def lbVmMap = new LoadBalancerVmMap()
                    lbVmMap.virtualMachine = VirtualMachine.findByReferenceId(vmId)
                    lbVmMap.loadBalancer = LoadBalancer.findByReferenceId(requestData.lbId)
                    lbVmMap.save(flush: true);
                }
                
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
    def removeVMFromLoadBalancerRule(requestBody) { 
        
        try {
            
            def requestData = JSON.parse(requestBody)
            
            def loadBalancerResponse = loadBalancerServer().removeFromLoadBalancerRule(requestData.lbId, requestData.vmId)
                              
            ArrayList<ArrayList<String>> lbRemoveVMResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            item.put("jobId", loadBalancerResponse.jobId);
            lbRemoveVMResponse.add(item) 

            return lbRemoveVMResponse  
            
        }catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
        
    }
    
    def removeVMFromLoadBalancerJob(requestBody) { 
        
         try {            
             
            def requestData = JSON.parse(requestBody)
            
            ArrayList<ArrayList<String>> jobResult = new ArrayList<ArrayList<String>>();  
            def jobResponse = asyncJobServer().queryAsyncJobResult(requestData.jobId)
            if(jobResponse.jobStatus == "0") {
                
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.JOB_RESULT_PENDING);
                jobResult.add(item);
                
                return jobResult
                
            } else if(jobResponse.jobStatus == "1") {
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.RESULT_SUCCESS);
                jobResult.add(item);
                            
                def lbVmMap = LoadBalancerVmMap.findWhere(loadBalancer:LoadBalancer.findByReferenceId(requestData.lbId),virtualMachine:VirtualMachine.findByReferenceId(requestData.vmId))
                lbVmMap.delete(flush: true);
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
    
    def deletePortForwardingRule(requestBody) {
        
        try {
             
            def requestData = JSON.parse(requestBody)
            
            def portForwardingRuleDeleteResponse = firewallServer().deletePortForwardingRule(requestData.ruleId)

            ArrayList<ArrayList<String>> portForwardingDeleteResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            item.put("jobId", portForwardingRuleDeleteResponse.jobId);
            portForwardingDeleteResponse.add(item) 

            return portForwardingDeleteResponse     
            
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
    }
    
    
    def deleteLoadBalancingRule(requestBody) {
        
        try {
             
            def requestData = JSON.parse(requestBody)
            
            def loadBalancerRuleDeleteResponse = loadBalancerServer().deleteLoadBalancerRule(requestData.lbId)

            ArrayList<ArrayList<String>> loadBalancerDeleteResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            item.put("jobId", loadBalancerRuleDeleteResponse.jobId);
            loadBalancerDeleteResponse.add(item)

            return loadBalancerDeleteResponse     
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
    }
    
    def addLoadBalancingRuleForIp(requestBody) {
        
        try {
            def requestData = JSON.parse(requestBody)
              
            
            def user = springSecurityService.currentUser
            def role = springSecurityService.getPrincipal().getAuthorities()   

            HashMap<String,String> optional = new HashMap<String,String>();
            optional.put("account", new String(user.account.accountIdentifier));
            optional.put("domainid", new String(user.account.domain.referenceId));
            optional.put("networkid", Network.get(requestData.networkId).referenceId);
            optional.put("openfirewall", "false");
            optional.put("publicipid", requestData.ipId);
            	
            def response = loadBalancerServer().createLoadBalancerRule(requestData.algorithm,
                requestData.name, Integer.toString(requestData.privatePort), 
                Integer.toString(requestData.publicPort), optional)
                    
            
            
            def loadBalancerRuleResponse = loadBalancerServer().assignToLoadBalancerRule(response.id, requestData.vmList)
            
            ArrayList<ArrayList<String>> loadBalancerAddResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            item.put("jobId", loadBalancerRuleResponse.jobId); 
            loadBalancerAddResponse.add(item)

            return loadBalancerAddResponse      
            
        } catch (CloudStackServerException ex) {
            
            ArrayList<ArrayList<String>> loadBalancerAddResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_FAILURE);
            item.put("message", ex.localizedMessage.substring(4));
            loadBalancerAddResponse.add(item)

            return loadBalancerAddResponse    
            
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }  
        
    }
    
    def getNetworkNicIPList(networkId, vmId) {
        
        def nics = Nic.findWhere(virtualMachine: VirtualMachine.findByReferenceId(vmId), network: Network.get(networkId), deleted: false);
           
        HashMap<String,String> optional = new HashMap<String,String>();
        optional.put("nicid", nics.referenceId);
        
        def response = nicServer().listNics(vmId, optional);   
        
        ArrayList<ArrayList<String>> nicIPList = new ArrayList<ArrayList<String>>();
        for(Iterator<NicResponse> iter = response.nic.iterator(); iter.hasNext();) {
            def data = iter.next();
            HashMap item = new HashMap();    
            item.put("referenceId", "primary");
            item.put("ip", data.ipAddress + "" + " ( Primary IP ) ");
            nicIPList.add(item)
            for(Iterator<NicSecondaryIPResponse> secIpIter = data.secondaryIp.iterator(); secIpIter.hasNext();) {
                def secIpData = secIpIter.next();
                
                HashMap secIpItem = new HashMap();    
                secIpItem.put("referenceId", secIpData.ipAddress);
                secIpItem.put("ip", secIpData.ipAddress);
                nicIPList.add(secIpItem)
            }
        }
        return nicIPList;
    }
    
    def getNetworkVmList(networkId) {        
        def nicList = Nic.withCriteria {
            if(networkId) {
                eq("network", Network.get(networkId))
            }
            eq("deleted", false)                  
        }           
        ArrayList<ArrayList<String>> networkVMListResponse = new ArrayList<ArrayList<String>>();          
        for(def nic: nicList) {
            if(nic.virtualMachine.deleted == false) {
                HashMap item = new HashMap();    
                def hasVPC = nic.virtualMachine.vpc?true:false;  
                def vpcName = nic.virtualMachine.vpc?nic.virtualMachine.vpc.name:"";                  
                item.put("referenceId", nic.virtualMachine.referenceId);
                item.put("account", nic.virtualMachine.owner.firstName);                
                item.put("state", nic.virtualMachine.state);
                item.put("name", nic.virtualMachine.displayName);
                item.put("zone", nic.virtualMachine.zone.aliasName);
                item.put("osCategory", nic.virtualMachine.template.osCategory.name);
                item.put("vmId", nic.virtualMachine.id);
                item.put("hasVPC", hasVPC);     
                item.put("vpc", vpcName);     
                
                networkVMListResponse.add(item); 
            }
        }
        return networkVMListResponse;
    }
    
    
    def getLoadBalancerVmList(loadBalancingId) {
        
        def loadBalancing = LoadBalancer.findByReferenceId(loadBalancingId)
        
        def nicList = Nic.withCriteria {
            eq("network", loadBalancing.netwotk)
            and {
               eq("deleted", false)      
            }         
        }
        
        HashMap<String,String> vmList = new HashMap<String,String>(); 
        
        def loadBalancingVM = LoadBalancerVmMap.findAllByLoadBalancer(loadBalancing) 
        for(def vmMap: loadBalancingVM) { 
            vmList.put(vmMap.virtualMachine.referenceId ,"referenceId");
        }
        
        ArrayList<ArrayList<String>> networkVMListResponse = new ArrayList<ArrayList<String>>();          
        for(def nic: nicList) {
            if(nic.virtualMachine.deleted == false) {
                boolean blnExists = vmList.containsKey(nic.virtualMachine.referenceId);
                if(blnExists.toString() == "false" || blnExists == false) {
                    HashMap item = new HashMap();    
                    item.put("referenceId", nic.virtualMachine.referenceId);
                    item.put("state", nic.virtualMachine.state);
                    item.put("name", nic.virtualMachine.displayName);
                    item.put("zone", nic.virtualMachine.zone.aliasName);

                    networkVMListResponse.add(item);   
                }
            }
        }
        return networkVMListResponse;
    }
    
    def getLoadBalancerAttachedVMList(loadBalancingId) {
        ArrayList<ArrayList<String>> networkVMListResponse = new ArrayList<ArrayList<String>>();     
        def loadBalancing = LoadBalancer.findByReferenceId(loadBalancingId)
       
        def loadBalancingVM = LoadBalancerVmMap.findAllByLoadBalancer(loadBalancing) 
        for(def vmMap: loadBalancingVM) { 
            
            HashMap item = new HashMap();    
            item.put("referenceId", vmMap.virtualMachine.referenceId);
            item.put("state", vmMap.virtualMachine.state);
            item.put("name", vmMap.virtualMachine.displayName);
            item.put("zone", vmMap.virtualMachine.zone.aliasName);

            networkVMListResponse.add(item);   
        }
        return networkVMListResponse;
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
    
    def asyncJobServer() {
        
        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret
    
        CloudStackServer server =
        new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);
                
        CSAsyncJobService jobService = new CSAsyncJobService(server);
        
        return jobService;
        
    }
    
    def deleteNetwork(requestBody) {
                
        try {
            def requestData = JSON.parse(requestBody)
        
            def network = Network.get(requestData.networkId)

            def response = networkServer().deleteNetwork(network.referenceId)

            ArrayList<ArrayList<String>> networkDeleteResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            item.put("jobId", response.jobId);
            item.put("networkId", network.referenceId);
            networkDeleteResponse.add(item)

            return networkDeleteResponse   
        
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }  
    }
    
    
    def networkDeletejob(requestBody) {
        try {            
            
            def requestData = JSON.parse(requestBody)
            
            ArrayList<ArrayList<String>> jobResult = new ArrayList<ArrayList<String>>();  
            def jobResponse = asyncJobServer().queryAsyncJobResult(requestData.jobId)
            if(jobResponse.jobStatus == "0") {
                
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.JOB_RESULT_PENDING);
                jobResult.add(item);
                
                return jobResult
                
            } else if(jobResponse.jobStatus == "1") {
                
                def network = Network.findByReferenceId(requestData.networkId)
                
                network.deleted = true;
                network.save(flush: true)
                
                def userIPAddressList = UserIPAddress.findAllWhere(network: network, removed:false)
                for (def userIPAddress: userIPAddressList) {
                    
                    userIPAddress.virtualMachine = null
                    userIPAddress.network = null
                    userIPAddress.account = null
                    userIPAddress.user = null
                    userIPAddress.staticNatVirtualMachine = null
                    userIPAddress.isSourceNat =  false
                    userIPAddress.isStaticNat = false
                    userIPAddress.removed = true
                    userIPAddress.state = "Free"
                    userIPAddress.revokeDate = new Date()
                    userIPAddress.save(flush: true);
                } 
                                
                def loadBalancerList = LoadBalancer.findAllByNetwotk(network)
                for(def loadBalancer: loadBalancerList) {
                    def lbVmList = LoadBalancerVmMap.findAllWhere(loadBalancer: loadBalancer)
                    for(def lbVm : lbVmList) {
                       lbVm.delete(flush: true)
                    }
                    loadBalancer.delete(flush: true)
                    loadBalancer.firewallRules.delete(flush: true)
                }
                
                def portForwardingList = PortForwarding.findAllByNetwotk(network)
                for(def portForwarding: portForwardingList) {
                    portForwarding.delete(flush: true)
                    portForwarding.firewallRules.delete(flush: true)
                }
                def vpnList = RemoteAccessVpn.findAllByNetwork(network)
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
                item.put("message", jobResponse.errorText);                
                jobResult.add(item);                
                return jobResult  
            }
                
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }             
    }
    

    def createNetwork(requestBody) {
     
        try {
            def requestData = JSON.parse(requestBody)

            def user = springSecurityService.currentUser
            def role = springSecurityService.getPrincipal().getAuthorities()   

            HashMap<String,String> optional = new HashMap<String,String>();
            optional.put("account", new String(user.account.accountIdentifier));
            optional.put("domainid", new String(user.account.domain.referenceId));

            def response = networkServer().createNetwork(requestData.desc ,requestData.name, requestData.networkOffer, requestData.zone, optional) 

            def network = new Network()
            network.referenceId = response.id
            network.account = user.account
            network.createdDate = new Date()
            network.hasFirstIp = false
            network.description = requestData.desc
            network.name = requestData.name
            network.networkOffer = NetworkOffer.findByNetworkReferenceId(requestData.networkOffer)
            network.type = NetworkOffer.findByNetworkReferenceId(requestData.networkOffer)?.guestIpType
            network.zone = Zone.findByReferenceZoneId(requestData.zone)
            network.save(flush: true)

            ArrayList<ArrayList<String>> networkResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            networkResponse.add(item)

            log.info("Network: ${network.referenceId} Created added for account: ${user.account}") 

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
    def listNetworkByVM(vmReferenceId) {
        try {              
            def user = springSecurityService.currentUser
            def role = springSecurityService.getPrincipal().getAuthorities()              
            ArrayList<ArrayList<String>> networkListResponse = new ArrayList<ArrayList<String>>();
            if(role.iterator().next() == "ROLE_ADMIN" ) {
            } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") { 
                
                def virtualMachine = VirtualMachine.findByReferenceId(vmReferenceId);
                def nicList = Nic.withCriteria {
                    eq("deleted", false)
                    eq("account", user.account)
                    eq("virtualMachine", virtualMachine)
                }
                
                HashMap<String,String> vmConnectedToNetworkList = new HashMap<String,String>(); 
                
                for(def nic: nicList) {
                    vmConnectedToNetworkList.put(nic.network.referenceId ,"referenceId");
                }
                
                def networkList = Network.findAllWhere(deleted: false, account: user.account, zone: virtualMachine.zone)
                for(def network: networkList) {
                    boolean blnExists = vmConnectedToNetworkList.containsKey(network.referenceId);
                    if(blnExists.toString() == "false" || blnExists == false) {
                        HashMap item = new HashMap(); 
                        item.put("referenceId", network.referenceId);
                        item.put("id", network.id);
                        item.put("name", network.name);
                        item.put("description", network.description);
                        item.put("networkOffer", network.networkOffer.name);
                        item.put("zone", network.zone.aliasName);
                        item.put("gateway", network.gateway);
                        item.put("cidr", network.cidr);
                        item.put("state", network.state);
                        item.put("type", network.type);
                        item.put("trafficType", network.trafficType);
                        networkListResponse.add(item);
                    }
                    
                }
                return networkListResponse;
            }
            
            //            

            //            for(def netwo: network) {
            //                Console.print("network ref id"+ network.referenceId);
            //                HashMap item = new HashMap();    
            //                item.put("referenceId", network.referenceId);
            //                item.put("id", network.id);
            //                item.put("name", network.name);
            //                item.put("description", network.description);
            //                item.put("networkOffer", network.networkOffer.name);
            //                item.put("zone", network.zone.aliasName);
            //                item.put("gateway", network.gateway);
            //                item.put("cidr", network.cidr);
            //                item.put("state", network.state);
            //                item.put("type", network.type);
            //                item.put("trafficType", network.trafficType);
            //                networkListResponse.add(item);  
            //            }
            //            return networkListResponse

        } catch (Exception ex) {
            [ex] as JSON
        }
    }
    
    def listNetwork(zoneReferenceId) {
        
        try {            
            def user = springSecurityService.currentUser
            def role = springSecurityService.getPrincipal().getAuthorities()   
            def networkList;


            if(role.iterator().next() == "ROLE_ADMIN" ) {

                networkList = Network.withCriteria {
                    eq("deleted", false)           
                    ne("account", user.account) 
                }

            } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {

                HashMap<String,String> optional = new HashMap<String,String>();
                optional.put("account", new String(user.account.accountIdentifier));
                optional.put("domainid", new String(user.account.domain.referenceId));
                networkServer().listNetworks(optional)

                def response = networkServer().listNetworks(optional);
                for(Iterator <NetworkResponse> iter = response.networks.iterator(); iter.hasNext();) {
                    def data = iter.next();

                    def network = Network.findByReferenceId(data.id)
                    if(network) {
                        network.cidr = data.networkCidr
                        network.gateway = data.gateway
                        network.state = data.state
                        network.type = data.type
                        network.trafficType = data.trafficType
                        network.save(flush: true)
                    }
                }            
                //            networkList = Network.findAllWhere(deleted: false, account: user.account) 
                networkList = Network.withCriteria {
                    if(zoneReferenceId != "All") {
                        eq("zone", Zone.findByReferenceZoneId(zoneReferenceId))
                    }
                    eq("account", user.account)
                    eq("deleted", false)
                    isNull("vpc")
                }
            }
        
            ArrayList<ArrayList<String>> networkListResponse = new ArrayList<ArrayList<String>>();
                    
            for(def network: networkList) {
                HashMap item = new HashMap();    
                item.put("referenceId", network.referenceId);
                item.put("id", network.id);
                item.put("name", network.name);
                item.put("description", network.description);
                item.put("networkOffer", network.networkOffer.name);
                item.put("zone", network.zone.aliasName);
                item.put("gateway", network.gateway);
                item.put("cidr", network.cidr);
                item.put("state", network.state);
                item.put("type", network.type);
                item.put("account", network.account.email);
                item.put("trafficType", network.trafficType);
                networkListResponse.add(item);  
            }
            return networkListResponse            
        } catch (CloudStackServerException ex) {            
            ex.printStackTrace(System.err);
            throw ex;              
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }                       
    }
    
    
    def addEgressRule(requestBody) {
     
        try {
            def requestData = JSON.parse(requestBody)

            HashMap<String,String> optional = new HashMap<String,String>();
            if(requestData.protocol == "ICMP") {
                if(requestData.icmpCode) {
                    optional.put("icmpcode", Integer.toString(requestData.icmpCode));
                }
                if(requestData.icmpType) {
                    optional.put("icmptype", Integer.toString(requestData.icmpType));
                }
            } else if(requestData.protocol == "TCP" || requestData.protocol == "UDP") {
                if(requestData.endPort) {
                    optional.put("endport", Integer.toString(requestData.endPort));
                }
                if(requestData.startPort) {
                    optional.put("startport", Integer.toString(requestData.startPort));
                }
            } 
            if(requestData.cidr) {
                optional.put("cidrlist", requestData.cidr); 
            }   
            def network = Network.get(requestData.networkId)

            def response = firewallServer().createEgressFirewallRule(network.referenceId, requestData.protocol, optional)

            def jobResponse = asyncJobServer().queryAsyncJobResult(response.jobId)


            ArrayList<ArrayList<String>> networkEgressResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            item.put("jobId", response.jobId);
            networkEgressResponse.add(item)

            return networkEgressResponse        
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
    
    def getLoadBalancingJob(jobId) {
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
                item.put("message", jobResponse.errorText);  
                jobResult.add(item);
                
                return jobResult  
            }
                
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }             
    }
    
    def getLoadBalancingDeleteJobStatus(requestBody) {
        
        def requestData = JSON.parse(requestBody)
        
        try {            
                        
            ArrayList<ArrayList<String>> jobResult = new ArrayList<ArrayList<String>>();  
            def jobResponse = asyncJobServer().queryAsyncJobResult(requestData.jobId)
            if(jobResponse.jobStatus == "0") {
                
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.JOB_RESULT_PENDING);
                jobResult.add(item);
                
                return jobResult
                
            } else if(jobResponse.jobStatus == "1") {
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.RESULT_SUCCESS);
                jobResult.add(item);
                                
                def loadBalancer = LoadBalancer.findByReferenceId(requestData.lbId)
                if(loadBalancer) {
                    def lbVmList = LoadBalancerVmMap.findAllWhere(loadBalancer: loadBalancer)
                    for(def lbVm : lbVmList) {
                       lbVm.delete(flush: true)
                    }
                    loadBalancer.delete(flush: true)
                    loadBalancer.firewallRules.delete(flush: true)
                }
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
    
    def getPortForwardingDeleteJobStatus(requestBody) {
        
        def requestData = JSON.parse(requestBody)
        
        try {            
                        
            ArrayList<ArrayList<String>> jobResult = new ArrayList<ArrayList<String>>();  
            def jobResponse = asyncJobServer().queryAsyncJobResult(requestData.jobId)
            if(jobResponse.jobStatus == "0") {
                
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.JOB_RESULT_PENDING);
                jobResult.add(item);
                
                return jobResult
                
            } else if(jobResponse.jobStatus == "1") {
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.RESULT_SUCCESS);
                jobResult.add(item);
                                
                def portForwarding = PortForwarding.findByReferenceId(requestData.portForwardId)
                if(portForwarding) {
                    portForwarding.delete(flush: true)
                    portForwarding.firewallRules.delete(flush: true)
                }
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
    
    def job(jobId) {
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
    
    def deleteEgressRule(id) {
        firewallServer().deleteEgressFirewallRule(id)
    }
    
    def getNetworkEgressRules(id) {
        
        def network = Network.get(id)
        
        HashMap<String,String> optional = new HashMap<String,String>();
        optional.put("networkid", network.referenceId);
        optional.put("listall", "true");
        
        ArrayList<ArrayList<String>> networkRuleList = new ArrayList<ArrayList<String>>();
        
        def response = firewallServer().listEgressFirewallRules(optional)
                                        
        for(Iterator <EgressRuleResponse> iter = response.egressFirewallRules.iterator(); iter.hasNext();) {
            def data = iter.next();
            HashMap item = new HashMap();    
            item.put("id", data.id);
            item.put("cidrList", data.cidrList);           
            if(data.protocol == "icmp") {
                item.put("startPort", data.icmpCode);
                item.put("endPort", data.icmpType);
                item.put("protocol", "ICMP");
            } else if(data.protocol == "tcp" || data.protocol == "udp") {
                item.put("endPort", data.endPort);
                item.put("startPort", data.startPort);
                if(data.protocol == "udp") {
                    item.put("protocol", "UDP");
                } else {
                    item.put("protocol", "TCP"); 
                }                
            } else {
                item.put("endPort", "ALL");
                item.put("startPort", "ALL");
                item.put("protocol", "ALL");
            }
            
            networkRuleList.add(item); 
        }
        return networkRuleList;
    }
    
    def addFirewallForIp(requestBody) {
        try {
            def requestData = JSON.parse(requestBody)

            HashMap<String,String> optional = new HashMap<String,String>();
            if(requestData.protocol == "ICMP") {
                optional.put("icmpcode", Integer.toString(requestData.icmpCode));             
                optional.put("icmptype", Integer.toString(requestData.icmpType));
        
            } else if(requestData.protocol == "TCP" || requestData.protocol == "UDP") {
                if(requestData.endPort) {
                    optional.put("endport", Integer.toString(requestData.endPort));
                }
                if(requestData.startPort) {
                    optional.put("startport", Integer.toString(requestData.startPort));
                }
            } 
            optional.put("cidrlist", requestData.cidr); 
           
            def response = firewallServer().createFirewallRule(requestData.ipId, requestData.protocol, optional)
       
            ArrayList<ArrayList<String>> networkEgressResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            item.put("jobId", response.jobId);
            networkEgressResponse.add(item)

            return networkEgressResponse        
            
        } catch (CloudStackServerException ex) {
            
            ArrayList<ArrayList<String>> networkEgressResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_FAILURE);
            item.put("message", ex.localizedMessage.substring(4));
            networkEgressResponse.add(item)

            return networkEgressResponse    
            
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
    }
    
    def deleteIPFirewallRule(id) {
        firewallServer().deleteFirewallRule(id)
    }
    
    def getFirewallRulesForIp(id) {
                
        HashMap<String,String> optional = new HashMap<String,String>();
        optional.put("ipaddressid", id);
        optional.put("listall", "true");
        
        ArrayList<ArrayList<String>> networkFirewallRuleList = new ArrayList<ArrayList<String>>();
        
        def response = firewallServer().listFirewallRules(optional)
        
        for(Iterator <EgressRuleResponse> iter = response.firewallRules.iterator(); iter.hasNext();) {
            def data = iter.next();
            HashMap item = new HashMap();    
            item.put("id", data.id);
            item.put("cidrList", data.cidrList);           
            if(data.protocol == "icmp") {
                item.put("startPort", data.icmpCode);
                item.put("endPort", data.icmpType);
                item.put("protocol", "ICMP");
            } else if(data.protocol == "tcp" || data.protocol == "udp") {
                item.put("endPort", data.endPort);
                item.put("startPort", data.startPort);
                if(data.protocol == "udp") {
                    item.put("protocol", "UDP");
                } else {
                    item.put("protocol", "TCP"); 
                }                
            } else {
                item.put("endPort", "ALL");
                item.put("startPort", "ALL");
                item.put("protocol", "ALL");
            }
            
            networkFirewallRuleList.add(item); 
        }
        return networkFirewallRuleList;
    }
    
    
    
    def getNetworkIpAddressesList(networkId) {
        try {           
            def network = Network.get(networkId)
            
            HashMap<String,String> optional = new HashMap<String,String>();
            optional.put("associatedNetworkId", network.referenceId);
            optional.put("forvirtualnetwork", "true");
            optional.put("listAll", "true");
            
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
                    if(userIPAddress.isSourceNat == true) {
                        userIPAddress.isFirstIp = true
                        network.hasFirstIp = true;
                        network.save(flush: true)  
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

                    if(userIPAddress.isSourceNat == true) {
                        userIPAddress.isFirstIp = true
                    }
                    userIPAddress.save(flush: true)
                }               
                def isEnabledVPN = RemoteAccessVpn.findByNetwork(Network.findByReferenceId(data.associatedNetworkid))? true: false;
                HashMap item = new HashMap();   
                if(userIPAddress) {     
                    item.put("id", data.id);
                    item.put("associateNeworkReferenceId", data.associatedNetworkid);                
                    item.put("ip", data.ipAddress);
                    item.put("account", userIPAddress.account.firstName);                                
                    item.put("isSourceNat", data.isSourceNat);
                    item.put("isStaticNat", data.isStaticNat);
                    item.put("networkReferenceId", data.networkId);
                    item.put("state", data.state);
                    item.put("vmDisplayName", data.virtualMachineDisplayName);
                    item.put("vmReferenceId", data.virtualMachineId);
                    item.put("vlanId", data.vlanId);  
                    item.put("vlanName", data.vlanName);
                    item.put("zone", Zone.findByReferenceZoneId(data.zoneId)?.aliasName);
                    item.put("vpnEnabled", RemoteAccessVpn.findByPublicIPAddress(UserIPAddress.findByIpReferenceId(data.id))? true: false);
                    item.put("isEnabledVPN", isEnabledVPN);              

                    item.put("isVPCLBAdded", LoadBalancer.findAllByUserIPAddress(UserIPAddress.findByIpReferenceId(data.id)) ? true : false);
                    item.put("isVPCPFAdded", PortForwarding.findAllByUserIPAddress(UserIPAddress.findByIpReferenceId(data.id)) ? true : false);
                    networkIPListResponse.add(item)                     
                }                    
            }
            return networkIPListResponse
        } catch(Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
        
    }    
    
    
    def acquireIpForNetwork(requestBody) {
          
        try {    
            
            def user = springSecurityService.currentUser
            
            def requestData = JSON.parse(requestBody)

            def network = Network.get(requestData.networkId)
            
            def invoice = Invoice.findWhere(account: user.account, status: InvoiceStatus.values()[6])
            
            if(network.hasFirstIp == true && !invoice) {
                
                ArrayList<ArrayList<String>> networkIPAcquireResponse = new ArrayList<ArrayList<String>>();
                HashMap item = new HashMap();    
                item.put("result", "HASFIRSTIP");
                networkIPAcquireResponse.add(item)

                return networkIPAcquireResponse;
                 
            } else {
                HashMap<String,String> optional = new HashMap<String,String>();
                optional.put("networkid", network.referenceId);

                def response = addressServer().associateIpAddress(optional);     

                ArrayList<ArrayList<String>> networkIPAcquireResponse = new ArrayList<ArrayList<String>>();
                HashMap item = new HashMap();    
                item.put("result", GeneralConstants.RESULT_SUCCESS);
                item.put("jobId", response.jobId);
                item.put("networkReferenceId", network.referenceId);
                networkIPAcquireResponse.add(item)

                return networkIPAcquireResponse;

                log.info("IP Acquired for Network: ${network.referenceId}") 
            }
            
            
        } catch (CloudStackServerException ex) {
            
            ArrayList<ArrayList<String>> networkIPAcquireResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_FAILURE);
            item.put("message", ex.localizedMessage.substring(4));
            networkIPAcquireResponse.add(item)

            return networkIPAcquireResponse    
            
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
    }
    
    def releaseIpForNetwork(requestBody) {
        try {
            
            def requestData = JSON.parse(requestBody)
            
            def response;
            
            def userIPAddress = UserIPAddress.findByIpReferenceId(requestData.ipId)   
            
            if(userIPAddress.zone.networkType == "Basic") {
                response = nicServer().removeIpFromNic(requestData.ipId) 
            } else {
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
    
    
    def disableStaticNat(requestBody) {
        
        try {
             
            def requestData = JSON.parse(requestBody)
                       
            def disableStaticNatResponse = NATServer().disableStaticNat(requestData.ipId)

            ArrayList<ArrayList<String>> disableStaticResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            
            if(disableStaticNatResponse.success) {
                item.put("result", disableStaticNatResponse.success);
            } else {
                item.put("result", GeneralConstants.RESULT_SUCCESS);
                item.put("jobId", disableStaticNatResponse.jobId);
            }
            disableStaticResponse.add(item)

            return disableStaticResponse     
        }  catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
        
    }
    
    def getDisableStaticNatJob(jobId) {
        
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
    
    def getIpAddressesDetails(id) {
        
        HashMap<String,String> optional = new HashMap<String,String>();
        optional.put("id", id);
        
        def response = addressServer().listPublicIpAddresses(optional);       
            
        ArrayList<ArrayList<String>> IPDetailsResponse = new ArrayList<ArrayList<String>>();
            
        for(Iterator <IpAddressResponse> iter = response.ipAddresses.iterator(); iter.hasNext();) {
            def data = iter.next();
            def isEnabledVPN = RemoteAccessVpn.findByNetwork(Network.findByReferenceId(data.associatedNetworkid))? true: false;            
            HashMap item = new HashMap();    
            item.put("id", data.id);
            item.put("networkName", Network.findByReferenceId(data.associatedNetworkid)?.name);
            item.put("networkId", Network.findByReferenceId(data.associatedNetworkid)?.id);
            item.put("networkReferenceId", Network.findByReferenceId(data.associatedNetworkid)?.referenceId);
            item.put("ip", data.ipAddress);   
            item.put("isSourceNat", data.isSourceNat);
            item.put("isStaticNat", data.isStaticNat);
            item.put("state", data.state);
            item.put("vmDisplayName", data.virtualMachineDisplayName);
            item.put("vmId", VirtualMachine.findByReferenceId(data.virtualMachineId)?.id);
            item.put("vlanId", data.vlanId);
            item.put("vlanName", data.vlanName);
            item.put("zone", Zone.findByReferenceZoneId(data.zoneId)?.aliasName);
            item.put("vpnEnabled", RemoteAccessVpn.findByPublicIPAddress(UserIPAddress.findByIpReferenceId(data.id))? true: false);
            item.put("isEnabledVPN", isEnabledVPN);
            
            item.put("isVPCLBAdded", LoadBalancer.findAllByUserIPAddress(UserIPAddress.findByIpReferenceId(data.id)) ? true : false);
            item.put("isVPCPFAdded", PortForwarding.findAllByUserIPAddress(UserIPAddress.findByIpReferenceId(data.id)) ? true : false);
            
            item.put("vpcId",  UserIPAddress.findByIpReferenceId(data.id)?.vpc?.referenceId);
            item.put("vpcName",  UserIPAddress.findByIpReferenceId(data.id)?.vpc?.name); 
            
            IPDetailsResponse.add(item)

        }
        return IPDetailsResponse
    }
    
    def acquireIpJobForNetwork(requestBody) {
        try {            
            
            def user = springSecurityService.currentUser
            
            def requestData = JSON.parse(requestBody)
            
            ArrayList<ArrayList<String>> jobResult = new ArrayList<ArrayList<String>>();  
            def jobResponse = addressServer().addIpJobResult(requestData.jobId)
            if(jobResponse.jobStatus == "0") {
                
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.JOB_RESULT_PENDING);
                jobResult.add(item);
                
                return jobResult
                
            } else if(jobResponse.jobStatus == "1") {
                
                def network = Network.findByReferenceId(requestData.networkReferenceId)
                
                //                Console.print("jobResponse.secondaryIpId" + jobResponse.secondaryIpId)
                //                Console.print("jobResponse.secondaryIp" + jobResponse.secondaryIp)
               
                if(network.hasFirstIp == true) {

                    def invoice = Invoice.findWhere(account: user.account, status: InvoiceStatus.values()[6])

                    def ipOffer = MiscellaneousOffer.get(2);
                    def ipBillableItem = BillableItem.get(12)
                    if(ipBillableItem.enabled == true || ipBillableItem.enabled == "true") {

                        def newInvoiceItem = new InvoiceItem()
                        newInvoiceItem.billableItem = ipBillableItem
                        newInvoiceItem.invoice = invoice
                        newInvoiceItem.taxPercent = ipBillableItem.tax.percentage
                        newInvoiceItem.zone = network.zone
                        newInvoiceItem.usageUnitPrice = MiscellaneousOfferZoneCost.findWhere(miscellaneousOffer :ipOffer, zone :network.zone).cost;
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
                network.hasFirstIp = true;
                network.save(flush: true)
                
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
    
    
    def getNetwork(id) {   
        
        def network = Network.get(id)              
        ArrayList<ArrayList<String>> networkList = new ArrayList<ArrayList<String>>();
        HashMap item = new HashMap(); 
        
        HashMap<String,String> optional = new HashMap<String,String>();
        optional.put("id", network.referenceId);        
        def response = networkServer().listNetworks(optional);
        for(Iterator <NetworkResponse> iter = response.networks.iterator(); iter.hasNext();) {
            def data = iter.next();            
            item.put("networkDomain", data.networkDomain);
            item.put("netmask", data.netmask);
            item.put("cidr", data.cidr);
        }          
        item.put("referenceId", network.referenceId);
        item.put("id", network.id);
        item.put("name", network.name);
        item.put("description", network.description);
        item.put("networkOffer", network.networkOffer.name);
        item.put("networkOfferReferenceId", network.networkOffer.networkReferenceId);        
        item.put("zone", network.zone.aliasName);
        item.put("zoneId", network.zone.id);        
        item.put("gateway", network.gateway);        
        item.put("state", network.state);
        item.put("type", network.type);
        item.put("account", network.account.firstName);        
        
        
        
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
                
        item.put("tierType", tierType);
        item.put("vpcName", network.vpc?.name);
        item.put("vpcId", network.vpc?.referenceId);
        
        
        item.put("trafficType", network.trafficType);
        networkList.add(item);
        return networkList;
    }
    
    def updateNetwork(requestBody) {
        
        try {      
            def requestData = JSON.parse(requestBody)

            def user = springSecurityService.currentUser
            def role = springSecurityService.getPrincipal().getAuthorities()  

            def network = Network.get(requestData.id)

            HashMap<String,String> optional = new HashMap<String,String>();
            optional.put("displaytext", requestData.name);
            optional.put("name", requestData.desc);

            def response = networkServer().updateNetwork(network.referenceId, optional);

            network.name =requestData.name
            network.description = requestData.desc
            network.save(flush: true);

            ArrayList<ArrayList<String>> networkResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            networkResponse.add(item)

            log.info("Network: ${network.referenceId} Updated by account: ${user.account}") 

            return networkResponse;
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
    }
    
    def getInternalLoadBalancerList(loadBalancingId, networkId) {
        try {   
        
            def user = springSecurityService.currentUser
            def role = springSecurityService.getPrincipal().getAuthorities()      
            def networkAccout = Network.findByReferenceId(networkId);
            HashMap<String,String> optional = new HashMap<String,String>();
            optional.put("account", new String(networkAccout.account.accountIdentifier));
            optional.put("domainid", new String(networkAccout.account.domain.referenceId));
            optional.put("networkid", networkId);
            if(loadBalancingId) {
               optional.put("id", loadBalancingId);  
            }

            def response = loadBalancerServer().listLoadBalancers(optional)

            ArrayList<ArrayList<String>> LBRuleListResponse = new ArrayList<ArrayList<String>>();                

            for(Iterator <LoadBalancersResponse> iter = response.loadBalancerLists.iterator(); iter.hasNext();) { 
                def data = iter.next();                
                
                
                HashMap item = new HashMap();
                item.put("id", data.id);
                item.put("algorithm", data.algorithm);
                item.put("description", data.description);
                item.put("loadBalancerName", data.name);
                item.put("account", networkAccout.account.firstName);
                
                
                for(Iterator <LoadBalancerResponse> lbruleiter1 = data.loadBalancers.iterator(); lbruleiter1.hasNext();) { 
                        def lbrule1 = lbruleiter1.next();

                        item.put("instancePort", lbrule1.instancePort);
                        item.put("sourcePort", lbrule1.sourcePort);
                        item.put("state", lbrule1.state);
                }
                
                String vmListString = ""
                for(Iterator <LoadBalancerInstanceResponse> vmlbitter1 = data.loadBalancerInstances.iterator(); vmlbitter1.hasNext();) { 
                    def vmlb1 = vmlbitter1.next();
                    def vm1 = VirtualMachine.findByReferenceId(vmlb1.id)   
                    vmListString += vmListString.size() == 0 ? vmListString : ",";
                    vmListString += vm1?.displayName

                }
                item.put("vm", vmListString);
                LBRuleListResponse.add(item);
                
                
                def firewallRules = FirewallRules.findByReferenceId(data.id)
                def loadBalancer = LoadBalancer.findByReferenceId(data.id)
                if(!firewallRules && !loadBalancer) {

                    firewallRules = new FirewallRules()
                    firewallRules.account = user.account
                    firewallRules.createdDate = new Date()
                    firewallRules.referenceId = data.id
//                    firewallRules.userIPAddress = UserIPAddress.findByIpReferenceId(ipId)
                    
                    firewallRules.netwotk = Network.findByReferenceId(networkId)
                             
                    firewallRules.startPort = "internallb"
                    firewallRules.endPort = "internallb"
                    firewallRules.state = "internallb"
                    firewallRules.protocol = "internallb" 
                    firewallRules.zone = firewallRules.netwotk.zone  
                    firewallRules.type = "User"
                    firewallRules.purpose = "Internal LoadBalancer"
                    firewallRules.purposeId = "creating"
                    firewallRules.save(flush: true)    
                    if (!firewallRules.save()) {
                        firewallRules.errors.allErrors.each { println it }
                    }

                    loadBalancer = new LoadBalancer()
                    loadBalancer.referenceId = data.id
                    loadBalancer.description = data.description
                    loadBalancer.createdDate = new Date()
                    loadBalancer.name = data.name
                    loadBalancer.algorithm = data.algorithm
                    for(Iterator <LoadBalancerResponse> lbruleiter = data.loadBalancers.iterator(); lbruleiter.hasNext();) { 
                        def lbrule = lbruleiter.next();                        
                        loadBalancer.privatePort = lbrule.instancePort
                        loadBalancer.publicPort = lbrule.sourcePort
                    }

                    loadBalancer.userIPAddress = firewallRules.userIPAddress
                    loadBalancer.netwotk = firewallRules.netwotk

                    loadBalancer.scheme = "Internal"
                    loadBalancer.firewallRules = firewallRules
                    loadBalancer.zone = firewallRules.zone
                    loadBalancer.account = user.account
                    loadBalancer.save(flush:true)
                    if (!loadBalancer.save()) {
                        loadBalancer.errors.allErrors.each { println it }
                    } 
                    firewallRules.purposeId = loadBalancer.id
                    firewallRules.save(flush: true)
                    
                    for(Iterator <LoadBalancerInstanceResponse> vmlbitter = data.loadBalancerInstances.iterator(); vmlbitter.hasNext();) { 
                        def vmlb = vmlbitter.next();
                        def vm = VirtualMachine.findByReferenceId(vmlb.id)
                        
                        def loadbalancervmmap = LoadBalancerVmMap.findAllWhere(loadBalancer : loadBalancer, virtualMachine:VirtualMachine.findByReferenceId(vmlb.id))
                        
                        if(!loadbalancervmmap) {
                            def loadBalancerVmMap = new LoadBalancerVmMap()
                            loadBalancerVmMap.loadBalancer = loadBalancer
                            loadBalancerVmMap.virtualMachine = VirtualMachine.findByReferenceId(vmlb.id)
                            loadBalancerVmMap.save(flush: true); 
                        }
                        

                    }
//                    HashMap<String,String> vmOptional = new HashMap<String,String>();
//                    vmOptional.put("listAll", "true");
//                    def vmListResponse = loadBalancerServer().listLoadBalancerRuleInstances(data.loadBalancerId, vmOptional)
//                    for(Iterator <LoadBalancerRuleInstanceResponse> vmListIter = vmListResponse.loadBalancerRuleInstances.iterator(); vmListIter.hasNext();) {  
//                        def VMData = vmListIter.next();
//
//                        
//                    }

//                     if(lbBillableItem.enabled == true || lbBillableItem.enabled == "true") {
//
//                        def newInvoiceItem = new InvoiceItem()
//                        newInvoiceItem.billableItem = lbBillableItem
//                        newInvoiceItem.invoice = invoice
//                        newInvoiceItem.taxPercent = lbBillableItem.tax.percentage
//                        newInvoiceItem.zone = loadBalancer.zone
//                        def offerCost = MiscellaneousOfferZoneCost.findWhere(miscellaneousOffer :lbOffer, zone :loadBalancer.zone)
//                        newInvoiceItem.usageUnitPrice = offerCost ? offerCost.cost : 0.0;
//                        newInvoiceItem.usageUnits = 1.0;
//                        double usageAmount = newInvoiceItem.usageUnitPrice                
//                        newInvoiceItem.usageAmount = Math.ceil(usageAmount * 100d) / 100d;   
//                        double taxAmount = (newInvoiceItem.usageAmount/100)* newInvoiceItem.taxPercent
//                        newInvoiceItem.taxAmount = Math.ceil(taxAmount * 100d) / 100d;     
//                        newInvoiceItem.totalAmount = Math.ceil((newInvoiceItem.usageAmount + newInvoiceItem.taxAmount) * 100d) / 100d;     
//                        newInvoiceItem.referenceItemName = data.loadBalancerName
//                        newInvoiceItem.referenceItemId = data.loadBalancerId
//                        newInvoiceItem.save(flush: true); 
//
//                    }

                } else {
                    loadBalancer.description = data.description
                    loadBalancer.save(flush: true);   
                    for(Iterator <LoadBalancerInstanceResponse> vmlbitter = data.loadBalancerInstances.iterator(); vmlbitter.hasNext();) { 
                        def vmlb = vmlbitter.next();
                        def vm = VirtualMachine.findByReferenceId(vmlb.id)
                        
                        def loadbalancervmmap = LoadBalancerVmMap.findAllWhere(loadBalancer : loadBalancer, virtualMachine:VirtualMachine.findByReferenceId(vmlb.id))
                        
                        if(!loadbalancervmmap) {
                            def loadBalancerVmMap = new LoadBalancerVmMap()
                            loadBalancerVmMap.loadBalancer = loadBalancer
                            loadBalancerVmMap.virtualMachine = VirtualMachine.findByReferenceId(vmlb.id)
                            loadBalancerVmMap.save(flush: true); 
                        }
                        

                    }
                    
                    
//                    def userIP = UserIPAddress.findByIpReferenceId(ipId)
//                    userIP.isVPCLBAdded = true;
//                    userIP.isVPCPFAdded = false;
//                    userIP.save(flush: true)

//                    def invoiceItem = InvoiceItem.findWhere(billableItem: lbBillableItem, referenceItemId:data.loadBalancerId, invoice:invoice)

//                    if(!invoiceItem) {
//                        if(lbBillableItem.enabled == true || lbBillableItem.enabled == "true") {
//
//                            def newInvoiceItem = new InvoiceItem()
//                            newInvoiceItem.billableItem = lbBillableItem
//                            newInvoiceItem.invoice = invoice
//                            newInvoiceItem.taxPercent = lbBillableItem.tax.percentage
//                            newInvoiceItem.zone = loadBalancer.zone
//                            def offerCost = MiscellaneousOfferZoneCost.findWhere(miscellaneousOffer :lbOffer, zone :loadBalancer.zone)
//                            newInvoiceItem.usageUnitPrice = offerCost ? offerCost.cost : 0.0;
//                            newInvoiceItem.usageUnits = 1.0;
//                            double usageAmount = newInvoiceItem.usageUnitPrice                
//                            newInvoiceItem.usageAmount = Math.ceil(usageAmount * 100d) / 100d;   
//                            double taxAmount = (newInvoiceItem.usageAmount/100)* newInvoiceItem.taxPercent
//                            newInvoiceItem.taxAmount = Math.ceil(taxAmount * 100d) / 100d;     
//                            newInvoiceItem.totalAmount = Math.ceil((newInvoiceItem.usageAmount + newInvoiceItem.taxAmount) * 100d) / 100d;     
//                            newInvoiceItem.referenceItemName = data.loadBalancerName
//                            newInvoiceItem.referenceItemId = data.loadBalancerId
//                            newInvoiceItem.save(flush: true); 
//
//                        }
//                    }
                }
                
            }   
            return LBRuleListResponse
            
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }  
    }
    def addVMToInternalLoadBalancerRule(requestData) {
        try {   
        
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
    }
    
    def deleteInternalLoadBalancingRule(requestBody) {
        
        try {
             
            def requestData = JSON.parse(requestBody)
            
            def loadBalancerRuleDeleteResponse = loadBalancerServer().deleteLoadBalancer(requestData.lbId)

            ArrayList<ArrayList<String>> loadBalancerDeleteResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            item.put("jobId", loadBalancerRuleDeleteResponse.jobId);
            loadBalancerDeleteResponse.add(item)

            return loadBalancerDeleteResponse     
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
    }
    
    
    def addInternalLoadBalancer(requestData) {
        try {
              
            def user = springSecurityService.currentUser
            def role = springSecurityService.getPrincipal().getAuthorities()   

            HashMap<String,String> optional = new HashMap<String,String>();
            optional.put("description", requestData.description);
            	
            def response = loadBalancerServer().createLoadBalancer(requestData.algorithm, Integer.toString(requestData.instancePort), 
                requestData.name, requestData.networkId, "Internal", requestData.networkId, 
                Integer.toString(requestData.sourcePort), optional)
                    
           
            ArrayList<ArrayList<String>> loadBalancerAddResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            item.put("jobId", response.jobId);
            loadBalancerAddResponse.add(item)

            return loadBalancerAddResponse      
            
        } catch (CloudStackServerException ex) {
            
            ArrayList<ArrayList<String>> loadBalancerAddResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_FAILURE);
            item.put("message", ex.localizedMessage.substring(4));
            loadBalancerAddResponse.add(item)

            return loadBalancerAddResponse    
            
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
    }
    
    def internalLoadBalancerJob(jobId) {
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
    
    def getInternalLoadBalancingDeleteJobStatus(requestData) {
        
        try {            
                        
            ArrayList<ArrayList<String>> jobResult = new ArrayList<ArrayList<String>>();  
            def jobResponse = asyncJobServer().queryAsyncJobResult(requestData.jobId)
            if(jobResponse.jobStatus == "0") {
                
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.JOB_RESULT_PENDING);
                jobResult.add(item);
                
                return jobResult
                
            } else if(jobResponse.jobStatus == "1") {
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.RESULT_SUCCESS);
                jobResult.add(item);
                                
                def loadBalancer = LoadBalancer.findByReferenceId(requestData.lbId)
                if(loadBalancer) {
                    def lbVmList = LoadBalancerVmMap.findAllWhere(loadBalancer: loadBalancer)
                    for(def lbVm : lbVmList) {
                       lbVm.delete(flush: true)
                    }
                    loadBalancer.delete(flush: true)
                    loadBalancer.firewallRules.delete(flush: true)
                }
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
    def pullNetworkFromCloudStackJobStart() {
        
        PullNetworkJob.triggerNow([id:"network"])
            
        return "OK"
        
    }
    def pullNetworkCloudStack(jobid) {
        
        def job = AsynchronousJobs.get(jobid)
        try { 
            
            job.status = JobStatus.valueOf("RUNNING")
            job.startedAt = new Date()
            job.save(flush: true)
            
            def response = networkServer().listNetworks(null);
            
            for(Iterator <NetworkResponse> iter = response.networks.iterator(); iter.hasNext();) {
                def data = iter.next();
               
                def network = Network.findByReferenceId(data.id)
                
                if(!network) {
                    
                    def networkOffer =  NetworkOffer.findByNetworkReferenceId(data.networkOfferingId)
                    Domain domain = Domain.findByReferenceId(data.domainId);
                    Console.print("domainb id" + data.domainId + "account" + data.account)
                    
                    network = new Network();
                    network.referenceId = data.id
                    network.account = Account.findWhere(accountIdentifier : data.account,domain : domain );
                    network.aclReferenceId = data.aclId 
                    network.broadcastUri = data.broadcastUri
                    network.cidr = data.networkCidr
                    network.description = data.displayText
                    network.gateway = data.gateway  
                    network.name = data.name
                    network.networkDomain = data.domain
                    network.networkOffer = networkOffer                 
                    network.state = data.state   
                    network.type = data.type
                    network.trafficType = data.trafficType
                    network.vpc = VPC.findByReferenceId(data.vpcId)
                    network.zone = Zone.findByReferenceZoneId(data.zoneId)
                    

                    HashMap<String,String> optional = new HashMap<String,String>();
                    optional.put("forvirtualnetwork", "true");
                    optional.put("listAll", "true");
                    optional.put("associatednetworkid", data.id);
                    def addressResponse = addressServer().listPublicIpAddresses(optional);   
                    
                    if(addressResponse.ipAddresses.iterator().size() > 0) {
                        
                        network.hasFirstIp = true
                    } else {
                        
                        network.hasFirstIp = false
                    }    
                                
                    def tierType = "";                    
                    def web = NetworkOfferServiceList.withCriteria {
                        eq('networkOffer', networkOffer)
                        'in'("service",["Lb"])
                    }

                    def app = NetworkOfferServiceList.withCriteria {
                        eq('networkOffer', networkOffer)
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

                    if (!network.save(flush: true)) {
                        network.errors.allErrors.each { println it }
                    }
                    
                } else {
                    
                    
                    network.name = data.name
                    network.networkDomain = data.domain
                    network.description = data.displayText
                    def networkOffer =  NetworkOffer.findByNetworkReferenceId(data.networkOfferingId)
                    network.networkOffer = networkOffer   
                    network.cidr = data.cidr
                    
                    if (!network.save(flush: true)) {
                        network.errors.allErrors.each { println it }
                    }
                    
                }
            }       
            
            job.status = JobStatus.valueOf("COMPLETED")
            job.completedAt = new Date()
            job.save(flush: true)
            
        } catch (Exception ex) {
            
            job.status = JobStatus.valueOf("ERROR")
            job.completedAt = new Date()
            job.save(flush: true)
            
            ex.printStackTrace(System.err);
            throw ex;
        }
        
    }
    
}
