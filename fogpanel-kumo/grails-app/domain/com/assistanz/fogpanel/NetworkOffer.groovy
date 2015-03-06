package com.assistanz.fogpanel

//@gorm.AuditStamp
class NetworkOffer {
    
    String networkReferenceId
    String name
    String description
    String networkRate
    String trafficType
    String availability  
    String guestIpType 
//    Zone zone
    Date created      
    Boolean isDefault   
    Boolean specifyVlan
    Boolean specifyIpRanges   
    Boolean conserveMode   
    Boolean deleted
    String state  
        
    static constraints = {
        networkReferenceId(nullable: false, blank: false) 
        name(nullable: false, blank: false) 
        description(nullable: false, blank: false) 
        networkRate(nullable: false, blank: false) 
        trafficType(nullable: false, blank: false) 
        availability(nullable: false, blank: false) 
        guestIpType(nullable: true, blank: true)  
//        zone(nullable: false, blank: false)
        created(nullable: true, blank: true)   
        isDefault(nullable: false, blank: false)
        specifyVlan(nullable: false, blank: false)
        specifyIpRanges(nullable: false, blank: false)
        conserveMode(nullable: true, blank: false)
        deleted(nullable: false, blank: false) 
        state(nullable: true, blank: true)        
    }    
    static hasMany = [networkOfferZoneCosts: NetworkOfferZoneCost]
    
    static mapping = {
        networkOfferZoneCosts cascade: "all-delete-orphan"
    }   
}
