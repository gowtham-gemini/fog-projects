package com.assistanz.fogpanel

/**
 * Invoice indicate's the products, quantities, and agreed prices for services
 * It provides invoice information about used service.
 * The invoice items include tax. 
 * Invoice is made up of Invoice items.
 */
//@gorm.AuditStamp
class Invoice {
    
    /**
     * The billing address for the invoice.
     */
    Address billingAddress
    
    /**
     * The organisation address for the invoice.
     */
    Address organisationAddress
    
    /**
     * The generated invoice number for reference.
     */
    String referenceNumber
    
    /**             
     * The account which the invoice belongs to.
     */
    Account account
    
    /**
     * The due date when the payment has to happen.
     */
    Date dueDate
    
    /**
     * The sum of invoice item amount for this invoice.
     */
    Double totalAmount = 0
    
    /**
     * Customer name for this invoice.
     */
    String customerName
    
    /**
     * Currency type for this invoice.
     */
    String currency
    
    /**
     * The status about this invoice.
     * Possible values include ENABLED, DISABLED, SUSPENDED, PAID and PARTIAL_PAID.
     */
    InvoiceStatus status
    
    /**
     * The pending amount for this invoice, if any. Default value 0.
     */
    Double previousBalance = 0
    
    /**
     * The total payment for this invoice.
     */
    Double payment = 0
    
    /**
     * The invoice date
     */
    Date date
    
    /**
     * The current payable amount
     */
    Double currentDue = 0
    
    /**
     * The previous invoice date
     */
    Date previousInvoiceDate
    
    /**
     * The previous invoice id for reference
     */
    Long previousInvoiceId
    
    /**
     * The total credit amount for this invoice
     */
    Double credit = 0
    
    /**
     * The total refund amount for this invoice
     */
    Double refundAmount = 0
    
    
//    Integer createdBy
//    Integer editedBy
//    Date editedDate
//    Date createdDate
    
    
    static constraints = {
        previousBalance(nullable: true, blank: true) 
        currency(nullable: false, blank: false) 
        customerName(nullable: false, blank: false) 
        account(nullable: false, blank: false) 
        dueDate(nullable: true, blank: true) 
        referenceNumber(nullable: false, blank: false) 
        totalAmount(nullable: false, blank: false) 
        billingAddress(nullable: false, blank: false)
        status(nullable: false, blank: false)
        organisationAddress(nullable: false, blank: false)  
        payment(nullable: false, blank: false)  
        date(nullable: false, blank: false)  
        currentDue(nullable: false, blank: false)  
        previousInvoiceDate(nullable: false, blank: false)  
        previousInvoiceId(nullable: true, blank: true) 
        credit(nullable: false, blank: false) 
        refundAmount(nullable: false, blank: false) 
        
//        createdBy   nullable:true,display:false,editable:false
//        editedBy    nullable:true,display:false,editable:false
//        editedDate  nullable:true,display:false,editable:false
//        createdDate nullable:true,display:false,editable:false
    }
    
    static hasMany = [invoiceItems: InvoiceItem]
    static embedded = ['billingAddress', 'organisationAddress']   
    static belongsTo = [account: Account]
}

/**
 * ENABLED- 
 * DISABLED-
 * SUSPENDED-
 * PAID-
 * PARTIAL_PAID-
 *
*/
enum InvoiceStatus {
    ENABLED, //0
    DISABLED, //1
    SUSPENDED, //2
    PAID, //3
    PARTIAL_PAID, //4 
    CLOSED, //5
    DRAFT, //6
    FINAL //7
}
