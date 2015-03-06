package com.assistanz.fogpanel

//@gorm.AuditStamp
class Promotion {
    
    String code
    PromotionType type
    Double value
    Date startDate
    Date endDate
    Integer maxUses
    Integer uses
    String notes
    
//    Integer createdBy
//    Integer editedBy
//    Date editedDate
//    Date createdDate

    static constraints = {
        code(nullable: false, unique: true, blank: false)
        type(nullable: false, blank: false)
        value(nullable: false, blank: false, defaultValue:0.00)
        startDate(nullable: true, blank: true)
        endDate(nullable: true, blank: true)
        maxUses(nullable: false, blank: false, defaultValue:0)
        uses(nullable: false, blank: false, defaultValue:0)
        notes(nullable: false, blank: false, maxSize:1000)
        
//        createdBy   nullable:true,display:false,editable:false
//        editedBy    nullable:true,display:false,editable:false
//        editedDate  nullable:true,display:false,editable:false
//        createdDate nullable:true,display:false,editable:false
    }
    
}

enum PromotionType {
    PERCENTAGE, FIXED
}