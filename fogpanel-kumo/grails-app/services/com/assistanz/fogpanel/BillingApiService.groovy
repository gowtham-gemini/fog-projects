package com.assistanz.fogpanel

import java.text.DateFormat
import java.text.SimpleDateFormat
import com.assistanz.cloud.cloudstack.CloudStackServer
import com.assistanz.cloud.cloudstack.accounts.CSAccountService
import com.assistanz.cloud.cloudstack.limit.CSLimitService
import grails.transaction.Transactional
import org.codehaus.groovy.grails.commons.ApplicationHolder
import java.sql.Timestamp;
import org.apache.commons.logging.LogFactory;

@Transactional
class BillingApiService {

    static transactional = true
    private static final log = LogFactory.getLog(this) 
    
    InvoiceService invoiceService
    NotificationService notificationService 
    
    
    /**
     * Get final invoice info 
     * 
     */
    def getInvoice(username, fromdate, todate, pageNo, recordPerPage) {
        
//        def account = Account.findByUserName(username)
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat givenDateFormater = new SimpleDateFormat("yyyy-MM-dd");
        
        ArrayList<ArrayList<String>> pageArrayList = new ArrayList<ArrayList<String>>();   
        HashMap currentPage = new HashMap();                         
             
        ArrayList<ArrayList<String>> invoiceInfoList = new ArrayList<ArrayList<String>>();
        def currentPageNo = "";
        def currentRecordPerPage = "";
        
        if(pageNo) {
            if(pageNo == 0) {
                currentPageNo = 1
            } else {
                currentPageNo = Integer.parseInt(pageNo)
            }            
        } else {
            currentPageNo = 1;
        }            
        
        if(recordPerPage) {            
            currentRecordPerPage = Integer.parseInt(recordPerPage)
        } else {
            currentRecordPerPage = 100;
        } 
        Date fromDate;
        Date toDate;
        
        if(fromdate) {
             fromDate = givenDateFormater.parse(fromdate);
        }
        
        if(todate) {
            Date givenToDate = givenDateFormater.parse(todate);
            Calendar toDateCalendar = Calendar.getInstance(); 
            toDateCalendar.setTime(givenToDate);
            toDateCalendar.set(Calendar.HOUR_OF_DAY, 23);
            toDateCalendar.set(Calendar.MINUTE, 59);
            toDateCalendar.set(Calendar.SECOND, 59);
            toDateCalendar.set(Calendar.MILLISECOND, 999);                   
            toDate = toDateCalendar.getTime() 
        }
        
        def invoiceList = Invoice.withCriteria {
            if(username) {
               eq("account", findByUserName(username))  
            }
            and {
               eq("status", InvoiceStatus.values()[7]) 
            }
            and {
                 if(fromDate) {
                   ge("date", fromDate)  
                 }
                 and { 
                     if(toDate) {
                        le("date", toDate)  
                     }
                     
                 }  
             }
            order("id", "desc")
        }
        
        currentPage.put("page", currentPageNo)
        currentPage.put("recordPerPage", currentRecordPerPage)                  
        def noOfPages = Math.ceil(invoiceList.size() / currentRecordPerPage);            
        def pages = Math.round(noOfPages);            
        currentPage.put("totalPages", pages)     
        currentPage.put("totalRecords", invoiceList.size())    
                
        if(currentPageNo <= pages) {
            def minLimit = (currentPageNo * currentRecordPerPage) - currentRecordPerPage;
            def maxLimit = minLimit + currentRecordPerPage;  
            if(invoiceList.size() != 0) {            
                for(int i=minLimit; i < maxLimit; i++) {                 
                    def invoice = invoiceList[i];
                    if(invoice) {

                        HashMap invoiceData = new HashMap();
                        invoiceData.put("invoiceId", invoice.id);
                        invoiceData.put("account", invoice.account.userName);
                        invoiceData.put("currentUsage", invoice.currentDue);
                        invoiceData.put("payment", invoice.payment);
                        invoiceData.put("totalAmount", invoice.totalAmount);
                        invoiceData.put("previousBalance", invoice.previousBalance);
                        invoiceData.put("credit", invoice.credit);
                        invoiceData.put("refundAmount", invoice.refundAmount);
                        invoiceData.put("dueDate", dateFormat.format(invoice.dueDate).toString());
                        invoiceData.put("invoiceDate", dateFormat.format(invoice.date).toString());


                        def invoiceItemList = InvoiceItem.findAllByInvoice(invoice)

                        ArrayList<ArrayList<String>> invoiceItemInfoList = new ArrayList<ArrayList<String>>();

                        for(def invoiceItem : invoiceItemList) {
                            HashMap invoiceItemData = new HashMap();
                            invoiceItemData.put("billableItem", invoiceItem.billableItem.name);
                            invoiceItemData.put("billableItemId", invoiceItem.billableItem.id);
                            invoiceItemData.put("usageUnits", invoiceItem.usageUnits);
                            invoiceItemData.put("usageUnitPrice", invoiceItem.usageUnitPrice);
                            invoiceItemData.put("taxPercent", invoiceItem.taxPercent);
                            invoiceItemData.put("taxAmount", invoiceItem.taxAmount);
                            invoiceItemData.put("totalAmount", invoiceItem.totalAmount);
                            invoiceItemData.put("discountPercent", invoiceItem.discountPercent);
                            invoiceItemData.put("discountAmount", invoiceItem.discountAmount);
                            if(invoiceItem.zone) {
                                invoiceItemData.put("zone", invoiceItem.zone.aliasName);
                            } else {
                                invoiceItemData.put("zone", "-");
                            }
                            if(invoiceItem.billableItem == BillableItem.get(1)) {
                                def vm = VirtualMachine.findByReferenceId(invoiceItem.referenceItemId);
                                def computeOffer = ComputingOffer.findByOfferReferenceId(invoiceItem.referencePlanId)
                                if(vm && computeOffer) {

                                    invoiceItemData.put("name", vm.displayName);
                                    invoiceItemData.put("plan", computeOffer.name);
                                }

                            } else if(invoiceItem.billableItem == BillableItem.get(2)) {
                                def volume = Volume.findByVolumeReferenceId(invoiceItem.referenceItemId);
                                if(volume) {
                                    invoiceItemData.put("name", volume.name); 
                                    invoiceItemData.put("plan", volume.diskOffer.name);
                                }

                            } else if(invoiceItem.billableItem == BillableItem.get(3)) {
                                def snapshot = Snapshot.findBySnapshotReferenceId(invoiceItem.referenceItemId);
                                if(snapshot) {
                                    invoiceItemData.put("name", snapshot.name); 
                                    invoiceItemData.put("plan", snapshot.volume.name);
                                }


                            } else if(invoiceItem.billableItem == BillableItem.get(4)) {
                                def template = Template.findByTemplateReferenceId(invoiceItem.referenceItemId);
                                if(template) {
                                    invoiceItemData.put("name", template.name); 
                                    invoiceItemData.put("plan", "-");
                                }


                            } else if(invoiceItem.billableItem == BillableItem.get(5)) {
                                def vm = VirtualMachine.findByReferenceId(invoiceItem.referenceItemId);
                                def computeOffer = ComputingOffer.findByOfferReferenceId(invoiceItem.referencePlanId)
                                if(vm && computeOffer) {
                                    invoiceItemData.put("name", vm.displayName);
                                    invoiceItemData.put("plan", computeOffer.name);
                                }


                            } else if(invoiceItem.billableItem == BillableItem.get(6)) {

                            } else if(invoiceItem.billableItem == BillableItem.get(7)) {
                                invoiceItemData.put("name", invoiceItem.referenceItemName);
                                invoiceItemData.put("plan", "-");

                            } else if(invoiceItem.billableItem == BillableItem.get(8)) {
                                invoiceItemData.put("name", invoiceItem.referenceItemName);
                                invoiceItemData.put("plan", "-");

                            } else if(invoiceItem.billableItem == BillableItem.get(9)) {
                                invoiceItemData.put("name", invoiceItem.referenceItemName);
                                invoiceItemData.put("plan", "-");

                            } else if(invoiceItem.billableItem == BillableItem.get(10)) {
                                def vm = VirtualMachine.findByReferenceId(invoiceItem.referenceItemId);
                                def computeOffer = ComputingOffer.findByOfferReferenceId(invoiceItem.referencePlanId)
                                if(vm && computeOffer) {
                                    invoiceItemData.put("name", vm.displayName);
                                    invoiceItemData.put("plan", computeOffer.name);
                                }

                            } else if(invoiceItem.billableItem == BillableItem.get(11)) {
                                def vm = VirtualMachine.findByReferenceId(invoiceItem.referenceItemId);
                                def computeOffer = ComputingOffer.findByOfferReferenceId(invoiceItem.referencePlanId)
                                if(vm && computeOffer) {
                                    invoiceItemData.put("name", vm.displayName);
                                    invoiceItemData.put("plan", computeOffer.name);

                                }
                            } else if(invoiceItem.billableItem == BillableItem.get(12)) {
                                def vmip = VmIp.findByIpReferenceId(invoiceItem.referenceItemId);
                                if(vmip) {
                                    HashMap iItem = new HashMap();
                                    invoiceItemData.put("name", vmip.ipAddress);
                                    invoiceItemData.put("plan", "-");
                                }
                            }


                            invoiceItemInfoList.add(invoiceItemData)

                        }

                        invoiceData.put("invoiceItem", invoiceItemInfoList);
                        invoiceInfoList.add(invoiceData)

                    }
                }                                
            } else {
                currentPage.put("invoices", invoiceInfoList) 
            }  
        } else {
            currentPage.put("invoices", invoiceInfoList) 
        }                  
        currentPage.put("invoices", invoiceInfoList)                              
        pageArrayList.add(currentPage);
        return pageArrayList;       
    }
    
    
   /**
     * Get draft invoice info 
     * 
     */ 
   def getCurrentUsage(username) {
       
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                        
        def invoiceList = Invoice.withCriteria {
            if(username) {
                eq("account", Account.findByUserName(username))  
            }
            eq("status", InvoiceStatus.values()[6]) 
        }
        
         ArrayList<ArrayList<String>> currentUsageInfoList = new ArrayList<ArrayList<String>>();
        
        for(def invoice: invoiceList) {
                        
            ArrayList<ArrayList<String>> invoiceItemInfoList = new ArrayList<ArrayList<String>>();

            HashMap invoiceData = new HashMap();
            invoiceData.put("invoiceId", invoice.id);
            invoiceData.put("account", invoice.account.userName);
            invoiceData.put("currentUsage", invoice.currentDue);
            invoiceData.put("payment", invoice.payment);
            invoiceData.put("totalAmount", invoice.totalAmount);
            invoiceData.put("previousBalance", invoice.previousBalance);
            invoiceData.put("credit", invoice.credit);
            invoiceData.put("refundAmount", invoice.refundAmount);
            invoiceData.put("invoiceDate", dateFormat.format(invoice.date).toString());


            def invoiceItemList = InvoiceItem.findAllByInvoice(invoice)


            for(def invoiceItem : invoiceItemList) {
                HashMap invoiceItemData = new HashMap();
                invoiceItemData.put("billableItem", invoiceItem.billableItem.name);
                invoiceItemData.put("billableItemId", invoiceItem.billableItem.id);
                invoiceItemData.put("usageUnits", invoiceItem.usageUnits);
                invoiceItemData.put("usageUnitPrice", invoiceItem.usageUnitPrice);
                invoiceItemData.put("taxPercent", invoiceItem.taxPercent);
                invoiceItemData.put("taxAmount", invoiceItem.taxAmount);
                invoiceItemData.put("totalAmount", invoiceItem.totalAmount);
                invoiceItemData.put("discountPercent", invoiceItem.discountPercent);
                invoiceItemData.put("discountAmount", invoiceItem.discountAmount);
                if(invoiceItem.zone) {
                    invoiceItemData.put("zone", invoiceItem.zone.aliasName);
                } else {
                    invoiceItemData.put("zone", "-");
                }
                
                if(invoiceItem.billableItem == BillableItem.get(1)) {
                    def vm = VirtualMachine.findByReferenceId(invoiceItem.referenceItemId);
                    def computeOffer = ComputingOffer.findByOfferReferenceId(invoiceItem.referencePlanId)
                    if(vm && computeOffer) {

                        invoiceItemData.put("name", vm.displayName);
                        invoiceItemData.put("plan", computeOffer.name);
                    }

                } else if(invoiceItem.billableItem == BillableItem.get(2)) {
                    def volume = Volume.findByVolumeReferenceId(invoiceItem.referenceItemId);
                    if(volume) {
                        invoiceItemData.put("name", volume.name); 
                        invoiceItemData.put("plan", volume.diskOffer.name);
                    }

                } else if(invoiceItem.billableItem == BillableItem.get(3)) {
                    def snapshot = Snapshot.findBySnapshotReferenceId(invoiceItem.referenceItemId);
                    if(snapshot) {
                        invoiceItemData.put("name", snapshot.name); 
                        invoiceItemData.put("plan", snapshot.volume.name);
                    }


                } else if(invoiceItem.billableItem == BillableItem.get(4)) {
                    def template = Template.findByTemplateReferenceId(invoiceItem.referenceItemId);
                    if(template) {
                        invoiceItemData.put("name", template.name); 
                        invoiceItemData.put("plan", "-");
                    }


                } else if(invoiceItem.billableItem == BillableItem.get(5)) {
                    def vm = VirtualMachine.findByReferenceId(invoiceItem.referenceItemId);
                    def computeOffer = ComputingOffer.findByOfferReferenceId(invoiceItem.referencePlanId)
                    if(vm && computeOffer) {
                        invoiceItemData.put("name", vm.displayName);
                        invoiceItemData.put("plan", computeOffer.name);
                    }


                } else if(invoiceItem.billableItem == BillableItem.get(6)) {

                } else if(invoiceItem.billableItem == BillableItem.get(7)) {
                    invoiceItemData.put("name", invoiceItem.referenceItemName);
                    invoiceItemData.put("plan", "-");

                } else if(invoiceItem.billableItem == BillableItem.get(8)) {
                    invoiceItemData.put("name", invoiceItem.referenceItemName);
                    invoiceItemData.put("plan", "-");

                } else if(invoiceItem.billableItem == BillableItem.get(9)) {
                    invoiceItemData.put("name", invoiceItem.referenceItemName);
                    invoiceItemData.put("plan", "-");

                } else if(invoiceItem.billableItem == BillableItem.get(10)) {
                    def vm = VirtualMachine.findByReferenceId(invoiceItem.referenceItemId);
                    def computeOffer = ComputingOffer.findByOfferReferenceId(invoiceItem.referencePlanId)
                    if(vm && computeOffer) {
                        invoiceItemData.put("name", vm.displayName);
                        invoiceItemData.put("plan", computeOffer.name);
                    }

                } else if(invoiceItem.billableItem == BillableItem.get(11)) {
                    def vm = VirtualMachine.findByReferenceId(invoiceItem.referenceItemId);
                    def computeOffer = ComputingOffer.findByOfferReferenceId(invoiceItem.referencePlanId)
                    if(vm && computeOffer) {
                        invoiceItemData.put("name", vm.displayName);
                        invoiceItemData.put("plan", computeOffer.name);

                    }
                } else if(invoiceItem.billableItem == BillableItem.get(12)) {
                    def vmip = VmIp.findByIpReferenceId(invoiceItem.referenceItemId);
                    if(vmip) {
                        HashMap iItem = new HashMap();
                        invoiceItemData.put("name", vmip.ipAddress);
                        invoiceItemData.put("plan", "-");
                    }
                }

                invoiceItemInfoList.add(invoiceItemData)

            }

            invoiceData.put("invoiceItem", invoiceItemInfoList);
            currentUsageInfoList.add(invoiceData)
                  
        }    
        return currentUsageInfoList;
        
   }
   
