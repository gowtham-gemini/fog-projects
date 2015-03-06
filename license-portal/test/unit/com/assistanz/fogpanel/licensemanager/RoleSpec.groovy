package com.assistanz.fogpanel.licensemanager

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

@TestMixin(GrailsUnitTestMixin)
class RoleSpec extends Specification {
    
    void 'Test Role with valid values'() { 
        
        given:
            def role = new Role()
            
        when: 
            role.authority = "admin"
        
        then: 
            role.validate()
    }
        
    void 'Test Role with blank values'() { 
        
        when: 
            def role = new Role()
        
        then: 
            !role.validate()
    }
    
    void 'Test Role with null values'() { 
        
        when: 
            def role = new Role()
        
        then: 
            !role.validate()
    }
}
