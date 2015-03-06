package com.assistanz.fogpanel

class TopicSubscriber {

    String email
    NotificationTopic topic
    Date subscribedDate
    String token
    Boolean deleted = false
    Date unsubscribedDate  
    
    static constraints = {
        
        email(email: true, blank: false, nullable: false, unique: 'topic')
        topic(blank: false,nullable: false)
        subscribedDate(blank: false,nullable: false)
        token(blank: false,nullable: false)
        deleted(blank: false,nullable: false)
        unsubscribedDate(blank: true,nullable: true)
        
    }
}
