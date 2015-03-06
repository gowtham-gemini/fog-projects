package com.assistanz.fogpanel

class VirtualMachineSecurityGroups {

    Date createdAt
    Date deletedAt    
    Boolean deleted = false
    SecurityGroup securityGroup
    VirtualMachine virtualMachine
        
    static constraints = {
        
        deletedAt (nullable: true, blank: true)
        createdAt (nullable: true, blank: true)
        deleted (nullable: false, blank: false)
        securityGroup (nullable: false, blank: false)
        virtualMachine (nullable: false, blank: false)
        
    }
    
    static belongsTo = [virtualMachine: VirtualMachine]

}
