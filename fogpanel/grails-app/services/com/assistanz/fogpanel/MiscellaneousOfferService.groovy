package com.assistanz.fogpanel

import grails.converters.JSON
import org.apache.commons.logging.LogFactory;
import org.codehaus.groovy.grails.commons.ApplicationHolder
class MiscellaneousOfferService {
    
    LicenseValidationService licenseValidationService
    def springSecurityService
    private static final log = LogFactory.getLog(this)

    def list(String clusterReferenceId, String miscellaneousOfferId) {
        
        if((clusterReferenceId == "null" || clusterReferenceId == null) && (miscellaneousOfferId == "null" || miscellaneousOfferId == null)) {
            return MiscellaneousOffer.findAll();   
        } else if((clusterReferenceId != "null" || clusterReferenceId != null) && (miscellaneousOfferId != "null" || miscellaneousOfferId != null)) {
            def miscelOffer =  MiscellaneousOfferZoneCost.findAllWhere(miscellaneousOffer: MiscellaneousOffer.get(miscellaneousOfferId), cluster: Cluster.findByClusterReferenceId(clusterReferenceId));              
            return miscelOffer
        } 
    }
    
    def getMisc (String zoneReferenceId, String miscellaneousOfferId) {
        if((zoneReferenceId == "null" || zoneReferenceId == null) && (miscellaneousOfferId == "null" || miscellaneousOfferId == null)) {
            return MiscellaneousOffer.findAll();   
        } else if((zoneReferenceId != "null" || zoneReferenceId != null) && (miscellaneousOfferId != "null" || miscellaneousOfferId != null)) {
            def miscelOffer =  MiscellaneousOfferZoneCost.findAllWhere(miscellaneousOffer: MiscellaneousOffer.get(miscellaneousOfferId), zone: Zone.findByReferenceZoneId(zoneReferenceId));              
            return miscelOffer
        }
    }
    def getCurrentMiscOffer (id) {        
        try {                  
            def currency = ApplicationHolder.getApplication().config.billing.defaultCurrency
            def miscOffer = MiscellaneousOffer.get(id);            
            
            def miscZoneQuery = MiscellaneousOfferZoneCost.createCriteria()
            def miscZoneCostResult = miscZoneQuery.list {
               eq("miscellaneousOffer", miscOffer)    
               order("id", "asc")                
            }
            
            ArrayList miscArrayList = new ArrayList(); 
            ArrayList miscZoneCostArrayList = new ArrayList(); 
            
            HashMap miscItem = new HashMap();  
            miscItem.put("description", miscOffer.description)
            miscItem.put("id", miscOffer.id)
            miscItem.put("name", miscOffer.name)
            miscItem.put("unit", miscOffer.unit)                                
            for(def miscZone: miscZoneCostResult) {                
                HashMap miscOfferZoneItem = new HashMap();  
                miscOfferZoneItem.put("miscellaneousOffer", "")
                miscOfferZoneItem.put("cost", miscZone.cost)
                miscOfferZoneItem.put("currency", currency)                
                miscOfferZoneItem.put("id", miscZone.id)                           
                miscOfferZoneItem.put("zone", miscZone.zone)                
                miscZoneCostArrayList.add(miscOfferZoneItem)
            }                        
            miscItem.put("miscellaneousOfferZoneCosts", miscZoneCostArrayList)
            miscArrayList.add(miscItem)            
            return miscArrayList;
        } catch (Exception ex) {    
            ex.printStackTrace(System.err);
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
         
    }
    def update(String requestBody) {        
        try {            
            licenseValidationService.authorizeAction(FogPanelService.OFFERING_UPDATE)                        
            def user = springSecurityService.currentUser
            def role = springSecurityService.getPrincipal().getAuthorities()      
            
            // convert string to json object
            def requestData = JSON.parse(requestBody)                                          
            def oldCost = 0.00;          
            def miscellaneousOffer =  MiscellaneousOffer.get(requestData.id);               
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
                            MiscellaneousOffer : miscellaneousOffer.get(requestData.id),
                            zone:Zone.get(requestData.zoneCosts[i].zoneId),                         
                            cost: cost
                        )
                    )  
                }                    
            }
            } 
            //save MiscellaneousOffer
            miscellaneousOffer.save(flush: true);                        
//            def newCost = MiscellaneousOfferZoneCost.findWhere(miscellaneousOffer: miscellaneousOffer);            
//            if(oldCost != newCost.cost) {            
//                def serviceCostChangeLog = new ServiceCostChangeLog()
//                serviceCostChangeLog.serviceName = ServiceName.valueOf("MISC_OFFER")
//                serviceCostChangeLog.oldCost = oldCost
//                serviceCostChangeLog.changedDate = new Date()
//
//                serviceCostChangeLog.user = user
//                serviceCostChangeLog.account = user.account
//
//                serviceCostChangeLog.newCost = newCost.cost
//                serviceCostChangeLog.miscellaneousOfferZoneCost = newCost;
//                serviceCostChangeLog.save(flush: true)              
//            }
            log.info("Updated Miscellaneous for : ${miscellaneousOffer.name}, successfully")   
            if (miscellaneousOffer.hasErrors()) {
                throw new ValidationException(miscellaneousOffer.errors.allErrors);
            }            
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
}
