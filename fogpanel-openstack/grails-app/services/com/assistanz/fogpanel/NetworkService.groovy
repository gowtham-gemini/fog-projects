package com.assistanz.fogpanel

import grails.transaction.Transactional
import com.assistanz.openstack.OpenStackServer;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern
import java.util.regex.Matcher
import com.assistanz.cloud.config.ConfigLoader
import org.openstack4j.api.Builders;
import org.openstack4j.api.OSClient;
import org.openstack4j.model.network.IPVersionType;
import org.openstack4j.model.network.Network as OpenstackNetwork;
import org.openstack4j.model.network.NetworkType;
import org.openstack4j.model.network.Subnet;
import org.openstack4j.model.network.Pool;
import org.openstack4j.model.network.Port as OpenstackPort;
import org.openstack4j.model.network.IP;
import org.openstack4j.model.compute.ActionResponse;
import com.assistanz.fogpanel.PullNetworkJob
import grails.plugin.springsecurity.SpringSecurityService

@Transactional
class NetworkService {
    
    InvoiceService invoiceService
    def springSecurityService;
    OpenStackAuthService openStackAuthService
    def listNetwork(referenceId) {
        
        def user = springSecurityService.currentUser;  
        
        ArrayList<ArrayList<String>> networks = new ArrayList<ArrayList<String>>();
                  
        def networkList = Network.withCriteria { 
            eq('deleted', false)
            if(referenceId) {
                eq('referenceId', referenceId) 
            }
        }
        for (def network : networkList) {
            
            def isShared = network.isShared;
            def isExternal = network.isExternal;
            def account = user.account;
            def networkAccount = network.account;
            def action = true;
            
            if((isShared == true && isExternal == false) || (isShared == false && isExternal == false && account == networkAccount) ||
//                (isExternal == true && account == networkAccount) || 
                (isExternal == false && account == networkAccount)) {
                
                HashMap item = new HashMap();
                if(isShared == true) {
                    action = false;
                    item.put("action",action)
                } else {
                    item.put("action",action)
                }
                item.put("name", network.name);
                item.put("referenceId", network.referenceId);
                item.put("isShared", network.isShared);
                item.put("isExternal", network.isExternal);
                item.put("status", network.status);
                item.put("isAdminState", network.isAdminState);
                def subnet = NetworkSubnet.findByNetwork(network)
                item.put("subnetsAssociated", subnet != null ? subnet.name +": "+ subnet.cidr : "-");
                networks.add(item)
            }
                                              
        }
        
        return networks
    }
    
    def getExternalNetworkList() {     
        try{
            
            def user = springSecurityService.currentUser;  

            ArrayList<ArrayList<String>> networks = new ArrayList<ArrayList<String>>();
    
            def networkList = Network.withCriteria { 
                eq('deleted', false)
                eq('isExternal', true)
            }
            for (def network : networkList) {

                HashMap item = new HashMap();
                item.put("name", network.name);
                item.put("referenceId", network.referenceId);
                item.put("isShared", network.isShared);
                item.put("status", network.status);
                item.put("isAdminState", network.isAdminState);
                networks.add(item)             
            }

            return networks
            
            
        } catch (Exception ex) {
            throw ex;
        }
    }           
    
