package com.assistanz.fogpanel

class VPCServiceList {

    VPC vpc
    String service
    String provider
    Date created
    
    static constraints = {
        
        vpc(nullable: false, blank: false) 
        service(nullable: false, blank: false) 
        provider(nullable: false, blank: false) 
        created(nullable: true, blank: true) 
    }
}
