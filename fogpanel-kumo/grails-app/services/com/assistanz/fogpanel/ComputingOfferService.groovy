package com.assistanz.fogpanel

import java.io.UnsupportedEncodingException;
import org.apache.commons.httpclient.NameValuePair;
import java.net.URLEncoder;
import java.util.HashMap;
import com.assistanz.cloud.cloudstack.CloudStackServer
import com.assistanz.cloud.cloudstack.serviceoffering.CSServiceOfferingService
import com.assistanz.cloud.cloudstack.ServiceOfferingResponse
import grails.converters.JSON
import org.codehaus.groovy.grails.commons.ApplicationHolder
import org.springframework.context.MessageSource
import org.apache.commons.logging.LogFactory;
class ComputingOfferService {
    private static final log = LogFactory.getLog(this)
    MessageSource messageSource
    static transactional = true;
    def springSecurityService;
    LicenseValidationService licenseValidationService
    
    def computingOfferServer() {
        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret
    
        CloudStackServer server =
                new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);

        CSServiceOfferingService csServiceOfferingService = new CSServiceOfferingService(server);
        
        return csServiceOfferingService;
    }
    
    
    def listByZone(zoneId) {
        try {   
            def computingOfferQuery = ComputingOffer.createCriteria()
            def computingOfferResult = computingOfferQuery.list {
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
    def listByCluster(String clusterReferenceId, String baseOs, String tags, String type, String templateReferenceId) { 
        def template = Template.findByTemplateReferenceId(templateReferenceId)
        def computingOfferQuery = ComputingOffer.createCriteria()
        
        def computingOfferResult = ComputingOffer.withCriteria {
            eq("deleted", false) 
            and {
                eq("cluster", Cluster.findByClusterReferenceId(clusterReferenceId)) 
            } 
            and {
                eq("baseOs", baseOs)
            } 
            and {
                ge("cpuNumber", template.minimumCpu.toDouble()) 
            }
            and {
                ge("memory", template.minimumRam.toDouble()) 
            } 
            and {
                eq("hypervisor", template.hypervisor) 
            } 
            and {
                if(tags == "" || tags == null) {
                    isNull("tags")
                } else {
                    ge("tags", tags) 
                }
            } 
            and {
                eq("available", true)                
            } 
            and {
                eq("storageType", type)                
            }
            order("orderNo", "asc")
        }
        
//        def computingOffer = ComputingOffer.findAllWhere(deleted: false, cluster: Cluster.findByClusterReferenceId(clusterReferenceId), tags: tags,storageType: type, available: true,baseOs: baseOs)
        return computingOfferResult;
    }
    
    
    def pullComputeOfferFromCloudStack() {
        
        try {
            
            
            def user = springSecurityService.currentUser
            def role = springSecurityService.getPrincipal().getAuthorities()             
            
            if(role.iterator().next() == "ROLE_ADMIN" ) {
                def response = computingOfferServer().listServiceOfferings();
                for(Iterator<ServiceOfferingResponse> iter = response.serviceOfferings.iterator(); iter.hasNext();) {
                    def data = iter.next();

                    ComputingOffer computingOffer = ComputingOffer.findByOfferReferenceId(data.serviceOfferingId);
                    if (!computingOffer) {
                        computingOffer  = new ComputingOffer();
                        computingOffer.offerReferenceId = data.serviceOfferingId;
                        computingOffer.name = data.serviceOfferingName;
                        computingOffer.description = data.displayText;
                        computingOffer.bandWidth = 1000
                        computingOffer.coreCpuSpeed = data.cpuSpeed;
                        computingOffer.cpuNumber = Double.parseDouble(data.cpuNumber);
                        computingOffer.memory = Double.parseDouble(data.memory);
                        Boolean defaultUse = new Boolean(data.defaultUse);
                        computingOffer.defaultUse = defaultUse;
                        computingOffer.hostTags = data.serviceOfferingHostTags;
                        computingOffer.diskIO = "Good";
                        Boolean isSystem = new Boolean(data.isSystem);
                        computingOffer.isSystem = isSystem;
                        Boolean limitCpuUse = new Boolean(data.limitCpuUse);
                        computingOffer.limitCpuUse = limitCpuUse;
                        computingOffer.networkRate = data.serviceOfferingNetworkRate;
                        Boolean offeringHa = new Boolean(data.serviceOfferingHa);
                        computingOffer.offerHA = offeringHa;
                        computingOffer.storageType = data.storageType;
                        computingOffer.tags = data.serviceOfferingTags;
                        computingOffer.available = true;
                        computingOffer.deleted = false;
                        computingOffer.orderNo = 1;
                        computingOffer.baseOs = "Linux"                   
                        computingOffer.hypervisor = "XenServer"

                        computingOffer.zone = Zone.findAll()[0]
                        computingOffer.cluster = Cluster.findByZone(computingOffer.zone)
                        computingOffer.pod = Pod.findByZone(computingOffer.zone)

                        computingOffer.diskReadRateBPS = data.diskReadRateBPS
                        computingOffer.diskWriteRateBPS = data.diskWriteRateBPS
                        computingOffer.diskReadRateIOPS = data.diskReadRateIOPS
                        computingOffer.diskWriteRateIOPS = data.diskWriteRateIOPS

                        computingOffer.addToComputingOfferZoneCosts(new ComputingOfferZoneCost(
                            zone:computingOffer.zone, 
                            cost: 0.0,
                            setupCost:0.0,
                            minimumCost:0.0))   


                        computingOffer.save(flush: true);

                        log.info("Computing offer: ${computingOffer.offerReferenceId} is added from cloud stack by ${user.username}") 

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
    
    def list(String clusterReferenceId, String zoneReferenceId, String name, String baseOs, String templateReferenceId, storageType) {        
        def computingOfferQuery = ComputingOffer.createCriteria()
        def computingOfferResult;
        try {
//            def response = computingOfferServer().listServiceOfferings();
//            
//            for(Iterator<ServiceOfferingResponse> iter = response.serviceOfferings.iterator(); iter.hasNext();) {
//                def data = iter.next();
//
//                ComputingOffer computingOffer = ComputingOffer.findByOfferReferenceId(data.serviceOfferingId);
//                if (!computingOffer) {
//                    computingOffer  = new ComputingOffer();
//                    computingOffer.offerReferenceId = data.serviceOfferingId;
//                    computingOffer.name = data.serviceOfferingName;
//                    computingOffer.description = data.displayText;
//                    computingOffer.coreCpuSpeed = data.cpuSpeed;
//                    computingOffer.cpuNumber = data.cpuNumber;
//                    computingOffer.memory = data.memory;
//                    Boolean defaultUse = new Boolean(data.defaultUse);
//                    computingOffer.defaultUse = defaultUse;
//                    computingOffer.hostTags = data.serviceOfferingHostTags;
//                    Boolean isSystem = new Boolean(data.isSystem);
//                    computingOffer.isSystem = isSystem;
//                    Boolean limitCpuUse = new Boolean(data.limitCpuUse);
//                    computingOffer.limitCpuUse = limitCpuUse;
//                    computingOffer.networkRate = data.serviceOfferingNetworkRate;
//                    Boolean offeringHa = new Boolean(data.serviceOfferingHa);
//                    computingOffer.offerHA = offeringHa;
//                    computingOffer.storageType = data.storageType;
//                    computingOffer.tags = data.serviceOfferingTags;
//                    computingOffer.available = true;
//                    computingOffer.deleted = false;
//                    computingOffer.orderNo = 1;
//                    computingOffer.save(flush: true);
//                }
//            } 
            if((clusterReferenceId == "null" || clusterReferenceId == null) && (name == "null" || name == null) && (zoneReferenceId == "null" || zoneReferenceId == null) && (baseOs == "null" || baseOs == null) && (templateReferenceId == "null" || templateReferenceId == null)) {  
                computingOfferResult = ComputingOffer.findAll("from ComputingOffer as computingOffer where computingOffer.deleted=?", [false]);   
            } else if((clusterReferenceId != "null" || clusterReferenceId != null) && (name == "null" || name == null) && (zoneReferenceId == "null" || zoneReferenceId == null) && (baseOs == "null" || baseOs == null) && (templateReferenceId == "null" || templateReferenceId == null)) {
                computingOfferResult = ComputingOffer.findAllWhere(deleted: false, cluster: Cluster.findByClusterReferenceId(clusterReferenceId))
            } else if((clusterReferenceId == "null" || clusterReferenceId == null) && (name != "null" || name != null) && (zoneReferenceId == "null" || zoneReferenceId == null) && (baseOs == "null" || baseOs == null) && (templateReferenceId == "null" || templateReferenceId == null)) {
                computingOfferResult = computingOfferQuery.list {
                    like("name", "%" + name + "%")
                    and {
                      eq("deleted", false)                       
                    }
                }
            } else if((clusterReferenceId != "null" || clusterReferenceId != null) && (name != "null" || name != null) && (zoneReferenceId == "null" || zoneReferenceId == null) && (baseOs == "null" || baseOs == null) && (templateReferenceId == "null" || templateReferenceId == null)) {
                computingOfferResult = computingOfferQuery.list {
                    like("name", "%" + name + "%")
                    and {
                       eq("deleted", false)
                       and {
                          eq("available", true)
                      } 
                    } 
                    and {
                        eq("cluster", Cluster.findByClusterReferenceId(clusterReferenceId))
                    }
                }
            } else if((zoneReferenceId != "null" || zoneReferenceId != null) && (clusterReferenceId == "null" || clusterReferenceId == null) && (name == "null" || name == null) && (baseOs == "null" || baseOs == null) && (templateReferenceId == "null" || templateReferenceId == null)) {
                computingOfferResult = ComputingOffer.findAllWhere(deleted: false, zone: Zone.findByReferenceZoneId(zoneReferenceId))
            } else if((baseOs != "null" || baseOs != null) && (clusterReferenceId == "null" || clusterReferenceId == null) && (name == "null" || name == null) && (zoneReferenceId == "null" || zoneReferenceId == null) && (templateReferenceId == "null" || templateReferenceId == null)) {
                computingOfferResult = ComputingOffer.findAllWhere(deleted: false, baseOs: baseOs)
            } else if((baseOs != "null" || baseOs != null) && (zoneReferenceId != "null" || zoneReferenceId != null) && (clusterReferenceId == "null" || clusterReferenceId == null) && (name == "null" || name == null) && (templateReferenceId == "null" || templateReferenceId == null)) {
                computingOfferResult = computingOfferQuery.list {
                eq("deleted", false) 
                and {
                    eq("zone", Zone.findByReferenceZoneId(zoneReferenceId)) 
                } 
                and {
                    eq("baseOs", baseOs)
                }
                order("orderNo", "asc")
            } 
            } else if((templateReferenceId != "null" || templateReferenceId != null) && (zoneReferenceId != "null" || zoneReferenceId != null) && (baseOs == "null" || baseOs == null) && (clusterReferenceId == "null" || clusterReferenceId == null) && (name == "null" || name == null)) {
                def template = Template.findByTemplateReferenceId(templateReferenceId)
                if((storageType != "null" || storageType != null)) {                  
                    computingOfferResult = computingOfferQuery.list {
                        eq("deleted", false) 
                        and {
                            eq("hypervisor", template.hypervisor) 
                        }
                        and {
                            eq("zone", Zone.findByReferenceZoneId(zoneReferenceId)) 
                        } 
                        and {
                            eq("baseOs", template.baseOs)
                        } 
                        and {
                            ge("cpuNumber", template.minimumCpu.toDouble()) 
                        }
                        and {
                            ge("memory", template.minimumRam.toDouble()) 
                        } 
                        and {
                            eq("available", true)                
                        } 
                        and {
                            eq("storageType", storageType)                
                        }
                        order("orderNo", "asc")
                    }
                } else {                    
                    computingOfferResult = computingOfferQuery.list {
                        eq("deleted", false) 
                            and {
                                eq("hypervisor", template.hypervisor) 
                            }
                            and {
                                eq("zone", Zone.findByReferenceZoneId(zoneReferenceId)) 
                            } 
                            and {
                                eq("baseOs", template.baseOs)
                            }
                            and {
                                ge("cpuNumber", template.minimumCpu.toDouble())
                            } 
                            and {
                                ge("memory", template.minimumRam.toDouble())
                            } 
                            and {
                                eq("available", true)                
                            }                
                           order("orderNo", "asc")
                       }
                   }                                 
               }
//            ArrayList<ArrayList<String>> computingOfferList = new ArrayList<ArrayList<String>>();            
//            for(int i=0; i < computingOfferResult.size(); i++) { 
//                def item = computingOfferResult[i]; 
//                HashMap computingOfferItem = new HashMap();  
//                    computingOfferItem.put("id", item.id);
//                    computingOfferItem.put("name", item.name);
//                    computingOfferItem.put("offerReferenceId", item.offerReferenceId);
//                    computingOfferItem.put("description", item.description);
//                    computingOfferItem.put("coreCpuSpeed", item.coreCpuSpeed);
//                    computingOfferItem.put("cpuNumber", item.cpuNumber);
//                    computingOfferItem.put("orderNo", item.orderNo);
//                    computingOfferItem.put("memory", item.memory);
//                    computingOfferItem.put("hostTags", item.hostTags);
//                    computingOfferItem.put("isSystem", item.isSystem);
//                    computingOfferItem.put("limitCpuUse", item.limitCpuUse);
//                    computingOfferItem.put("networkRate", item.networkRate);
//                    computingOfferItem.put("offerHA", item.offerHA);
//                    computingOfferItem.put("storageType", item.storageType);
//                    computingOfferItem.put("tags", item.tags);
//                    computingOfferItem.put("aliasZoneName", item.zone.aliasName);
//                    computingOfferItem.put("referenceZoneId", item.zone.referenceZoneId);
//                    computingOfferItem.put("pod", item.pod);
//                    computingOfferItem.put("cluster", item.cluster);
//                    computingOfferItem.put("bandWidth", item.bandWidth);
//                    computingOfferItem.put("cost", item.computingOfferZoneCosts);
//                    computingOfferList.add(computingOfferItem);
//            }
//            return computingOfferList;
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
    def count() {
        try {            
            ArrayList countList = new ArrayList(); 
            HashMap countItem = new HashMap();  
            def total = ComputingOffer.findAllWhere(deleted: false);
            def disabled = ComputingOffer.findAllWhere(deleted: false, available: false);
            def enabled = ComputingOffer.findAllWhere(deleted: false, available: true);
            
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
    def create(ComputingOffer newComputingOffer) {
        try {
            
            licenseValidationService.authorizeAction(FogPanelService.OFFERING_ADD)
            
//            String issystem = new Boolean(newComputingOffer.isSystem).toString();
            String limitcpuuse = new Boolean(newComputingOffer.limitCpuUse).toString();
            String offerha = new Boolean(newComputingOffer.offerHA).toString();

            HashMap<String,String> optional = new HashMap<String,String>();
//            optional.put("defaultuse", new String(newComputingOffer.defaultUse));
            if(newComputingOffer.hostTags == "null" || newComputingOffer.hostTags == "" || newComputingOffer.hostTags == null){
//                optional.put("hosttags", "");
            } else {
                 optional.put("hosttags", new String(newComputingOffer.hostTags));
            }
            if(newComputingOffer.tags == "null" || newComputingOffer.tags == "" || newComputingOffer.tags == null){
//                optional.put("hosttags", "");
            } else {
                optional.put("tags", new String(newComputingOffer.tags)); 
            }
//            optional.put("issystem", issystem);
            optional.put("limitcpuuse", limitcpuuse);
            if(newComputingOffer.networkRate == "null"){
            } else {
                 optional.put("networkrate", new String(newComputingOffer.networkRate));
            }
            optional.put("offerha", offerha);
//            optional.put("storagetype", new String(newComputingOffer.storageType));
            if(new String(newComputingOffer.storageType) == "null"){
                optional.put("storagetype","Shared");
            }else{
                 optional.put("storagetype", new String(newComputingOffer.storageType));
            }
            
            if(newComputingOffer.diskReadRateBPS == "null" || newComputingOffer.diskReadRateBPS == "" || newComputingOffer.diskReadRateBPS == null){
//                optional.put("hosttags", "");
            } else {
                 optional.put("bytesreadrate", new String(newComputingOffer.diskReadRateBPS));
            }
             if(newComputingOffer.diskWriteRateBPS == "null" || newComputingOffer.diskWriteRateBPS == "" || newComputingOffer.diskWriteRateBPS == null){
//                optional.put("hosttags", "");
            } else {
                 optional.put("byteswriterate", new String(newComputingOffer.diskWriteRateBPS));
            }
             if(newComputingOffer.diskReadRateIOPS == "null" || newComputingOffer.diskReadRateIOPS == "" || newComputingOffer.diskReadRateIOPS == null){
//                optional.put("hosttags", "");
            } else {
                 optional.put("iopsreadrate", new String(newComputingOffer.diskReadRateIOPS));
            }
             if(newComputingOffer.diskWriteRateIOPS == "null" || newComputingOffer.diskWriteRateIOPS == "" || newComputingOffer.diskWriteRateIOPS == null){
//                optional.put("hosttags", "");
            } else {
                 optional.put("iopswriterate", new String(newComputingOffer.diskWriteRateIOPS));
            }
            
            def response = computingOfferServer().createServiceOffering( Math.round(newComputingOffer.cpuNumber).toString() , newComputingOffer.coreCpuSpeed, 
            newComputingOffer.description, Math.round(newComputingOffer.memory).toString(), newComputingOffer.name, optional);
            newComputingOffer.available = true;
            newComputingOffer.deleted = false;
            newComputingOffer.offerReferenceId = response.serviceOfferingId

           //save ComputingOffer 
           newComputingOffer.save(flush: true)            
           if (newComputingOffer.hasErrors()) {
               throw new ValidationException(newComputingOffer.errors.allErrors);
           }
           ArrayList<ArrayList<String>> computingOfferList = new ArrayList<ArrayList<String>>();            
               HashMap<String,String> computingOffer = new HashMap<String,String>();                
                   computingOffer.put("computeOffer", newComputingOffer);
                   computingOffer.put("result", "OK");
                   computingOfferList.add(computingOffer);
           log.info("Created computing offer : ${newComputingOffer.offerReferenceId}, successfully")          
           return computingOfferList;
            
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
    }

    def update(ComputingOffer computingOffer, String name, String description){
       try {            
           
            licenseValidationService.authorizeAction(FogPanelService.OFFERING_UPDATE)
            
            // Create a HashMap which stores optional values
            HashMap<String,String> optional = new HashMap<String,String>();

            // Adding optional values to the HashMap
            optional.put("name", name);
            optional.put("displaytext", description);                                
            def response = computingOfferServer().updateServiceOffering(computingOffer.offerReferenceId, optional)
            computingOffer.name = response.serviceOfferingName
            computingOffer.description = response.displayText
            
            //save ComputingOffer 
            computingOffer.save(flush: true)      
            log.info("Updated computing offer : ${computingOffer.offerReferenceId}, successfully")         
            if (computingOffer.hasErrors()) {
                throw new ValidationException(computingOffer.errors.allErrors);
            }           
       } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
    
    def updateOrder(String requestBody) {
         try {   
             // convert string to json object
            def requestData = JSON.parse(requestBody)
            
            ComputingOffer computingOffer = ComputingOffer.findByOfferReferenceId(requestData.referenceId);                          
           
            computingOffer.orderNo = requestData.orderNo
            //save ComputingOffer 
            computingOffer.save(flush: true)     
            
            if (computingOffer.hasErrors()) {
                throw new ValidationException(computingOffer.errors.allErrors);
            }        
            
            ArrayList<ArrayList<String>> computingOfferList = new ArrayList<ArrayList<String>>();            
               HashMap<String,String> computingOffering = new HashMap<String,String>();                
                   computingOffering.put("computeOffer", computingOffer);
                   computingOffering.put("result", "OK");
                   computingOfferList.add(computingOffering);
           return computingOfferList;
       } catch (Exception ex) {
           ex.printStackTrace(System.err);
           throw ex;
       } 
    }
    
    def delete(String id, String forced) {
        try { 
            
            licenseValidationService.authorizeAction(FogPanelService.OFFERING_DELETE)
            
            def computingOffer = ComputingOffer.get(id)  
            def computingOfferZoneCost = ComputingOfferZoneCost.findAllByComputingOffer(computingOffer)           
            def virtualMachine = VirtualMachine.findAll("from VirtualMachine as virtualMachine where virtualMachine.computingOffer=?", [computingOffer]);
            if(forced == "true") {
                def response = computingOfferServer().deleteServiceOffering(computingOffer.offerReferenceId)
                    if(response.success == "true") {
                        computingOffer.deleted = true;
                        computingOffer.available = false
                        computingOffer.save(flush: true)
                        if (computingOffer.hasErrors()) {
                            throw new ValidationException(computingOffer.errors.allErrors);
                        }                         
                        log.info("Computing offer: ${computingOffer.offerReferenceId} is deleted successfully") 
                        ["OK"]               
                    }
                if (computingOffer.hasErrors()) {
                    throw new ValidationException(computingOffer.errors.allErrors);
                }   
                ["OK"]
            } else if(forced == "false") {
                if(!virtualMachine) { 
                    def response = computingOfferServer().deleteServiceOffering(computingOffer.offerReferenceId)
                    if(response.success == "true") {
                        computingOffer.deleted = true;
                        computingOffer.available = false
                        computingOffer.save(flush: true)
                        if (computingOffer.hasErrors()) {
                            throw new ValidationException(computingOffer.errors.allErrors);
                        } 
                        log.info("Computing offer: ${computingOffer.offerReferenceId} is deleted successfully") 
                        ["OK"]               
                    }              
                } else {                    
                    def language;
                    Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
                    language = defaultLanguage.value              
                    log.info("Computing offer: ${computingOffer.offerReferenceId} is VM Exist so cannot delete this offer") 
                    throw new Exception(messageSource.getMessage('admin.deleteOffer.vmExistMsg', null, new Locale(language)));
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
            
            def computingOffer = ComputingOffer.get(id)  
            def computingOfferZoneCost = ComputingOfferZoneCost.findAllByComputingOffer(computingOffer)  
            if(status == "disable") {
                computingOffer.available = false
                computingOffer.save(flush: true)
            } else if(status == "enable") {
                computingOffer.available = true
                computingOffer.save(flush: true)
            }
            
            if (computingOffer.hasErrors()) {
                throw new ValidationException(computingOffer.errors.allErrors);
            } 
            log.info("Computing offer: ${computingOffer.offerReferenceId} is ${status}d") 
            ["OK"]   
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
    }
}
