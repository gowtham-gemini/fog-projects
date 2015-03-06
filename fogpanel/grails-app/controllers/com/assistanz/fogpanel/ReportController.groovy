package com.assistanz.fogpanel

import java.text.DateFormat
import java.text.DecimalFormat
import grails.plugin.springsecurity.annotation.Secured
import grails.plugin.springsecurity.SpringSecurityService
import java.text.SimpleDateFormat
import org.springframework.context.MessageSource
import org.codehaus.groovy.grails.commons.ApplicationHolder

class ReportController {
    
    def springSecurityService;
    MessageSource messageSource
    ConfigService configService;
    @Secured(['ROLE_ADMIN', 'ROLE_FREE_USER', 'ROLE_PAID_USER'])
    def index() { 
        
        if(params.reportFor == "account") {
            reportForAccount(params);          
        } else {
            
        }
        
        def invoiceList = Invoice.findAll()
    
        if (params.format == "csv") {
            response.setHeader("Content-disposition", "attachment; filename=invoice-report.csv")
            def varContainingData = '"ID", "amount" \n';
            for (def invoice: invoiceList) {
                varContainingData += '"' + invoice.id + '", '
                varContainingData += '"' + invoice.totalAmount + '"'
                varContainingData += "\n"
            }
            render(contentType: "text/csv", text:varContainingData )
        } else {
            [invoiceList: invoiceList]
        }
    
    }
    
