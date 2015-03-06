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
		String csLoginResponse = request.getParameter('csLoginResponse')
		String domainName = request.getParameter('domainName')
                
                def authentication = new CustomAuthentication(username, password)

		HttpSession session = request.getSession(false)
		if (session || getAllowSessionCreation()) {
			request.session[SPRING_SECURITY_LAST_USERNAME_KEY] =
				TextEscapeUtils.escapeEntities(username)
		}
                
                println("csLoginResponse  "+csLoginResponse)
                session.setAttribute("csLoginResponse", csLoginResponse);
                session.setAttribute("domainName", domainName);
                
		setDetails request, authentication

		return getAuthenticationManager().authenticate(authentication)
	}
}
