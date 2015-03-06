package com.assistanz.fogpanel

import grails.plugin.springsecurity.SpringSecurityUtils
import org.codehaus.groovy.grails.commons.ApplicationHolder
import org.springframework.security.core.context.SecurityContextHolder as SCH

class LogoutController {

	/**
	 * Index action. Redirects to the Spring security logout uri.
	 */
	def index = {
		// TODO put any pre-logout code here
                println("****** Logout successfull ********");
                SCH.getContext().setAuthentication(null);
                def faasUrl = ApplicationHolder.getApplication().config.faas.applicationUrl
//		redirect uri: SpringSecurityUtils.securityConfig.logout.filterProcessesUrl // '/j_spring_security_logout'
		redirect uri: faasUrl+"/logout/index" // '/j_spring_security_logout'
	}
}
