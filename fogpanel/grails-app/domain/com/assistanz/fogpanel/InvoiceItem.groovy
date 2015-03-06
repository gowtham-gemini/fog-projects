package com.assistanz.fogpanel

/**
 * A unit of information in a document, record, or statement, shown on a 
 * separate line of its own. An invoice item is a single service charge on an
 * invoice. It includes tax.
 */
//@gorm.AuditStamp
class InvoiceItem {
    
    /**
     * Reference to the BillableItem which provides information for this
     * invoice item
     */
    BillableItem billableItem
    
    /**
     * This InvoiceItem belongs to this specific Invoice.
     */
    Invoice invoice
    
    /**
     * No. of units used, can be considered as the usage value.
     */
    Double usageUnits = 0
    
    /**
     * The per unit price for the used service.
     */
    Double usageUnitPrice
    
    /**
     * The product of usage units and unit price. Stored separetly to support
     * aggregate functionalities.
     */
    Double usageAmount = 0
    
    /**
     * The percentage value of the tax applied on this item.
     */
    Double taxPercent
    
    /**
     * The calculated value by applying tax percentage on usage amount.
     */
    Double taxAmount = 0
    
    /**
     * The sum of usage amount and tax amount. 
     */
    Double totalAmount = 0
    
    /**
     * Reference to the service offered which provides information for this
     * invoice item
     */
    String referenceItemName
    
    /**
     * Reference to the service offered item id which provides information for this
     * invoice item
     */
    String referenceItemId
    
    /**
     * The percentage value of the discount applied on this item, Default value 0.
     */
    Double discountPercent = 0
    
    /**
     * The calculated value by applying discount percentage on usage amount, Default value 0.
     */
    Double discountAmount = 0
    
    /**
     * Reference to the Discount applied, which provides information for this
     * Invoice item.
     */
    Discount discount
    
    /**
     * Reference to the computer offer plan item id which provides information for this
     * invoice item
     */
    String referencePlanId
    
    /**
     * This InvoiceItem belongs to this specific Invoice.
     */
    Zone zone
    
//    Integer createdBy
//    Integer editedBy
//    Date editedDate
//    Date createdDate
    
    static constraints = {
        billableItem(nullable: true, blank: true)  
        invoice(nullable: false, blank: false) 
        usageUnits(nullable: false, blank: false) 
        usageUnitPrice(nullable: false, blank: false) 
        usageAmount(nullable: false, blank: false) 
        taxPercent(nullable: false, blank: false) 
        taxAmount(nullable: false, blank: false) 
        totalAmount(nullable: false, blank: false) 
        referenceItemName(nullable: false, blank: false) 
        referenceItemId(nullable: false, blank: false)
        referencePlanId(nullable: true, blank: true)
        discountPercent(nullable: false, blank: false) 
        discountAmount(nullable: false, blank: false) 
        discount(nullable: true, blank: true) 
        zone(nullable: true, blank: true) 
        
        
//        createdBy   nullable:true,display:false,editable:false
//        editedBy    nullable:true,display:false,editable:false
//        editedDate  nullable:true,display:false,editable:false
//        createdDate nullable:true,display:false,editable:false
    }
    
    static belongsTo = [invoice: Invoice]    
}
