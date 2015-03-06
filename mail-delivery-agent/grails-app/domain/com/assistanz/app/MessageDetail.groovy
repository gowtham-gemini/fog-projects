package com.assistanz.app

class MessageDetail {
    
    String qFrom
    String qTo
    String qCc
    String qBcc
    Boolean isDelivered
    Date date
    String failureReason
    
    String subject
    String body
    
    static constraints = {
        qCc(nullable: true)
        qBcc(nullable: true)
        isDelivered(nullable: true)
        failureReason(nullable: true)
    }
    static belongsTo = [message: Message]
    static transients = ['subject','body']
    static mapping = {
      failureReason sqlType:"longtext"
    }
    
}
