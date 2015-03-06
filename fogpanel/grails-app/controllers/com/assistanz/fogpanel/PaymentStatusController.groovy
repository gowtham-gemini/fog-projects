package com.assistanz.fogpanel

import grails.plugin.springsecurity.annotation.Secured
import grails.plugin.springsecurity.SpringSecurityService
import java.net.URL;
import org.codehaus.groovy.grails.commons.ApplicationHolder
import com.assistanz.fogpanel.GeneralConstants;


class PaymentStatusController {
    PaymentCCAvenueService paymentCCAvenueService;
    
    def grailsApplication = ApplicationHolder.application    
    SpringSecurityService springSecurityService;
    ConfigService configService

    
    @Secured(['ROLE_ADMIN', 'ROLE_FREE_USER', 'ROLE_PAID_USER'])
    def index() {
        
        def user = springSecurityService.currentUser
        def myURL = request.requestURL; 
        URL url = new URL(myURL.toString());
        def singleSignOnUrl = ApplicationHolder.getApplication().config.cloudstack.singleSignOnUrl
        def baseURL;
        if(url.port == 80 || url.port == 443 || url.port <= 0) {
           baseURL = url.protocol + "://" + url.host + "" + request.getContextPath(); 
        } else {
           baseURL = url.protocol + "://" + url.host + ":" + url.port + "" + request.getContextPath();
        }
        
        Console.print(baseURL);
        
        def logoUrl = Config.findByName(Config.ORGANISATION_BILLING_LOGO);
        def currency = ApplicationHolder.getApplication().config.billing.defaultCurrency        
            
        Config creditCardEnabled =  Config.findByName(Config.CARD_PROCESSING_ENABLED);
        def language;
        def role = springSecurityService.getPrincipal().getAuthorities()    
        if(role.iterator().next() == "ROLE_ADMIN" ) {
            Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
            language = defaultLanguage.value
        } else {
            if(user.account.preferredLanguage == "" || user.account.preferredLanguage == "null" || user.account.preferredLanguage == null) {
                Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
                language = defaultLanguage.value
            } else {
                language = user.account.preferredLanguage
            }            
        }
        session['org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE'] = new Locale(language, language)
        
        
        if(params.AuthDesc == 'Y'){
            Payments paymentStatus = new Payments();
            paymentStatus.paymentToken = params.nb_order_no;
            int order_id = Integer.parseInt(params.Order_Id);
            paymentStatus.paymentStatus = GeneralConstants.PAYMENT_RESULT_SUCCESS;
            String paymentLoadString = params;
            paymentCCAvenueService.insertPaymentStatus(paymentStatus,order_id,paymentLoadString);
            
            [paymentStatus: GeneralConstants.PAYMENT_RESULT_SUCCESS, lang:language,contextPath: request.getContextPath(), baseUrl: baseURL.toString(), singleSignOnUrl: singleSignOnUrl, accountId: user.account.id, accountStatus:user.account.status.name().toString(), accountDue:user.account.totalPayable,  homepage:"#/admin/dashboard", accountType:user.account.accountType.name(), logoUrl: logoUrl.value, currency: currency, orgName: Config.findByName(Config.ORGANISATION_NAME).value, creditCardEnabled: creditCardEnabled.value,paymentToken:paymentStatus.paymentToken,amount:params.Amount]
            
            
//            redirect(controller:"home",action:"index");
        }else if(params.AuthDesc == 'N'){
            Payments paymentStatus = new Payments();
            paymentStatus.paymentToken = params.nb_order_no;
            paymentStatus.paymentStatus = GeneralConstants.PAYMENT_RESULT_FAILURE;
            int order_id = Integer.parseInt(params.Order_Id);
            String paymentLoadString = params
            
            paymentCCAvenueService.insertPaymentStatus(paymentStatus,order_id,paymentLoadString);
           
            [paymentStatus: GeneralConstants.PAYMENT_RESULT_FAILURE, lang:language,contextPath: request.getContextPath(), baseUrl: baseURL.toString(), singleSignOnUrl: singleSignOnUrl, accountId: user.account.id, accountStatus:user.account.status.name().toString(), accountDue:user.account.totalPayable,  homepage:"#/admin/dashboard", accountType:user.account.accountType.name(), logoUrl: logoUrl.value, currency: currency, orgName: Config.findByName(Config.ORGANISATION_NAME).value, creditCardEnabled: creditCardEnabled.value,amount:params.Amount]
            
//            redirect(controller:"home",action:"index");
        }else if(params.AuthDesc == "B"){
            Payments paymentStatus = new Payments();
            paymentStatus.paymentToken = params.nb_order_no;
            paymentStatus.paymentStatus = GeneralConstants.PAYMENT_RESULT_FAILURE;
            int order_id = Integer.parseInt(params.Order_Id);
            String paymentLoadString = params
                     
            paymentCCAvenueService.insertPaymentStatus(paymentStatus,order_id,paymentLoadString);
           
            [paymentStatus: GeneralConstants.PAYMENT_RESULT_FAILURE, lang:language,contextPath: request.getContextPath(), baseUrl: baseURL.toString(), singleSignOnUrl: singleSignOnUrl, accountId: user.account.id, accountStatus:user.account.status.name().toString(), accountDue:user.account.totalPayable,  homepage:"#/admin/dashboard", accountType:user.account.accountType.name(), logoUrl: logoUrl.value, currency: currency, orgName: Config.findByName(Config.ORGANISATION_NAME).value, creditCardEnabled: creditCardEnabled.value]
//            redirect(controller:"home",action:"index");
        }
    }
//    def payulatam(){
//        
//    }
}
