package com.assistanz.fogpanel

import java.util.HashMap;
import grails.converters.deep.JSON;
import com.assistanz.cloud.cloudstack.CloudStackServer
import com.assistanz.cloud.cloudstack.address.CSAddressService
import com.assistanz.cloud.cloudstack.address.IpAddressResponse
import com.assistanz.cloud.cloudstack.asyncjob.CSAsyncJobService
import com.assistanz.cloud.cloudstack.nic.CSNicService
import com.assistanz.cloud.cloudstack.nic.NicResponse
import com.assistanz.cloud.cloudstack.nic.NicSecondaryIPResponse
import com.assistanz.fogpanel.Util;
import org.codehaus.groovy.grails.commons.ApplicationHolder
import com.assistanz.cloud.cloudstack.vpn.CSVPNService
import com.assistanz.cloud.cloudstack.vpn.VpnUserResponse

class IpAddressService {
    
    def springSecurityService
    
    
    def addressServer() {
        
        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret
    
        CloudStackServer server =
        new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);
                
        CSAddressService addressService = new CSAddressService(server);
        
        return addressService;
        
    }
    def remoteAccessVpnService() {
        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret
    
        CloudStackServer server = new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);                                
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
    
    def nicServer() {
        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret
        CloudStackServer server = new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);                
        CSNicService csNicService = new CSNicService(server);        
        return csNicService;
    }        
    def vpnEnableJob(requestBody) {
        try {                        
            def requestData = JSON.parse(requestBody)
            def user = springSecurityService.currentUser
            ArrayList<ArrayList<String>> jobResult = new ArrayList<ArrayList<String>>();  
            def jobResponse = remoteAccessVpnService().vpnJobResult(requestData.jobId)
            if(jobResponse.jobStatus == "0") {                
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.JOB_RESULT_PENDING);
                jobResult.add(item);                
                return jobResult
                
            } else if(jobResponse.jobStatus == "1") {
                def remoteAccessVPN = new RemoteAccessVpn();                
                def userIPAddres = UserIPAddress.findByIpReferenceId(jobResponse.publicIpId);                
                if(userIPAddres) {
                    remoteAccessVPN.publicIPAddress = userIPAddres; 
                    remoteAccessVPN.network = userIPAddres.network;
                }                               
                remoteAccessVPN.account = user.account;                
                remoteAccessVPN.ipRange = jobResponse.ipRange;
                remoteAccessVPN.ipsecPsk = jobResponse.preSharedKey;
                remoteAccessVPN.state = jobResponse.state;
                remoteAccessVPN.referenceId = jobResponse.vpnId;    
                remoteAccessVPN.save(flush: true);      
                if (!remoteAccessVPN.save()) {
                    remoteAccessVPN.errors.allErrors.each { println it }
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
    def vpnDisableJob(requestBody) {
        try {                        
            def requestData = JSON.parse(requestBody)
            def user = springSecurityService.currentUser
            ArrayList<ArrayList<String>> jobResult = new ArrayList<ArrayList<String>>();  
            def jobResponse = remoteAccessVpnService().vpnJobResult(requestData.jobId)
            if(jobResponse.jobStatus == "0") {                
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.JOB_RESULT_PENDING);
                jobResult.add(item);                
                return jobResult
                
            } else if(jobResponse.jobStatus == "1") {
                def remoteAccessVPN = RemoteAccessVpn.findByReferenceId(requestData.vpnReferenceId);
                if(remoteAccessVPN) {
                    remoteAccessVPN.delete(flush: true);                   
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
    
    def enableVPNService(String ipAddressId) {
        try {            
            ArrayList<ArrayList<String>> vpnResponse = new ArrayList<ArrayList<String>>();
            HashMap<String,String> item = new HashMap<String,String>(); 
            def response = remoteAccessVpnService().createRemoteAccessVpn(ipAddressId, null);
            item.put("jobResult", GeneralConstants.JOB_RESULT_PENDING);
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            item.put("jobId", response.jobId);
            item.put("ipAddressId", ipAddressId);            
            vpnResponse.add(item);
            return vpnResponse;
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
    def disableVPNService(String ipAddressId) {
        try {            
            ArrayList<ArrayList<String>> vpnResponse = new ArrayList<ArrayList<String>>();
            HashMap<String,String> item = new HashMap<String,String>();             
            def response = remoteAccessVpnService().deleteRemoteAccessVpn(ipAddressId);
            def remoteAccessVPN = RemoteAccessVpn.findWhere(publicIPAddress: UserIPAddress.findByIpReferenceId(ipAddressId))
            if(remoteAccessVPN) {
                item.put("vpnReferenceId", remoteAccessVPN.referenceId);           
            }
            item.put("jobResult", GeneralConstants.JOB_RESULT_PENDING);             
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            item.put("jobId", response.jobId);
            item.put("ipAddressId", ipAddressId);            
            vpnResponse.add(item);
            return vpnResponse;
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
    
    def addVpnUser(requestBody) {
        try {
            ArrayList<ArrayList<String>> vpnUserArrayList = new ArrayList<ArrayList<String>>();  
            def requestData = JSON.parse(requestBody)    
            def user = springSecurityService.currentUser
            HashMap<String,String> optional = new HashMap<String,String>();
            optional.put("account", new String(user.account.accountIdentifier));
            optional.put("domainid", new String(user.account.domain.referenceId));
            def vpnUserResponse = remoteAccessVpnService().addVpnUser(requestData.password, requestData.userName, optional);   
            HashMap<String,String> item = new HashMap<String,String>(); 
            item.put("jobResult", GeneralConstants.JOB_RESULT_PENDING);             
            item.put("result", GeneralConstants.RESULT_SUCCESS);            
            item.put("jobId", vpnUserResponse.jobId);           
            item.put("vpnUserId", vpnUserResponse.id);               
            
            vpnUserArrayList.add(item);
            return vpnUserArrayList;                                                    
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
    def deleteVpnUserJob (requestBody) {
        try {    
            def user = springSecurityService.currentUser
            def requestData = JSON.parse(requestBody);  
            ArrayList<ArrayList<String>> jobResult = new ArrayList<ArrayList<String>>();  
            def jobResponse = asyncJobServer().queryAsyncJobResult(requestData.jobId)
            if(jobResponse.jobStatus == "0") {                
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.JOB_RESULT_PENDING);
                jobResult.add(item);                
                return jobResult                
            } else if(jobResponse.jobStatus == "1") {                                                             
                HashMap<String,String> item = new HashMap<String,String>()    
                def vpnUser = VpnUsers.findByVpnUserName(requestData.vpnUserName);
                if(vpnUser) {
                    vpnUser.delete(flush: true);                   
                }
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
    def deleteVpnUser(requestBody) {
        try {   
            ArrayList<ArrayList<String>> vpnUserArrayList = new ArrayList<ArrayList<String>>();  
            def user = springSecurityService.currentUser
            def requestData = JSON.parse(requestBody)    
            def vpnUserName = VpnUsers.findByVpnUserName(requestData.userName);
            if(vpnUserName) {
                HashMap<String,String> optional = new HashMap<String,String>();
                optional.put("account", new String(user.account.accountIdentifier));
                optional.put("domainid", new String(user.account.domain.referenceId));
                def vpnUserResponse = remoteAccessVpnService().removeVpnUser(requestData.userName, optional); 
                HashMap<String,String> item = new HashMap<String,String>(); 
                item.put("jobResult", GeneralConstants.JOB_RESULT_PENDING);             
                item.put("result", GeneralConstants.RESULT_SUCCESS);            
                item.put("jobId", vpnUserResponse.jobId);           
                item.put("vpnUserName", vpnUserName.vpnUserName);   
                vpnUserArrayList.add(item);
                return vpnUserArrayList;
            }            
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
    }
    def validateVpnUser(vpnUserName) {
        try { 
            ArrayList<ArrayList<String>> vpnUserArrayList = new ArrayList<ArrayList<String>>();  
            def isUser = VpnUsers.findByVpnUserName(vpnUserName)? true: false;            
            HashMap<String,String> item = new HashMap<String,String>(); 
            item.put("isUser", isUser);
            vpnUserArrayList.add(item)
            return vpnUserArrayList;
                      
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
    } 
    def getVpnByIP(ipAddressId) {
        try {
            ArrayList<ArrayList<String>> vpnArrayList = new ArrayList<ArrayList<String>>();  
            def vpnList = RemoteAccessVpn.findAllByPublicIPAddress(UserIPAddress.findByIpReferenceId(ipAddressId))
            if(vpnList) {
                for(int i =0 ; i < vpnList.size(); i++) {
                    def currentItem = vpnList[i];
                    HashMap<String,String> item = new HashMap<String,String>();            
                    item.put("ipsecPsk", currentItem.ipsecPsk);
                    item.put("publicIPId", currentItem.publicIPAddress.ipReferenceId);
                    item.put("publicIP", currentItem.publicIPAddress.publicIPAddress);                         
                    vpnArrayList.add(item);
                }
            }
            return vpnArrayList;
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
    }
    def listVPNUsers() {
        def user = springSecurityService.currentUser
        ArrayList<ArrayList<String>> vpnUserArrayList = new ArrayList<ArrayList<String>>();  
        def vpnUsers = VpnUsers.findAllWhere(account: user.account);
        for(int i =0 ; i < vpnUsers.size(); i++) {
            def currentItem = vpnUsers[i];
            HashMap<String,String> item = new HashMap<String,String>();            
            item.put("userName", currentItem.vpnUserName);
            item.put("account", user.account.accountIdentifier);
            item.put("state", currentItem.state);
            item.put("referenceId", currentItem.referenceId);      
            vpnUserArrayList.add(item);
        }
        return vpnUserArrayList;
    }
    def addVpnUserJob(requestBody) {
        try {    
            def user = springSecurityService.currentUser
            def requestData = JSON.parse(requestBody);  
            ArrayList<ArrayList<String>> jobResult = new ArrayList<ArrayList<String>>();  
            def jobResponse = asyncJobServer().queryAsyncJobResult(requestData.jobId)
            if(jobResponse.jobStatus == "0") {                
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.JOB_RESULT_PENDING);
                jobResult.add(item);                
                return jobResult                
            } else if(jobResponse.jobStatus == "1") {
               HashMap<String,String> optional = new HashMap<String,String>();
               optional.put("account", new String(user.account.accountIdentifier));
               optional.put("domainid", new String(user.account.domain.referenceId));
               def vpnUserList =  remoteAccessVpnService().listVpnUsers(optional);                
               for(Iterator<VpnUserResponse> iter = vpnUserList.vpnUsers.iterator(); iter.hasNext();) {                   
                   def data = iter.next();  
                   if(requestData.vpnUserId == data.id) {
                       def newVpnUser = new VpnUsers();                        
                       newVpnUser.account = user.account;                
                       newVpnUser.referenceId = data.id;
                       newVpnUser.vpnUserName = data.userName;
                       newVpnUser.state = data.state;                       
                       newVpnUser.save(flush: true);      
                       if (!newVpnUser.save()) {
                           newVpnUser.errors.allErrors.each { println it }
                       }
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
    def getIpCost(zoneReferenceId) {
        
        def user = springSecurityService.currentUser
        def role = springSecurityService.getPrincipal().getAuthorities()   
        
        
        def networkListTotal = Network.withCriteria {
            if(zoneReferenceId && zoneReferenceId != "All") {
                eq("zone", Zone.findByReferenceZoneId(zoneReferenceId))
            }
            eq("account", user.account)
            eq("deleted", false) 
            isNull("vpc") 
        }
        
        def adPublicIpCount = 0
        
        def advancedZoneList = Zone.findAllByNetworkType("Advanced")
        for(def adZone: advancedZoneList) {
            
            def adPublicIpList = UserIPAddress.withCriteria {
                if(zoneReferenceId && zoneReferenceId == "All") {
                    eq("zone", adZone)
                } else {
                    eq("zone", Zone.findByReferenceZoneId(zoneReferenceId))
                }
                eq("account", user.account)
                eq("removed", false)                  
            }
            adPublicIpCount += adPublicIpList.size()
        }
                
        def publicIpList = UserIPAddress.withCriteria {
            if(zoneReferenceId && zoneReferenceId != "All") {
                eq("zone", Zone.findByReferenceZoneId(zoneReferenceId))
            }
            eq("account", user.account)
            eq("removed", false)     
            isNull("vpc") 
        }
        
        
        def lbList = LoadBalancer.withCriteria {
            if(zoneReferenceId && zoneReferenceId != "All") {
                eq("zone", Zone.findByReferenceZoneId(zoneReferenceId))
            }
            eq("account", user.account)             
        }
        def lbCount = 0
        for(def lb: lbList) {
            if(!lb.netwotk.vpc) {
                lbCount++;
            }
        }
        
        def vpnList = RemoteAccessVpn.withCriteria {
            
            // add zone field to vpn table and un coment below lines
            
//            if(zoneReferenceId && zoneReferenceId != "All") {
//                eq("zone", Zone.findByReferenceZoneId(zoneReferenceId))
//            }
            
            eq("account", user.account)             
        }
        
        def pfList = PortForwarding.withCriteria {
            if(zoneReferenceId && zoneReferenceId != "All") {
                eq("zone", Zone.findByReferenceZoneId(zoneReferenceId))
            }
            eq("account", user.account)              
        }
        def pfCount = 0
        for(def pf: pfList) {
            if(!pf.netwotk.vpc) {
                pfCount++;
            }
        }
        
        def invoice = Invoice.findWhere(account: user.account, status: InvoiceStatus.values()[6])
        
        def publicIpCost = InvoiceItem.withCriteria {
            if(zoneReferenceId && zoneReferenceId != "All") {
                eq("zone", Zone.findByReferenceZoneId(zoneReferenceId))
            }
            eq("billableItem", BillableItem.get(12))
            eq("invoice", invoice)    
            projections {
                sum("totalAmount")
            }
        }
        def lbCost = InvoiceItem.withCriteria {
            if(zoneReferenceId && zoneReferenceId != "All") {
                eq("zone", Zone.findByReferenceZoneId(zoneReferenceId))
            }
            eq("billableItem", BillableItem.get(18))
            eq("invoice", invoice)    
            projections {
                sum("totalAmount")
            }
        }
        
        def pfCost = InvoiceItem.withCriteria {
            if(zoneReferenceId && zoneReferenceId != "All") {
                eq("zone", Zone.findByReferenceZoneId(zoneReferenceId))
            }
            eq("billableItem", BillableItem.get(19))
            eq("invoice", invoice)    
            projections {
                sum("totalAmount")
            }
        }
        
        ArrayList<ArrayList<String>> IPListResponse = new ArrayList<ArrayList<String>>();
        HashMap item = new HashMap();    
    
        if(publicIpCost[0] == "null" || publicIpCost[0] == null) {
             item.put("ipCost", 0);
        } else {
           item.put("ipCost", publicIpCost[0]);
        } 
        
        if(lbCost[0] == "null" || lbCost[0] == null) {
             item.put("lbCost", 0);
        } else {
           item.put("lbCost", lbCost[0]);
        } 

        if(pfCost[0] == "null" || pfCost[0] == null) {
             item.put("pfCost", 0);
        } else {
           item.put("pfCost", pfCost[0]);
        } 
           
        item.put("network", networkListTotal.size());
        item.put("vpnList", vpnList.size());
        item.put("ipCount", publicIpList.size());
        item.put("advanceZoneIPCount", adPublicIpCount);
        item.put("lbCount", lbCount); 
        item.put("pfCount", pfCount); 
        item.put("currency",ApplicationHolder.getApplication().config.billing.defaultCurrency);
        IPListResponse.add(item)
        
        return IPListResponse
    }
    
    
    def getPrivateIp(zoneReferenceId) {
        
        def user = springSecurityService.currentUser
        def role = springSecurityService.getPrincipal().getAuthorities()   
        
        def vmList = VirtualMachine.findAllWhere(user: user, deleted:false)
        
        for (def currentVM : vmList) {
            def response = nicServer().listNics(currentVM.referenceId, null);   

            ArrayList<ArrayList<String>> nicList = new ArrayList<ArrayList<String>>();
            for(Iterator<NicResponse> iter = response.nic.iterator(); iter.hasNext();) {
                def nicData = iter.next();                  
                if(nicData) {
                    Nic nic = Nic.findByReferenceId(nicData.id);
                     for(Iterator<NicSecondaryIPResponse> secIpIter = nicData.secondaryIp.iterator(); secIpIter.hasNext();) {
                        def secIpData = secIpIter.next();   
                        if(nic.network.zone.networkType == "Advanced") {

                            NicSecondaryIP nicIp = new NicSecondaryIP()
                            nicIp.account = nic.account
                            nicIp.nic = nic 
                            nicIp.ipReferenceId = secIpData.id
                            nicIp.virtualMachine = nic.virtualMachine
                            nicIp.network = nic.network
                            nicIp.zone = nic.network.zone
                            nicIp.ipAddress = secIpData.ipAddress
                            nicIp.save(flush: true)  
                        }
                    }
                }
            }     
        }
        def privateIpList = NicSecondaryIP.withCriteria {
            if(zoneReferenceId && zoneReferenceId != "All") {
                eq("zone", Zone.findByReferenceZoneId(zoneReferenceId))
            }
            eq("account", user.account)
        }
               
        ArrayList<ArrayList<String>> privateIPListResponse = new ArrayList<ArrayList<String>>();
                
        for(def privateIp: privateIpList) {
            
            HashMap item = new HashMap();    
            item.put("id", privateIp.ipReferenceId);
            item.put("networkReferenceId", privateIp.network?.referenceId);
            item.put("networkId", privateIp.network?.id);
            item.put("ip", privateIp.ipAddress);
            item.put("network", privateIp.network?.name);
            item.put("networkType", privateIp.network?.type);
            item.put("vm", privateIp.virtualMachine?.displayName); 
            item.put("zone", privateIp.zone.aliasName);
            item.put("zoneType", privateIp.zone.networkType);
            privateIPListResponse.add(item)
        }
        
        return privateIPListResponse
        
    }
    
    def getPublicIp(zoneReferenceId) {
        
        
        def user = springSecurityService.currentUser
        def role = springSecurityService.getPrincipal().getAuthorities()   
        
        if(role.iterator().next() == "ROLE_FREE_USER" || role.iterator().next() == "ROLE_PAID_USER") {
        
            HashMap<String,String> optional = new HashMap<String,String>();
            optional.put("account", new String(user.account.accountIdentifier));
            optional.put("domainid", new String(user.account.domain.referenceId));
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
                        def network = Network.findByReferenceId(data.associatedNetworkid)
                        network?.hasFirstIp = true;
                        network?.save(flush: true)  
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
            }
        }
        def puplicIpList;
        
        if(role.iterator().next() == "ROLE_ADMIN" ) {
            
            puplicIpList = UserIPAddress.withCriteria {
                eq("removed", false)
                and  {
                    isNotNull("user") 
                }
                and {
                    ne("account", user.account)
                }
            }
            
        } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {
            puplicIpList = UserIPAddress.withCriteria {
                if(zoneReferenceId && zoneReferenceId != "All") {
                    eq("zone", Zone.findByReferenceZoneId(zoneReferenceId))
                }
                eq("account", user.account)
                eq("removed", false)   
                isNull("vpc")
            }
        }
               
        ArrayList<ArrayList<String>> publicIPListResponse = new ArrayList<ArrayList<String>>();
                
        for(def puplicIp: puplicIpList) {
            
            HashMap item = new HashMap();    
            
            def isEnabledVPN = RemoteAccessVpn.findByNetwork(puplicIp.network)? true: false;
            
            item.put("id", puplicIp.ipReferenceId);
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

//            String vmListString  = "";
//            def lbVmList = LoadBalancerVmMap.findAllWhere(loadBalancer: lb)
//            for(def lbVm : lbVmList) {
//                vmListString += vmListString.size() == 0 ? vmListString : ",";
//                vmListString += lbVm.virtualMachine.name
//            }
//            item.put("lbVmList", vmListString);
            item.put("state", puplicIp.state);
            item.put("zone", puplicIp.zone.aliasName);
            item.put("zoneType", puplicIp.zone.networkType);
            item.put("account", puplicIp.account?.accountIdentifier);
            publicIPListResponse.add(item)
        }
        
        return publicIPListResponse
        
    }
    
    def getPublicVPCIp(zoneReferenceId) {
        
        
        def user = springSecurityService.currentUser
        def role = springSecurityService.getPrincipal().getAuthorities()   
        
        if(role.iterator().next() == "ROLE_FREE_USER" || role.iterator().next() == "ROLE_PAID_USER") {
        
            HashMap<String,String> optional = new HashMap<String,String>();
            optional.put("account", new String(user.account.accountIdentifier));
            optional.put("domainid", new String(user.account.domain.referenceId));
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
                        def network = Network.findByReferenceId(data.associatedNetworkid)
                        network?.hasFirstIp = true;
                        network?.save(flush: true)  
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
            }
        }
        def puplicIpList;
        
        if(role.iterator().next() == "ROLE_ADMIN" ) {
            
            puplicIpList = UserIPAddress.withCriteria {
                eq("removed", false)                  
            }
            
        } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {
            puplicIpList = UserIPAddress.withCriteria {
                if(zoneReferenceId && zoneReferenceId != "All") {
                    eq("zone", Zone.findByReferenceZoneId(zoneReferenceId))
                }
                eq("account", user.account)
                eq("removed", false)   
                isNotNull("vpc")
            }
        }
               
        ArrayList<ArrayList<String>> publicIPListResponse = new ArrayList<ArrayList<String>>();
                
        for(def puplicIp: puplicIpList) {
            
            HashMap item = new HashMap();    
            
            def isEnabledVPN = RemoteAccessVpn.findByNetwork(puplicIp.network)? true: false;
            
            item.put("id", puplicIp.ipReferenceId);
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
            item.put("vpc", puplicIp.vpc.name);

//            String vmListString  = "";
//            def lbVmList = LoadBalancerVmMap.findAllWhere(loadBalancer: lb)
//            for(def lbVm : lbVmList) {
//                vmListString += vmListString.size() == 0 ? vmListString : ",";
//                vmListString += lbVm.virtualMachine.name
//            }
//            item.put("lbVmList", vmListString);
            item.put("state", puplicIp.state);
            item.put("zone", puplicIp.zone.aliasName);
            item.put("zoneType", puplicIp.zone.networkType);
            item.put("account", puplicIp.account?.accountIdentifier);
            publicIPListResponse.add(item)
        }
        
        return publicIPListResponse
        
    }
    
    
    def list(String podReferenceId, String name) {
        try {
            
            def ipAddressQuery = IpAddress.createCriteria()
            def ipAddressResult;
        
            if((podReferenceId == "null" || podReferenceId == null) && (name == "null" || name == null)) {                      
                ipAddressResult = IpAddress.findAll("from IpAddress as ipAddress where ipAddress.deleted=?", [false]);   
            } else if((podReferenceId != "null" || podReferenceId != null) && (name == "null" || name == null)) {
                ipAddressResult = IpAddress.findAllWhere(deleted: false, pod: Pod.findByPodReferenceId(podReferenceId))
            } else if((podReferenceId == "null" || podReferenceId == null) && (name != "null" || name != null)) {

                ipAddressResult = ipAddressQuery.list {
                    like("name", "%" + name + "%")
                    and {
                      eq("deleted", false)
                    }
                }
            } else if((podReferenceId != "null" || podReferenceId != null) && (name != "null" || name != null)) {

                ipAddressResult = ipAddressQuery.list {
                    like("name", "%" + name + "%")
                    and {
                       eq("deleted", false)
                    } and {
                         eq("pod", Pod.findByPodReferenceId(podReferenceId))
                    }
                }
            } 
                
            ArrayList<ArrayList<String>> ipAddressList = new ArrayList<ArrayList<String>>();            
            for(int i=0; i < ipAddressResult.size(); i++) { 
                def item = ipAddressResult[i]; 
                HashMap<String,String> ipAddress = new HashMap<String,String>();                
                    ipAddress.put("name", item.ipBlockName);
                    ipAddress.put("ipId", item.id);
                    ipAddress.put("startIp", item.startIp);
                    ipAddress.put("endIp", item.endIp);
                    ipAddress.put("netMask", item.netMask);
                    ipAddress.put("gateWay", item.gateWay);
                    ipAddress.put("dns1", item.dns1);
                    ipAddress.put("dns2", item.dns2);
                    ipAddress.put("zoneName", item.zone.aliasName);
                    ipAddress.put("referenceZoneId", item.zone.referenceZoneId);
                    ipAddress.put("podName", item.pod.name);
                    ipAddress.put("podReferenceId", item.pod.podReferenceId);
                    ipAddressList.add(ipAddress);
            }
            return ipAddressList;
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
        
    }
    
    def create(String requestBody) {
        try { 
            def StringTokenizer;
            
            Util ipUtil = new Util()
            
            //convert string to json object
            def requestData = JSON.parse(requestBody)
            
            if(ipUtil.validate(requestData.startIp) == true && ipUtil.validate(requestData.endIp) == true && ipUtil.validate(requestData.netMask) == true
            && ipUtil.validate(requestData.gateWay) == true && ipUtil.validate(requestData.dns1) == true
            && ipUtil.validate(requestData.dns1) == true) {
                def newIpAddress = new IpAddress()   
                newIpAddress.ipBlockName = requestData.ipBlockName
                newIpAddress.startIp = requestData.startIp
                newIpAddress.endIp = requestData.endIp
                newIpAddress.netMask = requestData.netMask
                newIpAddress.gateWay = requestData.gateWay
                newIpAddress.dns1 = requestData.dns1
                newIpAddress.deleted = false
                newIpAddress.dns2 = requestData.dns2
                newIpAddress.zone = Zone.findByReferenceZoneId(requestData.zoneReferenceId)
                newIpAddress.pod = Pod.findByPodReferenceId(requestData.podReferenceId)
                BigInteger startIpValue = ipUtil.getNumberFromIp(requestData.startIp)
                BigInteger endIpValue = ipUtil.getNumberFromIp(requestData.endIp)
                newIpAddress.startIpValue = startIpValue
                newIpAddress.endIpValue = endIpValue
                def endIpCriteria = IpAddress.createCriteria()
                def queryEndIp = endIpCriteria.list {
                    le("startIpValue", endIpValue) and { ge("endIpValue", endIpValue) }
                }
                def startIpCriteria = IpAddress.createCriteria()
                def queryStartIp = startIpCriteria.list {
                    le("startIpValue", startIpValue) and { ge("endIpValue", startIpValue)  } 
                }
                def ipCriteria = IpAddress.createCriteria()
                def queryIp= ipCriteria.list {
                    between("startIpValue", startIpValue, endIpValue) and { between("endIpValue", startIpValue, endIpValue) } 
                }
                if(queryStartIp.size() == 0 || queryEndIp.size() == 0 || queryIp.size() == 0) {
                    if (newIpAddress.validate()) {
                        newIpAddress.save(flush: true); 
                        if (newIpAddress.hasErrors()) {
                            throw new ValidationException(newIpAddress.errors.allErrors);
                        } 
                        ArrayList<ArrayList<String>> ipAddressList = new ArrayList<ArrayList<String>>();  
                        def item = IpAddress.findByStartIp(newIpAddress.startIp) 
                        HashMap<String,String> ipAddress = new HashMap<String,String>();  
                        ipAddress.put("result", GeneralConstants.RESULT_SUCCESS);
                        ipAddress.put("name", item.ipBlockName);
                        ipAddress.put("ipId", item.id);
                        ipAddress.put("startIp", item.startIp);
                        ipAddress.put("endIp", item.endIp);
                        ipAddress.put("netMask", item.netMask);
                        ipAddress.put("gateWay", item.gateWay);
                        ipAddress.put("dns1", item.dns1);
                        ipAddress.put("dns2", item.dns2);
                        ipAddress.put("zoneName", item.zone.aliasName);
                        ipAddress.put("referenceZoneId", item.zone.referenceZoneId);
                        ipAddress.put("podName", item.pod.name);
                        ipAddress.put("podReferenceId", item.pod.podReferenceId);
                        ipAddressList.add(ipAddress);
                        return ipAddressList;
                    } else {
                        newIpAddress.errors.allErrors.each {
//                            Console.print("it" + it)
                        }
                    }
                } else {
                    throw new Exception("ip address not aviliable in this range");
                }
            } else {
                throw new Exception("validation failed");
            }
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
        
    }
    
}
