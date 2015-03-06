package com.assistanz.fogpanel

//@gorm.AuditStamp
class RecurringItem {
    
    /**
     * Reference to the BillableItem which provides information for this
     * invoice item
     */
    BillableItem billableItem
    
    /**
     * This RecurringItem belongs to this specific Account.
     */
    Account account
    
    /**
     * The amount for this Item.
     */
    Double amount
        
    /**
     * The percentage value of the tax applied on this item.
     */
    Double taxPercent
    
    /**
     * The calculated value by applying tax percentage on usage amount.
     */
    Double taxAmount = 0
    
    /**
     * The sum of amount and tax amount. 
     */
    Double totalAmount = 0
    
    /**
     * Name for this Item
     */
    String name
    
    /**
     * Description abouut this Item
     */
    String description
    
    /**
     * This item Applyes to given billing cycles
     */
    Integer billingCycles = 0
    
    Integer uses = 0
    
    Boolean deleted = false
    
//    Integer createdBy
//    Integer editedBy
//    Date editedDate
//    Date createdDate
       
    static constraints = {
        billableItem(nullable: true, blank: true)  
        account(nullable: false, blank: false)
        amount(nullable: false, blank: false) 
        taxPercent(nullable: false, blank: false) 
        taxAmount(nullable: false, blank: false) 
        totalAmount(nullable: false, blank: false) 
        name(nullable: false, blank: false) 
        description(nullable: false, blank: false)
        billingCycles(nullable: false, blank: false, defaultValue:0)
        uses(nullable: false, blank: false, defaultValue:0)
        deleted(nullable: false, blank: false)
        
//        createdBy   nullable:true,display:false,editable:false
//        editedBy    nullable:true,display:false,editable:false
//        editedDate  nullable:true,display:false,editable:false
//        createdDate nullable:true,display:false,editable:false
    }
}
