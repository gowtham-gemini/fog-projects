package com.assistanz.fogpanel

class SSHKeys {

    String fingerprint
    String name
    String privatekey
    Boolean isDefault
    Account account
    Boolean deleted = false
    
    static constraints = {
        fingerprint(nullable: false, blank: false, size:0..5000)   
        name(nullable: false, blank: false, unique: true)   
        privatekey(nullable: false, blank: false, size:0..5000)   
        isDefault(nullable: false, blank: false)   
        account(nullable: false, blank: false)   
        deleted(nullable: false, blank: false)   
        
    }
}
