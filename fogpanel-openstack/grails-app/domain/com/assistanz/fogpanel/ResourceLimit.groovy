package com.assistanz.fogpanel

class ResourceLimit {
    
    Boolean deleted = false
    Date createdAt
    Date deletedAt
    Date updatedAt
    
    Integer metadataItems = 0
    Integer injectedFileContentBytes = 0
    Integer injectedFiles = 0
    Integer ram = 0
    Integer instances = 0
    Integer cores = 0
    Integer securityGroups = 0
    Integer securityGroupRules = 0
    
    Integer subnet = 0
    Integer router = 0
    Integer port = 0
    Integer network = 0
    Integer floatingIp = 0
    
    Integer gigabytes = 0
    Integer volumes = 0
    Integer snapshots = 0
    
    Account account
    
    static constraints = {
        
        deleted (nullable: false, blank: false)
        deletedAt (nullable: true, blank: true)
        createdAt (nullable: true, blank: true)
        updatedAt (nullable: true, blank: true)
        
        metadataItems (nullable: false, blank: false)
        injectedFileContentBytes (nullable: false, blank: false)
        injectedFiles (nullable: false, blank: false)
        ram (nullable: false, blank: false)
        instances (nullable: false, blank: false)
        cores (nullable: false, blank: false)
        securityGroups (nullable: false, blank: false)
        securityGroupRules (nullable: false, blank: false)
        
        subnet (nullable: false, blank: false)
        router (nullable: false, blank: false)
        port (nullable: false, blank: false)
        network (nullable: false, blank: false)
        floatingIp (nullable: false, blank: false)
                
        gigabytes (nullable: false, blank: false)
        volumes (nullable: false, blank: false)
        snapshots (nullable: false, blank: false)
        
        account (nullable: true, blank: true)
        
    }
}
