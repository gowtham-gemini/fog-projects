package com.assistanz.fogpanel

class Subnet {
    
    String referenceId
    Integer regionId
    String state
    String cidr
    Boolean deleted = false
    String availabilityZone
    Date createdAt
    Date deletedAt 
    static belongsTo = [vpc: Vpc]
     
    static constraints = {
        
        referenceId(nullable: false, blank: false)
        state (nullable: false, blank: false)
        cidr (nullable: true, blank: true)
        vpc(nullable:false,blank:false)
        availabilityZone (nullable: false, blank: false)
        deleted (nullable: true, blank: true)
        createdAt (nullable: true, blank: true)
        deletedAt (nullable: true, blank: true)
        regionId(nullable:false,blank:false)
    }
}
