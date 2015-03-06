package com.assistanz.fogpanel

//@gorm.AuditStamp
class Snapshot {

    String snapshotReferenceId
    String name
    Boolean deleted
    User user
    String state
    String job
    String snapshotType
    String created
    Volume volume
    SnapshotPolicy snapshotPolicy
    String billingType = "hourly"
    Double size
    Zone zone
    
//    Integer createdBy
//    Integer editedBy
//    Date editedDate
//    Date createdDate
    
    static constraints = {
        state(nullable: true, blank: true)
        name(nullable: true, blank: true)
        created(nullable: true, blank: true)
        snapshotReferenceId(nullable: true, unique: true, blank: true)
        deleted(nullable: false, blank: false)
        size(nullable: false, blank: false)
        user(nullable: false, blank: false)
        volume(nullable: false, blank: false)
        snapshotType(nullable: true, blank: true)
        snapshotPolicy(nullable: true, blank: true)
        job(nullable: true, blank: true, unique: true)
        billingType(nullable: true, blank: true)
        zone(nullable: true, blank: true)
        
//        createdBy   nullable:true,display:false,editable:false
//        editedBy    nullable:true,display:false,editable:false
//        editedDate  nullable:true,display:false,editable:false
//        createdDate nullable:true,display:false,editable:false
    }
}
