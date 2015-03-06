package com.assistanz.fogpanel

class VPCOfferingServiceList {
    
    VPCOffering vpcOffering
    String service
    String provider
    Date created
    
    static constraints = {
        
        vpcOffering(nullable: false, blank: false) 
        service(nullable: false, blank: false) 
        provider(nullable: false, blank: false) 
        created(nullable: true, blank: true) 
    }
}
