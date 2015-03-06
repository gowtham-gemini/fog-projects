package com.assistanz.fogpanel

class JobProperties {
    
    String name
    String value
    AsynchronousJobs asynchronousJobs
    String jobStatus
    static constraints = {
        name (nullable: false, blank: false)  
        value (nullable: false, blank: false)  
        asynchronousJobs (nullable: false, blank: false)  
        jobStatus (nullable: false, blank: false)          
    }
}
