package com.assistanz.fogpanel

class Nic {
    String referenceId
    String gateway
    Boolean isDefault
    String macAddress
    Network network
    Date createdDate
    Date removedDate
    Account account
    String type
    String netMask
    String ipAddress
    VirtualMachine virtualMachine
    Boolean deleted = false
    static constraints = {
        referenceId(nullable: false, blank: false, unique: true)
        gateway(nullable: false, blank: false)
        isDefault(nullable: false, blank: false)
        macAddress(nullable: false, blank: false)
        network(nullable: false, blank: false)
        createdDate(nullable: false, blank: false)
        removedDate(nullable: true, blank: true)
        account(nullable: false, blank: false)
        type(nullable: false, blank: false)
        netMask(nullable: false, blank: false)
        ipAddress(nullable: false, blank: false)
        virtualMachine(nullable: false, blank: false)        
        deleted(nullable: false, blank: false)
    }
}
