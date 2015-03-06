package com.assistanz.fogpanel

import grails.plugin.springsecurity.annotation.Secured
import grails.plugin.springsecurity.SpringSecurityService
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
//    def settings() {
//        [settings: new SettingsCommand(params)]
//    }
//    
//    //@Secured(['ROLE_ADMIN'])
//    def save() {
//        SettingsCommand settings = new SettingsCommand(params)
//        def user = User.get(springSecurityService.principal.id)
//        if (!settings.hasErrors()) {
//            user.uuid = settings.uuid
//            user.apiKey = settings.apiKey
//            user.secretKey = settings.secretKey
//			
//            user.save(flush: true)
//        } else {
//            render(view: "settings", model: [settings: settings])
//            return
//        }
//		
//        flash.message = message(code: 'default.created.message', args: [message(code: 'config.label', default: 'Config'), settings.uuid])
//        redirect(action: "index")
//    }
}
