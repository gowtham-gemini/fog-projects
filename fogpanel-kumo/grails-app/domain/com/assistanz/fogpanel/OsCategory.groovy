package com.assistanz.fogpanel

//@gorm.AuditStamp
class OsCategory {

    String name;	
    String osCategoryId;
    String baseOs;
    
//    Long createdBy
//    Long editedBy
//    Date editedDate
//    Date createdDate
    
    static constraints = {
        
        osCategoryId(nullable: false, blank: false, unique: true)
        name(nullable: false, blank: false)	 
        baseOs(nullable: false, blank: false)
        
//        createdBy   nullable:true,display:false,editable:false
//        editedBy    nullable:true,display:false,editable:false
//        editedDate  nullable:true,display:false,editable:false
//        createdDate nullable:true,display:false,editable:false
    }
    
}
