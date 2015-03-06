package com.assistanz.fogpanel

//@gorm.AuditStamp
class Zone {
    String referenceZoneId
    String name
    boolean available = true
    String aliasName
    String aliasDescription
    Integer orderNo
    String networkType
    
//    Integer createdBy
//    Integer editedBy
//    Date editedDate
//    Date createdDate
    
    static constraints = {
        name(blank: false, nullable: false)
        referenceZoneId(nullable: false, unique: true)
        available(nullable: false);
        aliasName(nullable: false ,blank: false, unique: true)
        aliasDescription(nullable: false, blank: false)
        orderNo(nullable: false)
        networkType(nullable: false, blank: false)
        
//        createdBy   nullable:true,display:false,editable:false
//        editedBy    nullable:true,display:false,editable:false
//        editedDate  nullable:true,display:false,editable:false
//        createdDate nullable:true,display:false,editable:false
    }    
    static hasMany = [hypervisors: Hypervisor, templates:Template, securityGroupTemplates: SecurityGroupTemplate, cloudMaintenances:CloudMaintenance]
    static belongsTo = [Template, SecurityGroupTemplate, CloudMaintenance]
}
