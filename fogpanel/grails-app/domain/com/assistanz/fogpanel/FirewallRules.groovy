package com.assistanz.fogpanel

class FirewallRules {

    String referenceId
    String purpose
    String purposeId
    UserIPAddress userIPAddress
    Date createdDate
    String state
    String startPort
    String endPort
    String protocol
    Account account
    Network netwotk
    Zone zone
    String type
    
    static constraints = {
        
        referenceId (nullable: false, blank: false)
        purpose (nullable: false, blank: false)
        purposeId (nullable: false, blank: false)
        userIPAddress (nullable: true, blank: true)
        createdDate (nullable: false, blank: false)
        state (nullable: true, blank: true)
        startPort (nullable: false, blank: false)
        endPort (nullable: false, blank: false)
        protocol (nullable: true, blank: true)
        account (nullable: false, blank: false)
        netwotk (nullable: false, blank: false)
        type (nullable: false, blank: false)
        zone (nullable: false, blank: false)
    }
}
