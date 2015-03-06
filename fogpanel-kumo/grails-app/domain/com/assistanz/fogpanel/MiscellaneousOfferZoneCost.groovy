package com.assistanz.fogpanel

//@gorm.AuditStamp
class MiscellaneousOfferZoneCost {

    MiscellaneousOffer miscellaneousOffer
    Zone zone
    Pod pod
    Cluster cluster
    double cost
    
//    Integer createdBy
//    Integer editedBy
//    Date editedDate
//    Date createdDate
    
    static constraints = {
        miscellaneousOffer(nullable: false, blank: false)
        zone(nullable: false, blank: false)
        cost(nullable: false, blank: false)
        pod(nullable: false, blank: false)
        cluster(nullable: false, blank: false)
        
//        createdBy   nullable:true,display:false,editable:false
//        editedBy    nullable:true,display:false,editable:false
//        editedDate  nullable:true,display:false,editable:false
//        createdDate nullable:true,display:false,editable:false
        
    }
    static belongsTo = [miscellaneousOffer: MiscellaneousOffer]
}
