package com.assistanz.fogpanel

//@gorm.AuditStamp
class MailTemplate {
    
    String name
    String content
    String subject
    Boolean hasHeader
    Boolean hasFooter
    Boolean hasSignature
    String title
//    Integer createdBy
//    Integer editedBy
//    Date editedDate
//    Date createdDate
    
    static constraints = {
        
        name(nullable: false, blank: false, unique: true)
        content(nullable: false, blank: false, size:0..5000)
        subject(nullable: true, blank: true, size:0..5000)
        title(nullable: true, blank: true, size:0..5000)
        hasHeader(nullable: false, blank: false)
        hasFooter(nullable: false, blank: false)
        hasSignature(nullable: false, blank: false)
        
//        createdBy   nullable:true,display:false,editable:false
//        editedBy    nullable:true,display:false,editable:false
//        editedDate  nullable:true,display:false,editable:false
//        createdDate nullable:true,display:false,editable:false
    }
}
