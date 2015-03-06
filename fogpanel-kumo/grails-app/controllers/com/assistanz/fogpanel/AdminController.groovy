package com.assistanz.fogpanel

import grails.plugins.springsecurity.Secured
import grails.plugins.springsecurity.SpringSecurityService
import grails.validation.Validateable

class AdminController {
	
    SpringSecurityService springSecurityService

    @Secured(['ROLE_ADMIN'])
    def index() { 
//        def user = User.get(springSecurityService.principal.id)
//        if (!user.uuid) {
//            redirect action: "settings"
//        }
    }
    
    //@Secured(['ROLE_ADMIN'])
    def settings() {
        [settings: new SettingsCommand(params)]
    }
    
    //@Secured(['ROLE_ADMIN'])
    def save() {
        SettingsCommand settings = new SettingsCommand(params)
        def user = User.get(springSecurityService.principal.id)
        if (!settings.hasErrors()) {
            user.uuid = settings.uuid
            user.apiKey = settings.apiKey
            user.secretKey = settings.secretKey
			
            user.save(flush: true)
        } else {
            render(view: "settings", model: [settings: settings])
            return
        }
		
        flash.message = message(code: 'default.created.message', args: [message(code: 'config.label', default: 'Config'), settings.uuid])
        redirect(action: "index")
    }
}

@Validateable
class SettingsCommand {
	
    String uuid
    String apiKey
    String secretKey
	
    String action
    String controller
    String save
	
    static constraints = {
        uuid (nullable: false, blank: false)
        apiKey (nullable: false, blank: false)
        secretKey (nullable: false, blank: false)
		
        action (blank: true, nullable: true)
        controller (blank: true, nullable: true)
        save (blank: true, nullable: true)
    }
}
