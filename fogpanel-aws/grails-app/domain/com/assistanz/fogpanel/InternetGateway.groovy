package com.assistanz.fogpanel

class InternetGateway {
    
    String referenceId
    Integer regionId
    String vpcId
    String state
    String name
    
    static constraints = {
        
        referenceId(nullable:false,blank:false)
        regionId(nullable:false,blank:false)
        vpcId(nullable:true,blank:true)
        state(nullable:true,blank:true)
        name(nullable:true,blank:true)
    }
}
