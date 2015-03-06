package com.assistanz.fogpanel

//@gorm.AuditStamp
class Template {
    
    String templateReferenceId
    String description	
    String format	
    String hypervisor	
    String architecture // 64
    String name	
    OsType osType	
    OsCategory osCategory
    String baseOs // 
    String url	//
    Boolean extractable	
    Boolean featured	
    Boolean isPublic
    Boolean myTemplate
    Boolean appTemplate
    Boolean passwordEnabled
    Boolean deleted
    Boolean isReady
    Double cost = 0.0
    String job 
    Account account
    User user
    String status
    Double size
    String path
    String detailedDescription //
    String osReferenceURL //
    String minimumCpu = "1"	
    String minimumRam = "512"
    boolean oneTimeChargeable = false
//    String billingType = "FREE";
 
//    Integer createdBy
//    Integer editedBy
//    Date editedDate
//    Date createdDate
    
    static constraints = {
        templateReferenceId(nullable: false, unique: true, blank: false)
        description(nullable: false, blank: false)
        detailedDescription(nullable: false, blank: false, size:0..10000)
        osReferenceURL(nullable: true, blank: true)        
        job(nullable: true, blank: true)
        path(nullable: true, blank: true)        
        format(nullable: false, blank: false)
        name(nullable: false, blank: false)
        architecture(nullable: false, blank: false)
        hypervisor(nullable: false, blank: false)
        osType(nullable: false, blank: false)
        osCategory(nullable: false, blank: false)
        url(nullable: false, blank: false)
        extractable(nullable: true, blank: true)
        featured(nullable: true, blank: true)
        isPublic(nullable: true, blank: true)
        myTemplate(nullable: false, blank: false)
        appTemplate(nullable: false, blank: false)
        passwordEnabled(nullable: true, blank: true)
        deleted(nullable: false, blank: false)  
        isReady(nullable: false, blank: false)          
        baseOs(nullable: true, blank: true)
        account(nullable: true, blank: true)
        user(nullable: true, blank: true)
        cost(nullable: false, blank: false)
        status(nullable: false, blank: false)
        size(nullable: true, blank: true)
        
        minimumCpu(nullable: true, blank: true)
        minimumRam(nullable: true, blank: true)
        oneTimeChargeable(nullable: false, blank: false)
//        billingType(nullable: false, blank: false)
        
//        createdBy   nullable:true,display:false,editable:false
//        editedBy    nullable:true,display:false,editable:false
//        editedDate  nullable:true,display:false,editable:false
//        createdDate nullable:true,display:false,editable:false
    }   
     static hasMany = [zones:Zone]
}
