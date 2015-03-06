package com.assistanz.fogpanel.licensemanager

import grails.plugin.springsecurity.annotation.Secured

class HomeController {
    
    def springSecurityService;
    
    @Secured(['ROLE_ADMIN', 'ROLE_USER', 'ROLE_SUPPORT'])
    def index() { 
        if (springSecurityService.currentUser) {
            redirect controller: "home", action: "dashboard"
        }
    }
    
    @Secured(['ROLE_ADMIN', 'ROLE_USER', 'ROLE_SUPPORT'])
    def dashboard() { 
        render (view: "dashboard", model: [menu: true])
    }
    
    @Secured(['permitAll'])
    def registrationSuccess() {
        
    }
    
    @Secured(['permitAll'])
    def privacy() {
        
    }
    
    @Secured(['permitAll'])
    def confirmAccountSuccess() {
        
    }
    
    @Secured(['permitAll'])
    def confirmAccountFailed() {
        
    }
}
