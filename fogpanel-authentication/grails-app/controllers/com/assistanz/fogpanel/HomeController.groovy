package com.assistanz.fogpanel

import grails.plugin.springsecurity.annotation.Secured
import grails.plugin.springsecurity.SpringSecurityService
import java.net.URL;
import org.codehaus.groovy.grails.commons.ApplicationHolder;

import com.assistanz.cloud.config.ConfigLoader;
import com.auth.uitls.LdapUtils
//import com.auth.uitls.LdapUtils

class HomeController {        
    def grailsApplication = ApplicationHolder.application    
    SpringSecurityService springSecurityService;
    def singleSignOnUrl =""
    LdapUtils ldapUtils
    UserService userService
    
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
                     
    
    def index() {                    
        println(" in Home index")
//        println(" get all users uid ")        
//        ldapUtils.getAllUsers()
//        ldapUtils.getAdminUsers()      

        
        
        def user = springSecurityService.currentUser
        def myURL = request.requestURL; 
        URL url = new URL(myURL.toString());        
        def baseURL;
        if(url.port == 80 || url.port == 443 || url.port <= 0) {
           baseURL = url.protocol + "://" + url.host + "" + request.getContextPath(); 
        } else {
           baseURL = url.protocol + "://" + url.host + ":" + url.port + "" + request.getContextPath();
        }
        def result = "OK"
        def logoUrl = Config.findByName(Config.ORGANISATION_BILLING_LOGO);
        def currency = ApplicationHolder.getApplication().config.billing.defaultCurrency        
        def invoiceCriteria = Invoice.createCriteria()
        def invoiceList = invoiceCriteria.list {
            //            eq("account", user.account) 
            order("id", "desc")
        }  
        def finalInvoice = invoiceList[0]            
        def invoiceState;
        if(finalInvoice) {
            invoiceState = finalInvoice.status.name()
        } 
        Config creditCardEnabled =  Config.findByName(Config.CARD_PROCESSING_ENABLED);
        def language;
        def role = springSecurityService.getPrincipal().getAuthorities()    
        //        if(role.iterator().next() == "ROLE_ADMIN" ) {
        //            Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
        //            language = defaultLanguage.value
        //        } else {
        //            if(user.account.preferredLanguage == "" || user.account.preferredLanguage == "null" || user.account.preferredLanguage == null) {
            Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
            language = defaultLanguage.value
        //            } else {
        //                language = user.account.preferredLanguage
        //            }            
        //        }
        session['org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE'] = new Locale(language, language)
                
                    
            for (String header : HEADERS_TO_TRY) {
                String ip = request.getHeader(header);
                if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
                    return ip;
                }
            }
            EventLogIpAddress eventLogIpAddress = EventLogIpAddress.findWhere(ipAddress:request.getRemoteAddr(),
                                            user:user)
                                        
            if(eventLogIpAddress) {
                eventLogIpAddress.failureCount = 0
                eventLogIpAddress.save(flush: true);
            }
        //checkIsConfigModified
                
        def isConfigModified = ConfigLoader.isConfigModified;
        Console.print("isConfigModified" + isConfigModified)        
        if(result == "OK") { 
            [lang:language,contextPath: request.getContextPath(), baseUrl: baseURL.toString(), singleSignOnUrl: singleSignOnUrl, accountId: user?.id, accountStatus:"ACTIVE", accountDue:null,  homepage:"#/admin/dashboard", accountType:"ADMIN", logoUrl: logoUrl.value, currency: currency, invoiceState: invoiceState, orgName: Config.findByName(Config.ORGANISATION_NAME).value, creditCardEnabled: creditCardEnabled.value, isConfigModified: isConfigModified]
        } else {
            [lang:language,contextPath: request.getContextPath(), baseUrl: baseURL.toString(), singleSignOnUrl: singleSignOnUrl, accountId: user?.id, accountStatus:"ACTIVE", accountDue:null, homepage:"#/admin/dashboard", accountType:"RETAIL", logoUrl: logoUrl.value, currency: currency, invoiceState: invoiceState, orgName: Config.findByName(Config.ORGANISATION_NAME).value, creditCardEnabled: creditCardEnabled.value, isConfigModified: isConfigModified] 
        }

    }
}
