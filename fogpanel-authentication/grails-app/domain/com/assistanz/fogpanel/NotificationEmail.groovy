package com.assistanz.fogpanel

//@gorm.AuditStamp
class NotificationEmail {

    String email
    User user
     
//    Integer createdBy
//    Integer editedBy
//    Date editedDate
//    Date createdDate
    
    static constraints = {
        email (email: true, blank: false, unique: true)
        user (nullable: false, blank: false)
        
//        createdBy   nullable:true,display:false,editable:false
//        editedBy    nullable:true,display:false,editable:false
//        editedDate  nullable:true,display:false,editable:false
//        createdDate nullable:true,display:false,editable:false
    }
}
