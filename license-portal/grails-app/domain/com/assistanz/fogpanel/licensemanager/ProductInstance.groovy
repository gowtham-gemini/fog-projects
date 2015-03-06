package com.assistanz.fogpanel.licensemanager

class ProductInstance {
    
    transient springSecurityService
    
    String name
    Product product
    User user
    Long baseLicense
    Long additionalLicense = 0
    Long activeLicense
    Date inceptionDate
    Date expirationDate
    Date registeredDate
    Long lastUpdatedOn 
    String secretKey
    Version initialVersion
    Version maxVersion
    String timeZone
    String instancePath
    String autoUpdateComment
    Status status = Status.FREE_TRIAL
    Date emergencyExpiryDate
    String appStatus = "Not Registered"
    
    static embedded = ['initialVersion', 'maxVersion']
       
    static constraints = {
        name blank: true, nullable: true
        product blank: false, nullable: false
        user blank: false, nullable: false
        baseLicense blank: false, nullable: false
        additionalLicense blank: false, nullable: false
        inceptionDate blank: false, nullable: false
        expirationDate blank: false, nullable: false
        status blank: false, nullable: false
        secretKey blank: false, nullable: false
        lastUpdatedOn blank: true, nullable: true
        timeZone blank: true, nullable: true
        instancePath blank: true, nullable: true
        autoUpdateComment blank: true, nullable: true
        emergencyExpiryDate blank: true, nullable: true
        initialVersion blank: true, nullable: true
        maxVersion blank: true, nullable: true
        activeLicense blank: true, nullable: true
        registeredDate blank: true, nullable: true
        appStatus blank: true, nullable: true
    }
    
    static namedQueries = {
        forCurrentUser {
            def user = springSecurityService.currentUser
            eq 'user', user
        }
       
    }
    
    enum Status {
        ACTIVE("ACTIVE"),
        VALID("VALID"),
        EXPIRED("EXPIRED"),
        FREE_TRIAL("FREE_TRIAL"),
        EMERGENCY_TRIAL("EMERGENCY_TRIAL"),
        LOCKED("LOCKED"),
        DISABLED("DISABLED")
        
        private String name
        
        private Status(String name) {
            this.name = name
        }

        static Status valueOfName( String name ) {
            values().find { it.name == name }
        }
        
        static Status valueOfName( Status value ) {
            value.name
        }
    }
}