    @Secured(['ROLE_ADMIN', 'ROLE_FREE_USER', 'ROLE_PAID_USER'])
    def accountReport() {
        
        def language = "";
        def user = springSecurityService.currentUser 
        if(user.account.preferredLanguage == "" || user.account.preferredLanguage == "null" || user.account.preferredLanguage == null) {
            Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
            language = defaultLanguage.value
        } else {
            language = user.account.preferredLanguage
        }           
        def currency = ApplicationHolder.getApplication().config.billing.defaultCurrency    
        DateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
        def dateFormatCSVValue = configService.getInvoiceDateFormat();        
        SimpleDateFormat formater2 = new SimpleDateFormat(dateFormatCSVValue.toString() +" H:mm:ss.SS");
        
        def accountCriteria = Account.createCriteria()
        
        def accountList;
        def pattern = "###,##0.00"
        def currencyFormat = new DecimalFormat(pattern);
        if (params.format == "csv") {
            if(params.forDateRange == "ALL") {
                if(params.forAccount == "ALL") {
                    accountList = Account.findAllWhere(accountType: AccountType.values()[Integer.parseInt(params.accountType)])
                } else {
//                    def accountListArray = [];
//
//                    def accounts = params.accountList.split(",");
//                    for(def i=0; i < accounts.length; i++) {
//                        accountListArray[i] = Account.get(Integer.parseInt(accounts[i]));
//                    }
//
//                    accountList = accountCriteria.list {
//                        'in'("account", accountListArray)
//                    }
                }

            } else {
                Date fromDate = formater.parse(params.fromDate);
                Date givenToDate = formater.parse(params.toDate);
                Calendar toDateCalendar = Calendar.getInstance(); 
                toDateCalendar.setTime(givenToDate);
                toDateCalendar.set(Calendar.HOUR_OF_DAY, 23);
                toDateCalendar.set(Calendar.MINUTE, 59);
                toDateCalendar.set(Calendar.SECOND, 59);
                toDateCalendar.set(Calendar.MILLISECOND, 999);                   
                Date toDate = toDateCalendar.getTime()
                accountList = accountCriteria.list {
                    eq("accountType", AccountType.values()[Integer.parseInt(params.accountType)])
                    and{
                      ge("signUpDate", fromDate) and { le("signUpDate", toDate) }  
                    }
                }
            } 
            
            response.setHeader("Content-disposition", "attachment; filename=signup-report.csv")
            def varContainingData = '"'+messageSource.getMessage('common.indexNo', null, new Locale(language))+'", "'+messageSource.getMessage('common.signUpDate', null, new Locale(language))+'", "'+messageSource.getMessage('common.email', null, new Locale(language))+'", "'+messageSource.getMessage('common.status', null, new Locale(language))+'", "'+messageSource.getMessage('common.accountType', null, new Locale(language))+'", "'+messageSource.getMessage('common.cardVerified', null, new Locale(language))+'", "'+messageSource.getMessage('common.totalAmount', null, new Locale(language))+'", "'+messageSource.getMessage('common.totalPayable', null, new Locale(language))+'", "'+messageSource.getMessage('common.creditLimit', null, new Locale(language))+'"  \n';
            for (def account: accountList) {
                varContainingData += '"' + account.id + '", '
                varContainingData += '"' + formater2.format(account.signUpDate).toString() + '", '
                varContainingData += '"' + account.email + '", '
                varContainingData += '"' + account.status.name() + '", '
                varContainingData += '"' + account.accountType.name() + '", '
                varContainingData += '"' + account.cardVerified + '", '  
                varContainingData += '"' + currencyFormat.format(account.totalAmount) + '", '  
                varContainingData += '"' + currencyFormat.format(account.totalPayable) + '", '  
                varContainingData += '"' + currencyFormat.format(account.creditLimit) + '"'  
                varContainingData += "\n"
            }
            render(contentType: "text/csv", text:varContainingData)            
        } else {
        
            if(params.forDateRange == "ALL") {

                if(params.forAccount == "ALL") {
                    accountList = Account.findAllWhere(accountType: AccountType.values()[Integer.parseInt(params.accountType)])
                } else {
//                    def accountListArray = [];
//
//                    def accounts = params.accountList.split(",");
//                    for(def i=0; i < accounts.length; i++) {
//                        accountListArray[i] = Account.get(Integer.parseInt(accounts[i]))
//                    }
//
//                    accountList = accountCriteria.list {
//                        'in'("account", accountListArray)
//                    }
                }

            } else {
                                
                if(params.fromDate == "NaN/NaN/0NaN" || params.toDate == "NaN/NaN/0NaN") {
                    
                } else {
                    Date fromDate = formater.parse(params.fromDate);
                    Date givenToDate = formater.parse(params.toDate);
                    Calendar toDateCalendar = Calendar.getInstance(); 
                    toDateCalendar.setTime(givenToDate);
                    toDateCalendar.set(Calendar.HOUR_OF_DAY, 23);
                    toDateCalendar.set(Calendar.MINUTE, 59);
                    toDateCalendar.set(Calendar.SECOND, 59);
                    toDateCalendar.set(Calendar.MILLISECOND, 999);                   
                    Date toDate = toDateCalendar.getTime()
                    accountList = accountCriteria.list {
                        eq("accountType", AccountType.values()[Integer.parseInt(params.accountType)])
                        and{
                          ge("signUpDate", fromDate) and { le("signUpDate", toDate) }  
                        }
                    }
                }
                
            } 
            def dateFormatValue = configService.getDateFormat();
            SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatValue);
            Calendar toDateCalendar = Calendar.getInstance();               
            
            def fromDateString = ""
            def toDateString = ""
            
            if(params.fromDate == "undefined" || params.fromDate == " ") {
                fromDateString = ""
            } else {
                Date fromDate = formater.parse(params.fromDate);
                fromDateString = dateFormat.format(fromDate).toString();                
            }
            
            if(params.toDate == "undefined" || params.toDate == " ") {
                toDateString = ""
            } else {
                Date toDate = toDateCalendar.getTime()
                toDateString = dateFormat.format(toDate).toString();                
            }
            ArrayList accountArrayList = new ArrayList();             
            for(def account: accountList) {                 
                HashMap accountItem = new HashMap();                          
                accountItem.put("id", account.id)
                accountItem.put("signUpDate", dateFormat.format(account.signUpDate).toString())
                accountItem.put("email", account.email)
                accountItem.put("status", account.status.name())
                accountItem.put("cardVerified", account.cardVerified)
                accountItem.put("accountType", account.accountType.name())
                accountItem.put("totalAmount", account.totalAmount)
                accountItem.put("totalPayable", account.totalPayable)
                accountItem.put("creditLimit", account.creditLimit)    
                accountArrayList.add(accountItem)
            }
            [fdate: fromDateString, tdate: toDateString , accountList: accountArrayList, accountType:AccountType.values()[Integer.parseInt(params.accountType)].name(), currency:currency]
        }
    }
}
