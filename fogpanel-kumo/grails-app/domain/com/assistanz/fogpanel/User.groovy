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
        
//        Integer createdBy
//        Integer editedBy
//        Date editedDate
//        Date createdDate
    
	static constraints = {
                failureCount (nullable: false, blank: false)
                lockTime (nullable: true, blank: true)
                tokenExpiryDate(nullable: true, blank: true)
		username blank: false, unique: true
		password blank: false
		account nullable: false
		uuid nullable: true, unique: true
		apiKey nullable: true, unique: true
                token (nullable: false, blank: false)
		secretKey nullable: true, unique: true
                
//            createdBy   nullable:true,display:false,editable:false
//            editedBy    nullable:true,display:false,editable:false
//            editedDate  nullable:true,display:false,editable:false
//            createdDate nullable:true,display:false,editable:false
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
