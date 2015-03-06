package com.assistanz.fogpanel

class Network {
    
    String referenceId
    String name
    String status
    Boolean deleted = false
    Boolean isShared = false
    Boolean isAdminState = true
    Boolean isExternal = false
    Date createdAt
    Date deletedAt    
    Account account
    User user
    String billingType = "hourly"
    static constraints = {
        
        referenceId (nullable: false, blank: false)
        name (nullable: true, blank: true)
        deleted (nullable: false, blank: false)
        isAdminState (nullable: false, blank: false)
        isExternal (nullable: true, blank: true)
        deletedAt (nullable: true, blank: true)
        createdAt (nullable: true, blank: true)
        account (nullable: true, blank: true)
        user (nullable: true, blank: true)
        isShared (nullable: true, blank: true)
        status (nullable: true, blank: true)
        billingType(nullable: false, blank: false)        
    }
}
