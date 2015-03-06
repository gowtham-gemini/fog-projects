package com.assistanz.fogpanel

import java.sql.Timestamp

class VirtualMachine {
    
    String name
    String referenceId
    String architecture
    String hypervisor
    String rootDeviceType
    String rootDeviceName
    String privateDnsName    
    String publicDnsName
    String privateIpAddress
    String publicIpAddress
    String kernelId
    String ramdiskId
    String state
    Boolean ebsOptimized = false    
    Boolean monitoringEnabled = false
    Timestamp launchTime            
    
    Images image
    Flavors flavor
    SSHKeys sshkeys
    SecurityGroup securityGroup
    Vpc vpc
    Subnet subnet
    Region region
    Account account 
    User user      
    Date createdAt
    Date deletedAt    
    Boolean deleted = false    
       
    static constraints = {
        
        name(nullable: false, blank: false)
        referenceId(nullable: false, blank: false)
        architecture(nullable: true, blank: true)
        hypervisor(nullable: true, blank: true)
        rootDeviceType(nullable: true, blank: true)
        rootDeviceName(nullable: true, blank: true)
        privateDnsName(nullable: true, blank: true)
        publicDnsName(nullable: true, blank: true)
        privateIpAddress(nullable: true, blank: true)
        publicIpAddress(nullable: true, blank: true)
        kernelId(nullable: true, blank: true)
        ramdiskId(nullable: true, blank: true)
        ebsOptimized(nullable: false, blank: false)   
        monitoringEnabled(nullable: false, blank: false)   
        launchTime(nullable: true, blank: true)        
        
        image(nullable: false, blank: false)
        flavor(nullable: false, blank: false)
        sshkeys(nullable: false, blank: false)
        securityGroup(nullable: false, blank: false)
        vpc(nullable: false, blank: false)
        subnet(nullable: false, blank: false)
        region(nullable: false, blank: false)
        account(nullable: false, blank: false)
        user(nullable: false, blank: false)        
        createdAt(nullable: true, blank: true)
        deletedAt(nullable: true, blank: true)        
        deleted(nullable: true, blank: true)        
                
    }
    
    
}
