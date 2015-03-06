package com.assistanz.fogpanel

//@gorm.AuditStamp
class Domain {
    
    String referenceId
    Boolean hasChild
    Integer level
    String name
    String parentDomainId	
    String networkDomain
    String parentDomainName	
    String path	
    
//    Integer createdBy
//    Integer editedBy
//    Date editedDate
//    Date createdDate
    
    static constraints = {
        referenceId(nullable: false, unique: true, blank: false)
        hasChild(nullable: false, blank: false)
        level(nullable: false, blank: false)
        name(nullable: false, blank: false)
        parentDomainId(nullable: true, blank: true)
        parentDomainName(nullable: true, blank: true)
        networkDomain(nullable: true, blank: true)
        path(nullable: false, blank: false)
        
//        createdBy   nullable:true,display:false,editable:false
//        editedBy    nullable:true,display:false,editable:false
//        editedDate  nullable:true,display:false,editable:false
//        createdDate nullable:true,display:false,editable:false
    }
}
