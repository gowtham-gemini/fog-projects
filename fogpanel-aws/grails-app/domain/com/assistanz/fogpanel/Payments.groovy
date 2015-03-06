package com.assistanz.fogpanel

//@gorm.AuditStamp
class Payments {

    /**             
     * The account which the payment belongs to.
     */
    Account account
    
    /**
     * The date when the payment has to done.
     */
    Date paymentDate
    
    /**
     * The payment token number.
     */
    String paymentToken
    
    /**
     * The payment amount
     */
    Double amount

    /**
     * The total payment amount included processing fee
     */
    Double totalAmount
    
    /**
     * The prcessing fee for payment amount
     */
    Double processingFee
    
    /**
     * The payment code for manual payment.
     */
    String manualPaymentCode
    
    String paymentLoad
    
    String paymentStatus
    
    String gatewayName
//    Integer createdBy
//    Integer editedBy
//    Date editedDate
//    Date createdDate
    
    static constraints = {
        account(nullable: false, blank: false) 
        paymentDate(nullable: false, blank: false) 
        paymentToken(nullable: true, blank: true) 
        manualPaymentCode(nullable: true, blank: true) 
        amount(nullable: true, blank: true) 
        totalAmount(nullable: false, blank: false)
        processingFee(nullable: false, blank: false) 
        paymentLoad(nullable:true,size:0..10000)
        paymentStatus(nullable:true,size:0..10000)
        gatewayName(nullable:true,size:0..10000)
//        createdBy   nullable:true,display:false,editable:false
//        editedBy    nullable:true,display:false,editable:false
//        editedDate  nullable:true,display:false,editable:false
//        createdDate nullable:true,display:false,editable:false
    }
}
