package com.assistanz.fogpanel

class Flavors {
    
    String referenceId
    String name
    String description
    Boolean deleted = false
    Boolean isDisabled = false
    Boolean isPublic = true
    Integer memory
    Integer vcpus
    Integer swap = 0
    Integer vcpuWeight
    Double rxtxFactor = 1
    Integer ephemeralgb = 0
    Integer rootgb
    Date createdAt
    Date deletedAt    
    
    static constraints = {
        
        referenceId (nullable: false, blank: false)
        name (nullable: false, blank: false)
        description (nullable: true, blank: true)
        isDisabled (nullable: false, blank: false)
        deleted (nullable: false, blank: false)
        isPublic (nullable: false, blank: false)
        memory (nullable: false, blank: false)
        vcpus (nullable: false, blank: false)
        swap (nullable: false, blank: false)
        vcpuWeight (nullable: true, blank: true)
        rxtxFactor (nullable: true, blank: true)
        ephemeralgb (nullable: true, blank: true)
        rootgb (nullable: true, blank: true)
        deletedAt (nullable: true, blank: true)
        createdAt (nullable: true, blank: true)
    }
    
    static hasMany = [flavorProjects: FlavorProject , flavorCosts : FlavorCostInfo]
}
