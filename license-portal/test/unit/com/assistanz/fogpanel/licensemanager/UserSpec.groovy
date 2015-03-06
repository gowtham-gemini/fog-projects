package com.assistanz.fogpanel.licensemanager

import grails.test.mixin.TestFor
import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

@TestFor(User)
@TestMixin(GrailsUnitTestMixin)
class UserSpec extends Specification {
    
    void 'Test User with valid values'() { 
        
        given:
            def user = new User()
            def date = Calendar.getInstance();
            
        when: 
            user.username = "ramkumar@gmail.com"
            user.password = "ramkumar123"
            user.enabled = true
            user.accountExpired = true
            user.accountLocked = true
            user.passwordExpired = true
            user.firstName = "ram"
            user.companyName = "fpsolution"
            user.lastName = "kumar"
            user.lastLogin = date.getTime()
            user.registrationDate = date.getTime()
            user.ipAddress = "192.168.2.23"
            user.address1 = "1/2 ram street chennai"
            user.address2 = "1/2 ram street chennai"
            user.state = "tamilnadu"
            user.country = "india"
            user.city = "coimbatore"
            user.zip = "642811"
            user.tokenExpiryDate = date.getTime()
            user.token = "tok123654"
            user.status = "ACTIVE"
            
        then: 
            user.validate()
    }
    
    void 'Test User with unique username'() {     

        given:
            def user = new User()

        when: 
            user.username = "ramkumar@gmail.com"

        then: 
            !user.validate()
    }
    
    void 'Test User with blank values'() { 
        
        when: 
            def user = new User()
        
        then: 
            !user.validate()
    }
    
        void 'Test User with null values'() { 
        
        when: 
            def user = new User()
        
        then: 
            !user.validate()
    }
}
