package com.assistanz.fogpanel

import grails.transaction.Transactional
import com.assistanz.openstack.OpenStackServer;
import java.util.Arrays;
import java.util.List;
import com.assistanz.cloud.config.ConfigLoader;
import com.assistanz.fogpanel.Config

@Transactional
class FlavorService {
    
     def springSecurityService; 

    def listFlavor() {
        
        ArrayList<ArrayList<String>> flavorList = new ArrayList<ArrayList<String>>();  
        
        def flavorsList = Flavors.withCriteria {
            eq('deleted', false)            
        }
        
        for (def flavor : flavorsList) { 
            
            HashMap item = new HashMap();
            item.put("id", flavor.id);
            item.put("name", flavor.name);
            item.put("family", flavor.family);
            item.put("deleted", flavor.deleted);
            item.put("isDisabled", flavor.isDisabled);
            item.put("isPublic", flavor.isPublic);
            item.put("memory", flavor.memory);
            item.put("vcpu", flavor.vcpus);
            item.put("clockSpeed", flavor.clockSpeed);
            item.put("instanceStore", flavor.instanceStore);                        
            item.put("isEbsOptimized", flavor.isEbsOptimized);
            item.put("rootgb", flavor.rootgb);
            item.put("createdAt", flavor.createdAt);
            flavorList.add(item);      
                                    
        }
        return flavorList
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
    
    def updateFlavorStatus (requestData) {
        try { 
            ArrayList<ArrayList<String>> flavorList = new ArrayList<ArrayList<String>>();  
            HashMap<String,String> flavorItem = new HashMap<String,String>(); 
            def flavor =  Flavors.findById(requestData.flavorId);   
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
       
    def getFlavorDetails(id) {
        
        def flavor = Flavors.findById(id)
        ArrayList list = new ArrayList()
        HashMap map = new HashMap()
        map.put("id",flavor.id)
        map.put("name",flavor.name)
        map.put("clockSpeed",flavor.clockSpeed)
        map.put("family",flavor.family)
        map.put("isPublic",flavor.isPublic == true ? "Yes" : "No")
        map.put("memory",flavor.memory)
        map.put("vcpus",flavor.vcpus)
        map.put("instanceStore",flavor.instanceStore)
        map.put("isEbsOptimized",flavor.isEbsOptimized == "1" ? "Yes" : "No")
        map.put("rootgb",flavor.rootgb)
        map.put("status",flavor.isDisabled == true ? "Disabled" : "Enabled")
        list.add(map)
        return list
    }
}  
