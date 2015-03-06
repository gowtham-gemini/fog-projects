package com.assistanz.fogpanel

class SecurityGroupRules {
    
    Boolean deleted = false
    Date createdAt
    Date deletedAt   
    String cidr
    Integer toPort
    Integer fromPort
    String protocol
    String referenceId
    String direction
    String etherType
    String remoteGroupId
    SecurityGroup securityGroup
    
    static constraints = {
        
        cidr (nullable: true, blank: true)
        toPort (nullable: true, blank: true)
        fromPort (nullable: true, blank: true)
        protocol (nullable: true, blank: true)
        referenceId (nullable: false, blank: false)
        direction (nullable: true, blank: true)
        etherType (nullable: true, blank: true)
        remoteGroupId (nullable: true, blank: true)
        deleted (nullable: false, blank: false)
        createdAt (nullable: true, blank: true)
        deletedAt (nullable: true, blank: true)
        securityGroup (nullable: true, blank: true)
    }
}
