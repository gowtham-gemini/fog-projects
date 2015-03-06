package com.assistanz.fogpanel.auth

import com.assistanz.fogpanel.Account
import com.assistanz.fogpanel.AccountClosedException
import com.assistanz.fogpanel.IPLockedException
import com.assistanz.fogpanel.User
import org.springframework.context.ApplicationListener
import org.springframework.security.authentication.event.AuthenticationSuccessEvent
import com.assistanz.fogpanel.UserEvent
import com.assistanz.fogpanel.User
import com.assistanz.fogpanel.EventLogIpAddress
import java.util.Date
import grails.converters.deep.JSON
import org.codehaus.groovy.grails.plugins.springsecurity.GrailsUser
import org.codehaus.groovy.grails.plugins.springsecurity.GrailsUserDetailsService
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import org.springframework.security.core.authority.GrantedAuthorityImpl
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.core.AuthenticationException
import org.springframework.security.authentication.BadCredentialsException
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsWebRequest
import org.codehaus.groovy.grails.web.util.WebUtils
    
class CustomAuthService implements GrailsUserDetailsService {
    
    static final List NO_ROLES = [new GrantedAuthorityImpl(SpringSecurityUtils.NO_ROLE)]
 
    
    UserDetails loadUserByUsername(String username, boolean loadRoles) throws UsernameNotFoundException, AuthenticationException {
        return loadUserByUsername(username)
    }
    
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, AuthenticationException {
     
        User.withTransaction { status ->
            User user = User.findByUsername(username)
            if (!user) {
                throw new UsernameNotFoundException('User not found', username)
            } else {
                def account = user.account
                if(account.status.name() == "CLOSED") {
                    throw new AccountClosedException('Account Closed')  
                } 
            }
            
            GrailsWebRequest webUtils = WebUtils.retrieveGrailsWebRequest()
            def request = webUtils.getCurrentRequest()
            
            String ipAddress = getIPAddress(request)
            
            Date date = new Date()
            EventLogIpAddress.withTransaction {
                EventLogIpAddress eventLogIpAddress = EventLogIpAddress.findWhere(ipAddress:ipAddress,
                        user:User.findByUsername(username))
                    
                if(eventLogIpAddress && eventLogIpAddress.isLocked == true) {
                     throw new IPLockedException('Your IP locked')  
                }
                if (!eventLogIpAddress) {
                    eventLogIpAddress = new EventLogIpAddress()
                    eventLogIpAddress.ipAddress = ipAddress
                    eventLogIpAddress.failureCount = 0
                    eventLogIpAddress.successCount = 1
                    eventLogIpAddress.overAllCount = 1
                    eventLogIpAddress.overAllFailureCount = 0
                    eventLogIpAddress.isLocked = false
                    eventLogIpAddress.user = User.findByUsername(username)
                    eventLogIpAddress.save(flush: true)
                } else if(eventLogIpAddress) {
                    eventLogIpAddress.failureCount = eventLogIpAddress.failureCount
                    eventLogIpAddress.overAllFailureCount = eventLogIpAddress.overAllFailureCount
                    eventLogIpAddress.successCount = eventLogIpAddress.successCount + 1
                    eventLogIpAddress.overAllCount = eventLogIpAddress.successCount + eventLogIpAddress.overAllFailureCount
                    eventLogIpAddress.lockTime = null;
                    eventLogIpAddress.save(flush: true)
                }    
            }
            UserEvent.withTransaction {
                UserEvent userEvent = new UserEvent()
                userEvent.ipAddress = ipAddress
                userEvent.eventType = "login success"
                userEvent.date = date.toString()
                userEvent.failedCount = 0
                userEvent.user = User.findByUsername(username)
                userEvent.eventLogIpAddress = EventLogIpAddress.findWhere(ipAddress:ipAddress,
                        user:User.findByUsername(username))
                userEvent.save(flush: true)
                if (!userEvent.save()) {
                        userEvent.errors.allErrors.each { Console.print(it) }
                }
            }
            User.withTransaction {
                User eventuser = User.findByUsername(username)
                eventuser.failureCount = 0
                eventuser.lockTime = null
                eventuser.save(flush: true)
            }
            
            
            def authorities = user.authorities.collect {new GrantedAuthorityImpl(it.authority)}
             
            return new GrailsUser(user.username, user.password, user.enabled, !user.accountExpired,
                !user.passwordExpired, !user.accountLocked, authorities ?: NO_ROLES, user.id)
        }
    }
    
    
    private static final String[] HEADERS_TO_TRY = [ 
                "X-Forwarded-For", 
                "Proxy-Client-IP",
                "WL-Proxy-Client-IP",
                "HTTP_X_FORWARDED_FOR",
                "HTTP_X_FORWARDED",
                "HTTP_X_CLUSTER_CLIENT_IP",
                "HTTP_CLIENT_IP",
                "HTTP_FORWARDED_FOR",
                "HTTP_FORWARDED",
                "HTTP_VIA",
                "REMOTE_ADDR" ];
    
        def getIPAddress(request) {
                    
            for (String header : HEADERS_TO_TRY) {
                String ip = request.getHeader(header);
                if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
                    return ip;
                }
            }
            return request.getRemoteAddr();
        
        } 
    
}