    /**
     * Get payment info 
     * 
     */
    def getPayments(username, fromDate, toDate, pageNo, recordPerPage) {
        
        ArrayList<ArrayList<String>> pageArrayList = new ArrayList<ArrayList<String>>();   
        HashMap currentPage = new HashMap();         
        ArrayList<ArrayList<String>> paymentInfoList = new ArrayList<ArrayList<String>>();
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat givenDateFormater = new SimpleDateFormat("yyyy-MM-dd");
        
        def currentPageNo = "";
        def currentRecordPerPage = "";
        
        Date fDate;
        Date tDate;
         
        if(fromDate) {
            fDate = givenDateFormater.parse(fromDate);
        }
        
        if(toDate) {
            Date givenToDate = givenDateFormater.parse(toDate);
            Calendar toDateCalendar = Calendar.getInstance(); 
            toDateCalendar.setTime(givenToDate);
            toDateCalendar.set(Calendar.HOUR_OF_DAY, 23);
            toDateCalendar.set(Calendar.MINUTE, 59);
            toDateCalendar.set(Calendar.SECOND, 59);
            toDateCalendar.set(Calendar.MILLISECOND, 999);                   
            tDate = toDateCalendar.getTime() 
        }
        
        if(pageNo) {
            if(pageNo == 0) {
                currentPageNo = 1
            } else {
                currentPageNo = Integer.parseInt(pageNo)
            }            
        } else {
            currentPageNo = 1;
        }            
        
        if(recordPerPage) {            
            currentRecordPerPage = Integer.parseInt(recordPerPage)
        } else {
            currentRecordPerPage = 100;
        } 
        
        
        def paymentList = Payments.withCriteria {
            if(username) {
               eq("account", Account.findByUserName(username)) 
            }
            and {
                 if(fromDate) {
                   ge("paymentDate", fDate)  
                 }
                 and { 
                     if(toDate) {
                        le("paymentDate", tDate)  
                     }
                     
                 }  
             }
            and {
                eq("paymentStatus", GeneralConstants.PAYMENT_RESULT_SUCCESS)
            }
            order("id", "desc")
        }
        
        currentPage.put("page", currentPageNo)
        currentPage.put("recordPerPage", currentRecordPerPage)                  
        def noOfPages = Math.ceil(paymentList.size() / currentRecordPerPage);            
        def pages = Math.round(noOfPages);            
        currentPage.put("totalPages", pages)     
        currentPage.put("totalRecords", paymentList.size())    
        
        if(currentPageNo <= pages) {
            def minLimit = (currentPageNo * currentRecordPerPage) - currentRecordPerPage;
            def maxLimit = minLimit + currentRecordPerPage;  
            if(paymentList.size() != 0) {            
                for(int i=minLimit; i < maxLimit; i++) {                 
                    def payment = paymentList[i];
                    if(payment) {

                        HashMap paymentItem = new HashMap();                
                        paymentItem.put("id", payment.id);
                        paymentItem.put("date", dateFormat.format(payment.paymentDate).toString());
                        paymentItem.put("amount", payment.amount);
                        paymentItem.put("account", payment.account.userName);
                        paymentItem.put("tokenNo", payment.paymentToken); 
                        paymentItem.put("totalAmount", payment.totalAmount); 
                        paymentItem.put("processingFee", payment.processingFee); 
                        paymentInfoList.add(paymentItem)
            
                    }
                }                                
            } else {
                currentPage.put("payments", paymentInfoList) 
            }  
        } else {
            currentPage.put("payments", paymentInfoList) 
        }                  
        currentPage.put("payments", paymentInfoList)                              
        pageArrayList.add(currentPage);
        return pageArrayList;
    }
    
