package com.assistanz.fogpanel

class Invitation {
    String email
    String link
    String status
    Date createdDate 
    String type
    String name
    static constraints = {
        email (email: true, blank: false, unique: true, nullable: false)
        link (blank: false, nullable: false)
        name (blank: false, nullable: false)        
        status (blank: false, nullable: false)
        createdDate (blank: false, nullable: false)
        type (blank: false, nullable: false)                
    }
}
