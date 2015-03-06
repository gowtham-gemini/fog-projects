package com.assistanz.fogpanel

//@gorm.AuditStamp
class VirtualMachine {    
    String referenceId
    ComputingOffer computingOffer
    Template template
    Zone zone
    Cluster cluster
    DiskOffer diskOffer
    String displayName
    String name
    String vmInternalName
    String groupName
    String hypervisor
    NetworkOffer networkOffer
    Double networkRead = 0.0
    Double networkWrite = 0.0
    Double cloudPreviousNetworkRead = 0.0
    Double cloudPreviousnetworkWrite = 0.0
    Boolean deleted
    Account owner 
    Iso iso
    User user
    String state
    String password
    String securityGroupReferenceId
    String nicIp
    String job
    Date upgradedDate
    Boolean firstRun
    SSHKeys sshKey
    String nicId
    String hostName
    String billingType = "hourly"
    
    VPC vpc
    
//    Long createdBy
//    Long editedBy
//    Date editedDate
//    Date createdDate
    
    static constraints = {
        owner(nullable: false, blank: false)
        networkRead(nullable: false, blank: false)
        networkWrite(nullable: false, blank: false)
        user(nullable: false, blank: false)
        securityGroupReferenceId(nullable: true, blank: true)
        referenceId(nullable: false, unique: true, blank: false)
        computingOffer(nullable: false, blank: false)
        template(nullable: false, blank: false)
        zone(nullable: false, blank: false)
        cluster(nullable: true, blank: true)
        hypervisor(nullable: false, blank: false)
        diskOffer(nullable: true, blank: true)
        displayName(nullable: false, blank: false)
        name(nullable: false, blank: false)  
        groupName(nullable: true, blank: true) 
        networkOffer(nullable: true, blank: true) 
        deleted(nullable: false, blank: false)
        state(nullable: false, blank: false)
        password(nullable: true, blank: true)
        vmInternalName(nullable: true, blank: true)
        nicIp(nullable: true, blank: true)
        vpc(nullable: true, blank: true)
        iso(nullable: true, blank: true)
        upgradedDate(nullable: false, blank: false)
        cloudPreviousNetworkRead(nullable: false, blank: false)
        cloudPreviousnetworkWrite(nullable: false, blank: false)
        firstRun(nullable: false, blank: false)
        sshKey(nullable: false, blank: false) 
        nicId(nullable: true, blank: true)
        hostName(nullable: true, blank: true)
        billingType(nullable: false, blank: false)
        
//        createdBy   nullable:true,display:false,editable:false
//        editedBy    nullable:true,display:false,editable:false
//        editedDate  nullable:true,display:false,editable:false
//        createdDate nullable:true,display:false,editable:false
    }
    
//    def setCreatedBy(Long id) {
//        if(id == null || id == "null" || id == "") {
//          createdBy = 0
//        } else {
//          createdBy = id;  
//        }
//    }
//    
//    def setCreatedBy(Integer id) {
//        if(id == null || id == "null" || id == "") {
//            createdBy = 0
//        } else {
//            createdBy = id;  
//        }
//    } 
//    
//    
//    def setEditedBy(Long id) {
//        if(id == null || id == "null" || id == "") {
//            editedBy = 0
//        } else {
//            editedBy = id;
//        }
//        
//    }
//    
//    def setEditedBy(Integer id) {
//       if(id == null || id == "null" || id == "") {
//            editedBy = 0
//        } else {
//            editedBy = id;
//        }
//    }
}
