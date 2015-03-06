package com.assistanz.fogpanel

class Project {
    
    String name
    
    static transients = ['groupDn'] 
    String []groupDn
    
    static hasMany = [groups:LdapGroup, cloudEngines:CloudEngine]  
    
    static belongsTo = CloudEngine    

    static constraints = {
        name (nullable: false ,unique: true)
    }    
}
