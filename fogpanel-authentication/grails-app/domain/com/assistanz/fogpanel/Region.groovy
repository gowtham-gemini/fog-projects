package com.assistanz.fogpanel

class Region {

    
    String name
    String endPoint
    Boolean deleted = false
    
    static constraints = {
        
        name(nullable: false, blank: false)
        deleted(nullable: false, blank: false)
        endPoint(nullable: false, blank: false)
        
    }
}
