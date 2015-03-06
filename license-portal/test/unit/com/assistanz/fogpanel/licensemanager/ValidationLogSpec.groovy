package com.assistanz.fogpanel.licensemanager

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

@TestMixin(GrailsUnitTestMixin)
class ValidationLogSpec extends Specification {
    
    void 'Test validation log with valid values'() { 
        
        given:
            def user = new User()
            def date = Calendar.getInstance();
            def product = new Product()
            def productInstance = new ProductInstance()
            def validationLog = new ValidationLog()
            
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
            
            validationLog.requestID = "ReqId78456932ed"
            validationLog.hostName = "testdomain"
            validationLog.initialVersion = "1.1"
            validationLog.currentVersion = "4.2"
            validationLog.hostIP = "192.168.1.23"
            validationLog.sockets = 12368
            validationLog.lastUpdatedDate = 12354
            validationLog.deployedDate = 58796
            validationLog.registrationDate = 54297
            validationLog.timeZone = "chennai"
            validationLog.generatedKey = "Hmaz45687jhg"
            validationLog.checksum = "Kj542369"
            validationLog.additionalData = "Id521364LKM"
            validationLog.valid = true
            validationLog.errorMessage = "message added"
            validationLog.createdDate = date.getTime()
            validationLog.productInstance = productInstance
            
        then: 
            validationLog.validate()
    }
}
