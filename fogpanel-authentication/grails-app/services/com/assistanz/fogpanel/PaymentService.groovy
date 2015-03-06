package com.assistanz.fogpanel

import com.assistanz.fogpanel.paymentgateway.*;
import grails.converters.deep.JSON
import java.util.Date;
import java.util.SimpleDateFormat
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.text.SimpleDateFormat
import java.text.DateFormat
import java.text.DecimalFormat
import org.codehaus.groovy.grails.commons.ApplicationHolder
import org.apache.commons.logging.LogFactory;
import grails.transaction.Transactional
//import com.assistanz.fogpanel.ConfigService;

@Transactional
class PaymentService {
    
//    LicenseValidationService licenseValidationService
    private static final log = LogFactory.getLog(this)
    
    //    def configService
    def springSecurityService;
    InvoiceService invoiceService
    NotificationService notificationService 
    ConfigService configService;
    
    def saveCard(uuid, cardNumber, expMonth, expYear, cardType, cvv) {         
        PaymentGateway gateway = this.getGateway();
        //        def account = springSecurityService.currentUser.account;
        CreditCard card = new CreditCard(); 
        card.UUID = uuid;
        card.number = cardNumber;
        card.expiryMonth = expMonth;
        card.expiryYear = expYear;
        card.type = cardType;
        card.cvv = cvv
                        
        def savedCard = gateway.addCreditCard(card);        
        return savedCard;
        //        sendMail();        
    }   
    
    def saveCard(cardData) {        
        
        //        uuid, cardNumber, expMonth, expYear, cardType
        
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
                 
        if(account.accountType.name() == "TRIAL") {
            
            Config retailCreditLimit =  Config.findByName(Config.SIGNUP_TYPE_RETAIL_CREDIT_LIMIT);
            account.creditLimit = Double.parseDouble(retailCreditLimit.value); 
            
            if(account.status.name() == "DISABLED") {
                            
                
            }
        }      
        account.accountType = AccountType.values()[1]
        account.save(flush:true);       
        log.info("default card changed for account: ${account.id}")
        
        Date date = new Date()
        def time = new Timestamp(date.getTime())
        
        Map tempalteMap = notificationService.getDefaultMailTemplateMap()
        tempalteMap.put("updatedDate", time.toString())
        notificationService.send(account.email.toString(), "defaultCardChanged.subject.ftl", tempalteMap, "defaultCardChanged.ftl")    
        
    }
    
    
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
    
    def getGateway() {
        
        //Find the active gateway and support the logic by the provider        
        PaymentGateway gateway = 
        new Paypal(getPaypalConfig(), getPaypalMap());
        return gateway;
    }
    
    def getPaypalConfig() {
        def paypalConfig = new Properties();
        //paypalConfig.findByCr
        def paypalRecords = Config.findAllByNameLike("payment.gateway.paypal.%");
        paypalRecords.each {
            def name = it.name.replaceAll("payment.gateway.paypal.", "");
            paypalConfig.setProperty(name.trim(), it.value);
        }
        
        return paypalConfig; 
    }
    
