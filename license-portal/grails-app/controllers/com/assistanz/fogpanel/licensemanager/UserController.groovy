package com.assistanz.fogpanel.licensemanager

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import java.security.MessageDigest
import java.sql.Timestamp
import com.assistanz.fogpanel.licensemanager.Role
import com.assistanz.fogpanel.licensemanager.User
import com.assistanz.fogpanel.licensemanager.UserRole

import grails.plugin.springsecurity.annotation.Secured
import grails.plugin.springsecurity.SpringSecurityUtils


@Secured(['permitAll'])
class UserController {

    static transactional = true
    def springSecurityService
    def userService
    def passwordEncoder
    
    @Secured(['permitAll'])
    def save() { 
        try {
            if(!params.validator) {
                def user = new User(params)
                user.registrationDate = new Date()
                if (!user.save()) {
                    def config = SpringSecurityUtils.securityConfig
                    String postUrl = "${request.contextPath}${config.apf.filterProcessesUrl}"
                    render(view: "/login/auth", model: [userInstance: user, postUrl: postUrl])               
                } else {
                    userService.register(user)
                    redirect controller: "home", action: "registrationSuccess"
                    //render(view: "successPage", model: [messageVerify: "Please verify you email to enable you account"]);    
                } 
            } else {
                throw new Exception("Signup Failed! Contact admin")
            }
        } catch (RuntimeException ex) {
            throw ex
        } catch (Exception ex) {
            throw ex
        }
    }
            
    @Secured(['permitAll'])
    def confirmAccount() {    
                        
        Calendar calendar = Calendar.getInstance();         
        User user = User.findByToken(params.id);    
        
        if(user) {
            if(calendar.getTime() <= user.tokenExpiryDate) {
                Date date = new Date()
                def time = new Timestamp(date.getTime())

                user.enabled = true
                user.status = User.Status.ACTIVE
                user.save(flush: true)
                
                flash.message = "Account Successfully Verified"
                redirect controller: "home", action: "confirmAccountSuccess"
            } else {
                flash.message = "Link Expired"  
                redirect controller: "home", action: "confirmAccountFailed"
            } 
        } else {
           flash.message = "Invalid Verification Link"
           redirect controller: "home", action: "confirmAccountFailed"
        }
    }
    
    
    @Secured(['ROLE_ADMIN', 'ROLE_SUPPORT'])
    def updateUser() { 
        def user = User.get(params.id)
        if (!userService.updateBasicInfo(user, params)) {
            def message = message(code: 'user.update.failed')
            render(view: "edit", model:[message: message, user: user, error: user.errors]); 
        } else {
            flash.message = "Profile updated successfully"
            redirect controller: "user", action: "show", id: params.id
        }
    }
    
    @Secured(['ROLE_ADMIN', 'ROLE_USER', 'ROLE_SUPPORT'])
    def updateProfileUser() { 
        
        def user = springSecurityService.currentUser
        
        if (!userService.updateBasicInfo(user, params)) {
            def message = message(code: 'user.update.failed')
            render(view: "edit", model:[message: message, user: user, error: user.errors]); 
        } else {
            flash.message = "Profile updated successfully"
            redirect controller: "user", action: "profile"
        }
    }
    
    @Secured(['ROLE_ADMIN', 'ROLE_USER', 'ROLE_SUPPORT'])
    def profile() {
        
        def user = springSecurityService.currentUser
                
        render(view: "profile", model:[user: user]);
        
    }
    
    @Secured(['ROLE_ADMIN', 'ROLE_USER', 'ROLE_SUPPORT'])
    def show(User user) {
        
        respond user, model:[user: user];
        
    }
    
    @Secured(['ROLE_ADMIN', 'ROLE_USER', 'ROLE_SUPPORT'])
    def editProfile() {
        def user = springSecurityService.currentUser
        render(view: "editProfile", model:[user: user])
    }
    
    @Secured(['ROLE_ADMIN', 'ROLE_USER', 'ROLE_SUPPORT'])
    def edit(User user) {
        respond user, model:[user: user];
    }
    
    @Secured(['ROLE_ADMIN'])
    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond User.list(params), model:[userCount: User.count()]
    }
    
    @Secured(['ROLE_ADMIN'])
    def resetPasswordForUser(User user) {
        user.password = params.newPassword
        user.save(flush:true);
        render(view: "resetPassword", model:[user: user, message: "Password Reset Success"]);
    }
    
    @Secured(['ROLE_ADMIN', 'ROLE_USER', 'ROLE_SUPPORT'])
    def resetPassword() {
        
        def user = springSecurityService.currentUser
        if(!passwordEncoder.isPasswordValid(user.password, params.oldPassword, null)) {
            render(view: "edit", model:[user: user, messagError: "Old Password Mismatch"]);
        } else {
            user.password = params.newPassword
            user.save(flush:true);
            render(view: "edit", model:[user: user, message: "Password Reset Success"]);
        }
    }
    
    @Secured(['permitAll'])
    def recoverPassword() {
        render(view: "recoverPassword");
    }
    
    @Secured(['permitAll'])
    def forgetPasswordSentMail() {
        def user = User.findByUsername(params.username)
        if(!user) {
            render(view: "recoverPassword",  model: [messageError: "No User Found"]);  
        } else {
            userService.sendForgetPasswordLink(user)
            render(view: "recoverPassword", model: [messageVerify: "Your password reset link has been sent. Please check your inbox."])
        }
    }
    
    @Secured(['permitAll'])
    def resetForgetPassword() {
        
        User user = User.findByToken(params.id);
        if(!user) {
            render(view: "resetPassword",  model: [messageError: "Invalid Link"]);  
        } else {
            render(view: "resetPassword", model: [user: user, token: params.id]);
        }
    }
    
    @Secured(['permitAll'])
    def changePassword() {
        
        def user = User.findByToken(params.token)
        user.password = params.password
        user.save(flush:true)
        flash.message = "Your password has been reset successfully"
        redirect(controller: "login", action: "auth", params: [messageSuccess: "Your password has been reset successfully."])    
    }
    
    @Secured(['permitAll'])
    def changeMyPassword() {
        def user = springSecurityService.currentUser
        respond user, model:[user: user];
    }
    
    @Secured(['ROLE_ADMIN', 'ROLE_USER', 'ROLE_SUPPORT'])
    def changeCurrentUserPassword() {
       def user = springSecurityService.currentUser
        if(!passwordEncoder.isPasswordValid(user.password, params.oldPassword, null)) {
            return render(view: "changeMyPassword", model:[user: user, messageError: "Old Password Mismatch"]);
        } 
        
        if (params.confirmPassword != params.password) {
            return render(view: "changeMyPassword", model:[user: user, messageError: "Passwords don't match"]);
        }
        
        user.password = params.confirmPassword
        if (user.save(flush:true)) {
            flash.message = "Password Changed successfully."
            redirect(controller: "user", action: "profile")
        }
        
        return render(view: "changeMyPassword", model:[user: user, messageError: "Error while processing"]);

    }    
}
