package com.assistanz.fogpanel.licensemanager

class User {

    transient springSecurityService

    String username
    String password
    boolean enabled = true
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired
    String firstName
    String companyName
    String lastName
    String ipAddress
    Date lastLogin
    Date registrationDate
    String address1
    String address2
    String state
    String country
    String city
    String zip
    Date tokenExpiryDate
    String token
    Status status = Status.NOT_VERIFIED

    static transients = ['springSecurityService']

    static constraints = {
        username blank: false, unique: true, email: true
        password blank: false, nullable: false, minSize: 8
        firstName blank: false, nullable: false
        companyName blank: true, nullable: true
        lastName blank: false, nullable: false
        ipAddress blank: true, nullable: true
        lastLogin blank: true, nullable: true
        registrationDate blank: true, nullable: true
        address1 blank: false , nullable: false
        address2 blank: true, nullable: true
        state blank: false, nullable: false
        country blank: false, nullable: false
        city blank: false , nullable: false
        zip blank: false, nullable: false
        tokenExpiryDate nullable: true, blank: true
        token nullable: true, blank: true
        status nullable: false, blank: false
                   
    }

    static mapping = {
        password column: '`password`'
    }

    Set<Role> getAuthorities() {
        UserRole.findAllByUser(this).collect { it.role } as Set
    }

    def beforeInsert() {
        encodePassword()
    }

    def beforeUpdate() {
        if (isDirty('password')) {
            encodePassword()
        }
    }

    protected void encodePassword() {
        password = springSecurityService.encodePassword(password)
    }
        
    String toString() {
        return username;
    }
        
    
    enum Status  {
        ACTIVE, //0
        BLOCKED, //1
        LOCKED, //2
        DISABLED, //3
        NOT_VERIFIED, //4 
        SUSPENDED, //5
        CANCELED, //6
        CLOSED //7
    }
}
