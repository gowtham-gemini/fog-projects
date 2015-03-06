package com.assistanz.fogpanel

class Country {

    String countryName
    String countryAlpha2Code
    String countryAlpha3Code
    Integer countryCode
    String callingCode
        
    static constraints = {
        
        countryName(nullable: false, unique: true, blank: false)
        countryAlpha2Code(nullable: false, unique: true, blank: false)
        countryAlpha3Code(nullable: false, unique: true, blank: false)
        countryCode(nullable: false, unique: true, blank: false)
        callingCode(nullable: true, blank: true)
    }
    
     static hasMany = [states: State]
    
}
