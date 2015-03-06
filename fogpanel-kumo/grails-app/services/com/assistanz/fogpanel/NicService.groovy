package com.assistanz.fogpanel

import com.assistanz.cloud.cloudstack.virtualmachine.CSVirtualMachineService
import org.codehaus.groovy.grails.commons.ApplicationHolder
import org.springframework.context.MessageSource
import com.assistanz.cloud.cloudstack.CloudStackServer
import com.assistanz.cloud.cloudstack.virtualmachine.VirtualMachineResponse
import groovy.json.*
import com.assistanz.cloud.cloudstack.iso.CSIsoService
import com.assistanz.cloud.cloudstack.iso.AttachISOResponse
import com.assistanz.cloud.cloudstack.iso.DetachISOResponse
import com.assistanz.cloud.cloudstack.iso.IsoResponse
import com.assistanz.cloud.cloudstack.nic.CSNicService
import com.assistanz.cloud.cloudstack.nic.NicResponse
import com.assistanz.cloud.cloudstack.nic.NicSecondaryIPResponse
import com.assistanz.cloud.cloudstack.pool.CSPoolService
import com.assistanz.cloud.cloudstack.pool.StoragePoolResponse
import com.assistanz.fogpanel.GeneralConstants;
import com.assistanz.cloud.cloudstack.asyncjob.CSAsyncJobService
import com.assistanz.cloud.cloudstack.host.CSHostService
import com.assistanz.cloud.cloudstack.host.HostResponse
import grails.converters.deep.JSON
import com.assistanz.cloud.cloudstack.volume.CSVolumeService
import com.assistanz.cloud.cloudstack.volume.VolumeResponse
import java.net.URL;
import java.util.StringTokenizer;
import com.assistanz.fogpanel.NotificationService
import com.assistanz.cloud.cloudstack.NetworkInterfaceCardResponse
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.text.DateFormat
import javax.xml.bind.DatatypeConverter;
import com.assistanz.cloud.cloudstack.SecurityGroupResponse
import com.assistanz.cloud.cloudstack.securitygroup.CSSecurityGroupService
import javax.crypto.Cipher;
import java.security.Key;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.security.KeyPair;
import java.security.Security;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMReader;
import java.io.StringReader;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.context.MessageSource;
import org.apache.commons.logging.LogFactory;

class NicService {       
    
    def springSecurityService;
    
    def nicServer() {
        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret
        CloudStackServer server = new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);                
        CSNicService csNicService = new CSNicService(server);        
        return csNicService;
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
    
    def virtualMachineServer() {
        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret

        CloudStackServer server =
        new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);
                
        CSVirtualMachineService csVirtualMachineService = new CSVirtualMachineService(server);
        
