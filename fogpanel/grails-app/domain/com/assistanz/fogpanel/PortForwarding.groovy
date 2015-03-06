package com.assistanz.fogpanel

class PortForwarding {

    VirtualMachine virtualMachine
    String referenceId
    Date createdDate
    String name
    FirewallRules firewallRules
    Zone zone
    Account account
    
    UserIPAddress userIPAddress
    Network netwotk
    String destIpAddress
    
    String privateStartPort
    String privateEndPort
    
    static constraints = {
        
        name (nullable: false, blank: false)
        referenceId (nullable: false, blank: false)
        virtualMachine (nullable: false, blank: false)
        createdDate (nullable: false, blank: false)
        firewallRules (nullable: false, blank: false)
        zone (nullable: false, blank: false)
        account (nullable: false, blank: false)
        userIPAddress (nullable: false, blank: false)
        netwotk (nullable: false, blank: false)
        destIpAddress (nullable: false, blank: false)
        privateStartPort (nullable: false, blank: false)
        privateEndPort (nullable: false, blank: false)
    }
}
