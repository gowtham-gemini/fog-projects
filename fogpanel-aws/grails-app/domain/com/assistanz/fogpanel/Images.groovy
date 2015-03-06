package com.assistanz.fogpanel

class Images {

    String referenceId
    String name
    String description    
    String rootDeviceType;
    String rootDeviceName;
    String virtualizationType;
    String hypervisor;
    String state;
    String ownerId;
    String architecture;
    String imageType;
    String kernelId;
    String ramdiskId;
    String platform;
    String imageOwnerAlias;    
    Boolean isPublic = true        
    Date createdAt
    Date deletedAt       
    VirtualMachine virtualMachine  
    User user
    Account account    
    Boolean deleted = false
    
    Double cost = 0.0
    Boolean isVMSnapshot = false    
    boolean oneTimeChargeable = false
    String billingType = "hourly"
        
    static constraints = {
        
        referenceId (nullable: false, blank: false)
        name (nullable: false, blank: false)
        description (nullable: true, blank: true)
        rootDeviceType (nullable: false, blank: true)
        rootDeviceName (nullable: true, blank: true)
        virtualizationType (nullable: false, blank: true)
        hypervisor (nullable: false, blank: true)
        state (nullable: false, blank: true)
        ownerId (nullable: true, blank: true)
        architecture (nullable: false, blank: true)
        imageType (nullable: true, blank: true)
        kernelId (nullable: true, blank: true)
        ramdiskId (nullable: true, blank: true)
        platform (nullable: true, blank: true)
        imageOwnerAlias (nullable: true, blank: true)

        deleted (nullable: false, blank: false)
        isPublic (nullable: false, blank: false)
        deletedAt (nullable: true, blank: true)
        createdAt (nullable: true, blank: true)
        cost (nullable: false, blank: false)
        
        isVMSnapshot (nullable: true, blank: true)
        virtualMachine (nullable: true, blank: true)
        oneTimeChargeable(nullable: false, blank: false)
        user (nullable: true, blank: true)
        account (nullable: true, blank: true)
        billingType(nullable: false, blank: false)
    }
}
