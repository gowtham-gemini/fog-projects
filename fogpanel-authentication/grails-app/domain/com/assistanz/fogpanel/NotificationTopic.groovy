package com.assistanz.fogpanel

class NotificationTopic {
        
    String name
    Account account
    String status = "ACTIVE"
    Boolean deleted = false
    Date createdAt
    Date deletedAt  

    static constraints = {
        
        name (nullable: true, blank: true)
        status (nullable: true, blank: true)
        account (nullable: false, blank: false)
        deleted (nullable: false, blank: false)
        deletedAt (nullable: true, blank: true)
        createdAt (nullable: true, blank: true)
    }
    
    static hasMany = [topicSubscriber: TopicSubscriber]
}
