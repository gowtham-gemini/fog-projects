package com.assistanz.fogpanel.licensemanager

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(ApplicationsController)
@Mock([ProductInstance, ValidationLog, Product, User, Release, ApplicationService])

class ApplicationsControllerSpec extends Specification {
    
    def productInstance = null
    def product = null
    def user = null
    def release = null
    def date = Calendar.getInstance();
    
    def createUser() {
        if(user == null) {
            user = new User()
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
            user.city = "chennai"
            user.zip = "642811"
            user.tokenExpiryDate = date.getTime()
            user.token = "tok123654"
            user.status = "ACTIVE"
            def springSecurityService = new Object()
            springSecurityService.metaClass.encodePassword = {String password -> "ENCODED_PASSWORD"}
            user.springSecurityService = springSecurityService
            user.save(flush: true)
            createProduct()
        }
    }    
    
    def createProduct() {
        if(product == null) {
            product = new Product()
            product.name = "computer"
            product.description = "computer used for do a calculation "
            product.licenseQuantity = 124563
            product.price = 541.235
            product.code = "12365"
            product.baseProduct = product
            product.status = "ACTIVE"
            product.save(flush: true)
            createProductInstance()
        }
    }    
    
    def createProductInstance() {
        if(productInstance == null) {
            productInstance = new ProductInstance()
            productInstance.name = "raman"
            productInstance.product = product
            productInstance.user = user
            productInstance.baseLicense = 456.321
            productInstance.additionalLicense = 0
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
            productInstance.save(flush: true)
            createRelease()
        }
    }    
    def createRelease() {
        if(release == null) {
            release = new Release()
            release.productVersion = "Version123"
            release.vhdPath = "computer/desk/myfiles"
            release.bundlePath = "D:/computer/desk/myfiles"
            release.releaseNotes = "Description about the producr version"
            release.product = product
        }
    }
    
    void "Test Applications controller index method"() {
        
        given:
            createUser()
            controller.params.format = "json"
            request.addHeader "Accept", "text/json"
            
        when:
            controller.session.SPRING_SECURITY_CONTEXT = [authentication:[principal:[id: user.id]]]
            controller.springSecurityService = new Object()
            controller.springSecurityService.metaClass.currentUser = user
            controller.index()
            
        then:
            response.json.id == user.id     
    }
    
    void "Test Applications controller new app method"() {
        
        given:
            controller.params.format = "json"
            request.addHeader "Accept", "text/json"
            
        when: 
            controller.newApp()
            
        then:
            println controller.response.text
    }
    
        void "Test Applications controller releases method"() {
        
        given:
            controller.params.format = "json"
            request.addHeader "Accept", "text/json"
            
        when: 
            controller.releases()
            
        then:
            println controller.response.text
    }
    
    void "Test Applications controller show method"() {
        
        given:
            createUser()
            controller.params.id = productInstance.id
            
        when: 
            def model = controller.show()
            
        then:
            productInstance.id == model.productInstance.id
    }
    
    void "Test Applications controller change name method"() {
        
        given:
            createUser()
            controller.params.id = productInstance.id
            controller.params.customName = productInstance.name
        
        when: 
            controller.changeName()
           
        then: 
            view == '/applications/show'
    }
    
    void "Test Applications controller show add additional license method"() {
        
        given:
            createUser()
            controller.params.id = productInstance.id
            
        when:                                                                   
            def model = controller.showAddAdditionalLicense()
           
        then:
            productInstance.id == model.productInstance.id
    }
    
    void "Test Applications controller selected product method"() {
        
        given:
            createProduct()
            controller.params.productId = product.id
            controller.params.format = "json"
            request.addHeader "Accept", "text/json"
            
        when: 
            controller.selectedProduct()
            
        then:
            response.json.id == product.id
    }
    
    void "Test Applications controller free Trial method"() {
        
        given:
            createUser()
            controller.params.format = "json"
            request.addHeader "Accept", "text/json"
            
        when: 
            controller.params.productId = product.id
            controller.params.agree = 2
            controller.session.SPRING_SECURITY_CONTEXT = [authentication:[principal:[id: user.id]]]
            controller.springSecurityService = new Object()
            controller.springSecurityService.metaClass.currentUser = user
            controller.freeTrial()
            
        then: 
            response.redirectedUrl == '/applications/show'
    }
    
    void "Test Applications controller buy additional license method"() {
        
        given:
            createUser()
            controller.params.id = productInstance.id
            controller.params.quantity = (['1','2'] as String[])
            controller.params.additionalHostQuantity = (['1','2'] as String[])
            
        when:
            controller.buyAdditionalLicenseForInstanse()
        
        then:
            response.redirectedUrl == '/applications/index'
    }
    
    void "Test Applications controller buy License method"() {
        
        given:
            createUser()
            controller.params.format = "json"
            request.addHeader "Accept", "text/json"
            
        when:
            controller.applicationService = Mock(ApplicationService)
            controller.params.productId = product.id
            controller.session.SPRING_SECURITY_CONTEXT = [authentication:[principal:[id: user.id]]]
            controller.springSecurityService = new Object()
            controller.springSecurityService.metaClass.currentUser = user
            controller.params.quantity = (['1','2'] as String[])
            controller.params.productId = (['1','2'] as String[])
            controller.params.licenseExpiryMonth = 3
            controller.buyLicense()
            
        then:
            response.redirectedUrl == '/applications/index'
    }
    
    void "Test Applications controller renew license method"() {
        
        given:
            createUser()
            controller.params.id = productInstance.id
            controller.params.licenseExpiryMonth = 3
            
        when:
            controller.renewLicense()
            
        then:
            response.redirectedUrl == '/applications/index'
    }
}
