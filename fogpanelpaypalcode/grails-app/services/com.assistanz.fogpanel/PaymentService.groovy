package com.assistanz.fogpanel

import com.assistanz.fogpanel.paymentgateway.*;
import grails.converters.deep.JSON
import java.util.Date;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.text.SimpleDateFormat
import java.text.DateFormat
import java.text.DecimalFormat
import org.codehaus.groovy.grails.commons.ApplicationHolder

class PaymentService {

    def springSecurityService;
    
    def saveCard(uuid, cardNumber, expMonth, expYear, cardType, cvv) {         
        PaymentGateway gateway = this.getGateway();
        CreditCard card = new CreditCard(); 
        card.UUID = uuid;
        card.number = cardNumber;
        card.expiryMonth = expMonth;
        card.expiryYear = expYear;
        card.type = cardType;
        card.cvv = cvv
                        
        def savedCard = gateway.addCreditCard(card);        
        return savedCard;       
    }
    
    //save the default card
    def saveCard(cardData) {        
        
        def account = springSecurityService.currentUser.account;
        def uuid = "CARD-A" + account.id;
        PaymentGateway gateway = this.getGateway();
        
        CreditCard card = new CreditCard();
        card.UUID = uuid
        card.number = cardData.cardNumber;
        card.expiryMonth = Integer.parseInt(cardData.expiryMonth);
        card.expiryYear = cardData.expiryYear;
        card.cvv = cardData.cvv
        card.type = cardData.cardType
                        
        def savedCard = gateway.addCreditCard(card);
        
        account.defaultCard = savedCard.UUID
        account.cardVerified = true
        account.cardExpiryMonth = Double.parseDouble(cardData.expiryMonth)
        account.cardExpiryYear = Double.parseDouble(cardData.expiryYear.toString())
        Config retailCreditLimit =  Config.findByName(Config.SIGNUP_TYPE_RETAIL_CREDIT_LIMIT);
        account.creditLimit = Double.parseDouble(retailCreditLimit.value); 
        
        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret

        //CloudStack Server Connection
        CloudStackServer server = new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey)
        
        if(account.accountType.name() == "TRIAL") {
            
            
            if(account.status.name() == "DISABLED") {
                            
                HashMap<String,String> optional2 = new HashMap<String,String>();
                optional2.put("account", new String(account.userName));
                optional2.put("domainid", account.domain.referenceId);
                //CloudStack Account Processing
                CSAccountService csAccService = new CSAccountService(server);

                def response = csAccService.enableAccount(optional2)

            }
            //CloudStack Account Resource Update
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
                
        account.accountType = AccountType.values()[1]
        account.save(flush:true);
        sendMail();
    }
    
    // process for payment to paypal
    def makePayment(CreditCard card, amount, description) {
        
        def currency = ApplicationHolder.getApplication().config.billing.defaultCurrency
        
        PaymentGateway gateway = this.getGateway();
        
        Transaction payment = new Transaction();
        payment.creditCard = card;
        payment.amount = amount;
        payment.currency = currency;
        payment.description = description; 
        gateway.processPayment(payment);
    }
    
    //get paypal gate
    def getGateway() {
        
        //Find the active gateway and support the logic by the provider        
        PaymentGateway gateway = 
        new Paypal(getPaypalConfig(), getPaypalMap());
        return gateway;
    }
    
    //get paypal configuration
    def getPaypalConfig() {
        def paypalConfig = new Properties();
        def paypalRecords = Config.findAllByNameLike("payment.gateway.paypal.%");
        paypalRecords.each {
            def name = it.name.replaceAll("payment.gateway.paypal.", "");
            paypalConfig.setProperty(name.trim(), it.value);
        }
        
        return paypalConfig; 
    }
    
    def getPaypalMap() {
        def paypalConfig = new java.util.HashMap<String, String>();
        def paypalRecords = Config.findAllByNameLike("payment.gateway.paypal.%");
        paypalRecords.each {
            def name = it.name.replaceAll("payment.gateway.paypal.", "");
            paypalConfig.put(name.trim(), it.value);
        }    
        return paypalConfig;
    }
    