    /**
     * Add payment info
     * 
     */
    def addPayments(username, amount, date, paymentCode) {
        
        def account = Account.findByUserName(username)
        def invoice;
        DateFormat givenDateFormater = new SimpleDateFormat("yyyy-MM-dd");
        Date fDate = givenDateFormater.parse(date);
                
        if(account) {
            
            if(account.status.name() == "CANCELED") {
                def invoiceCriteria = Invoice.createCriteria()
                def invoiceList = invoiceCriteria.list {
                    eq("account", account) 
                    order("id", "desc")
                }  
                invoice = invoiceList[0];
            } else {
                invoice = Invoice.findWhere(account: account, status: InvoiceStatus.values()[6]);
            }
            
            if(invoice) {
                
                if(fDate < invoice.previousInvoiceDate || fDate > invoice.date) { 
                    
                    DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
                    DateFormat outPutFormater = new SimpleDateFormat("dd-MM-yyyy");
                    
                    Date toDate = formater2.parse(invoice.previousInvoiceDate.toString());
                    String toOutputDate = outPutFormater.format(toDate);
                    
                    Date fromDate = formater2.parse(invoice.date.toString());
                    String fromOutputDate = outPutFormater.format(fromDate);
                    log.info("Payment added for : ${account.id} by api service due to invalid date")
                    throw new InvalidDateRangeException("{'errorCode':'3300' ,'message':'Date must be between'"+toOutputDate+" to "+ fromOutputDate +"}");
                } 
                
                def payment = new Payments(); 
                payment.account = account;
                payment.paymentDate = fDate; 
                payment.processingFee = 0.0;
                payment.account = account;
                payment.manualPaymentCode = paymentCode;
                payment.amount = Double.parseDouble(amount);
                payment.totalAmount = Double.parseDouble(amount);
                payment.paymentToken = "Manual-" + paymentCode;
                payment.save(flush:true);
                
                invoice.payment = invoice.payment + Double.parseDouble(amount);
                invoice.save(flush:true);
                
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
                    account.accountType = AccountType.values()[1]

                }

                if(account.totalPayable < account.creditLimit) {
                    account.creditLimitLevel = ""; 
                }
                
                account.totalPaid = account.totalPaid + Double.parseDouble(amount);
                account.save(flush:true);
                
                invoiceService.updateDraftInvoice(invoice, account)
                invoiceService.sendBillingMailForAllAccount()
                
                ArrayList<ArrayList<String>> paymentInfo = new ArrayList<ArrayList<String>>();
                HashMap paymentItem = new HashMap();                
                paymentItem.put("result", "success");
                paymentInfo.add(paymentItem)
                
                log.info("Payment added for : ${account.id} , amount ${amount}, paymentCode:${paymentCode} by api service")
                
                return paymentInfo
                
            } else {
                log.info("Payment added for : ${account.id} by api service due to No Draft Invoice For This Account")
                throw new InvoiceNotFoundException("{'errorCode':'4100' ,'message':'No Draft Invoice For This Account'}");
            }
        } else {
            log.info("Payment added for : ${account.id} by api service due to Invalid field value")
            throw new InvalidFieldException("{'errorCode':'2000' ,'message':'Invalid field value'}");
        }
        
    }
}
