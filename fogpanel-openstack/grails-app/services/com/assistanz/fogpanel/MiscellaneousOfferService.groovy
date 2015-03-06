package com.assistanz.fogpanel

import grails.converters.JSON
import org.apache.commons.logging.LogFactory;
import org.codehaus.groovy.grails.commons.ApplicationHolder

class MiscellaneousOfferService {
    
    def updateMisc(String requestBody) {
        try {                  
            // convert string to json object
            def requestData = JSON.parse(requestBody)                                          
            def oldCost = 0.00;             
            def miscellaneousOffer =  MiscellaneousOffer.get(requestData.id);   
            def miscCost = new Double(requestData.cost)
            if(miscellaneousOffer.id == 3 || miscellaneousOffer.id == 4 || miscellaneousOffer.id == 5 || miscellaneousOffer.id == 6 || miscellaneousOffer.id == 7 || miscellaneousOffer.id == 8) {
               if(miscCost == 0.0) {
                   throw new NullPointerException("cost cannot be zero");
               } else {
                   miscellaneousOffer.cost = miscCost
               }
            } else {
                miscellaneousOffer.cost = 0.0;                
            }
            if(requestData.zoneCosts) {
                if(miscellaneousOffer) {                                                                
                    for(int i = 0; i < requestData.zoneCosts.length(); i++) {
                        MiscellaneousOfferZoneCost existsMiscellaneousOfferZoneCost = 
                        MiscellaneousOfferZoneCost.findWhere(miscellaneousOffer: MiscellaneousOffer.get(requestData.id),
                            zone: Zone.get(requestData.zoneCosts[i].zoneId)); 
                        Double cost = new Double(requestData.zoneCosts[i].cost);
                        if(cost == 0.0) {
                            throw new NullPointerException("cost cannot be zero");
                        }            
                        if(existsMiscellaneousOfferZoneCost) {
                            oldCost = existsMiscellaneousOfferZoneCost.cost
                            existsMiscellaneousOfferZoneCost.cost = cost
                            existsMiscellaneousOfferZoneCost.save(flush: true)
                        } else {
                            miscellaneousOffer.addToMiscellaneousOfferZoneCosts(new MiscellaneousOfferZoneCost(
                                    MiscellaneousOffer : MiscellaneousOffer.get(requestData.id),
                                    zone:Zone.get(requestData.zoneCosts[i].zoneId),                         
                                    cost: cost
                                )
                            )  
                        }                    
                    }
                }            
            } else if(requestData.regionCosts) {   
                if(miscellaneousOffer) {                                                                
                    for(int i = 0; i < requestData.regionCosts.length(); i++) {
                        MiscellaneousOfferRegionCost existsMiscellaneousOfferRegionCost = 
                        MiscellaneousOfferRegionCost.findWhere(miscellaneousOffer: MiscellaneousOffer.get(requestData.id),
                            region: Region.get(requestData.regionCosts[i].regionId)); 
                        Double cost = new Double(requestData.regionCosts[i].cost);  
                        if(cost == 0.0) {  
                            throw new NullPointerException("cost cannot be zero");
                        }              
                        if(existsMiscellaneousOfferRegionCost) {
                            oldCost = existsMiscellaneousOfferRegionCost.cost
                            existsMiscellaneousOfferRegionCost.cost = cost
                            existsMiscellaneousOfferRegionCost.save(flush: true)
                            if (!existsMiscellaneousOfferRegionCost.save()) {
                                throw new ValidationException(invitation.errors.allErrors)                                          
                            }
                        } else {
                            println("id"  + requestData.id )
                            println("regionCosts"  + cost)
                            println("regionId"  + requestData.regionCosts[i].regionId)
                            
                            miscellaneousOffer.addToMiscellaneousOfferRegionCosts(new MiscellaneousOfferRegionCost(
                                    MiscellaneousOffer : MiscellaneousOffer.get(requestData.id),
                                    region : Region.get(requestData.regionCosts[i].regionId),                         
                                    cost: cost
                                )
                            )
                        }                    
                    }
                }
            }
            //save MiscellaneousOffer
            miscellaneousOffer.save(flush: true);                        
            if (!miscellaneousOffer.save()) {
                throw new ValidationException(invitation.errors.allErrors)                                          
            } 
            log.info("Updated Miscellaneous for : ${miscellaneousOffer.name}, successfully")
             
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
    
    def getCurrentMiscOffer(String miscellaneousOfferId) { 
        try {
            def currency = ApplicationHolder.getApplication().config.billing.defaultCurrency
            ArrayList miscArrayList = new ArrayList(); 
            ArrayList miscZoneCostArrayList = new ArrayList(); 
            ArrayList miscRegionCostArrayList = new ArrayList(); 
            
            def miscZoneQuery = MiscellaneousOfferZoneCost.createCriteria()
            def miscRegionQuery = MiscellaneousOfferRegionCost.createCriteria()
            
            HashMap miscItem = new HashMap();  
            if(miscellaneousOfferId == "null" || miscellaneousOfferId == null) {
                return MiscellaneousOffer.findAll();   
            } else if(miscellaneousOfferId != "null" || miscellaneousOfferId != null) {  
                def miscOffer = MiscellaneousOffer.get(miscellaneousOfferId)
                miscItem.put("name", miscOffer.name)
                miscItem.put("unit", miscOffer.unit)     
                miscItem.put("description", miscOffer.description)
                miscItem.put("id", miscOffer.id)
                miscItem.put("currency", currency)
                miscItem.put("cost", miscOffer.cost)       
                def miscZoneCostResult = miscZoneQuery.list {
                    eq("miscellaneousOffer", miscOffer)    
                    order("id", "asc")                
                }
                
                def miscRegionCostResult = miscRegionQuery.list {
                    eq("miscellaneousOffer", miscOffer)    
                    order("id", "asc")                
                } 
                if(miscZoneCostResult) {
                    for(def miscZone: miscZoneCostResult) {                
                        HashMap miscOfferZoneItem = new HashMap();  
                        miscOfferZoneItem.put("miscellaneousOffer", "")
                        miscOfferZoneItem.put("cost", miscZone.cost)
                        miscOfferZoneItem.put("currency", currency)                
                        miscOfferZoneItem.put("id", miscZone.id)                           
                        miscOfferZoneItem.put("zone", miscZone.zone)                
                        miscZoneCostArrayList.add(miscOfferZoneItem)
                    } 
                } 
                
                if(miscRegionCostResult) {
                    for(def miscRegion: miscRegionCostResult) {  
                        HashMap miscOfferRegionItem = new HashMap();  
                        miscOfferRegionItem.put("miscellaneousOffer", "")
                        miscOfferRegionItem.put("cost", miscRegion.cost)
                        miscOfferRegionItem.put("currency", currency)                
                        miscOfferRegionItem.put("id", miscRegion.id)                           
                        miscOfferRegionItem.put("region", miscRegion.region)                
                        miscRegionCostArrayList.add(miscOfferRegionItem)
                    } 
                } 
                miscItem.put("miscellaneousOfferZoneCosts", miscZoneCostArrayList)
                miscItem.put("miscellaneousOfferRegionCosts", miscRegionCostArrayList)                
                miscArrayList.add(miscItem)
                return miscArrayList;
            }
        } catch (Exception ex) {    
            ex.printStackTrace(System.err);
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }         
    }
}
