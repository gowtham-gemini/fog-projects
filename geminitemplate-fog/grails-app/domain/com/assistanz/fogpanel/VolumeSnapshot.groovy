package com.assistanz.fogpanel

class VolumeSnapshot {
    
    String name
    String description
    String referenceId
    String status
    Volume volume
    Integer size
    User user
    Account account
    Date createdAt
    Date deletedAt
    Boolean deleted = false
    Zone zone
    String billingType = "hourly"
    static constraints = {
        
        name (nullable: false, blank: false)
        description (nullable: false, blank: false)
        referenceId (nullable: false, blank: false)
        status (nullable: true, blank: true)
        volume (nullable: false, blank: false)
        size (nullable: false, blank: false)
        user (nullable: false, blank: false)
        account (nullable: false, blank: false)
        createdAt (nullable: true, blank: true)
        deletedAt (nullable: true, blank: true)
        deleted (nullable: false, blank: false)
        zone (nullable: false, blank: false)
        billingType(nullable: false, blank: false)
    }
}
