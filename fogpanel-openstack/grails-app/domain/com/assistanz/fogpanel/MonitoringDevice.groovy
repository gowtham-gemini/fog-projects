package com.assistanz.fogpanel

class MonitoringDevice {

    String deviceName;
    VirtualMachine virtualMachine
    String refrenceId
    String osType;
    String snmpCommunity;
    Integer snmpPort;
    String collector;
    String jobId;
    String jobStatus;
    
    Boolean deleted = false
    Date createdAt
    Date deletedAt
    Date updatedAt
    
    static constraints = {
        
        deviceName (nullable: true, blank: true)
        virtualMachine(nullable: true, blank: true)
        refrenceId(nullable: false, blank: false)
        osType (nullable: false, blank: false)
        snmpCommunity (nullable: false, blank: false)
        snmpPort (nullable: false, blank: false)
        collector (nullable: false, blank: false)
        jobId (nullable: true, blank: true)
        jobStatus (nullable: true, blank: true)
        
        deleted (nullable: false, blank: false)
        deletedAt (nullable: true, blank: true)
        createdAt (nullable: true, blank: true)
        updatedAt (nullable: true, blank: true)
   
    }
}
