package com.assistanz.fogpanel

import com.assistanz.cloud.cloudstack.CloudStackServer
import com.assistanz.cloud.cloudstack.volume.CSVolumeService
import com.assistanz.cloud.cloudstack.volume.VolumeResponse
import grails.converters.deep.JSON
import com.assistanz.fogpanel.GeneralConstants;
import com.assistanz.cloud.cloudstack.asyncjob.CSAsyncJobService
import org.codehaus.groovy.grails.commons.ApplicationHolder
import org.apache.commons.logging.LogFactory;
import grails.transaction.Transactional
import java.text.DecimalFormat

@Transactional
class VolumeService { 
    
    private static final log = LogFactory.getLog(this)
    def springSecurityService;
    NotificationService notificationService
    VolumeQueueService volumeQueueService
    LicenseValidationService licenseValidationService
    
    def volumeServer() {
        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret

        CloudStackServer server =
                 new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);
        CSVolumeService csVolumeService = new CSVolumeService(server);
      
        return csVolumeService;
    }
    
    def getStorageForResize (String volumeReferenceId) {
       try {              
           def role = springSecurityService.getPrincipal().getAuthorities()
           def diskCriteria = DiskOffer.createCriteria();
           ArrayList<ArrayList<String>> diskArrayList = new ArrayList<ArrayList<String>>();                
             
           def diskOffering           
           
           if(role.iterator().next() == "ROLE_ADMIN" ) {                                      
           }  else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {                                                                                      
               def currentVolume = Volume.findByVolumeReferenceId(volumeReferenceId);    
               if(currentVolume.diskOffer.customized == false){
                   def size = currentVolume.diskOffer.size;            
                    def clusterId = ""
//                    def clusterId = currentVolume.diskOffer.cluster.clusterReferenceId;
                    def tag = currentVolume.diskOffer.tags;
                    def type = currentVolume.diskOffer.storageType                
                    def diskList = diskCriteria.list {
                        eq("cluster", Cluster.findByClusterReferenceId(clusterId))
                        and {
                            eq("tags", tag)  
                        }
                        and {
                            eq("storageType", type)  
                        }                    
                        and {
                            gt("size", size)  
                        }
                    };                     
                    for(int i=0; i < diskList.size(); i++) {                    
                    def item = diskList[i];                                 
                    HashMap<String,String> diskItem = new HashMap<String,String>(); 
                    diskItem.put("name", item.name);
                    diskItem.put("size", item.size);
                    diskItem.put("zoneName", item.zone.aliasName);
                    diskItem.put("zoneReferenceId", item.zone.referenceZoneId);                                                                                       
                    diskItem.put("offerId", item.diskOfferReferenceId);                                                               
                    diskItem.put("type", item.storageType);    
                    diskItem.put("tag", item.tags);    
                    diskItem.put("diskOfferReferenceId", item.diskOfferReferenceId);  
                    
                    if(currentVolume.billingType == "monthly") {
                        
                        double cost = DiskOfferZoneCost.findWhere(diskOffer: item).cost * 720.00    
                        diskItem.put("zoneCost",  Math.ceil(cost * 100)/100);
                        diskItem.put("unit", "/GB/month")
                    } else {
                        diskItem.put("zoneCost", DiskOfferZoneCost.findWhere(diskOffer: item).cost)
                        diskItem.put("unit", "/GB/Hr")
                    }    
                        
                    
                    diskItem.put("clusterReferenceId", item.cluster.clusterReferenceId);     
                    diskItem.put("custom", item.customized);     
                    
                    diskArrayList.add(diskItem); 
                }
                } else {
                    def customDisk = currentVolume.diskOffer;
                    HashMap<String,String> diskItem = new HashMap<String,String>(); 
                    diskItem.put("name", customDisk.name);
                    diskItem.put("size", customDisk.size);
                    diskItem.put("zoneName", customDisk.zone.aliasName);
                    diskItem.put("zoneReferenceId", customDisk.zone.referenceZoneId);                                                                                       
                    diskItem.put("offerId", customDisk.diskOfferReferenceId);                                                               
                    diskItem.put("type", customDisk.storageType);    
                    diskItem.put("tag", customDisk.tags);    
                    diskItem.put("diskOfferReferenceId", customDisk.diskOfferReferenceId);   
                    if(currentVolume.billingType == "monthly") {
                        double cost = DiskOfferZoneCost.findWhere(diskOffer: customDisk).cost * 720.00    
                        diskItem.put("zoneCost",  Math.ceil(cost * 100)/100);
                        diskItem.put("unit", "/GB/month")
                    } else {
                        diskItem.put("zoneCost", DiskOfferZoneCost.findWhere(diskOffer: customDisk).cost)
                        diskItem.put("unit", "/GB/Hr")
                    }  
                    diskItem.put("clusterReferenceId", customDisk.cluster.clusterReferenceId);     
                    diskItem.put("custom", customDisk.customized);     
                    diskItem.put("minSize", currentVolume.customDiskSize);     
                    diskItem.put("maxSize", customDisk.maxSize);                                             
                    diskArrayList.add(diskItem); 
                }
                            
                               
            }   
            return diskArrayList;
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }             
    }
    
    def count(zoneReferenceId, status) {
        def user = springSecurityService.currentUser
        def role = springSecurityService.getPrincipal().getAuthorities()
        def volumeCriteria = Volume.createCriteria()
        def totalVolumes = 0;
        def detachedStorage = 0;
        def attachedStorage = 0;
        def vmName
        def vmId 
        def totalSnapshots;
        def volumes;
            ArrayList countList = new ArrayList(); 
            HashMap countItem = new HashMap();   
            if((zoneReferenceId == null || zoneReferenceId == "null") && (status == null || status == "null")) {
                if(role.iterator().next() == "ROLE_ADMIN" ) {                    
                    volumes = Volume.findAllWhere(deleted: false, type:"DATADISK")                                                                                                                                                                                                   
                    attachedStorage = volumeCriteria.list {
                        isNotNull("virtualMachine")
                        and{
                          eq("type", "DATADISK")  } 
                          and{
                            eq("deleted", false)  
                          }
                          and{
                            isNotNull("diskOffer")  
                          }
                        }.size();           
                   
                    ArrayList<ArrayList<String>> volumeList = new ArrayList<ArrayList<String>>();            
                    for(int i=0; i < volumes.size(); i++) {                                               
                        
                        def item = volumes[i];                                                  
                        
                        if((item.virtualMachine == null) || (item.virtualMachine == "null")) {
                            vmName = "NOT ATTACHED"    
                            vmId = "No VM"
                        }  else {
                              vmName = item.virtualMachine.displayName;
                              vmId = item.virtualMachine.referenceId; 
                        }
                        HashMap<String,String> volumeItem = new HashMap<String,String>();   
                            if(item.diskOffer) {
                                volumeItem.put("name", item.name);
                                volumeItem.put("size", item.diskOffer.size);
                                volumeItem.put("zoneName", item.zone.aliasName);
                                volumeItem.put("zoneReferenceId", item.zone.referenceZoneId);
                                volumeItem.put("vmName", vmName);
                                volumeItem.put("vmId", vmId);                            
                                volumeItem.put("offer", item.diskOffer.name);
                                volumeItem.put("offerId", item.diskOffer.diskOfferReferenceId);
                                volumeItem.put("volumeReferenceId", item.volumeReferenceId);
                                volumeItem.put("user", item.user.username);                            
                                volumeItem.put("type", item.type);                            
                                volumeList.add(volumeItem);
                            }
                            
                    }
                    countItem.put("volumeData", volumeList);  
                    totalVolumes = volumeList.size();
                    detachedStorage = totalVolumes - attachedStorage;
                }  else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {  
                    volumes = Volume.findAllWhere(deleted: false, type:"DATADISK", owner: user.account)
                    detachedStorage = Volume.findAllWhere(deleted: false, virtualMachine: null, type:"DATADISK", owner: user.account).size();             
                    totalVolumes = volumes.size();
                    attachedStorage = volumeCriteria.list {
                        isNotNull("virtualMachine")
                        and{
                          eq("type", "DATADISK")  } 
                          and {
                            eq("deleted", false)  
                          }
                          and {
                            eq("owner", user.account)  
                          }
                        }.size();   
                }
            } else if((zoneReferenceId != null || zoneReferenceId != "null") && (status == null || status == "null")) {              
                if(role.iterator().next() == "ROLE_ADMIN" ) {                  
                    volumes = Volume.findAllWhere(deleted: false, type:"DATADISK", zone: Zone.findByReferenceZoneId(zoneReferenceId))                     
                    attachedStorage = volumeCriteria.list {
                        isNotNull("virtualMachine")
                        and {
                            eq("type", "DATADISK")  
                        } 
                        and {
                            eq("deleted", false)  
                        }
                        and {
                            eq("zone", Zone.findByReferenceZoneId(zoneReferenceId))  
                        }
                        and {
                          isNotNull("diskOffer")  
                        }
                      }.size();  
                   
                    ArrayList<ArrayList<String>> volumeList = new ArrayList<ArrayList<String>>();            
                    for(int i=0; i < volumes.size(); i++) { 
                        def item = volumes[i]; 
                      
                         if((item.virtualMachine == null) || (item.virtualMachine == "null")) {
                            vmName = "NO VM"    
                            vmId = "No VM"
                        }  else {
                              vmName = item.virtualMachine.name;
                              vmId = item.virtualMachine.referenceId;
                              
                        } 
                        HashMap<String,String> volumeItem = new HashMap<String,String>();                
                                
                            if(item.diskOffer) {
                                volumeItem.put("name", item.name);
                                volumeItem.put("size", item.diskOffer.size);
                                volumeItem.put("zoneName", item.zone.aliasName);
                                volumeItem.put("zoneReferenceId", item.zone.referenceZoneId);
                                volumeItem.put("vmName",vmName);
                                volumeItem.put("vmId", vmId);
                                volumeItem.put("offer", item.diskOffer.name);
                                volumeItem.put("offerId", item.diskOffer.diskOfferReferenceId);
                                volumeItem.put("volumeReferenceId", item.volumeReferenceId);
                                volumeItem.put("type", item.type);  
                                volumeItem.put("user", item.user.username); 
                                volumeList.add(volumeItem);
                            }                                                                                                 
                        }
                        countItem.put("volumeData", volumeList);  
                        totalVolumes = volumeList.size();
                        detachedStorage = totalVolumes - attachedStorage;
                }  else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER")  {
                    volumes = Volume.findAllWhere(deleted: false, type:"DATADISK", zone: Zone.findByReferenceZoneId(zoneReferenceId), user: user)                     
                    attachedStorage = volumeCriteria.list {
                        isNotNull("virtualMachine")
                        and {
                            eq("type", "DATADISK")  
                        } 
                        and {
                            eq("deleted", false)  
                        }
                        and {
                            eq("zone", Zone.findByReferenceZoneId(zoneReferenceId))  
                        }
                        and {
                          isNotNull("diskOffer")  
                        }
                        and {
                          eq("user", user)  
                        }
                      }.size();  
                   
                    ArrayList<ArrayList<String>> volumeList = new ArrayList<ArrayList<String>>();            
                    for(int i=0; i < volumes.size(); i++) { 
                        def item = volumes[i]; 
                      
                         if((item.virtualMachine == null) || (item.virtualMachine == "null")) {
                            vmName = "NO VM"    
                            vmId = "No VM"
                        }  else {
                              vmName = item.virtualMachine.name;
                              vmId = item.virtualMachine.referenceId;
                              
                        } 
                        HashMap<String,String> volumeItem = new HashMap<String,String>();                
                                
                            if(item.diskOffer) {                                                        
                                volumeItem.put("name", item.name);
                                volumeItem.put("size", item.diskOffer.size);
                                volumeItem.put("zoneName", item.zone.aliasName);
                                volumeItem.put("zoneReferenceId", item.zone.referenceZoneId);
                                volumeItem.put("vmName",vmName);
                                volumeItem.put("vmId", vmId);
                                volumeItem.put("offer", item.diskOffer.name);
                                volumeItem.put("offerId", item.diskOffer.diskOfferReferenceId);
                                volumeItem.put("volumeReferenceId", item.volumeReferenceId);
                                volumeItem.put("type", item.type);  
                                volumeItem.put("user", item.user.username); 
                                volumeList.add(volumeItem);
                            }                                                                                                 
                        }
                        countItem.put("volumeData", volumeList);  
                        totalVolumes = volumeList.size();
                        detachedStorage = totalVolumes - attachedStorage;
                }
            } else if((zoneReferenceId == null || zoneReferenceId == "null") && (status != null || status != "null")) {
               
                 if(role.iterator().next() == "ROLE_ADMIN" ) {
                    def totalVolume = Volume.findAllWhere(deleted: false, type:"DATADISK"); 
                    for(int i = 0; i < totalVolume.size(); i++ ) {
                        def currentItem = totalVolume[i];
                        if(currentItem.diskOffer) {
                            totalVolumes = totalVolumes + 1;
                            if(currentItem.virtualMachine == null) {
                                detachedStorage = detachedStorage + 1;
                            }
                        }
                    } 
                    attachedStorage = totalVolumes - detachedStorage;    
                     if(status == "Detached") {
                         volumes = Volume.findAllWhere(deleted: false, virtualMachine: null,  type:"DATADISK"); 
                                  
                     } else if(status == "Attached") {
                         volumes = volumeCriteria.list {
                             isNotNull("virtualMachine")
                        and{
                          eq("type", "DATADISK")  } 
                          and{
                            eq("deleted", false)  
                          }
                        }  
                    }  
                    ArrayList<ArrayList<String>> volumeList = new ArrayList<ArrayList<String>>();            
                    for(int i=0; i < volumes.size(); i++) { 
                        def item = volumes[i];                       
                        if((item.virtualMachine == null) || (item.virtualMachine == "null")) {
                            vmName = "NO VM"    
                            vmId = "No VM"
                        }  else {
                              vmName = item.virtualMachine.name;
                              vmId = item.virtualMachine.referenceId;                              
                        }
                        if(item.diskOffer) {
                            HashMap<String,String> volumeItem = new HashMap<String,String>();                
                            volumeItem.put("name", item.name);
                            volumeItem.put("size", item.diskOffer.size);
                            volumeItem.put("zoneName", item.zone.aliasName);
                            volumeItem.put("zoneReferenceId", item.zone.referenceZoneId);
                            volumeItem.put("vmName", vmName);
                            volumeItem.put("vmId", vmId);
                            volumeItem.put("offer", item.diskOffer.name);
                            volumeItem.put("offerId", item.diskOffer.diskOfferReferenceId);
                            volumeItem.put("volumeReferenceId", item.volumeReferenceId);
                            volumeItem.put("type", item.type);                       
                            volumeItem.put("user", item.user.username);              
                            volumeList.add(volumeItem);
                        }                        
                    }
                    countItem.put("volumeData", volumeList);  
                }  
            } else if((zoneReferenceId != null || zoneReferenceId != "null") && (status != null || status != "null")) {
                
                 if(role.iterator().next() == "ROLE_ADMIN" ) {
                    def totalVolume = Volume.findAllWhere(deleted: false, type:"DATADISK", zone: Zone.findByReferenceZoneId(zoneReferenceId)); 
                    for(int i = 0; i < totalVolume.size(); i++ ) {
                        def currentItem = totalVolume[i];
                        if(currentItem.diskOffer) {
                            totalVolumes = totalVolumes + 1;
                            if(currentItem.virtualMachine == null) {
                                detachedStorage = detachedStorage + 1;
                            }
                        }
                    } 
                    attachedStorage = totalVolumes - detachedStorage;                
                    if(status == "Detached") {
                        volumes = Volume.findAllWhere(deleted: false, virtualMachine: null,  type:"DATADISK", zone: Zone.findByReferenceZoneId(zoneReferenceId));                                        
                    } else if(status == "Attached") {
                        volumes = volumeCriteria.list {
                            isNotNull("virtualMachine")                        
                            and {                           
                                eq("type", "DATADISK")  
                            } 
                            and {
                                eq("deleted", false)  
                            }
                            and {
                                eq("zone", Zone.findByReferenceZoneId(zoneReferenceId))                              
                            }
                        }                        
                    }                       
                 
                    ArrayList<ArrayList<String>> volumeList = new ArrayList<ArrayList<String>>();            
                    for(int i=0; i < volumes.size(); i++) { 
                        def item = volumes[i];                       
                        if((item.virtualMachine == null) || (item.virtualMachine == "null")) {
                            vmName = "NO VM"    
                            vmId = "No VM"
                        }  else {
                              vmName = item.virtualMachine.name;
                              vmId = item.virtualMachine.referenceId;                              
                        }
                        
                        
                        if(item.diskOffer) {
                           
                            HashMap<String,String> volumeItem = new HashMap<String,String>();                
                            volumeItem.put("name", item.name);
                            volumeItem.put("size", item.diskOffer.size);
                            volumeItem.put("zoneName", item.zone.aliasName);
                            volumeItem.put("zoneReferenceId", item.zone.referenceZoneId);
                            volumeItem.put("vmName", vmName);
                            volumeItem.put("vmId", vmId);
                            volumeItem.put("offer", item.diskOffer.name);
                            volumeItem.put("offerId", item.diskOffer.diskOfferReferenceId);
                            volumeItem.put("volumeReferenceId", item.volumeReferenceId);
                            volumeItem.put("type", item.type);                       
                            volumeItem.put("user", item.user.username);
                            volumeList.add(volumeItem);
                        }
                        
                    }
                    countItem.put("volumeData", volumeList); 
                }  
            }
            countItem.put("totalStorage", totalVolumes);
            countItem.put("detachedStorage", detachedStorage);
            countItem.put("attachedStorage", attachedStorage);               
            countList.add(countItem);
            return countList;
    }
    
    def createVMDiskList() {
         try {
             
            def result;
            def size; 
            def volumeSize;
            HashMap<String,String> optional = new HashMap<String,String>();
            optional.put("listall", "true");
            def response = volumeServer().listVolumes(optional); 
            HashMap<String,String> cloudStackDiskList = new HashMap<String,String>(); 
            
             for(Iterator<VolumeResponse> iter = response.volumes.iterator(); iter.hasNext();) {
                def data = iter.next();
                cloudStackDiskList.put(data.diskVolumeId ,"referenceId");
                Volume volume = Volume.findByVolumeReferenceId(data.diskVolumeId);
                if (!volume) {                    
                    volume  = new Volume();
                    volume.volumeReferenceId = data.diskVolumeId;
                    volume.name = data.diskVolumeName; 
                    volume.owner = Account.findByAccountIdentifier(data.diskVolumeAccount);
                    volume.zone = Zone.findByReferenceZoneId(data.zoneId);
                    volume.type = data.diskVolumeType;
                    volume.state = data.diskVolumeState;
                    size = Double.parseDouble(data.diskVolumeSize);
                    volumeSize = size / (double) 1073741824;
                    volume.customDiskSize = volumeSize;
                    if(data.diskOfferingId == null) {
                        volume.diskOffer = null;
                    } else {
                        volume.diskOffer = DiskOffer.findByDiskOfferReferenceId(data.diskOfferingId);
                    }
                    if(data.virtualMachineId == null) {
                        volume.virtualMachine = null;
                        volume.billingType = "hourly"
                    } else {
                        volume.virtualMachine = VirtualMachine.findByReferenceId(data.virtualMachineId);
                        if(VirtualMachine.findByReferenceId(data.virtualMachineId)) {
                            volume.billingType = VirtualMachine.findByReferenceId(data.virtualMachineId).billingType
                        } else {
                            volume.billingType = "hourly"
                        }
                    }                    
                    volume.user = User.findByUsername(data.diskVolumeAccount);
                    volume.deleted = false;  
                    volume.save(flush: true);  
                    
                    if(volume.type == "DATADISK") {
                        def disk = DiskOfferZoneCost.findWhere(diskOffer: volume.diskOffer)
                        if (disk) {
                            def diskBillableItem
                            if(volume.billingType == "monthly") { 
                                diskBillableItem = BillableItem.get(14) 
                            } else {
                                diskBillableItem= BillableItem.get(2) 
                            }
                            
                            if(diskBillableItem.enabled == true || diskBillableItem.enabled == "true") {
                                def invoice = Invoice.findWhere(account: volume.owner, status: InvoiceStatus.values()[6])
                                def invoiceItem = InvoiceItem.findWhere(invoice: invoice, referenceItemId: volume.volumeReferenceId)
                                if(!invoiceItem) {
                                    invoiceItem = new InvoiceItem()
                                    invoiceItem.billableItem = diskBillableItem
                                    invoiceItem.taxPercent = diskBillableItem.tax.percentage
                                    invoiceItem.zone = volume.zone
                                    
                                    if(volume.billingType == "monthly") {
                                        
                                        double volsize = Double.parseDouble(volume.customDiskSize) 
                                        
                                        double monthlyAmount = disk.cost * 720.00 * volsize

                                        invoiceItem.usageUnitPrice = Math.ceil(monthlyAmount * 100d) / 100d;   
                                        invoiceItem.usageUnits = 1.0
                                        invoiceItem.usageAmount = Math.ceil(monthlyAmount * 100d) / 100d;   
                                        double taxAmount = (invoiceItem.usageAmount/100)* invoiceItem.taxPercent
                                        invoiceItem.taxAmount = Math.ceil(taxAmount * 100d) / 100d;   

                                        double totalAmt =  (invoiceItem.usageAmount + invoiceItem.taxAmount)

                                        invoiceItem.totalAmount = Math.ceil(totalAmt * 100d) / 100d;   
                                    } else {
                                        invoiceItem.usageUnitPrice = disk.cost
                                    }
                                    
                                    invoiceItem.invoice = invoice
                                    invoiceItem.referenceItemName = "Volume"
                                    invoiceItem.referenceItemId = volume.volumeReferenceId
                                    invoiceItem.referencePlanId = volume.diskOffer.diskOfferReferenceId
                                    invoiceItem.save(flush: true);  
                                    log.info("volume invoice item added to account: ${data.diskVolumeAccount} for volume: ${volume.id}")
                                    if (!invoiceItem.save()) {
                                        invoiceItem.errors.allErrors.each { Console.print(it) }
                                    }
                                }
                            }
                        }
                    }
                } 
            }
            
         }  catch (Exception ex) {
           Console.print(ex)
        } 
        
    }
    
    def list(String virtualMachineReferenceId, String zoneReferenceId, String name) {
        try {            
            def user = springSecurityService.currentUser
            def role = springSecurityService.getPrincipal().getAuthorities()
            def account = user.account
            def result;
            def size; 
            def volumeSize;
            def currency = ApplicationHolder.getApplication().config.billing.defaultCurrency
            volumeQueueService.list(user.account)
            if((virtualMachineReferenceId == "null" || virtualMachineReferenceId == null)&& (zoneReferenceId == "null" || zoneReferenceId == null) && (name == "null" || name == null))  {              
                if(role.iterator().next() == "ROLE_ADMIN" ) {
                    result = Volume.findAll("from Volume as volume where volume.deleted=?", [false]);
                } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {
                   result = Volume.findAllWhere(deleted: false, user: user); 
                }  
            } else if((virtualMachineReferenceId != "null" || virtualMachineReferenceId != null) && (zoneReferenceId == "null" || zoneReferenceId == null && (name == "null" || name == null))) {
                 VirtualMachine virtualMachine = VirtualMachine.findByReferenceId(virtualMachineReferenceId);
                if(role.iterator().next() == "ROLE_ADMIN" ) {
                    result = Volume.findAll("from Volume as volume where volume.deleted=?", [false]);
                } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {
                   result = Volume.findAllWhere(deleted: false, user: user, virtualMachine: virtualMachine);                    
                }  
            } else if((zoneReferenceId != "null" || zoneReferenceId != null) && (virtualMachineReferenceId == "null" || virtualMachineReferenceId == null) && (name == "null" || name == null))  {              
                Zone zone = Zone.findByReferenceZoneId(zoneReferenceId);
                if(role.iterator().next() == "ROLE_ADMIN" ) {
                    result = Volume.findAll("from Volume as volume where volume.deleted=?", [false]);
                } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {
                   result = Volume.findAllWhere(deleted: false, user: user, zone:zone);                    
                }  
            } else if((name != "null" || name != null) && (zoneReferenceId == "null" || zoneReferenceId == null) && (virtualMachineReferenceId == "null" || virtualMachineReferenceId == null))  {              
                def volumeCriteria = Volume.createCriteria()
                if(role.iterator().next() == "ROLE_ADMIN" ) {
                    result = volumeCriteria.list {
                        like("name", "%" + name + "%")
                        and {
                            eq("deleted", false)
                        } 
                    }
                } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {
                    result = volumeCriteria.list {
                        like("name", "%" + name + "%")
                        and {
                            eq("deleted", false)
                            and {
                                eq("user", user)
                            } 
                        }
                    }
                }  
            }                    
            def miscelCost = ""                                   
            ArrayList<ArrayList<String>> volumeList = new ArrayList<ArrayList<String>>();            
            for(int i=0; i < result.size(); i++) { 
                def item = result[i]; 
                miscelCost = MiscellaneousOfferZoneCost.findWhere(zone: Zone.findByReferenceZoneId(item.zone.referenceZoneId), miscellaneousOffer: MiscellaneousOffer.get(3));
                HashMap<String,String> volumeItem = new HashMap<String,String>();                
                    volumeItem.put("referenceId", item.volumeReferenceId);
                    volumeItem.put("zoneName", item.zone.aliasName);
                    volumeItem.put("snapshotLimit", account.snapshotLimit);
                    volumeItem.put("snapshotUsed", Snapshot.findAllWhere(user: user, deleted: false).size());
                    volumeItem.put("name", item.name);
                    volumeItem.put("currency", currency);
                    if(miscelCost) {
                        DecimalFormat df = new DecimalFormat("#.###############");     
                        volumeItem.put("miscelCost", df.format(miscelCost.cost));
                    } else {
                        volumeItem.put("miscelCost", "");
                    }
                    
                    
                    Snapshot snapShot = Snapshot.findWhere(deleted: false, volume: item);  
                    if(snapShot) {
                        volumeItem.put("snapshot", "yes");
                    } else {
                       volumeItem.put("snapshot", "no"); 
                    }
                    SnapshotPolicy snapshotPolicy = SnapshotPolicy.findWhere(deleted: false, volume: item);   
                    if(snapshotPolicy) {
                        volumeItem.put("snapshotPolicy", "yes");
                    } else {
                       volumeItem.put("snapshotPolicy", "no"); 
                    }
                    if(item.diskOffer == null) {
                       volumeItem.put("diskOffer", null);
                       volumeItem.put("size", item.customDiskSize);
                    } else {
                        if(item.diskOffer.customized == true || item.diskOffer.customized == "true") {
                            volumeItem.put("size", item.customDiskSize);
                        } else if(item.diskOffer.customized == false || item.diskOffer.customized == "false") {
                            volumeItem.put("size", item.diskOffer.size);
                        }
                        volumeItem.put("diskOffer", item.diskOffer.name);
                        volumeItem.put("tag", item.diskOffer.tags);
                    }
                    volumeItem.put("owner", item.owner.accountIdentifier);
                    volumeItem.put("state", item.state);
                    volumeItem.put("type", item.type);
                    if(item.virtualMachine == null) {
                      volumeItem.put("virtualMachin", null);
                      volumeItem.put("hasVMSnapshot", "false"); 
                    } else {
                        def hasVMSnapshot = "false";
                        def VMSnapList = VMSnapshot.findAllWhere(virtualMachine:item.virtualMachine, deleted: false);    
                        if(VMSnapList) {
                            hasVMSnapshot = "true";
                        }
                        volumeItem.put("virtualMachin", item.virtualMachine.displayName); 
                        volumeItem.put("virtualMachineReferenceId", item.virtualMachine.referenceId); 
                        volumeItem.put("virtualMachineState", item.virtualMachine.state); 
                        volumeItem.put("hasVMSnapshot", hasVMSnapshot); 
                    }       
                    volumeList.add(volumeItem);
                    DecimalFormat df = new DecimalFormat("#.###############");      
                    ArrayList<ArrayList<String>> zoneArrayList = new ArrayList<ArrayList<String>>(); 
                    def zoneList = Zone.findAll();                    
                    for(int index=0; index<zoneList.size(); index++) {
                        def zoneItem = zoneList[index]; 
                        def zoneMiscelCost = MiscellaneousOfferZoneCost.findWhere(zone: Zone.findByReferenceZoneId(zoneItem.referenceZoneId), miscellaneousOffer: MiscellaneousOffer.get(3));                        
                        HashMap<String,String> zoneArrayItem = new HashMap<String,String>(); 
                        if(zoneMiscelCost) {
                            zoneArrayItem.put("cost", df.format(zoneMiscelCost.cost));
                            zoneArrayItem.put("zoneName", zoneItem.aliasName);
                            zoneArrayItem.put("currency", currency);                                                
                            zoneArrayList.add(zoneArrayItem)
                            volumeItem.put("miscelZoneCostList",zoneArrayList);
                        }                        
                    }                
                }
            return volumeList;
            
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
    }

    def attachVolume(String requestBody) {
        try {
            
            licenseValidationService.authorizeAction(FogPanelService.VOLUME_ATTACH)
            
            def account = springSecurityService.currentUser.account;
            
            // convert string to json object
            def requestData = JSON.parse(requestBody)  
            Volume.withTransaction {
                Volume volume = Volume.findByVolumeReferenceId(requestData.volumeReferenceId);
                VirtualMachine virtualMachine = VirtualMachine.findByReferenceId(requestData.virtualMachineReferenceId);
                def volumeCount = Volume.findAll("from Volume as volume where volume.virtualMachine=?", [virtualMachine]);
                if(volumeCount.size()-1 == 5) {
                    return ["volume count reached"]
                } else {
                    def response = volumeServer().attachVolume(requestData.volumeReferenceId, requestData.virtualMachineReferenceId, null); 
                    def jobResponse = volumeServer().volumeJobResult(response.jobId);
                    if(jobResponse.asynchronousJobStatus == "2") {
                        log.info("volume attach failed for account: ${account.id} for volume: ${volume.id} due to ${jobResponse.errorText}")
                        return [jobResponse.errorText]
                    }
//                    volume.job = response.jobId; 
//                    volume.save(flush: true);
                    ArrayList<ArrayList<String>> volumeJob = new ArrayList<ArrayList<String>>();            
                    HashMap<String,String> item = new HashMap<String,String>(); 
                    item.put("result", GeneralConstants.RESULT_SUCCESS);
                    item.put("jobId", response.jobId);
                    volumeJob.add(item);
                    log.info("volume attach initiate for account: ${account.id} for volume: ${volume.id}, jobId ${response.jobId}")
                    return volumeJob;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
        
    }
    def resizeVolume(String requestBody) {
        try {
            
            licenseValidationService.authorizeAction(FogPanelService.VOLUME_RESIZE)
            
            def account = springSecurityService.currentUser.account;
            
            // convert string to json object
            def requestData = JSON.parse(requestBody)  
            Volume.withTransaction {
                Volume volume = Volume.findByVolumeReferenceId(requestData.volumeReferenceId);
                VirtualMachine virtualMachine = VirtualMachine.findByReferenceId(requestData.virtualMachineReferenceId);
                HashMap<String,String> optional = new HashMap<String,String>();
                optional.put("id", requestData.volumeReferenceId);
                optional.put("diskofferingid", requestData.diskOfferId);
                optional.put("shrinkok", "false");
                optional.put("size", requestData.size);    
                
                def response = volumeServer().resizeVolume(optional); 
                def jobResponse = volumeServer().volumeJobResult(response.jobId);
                if(jobResponse.asynchronousJobStatus == "2") {
                    log.info("volume resize failed for account: ${account.id} for volume: ${volume.id} due to ${jobResponse.errorText}")
                    return [jobResponse.errorText]
                }    
                volume.job = response.jobId;
                volume.save(flush: true);
                ArrayList<ArrayList<String>> volumeJob = new ArrayList<ArrayList<String>>();            
                HashMap<String,String> item = new HashMap<String,String>(); 
                item.put("result", GeneralConstants.RESULT_SUCCESS);
                item.put("jobId", response.jobId);
                volumeJob.add(item);
                log.info("volume resize initiate for account: ${account.id} for volume: ${volume.id}, jobId ${response.jobId}")
                return volumeJob; 
            }
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }         
    }
    
    def resizeJob (String requestBody) {
         try {
            
            // convert string to json object
            def account = springSecurityService.currentUser.account;
            def requestData = JSON.parse(requestBody)
            def size;
            def volumeSize 
            ArrayList<ArrayList<String>> volumeResult = new ArrayList<ArrayList<String>>();  
            
            def jobResponse = volumeServer().volumeJobResult(requestData.jobId);    
            def volume = Volume.findByVolumeReferenceId(jobResponse.diskVolumeId);
            if(jobResponse.asynchronousJobStatus == "0") {
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.JOB_RESULT_PENDING);
                item.put("jobId", jobResponse.asychronousJobInstanceId);
                volumeResult.add(item);
            } else if(jobResponse.asynchronousJobStatus == "1") {
                volume.state = jobResponse.diskVolumeState;
                volume.type = jobResponse.diskVolumeType;
                volume.volumeReferenceId = jobResponse.diskVolumeId;                
                
                def diskOffer = DiskOffer.findByDiskOfferReferenceId(jobResponse.diskOfferingId);
                volume.diskOffer = diskOffer
                HashMap<String,String> volumeItem = new HashMap<String,String>(); 
                volumeItem.put("jobResult", GeneralConstants.RESULT_SUCCESS);
                volumeItem.put("referenceId", jobResponse.diskVolumeId);
                volumeItem.put("zoneName", volume.zone.aliasName);
                volumeItem.put("name", volume.name);
                if(volume.diskOffer == null) {
                    volumeItem.put("diskOffer", null);
                } else {
                    volumeItem.put("diskOffer", volume.diskOffer.name);
                    if(volume.diskOffer.customized == true || volume.diskOffer.customized == "true") {                        
                        size = Double.parseDouble(jobResponse.diskVolumeSize);
                        volumeSize = size / (double) 1073741824;
                        volume.customDiskSize = volumeSize;
                        volumeItem.put("size", volume.customDiskSize);
                    } else if(volume.diskOffer.customized == false || volume.diskOffer.customized == "false") {
                        size = Double.parseDouble(jobResponse.diskVolumeSize);
                        volumeSize = size / (double) 1073741824;
                        volume.customDiskSize = volumeSize;
                        volumeItem.put("size", volume.diskOffer.size);                        
                    }
                    volumeItem.put("tag", volume.diskOffer.tags);
                    
                    // add volume invoice item
                    def diskBillableItem
                    if(volume.billingType == "monthly") { 
                        diskBillableItem = BillableItem.get(14) 
                    } else {
                        diskBillableItem= BillableItem.get(2) 
                    }
                    if(diskBillableItem.enabled == true || diskBillableItem.enabled == "true") {
                        def invoice = Invoice.findWhere(account: volume.owner, status: InvoiceStatus.values()[6])
                            def costDisk = DiskOfferZoneCost.findWhere(diskOffer: diskOffer)
                            def invoiceItem = new InvoiceItem()
                            invoiceItem.billableItem = diskBillableItem
                            invoiceItem.taxPercent = diskBillableItem.tax.percentage
                            invoiceItem.zone = volume.zone

                            if(volume.billingType == "monthly") {

                                double volsize = Double.parseDouble(volume.customDiskSize) 

                                double monthlyAmount = costDisk.cost * 720.00 * volsize

                                invoiceItem.usageUnitPrice = Math.ceil(monthlyAmount * 100d) / 100d;   
                                invoiceItem.usageUnits = 1.0
                                invoiceItem.usageAmount = Math.ceil(monthlyAmount * 100d) / 100d;   
                                double taxAmount = (invoiceItem.usageAmount/100)* invoiceItem.taxPercent
                                invoiceItem.taxAmount = Math.ceil(taxAmount * 100d) / 100d;   

                                double totalAmt =  (invoiceItem.usageAmount + invoiceItem.taxAmount)

                                invoiceItem.totalAmount = Math.ceil(totalAmt * 100d) / 100d;   
                            } else {
                                invoiceItem.usageUnitPrice = costDisk.cost
                            }

                            invoiceItem.invoice = invoice
                            invoiceItem.referenceItemName = "Volume"
                            invoiceItem.referenceItemId = volume.volumeReferenceId
                            invoiceItem.referencePlanId = volume.diskOffer.diskOfferReferenceId
                            invoiceItem.save(flush: true);  
                            log.info("volume resize invoice item added success for account: ${account.id} for volume: ${volume.id}")
                            if (!invoiceItem.save()) {
                                invoiceItem.errors.allErrors.each { Console.print(it) }
                            }
                    }
                }
                if(jobResponse.virtualMachineId == null || jobResponse.virtualMachineId == "null" || jobResponse.virtualMachineId == "") {
                    volumeItem.put("virtualMachin", null);
                    volume.virtualMachine = null;
                } else {
                    VirtualMachine virtualMachine = VirtualMachine.findByReferenceId(jobResponse.virtualMachineId);
                    if(virtualMachine) {
                        volume.virtualMachine = virtualMachine;
                        volumeItem.put("virtualMachin", volume.virtualMachine.displayName); 
                        volumeItem.put("virtualMachineReferenceId", volume.virtualMachine.referenceId); 
                    } else {
                        volumeItem.put("virtualMachin", null);
                        volume.virtualMachine = null;
                    }
                }
                volume.save(flush: true);
                volumeItem.put("state", volume.state);
                volumeItem.put("type", volume.type);
                volumeItem.put("snapshot", "no");
                volumeItem.put("snapshotPolicy", "no"); 
                volumeResult.add(volumeItem);                              
            } else if(jobResponse.asynchronousJobStatus == "2") {
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.RESULT_FAILURE);
                item.put("message", jobResponse.errorText);
                volumeResult.add(item); 
            }
            return volumeResult; 
            
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
    }
    
    def detachVolume(String requestBody) {
        try {
            
            licenseValidationService.authorizeAction(FogPanelService.VOLUME_DETACH)
            
            def user = springSecurityService.currentUser
            
            // convert string to json object
            def requestData = JSON.parse(requestBody)  
            Volume.withTransaction {
                Volume volume = Volume.findByVolumeReferenceId(requestData.volumeReferenceId);
                VirtualMachine virtualMachine = VirtualMachine.findByReferenceId(requestData.virtualMachineReferenceId);
                HashMap<String,String> optional = new HashMap<String,String>();
                optional.put("id", requestData.volumeReferenceId);
//                optional.put("virtualmachineid", requestData.virtualMachineReferenceId);
                def response = volumeServer().detachVolume(optional); 
                def jobResponse = volumeServer().volumeJobResult(response.jobId);
                if(jobResponse.asynchronousJobStatus == "2") {
                    log.info("volume detach failed for account: ${user.account.id} for volume: ${volume.id} due to ${jobResponse.errorText}")
                    return [jobResponse.errorText]
                }      
                volume.job = response.jobId;
                volume.save(flush: true);
                ArrayList<ArrayList<String>> volumeJob = new ArrayList<ArrayList<String>>();            
                HashMap<String,String> item = new HashMap<String,String>(); 
                item.put("result", GeneralConstants.RESULT_SUCCESS);
                item.put("jobId", response.jobId);
                volumeJob.add(item);
                log.info("volume detach initiate for account: ${user.account.id} for volume: ${volume.id}, jobId ${response.jobId}")
                return volumeJob;
                
            }
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
    }
    
    def create(String requestBody) {
        try {
            
            licenseValidationService.authorizeAction(FogPanelService.VOLUME_CREATE)
            
            def user = springSecurityService.currentUser
        
            // convert string to json object
            def requestData = JSON.parse(requestBody)
            
            def diskOffer = DiskOffer.findByDiskOfferReferenceId(requestData.diskOfferReferenceId);
            def vm = VirtualMachine.findByReferenceId(requestData.instanceId); 
            
            
            HashMap<String,String> optional = new HashMap<String,String>();
            optional.put("account", new String(user.account.accountIdentifier));
            optional.put("domainid", new String(user.account.domain.referenceId));
            optional.put("diskofferingid",  new String(diskOffer.diskOfferReferenceId));
            optional.put("zoneid",  new String(vm?.zone.referenceZoneId));
            if(diskOffer.customized == true || diskOffer.customized == "true") {
                    optional.put("size", requestData.customDiskSize);
            }
            Volume.withTransaction {
                def response = volumeServer().createVolume(requestData.name ,optional); 
                Volume newVolume = new Volume()
                newVolume.customDiskSize = requestData.customDiskSize;
                newVolume.name = requestData.name;
                newVolume.volumeReferenceId = response.id;
                newVolume.diskOffer = diskOffer
                newVolume.zone = vm?.zone // Zone.findByReferenceZoneId(diskOffer.zone.referenceZoneId);
                newVolume.owner = Account.findByAccountIdentifier(user.account.accountIdentifier);
                newVolume.user = User.findByUsername(user.username);
                newVolume.deleted = false; 
                newVolume.type = "DATADISK";
                newVolume.billingType = requestData.billingType;
//                newVolume.createdDate = Calendar.getInstance().getTime()
                newVolume.job = response.jobId;
                newVolume.save(flush: true, failOnError:true);
                
                ArrayList<ArrayList<String>> volume = new ArrayList<ArrayList<String>>();            
                HashMap<String,String> item = new HashMap<String,String>(); 
                item.put("result", GeneralConstants.RESULT_SUCCESS);
                item.put("jobId", response.jobId);
                volume.add(item);
                def diskBillableItem
                if(newVolume.billingType == "monthly") { 
                    diskBillableItem = BillableItem.get(14) 
                } else {
                    diskBillableItem= BillableItem.get(2) 
                }
                if(diskBillableItem.enabled == true || diskBillableItem.enabled == "true") {
                    def invoiceItem = new InvoiceItem()
                    invoiceItem.billableItem = diskBillableItem
                    invoiceItem.zone = newVolume.zone
                    invoiceItem.taxPercent = diskBillableItem.tax.percentage
                    invoiceItem.invoice = Invoice.findWhere(account: newVolume.owner, status: InvoiceStatus.values()[6])
                    
                    if(newVolume.billingType == "monthly") {
                                        
                        double volsize = Double.parseDouble(newVolume.customDiskSize) 

                        double monthlyAmount =  DiskOfferZoneCost.findWhere(diskOffer: newVolume.diskOffer, zone:newVolume.zone).cost * 720.00 * volsize

                        invoiceItem.usageUnitPrice = Math.ceil(monthlyAmount * 100d) / 100d;   
                        invoiceItem.usageUnits = 1.0
                        invoiceItem.usageAmount = Math.ceil(monthlyAmount * 100d) / 100d;   
                        double taxAmount = (invoiceItem.usageAmount/100)* invoiceItem.taxPercent
                        invoiceItem.taxAmount = Math.ceil(taxAmount * 100d) / 100d;   

                        double totalAmt =  (invoiceItem.usageAmount + invoiceItem.taxAmount)

                        invoiceItem.totalAmount = Math.ceil(totalAmt * 100d) / 100d;   
                    } else {
                        invoiceItem.usageUnitPrice = DiskOfferZoneCost.findWhere(diskOffer: newVolume.diskOffer, zone:newVolume.zone).cost
                    }
                    
                    invoiceItem.referenceItemName = "Volume"
                    invoiceItem.referenceItemId = newVolume.volumeReferenceId
                    invoiceItem.referencePlanId = newVolume.diskOffer.diskOfferReferenceId
                    invoiceItem.save(flush: true);  
                    log.info("volume invoice item added for account: ${user.account.id} for volume: ${volume.id}")
                }                
                
                // volume mail
                sendVolumeMail(newVolume)
                
                log.info("volume create for account: ${user.account.id} for data: ${requestBody}")
                
                return volume;
            }  
            
            
            
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
    }    
            
    def job(String requestBody) {  
        try {
            
            // convert string to json object
            def account = springSecurityService.currentUser.account;
            def requestData = JSON.parse(requestBody)
            ArrayList<ArrayList<String>> volumeResult = new ArrayList<ArrayList<String>>();  
               
            def jobResponse = volumeServer().volumeJobResult(requestData.jobId);    
            def volume = Volume.findByVolumeReferenceId(jobResponse.id);            
            if(jobResponse.asynchronousJobStatus == "0") {
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.JOB_RESULT_PENDING);
                item.put("jobId", jobResponse.asynchronousJobInstanceId);
                volumeResult.add(item);
            } else if(jobResponse.asynchronousJobStatus == "1") {
                volume.state = jobResponse.state;
                volume.type = jobResponse.type;
                volume.volumeReferenceId = jobResponse.id;
                HashMap<String,String> volumeItem = new HashMap<String,String>(); 
                volumeItem.put("jobResult", GeneralConstants.RESULT_SUCCESS);
                volumeItem.put("referenceId", jobResponse.id);
                volumeItem.put("zoneName", volume.zone.aliasName);
                volumeItem.put("name", volume.name);
                if(volume.diskOffer == null) {
                    volumeItem.put("diskOffer", null);
                } else {
                    volumeItem.put("diskOffer", volume.diskOffer.name);
                    if(volume.diskOffer.customized == true || volume.diskOffer.customized == "true") {
                        volumeItem.put("size", volume.customDiskSize);
                    } else if(volume.diskOffer.customized == false || volume.diskOffer.customized == "false") {
                        volumeItem.put("size", volume.diskOffer.size);
                    }
                    volumeItem.put("tag", volume.diskOffer.tags);
                }
                if(jobResponse.virtualMachineId == null || jobResponse.virtualMachineId == "null" || jobResponse.virtualMachineId == "") {
                    volumeItem.put("virtualMachin", null);
                    volume.virtualMachine = null;
                } else {
                    VirtualMachine virtualMachine = VirtualMachine.findByReferenceId(jobResponse.virtualMachineId);
                    if(virtualMachine) {
                        volume.virtualMachine = virtualMachine;
                        volumeItem.put("virtualMachin", volume.virtualMachine.displayName); 
                        volumeItem.put("virtualMachineReferenceId", volume.virtualMachine.referenceId); 
                    } else {
                        volumeItem.put("virtualMachin", null);
                        volume.virtualMachine = null;
                    }
                }
                volume.save(flush: true);
                volumeItem.put("state", volume.state);
                volumeItem.put("type", volume.type);
                volumeItem.put("snapshot", "no");
                volumeItem.put("snapshotPolicy", "no"); 
                volumeResult.add(volumeItem);  
                log.info("volume job success for account: ${account.id} for volume: ${volume.id}")
            } else if(jobResponse.asynchronousJobStatus == "2") {
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.RESULT_FAILURE);
                item.put("message", jobResponse.errorText);
                volumeResult.add(item);  
                log.info("volume job failed for account: ${account.id} for volume: ${volume?.id} due to ${jobResponse.errorText}")
                if(volume) {
                    def volumeInvoiceItem = InvoiceItem.findByReferenceItemId(volume.volumeReferenceId)
                    if(volumeInvoiceItem) {
                        volumeInvoiceItem.delete(flush: true); 
                    }
                }
            }
            return volumeResult; 
            
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }             
            
    } 
    def sendVolumeMail(newVolume) {
        try {
                def account = springSecurityService.currentUser.account                                
                // Content Area
                Map tempalteMap = notificationService.getDefaultMailTemplateMap()                
                tempalteMap.put("volumeName", newVolume.name)
                tempalteMap.put("diskSize", newVolume.customDiskSize.toString() + " GB")               
                notificationService.send(account.email.toString(), "createDisk.subject.ftl", tempalteMap, "createDisk.ftl")                    

        } catch(Exception ex) {
             ex.printStackTrace(System.err);
            throw ex;
         }
         
              
    }
    
    def volumeResourceClean() {
        
        def accountList = Account.findAll()
        
        for(def account: accountList) {
            if(account.status == "CANCELED" || account.status == "CLOSED") {
            def volumeList = Volume.findAllWhere(owner: account, deleted: false) 
                for(def volume: volumeList) {
                    volume.deleted = true;                   
                    volume.save(flush: true);   
                    def response = volumeServer().deleteVolume(volume.volumeReferenceId);  
                    
                }
            }
        }
    }
            
    def deleteVolume(String volumeId) {
        try {
            
            licenseValidationService.authorizeAction(FogPanelService.VOLUME_DELETE)
            
            def account = springSecurityService.currentUser.account 
            Volume volume = Volume.findByVolumeReferenceId(volumeId);
            if(volume.virtualMachine != null) {
                return "volume"
            }
            Volume.withTransaction {
                def response = volumeServer().deleteVolume(volumeId);  
                if (response.success == "true") {
//                        Volume currentVolume = Volume.findByVolumeReferenceId(volumeId);
//                        currentVolume.deleted = true;                   
//                        currentVolume.save(flush: true, failOnError:true);   
                    def snapjob = SnapshotPolicy.findAllWhere(deleted: false, volume: volume); 
                    for(def job: snapjob) { 
                        job.deleted = true;
                        job.save(flush: true); 
                    }
                    log.info("volume delete success for account: ${account.id} for volume: ${volume.id}")
                    return GeneralConstants.RESULT_SUCCESS
                } else {
                    log.info("volume delete failed for account: ${account.id} for volume: ${volume.id}")
                    return GeneralConstants.RESULT_FAILURE
                }

            }  
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            log.info("volume delete failed for account: ${account.id} for volume: ${volume.id} due to ${ex}")
            throw ex;
        } 
    } 
    
    def updateStorageName() {
        try {                
            def volume = Volume.findAll();
            for(int index = 0; index < volume.size(); index++ ) {              
                def volumeInfo = volume[index];                                    
                HashMap<String, String> optional = new HashMap<String,String>();            
                optional.put("id", volumeInfo.volumeReferenceId);
                def response = volumeServer().listVolumes(optional);           
                if(response) {                
                    for(Iterator<VolumeResponse> iter = response.volumes.iterator(); iter.hasNext();) {
                        def data = iter.next();      
                        if(data) {                        
                            HashMap<String, String> volumes = new HashMap<String,String>();                                            
                            Volume newVolume = Volume.findByVolumeReferenceId(data.id);                               
                            newVolume.storageName = data.storage;
                            newVolume.save(flush: true); 
                            if (newVolume.hasErrors()) {
                                throw new ValidationException(newVolume.errors.allErrors);
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
