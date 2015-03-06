package com.assistanz.fogpanel

class S2SCustomerGateway {
    
    String referenceId
    String name
    String gateway
    String cidr
    String ipsecPresharedKey
    String ikeEncryption
    String ikeHash
    String ikeDH
    String espEncryption
    String espHash
    String perfectForwardSecrecy
    String ikeLifetime
    String espLifetime
    Boolean deadPeerDetection
    Boolean isUsed = false
    Boolean deleted = false
    Account account
    Date removedDate
    Date createdDate
    String jobId
    
    static constraints = {
        
        referenceId(nullable: false, blank: false, unique: true) 
        name(nullable: false, blank: false) 
        gateway(nullable: false, blank: false) 
        cidr(nullable: false, blank: false) 
        ipsecPresharedKey(nullable: false, blank: false) 
        
        ikeEncryption(nullable: true, blank: true) 
        ikeHash(nullable: true, blank: true) 
        ikeDH(nullable: true, blank: true) 
        espEncryption(nullable: true, blank: true) 
        espHash(nullable: true, blank: true) 
        perfectForwardSecrecy(nullable: true, blank: true) 
        ikeLifetime(nullable: true, blank: true) 
        espLifetime(nullable: true, blank: true) 
        jobId(nullable: true, blank: true) 
        deadPeerDetection(nullable: true, blank: true) 
        isUsed(nullable: false, blank: false) 
        account(nullable: false, blank: false) 
        deleted(nullable: false, blank: false) 
        removedDate(nullable: true, blank: true) 
        createdDate(nullable: false, blank: false) 
    }
}
