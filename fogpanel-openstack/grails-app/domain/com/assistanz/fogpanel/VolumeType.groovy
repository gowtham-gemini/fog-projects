package com.assistanz.fogpanel

class VolumeType {
    String name;
    String referenceId;    
    Boolean deleted = false    
    Date createdAt
    Date deletedAt 
    Boolean isDisabled = false
    static constraints = {
        
        referenceId (nullable: false, blank: false)
        name (nullable: false, blank: false)
        isDisabled (nullable: false, blank: false)
        deleted (nullable: false, blank: false)        
        deletedAt (nullable: true, blank: true)
        createdAt (nullable: true, blank: true)
    }
    
    static hasMany = [volumeTypeZoneCosts: VolumeTypeZoneCost]
    
    static mapping = {
        volumeTypeZoneCosts cascade: "all-delete-orphan"
    }
}
