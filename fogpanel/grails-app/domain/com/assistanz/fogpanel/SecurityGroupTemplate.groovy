package com.assistanz.fogpanel

//@gorm.AuditStamp
class SecurityGroupTemplate {
    String securityGroupTemplateReferenceId
    String name
    String description
    boolean disabled 
    BaseOs baseOs
    
//    Integer createdBy
//    Integer editedBy
//    Date editedDate
//    Date createdDate
    
    static constraints = {
        securityGroupTemplateReferenceId(nullable: true, blank: true, unique: true)
        name(nullable: false, blank: false, unique: true)
        baseOs(nullable: false, blank: false)
        description(nullable: false, blank: false)
        disabled(nullable: false, blank: false)
        
//        createdBy   nullable:true,display:false,editable:false
//        editedBy    nullable:true,display:false,editable:false
//        editedDate  nullable:true,display:false,editable:false
//        createdDate nullable:true,display:false,editable:false
    }           
    static hasMany = [zones: Zone, securityGroupTemplateRules: SecurityGroupTemplateRule]
}
enum BaseOs  {
        Linux, Windows
}