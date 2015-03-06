package com.assistanz.fogpanel

import grails.transaction.Transactional
import com.assistanz.openstack.OpenStackServer;
import java.util.Arrays;
import java.util.List;
import static org.openstack4j.api.Builders.flavor;
import org.openstack4j.api.Builders;
import org.openstack4j.api.OSClient;
import org.openstack4j.api.exceptions.AuthenticationException;
import org.openstack4j.model.compute.Flavor;
import org.openstack4j.model.compute.Image;
import org.openstack4j.model.compute.SecGroupExtension;
import org.openstack4j.model.compute.ext.AvailabilityZone;
import org.openstack4j.model.network.Network;
import org.openstack4j.model.telemetry.Meter;
import com.assistanz.cloud.config.ConfigLoader;
import org.openstack4j.model.compute.ActionResponse;
import com.assistanz.fogpanel.Config
import com.assistanz.fogpanel.PullFlavorJob

@Transactional
class FlavorService {
    
     def springSecurityService; 

    def listFlavor(referenceId) {
        
        ArrayList<ArrayList<String>> flavorList = new ArrayList<ArrayList<String>>();         
        def flavorsList = Flavors.withCriteria {
            eq('deleted', false)
            
            if(referenceId) {
               eq('referenceId', referenceId) 
               eq('isDisabled', false)
           }
        }
        
        if(referenceId) {            
            def mappedFlavor = Flavors.findByReferenceId(referenceId);
            def flavorCostList = FlavorCostInfo.findAllByFlavor(mappedFlavor);                                    
            HashMap costItem = new HashMap();
            costItem.put("flavorCostList",flavorCostList);
            flavorList.add(costItem);  
        }    
        
        for (def flavor : flavorsList) { 
            ArrayList<ArrayList<String>> flavorZoneCostList = new ArrayList<ArrayList<String>>();   
            HashMap item = new HashMap();
            item.put("name", flavor.name);
            item.put("description", flavor.description);
            item.put("referenceId", flavor.referenceId);
            item.put("rootDisk", flavor.rootgb);
            item.put("ephemeralDisk", flavor.ephemeralgb);
            item.put("vcpu", flavor.vcpus);
            item.put("isDisabled", flavor.isDisabled);
            item.put("isPublic", flavor.isPublic);
            item.put("memory", flavor.memory);                        
            item.put("ram", flavor.memory);
            item.put("rxtxFacror", flavor.rxtxFactor);
            item.put("swapDisk", flavor.swap);
            
            def mappedFlavor = Flavors.findByReferenceId(flavor.referenceId);
            def flavorCostList = FlavorCostInfo.findByFlavor(mappedFlavor);  
            for(def flavorCost : flavorCostList) {                 
                HashMap costItem = new HashMap();    
                costItem.put("runningCost", flavorCost.runningCost)
                costItem.put("setupCost", flavorCost.setupCost)
                costItem.put("stopCost", flavorCost.stopCost)
                costItem.put("zone", flavorCost.zone)
                costItem.put("flavorReferenceId", flavorCost.flavor.referenceId) 
                flavorZoneCostList.add(costItem)
            }                        
            item.put("flavorCostList",flavorZoneCostList);
            flavorList.add(item);                         
        }
        
        return flavorList
    }
    
