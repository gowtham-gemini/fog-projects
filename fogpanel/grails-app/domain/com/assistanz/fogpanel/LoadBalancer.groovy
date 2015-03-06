package com.assistanz.fogpanel

class LoadBalancer {
    
    String name
    String description
    String referenceId
    String algorithm
    String scheme
    Date createdDate
    String publicPort
    String privatePort
    FirewallRules firewallRules
    Zone zone
    Account account
    
    UserIPAddress userIPAddress
    Network netwotk
    
//    source_ip_address
//    source_ip_address_network_id
    
    static constraints = {
        
        name (nullable: false, blank: false)
        referenceId (nullable: false, blank: false)
        description (nullable: true, blank: true)
        algorithm (nullable: false, blank: false)
        scheme (nullable: true, blank: true)
        createdDate (nullable: false, blank: false)
        publicPort (nullable: false, blank: false)
        privatePort (nullable: false, blank: false)
        firewallRules (nullable: false, blank: false)
        userIPAddress (nullable: true, blank: true)
        netwotk (nullable: false, blank: false)
        account (nullable: true, blank: true)
        zone (nullable: true, blank: true)
        
    }
}
