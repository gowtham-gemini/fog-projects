package com.assistanz.fogpanel

//@gorm.AuditStamp
class DiskOfferZoneCost {
    
    DiskOffer diskOffer
    Zone zone
    Double cost
    Double minimumCost
    
//    Integer createdBy
//    Integer editedBy
//    Date editedDate
//    Date createdDate
    
    static constraints = {
        diskOffer(nullable: false, blank: false)
        zone(nullable: false, blank: false)
        cost(nullable: false, blank: false)
        minimumCost(nullable: false, blank: false)
        
//        createdBy   nullable:true,display:false,editable:false
//        editedBy    nullable:true,display:false,editable:false
//        editedDate  nullable:true,display:false,editable:false
//        createdDate nullable:true,display:false,editable:false
    }
    
    static belongsTo = [diskOffer: DiskOffer]
}
