package com.assistanz.fogpanel.licensemanager

import javax.servlet.http.HttpServletResponse
import org.springframework.security.access.annotation.Secured
import grails.plugin.springsecurity.SpringSecurityUtils

@Secured('permitAll')
class LogoutController {

    /**
     * Dependency injection for the springSecurityService.
     */
    def springSecurityService
    
    /**
     * Index action. Redirects to the Spring security logout uri.
     */
    def index() {

//            if (!request.post && SpringSecurityUtils.getSecurityConfig().logout.postOnly) {
//                    response.sendError HttpServletResponse.SC_METHOD_NOT_ALLOWED // 405
//                    return
//            }

            // TODO put any pre-logout code here
//            forward uri: SpringSecurityUtils.securityConfig.logout.filterProcessesUrl // '/j_spring_security_logout'

            redirect uri: SpringSecurityUtils.securityConfig.logout.filterProcessesUrl
        
    }
}
