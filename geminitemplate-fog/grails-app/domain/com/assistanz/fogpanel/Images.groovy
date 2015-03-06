package com.assistanz.fogpanel

class Images {
    
    String referenceId
    String name
    String description
    Double size
    Double virtualSize
    String status
    Boolean deleted = false
    Boolean isPublic = true
    Date createdAt
    Date deletedAt   
    Integer minRam = 0
    Integer minDisk = 0
    Boolean isProtected = false
    String diskFormat
    String containerFormat
    String checksum
    Double cost = 0.0
    Boolean isVMSnapshot = false
    VirtualMachine virtualMachine  
    boolean oneTimeChargeable = false
    String billingType = "hourly"
    User user
    Account account
    
    static constraints = {
        
        referenceId (nullable: false, blank: false)
        name (nullable: false, blank: false)
        status (nullable: false, blank: false)
        description (nullable: true, blank: true)
        checksum (nullable: true, blank: true)
        virtualSize (nullable: true, blank: true)
        diskFormat (nullable: true, blank: true)
        containerFormat (nullable: true, blank: true)
        size (nullable: true, blank: true)
        deleted (nullable: false, blank: false)
        minRam (nullable: false, blank: false)
        minDisk (nullable: false, blank: false)
        isPublic (nullable: false, blank: false)
        isProtected (nullable: false, blank: false)
        deletedAt (nullable: true, blank: true)
        createdAt (nullable: true, blank: true)
        cost (nullable: false, blank: false)
        
        isVMSnapshot (nullable: true, blank: true)
        virtualMachine (nullable: true, blank: true)
        oneTimeChargeable(nullable: false, blank: false)
        user (nullable: true, blank: true)
        account (nullable: true, blank: true)
        billingType(nullable: false, blank: false)
    }
}
