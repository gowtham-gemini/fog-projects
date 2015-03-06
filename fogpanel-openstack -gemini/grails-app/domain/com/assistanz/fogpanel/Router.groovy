package com.assistanz.fogpanel

class Router {
    
    String referenceId
    String name
    String status
    String gatewayPortId
    String externalNetworkId
    Boolean deleted = false
    Boolean isAdminState = true
    Boolean enableSnat = false
    Date createdAt
    Date deletedAt    
    Account account
    User user
    
    static constraints = {
        
        referenceId (nullable: false, blank: false)
        name (nullable: true, blank: true)
        status (nullable: true, blank: true)
        gatewayPortId (nullable: true, blank: true)
        externalNetworkId (nullable: true, blank: true)
        deleted (nullable: false, blank: false)
        isAdminState (nullable: false, blank: false)
        enableSnat (nullable: true, blank: true)
        deletedAt (nullable: true, blank: true)
        createdAt (nullable: true, blank: true)
        account (nullable: true, blank: true)
        user (nullable: true, blank: true)
        
    }
	
}

