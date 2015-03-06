package com.assistanz.fogpanel

import grails.plugins.springsecurity.Secured
import grails.plugins.springsecurity.SpringSecurityService
import grails.validation.Validateable

class UserController {
    
    SpringSecurityService springSecurityService
    
    @Secured(['ROLE_FREE_USER', 'ROLE_PAID_USER'])
    def index() { 
        
    }
}
