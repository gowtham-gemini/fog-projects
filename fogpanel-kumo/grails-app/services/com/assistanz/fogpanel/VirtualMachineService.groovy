package com.assistanz.fogpanel

import com.assistanz.cloud.cloudstack.virtualmachine.CSVirtualMachineService
import org.codehaus.groovy.grails.commons.ApplicationHolder
import org.springframework.context.MessageSource
import com.assistanz.cloud.cloudstack.CloudStackServer
import com.assistanz.cloud.cloudstack.virtualmachine.VirtualMachineResponse
import groovy.json.JsonBuilder
import com.assistanz.cloud.cloudstack.iso.CSIsoService
import com.assistanz.cloud.cloudstack.iso.AttachISOResponse
import com.assistanz.cloud.cloudstack.iso.DetachISOResponse
import com.assistanz.cloud.cloudstack.iso.IsoResponse
import com.assistanz.cloud.cloudstack.nic.CSNicService
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
import com.assistanz.cloud.cloudstack.address.CSAddressService
import com.assistanz.cloud.cloudstack.address.IpAddressResponse
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
import org.springframework.context.MessageSource
import com.assistanz.fogpanel.*;
import grails.transaction.Transactional
import org.apache.commons.logging.LogFactory;

@Transactional
class VirtualMachineService {
    
    MessageSource messageSource
    def springSecurityService;
    NotificationService notificationService
    NicService nicService
    LicenseValidationService licenseValidationService
    private static final log = LogFactory.getLog(this)
    
    def securityGroupServer() {
        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret

        CloudStackServer server =
        new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);

        CSSecurityGroupService cSSecurityGroupService = new CSSecurityGroupService(server);
        
        return cSSecurityGroupService;
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
    
    def virtualMachineServer() {
        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret

        CloudStackServer server =
        new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);
                
        CSVirtualMachineService csVirtualMachineService = new CSVirtualMachineService(server);
        
