package com.assistanz.fogpanel

import com.assistanz.cloud.cloudstack.CloudStackServer
import com.assistanz.cloud.cloudstack.CloudStackServer.CloudStackServerException
import com.assistanz.cloud.cloudstack.ServiceResponse
import com.assistanz.cloud.cloudstack.address.CSAddressService
import com.assistanz.cloud.cloudstack.address.IpAddressResponse
import com.assistanz.cloud.cloudstack.vpc.CSVPCService
import com.assistanz.cloud.cloudstack.vpc.VpcOfferingResponse
import com.assistanz.cloud.cloudstack.vpc.VpcResponse
import com.assistanz.cloud.cloudstack.vpc.VpnConnectionResponse
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

@Transactional
class VpnCustomerGatewayService {

    def springSecurityService;
    
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
    
    def getVPNStat(zoneReferenceId , vpcId) {
        try {
            def user = springSecurityService.currentUser
            def role = springSecurityService.getPrincipal().getAuthorities()  
            def vpnConnectionCount = 0;
            def vpnCustomerGatewayLits = S2SCustomerGateway.findAllWhere(account:user.account, deleted:false).size()        
            def vpnConnectionLits = VPNConnection.withCriteria {
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
            vpnConnectionCount = vpnConnectionLits.size();
            ArrayList<ArrayList<String>> gatewayStatArray = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();
            item.put("vpnCustomerGateway", vpnCustomerGatewayLits);
            item.put("vpnConnection", vpnConnectionCount);                 

            gatewayStatArray.add(item) 

            VPCConnectionBillingCalc()

            return gatewayStatArray
        } catch (CloudStackServerException ex) {
            
            ArrayList<ArrayList<String>> vpnResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_FAILURE);
            item.put("message", ex.localizedMessage.substring(4));
            vpnResponse.add(item)

            return vpnResponse    
            
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
        
    }
        
    def list() {
        
        def user = springSecurityService.currentUser
        def role = springSecurityService.getPrincipal().getAuthorities()  
                
        def vpnCustomerGatewayLits = S2SCustomerGateway.findAllWhere(account:user.account, deleted:false)
        ArrayList<ArrayList<String>> gatewayArray = new ArrayList<ArrayList<String>>();
        for(def gateway: vpnCustomerGatewayLits) {
            HashMap<String,String> item = new HashMap<String,String>();
            item.put("name", gateway.name);
            item.put("referenceId", gateway.referenceId);
            item.put("cidr", gateway.cidr);
            item.put("gateway", gateway.gateway);
            item.put("ipsecPresharedKey", gateway.ipsecPresharedKey);
            item.put("ikeEncryption", gateway.ikeEncryption);
            item.put("ikeHash", gateway.ikeHash);
            item.put("ikeDH", gateway.ikeDH);
            item.put("espEncryption", gateway.espEncryption);
            item.put("espHash", gateway.espHash);
            item.put("perfectForwardSecrecy", gateway.perfectForwardSecrecy);
            item.put("ikeLifetime", gateway.ikeLifetime);
            item.put("espLifetime", gateway.espLifetime);
            item.put("deadPeerDetection", gateway.deadPeerDetection);
            item.put("isUsed", gateway.isUsed);
            
            gatewayArray.add(item)
        }
        return gatewayArray
    }
    
    def removeVPNCustomerGateway(requestData) {
        
        try {   
                                
            def response = vpnServer().deleteVpnCustomerGateway(requestData.vpnGatewayId);     

            ArrayList<ArrayList<String>> vpnResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            item.put("jobId", response.jobId);
            vpnResponse.add(item)
            
            S2SCustomerGateway vpnCustomer = S2SCustomerGateway.findByReferenceId(requestData.vpnGatewayId)
            vpnCustomer.jobId = response.jobId;
            vpnCustomer.save(flush:true)
            return vpnResponse;
            
        } catch (CloudStackServerException ex) {
            
            ArrayList<ArrayList<String>> vpnResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_FAILURE);
            item.put("message", ex.localizedMessage.substring(4));
            vpnResponse.add(item)

            return vpnResponse    
            
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
    }
    
    def getRemoveVPNCustomerGatewayJob(jobId) {
        
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
                
                S2SCustomerGateway vpnCustomer = S2SCustomerGateway.findByJobId(jobId)
                vpnCustomer.deleted = true;
                vpnCustomer.removedDate = new Date();
                vpnCustomer.save(flush: true)
                return jobResult
                
            } else if(jobResponse.asychronousJobStatus == "2") {
                               
                
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
    
     def editVPNCustomerGatewayJob(jobId) {
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
                item.put("message", jobResponse.errorText);
                jobResult.add(item);
                                
                return jobResult  
            }
                
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
     }
    
    def createVPNCustomerGatewayJob(jobId) {
        
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
                S2SCustomerGateway vpnCustomer = S2SCustomerGateway.findByJobId(jobId)
                vpnCustomer.referenceId = jobResponse.id
                vpnCustomer.save(flush: true);
               
                return jobResult
                
            } else if(jobResponse.asychronousJobStatus == "2") {
                               
                
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.RESULT_FAILURE);
                item.put("message", jobResponse.errorText);
                jobResult.add(item);
                S2SCustomerGateway vpnCustomer = S2SCustomerGateway.findByJobId(jobId)
                vpnCustomer.delete(flush: true);
                
                return jobResult  
            }
                
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
    
