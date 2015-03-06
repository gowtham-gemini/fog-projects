import com.assistanz.fogpanel.LoginEventListener
import grails.plugin.springsecurity.SpringSecurityUtils
// Place your Spring DSL code here
beans = {
//    loginEventListener(LoginEventListener)
    def conf = SpringSecurityUtils.securityConfig
    
    // adding custom auth  filter to get extra params from login form
    authenticationProcessingFilter(com.assistanz.fogpanel.auth.CustomAuthFilter) {
        	authenticationManager = ref('authenticationManager')
		sessionAuthenticationStrategy = ref('sessionAuthenticationStrategy')
		authenticationSuccessHandler = ref('authenticationSuccessHandler')
		authenticationFailureHandler = ref('authenticationFailureHandler')
		rememberMeServices = ref('rememberMeServices')
		authenticationDetailsSource = ref('authenticationDetailsSource')
                filterProcessesUrl = conf.apf.filterProcessesUrl
                usernameParameter = conf.apf.usernameParameter
                passwordParameter = conf.apf.passwordParameter
                continueChainBeforeSuccessfulAuthentication = conf.apf.continueChainBeforeSuccessfulAuthentication
                allowSessionCreation = conf.apf.allowSessionCreation
                postOnly = conf.apf.postOnly
                storeLastUsername=false 
        
    }
    
    // custom authentication
    daoAuthenticationProvider(com.assistanz.fogpanel.auth.CustomAuthProvider) {
            passwordEncoder = ref('passwordEncoder')
            saltSource = ref('saltSource')
            preAuthenticationChecks = ref('preAuthenticationChecks')
            postAuthenticationChecks = ref('postAuthenticationChecks')
    }

}
