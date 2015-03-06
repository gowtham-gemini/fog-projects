package com.assistanz.fogpanel

class LdapGroup {
    
    String name            
    
    static hasMany = [users:User ,projects:Project]          
    static belongsTo = Project    
    
    static constraints = {
        name (nullable: false ,unique: true)                        
    }    
    
    static transients  = ['memberDn'] 
    
    String []memberDn

    
    
}
