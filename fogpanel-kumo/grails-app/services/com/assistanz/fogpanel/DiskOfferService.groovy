package com.assistanz.fogpanel

import com.assistanz.cloud.cloudstack.diskoffering.DiskOfferingResponse
import com.assistanz.cloud.cloudstack.CloudStackServer
import com.assistanz.cloud.cloudstack.diskoffering.CSDiskOfferingService
import grails.converters.JSON
import org.codehaus.groovy.grails.commons.ApplicationHolder
import org.springframework.context.MessageSource
import org.apache.commons.logging.LogFactory;
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
                and {
                    eq("zone", Zone.get(zoneId)) 
                } 
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
    
    def pullDiskOfferFromCloudStack() {
        
        try {
                        
            def user = springSecurityService.currentUser
            def role = springSecurityService.getPrincipal().getAuthorities()             
            
            if(role.iterator().next() == "ROLE_ADMIN" ) {
                def response = diskServer().listDiskOfferings();
                
                for(Iterator<DiskOfferingResponse> iter = response.diskOfferings.iterator(); iter.hasNext();) {
                    def data = iter.next();

                   DiskOffer diskOffer = DiskOffer.findByDiskOfferReferenceId(data.diskOfferingId);
                    if (!diskOffer) {
                                   
                        diskOffer  = new DiskOffer();
                        diskOffer.diskOfferReferenceId = data.diskOfferingId
                        diskOffer.name = data.diskOfferingName
                        diskOffer.description = data.displayText
                        diskOffer.size = Integer.parseInt(data.diskSize)  
                        Boolean customized = new Boolean(data.isCustomized);
                        diskOffer.customized = customized
                        diskOffer.tags = data.diskOfferingTags
                        diskOffer.orderNo = 1
                        diskOffer.deleted = false

                        diskOffer.minSize = 1
                        diskOffer.maxSize = 2048
                        diskOffer.storageType = data.storageType  
                        
                        
//                        diskOffer.isCustomizedIops = requestData.isCustomizedIops
//                        diskOffer.qoSType = requestData.qoSType
                        diskOffer.diskReadRateBPS = data.diskReadRateBPS
                        diskOffer.diskWriteRateBPS = data.diskWriteRateBPS
                        diskOffer.diskReadRateIOPS = data.diskReadRateIOPS
                        diskOffer.diskWriteRateIOPS = data.diskWriteRateIOPS 
                                                                      
                        if(data.minIOPS || data.maxIOPS) {
                            diskOffer.qoSType = "storage"
                            diskOffer.isCustomizedIops = false
                        } else if(data.diskReadRateBPS || data.diskWriteRateBPS  || data.diskReadRateIOPS || data.diskWriteRateIOPS ) {
                            diskOffer.qoSType = "hypervisor"
                            diskOffer.isCustomizedIops = false
                        } else if(!data.minIOPS && !data.minIOPS) {
                             diskOffer.isCustomizedIops = true
                             diskOffer.qoSType = "storage"
                        }
                        diskOffer.minIOPS = data.minIOPS
                        diskOffer.maxIOPS = data.maxIOPS
                        if(data.diskOfferingDomainId) {
                            diskOffer.isPublic = false
                            diskOffer.domain = Domain.findByReferenceId(data.diskOfferingDomainId) 
                        } else {
                            diskOffer.isPublic = true
                        }
//                        diskOffer.hypervisorSnapReserve = requestData.hypervisorSnapReserve
                        diskOffer.zone = Zone.findAll()[0]
                        diskOffer.cluster = Cluster.findByZone(diskOffer.zone)
                        diskOffer.pod = Pod.findByZone(diskOffer.zone)
                        
                        
                        diskOffer.addToDiskOfferZoneCosts(new DiskOfferZoneCost(
                        zone:diskOffer.zone, 
                        cost: 0.0,
                        minimumCost:0.0))     
                        
                        diskOffer.save(flush: true)  
                        if (!diskOffer.save()) {
                            diskOffer.errors.allErrors.each { println it }
                        }  
                        

                        log.info("Disk offer: ${diskOffer.diskOfferReferenceId} is added from cloud stack by ${user.username}") 

                    }
                } 
            } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") { 
            
                throw new Exception("Access Denied");
            
            }
        }  catch(Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
        
    }
    
    
    def getByComputingOffer(String clusterReferenceId, String tags, Boolean customized, storageType) {
         
        def diskOffer = DiskOffer.withCriteria {
            eq("deleted", false)
            and {
                eq("cluster", Cluster.findByClusterReferenceId(clusterReferenceId)) 
            }
            and {
               eq("customized", customized) 
            }
            and {
               eq("available", true) 
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
        try { 
            def diskOfferingQuery = DiskOffer.createCriteria()
            def diskOfferingResult;            
            
//            def response = diskServer().listDiskOfferings();
//
//            for(Iterator<DiskOfferingResponse> iter = response.diskOfferings.iterator(); iter.hasNext();) {
//                def data = iter.next();
//
//                DiskOffer diskOffer = DiskOffer.findByDiskOfferReferenceId(data.diskOfferingId);
//                if (!diskOffer) {
//                    diskOffer  = new DiskOffer()
//                    diskOffer.diskOfferReferenceId = data.diskOfferingId
//                    diskOffer.name = data.diskOfferingName
//                    diskOffer.description = data.displayText
//                    diskOffer.size = data.diskSize
//                    Boolean customized = new Boolean(data.isCustomized);
//                    diskOffer.customized = customized
//                    diskOffer.tags = data.diskOfferingTags
//                    diskOffer.orderNo = 1
//                    diskOffer.deleted = false
//                    diskOffer.save(flush: true)                    
//                }                                        
//            }   
            if((clusterReferenceId == "null" || clusterReferenceId == null) && (name == "null" || name == null) && (zoneReferenceId == "null" || zoneReferenceId == null) && (diskOfferReferenceId == "null" || diskOfferReferenceId == null) && (custom == "null" || custom == null)) {
               
                return DiskOffer.findAll("from DiskOffer as diskOffer where diskOffer.deleted=?", [false]);
                
            } else if((clusterReferenceId != "null" || clusterReferenceId != null) && (name == "null" || name == null) && (zoneReferenceId == "null" || zoneReferenceId == null) && (diskOfferReferenceId == "null" || diskOfferReferenceId == null) && (custom == "null" || custom == null)) {
                
                return DiskOffer.findAllWhere(deleted: false, cluster: Cluster.findByClusterReferenceId(clusterReferenceId))
                
            } else if((clusterReferenceId == "null" || clusterReferenceId == null) && (name != "null" || name != null) && (zoneReferenceId == "null" || zoneReferenceId == null) && (diskOfferReferenceId == "null" || diskOfferReferenceId == null) && (custom == "null" || custom == null)) {
                
                diskOfferingResult = diskOfferingQuery.list {
                    like("name", "%" + name + "%")
                    and {
                      eq("deleted", false)
                    }
                }
                
                return diskOfferingResult;
            } else if((clusterReferenceId != "null" || clusterReferenceId != null) && (name != "null" || name != null) && (zoneReferenceId == "null" || zoneReferenceId == null) && (diskOfferReferenceId == "null" || diskOfferReferenceId == null) && (custom == "null" || custom == null)) {

                 diskOfferingResult = diskOfferingQuery.list {
                    like("name", "%" + name + "%")
                    and {
                        eq("deleted", "false")
                    }
                    and {           
                        eq("cluster", Cluster.findByClusterReferenceId(clusterReferenceId))
                    }
                }
                
                return diskOfferingResult;
            } else if((custom == "null" || custom == null) && (zoneReferenceId != "null" || zoneReferenceId != null) && (clusterReferenceId == "null" || clusterReferenceId == null) && (name == "null" || name == null) && (diskOfferReferenceId == "null" || diskOfferReferenceId == null)) {
               
                 diskOfferingResult = diskOfferingQuery.list {
                    
                    eq("deleted", false)
                    and {
                        eq("zone", Zone.findByReferenceZoneId(zoneReferenceId))
                    } 
                    and {
                        eq("customized", false)
                    }
                    order("size", "asc")
                }
                return diskOfferingResult;
            } else if((diskOfferReferenceId != "null" || diskOfferReferenceId != null) && (clusterReferenceId == "null" || clusterReferenceId == null) && (name == "null" || name == null) && (zoneReferenceId == "null" || zoneReferenceId == null) && (custom == "null" || custom == null)) {
               
                return DiskOffer.findAllWhere(deleted: false, diskOfferReferenceId: diskOfferReferenceId)
              
            }  else if((zoneReferenceId != "null" || zoneReferenceId != null) && (custom != "null" || custom != null) && (diskOfferReferenceId == "null" || diskOfferReferenceId == null) && (clusterReferenceId == "null" || clusterReferenceId == null) && (name == "null" || name == null)) {
                
                return DiskOffer.findAllWhere(deleted: false, zone: Zone.findByReferenceZoneId(zoneReferenceId), customized: true)
              
            }
            else {
                return "ERROR"
            }  
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
    
    def update(DiskOffer diskOffer, String name, String description){
        try{
            
            licenseValidationService.authorizeAction(FogPanelService.OFFERING_UPDATE)
            
            // Create a HashMap which stores optional values
            HashMap<String,String> optional = new HashMap<String,String>();

            // Adding optional values to the HashMap
            optional.put("name", name);
            optional.put("displaytext", description);
           
            def response = diskServer().updateDiskOffering(diskOffer.diskOfferReferenceId, optional)            
            diskOffer.name = response.diskOfferingName
            diskOffer.description = response.displayText
            
            //save diskOffer
            diskOffer.save(flush: true);
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
            newDiskOffer.diskOfferReferenceId = response.diskOfferingId
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
