package com.assistanz.fogpanel

class StaticRoutes {
    
    String referenceId
    VPCGateways vpcGateways
    String cidr
    VPC vpc
    Account account
    Zone zone
    Date created
    String state
     
    String jobId

    static constraints = {
        
        referenceId(nullable: false, blank: false, unique: true) 
        vpcGateways(nullable: false, blank: false) 
        cidr(nullable: false, blank: false) 
        vpc(nullable: false, blank: false) 
        account(nullable: false, blank: false) 
        zone(nullable: false, blank: false) 
        created(nullable: false, blank: false) 
        state(nullable: true, blank: true) 
        jobId(nullable: true, blank: true) 
        
    }
}
