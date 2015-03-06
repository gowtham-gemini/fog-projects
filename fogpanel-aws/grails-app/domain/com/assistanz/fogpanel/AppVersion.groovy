package com.assistanz.fogpanel

class AppVersion {
    
    String fogVersion
    Date initializeDate
    String checksum
    
    static constraints = {
        
        fogVersion (nullable: false, blank: false, unique: true)  
        initializeDate (nullable: false, blank: false)  
        checksum (nullable: false, blank: false)         
    }
    
    static mapping = {
      checksum sqlType:"longtext"
    }
}
