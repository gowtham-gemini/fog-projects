package com.assistanz.fogpanel

class VolumeType {

    String name;
    Integer sizeMin;
    Integer sizeMax;
    Integer iopsMin;
    Integer iopsMax;
    Integer baselineIops;
    Boolean deleted = false    
    Date createdAt
    Date deletedAt 
    Boolean isIops = false
    static constraints = {
        name(nullable: false, blank: false)
        isIops(nullable: false, blank: false)
        deleted(nullable: false, blank: false)        
        deletedAt(nullable: true, blank: true)
        createdAt(nullable: true, blank: true)
        iopsMin(nullable: true, blank: true)
        iopsMax(nullable: true, blank: true)
        sizeMax(nullable: true, blank: true)
        sizeMin(nullable: true, blank: true)
        baselineIops(nullable: true, blank: true)
    }
}
