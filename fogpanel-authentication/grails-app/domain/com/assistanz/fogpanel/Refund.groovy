package com.assistanz.fogpanel

//@gorm.AuditStamp
class Refund {
    
    /**             
     * The account which the invoice belongs to.
     */
    Account account
    
    /**
     * The refund date 
     */
    Date date
    
    /**
     * The refund amount.
     */
    Double amount = 0
    
    /**
     * The description about this refund
     */
    String description
    
    /**
     * This refund amount belongs to this specific Invoice.
     */
    Invoice invoice
    
//    Long createdBy
//    Long editedBy
//    Date editedDate
//    Date createdDate
        
    static constraints = {
        
        account(nullable: false, blank: false) 
        date(nullable: false, blank: false) 
        amount(nullable: false, blank: false) 
        description(nullable: true, blank: true, size:0..5000) 
        invoice(nullable: false, blank: false) 
        
//        createdBy   nullable:true,display:false,editable:false
//        editedBy    nullable:true,display:false,editable:false
//        editedDate  nullable:true,display:false,editable:false
//        createdDate nullable:true,display:false,editable:false
        
    }
}
