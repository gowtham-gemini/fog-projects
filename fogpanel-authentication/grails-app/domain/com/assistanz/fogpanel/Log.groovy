package com.assistanz.fogpanel

//@gorm.AuditStamp
class Log {
    
    User user
    Account account
    LogType type
    Date date
    String description
    Boolean viewed = false
    Boolean mailSend = true
    String creditLimitPercentage
    
//    Integer createdBy
//    Integer editedBy
//    Date editedDate
//    Date createdDate

    static constraints = {
        user (nullable: false, blank: false)
        type (nullable: false, blank: false)
        date (nullable: false, blank: false)
        description (nullable: false, blank: false)
        viewed (nullable: false, blank: false)
        mailSend (nullable: false, blank: false)
        creditLimitPercentage (nullable: true, blank: true)
        
//        createdBy   nullable:true,display:false,editable:false
//        editedBy    nullable:true,display:false,editable:false
//        editedDate  nullable:true,display:false,editable:false
//        createdDate nullable:true,display:false,editable:false
    }
}

 enum LogType  {
        SUSPENDED, //0 
        OVERLIMITALERT, //1
        DISABLED, //2
        ACCOUNT_ACTIONS, //3
        USAGE_STATUS //4
    }