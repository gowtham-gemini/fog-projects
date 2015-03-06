package com.assistanz.fogpanel

class ServiceCostChangeLog {
    
    ServiceName serviceName
    Double oldCost
    Double oldSetupCost
    Double oldMinimumCost
    Double newCost
    Double newSetupCost
    Double newMinimumCost
    User user
    Account account
    Date changedDate
    
    MiscellaneousOfferZoneCost miscellaneousOfferZoneCost
    ComputingOfferZoneCost computingOfferZoneCost
    DiskOfferZoneCost diskOfferZoneCost
    
    
    

    static constraints = {
        
        serviceName(nullable: false, blank: false)
        oldCost(nullable: false, blank: false)
        oldSetupCost(nullable: true, blank: true)
        oldMinimumCost(nullable: true, blank: true)
        newCost(nullable: false, blank: false)
        newSetupCost(nullable: true, blank: true)
        newMinimumCost(nullable: true, blank: true)
        user(nullable: false, blank: false)
        account(nullable: false, blank: false)
        changedDate(nullable: false, blank: false)
        
        miscellaneousOfferZoneCost(nullable: true, blank: true)
        computingOfferZoneCost(nullable: true, blank: true)
        diskOfferZoneCost(nullable: true, blank: true)
        
    }
}


enum ServiceName {
    
    COMPUTE_OFFER,
    DISK_OFFER,
    MISC_OFFER
    
}
