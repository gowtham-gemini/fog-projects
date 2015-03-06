package com.assistanz.fogpanel

//@gorm.AuditStamp
class StoragePool {

    String storagePoolReferenceId;
    String name;
    String tag;
    String storagePoolType;
    Double totalSize;
    Cluster cluster;
    
//    Integer createdBy
//    Integer editedBy
//    Date editedDate
//    Date createdDate
    
    static constraints = {
        storagePoolReferenceId(nullable: false, unique: true, blank: false)
        name(nullable: false, blank: false)
        tag(nullable: true, blank: true)
        storagePoolType(nullable: false, blank: false)
        cluster(nullable: false, blank: false)
        totalSize(nullable: false, blank: false)
        
//        createdBy   nullable:true,display:false,editable:false
//        editedBy    nullable:true,display:false,editable:false
//        editedDate  nullable:true,display:false,editable:false
//        createdDate nullable:true,display:false,editable:false
    }
}
