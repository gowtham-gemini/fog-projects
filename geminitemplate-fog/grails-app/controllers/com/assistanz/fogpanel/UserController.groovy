package com.assistanz.fogpanel

import grails.plugin.springsecurity.annotation.Secured
import grails.plugin.springsecurity.SpringSecurityService
import grails.validation.Validateable

class UserController {
    
    SpringSecurityService springSecurityService
    
    @Secured(['ROLE_FREE_USER', 'ROLE_PAID_USER'])
    def index() { 
        
    }
}
