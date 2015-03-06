package com.assistanz.fogpanel

import grails.plugin.springsecurity.annotation.Secured
import com.auth.uitls.LdapUtils
import grails.plugin.springsecurity.SpringSecurityService
import grails.validation.Validateable
import sun.misc.BASE64Decoder

class UserController {
    
    SpringSecurityService springSecurityService
    LdapUtils ldapUtils    
    UserService userService
    GroupService  groupService 
    ProjectService projectService
    
    def index() {         
     
    }
}
