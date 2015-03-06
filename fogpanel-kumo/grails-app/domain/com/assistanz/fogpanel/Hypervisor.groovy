package com.assistanz.fogpanel

//@gorm.AuditStamp
class Hypervisor {
    
    String name
    Zone zone
    
//    Integer createdBy
//    Integer editedBy
//    Date editedDate
//    Date createdDate
    
    static constraints = {
        
        name(nullable: false, blank: false)
        zone(nullable: false, blank: false)
        
//        createdBy   nullable:true,display:false,editable:false
//        editedBy    nullable:true,display:false,editable:false
//        editedDate  nullable:true,display:false,editable:false
//        createdDate nullable:true,display:false,editable:false
    }
}
