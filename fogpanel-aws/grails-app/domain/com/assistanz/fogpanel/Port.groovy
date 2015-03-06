package com.assistanz.fogpanel

class Port {

    String referenceId
    String name
    String status
    String fixedIps
    String macAddress
    String deviceId
    String deviceOwner
    Boolean isAdminState = true
    Boolean deleted = false
    Date createdAt
    Date deletedAt    
    Account account
    User user
    
    static constraints = {
        
        referenceId (nullable: false, blank: false)
        name (nullable: true, blank: true)
        status (nullable: true, blank: true)
        fixedIps (nullable: true, blank: true)
        macAddress (nullable: true, blank: true)
        deviceId (nullable: true, blank: true)
        deviceOwner (nullable: true, blank: true)
        isAdminState (nullable: false, blank: false)
        deleted (nullable: false, blank: false)
        deletedAt (nullable: true, blank: true)
        createdAt (nullable: true, blank: true)
        account (nullable: true, blank: true)
        user (nullable: true, blank: true)
        
    }
}
