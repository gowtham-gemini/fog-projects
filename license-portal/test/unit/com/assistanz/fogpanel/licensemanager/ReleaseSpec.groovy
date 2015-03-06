package com.assistanz.fogpanel.licensemanager

import grails.test.mixin.TestFor
import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

@TestFor(Release)
@TestMixin(GrailsUnitTestMixin)
class ReleaseSpec extends Specification {
    
    void 'Test Release with valid values'() { 
        
        given:
            def product = new Product()
            def release = new Release()
        
        when:
            product.name = "computer"
            product.description = "computer used for do a calculation "
            product.licenseQuantity = 124563
            product.price = 541.235
            product.baseProduct = product
            product.status = "ACTIVE"

            release.productVersion = "Version123"
            release.vhdPath = "computer/desk/myfiles"
            release.bundlePath = "D:/computer/desk/myfiles"
            release.releaseNotes = "Description about the producr version"
            release.product = product
        
        then:
            release.validate()
    }
    
    void 'Test Release with blank values'() { 
        
        when: 
            def product = new Product()
            def release = new Release()
            
        then: 
            !release.validate()
    }
    
    void 'Test Release with null values'() { 
        
        given:
            def product = new Product()
            def release = new Release()    
        
        when: 
            release.product = null
        
        then: 
            !release.validate()
    }
}
