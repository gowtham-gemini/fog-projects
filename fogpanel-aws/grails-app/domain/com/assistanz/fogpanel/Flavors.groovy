package com.assistanz.fogpanel

class Flavors {
    
    String name 
    String family
    String ecu
    String physicalProcessor
    String networkPerformance
    String processorArchitecture
    String intelAesNi
    String intelAvx
    String intelTurbo
    Boolean deleted = false
    Boolean isDisabled = false
    Boolean isPublic = true
    Boolean isEbsStorage = true
    Double memory     
    Integer vcpus
    Double clockSpeed
    String instanceStore
    String isEbsOptimized
//    Integer rootgb
    Date createdAt  
    Date deletedAt    
    
    static constraints = {
        
        name (nullable: false, blank: false)
        family (nullable: false, blank: false)
        isDisabled (nullable: false, blank: false)
        deleted (nullable: false, blank: false)
        isPublic (nullable: false, blank: false)
        memory (nullable: false, blank: false)
        vcpus (nullable: false, blank: false)
        clockSpeed (nullable: true, blank: true)
        instanceStore (nullable: false, blank: false)
        isEbsOptimized (nullable: false, blank: false)
//        rootgb (nullable: true, blank: true)
        deletedAt (nullable: true, blank: true)
        createdAt (nullable: true, blank: true)
        ecu (nullable: true, blank: true)
        physicalProcessor (nullable: true, blank: true)
        networkPerformance (nullable: true, blank: true)
        processorArchitecture (nullable: true, blank: true)
        intelAesNi (nullable: true, blank: true)
        intelAvx (nullable: true, blank: true)
        intelTurbo (nullable: true, blank: true)
    }        
}
