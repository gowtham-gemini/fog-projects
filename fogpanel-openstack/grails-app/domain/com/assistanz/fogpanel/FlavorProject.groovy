package com.assistanz.fogpanel

class FlavorProject {
    
    Date createdAt
    Date deletedAt    
    Boolean deleted = false
    Project project
    Flavors flavor
        
    static constraints = {
        
        deletedAt (nullable: true, blank: true)
        createdAt (nullable: true, blank: true)
        deleted (nullable: false, blank: false)
        project (nullable: false, blank: false)
        flavor (nullable: false, blank: false)
        
    }
    
    static belongsTo = [flavors: Flavors]
    
}
