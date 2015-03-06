package com.assistanz.fogpanel

class VirtualMachine {
    
    String referenceId
    String name
    Flavors flavor
    Images image
    String status
    Account account 
    Date createdAt
    Date deletedAt    
    User user
    Boolean firstRun = true
    Boolean deleted = false
    SSHKeys keypair
    Zone zone
    String billingType = "hourly"
    Boolean monitoringEnabled = false
    
    static constraints = {
        
        referenceId(nullable: false, blank: false)
        name(nullable: false, blank: false)
        flavor(nullable: false, blank: false)
        image(nullable: false, blank: false)
        status(nullable: true, blank: true)
        account(nullable: false, blank: false)
        user(nullable: false, blank: false)
        firstRun(nullable: false, blank: false)
        createdAt(nullable: true, blank: true)
        deletedAt(nullable: true, blank: true)        
        deleted(nullable: true, blank: true)        
        keypair(nullable: true, blank: true)   
        zone(nullable: true, blank: true)   
        monitoringEnabled(nullable: false, blank: false)   
        billingType(nullable: false, blank: false)
    }
    
    static hasMany = [virtualMachineSecurityGroups: VirtualMachineSecurityGroups, virtualMachineNetworks: VirtualMachineNetworks, volumes: Volume]
    
}
