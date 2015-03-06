package com.assistanz.fogpanel

class Domain {
    
    String referenceId
    String name
    Boolean enabled
    String description
    Boolean isDefault = false
    
    Boolean deleted = false
    Date createdAt
    Date deletedAt
    
    static constraints = {
        referenceId (nullable: false, blank: false)
        name (nullable: false, blank: false)
        enabled (nullable: false, blank: false)
        description (nullable: true, blank: true)
        isDefault(nullable: false, blank: false)
        deletedAt (nullable: true, blank: true)
        createdAt (nullable: true, blank: true)
        deleted (nullable: false, blank: false)
        
    }
    
    static mapping = {
      description sqlType:"longtext"
    }
    
}
