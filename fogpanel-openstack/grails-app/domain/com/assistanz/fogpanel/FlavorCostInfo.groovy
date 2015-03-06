package com.assistanz.fogpanel

class FlavorCostInfo {
    
     Flavors flavor
//     Region region
     Double runningCost = 0.0
     Double setupCost = 0.0
     Double stopCost = 0.0
     Zone zone
    

    static constraints = {
        flavor (nullable: false, blank: false)
//        region (nullable: false, blank: false)
        runningCost (nullable: false, blank: false)
        setupCost (nullable: false, blank: false)
        stopCost (nullable: false, blank: false)        
        zone (nullable: false, blank: false)        
    }
    
    static belongsTo = [flavor: Flavors]
}
