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
	                                     HttpServletResponse response)
			throws AuthenticationException {

		if (!request.post) {
			throw new AuthenticationServiceException(
				"Authentication method not supported: $request.method")
		}
                
		String username = (obtainUsername(request) ?: '').trim()
		String password = obtainPassword(request) ?: ''
		String domainName = request.getParameter('domainName')
                
                def authentication = new CustomAuthentication(username, password)

		HttpSession session = request.getSession(false)
		if (session || getAllowSessionCreation()) {
			request.session[SPRING_SECURITY_LAST_USERNAME_KEY] =
				TextEscapeUtils.escapeEntities(username)
		}

                if(session == null) {
                    GrailsWebRequest webUtils = WebUtils.retrieveGrailsWebRequest()
                    def httpRequest = webUtils.getCurrentRequest()  
                    session = httpRequest.getSession(false)
                }
                
                session.setAttribute("domainName", domainName);
                session.setAttribute("password", password);
                
		setDetails request, authentication

		return getAuthenticationManager().authenticate(authentication)
	}
}
