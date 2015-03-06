package com.assistanz.fogpanel

class SecurityGroup {

    String referenceId
    String name
    String description
    Project project
    User user
    Boolean deleted = false
    Date createdAt
    Date deletedAt   
    
    
    static constraints = {
        
        referenceId (nullable: false, blank: false)
        name (nullable: false, blank: false)
        deleted (nullable: false, blank: false)
        project (nullable: true, blank: true)
        user (nullable: true, blank: true)
        description (nullable: true, blank: true)
        createdAt (nullable: true, blank: true)
        deletedAt (nullable: true, blank: true)
        
    }
}
