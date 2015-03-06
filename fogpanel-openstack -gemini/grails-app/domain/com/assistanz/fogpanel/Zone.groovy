package com.assistanz.fogpanel

class Zone {
    String name
    Boolean available = false
    
    Boolean deleted = false
    
    Date createdAt
    Date deletedAt
    static constraints = {
        name (nullable: false, blank: false)
        available (nullable: false, blank: false)
        
        deleted (nullable: false, blank: false)
        
        deletedAt (nullable: true, blank: true)
        createdAt (nullable: true, blank: true)
    }
}