    def updateFlavorStatus (requestData) {
        try { 
            ArrayList<ArrayList<String>> flavorList = new ArrayList<ArrayList<String>>();  
            HashMap<String,String> flavorItem = new HashMap<String,String>(); 
            def flavor =  Flavors.findByReferenceId(requestData.flavorId);   
            def isDisabled = false
            if(requestData.status == "disable") {
                isDisabled = true
            }                
            if(flavor) {
                flavor.isDisabled = isDisabled
                flavor.save(flush: true);             
                if (flavor.hasErrors()) {
                    throw new ValidationException(flavor.errors.allErrors);
                }    
                flavorItem.put("result", "OK");
                flavorList.add(flavorItem);                 
            }
            return flavorList;
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
    
    def count() {
        try {
            
            ArrayList<ArrayList<String>> flavorCount = new ArrayList<ArrayList<String>>();        
            HashMap<String,String> item = new HashMap<String,String>();
            
            def totalFlavors = Flavors.findAllWhere(deleted: false);
            def enabledFlavors = Flavors.findAllWhere(deleted: false, isDisabled: false);
            def disabledFlavors = Flavors.findAllWhere(deleted: false, isDisabled:  true);
            
            item.put("totalFlavors",totalFlavors.size());
            item.put("enabledFlavors",enabledFlavors.size());
            item.put("disabledFlavors",disabledFlavors.size());
            
            flavorCount.add(item);
            return flavorCount;
            
        } catch (Exception ex) {
               throw ex;
        }
    }
    
    def updateFlavors(requestData) {
        try {                     
            ArrayList<ArrayList<String>> flavorList = new ArrayList<ArrayList<String>>();  
            HashMap<String,String> flavorItem = new HashMap<String,String>();  
            def flavor =  Flavors.findByReferenceId(requestData.id);               
            for(int i = 0; i < requestData.flavorZoneCosts.length(); i++) {              
                Double runningCost = Math.round(new Double(requestData.flavorZoneCosts[i].runningCost)*100000)/100000;     
                Double stopageCost = Math.round(new Double(requestData.flavorZoneCosts[i].stopageCost)*100000)/100000;     
                Double setupCost = Math.round(new Double(requestData.flavorZoneCosts[i].setupCost)*100000)/100000;     
                
                FlavorCostInfo existsFlavorZoneCost = FlavorCostInfo.findWhere(flavor: Flavors.findByReferenceId(requestData.id), zone:Zone.get(requestData.flavorZoneCosts[i].zoneId));
                         
                if(existsFlavorZoneCost) {                                          
                    existsFlavorZoneCost.runningCost = runningCost;
                    existsFlavorZoneCost.setupCost = setupCost;
                    existsFlavorZoneCost.stopCost = stopageCost;
                    existsFlavorZoneCost.save(flush: true)         
                    if (existsFlavorZoneCost.hasErrors()) {   
                        throw new ValidationException(existsFlavorZoneCost.errors.allErrors);
                    }
                } else {
                    flavor.addToFlavorCosts(new FlavorCostInfo(
                            zone:Zone.get(requestData.flavorZoneCosts[i].zoneId), 
                            runningCost : runningCost,
                            setupCost : setupCost,
                            stopCost : stopageCost)
                    )       
                }    
            }                                    
            flavor.save(flush: true);                
            if (flavor.hasErrors()) {
                throw new ValidationException(flavor.errors.allErrors);
            }    
            flavorItem.put("result", "OK");
            flavorList.add(flavorItem); 
            return flavorList;
        
        } catch (Exception ex) {
               throw ex;
        }
    }
    
    def createFlavors(requestData) {
        
        try { 
                       
            ConfigLoader configLoader = ConfigLoader.getInstance();
           
            Properties props = configLoader.getProperties();
                        
            OpenStackServer server = new OpenStackServer(props.get(Config.OPENSTACK_ENDPOINT_URL), props.get(Config.OPENSTACK_ADMIN_UUID), props.get(Config.OPENSTACK_ADMIN_PASSWORD), null);
            
            OSClient os = server.authenticate();
        
//            Flavor flavor = os.compute().flavors()
//                  .create(requestData.name, requestData.ram, requestData.vcpus, requestData.disk, requestData.ephemeral, requestData.swap, requestData.rxtxFactor, requestData.isPublic);
                  
            Flavor flavor = os.compute().flavors()
                            .create(Builders.flavor()
                            .name(requestData.name)
                            .ram(requestData.ram)
                            .vcpus(requestData.vcpus)
                            .disk(requestData.disk)
                            .swap(requestData.swap)
                            .ephemeral(requestData.ephemeral)
                            .rxtxFactor(requestData.rxtxFactor)
                            .isPublic(requestData.isPublic)
                            .build());
            
            
            def flavors = new Flavors()
            flavors.referenceId = flavor.getId()
            flavors.name = flavor.getName()
            flavors.description = requestData.description
            flavors.isPublic = flavor.isPublic()
            flavors.memory = flavor.getRam()
            flavors.vcpus = flavor.getVcpus()
            flavors.swap = flavor.getSwap()
            flavors.rxtxFactor =  flavor.getRxtxFactor()
            flavors.ephemeralgb = flavor.getEphemeral()
            flavors.createdAt = new Date()
            flavors.isDisabled = flavor.isDisabled()
            flavors.rootgb = flavor.getDisk()
            
            for( def flavorZoneCost : requestData.flavorZoneCosts) {
                println("zone name"+flavorZoneCost.zoneName)
                
                Double instanceRunningCost = new Double(flavorZoneCost.instanceRunningCost);
                Double instanceStoppageCost = new Double(flavorZoneCost.instanceStopageCost);
                Double setupCost = new Double(flavorZoneCost.setupCost);

                
                def zone = Zone.findByName(flavorZoneCost.zoneName);
                
                flavors.addToFlavorCosts(new FlavorCostInfo(
                    zone : zone ,
                    runningCost : instanceRunningCost,
                    stopCost : instanceStoppageCost,
                    setupCost : setupCost,
                    flavor : flavors,
                    )
                )
                
                flavors.save(flush: true)
            }
            
            ArrayList<ArrayList<String>> createFlavourResponse = new ArrayList<ArrayList<String>>();        
            HashMap<String,String> item = new HashMap<String,String>(); 
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            createFlavourResponse.add(item)
            
            return createFlavourResponse
            
            
        } catch (Exception ex) {
               throw ex;
        }
    } 
    
    def pullFlavorFromOpenstack() {          
                                   
            PullFlavorJob.triggerNow([id:"flavor"])
        
            return "OK"
        }
        
    def deleteFlavor(id) {
        try {
            
            
            ConfigLoader configLoader = ConfigLoader.getInstance();

            Properties props = configLoader.getProperties();

            OpenStackServer server = new OpenStackServer(props.get(Config.OPENSTACK_ENDPOINT_URL), props.get(Config.OPENSTACK_ADMIN_UUID), props.get(Config.OPENSTACK_ADMIN_PASSWORD), null);

            OSClient os = server.authenticate();
                        
            ActionResponse actionResponse = os.compute().flavors().delete(id);
            
            if(actionResponse.isSuccess() == true) {
                def flavor = Flavors.findByReferenceId(id)
                flavor.deleted = true;
                flavor.deletedAt = new Date();
                flavor.save(flush: true)
//            ["OK"]
            } else {
                
                return actionResponse.getFault();
            }
            
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
    
    
    def pullFlavour(jobid) {
        
        def job = AsynchronousJobs.get(jobid)
        
        try {
       
            job.status = JobStatus.valueOf("RUNNING")
            job.startedAt = new Date()
            job.save(flush: true)

            ConfigLoader configLoader = ConfigLoader.getInstance();

            Properties props = configLoader.getProperties();

            OpenStackServer server = new OpenStackServer(props.get(Config.OPENSTACK_ENDPOINT_URL), props.get(Config.OPENSTACK_ADMIN_UUID), props.get(Config.OPENSTACK_ADMIN_PASSWORD), null);

            OSClient os = server.authenticate();

            List<? extends Flavor> flavors  = os.compute().flavors().list();
            
            HashMap<String,String> openStackFlavorList = new HashMap<String,String>();

            for (Flavor openstackFlavor : flavors) {
                
                openStackFlavorList.put(openstackFlavor.getId(),"referenceId");

                def flavor = Flavors.findByReferenceId(openstackFlavor.getId())
                if(!flavor) {
                    flavor = new Flavors()
                    flavor.referenceId = openstackFlavor.getId()
                    flavor.name = openstackFlavor.getName()
                    flavor.isPublic = openstackFlavor.isPublic()
                    flavor.memory = openstackFlavor.getRam()
                    flavor.vcpus = openstackFlavor.getVcpus()
                    flavor.swap = openstackFlavor.getSwap()
                    flavor.rxtxFactor =  openstackFlavor.getRxtxFactor()
                    flavor.ephemeralgb = openstackFlavor.getEphemeral()
                    flavor.createdAt = new Date()
                    flavor.isDisabled = openstackFlavor.isDisabled()
                    flavor.rootgb = openstackFlavor.getDisk()
                    
                    def zones = Zone.findAllWhere(deleted : false);
                    
                    for( def iterateZone : zones) {
                        println("zone"+iterateZone.name)
                        Double instanceRunningCost = 0.00000;
                        Double instanceStoppageCost = 0.00000;
                        Double setupCost = 0.00000;

                        flavor.addToFlavorCosts(new FlavorCostInfo(
                            zone : iterateZone,
                            runningCost : instanceRunningCost,
                            stopCost : instanceStoppageCost,
                            setupCost : setupCost,
                            flavor : flavor,
                            )
                        )

                        flavor.save(flush: true)
                        if (!flavor.save()) {
                            flavor.errors.allErrors.each { println it }
                        }
                    }
                    
                }

            }
            
            def oldFlavor = Flavors.findAllWhere(deleted: false); 
            for(def flavor :oldFlavor ) { 

                boolean blnExists = openStackFlavorList.containsKey(flavor.referenceId);

                println("blnExists"+blnExists)

                if(blnExists.toString() == "false" || blnExists == false) {
                   flavor.deleted = true; 
                   flavor.deletedAt = new Date(); 
                   flavor.save(flush: true); 
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
}  
