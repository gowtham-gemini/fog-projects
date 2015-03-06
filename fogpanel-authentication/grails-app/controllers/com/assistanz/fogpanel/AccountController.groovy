package com.assistanz.fogpanel

import grails.converters.deep.JSON
import com.assistanz.fogpanel.UserService
import javax.net.ssl.HttpsURLConnection
import org.codehaus.groovy.grails.commons.ApplicationHolder
import grails.plugin.springsecurity.annotation.Secured
import grails.plugin.springsecurity.SpringSecurityService

import com.assistanz.cloud.config.ConfigLoader


class AccountController {
    NotificationService notificationService
    SpringSecurityService springSecurityService; 
    def userService
    
    def test_page () {}
    
    def confirmAccount() {                
        def language;
        Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
        language = defaultLanguage.value
        def myURL = request.requestURL; 
        URL url = new URL(myURL.toString());
        def baseURL;
        if(url.port == 80 || url.port == 443 || url.port <= 0) {
           baseURL = url.protocol + "://" + url.host + "" + request.getContextPath(); 
        } else {
           baseURL = url.protocol + "://" + url.host + ":" + url.port + "" + request.getContextPath();
        }                            
                     
        Calendar calendar = Calendar.getInstance(); 
        User user = User.findByToken(params.id);    
        
        def invitationUser = Invitation.findByEmail(user.username);
        if(invitationUser) {
            invitationUser.status = "Signedup"
            invitationUser.save(flush:true, failOnError:true);
        } 
        
        def logoUrl = Config.findByName(Config.ORGANISATION_BILLING_LOGO);
        session['org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE'] = new Locale(language, language)
        if(user) {
            if(calendar.getTime() <= user.tokenExpiryDate) {
                def result = userService.confirmAccount(params.id)
                render(view: "confirm",  model: [logoUrl: logoUrl.value, contextPath: request.getContextPath(), baseUrl: baseURL.toString(), orgName: Config.findByName(Config.ORGANISATION_NAME).value, lang: language]);  
            } else {
                render(view: "resendconfirmMail", model: [logoUrl: logoUrl.value, contextPath: request.getContextPath(), baseUrl: baseURL.toString(), orgName: Config.findByName(Config.ORGANISATION_NAME).value, lang: language]);  
            } 
        } else {
           render(view: "resendconfirmMail", model: [logoUrl: logoUrl.value, contextPath: request.getContextPath(), baseUrl: baseURL.toString(), orgName: Config.findByName(Config.ORGANISATION_NAME).value, lang: language]);   
        }
         
    }
    
    def mailService
    def index() {
        
    }    
    def signup() {
        
    }
    def TestLink() {}
    def trial() {}
    def forgotPassword() {
        
        def myURL = request.requestURL; 
        URL url = new URL(myURL.toString());
        def language;
        Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
        language = defaultLanguage.value
        
        def baseURL;
        if(url.port == 80 || url.port == 443 || url.port <= 0) {
           baseURL = url.protocol + "://" + url.host + "" + request.getContextPath(); 
        } else {
           baseURL = url.protocol + "://" + url.host + ":" + url.port + "" + request.getContextPath();
        }
        
        
       def logoUrl = Config.findByName(Config.ORGANISATION_BILLING_LOGO);
       render(view: "forgotPassword", model: [logoUrl: logoUrl.value, contextPath: request.getContextPath(), baseUrl: baseURL.toString(), orgName: Config.findByName(Config.ORGANISATION_NAME).value, lang: language])
        } 
    
    def resendconfirmMail() {
        def myURL = request.requestURL; 
        URL url = new URL(myURL.toString());
        
        def baseURL;
        if(url.port == 80 || url.port == 443 || url.port <= 0) {
           baseURL = url.protocol + "://" + url.host + "" + request.getContextPath(); 
        } else {
           baseURL = url.protocol + "://" + url.host + ":" + url.port + "" + request.getContextPath();
        }                
        def logoUrl = Config.findByName(Config.ORGANISATION_BILLING_LOGO);        
        render(view: "forgotPassword", model: [logoUrl: logoUrl.value, contextPath: request.getContextPath(), baseUrl: baseURL.toString(), orgName: Config.findByName(Config.ORGANISATION_NAME).value])
        } 
    
    def resetPassword() {                         
        def myURL = request.requestURL; 
        URL url = new URL(myURL.toString());
        def language;
        Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
        language = defaultLanguage.value
        
        def baseURL;
        if(url.port == 80 || url.port == 443 || url.port <= 0) {
           baseURL = url.protocol + "://" + url.host + "" + request.getContextPath(); 
        } else {
           baseURL = url.protocol + "://" + url.host + ":" + url.port + "" + request.getContextPath();
        }                      
        
        Calendar calendar = Calendar.getInstance(); 
        User user = User.findByToken(params.id);  
        def logoUrl = Config.findByName(Config.ORGANISATION_BILLING_LOGO);
        if(user) {                   
            if(calendar.getTime() <= user.tokenExpiryDate) {                       
                render(view: "resetPassword", model: [token:params.id, logoUrl: logoUrl.value, contextPath: request.getContextPath(), baseUrl: baseURL.toString(), lang: language], orgName: Config.findByName(Config.ORGANISATION_NAME).value);  
            } else {            
                render(view: "passwordExpiredMessage",model: [logoUrl: logoUrl.value, contextPath: request.getContextPath(), baseUrl: baseURL.toString(), lang: language], orgName: Config.findByName(Config.ORGANISATION_NAME).value);  
            }
        } else {
            render(view: "passwordExpiredMessage",model: [logoUrl: logoUrl.value, contextPath: request.getContextPath(), baseUrl: baseURL.toString(), lang: language], orgName: Config.findByName(Config.ORGANISATION_NAME).value);  
        }        
    }
    
