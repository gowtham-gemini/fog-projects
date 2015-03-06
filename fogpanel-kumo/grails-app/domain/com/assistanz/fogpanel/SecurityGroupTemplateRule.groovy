package com.assistanz.fogpanel
import com.assistanz.fogpanel.GeneralConstants;

//@gorm.AuditStamp
class SecurityGroupTemplateRule {
    SecurityProtocol securityGroupPort
    Integer startPort
    Integer endPort
    String cidr
    SecurityGroupTemplate securityGroupTemplate
    SecurityGroupRule securityGroupType
    String securityGroupTemplateRuleReferenceId
    
//    Integer createdBy
//    Integer editedBy
//    Date editedDate
//    Date createdDate
    
    static constraints = {      
        
        securityGroupType(nullable: false, blank: false)
        securityGroupPort(nullable: false, blank: false);
        startPort(nullable: false, blank: false, size: 0..65535);
        endPort(nullable: false, blank: false, size: 0..65535);
        cidr(nullable: false, blank: false, matches:/^\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}\/\d{1,2}$/);
        securityGroupTemplateRuleReferenceId(nullable: true, blank: true, unique: true)
        
//        createdBy   nullable:true,display:false,editable:false
//        editedBy    nullable:true,display:false,editable:false
//        editedDate  nullable:true,display:false,editable:false
//        createdDate nullable:true,display:false,editable:false
    }
    
    static belongsTo = [securityGroupTemplate: SecurityGroupTemplate]
    }
    
    enum SecurityProtocol  {
        TCP,UDP,ICMP
    }
    enum SecurityGroupRule  {
        INGRESS, EGRESS
    }
    

    