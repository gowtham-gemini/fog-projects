package com.assistanz.fogpanel

class Network {
    
    String referenceId
    String name
    String cidr
    Region region
   
    Boolean deleted = false
    Date createdAt
    Date deletedAt    

    Account account
    User user

    static constraints = {
        
        referenceId (nullable: false, blank: false)
        name (nullable: false, blank: true)
        cidr (nullable: false, blank: true)
        region (nullable: false, blank: false)
        deleted (nullable: false, blank: false)
        deletedAt (nullable: true, blank: true)
        createdAt (nullable: true, blank: true)
        account (nullable: true, blank: true)
        user (nullable: true, blank: true)
    }
}
