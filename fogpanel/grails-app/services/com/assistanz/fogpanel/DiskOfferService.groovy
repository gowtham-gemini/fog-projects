package com.assistanz.fogpanel

import com.assistanz.cloud.cloudstack.diskoffering.DiskOfferingResponse
import com.assistanz.cloud.cloudstack.CloudStackServer
import com.assistanz.cloud.cloudstack.diskoffering.CSDiskOfferingService
import grails.converters.JSON
import org.codehaus.groovy.grails.commons.ApplicationHolder
import org.springframework.context.MessageSource
import org.apache.commons.logging.LogFactory;
import com.assistanz.fogpanel.PullDiskOfferJob


class DiskOfferService {
    private static final log = LogFactory.getLog(this)
    MessageSource messageSource
    LicenseValidationService licenseValidationService
    def springSecurityService;
    
    def diskServer() {
        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret
    
        CloudStackServer server =
                new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);            
                    
        CSDiskOfferingService csDiskOfferingService = new CSDiskOfferingService(server);  
        
        return csDiskOfferingService;
    }
    
    def listByZone(zoneId) {
        try {
            def diskOfferingQuery = DiskOffer.createCriteria()
            def diskOfferingResult = diskOfferingQuery.list {
                eq("deleted", false) 
            } 
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
    
    def count() {
        try {            
            ArrayList countList = new ArrayList(); 
            HashMap countItem = new HashMap();  
            def total = DiskOffer.findAllWhere(deleted: false);
            def disabled = DiskOffer.findAllWhere(deleted: false, available: false);
            def enabled = DiskOffer.findAllWhere(deleted: false, available: true);
            
            countItem.put("total", total.size());
            countItem.put("disabled", disabled.size());
            countItem.put("enabled", enabled.size());               
            countList.add(countItem);
            return countList;
        } catch(Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
        
    }
    def getCurrentDisk (id) {
         try {              
            def currency = ApplicationHolder.getApplication().config.billing.defaultCurrency
            def diskOffer = DiskOffer.get(id);            
            
            def diskOfferZoneQuery = DiskOfferZoneCost.createCriteria()
            def diskOfferZoneCostResult = diskOfferZoneQuery.list {
               eq("diskOffer", diskOffer)    
               order("id", "desc")                
            }
            
            ArrayList diskOfferArrayList = new ArrayList(); 
            ArrayList diskOfferZoneCostArrayList = new ArrayList(); 
            
            HashMap diskOfferItem = new HashMap();  
            diskOfferItem.put("available", diskOffer.available)
            diskOfferItem.put("currency", currency)
            diskOfferItem.put("customized", diskOffer.customized)
            diskOfferItem.put("deleted", diskOffer.deleted)
            diskOfferItem.put("description", diskOffer.description)
            diskOfferItem.put("diskOfferReferenceId", diskOffer.diskOfferReferenceId)            
            diskOfferItem.put("diskReadRateBPS", diskOffer.diskReadRateBPS)            
            diskOfferItem.put("diskReadRateIOPS", diskOffer.diskReadRateIOPS)
            diskOfferItem.put("diskWriteRateBPS", diskOffer.diskWriteRateBPS)
            diskOfferItem.put("diskWriteRateIOPS", diskOffer.diskWriteRateIOPS)
            diskOfferItem.put("domainId", diskOffer.domainId)
            diskOfferItem.put("hypervisorSnapReserve", diskOffer.hypervisorSnapReserve)
            diskOfferItem.put("id", diskOffer.id)
            diskOfferItem.put("isCustomizedIops", diskOffer.isCustomizedIops)
            diskOfferItem.put("isPublic", diskOffer.isPublic)
            diskOfferItem.put("maxIOPS", diskOffer.maxIOPS)
            diskOfferItem.put("maxSize", diskOffer.maxSize)
            diskOfferItem.put("minIOPS", diskOffer.minIOPS)
            diskOfferItem.put("minSize", diskOffer.minSize)
            diskOfferItem.put("name", diskOffer.name)
            diskOfferItem.put("qoSType", diskOffer.qoSType)
            diskOfferItem.put("size", diskOffer.size)
            diskOfferItem.put("storageType", diskOffer.storageType)
            diskOfferItem.put("tags", diskOffer.tags)
            
            for(def diskOfferZone: diskOfferZoneCostResult) {                
                HashMap diskOfferZoneItem = new HashMap();  
                diskOfferZoneItem.put("diskOffer", "")
                diskOfferZoneItem.put("cost", diskOfferZone.cost)
                diskOfferZoneItem.put("currency", currency)
                
                diskOfferZoneItem.put("id", diskOfferZone.id)
                diskOfferZoneItem.put("minimumCost", diskOfferZone.minimumCost)                
                diskOfferZoneItem.put("zone", diskOfferZone.zone)
                
                diskOfferZoneCostArrayList.add(diskOfferZoneItem)
            }                        
            diskOfferItem.put("diskOfferZoneCosts", diskOfferZoneCostArrayList)
            diskOfferArrayList.add(diskOfferItem)            
            return diskOfferArrayList;
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
    
    
    def pullDiskOfferFromCloudStackJobStart() {
        
        PullDiskOfferJob.triggerNow([id:"diskOffer"])
            
        return "OK"
    }
    
    
    def pullDiskOfferFromCloudStack(jobid) {
        
        def job = AsynchronousJobs.get(jobid)
        try { 
                        
            job.status = JobStatus.valueOf("RUNNING")
            job.startedAt = new Date()
            job.save(flush: true)

            def response = diskServer().listDiskOfferings();
                
            for(Iterator<DiskOfferingResponse> iter = response.diskOfferings.iterator(); iter.hasNext();) {
                def data = iter.next();

               DiskOffer diskOffer = DiskOffer.findByDiskOfferReferenceId(data.id);
                if (!diskOffer) {

                    diskOffer  = new DiskOffer();
                    diskOffer.diskOfferReferenceId = data.id
                    diskOffer.name = data.name
                    diskOffer.description = data.displayText
                    diskOffer.size = Integer.parseInt(data.diskSize)  
                    Boolean customized = new Boolean(data.isCustomized);
                    diskOffer.customized = customized
                    diskOffer.tags = data.tags
                    diskOffer.orderNo = 1
                    diskOffer.deleted = false

                    diskOffer.minSize = 1
                    diskOffer.maxSize = 2048
                    diskOffer.storageType = data.storageType  
 
                    diskOffer.diskReadRateBPS = data.diskBytesReadRate
                    diskOffer.diskWriteRateBPS = data.diskBytesWriteRate
                    diskOffer.diskReadRateIOPS = data.diskIopsReadRate
                    diskOffer.diskWriteRateIOPS = data.diskIopsWriteRate  

                    if(data.minIops || data.maxIops) { 
                        diskOffer.qoSType = "storage"
                        diskOffer.isCustomizedIops = false
                    } else if(data.diskBytesReadRate || data.diskBytesWriteRate  || data.diskIopsReadRate || data.diskIopsWriteRate ) {
                        diskOffer.qoSType = "hypervisor"
                        diskOffer.isCustomizedIops = false
                    } else if(!data.minIops && !data.minIops) {
                         diskOffer.isCustomizedIops = true
                         diskOffer.qoSType = "storage"
                    }
                    diskOffer.minIOPS = data.minIops 
                    diskOffer.maxIOPS = data.maxIops
                    if(data.domainId) {
                        diskOffer.isPublic = false 
                        diskOffer.domain = Domain.findByReferenceId(data.domainId) 
                    } else {
                        diskOffer.isPublic = true
                    }
//                        diskOffer.hypervisorSnapReserve = requestData.hypervisorSnapReserve

                    def zoneList = Zone.findAll()

                    for(def zone: zoneList) {

                        diskOffer.addToDiskOfferZoneCosts(new DiskOfferZoneCost(
                        zone:zone, 
                        cost: 0.0,
                        minimumCost:0.0))
                    }

                    diskOffer.save(flush: true)  
                    if (!diskOffer.save()) {
                        diskOffer.errors.allErrors.each { println it }
                    }  
                    

                } else if(diskOffer) {
                                        
                    diskOffer.name = data.name
                    diskOffer.description = data.displayText
                    diskOffer.save(flush: true)  
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
    
    
//    def pullDiskOfferFromCloudStack() {
//        
//        try {
//                        
//            def user = springSecurityService.currentUser
//            def role = springSecurityService.getPrincipal().getAuthorities()             
//            
//            if(role.iterator().next() == "ROLE_ADMIN" ) {
//                
//            } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") { 
//            
//                throw new Exception("Access Denied");
//            
//            }
//        }  catch(Exception ex) {
//            ex.printStackTrace(System.err);
//            throw ex;
//        }
//        
//    }
    
    
    def getByComputingOffer(String clusterReferenceId, String tags, Boolean customized, storageType) {
         
        def user = springSecurityService.currentUser
        def role = springSecurityService.getPrincipal().getAuthorities() 
        
        def diskOffer = DiskOffer.withCriteria {
            eq("deleted", false)
            and {
               eq("customized", customized) 
            }
            and {
                if(role.iterator().next() == "ROLE_ADMIN" ) {

                } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") { 
                    eq("available", true)   
                }
            }
            and {
                if(storageType == null || storageType == "") {
                    isNull("storageType")
                } else {
                     eq("storageType", storageType)
                } 
            }
            and {
                if(tags == null || tags == "") {
                    isNull("tags")
                } else {
                    eq("tags", tags)
                } 
            }
        }
        
//        if(storageType == null || storageType == "null") {                 
//            diskOffer = DiskOffer.findAllWhere(deleted: false, cluster: Cluster.findByClusterReferenceId(clusterReferenceId), tags: tags, customized: customized, available: true)
//        } else {            
//            diskOffer = DiskOffer.findAllWhere(deleted: false, cluster: Cluster.findByClusterReferenceId(clusterReferenceId), tags: tags, customized: customized, available: true, storageType: storageType)
//        }                        
        return diskOffer;
    }
    def list(String clusterReferenceId, String zoneReferenceId, String name, String diskOfferReferenceId, custom) {
        
        def user = springSecurityService.currentUser
        def role = springSecurityService.getPrincipal().getAuthorities() 
        
        try { 
            def diskOfferingQuery = DiskOffer.createCriteria()
                       
            def diskOfferingResult = DiskOffer.withCriteria { 
                eq("deleted", false)
                if(custom == "null" || custom == null) {                           
                } else if(custom == "true" || custom == true)  {
                    and {
                        eq("customized", true) 
                    }
                } else {
                    and {
                        eq("customized", false) 
                    }
                }
                and {
                    if(role.iterator().next() == "ROLE_ADMIN" ) {

                    } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") { 
                        eq("available", true)   
                    }
                }
                if(name == "null" || name == null) {      
                        
                } else {
                    and {
                         like("name", "%" + name + "%") 
                     }   
                }
                if(diskOfferReferenceId == "null" || diskOfferReferenceId == null) {      
                        
                } else {
                    and {
                         like("diskOfferReferenceId", diskOfferReferenceId) 
                     }   
                }
                
            }            
            return diskOfferingResult;
            
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
    
    def update(requestData, String name, String description) {
        try{
            
            licenseValidationService.authorizeAction(FogPanelService.OFFERING_UPDATE)
            
            def user = springSecurityService.currentUser
            def role = springSecurityService.getPrincipal().getAuthorities() 
            
            def diskOffer =  DiskOffer.get(requestData.id);   
            
            for(int i = 0; i < requestData.zoneCosts.length(); i++) {

                Double cost = Math.round(new Double(requestData.zoneCosts[i].cost)*100000)/100000;
                Double minimumCost = new Double(requestData.zoneCosts[i].minimumCost);
                                
                DiskOfferZoneCost existsDiskOfferZoneCost = DiskOfferZoneCost.findWhere(diskOffer: DiskOffer.get(requestData.id), zone:Zone.get(requestData.zoneCosts[i].zoneId));
                       
                if(existsDiskOfferZoneCost) {
                    def oldCost = existsDiskOfferZoneCost.cost

                    existsDiskOfferZoneCost.cost = cost
                    existsDiskOfferZoneCost.minimumCost = minimumCost
                    existsDiskOfferZoneCost.save(flush: true)

                    if(oldCost != existsDiskOfferZoneCost.cost) {

                        def serviceCostChangeLog = new ServiceCostChangeLog()
                        serviceCostChangeLog.serviceName = ServiceName.valueOf("DISK_OFFER")
                        serviceCostChangeLog.oldCost = oldCost
                        serviceCostChangeLog.changedDate = new Date()

                        serviceCostChangeLog.user = user
                        serviceCostChangeLog.account = user.account

                        serviceCostChangeLog.newCost = existsDiskOfferZoneCost.cost
                        serviceCostChangeLog.diskOfferZoneCost = existsDiskOfferZoneCost;
                        serviceCostChangeLog.save(flush: true)            
            //            if (!serviceCostChangeLog.save()) {
            //                serviceCostChangeLog.errors.allErrors.each { println it }
            //            }             
                    }
                } else {
                    diskOffer.addToDiskOfferZoneCosts(new DiskOfferZoneCost(
                        zone:Zone.get(requestData.zoneCosts[i].zoneId), 
                        cost: cost,
                        minimumCost:minimumCost))       
                }    
            }
            
            //Create a HashMap which stores optional values
            HashMap<String,String> optional = new HashMap<String,String>();

            // Adding optional values to the HashMap
            optional.put("name", name);
            optional.put("displaytext", description);
           
            def response = diskServer().updateDiskOffering(diskOffer.diskOfferReferenceId, optional)            
            diskOffer.name = response.name
            diskOffer.description = response.displayText
            
            //save diskOffer
            diskOffer.save(flush: true);
                        
            def newCost = DiskOfferZoneCost.findByDiskOffer(diskOffer)
                        
            log.info("Updated disk offer : ${diskOffer.diskOfferReferenceId}, successfully")         
            if (diskOffer.hasErrors()) {
                throw new ValidationException(diskOffer.errors.allErrors);
            }                     
        }catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
    def checkCustom(clusterReferenceId, tags, storageType) {
        def customDisk = DiskOffer.findAllWhere(cluster: Cluster.findByClusterReferenceId(clusterReferenceId),  tags: tags, customized: true, deleted: false, storageType: storageType)
        
        def result;       
        if(customDisk.size() == 0) {           
            result = GeneralConstants.RESULT_FALSE
        } else {           
            result = GeneralConstants.RESULT_TRUE
        }
        ArrayList<ArrayList<String>> resultList = new ArrayList<ArrayList<String>>();            
        HashMap<String,String> option = new HashMap<String,String>();                                   
        option.put("result", result);
        resultList.add(option);           
        return resultList 
    }
    def create(DiskOffer newDiskOffer) {
        try{
            
            licenseValidationService.authorizeAction(FogPanelService.OFFERING_ADD)
            
            // Create a HashMap which stores optional values
            HashMap<String,String> optional = new HashMap<String,String>();
            
             if(new Boolean(newDiskOffer.customized).toString() == "true" && newDiskOffer.size != 0) {
                 throw new Exception("volumes are available with this disk Offer.");
             }
            
            // Adding optional values to the HashMap
            if(new Boolean(newDiskOffer.customized).toString() == "true") {
                optional.put("customized", new Boolean(newDiskOffer.customized).toString());
                newDiskOffer.size = 0
            } else if(new Boolean(newDiskOffer.customized).toString() == "false") {
                optional.put("disksize", Integer.toString(newDiskOffer.size));
            }
            if(newDiskOffer.tags == "null" || newDiskOffer.tags == "" || newDiskOffer.tags == null){
//                optional.put("hosttags", "");
            } else {
                optional.put("tags", new String(newDiskOffer.tags));
            }
            
            if(newDiskOffer.storageType == "null" || newDiskOffer.storageType == "" || newDiskOffer.storageType == null){
                optional.put("storagetype", "Shared");
            } else {
                optional.put("storagetype", new String(newDiskOffer.storageType));
            }
            
            if(newDiskOffer.qoSType == "hypervisor") {
                if(newDiskOffer.diskReadRateBPS == "null" || newDiskOffer.diskReadRateBPS == "" || newDiskOffer.diskReadRateBPS == null){
    //                optional.put("hosttags", "");
                } else {
                     optional.put("bytesreadrate", new String(newDiskOffer.diskReadRateBPS));
                }
                 if(newDiskOffer.diskWriteRateBPS == "null" || newDiskOffer.diskWriteRateBPS == "" || newDiskOffer.diskWriteRateBPS == null){
    //                optional.put("hosttags", "");
                } else {
                     optional.put("byteswriterate", new String(newDiskOffer.diskWriteRateBPS));
                }
                 if(newDiskOffer.diskReadRateIOPS == "null" || newDiskOffer.diskReadRateIOPS == "" || newDiskOffer.diskReadRateIOPS == null){
    //                optional.put("hosttags", "");
                } else {
                     optional.put("iopsreadrate", new String(newDiskOffer.diskReadRateIOPS));
                }
                if(newDiskOffer.diskWriteRateIOPS == "null" || newDiskOffer.diskWriteRateIOPS == "" || newDiskOffer.diskWriteRateIOPS == null){
    //                optional.put("hosttags", "");
                } else {
                     optional.put("iopswriterate", new String(newDiskOffer.diskWriteRateIOPS));
                }
            } else if(newDiskOffer.qoSType == "storage") {
                optional.put("customizediops", new Boolean(newDiskOffer.isCustomizedIops).toString());
                
                if(new Boolean(newDiskOffer.isCustomizedIops).toString() == "false") {
                    
                    if(newDiskOffer.minIOPS == "null" || newDiskOffer.minIOPS == "" || newDiskOffer.minIOPS == null){
        //                optional.put("hosttags", "");
                    } else {
                         optional.put("miniops", new String(newDiskOffer.minIOPS));
                    } 
                    if(newDiskOffer.maxIOPS == "null" || newDiskOffer.maxIOPS == "" || newDiskOffer.maxIOPS == null){
        //                optional.put("hosttags", "");
                    } else {
                         optional.put("maxiops", new String(newDiskOffer.maxIOPS));
                    }  
                }
            }
            
            if(newDiskOffer.isPublic == true || newDiskOffer.isPublic == "true") {
                
            } else {
                optional.put("domainid", new String(newDiskOffer.domain.referenceId)); 
            }
            
            
//            optional.put("hypervisorsnapshotreserve", newDiskOffer.hypervisorSnapReserve);
            def response = diskServer().createDiskOffering(newDiskOffer.description, newDiskOffer.name, optional)          
            newDiskOffer.deleted = false
            newDiskOffer.orderNo = 1;
            newDiskOffer.diskOfferReferenceId = response.id
            newDiskOffer.orderNo ++;
            //save diskOffer
            newDiskOffer.save(flush: true);
            if (newDiskOffer.hasErrors()) {
                throw new ValidationException(newDiskOffer.errors.allErrors);
            }
            ArrayList<ArrayList<String>> diskOfferList = new ArrayList<ArrayList<String>>();            
                HashMap<String,String> diskOffer = new HashMap<String,String>();                
                    diskOffer.put("diskOffer", newDiskOffer);
                    diskOffer.put("result", "OK");
                    diskOfferList.add(diskOffer);
            log.info("Created disk offer : ${newDiskOffer.diskOfferReferenceId}, successfully") 
            return diskOfferList;
        }catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }        
    }
    
    def delete(String id, String forced) {
        try {
            
            licenseValidationService.authorizeAction(FogPanelService.OFFERING_DELETE)
            
            def diskOffer = DiskOffer.get(id);            
            def volume = Volume.findAll("from Volume as volume where volume.diskOffer=?", [diskOffer]);

            if(forced == "true") {
                diskOffer.deleted = true;
                diskOffer.save(flush: true)
                if (diskOffer.hasErrors()) {
                    throw new ValidationException(diskOffer.errors.allErrors);
                }       
                log.info("Disk offer: ${diskOffer.diskOfferReferenceId} is deleted successfully") 
                ["OK"]
            } else {                
                if(volume.size() == 0) {                     
                    def response = diskServer().deleteDiskOffering(diskOffer.diskOfferReferenceId)                    
                    if(response.success == "true") {
                        diskOffer.deleted = true;
                        diskOffer.save(flush: true)
                        if (diskOffer.hasErrors()) {
                            throw new ValidationException(diskOffer.errors.allErrors);
                        } 
                        log.info("Disk offer: ${diskOffer.diskOfferReferenceId} is deleted successfully") 
                        ["OK"]                                  
                    }                    
                } else {
                    def language;
                    Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
                    language = defaultLanguage.value                   
                    throw new Exception(messageSource.getMessage('admin.diskOffer.volumeExistMsg', null, new Locale(language)));                    
                }
            }                                            
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }           
    }
    
     def disable(String id, String status) {
        try { 
            
            licenseValidationService.authorizeAction(FogPanelService.OFFERING_DISABLE)

            def diskOffer = DiskOffer.get(id)             
            if(status == "disable") {
                diskOffer.available = false
                diskOffer.save(flush: true)
            } else if(status == "enable") {
                diskOffer.available = true
                diskOffer.save(flush: true)
            }
            
            if (diskOffer.hasErrors()) {
                throw new ValidationException(diskOffer.errors.allErrors);
            } 
            log.info("Disk offer: ${diskOffer.diskOfferReferenceId} is ${status}d") 
            ["OK"]   
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
    }
}
