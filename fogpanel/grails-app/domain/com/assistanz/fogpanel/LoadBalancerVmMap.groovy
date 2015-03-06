package com.assistanz.fogpanel

class LoadBalancerVmMap {
    
    LoadBalancer loadBalancer 
    VirtualMachine virtualMachine
    String state
    Boolean revoked = false
    
    static constraints = {
        loadBalancer (nullable: false, blank: false)
        virtualMachine (nullable: false, blank: false)
        state (nullable: true, blank: true)
        revoked (nullable: false, blank: false)
    }
}
