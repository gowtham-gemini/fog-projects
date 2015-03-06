package com.assistanz.fogpanel

//@gorm.AuditStamp
class DiskOffer {
    
    String diskOfferReferenceId
    String name
    String description
    Integer size
    Integer minSize
    Integer maxSize
    boolean available = true
    Boolean customized  
    String storageType	//local or shared 
    String tags
    Integer orderNo
    Boolean deleted
     
    String qoSType
    
    String diskReadRateBPS	
    String diskWriteRateBPS	
    String diskReadRateIOPS	
    String diskWriteRateIOPS
    
    Boolean isCustomizedIops
    String minIOPS	
    String maxIOPS	
    
    String hypervisorSnapReserve 
    
    Domain domain
    boolean isPublic
    
    
//    Integer createdBy
//    Integer editedBy
//    Date editedDate
//    Date createdDate
    
    static constraints = {
        diskOfferReferenceId(nullable: false, unique: true)
        name(nullable: false, blank: false)
        available(nullable: false, blank: false)
        description(nullable: false, blank: false)
        size(nullable: false, blank: false)
        minSize(nullable: true, blank: true)     
        maxSize(nullable: true, blank: true)             
        customized(nullable: true)
        tags(nullable: true)
        storageType(nullable: true)
        orderNo(nullable: false, blank: false)
        deleted(nullable: false, blank: false)
                    
        diskReadRateBPS(nullable: true, blank: true)
        diskWriteRateBPS(nullable: true, blank: true)
        diskReadRateIOPS(nullable: true, blank: true)
        diskWriteRateIOPS(nullable: true, blank: true)
        
        minIOPS(nullable: true, blank: true)
        maxIOPS(nullable: true, blank: true)
        
        qoSType(nullable: true, blank: true)  
        hypervisorSnapReserve(nullable: true, blank: true)
        
        isPublic(nullable: false, blank: false)
        domain(nullable: true, blank: true)
        
        
        isCustomizedIops(nullable: true, blank: true)
        
//        createdBy   nullable:true,display:false,editable:false
//        editedBy    nullable:true,display:false,editable:false
//        editedDate  nullable:true,display:false,editable:false
//        createdDate nullable:true,display:false,editable:false
    }
    
    static hasMany = [diskOfferZoneCosts: DiskOfferZoneCost]
    
    static mapping = {
        diskOfferZoneCosts cascade: "all-delete-orphan"
    }
}
