package com.assistanz.fogpanel

class Volume {
    
    String name;
    String description;
    String referenceId;
    String snapshotId;
    String status;
    Integer size;
    Integer iops;
    Boolean encrypted;
    Boolean isDeleteOnTermination;
    
    VolumeType volumeType;
    Region region
    Zone zone
    VirtualMachine virtualMachine;
    
    Account account 
    User user
    
    Boolean deleted = false
    
    Date createdAt
    Date deletedAt 
    static constraints = {
        
        referenceId (nullable: false, blank: false)
        name (nullable: false, blank: false)
        snapshotId (nullable: false, blank: false)
        size (nullable: false, blank: false)
        status (nullable: false, blank: false)        
        iops (nullable: true, blank: true)        
        description (nullable:true , blank: true)
        encrypted (nullable: false, blank: false)
        
        volumeType (nullable:true , blank: true)
        region (nullable:true , blank: true)
        zone (nullable:true , blank: true)
        virtualMachine (nullable:true , blank: true)
        isDeleteOnTermination (nullable:true , blank: true)
        
        account(nullable: false, blank: false)
        user(nullable: false, blank: false)        
        deleted (nullable: false, blank: false)        
        deletedAt (nullable: true, blank: true)
        createdAt (nullable: true, blank: true)
    }
    
}