    def getPaypalMap() {
        def paypalConfig = new java.util.HashMap<String, String>();
        //paypalConfig.findByCr
        def paypalRecords = Config.findAllByNameLike("payment.gateway.paypal.%");
        paypalRecords.each {
            def name = it.name.replaceAll("payment.gateway.paypal.", "");
            paypalConfig.put(name.trim(), it.value);
            
            //            log.error name + ":" + it.value
        }
                
        return paypalConfig;
    }
    
    
    def addManualPayment(paymentData) {
        
//        licenseValidationService.authorizeAction(FogPanelService.PAYMENT_ADD)
        
        def account = springSecurityService.currentUser.account
        def applicationUrl = ApplicationHolder.getApplication().config.faas.applicationUrl                
                       
        Date date = new Date();
        def time = new Timestamp(date.getTime())
        Config processingFeeAmount =  Config.findByName(Config.PAYMENT_PROCESSING_FEE_AMOUNT);                            
        Config paymentFeePercentage =  Config.findByName(Config.PAYMENT_PROCESSING_FEE_PERCENTAGE);
        Config paymentFeeType =  Config.findByName(Config.PAYMENT_PROCESSING_FEE_TYPE);
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
        try {
            CreditCard card = new CreditCard(); 
            if(paymentData.useDefaultCard == true || paymentData.useDefaultCard == "true") {
                
                if(account.cardVerified == false || account.cardVerified == "false") {
                    ArrayList<ArrayList<String>> responseList = new ArrayList<ArrayList<String>>();  
                    HashMap paymentItem = new HashMap();                
                    paymentItem.put("result", "CARD_VERIFIED_FALSE");
                    paymentItem.put("message", "Card is not verified, Add card to make payment using default card");
                    
                    responseList.add(paymentItem)
                    
                    return responseList
                }
                
                card.UUID = account.defaultCard
            } else if(paymentData.useDefaultCard == false || paymentData.useDefaultCard == "false") {
                def uuid = "CARD-A" + account.id;
                card.UUID = uuid
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
                    if(account.accountType.name() == "TRIAL") {
                        Config retailCreditLimit =  Config.findByName(Config.SIGNUP_TYPE_RETAIL_CREDIT_LIMIT);
                        account.creditLimit = Double.parseDouble(retailCreditLimit.value);  
                    }
                }
            }
            double processingFeeTotalAmount = (paymentData.amount/100)* Double.parseDouble(paymentFeePercentage.value) + Double.parseDouble(processingFeeAmount.value)
            
            def payment = new Payments() 
            payment.account = account;
            payment.paymentDate = time
            payment.paymentStatus = GeneralConstants.PAYMENT_RESULT_SUCCESS
            payment.gatewayName = Config.PAYMENT_GW_PAYPAL;
            
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
            //      bd.setScale(2, BigDecimal.ROUND_HALF_UP);   bd.setScale does not change bd
            payAmount = payAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
            
            def makePayment = makePayment(card, payAmount, "paymentData.description") 
            payment.paymentToken = makePayment.getUUID();
            payment.save(flush:true)
            if(!invoice) {
                if(account.creditLimit == 0) {
                    invoice = invoiceService.createDraftInvoice(account)                    
                }
            }
            invoice.payment = invoice.payment + paymentData.amount
            invoice.save(flush:true)
            
            if(account.accountType.name() == "TRIAL") {
                                                
                if(account.status.name() == "DISABLED") {
                                    
                }
            }
            
            if(account.totalPayable < account.creditLimit) {
                account.creditLimitLevel = ""; 
            }
            account.accountType = AccountType.values()[1]
            account.totalPaid = account.totalPaid + paymentData.amount;
            account.save(flush:true);
            
            log.info("payment for account: ${account.id}, amount: ${paymentData.amount}")
                        
            Map tempalteMap = notificationService.getDefaultMailTemplateMap()
            tempalteMap.put("processingFee", payment.processingFee.toString())
            tempalteMap.put("paymentAmount", payment.amount.toString())
            tempalteMap.put("totalAmount", payment.totalAmount.toString())
            notificationService.send(account.email.toString(), "addPayment.subject.ftl", tempalteMap, "addPayment.ftl")    
            
            invoiceService.updateInvoice(invoice)
            
            ArrayList<ArrayList<String>> responseList = new ArrayList<ArrayList<String>>();  
            HashMap paymentItem = new HashMap();                
            paymentItem.put("result", "OK");
            
            responseList.add(paymentItem)

            return responseList
            
            
        } catch (Exception ex) {
                       
            Map tempalteMap = notificationService.getDefaultMailTemplateMap()
            tempalteMap.put("processingFee", time.toString())
            tempalteMap.put("paymentAmount", time.toString())
            tempalteMap.put("totalAmount", time.toString())
            notificationService.send(account.email.toString(), "paymentFailed.subject.ftl", tempalteMap, "paymentFailed.ftl")    

            log.info("payment for account ${account.id}, amount: ${paymentData.amount} is faild due to ${ex}")
                        
            throw ex;
        }  
    }
      
    
    def count(forMonth) {
        def currency = ApplicationHolder.getApplication().config.billing.defaultCurrency
        def today = 0
        def month = 0
        def prevMonth = 0
        def dateFormatValue = configService.getDateFormat();     
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatValue);
        ArrayList countList = new ArrayList(); 
        HashMap countItem = new HashMap();  
        
        
        Calendar startDateCalendar = Calendar.getInstance();
        startDateCalendar.set(Calendar.DAY_OF_MONTH, 1); 
        Calendar currDateCalendar = Calendar.getInstance();
        Date fromDate = startDateCalendar.getTime()
        Date toDate = currDateCalendar.getTime()
        
        int year = currDateCalendar.get(Calendar.YEAR);
        Calendar previousMonthStartDateCalendar = Calendar.getInstance();
        previousMonthStartDateCalendar.set(Calendar.YEAR, year);
        previousMonthStartDateCalendar.set(Calendar.MONTH, 0);
        previousMonthStartDateCalendar.set(Calendar.DAY_OF_MONTH, 1);
        // set DATE to 1, so first date of previous month
        previousMonthStartDateCalendar.set(Calendar.DATE, 1);
        Date preFromDate = previousMonthStartDateCalendar.getTime()

        Calendar previousMonthEndDateCalendar = Calendar.getInstance();
        // set actual maximum date of previous month
        previousMonthEndDateCalendar.set(Calendar.YEAR, year);
        previousMonthEndDateCalendar.set(Calendar.MONTH, 11); // 11 = december
        previousMonthEndDateCalendar.set(Calendar.DAY_OF_MONTH, 31); // new years eve
        Date preToDate = previousMonthEndDateCalendar.getTime()
        
        Calendar dailyStartDateCalendar = Calendar.getInstance();
        dailyStartDateCalendar.set(Calendar.HOUR_OF_DAY, 0);
        dailyStartDateCalendar.set(Calendar.MINUTE, 0);
        dailyStartDateCalendar.set(Calendar.SECOND, 0);
        dailyStartDateCalendar.set(Calendar.MILLISECOND, 0);
        Date dailyFromDate = dailyStartDateCalendar.getTime()
        
        
        if(forMonth == "Today") {
            def todayMonthPaymentsCriteria = Payments.createCriteria()
            def todayPaymentList = todayMonthPaymentsCriteria.list {
                ge("paymentDate", dailyFromDate) and { le("paymentDate", toDate) } 
                order("id", "desc")
            }
        
            ArrayList paymentList = new ArrayList();  
            for(int i=0; i < todayPaymentList.size(); i++) { 
                def item = todayPaymentList[i]; 
                HashMap payment = new HashMap();                
                payment.put("id", item.id);
                payment.put("date", dateFormat.format(item.paymentDate).toString());
                payment.put("amount", item.amount);
                payment.put("account", item.account.accountIdentifier);
                payment.put("tokenNo", item.paymentToken); 
                payment.put("totalAmount", item.totalAmount); 
                payment.put("processingFee", item.processingFee); 
                 payment.put("status", item.paymentStatus); 
                payment.put("gateway", item.gatewayName); 
                paymentList.add(payment)
            }
            countItem.put("paymentItems", paymentList); 
        } else if(forMonth == "Current") {
            def currentMonthPaymentsCriteria = Payments.createCriteria()
            def currentMonthPayments = currentMonthPaymentsCriteria.list {
                ge("paymentDate", fromDate) and { le("paymentDate", toDate) } 
                order("id", "desc")
            }
            ArrayList currentMonthPaymentList = new ArrayList();  
            for(int i=0; i < currentMonthPayments.size(); i++) { 
                def currentMonthItem = currentMonthPayments[i]; 
                HashMap payment = new HashMap();                
                payment.put("id", currentMonthItem.id);
                payment.put("date", dateFormat.format(currentMonthItem.paymentDate).toString());
                payment.put("amount", currentMonthItem.amount);
                payment.put("account", currentMonthItem.account.accountIdentifier);
                payment.put("tokenNo", currentMonthItem.paymentToken); 
                payment.put("totalAmount", currentMonthItem.totalAmount); 
                payment.put("processingFee", currentMonthItem.processingFee); 
                payment.put("status", currentMonthItem.paymentStatus); 
                payment.put("gateway", currentMonthItem.gatewayName); 
                currentMonthPaymentList.add(payment)
            }
            countItem.put("currentMonthPaymentList", currentMonthPaymentList);
                        
        } else if(forMonth == "Pre") {
            
            def preMonthPaymentsCriteria = Payments.createCriteria()
            def preMonthPayments = preMonthPaymentsCriteria.list {
                ge("paymentDate", preFromDate) and { le("paymentDate", preToDate) } 
                order("id", "desc")
            }
            ArrayList preMonthPaymentList = new ArrayList();  
            for(int i=0; i < preMonthPayments.size(); i++) { 
                def preMonthItem = preMonthPayments[i]; 
                HashMap payment = new HashMap();                
                payment.put("id", preMonthItem.id);
                payment.put("date", dateFormat.format(preMonthItem.paymentDate).toString());
                payment.put("amount", preMonthItem.amount);
                payment.put("account", preMonthItem.account.accountIdentifier);
                payment.put("tokenNo", preMonthItem.paymentToken); 
                payment.put("totalAmount", preMonthItem.totalAmount); 
                payment.put("processingFee", preMonthItem.processingFee); 
                payment.put("status", preMonthItem.paymentStatus); 
                payment.put("gateway", preMonthItem.gatewayName); 
                preMonthPaymentList.add(payment)
            }
            countItem.put("preMonthPaymentList", preMonthPaymentList);
            
        }
        def currentMonthPaymentsCriteriaAmount = Payments.createCriteria()
        def currentMonthPaymentAmount = currentMonthPaymentsCriteriaAmount.list {
            ge("paymentDate", fromDate) and { le("paymentDate", toDate) }
            and {
                eq("paymentStatus", GeneralConstants.PAYMENT_RESULT_SUCCESS)
            }
            projections {
                sum("totalAmount")
            }
        }
        if(currentMonthPaymentAmount[0] == "null" ||currentMonthPaymentAmount[0] == null) {
            countItem.put("currentMonthPaymentAmount", 0);
        } else {
            countItem.put("currentMonthPaymentAmount", currentMonthPaymentAmount[0]);  
        } 
        def preMonthPaymentsCriteriaAmount = Payments.createCriteria()
        def preMonthPaymentAmount = preMonthPaymentsCriteriaAmount.list {
            ge("paymentDate", preFromDate) and { le("paymentDate", preToDate) }
            and {
                eq("paymentStatus", GeneralConstants.PAYMENT_RESULT_SUCCESS)
            }
            projections {
                sum("totalAmount")
            }
        }
        
        if(preMonthPaymentAmount[0] == "null" ||preMonthPaymentAmount[0] == null) {
            countItem.put("preMonthPaymentAmount", 0);
        } else {
            countItem.put("preMonthPaymentAmount", preMonthPaymentAmount[0]);  
        }
        
        def dailyPaymentsCriteriaAmount = Payments.createCriteria()
        def dailyPaymentAmount = dailyPaymentsCriteriaAmount.list {
            ge("paymentDate", dailyFromDate) and { le("paymentDate", toDate) }
            and {
                eq("paymentStatus", GeneralConstants.PAYMENT_RESULT_SUCCESS)
            }
            projections {
                sum("totalAmount")
            }
        }
        if(dailyPaymentAmount[0] == "null" ||dailyPaymentAmount[0] == null) {
            countItem.put("dailyPaymentAmount", 0);
        } else {
            countItem.put("dailyPaymentAmount", dailyPaymentAmount[0]);  
        }
        
        countItem.put("dailyDate", dateFormat.format(toDate).toString());
        countItem.put("currMonth", dateFormat.format(fromDate).toString() + " - " + dateFormat.format(toDate).toString());
        countItem.put("preMonth", dateFormat.format(preFromDate).toString() + " - " + dateFormat.format(preToDate).toString());
        countItem.put("currency", currency);
        countList.add(countItem);

        return countList;
        
    }
    
    def list(accountId) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        def account = springSecurityService.currentUser.account
        def role = springSecurityService.getPrincipal().getAuthorities()
        def payments = Payments.findAllWhere(account: account)
        def paymentsCriteria = Payments.createCriteria()
        if((accountId == "null" || accountId == null)) {
            if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {
                payments = paymentsCriteria.list {
                    eq("account",account)
                     order("id", "desc")
                }
            }
        } else if((accountId != "null" || accountId != null)) {            
            if(role.iterator().next() == "ROLE_ADMIN" ) {
                payments = paymentsCriteria.list {
                    eq("account", Account.get(accountId))   
                    order("id", "desc")
                }         
            }
        }
        
        ArrayList<ArrayList<String>> paymentList = new ArrayList<ArrayList<String>>();  
        for(int i=0; i < payments.size(); i++) { 
            def item = payments[i]; 
            HashMap payment = new HashMap();                
            payment.put("id", item.id);
            payment.put("date", dateFormat.format(item.paymentDate).toString());
            payment.put("amount", item.amount);
            payment.put("tokenNo", item.paymentToken ? item.paymentToken : "-"); 
            payment.put("totalAmount", item.totalAmount); 
            payment.put("processingFee", item.processingFee); 
            payment.put("status", item.paymentStatus); 
            payment.put("gateway", item.gatewayName); 
            paymentList.add(payment)
        }
        return paymentList
    }
    
    def chartData(accountId) {
        ArrayList<ArrayList<String>> paymentList = new ArrayList<ArrayList<String>>(); 
        def paymentsCriteria = Payments.createCriteria()
        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS"); 
        
        def payments = paymentsCriteria.list {
            eq("account", Account.get(accountId)) 
            and {
                eq("paymentStatus", GeneralConstants.PAYMENT_RESULT_SUCCESS)
            }
            maxResults(6)
        } 
        
        for(int i=0; i < payments.size(); i++) { 
            def item = payments[i]; 
            Date inputdate = formater2.parse(item.paymentDate.toString());
            Calendar cal = Calendar.getInstance();
            cal.setTime(inputdate)
            
            HashMap payment = new HashMap();                
            payment.put("id", item.id);
            payment.put("month", new SimpleDateFormat("MMM").format(cal.getTime()));
            payment.put("amount", item.amount);
            payment.put("tokenNo", item.paymentToken); 
            payment.put("totalAmount", item.totalAmount); 
            payment.put("processingFee", item.processingFee); 
            paymentList.add(payment)
        }
        return paymentList
        
        
    }
    
    def pendingPaymentList() {
        
        def currency = ApplicationHolder.getApplication().config.billing.defaultCurrency
        
        Calendar startDateCalendar = Calendar.getInstance();
        startDateCalendar.set(Calendar.DATE, 1) 
        startDateCalendar.set(Calendar.HOUR_OF_DAY, 00);
        startDateCalendar.set(Calendar.MINUTE, 00);
        startDateCalendar.set(Calendar.SECOND, 00);
        startDateCalendar.set(Calendar.MILLISECOND, 00);   

        Calendar toDateCalendar = Calendar.getInstance();
        toDateCalendar.set(Calendar.HOUR_OF_DAY, 23);
        toDateCalendar.set(Calendar.MINUTE, 59);
        toDateCalendar.set(Calendar.SECOND, 59);
        toDateCalendar.set(Calendar.MILLISECOND, 999);     
        
        Date fromDate = startDateCalendar.getTime()
        Date toDate = toDateCalendar.getTime()
        
        
        ArrayList<ArrayList<String>> paymentAccount = new ArrayList<ArrayList<String>>();  
        
        def InvoiceCriteria = Invoice.createCriteria()
        def pendingPaymentInvoiceList = InvoiceCriteria.list { 
            eq("status", InvoiceStatus.values()[6])
            and {
                ge("date", fromDate) and { le("date", toDate) }  
            }
        }
        
        for(def invoice: pendingPaymentInvoiceList) {
            if((invoice.previousBalance - invoice.payment) > 0) {
                
                HashMap item = new HashMap();  
                item.put("accountId",  invoice.account.id);
                item.put("account",  invoice.account.firstName);
                item.put("amount", Math.ceil((invoice.previousBalance - invoice.payment) * 100d) / 100d);
                item.put("currency", currency);               
                paymentAccount.add(item)
            }
        }
        
        return paymentAccount;
        
    }
    
    def cardValidityCheck() {
        
        def accountList = Account.findAllWhere(cardVerified: true)
        
        for(def account: accountList) {
            
            Calendar now = Calendar.getInstance();
            now.add(Calendar.MONTH, 1);
            int year = now.get(Calendar.YEAR);
            int month = now.get(Calendar.MONTH);   
                        
            if(((account.cardExpiryMonth - 1.0) == Double.parseDouble(Integer.toString(month)))  && (account.cardExpiryYear == Double.parseDouble(Integer.toString(year)))) {
               
                Map tempalteMap = notificationService.getDefaultMailTemplateMap()
                notificationService.send(account.email.toString(), "cardValidityCheck.subject.ftl", tempalteMap, "cardValidityCheck.ftl")    
            }
        }
        log.info("CardValidity Cron Completed");
    }
    
    def paymentChartData(accountId) {
        
        ArrayList<ArrayList<String>> paymentListArray = new ArrayList<ArrayList<String>>(); 
        
        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS"); 
        
        def user = springSecurityService.currentUser
        def role = springSecurityService.getPrincipal().getAuthorities()  
        
        def account;
        
        if(role.iterator().next() == "ROLE_ADMIN" ) {
            
            account = Account.get(accountId);
         
            //            c.add(Calendar.MONTH, -3);
            //             invoice.put("month", new SimpleDateFormat("MMM").format(cal.getTime()));

        } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {           
            account = user.account
        }
         
        for(def i = -5; i <= -1; i++) {
            
            Calendar preFromDateCalendar = Calendar.getInstance(); 
            preFromDateCalendar.add(Calendar.MONTH, i);
            preFromDateCalendar.set(Calendar.DAY_OF_MONTH, 1);  
            preFromDateCalendar.set(Calendar.HOUR_OF_DAY, 01);
            preFromDateCalendar.set(Calendar.MINUTE, 00);
            preFromDateCalendar.set(Calendar.SECOND, 00);
            preFromDateCalendar.set(Calendar.MILLISECOND, 001);                   
            Date preFromDate = preFromDateCalendar.getTime()
            
            Calendar preToDateCalendar = Calendar.getInstance(); 
            preToDateCalendar.add(Calendar.MONTH, i);
            preToDateCalendar.set(Calendar.DAY_OF_MONTH,
                preToDateCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            preToDateCalendar.set(Calendar.HOUR_OF_DAY, 23);
            preToDateCalendar.set(Calendar.MINUTE, 59);
            preToDateCalendar.set(Calendar.SECOND, 59);
            preToDateCalendar.set(Calendar.MILLISECOND, 999);                   
            Date preToDate = preToDateCalendar.getTime()
            
            
            
            def paymentCriteria = Payments.createCriteria()
            def totalAmount1 = paymentCriteria.list {
                eq("account", account)
                and {
                    ge("paymentDate", preFromDate) and { le("paymentDate", preToDate) }   
                }
                projections {
                    sum("totalAmount")
                }
            }
            
            HashMap pay2 = new HashMap();                
            pay2.put("monthNo", preFromDateCalendar.get(Calendar.MONTH));
            if(totalAmount1[0] == null || totalAmount1[0] == "null") { 
                pay2.put("payment", 0); 
            } else {
                pay2.put("payment", totalAmount1[0]); 
            }
            
            pay2.put("month", new SimpleDateFormat("MMM").format(preFromDateCalendar.getTime()));
            paymentListArray.add(pay2) 
            
            
        }
        
        Calendar fromDateCalendar = Calendar.getInstance(); 
        fromDateCalendar.set(Calendar.DAY_OF_MONTH, 1);  
        fromDateCalendar.set(Calendar.HOUR_OF_DAY, 01);
        fromDateCalendar.set(Calendar.MINUTE, 00);
        fromDateCalendar.set(Calendar.SECOND, 00);
        fromDateCalendar.set(Calendar.MILLISECOND, 001);                   
        Date fromDate = fromDateCalendar.getTime()

        Calendar toDateCalendar = Calendar.getInstance(); 
        toDateCalendar.set(Calendar.HOUR_OF_DAY, 23);
        toDateCalendar.set(Calendar.MINUTE, 59);
        toDateCalendar.set(Calendar.SECOND, 59);
        toDateCalendar.set(Calendar.MILLISECOND, 999);                   
        Date toDate = toDateCalendar.getTime()


        def curpaymentCriteria = Payments.createCriteria()
        def curtotalAmount1 = curpaymentCriteria.list {
            eq("account", account)
            and {
                ge("paymentDate", fromDate) and { le("paymentDate", toDate) }   
            }
            projections {
                sum("totalAmount")
            }
        }
        HashMap pay = new HashMap();       
        if(curtotalAmount1[0] == null || curtotalAmount1[0] == "null") { 
            pay.put("payment", 0);
        } else {
            pay.put("payment", curtotalAmount1[0]); 
        }

        pay.put("monthNo", fromDateCalendar.get(Calendar.MONTH));
        pay.put("month", new SimpleDateFormat("MMM").format(fromDateCalendar.getTime()));
        paymentListArray.add(pay) 
        
        
        return paymentListArray;
        
    }
    
    def manualEntryForPayment(paymentData) {
        
        def account = Account.get(paymentData.acNo)
        Payments payment = new Payments();
        
        payment.account = account;
        payment.amount = paymentData.amount;
        payment.paymentToken = "Manual-Token";
        payment.processingFee = 0;
        payment.paymentStatus = GeneralConstants.PAYMENT_RESULT_SUCCESS;
        payment.totalAmount = paymentData.amount;
        payment.paymentLoad = paymentData.desc;
        payment.gatewayName = "Manual";
        
        String transactionDateString = paymentData.transactionDate;
        DateFormat formatter; 
        Date date; 
        formatter = new SimpleDateFormat("dd/MM/yyyy");
        date = (Date)formatter.parse(transactionDateString);  
        payment.paymentDate = date;
        
        payment.save(flush: true);
             
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
        invoice.payment = invoice.payment + payment.amount
        invoice.save(flush:true)

        if(account.accountType.name() == "TRIAL") {

            if(account.status.name() == "DISABLED") {
                
            }

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
        account.save(flush:true);

        log.info("payment for account: ${account.id}, amount: ${payment.amount}")
        
        Map tempalteMap = notificationService.getDefaultMailTemplateMap()
        tempalteMap.put("processingFee", payment.processingFee.toString())
        tempalteMap.put("paymentAmount", payment.amount.toString())
        tempalteMap.put("totalAmount", payment.totalAmount.toString())
//        tempalteMap.put("user", User.findByUsername(account.email))
        notificationService.send(account.email.toString(), "addPayment.subject.ftl", tempalteMap, "addPayment.ftl")    
        invoiceService.updateInvoice(invoice)
//        invoiceService.sendBillingMailForAllAccount()
        
        if (!payment.save()) {
            payment.errors.allErrors.each { println it }
        }
    }
    
}
