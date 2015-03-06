package com.assistanz.fogpanel

class S2SVPNGateways {
    
    VPC vpc
    String referenceId
    UserIPAddress userIPAddress
    Date createdDate
    Zone zone
    Account account
    Boolean deleted = false
    
    static constraints = {
        
        referenceId(nullable: false, blank: false, unique: true) 
        vpc(nullable: false, blank: false) 
        userIPAddress(nullable: false, blank: false) 
        zone(nullable: false, blank: false) 
        account(nullable: false, blank: false) 
        deleted(nullable: false, blank: false) 
    }
}
