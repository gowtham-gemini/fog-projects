package com.assistanz.fogpanel

class State {
    Integer countryCode
    String stateCode
    String stateName
    Country country
        
    static constraints = {
        countryCode(nullable: false, blank: false)
        country(nullable: false, blank: false)
        stateName(nullable: false, blank: false)
        stateCode(nullable: false, blank: false)
    }
}
