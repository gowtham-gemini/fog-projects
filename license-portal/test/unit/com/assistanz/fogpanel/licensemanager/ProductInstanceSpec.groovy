package com.assistanz.fogpanel.licensemanager

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

@TestMixin(GrailsUnitTestMixin)
class ProductInstanceSpec extends Specification {
    
    void 'Test product instance with valid values'() { 
        
        given:
            def user = new User()
            def date = Calendar.getInstance();
            def product = new Product()
            def productInstance = new ProductInstance()
            
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
            user.address1 = "1/2 ram street chennai"
            user.address2 = "1/2 ram street chennai"
            user.state = "tamilnadu"
            user.country = "india"
            user.city = "coimbatore"
            user.zip = "642811"
            user.tokenExpiryDate = date.getTime()
            user.token = "tok123654"
            user.status = "ACTIVE"
            
            product.name = "computer"
            product.description = "computer used for do a calculation "
            product.licenseQuantity = 124563
            product.price = 541.235
            product.baseProduct = product
            product.status = "ACTIVE"
            
            productInstance.name = "raman"
            productInstance.product = product
            productInstance.user = user
            productInstance.baseLicense = 456.321
            productInstance.additionalLicense = 987.321
            productInstance.activeLicense = 741.123
            productInstance.inceptionDate = date.getTime()
            productInstance.expirationDate = date.getTime()
            productInstance.registeredDate = date.getTime()
            productInstance.lastUpdatedOn = 12.32
            productInstance.secretKey = "secret852963"
            productInstance.timeZone = "India"
            productInstance.instancePath = "computer/myfile"
            productInstance.autoUpdateComment = "product values updated"
            productInstance.status = "ACTIVE"
            productInstance.emergencyExpiryDate = date.getTime()
            productInstance.appStatus = "Not Registered"
            
        then: 
            productInstance.validate()
    }
    void 'Test product instance with blank values'() { 
        
        when:
            def productInstance = new ProductInstance()
            
        then: 
            !productInstance.validate()
    }
    
    void 'Test product instance with null values'() { 
        
        given:
            def user = new User()
            def product = new Product()
            def productInstance = new ProductInstance()
        
        when: 
            productInstance.user = null
            productInstance.product = null
        
        then: 
            !productInstance.validate()
    }    
}
