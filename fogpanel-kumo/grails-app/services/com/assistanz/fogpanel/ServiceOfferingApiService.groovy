package com.assistanz.fogpanel
import org.codehaus.groovy.grails.commons.ApplicationHolder


class ServiceOfferingApiService {

    def getComputingOffer(baseOs, storageType) {
        try {            
            ArrayList<ArrayList<String>> computingOfferList = new ArrayList<ArrayList<String>>();
            def currency = ApplicationHolder.getApplication().config.billing.defaultCurrency
            def computingOffer =  ComputingOffer.withCriteria {   
                eq("deleted", false)  
                and {
                   eq("available", true) 
                } 
                if(baseOs) {
                    eq("baseOs", baseOs)  
                }
                if(storageType) {
                    eq("storageType", storageType)  
                }            
            }
            for(int i=0; i < computingOffer.size(); i++) { 
                def item = computingOffer[i]; 
                HashMap<String,String> computingOfferItem = new HashMap<String,String>();    
                computingOfferItem.put("id", item.id);
                computingOfferItem.put("available", item.available);
                computingOfferItem.put("bandWidth", item.bandWidth);
                computingOfferItem.put("baseOS", item.baseOs);                
                computingOfferItem.put("referenceZoneId", item.zone.referenceZoneId);
                computingOfferItem.put("zone", item.zone.aliasName);
                computingOfferItem.put("computingOfferZoneCosts", item.computingOfferZoneCosts.cost[0]);
                computingOfferItem.put("coreCpuSpeed", item.coreCpuSpeed);
                computingOfferItem.put("cpuNumber", item.cpuNumber);
                computingOfferItem.put("defaultUse", item.defaultUse);
                computingOfferItem.put("deleted", item.deleted);
                computingOfferItem.put("description", item.description);
                computingOfferItem.put("hostTags", item.hostTags);
                computingOfferItem.put("isSystem", item.isSystem);                
                computingOfferItem.put("limitCpuUse", item.limitCpuUse);
                computingOfferItem.put("memory", item.memory);
                computingOfferItem.put("name", item.name);
                computingOfferItem.put("networkRate", item.networkRate);
                computingOfferItem.put("offerHA", item.offerHA);
                computingOfferItem.put("offerReferenceId", item.offerReferenceId);
                computingOfferItem.put("orderNo", item.orderNo);
                computingOfferItem.put("memory", item.memory);
                computingOfferItem.put("storageType", item.storageType);                
                computingOfferItem.put("tags", item.tags);
                computingOfferItem.put("currency", currency);                
                computingOfferList.add(computingOfferItem);
            }
            return computingOfferList
        } catch(Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }        
    }
}