    def savePassword() {       
        User user = User.findByToken(params.token);   
        def logoUrl = Config.findByName(Config.ORGANISATION_BILLING_LOGO);
        def language;
        Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
        language = defaultLanguage.value
        if(user) {            
            def newPassword =  params.newPassword
            def confirPasword = params.confirmPassword;            
            if(newPassword == "" || confirPasword == "" || newPassword == " " || confirPasword == " ") {              
                render(view: "passwordResetError", model: [logoUrl: logoUrl.value]);  
            } else if(newPassword != confirPasword) {             
                render(view: "passwordResetError", model: [logoUrl: logoUrl.value]);  
            } else { 
                
                
            
                ConfigLoader configLoader = ConfigLoader.getInstance();

                Properties props = configLoader.getProperties();
                                
                String data = '{ "user": {"password": "'+ params.confirmPassword +'"}}'                  
                def response = "success"

                if(response == "Success") {
                    user.password = params.confirmPassword;            
                    user.token = "null"; 
                    user.save(flush:true);    

                    Map templateMap = notificationService.getDefaultMailTemplateMap() 
                    templateMap.put("user", user);

                    notificationService.send(user.email.toString(), "accountPasswordReset.subject.ftl", templateMap, "accountPasswordReset.ftl")                         

                    render(view: "passwordResetSuccess", model: [logoUrl: logoUrl.value, lang: language]);  

                } else {
                    render(view: "passwordResetError", model: [logoUrl: logoUrl.value, lang: language]);  
                }             
            }
            
        } else {
           render(view: "passwordResetError", model: [logoUrl: logoUrl.value, lang: language]);  
        }
        
    }
    def passwordResetSuccess() {    
        
    }
    def passwordResetError() {  
          
    }
    def invitationExpiry() {}
    def noUserInvitation() {}
    def trialSignupWarningPage() {}
    def successPage() {
        def language;
        Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
        language = defaultLanguage.value
        def logoUrl = Config.findByName(Config.ORGANISATION_BILLING_LOGO);
        session['org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE'] = new Locale(language, language)
        render(view: "successPage", model: [logoUrl: logoUrl.value, orgName: Config.findByName(Config.ORGANISATION_NAME).value, lang: language]); 
    }
    def signupTypePage() {        
        
        Config trialEnabled =  Config.findByName(Config.SIGNUP_TYPE_TRIAL_ENABLED);
        if(trialEnabled.value == "true") {
            render(view: "signupTypePage", model: [trialEnabled: "TRUE"])   
        } else {
            render(view: "signupTypePage", model: [trialEnabled: "FALSE"])   
        }
    }
    def invitationsignup() {        
        Calendar calendar = Calendar.getInstance();                     
        def invitation = Invitation.findByEmail(params.invitationEmail);
        def logoUrl = Config.findByName(Config.ORGANISATION_BILLING_LOGO);
        if(invitation) {             
            calendar.setTime(invitation.createdDate);
            calendar.add(Calendar.DATE, 60);                      
            if(invitation.createdDate >= calendar.getTime()) {                  
                render(view: "invitationExpiry",  model: [logoUrl: logoUrl.value]);  
            } else {
               if(invitation.type == "trial") {
                  redirect(action: "trialSignup", params: [invitationName: invitation.name, invitationEmail: invitation.email])
               } else {
                   redirect(action: "retailSignup", params: [invitationName: invitation.name, invitationEmail: invitation.email])
               }
            }
        } else {
            render(view: "noUserInvitation",  model: [logoUrl: logoUrl.value]);  
        }                
    }
    def trialSignup() {        
        def myURL = request.requestURL; 
        URL url = new URL(myURL.toString());
        
        def baseURL;
        if(url.port == 80 || url.port == 443 || url.port <= 0) {
           baseURL = url.protocol + "://" + url.host + "" + request.getContextPath(); 
        } else {
           baseURL = url.protocol + "://" + url.host + ":" + url.port + "" + request.getContextPath();
        }
        def language = "";
        if(params.lang) {
            language = params.lang
        } else {
            Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
            language = defaultLanguage.value;
        }
        def invitationName = params.invitationName? params.invitationName: ""; 
        def invitationEmail = params.invitationEmail? params.invitationEmail: "";  
        
        Config trialCreditLimit =  Config.findByName(Config.SIGNUP_TYPE_TRIAL_CREDIT_LIMIT);
        def logoUrl = Config.findByName(Config.ORGANISATION_BILLING_LOGO);
        def promotion = Promotion.findAll();
//        session['org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE'] = new Locale(defaultLanguage.value, defaultLanguage.value)
        if(promotion) {
            render(view: "signup", model: [accountType: "TRIAL", lang: language,credLimit:trialCreditLimit.value, signUPType:"trial", logoUrl: logoUrl.value, contextPath: request.getContextPath(), baseUrl: baseURL.toString(), invitationName: invitationName, invitationEmail: invitationEmail])
        } else {
            render(view: "trialSignupWarningPage", model: [logoUrl: logoUrl.value, ])
        }        
    }
    
