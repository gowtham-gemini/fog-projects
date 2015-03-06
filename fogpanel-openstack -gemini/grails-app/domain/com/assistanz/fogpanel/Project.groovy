package com.assistanz.fogpanel

class Project {

    String referenceId
    String name
    String description
    Boolean enabled
    String extra
    Domain domain
    
    static constraints = {
        
        referenceId (nullable: false, blank: false)
        name (nullable: false, blank: false)
        enabled (nullable: false, blank: false)
        extra (nullable: true, blank: true)
        domain (nullable: true, blank: true)
    }
}
