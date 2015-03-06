package com.assistanz.fogpanel

class UserIPAddress {
    
    String ipReferenceId
    String publicIPAddress
    Boolean removed = false
    Date acquireDate
    Date revokeDate
    Account account
    User user
    String jobId
    Boolean isSourceNat
    Boolean isStaticNat
    String state
    String macAddress
    String vlanName
    Boolean isBasicVmDefaultIp = false
    Boolean isFirstIp = false
    
    Boolean isVPCLBAdded = false
    Boolean isVPCPFAdded = false
        
    static constraints = {
        ipReferenceId(nullable: false, blank: false, unique: true)
        publicIPAddress(nullable: false, blank: false)
        removed(nullable: false, blank: false)
        acquireDate(nullable: true, blank: true)
        revokeDate(nullable: true, blank: true)
        account(nullable: true, blank: true)
        user(nullable: true, blank: true)
        jobId(nullable: true, blank: true)
        isSourceNat(nullable: true, blank: true)
        isStaticNat(nullable: true, blank: true)
        state(nullable: true, blank: true)
        macAddress(nullable: true, blank: true)
        vlanName(nullable: true, blank: true)
        isVPCLBAdded(nullable: true, blank: true) 
        isVPCPFAdded(nullable: true, blank: true) 
        isBasicVmDefaultIp(nullable: false, blank: false)
        isFirstIp(nullable: false, blank: false)
    }
}
