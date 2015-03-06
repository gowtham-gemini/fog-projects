package com.assistanz.fogpanel

//@gorm.AuditStamp
class Discount {

    String discountName
    Double value
    Date startDate
    Date endDate
    DiscountType type
    Boolean isAll
    Boolean deleted
    Boolean isAllPlan
    Boolean isAllUser
    String subType
    Integer billingCycles = 1
    
//    Integer createdBy
//    Integer editedBy
//    Date editedDate
//    Date createdDate
    
    static constraints = {
        
        discountName(nullable: false, blank: false)
        value(nullable: false, blank: false, defaultValue:0.00)
        startDate(nullable: false, blank: false)
        endDate(nullable: false, blank: false)
        isAll(nullable: false, blank: false)
        deleted(nullable: false, blank: false)
        isAllPlan(nullable: false, blank: false)
        isAllUser(nullable: false, blank: false)
        subType(nullable: true, blank: true)
        billingCycles(nullable: false, blank: false)
        
//        createdBy   nullable:true,display:false,editable:false
//        editedBy    nullable:true,display:false,editable:false
//        editedDate  nullable:true,display:false,editable:false
//        createdDate nullable:true,display:false,editable:false
        
    }
    
    static hasMany = [accounts:Account, computingOffers:ComputingOffer]
    
}

enum DiscountType {
    USER, PARTICULAR
}

enum DiscountSubType {
    CREATE_VM, INVOICE_DISCOUNT
}
