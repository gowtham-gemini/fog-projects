package com.assistanz.fogpanel

class CloudEngine {

    String name
    String url
    String type
    Boolean status = false
    String engineId
    Boolean deleted = false
    
    static hasMany = [projects:Project]          
     
    
    static constraints = {
        name (nullable: false, blank: false)
        url (nullable: false, blank: false)
        type (nullable: false, blank: false)
        status (nullable: false, blank: false)
        engineId (nullable: false, blank: false)
    }
}
