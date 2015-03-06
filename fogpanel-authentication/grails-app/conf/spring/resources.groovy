
import com.assistanz.fogpanel.LoginEventListener
import com.assistanz.fogpanel.SSOResource
import com.assistanz.fogpanel.ldap.MyUserDetailsContextMapper
import grails.plugin.springsecurity.SpringSecurityUtils
// Place your Spring DSL code here
beans = {
    //    loginEventListener(LoginEventListener)
    def conf = SpringSecurityUtils.securityConfig
    
    ldapUserDetailsMapper(MyUserDetailsContextMapper) { }
    
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
        //            preAuthenticationChecks = ref('preAuthenticationChecks')
        //            postAuthenticationChecks = ref('postAuthenticationChecks')
    }
    
    contextSource(org.springframework.ldap.core.support.LdapContextSource){                        
            
        url="ldap://96.31.86.211:389/"
        base=""
        userDn="cn=admin,dc=fogpanel,dc=com"
        password="l3tm3in"                
    }                       
    // the LDAP server we use if we're using LDAP as the backing store for
    // accounts
    ldapTemplate(org.springframework.ldap.core.LdapTemplate, ref("contextSource"))      
    

    ldapUtils(com.auth.uitls.LdapUtils){
        ldapTemplate = ref("ldapTemplate")
    }        
        
    //custom autowiring since entity not there
    SSOResource(com.assistanz.fogpanel.SSOResource) {
        ssoservice = ref('SSOService');
    }
}
