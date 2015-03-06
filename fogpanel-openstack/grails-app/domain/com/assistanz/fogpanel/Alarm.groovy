package com.assistanz.fogpanel

class Alarm {
    
    NotificationTopic topic
    VirtualMachine virtualMachine
    String refrenceId
    Account account
    String type
    String subType
    String partition
    String mountPoint
    Double minValue
    Double maxValue
    String thresholdType
    String name
    String severity
    Integer portNumber
    MonitoringDevice monitoringDevice
    
    Boolean portEnabled = false
    Boolean deleted = false
    Date createdAt
    Date deletedAt
    Date updatedAt
    
    
    static constraints = {
        
        topic(nullable: false, blank: false)
        account(nullable: false, blank: false)        
        virtualMachine(nullable: true, blank: true, unique: ['type', 'portNumber'])
        refrenceId(nullable: false, blank: false)
        type(nullable: false, blank: false)
        subType(nullable: true, blank: true)
        partition(nullable: true, blank: true)
        mountPoint(nullable: true, blank: true)
        minValue(nullable: false, blank: false)
        maxValue(nullable: false, blank: false)
        thresholdType(nullable: false, blank: false)
        name(nullable: true, blank: true)
        severity(nullable: true, blank: true)
        portNumber(nullable: true, blank: true)
        monitoringDevice(nullable: false, blank: false)
        
        portEnabled (nullable: false, blank: false)
        deleted (nullable: false, blank: false)
        deletedAt (nullable: true, blank: true)
        createdAt (nullable: true, blank: true)
        updatedAt (nullable: true, blank: true)
    }
}