    def createVPNCustomerGateway(requestData) {
     
        try {
            def user = springSecurityService.currentUser
            def role = springSecurityService.getPrincipal().getAuthorities()   

            HashMap<String,String> optional = new HashMap<String,String>();
            optional.put("account", new String(user.account.userName));
            optional.put("domainid", new String(user.account.domain.referenceId));
            optional.put("esplifetime", requestData.espLifetime);
            optional.put("ikeLifetime", requestData.ikeLifetime);
            optional.put("name", requestData.vpncgName);
            optional.put("dpd", Boolean.toString(requestData.deadPeerDetection));
                        
            String esppolicy = requestData.espEncryption+"-"+requestData.espHash
            esppolicy += requestData.ikeDH !="" ? ";"+requestData.ikeDH : ""
            String ikepolicy = requestData.ikeEncryption+"-"+requestData.ikeHash
            ikepolicy += requestData.perfectForwardSecrecy !="" ? ";"+requestData.perfectForwardSecrecy : ""
           
            
            def response = vpnServer().createVpnCustomerGateway(requestData.vpncgCidr, esppolicy,
            requestData.vpncgGateway, ikepolicy, requestData.ipsecPresharedKey, optional) 
        
            def jobResponse = asyncJobServer().queryAsyncJobResult(response.jobId)
            
            
            S2SCustomerGateway s2sCustomerGateway = new S2SCustomerGateway()
            
            s2sCustomerGateway.referenceId = response.jobId
            s2sCustomerGateway.name = requestData.vpncgName
            s2sCustomerGateway.gateway = requestData.vpncgGateway
            s2sCustomerGateway.cidr = requestData.vpncgCidr
            s2sCustomerGateway.ipsecPresharedKey = requestData.ipsecPresharedKey
            s2sCustomerGateway.ikeEncryption = requestData.ikeEncryption
            s2sCustomerGateway.ikeHash = requestData.ikeHash
            s2sCustomerGateway.ikeDH = requestData.ikeDH
            s2sCustomerGateway.espEncryption = requestData.espEncryption
            s2sCustomerGateway.espHash = requestData.espHash
            s2sCustomerGateway.perfectForwardSecrecy = requestData.perfectForwardSecrecy
            s2sCustomerGateway.ikeLifetime = requestData.ikeLifetime
            s2sCustomerGateway.espLifetime = requestData.espLifetime
            s2sCustomerGateway.deadPeerDetection =requestData.deadPeerDetection
            s2sCustomerGateway.account = user.account 
            s2sCustomerGateway.createdDate = new Date()
            s2sCustomerGateway.jobId = response.jobId
            s2sCustomerGateway.save(flush:true)
            if (!s2sCustomerGateway.save()) {
                s2sCustomerGateway.errors.allErrors.each { println it }
            }
            ArrayList<ArrayList<String>> networkResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            item.put("jobId", response.jobId);
            networkResponse.add(item)

            

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
    
    def updateVPNCustomerGateway(requestData) {
     
        try {
            def user = springSecurityService.currentUser
            def role = springSecurityService.getPrincipal().getAuthorities()   

            HashMap<String,String> optional = new HashMap<String,String>();
            optional.put("account", new String(user.account.userName));
            optional.put("domainid", new String(user.account.domain.referenceId));
            optional.put("esplifetime", requestData.espLifetime);
            optional.put("ikeLifetime", requestData.ikeLifetime);
            optional.put("name", requestData.vpncgName);
            optional.put("dpd", Boolean.toString(requestData.deadPeerDetection));
                        
            String esppolicy = requestData.espEncryption+"-"+requestData.espHash
            esppolicy += requestData.ikeDH !="" ? ";"+requestData.ikeDH : ""
            String ikepolicy = requestData.ikeEncryption+"-"+requestData.ikeHash
            ikepolicy += requestData.perfectForwardSecrecy !="" ? ";"+requestData.perfectForwardSecrecy : ""
           
            
            def response = vpnServer().updateVpnCustomerGateway(requestData.id, requestData.vpncgCidr, esppolicy,
            requestData.vpncgGateway, ikepolicy, requestData.ipsecPresharedKey, optional) 
                      
            S2SCustomerGateway s2sCustomerGateway = S2SCustomerGateway.findByReferenceId(requestData.id)
                        
            s2sCustomerGateway.name = requestData.vpncgName
            s2sCustomerGateway.gateway = requestData.vpncgGateway
            s2sCustomerGateway.cidr = requestData.vpncgCidr
            s2sCustomerGateway.ipsecPresharedKey = requestData.ipsecPresharedKey
            s2sCustomerGateway.ikeEncryption = requestData.ikeEncryption
            s2sCustomerGateway.ikeHash = requestData.ikeHash
            s2sCustomerGateway.ikeDH = requestData.ikeDH
            s2sCustomerGateway.espEncryption = requestData.espEncryption
            s2sCustomerGateway.espHash = requestData.espHash
            s2sCustomerGateway.perfectForwardSecrecy = requestData.perfectForwardSecrecy
            s2sCustomerGateway.ikeLifetime = requestData.ikeLifetime
            s2sCustomerGateway.espLifetime = requestData.espLifetime
            s2sCustomerGateway.deadPeerDetection =requestData.deadPeerDetection
                      
            s2sCustomerGateway.jobId = response.jobId
            s2sCustomerGateway.save(flush:true)
           
            ArrayList<ArrayList<String>> networkResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            item.put("jobId", response.jobId);
            networkResponse.add(item)
            
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
    
    def listVPNConnection(vpcId, zoneReferenceId) { 
                         
//        def vpc = VPC.findByReferenceId(vpcId)
        def user = springSecurityService.currentUser
        def role = springSecurityService.getPrincipal().getAuthorities()
        HashMap<String,String> optional = new HashMap<String,String>();
        
        if(role.iterator().next() == "ROLE_ADMIN" ) {     
            optional.put("listAll", "true");
            if(vpcId) {
                optional.put("vpcid", vpcId);                    
            }                        
        } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {
            optional.put("listAll", "true");
            if(vpcId) {
                optional.put("vpcid", vpcId);                    
            }                        
            optional.put("account", new String(user.account.userName));
            optional.put("domainid", new String(user.account.domain.referenceId));
        }        
        
        def response = vpnServer().listVpnConnections(optional);       
                        
        for(Iterator <VpnConnectionResponse> iter = response.vpnConnections.iterator(); iter.hasNext();) {
            def data = iter.next();
            VPNConnection currentVpnConnection = VPNConnection.findByReferenceId(data.vpnGatewayId)
                   
                def vpnCustomerGateway = S2SCustomerGateway.findByReferenceId(data.s2sCustomerGatewayId)
                def vpnGateway = S2SVPNGateways.findByReferenceId(data.s2sVpnGatewayId)
            
            if(!currentVpnConnection) {
                
                currentVpnConnection = new VPNConnection()
                currentVpnConnection.referenceId = data.vpnGatewayId
                currentVpnConnection.vpc = vpnGateway.vpc
                currentVpnConnection.vpnGateway = vpnGateway
                currentVpnConnection.vpnCustomerGateway = vpnCustomerGateway
                currentVpnConnection.account = vpnGateway.vpc.account 
                currentVpnConnection.zone = vpnGateway.vpc.zone 
                currentVpnConnection.created = new Date()
                currentVpnConnection.billingUpdatedDate = new Date()
                currentVpnConnection.jobId = data.vpnGatewayId
                currentVpnConnection.state = data.state;
                currentVpnConnection.save(flush:true)
                
                vpnCustomerGateway?.isUsed = true;
                vpnCustomerGateway?.save(flush:true)
                   
                def invoice = Invoice.findWhere(account: vpnGateway.vpc.account, status: InvoiceStatus.values()[6])
                   
                if(invoice) {

                    def vpnOffer = MiscellaneousOffer.get(7);
                    def vpnBillableItem = BillableItem.get(20)
                    if(vpnBillableItem.enabled == true || vpnBillableItem.enabled == "true") {
                       if(currentVpnConnection.state =="Connected") {
                            def newInvoiceItem = new InvoiceItem()
                            newInvoiceItem.billableItem = vpnBillableItem
                            newInvoiceItem.invoice = invoice
                            newInvoiceItem.taxPercent = vpnBillableItem.tax.percentage
                            newInvoiceItem.zone = vpnGateway.vpc.zone
                            def offerCost = MiscellaneousOfferZoneCost.findWhere(miscellaneousOffer :vpnOffer, zone : vpnGateway.vpc.zone)
                            newInvoiceItem.usageUnitPrice = offerCost ? offerCost.cost : 0.0;
                            newInvoiceItem.usageUnits = 1.0;
                            double usageAmount = newInvoiceItem.usageUnits                
                            newInvoiceItem.usageAmount = Math.ceil(usageAmount * 100d) / 100d;   
                            double taxAmount = (newInvoiceItem.usageAmount/100)* newInvoiceItem.taxPercent
                            newInvoiceItem.taxAmount = Math.ceil(taxAmount * 100d) / 100d;     
                            newInvoiceItem.totalAmount = Math.ceil((newInvoiceItem.usageAmount + newInvoiceItem.taxAmount) * 100d) / 100d;     
                            newInvoiceItem.referenceItemName = "VPN Connection"
                            newInvoiceItem.referenceItemId = currentVpnConnection.referenceId
                            newInvoiceItem.save(flush: true); 
                        } 
                    }
                }
                
                if (!currentVpnConnection.save()) {
                    currentVpnConnection.errors.allErrors.each { println it }
                }
                
            } else if(currentVpnConnection) {
                currentVpnConnection.state = data.state
                currentVpnConnection.save(flush: true);
                                                
                def invoice = Invoice.findWhere(account: vpnGateway.vpc.account, status: InvoiceStatus.values()[6])
                   
                if(invoice) {

                    def vpnOffer = MiscellaneousOffer.get(7);
                    def vpnBillableItem = BillableItem.get(20)
                    if(vpnBillableItem.enabled == true || vpnBillableItem.enabled == "true") {
                       if(currentVpnConnection.state =="Connected") {
                            def newInvoiceItem = new InvoiceItem()
                            newInvoiceItem.billableItem = vpnBillableItem
                            newInvoiceItem.invoice = invoice
                            newInvoiceItem.taxPercent = vpnBillableItem.tax.percentage
                            newInvoiceItem.zone = vpnGateway.vpc.zone
                            def offerCost = MiscellaneousOfferZoneCost.findWhere(miscellaneousOffer :vpnOffer, zone : vpnGateway.vpc.zone)
                            newInvoiceItem.usageUnitPrice = offerCost ? offerCost.cost : 0.0;
                            newInvoiceItem.usageUnits = 1.0;
                            double usageAmount = newInvoiceItem.usageUnits                
                            newInvoiceItem.usageAmount = Math.ceil(usageAmount * 100d) / 100d;   
                            double taxAmount = (newInvoiceItem.usageAmount/100)* newInvoiceItem.taxPercent
                            newInvoiceItem.taxAmount = Math.ceil(taxAmount * 100d) / 100d;     
                            newInvoiceItem.totalAmount = Math.ceil((newInvoiceItem.usageAmount + newInvoiceItem.taxAmount) * 100d) / 100d;     
                            newInvoiceItem.referenceItemName = "VPN Connection"
                            newInvoiceItem.referenceItemId = currentVpnConnection.referenceId
                            newInvoiceItem.save(flush: true); 
                        } 
                    }
                }
            }
        }
                
        def vpnConnectionLits = "";
        if(role.iterator().next() == "ROLE_ADMIN" ) {     
                vpnConnectionLits = VPNConnection.withCriteria {    
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
            vpnConnectionLits = VPNConnection.withCriteria {
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
        ArrayList<ArrayList<String>> vpnConnectionArray = new ArrayList<ArrayList<String>>();
        for(def vpnConnection: vpnConnectionLits) {
            HashMap<String,String> item = new HashMap<String,String>();
            item.put("ip", vpnConnection.vpnGateway.userIPAddress.publicIPAddress);
            item.put("referenceId", vpnConnection.referenceId);
            item.put("gateway", vpnConnection.vpnCustomerGateway.gateway);
            item.put("ipsecPresharedKey", vpnConnection.vpnCustomerGateway.ipsecPresharedKey);
            item.put("vpnCustomerGateway", vpnConnection.vpnCustomerGateway.name);
            item.put("state", vpnConnection.state);
            item.put("account", vpnConnection.account.firstName);            
            
            String esppolicy = vpnConnection.vpnCustomerGateway.espEncryption+"-"+vpnConnection.vpnCustomerGateway.espHash
            esppolicy += vpnConnection.vpnCustomerGateway.ikeDH !="" ? ";"+vpnConnection.vpnCustomerGateway.ikeDH : ""
            String ikepolicy = vpnConnection.vpnCustomerGateway.ikeEncryption+"-"+vpnConnection.vpnCustomerGateway.ikeHash
            ikepolicy += vpnConnection.vpnCustomerGateway.perfectForwardSecrecy !="" ? ";"+vpnConnection.vpnCustomerGateway.perfectForwardSecrecy : ""
            
            item.put("ikePolicy", ikepolicy);
            item.put("espPolicy", esppolicy);

            vpnConnectionArray.add(item)
        }
        return vpnConnectionArray
    }
        
    def createVPNConnection(requestData) {
     
        try {
            def user = springSecurityService.currentUser
            def role = springSecurityService.getPrincipal().getAuthorities()   
                        
            def invoice = Invoice.findWhere(account: user.account, status: InvoiceStatus.values()[6])
                               
            if(!invoice) {
                ArrayList<ArrayList<String>> networkResponse = new ArrayList<ArrayList<String>>();
                HashMap item = new HashMap();    
                item.put("result", GeneralConstants.RESULT_FAILURE);
                item.put("message", "Create a VM to add VPN Connection");
                networkResponse.add(item)

                return  networkResponse  
            }
            
            def vpc = VPC.findByReferenceId(requestData.vpcId)
            def vpnCustomerGateway = S2SCustomerGateway.findByReferenceId(requestData.VPNCustomerGateway)
            def vpnGateway = S2SVPNGateways.findWhere(vpc:vpc, deleted:false)
            
            
            HashMap<String,String> optional = new HashMap<String,String>();
            optional.put("account", new String(user.account.userName));
            optional.put("domainid", new String(user.account.domain.referenceId));
                                   
            def response = vpnServer().createVpnConnection(vpnCustomerGateway.referenceId,
            vpnGateway.referenceId) 
               
//            VPNConnection vpnConnection = new VPNConnection()
//            
//            vpnConnection.referenceId = response.jobId
//            vpnConnection.vpc = vpc
//            vpnConnection.vpnGateway = vpnGateway
//            vpnConnection.vpnCustomerGateway = vpnCustomerGateway
//            vpnConnection.account = user.account 
//            vpnConnection.created = new Date()
//            vpnConnection.jobId = response.jobId
//            vpnConnection.state = "Pending";
//            vpnConnection.save(flush:true)
//            if (!vpnConnection.save()) {
//                vpnConnection.errors.allErrors.each { println it }
//            }
            ArrayList<ArrayList<String>> networkResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            item.put("jobId", response.jobId);
            networkResponse.add(item)

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
    
    def createVPNConnectionJob(jobId) {
        
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
//                VPNConnection vpnConnection = VPNConnection.findByJobId(jobId)
//                VPNConnection.referenceId = jobResponse.id
//                VPNConnection.save(flush: true);
               
                return jobResult
                
            } else if(jobResponse.asychronousJobStatus == "2") {
                              
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.RESULT_FAILURE);
                item.put("message", jobResponse.errorText);
                jobResult.add(item);
//                VPNConnection vpnConnection = VPNConnection.findByJobId(jobId)
//                vpnConnection.delete(flush: true);
                
                return jobResult  
            }
                
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
    
    
    def removeVPNConnection(requestData) {
        
        try {   
                                
            def response = vpnServer().deleteVpnConnection(requestData.vpnConnectionId);     

            ArrayList<ArrayList<String>> vpnResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            item.put("jobId", response.jobId);
            vpnResponse.add(item)
            
            VPNConnection vpnConnection = VPNConnection.findByReferenceId(requestData.vpnConnectionId)
            vpnConnection.jobId = response.jobId;
            vpnConnection.save(flush:true)
            return vpnResponse;
            
        } catch (CloudStackServerException ex) {
            
            ArrayList<ArrayList<String>> vpnResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_FAILURE);
            item.put("message", ex.localizedMessage.substring(4));
            vpnResponse.add(item)

            return vpnResponse    
            
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
    }
    
    def getRemoveVPNConnectionJob(jobId) {
        
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
                
                VPNConnection vpnConnection = VPNConnection.findByJobId(jobId)
                vpnConnection.deleted = true;
                vpnConnection.removed = new Date();
                vpnConnection.billingUpdatedDate = new Date();
                vpnConnection.save(flush: true)
                
                def user = springSecurityService.currentUser
                def role = springSecurityService.getPrincipal().getAuthorities()   

//                def invoice = Invoice.findWhere(account: user.account, status: InvoiceStatus.values()[6])
//                def invoiceItem = InvoiceItem.findWhere(invoice:invoice, referenceItemId:vpnConnection.referenceId)
//                           
//                if(invoiceItem && (vpnConnection== "Connected" || vpnConnection== "Disconnected")) {
//                    
//                    double diff = new Date().getTime() - vpnConnection.billingUpdatedDate.getTime();
//
//                    double diffHours = diff / (60 * 60 * 1000) % 24;
//                    invoiceItem.usageUnits += diffHours;
//                    double usageAmount = invoiceItem.usageUnits * invoiceItem.usageUnitPrice              
//                    invoiceItem.usageAmount = Math.ceil(usageAmount * 100d) / 100d;   
//                    double taxAmount = (invoiceItem.usageAmount/100)* invoiceItem.taxPercent
//                    invoiceItem.taxAmount = Math.ceil(taxAmount * 100d) / 100d;     
//                    invoiceItem.totalAmount = Math.ceil((invoiceItem.usageAmount + invoiceItem.taxAmount) * 100d) / 100d;    
//                    invoiceItem.save(flush: true);
//                }                 
                
                def customerGateway = vpnConnection.vpnCustomerGateway
                customerGateway.isUsed = false;
                customerGateway.save(flush:true)
                return jobResult
                
            } else if(jobResponse.asychronousJobStatus == "2") {
                  
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
    def restartVPNConnection(requestData) {
        
        try {   
                                
            def response = vpnServer().resetVpnConnection(requestData.vpnConnectionId, null);     

            ArrayList<ArrayList<String>> vpnResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            item.put("jobId", response.jobId);
            vpnResponse.add(item)
            
            VPNConnection vpnConnection = VPNConnection.findByReferenceId(requestData.vpnConnectionId)
            vpnConnection.jobId = response.jobId;
            vpnConnection.save(flush:true)
            return vpnResponse;
            
        } catch (CloudStackServerException ex) {
            
            ArrayList<ArrayList<String>> vpnResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_FAILURE);
            item.put("message", ex.localizedMessage.substring(4));
            vpnResponse.add(item)

            return vpnResponse    
            
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
    }
    
    def getRestartVPNConnectionJob(jobId) {
        
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
                
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
    
    def VPCConnectionBillingCalc() {

        def vpnConnectionList = VPNConnection.findAllWhere(deleted: false);                             
        for(def vpnConnection: vpnConnectionList) {
            
            if(vpnConnection.billingUpdatedDate && (vpnConnection.state == "Connected" || vpnConnection.state == "Disconnected")) {
                def invoice = Invoice.findWhere(account: vpnConnection.account, status: InvoiceStatus.values()[6])
                def invoiceItem = InvoiceItem.findWhere(invoice:invoice, referenceItemId:vpnConnection.referenceId)
                                
                Console.print(invoice)
                Console.print(invoiceItem)
                Console.print(vpnConnection)
                
                    if(invoiceItem) {

                        double diff = new Date().getTime() - vpnConnection.billingUpdatedDate.getTime();

                        double diffHours = Math.ceil((diff / (60 * 60 * 1000) % 24) * 100d) / 100d;
                        invoiceItem.usageUnits += diffHours;
                        double usageAmount = invoiceItem.usageUnits * invoiceItem.usageUnitPrice              
                        invoiceItem.usageAmount = Math.ceil(usageAmount * 100d) / 100d;   
                        double taxAmount = (invoiceItem.usageAmount/100)* invoiceItem.taxPercent
                        invoiceItem.taxAmount = Math.ceil(taxAmount * 100d) / 100d;     
                        invoiceItem.totalAmount = Math.ceil((invoiceItem.usageAmount + invoiceItem.taxAmount) * 100d) / 100d;    
                        invoiceItem.save(flush: true);
                    }
                    
                vpnConnection.billingUpdatedDate = new Date()
                vpnConnection.save(flush: true);
            }
            
        }
    }
}