    // add payment for user
    def addManualPayment(paymentData) {
        def account = springSecurityService.currentUser.account
                
        def mailtemplate = MailTemplate.findByName("paymentCreated")
        def headerTemplate = MailTemplate.findByName("header");
        def footerTemplate = MailTemplate.findByName("footer");
        def finalMessage;
        def logoConfig = Config.findByName("organisation.billing.logo")
        def signature = Config.findByName("organisation.billing.signature");

        def hasHeader = mailtemplate.hasHeader
        def hasFooter = mailtemplate.hasFooter                    

        // Header Content
        def logoUrl =  logoConfig.value;
        def logoContent = '<img style="height: 30px; width: 100px"  src = '+logoUrl+' alt="progress" height="9" width="100">'
        def headerContent = logoContent + headerTemplate.content;

        // Footer content
        def footerContent = footerTemplate.content;                
        Date date = new Date();
        def time = new Timestamp(date.getTime())
        Config processingFeeAmount =  Config.findByName(Config.PAYMENT_PROCESSING_FEE_AMOUNT);                            
        Config paymentFeePercentage =  Config.findByName(Config.PAYMENT_PROCESSING_FEE_PERCENTAGE);
        Config paymentFeeType =  Config.findByName(Config.PAYMENT_PROCESSING_FEE_TYPE);
        def invoice;
        
        //Get Account Invoice
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
        
        try {
            CreditCard card = new CreditCard(); 
            if(paymentData.useDefaultCard == true || paymentData.useDefaultCard == "true") {
                card.UUID = account.defaultCard
            } else if(paymentData.useDefaultCard == false || paymentData.useDefaultCard == "false") {
                card.number = paymentData.cardNumber
                card.expiryMonth = Integer.parseInt(paymentData.expiryMonth);
                card.expiryYear = paymentData.expiryYear
                card.cvv = paymentData.cvv
                card.type = paymentData.cardType;
                if(account.cardVerified == false || account.cardVerified == "false" ) {
                    def savedCard = gateway.addCreditCard(card);
        
                    account.defaultCard = savedCard.UUID
                    
                    account.cardExpiryMonth = Double.parseDouble(paymentData.expiryMonth)
                    account.cardExpiryYear = Double.parseDouble(paymentData.expiryYear.toString())
                    account.cardVerified = true
                    Config retailCreditLimit =  Config.findByName(Config.SIGNUP_TYPE_RETAIL_CREDIT_LIMIT);
                    account.creditLimit = Double.parseDouble(retailCreditLimit.value); 
                }
            }
            double processingFeeTotalAmount = (paymentData.amount/100)* Double.parseDouble(paymentFeePercentage.value) + Double.parseDouble(processingFeeAmount.value)
            
            def payment = new Payments() 
            payment.account = account;
            payment.paymentDate = time
            
            // processing fee calc
            if(paymentFeeType.value == "INCLUDE") {
                payment.processingFee = processingFeeTotalAmount 
                payment.amount = paymentData.amount - payment.processingFee
                def totalAmount = (int)Math.round((payment.amount + payment.processingFee) * 100)
                payment.totalAmount =  totalAmount / 100;  
                
                if(processingFeeTotalAmount >= payment.amount) {
                    throw new Exception("processing fee is grater than payment amount")
                }             
            } else {
                payment.processingFee = processingFeeTotalAmount
                payment.amount = paymentData.amount
                def totalAmount = (int)Math.round((payment.amount + payment.processingFee) * 100)
                payment.totalAmount =  totalAmount / 100;                  
            }

            BigDecimal payAmount = new BigDecimal(payment.totalAmount);
            payAmount = payAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
            
            def makePayment = makePayment(card, payAmount, description) 
            payment.paymentToken = makePayment.getUUID();
            payment.save(flush:true)
            invoice.payment = invoice.payment + paymentData.amount
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
                
                //CloudStack Server Connection
                CloudStackServer server = new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey)
                                
                if(account.status.name() == "DISABLED") {
                
                    HashMap<String,String> optional2 = new HashMap<String,String>();
                    optional2.put("account", new String(account.userName));
                    optional2.put("domainid", account.domain.referenceId);
                    
                     //CloudStack Account Processing
                    CSAccountService csAccService = new CSAccountService(server);

                    def response = csAccService.enableAccount(optional2)
                }
                
                //CloudStack Account Resource Update
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
            account.totalPaid = account.totalPaid + paymentData.amount;
            account.save(flush:true);
            
            // Paymentg create Mail
            
            // Content Area
            def userName = mailtemplate.content.replaceAll("\\[userName\\]", account.userName)
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
            
            //Send Payment Success mail
            notificationService.send(account.email.toString(), mailtemplate.subject, finalMessage)
            
            //Update Payment to Invoice
            invoiceService.updateDraftInvoice(invoice, account)
            invoiceService.sendBillingMailForAllAccount()
                                    
        } catch (Exception ex) {
            // Paymentg failed Process
            mailtemplate = MailTemplate.findByName("paymentFailed")
            headerTemplate = MailTemplate.findByName("header");
            footerTemplate = MailTemplate.findByName("footer");            

            hasHeader = mailtemplate.hasHeader
            hasFooter = mailtemplate.hasFooter                    

            // Header Content
            logoUrl =  logoConfig.value;
            logoContent = '<img style="height: 30px; width: 100px"  src = '+logoUrl+' alt="progress" height="9" width="100">'
            headerContent = logoContent + headerTemplate.content;

            // Footer content
            footerContent = footerTemplate.content;
            
            // Content Area
            def userName = mailtemplate.content.replaceAll("\\[userName\\]", account.userName)
            def amount = userName.replaceAll("\\[paymentAmt\\]", paymentData.amount.toString())            
            def message = amount.replaceAll("\\[signature\\]", signature.value);    
            
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
            log.error "payment for account:"+account.userName + "amount" + paymentData.amount + "failed due to:" , ex
            throw ex;
        }  
    }
    
    //Auto Payment Attempt 1 check
    def makeAutomatedPaymentAttempt1() {
            
        Calendar fromDateCalendar = Calendar.getInstance(); 
        fromDateCalendar.add(Calendar.DATE, -1);
        fromDateCalendar.set(Calendar.HOUR_OF_DAY, 00);
        fromDateCalendar.set(Calendar.MINUTE, 00);
        fromDateCalendar.set(Calendar.SECOND, 01);
        fromDateCalendar.set(Calendar.MILLISECOND, 001);                   
        Date fromDate = fromDateCalendar.getTime()

        Calendar toDateCalendar = Calendar.getInstance(); 
        toDateCalendar.add(Calendar.DATE, -1);
        toDateCalendar.set(Calendar.HOUR_OF_DAY, 23);
        toDateCalendar.set(Calendar.MINUTE, 59);
        toDateCalendar.set(Calendar.SECOND, 59);
        toDateCalendar.set(Calendar.MILLISECOND, 999);                   
        Date toDate = toDateCalendar.getTime()

        def invoiceCriteria = Invoice.createCriteria()

        def invoiceList = invoiceCriteria.list {
            ge("dueDate", fromDate) and { le("dueDate", toDate) }   
        }

        for(def invoice: invoiceList) {
            makeAutomatedPayment(invoice.account, invoice, 1)
        }
    }
    
    //Auto Payment Attempt 2 check
    def makeAutomatedPaymentAttempt2() {
       
        Calendar fromDateCalendar = Calendar.getInstance(); 
        fromDateCalendar.add(Calendar.DATE, -2);
        fromDateCalendar.set(Calendar.HOUR_OF_DAY, 00);
        fromDateCalendar.set(Calendar.MINUTE, 00);
        fromDateCalendar.set(Calendar.SECOND, 01);
        fromDateCalendar.set(Calendar.MILLISECOND, 001);                   
        Date fromDate = fromDateCalendar.getTime()

        Calendar toDateCalendar = Calendar.getInstance(); 
        toDateCalendar.add(Calendar.DATE, -2);
        toDateCalendar.set(Calendar.HOUR_OF_DAY, 23);
        toDateCalendar.set(Calendar.MINUTE, 59);
        toDateCalendar.set(Calendar.SECOND, 59);
        toDateCalendar.set(Calendar.MILLISECOND, 999);                   
        Date toDate = toDateCalendar.getTime()
        
        
        def invoiceCriteria = Invoice.createCriteria()

        def invoiceList = invoiceCriteria.list {
            ge("dueDate", fromDate) and { le("dueDate", toDate) }   
        }
        
        
        for(def invoice: invoiceList) {
            if(invoice.account.autoPaymentAttempt == 1) {
                makeAutomatedPayment(invoice.account, invoice, 2)
            }
        }
        makeAutomatedPaymentAttempt1()
    }
    
    //Auto Payment Attempt 3 check
    def makeAutomatedPaymentAttempt3() {
        
        Calendar fromDateCalendar = Calendar.getInstance(); 
        fromDateCalendar.add(Calendar.DATE, -3);
        fromDateCalendar.set(Calendar.HOUR_OF_DAY, 00);
        fromDateCalendar.set(Calendar.MINUTE, 00);
        fromDateCalendar.set(Calendar.SECOND, 01);
        fromDateCalendar.set(Calendar.MILLISECOND, 001);                   
        Date fromDate = fromDateCalendar.getTime()

        Calendar toDateCalendar = Calendar.getInstance(); 
        toDateCalendar.add(Calendar.DATE, -3);
        toDateCalendar.set(Calendar.HOUR_OF_DAY, 23);
        toDateCalendar.set(Calendar.MINUTE, 59);
        toDateCalendar.set(Calendar.SECOND, 59);
        toDateCalendar.set(Calendar.MILLISECOND, 999);                   
        Date toDate = toDateCalendar.getTime()
        
        
        def invoiceCriteria = Invoice.createCriteria()

        def invoiceList = invoiceCriteria.list {
            ge("dueDate", fromDate) and { le("dueDate", toDate) }   
        }
        
        
        for(def invoice: invoiceList) {
            if(invoice.account.autoPaymentAttempt == 2) {
                makeAutomatedPayment(invoice.account, invoice, 3)
            }
        }
        
        makeAutomatedPaymentAttempt2()
    }
    
    //Auto payment processing
    def makeAutomatedPayment(account, dueInvoice, attempt) {
        
        Calendar currentDateCalendar = Calendar.getInstance(); 
        currentDateCalendar.set(Calendar.HOUR_OF_DAY, 00);
        currentDateCalendar.set(Calendar.MINUTE, 00);
        currentDateCalendar.set(Calendar.SECOND, 01);
        currentDateCalendar.set(Calendar.MILLISECOND, 001);   
        Date curDate = currentDateCalendar.getTime()
        
        
        Date date = new Date();
        def time = new Timestamp(date.getTime())
        Config processingFeeAmount =  Config.findByName(Config.PAYMENT_PROCESSING_FEE_AMOUNT);                            
        Config paymentFeePercentage =  Config.findByName(Config.PAYMENT_PROCESSING_FEE_PERCENTAGE);
        Config paymentFeeType =  Config.findByName(Config.PAYMENT_PROCESSING_FEE_TYPE);
        def invoice = Invoice.findWhere(account: account, status: InvoiceStatus.values()[6]) 
        try {
            CreditCard card = new CreditCard(); 
            card.UUID == account.defaultCard
            def paymentsCriteria = Payments.createCriteria()
            def paymentAmmount = paymentsCriteria.list {
                eq("account", invoice.account)
                and{
                    ge("paymentDate", dueInvoice.date) and { le("paymentDate", curDate) }
                } 
                projections {
                    sum("totalAmount")
                }
            }
            double paymentTotalAmount;
            if(paymentAmmount[0] == null || paymentAmmount[0] == "null") {
                paymentTotalAmount =  invoice.previousBalance
            } else {
                paymentTotalAmount =  invoice.previousBalance - paymentAmmount[0]
            }
            if(paymentTotalAmount > 0) {
                double processingFeeTotalAmount = (paymentTotalAmount/100)* Double.parseDouble(paymentFeePercentage.value) + Double.parseDouble(processingFeeAmount.value)
            
                def payment = new Payments() 
                payment.account = account;
                payment.paymentDate = time

                // processing fee calculation
                if(paymentFeeType.value == "INCLUDE") {
                    payment.processingFee = processingFeeTotalAmount 
                    payment.amount = paymentTotalAmount - payment.processingFee
                    payment.totalAmount = Math.ceil((payment.amount + payment.processingFee) * 100d) / 100d;
                             
                } else {
                    payment.processingFee = processingFeeTotalAmount
                    payment.amount = paymentTotalAmount
                    payment.totalAmount = Math.ceil((paymentTotalAmount + payment.processingFee) * 100d) / 100d;
                }
                
                BigDecimal payAmount = new BigDecimal(payment.totalAmount);
                payAmount = payAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
                
                def makePayment = makePayment(card, payAmount, description)
                payment.paymentToken = makePayment.getUUID();
                payment.save(flush:true)
                invoice.payment = invoice.payment + paymentTotalAmount
                invoice.save(flush:true)
                account.totalPaid = account.totalPaid + paymentTotalAmount;
                account.save(flush:true);
                
                //Update Payment to invoice
                invoiceService.updateDraftInvoice(invoice, account)
            }                    
        } catch (Exception ex){
            
            account.autoPaymentAttempt = attempt
            account.save(flush:true);
            log.error "payment for account:"+account.userName + "failed due to:" , ex 
            throw ex;
        }  
    }
}
