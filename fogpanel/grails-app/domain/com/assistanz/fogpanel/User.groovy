package com.assistanz.fogpanel

//@gorm.AuditStamp
class User {

	transient springSecurityService

	String username
	String password
	boolean enabled
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired
	Account account
	Integer failureCount
        Date lockTime
        Date tokenExpiryDate
	String uuid
	String apiKey
	String secretKey
        String token
        String dateFormat="dd/MMM/yyyy"
        String currencyFormat
        Domain domain
        String firstName
        String lastName
        String email
            
	static constraints = {
                failureCount (nullable: false, blank: false)
                lockTime (nullable: true, blank: true)
                domain (nullable: false, blank: false)
                tokenExpiryDate(nullable: true, blank: true)
		username blank: false, unique: 'domain'
                //username blank: false, unique: true
		password blank: false
		account nullable: false
		uuid nullable: true, unique: true
		apiKey nullable: true, unique: true
                token (nullable: false, blank: false)
                dateFormat(nullable: true, blank: true)
                currencyFormat(nullable: true, blank: true)                
		secretKey nullable: true, unique: true
                email (email: true, blank: false,nullable: false)
                firstName (nullable: false, blank: false)
                lastName (maxSize: 150, nullable: false, blank: false)
                
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
}
