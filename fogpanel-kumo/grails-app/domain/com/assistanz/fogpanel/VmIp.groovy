package com.assistanz.fogpanel

class VmIp {

    String ipReferenceId
    String ipAddress
    Boolean isDefault
    VirtualMachine virtualMachine
    Boolean inUse
    Date acquireDate
    Date revokeDate
    Account account
    String jobId
    
    static constraints = {
        ipAddress(nullable: false, blank: false)
        isDefault(nullable: false, blank: false)
        virtualMachine(nullable: false, blank: false)
        inUse(nullable: false, blank: false)
        acquireDate(nullable: false, blank: false)
        revokeDate(nullable: true, blank: true)
        account(nullable: false, blank: false)
        ipReferenceId(nullable: false, blank: false)
        jobId(nullable: true, blank: true)
    }
}