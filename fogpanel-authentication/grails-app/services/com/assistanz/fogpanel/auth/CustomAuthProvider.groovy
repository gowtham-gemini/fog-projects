package com.assistanz.fogpanel.auth

import com.assistanz.cloud.config.ConfigLoader
import com.assistanz.fogpanel.Account
import com.assistanz.fogpanel.AccountClosedException
import com.assistanz.fogpanel.IPLockedException
import com.assistanz.fogpanel.OSAccountNotFound
import com.assistanz.fogpanel.OSDomainUserNotFound
import com.assistanz.fogpanel.Role
import com.assistanz.fogpanel.User
import org.springframework.context.ApplicationListener
import org.springframework.security.authentication.event.AuthenticationSuccessEvent
import com.assistanz.fogpanel.UserEvent
import com.assistanz.fogpanel.UserRole
import com.assistanz.fogpanel.ldap.MyUserDetails
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


import com.assistanz.fogpanel.Config

class CustomAuthProvider implements AuthenticationProvider {

    static final List NO_ROLES = [new GrantedAuthorityImpl(SpringSecurityUtils.NO_ROLE)]

    PasswordEncoder passwordEncoder
    SaltSource saltSource    

    /**
     * authenticate
     * 
     * When the login is requested it hits here for the cutom authentication and on success anauthentication object is send back.
     * When the authentication fail it thros an AuthenticationException.
     * returns  Authentication.
     */   
    
    Authentication authenticate(Authentication auth) throws AuthenticationException {
        
        
        println(" Inside cutom authentication ")
        
        CustomAuthentication authentication = auth
        GrailsWebRequest webUtils = WebUtils.retrieveGrailsWebRequest()
        def request = webUtils.getCurrentRequest()  

        String password = authentication.credentials
        String username = authentication.name

        HttpSession session = request.getSession(false)
        GrailsUser userDetails
        def authorities
        User user
        
        User.withTransaction { status ->           

            if(username == "admin" && password == "password"  ){
                user = User.findByUsername(username)            
            }                      
            
            //create user 
            // use withTransaction to avoid lazy loading exceptions        
            authorities = user ?.authorities.collect { new GrantedAuthorityImpl(it.authority) }
            //        authorities = authorities ?: GormUserDetailsService.NO_ROLES                                        
        }
        
        userDetails = new GrailsUser(user.username, "user.password",

            user.enabled, !user.accountExpired, !user.passwordExpired,
            !user.accountLocked, authorities, user.id)      

        def result = new CustomAuthentication(userDetails,
            authentication.credentials, authorities)
        result.details = authentication.details
        
        println(result)
        
        result
        
    }   
    
    boolean supports(Class<? extends Object> authenticationClass) {
        CustomAuthentication.isAssignableFrom authenticationClass
    }
}
