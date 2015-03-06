package com.assistanz.fogpanel.auth

import com.assistanz.cloud.config.ConfigLoader
import com.assistanz.fogpanel.Account
import com.assistanz.fogpanel.AccountClosedException
import com.assistanz.fogpanel.IPLockedException
import com.assistanz.fogpanel.OSAccountNotFound
import com.assistanz.fogpanel.OSDomainUserNotFound
import com.assistanz.fogpanel.User
import org.springframework.context.ApplicationListener
import org.springframework.security.authentication.event.AuthenticationSuccessEvent
import com.assistanz.fogpanel.UserEvent
import com.assistanz.openstack.OpenStackServer
import com.assistanz.fogpanel.User
import com.assistanz.fogpanel.EventLogIpAddress
import java.util.Date
import grails.converters.deep.JSON
import grails.plugin.springsecurity.userdetails.GrailsUser
import grails.plugin.springsecurity.userdetails.GrailsUserDetailsService
import grails.plugin.springsecurity.SpringSecurityUtils
import org.springframework.security.core.authority.GrantedAuthorityImpl
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.core.AuthenticationException
import org.springframework.security.authentication.BadCredentialsException
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsWebRequest
import org.codehaus.groovy.grails.web.util.WebUtils
import javax.servlet.http.HttpSession
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import org.codehaus.groovy.grails.commons.ApplicationHolder

import org.springframework.security.core.Authentication
import org.springframework.security.authentication.dao.SaltSource
import org.springframework.security.authentication.encoding.PasswordEncoder
import org.springframework.security.core.userdetails.UserDetailsChecker
import org.springframework.security.authentication.AuthenticationProvider
import org.openstack4j.api.OSClient;
import com.assistanz.fogpanel.Domain;
import com.assistanz.fogpanel.Config

class CustomAuthProvider implements AuthenticationProvider {

    
    static final List NO_ROLES = [new GrantedAuthorityImpl(SpringSecurityUtils.NO_ROLE)]
    PasswordEncoder passwordEncoder
    SaltSource saltSource
    UserDetailsChecker preAuthenticationChecks
    UserDetailsChecker postAuthenticationChecks

    Authentication authenticate(Authentication auth) throws AuthenticationException {
        
        
        CustomAuthentication authentication = auth
        GrailsWebRequest webUtils = WebUtils.retrieveGrailsWebRequest()
        def request = webUtils.getCurrentRequest()  

        String password = authentication.credentials
        String username = authentication.name

        HttpSession session = request.getSession(false)
        String domainName = (String)session.getAttribute("domainName");
        
        GrailsUser userDetails
        def authorities
        User user
        
        if(domainName != null && domainName != "" 
            && username != "admin" && password != "password") {
                
            def domain = Domain.findByName(domainName);
            println("domain null"+(domain == null))
            
            // use withTransaction to avoid lazy loading exceptions
            User.withTransaction { status ->
                
                user = User.findWhere(username: username, domain: domain);
                
                if (!user) {
                    
                    User.withTransaction { status1 ->
                        def dbUser = User.findByUsername(username);
                        if(!dbUser) {
                            // TODO customize 'springSecurity.errors.login.fail' i18n message in app's messages.properties with org name
                            println "User not found: $username "
                            throw new UsernameNotFoundException('User not found', username)
                        } else {
                            
                            throw new OSDomainUserNotFound(domainName);
                        }
                    }
                }
                authorities = user.authorities.collect { new GrantedAuthorityImpl(it.authority) }
                authorities = authorities ?: GormUserDetailsService.NO_ROLES
                
                userDetails = new GrailsUser(user.username, user.password,
                    user.enabled, !user.accountExpired, !user.passwordExpired,
                    !user.accountLocked, authorities, user.id)
                
            }
            
        } else {
            // use withTransaction to avoid lazy loading exceptions
            User.withTransaction { status ->
                user = User.findByUsername(username)

                if (!user) {
                    // TODO customize 'springSecurity.errors.login.fail' i18n message in app's messages.properties with org name
                    println "User not found: $username "
                    throw new UsernameNotFoundException('User not found', username)
                }

                authorities = user.authorities.collect { new GrantedAuthorityImpl(it.authority) }
                authorities = authorities ?: GormUserDetailsService.NO_ROLES

                userDetails = new GrailsUser(user.username, user.password,
                    user.enabled, !user.accountExpired, !user.passwordExpired,
                    !user.accountLocked, authorities, user.id)
            }
                
        } 
        
        preAuthenticationChecks.check userDetails
        additionalAuthenticationChecks userDetails, authentication,user.uuid
        postAuthenticationChecks.check userDetails

        def result = new CustomAuthentication(userDetails,
            authentication.credentials, authorities)
        result.details = authentication.details
        result
                
    }

    protected void additionalAuthenticationChecks(GrailsUser userDetails,
        CustomAuthentication authentication, String uuid) throws AuthenticationException {
                          
        GrailsWebRequest webUtils = WebUtils.retrieveGrailsWebRequest()
        def request = webUtils.getCurrentRequest()          

        def salt = saltSource.getSalt(userDetails)

        if (authentication.credentials == null) {
            println 'Authentication failed: no credentials provided'
            throw new BadCredentialsException('Bad credentials', userDetails)
        }

        String presentedPassword = authentication.credentials
        if (!passwordEncoder.isPasswordValid(userDetails.password, presentedPassword, salt)) {
            println 'Authentication failed: password does not match stored value'
                
            throw new BadCredentialsException('Bad credentials', userDetails)
        }
        
        HttpSession session = request.getSession(false)
        String domainName = (String)session.getAttribute("domainName");
        String password = authentication.credentials;
        String username = authentication.name
               
        println("logged in domain"+domainName)
        if(domainName != null && domainName != "" ) {
                    
            if(username != "admin" && password != "password") {
                this.osApiCheckByUserNameAndPassword(username , password, domainName, uuid);
            }
        } 
                
        // use withTransaction to avoid lazy loading exceptions
        User.withTransaction { status ->
            User user = User.findByUsername(username)

            if (user) {
                
                def account = user.account
                if(account.status.name() == "CLOSED") {
                    throw new AccountClosedException('Account Closed')  
                } 
            }   
        }
        
                
        
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
            
    }

    boolean supports(Class<? extends Object> authenticationClass) {
        CustomAuthentication.isAssignableFrom authenticationClass
    }
    def getIPAddress(request) {
                    
        for (String header : HEADERS_TO_TRY) {
            String ip = request.getHeader(header);
            if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
                return ip;
            }
        }
        return request.getRemoteAddr();
        
    } 
        
    
    // background check with api using admin credentials
    def osApiCheckByUserNameAndPassword(String username, String password,String domainName, String uuid) {
        
        try {
            ConfigLoader configLoader = ConfigLoader.getInstance();
           
            Properties props = configLoader.getProperties();
            OpenStackServer server = new OpenStackServer(props.get(Config.OPENSTACK_ENDPOINT_URL), username, password, domainName);
            OSClient os = server.authenticate();
            System.out.println("os check null"+(os==null));
            
        } catch (AuthenticationException ex) {
            System.out.println(ex);
            throw new OSAccountNotFound("Account not found in OpenStack");
        } catch (Exception e) {
            System.out.println("inside Exception")
           // e.printStackTrace(System.err);
            throw new OSAccountNotFound("Account not found in OpenStack");
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
}
