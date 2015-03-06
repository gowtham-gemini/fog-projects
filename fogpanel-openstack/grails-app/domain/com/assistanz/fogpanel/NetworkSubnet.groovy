package com.assistanz.fogpanel

class NetworkSubnet {
    
    String referenceId
    String name
    String cidr
    Network network
    String IPVersion
    String gatewayIP
    Boolean DHCPEnable
    String allocationPools
    String DNSNameServers
    String hostRoutes
    Boolean deleted = false
    Date createdAt
    Date deletedAt 

    static constraints = {
        
        referenceId (nullable: false, blank: false)
        name (nullable: false, blank: false)
        cidr (nullable: true, blank: true)
        network (nullable: false, blank: false)
        IPVersion (nullable: false, blank: false)
        gatewayIP (nullable: true, blank: true)
        DHCPEnable (nullable: false, blank: false)
        allocationPools (nullable: true, blank: true)
        DNSNameServers (nullable: true, blank: true)
        hostRoutes (nullable: true, blank: true)
        deleted (nullable: false, blank: false)
        createdAt (nullable: true, blank: true)
        deletedAt (nullable: true, blank: true)
    }
}
