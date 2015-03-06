package com.assistanz.fogpanel

/**
 * A fee charged by a government on a service.
 * The information about tax
 */
//@gorm.AuditStamp
class Tax {
    
    /**
     * Name of the tax
     */
    String name
    
    /**
     * Description about the tax
     */
    String description 
    
    /**
     * The percentage value of the tax. 
     */
    Double percentage
    
//    Integer createdBy
//    Integer editedBy
//    Date editedDate
//    Date createdDate
    
    static constraints = {
        name(nullable: false, blank: false, unique: true)   
        description(nullable: false, blank: false)   
        percentage(nullable: false, blank: false)   
        
//        createdBy   nullable:true,display:false,editable:false
//        editedBy    nullable:true,display:false,editable:false
//        editedDate  nullable:true,display:false,editable:false
//        createdDate nullable:true,display:false,editable:false
    }
}
