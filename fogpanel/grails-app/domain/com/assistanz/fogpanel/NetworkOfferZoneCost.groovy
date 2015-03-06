package com.assistanz.fogpanel

//@gorm.AuditStamp
class NetworkOfferZoneCost {
    
    NetworkOffer networkOffer
    Double cost = 0    
    Zone zone
    
    static constraints = {
        networkOffer(nullable: false, blank: false)
        cost(nullable: false, blank: false)
        zone(nullable: false, blank: false)
    }
    static belongsTo = [networkOffer: NetworkOffer]
    
}
