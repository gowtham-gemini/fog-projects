package com.assistanz.fogpanel

class VPNConnection {
    
    String referenceId          
    VPC vpc
    S2SVPNGateways vpnGateway
    S2SCustomerGateway vpnCustomerGateway
    String state               
    Account account
    Date created             
    Date removed  
    Zone zone  
    Boolean deleted = false    
    String jobId
    
    Date billingUpdatedDate  
    
    static constraints = {
        referenceId(nullable: false, blank: false, unique: true) 
        vpc(nullable: false, blank: false) 
        vpnGateway(nullable: false, blank: false) 
        vpnCustomerGateway(nullable: false, blank: false) 
        state(nullable: false, blank: false) 
        account(nullable: false, blank: false) 
        deleted(nullable: false, blank: false) 
        zone(nullable: false, blank: false) 
        
        created(nullable: false, blank: false) 
        removed(nullable: true, blank: true) 
        jobId(nullable: true, blank: true) 
        billingUpdatedDate(nullable: true, blank: true) 
    }
}
