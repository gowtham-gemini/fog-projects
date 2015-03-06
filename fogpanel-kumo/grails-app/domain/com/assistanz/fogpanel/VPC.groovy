package com.assistanz.fogpanel

class VPC {
    
    String referenceId
    String name
    String description
    String state
    String cidr
    VPCOffering vpcOffering
    Zone zone
    Account account
    User user
    Date created
    Boolean deleted =  false
    String jobId
    String networkDomain
    Boolean restartRequired = false
    
    
    static constraints = {
        
        referenceId(nullable: false, blank: false, unique: true) 
        name(nullable: false, blank: false) 
        description(nullable: false, blank: false) 
        cidr(nullable: false, blank: false) 
        vpcOffering(nullable: false, blank: false) 
        zone(nullable: false, blank: false) 
        account(nullable: false, blank: false) 
        user(nullable: false, blank: false) 
        created(nullable: false, blank: false) 
        deleted(nullable: false, blank: false) 
        state(nullable: true, blank: true) 
        jobId(nullable: true, blank: true) 
        networkDomain(nullable: true, blank: true) 
        restartRequired(nullable: false, blank: false) 
    }
}
