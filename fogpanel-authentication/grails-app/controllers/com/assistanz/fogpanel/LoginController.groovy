package com.assistanz.fogpanel

import grails.converters.JSON
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpSession
import grails.plugin.springsecurity.SpringSecurityUtils
import grails.plugin.springsecurity.userdetails.GrailsUser
import com.assistanz.fogpanel.UserEvent
import com.assistanz.fogpanel.auth.CustomAuthentication
import com.assistanz.fogpanel.EventLogIpAddress
import com.assistanz.fogpanel.User
import java.util.Date;
import javax.servlet.http.HttpServletRequest
import java.sql.Timestamp;
import java.text.DateFormat;    
import java.text.SimpleDateFormat;
import org.springframework.security.authentication.AccountExpiredException
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.CredentialsExpiredException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.LockedException
import org.springframework.security.core.context.SecurityContextHolder as SCH
import org.springframework.security.web.WebAttributes
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class LoginController {

	/**
	 * Dependency injection for the authenticationTrustResolver.
	 */
	def authenticationTrustResolver

	/**
	 * Dependency injection for the springSecurityService.
	 */
	def springSecurityService
        NotificationService notificationService
	/**
	 * Default action; redirects to 'defaultTargetUrl' if logged in, /login/auth otherwise.
	 */
	def index = {
                
                println("SpringSecurityUtils.securityConfig.successHandler.defaultTargetUrl");
                println(""+SpringSecurityUtils.securityConfig.successHandler.defaultTargetUrl);
                if (springSecurityService.isLoggedIn()) {
                        redirect uri: SpringSecurityUtils.securityConfig.successHandler.defaultTargetUrl
                }
                else {
                        redirect action: 'auth', params: params                        
                }
	}
        
	/**
	 * Show the login page.
	 */
	def auth = {
            println("***** inside auth page*****8");
                Config trialEnabled =  Config.findByName(Config.SIGNUP_TYPE_TRIAL_ENABLED);
                def logoUrl = Config.findByName(Config.ORGANISATION_BILLING_LOGO);
                def bgnImageUrl = Config.findByName(Config.ORGANISATION_BACKGROUND_IMG_URL);
		def config = SpringSecurityUtils.securityConfig            
		if (springSecurityService.isLoggedIn()) {
                    redirect uri: config.successHandler.defaultTargetUrl
                    return
                }                       
		String view = 'auth'
                
                def fogInstanceId =  Config.findByName(Config.FOG_INSTANCE_ID).value;
                def licenseeEmail=  Config.findByName(Config.LICENSEE_EMAIL).value;
                        
//                if(fogInstanceId == "" || licenseeEmail == "") {
//                    
//                    String postUrl = "${request.contextPath}"
//            
//                    render view: "licenseMissing",model: [logoUrl: logoUrl.value, postUrl: postUrl+"/config/saveLicenseInfo"]
//                } else {

                    String postUrl = "${request.contextPath}${config.apf.filterProcessesUrl}"
                    def language;
                    if(params.lang) {
                      language = params.lang
                    } else {
                        language = "en"
                    }
                    session['org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE'] = new Locale(language, language) 
                    
                    def enableSignup = "true";
                    
                    def signupSetting =  Config.findByName(Config.SIGNUP_SETTING).value;
            
                    if(signupSetting == "PUBLIC") {
                          
                        def name = Config.findByName(Config.ORGANISATION_NAME).value
                        def street = Config.findByName(Config.ORGANISATION_ADDRESS).value
                        def state = Config.findByName(Config.ORGANISATION_ADDRESS_STATE).value
                        def country = Config.findByName(Config.ORGANISATION_ADDRESS_COUNTRY).value
                        def city = Config.findByName(Config.ORGANISATION_ADDRESS_CITY).value
                        def zip = Config.findByName(Config.ORGANISATION_ADDRESS_ZIP).value                         
                        def regionList = Region.findAllWhere(deleted: false);
                        

                        if(street == "" || country == "" || state == "" || 
                            city == "" || zip == "" || name == "" ) {                            
                            enableSignup = "false";
                        } 
                        
                    } else {
                        enableSignup = "false"; 
                    }  
                    
                    enableSignup = "true"; 
                    
        
                    String domainName = "";
//                    domainName = Domain.findByIsDefault(true)?.name == "dummy" ? "default" : Domain.findByIsDefault(true)?.name;
                    
                    if(trialEnabled.value == "true") {
                        render view: view, model: [postUrl: postUrl, enableSignup:enableSignup, 
                                               rememberMeParameter: config.rememberMe.parameter, trialEnabled: "TRUE", logoUrl: logoUrl.value, lang:language, bgnImageUrl: bgnImageUrl.value] 
                    } else {
                        render view: view, model: [postUrl: postUrl,enableSignup:enableSignup, 
                                               rememberMeParameter: config.rememberMe.parameter, trialEnabled: "FALSE", logoUrl: logoUrl.value, lang:language, bgnImageUrl: bgnImageUrl.value]
                    }
//                }
        }

	/**
	 * The redirect action for Ajax requests.
	 */
	def authAjax = {
		response.setHeader 'Location', SpringSecurityUtils.securityConfig.auth.ajaxLoginFormUrl
		response.sendError HttpServletResponse.SC_UNAUTHORIZED
	}

	/**
	 * Show denied page.
	 */
	def denied = {
		if (springSecurityService.isLoggedIn() &&
				authenticationTrustResolver.isRememberMe(SCH.context?.authentication)) {
			// have cookie but the page is guarded with IS_AUTHENTICATED_FULLY
			redirect action: 'full', params: params
		}
	}

	/**
	 * Login page for users with a remember-me cookie but accessing a IS_AUTHENTICATED_FULLY page.
	 */
	def full = {
		def config = SpringSecurityUtils.securityConfig
		render view: 'auth', params: params,
			model: [hasCookie: authenticationTrustResolver.isRememberMe(SCH.context?.authentication),
			        postUrl: "${request.contextPath}${config.apf.filterProcessesUrl}"]
	}

	/**
	 * Callback after a failed login. Redirects to the auth page with a warning message.
	 */
	def authfail = {             
                
		def username = session[UsernamePasswordAuthenticationFilter.SPRING_SECURITY_LAST_USERNAME_KEY]                
                def replaceHash = username?.replaceAll("&#64;", "@");
                def userName =  replaceHash?.replaceAll("&#46;", ".");             
		String msg = ''
		def exception = session[WebAttributes.AUTHENTICATION_EXCEPTION]
		if (exception) {
			if (exception instanceof AccountExpiredException) {
				msg = g.message(code: "springSecurity.errors.login.expired")
			}
			else if (exception instanceof CredentialsExpiredException) {
				msg = g.message(code: "springSecurity.errors.login.passwordExpired")
			}
			else if (exception instanceof DisabledException) {
				msg = g.message(code: "springSecurity.errors.login.disabled")
			}
			else if (exception instanceof LockedException) {
				msg = g.message(code: "springSecurity.errors.login.locked")
			} else if (exception instanceof AccountClosedException) {
                                msg = g.message(code: "springSecurity.errors.login.account.closed")
                        } else if (exception instanceof IPLockedException) {
                                msg = g.message(code: "springSecurity.errors.ip.locked")
                        } else if (exception instanceof OSAccountNotFound) {
                                msg = g.message(code: "springSecurity.errors.login.account.notfound.in.OS")
                        } else if (exception instanceof OSDomainUserNotFound) {
                            msg = g.message(code: "common.username.notfound.in.domain",args: [exception.getMessage()])
                        } 
			else {
                            def failureCountPerAccount
                            def failureCountPerIp
                            Config maxCount =  Config.findByName(Config.MAX_LOGIN_FAILURE);
                            Config maxCountPerIp =  Config.findByName(Config.MAX_LOGIN_FAILURE_PER_IP);
                            if((maxCount.value == "" || maxCount.value == null) 
                                && (maxCountPerIp.value == "" || maxCountPerIp.value == null)) {                                
                                failureCountPerAccount = 15
                                failureCountPerIp = 5
                            } else {
                                failureCountPerAccount = Integer.parseInt(maxCount.value)
                                failureCountPerIp = Integer.parseInt(maxCountPerIp.value)
                            }
                            Date date = new Date()
                            //                            SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");
                            
                            String ipAddress = getIPAddress(request)
                                                           
                            User user = User.findByUsername(userName)
                            
                            EventLogIpAddress eventLogIpAddress = EventLogIpAddress.findWhere(ipAddress:ipAddress,
                                            user:User.findByUsername(userName))
                            if(user) {
//                                   println("user  " + user.domain )
//                                    def count = user.failureCount
//                                    user.failureCount = count + 1
                                    if (!eventLogIpAddress) {                        
                                        eventLogIpAddress = new EventLogIpAddress()
                                        eventLogIpAddress.ipAddress = ipAddress
                                        eventLogIpAddress.failureCount = 1
                                        eventLogIpAddress.successCount = 0
                                        eventLogIpAddress.overAllCount = 1
                                        eventLogIpAddress.overAllFailureCount = 1
                                        eventLogIpAddress.isLocked = false
                                        eventLogIpAddress.user = user
                                        eventLogIpAddress.save(flush: true)
                                        if (!eventLogIpAddress.save()) {
                                            eventLogIpAddress.errors.allErrors.each { Console.println (it) }
                                        }
                                    } else if(eventLogIpAddress) {
                                        eventLogIpAddress.successCount = eventLogIpAddress.successCount
                                        eventLogIpAddress.failureCount = eventLogIpAddress.failureCount + 1   
                                        eventLogIpAddress.overAllFailureCount = eventLogIpAddress.overAllFailureCount + eventLogIpAddress.failureCount
                                        eventLogIpAddress.overAllCount = eventLogIpAddress.successCount + eventLogIpAddress.overAllFailureCount
                                        eventLogIpAddress.isLocked = false
                                        eventLogIpAddress.save(flush: true)
                                        if (!eventLogIpAddress.save()) {
                                        eventLogIpAddress.errors.allErrors.each { Console.println (it) }
                                        }
                                    }
                                    UserEvent userEvent = new UserEvent()
                                    userEvent.ipAddress = ipAddress
                                    userEvent.eventType = "login failed"
                                    userEvent.date = date.toString()
                                    userEvent.failedCount = eventLogIpAddress.failureCount
                                    userEvent.user = User.findByUsername(userName)
                                    userEvent.eventLogIpAddress = EventLogIpAddress.findWhere(ipAddress:ipAddress,
                                                                        user:User.findByUsername(userName))
                                    userEvent.save(flush: true)
                                                      
                                    if(eventLogIpAddress.failureCount >= failureCountPerIp) {
                                        
//                                        if(user.account.accountType.name() !="ADMIN") {
//                                            def lockTime = new Timestamp(date.getTime())
//                                            eventLogIpAddress.isLocked = true
//                                            eventLogIpAddress.lockTime = lockTime;
//                                            eventLogIpAddress.save(flush: true)     
//                                                                       
//                            
//                                            Map tempalteMap = notificationService.getDefaultMailTemplateMap()   
//                                            tempalteMap.put("user", user)
//                                            notificationService.send(userName, "loginLocked.subject.ftl", tempalteMap, "loginLocked.ftl")   
//                                        }                                                                           
                                    }                        
                            }
                            
                            msg = g.message(code: "springSecurity.errors.login.fail")
                        }
                }

		if (springSecurityService.isAjax(request)) {
			render([error: msg] as JSON)
		}
		else {
			flash.message = msg
			redirect action: 'auth', params: params
		}
            }    

	/**
	 * The Ajax success redirect url.
	 */
	def ajaxSuccess = {
		render([success: true, username: springSecurityService.authentication.name] as JSON)
	}

	/**
	 * The Ajax denied redirect url.
	 */
	def ajaxDenied = {
		render([error: 'access denied'] as JSON)
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
