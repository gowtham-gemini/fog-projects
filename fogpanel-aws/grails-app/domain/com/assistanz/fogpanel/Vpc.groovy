package com.assistanz.fogpanel

class Vpc {
    
    String referenceId
    String state
    String cidrBlock
    String dhcpOptionsId
    String instanceTenancy
    Boolean isDefault
    Date createdAt
    Date deletedAt
    Account account
    User user
    Region region
    Boolean deleted
    
    static hasMany = [subnets: Subnet, securityGroup: SecurityGroup, virtualMachine: VirtualMachine]
    static constraints = {
        
        referenceId(nullable:false,blank:false,unique:true)
        state(nullable:false,blank:false)
        cidrBlock(nullable:false,blank:false)
        dhcpOptionsId(nullable:true,blank:true)
        instanceTenancy(nullable:true,blank:true)
        createdAt(nullable:true,blank:true)
        deleted(nullable:true,blank:true)
        deletedAt(nullable:true,blank:true)
        virtualMachine(nullable:true,blank:true)
        
    }
}
