package com.assistanz.fogpanel

class MiscellaneousOfferRegionCost {

    MiscellaneousOffer miscellaneousOffer
    Region region    
    double cost
    
    static constraints = {
        miscellaneousOffer(nullable: false, blank: false)
        region(nullable: false, blank: false)       
    }
    
    static belongsTo = [miscellaneousOffer: MiscellaneousOffer]
}
