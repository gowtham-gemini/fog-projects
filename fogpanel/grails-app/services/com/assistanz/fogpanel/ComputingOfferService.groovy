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
import com.assistanz.fogpanel.PullComputeOfferJob


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
                ge("cpuNumber", template.minimumCpu.toDouble()) 
            }
            and {
                ge("memory", template.minimumRam.toDouble()) 
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
    
    
    def pullComputeOfferFromCloudStackJobStart() {
        
        PullComputeOfferJob.triggerNow([id:"computeOffer"])
            
        return "OK"
    }
    
    
    def pullComputeOfferFromCloudStack(jobid) {
        
        def job = AsynchronousJobs.get(jobid)
        try { 
                        
            job.status = JobStatus.valueOf("RUNNING")
            job.startedAt = new Date()
            job.save(flush: true)
 
            def response = computingOfferServer().listServiceOfferings();
            for(Iterator<ServiceOfferingResponse> iter = response.serviceOfferings.iterator(); iter.hasNext();) {
                def data = iter.next();

                ComputingOffer computingOffer = ComputingOffer.findByOfferReferenceId(data.id);
                if (!computingOffer) {
                    computingOffer  = new ComputingOffer();
                    computingOffer.offerReferenceId = data.id;
                    computingOffer.name = data.name;
                    computingOffer.description = data.displayText;
                    computingOffer.bandWidth = 1000
                    computingOffer.coreCpuSpeed = data.cpuSpeed;
                    computingOffer.cpuNumber = Double.parseDouble(data.cpuNumber);
                    computingOffer.memory = Double.parseDouble(data.memory);
                    Boolean defaultUse = new Boolean(data.defaultUse);
                    computingOffer.defaultUse = defaultUse;
                    computingOffer.hostTags = data.hostTags;
                    computingOffer.diskIO = "Good"; 
                    Boolean isSystem = new Boolean(data.isSystem);
                    computingOffer.isSystem = isSystem;
                    Boolean limitCpuUse = new Boolean(data.limitCpuUse);
                    computingOffer.limitCpuUse = limitCpuUse;
                    computingOffer.networkRate = data.networkRate;
                    Boolean offeringHa = new Boolean(data.offerHa);
                    computingOffer.offerHA = data.offerHa;
                    computingOffer.storageType = data.storageType;
                    computingOffer.tags = data.tags;
                    computingOffer.available = true;
                    computingOffer.deleted = false;
                    computingOffer.orderNo = 1;

                    computingOffer.diskReadRateBPS = data.diskBytesReadRate
                    computingOffer.diskWriteRateBPS = data.diskBytesWriteRate
                    computingOffer.diskReadRateIOPS = data.diskIopsReadRate
                    computingOffer.diskWriteRateIOPS = data.diskIopsWriteRate

                    def zoneList = Zone.findAll()

                    for(def zone: zoneList) {

                        computingOffer.addToComputingOfferZoneCosts(new ComputingOfferZoneCost(
                        zone:zone, 
                        cost: 0.0,
                        setupCost:0.0,
                        minimumCost:0.0))   
                    }
                    computingOffer.save(flush: true);

                } else if(computingOffer) {
                 
                    computingOffer.name = data.name;
                    computingOffer.description = data.displayText;
                    computingOffer.save(flush: true);
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
    
//    def pullComputeOfferFromCloudStack() {
//        
//        try {
//            
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
    
    def list(String zoneReferenceId, String name, String templateReferenceId, String storageType) {        
        def computingOfferQuery = ComputingOffer.createCriteria()
        def computingOfferResult;
        
        def user = springSecurityService.currentUser
        def role = springSecurityService.getPrincipal().getAuthorities()   
        
        
        try {
            def template
            if(templateReferenceId == "null" || templateReferenceId == null) {
                
            } else {
                template = Template.findByTemplateReferenceId(templateReferenceId)   
            }
            computingOfferResult = ComputingOffer.withCriteria {
               eq("deleted", false)
//                and {
                    and {
                        if(role.iterator().next() == "ROLE_ADMIN" ) {

                        } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") { 
                            eq("available", true)   
                        }
                    }
                    if(storageType == "null" || storageType == null) {
                        
                    } else {
                        and {
                           eq("storageType", storageType)           
                        }
                    }
                    if(name == "null" || name == null) {      
                        
                    } else {
                       and {
                            like("name", "%" + name + "%") 
                        }   
                    }
                    if(templateReferenceId == "null" || templateReferenceId == null) {
                        
                      
                    } else {
                        and {
                            ge("cpuNumber", template.minimumCpu.toDouble()) 
                        }
                        and {
                            ge("memory", template.minimumRam.toDouble()) 
                        }  
                    }
//                } 
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
            
            String limitcpuuse = new Boolean(newComputingOffer.limitCpuUse).toString();
            String offerha = new Boolean(newComputingOffer.offerHA).toString();

            HashMap<String,String> optional = new HashMap<String,String>();

            if(newComputingOffer.hostTags == "null" || newComputingOffer.hostTags == "" || newComputingOffer.hostTags == null){

            } else {
                 optional.put("hosttags", new String(newComputingOffer.hostTags));
            }
            if(newComputingOffer.tags == "null" || newComputingOffer.tags == "" || newComputingOffer.tags == null){
                
            } else {
                optional.put("tags", new String(newComputingOffer.tags)); 
            }

            optional.put("limitcpuuse", limitcpuuse);
            if(newComputingOffer.networkRate == "null"){
            } else {
                 optional.put("networkrate", new String(newComputingOffer.networkRate));
            }
            optional.put("offerha", offerha);

            if(new String(newComputingOffer.storageType) == "null"){
                optional.put("storagetype","Shared");
            }else{
                 optional.put("storagetype", new String(newComputingOffer.storageType));
            }
            
            if(newComputingOffer.diskReadRateBPS == "null" || newComputingOffer.diskReadRateBPS == "" || newComputingOffer.diskReadRateBPS == null){

            } else {
                 optional.put("bytesreadrate", new String(newComputingOffer.diskReadRateBPS));
            }
             if(newComputingOffer.diskWriteRateBPS == "null" || newComputingOffer.diskWriteRateBPS == "" || newComputingOffer.diskWriteRateBPS == null){

            } else {
                 optional.put("byteswriterate", new String(newComputingOffer.diskWriteRateBPS));
            }
             if(newComputingOffer.diskReadRateIOPS == "null" || newComputingOffer.diskReadRateIOPS == "" || newComputingOffer.diskReadRateIOPS == null){

            } else {
                 optional.put("iopsreadrate", new String(newComputingOffer.diskReadRateIOPS));
            }
             if(newComputingOffer.diskWriteRateIOPS == "null" || newComputingOffer.diskWriteRateIOPS == "" || newComputingOffer.diskWriteRateIOPS == null){

            } else {
                 optional.put("iopswriterate", new String(newComputingOffer.diskWriteRateIOPS));
            }
            
            def response = computingOfferServer().createServiceOffering( Math.round(newComputingOffer.cpuNumber).toString() , newComputingOffer.coreCpuSpeed, 
            newComputingOffer.description, Math.round(newComputingOffer.memory).toString(), newComputingOffer.name, optional);
            newComputingOffer.available = true;
            newComputingOffer.deleted = false;
            newComputingOffer.offerReferenceId = response.id

           //save ComputingOffer 
           newComputingOffer.save(flush: true)
           
            
           //add log for compute offer 
                        
            
            
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
    def getCurrentOffer(id) {
        try {              
            def currency = ApplicationHolder.getApplication().config.billing.defaultCurrency
            def computingOffer = ComputingOffer.get(id);            
            
            def computingOfferZoneQuery = ComputingOfferZoneCost.createCriteria()
            def computingOfferZoneCostResult = computingOfferZoneQuery.list {
               eq("computingOffer", computingOffer)    
               order("id", "desc")                
            }
            
            ArrayList computingOfferArrayList = new ArrayList(); 
            ArrayList computingOfferZoneCostArrayList = new ArrayList(); 
            
            HashMap computingOfferItem = new HashMap();  
            computingOfferItem.put("available", computingOffer.available)
            computingOfferItem.put("bandWidth", computingOffer.bandWidth)
            computingOfferItem.put("coreCpuSpeed", computingOffer.coreCpuSpeed)
            computingOfferItem.put("cpuNumber", computingOffer.cpuNumber)            
            computingOfferItem.put("currency", currency)
            computingOfferItem.put("defaultUse", computingOffer.defaultUse)
            computingOfferItem.put("deleted", computingOffer.deleted)
            computingOfferItem.put("description", computingOffer.description)
            
            computingOfferItem.put("diskIO", computingOffer.diskIO)
            computingOfferItem.put("diskReadRateBPS", computingOffer.diskReadRateBPS)
            computingOfferItem.put("diskReadRateIOPS", computingOffer.diskReadRateIOPS)
            computingOfferItem.put("diskWriteRateBPS", computingOffer.diskWriteRateBPS)
            computingOfferItem.put("diskWriteRateIOPS", computingOffer.diskWriteRateIOPS)
            computingOfferItem.put("hostTags", computingOffer.hostTags)
            
            computingOfferItem.put("id", computingOffer.id)
            computingOfferItem.put("isSystem", computingOffer.isSystem)
            computingOfferItem.put("limitCpuUse", computingOffer.limitCpuUse)
            computingOfferItem.put("memory", computingOffer.memory)
            computingOfferItem.put("name", computingOffer.name)
            
            computingOfferItem.put("networkRate", computingOffer.networkRate)
            computingOfferItem.put("offerHA", computingOffer.offerHA)
            computingOfferItem.put("offerReferenceId", computingOffer.offerReferenceId)
            computingOfferItem.put("orderNo", computingOffer.orderNo)
            computingOfferItem.put("storageType", computingOffer.storageType)
            computingOfferItem.put("tags", computingOffer.tags)
            
            
            for(def computingOfferZone: computingOfferZoneCostResult) {                
                HashMap computingOfferZoneItem = new HashMap();  
                computingOfferZoneItem.put("computingOffer", "")
                computingOfferZoneItem.put("cost", computingOfferZone.cost)
                computingOfferZoneItem.put("currency", currency)
                
                computingOfferZoneItem.put("id", computingOfferZone.id)
                computingOfferZoneItem.put("minimumCost", computingOfferZone.minimumCost)
                computingOfferZoneItem.put("setupCost", computingOfferZone.setupCost)
                computingOfferZoneItem.put("zone", computingOfferZone.zone)
                
                computingOfferZoneCostArrayList.add(computingOfferZoneItem)
            }
            
            
            computingOfferItem.put("computingOfferZoneCosts", computingOfferZoneCostArrayList)
            computingOfferArrayList.add(computingOfferItem)            
            return computingOfferArrayList;
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
    def update(requestData, String name, String description) {
       try {            
           
            def user = springSecurityService.currentUser
            def role = springSecurityService.getPrincipal().getAuthorities()             
            
            licenseValidationService.authorizeAction(FogPanelService.OFFERING_UPDATE)
                        
            def computingOffer =  ComputingOffer.get(requestData.id);   
                
            for(int i = 0; i < requestData.zoneCosts.length(); i++) {

                Double cost = new Double(requestData.zoneCosts[i].cost);
                Double setupCost = new Double(requestData.zoneCosts[i].setupCost);
                Double minimumCost = new Double(requestData.zoneCosts[i].minimumCost);
                
                ComputingOfferZoneCost existsComputingOfferZoneCost = ComputingOfferZoneCost.findWhere(computingOffer:computingOffer, zone:Zone.get(requestData.zoneCosts[i].zoneId));
                
                if(existsComputingOfferZoneCost) {
                    
                    def oldCost = existsComputingOfferZoneCost.cost
                    def oldSetupCost = existsComputingOfferZoneCost.setupCost
                    def oldMinimumCost = existsComputingOfferZoneCost.minimumCost

                    existsComputingOfferZoneCost.cost = cost
                    existsComputingOfferZoneCost.setupCost = setupCost
                    existsComputingOfferZoneCost.minimumCost = minimumCost
                    existsComputingOfferZoneCost.save(flush: true)

                    if(oldCost != existsComputingOfferZoneCost.cost || oldSetupCost != existsComputingOfferZoneCost.setupCost || oldMinimumCost != existsComputingOfferZoneCost.minimumCost) {
                        def serviceCostChangeLog = new ServiceCostChangeLog()
                        serviceCostChangeLog.serviceName = ServiceName.valueOf("COMPUTE_OFFER")
                        serviceCostChangeLog.oldCost = oldCost
                        serviceCostChangeLog.oldSetupCost = oldSetupCost
                        serviceCostChangeLog.oldMinimumCost = oldMinimumCost
                        serviceCostChangeLog.changedDate = new Date()


                        serviceCostChangeLog.user = user
                        serviceCostChangeLog.account = user.account

                        serviceCostChangeLog.newCost = existsComputingOfferZoneCost.cost
                        serviceCostChangeLog.newSetupCost = existsComputingOfferZoneCost.setupCost
                        serviceCostChangeLog.newMinimumCost = existsComputingOfferZoneCost.minimumCost
                        serviceCostChangeLog.computingOfferZoneCost = existsComputingOfferZoneCost;
                        serviceCostChangeLog.save(flush: true)

                    }
                    
                } else {
                    
                computingOffer.addToComputingOfferZoneCosts(new ComputingOfferZoneCost(
                        zone:Zone.get(requestData.zoneCosts[i].zoneId), 
                        cost: cost,
                        setupCost:setupCost,
                        minimumCost:minimumCost))      
                    
                }                
               
            } 
            
            computingOffer.diskIO = requestData.diskIO            
            
            // Create a HashMap which stores optional values
            HashMap<String,String> optional = new HashMap<String,String>();

            // Adding optional values to the HashMap
            optional.put("name", name);
            optional.put("displaytext", description);                                
            def response = computingOfferServer().updateServiceOffering(computingOffer.offerReferenceId, optional)
            computingOffer.name = response.name
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