    def createNetwork(requestData) {        
        try { 
            def user = springSecurityService.currentUser; 
            
            def projectId = user.account.uuid;
            
            OSClient os = openStackAuthService.authenticate();       
            
            OpenstackNetwork openstackNetwork = os.networking().network()
            .create(Builders.network().tenantId(projectId).name(requestData.name).adminStateUp(requestData.isAdminState).isShared(false).build());
            
            Subnet subnet = os.networking().subnet().create(Builders.subnet()
                .name(requestData.subnetName)
                .network(openstackNetwork)
                .ipVersion(IPVersionType.valueOf(Integer.parseInt(requestData.ipVersion)))
                .cidr(requestData.networkAddress)
                .enableDHCP(true)
                .gateway(requestData.gatewayIp == "" ? null : requestData.gatewayIp)
                .build());
              
            
            List<? extends Pool> pools = subnet.getAllocationPools();
            def subnetPool;
            for(Pool pool : pools) {
                subnetPool = "start "+pool.getStart()+" - End "+pool.getEnd();
            }
            
            def network = new Network()  
            network.referenceId = openstackNetwork.getId()
            network.name = openstackNetwork.getName()
            network.status = openstackNetwork.getStatus()
            network.isShared = openstackNetwork.isShared()
            network.isAdminState = openstackNetwork.isAdminStateUp()
            network.createdAt = new Date()
            network.account = user.account
            network.user = user
            network.billingType = requestData.billingType
            
            network.save(flush: true)
            
            if (!network.save()) {
                println("network unsave")
                network.errors.allErrors.each { println it }
            }
            
            
            def networkSubnet = new NetworkSubnet()
            networkSubnet.referenceId = subnet.getId()
            networkSubnet.name = subnet.getName()
            networkSubnet.cidr = subnet.getCidr()
            networkSubnet.network = network
            networkSubnet.IPVersion = subnet.getIpVersion()
            networkSubnet.gatewayIP = subnet.getGateway()
            networkSubnet.DHCPEnable = subnet.isDHCPEnabled()
            networkSubnet.allocationPools = subnetPool
            networkSubnet.createdAt = new Date()            
            networkSubnet.save(flush: true)
            //            networkSubnet.allocationPools
            //            networkSubnet.DNSNameServers
            //            networkSubnet.hostRoutes
            
            
            def invoice = Invoice.findWhere(account: user.account, status: InvoiceStatus.values()[6])
            
            def price = ""
            def cost = MiscellaneousOffer.get(3)?.cost
            if(network.billingType == "monthly") {
                price = cost * 720
            } else {
                price = cost
            }
            def bandwidthReadPrice = MiscellaneousOffer.get(4)?.cost
            def bandwidthWritePrice = MiscellaneousOffer.get(5)?.cost
            
            if(!invoice) {
                invoice = invoiceService.createDraftInvoice(user.account) 
            } 
            invoiceService.addInvoiceItem(invoice, BillableItem.get(4), network.billingType, network.name, network.referenceId, null, price, null, null)            
             
            def read = InvoiceItem.findWhere(invoice:invoice, billableItem:BillableItem.get(11), referenceItemId:invoice.account.uuid) 
            def write = InvoiceItem.findWhere(invoice:invoice, billableItem:BillableItem.get(12), referenceItemId:invoice.account.uuid)

            if (!read) {
                // for bandwith read
                invoiceService.addInvoiceItem(invoice, BillableItem.get(11), "hourly", "Bandwith Read", invoice.account.uuid, null, bandwidthReadPrice, null, null)
            }

            if (!write) {
                // for bandwith write
                invoiceService.addInvoiceItem(invoice, BillableItem.get(12), "hourly", "Bandwith Write", invoice.account.uuid, null, bandwidthWritePrice, null, null)
            }                                    
            ArrayList<ArrayList<String>> createFlavourResponse = new ArrayList<ArrayList<String>>();        
            HashMap<String,String> item = new HashMap<String,String>(); 
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            createFlavourResponse.add(item)
            
            return createFlavourResponse
            
        } catch (Exception ex) {
            throw ex;
        }
    } 
    
    def createNetworkSubnet(requestData) {
        
         try { 
            
            def user = springSecurityService.currentUser; 
            
            def projectId = user.account.uuid;
            
            OSClient os = openStackAuthService.authenticate(); 
            
            println("requestData.networkId: "+requestData.networkId)
            
            Subnet subnet = os.networking().subnet().create(Builders.subnet()
                .name(requestData.subnetName)
                .network(os.networking().network().get(requestData.networkId))
                .ipVersion(IPVersionType.valueOf(Integer.parseInt(requestData.ipVersion)))
                .cidr(requestData.networkAddress)
                .enableDHCP(true)
                .gateway(requestData.gatewayIp == "" ? null : requestData.gatewayIp)
                .build());

            List<? extends Pool> pools = subnet.getAllocationPools();
            def subnetPool;
                for(Pool pool : pools) {
                    subnetPool = "start "+pool.getStart()+" - End "+pool.getEnd();
                }
            def subNetwork = Network.findByReferenceId(requestData.networkId);
            
            def networkSubnet = new NetworkSubnet()
            networkSubnet.referenceId = subnet.getId()
            networkSubnet.name = subnet.getName()
            networkSubnet.cidr = subnet.getCidr()
            networkSubnet.network = subNetwork
            networkSubnet.IPVersion = subnet.getIpVersion()
            networkSubnet.gatewayIP = subnet.getGateway()
            networkSubnet.DHCPEnable = subnet.isDHCPEnabled()
            networkSubnet.allocationPools = subnetPool
            networkSubnet.save(flush: true)
            
            if (!networkSubnet.save()) {
                networkSubnet.errors.allErrors.each { println it }
            }
            
            ArrayList<ArrayList<String>> createSubnetResponse = new ArrayList<ArrayList<String>>();        
            HashMap<String,String> item = new HashMap<String,String>(); 
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            createSubnetResponse.add(item)
            
            return createSubnetResponse
            
        } catch (Exception ex) {
            throw ex;
        }
        
    }
    
