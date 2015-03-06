package com.assistanz.fogpanel

//@gorm.AuditStamp
class MiscellaneousOffer {

    String name
    String unit
    String description
    
//    Integer createdBy
//    Integer editedBy
//    Date editedDate
//    Date createdDate
    
    static constraints = {
        name(nullable: false, blank: false)
        unit(nullable: false, blank: false)
        description(nullable: false, blank: false)
        
//        createdBy   nullable:true,display:false,editable:false
//        editedBy    nullable:true,display:false,editable:false
//        editedDate  nullable:true,display:false,editable:false
//        createdDate nullable:true,display:false,editable:false
    }
    
    static hasMany = [miscellaneousOfferZoneCosts: MiscellaneousOfferZoneCost]
    
    static mapping = {
        miscellaneousOfferZoneCosts cascade: "all-delete-orphan"
    }
}
