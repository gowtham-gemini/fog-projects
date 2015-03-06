package com.assistanz.fogpanel

import com.assistanz.cloud.config.ConfigLoader
import com.assistanz.openstack.OpenStackServer
import org.openstack4j.api.OSClient;
import org.openstack4j.model.storage.block.VolumeType as OpenStackVolumeType;
import grails.transaction.Transactional
import com.assistanz.fogpanel.PullVolumeTypeJob
import org.openstack4j.model.compute.ActionResponse;
import org.springframework.context.MessageSource
@Transactional
class VolumeTypeService {
    MessageSource messageSource
    def getList() {
        
        def list = VolumeType.findAllWhere(deleted: false)
        
        return list
    }
    
    def pullFromOpenstack() {
        
        PullVolumeTypeJob.triggerNow([id:"volumeType"])
            
        return "OK"
    }
    def deleteVolumeType(String id) {                                            
        try {         
            ArrayList<ArrayList<String>> volumeTypeList = new ArrayList<ArrayList<String>>();  
            HashMap<String,String> volumeItem = new HashMap<String,String>();  
            
            def volumeType  = VolumeType.findByReferenceId(id);            
            def volume = Volume.findAll("from Volume as volume where volume.volumeType=?", [volumeType]);
            ConfigLoader configLoader = ConfigLoader.getInstance();

            Properties props = configLoader.getProperties(); 
            OpenStackServer server = new OpenStackServer(props.get(Config.OPENSTACK_ENDPOINT_URL), props.get(Config.OPENSTACK_ADMIN_UUID), props.get(Config.OPENSTACK_ADMIN_PASSWORD), null);
            OSClient os = server.authenticate();                          
            if(volume.size() == 0) {                     
                ActionResponse actionResponse  = os.blockStorage().volumes().deleteVolumeType(id);                  

                if(actionResponse.isSuccess() == true) {
                    volumeType.deleted = true;
                    volumeType.save(flush: true)
                    if (volumeType.hasErrors()) {
                        throw new ValidationException(volumeType.errors.allErrors);
                    }                         
                   volumeItem.put("result", "OK");      
                   volumeTypeList.add(volumeItem);   
                } else {
                    volumeItem.put("result", actionResponse.getFault());    
                    volumeTypeList.add(volumeItem);
                }                   
            } else {
                def language;
                Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
                language = defaultLanguage.value                   
                throw new Exception(messageSource.getMessage('admin.volumeType.volumeExistMsg', null, new Locale(language)));                    
            }     
            return volumeTypeList
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
    }
    def updateVolumeTypeStatus (requestData) {
        try { 
            ArrayList<ArrayList<String>> volumeTypeList = new ArrayList<ArrayList<String>>();  
            HashMap<String,String> volumeItem = new HashMap<String,String>(); 
            def volumeType =  VolumeType.findByReferenceId(requestData.volumeTypeId);   
            def isDisabled = false
            if(requestData.status == "disable") {
                isDisabled = true
            }                
            if(volumeType) {
                volumeType.isDisabled = isDisabled
                volumeType.save(flush: true);             
                if (volumeType.hasErrors()) {
                    throw new ValidationException(volumeType.errors.allErrors);
                }    
                volumeItem.put("result", "OK");
                volumeTypeList.add(volumeItem);                 
            }
            return volumeTypeList;
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
    def updateVolumeType(requestData) {
        try {               
            ArrayList<ArrayList<String>> volumeTypeList = new ArrayList<ArrayList<String>>();  
            HashMap<String,String> volumeItem = new HashMap<String,String>();  
            def volumeType =  VolumeType.findByReferenceId(requestData.id);               
            for(int i = 0; i < requestData.zoneCosts.length(); i++) {
                Double cost = Math.round(new Double(requestData.zoneCosts[i].cost)*100000)/100000;                                                
                VolumeTypeZoneCost existsVolumeTypeZoneCost = VolumeTypeZoneCost.findWhere(volumeType: VolumeType.findByReferenceId(requestData.id), zone:Zone.get(requestData.zoneCosts[i].zoneId));
                       
                if(existsVolumeTypeZoneCost) {                    
                    existsVolumeTypeZoneCost.cost = cost                    
                    existsVolumeTypeZoneCost.save(flush: true)         
                    if (existsVolumeTypeZoneCost.hasErrors()) {
                        throw new ValidationException(existsVolumeTypeZoneCost.errors.allErrors);
                    }
                } else {
                    volumeType.addToVolumeTypeZoneCosts(new VolumeTypeZoneCost(
                        zone:Zone.get(requestData.zoneCosts[i].zoneId), 
                        cost: cost))       
                }    
            }                                    
            volumeType.save(flush: true);             
            if (volumeType.hasErrors()) {
                throw new ValidationException(volumeType.errors.allErrors);
            }    
            volumeItem.put("result", "OK");
            volumeTypeList.add(volumeItem); 
            return volumeTypeList;
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
    
    def pullVolumeTypeFromOpenstack(jobid) {
        def job = AsynchronousJobs.get(jobid)
        try {                         
            job.status = JobStatus.valueOf("RUNNING")
            job.startedAt = new Date()
            job.save(flush: true)
            ConfigLoader configLoader = ConfigLoader.getInstance();
            Properties props = configLoader.getProperties();
            OpenStackServer server = new OpenStackServer(props.get(Config.OPENSTACK_ENDPOINT_URL), props.get(Config.OPENSTACK_ADMIN_UUID), props.get(Config.OPENSTACK_ADMIN_PASSWORD), null);
            
            OSClient os = server.authenticate();
            def defaultVolumeType = VolumeType.findByReferenceId("default")
            if(!defaultVolumeType) {                
                def newDefaultVolumeType = new VolumeType();
                newDefaultVolumeType.name = "default"
                newDefaultVolumeType.referenceId = "default"
                newDefaultVolumeType.deleted = false
                newDefaultVolumeType.createdAt = new Date()
                newDefaultVolumeType.isDisabled = false  
                
                def zoneList = Zone.findAll()
                for(def zone: zoneList) {
                    newDefaultVolumeType.addToVolumeTypeZoneCosts(new VolumeTypeZoneCost(
                            zone:Zone.get(zone.id), 
                            cost: 0.0
                        ))                       
                }                  
                if (!newDefaultVolumeType.save()) {
                    newDefaultVolumeType.errors.allErrors.each { println it }
                }
            }    
            
            List<? extends OpenStackVolumeType> volumeTypes = os.blockStorage().volumes().listVolumeTypes();

            HashMap<String,String> openStackVolumeTypeList = new HashMap<String,String>();            
            for (OpenStackVolumeType openstackVolumeType : volumeTypes) {

                openStackVolumeTypeList.put( openstackVolumeType.getName(),"volumeTypeName");     

                def volumeType = VolumeType.findByReferenceId(openstackVolumeType.getId())
                if(!volumeType) {
                    volumeType = new VolumeType()
                    volumeType.name = openstackVolumeType.getName()
                    volumeType.referenceId = openstackVolumeType.getId()

                    volumeType.createdAt = new Date()
                    volumeType.save(flush: true)                    

                    def zoneList = Zone.findAll()
                    for(def zone: zoneList) { 
                        volumeType.addToVolumeTypeZoneCosts(new VolumeTypeZoneCost(
                                zone:Zone.get(zone.id), 
                                cost: 0.0
                            )
                        )                       
                    }                    
                    if (!volumeType.save()) {
                        volumeType.errors.allErrors.each { println it }
                    }
                }
            }
            def volumeTypeQuery = VolumeType.createCriteria()
            def oldVolumeType = volumeTypeQuery.list {
               eq("deleted", false)    
               and {
                   ne("referenceId", "default") 
               }
           }           
            for(def volumeType :oldVolumeType ) { 
                boolean blnExists = openStackVolumeTypeList.containsKey(volumeType.name);
                if(blnExists.toString() == "false" || blnExists == false) {
                   volumeType.deleted = true; 
                   volumeType.deletedAt =  new Date();
                   volumeType.save(flush: true); 
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
    
    def createVolumeType(requestData) {
        try {
            ArrayList<ArrayList<String>> volumeTypeList = new ArrayList<ArrayList<String>>();  
            HashMap<String,String> volumeItem = new HashMap<String,String>();    
            
            ConfigLoader configLoader = ConfigLoader.getInstance();

            Properties props = configLoader.getProperties();

            OpenStackServer server = new OpenStackServer(props.get(Config.OPENSTACK_ENDPOINT_URL), props.get(Config.OPENSTACK_ADMIN_UUID), props.get(Config.OPENSTACK_ADMIN_PASSWORD), null);

            OSClient os = server.authenticate();
            
            OpenStackVolumeType volumeType = os.blockStorage().volumes().createVolumeType(requestData.name);
            if(volumeType.getId()) {                              
                def newvolumeType = new VolumeType();
                newvolumeType.name = requestData.name;
                newvolumeType.referenceId = volumeType.getId();
                newvolumeType.deleted = false;
                newvolumeType.isDisabled = false;
                newvolumeType.createdAt = new Date()
            
                for(int i = 0; i < requestData.zoneCosts.length(); i++) {                   
                    Double cost = new Double(requestData.zoneCosts[i].cost);                
                    newvolumeType.addToVolumeTypeZoneCosts(new VolumeTypeZoneCost(
                            zone:Zone.get(requestData.zoneCosts[i].zoneId), 
                            cost: cost
                        ))       
                }            
                if (!newvolumeType.save()) {
                    newvolumeType.errors.allErrors.each { println it }
                }
                volumeItem.put("result", "OK");
                volumeTypeList.add(volumeItem);                 
             }
             return volumeTypeList;
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }        
    }       
    
    def getCurrentVolumeType (referenceId) {
        try {                            
            def volumeType = VolumeType.findByReferenceId(referenceId);            
            
            def volumeTypeZoneQuery = VolumeTypeZoneCost.createCriteria()
            def volumeTypeZoneCostResult = volumeTypeZoneQuery.list {
               eq("volumeType", volumeType)       
               order("id", "desc")                
            }
            
            ArrayList vtArrayList = new ArrayList(); 
            ArrayList vtZoneCostArrayList = new ArrayList(); 
            
            HashMap vtItem = new HashMap();  
            vtItem.put("name", volumeType.name)            
            vtItem.put("referenceId", volumeType.referenceId)
            vtItem.put("deleted", volumeType.deleted)
            vtItem.put("deletedAt", volumeType.deletedAt)
            vtItem.put("createdAt", volumeType.createdAt)            
            vtItem.put("isDisabled", volumeType.isDisabled)                        
            
            for(def vtZone: volumeTypeZoneCostResult) {                
                HashMap vtZoneItem = new HashMap();                  
                vtZoneItem.put("cost", vtZone.cost)                                
                vtZoneItem.put("id", vtZone.id)                      
                vtZoneItem.put("zone", vtZone.zone)                
                vtZoneCostArrayList.add(vtZoneItem)
            }                        
            vtItem.put("volumeTypeZoneCosts", vtZoneCostArrayList)
            vtArrayList.add(vtItem)            
            return vtArrayList;
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
    
    def count() {
        
        try {
            
            ArrayList<ArrayList<String>> response = new ArrayList<ArrayList<String>>();
            
            def totalVolumeType = VolumeType.findAllWhere(deleted: false).size();
            def enabledVolumeType = VolumeType.findAllWhere(deleted: false, isDisabled: false).size();
            def disabledVolumeType = VolumeType.findAllWhere(deleted: false, isDisabled: true).size();
            
            HashMap<String,String> item = new HashMap<String,String>();
            item.put("totalVolumeType",totalVolumeType);
            item.put("enabledVolumeType",enabledVolumeType);
            item.put("disabledVolumeType",disabledVolumeType);
            response.add(item);
            
            return response;
            
            
        } catch(Exception e) {
            
        }
    }
}
