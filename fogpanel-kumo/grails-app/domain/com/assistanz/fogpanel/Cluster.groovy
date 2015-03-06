package com.assistanz.fogpanel

//@gorm.AuditStamp
class Cluster {
    
    String clusterReferenceId;
    String name;
    String description;
    Pod pod;
    Zone zone;
    
//    Integer createdBy
//    Integer editedBy
//    Date editedDate
//    Date createdDate
    
    static constraints = {
        clusterReferenceId(nullable: false, unique: true, blank: false)
        name(nullable: false, blank: false)
        description(nullable: false, blank: false)
        zone(nullable: false, blank: false)
        pod(nullable: false, blank: false)
        
//        createdBy   nullable:true,display:false,editable:false
//        editedBy    nullable:true,display:false,editable:false
//        editedDate  nullable:true,display:false,editable:false
//        createdDate nullable:true,display:false,editable:false
    }
}