    def subnetListForNetwork (networkId, subnetId) {
        try {
            
            ArrayList<ArrayList<String>> subnets = new ArrayList<ArrayList<String>>();
            def network = Network.findByReferenceId(networkId);
                        
            def subnetList;
            
            if(subnetId) {
                subnetList = NetworkSubnet.withCriteria {
                   eq('referenceId', subnetId)      
                   eq('deleted', false)      
                }
                println("subnetList: "+subnetList)
            } else {
                subnetList = NetworkSubnet.withCriteria {
                   eq('network', network) 
                   eq('deleted', false)
                }
            }
            
            def action = true;
            
            for(def subnet : subnetList) {
                
                HashMap item = new HashMap(); 
                
               if(subnet.network.isShared == true) {
                    action = false;
                    item.put("action",action)
                } else {
                    item.put("action",action)
                } 
 
               item.put("referenceId", subnet.referenceId);
               item.put("name", subnet.name);
               item.put("cidr", subnet.cidr);
               item.put("IPVersion", subnet.IPVersion);
               item.put("network", subnet.network);
               item.put("gatewayIP", subnet.gatewayIP);
               item.put("DHCPEnable", subnet.DHCPEnable);
               item.put("allocationPools", subnet.allocationPools);
               item.put("hostRoutes", subnet.hostRoutes);
               item.put("DNSNameServers", subnet.DNSNameServers);
               subnets.add(item);
            }
            
            return subnets;
            
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    def detailedSubnetList() {
        try {
            ArrayList<ArrayList<String>> subnets = new ArrayList<ArrayList<String>>();
            def user = springSecurityService.currentUser; 
            
            def subnetList = NetworkSubnet.withCriteria {     
                eq('deleted', false)      
            }
            
            for(def subnet : subnetList) {
                
                if(subnet.network.user == user) {
                    
                    HashMap item = new HashMap();
                    def subnetDetail = subnet.network.name + ": " + subnet.cidr + " (" + subnet.name + ")"
                    item.put("referenceId", subnet.referenceId);
                    item.put("networkId", subnet.network.referenceId);
                    item.put("name", subnetDetail);
                    subnets.add(item);
                }
            }
            
            return subnets;
        } catch (Exception ex) {   
            throw ex;
        }
    }
    def pullPublicNetworkFromOpenstack() {
        
        def user = springSecurityService.currentUser;  
        println("user from resource cal"+user)

        PullNetworkJob.triggerNow([id:"network"])        
        return "OK"
    }
    
    def pullNetworkFromOpenstack(jobid) {        
        
        def job = AsynchronousJobs.get(jobid)
        println("job id desc" + jobid)
        println("job  desc" + job)                   

        try {   
            job.status = JobStatus.valueOf("RUNNING")
            job.startedAt = new Date()
            job.save(flush: true)  

                ConfigLoader configLoader = ConfigLoader.getInstance();
     
                Properties props = configLoader.getProperties();
   
                OSClient os = openStackAuthService.authenticate();
                   
                HashMap<String,String> openStackNetworkList = new HashMap<String,String>();
                HashMap<String,String> openStackSubnetList = new HashMap<String,String>();
                HashMap<String,String> openStackPortList = new HashMap<String,String>();
                
                List<? extends OpenstackNetwork> networks = os.networking().network().list();     
               
                
                for (OpenstackNetwork openstackNetwork : networks) {     
                    
                    
                    if(openstackNetwork.isRouterExternal() == true) {
                        
                        openStackNetworkList.put(openstackNetwork.getId(),"referenceId");
                        def network = com.assistanz.fogpanel.Network.findByReferenceId(openstackNetwork.getId())
                               
                        if(!network) {
                            
                            network = new com.assistanz.fogpanel.Network()
                            network.referenceId = openstackNetwork.getId()
                            network.name = openstackNetwork.getName()
                            network.status = openstackNetwork.getStatus()                        
                            network.deleted = false        
                            network.isShared = openstackNetwork.isShared()                        
                            network.isAdminState = openstackNetwork.isAdminStateUp()                                                
                            network.isExternal = openstackNetwork.isRouterExternal()                       
                            network.createdAt = new Date()
//                            network.account = user.account   
//                            network.user = user
                            network.billingType = "hourly"                                                
                            network.save(flush: true)
                                                                                   
                            
                            if (!network.save()) {
                                network.errors.allErrors.each { println it }
                            }                                                                                                                
                            List<? extends String> subnetsList =  openstackNetwork.getSubnets(); 
                            
                            if(subnetsList) {                                
                                for(String subnet: subnetsList) {                                                                        
                                    Subnet subnetItem = os.networking().subnet().get(subnet);
                                    openStackSubnetList.put(subnetItem.getId(),"referenceId");
                                    if(subnetItem) {  
                                        
                                        List<? extends Pool> pools = subnetItem.getAllocationPools();
                                        def subnetPool;
                                        for(Pool pool : pools) {
                                            subnetPool = "start "+pool.getStart()+" - End "+pool.getEnd();
                                        }
                                        
                                        def subNetwork = Network.findByReferenceId(openstackNetwork.getId());
                                        def exisSubnet = NetworkSubnet.findByReferenceId(subnetItem.getId());
                                        
                                        if(!exisSubnet) {
                                            def networkSubnet = new NetworkSubnet()
                                            networkSubnet.referenceId = subnetItem.getId()
                                            networkSubnet.deleted = false                                        
                                            networkSubnet.name = subnetItem.getName()
                                            networkSubnet.cidr = subnetItem.getCidr()
                                            networkSubnet.network = subNetwork
                                            networkSubnet.IPVersion = subnetItem.getIpVersion()
                                            networkSubnet.gatewayIP = subnetItem.getGateway()
                                            networkSubnet.DHCPEnable = subnetItem.isDHCPEnabled()
                                            networkSubnet.allocationPools = subnetPool
                                            networkSubnet.createdAt = new Date();

                                            networkSubnet.save(flush: true)

                                            if (!networkSubnet.save()) {
                                                networkSubnet.errors.allErrors.each { println it }
                                            }
                                        }                                                                                                                        
                                    }
                                }
                            }
                            
                       //port list values persisist to db - temporarly commented
                            
//                            List<? extends OpenstackPort> ports = os.networking().port().list();
                           
//                            for(OpenstackPort port : ports) {                                                                                                
//                                println("****port name*****" +  port.getNetworkId()) 
//                                def ips = "";
//                                def i=1;
//                                def subnets = "";
//                                def action = true; 
//                                def openStackNetworkId = port.getNetworkId();                                                                
//                                
//                                if((openStackNetworkId.equals(openstackNetwork.getId())) || (openStackNetworkId == openstackNetwork.getId()) ) {
//                                    
//                                    openStackPortList.put(port.getId(),"referenceId");
//                                    
//                                    def existingPort = Port.findByReferenceId(port.getId());
//                                    
//                                    if(openStackNetworkId != null && !openStackNetworkId.isEmpty() && openStackNetworkId != "null" && openStackNetworkId.equals(openstackNetwork.getId())) {
//                                        
//                                        for(IP ip : port.getFixedIps()) {
//                                            
//                                            def fixedIps = port.getFixedIps().size();
//                                            ips = ips + ip.getIpAddress();
//                                            
//                                            if(fixedIps != i++) {
//                                                ips+= ",";
//                                                subnets+= ",";
//                                            }
//                                        }                                        
//                                    }
//                                    if(!existingPort) {
//
//                                        def newPort = new Port();
//                                        newPort.deleted = false  
//                                        newPort.referenceId = port.getId();
//                                        newPort.name = port.getName();
//                                        newPort.status = port.getState();
//                                        newPort.fixedIps = ips;
//                                        newPort.macAddress = port.getMacAddress();
//                                        newPort.user = user;
//                                        newPort.account = user.account;
//                                        newPort.isAdminState = port.isAdminStateUp();
//                                        newPort.deviceId = port.getDeviceId();
//                                        newPort.deviceOwner = port.getDeviceOwner();
//                                        newPort.createdAt = new Date();
//                                        newPort.save(flush: true);
//                                        
//                                        if (!newPort.save()) {
//                                            newPort.errors.allErrors.each { println it }
//                                        }                                         
//                                    } 
//                                }                                                                                                                                                                                                                                                                                                                                                                    
//                            }                                                                                                                                                                                                                                
                        }
//                        println("****finish****") 
                    }                            
                }
                
                def oldNetworkList = Network.findAllWhere(deleted: false); 
                def oldSubnetList = NetworkSubnet.findAllWhere(deleted: false); 
//                def oldPortList = Port.findAllWhere(deleted: false);                 
                
                for(def network :oldNetworkList ) { 
                    
                    boolean blnExists = openStackNetworkList.containsKey(network.referenceId);

                    println("blnExists net"+blnExists)
                    
                    if(blnExists.toString() == "false" || blnExists == false) {
                        network.deleted = true; 
                        network.deletedAt = new Date(); 
                        network.save(flush: true); 
                    }
                }   
                  
                for (def subnet : oldSubnetList) {     
                    
                    boolean blnExists = openStackSubnetList.containsKey(subnet.referenceId);                    
                    println("sub blnExists"+blnExists)
                    if(blnExists.toString() == "false" || blnExists == false) {
                        subnet.deleted = true; 
                        subnet.deletedAt = new Date(); 
                        subnet.save(flush: true); 
                    }
                }

//                for (def port : oldPortList) {     
//                    
//                    boolean blnExists = openStackPortList.containsKey(port.referenceId);                    
//                    println("port blnExists"+blnExists)
//                    if(blnExists.toString() == "false" || blnExists == false) {
//                        port.deleted = true; 
//                        port.deletedAt = new Date(); 
//                        port.save(flush: true); 
//                    }
//                } 
            job.status = JobStatus.valueOf("COMPLETED")
            job.completedAt = new Date()
            job.save(flush: true)
            println("***Completed***")
        } catch (Exception ex) {            
            
            job.status = JobStatus.valueOf("ERROR")
            job.completedAt = new Date()
            job.save(flush: true)
            
            ex.printStackTrace(System.err);
            throw ex;
        }
     
        return "OK"
    }
    
    def updateNetwork(requestData) {
        try{
            def user = springSecurityService.currentUser;  
            
            ConfigLoader configLoader = ConfigLoader.getInstance();
            Properties props = configLoader.getProperties();

            OSClient os = openStackAuthService.authenticate();
            
            def isShared = false;
            def isExternal = false;
            //for user network update isShared and isExternal check
            if(requestData.isShared) {
                isShared = requestData.isShared;
            } 
            if(requestData.isExternal) {
                isExternal = requestData.isExternal;
            }
            
            OpenstackNetwork openstackNetwork = os.networking().network().update(requestData.id, Builders.networkUpdate().name(requestData.name)
                                                               .adminStateUp(requestData.isAdminState)
                                                               .routerExternal(isExternal)
                                                               .shared(isShared)
                                                               .build());
             
            def network = Network.findByReferenceId(requestData.id);
            
            network.name = requestData.name;
            network.isAdminState = requestData.isAdminState;
            network.isShared = isShared;
            network.isExternal = isExternal;
            network.createdAt = new Date();
            network.save(flush: true);
            
            ArrayList<ArrayList<String>> updateResponse = new ArrayList<ArrayList<String>>();        
            HashMap<String,String> item = new HashMap<String,String>(); 
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            updateResponse.add(item)
            
            return updateResponse
            
       } catch (Exception exception) {
            throw exception;
        }
        
        
    }
    
    def deleteNetwork(referenceId) {
       try{
            def user = springSecurityService.currentUser;  

            OSClient os = openStackAuthService.authenticate(); 
            
            ActionResponse actionResponse = os.networking().network().delete(referenceId);
            
            if(actionResponse.isSuccess() == true) {
                                           
                def network = Network.findByReferenceId(referenceId);
                
                def subnets = NetworkSubnet.withCriteria {
                    eq('network', network)
                }

                for(def subnet : subnets) {
                    subnet.deleted = true;
                    subnet.deletedAt = new Date();
                    subnet.save(flush: true);
                }

                network.deleted = true;
                network.deletedAt = new Date();
                network.save(flush: true);
            } else {
                
                
            }
            return actionResponse;
            
       } catch (Exception exception) {
            throw exception;
        }
    }
    
    def updateSubnetNetwork(requestData) {
        try{
            
            def user = springSecurityService.currentUser; 
                       
            OSClient os = openStackAuthService.authenticate(); 
            
            Subnet subnet = os.networking().subnet().get(requestData.id);
            os.networking().subnet().update(requestData.id, Builders.subnet()
                .name(requestData.name)
//                .enableDHCP(requestData.enableDHCP)
                .gateway(requestData.gateway)
                .build());
           
            def networkSubnet = NetworkSubnet.findByReferenceId(requestData.id);
            networkSubnet.name = requestData.name
            networkSubnet.gatewayIP = requestData.gateway
//            networkSubnet.DHCPEnable = requestData.enableDHCP
            networkSubnet.save(flush: true)
            
            ArrayList<ArrayList<String>> updateSubnetResponse = new ArrayList<ArrayList<String>>();
            HashMap<String,String> item = new HashMap<String,String>(); 
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            updateSubnetResponse.add(item)
            
            return updateSubnetResponse;
                
            
        } catch (Exception exception) {
            throw exception;
        }
    }
    
    def deleteSubnetNetwork(referenceId) {
        try{
            def user = springSecurityService.currentUser;  
          
            OSClient os = openStackAuthService.authenticate(); 
           
            ActionResponse actionResponse = os.networking().subnet().delete(referenceId);
            
            if(actionResponse.isSuccess() == true) {
           
                def subnet = NetworkSubnet.findByReferenceId(referenceId);
                subnet.deleted = true;
                subnet.deletedAt = new Date();
                subnet.save(flush: true);

            } else {

            }
            return actionResponse;
                        
       } catch (Exception exception) {
            throw exception;
        }
        
    }
    
    def networkPortList(networkId) {
        
        try {
            
            ArrayList<ArrayList<String>> portList = new ArrayList<ArrayList<String>>();
            
            def user = springSecurityService.currentUser;
             
//            ConfigLoader configLoader = ConfigLoader.getInstance();
//            Properties props = configLoader.getProperties();
//            OpenStackServer server = new OpenStackServer(props.get(Config.OPENSTACK_ENDPOINT_URL), props.get(Config.OPENSTACK_ADMIN_UUID), props.get(Config.OPENSTACK_ADMIN_PASSWORD), null);
//            OSClient os = server.authenticate(); 

            OSClient os = openStackAuthService.authenticate(); 
            
            List<? extends OpenstackPort> ports = os.networking().port().list();
                        
            for(OpenstackPort port : ports) {
                
                HashMap item = new HashMap();
                
                def openStackNetworkId = port.getNetworkId();
                def network = Network.findByReferenceId(openStackNetworkId);
                def ips = "";
                def i=1;
                def subnets = "";
                def action = true;
                
                if(openStackNetworkId != null && !openStackNetworkId.isEmpty() && openStackNetworkId != "null" && openStackNetworkId.equals(networkId)) {
                
                    for(IP ip : port.getFixedIps()) {
                        
                        def fixedIps = port.getFixedIps().size();
                    
                        ips = ips + ip.getIpAddress();
                        subnets = subnets + ip.getSubnetId();
                        
                        if(fixedIps != i++) {
                            ips+= ",";
                            subnets+= ",";
                        }
                        
                    }
                    //Port table entry disbled
//                    def existingPort = Port.findByReferenceId(port.getId());
//   
//                    if(!existingPort) {
//                    
//                        def newPort = new Port();
//                        newPort.referenceId = port.getId();
//                        newPort.name = port.getName();
//                        newPort.status = port.getState();
//                        newPort.fixedIps = ips;
//                        newPort.macAddress = port.getMacAddress();
//                        newPort.user = user;
//                        newPort.account = user.account;
//                        newPort.isAdminState = port.isAdminStateUp();
//                        newPort.deviceId = port.getDeviceId();
//                        newPort.deviceOwner = port.getDeviceOwner();
//                        newPort.createdAt = new Date();
//                        newPort.save(flush: true);
//
//
//                    } else {
//                        
//                        def updatedfixedIps = Port.findWhere(referenceId: port.getId(), fixedIps: ips);
//                        if(!updatedfixedIps) {
//                            updatedfixedIps.fixedIps = ips;
//                            updatedfixedIps.save(flush: true);
//                        }
//                    }
                    
                    //set list action
                    if(network.isShared == true) {
                        action = false;
                        item.put("action",action)
                    } else {
                        item.put("action",action)
                    }
                    
                    item.put("ipAddress",ips);
                    item.put("subnetId",subnets);
                    item.put("referenceId",port.getId());
                    item.put("name",port.getName());
                    item.put("networkId",port.getNetworkId());
                    item.put("projectId",port.getTenantId());
                    item.put("macAddress",port.getMacAddress());
                    item.put("status",port.getState());
                    item.put("adminState",port.isAdminStateUp());
                    item.put("deviceId",port.getDeviceId());
                    item.put("deviceOwner",port.getDeviceOwner());
                    portList.add(item);
                }
            }
            return portList;
            
            
        } catch (Exception ex) {          
            throw ex;
        } 
        
    }
       
    def networkPortView(portId) {
        try {
            
            ArrayList<ArrayList<String>> portList = new ArrayList<ArrayList<String>>();
            
            def user = springSecurityService.currentUser;
            
            ConfigLoader configLoader = ConfigLoader.getInstance();
            Properties props = configLoader.getProperties();
            OpenStackServer server = new OpenStackServer(props.get(Config.OPENSTACK_ENDPOINT_URL), props.get(Config.OPENSTACK_ADMIN_UUID), props.get(Config.OPENSTACK_ADMIN_PASSWORD), null);
            OSClient os = server.authenticate();
            
            OpenstackPort port = os.networking().port().get(portId);            
                
            HashMap item = new HashMap();

            for(IP ip : port.getFixedIps()) {
                item.put("ipAddress",ip.getIpAddress());
                item.put("subnetId",ip.getSubnetId());
            }
            def network = Network.findByReferenceId(port.getNetworkId());

            item.put("referenceId",port.getId());
            item.put("name",port.getName());
            item.put("network",network);
            item.put("projectId",port.getTenantId());
            item.put("macAddress",port.getMacAddress());
            item.put("status",port.getState());
            item.put("adminState",port.isAdminStateUp());
            item.put("deviceId",port.getDeviceId());
            item.put("deviceOwner",port.getDeviceOwner());
            portList.add(item);
            
            return portList;
            
            
        } catch (Exception ex) {          
            throw ex;
        } 
    }
    
    def networkPortUpdate(requestData) {
        try{
            
            ArrayList<ArrayList<String>> updatePortResponse = new ArrayList<ArrayList<String>>();
            
            def user = springSecurityService.currentUser;
            
            OSClient os = openStackAuthService.authenticate(); 
            
            OpenstackPort port = os.networking().port().get(requestData.id);
            
            os.networking().port().update(port.toBuilder().name(requestData.name).build());
            
            HashMap<String,String> item = new HashMap<String,String>(); 
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            updatePortResponse.add(item)
            
            return updatePortResponse;
            
        } catch (Exception ex) {          
            throw ex;
        } 
        
        
    }
    
    def adminNetworkList (referenceId) {
        
        try {
            
           def user = springSecurityService.currentUser;  
        
            ArrayList<ArrayList<String>> networks = new ArrayList<ArrayList<String>>();

            def networkList = Network.withCriteria { 
                eq('deleted', false)
                if(referenceId) {
                    eq('referenceId', referenceId) 
                }
            }
            for (def network : networkList) {

                HashMap item = new HashMap();
                item.put("name", network.name);
                item.put("projectName", network.account != null ? network.account.accountIdentifier : user.account.accountIdentifier);
                item.put("referenceId", network.referenceId);
                item.put("isShared", network.isShared);
                item.put("status", network.status);
                item.put("isAdminState", network.isAdminState);
                item.put("isExternal", network.isExternal);
                def subnet = NetworkSubnet.findWhere(network: network, deleted: false)
                if(subnet) {
                    item.put("subnetsAssociated", subnet.name +":"+ subnet.cidr);
                } else {
                    item.put("subnetsAssociated", "-");
                }
                networks.add(item)             
            }

            return networks
            
            
        }  catch (Exception ex) {          
            throw ex;
        } 
    }
    
    def deleteNetworkByAdmin(referenceId) {
       try{
            def user = springSecurityService.currentUser;  

            OSClient os = openStackAuthService.authenticate(); 
           
            ActionResponse actionResponse = os.networking().network().delete(referenceId);
            
            if(actionResponse.isSuccess() == true) {
           
                def network = Network.findByReferenceId(referenceId);

                def subnets = NetworkSubnet.withCriteria {
                    eq('network', network)
                }

                for(def subnet : subnets) {
                    subnet.deleted = true;
                    subnet.deletedAt = new Date();
                    subnet.save(flush: true);
                }

                network.deleted = true;
                network.deletedAt = new Date();
                network.save(flush: true);
            } else {
                
            }
            return actionResponse;
            
       } catch (Exception exception) {
            throw exception;
        }
    }
    
   def createNetworkByAdmin(requestData) {
        
        try { 
            def user = springSecurityService.currentUser; 
            
            ConfigLoader configLoader = ConfigLoader.getInstance();
            Properties props = configLoader.getProperties();
            
            OpenStackServer server = new OpenStackServer(props.get(Config.OPENSTACK_ENDPOINT_URL), props.get(Config.OPENSTACK_ADMIN_UUID), props.get(Config.OPENSTACK_ADMIN_PASSWORD), null);
            OSClient os = server.authenticate();       
                    
            OpenstackNetwork openstackNetwork = os.networking().network()
            .create(Builders.network().tenantId(requestData.projectId).name(requestData.name).adminStateUp(requestData.isAdminState)
            .isRouterExternal(requestData.isRouterExternal)    
            .isShared(requestData.isShared).build());
            
            def account = Account.findByUuid(requestData.projectId);
            
            def network = new Network()  
            network.referenceId = openstackNetwork.getId()
            network.name = openstackNetwork.getName()
            network.status = openstackNetwork.getStatus()
            network.isShared = openstackNetwork.isShared()
            network.isExternal = openstackNetwork.isRouterExternal()
            network.isAdminState = openstackNetwork.isAdminStateUp()
            network.createdAt = new Date()
            network.account = account
            network.user = user
            network.save(flush: true)
            
            if (!network.save()) {
                println("network unsave")
                network.errors.allErrors.each { println it }
            }            
            
            ArrayList<ArrayList<String>> createNetworkResponse = new ArrayList<ArrayList<String>>();        
            HashMap<String,String> item = new HashMap<String,String>(); 
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            createNetworkResponse.add(item)
            
            return createNetworkResponse
            
        } catch (Exception ex) {
            throw ex;
        }
    } 
    
    def createPortByAdmin(requestData) {
        
        try{
            
            ArrayList<ArrayList<String>> createPortResponse = new ArrayList<ArrayList<String>>();
            
            ConfigLoader configLoader = ConfigLoader.getInstance();
            Properties props = configLoader.getProperties();
            
            OpenStackServer server = new OpenStackServer(props.get(Config.OPENSTACK_ENDPOINT_URL), props.get(Config.OPENSTACK_ADMIN_UUID), props.get(Config.OPENSTACK_ADMIN_PASSWORD), null);
            OSClient os = server.authenticate(); 
            
            OpenstackPort port = os.networking().port().create(Builders.port().name(requestData.name).networkId(requestData.networkId)
                    .deviceId(requestData.deviceId)
                    .deviceOwner(requestData.deviceOwner)
                    .adminState(requestData.isAdminState)
                    .build());
                
            HashMap<String,String> item = new HashMap<String,String>(); 
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            createPortResponse.add(item)
            
            return createPortResponse;
            
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    def deletePort(referenceId) {
        try{
            
            ConfigLoader configLoader = ConfigLoader.getInstance();
            Properties props = configLoader.getProperties();
            
            OpenStackServer server = new OpenStackServer(props.get(Config.OPENSTACK_ENDPOINT_URL), props.get(Config.OPENSTACK_ADMIN_UUID), props.get(Config.OPENSTACK_ADMIN_PASSWORD), null);
            OSClient os = server.authenticate();
            
            ActionResponse actionResponse = os.networking().port().delete(referenceId);
            
            if(actionResponse.isSuccess() == true) {
                
            } else {

            } 
            return actionResponse;
            
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    
}
