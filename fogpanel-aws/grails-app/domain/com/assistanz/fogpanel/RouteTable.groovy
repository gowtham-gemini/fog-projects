package com.assistanz.fogpanel

class RouteTable {
    
    String referenceId
    String vpcId
    Integer regionId
    static hasMany = [routes: Routes]
    
//    static belongsTo = [vpc: Vpc]
    
    static constraints = {
        referenceId(blank:false,nullable:false)
        regionId(blank:false,nullable:false)
    }
}
