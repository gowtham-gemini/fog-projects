package com.assistanz.fogpanel

//@gorm.AuditStamp
class CloudMaintenance {

    Date date
    String description
    Boolean deleted
    Boolean mailSend = true
//    Integer createdBy
//    Integer editedBy
//    Date editedDate
//    Date createdDate
    
    static constraints = {        
        
        date(nullable: false, blank: false)
        description(nullable: false, blank: false, size:0..5000)
        deleted(nullable: false, blank: false)
        mailSend (nullable: false, blank: false)
//        createdBy   nullable:true,display:false,editable:false
//        editedBy    nullable:true,display:false,editable:false
//        editedDate  nullable:true,display:false,editable:false
//        createdDate nullable:true,display:false,editable:false
    }
    static hasMany = [zones:Zone]
}
