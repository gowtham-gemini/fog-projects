package com.assistanz.fogpanel

//@gorm.AuditStamp
class MiscellaneousOfferZoneCost {

    MiscellaneousOffer miscellaneousOffer
    Zone zone    
    double cost
    
//    Integer createdBy
//    Integer editedBy
//    Date editedDate
//    Date createdDate
    
    static constraints = {
        miscellaneousOffer(nullable: false, blank: false)
        zone(nullable: false, blank: false)                
        
//        createdBy   nullable:true,display:false,editable:false
//        editedBy    nullable:true,display:false,editable:false
//        editedDate  nullable:true,display:false,editable:false
//        createdDate nullable:true,display:false,editable:false
        
    }
    static belongsTo = [miscellaneousOffer: MiscellaneousOffer]
}
