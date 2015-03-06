package com.assistanz.fogpanel

class Region {

    
    String name
    String aliasName
    String endPoint
    Boolean deleted = false
    
    static constraints = {
        
        name(nullable: false, blank: false)
        aliasName(nullable: false, blank: false)
        deleted(nullable: false, blank: false)
        endPoint(nullable: false, blank: false)
        
    }
}
