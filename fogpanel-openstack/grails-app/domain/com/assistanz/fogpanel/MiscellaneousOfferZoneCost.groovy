package com.assistanz.fogpanel

class MiscellaneousOfferZoneCost {
    
    MiscellaneousOffer miscellaneousOffer
    Zone zone    
    double cost
    
    static constraints = {
        miscellaneousOffer(nullable: false, blank: false)
        zone(nullable: false, blank: false)       
    }
    
    static belongsTo = [miscellaneousOffer: MiscellaneousOffer]
}
