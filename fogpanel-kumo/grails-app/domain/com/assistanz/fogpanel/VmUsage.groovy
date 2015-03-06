package com.assistanz.fogpanel

//@gorm.AuditStamp
class VmUsage {

    Double diskRead
    Double diskWrite
    Double cpuUtil
    Double memeoryUtil
    Double networkRead
    Double networkWrite
    Date date
    VirtualMachine virtualMachine
    
//    Integer createdBy
//    Integer editedBy
//    Date editedDate
//    Date createdDate
    
    static constraints = {
        
        diskRead(nullable: true, blank: true)
        diskWrite(nullable: true, blank: true)
        cpuUtil(nullable: true, blank: true)
        memeoryUtil(nullable: true, blank: true)
        networkRead(nullable: true, blank: true)
        networkWrite(nullable: true, blank: true)
        date(nullable: true, blank: true)
        virtualMachine(nullable: true, blank: true)
        
//        createdBy   nullable:true,display:false,editable:false
//        editedBy    nullable:true,display:false,editable:false
//        editedDate  nullable:true,display:false,editable:false
//        createdDate nullable:true,display:false,editable:false
        
    }
}
