package com.assistanz.fogpanel

class TicketDetails {
    
    
    String content
    String subject 
    String state
    Account account
    User user
    Date date
    String userType
    Boolean deleted
    Ticket ticket
    Boolean viewed = false
    
    static constraints = {
        
        content(nullable: false, blank: false, size:0..5000)
        subject(nullable: true, blank: true, size:0..5000)
        userType(nullable: false, blank: false)
        account(nullable: false, blank: false)
        user(nullable: false, blank: false)
        state(nullable: false, blank: false)
        date(nullable: false, blank: false)
        deleted(nullable: false, blank: false)
        ticket(nullable: false, blank: false)
        viewed(nullable: false, blank: false)
    }
    
    
    static belongsTo = [ticket: Ticket] 
}
