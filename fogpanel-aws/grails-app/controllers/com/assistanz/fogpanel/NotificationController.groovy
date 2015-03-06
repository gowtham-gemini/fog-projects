package com.assistanz.fogpanel

import org.springframework.security.access.annotation.Secured
import grails.transaction.Transactional

@Transactional
class NotificationController {
    
    NotificationTopicService notificationTopicService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    
    @Secured('permitAll')
    def unsubscribe() { 
               
        Console.print(params);
        
        def logoUrl = Config.findByName(Config.ORGANISATION_BILLING_LOGO);        
        if(params.force == "1") {
             def response = "SUCCESS" //notificationTopicService.unsubscribe(params.id)
             if(response == "SUCCESS") {
                 flash.message = "You have successfully unsubscribed from our email list"
             } else if(response == "NO_SUBSCRIBER") {
                 flash.message = "Invalid Token"
             }
             render(view: "unsubscribe" , model: [logoUrl: logoUrl.value, token:params.id]) 
        } else {
            render(view: "unsubscrideConfirm" , model: [logoUrl: logoUrl.value, token:params.id]) 
        }       
     
    }
    
    
    
    
}