        return csVirtualMachineService;
    }
    
    def isoServer() {
        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret

        CloudStackServer server =
        new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);

        CSIsoService csIsoService = new CSIsoService(server);
        
        return csIsoService;
        
    }
       
    def volumeServer() {
        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret

        CloudStackServer server =
        new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);
        CSVolumeService csVolumeService = new CSVolumeService(server);
      
        return csVolumeService;
    }
    
    
    //    CSVolumeService csVolumeService = new CSVolumeService(server);
    def byDisk(volumeReferenceId) {
        try {            
            def result;
            def user = springSecurityService.currentUser
            def role = springSecurityService.getPrincipal().getAuthorities()   
            ArrayList<ArrayList<String>> instancesList = new ArrayList<ArrayList<String>>();      
            def virtualMachineCriteria = VirtualMachine.createCriteria()
            if(role.iterator().next() == "ROLE_ADMIN" ) {                
            } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {
                if(volumeReferenceId == null || volumeReferenceId == 'null') {                    
                    result = virtualMachineCriteria.list {                        
                        eq("deleted", false)  
                        and { 
                            eq("user", user) 
                            and {
                                isNull("vpc")
                            }
                        }                        
                        
                    };                                                    
                } else {                    
                    Volume volume = Volume.findByVolumeReferenceId(volumeReferenceId);    
                    result = VirtualMachine.findAllWhere(deleted: false, user: user, cluster:volume.diskOffer.cluster, vpc: null);                     
                }     
                for(int i=0; i < result.size(); i++) {                
                    def item = result[i]; 
                    def hasVMSnapshot = "false";
                    def VMSnapList = VMSnapshot.findAllWhere(virtualMachine:item, deleted: false);
                    if(VMSnapList) {
                        hasVMSnapshot = "true";
                    }
                    def volumeList = Volume.findAllWhere(type:"DATADISK", deleted: false, virtualMachine: VirtualMachine.findByReferenceId(item.referenceId));
                    if(volumeList.size() < 5) {        
                        HashMap<String,String> instances = new HashMap<String,String>();                        
                        instances.put("referenceId", item.referenceId);
                        instances.put("state", item.state);
                        instances.put("name", item.displayName);
                        instances.put("instanceName", item.name);
                        instances.put("computingOffer", item.computingOffer.name);
                        instances.put("hypervisor", item.hypervisor);
                        instances.put("template", item.template.name);
                        instances.put("owner", item.owner.userName);
                        instances.put("user", item.user.username);
                        instances.put("zoneName", item.zone.aliasName);
                        instances.put("osType", item.template.osType.name);
                        instances.put("osCategory", item.template.osCategory.name);
                        instances.put("securityGroupReferenceId", item.securityGroupReferenceId);
                        instances.put("firstName", item.owner.firstName);
                        instances.put("ip", item.nicIp);
                        instances.put("computingOfferClusterId", item.computingOffer.cluster.clusterReferenceId);
                        instances.put("computingOfferTags", item.computingOffer.tags);
                        instances.put("computingOfferType", item.computingOffer.storageType);   
                        instances.put("hasVMSnapshot", hasVMSnapshot);
                        instancesList.add(instances);                                                                                
                    }                
                }    
            }                                   
            return instancesList;
        } catch (Exception ex) { 
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
    
    
    def getHost(vmId) {
        try {
            def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
            def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
            def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret

            CloudStackServer server =
            new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);

            CSHostService csHostService = new CSHostService(server); 

            CSPoolService csPoolService = new CSPoolService(server);
            VirtualMachine vm = VirtualMachine.findByReferenceId(vmId)

            if(vm.state == "Stopped") {

                HashMap<String,String> optional = new HashMap<String,String>();
                optional.put("zoneid", vm.zone.referenceZoneId);

                def response = csPoolService.listStoragePools(optional);
                ArrayList<ArrayList<String>> hostList = new ArrayList<ArrayList<String>>();
                for(Iterator<StoragePoolResponse> storagePoolIter = response.storagePools.iterator(); storagePoolIter.hasNext();) {
                    def storagePoolData = storagePoolIter.next();
                    HashMap<String,String> hostItem = new HashMap<String,String>();                
                    hostItem.put("referenceId", storagePoolData.storagePoolId); 
                    hostItem.put("name", storagePoolData.storagePoolName); 
                    hostItem.put("type", "storage"); 
                    hostList.add(hostItem);
                }
                return hostList;
            } else if(vm.state == "Running") {
                def response = csHostService.findHostsForMigration(vmId);
                ArrayList<ArrayList<String>> hostList = new ArrayList<ArrayList<String>>();
                for(Iterator<HostResponse> hostIter = response.hosts.iterator(); hostIter.hasNext();) { 
                    def hostData = hostIter.next();
                    HashMap<String,String> hostItem = new HashMap<String,String>();                
                    hostItem.put("referenceId", hostData.hostId); 
                    hostItem.put("name", hostData.hostName); 
                    hostItem.put("type", "host"); 
                    hostList.add(hostItem);
                }
                return hostList;
            }
        
        } catch(Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
    def updateDefaultNic(requestBody) {
        try {
            def requestData = JSON.parse(requestBody);  
            VirtualMachine vm = VirtualMachine.findByReferenceId(requestData.vmId)
            Nic nic = Nic.findByReferenceId(requestData.nicId)           
            def nicResponse = virtualMachineServer().updateDefaultNicForVirtualMachine(nic.referenceId, vm.referenceId);
            ArrayList<ArrayList<String>> nicResultResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            item.put("jobId", nicResponse.jobId);
            item.put("nicId", nic.referenceId);
            item.put("vmId", vm.id);
            nicResultResponse.add(item);
            return nicResultResponse        
        } catch(Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
    
    def updateDefaultNicJob (requestBody) {
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
                def nicList =  nicService.listNic(VirtualMachine.get(requestData.vmId));
                
//                def nic = Nic.findByReferenceId(requestData.nicId)
//                nic.isDefault = true
                
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
    
    
    def addNicToVM(requestBody) {
        try {
            def requestData = JSON.parse(requestBody);  
            VirtualMachine vm = VirtualMachine.findByReferenceId(requestData.vmId)
            def network = Network.findByReferenceId(requestData.networkReferenceId)
            def nicResponse = virtualMachineServer().addNicToVirtualMachine(network.referenceId, vm.referenceId, null);
            ArrayList<ArrayList<String>> nicResultResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            item.put("jobId", nicResponse.jobId);
            nicResultResponse.add(item);
            return nicResultResponse        
        } catch(Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }        
    }
    def addNicToVMJob (requestBody) {
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
                VirtualMachine vm = VirtualMachine.findByReferenceId(requestData.vmId)                     
                nicService.listNic(vm);
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.RESULT_SUCCESS);
                item.put("vmId", vm.id);
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
    def migrateInstance(requestBody) {
       
        try {
            
            licenseValidationService.authorizeAction(FogPanelService.VM_MIGRATE)
            
            // convert string to json object
            def requestData = JSON.parse(requestBody)  

            VirtualMachine vm = VirtualMachine.findByReferenceId(requestData.vmId)

            HashMap<String,String> optional = new HashMap<String,String>();

            if(vm.state == "Stopped") {
                optional.put("storageid", requestData.hostId);
            } else if(vm.state == "Running") {
                optional.put("hostid", requestData.hostId);
            }        
            def migrateResponse = virtualMachineServer().migrateVirtualMachine(requestData.vmId, optional);   

            ArrayList<ArrayList<String>> jobList = new ArrayList<ArrayList<String>>();            
            HashMap<String,String> item = new HashMap<String,String>(); 
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            item.put("jobId", migrateResponse.jobId);
            jobList.add(item);
            log.info("Migrate to host initiate for vm: ${vm.referenceId} to the host: ${requestData.hostId}, and returned job id ") 
            return jobList;
        } catch(Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
    
    def migrateVmJob(requestBody) {
        
        try {
        
            def requestData = JSON.parse(requestBody)
                       
            def jobResponse = virtualMachineServer().virtualMachineJobResult(requestData.jobId);   
            if(jobResponse.asychronousJobStatus == "1") {                                             
                ArrayList<ArrayList<String>> jobList = new ArrayList<ArrayList<String>>();        
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.RESULT_SUCCESS);
                jobList.add(item);
                log.info("Migrate to host job for vm: ${jobResponse.virtualMachineId} is success")   
                return jobList
            } else if(jobResponse.asychronousJobStatus == "0") {                
                ArrayList<ArrayList<String>> jobList = new ArrayList<ArrayList<String>>();        
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.JOB_RESULT_PENDING);
                item.put("jobId", jobResponse.asychronousJobId);
                jobList.add(item);
                log.info("Migrate to host job for vm: ${jobResponse.virtualMachineId} is pending") 
                return jobList
            } else if(jobResponse.asychronousJobStatus == "2") {                
                ArrayList<ArrayList<String>> jobList = new ArrayList<ArrayList<String>>();        
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.RESULT_FAILURE);
                item.put("jobId", jobResponse.asychronousJobId);
                item.put("message", jobResponse.errorText);
                log.info("Migrate to host job for vm: ${jobResponse.virtualMachineId} is failed") 
                jobList.add(item);                
                return jobList
            }           
        } catch(Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
    
    
    def list(String volumeReferenceId, String securityGroupReferenceId) {
        try { 
            def result;
            def user = springSecurityService.currentUser
            def role = springSecurityService.getPrincipal().getAuthorities()   
            
            
            if(role.iterator().next() == "ROLE_ADMIN" ) {
                    
            } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {
                HashMap<String,String> optional = new HashMap<String,String>();
                optional.put("account", new String(user.account.userName));
                optional.put("domainid", new String(user.account.domain.referenceId));

                def response = virtualMachineServer().listVirtualMachines(optional);
                //            HashMap<String,String> cloudStackVmList = new HashMap<String,String>(); 
                for(Iterator<VirtualMachineResponse> iter = response.virtualMachines.iterator(); iter.hasNext();) {
                    def data = iter.next();               
                    //                cloudStackVmList.put(data.virtualMachineId ,"referenceId");
                    VirtualMachine virtualMachine = VirtualMachine.findByReferenceId(data.virtualMachineId);
                    for(Iterator<NetworkInterfaceCardResponse> networkInterfaceCardIter = data.networkInterfaceCards.iterator(); networkInterfaceCardIter.hasNext();) {

                        def networkInterfaceCardData = networkInterfaceCardIter.next();

                        if(virtualMachine) {
                            if(virtualMachine.nicIp == null || virtualMachine.nicIp == "null" || virtualMachine.nicIp == "") {
                                virtualMachine.nicIp = networkInterfaceCardData.ipAddress;
                                
//                                HashMap<String,String> ipOptional = new HashMap<String,String>();
//                                ipOptional.put("ipaddress", networkInterfaceCardData.ipAddress);
//
//                                def ipresponse = addressServer().listPublicIpAddresses(ipOptional);       
//                                for(Iterator <IpAddressResponse> ipiter = ipresponse.ipAddresses.iterator(); ipiter.hasNext();) {
//                                    def ipdata = ipiter.next();
//                                    UserIPAddress userIPAddress = UserIPAddress.findByIpReferenceId(ipdata.publicIpAddressId)
//                                    if(!userIPAddress) {
//                                        userIPAddress = new UserIPAddress(); 
//                                        userIPAddress.account = Account.findByUserName(ipdata.publicIpAddressAccount)
//                                        userIPAddress.user = User.findByUsername(ipdata.publicIpAddressAccount)
//                                        userIPAddress.ipReferenceId = ipdata.publicIpAddressId
//                                        userIPAddress.publicIPAddress = ipdata.publicIpAddress
//                                        userIPAddress.acquireDate = new Date()
//                                        userIPAddress.network = Network.findByReferenceId(ipdata.publicIpAddressAssociatedNetworkid)
//                                        userIPAddress.state = ipdata.publicIpAddressState
//                                        userIPAddress.virtualMachine = virtualMachine
//                                        userIPAddress.isSourceNat =  Boolean.parseBoolean(ipdata.publicIpAddressIsSourceNat)
//                                        userIPAddress.isStaticNat = Boolean.parseBoolean(ipdata.publicIpAddressIsStaticNat)
//                                        if(userIPAddress.isSourceNat == true) {
//                                            userIPAddress.isFirstIp = true
//                                        }
//                                        userIPAddress.isBasicVmDefaultIp = true
//                                        userIPAddress.zone = Zone.findByReferenceZoneId(ipdata.publicIpAddressZoneId)
//                                        userIPAddress.save(flush: true)
//                                        if (!userIPAddress.save()) {
//                                            userIPAddress.errors.allErrors.each { println it }
//                                        }
//
//                                    }
//                                }
                            }
                            if(virtualMachine.nicId == null || virtualMachine.nicId == "null" || virtualMachine.nicId == "") {
                                virtualMachine.nicId = networkInterfaceCardData.networkInterfaceCardId;
                            }
                            virtualMachine.save(flush: true); 
                        }
                    }  
                }  
            }           
            if((volumeReferenceId == "null" || volumeReferenceId == null) && (securityGroupReferenceId == "null" || securityGroupReferenceId == null)) {                              
                if(role.iterator().next() == "ROLE_ADMIN" ) {
                    result = VirtualMachine.findAll("from VirtualMachine as virtualMachine where virtualMachine.deleted=?", [false]);
                } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {
                    result = VirtualMachine.findAllWhere(deleted: false, user: user); 
                }
            } else if((volumeReferenceId != "null" || volumeReferenceId != null) && (securityGroupReferenceId == "null" || securityGroupReferenceId == null)) {
                Volume volume = Volume.findByVolumeReferenceId(volumeReferenceId);
                if(role.iterator().next() == "ROLE_ADMIN" ) {
                    result = VirtualMachine.findAll("from VirtualMachine as virtualMachine where virtualMachine.deleted=?", [false]);
                } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {
                    result = VirtualMachine.findAllWhere(deleted: false, user: user, cluster:volume.diskOffer.cluster); 
                }   
            }  else if((securityGroupReferenceId != "null" || securityGroupReferenceId != null) && (volumeReferenceId == "null" || volumeReferenceId == null)) {
                result = VirtualMachine.findAllWhere(deleted: false, user: user, securityGroupReferenceId: securityGroupReferenceId); 
            }  
            def currency = ApplicationHolder.getApplication().config.billing.defaultCurrency
            ArrayList<ArrayList<String>> instancesList = new ArrayList<ArrayList<String>>();            
            for(int i=0; i < result.size(); i++) { 
                def item = result[i];                 
                def miscelCost = MiscellaneousOfferZoneCost.findWhere(zone: Zone.findByReferenceZoneId(item.zone.referenceZoneId), miscellaneousOffer: MiscellaneousOffer.get(2));
                def hasVMSnapshot = "false";
                def VMSnapList = VMSnapshot.findAllWhere(virtualMachine:item, deleted: false);    
                if(VMSnapList) {
                    hasVMSnapshot = "true";
                }    
//                if(item.vpc == "null" || item.vpc == null) {
                    HashMap<String,String> instances = new HashMap<String,String>();   
                    if(miscelCost) {
                        instances.put("ipMiscelCost", miscelCost.cost.toString());
                    } else {
                        instances.put("ipMiscelCost", "");
                    }
                    instances.put("referenceId", item.referenceId);
                    def vpc = item.vpc?item.vpc.name:"-";  
                    instances.put("vpc", vpc);    
                    instances.put("id", item.id);                                          
                    instances.put("nicId", item.nicId);   
                    instances.put("offerHA", item.computingOffer.offerHA);                   
                    instances.put("state", item.state);
                    instances.put("name", item.displayName);
                    instances.put("instanceName", item.name);
                    instances.put("computingOffer", item.computingOffer.name);
                    instances.put("hypervisor", item.hypervisor);
                    instances.put("template", item.template.name);
                    instances.put("owner", item.owner.userName);
                    instances.put("user", item.user.username);
                    instances.put("zoneName", item.zone.aliasName);
                    instances.put("osType", item.template.osType.name);
                    instances.put("osCategory", item.template.osCategory.name);
                    instances.put("securityGroupReferenceId", item.securityGroupReferenceId);
                    instances.put("firstName", item.owner.firstName);
                    instances.put("ip", item.nicIp);
                    instances.put("computingOfferClusterId", item.computingOffer.cluster.clusterReferenceId);
                    instances.put("computingOfferTags", item.computingOffer.tags);
                    instances.put("computingOfferType", item.computingOffer.storageType);
                    instances.put("hasVMSnapshot", hasVMSnapshot);                
                    instances.put("currency", currency);


                    //                    instances.put("networkOffer", item.networkOffer.name);
                    //                    instances.put("diskOffer", item.diskOffer.name); 
                    instancesList.add(instances);
//                }
                
            }
            return instancesList;
        } catch (Exception ex) { 
            ex.printStackTrace(System.err);
            throw ex;
        } 
    }
    def getByVMId(Long vmId) {
        try {                        
            return VirtualMachine.get(vmId) ;
        } catch(Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }        
    }
    def getInstance(String virtualMachineId) {          
        HashMap instances = new HashMap();
        HashMap<String,String> optional = new HashMap<String,String>();
        optional.put("id", virtualMachineId);
        def response = virtualMachineServer().listVirtualMachines(optional);

        for(Iterator<VirtualMachineResponse> iter = response.virtualMachines.iterator(); iter.hasNext();) {
            def data = iter.next();
            instances.put("cpuUsed", data.cpuUsed);
            VirtualMachine virtualMachine = VirtualMachine.findByReferenceId(data.virtualMachineId);
            for(Iterator<NetworkInterfaceCardResponse> networkInterfaceCardIter = data.networkInterfaceCards.iterator(); networkInterfaceCardIter.hasNext();) {

                def networkInterfaceCardData = networkInterfaceCardIter.next();
                if(virtualMachine) {
                    if(virtualMachine.nicIp == null || virtualMachine.nicIp == "null" || virtualMachine.nicIp == "") {
                        virtualMachine.nicIp = networkInterfaceCardData.ipAddress;                        
                    }
                    if(virtualMachine.nicId == null || virtualMachine.nicId == "null" || virtualMachine.nicId == "") {
                        virtualMachine.nicId = networkInterfaceCardData.networkInterfaceCardId;
                    }

                }
            }   
            Iso iso = Iso.findByIsoReferenceId(data.isoId);
            virtualMachine.iso = iso;
            virtualMachine.vmInternalName = data.instanceName;
            virtualMachine.displayName = data.displayName;
            virtualMachine.state = data.virtualMachineState;       
            virtualMachine.save(flush: true); 
        }

        VirtualMachine virtualMachine = VirtualMachine.findByReferenceId(virtualMachineId);

        def hasVMSnapshot = "false";
        def VMSnapList = VMSnapshot.findAllWhere(virtualMachine:virtualMachine, deleted: false);
        if(VMSnapList) {
            hasVMSnapshot = "true";
        }
        def VMSnapListCount = VMSnapshot.findAllWhere(virtualMachine:virtualMachine, deleted: false).size()
        def currentVM = VirtualMachine.findByReferenceId(virtualMachineId);
        if(currentVM.zone.networkType == GeneralConstants.ZONE_TYPE_BASIC) {
            optional.put("id", virtualMachine.securityGroupReferenceId);
            def fireWallResponse = securityGroupServer().listSecurityGroups(optional);            
            for(Iterator<SecurityGroupResponse> siter = fireWallResponse.securityGroups.iterator(); siter.hasNext();) {
                def data = siter.next(); 
                instances.put("securityGroupId", virtualMachine.securityGroupReferenceId);
                instances.put("securityGroupName", data.securityGroupName);  
            }            
        } else {
            instances.put("securityGroupId", "");
            instances.put("securityGroupName", "");
        }       
        ArrayList<ArrayList<String>> instancesList = new ArrayList<ArrayList<String>>();            
        def vpcReferenceId = virtualMachine.vpc?virtualMachine.vpc.referenceId:"";  
        def vpcName = virtualMachine.vpc?virtualMachine.vpc.name:"";  
        
        def hasVPC = virtualMachine.vpc?true:false;  
        
        instances.put("referenceId", virtualMachine.referenceId);
        instances.put("state", virtualMachine.state);
        instances.put("nicIp", virtualMachine.nicIp);
        instances.put("name", virtualMachine.displayName);
        instances.put("instanceName", virtualMachine.name);
        instances.put("vpcReferenceId", vpcReferenceId); 
        instances.put("vpcName", vpcName); 
        
        instances.put("hasVPC", hasVPC);       
        
        if(virtualMachine.iso == "null" || virtualMachine.iso == null) {
            instances.put("isoName", "null");
        } else {
            instances.put("isoName", virtualMachine.iso.name);
            instances.put("isoId", virtualMachine.iso.isoReferenceId);

        }       
        def currency = ApplicationHolder.getApplication().config.billing.defaultCurrency
        def miscelCost = MiscellaneousOfferZoneCost.findWhere(zone: Zone.findByReferenceZoneId(virtualMachine.zone.referenceZoneId), miscellaneousOffer: MiscellaneousOffer.get(2));
        if(miscelCost) {
            instances.put("miscelIPCost", miscelCost.cost.toString());
        } else {
            instances.put("miscelIPCost", "");
        }    
        instances.put("currency", currency);
        instances.put("VMSnapListCount", VMSnapListCount); 
        if(virtualMachine.zone.networkType == "Advanced") {
            def nic = Nic.findWhere(virtualMachine : virtualMachine, isDefault: true)            
            instances.put("networkName", nic.network.name); 
            instances.put("networkID", nic.network.id);             
            instances.put("defaultNicIp", nic.ipAddress);             
        }        
        instances.put("hasVMSnapshot", hasVMSnapshot);
        instances.put("computingOffer", virtualMachine.computingOffer.name);
        instances.put("hypervisor", virtualMachine.hypervisor);
        instances.put("template", virtualMachine.template.name);
        instances.put("owner", virtualMachine.owner.userName);
        instances.put("user", virtualMachine.user.username);
        instances.put("zoneName", virtualMachine.zone.aliasName);
        instances.put("networkType", virtualMachine.zone.networkType);
        
        instances.put("read", virtualMachine.networkRead);
        instances.put("bandWidth", virtualMachine.computingOffer.bandWidth);
        instances.put("write", virtualMachine.networkWrite);
        instances.put("zoneId", virtualMachine.zone.referenceZoneId);
        instances.put("templateId", virtualMachine.template.templateReferenceId);
        instances.put("templateDiskSize", virtualMachine.template.size.toString());        
        instances.put("osType", virtualMachine.template.osType.name);
        instances.put("baseOs", virtualMachine.template.baseOs);
        instances.put("osCategory", virtualMachine.template.osCategory.name);
        instances.put("computingOfferClusterId", virtualMachine.computingOffer.cluster.clusterReferenceId);
        instances.put("offerHA", virtualMachine.computingOffer.offerHA);        
        instances.put("computingOfferTags", virtualMachine.computingOffer.tags);
        instances.put("storageType", virtualMachine.computingOffer.storageType);
        instances.put("computingOfferId", virtualMachine.computingOffer.offerReferenceId);
        instances.put("sshKey", virtualMachine.sshKey.name);
        instances.put("accountType", virtualMachine.owner.accountType.name());
        instances.put("instanceBilingType", virtualMachine.billingType);

        //      instances.put("networkOffer", item.networkOffer.name);
        //      instances.put("diskOffer", item.diskOffer.name); 
        def volume = Volume.findAllWhere(virtualMachine:virtualMachine);
        ArrayList<ArrayList<String>> currentVolumeList = new ArrayList<ArrayList<String>>(); 
        for(int j=0; j < volume.size(); j++) { 
            def currentVolume = volume[j]; 
            HashMap<String,String> curerntVolumeItem = new HashMap<String,String>(); 
            curerntVolumeItem.put("referenceId", currentVolume.volumeReferenceId);
            curerntVolumeItem.put("zoneName", currentVolume.zone.aliasName);
            curerntVolumeItem.put("name", currentVolume.name);
            currentVolumeList.add(curerntVolumeItem); 
        }
        instances.put("storage", currentVolumeList);
        instancesList.add(instances);
        return instancesList;                   
    }
    
    def attachIso(requestBody) {
        
        licenseValidationService.authorizeAction(FogPanelService.VM_ATTACH_ISO)
        
        // convert string to json object
        def requestData = JSON.parse(requestBody)  
            
        def attachIsoResponse = isoServer().attachIso(requestData.isoReferenceId, requestData.virtualmachineReferenceId)
        Iso iso = Iso.findByIsoReferenceId(requestData.isoReferenceId);
        VirtualMachine virtualMachine = VirtualMachine.findByReferenceId(requestData.virtualmachineReferenceId);
        virtualMachine.job = attachIsoResponse.jobId;
        virtualMachine.save(flush: true); 
        ArrayList<ArrayList<String>> isoList = new ArrayList<ArrayList<String>>();            
        HashMap<String,String> item = new HashMap<String,String>(); 
        item.put("result", GeneralConstants.RESULT_SUCCESS);
        item.put("jobId", attachIsoResponse.jobId);
        isoList.add(item);
        return isoList;
    }
    
    def detachIso(requestBody) {
        
        licenseValidationService.authorizeAction(FogPanelService.VM_DETACH_ISO)
        
        // convert string to json object
        def requestData = JSON.parse(requestBody)  
            
        def detachIsoResponse = isoServer().detachIso(requestData.virtualmachineReferenceId)
        VirtualMachine virtualMachine = VirtualMachine.findByReferenceId(requestData.virtualmachineReferenceId);
        virtualMachine.job = detachIsoResponse.jobId;
        virtualMachine.save(flush: true); 
        ArrayList<ArrayList<String>> isoList = new ArrayList<ArrayList<String>>();            
        HashMap<String,String> item = new HashMap<String,String>(); 
        item.put("result", GeneralConstants.RESULT_SUCCESS);
        item.put("jobId", detachIsoResponse.jobId);
        isoList.add(item);
        return isoList;
    }
    
    def update(requestBody) {
        
        licenseValidationService.authorizeAction(FogPanelService.VM_UPDATE_HOSTNAME)
        
        // convert string to json object
        def requestData = JSON.parse(requestBody)  
          
        HashMap<String,String> optional = new HashMap<String,String>();
        optional.put("displayname", requestData.virtualMachineName);
        VirtualMachine oldVirtualMachine = VirtualMachine.findByReferenceId(requestData.id);
        def response = virtualMachineServer().updateVirtualMachine(requestData.id ,optional);
        oldVirtualMachine.displayName = response.displayName;
        if (oldVirtualMachine.hasErrors()) {
            throw new ValidationException(oldVirtualMachine.errors.allErrors);
        } 
        ArrayList<ArrayList<String>> instancesList = new ArrayList<ArrayList<String>>();            
        HashMap<String,String> instances = new HashMap<String,String>(); 
        instances.put("result",  GeneralConstants.RESULT_SUCCESS);
        instances.put("referenceId",  oldVirtualMachine.referenceId);
        instances.put("state", oldVirtualMachine.state);
        instances.put("jobId", oldVirtualMachine.job);
        instances.put("name", oldVirtualMachine.displayName);
        instances.put("computingOffer", oldVirtualMachine.computingOffer.name);
        instances.put("hypervisor", oldVirtualMachine.hypervisor);
        instances.put("template", oldVirtualMachine.template.name);
        instances.put("owner", oldVirtualMachine.owner.userName);
        instances.put("user", oldVirtualMachine.user.username);
        instances.put("zoneName", oldVirtualMachine.zone.aliasName);
        instances.put("osType", oldVirtualMachine.template.osType.name);
        instancesList.add(instances);
        return instancesList;
        
    }
    
    def create(String requestBody) {
        try { 
            licenseValidationService.authorizeAction(FogPanelService.VM_CREATE)
            
            def currentUser = springSecurityService.currentUser 
            def role = springSecurityService.getPrincipal().getAuthorities()
            Date date = Calendar.getInstance().getTime()
            def currentTime = new Timestamp(date.getTime())
            
            // convert string to json object
            def requestData = JSON.parse(requestBody)
            
            def newVirtualMachine = new VirtualMachine()            
            newVirtualMachine.computingOffer = ComputingOffer.findByOfferReferenceId(requestData.computingOfferId);
            Template template = Template.findByTemplateReferenceId(requestData.tempId);
            newVirtualMachine.template = template;
            newVirtualMachine.zone = Zone.findByReferenceZoneId(requestData.zoneId);   
            newVirtualMachine.upgradedDate = date;
            if(requestData.billingType == "hourly" || requestData.billingType == "monthly") {   
                newVirtualMachine.billingType = requestData.billingType;
            } else {
                newVirtualMachine.billingType = "hourly";
            }              
            newVirtualMachine.displayName = requestData.instanceName;                       
            newVirtualMachine.hypervisor = template.hypervisor;
            newVirtualMachine.cluster = newVirtualMachine.computingOffer.cluster;
            String hypervisor = newVirtualMachine.hypervisor;
            String zoneId = newVirtualMachine.zone.referenceZoneId;
            String serviceOfferingId = newVirtualMachine.computingOffer.offerReferenceId;
            String templateId = newVirtualMachine.template.templateReferenceId;
            HashMap<String,String> optional = new HashMap<String,String>();
            if(requestData.diskOfferId == "" || requestData.diskOfferId == null || requestData.diskOfferId == "null") {
                
            } else {
                newVirtualMachine.diskOffer = DiskOffer.findByDiskOfferReferenceId(requestData.diskOfferId);
                String diskOfferingId = newVirtualMachine.diskOffer.diskOfferReferenceId;
                optional.put("diskofferingid", diskOfferingId); 
                def diskOffer = DiskOffer.findByDiskOfferReferenceId(requestData.diskOfferId);
                if(diskOffer.customized == true || diskOffer.customized == "true") {
                    optional.put("size", requestData.diskSize);
                }
            }
            optional.put("hypervisor", hypervisor);
            optional.put("displayname", new String(newVirtualMachine.displayName)); 
            def currentZone = Zone.findByReferenceZoneId(requestData.zoneId)
            if(currentZone.networkType == "Advanced") {          
                def networkId = Network.findByReferenceId(requestData.networkId);
                newVirtualMachine.vpc = networkId.vpc
                if(networkId.hasFirstIp == false) {
                    networkId.hasFirstIp = true;
                    networkId.save(flush: true)
                }
                if(networkId.type == "Isolated" || networkId.type == "Shared") {
                    optional.put("networkids", new String(requestData.networkId));
                } else {
                    optional.put("networkids", new String(requestData.networkId));
                    optional.put("securitygroupids", new String(requestData.securityGroupReferenceId));
                    newVirtualMachine.securityGroupReferenceId = requestData.securityGroupReferenceId; 
                }                
            } else {
                optional.put("securitygroupids", new String(requestData.securityGroupReferenceId));
                newVirtualMachine.securityGroupReferenceId = requestData.securityGroupReferenceId; 
            }
            def hostName = "";
            def accId = "";
            Date newDate = new Date(); 
            def currentDate = newDate.getTime();
            if(role.iterator().next() == "ROLE_ADMIN" ) {
                def user = User.findByUsername(requestData.userName);
                optional.put("account", new String(user.account.userName));
                optional.put("domainid", new String(user.account.domain.referenceId));
                newVirtualMachine.owner = Account.findByUserName(user.username);
                newVirtualMachine.user = user
                accId = newVirtualMachine.owner.id;                
            } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {
                optional.put("account", new String(currentUser.account.userName));
                optional.put("domainid", new String(currentUser.account.domain.referenceId));
                newVirtualMachine.owner = Account.findByUserName(currentUser.username);
                newVirtualMachine.user = currentUser
                accId = newVirtualMachine.owner.id;                
            }
            hostName = "H" + accId.toString() + "-" + currentDate.toString();
            newVirtualMachine.name = hostName.toString(); 
            optional.put("keypair", requestData.keyPair);
            optional.put("name", hostName.toString());	
            def response =  virtualMachineServer().deployVirtualMachine(serviceOfferingId, templateId, zoneId, optional);
            newVirtualMachine.deleted = false;  
            newVirtualMachine.sshKey = SSHKeys.findByName(requestData.keyPair);
            newVirtualMachine.referenceId = response.virtualMachineId;    
            newVirtualMachine.networkRead = 0.0; 
            newVirtualMachine.networkWrite = 0.0; 
            
            newVirtualMachine.job = response.jobId; 
            newVirtualMachine.firstRun = true;
            def jobResponse = virtualMachineServer().virtualMachineJobResult(response.jobId)
            if(jobResponse.asychronousJobProgressStatus == "0") {
                newVirtualMachine.state = "in progress"
                newVirtualMachine.referenceId = jobResponse.asychronousJobInstanceId; 
            }            
            newVirtualMachine.save(flush: true); 
            if (newVirtualMachine.hasErrors()) {
                throw new ValidationException(newVirtualMachine.errors.allErrors);
            }           
            ArrayList<ArrayList<String>> instancesList = new ArrayList<ArrayList<String>>();            
            HashMap<String,String> instances = new HashMap<String,String>(); 
            instances.put("result",  GeneralConstants.RESULT_SUCCESS);
            instances.put("referenceId",  newVirtualMachine.referenceId);
            instances.put("state", newVirtualMachine.state);
            instances.put("jobId", newVirtualMachine.job);
            instances.put("name", newVirtualMachine.displayName);
            instances.put("computingOffer", newVirtualMachine.computingOffer.name);
            instances.put("hypervisor", newVirtualMachine.hypervisor);
            instances.put("template", newVirtualMachine.template.name);
            instances.put("owner", newVirtualMachine.owner.userName);
            instances.put("user", newVirtualMachine.user.username);
            instances.put("zoneName", newVirtualMachine.zone.aliasName);
            instances.put("osType", newVirtualMachine.template.osType.name);
            instances.put("securityGroupReferenceId", newVirtualMachine.securityGroupReferenceId);
            instancesList.add(instances);
            log.info("Created VM id:${newVirtualMachine.referenceId} and returned job id:${newVirtualMachine.job}") 
            return instancesList;
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
    
    def addNewInvoice(invoice, account, invoiceDate, currentTime, newVirtualMachine) {       
    
        addInvoiceItem(newVirtualMachine, invoice)    
    
    }
    
    def addInvoiceItem(newVirtualMachine, invoice) {        
        
        
    }
    
    def addBandwidthCost(referenceItemId, oldComputeOffer, invoice) {
        def virtualMachine = VirtualMachine.findByReferenceId(referenceItemId);
       
        double readGB = virtualMachine.networkRead / 1048576
        double writeGB = virtualMachine.networkWrite / 1048576
        
        double totalGB = writeGB;
              
        double planBandwidth = 1.0 * oldComputeOffer.bandWidth
        if(totalGB > planBandwidth) {
            
            def bandWidthBillableItem = BillableItem.get(5)
            def computingOffer = oldComputeOffer
            if(bandWidthBillableItem.enabled == true || bandWidthBillableItem.enabled == "true") {
                def bandwidthInvoiceItem = InvoiceItem.findWhere(invoice: invoice, referenceItemId:referenceItemId, referencePlanId:computingOffer.offerReferenceId);
                if(bandwidthInvoiceItem) {
                    def newInvoiceItem = new InvoiceItem()
                    newInvoiceItem.billableItem = bandWidthBillableItem
                    newInvoiceItem.invoice = invoice
                    newInvoiceItem.taxPercent = bandWidthBillableItem.tax.percentage
                    newInvoiceItem.referencePlanId = computingOffer.offerReferenceId
                    newInvoiceItem.zone = virtualMachine.zone
                    def bandWidth = MiscellaneousOffer.get(1);
                    def bandWidthCost = MiscellaneousOfferZoneCost.findWhere(miscellaneousOffer :bandWidth, zone :computingOffer.zone);
                    newInvoiceItem.usageUnitPrice = bandWidthCost.cost
                    newInvoiceItem.usageUnits = totalGB - planBandwidth
                    double usageAmount = newInvoiceItem.usageUnits * newInvoiceItem.usageUnitPrice                
                    newInvoiceItem.usageAmount = Math.ceil(usageAmount * 100d) / 100d;
                    double taxAmount = (newInvoiceItem.usageAmount/100)* newInvoiceItem.taxPercent
                    newInvoiceItem.taxAmount = Math.ceil(taxAmount * 100d) / 100d;    
                    newInvoiceItem.totalAmount = Math.ceil((newInvoiceItem.usageAmount + newInvoiceItem.taxAmount) * 100d) / 100d;   
                    newInvoiceItem.referenceItemName = "Bandwidth"
                    newInvoiceItem.referenceItemId = referenceItemId 
                    
                    return newInvoiceItem 
                }
                return null
            }
            return null
        }
        return null
        
        
    }
            
    def addSetupCost(invoice, newVirtualMachine) {
        
        def setupCost = ComputingOfferZoneCost.findWhere(computingOffer: newVirtualMachine.computingOffer).setupCost
        
        if(setupCost != 0) {
            def setupBillableItem = BillableItem.findByReferenceItemName("SetupCost")
            if(setupBillableItem.enabled == true || setupBillableItem.enabled == "true") {
                def invoiceItem = new InvoiceItem()
                invoiceItem.billableItem = setupBillableItem
                invoiceItem.invoice = invoice
                invoiceItem.zone = newVirtualMachine.zone
                invoiceItem.taxPercent = setupBillableItem.tax.percentage
                invoiceItem.referencePlanId = newVirtualMachine.computingOffer.offerReferenceId
                invoiceItem.usageUnits = 1.0
                invoiceItem.usageUnitPrice = ComputingOfferZoneCost.findWhere(computingOffer: newVirtualMachine.computingOffer).setupCost
                invoiceItem.usageAmount = invoiceItem.usageUnitPrice
                double taxAmount = (invoiceItem.usageAmount/100)* invoiceItem.taxPercent
                invoiceItem.taxAmount = taxAmount;     
                invoiceItem.totalAmount = invoiceItem.usageAmount + invoiceItem.taxAmount   
                invoiceItem.referenceItemName = "SetupCost"
                invoiceItem.referenceItemId = newVirtualMachine.referenceId

                return invoiceItem;
            }
            return null
        }
        return null
    }
    
    def addTemplateCost(invoice, newVirtualMachine) {
        def templateBillableItem = BillableItem.findByReferenceItemName("template")
        
        if(newVirtualMachine.template.cost != 0) {
            
            if(templateBillableItem.enabled == true || templateBillableItem.enabled == "true") {
                def invoiceItem = new InvoiceItem()
                invoiceItem.billableItem = templateBillableItem
                invoiceItem.invoice = invoice
                invoiceItem.zone = newVirtualMachine.zone
                invoiceItem.taxPercent = templateBillableItem.tax.percentage
                invoiceItem.referencePlanId = newVirtualMachine.referenceId
                invoiceItem.usageUnits = 1.0
                invoiceItem.usageUnitPrice = newVirtualMachine.template.cost;
                invoiceItem.usageAmount = invoiceItem.usageUnitPrice
                double taxAmount = (invoiceItem.usageAmount/100)* invoiceItem.taxPercent
                invoiceItem.taxAmount = taxAmount;     
                invoiceItem.totalAmount = invoiceItem.usageAmount + invoiceItem.taxAmount   
                invoiceItem.referenceItemName = "Template"
                invoiceItem.referenceItemId = newVirtualMachine.template.templateReferenceId;

                return invoiceItem;
            }
            
            return null;
        }
    }
        
    def start(String virtualMachineId) {   
        try { 
            licenseValidationService.authorizeAction(FogPanelService.VM_START)
            VirtualMachine virtualMachinestat = VirtualMachine.findByReferenceId(virtualMachineId);
            if(virtualMachinestat.owner.status.name() != "ACTIVE") {
                ArrayList<ArrayList<String>> vmJob = new ArrayList<ArrayList<String>>();            
                HashMap<String,String> item = new HashMap<String,String>(); 
                item.put("result", GeneralConstants.RESULT_FAILURE);
                item.put("message", "Account is not in Active state!cannot start instance");
                vmJob.add(item);
                return vmJob;
            }
            def response =  virtualMachineServer().startVirtualMachine(virtualMachineId);
            def jobResponse = virtualMachineServer().virtualMachineJobResult(response.jobId);   
            if(jobResponse.asychronousJobStatus == "2") {
                return [jobResponse.errorText]
            } 
            VirtualMachine virtualMachine = VirtualMachine.findByReferenceId(virtualMachineId);
            virtualMachine.job = response.jobId;
            virtualMachine.save(flush: true); 
            if (virtualMachine.hasErrors()) {
                throw new ValidationException(virtualMachine.errors.allErrors);
            } 
            ArrayList<ArrayList<String>> vmJob = new ArrayList<ArrayList<String>>();            
            HashMap<String,String> item = new HashMap<String,String>(); 
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            item.put("jobId", response.jobId);
            vmJob.add(item);
            log.info("Initiated Start VM for vm id: ${virtualMachine.referenceId}, and returned job id: ${virtualMachine.job}") 
            return vmJob;
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
    
    def stop(String virtualMachineId, String forced) { 
        try {
            
            licenseValidationService.authorizeAction(FogPanelService.VM_STOP)
            
            HashMap<String,String> optional = new HashMap<String,String>();
            optional.put("forced", forced);
            def response =  virtualMachineServer().stopVirtualMachine(virtualMachineId, optional)
            def jobResponse = virtualMachineServer().virtualMachineJobResult(response.jobId);   
            if(jobResponse.asychronousJobStatus == "2") {
                return [jobResponse.errorText]
            }
            VirtualMachine virtualMachine = VirtualMachine.findByReferenceId(virtualMachineId);
            virtualMachine.job = response.jobId;
            virtualMachine.save(flush: true); 
            if (virtualMachine.hasErrors()) {
                throw new ValidationException(virtualMachine.errors.allErrors);
            }            
            ArrayList<ArrayList<String>> vmJob = new ArrayList<ArrayList<String>>();            
            HashMap<String,String> item = new HashMap<String,String>(); 
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            item.put("jobId", response.jobId);
            vmJob.add(item);
            log.info("Initiated Stop VM for vm id: ${virtualMachineId}, and returned job id: ${virtualMachine.job}") 
            return vmJob;
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }   
    } 
    
    def console(String virtualMachineId) {   
        try {            
            licenseValidationService.authorizeAction(FogPanelService.VM_VIEW_CONSOLE)
            VirtualMachine virtualMachine = VirtualMachine.findByReferenceId(virtualMachineId);
            def ssoURL = ApplicationHolder.getApplication().config.cloudstack.singleSignOnUrl;            
            
            def consoleURL = ssoURL+"/console?cmd=access&vm=" + virtualMachine.referenceId
            ArrayList<ArrayList<String>> consoleUrl = new ArrayList<ArrayList<String>>()
            HashMap<String,String> instanceUrl = new HashMap<String,String>(); 
            instanceUrl.put("url", consoleURL.toString());
            consoleUrl.add(instanceUrl);
            log.info("console opened for vm: ${virtualMachine.referenceId}") 
            return consoleUrl;
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
    
    def reboot(String virtualMachineId) {  
        try{
            licenseValidationService.authorizeAction(FogPanelService.VM_REBOOT)
            def response =  virtualMachineServer().rebootVirtualMachine(virtualMachineId)
            def jobResponse = virtualMachineServer().virtualMachineJobResult(response.jobId);   
            if(jobResponse.asychronousJobStatus == "2") {
                return [jobResponse.errorText]
            }
            VirtualMachine virtualMachine = VirtualMachine.findByReferenceId(virtualMachineId);
            virtualMachine.job = response.jobId;
            virtualMachine.save(flush: true); 
            if (virtualMachine.hasErrors()) {
                throw new ValidationException(virtualMachine.errors.allErrors);
            } 
            ArrayList<ArrayList<String>> vmJob = new ArrayList<ArrayList<String>>();            
            HashMap<String,String> item = new HashMap<String,String>(); 
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            item.put("jobId", response.jobId);
            vmJob.add(item);
            log.info("Initiated reboot VM for vm: ${virtualMachine.referenceId}, and returned job id: ${virtualMachine.job}") 
            return vmJob;
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }    
    
    def restoreInstance(String virtualMachineId) {  
        try{
            
            licenseValidationService.authorizeAction(FogPanelService.VM_RESTORT)
            
            ArrayList<ArrayList<String>> vmJob = new ArrayList<ArrayList<String>>();
            HashMap<String,String> item = new HashMap<String,String>(); 
            VirtualMachine virtualMachine = VirtualMachine.findByReferenceId(virtualMachineId);
            def response =  virtualMachineServer().recoverVirtualMachine(virtualMachineId);
           
            for(Iterator<VirtualMachineResponse> iter = response.virtualMachines.iterator(); iter.hasNext();) {
                def data = iter.next();
                item.put("result", GeneralConstants.RESULT_SUCCESS);
                item.put("status", data.virtualMachineState);
                item.put("referenceId", virtualMachine.referenceId);
                
                vmJob.add(item);
                log.info("VM restored for vm: ${virtualMachine.referenceId}") 
            }
            //            def jobResponse = virtualMachineServer().virtualMachineJobResult(response.jobId);   
            //            if(jobResponse.asychronousJobStatus == "2") {
            //                return [jobResponse.errorText]
            //            } 
            //            virtualMachine.deleted = false;
            //            def volume = Volume.findWhere(virtualMachine:virtualMachine, type:"ROOT");
            //            if(volume) {
            //                volume.deleted = false;
            //                volume.save(flush: true); 
            //            }                        
            //            virtualMachine.job = response.jobId;
            //            virtualMachine.save(flush: true); 
            //            if (virtualMachine.hasErrors()) {
            //                throw new ValidationException(virtualMachine.errors.allErrors);
            //            } 
            //                     
            return vmJob;
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    } 
    
    
    
    
    def delete(String requestBody) {     
        try {
            
            licenseValidationService.authorizeAction(FogPanelService.VM_DELETE)
            
            // convert string to json object
            def requestData = JSON.parse(requestBody)      
                       
            def response =  virtualMachineServer().destroyVirtualMachine(requestData.referenceId);                      
            def jobResponse = virtualMachineServer().virtualMachineJobResult(response.jobId);   
            if(jobResponse.asychronousJobStatus == "2") {
                return [jobResponse.errorText]
            }    
            VirtualMachine virtualMachine = VirtualMachine.findByReferenceId(requestData.referenceId);
            virtualMachine.job = response.jobId;
            virtualMachine.save(flush: true); 
            if (virtualMachine.hasErrors()) {
                throw new ValidationException(virtualMachine.errors.allErrors);
            } 
            ArrayList<ArrayList<String>> vmJob = new ArrayList<ArrayList<String>>();            
            HashMap<String,String> item = new HashMap<String,String>(); 
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            item.put("jobId", response.jobId);
            vmJob.add(item);
            log.info("VM Deleted for vm: ${virtualMachine.referenceId}, and returned job id: ${response.jobId}") 
            return vmJob;
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
    }
    
    def changeServiceOffering(String requestBody) {
        try {
            
            licenseValidationService.authorizeAction(FogPanelService.VM_CHANGE_PLAN)
            
            // convert string to json object
            def requestData = JSON.parse(requestBody)
            def account = springSecurityService.currentUser.account
            def discountList;
            def discountCriteria = Discount.createCriteria()
            DateFormat formater2 = new SimpleDateFormat("dd/MM/yyyy"); 
            Date curDate = Calendar.getInstance().getTime()
            def today = formater2.format(curDate)
            Date currentDate = formater2.parse(today);
            
            def invoice = Invoice.findWhere(account: account, status: InvoiceStatus.values()[6])
            
            VirtualMachine virtualMachine = VirtualMachine.findByReferenceId(requestData.virtualMachineReferenceId);
            
            def computingBillableItem
            if(virtualMachine.billingType == "monthly") { 
                computingBillableItem= BillableItem.get(13) 
            } else {
                computingBillableItem= BillableItem.get(1) 
            }
            def user = springSecurityService.currentUser
            def language = "";
            if(user.account.preferredLanguage == "" || user.account.preferredLanguage == "null" || user.account.preferredLanguage == null) {
                Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
                language = defaultLanguage.value
            } else {
                language = user.account.preferredLanguage
            }
            
            def computingOffer = ComputingOffer.findByOfferReferenceId(requestData.computingOfferReferenceId)
            def oldComputeOffer = virtualMachine.computingOffer
            ArrayList<ArrayList<String>> resultList = new ArrayList<ArrayList<String>>();            
            HashMap<String,String> item = new HashMap<String,String>(); 
            if (virtualMachine.state == GeneralConstants.VM_RUNNING_STATE || virtualMachine.state == GeneralConstants.VM_STOPPING_STATE) {
                item.put("result", GeneralConstants.RESULT_FAILURE);
                item.put("message", messageSource.getMessage('user.vm.error.stopVM', null, new Locale(language)));
                resultList.add(item);
                return resultList;
            } else if(virtualMachine.computingOffer == computingOffer) {
                item.put("result", GeneralConstants.RESULT_FAILURE);
                item.put("message", messageSource.getMessage('user.vm.error.alreadyHasPlan', null, new Locale(language)) + "(" + computingOffer.name + ")");
                resultList.add(item);
                return resultList;
            }
            def response =  virtualMachineServer().changeServiceForVirtualMachine(requestData.virtualMachineReferenceId, requestData.computingOfferReferenceId);
            virtualMachine.computingOffer = ComputingOffer.findByOfferReferenceId(response.serviceOfferingId);
            virtualMachine.cluster = virtualMachine.computingOffer.cluster;
            virtualMachine.upgradedDate = curDate;
            virtualMachine.save(flush: true); 
            if (virtualMachine.hasErrors()) {
                throw new ValidationException(virtualMachine.errors.allErrors);
            }
            if(computingBillableItem.enabled == true || computingBillableItem.enabled == "true") {
                def invoiceItem = new InvoiceItem()
                invoiceItem.billableItem = computingBillableItem
                invoiceItem.invoice = invoice
                invoiceItem.zone = virtualMachine.zone
                invoiceItem.taxPercent = computingBillableItem.tax.percentage
                invoiceItem.referencePlanId = virtualMachine.computingOffer.offerReferenceId
                if(computingBillableItem.discountable == true || computingBillableItem.discountable == "true") {
                    discountList = discountCriteria.list {
                        eq("subType", "CREATE_VM")
                        and{
                            le("startDate", currentDate) and { ge("endDate", currentDate)} 
                            and{
                                eq("deleted", false)  
                            }
                        }
                    }
                    if(discountList.size() == 0) {

                    } else {
                        for(int i=0; i < discountList.size(); i++) {
                            def discount = discountList[i]; 
                            if(discount.isAll == true || discount.isAll == "true") {
                                invoiceItem.discount = discount;
                                invoiceItem.discountPercent = discount.value;
                                break
                            } else {
                                if(discount.isAllPlan == true || discount.isAllPlan == "true") {
                                    for(Iterator l = discount.accounts.iterator();l.hasNext();) { 
                                        def discountAccount = l.next(); 
                                        if(discountAccount == account) {
                                            invoiceItem.discount = discount;
                                            invoiceItem.discountPercent = discount.value;
                                            break
                                        }
                                    }
                                } else {
                                    if(discount.isAllUser == true || discount.isAllUser == "true") {
                                        for(Iterator m = discount.computingOffers.iterator();m.hasNext();) { 
                                            def discountComputerOffer = m.next();
                                            if(discountComputerOffer == virtualMachine.computingOffer) {
                                                invoiceItem.discount = discount;
                                                invoiceItem.discountPercent = discount.value;
                                                break 
                                            }
                                        }                      
                                    } 
                                }
                            }                   

                        }
                    }
                }
                if(virtualMachine.billingType == "monthly") {
                    
                    double monthlyAmount = ComputingOfferZoneCost.findWhere(computingOffer: virtualMachine.computingOffer).cost * 720.00   
                    invoiceItem.usageUnitPrice = Math.ceil(monthlyAmount * 100d) / 100d;                    
                    invoiceItem.usageUnits = 1.0
                    invoiceItem.usageAmount = Math.ceil(monthlyAmount * 100d) / 100d;   
                    double taxAmount = (invoiceItem.usageAmount/100)* invoiceItem.taxPercent
                    invoiceItem.taxAmount = Math.ceil(taxAmount * 100d) / 100d;   

                    double disAmount = (invoiceItem.usageAmount/100)* invoiceItem.discountPercent
                    invoiceItem.discountAmount = Math.ceil(disAmount * 100d) / 100d;   

                    double totalAmt =  (invoiceItem.usageAmount + invoiceItem.taxAmount) - invoiceItem.discountAmount

                    invoiceItem.totalAmount = Math.ceil(totalAmt * 100d) / 100d;   
                    invoiceItem.referenceItemName = "VirtualMachine"
                    invoiceItem.referenceItemId = virtualMachine.referenceId
                    invoiceItem.save(flush: true);
                } else {
                    invoiceItem.usageUnitPrice = ComputingOfferZoneCost.findWhere(computingOffer: virtualMachine.computingOffer).cost
                    invoiceItem.referenceItemName = "VirtualMachine"
                    invoiceItem.referenceItemId = virtualMachine.referenceId
                    invoiceItem.save(flush: true); 
                }
            }
            def bandWidthInvoiceItem = addBandwidthCost(virtualMachine.referenceId, oldComputeOffer, invoice)
            if(bandWidthInvoiceItem == null || bandWidthInvoiceItem == "null") {
                            
            } else {
                bandWidthInvoiceItem.save(flush: true);
                if (!bandWidthInvoiceItem.save()) {
                    //                  bandWidthInvoiceItem.errors.allErrors.each { Console.print(it) }
                }
            }
            VirtualMachine oldVirtualMachine = VirtualMachine.findByReferenceId(requestData.virtualMachineReferenceId);
            if(account.accountType.name() == "RETAIL") {
                oldVirtualMachine.networkRead = 0.0
                oldVirtualMachine.networkWrite = 0.0
                oldVirtualMachine.save(flush: true);
            }
            item.put("plan", computingOffer.name);
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            item.put("offerReferenceId", computingOffer.offerReferenceId)
            resultList.add(item);
            log.info("Computing offer: ${computingOffer.offerReferenceId} plan change for VM : ${requestData.virtualMachineReferenceId}") 
            return resultList;
           
        }catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
    }        
    def getPassword(String virtualMachineId) {  
        try{
            VirtualMachine virtualMachine = VirtualMachine.findByReferenceId(virtualMachineId);
            ArrayList<ArrayList<String>> virtualMachineResult = new ArrayList<ArrayList<String>>();       
            HashMap<String,String> item = new HashMap<String,String>(); 
                        
            def password
            String privateKeyString = virtualMachine.sshKey.privatekey
            def response = virtualMachineServer().getVMPassword(virtualMachineId)
            virtualMachine.password = response.encryptedPassword
            virtualMachine.save(flush: true);
            StringReader br = new StringReader(privateKeyString);
            Security.addProvider(new BouncyCastleProvider());
            KeyPair kp = (KeyPair) new PEMReader(br).readObject();

            Key key = kp.getPrivate(); // new SecretKeySpec(byteKey, "RSA");

            String encryptedPassword = virtualMachine.password;
            //System.out.println(new String(Base64.decode(encryptedPassword)));
            Cipher decryptor = Cipher.getInstance("RSA");
            //            decryptor.getMaxAllowedKeyLength("1024");
            decryptor.init(Cipher.DECRYPT_MODE, key);
            byte[] decryptedPasswordBytes = decryptor.doFinal(Base64.decode(encryptedPassword));
            password = new String(decryptedPasswordBytes);
            
            item.put("result", "OK"); 
            item.put("password", password); 
            virtualMachineResult.add(item);
            return virtualMachineResult;
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }  
    }  
    
    
    def resetPassword(String requestBody) {  
        try{ 
            
            licenseValidationService.authorizeAction(FogPanelService.VM_RESET_PASSWORD)
            
            // convert string to json object
            def requestData = JSON.parse(requestBody)     
            def user = springSecurityService.currentUser
            def language = "";
            if(user.account.preferredLanguage == "" || user.account.preferredLanguage == "null" || user.account.preferredLanguage == null) {
                Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
                language = defaultLanguage.value
            } else {
                language = user.account.preferredLanguage
            }
            VirtualMachine virtualMachine = VirtualMachine.findByReferenceId(requestData.virtualMachineId);
            ArrayList<ArrayList<String>> virtualMachineResult = new ArrayList<ArrayList<String>>();            
            HashMap<String,String> item = new HashMap<String,String>(); 
            if(virtualMachine.template.passwordEnabled == true || virtualMachine.template.passwordEnabled == "true") {
                if (virtualMachine.state == GeneralConstants.VM_STOPPED_STATE) {
                    def response =  virtualMachineServer().resetPasswordForVirtualMachine(requestData.virtualMachineId);
                    virtualMachine.job = response.jobId;
                    virtualMachine.save(flush: true);
                    item.put("result", GeneralConstants.RESULT_SUCCESS);
                    item.put("jobId", response.jobId);
                    virtualMachineResult.add(item);
                    log.info("Password reset success for vm:${virtualMachine.referenceId}, and returned job") 
                    return virtualMachineResult;
                } else {
                    item.put("result", GeneralConstants.RESULT_FAILURE);
                    item.put("message", messageSource.getMessage('user.vm.error.resetPassword', null, new Locale(language)));
                    virtualMachineResult.add(item);
                    log.info("Password reset pending for vm:${virtualMachine.referenceId}, and returned job") 
                    return virtualMachineResult;
                }
            } else {
                item.put("result", GeneralConstants.RESULT_FAILURE);
                item.put("message", messageSource.getMessage('user.vm.error.passwordNotEnabled', null, new Locale(language)));
                virtualMachineResult.add(item);
                log.info("Password failed success for vm:${virtualMachine.referenceId}") 
                return virtualMachineResult;
            }
            
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }  
    }  
    
    
    def count(accountId, status, zoneReferenceId) { 
        try {
           
            def user = springSecurityService.currentUser
            def role = springSecurityService.getPrincipal().getAuthorities()  
            def runningVms
            def stoppedVms
            def destroyedVms
            def totalVms
            def totalVolumes;
            def detachedStorage;
            def attachedStorage;
            def totalSnapshots = 0;
            def instances;
            ArrayList countList = new ArrayList(); 
            HashMap countItem = new HashMap();  
            
            if((accountId == "null" || accountId == null) && (status == "null" || status == null) && (zoneReferenceId == "null" || zoneReferenceId == null)) {   
                if(role.iterator().next() == "ROLE_ADMIN" ) {                    
                    runningVms = VirtualMachine.findAllWhere(deleted: false, state:"Running"); 
                    stoppedVms =  VirtualMachine.findAllWhere(deleted: false, state:"Stopped"); 
                    destroyedVms =  VirtualMachine.findAllWhere(deleted: false, state:"Destroyed"); 
                    totalVms =  VirtualMachine.findAllWhere(deleted: false); 
                    totalVolumes = Volume.findAllWhere(deleted: false, type:"DATADISK").size();
                    detachedStorage = Volume.findAllWhere(deleted: false, virtualMachine: null).size();
                    attachedStorage = totalVolumes - detachedStorage;
                    totalSnapshots = Snapshot.findAllWhere(deleted: false).size();
                } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {                    
                    runningVms = VirtualMachine.findAllWhere(deleted: false, user: user, state:"Running"); 
                    stoppedVms =  VirtualMachine.findAllWhere(deleted: false, user: user, state:"Stopped"); 
                    destroyedVms =  VirtualMachine.findAllWhere(deleted: false, user: user, state:"Destroyed"); 
                    totalVms =  VirtualMachine.findAllWhere(deleted: false, user: user); 
                    totalVolumes = Volume.findAllWhere(deleted: false, user: user, type:"DATADISK").size();
                    detachedStorage = Volume.findAllWhere(deleted: false, virtualMachine: null, user: user).size();
                    attachedStorage = totalVolumes - detachedStorage;
                    totalSnapshots = Snapshot.findAllWhere(deleted: false, user: user).size();
                }
            } else if ((accountId == "null" || accountId == null) && (status != "null" || status != null) && (zoneReferenceId == "null" || zoneReferenceId == null)) {
                if(role.iterator().next() == "ROLE_ADMIN" ) {                             
                    runningVms = VirtualMachine.findAllWhere(deleted: false, state:"Running"); 
                    stoppedVms =  VirtualMachine.findAllWhere(deleted: false, state:"Stopped"); 
                    destroyedVms =  VirtualMachine.findAllWhere(deleted: false, state:"Destroyed"); 
                    totalVms =  VirtualMachine.findAllWhere(deleted: false); 
                    
                    totalVolumes = Volume.findAllWhere(deleted: false, type:"DATADISK").size();
                    detachedStorage = Volume.findAllWhere(deleted: false, virtualMachine: null).size();
                    attachedStorage = totalVolumes - detachedStorage;
                    totalSnapshots = Snapshot.findAllWhere(deleted: false).size();
                    instances = VirtualMachine.findAllWhere(deleted: false, state:status);           
                    
                } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {                    
                    
                    runningVms = VirtualMachine.findAllWhere(deleted: false, state:"Running", user: user); 
                    stoppedVms =  VirtualMachine.findAllWhere(deleted: false, state:"Stopped", user: user); 
                    destroyedVms =  VirtualMachine.findAllWhere(deleted: false, user: user, state:"Destroyed"); 
                    totalVms =  VirtualMachine.findAllWhere(deleted: false, user: user); 
                    
                    totalVolumes = 0;
                    detachedStorage = 0;
                    attachedStorage = 0;
                    totalSnapshots = 0;
                    instances = VirtualMachine.findAllWhere(deleted: false, state:status, user: user);                     
                } 
                
                ArrayList<ArrayList<String>> instancesList = new ArrayList<ArrayList<String>>();            
                for(int i=0; i < instances.size(); i++) { 
                    def item = instances[i]; 
                    HashMap<String,String> instancesItem = new HashMap<String,String>();  
                    def vpc = item.vpc?item.vpc.name:"-";  
                    instancesItem.put("vpc", vpc);  
                    instancesItem.put("referenceId", item.referenceId);
                    instancesItem.put("state", item.state);
                    instancesItem.put("name", item.displayName);
                    instancesItem.put("instanceName", item.name);
                    instancesItem.put("computingOffer", item.computingOffer.name);
                    instancesItem.put("hypervisor", item.hypervisor);
                    instancesItem.put("template", item.template.name);
                    instancesItem.put("owner", item.owner.userName);
                    instancesItem.put("user", item.owner.userName);
                    instancesItem.put("zoneName", item.zone.aliasName);
                    instancesItem.put("osType", item.template.osType.name);
                    instancesItem.put("osCategory", item.template.osCategory.name);
                    instancesItem.put("securityGroupReferenceId", item.securityGroupReferenceId);
                    instancesItem.put("ip", item.nicIp);
                    instancesList.add(instancesItem);
                }
                countItem.put("instanceData", instancesList);
            } else if ((zoneReferenceId != "null" || zoneReferenceId != null) && (accountId == "null" || accountId == null) && (status == "null" || status == null)) {
               
                if(role.iterator().next() == "ROLE_ADMIN" ) { 
                    runningVms = VirtualMachine.findAllWhere(deleted: false, state:"Running", zone: Zone.findByReferenceZoneId(zoneReferenceId)); 
                    stoppedVms =  VirtualMachine.findAllWhere(deleted: false, state:"Stopped", zone: Zone.findByReferenceZoneId(zoneReferenceId)); 
                    destroyedVms =  VirtualMachine.findAllWhere(deleted: false, zone: Zone.findByReferenceZoneId(zoneReferenceId), state:"Destroyed"); 
                    totalVms =  VirtualMachine.findAllWhere(deleted: false, zone: Zone.findByReferenceZoneId(zoneReferenceId)); 
                    
                    totalVolumes = Volume.findAllWhere(deleted: false, type:"DATADISK").size();
                    detachedStorage = Volume.findAllWhere(deleted: false, virtualMachine: null).size();
                    attachedStorage = totalVolumes - detachedStorage;
                    totalSnapshots = Snapshot.findAllWhere(deleted: false).size();
                    instances = VirtualMachine.findAllWhere(deleted: false,zone: Zone.findByReferenceZoneId(zoneReferenceId));                       
                    
                } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {
                    runningVms = VirtualMachine.findAllWhere(deleted: false, state:"Running", zone: Zone.findByReferenceZoneId(zoneReferenceId), user: user); 
                    stoppedVms =  VirtualMachine.findAllWhere(deleted: false, state:"Stopped", zone: Zone.findByReferenceZoneId(zoneReferenceId), user: user); 
                    destroyedVms =  VirtualMachine.findAllWhere(deleted: false, zone: Zone.findByReferenceZoneId(zoneReferenceId), state:"Destroyed", user: user); 
                    totalVms =  VirtualMachine.findAllWhere(deleted: false, zone: Zone.findByReferenceZoneId(zoneReferenceId), user: user); 
                    
                    totalVolumes = 0
                    detachedStorage = 0
                    attachedStorage = 0
                    totalSnapshots = 0
                    instances = VirtualMachine.findAllWhere(deleted: false,zone: Zone.findByReferenceZoneId(zoneReferenceId), user: user);   
                }
                
                ArrayList<ArrayList<String>> instancesList = new ArrayList<ArrayList<String>>();            
                for(int i=0; i < instances.size(); i++) { 
                    def item = instances[i]; 
                    def hasVMSnapshot = "false";
                    def VMSnapList = VMSnapshot.findAllWhere(virtualMachine:item, deleted: false);
                    def miscelCost = MiscellaneousOfferZoneCost.findWhere(zone: Zone.findByReferenceZoneId(item.zone.referenceZoneId), miscellaneousOffer: MiscellaneousOffer.get(2));
                    
                    if(VMSnapList) {
                        hasVMSnapshot = "true";
                    }
                    HashMap<String,String> instancesItem = new HashMap<String,String>();   
                    if(miscelCost) {
                        instancesItem.put("ipMiscelCost", miscelCost.cost.toString());
                    } else {
                        instancesItem.put("ipMiscelCost", "");
                    }
                    def vpc = item.vpc?item.vpc.name:"-";  
                    instancesItem.put("vpc", vpc);  
                    instancesItem.put("referenceId", item.referenceId);
                    instancesItem.put("state", item.state);
                    instancesItem.put("id", item.id);                    
                    instancesItem.put("name", item.displayName);
                    instancesItem.put("instanceName", item.name);
                    instancesItem.put("computingOffer", item.computingOffer.name);
                    instancesItem.put("hypervisor", item.hypervisor);
                    instancesItem.put("template", item.template.name);
                    instancesItem.put("owner", item.owner.userName);
                    instancesItem.put("user", item.owner.userName);
                    instancesItem.put("zoneName", item.zone.aliasName);
                    instancesItem.put("osType", item.template.osType.name);
                    instancesItem.put("osCategory", item.template.osCategory.name);
                    instancesItem.put("securityGroupReferenceId", item.securityGroupReferenceId);
                    instancesItem.put("firstName", item.owner.firstName);
                    instancesItem.put("computingOfferClusterId", item.computingOffer.cluster.clusterReferenceId)
                    instancesItem.put("computingOfferTags", item.computingOffer.tags);
                    instancesItem.put("computingOfferType", item.computingOffer.storageType); 
                    instancesItem.put("hasVMSnapshot", hasVMSnapshot);                     
                    instancesItem.put("ip", item.nicIp);                                        
                    instancesList.add(instancesItem);
                }
                countItem.put("instanceData", instancesList); 
            
            }  else if((status != "null" || status != null) && (zoneReferenceId != "null" || zoneReferenceId != null) && (accountId == "null" || accountId == null)) {
                if(role.iterator().next() == "ROLE_ADMIN" ) { 
                    runningVms = VirtualMachine.findAllWhere(deleted: false, state:"Running", zone: Zone.findByReferenceZoneId(zoneReferenceId)); 
                    stoppedVms =  VirtualMachine.findAllWhere(deleted: false, state:"Stopped", zone: Zone.findByReferenceZoneId(zoneReferenceId)); 
                    destroyedVms =  VirtualMachine.findAllWhere(deleted: false, zone: Zone.findByReferenceZoneId(zoneReferenceId), state:"Destroyed"); 
                    totalVms =  VirtualMachine.findAllWhere(deleted: false); 
                
                    totalVolumes = Volume.findAllWhere(deleted: false, type:"DATADISK").size();
                    detachedStorage = Volume.findAllWhere(deleted: false, virtualMachine: null).size();
                    attachedStorage = totalVolumes - detachedStorage;
                    totalSnapshots = Snapshot.findAllWhere(deleted: false).size();
                    instances = VirtualMachine.findAllWhere(deleted: false, state:status, zone: Zone.findByReferenceZoneId(zoneReferenceId));
                } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") { 
                    runningVms = VirtualMachine.findAllWhere(deleted: false, state:"Running", zone: Zone.findByReferenceZoneId(zoneReferenceId), user: user, vpc: null); 
                    stoppedVms =  VirtualMachine.findAllWhere(deleted: false, state:"Stopped", zone: Zone.findByReferenceZoneId(zoneReferenceId), user: user, vpc: null); 
                    destroyedVms =  VirtualMachine.findAllWhere(deleted: false, zone: Zone.findByReferenceZoneId(zoneReferenceId), state:"Destroyed", user: user); 
                    totalVms =  VirtualMachine.findAllWhere(deleted: false, user: user, zone: Zone.findByReferenceZoneId(zoneReferenceId)); 
                
                    totalVolumes = 0;
                    detachedStorage = 0;
                    attachedStorage = 0;
                    totalSnapshots = 0;
                    instances = VirtualMachine.findAllWhere(deleted: false, state:status, zone: Zone.findByReferenceZoneId(zoneReferenceId), user: user);
                }
                ArrayList<ArrayList<String>> instancesList = new ArrayList<ArrayList<String>>();            
                for(int i=0; i < instances.size(); i++) { 
                    def item = instances[i]; 
                    HashMap<String,String> instancesItem = new HashMap<String,String>();  
                    def vpc = item.vpc?item.vpc.name:"-";  
                    instancesItem.put("vpc", vpc);  
                    instancesItem.put("referenceId", item.referenceId);
                    instancesItem.put("state", item.state);
                    instancesItem.put("name", item.displayName);
                    instancesItem.put("instanceName", item.name);
                    instancesItem.put("computingOffer", item.computingOffer.name);
                    instancesItem.put("hypervisor", item.hypervisor);
                    instancesItem.put("template", item.template.name);
                    instancesItem.put("owner", item.owner.userName);
                    instancesItem.put("user", item.owner.userName);
                    instancesItem.put("zoneName", item.zone.aliasName);
                    instancesItem.put("osType", item.template.osType.name);
                    instancesItem.put("osCategory", item.template.osCategory.name);
                    instancesItem.put("securityGroupReferenceId", item.securityGroupReferenceId);                            
                    instancesItem.put("ip", item.nicIp);
                    instancesList.add(instancesItem);
                }
                countItem.put("instanceData", instancesList);    
            } else if ((accountId != "null" || accountId != null) && (status == "null" || status == null) && (zoneReferenceId == "null" || zoneReferenceId == null)) {
                if(role.iterator().next() == "ROLE_ADMIN" ) {     
                    def givenAccount = Account.get(accountId)
                    runningVms = VirtualMachine.findAllWhere(deleted: false, owner: givenAccount, state:"Running"); 
                    stoppedVms =  VirtualMachine.findAllWhere(deleted: false, owner: givenAccount, state:"Stopped"); 
                    destroyedVms =  VirtualMachine.findAllWhere(deleted: false, state:"Destroyed", owner: givenAccount,); 
                    totalVms =  VirtualMachine.findAllWhere(deleted: false, owner: givenAccount); 
                    
                    totalVolumes = Volume.findAllWhere(deleted: false, owner: givenAccount, type:"DATADISK").size();
                    detachedStorage = Volume.findAllWhere(deleted: false, owner: givenAccount, virtualMachine: null).size();
                    attachedStorage = totalVolumes - detachedStorage;
                    def snapShotList = Snapshot.findAllWhere(deleted: false)
                    for(def snapShot : snapShotList) {
                        if(snapShot.user.account == givenAccount) {
                            totalSnapshots += 1;
                        }
                    }
                } 
            }                           
            countItem.put("runningVms", runningVms.size());
            countItem.put("stoppedVms", stoppedVms.size());
            countItem.put("destroyedVms", destroyedVms.size());
            countItem.put("totalVms", totalVms.size());
            countItem.put("totalStorage", totalVolumes);
            countItem.put("detachedStorage", detachedStorage);
            countItem.put("attachedStorage", attachedStorage);
            countItem.put("totalSnapshots", totalSnapshots);   
           
            countList.add(countItem);
                                  
            return countList;
            
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
    }
    
    def userResourceCount(accountId, status, zoneReferenceId) {
        
        try {
           
            def user = springSecurityService.currentUser
            def role = springSecurityService.getPrincipal().getAuthorities() 
            def currency = ApplicationHolder.getApplication().config.billing.defaultCurrency
            def account;
            
            if(accountId) {
               account = Account.get(accountId)
            } else {
                account = user.account;
            }
            
            def invoice = Invoice.findWhere(account: account, status: InvoiceStatus.values()[6])
          
            ArrayList countList = new ArrayList(); 
            HashMap countItem = new HashMap();  
                                    
            def totalRunningVm =  VirtualMachine.withCriteria {
                eq('deleted', false)
                eq('state', "Running")
                if(zoneReferenceId && zoneReferenceId != "All") {
                    eq('zone', Zone.findByReferenceZoneId(zoneReferenceId))
                }
                if(accountId) {
                    eq('owner', Account.get(accountId))
                } else {
                    eq('user', user)
                }
            }
            
            def totalRunningVmCost = InvoiceItem.withCriteria {
                eq("invoice", invoice)
                if(zoneReferenceId && zoneReferenceId != "All") {
                    eq('zone', Zone.findByReferenceZoneId(zoneReferenceId))
                }
                or {
                    eq("billableItem", BillableItem.get(1))  
                    or {
                        eq("billableItem", BillableItem.get(13))
                    }
                }
                projections {
                     sum("totalAmount")
                }
            }
                        
            def totalVolumes = Volume.withCriteria {
                eq('deleted', false)
                eq('type', "DATADISK")
                if(zoneReferenceId && zoneReferenceId != "All") {
                    eq('zone', Zone.findByReferenceZoneId(zoneReferenceId))
                }
                if(accountId) {
                    eq('owner', Account.get(accountId))
                } else {
                    eq('user', user)
                }
            }
            
            def totalVolumeCost = InvoiceItem.withCriteria {
                eq("invoice", invoice)
                if(zoneReferenceId && zoneReferenceId != "All") {
                    eq('zone', Zone.findByReferenceZoneId(zoneReferenceId))
                } 
                or {
                    eq("billableItem", BillableItem.get(2))  
                    or {
                        eq("billableItem", BillableItem.get(14))
                    }
                }
                projections {
                     sum("totalAmount")
                 }
            }
            
            def totalNetwork = Network.withCriteria {
                eq('deleted', false)
                if(zoneReferenceId && zoneReferenceId != "All") {
                    eq('zone', Zone.findByReferenceZoneId(zoneReferenceId))
                }
                if(accountId) {
                    eq('account', Account.get(accountId))
                } else {
                    eq('account', user.account)
                }
                isNull("vpc") 
            }
            
            def totalVpc = VPC.withCriteria {
                eq('deleted', false)
                if(zoneReferenceId && zoneReferenceId != "All") {
                    eq('zone', Zone.findByReferenceZoneId(zoneReferenceId))
                }
                if(accountId) {
                    eq('account', Account.get(accountId))
                } else {
                    eq('account', user.account)
                }
            }
            
            def totalSnapshot = Snapshot.withCriteria {
                eq('deleted', false)
                if(zoneReferenceId && zoneReferenceId != "All") {
                    eq('zone', Zone.findByReferenceZoneId(zoneReferenceId))
                }
                if(accountId) {
                    eq('owner', Account.get(accountId))
                } else {
                    eq('user', user)
                }
            }
            
            def totalSnapCost = InvoiceItem.withCriteria {
                eq("invoice", invoice)
                eq("billableItem", BillableItem.get(3))
                if(zoneReferenceId && zoneReferenceId != "All") {
                    eq('zone', Zone.findByReferenceZoneId(zoneReferenceId))
                }
                projections {
                     sum("totalAmount")
                 }
            }
            
            def totalStoppedVm =  VirtualMachine.withCriteria {
                eq('deleted', false)
                eq('state', "Stopped")
                if(zoneReferenceId && zoneReferenceId != "All") {
                    eq('zone', Zone.findByReferenceZoneId(zoneReferenceId))
                }
                if(accountId) {
                    eq('owner', Account.get(accountId))
                } else {
                    eq('user', user)
                }
            }
            
            def totalDestroyedVm =  VirtualMachine.withCriteria {
                eq('deleted', false)
                eq('state', "Destroyed")
                if(zoneReferenceId && zoneReferenceId != "All") {
                    eq('zone', Zone.findByReferenceZoneId(zoneReferenceId))
                }
                if(accountId) {
                    eq('owner', Account.get(accountId))
                } else {
                    eq('user', user)
                }
            }
            
            
            
            ArrayList zoneCountList = new ArrayList(); 
            
            def zoneList = Zone.findAll()
            for(def zone: zoneList) {
                
                HashMap zoneCountItem = new HashMap();  
                zoneCountItem.put("zone", zone.aliasName)
                zoneCountItem.put("type", zone.networkType)
                                
                def runningVm =  VirtualMachine.withCriteria {
                    eq('deleted', false)
                    eq('state', "Running")
                    eq('zone', zone)
                    if(accountId) {
                        eq('owner', Account.get(accountId))
                    } else {
                        eq('user', user)
                    }
                }
                
                def snapCost = InvoiceItem.withCriteria {
                    eq("invoice", invoice)
                    eq("billableItem", BillableItem.get(3))
                    eq('zone', zone)
                    projections {
                            sum("totalAmount")
                    }
                }
                
                def volumeCost = InvoiceItem.withCriteria {
                    eq("invoice", invoice)
                    eq('zone', zone)
                    or {
                        eq("billableItem", BillableItem.get(2))  
                        or {
                            eq("billableItem", BillableItem.get(14))
                        }
                    }
                    projections {
                         sum("totalAmount")
                     }
                }
                
                def runningVmCost = InvoiceItem.withCriteria {
                    eq("invoice", invoice)
                    eq('zone', zone)
                    or {
                        eq("billableItem", BillableItem.get(1))  
                        or {
                            eq("billableItem", BillableItem.get(13))
                        }
                    }
                    projections {
                         sum("totalAmount")
                     }
                }
                
                
                def network = Network.withCriteria {
                    eq('deleted', false)
                    eq('zone', zone)
                    if(accountId) {
                        eq('account', Account.get(accountId))
                    } else {
                        eq('account', user.account)
                    }
                    isNull("vpc") 
                }
                
                def vpc = VPC.withCriteria {
                    eq('deleted', false)
                    eq('zone', zone)
                    if(accountId) {
                        eq('account', Account.get(accountId))
                    } else {
                        eq('account', user.account)
                    }
                }
            
                def stoppedVm =  VirtualMachine.withCriteria {
                    eq('deleted', false)
                    eq('state', "Stopped")
                    eq('zone', zone)
                    if(accountId) {
                        eq('owner', Account.get(accountId))
                    } else {
                        eq('user', user)
                    }
                }
                
                def destroyedVm =  VirtualMachine.withCriteria {
                    eq('deleted', false)
                    eq('state', "Destroyed")
                    eq('zone', zone)
                    if(accountId) {
                        eq('owner', Account.get(accountId))
                    } else {
                        eq('user', user)
                    }
                }
                
                def volumes = Volume.withCriteria {
                    eq('deleted', false)
                    eq('type', "DATADISK")
                    eq('zone', zone)
                    if(accountId) {
                        eq('owner', Account.get(accountId))
                    } else {
                        eq('user', user)
                    }
                }
                
                def snapshot = Snapshot.withCriteria {
                    eq('deleted', false)
                    eq('zone', zone)
                    if(accountId) {
                        eq('owner', Account.get(accountId))
                    } else {
                        eq('user', user)
                    }
                }
                if(runningVmCost[0] == "null" || runningVmCost[0] == null) {
                    zoneCountItem.put("runningVmCost", 0 )
                } else {
                    zoneCountItem.put("runningVmCost", Math.ceil(runningVmCost[0] * 100d) / 100d)
                }
                if(volumeCost[0] == "null" || volumeCost[0] == null) {
                    zoneCountItem.put("volumeCost", 0 )
                } else {
                    zoneCountItem.put("volumeCost", Math.ceil(volumeCost[0] * 100d) / 100d )
                }
                if(snapCost[0] == "null" || snapCost[0] == null) {
                    zoneCountItem.put("snapCost", 0 )
                } else {
                    zoneCountItem.put("snapCost", Math.ceil(snapCost[0] * 100d) / 100d)
                }
                zoneCountItem.put("runningVm", runningVm.size() + stoppedVm.size() + destroyedVm.size())
                zoneCountItem.put("stoppedVm", stoppedVm.size())
                zoneCountItem.put("storage", volumes.size())
                zoneCountItem.put("snapshot", snapshot.size())
                zoneCountItem.put("network", network.size())
                zoneCountItem.put("vpc", vpc.size())
                zoneCountItem.put("networkCost", 0 )
                 countItem.put("VpcCost", 0)
                
                def totalZoneBandwidth = 0;
                def totalZoneBandwidthCost = 0;
            
                def totalZoneBandCost = InvoiceItem.withCriteria {
                    eq("invoice", invoice)
                    eq("billableItem", BillableItem.get(5))
                    if(zoneReferenceId && zoneReferenceId != "All") {
                        eq('zone', Zone.findByReferenceZoneId(zoneReferenceId))
                    }
                    projections {
                         sum("totalAmount")
                         sum("usageUnits")
                    }
                }
            
                if(totalZoneBandCost[0][0] == "null" || totalZoneBandCost[0][0] == null) {
                    totalZoneBandwidthCost += 0;
                } else {
                    totalZoneBandwidthCost += totalZoneBandCost[0][0];
                }
                if(totalZoneBandCost[0][1] == "null" || totalZoneBandCost[0][1] == null) {
                    totalZoneBandwidth += 0
                } else {
                    totalZoneBandwidth += totalZoneBandCost[0][1]
                }

                def zoneVirtualMachineList = VirtualMachine.withCriteria {
                    eq('deleted', false)
                    eq('zone', zone)
                    if(accountId) {
                        eq('owner', Account.get(accountId))
                    } else {
                        eq('user', user)
                    } 
                }
                for(def zoneVirtualMachine : zoneVirtualMachineList) {

                    double planBandwidth = 1.0 * zoneVirtualMachine.computingOffer.bandWidth

                    def bandWidth = MiscellaneousOfferZoneCost.findWhere(miscellaneousOffer :MiscellaneousOffer.get(1), zone :zoneVirtualMachine.computingOffer.zone);
                    
                    def bandWidthCost;
                    
                    if(bandWidth) {
                        bandWidthCost = bandWidth.cost
                    } else {
                        bandWidthCost = 0
                    }
                    
                    def usageUnits = (zoneVirtualMachine.networkWrite / 1048576) - planBandwidth
                    def vmband = zoneVirtualMachine.networkWrite / 1048576
                    if(vmband > planBandwidth) {
                        double usageAmount = usageUnits * bandWidthCost                          
                        totalZoneBandwidthCost = totalZoneBandwidthCost + usageAmount
                        totalZoneBandwidth = totalZoneBandwidth + usageUnits
                    }
                }
                
                zoneCountItem.put("bandwidth", totalZoneBandwidth)
                zoneCountItem.put("bandwidthCost", totalZoneBandwidthCost )
                
                zoneCountList.add(zoneCountItem)
            }
              
            if(zoneReferenceId && zoneReferenceId != "All") {
                countItem.put("zoneName", Zone.findByReferenceZoneId(zoneReferenceId)?.aliasName)
            }
            countItem.put("zoneCount", zoneCountList)
            countItem.put("totalRunningVm", totalRunningVm.size() + totalStoppedVm.size() + totalDestroyedVm.size())
            if(totalRunningVmCost[0] == "null" || totalRunningVmCost[0] == null) {
                    countItem.put("totalRunningVmCost", 0 )
            } else {
                countItem.put("totalRunningVmCost", Math.ceil(totalRunningVmCost[0] * 100d) / 100d)
            }
            if(totalVolumeCost[0] == "null" || totalVolumeCost[0] == null) {
                countItem.put("totalVolumeCost", 0 )
            } else {
                countItem.put("totalVolumeCost", Math.ceil(totalVolumeCost[0] * 100d) / 100d)
            }
            if(totalSnapCost[0] == "null" || totalSnapCost[0] == null) {
                countItem.put("totalSnapCost", 0 )
            } else {
                countItem.put("totalSnapCost", Math.ceil(totalSnapCost[0] * 100d) / 100d)
            }
            countItem.put("currency", currency)
            countItem.put("totalNetworkCost", 0 )
            countItem.put("totalStoppedVm", totalStoppedVm.size())
            countItem.put("totalStorage", totalVolumes.size())
            countItem.put("totalSnapshot", totalSnapshot.size())
            countItem.put("totalNetwork", totalNetwork.size())
            countItem.put("totalVpc", totalVpc.size())
            countItem.put("totalVpcCost", 0)
                                    
            def totalBandwidth = 0;
            def totalBandwidthCost = 0;
            
            def totalBandCost = InvoiceItem.withCriteria {
                eq("invoice", invoice)
                eq("billableItem", BillableItem.get(5))
                if(zoneReferenceId && zoneReferenceId != "All") {
                    eq('zone', Zone.findByReferenceZoneId(zoneReferenceId))
                }
                projections {
                     sum("totalAmount")
                     sum("usageUnits")
                }
            }
            
            if(totalBandCost[0][0] == "null" || totalBandCost[0][0] == null) {
                totalBandwidthCost += 0;
            } else {
                totalBandwidthCost += totalBandCost[0][0];
            }
            if(totalBandCost[0][1] == "null" || totalBandCost[0][1] == null) {
                totalBandwidth += 0
            } else {
                totalBandwidth += totalBandCost[0][1]
            }
            
            def virtualMachineList = VirtualMachine.withCriteria {
                eq('deleted', false)
                if(zoneReferenceId && zoneReferenceId != "All") {
                    eq('zone', Zone.findByReferenceZoneId(zoneReferenceId))
                }
                if(accountId) {
                    eq('owner', Account.get(accountId))
                } else {
                    eq('user', user)
                } 
            }
            for(def virtualMachine : virtualMachineList) {
                
                double planBandwidth = 1.0 * virtualMachine.computingOffer.bandWidth

                def bandWidth = MiscellaneousOfferZoneCost.findWhere(miscellaneousOffer :MiscellaneousOffer.get(1), zone : virtualMachine.computingOffer.zone);
                    
                def bandWidthCost;

                if(bandWidth) {
                    bandWidthCost = bandWidth.cost
                } else {
                    bandWidthCost = 0
                }

                def usageUnits = (virtualMachine.networkWrite / 1048576) - planBandwidth
                def vmband = virtualMachine.networkWrite / 1048576
                if(vmband > planBandwidth) {
                    double usageAmount = usageUnits * bandWidthCost                          
                    totalBandwidthCost = totalBandwidthCost + usageAmount
                    totalBandwidth = totalBandwidth + usageUnits
                }
            }
            
            countItem.put("totalBandwidthCost", totalBandwidthCost )
            countItem.put("totalBandwidth", totalBandwidth)
            
            countList.add(countItem)
            
            return countList
            
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
    }
    
    def createVmJob(String requestBody) {
        try {
            
            // convert string to json object
            def jobResult = ""
            def requestData = JSON.parse(requestBody)
            ArrayList<ArrayList<String>> virtualMachineResult = new ArrayList<ArrayList<String>>();  
            def virtualMachine = VirtualMachine.findByJob(requestData.jobId);
            //            def jobResponse = virtualMachineServer().virtualMachineJobResult(requestData.jobId);    
            if(virtualMachine.state == "Starting") {
                jobResult = "Pending";
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.JOB_RESULT_PENDING);
                item.put("jobId", virtualMachine.job);
                virtualMachineResult.add(item);
            } else if(virtualMachine.state == "Running") {
                
                def diskName
                def diskSize
                def diskCost
                jobResult = "Success";
                if(virtualMachine.diskOffer == null || virtualMachine.diskOffer == 'null') {
                    diskName = "-"
                    diskSize = " "
                    diskCost = " "
                } else {
                    diskName = virtualMachine.diskOffer.name
                    diskSize = ", Size:" + virtualMachine.diskOffer.size +"GB,"
                    diskCost = " Cost:" +virtualMachine.diskOffer.diskOfferZoneCosts.cost + "per/GB "
                }
                HashMap<String,String> virtualMachineItem = new HashMap<String,String>(); 
                virtualMachineItem.put("jobResult", GeneralConstants.RESULT_SUCCESS);
                virtualMachineItem.put("password", virtualMachine.password);
                virtualMachineItem.put("referenceId",  virtualMachine.referenceId);
                virtualMachineItem.put("state", virtualMachine.state);
                virtualMachineItem.put("name", virtualMachine.displayName);
                virtualMachineItem.put("instanceName", virtualMachine.name);
                virtualMachineItem.put("ipAddress", virtualMachine.nicIp);
                virtualMachineItem.put("cpuNumber", virtualMachine.computingOffer.cpuNumber);
                virtualMachineItem.put("coreCpuSpeed", virtualMachine.computingOffer.coreCpuSpeed);
                virtualMachineItem.put("offerName", virtualMachine.computingOffer.name);
                
                virtualMachineItem.put("memory", virtualMachine.computingOffer.memory);
                virtualMachineItem.put("bandWidth", virtualMachine.computingOffer.bandWidth);
                virtualMachineItem.put("memory", virtualMachine.computingOffer.memory);
                virtualMachineItem.put("setupCost", virtualMachine.computingOffer.computingOfferZoneCosts.setupCost);
                virtualMachineItem.put("RunningCost", virtualMachine.computingOffer.computingOfferZoneCosts.cost);
                virtualMachineItem.put("nextBillingDate", virtualMachine.user.account.nextBillingData);
                virtualMachineItem.put("diskName", diskName);
                virtualMachineItem.put("diskSize", diskSize);
                virtualMachineItem.put("diskCost", diskCost);
                
                virtualMachineItem.put("computingOffer", virtualMachine.computingOffer.name);
                if(virtualMachine.iso == "null" || virtualMachine.iso == null) {
                    virtualMachineItem.put("isoName", "null");
                } else {
                    virtualMachineItem.put("isoName", virtualMachine.iso.name);
                    virtualMachineItem.put("isoId", virtualMachine.iso.isoReferenceId);
                }
                virtualMachineItem.put("password", virtualMachine.password);
                virtualMachineItem.put("hypervisor", virtualMachine.hypervisor);
                virtualMachineItem.put("template", virtualMachine.template.name);
                virtualMachineItem.put("templateSize", virtualMachine.template.size);
                virtualMachineItem.put("templateCost", virtualMachine.template.cost);
                
                virtualMachineItem.put("owner", virtualMachine.owner.userName);
                virtualMachineItem.put("user", virtualMachine.user.username);
                virtualMachineItem.put("zoneName", virtualMachine.zone.aliasName);
                virtualMachineItem.put("osType", virtualMachine.template.osType.name);
                virtualMachineItem.put("osCategory", virtualMachine.template.osCategory.name);
                virtualMachineResult.add(virtualMachineItem);
            } else if(virtualMachine.state == "Error") {
                jobResult = "Failed";
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.RESULT_FAILURE);
                item.put("message", "Error in creating vm! contact admin");
                def invoice = Invoice.findWhere(account: virtualMachine.owner, status: InvoiceStatus.values()[6])
                def comBillableItem = BillableItem.findByReferenceItemName("computingOffer")
                def setupBillableItem = BillableItem.findByReferenceItemName("SetupCost")
                def templateBillableItem = BillableItem.get(4)
                def vmInvoiceItem = InvoiceItem.findWhere(invoice: invoice, referenceItemId:virtualMachine.referenceId, referencePlanId:virtualMachine.computingOffer.offerReferenceId, billableItem: comBillableItem)
                if(vmInvoiceItem) {
                    vmInvoiceItem.delete(flush: true);
                }
                def setupInvoiceItem = InvoiceItem.findWhere(invoice: invoice, referenceItemId:virtualMachine.referenceId, referencePlanId:virtualMachine.computingOffer.offerReferenceId, billableItem: setupBillableItem)
                if(setupInvoiceItem) {
                    setupInvoiceItem.delete(flush: true);
                }
                def templateItem = InvoiceItem.findWhere(invoice: invoice, referenceItemId:virtualMachine.template.templateReferenceId, referencePlanId:virtualMachine.referenceId, billableItem: templateBillableItem)
                if(templateItem) {
                    templateItem.delete(flush: true);
                }
                virtualMachineResult.add(item); 
            }
            log.info("Create VM Job completed ${jobResult}, for vm : ${virtualMachine.referenceId}") 
            return virtualMachineResult; 
            
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
        
        
    }   
    
    def attachIsoJob(String requestBody) {
        
        try {            
            
            def requestData = JSON.parse(requestBody)
                        
            ArrayList<ArrayList<String>> jobResult = new ArrayList<ArrayList<String>>();  
            def jobResponse = asyncJobServer().queryAsyncJobResult(requestData.jobId)
            if(jobResponse.asychronousJobStatus == "0") {
                
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.JOB_RESULT_PENDING);
                jobResult.add(item);
                
                return jobResult
                
            } else if(jobResponse.asychronousJobStatus == "1") {
                HashMap<String,String> item = new HashMap<String,String>()
                
                def jobIsoResponse = virtualMachineServer().virtualMachineJobResult(requestData.jobId); 
                
                def virtualMachine = VirtualMachine.findByJob(requestData.jobId);
                
                Iso iso = Iso.findByIsoReferenceId(jobIsoResponse.isoId);
                if(iso) {
                    virtualMachine.iso = iso;  
                } else {
                    virtualMachine.iso = null;  
                }
                
                virtualMachine.save(flush: true);
                
                if(virtualMachine.iso == "null" || virtualMachine.iso == null) {
                    item.put("isoName", "null");
                } else {
                    item.put("isoName", virtualMachine.iso.name);
                    item.put("isoId", virtualMachine.iso.isoReferenceId);
                }
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
        
    def job(String requestBody) {
        try {            
            // convert string to json object
            def jobResult = "";
            def requestData = JSON.parse(requestBody)
            ArrayList<ArrayList<String>> virtualMachineResult = new ArrayList<ArrayList<String>>();  
            def virtualMachine = VirtualMachine.findByJob(requestData.jobId);
            def jobResponse = virtualMachineServer().virtualMachineJobResult(requestData.jobId);    
            if(virtualMachine.state == "Starting" || virtualMachine.state == "Stopping") {
                jobResult = GeneralConstants.JOB_RESULT_PENDING
                HashMap<String,String> item = new HashMap<String,String>()                
                item.put("jobResult", GeneralConstants.JOB_RESULT_PENDING);
                item.put("jobId", jobResponse.asychronousJobInstanceId);
                virtualMachineResult.add(item); 
            } else if(virtualMachine.state == "Running" || virtualMachine.state == "Stopped" || virtualMachine.state == "Destroyed") {
                
                Iso iso = Iso.findByIsoReferenceId(jobResponse.isoId);
                if(iso) {
                    virtualMachine.iso = iso;  
                } else {
                    virtualMachine.iso = null;  
                }
                
                virtualMachine.save(flush: true);
                jobResult = GeneralConstants.RESULT_SUCCESS
                HashMap<String,String> virtualMachineItem = new HashMap<String,String>(); 
                virtualMachineItem.put("jobResult", GeneralConstants.RESULT_SUCCESS);
                virtualMachineItem.put("password", virtualMachine.password);
                virtualMachineItem.put("referenceId",  virtualMachine.referenceId);
                virtualMachineItem.put("state", virtualMachine.state);
                virtualMachineItem.put("name", virtualMachine.displayName);
                virtualMachineItem.put("instanceName", virtualMachine.name);
                virtualMachineItem.put("computingOffer", virtualMachine.computingOffer.name);
                if(virtualMachine.iso == "null" || virtualMachine.iso == null) {
                    virtualMachineItem.put("isoName", "null");
                } else {
                    virtualMachineItem.put("isoName", virtualMachine.iso.name);
                    virtualMachineItem.put("isoId", virtualMachine.iso.isoReferenceId);
                }
                virtualMachineItem.put("password", virtualMachine.password);
                virtualMachineItem.put("hypervisor", virtualMachine.hypervisor);
                virtualMachineItem.put("template", virtualMachine.template.name);
                virtualMachineItem.put("owner", virtualMachine.owner.userName);
                virtualMachineItem.put("user", virtualMachine.user.username);
                virtualMachineItem.put("zoneName", virtualMachine.zone.aliasName);
                virtualMachineItem.put("osType", virtualMachine.template.osType.name);
                virtualMachineItem.put("osCategory", virtualMachine.template.osCategory.name);
                virtualMachineItem.put("vmId", virtualMachine.id);
                
                virtualMachineResult.add(virtualMachineItem);
            } else if(jobResponse.asychronousJobStatus == "2") {
                jobResult = GeneralConstants.RESULT_FAILURE
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.RESULT_FAILURE);
                item.put("message", jobResponse.errorText);
                virtualMachineResult.add(item); 
            }
            log.info("VM Job completed ${jobResult}, for vm : ${virtualMachine.referenceId}") 
            return virtualMachineResult;             
            
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
            
    }
    
    def sendVmMail(vm) {
        try {                      
            def user = vm.user
            //             def role = springSecurityService.getPrincipal().getAuthorities() 
            def applicationUrl = ApplicationHolder.getApplication().config.cloudstack.applicationUrl
            def finalMessage 
            ArrayList<ArrayList<String>> sendMailResultList = new ArrayList<ArrayList<String>>();   
            
            HashMap<String,String> optional = new HashMap<String,String>();
            optional.put("id", vm.referenceId);
            def response = virtualMachineServer().listVirtualMachines(optional);

            for(Iterator<VirtualMachineResponse> iter = response.virtualMachines.iterator(); iter.hasNext();) {               
                def data = iter.next();                
                for(Iterator<NetworkInterfaceCardResponse> networkInterfaceCardIter = data.networkInterfaceCards.iterator(); networkInterfaceCardIter.hasNext();) {
                    def networkInterfaceCardData = networkInterfaceCardIter.next();
                    if(vm) {                     
                        vm.nicIp = networkInterfaceCardData.ipAddress;                         
                    }                    
                } 
            }
            vm.save(flush: true);        
            
            HashMap<String,String> item = new HashMap<String,String>(); 
            if(user) {                                                                                                                  
                def passwordData = "xxxxxxxx"
                def ipAddress;   
                def diskName;
                def volumeSize;
                def volumeCost;
                if(vm.nicIp == 'null' || vm.nicIp == null) {                    
                    ipAddress = "-"
                } else {                      
                    ipAddress = vm.nicIp;
                }    
                 
                if((vm.diskOffer == null) || (vm.diskOffer == 'null')) { 
                    diskName = "-"
                    volumeSize = ""
                    volumeCost = ""
                } else {
                    diskName = vm.diskOffer.name;   
                    if(vm.billingType == "monthly") {

                        double monthlyAmount = vm.diskOffer.diskOfferZoneCosts.cost[0] * 720.00

                        double amt = Math.ceil(monthlyAmount * 100d) / 100d;   

                        volumeCost = amt.toString() + " per GB/month";
                    } else {
                        volumeCost = vm.diskOffer.diskOfferZoneCosts.cost[0].toString() + " per GB/Hr";
                    }
                    
                    if(vm.diskOffer.customized == true || vm.diskOffer.customized == "true") {
                        volumeSize = "";
                    } else {
                        volumeSize = vm.diskOffer.size.toString() + "GB";
                    }

                }  
                
                Date date = new Date()
                def time = new Timestamp(date.getTime())                
                // Content Area                                
                Map tempalteMap = notificationService.getDefaultMailTemplateMap()                
                tempalteMap.put("hostName", vm.name)
                tempalteMap.put("user", user)               
                tempalteMap.put("displayName", vm.displayName)
                tempalteMap.put("osType", vm.template.osType.name)
                tempalteMap.put("vmPassword", passwordData)
                tempalteMap.put("vmOsType", vm.template.osType.name)                                 
                tempalteMap.put("vmIp", ipAddress)
                tempalteMap.put("status",  vm.state)
                tempalteMap.put("vmOfferName", vm.computingOffer.name)
                tempalteMap.put("vmCpuCore", vm.computingOffer.cpuNumber.toString())                
                tempalteMap.put("vmCpuSpeed", vm.computingOffer.coreCpuSpeed.toString())
                tempalteMap.put("vmOfferMemory", vm.computingOffer.memory.toString())
                tempalteMap.put("vmBandwidth", vm.computingOffer.bandWidth.toString())
                tempalteMap.put("vmSetupCost", vm.computingOffer.computingOfferZoneCosts.setupCost[0].toString())
                tempalteMap.put("vmTemplateSize", vm.template.size.toString())
                tempalteMap.put("vmSetupCost", vm.computingOffer.computingOfferZoneCosts.setupCost[0].toString())
                tempalteMap.put("vmTemplateCost", vm.template.cost.toString())
                tempalteMap.put("vmDiskName", diskName)                
                tempalteMap.put("vmDiskSize", volumeSize)
                tempalteMap.put("vmDiskCost", volumeCost)
                tempalteMap.put("createdDate", time.toString())
                tempalteMap.put("vmZone", vm.zone.aliasName)
                if(vm.billingType == "monthly") { 
                    double monthlyAmount = vm.computingOffer.computingOfferZoneCosts.cost[0] * 720.00                            
                    double amt = Math.ceil(monthlyAmount * 100d) / 100d;                       
                    tempalteMap.put("vmRunningCost", amt.toString() + "per month")
                } else {
                    tempalteMap.put("vmRunningCost", vm.computingOffer.computingOfferZoneCosts.cost[0].toString() + " per Hr")                   
                }                                
                notificationService.send(user.username.toString(), "createVM.subject.ftl", tempalteMap, "createVM.ftl")                                

                item.put("Result",  GeneralConstants.RESULT_SUCCESS);
                sendMailResultList.add(item);                              
            } else {
                item.put("Result",  "No user found");
                sendMailResultList.add(item);
            }
            return sendMailResultList;            
        } catch(Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }   
    
    def resetInstance(String virtualMachineId) {
        try {            
            licenseValidationService.authorizeAction(FogPanelService.VM_RESTORT)            
            ArrayList<ArrayList<String>> vmResponse = new ArrayList<ArrayList<String>>();
            HashMap<String,String> item = new HashMap<String,String>(); 
            VirtualMachine virtualMachine = VirtualMachine.findByReferenceId(virtualMachineId);
            def response =  virtualMachineServer().restoreVirtualMachine(virtualMachineId);           
            item.put("jobResult", GeneralConstants.JOB_RESULT_PENDING);
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            item.put("jobId", response.jobId);
            item.put("vmId", virtualMachineId);   
            item.put("vmName", virtualMachine.displayName);               
            vmResponse.add(item);
            log.info("VM reset Job, for vm: ${virtualMachine.referenceId}")                      
            return vmResponse;
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
    def resetVMJob(jobId) {
        try {                                    
            ArrayList<ArrayList<String>> jobResult = new ArrayList<ArrayList<String>>();  
            def jobResponse = asyncJobServer().queryAsyncJobResult(jobId)
            if(jobResponse.asychronousJobStatus == "0") {                
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.JOB_RESULT_PENDING);
                jobResult.add(item);                
                return jobResult;                
            } else if(jobResponse.asychronousJobStatus == "1") {
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.RESULT_SUCCESS);
                jobResult.add(item);                
                return jobResult;                
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
    def acquireIp(requestBody) {
        
        try {
            
            licenseValidationService.authorizeAction(FogPanelService.VM_ACQUIRE_IP)
            
            def user = springSecurityService.currentUser 
            // convert string to json object
            def requestData = JSON.parse(requestBody)

            def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
            def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
            def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret

            CloudStackServer server = new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey)

            CSNicService cSNicService = new CSNicService(server)

            def vm = VirtualMachine.findByReferenceId(requestData.vmId)

            def responce = cSNicService.addIpToNic(vm.nicId, null)      
            
            ArrayList<ArrayList<String>> ipJobList = new ArrayList<ArrayList<String>>();        
            HashMap<String,String> item = new HashMap<String,String>()
            item.put("jobResult", GeneralConstants.JOB_RESULT_PENDING);
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            item.put("jobId", responce.jobId);
            item.put("vmId", requestData.vmId);
            ipJobList.add(item);
            log.info("IP Aquired for vm ${requestData.vmId}, and returned job") 
            return ipJobList
            
        } catch(Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
    
    def listVmIp(vmId) {
        
        def ipList
        
        def user = springSecurityService.currentUser 
        def vm = VirtualMachine.findByReferenceId(vmId)
        def role = springSecurityService.getPrincipal().getAuthorities() 
        if(role.iterator().next() == "ROLE_ADMIN" ) {
            ipList = VmIp.findAllWhere(inUse: true)
        } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {
            if(vmId == "ALL") {
                ipList = VmIp.findAllWhere(account: user.account, inUse: true) 
            } else {
                ipList = VmIp.findAllWhere(virtualMachine: vm, inUse: true) 
            }
        }
        ArrayList<ArrayList<String>> ipListArray = new ArrayList<ArrayList<String>>();   
        for(def ip : ipList) { 
                 
            HashMap<String,String> item = new HashMap<String,String>()                     
            item.put("id", ip.ipReferenceId);
            item.put("ipAddress", ip.ipAddress);
            item.put("virtualMachine", ip.virtualMachine.displayName);
            item.put("account", ip.account.userName);
            
            ipListArray.add(item);
        }
        return ipListArray        
    }
    
    def revokeIp(requestBody) {
        try {
            
            licenseValidationService.authorizeAction(FogPanelService.VM_REVOKE_IP)
            
            def user = springSecurityService.currentUser 
            // convert string to json object
            def requestData = JSON.parse(requestBody)

            def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
            def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
            def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret

            CloudStackServer server = new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey)

            CSNicService cSNicService = new CSNicService(server)
            
            def responce = cSNicService.removeIpFromNic(requestData.ipId) 
            
            def userIPAddress = UserIPAddress.findByIpReferenceId(requestData.ipId)
                                    
            userIPAddress.jobId = responce.jobId
            userIPAddress.save(flush: true);    
            
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
                 
            def userIPAddress = UserIPAddress.findByJobId(requestData.jobId)
             
            if(jobResponse.asychronousJobStatus == "1") {
              
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
                
                ArrayList<ArrayList<String>> ipJobList = new ArrayList<ArrayList<String>>();        
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.RESULT_SUCCESS);
                ipJobList.add(item);
                log.info("IP revoked job success for ${userIPAddress.publicIPAddress}") 
                return ipJobList
            } else if(jobResponse.asychronousJobStatus == "0") {
                
                ArrayList<ArrayList<String>> ipJobList = new ArrayList<ArrayList<String>>();        
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.JOB_RESULT_PENDING);
                item.put("jobId", jobResponse.asychronousJobId);
                ipJobList.add(item);
                log.info("IP revoked job pending for ${userIPAddress.publicIPAddress}") 
                return ipJobList
            } else if(jobResponse.asychronousJobStatus == "2") {
                
                ArrayList<ArrayList<String>> ipJobList = new ArrayList<ArrayList<String>>();        
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.RESULT_FAILURE);
                item.put("jobId", jobResponse.asychronousJobId);
                ipJobList.add(item);
                log.info("IP revoked job failed for ${userIPAddress.publicIPAddress}") 
                return ipJobList
            }
           
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
            
            Date date = Calendar.getInstance().getTime()
            def currentTime = new Timestamp(date.getTime())
            
            def vm = VirtualMachine.findByReferenceId(requestData.vmId)
             
            if(jobResponse.asychronousJobStatus == "1") {
                                
                def userIPAddress = new UserIPAddress(); 
                userIPAddress.account = user.account
                userIPAddress.user = user
                userIPAddress.ipReferenceId = jobResponse.secondaryIpId
                userIPAddress.publicIPAddress = jobResponse.secondaryIp
                userIPAddress.acquireDate = new Date()
                userIPAddress.virtualMachine = vm
                userIPAddress.isSourceNat =  false
                userIPAddress.isStaticNat = false
                userIPAddress.zone = vm.zone
                userIPAddress.save(flush: true)
                if (!userIPAddress.save()) {
                    userIPAddress.errors.allErrors.each { println it }
                }
                
//                    def vmIp = new VmIp()
//                    vmIp.ipReferenceId = jobResponse.secondaryIpId
//                    vmIp.ipAddress = jobResponse.secondaryIp
//                    vmIp.isDefault = false
//                    vmIp.virtualMachine = vm
//                    vmIp.inUse = true
//                    vmIp.acquireDate = currentTime
//                    vmIp.account = user.account
//                    vmIp.jobId = requestData.jobId
//                    vmIp.save(flush: true);    
                
                def invoice = Invoice.findWhere(account: vm.owner, status: InvoiceStatus.values()[6])

                def ipOffer = MiscellaneousOffer.get(2);
                def ipBillableItem = BillableItem.get(12)
                if(ipBillableItem.enabled == true || ipBillableItem.enabled == "true") {
                    
                    
                    def newInvoiceItem = new InvoiceItem()
                    newInvoiceItem.billableItem = ipBillableItem
                    newInvoiceItem.invoice = invoice
                    newInvoiceItem.taxPercent = ipBillableItem.tax.percentage
                    newInvoiceItem.zone = vm.zone
                    newInvoiceItem.usageUnitPrice = MiscellaneousOfferZoneCost.findWhere(miscellaneousOffer :ipOffer, zone :vm.zone).cost;
                    newInvoiceItem.usageUnits = 1.0;
                    double usageAmount = newInvoiceItem.usageUnitPrice                
                    newInvoiceItem.usageAmount = Math.ceil(usageAmount * 100d) / 100d;   
                    double taxAmount = (newInvoiceItem.usageAmount/100)* newInvoiceItem.taxPercent
                    newInvoiceItem.taxAmount = Math.ceil(taxAmount * 100d) / 100d;     
                    newInvoiceItem.totalAmount = Math.ceil((newInvoiceItem.usageAmount + newInvoiceItem.taxAmount) * 100d) / 100d;     
                    newInvoiceItem.referenceItemName = "SecondaryIPCost"
                    newInvoiceItem.referenceItemId = userIPAddress.publicIPAddress
                    newInvoiceItem.save(flush: true); 
                    
                }
                
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
                item.put("vmId", requestData.vmId);
                ipJobList.add(item);
                log.info("AcquireIp job pending for ip: ${jobResponse.secondaryIp}") 
                return ipJobList
            } else if(jobResponse.asychronousJobStatus == "2") {
                
                ArrayList<ArrayList<String>> ipJobList = new ArrayList<ArrayList<String>>();        
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.RESULT_FAILURE);
                item.put("jobId", jobResponse.asychronousJobId);
                item.put("vmId", requestData.vmId);
                ipJobList.add(item);
                log.info("AcquireIp job failed for ip: ${jobResponse.secondaryIp}") 
                return ipJobList
            }
           
        } catch(Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }    
    
    def UpdateHostName() {
        try {        
            def virtualMachine = VirtualMachine.findAll();
            for(int index = 0; index < virtualMachine.size(); index++ ) {              
                def vmInfo = virtualMachine[index];                                    
                HashMap<String, String> optional = new HashMap<String,String>();            
                optional.put("id", vmInfo.referenceId);
                def response = virtualMachineServer().listVirtualMachines(optional);              
                if(response) {                
                    for(Iterator<VirtualMachineResponse> iter = response.virtualMachines.iterator(); iter.hasNext();) {
                        def data = iter.next();      
                        if(data) {                        
                            HashMap<String, String> instances = new HashMap<String,String>();                                            
                            VirtualMachine newVirtualMachine = VirtualMachine.findByReferenceId(data.virtualMachineId);                    
                            newVirtualMachine.hostName = data.hostName;
                            newVirtualMachine.save(flush: true); 
                            if (newVirtualMachine.hasErrors()) {
                                throw new ValidationException(newVirtualMachine.errors.allErrors);
                            }
                        }                    
                    }
                }                            
            }                                        
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;            
        }    
    }
}
