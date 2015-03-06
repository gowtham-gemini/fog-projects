package com.assistanz.fogpanel

//@gorm.AuditStamp
class Credit {
    
    Account account
    Date date
    String description
    Double amount
    String relId
    
//    Integer createdBy
//    Integer editedBy
//    Date editedDate
//    Date createdDate
    
    static constraints = {
        
        account (nullable: false, blank: false)
        date (nullable: false, blank: false)
        description (nullable: false, blank: false)
        amount (nullable: false, blank: false)
        relId (nullable: false, blank: false)
        
//        createdBy   nullable:true,display:false,editable:false
//        editedBy    nullable:true,display:false,editable:false
//        editedDate  nullable:true,display:false,editable:false
//        createdDate nullable:true,display:false,editable:false
        
    }
}
