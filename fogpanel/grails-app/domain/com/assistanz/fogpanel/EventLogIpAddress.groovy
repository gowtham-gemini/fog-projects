package com.assistanz.fogpanel

class EventLogIpAddress {

    String ipAddress
    Integer failureCount
    Integer successCount
    Integer overAllCount
    Integer overAllFailureCount
    Boolean isLocked = false
    User user
    Date lockTime
    
    static constraints = {
        ipAddress (nullable: false, blank: false)
        failureCount (nullable: false, blank: false)
        successCount (nullable: false, blank: false)
        overAllCount (nullable: false, blank: false)
        user (nullable: false, blank: false)
        overAllFailureCount (nullable: false, blank: false)
        isLocked (nullable: false, blank: false)
        lockTime (nullable: true, blank: true)
    }
}
