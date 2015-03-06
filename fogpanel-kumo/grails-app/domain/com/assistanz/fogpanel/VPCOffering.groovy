package com.assistanz.fogpanel

class VPCOffering {
    
    String referenceId
    String name
    String description
    String state
    Boolean isDefault
    Boolean deleted = false

    static constraints = {
         referenceId(nullable: false, blank: false, unique: true) 
         name(nullable: false, blank: false) 
         description(nullable: false, blank: false) 
         state(nullable: false, blank: false) 
         isDefault(nullable: false, blank: false) 
         deleted(nullable: false, blank: false) 
    }
}
