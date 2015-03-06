package com.assistanz.fogpanel

class PersistentSession {

        transient springSecurityService
        
	String id
	Long creationTime
	Long lastAccessedTime
	Boolean invalidated = false
	Integer maxInactiveInterval = 30
        String data

	static transients = ['valid']

	static mapping = {
		id generator: 'assigned'
		version false // be sure to lock when changing invalidated but ok to have concurrent updates of lastAccessedTime
		dynamicUpdate true
                data sqlType:"longtext"
                
	}
        static constraints = {
            data (nullable: false, blank: false)
            
        }

	boolean isValid() {
		!invalidated && lastAccessedTime > System.currentTimeMillis() - maxInactiveInterval * 1000 * 60
	}
}