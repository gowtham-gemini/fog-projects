package com.assistanz.fogpanel

class VirtualMachineNetworks {

    Date createdAt
    Date deletedAt    
    Boolean deleted = false
    VirtualMachine virtualMachine
    Network network
    String fixedIps
    
    static constraints = {
        
        deletedAt (nullable: true, blank: true)
        createdAt (nullable: true, blank: true)
        deleted (nullable: false, blank: false)
        virtualMachine (nullable: false, blank: false)
        network (nullable: false, blank: false)
        fixedIps (nullable: true, blank: true)
        
                
    }
    
    static belongsTo = [virtualMachine: VirtualMachine]
}
