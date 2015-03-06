package com.assistanz.fogpanel

import java.sql.Timestamp

/**
 * The offering usage is a list of services from cloud stack
 * and usage information of a particular service like instance.
 * It stores hourly usage information of this service
 */
//@gorm.AuditStamp
class OfferingUsage {
    
    /**
     * The account which the usage record belongs to.
     */
    Account account
    
    /**
     * The domain which the usage record belong to.
     */
    Domain domain
    
    /**
     * The zone which the usage record belong to.
     */
    Zone zone
    
    /**
     * The description about usage record.
     */
    String description
    
    /**
     *  Reference id of service offereing.
     */
    String offeringId
    
    /**
     * Reference id of template.
     */
    String templateId
    
    /**
     * Reference id of virtual Machine.
     */
    String virtualMachineId
    
    /**
     * Reference id of virtual Machine.
     */
    String virtualMachineName
    
    /**
     * Usage in hours
     */
    Double hours
    
    /**
     * Usage in hours
     */
    String usageDisplay
    
    /**
     * The generated id for reference.
     */
    String referenceId
    
    /**
     * The size of the volume.
     */
    String size
    
    /**
     * The netwotkId of the vm.
     */
    String netwotkId
    
    /**
     * Start date of the usage record
     */
    Timestamp startDate
    
    /**
     * End date of the usage record
     */
    Timestamp endDate
    
    /**
     * Cost for this usage offering
     */
    Double cost = 0.0
    
    /**
     * The Type of usage which belong to this offering
     * Possible values include RUNNING_VM_USAGE, ALLOCATED_VM_USAGE,
     * IP_ADDRESS_USAGE, NETWORK_USAGE_BYTE_SENT, NETWORK_USAGE_BYTE_RECEIVED,
     * VOLUME_USAGE, TEMPLATE_USAGE, ISO_USAGE, SNAPSHOT_USAGE, SECURITY_GROUP_USAGE,
     * LOAD_BALANCER_USAGE, PORT_FORWARDING_USAGE, NETWORK_OFFERING_USAGE, VPN_USER_USAGE
     */ 
    UsageType usageType
    
//    Integer createdBy
//    Integer editedBy
//    Date editedDate
//    Date createdDate
    
    static constraints = {
        account(nullable: false, blank: false) 
        domain(nullable: false, blank: false) 
        zone(nullable: false, blank: false) 
        description(nullable: true, blank: true) 
        offeringId(nullable: true, blank: true) 
        templateId(nullable: true, blank: true) 
        virtualMachineId(nullable: true, blank: true) 
        virtualMachineName(nullable: true, blank: true) 
        hours(nullable: true, blank: true) 
        usageDisplay(nullable: true, blank: true) 
        referenceId(nullable: true, blank: true) 
        startDate(nullable: true, blank: true) 
        endDate(nullable: true, blank: true) 
        cost(nullable: true, blank: true) 
        usageType(nullable: false, blank: false)     
        size(nullable: true, blank: true)
        netwotkId(nullable: true, blank: true)
        
//        createdBy   nullable:true,display:false,editable:false
//        editedBy    nullable:true,display:false,editable:false
//        editedDate  nullable:true,display:false,editable:false
//        createdDate nullable:true,display:false,editable:false
    }
    static belongsTo = [account: Account]
    
    static mapping = {          
        startDate(type: 'timestamp', sqlType: 'timestamp')      
        endDate(type: 'timestamp', sqlType: 'timestamp' )
    } 
}

enum UsageType {
    NOT_AVAILABLE,//0
    RUNNING_VM_USAGE, //1
    ALLOCATED_VM_USAGE, //2
    IP_ADDRESS_USAGE, //3
    NETWORK_USAGE_BYTE_SENT, //4
    NETWORK_USAGE_BYTE_RECEIVED, //5 
    VOLUME_USAGE, //6
    TEMPLATE_USAGE, //7 
    ISO_USAGE, //8
    SNAPSHOT_USAGE, //9
    SECURITY_GROUP_USAGE, //10 
    LOAD_BALANCER_USAGE, //11
    PORT_FORWARDING_USAGE, //12
    NETWORK_OFFERING_USAGE, //13
    VPN_USER_USAGE, //14
    VM_SNAPSHOT // 15
}
