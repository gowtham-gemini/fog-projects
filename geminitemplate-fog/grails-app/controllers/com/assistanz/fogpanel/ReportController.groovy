package com.assistanz.fogpanel

import java.text.DateFormat
import java.text.SimpleDateFormat
import org.springframework.context.MessageSource
import grails.plugin.springsecurity.annotation.Secured
import grails.plugin.springsecurity.SpringSecurityService

class ReportController {
    
    def springSecurityService;
    MessageSource messageSource

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
        
        DateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
        
        def accountCriteria = Account.createCriteria()
        
        def accountList;
        
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
                varContainingData += '"' + formater2.parse(account.signUpDate.toString()) + '", '
                varContainingData += '"' + account.email + '", '
                varContainingData += '"' + account.status.name() + '", '
                varContainingData += '"' + account.accountType.name() + '", '
                varContainingData += '"' + account.cardVerified + '", '  
                varContainingData += '"' + account.totalAmount + '", '  
                varContainingData += '"' + account.totalPayable + '", '  
                varContainingData += '"' + account.creditLimit + '"'  
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
            [accountList: accountList, accountType:AccountType.values()[Integer.parseInt(params.accountType)].name()]
        }
    }
}
