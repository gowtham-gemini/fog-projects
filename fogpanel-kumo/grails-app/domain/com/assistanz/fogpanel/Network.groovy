package com.assistanz.fogpanel

class Network {

    NetworkOffer networkOffer
    Account account
    String name
    String description
    String referenceId
    Zone zone
    String gateway
    String cidr
    String state
    Boolean deleted = false
    Date createdDate
    Date removedDate
    String type
    String trafficType
    Boolean hasFirstIp = false
    VPC vpc
    String broadcastUri
    String tierType
        
    static constraints = {
        
        referenceId(nullable: false, blank: false, unique: true)
        name(nullable: false, blank: false) 
        description(nullable: false, blank: false) 
        account(nullable: false, blank: false) 
        zone(nullable: false, blank: false) 
        networkOffer(nullable: false, blank: false) 
        gateway(nullable: true, blank: true) 
        cidr(nullable: true, blank: true) 
        state(nullable: true, blank: true) 
        deleted(nullable: false, blank: false) 
        createdDate(nullable: true, blank: true) 
        removedDate(nullable: true, blank: true) 
        type(nullable: true, blank: true) 
        trafficType(nullable: true, blank: true) 
        vpc(nullable: true, blank: true) 
        broadcastUri(nullable: true, blank: true) 
        hasFirstIp(nullable: false, blank: false) 
        tierType(nullable: true, blank: true) 
    }
}
