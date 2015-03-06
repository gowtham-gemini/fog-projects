package com.assistanz.fogpanel

//@gorm.AuditStamp
class User {

    transient springSecurityService

    String username
    boolean enabled
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired    
    String firstName
    String lastName
    String email
    String contactNumber
    String uuid
    String apiKey    
    
    static belongsTo = LdapGroup
    static hasMany = [groups:LdapGroup]
    
    static transients = ['password']
    
    static constraints = {                                                                
        uuid nullable: true, unique: true
        username nullable: true, unique: true
        apiKey nullable: true, unique: true        
        email (email: true, blank: true,nullable: true)
        firstName (nullable: false, blank: false)
        lastName (maxSize: 150, nullable: false, blank: false)
        contactNumber(nullable: true )
    }   
    
    
    User(String username ,boolean enabled, 
        boolean accountExpired,boolean accountLocked,boolean passwordExpired,
        String uuid ){
        
        this.username = username
        this.uuid = uuid        
        this.accountExpired =  accountExpired
        this.accountLocked =  accountLocked
        this.passwordExpired =  passwordExpired

    }   

    Set<Role> getAuthorities() {
        UserRole.findAllByUser(this).collect { it.role } as Set
    }
    
}