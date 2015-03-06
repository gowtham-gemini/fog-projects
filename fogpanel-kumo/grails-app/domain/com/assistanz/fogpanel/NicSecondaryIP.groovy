package com.assistanz.fogpanel

class NicSecondaryIP {
    
    String ipReferenceId
    String ipAddress
    Boolean removed = false
    VirtualMachine virtualMachine
    Account account
    Network network
    Nic nic
    String jobId
    Zone zone
    
    static constraints = {
        
        ipReferenceId(nullable: false, blank: false, unique: true)
        ipAddress(nullable: false, blank: false)
        removed(nullable: false, blank: false)
        virtualMachine(nullable: false, blank: false)
        jobId(nullable: true, blank: true)
        account(nullable: false, blank: false)
        network(nullable: false, blank: false)
        nic(nullable: false, blank: false)
        zone(nullable: false, blank: false) 
    }
}
