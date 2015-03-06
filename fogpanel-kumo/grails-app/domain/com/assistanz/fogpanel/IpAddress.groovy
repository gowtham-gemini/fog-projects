package com.assistanz.fogpanel

//@gorm.AuditStamp
class IpAddress {
    
    String ipBlockName
    BigInteger startIpValue
    BigInteger endIpValue
    String startIp
    String endIp
    String netMask
    String gateWay
    String dns1
    String dns2
    Zone zone
    Pod pod
    Boolean deleted
    
//    Integer createdBy
//    Integer editedBy
//    Date editedDate
//    Date createdDate
    
    static constraints = {
        ipBlockName(nullable: false, blank: false)
        zone(nullable: false, blank: false)
        deleted(nullable: false, blank: false)
        pod(nullable: false, blank: false)
        gateWay(nullable: false, blank: false, matches:/^\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}$/)
        netMask(nullable: false, blank: false, matches:/^\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}$/) 
        startIp(nullable: false, blank: false, unique: true, matches:/^\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}$/)
        startIpValue(nullable: false, blank: false, unique: true)
        endIp(nullable: false, blank: false, unique: true, matches:/^\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}$/)
        endIpValue(nullable: false, blank: false, unique: true)
        dns2(nullable: true, blank: true, matches:/^\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}$/)
        dns1(nullable: true, blank: true, matches:/^\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}$/)
        
//        createdBy   nullable:true,display:false,editable:false
//        editedBy    nullable:true,display:false,editable:false
//        editedDate  nullable:true,display:false,editable:false
//        createdDate nullable:true,display:false,editable:false
    }
}
