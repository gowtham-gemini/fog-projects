package com.assistanz.fogpanel

class UserEvent {
    
    String eventType
    String ipAddress
    String date
    User user
    Integer failedCount
    EventLogIpAddress eventLogIpAddress
    static constraints = {
        
        eventType (nullable: false, blank: false)
        ipAddress (nullable: false, blank: false)
        failedCount (nullable: false, blank: false)
        date (nullable: false, blank: false)
        user (nullable: false, blank: false)
        eventLogIpAddress(nullable: false, blank: false)
    }
}
