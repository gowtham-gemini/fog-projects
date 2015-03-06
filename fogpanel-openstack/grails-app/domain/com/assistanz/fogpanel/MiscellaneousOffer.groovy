package com.assistanz.fogpanel

class MiscellaneousOffer {
    String name
    String unit
    String description
    double cost = 0
    static constraints = {              
        name(nullable: false, blank: false)
        unit(nullable: false, blank: false)
        description(nullable: false, blank: false)      
    }
    
    static hasMany = [miscellaneousOfferZoneCosts: MiscellaneousOfferZoneCost, miscellaneousOfferRegionCosts : MiscellaneousOfferRegionCost]
    
    static mapping = {
        miscellaneousOfferZoneCosts cascade: "all-delete-orphan"
        miscellaneousOfferRegionCosts cascade: "all-delete-orphan"        
    }
}
