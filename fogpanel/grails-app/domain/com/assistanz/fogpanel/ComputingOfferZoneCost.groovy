package com.assistanz.fogpanel

//@gorm.AuditStamp
class ComputingOfferZoneCost {
    
    ComputingOffer computingOffer
    Zone zone
    Double cost
    Double setupCost
    Double minimumCost
    
//    Integer createdBy
//    Integer editedBy
//    Date editedDate
//    Date createdDate
    
    static constraints = {
        computingOffer(nullable: false, blank: false)
        zone(nullable: false, blank: false)
        cost(nullable: false, blank: false)
        setupCost(nullable: false, blank: false)
        minimumCost(nullable: false, blank: false)
        
//        createdBy   nullable:true,display:false,editable:false
//        editedBy    nullable:true,display:false,editable:false
//        editedDate  nullable:true,display:false,editable:false
//        createdDate nullable:true,display:false,editable:false
    }
    
    static belongsTo = [computingOffer: ComputingOffer]
}
