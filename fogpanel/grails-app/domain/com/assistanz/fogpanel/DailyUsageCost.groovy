package com.assistanz.fogpanel

class DailyUsageCost {
    
    /**
     * The total Usage for this Day.
     */
    Double cost = 0
    
    /**
     * The invoice date
     */
    Date date
    
    /**
     * The Refund  Amount
     */
    Double refund = 0

    static constraints = {
        cost(nullable: false, blank: false) 
        date(nullable: false, blank: false)    
        refund(nullable: false, blank: false)    
    }
}
