package com.assistanz.fogpanel

class SSHKeys {

    String fingerprint
    String name
    String privateKey
    String publicKey
    Boolean isDefault
    Account account
    User user
    Date createdAt  
    
    static constraints = {
        
        fingerprint(nullable: false, blank: false)   
        name(nullable: false, blank: false, unique: 'account') 
        publicKey(nullable: false, blank: false)
        privateKey(nullable: true, blank: true)   
        isDefault(nullable: false, blank: false)   
        account(nullable: false, blank: false)   
        user(nullable: false, blank: false)   
        createdAt (nullable: true, blank: true)
        
    }
    
    static mapping = {
      fingerprint sqlType:"longtext"
      publicKey sqlType:"longtext"
      privateKey sqlType:"longtext" 

    }
}
