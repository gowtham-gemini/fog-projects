package com.assistanz.fogpanel.licensemanager

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(UserController)
@Mock([ProductInstance, ValidationLog, Product, User, Release, ApplicationService])
class UserControllerSpec extends Specification {
    
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
            user.lastLogin = date.getTime()
            user.ipAddress = "192.168.2.23"
            user.registrationDate = date.getTime()
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
        }
    }
    
//    void "Test User controller confirm Account method"() {
//        given:
//            createUser()
//            controller.params.format = "json"
//            request.addHeader "Accept", "text/json"
//            
//        when: 
//            controller.params.id = user.id
//            controller.confirmAccount()
//            
//        then:
//            response.redirectedUrl == '/home/confirmAccountSuccess'
//    }
    
    void "Test User controller profile method"() {
        
        given:
            createUser()
            
        when: 
            controller.session.SPRING_SECURITY_CONTEXT = [authentication:[principal:[id: user.id]]]
            controller.springSecurityService = new Object()
            controller.springSecurityService.metaClass.currentUser = user
            controller.profile()
            
        then:
            view == '/user/profile'
            model.user.id == user.id
    }
    
    void "Test User controller show method"() {
        given:
            createUser()
            controller.params.format = "json"
            request.addHeader "Accept", "text/json"
            
        when: 
            controller.show(user)
            
        then:
            response.json.id == user.id
    }
    
    void "Test User controller edit profile method"() {
        given:
            createUser()
            
        when: 
            controller.session.SPRING_SECURITY_CONTEXT = [authentication:[principal:[id: user.id]]]
            controller.springSecurityService = new Object()
            controller.springSecurityService.metaClass.currentUser = user
            controller.editProfile()
            
        then:
            view == '/user/editProfile'
            model.user.id == user.id
    }
    
    void "Test User controller edit method"() {
        given:
            createUser()
            controller.params.format = "json"
            request.addHeader "Accept", "text/json"
            
        when: 
            controller.edit(user)
            
        then:
            response.json.id == user.id
    }
    
    void "Test User controller reset password for user method"() {
        given:
            createUser()
            controller.params.format = "json"
            request.addHeader "Accept", "text/json"
            
        when: 
            controller.params.newPassword = "MYPASSWORD"
            controller.resetPasswordForUser(user)
            
        then:
            view == '/user/resetPassword'
            model.user.id == user.id
    }
    
    void "Test User controller recover password method"() {
        given:
            createUser()
            controller.params.format = "json"
            request.addHeader "Accept", "text/json"
            
        when: 
            controller.recoverPassword()
            
        then:
            view == '/user/recoverPassword'
    }
    
    
//    void "Test User controller reset password method"() {
//        given:
//            createUser()
//            controller.params.format = "json"
//            request.addHeader "Accept", "text/json"
//            
//        when:
//            controller.session.SPRING_SECURITY_CONTEXT = [authentication:[principal:[id: user.id]]]
//            controller.springSecurityService = new Object()
//            controller.springSecurityService.metaClass.currentUser = user
//            controller.params.oldPassword = "ENCODED_PASSWORD"
//            controller.params.newPassword = "MYPASSWORD"
//            controller.resetPassword()
//            
//        then:
//            view == '/user/edit'
//            model.user.id == user.id
//    }  

//    void "Test User controller reset forget password method"() {
//        given:
//            createUser()
//            controller.params.format = "json"
//            request.addHeader "Accept", "text/json"
//            
//        when: 
//            controller.params.id = user.id
//            controller.resetForgetPassword()
//            
//        then:
//            view == '/user/resetPassword'
//            //model.user.id == user.id
//    }

    void "Test User controller change my password method"() {
        given:
            createUser()
            controller.params.format = "json"
            request.addHeader "Accept", "text/json"
            
        when: 
            controller.session.SPRING_SECURITY_CONTEXT = [authentication:[principal:[id: user.id]]]
            controller.springSecurityService = new Object()
            controller.springSecurityService.metaClass.currentUser = user
            controller.changeMyPassword()
            
        then:
            response.json.id == user.id
    }
}
