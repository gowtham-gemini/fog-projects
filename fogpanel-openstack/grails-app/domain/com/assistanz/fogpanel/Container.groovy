package com.assistanz.fogpanel

class Container {
    
    Account account
    String name
    String access    
    Integer objectCount
    Double size    
    String publicURL
    
    Boolean deleted = false
    Date createdAt
    Date deletedAt
    Date updatedAt

    static constraints = {
        
        account(nullable: false, blank: false)
        name(nullable: false, blank: false)
        access(nullable: false, blank: false)
        objectCount (nullable: true, blank: true)
        size (nullable: true, blank: true)
        publicURL (nullable: true, blank: true)        
        deleted (nullable: false, blank: false)
        createdAt (nullable: true, blank: true)
        deletedAt (nullable: true, blank: true)        
        updatedAt (nullable: true, blank: true)
    }
}
