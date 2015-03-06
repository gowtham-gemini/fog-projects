package com.assistanz.fogpanel

import org.springframework.context.ApplicationListener
import org.springframework.security.authentication.event.AuthenticationSuccessEvent
import com.assistanz.fogpanel.UserEvent
import com.assistanz.fogpanel.User
import com.assistanz.fogpanel.EventLogIpAddress
import java.util.Date
import grails.converters.deep.JSON

/**
 *
 * @author Gowtham
 */
class LoginEventListener implements ApplicationListener<AuthenticationSuccessEvent> {
        
    void onApplicationEvent(AuthenticationSuccessEvent event) {
             
        def events = event as JSON
        def requestBody = events.toString()
        def requestData = JSON.parse(requestBody)  
        Date date = new Date()
        EventLogIpAddress.withTransaction {
            EventLogIpAddress eventLogIpAddress = EventLogIpAddress.findWhere(ipAddress:InetAddress.getLocalHost().getHostAddress(),
                    user:User.findByUsername(requestData.source.principal.username.toString()))
            if (!eventLogIpAddress) {
                eventLogIpAddress = new EventLogIpAddress()
                eventLogIpAddress.ipAddress = InetAddress.getLocalHost().getHostAddress()
                eventLogIpAddress.failureCount = 0
                eventLogIpAddress.successCount = 1
                eventLogIpAddress.overAllCount = 1
                eventLogIpAddress.user = User.findByUsername(requestData.source.principal.username.toString())
                eventLogIpAddress.save(flush: true)
            } else if(eventLogIpAddress) {
                eventLogIpAddress.failureCount = eventLogIpAddress.failureCount
                eventLogIpAddress.successCount = eventLogIpAddress.successCount + 1
                eventLogIpAddress.overAllCount = eventLogIpAddress.overAllCount + 1
                eventLogIpAddress.save(flush: true)
            }    
        }
        UserEvent.withTransaction {
            UserEvent userEvent = new UserEvent()
            userEvent.ipAddress = InetAddress.getLocalHost().getHostAddress()
            userEvent.eventType = "login success"
            userEvent.date = date.toString()
            userEvent.failedCount = 0
            userEvent.user = User.findByUsername(requestData.source.principal.username.toString())
            userEvent.eventLogIpAddress = EventLogIpAddress.findWhere(ipAddress:InetAddress.getLocalHost().getHostAddress(),
                    user:User.findByUsername(requestData.source.principal.username.toString()))
            userEvent.save(flush: true)
            if (!userEvent.save()) {
                    userEvent.errors.allErrors.each { Console.print(it) }
            }
        }
        User.withTransaction {
            User user = User.findByUsername(requestData.source.principal.username.toString())
            user.failureCount = 0
            user.lockTime = null
            user.save(flush: true)
        }
        
        
        
//        loginEventService.add(requestData.source.principal.username.toString(), "login success")
   }
    
	
}

