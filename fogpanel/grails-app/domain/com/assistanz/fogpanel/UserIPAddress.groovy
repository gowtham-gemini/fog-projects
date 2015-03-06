package com.assistanz.fogpanel

class UserIPAddress {
    
    Zone zone
    String ipReferenceId
    String publicIPAddress
    Boolean removed = false
    VirtualMachine virtualMachine
    VirtualMachine staticNatVirtualMachine
    Date acquireDate
    Date revokeDate
    Account account
    User user
    String jobId
    Boolean isSourceNat
    Boolean isStaticNat
    String state
    String macAddress
    Network network
    String vlanName
    Boolean isBasicVmDefaultIp = false
    Boolean isFirstIp = false
    
    Boolean isVPCLBAdded = false
    Boolean isVPCPFAdded = false
    
    VPC vpc
        
    static constraints = {
        zone(nullable: false, blank: false)
        ipReferenceId(nullable: false, blank: false, unique: true)
        publicIPAddress(nullable: false, blank: false)
        removed(nullable: false, blank: false)
        virtualMachine(nullable: true, blank: true)
        staticNatVirtualMachine(nullable: true, blank: true)
        acquireDate(nullable: true, blank: true)
        revokeDate(nullable: true, blank: true)
        account(nullable: true, blank: true)
        user(nullable: true, blank: true)
        jobId(nullable: true, blank: true)
        isSourceNat(nullable: true, blank: true)
        isStaticNat(nullable: true, blank: true)
        state(nullable: true, blank: true)
        macAddress(nullable: true, blank: true)
        network(nullable: true, blank: true)
        vlanName(nullable: true, blank: true)
        vpc(nullable: true, blank: true) 
        isVPCLBAdded(nullable: true, blank: true) 
        isVPCPFAdded(nullable: true, blank: true) 
        isBasicVmDefaultIp(nullable: false, blank: false)
        isFirstIp(nullable: false, blank: false)
    }
}
