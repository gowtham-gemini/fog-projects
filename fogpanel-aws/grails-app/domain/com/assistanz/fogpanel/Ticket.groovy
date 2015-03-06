package com.assistanz.fogpanel

class Ticket {

    String content
    String subject 
    SupportDepartment department 
    String priority
    String state
    Account account
    User user
    Date date
    
    static constraints = {
        
        content(nullable: false, blank: false, size:0..5000)
        subject(nullable: true, blank: true, size:0..5000)
        department(nullable: false, blank: false)
        priority(nullable: false, blank: false)
        account(nullable: false, blank: false)
        user(nullable: false, blank: false)
        state(nullable: false, blank: false)
        date(nullable: false, blank: false)
    }
    
    static hasMany = [ticketDetails: TicketDetails]
}
