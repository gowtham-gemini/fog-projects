package com.assistanz.fogpanel.licensemanager

import org.springframework.context.ApplicationListener
import org.springframework.security.authentication.event.AuthenticationSuccessEvent
import com.assistanz.fogpanel.licensemanager.User
import java.util.Date
import grails.converters.deep.JSON

class LoginEventListener implements ApplicationListener<AuthenticationSuccessEvent> {
        
    void onApplicationEvent(AuthenticationSuccessEvent event) {

        def events = event as JSON
        def requestBody = events.toString()
        def requestData = JSON.parse(requestBody)  
        Date date = new Date()
        User.withTransaction {
            User user = User.findByUsername(requestData.source.principal.username.toString())
            user.lastLogin = new Date();
            user.ipAddress = InetAddress.getLocalHost().getHostAddress()
            user.save(flush: true)
            if (!user.save()) {
                user.errors.allErrors.each { Console.print(it) }
            }
        }
   }
}

