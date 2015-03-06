package com.assistanz.fogpanel

//@gorm.AuditStamp
class OsType {
    
    String referenceOsTypeId;	
    String name;	
    OsCategory osCategory;	
    
//    Integer createdBy
//    Integer editedBy
//    Date editedDate
//    Date createdDate

    static constraints = {
        
        referenceOsTypeId(nullable: false, blank: false, unique: true)
        name(nullable: false, blank: false)	
        osCategory(nullable: false, blank: false)
        
//        createdBy   nullable:true,display:false,editable:false
//        editedBy    nullable:true,display:false,editable:false
//        editedDate  nullable:true,display:false,editable:false
//        createdDate nullable:true,display:false,editable:false
    }
}
