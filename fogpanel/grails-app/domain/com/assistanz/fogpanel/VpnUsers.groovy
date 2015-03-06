package com.assistanz.fogpanel

class VpnUsers {
    String referenceId
    Account account    
    String vpnUserName
    String  password
    String state
    static constraints = {
       referenceId(nullable: false, blank: false)
       account(nullable: false, blank: false)       
       vpnUserName(nullable: false, blank: false, unique: true)   
       password(nullable: true, blank: true)  
       state(nullable: false, blank: false)       
    }
}
