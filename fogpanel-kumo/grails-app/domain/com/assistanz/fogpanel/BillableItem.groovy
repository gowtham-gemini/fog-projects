package com.assistanz.fogpanel

/**
 * The billable Item includes list of billable services
 * It maintains separate tax information per billable item.
 * Billable service include customized option for manual change.
 */
//@gorm.AuditStamp
class BillableItem {
    
    /**
     * Name of the Billable Item.
     */
    String name
    
    /**
     * Reference to the service offered which provides information for this
     * Billable item.
     */
    String referenceItemName
    
    /**
     * Reference to the Tax applied, which provides information for this
     * billable item.
     */
    Tax tax
    
    /**
     * Do this billable item should be availabe for Invoice or not.
     */
    Boolean enabled
    
    /**
     * Do this billable item is manually added item or not.
     */
    Boolean customized
    
    /**
     * Do this billable item has discount. default value false
     */
    Boolean discountable = false
    
    Boolean hasZone = false
    
//    Integer createdBy
//    Integer editedBy
//    Date editedDate
//    Date createdDate
        
    static constraints = {
        name(nullable: false, blank: false)   
        referenceItemName(nullable: false, blank: false)   
        tax(nullable: false, blank: false)   
        enabled(nullable: false, blank: false)  
        customized(nullable: false, blank: false) 
        discountable(nullable: false, blank: false) 
        hasZone(nullable: false, blank: false) 
        
//        createdBy   nullable:true,display:false,editable:false
//        editedBy    nullable:true,display:false,editable:false
//        editedDate  nullable:true,display:false,editable:false
//        createdDate nullable:true,display:false,editable:false
    }
}