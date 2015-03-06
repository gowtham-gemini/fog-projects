package com.assistanz.fogpanel

import grails.transaction.Transactional
import java.text.SimpleDateFormat
import com.assistanz.fogpanel.PullFailedUsageJob

@Transactional
class FailedUsageService {
    
    InvoiceService invoiceService
    UsageService usageService
        
    def startFailedUsageUpdateJob() {          
                                   
        PullFailedUsageJob.triggerNow([id:"failedUsageJob"])

        return "OK"
    }
    
    def generateFaildeUsageData(params) {
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
        // Find all Failed usage account
        def accountList = Account.withCriteria {               
            ne("accountType", AccountType.values()[2])                        
        }     
        
        Date startDate = params.startDate ? (Date)dateFormat.parse(params.startDate) : new Date()
        
        Calendar startDateCalendar = Calendar.getInstance();
        startDateCalendar.setTime(startDate) 
        startDateCalendar.set(Calendar.HOUR_OF_DAY, 00);
        startDateCalendar.set(Calendar.MINUTE, 00);
        startDateCalendar.set(Calendar.SECOND, 00);
        startDateCalendar.set(Calendar.MILLISECOND, 00);  
        startDate = startDateCalendar.getTime();
        
        Date endDate = new Date()
        
        Calendar endDateCalendar = Calendar.getInstance();
        endDateCalendar.setTime(endDate) 
        endDateCalendar.set(Calendar.HOUR_OF_DAY, 00);
        endDateCalendar.set(Calendar.MINUTE, 00);
        endDateCalendar.set(Calendar.SECOND, 00);
        endDateCalendar.set(Calendar.MILLISECOND, 00);  
        endDate = endDateCalendar.getTime();
               
        Console.print("account" + accountList)
        
        if((params.updateFinalInvoice == "true" || params.updateFinalInvoice == true)) { 
        
            for(; startDate < endDate ;)  {


                for(def account: accountList) {


                    def invoice = Invoice.executeQuery( "SELECT i FROM Invoice i WHERE i.account = ? AND  ? BETWEEN i.previousInvoiceDate AND i.date", [account, startDate],[max: 1]);
                    invoice  = invoice ? invoice[0] : null;
                    Console.print("startDate" + startDate)
                    Console.print("account" + account)
                    Console.print("invoice" + invoice)

                    if(invoice) {
                        
                         try {
                                                
                            usageService.usageResponseForAccount(account, dateFormat.format(startDate).toString(), invoice) 
                        
                        }  catch (Exception ex) {
                            Console.print("Usage Failed for account" + account.id + ":due to" + ex);    
                        } 
                    }
                }
                // increment date by one day
                Calendar c = Calendar.getInstance(); 
                c.setTime(startDate); 
                c.add(Calendar.DATE, 1);
                startDate = c.getTime();
            }
        
        }
        
    }
    
    
//    def generateUsageData(params) {
//        
//        Console.print(params);
//                
//        for(def account: accountList) {
//            
//            try {
//                
//                Date startDate = params.startDate ? (Date)dateFormat.parse(params.startDate) : account.lastUsageRunDate
//                def invoice;  
//                def previousInvoice;
//                Date loopDate = new Date()
//
//                for(; startDate < loopDate;) {
//
//
//                    Calendar loopDateCalendar = Calendar.getInstance();
//                    loopDateCalendar.setTime(loopDate) 
//                    loopDateCalendar.set(Calendar.HOUR_OF_DAY, 00);
//                    loopDateCalendar.set(Calendar.MINUTE, 00);
//                    loopDateCalendar.set(Calendar.SECOND, 00);
//                    loopDateCalendar.set(Calendar.MILLISECOND, 00);  
//                    loopDate = loopDateCalendar.getTime();
//
//                    Calendar startDateCalendar = Calendar.getInstance();
//                    startDateCalendar.setTime(startDate) 
//                    startDateCalendar.set(Calendar.HOUR_OF_DAY, 00);
//                    startDateCalendar.set(Calendar.MINUTE, 00);
//                    startDateCalendar.set(Calendar.SECOND, 00);
//                    startDateCalendar.set(Calendar.MILLISECOND, 00);  
//                    startDate = startDateCalendar.getTime();
//
//
//                    if((params.updateFinalInvoice == "true" || params.updateFinalInvoice == true) && (startDate < loopDate)) { 
//
//                        def invoiceCriteria = Invoice.createCriteria()
//                        previousInvoice = Invoice.get(invoice?.previousInvoiceId) 
////                        invoice = invoiceCriteria.get {
////                            eq("account", account)
////                            and {
////                                ge("previousInvoiceDate", startDate) and { le("date", startDate) }
////                            }
////                        }
//
//                        invoice = Invoice.executeQuery( "select i from Invoice i where i.account = ? and  ? between i.previousInvoiceDate and i.date", [account, startDate],[max: 1]);
//                        invoice  = invoice ? invoice[0] : null;
//                        Console.print("invoice" + invoice);
//                        Console.print("startDate" + startDate);
//                        Console.print("startDate" + startDate);
//                        Console.print("account" + account);
//
//                        loopDate = invoice?.date > new Date() ? new Date() : invoice?.date
//                        if(invoice && (startDate < new Date())) {
//
//                            usageService.usageResponseForAccount(account, dateFormat.format(startDate).toString(), invoice)
//
//                        } else {
//
//                            invoiceService.generateActiveInvoice(account, previousInvoice)
//
//
//                            invoice = Invoice.findWhere(account: account, status: InvoiceStatus.values()[6])
//
//                            usageService.usageResponseForAccount(account, dateFormat.format(startDate).toString(), invoice)
//
//                            // add generate invoice logic and update usage to that invoice
//
//                        }
//
//                    } else {
//
//                        if(params.usageUpdateType == "SKIP_DATA") {
//
//
//                            invoice = Invoice.findWhere(account: account, status: InvoiceStatus.values()[6])
//                            previousInvoice = Invoice.get(invoice?.previousInvoiceId) 
//
//                            // start data = least of current date or invoice date
//                            loopDate = invoice.date
//                            startDate = invoice.previousDate
//
//                            if(invoice && (loopDate > new Date())) {
//
//                                usageService.usageResponseForAccount(account, dateFormat.format(startDate).toString(), invoice)
//
//                            } else {
//
////                                // add generate invoice logic and update usage to that invoice
////                                invoiceService.generateActiveInvoice(account, previousInvoice)
////
////                                invoice = Invoice.findWhere(account: account, status: InvoiceStatus.values()[6])
////
////                                usageService.usageResponseForAccount(account, dateFormat.format(startDate).toString(), invoice)
//
//                            }
//
//                        } else {
//
//                            invoice = Invoice.findWhere(account: account, status: InvoiceStatus.values()[6])
//                            previousInvoice = Invoice.get(invoice?.previousInvoiceId) 
//
//                            startDate = account.lastUsageRunDate
//
//                            loopDate = invoice.date                                                     
//
//                            if(invoice && (loopDate > new Date())) {
//
//                                usageService.usageResponseForAccount(account, dateFormat.format(startDate).toString(), invoice)
//
//                            } else {
//
////                                // add generate invoice logic and update usage to that invoice
////                                invoiceService.generateActiveInvoice(account, previousInvoice)
////
////
////                                // add previous data to current draft invoice
////                                invoice = Invoice.findWhere(account: account, status: InvoiceStatus.values()[6])
////
////                                usageService.usageResponseForAccount(account, dateFormat.format(startDate).toString(), invoice)
//
//
//                            }
//                        }
//
//                    }
//
//                    // increment date by one day
//                    Calendar c = Calendar.getInstance(); 
//                    c.setTime(startDate); 
//                    c.add(Calendar.DATE, 1);
//                    startDate = c.getTime();
//
//                }
//                account.isUsageRunningFailed = false
//                account.save(flush: true)
//                                
//            }  catch (Exception ex) {
//                Console.print("Usage Failde for account" + account.id + ":due to" + ex);    
//            } 
//        }
//    }

    
    
