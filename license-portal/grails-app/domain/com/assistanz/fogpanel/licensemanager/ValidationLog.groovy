package com.assistanz.fogpanel.licensemanager

class ValidationLog {
    
    String requestID;
    String hostName;
    String initialVersion;
    String currentVersion;
    String hostIP;
    Long sockets;
    String email;
    Long lastUpdatedDate;
    Long deployedDate;
    Long registrationDate;
    String timeZone;
    String generatedKey;
    String checksum;
    String additionalData;
    boolean valid;
    String errorMessage;
    Date createdDate
    ProductInstance productInstance

    static constraints = {
        
        requestID blank: true, nullable: true
        hostName blank: true, nullable: true
        initialVersion blank: true, nullable: true
        currentVersion blank: true, nullable: true
        hostIP blank: true, nullable: true
        sockets blank: true, nullable: true
        email blank: true, nullable: true
        lastUpdatedDate blank: true, nullable: true
        deployedDate blank: true, nullable: true
        timeZone blank: true, nullable: true
        registrationDate blank: true, nullable: true
        generatedKey blank: true, nullable: true
        checksum blank: true, nullable: true
        valid blank: true, nullable: true
        errorMessage blank: true, nullable: true
        productInstance blank: true, nullable: true
            
    }
    
    static mapping = {
      checksum sqlType:"longtext"
    }
    
}
