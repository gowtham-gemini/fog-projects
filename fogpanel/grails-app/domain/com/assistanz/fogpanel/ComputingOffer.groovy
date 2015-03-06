package com.assistanz.fogpanel

//@gorm.AuditStamp
class ComputingOffer {
    
    String offerReferenceId
    String name
    String description
    boolean available = true
    Boolean deleted
    String coreCpuSpeed 
    Integer orderNo
    Double cpuNumber	
    Double memory	
    Boolean defaultUse
    String hostTags	
    Boolean isSystem	
    Boolean isPublic = true  
    Boolean limitCpuUse	
    String networkRate	
    Boolean offerHA	
    String storageType	//local or shared 
    String tags
        
//    String baseOs   
//    Zone zone
//    Pod pod
//    Cluster cluster
    
    Integer bandWidth
    String diskIO
    String diskReadRateBPS	
    String diskWriteRateBPS	
    String diskReadRateIOPS	
    String diskWriteRateIOPS	
    
//    Integer createdBy
//    Integer editedBy
//    Date editedDate
//    Date createdDate
   
    static constraints = {
        offerReferenceId(nullable: false, unique: true, blank: false)
        name(nullable: false, blank: false)
        description(nullable: false, blank: false)
        available(nullable: false, blank: false)
        coreCpuSpeed(nullable: false, blank: false)
        orderNo(nullable: true, blank: true, unique: false)
        cpuNumber(nullable: false, blank: false)
        memory(nullable: false, blank: false)
        defaultUse(nullable: true)
        hostTags(nullable: true)
        isSystem(nullable: true)
        isPublic(nullable: false, blank: false)
        networkRate(nullable: true)
        limitCpuUse(nullable: true)
        offerHA(nullable: true)
        storageType(nullable: true)
        tags(nullable: true)
        deleted(nullable: false, blank: false)
        
//        zone(nullable: false, blank: false)
//        pod(nullable: false, blank: false)
//        cluster(nullable: false, blank: false)
//        baseOs(nullable: false, blank: false)
        
        bandWidth(nullable: true, blank: true)
        
        diskReadRateBPS(nullable: true, blank: true)
        diskWriteRateBPS(nullable: true, blank: true)
        diskReadRateIOPS(nullable: true, blank: true)
        diskWriteRateIOPS(nullable: true, blank: true)
        diskIO(nullable: true, blank: true)
        
//        createdBy   nullable:true,display:false,editable:false
//        editedBy    nullable:true,display:false,editable:false
//        editedDate  nullable:true,display:false,editable:false
//        createdDate nullable:true,display:false,editable:false
    }
    
    static hasMany = [computingOfferZoneCosts: ComputingOfferZoneCost, discounts: Discount]
    static belongsTo = [Discount]
    
    static mapping = {
        computingOfferZoneCosts cascade: "all-delete-orphan"
    }
    
}
