package com.assistanz.app

//import grails.plugin.springsecurity.annotation.Secured
import grails.converters.deep.JSON
import grails.plugins.springsecurity.Secured
import com.assistanz.app.SecAppUser;

class SensitiveContentController {

    @Secured(['ROLE_USER','ROLE_ADMIN'])
    def index() {
        redirect(uri:'/index') 
    }
  
    def resetPassword() {
        println("resetting password ***")
        render view: "/login/resetPassword"
    }
    def savePassword() {
      
        println("params"+params);
        def newPassword = params?.newPassword
        def confirPasword = params?.confirmPassword
        def userId = params?.user.id
        
        if(newPassword == "" || confirPasword == "" || newPassword == " " || confirPasword == " ") {              
            flash.message = message(code: 'common.password.empty.message')
            redirect action:"resetPassword", method:"GET"
        } else if(newPassword != confirPasword) {            
            flash.message = message(code: 'common.password.mismatch.message')
            redirect action:"resetPassword", method:"GET"
        } else { 
            println("password saved")
            SecAppUser secAppUser = SecAppUser.findById(userId)
            secAppUser.password = newPassword
            secAppUser.save(flush: true)
            
            flash.message = message(code: 'common.password.saved.message')
            redirect(uri:'/index') 
        }
      
    }
}
