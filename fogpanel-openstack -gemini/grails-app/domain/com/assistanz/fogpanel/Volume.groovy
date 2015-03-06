package com.assistanz.fogpanel

class Volume {
    
    String name;
    String description;
    String referenceId;
    String status="Available";
    Integer size;
    String sourceVolumeId;
    
    Images image;
    VolumeType volumeType;
    Zone zone;
    VolumeSnapshot volumeSnapshot;
    VirtualMachine virtualMachine;
    
    Account account 
    User user
    
    Boolean deleted = false
    
    Date createdAt
    Date deletedAt 
    String billingType = "hourly"
    static constraints = {
        
        referenceId (nullable: false, blank: false)
        name (nullable: false, blank: false)
        size (nullable: false, blank: false)
        status (nullable: false, blank: false)
        
        description (nullable:true , blank: true)
        
        image (nullable:true , blank: true)
        volumeType (nullable:true , blank: true)
        zone (nullable:true , blank: true)
        volumeSnapshot (nullable:true , blank: true)
        virtualMachine (nullable:true , blank: true)
        
        account(nullable: false, blank: false)
        user(nullable: false, blank: false)
        sourceVolumeId(nullable:true , blank: true)
        
        deleted (nullable: false, blank: false)
        
        deletedAt (nullable: true, blank: true)
        createdAt (nullable: true, blank: true)
        billingType(nullable: false, blank: false)
    }
    
    static belongsTo = [virtualMachine: VirtualMachine]
    
}