    def retailSignup() {
                   
        def myURL = request.requestURL; 
        URL url = new URL(myURL.toString());
        def baseURL;
        if(url.port == 80 || url.port == 443 || url.port <= 0) {
           baseURL = url.protocol + "://" + url.host + "" + request.getContextPath(); 
        } else {
           baseURL = url.protocol + "://" + url.host + ":" + url.port + "" + request.getContextPath();
        }
        
        Config signupCardVerificationEnabled =  Config.findByName(Config.SIGNUP_CARD_VERIFICATION_ENABLED);
        Config creditCardEnabled =  Config.findByName(Config.CARD_PROCESSING_ENABLED);
        Config retailCreditLimit =  Config.findByName(Config.SIGNUP_TYPE_RETAIL_CREDIT_LIMIT);
        def logoUrl = Config.findByName(Config.ORGANISATION_BILLING_LOGO);
        def language = "";
        if(params.lang) {
            language = params.lang
        } else {
            Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
            language = defaultLanguage.value;
        }
        
        def enableCard = false;    
        
        
        def invitationName = params.invitationName?params.invitationName: "";  
        def invitationEmail = params.invitationEmail?params.invitationEmail: "";  
//        session['org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE'] = new Locale(defaultLanguage.value, defaultLanguage.value)
        if((signupCardVerificationEnabled.value == "true" && creditCardEnabled.value == "true") && enableCard == true) {
            render(view: "signup", model: [accountType: "RETAIL", lang:language, cardRequired:"TRUE", credLimit:retailCreditLimit.value, signUPType:"retail", logoUrl: logoUrl.value, contextPath: request.getContextPath(), baseUrl: baseURL.toString(), invitationName: invitationName, invitationEmail: invitationEmail])   
        } else {
            render(view: "signup", model: [accountType: "RETAIL", lang:language, cardRequired:"FALSE", credLimit:retailCreditLimit.value, signUPType:"retail", logoUrl: logoUrl.value, contextPath: request.getContextPath(), baseUrl: baseURL.toString(), invitationName: invitationName, invitationEmail: invitationEmail])   
        }
    }
    
    def setDefaultLanguage() {                
        def user = springSecurityService.currentUser  
        System.out.println("user is  "+user)
//        def account = user.account
//        account.preferredLanguage = params.language
//        account.save(flush: true);
        
        session['org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE'] = new Locale(account.preferredLanguage, account.preferredLanguage)
        redirect(controller: "home", action: "index")
    }
    
    def testFile() {
        Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
        session['org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE'] = new Locale(defaultLanguage.value, defaultLanguage.value)
        Console.print("lang" + defaultLanguage.value);
        render(view: "testFile", model: [lang: defaultLanguage.value])
    }
    
//    def dojotest() {}
//
//    def cloud_builder_test() {}
//    
//    def DTLTest() {}
//    
//    def DTLDemo(){}
//    
//    def diskoffering() {
//    }
//    
//    def sign_up() {
//    }
//	
//    def sliderTest() {}
//    def profilePicture() {}
//    def ListTest(){}	
//    def zoneList(){}
//    def css960Test(){}
//    def DashboardFirstPage(){}
//    def billing() {
//        
//    }
//    def billing123() {
//        
////        String ipAddress = request.getHeader("X-FORWARDED-FOR");  
//        
//           String ipAddress2 = request.getRemoteAddr();  
//           String ipAddress3 = request.getHeader("Client-IP");  
//              Console.print("request.getHeader(Client-IP)" + ipAddress3)
//        Console.print("request.getRemoteAddr()" + ipAddress2)
//        Console.print("InetAddress.getLocalHost().getHostAddress()" + InetAddress.getLocalHost().getHostAddress())
//    }
    
//    def postAction() {
//        
//        
//        def USER_AGENT = "Mozilla/5.0";
//        
//        String url = "https://selfsolve.apple.com/wcResults.do";
//        URL obj = new URL(url);
//        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
//
//        //add reuqest header
//        con.setRequestMethod("POST");
//        con.setRequestProperty("User-Agent", USER_AGENT);
//        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
//
//        String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";
//
//        // Send post request
//        con.setDoOutput(true);
//        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
//        wr.writeBytes(urlParameters);
//        wr.flush();
//        wr.close();
//        
//        int responseCode = con.getResponseCode();
//        System.out.println("\nSending 'POST' request to URL : " + url);
//        System.out.println("Post parameters : " + urlParameters);
//        System.out.println("Response Code : " + responseCode);
//        
//    }
    
}
   
