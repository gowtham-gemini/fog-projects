package com.assistanz.fogpanel

class Tag {
    
    String name
    VirtualMachine virtualMachine
    
    Account account
    User user
	
    static constraints = {
        
        name (nullable: false, blank: false)
        virtualMachine (nullable: false, blank: false)
        account (nullable: true, blank: true)
        user (nullable: true, blank: true)
    }
            
} 
        

