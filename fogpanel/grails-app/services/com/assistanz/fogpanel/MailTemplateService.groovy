package com.assistanz.fogpanel
import com.assistanz.fogpanel.paymentgateway.*;
import com.assistanz.cloud.cloudstack.accounts.CSAccountService;
import com.assistanz.cloud.cloudstack.CloudStackServer;
import com.assistanz.cloud.cloudstack.accounts.CreateAccountResponse;
import grails.converters.deep.JSON
import java.util.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import com.assistanz.fogpanel.GeneralConstants;
import com.assistanz.fogpanel.Account;
import com.assistanz.cloud.cloudstack.address.CSAddressService
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import com.assistanz.fogpanel.MailManager;
import com.assistanz.fogpanel.NotificationService
import java.text.SimpleDateFormat
import java.util.regex.Pattern
import java.util.regex.Matcher
import com.assistanz.cloud.cloudstack.accounts.AccountResponse
import org.codehaus.groovy.grails.commons.ApplicationHolder
import com.assistanz.cloud.cloudstack.virtualmachine.CSVirtualMachineService
import com.assistanz.cloud.cloudstack.snapshot.CSSnapshotService
class MailTemplateService {
    NotificationService notificationService
    def springSecurityService; 
    def serviceMethod() {

    }
    
    def list () {
        try { 
            def mailTemplate = MailTemplate.findAll();
            return mailTemplate;
        
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
    
    def sendMail(String requestBody) {
        try {
           
            def requestData = JSON.parse(requestBody);
            def applicationUrl = ApplicationHolder.getApplication().config.cloudstack.applicationUrl
            def finalMessage 
            def user = User.findByUsername(requestData.email)
            ArrayList<ArrayList<String>> sendMailResultList = new ArrayList<ArrayList<String>>();            
            HashMap<String,String> item = new HashMap<String,String>(); 
            if(user) {                                                                                       
                    def mailtemplate = MailTemplate.findByName(requestData.templateName)
                    def headerTemplate = MailTemplate.findByName("header");
                    def footerTemplate = MailTemplate.findByName("footer");
                    def verifyLink = applicationUrl + "/account/forgotPassword/"
                    def logoConfig = Config.findByName("organisation.billing.logo")
                    def signature = Config.findByName("organisation.billing.signature");
                    
                    def hasHeader = mailtemplate.hasHeader
                    def hasFooter = mailtemplate.hasFooter
                    def hasSignature = mailtemplate.hasSignature
//                    Console.print("hasHeader" + hasHeader)
//                    Console.print("hasFooter" + hasFooter)
//                    Console.print("hasSignature" + hasSignature)
                    
                    // Header Content
                    def logoUrl = ""
                    if(logoConfig.value == "") {                    
                        logoUrl = applicationUrl.toString() + "/images/fog_logo.png"
                    } else {
                        logoUrl =  logoConfig.value;
                    }  
                    def logoContent = '<img style="height: 30px; width: 100px"  src = '+logoUrl+' alt="progress" height="9" width="100">'
                    def headerContent = logoContent + headerTemplate.content;
                    
                    // Footer content
                    def footerContent = footerTemplate.content;
                    
                    // Content Area
                    def setUserName = mailtemplate.content.replaceAll("\\[userName\\]", user.account.firstName)
                    def linkMessage = setUserName.replaceAll("\\[verifyLink\\]", verifyLink) 
                    def message = linkMessage.replaceAll("\\[signature\\]", signature.value); 
                    if((hasHeader == true || hasHeader == 'true') && (hasFooter == true || hasFooter == 'true')) {
                        finalMessage = "<html><body>" + headerContent.toString() + message.toString() + footerContent.toString() + "</body></html>";
                    } else if((hasHeader == true || hasHeader == 'true') && (hasFooter == false || hasFooter == 'false')) {
                        finalMessage = "<html><body>" + headerContent.toString() + message.toString() + "</body></html>";
                    } else if((hasHeader == false || hasHeader == 'false') && (hasFooter == true || hasFooter == 'true')) {                       
                        finalMessage = "<html><body>" + message.toString() + footerContent.toString() + "</body></html>";
                    } else if((hasHeader == true || hasHeader == 'true') && (hasFooter == false || hasFooter == 'false')) {                       
                        finalMessage = "<html><body>" + headerContent.toString() + message.toString() + "</body></html>";
                    } else {
                        finalMessage = "<html><body>" + message.toString() + "</body></html>";
                    }
                    notificationService.send(requestData.email.toString(), mailtemplate.subject, finalMessage)
                    item.put("Result",  GeneralConstants.RESULT_SUCCESS);
                    sendMailResultList.add(item);                    
          
            } else {
                item.put("Result",  "No user found");
                sendMailResultList.add(item);
            }
            return sendMailResultList;
            
        } catch(Exception ex) {
             ex.printStackTrace(System.err);
            throw ex;
         }
    }
}
