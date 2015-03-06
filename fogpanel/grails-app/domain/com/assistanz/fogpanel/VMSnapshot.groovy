package com.assistanz.fogpanel

class VMSnapshot {
    
    String referenceId
    String name
    String description
    String jobId
    VirtualMachine virtualMachine
    Boolean snapshotMemory = false
    Account account
    Boolean deleted = false
    User user
    String state
    String type
    Date createdDate
    Boolean current = true
    Zone zone
    String billingType = "hourly"
    String parentName

    static constraints = {
        referenceId(nullable: true, unique: true, blank: true)
        name(nullable: false, blank: false)
        description(nullable: false, blank: false)
        virtualMachine(nullable: false, blank: false)
        account(nullable: false, blank: false)
        billingType(nullable: false, blank: false)
        user(nullable: false, blank: false)
        state(nullable: true, blank: true)
        jobId(nullable: true, blank: true)
        snapshotMemory(nullable: true, blank: true)
        deleted(nullable: false, blank: false)
        createdDate(nullable: false, blank: false)
        type(nullable: true, blank: true)
        current(nullable: true, blank: true)
        zone(nullable: false, blank: false)
        parentName(nullable: true, blank: true)
    }
}
