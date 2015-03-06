package com.assistanz.fogpanel

class Region {

    
    String name
    Boolean deleted = false
    
    static constraints = {
        
        name(nullable: false, blank: false)
        deleted(nullable: false, blank: false)
        
    }
}
