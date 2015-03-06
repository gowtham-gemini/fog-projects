package com.assistanz.fogpanel

//@gorm.AuditStamp
class Host {
    String hostReferenceId;
    String name;
    Integer cpuNumberTotal;
    Integer coreCpuSpeedTotal; 
    Double memoryTotal;
    String description;
    String hostTag;
    Zone zone;
    Pod pod;
    Cluster cluster;
    
//    Integer createdBy
//    Integer editedBy
//    Date editedDate
//    Date createdDate
    

    static constraints = {
        hostReferenceId(nullable: false, unique: true, blank: false)
        hostTag(nullable: true, blank: true)
        cpuNumberTotal(nullable: false, blank: false)
        coreCpuSpeedTotal(nullable: false, blank: false)
        memoryTotal(nullable: false, blank: false)
        name(nullable: false, blank: false)
        description(nullable: false, blank: false)
        zone(nullable: false, blank: false)
        pod(nullable: false, blank: false)
        cluster(nullable: false, blank: false)
        
//        createdBy   nullable:true,display:false,editable:false
//        editedBy    nullable:true,display:false,editable:false
//        editedDate  nullable:true,display:false,editable:false
//        createdDate nullable:true,display:false,editable:false
        
    }
}
