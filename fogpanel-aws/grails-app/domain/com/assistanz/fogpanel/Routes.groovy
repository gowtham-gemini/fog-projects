package com.assistanz.fogpanel

class Routes {
    
    
    String destinationCidrBlock
    String gatewayId
    String state
    String origin
    
    static belongsTo = [ routeTable: RouteTable]
    static constraints = {
        
        destinationCidrBlock(nullable:true,blank:true)
        gatewayId(nullable:true,blank:true)
        state(nullable:true,blank:true)
        origin(nullable:true,blank:true)
    }
}
