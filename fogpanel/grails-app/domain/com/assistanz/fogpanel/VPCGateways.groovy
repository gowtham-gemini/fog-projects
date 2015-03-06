package com.assistanz.fogpanel

class VPCGateways {
    
    String referenceId
    String ipAddress
    String netmask
    String gateway
    String vlanTag
    String networkReferenceId
    String type
    Network network
    VPC vpc
    Zone zone
    Account account
    String state
    Boolean isStaticNat = false
    String networkAclRuleId
    Date created
    Date removed
    Boolean deleted = false
    
    String jobId
    
    static constraints = {
        
        referenceId(nullable: false, blank: false, unique: true) 
        ipAddress(nullable: false, blank: false) 
        netmask(nullable: false, blank: false) 
        gateway(nullable: false, blank: false) 
        vlanTag(nullable: false, blank: false) 
        vpc(nullable: false, blank: false) 
        network(nullable: true, blank: true) 
        networkReferenceId(nullable: true, blank: true) 
        zone(nullable: false, blank: false) 
        account(nullable: false, blank: false) 
        state(nullable: true, blank: true) 
        type(nullable: false, blank: false) 
        isStaticNat(nullable: false, blank: false) 
        networkAclRuleId(nullable: false, blank: false) 
        created(nullable: false, blank: false) 
        removed(nullable: true, blank: true) 
        deleted(nullable: false, blank: false) 
        
        jobId(nullable: true, blank: true)
        
    }
}
