package com.assistanz.fogpanel

class Flavors {
    
    String name 
    String family
    Boolean deleted = false
    Boolean isDisabled = false
    Boolean isPublic = true
    Integer memory     
    Integer vcpus
    Integer clockSpeed
    String instanceStore
    String isEbsOptimized
    Integer rootgb
    Date createdAt  
    Date deletedAt    
    
    static constraints = {
        
        name (nullable: false, blank: false)
        family (nullable: false, blank: false)
        isDisabled (nullable: false, blank: false)
        deleted (nullable: false, blank: false)
        isPublic (nullable: false, blank: false)
        memory (nullable: false, blank: false)
        vcpus (nullable: false, blank: false)
        clockSpeed (nullable: false, blank: false)
        instanceStore (nullable: false, blank: false)
        isEbsOptimized (nullable: false, blank: false)
        rootgb (nullable: true, blank: true)
        deletedAt (nullable: true, blank: true)
        createdAt (nullable: true, blank: true)
    }        
}
