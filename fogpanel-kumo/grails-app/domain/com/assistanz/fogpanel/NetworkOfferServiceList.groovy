package com.assistanz.fogpanel

class NetworkOfferServiceList {

    NetworkOffer networkOffer
    String service
    String provider
    Date created
    
    static constraints = {
        
        networkOffer(nullable: false, blank: false) 
        service(nullable: false, blank: false) 
        provider(nullable: false, blank: false) 
        created(nullable: true, blank: true) 
    }
}