    def usageUpdateFailed(jobid) {
        
        
        def job = AsynchronousJobs.get(jobid)
        
        
        def accountList = Account.findWhere(isUsageRunningFailed: true)
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
               
        job.status = JobStatus.valueOf("RUNNING")
        job.startedAt = new Date()
        job.save(flush: true)
        def usageFailureAccounts = "";        
        for(def account: accountList) {
             
            Console.print("account" + account)
                
            Date startDate = account.lastUsageRunDate
            Calendar startDateCalendar = Calendar.getInstance();
            startDateCalendar.setTime(startDate) 
            startDateCalendar.set(Calendar.HOUR_OF_DAY, 00);
            startDateCalendar.set(Calendar.MINUTE, 00);
            startDateCalendar.set(Calendar.SECOND, 00);
            startDateCalendar.set(Calendar.MILLISECOND, 00);  
            startDate = startDateCalendar.getTime();

            Date endDate = new Date()

            Calendar endDateCalendar = Calendar.getInstance();
            endDateCalendar.setTime(endDate) 
            endDateCalendar.set(Calendar.HOUR_OF_DAY, 00);
            endDateCalendar.set(Calendar.MINUTE, 00);
            endDateCalendar.set(Calendar.SECOND, 00);
            endDateCalendar.set(Calendar.MILLISECOND, 00);  
            endDate = endDateCalendar.getTime();
            
            for(; startDate < endDate;) {
                try { 
                                        
                    def invoice = Invoice.findWhere(account: account, status: InvoiceStatus.values()[6])
                    
                    Console.print("startDate" + startDate)
                    Console.print("invoice" + invoice)

                    usageService.usageResponseForAccount(account, dateFormat.format(startDate).toString(), invoice)

                    Date invoiceDate = invoice.date
                    Calendar invoiceDateCalendar = Calendar.getInstance();
                    invoiceDateCalendar.setTime(invoiceDate) 
                    invoiceDateCalendar.set(Calendar.HOUR_OF_DAY, 00);
                    invoiceDateCalendar.set(Calendar.MINUTE, 00);
                    invoiceDateCalendar.set(Calendar.SECOND, 00);
                    invoiceDateCalendar.set(Calendar.MILLISECOND, 00);  
                    invoiceDate = invoiceDateCalendar.getTime();

                    if(invoiceDate == startDate) {

                        def previousInvoice = Invoice.get(invoice?.previousInvoiceId) 
                        invoiceService.generateActiveInvoice(account, previousInvoice)

                        invoice = Invoice.findWhere(account: account, status: InvoiceStatus.values()[6])

                        usageService.usageResponseForAccount(account, dateFormat.format(startDate).toString(), invoice)

                    }
                
                }  catch (Exception ex) {
                    usageFailureAccounts += usageFailureAccounts.size() == 0 ? usageFailureAccounts : ",";
                    usageFailureAccounts += account.id
                                        
                    Console.print("Usage Failde for account" + account.id + ":due to" + ex);    
                } 
                                 
//              increment date by one day
                Calendar c = Calendar.getInstance(); 
                c.setTime(startDate); 
                c.add(Calendar.DATE, 1);
                startDate = c.getTime();
                
            }
            
        }
        
        if(usageFailureAccounts.size() == 0) {
           job.resultDescription = "" 
           job.status = JobStatus.valueOf("COMPLETED")
        } else {
           job.resultDescription =  "Usage failed for accouts:" + usageFailureAccounts
           job.status = JobStatus.valueOf("PARTIALLY_FAILED")
        }        
        job.completedAt = new Date()
        job.save(flush: true)
    }
    
    
}
