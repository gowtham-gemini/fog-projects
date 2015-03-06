package com.assistanz.fogpanel

class FloatingIp {
    
    String referenceId
    Network externalNetwork
    VirtualMachine virtualMachine
    String portId
    String routerId
    String fixedIpAddress
    String floatingIpAddress   
    String status
    Account account
    Boolean deleted = false
    Date createdAt
    Date deletedAt  

    static constraints = {
        
        referenceId (nullable: false, blank: false)
        externalNetwork (nullable: false, blank: false)
        virtualMachine (nullable: true, blank: true)
        portId (nullable: true, blank: true)
        routerId (nullable: true, blank: true)
        fixedIpAddress (nullable: true, blank: true)
        floatingIpAddress (nullable: false, blank: false)
        status (nullable: true, blank: true)
        account (nullable: false, blank: false)
        deleted (nullable: false, blank: false)
        deletedAt (nullable: true, blank: true)
        createdAt (nullable: true, blank: true)
    }
}
