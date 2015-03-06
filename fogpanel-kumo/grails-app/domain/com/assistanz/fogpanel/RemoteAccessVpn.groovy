package com.assistanz.fogpanel

class RemoteAccessVpn {
    UserIPAddress publicIPAddress
    Account account
    Network network
    String ipRange
    String localIp
    String ipsecPsk
    String state
    String referenceId
    static constraints = {
        referenceId(nullable: false, blank: false)
        publicIPAddress(nullable: false, blank: false)
        account(nullable: false, blank: false)
        network(nullable: false, blank: false)
        ipRange(nullable: false, blank: false)
        localIp(nullable: true, blank: true)
        ipsecPsk(nullable: false, blank: false)
        state(nullable: true, blank: true) 
    }
}
