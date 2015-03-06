package com.assistanz.fogpanel

import java.sql.Timestamp
import java.util.zip.Adler32
import com.assistanz.cloud.cloudstack.CloudStackServer
import com.assistanz.cloud.cloudstack.accounts.CSAccountService
import com.assistanz.cloud.cloudstack.limit.CSLimitService
import com.assistanz.fogpanel.paymentgateway.*;
import grails.converters.deep.JSON
import java.awt.Desktop
import java.net.URI;
import java.util.Date;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.text.SimpleDateFormat
import java.text.DateFormat
import java.text.DecimalFormat
import org.codehaus.groovy.grails.commons.ApplicationHolder
import org.apache.commons.logging.LogFactory;
import com.assistanz.fogpanel.GeneralConstants;


class PaymentCCAvenueService {
    
    InvoiceService invoiceService
    NotificationService notificationService 
    def springSecurityService;
    
    def check(paymentData) {
        
        def user = springSecurityService.currentUser
        //Getting the Merchant Id and Working Key
        Config merchantId =  Config.findByName(Config.PAYMENT_GW_CCAVENUE_MERCHANT);                            
        Config workingKey =  Config.findByName(Config.PAYMENT_GW_CCAVENUE_WORKING_KEY);
        //        def payment = new Payments();
        //        payment.amount = paymentData.amount;
        def cardOption = paymentData.cardOption;
        def bankName = paymentData.bankName;
        def paymentGWName = paymentData.paymentGWName

        Date date = new Date();
        def time = new Timestamp(date.getTime()) 
        Payments paymentEntry = new Payments();
        paymentEntry.amount = paymentData.amount;
        paymentEntry.paymentDate = time;
        paymentEntry.account = user.account;
        paymentEntry.paymentToken = "CC-Avenue-Token";
        paymentEntry.totalAmount =  paymentEntry.amount;
        paymentEntry.processingFee = 0;
        paymentEntry.paymentStatus = "Pending";
        paymentEntry.paymentLoad = "";
        paymentEntry.gatewayName = paymentGWName;
        // Adding an entry in the payments table with status as in progress
        paymentEntry.save(flush:true);      
        if (!paymentEntry.save()) {
            paymentEntry.errors.allErrors.each { println it }
        }
        
        // Getting the max id which is used as order id to pass to the gateway
        def c = Payments.createCriteria()
        def maxId = c.get { projections { max('id') } }
                        
        def appURL =  ApplicationHolder.getApplication().config.cloudstack.applicationUrl
        String redirectURL = appURL+"/paymentStatus"
        String checkSumStr = merchantId.value+"|"+maxId+"|"+paymentData.amount+"|"+redirectURL+"|"+workingKey.value;
        byte[] bytes = checkSumStr.getBytes();
        Adler32 checksum = new Adler32();
        checksum.update(bytes, 0, bytes.length);
        String result = checksum.getValue();
        String bankNameAttr="";
        if(paymentData.cardOption.equalsIgnoreCase("NonMoto")){
            bankNameAttr = "NonMotoCardType";
        }else if(paymentData.cardOption.equalsIgnoreCase("netBanking")){
            bankNameAttr = "netBankingCards";
        }else if(paymentData.cardOption.equalsIgnoreCase("CCRD")){
            bankNameAttr = "CCRDType";
        }
        try {            
            String urlParameters = "Amount="+paymentData.amount+"&Merchant_Id="+merchantId.value+"&WorkingKey="+workingKey.value+"&Order_Id="+maxId+"&cardOption="+paymentData.cardOption+"&"+bankNameAttr+"="+paymentData.bankName+"&Redirect_Url="+redirectURL+"&Checksum="+result;

            String request = "https://www.ccavenue.com/servlet/new_txn.PaymentIntegration?"+urlParameters;   
            
//            Desktop.getDesktop().browse(new URL(request).toURI());  
                       
            ArrayList<ArrayList<String>> paymentResponse = new ArrayList<ArrayList<String>>(); 
            HashMap item = new HashMap();
            item.put("result", "OK")
            item.put("url", request)
            paymentResponse.add(item)
            
            return paymentResponse
        }
        catch (java.io.IOException e) {
            ArrayList<ArrayList<String>> paymentResponse = new ArrayList<ArrayList<String>>(); 
            HashMap item = new HashMap();
            item.put("result", "FAILED")
            paymentResponse.add(item);
            
            return paymentResponse
        }  catch (Exception e) {
            Console.print(e)
        }
    }
    
    
    def insertPaymentStatus(Payments paymentStatus,int orderId,String paymentLoadString) {
        def payment = Payments.findById(orderId);
        
        if(payment) {
            payment.paymentToken = paymentStatus.paymentToken;
            payment.paymentLoad = paymentLoadString;
            payment.paymentStatus = paymentStatus.paymentStatus;
            payment.save(flush:true);

            def account = payment.account
            
            
            def mailtemplate = MailTemplate.findByName("paymentCreated")
            def headerTemplate = MailTemplate.findByName("header");
            def footerTemplate = MailTemplate.findByName("footer");
            def finalMessage;
            def logoConfig = Config.findByName("organisation.billing.logo")
            def signature = Config.findByName("organisation.billing.signature");

            def hasHeader = mailtemplate.hasHeader
            def hasFooter = mailtemplate.hasFooter                    
            def applicationUrl = ApplicationHolder.getApplication().config.cloudstack.applicationUrl 
             
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

            if(paymentStatus.paymentStatus == GeneralConstants.PAYMENT_RESULT_SUCCESS) {

                def invoice;
                if(account.status.name() == "CANCELED") {
                    def invoiceCriteria = Invoice.createCriteria()
                    def invoiceList = invoiceCriteria.list {
                        eq("account", account) 
                        order("id", "desc")
                    }  
                    invoice = invoiceList[0]
                } else {
                    invoice = Invoice.findWhere(account: account, status: InvoiceStatus.values()[6])
                }

                if(!invoice) {
                    if(account.creditLimit == 0) {
                        invoiceService.createNewPrepaidInvoice()

                        invoice = Invoice.findWhere(account: account, status: InvoiceStatus.values()[6]) 
                    }
                }
                invoice.payment = invoice.payment + payment.amount
                invoice.save(flush:true)

                if(account.accountType.name() == "TRIAL") {

                    def vmCriteria = VirtualMachine.createCriteria()

                    def vmList = vmCriteria.list {
                        eq("owner", account)
                        and {
                            eq("deleted", false)  
                        }
                    }

                    for(def vm: vmList) {
                        vm.networkRead = 0.0
                        vm.networkWrite = 0.0
                        vm.save(flush: true);
                    }


                    def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
                    def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
                    def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret

                    CloudStackServer server = new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey)

                    if(account.status.name() == "DISABLED") {

                        HashMap<String,String> optional2 = new HashMap<String,String>();
                        optional2.put("account", new String(account.userName));
                        optional2.put("domainid", account.domain.referenceId);

                        CSAccountService csAccService = new CSAccountService(server);

                        def response = csAccService.enableAccount(optional2)
                    }


                    CSLimitService csLimitService = new CSLimitService(server); 

                    HashMap<String,String> optional = new HashMap<String,String>();  
                    optional.put("account", new String(account.userName));
                    optional.put("domainid", new String(account.domain.referenceId));
                    optional.put("max", "-1");

                    def pStorageLimit = csLimitService.updateResourceLimit("10", optional)
                    def sStorgemLimit = csLimitService.updateResourceLimit("11", optional)

                    def vmLimit = csLimitService.updateResourceLimit("0", optional)
                    def storageLimit = csLimitService.updateResourceLimit("2", optional)
                    def snapshotLimit = csLimitService.updateResourceLimit("3", optional)

                    account.vmLimit = "-1"
                    account.storageLimit = "-1"
                    account.snapshotLimit = "-1"
                    account.bandwidthLimit = "-1"

                }

                if(account.totalPayable < account.creditLimit) {
                    account.creditLimitLevel = ""; 
                }
                account.accountType = AccountType.values()[1]
                account.totalPaid = account.totalPaid + payment.amount;
                account.cardVerified = true
                account.save(flush:true);

                log.info("payment for account: ${account.id}, amount: ${payment.amount}")

                // Paymentg create Mail
                //send Mail Menthod
                // Content Area
                def userName = mailtemplate.content.replaceAll("\\[userName\\]", account.firstName)
                def processingFee = userName.replaceAll("\\[processingFee\\]", payment.processingFee.toString())
                def amount = processingFee.replaceAll("\\[amount\\]", payment.amount.toString())
                def totalAmount = amount.replaceAll("\\[totalAmount\\]", payment.totalAmount.toString())
                def message = totalAmount.replaceAll("\\[signature\\]", signature.value);    

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
                notificationService.send(account.email.toString(), mailtemplate.subject, finalMessage)
                invoiceService.updateDraftInvoice(invoice, account)
                invoiceService.sendBillingMailForAllAccount()
            }
        }
    }
}
