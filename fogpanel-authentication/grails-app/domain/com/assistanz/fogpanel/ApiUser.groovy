package com.assistanz.fogpanel

class ApiUser {

    User user
    String apiKey
    String secret
    Boolean enabled
    Date createdDate
    
    static constraints = {
      user (nullable: false, blank: false)  
      apiKey (nullable: false, blank: false)  
      secret (nullable: false, blank: false)  
      enabled (nullable: false, blank: false)  
      createdDate (nullable: false, blank: false)  
    }
}
