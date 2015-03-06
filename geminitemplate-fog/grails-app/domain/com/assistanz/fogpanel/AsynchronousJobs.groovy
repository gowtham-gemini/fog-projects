package com.assistanz.fogpanel

class AsynchronousJobs {
    
    JobType jobType
    JobStatus status
    Date createdAt
    Date completedAt
    Date startedAt
    Account account
    User user

    static constraints = {
        
        jobType(nullable: false, blank: false)
        status(nullable: false, blank: false)
        createdAt(nullable: false, blank: false)
        startedAt(nullable: true, blank: true)
        completedAt(nullable: true, blank: true)
        
        account(nullable: true, blank: true)
        user(nullable: true, blank: true)
        
    }
    
    static hasMany = [jobProperties: JobProperties]
}


    enum JobType  {
        PULL_FLAVOR,
        PULL_IMAGE,
        PULL_VOLUME_TYPE,
        PULL_ZONE,
        PULL_REGION,
        PULL_DOMAIN,
        PULL_USER,
        PULL_ACCOUNT,
        ZENOSS_ADD_DEVICE
    }
    
    enum JobStatus  {
        SCHEDULED, //0
        RUNNING, //1
        COMPLETED, //2
        ERROR, //3
    }
