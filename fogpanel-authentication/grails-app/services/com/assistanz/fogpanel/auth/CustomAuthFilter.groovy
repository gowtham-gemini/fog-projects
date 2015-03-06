package com.assistanz.fogpanel.auth


import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpSession

import grails.converters.deep.JSON
import grails.plugin.springsecurity.web.authentication.RequestHolderAuthenticationFilter
import org.springframework.security.authentication.AuthenticationServiceException
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.util.TextEscapeUtils
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsWebRequest
import org.codehaus.groovy.grails.web.util.WebUtils

class CustomAuthFilter extends RequestHolderAuthenticationFilter {

    @Override
    Authentication attemptAuthentication(HttpServletRequest request, 
        HttpServletResponse response) throws AuthenticationException {
        
        println(" IN authentication Filter ")
        if (!request.post) {
            throw new AuthenticationServiceException(
				"Authentication method not supported: $request.method")
        }
        
        println("******request****"+request);
        println("******request****"+request.method);
        String username = (obtainUsername(request) ?: '').trim()
        String password = obtainPassword(request) ?: ''	
        
        println("*****username*****"+username);
        println("*****password*****"+password);
                
        println("2")
        def authentication = new CustomAuthentication(username, password)

        println("3")
        HttpSession session = request.getSession(false)
        if (session || getAllowSessionCreation()) {
            request.session[SPRING_SECURITY_LAST_USERNAME_KEY] =
            TextEscapeUtils.escapeEntities(username)
        }
        println("4")

        if(session == null) {
            GrailsWebRequest webUtils = WebUtils.retrieveGrailsWebRequest()
            def httpRequest = webUtils.getCurrentRequest()  
            session = httpRequest.getSession(false)
        }                        
                
        println("5")
        session.setAttribute("password", password);
                
        setDetails request, authentication
        
        println("6")

        return getAuthenticationManager().authenticate(authentication)
    }    
}
