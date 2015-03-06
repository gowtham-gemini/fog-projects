package com.assistanz.fogpanel

//@gorm.AuditStamp
class Iso {
    
    String isoReferenceId
    String description	
    String name	
    OsType osType	
    OsCategory osCategory
    String baseOs
    String url	
    Zone zone	
    Boolean bootable
    Boolean extractable	
    Boolean featured	
    Boolean isPublic
    Boolean deleted
    String job
    
//    Integer createdBy
//    Integer editedBy
//    Date editedDate
//    Date createdDate
    
    static constraints = {
        isoReferenceId(nullable: false, unique: true, blank: false)
        description(nullable: false, blank: false)
        name(nullable: false, blank: false)
        osType(nullable: true, blank: true)
        osCategory(nullable: true, blank: true)
        baseOs(nullable: true, blank: true)
        url(nullable: false, blank: false)
        zone(nullable: true, blank: true)
        extractable(nullable: true, blank: true)
        featured(nullable: true, blank: true)
        bootable(nullable: true, blank: true)
        isPublic(nullable: true, blank: true)
        deleted(nullable: false, blank: false)  
        job(nullable: true, blank: true)
        
//        createdBy   nullable:true,display:false,editable:false
//        editedBy    nullable:true,display:false,editable:false
//        editedDate  nullable:true,display:false,editable:false
//        createdDate nullable:true,display:false,editable:false
    }   
}