        return csVirtualMachineService;
    }
    
    def listNic (vm) {
        try {                 
            def currentVM = VirtualMachine.findByReferenceId(vm.referenceId);
            def response = nicServer().listNics(vm.referenceId, null);   
            
            ArrayList<ArrayList<String>> nicList = new ArrayList<ArrayList<String>>();
            for(Iterator<NicResponse> iter = response.nic.iterator(); iter.hasNext();) {
                def nicData = iter.next();  
                HashMap nicItem = new HashMap();
                Nic nic = Nic.findByReferenceId(nicData.id);
                if(!nic) {
                    nic = new Nic();
                    if(nicData.networkid) {
                        def network = Network.findByReferenceId(nicData.networkid)
                        network.hasFirstIp = true;
                        network.save(flush: true)
                        nic.network = network;
                        nic.type = network.type;
                        nicItem.put("network", network);
                        nicItem.put("type", network.type);
                    }                                        
                    nic.referenceId = nicData.id;
                    nic.gateway = nicData.gateway;
                    nic.isDefault = Boolean.parseBoolean(nicData.isdefault);
                    nic.macAddress = nicData.macaddress;                    
                    nic.createdDate = new Date();
                    nic.account = currentVM.user.account;                    
                    nic.netMask = nicData.netmask;
                    nic.ipAddress = nicData.ipaddress;
                    nic.virtualMachine = currentVM;
                    nic.deleted = false;
                    nic.save(flush: true);
                    if (nic.hasErrors()) {
                        Console.print("Add Nic Exception" + nic.errors.allErrors)
                    }                    
                    nicItem.put("referenceId",nicData.id);
                    nicItem.put("gateway", nicData.gateway);                                       
                    nicItem.put("isDefault", nicData.isdefault);
                    nicItem.put("macAddress", nicData.macaddress);                    
                    nicItem.put("createdDate", new Date().toString());
                    nicItem.put("account", currentVM.user.account);                    
                    nicItem.put("netMask", nicData.netmask);
                    nicItem.put("ipAddress", nicData.ipaddress);
                    nicItem.put("virtualMachine", currentVM);
                    nicItem.put("deleted", nic.deleted);                               
                    nicList.add(nicItem);
                } else if(nic)  {
                     nic.isDefault = Boolean.parseBoolean(nicData.isdefault);
                     nic.save(flush: true);
                }             
            }
            return nicList;                                                
        } catch(Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
    def list(vmReferenceId) {        
            def currentVM = VirtualMachine.findByReferenceId(vmReferenceId);            
                   
            def response = nicServer().listNics(currentVM.referenceId, null);   
        
            for(Iterator<NicResponse> iter = response.nic.iterator(); iter.hasNext();) {
                def nicData = iter.next();      
                Nic nic = Nic.findByReferenceId(nicData.id);
                if(!nic) {
                
                } else if(nic)  {
                     nic.isDefault = Boolean.parseBoolean(nicData.isdefault);
                     nic.save(flush: true);
                }             
            }
            
            def nicQuery = Nic.createCriteria()
            def nics = nicQuery.list {
                eq("deleted", false) 
                and {
                    eq("virtualMachine", currentVM) 
                }                 
            }
            ArrayList<ArrayList<String>> nicList = new ArrayList<ArrayList<String>>();
            for(int i = 0; i < nics.size(); i++) {
                def nicData = nics[i];    
                    HashMap nicItem = new HashMap();
                    if(nicData.network) {
                        def hasVPC = nicData.network.vpc?true:false;  
                        def vpcName = nicData.network.vpc?nicData.network.vpc.name:"";  
                        nicItem.put("type", nicData.network.type);
                        nicItem.put("networkName", nicData.network.name);                          
                        nicItem.put("networkID", nicData.network.id);    
                        nicItem.put("zoneType", nicData.network.zone.networkType);    
                        nicItem.put("hasVPC", hasVPC);    
                        nicItem.put("vpc", vpcName);       
                        nicItem.put("vpcReferenceId", nicData.network.vpc?nicData.network.vpc.referenceId:"");                               
                    }                    
                    nicItem.put("referenceId",nicData.referenceId);
                    nicItem.put("gateway", nicData.gateway);                                       
                    nicItem.put("isDefault", nicData.isDefault);
                    nicItem.put("macAddress", nicData.macAddress);                    
                    nicItem.put("createdDate", nicData.createdDate.toString());                                   
                    nicItem.put("netMask", nicData.netMask);
                    nicItem.put("ipAddress", nicData.ipAddress);
                    nicItem.put("deleted", nicData.deleted);       
                    nicList.add(nicItem);  
            }
            return nicList;                       
    }
    
    def deleteNic(requestBody) {
        try {
            def requestData = JSON.parse(requestBody)
        
            def network = Network.get(requestData.networkId)

            def response = virtualMachineServer().removeNicFromVirtualMachine(requestData.nicId, requestData.vmId)

            ArrayList<ArrayList<String>> nicDeleteResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            item.put("jobId", response.jobId);
            item.put("nicId", requestData.nicId);
            item.put("vmId",  VirtualMachine.findByReferenceId(requestData.vmId).id);
            nicDeleteResponse.add(item)

            return nicDeleteResponse   
        
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }  
    }
    
    def deleteNicJob (requestBody) {
        try {    
            def requestData = JSON.parse(requestBody);  
            ArrayList<ArrayList<String>> jobResult = new ArrayList<ArrayList<String>>();  
            def jobResponse = asyncJobServer().queryAsyncJobResult(requestData.jobId) 
            if(jobResponse.asychronousJobStatus == "0") {                
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.JOB_RESULT_PENDING);
                jobResult.add(item);                
                return jobResult                
            } else if(jobResponse.asychronousJobStatus == "1") {
               
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.RESULT_SUCCESS);
                item.put("vmId", requestData.vmId);
                
                def nic = Nic.findByReferenceId(requestData.nicId)
                nic.deleted = true
                nic.removedDate = new Date()
                nic.save(flush: true)
                listNic(VirtualMachine.get(requestData.vmId));            
                jobResult.add(item);                
                return jobResult                
            } else if(jobResponse.asychronousJobStatus == "2") {
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.JOB_RESULT_FAILURE);
                item.put("message", jobResponse.errorText);
                jobResult.add(item);                
                return jobResult  
            }
                
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
    }
    
    
    def getNetworkNicIPList(nicId, vmId) {                  
        HashMap<String,String> optional = new HashMap<String,String>();
        optional.put("nicid", nicId);
        
        def response = nicServer().listNics(vmId, optional);   
        
        ArrayList<ArrayList<String>> nicIPList = new ArrayList<ArrayList<String>>();
        for(Iterator<NicResponse> iter = response.nic.iterator(); iter.hasNext();) {
            def data = iter.next();
            for(Iterator<NicSecondaryIPResponse> secIpIter = data.secondaryIp.iterator(); secIpIter.hasNext();) {
                def secIpData = secIpIter.next();   
                   
                def nic = Nic.findByReferenceId(data.id)
                if(nic.network.zone.networkType == "Advanced") {                     
                    NicSecondaryIP nicIp = new NicSecondaryIP()
                    nicIp.account = nic.account
                    nicIp.nic = nic 
                    nicIp.ipReferenceId = secIpData.secIpId
                    nicIp.virtualMachine = nic.virtualMachine
                    nicIp.network = nic.network
                    nicIp.zone = nic.network.zone
                    nicIp.ipAddress = secIpData.ipAddress
                    nicIp.save(flush: true)
                }
                
                HashMap secIpItem = new HashMap();    
                secIpItem.put("referenceId", secIpData.secIpId);
                secIpItem.put("ip", secIpData.ipAddress);
                secIpItem.put("instanceName", VirtualMachine.findByReferenceId(vmId).displayName);
                secIpItem.put("networkName", Network.findByReferenceId(data.networkid).name);
                nicIPList.add(secIpItem)
            }
        }
        return nicIPList;
    }
    
    
    def acquireIp(requestBody) {
        
        try {
            def user = springSecurityService.currentUser 
            // convert string to json object
            def requestData = JSON.parse(requestBody)

            def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
            def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
            def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret

            CloudStackServer server = new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey)

            CSNicService cSNicService = new CSNicService(server)
            
            def responce = cSNicService.addIpToNic(requestData.nicId, null)      
            
            ArrayList<ArrayList<String>> ipJobList = new ArrayList<ArrayList<String>>();        
            HashMap<String,String> item = new HashMap<String,String>()
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            item.put("jobId", responce.jobId);
            item.put("nicId", requestData.nicId);
            ipJobList.add(item);
            log.info("IP Aquired for Nic ${requestData.nicId}, and returned job") 
            return ipJobList
            
        } catch(Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
    
    def acquireIpJob(requestBody) { 
        try {
        
            def user = springSecurityService.currentUser 
            // convert string to json object
            def requestData = JSON.parse(requestBody)

            def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
            def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
            def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret

            CloudStackServer server = new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey)
            
            CSNicService cSNicService = new CSNicService(server)
            
            def jobResponse = cSNicService.addIpJobResult(requestData.jobId)
                
            def nic = Nic.findByReferenceId(requestData.nicId)
            
            if(jobResponse.asychronousJobStatus == "1") {
                              
//                def invoice = Invoice.findWhere(account: user.account, status: InvoiceStatus.values()[6])
//
//                def ipOffer = MiscellaneousOffer.get(2);
//                def ipBillableItem = BillableItem.get(12)
//                if(ipBillableItem.enabled == true || ipBillableItem.enabled == "true") {
//
//                    def newInvoiceItem = new InvoiceItem()
//                    newInvoiceItem.billableItem = ipBillableItem
//                    newInvoiceItem.invoice = invoice
//                    newInvoiceItem.taxPercent = ipBillableItem.tax.percentage
//                    newInvoiceItem.zone = nic.network.zone
//                    newInvoiceItem.usageUnitPrice = MiscellaneousOfferZoneCost.findWhere(miscellaneousOffer :ipOffer, zone :nic.network.zone).cost;
//                    newInvoiceItem.usageUnits = 1.0;
//                    double usageAmount = newInvoiceItem.usageUnitPrice                
//                    newInvoiceItem.usageAmount = Math.ceil(usageAmount * 100d) / 100d;   
//                    double taxAmount = (newInvoiceItem.usageAmount/100)* newInvoiceItem.taxPercent
//                    newInvoiceItem.taxAmount = Math.ceil(taxAmount * 100d) / 100d;     
//                    newInvoiceItem.totalAmount = Math.ceil((newInvoiceItem.usageAmount + newInvoiceItem.taxAmount) * 100d) / 100d;     
//                    newInvoiceItem.referenceItemName = "Secondary IP For Network"
//                    newInvoiceItem.referenceItemId = jobResponse.secondaryIp
//                    newInvoiceItem.save(flush: true); 
//
//                }
                
                ArrayList<ArrayList<String>> ipJobList = new ArrayList<ArrayList<String>>();        
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.RESULT_SUCCESS);
                item.put("ip", jobResponse.secondaryIp);
                ipJobList.add(item);
                log.info("AcquireIp job success for ip: ${jobResponse.secondaryIp}") 
                return ipJobList
                
            } else if(jobResponse.asychronousJobStatus == "0") {
                
                ArrayList<ArrayList<String>> ipJobList = new ArrayList<ArrayList<String>>();        
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.JOB_RESULT_PENDING);
                item.put("jobId", jobResponse.asychronousJobId);
                ipJobList.add(item);
                return ipJobList
            } else if(jobResponse.asychronousJobStatus == "2") {
                
                ArrayList<ArrayList<String>> ipJobList = new ArrayList<ArrayList<String>>();        
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.RESULT_FAILURE);
                item.put("jobId", jobResponse.asychronousJobId);
                ipJobList.add(item);
                log.info("AcquireIp job failed for ip: ${jobResponse.secondaryIp}") 
                return ipJobList
            }
           
        } catch(Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
    
    
    def revokeIp(requestBody) {
        try {
            // convert string to json object
            def requestData = JSON.parse(requestBody)

            def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
            def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
            def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret

            CloudStackServer server = new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey)

            CSNicService cSNicService = new CSNicService(server)
            
            def responce = cSNicService.removeIpFromNic(requestData.ipId) 
                        
            def nicSecondaryIP = NicSecondaryIP.findByIpReferenceId(requestData.ipId)
                                    
            nicSecondaryIP?.jobId = responce.jobId
            nicSecondaryIP?.save(flush: true);    
            
            def userIPAddress = UserIPAddress.findByIpReferenceId(requestData.ipId)
            userIPAddress?.jobId = responce.jobId
            userIPAddress?.save(flush: true);               
                                                
            ArrayList<ArrayList<String>> ipJobList = new ArrayList<ArrayList<String>>();        
            HashMap<String,String> item = new HashMap<String,String>()
            item.put("jobResult", GeneralConstants.JOB_RESULT_PENDING);
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            item.put("jobId", responce.jobId);
            ipJobList.add(item);
            log.info("IP revoked for ip: ${requestData.ipId}, and returned job") 
            return ipJobList
        
        } catch(Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
        
    }
    
    def revokeIpJob(requestBody) { 
        try {
        
            def user = springSecurityService.currentUser 
            // convert string to json object
            def requestData = JSON.parse(requestBody)

            def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
            def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
            def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret

            CloudStackServer server = new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey)
            
            CSNicService cSNicService = new CSNicService(server)
            
            def jobResponse = cSNicService.addIpJobResult(requestData.jobId)
                   
            def nicSecondaryIP = NicSecondaryIP.findByJobId(requestData.jobId)
            
            def userIPAddress = UserIPAddress.findByJobId(requestData.jobId)
            
            if(jobResponse.asychronousJobStatus == "1") {
                
                nicSecondaryIP?.delete(flush: true)
                
                userIPAddress?.virtualMachine = null
                userIPAddress?.network = null
                userIPAddress?.account = null
                userIPAddress?.user = null
                userIPAddress?.staticNatVirtualMachine = null
                userIPAddress?.isSourceNat =  false
                userIPAddress?.isStaticNat = false
                userIPAddress?.removed = true
                userIPAddress?.state = "Free"
                           
                userIPAddress?.removed = true
                userIPAddress?.revokeDate = new Date()
                userIPAddress?.save(flush: true);  
                                                
                ArrayList<ArrayList<String>> ipJobList = new ArrayList<ArrayList<String>>();        
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.RESULT_SUCCESS);
                ipJobList.add(item);
//                log.info("IP revoked job success for ${vmIp.ipAddress}") 
                return ipJobList
            } else if(jobResponse.asychronousJobStatus == "0") {
                
                ArrayList<ArrayList<String>> ipJobList = new ArrayList<ArrayList<String>>();        
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.JOB_RESULT_PENDING);
                item.put("jobId", jobResponse.asychronousJobId);
                ipJobList.add(item);
//                log.info("IP revoked job pending for ${vmIp.ipAddress}") 
                return ipJobList
            } else if(jobResponse.asychronousJobStatus == "2") {
                
                ArrayList<ArrayList<String>> ipJobList = new ArrayList<ArrayList<String>>();        
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.RESULT_FAILURE);
                item.put("jobId", jobResponse.asychronousJobId);
                ipJobList.add(item);
//                log.info("IP revoked job failed for ${vmIp.ipAddress}") 
                return ipJobList
            }
           
        } catch(Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
}
