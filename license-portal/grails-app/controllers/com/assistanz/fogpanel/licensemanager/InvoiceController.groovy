package com.assistanz.fogpanel.licensemanager

import grails.plugin.springsecurity.annotation.Secured

class InvoiceController {
    
    def springSecurityService
        
    @Secured(['ROLE_ADMIN', 'ROLE_USER', 'ROLE_SUPPORT'])
    def index() {
      

        
        
    }
    
}
