package com.assistanz.fogpanel

//@gorm.AuditStamp
class Volume {
    
    String volumeReferenceId
    String name
    String type
    String job
    String storageName
    String customDiskSize
    DiskOffer diskOffer
    VirtualMachine virtualMachine
    Account owner
    Boolean deleted
    User user
    Zone zone
    String state
    String source
    Cluster cluster
    String billingType = "hourly"
    
//    Integer createdBy
//    Integer editedBy
//    Date editedDate
//    Date createdDate
    
    static constraints = {
        state(nullable: true, blank: true)
        customDiskSize(nullable: true, blank: true)
        volumeReferenceId(nullable: true, unique: true, blank: true)
        job(nullable: true, blank: true, unique: true)
        type(nullable: true, blank: true)
        zone(nullable: false, blank: false)
        name(nullable: false, blank: false)
        diskOffer(nullable: true, blank: true)
        owner(nullable: false, blank: false) 
        deleted(nullable: false, blank: false) 
        virtualMachine(nullable: true, blank: true) 
        user(nullable: false, blank: false)
        source(nullable: true, blank: true)
        cluster(nullable: true, blank: true)
        storageName(nullable: true, blank: true)
        billingType(nullable: true, blank: true)
//        createdBy   nullable:true,display:false,editable:false
//        editedBy    nullable:true,display:false,editable:false
//        editedDate  nullable:true,display:false,editable:false
//        createdDate nullable:true,display:false,editable:false
    }
}
