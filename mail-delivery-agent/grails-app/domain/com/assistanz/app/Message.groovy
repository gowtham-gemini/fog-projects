package com.assistanz.app

class Message {
    
    String subject
    String body
    
//    String qFrom
//    String qTo
//    String qCc
//    String qBcc
//    Boolean isDelivered
    static constraints = {
        subject(size:0..5000)
//        subject(nullable: false)
//        body(nullable: false)
    }
//    static transients = ['qFrom','qTo','qCc','qBcc','isDelivered']
    static hasMany = [messageDetails: MessageDetail]
    static mapping = {
      body sqlType:"longtext"
    }
    
}
