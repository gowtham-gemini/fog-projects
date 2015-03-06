package com.assistanz.fogpanel

import java.text.SimpleDateFormat
import java.sql.Timestamp
import java.text.DateFormat
import com.assistanz.cloud.cloudstack.CloudStackServer
import com.assistanz.cloud.cloudstack.accounts.CSAccountService
import java.util.Collections;
import org.codehaus.groovy.grails.commons.ApplicationHolder
import grails.converters.deep.JSON
import org.apache.commons.logging.LogFactory;
import grails.transaction.Transactional

@Transactional
class InvoiceService {
    
    private static final log = LogFactory.getLog(this)
    def springSecurityService;
    NotificationService notificationService
    AccountService accountService
    LicenseValidationService licenseValidationService
    ConfigService configService;
    // List all aviliable invoice 
    def list(accountId) {
        
        def finalInvoiceList;
        def account = springSecurityService.currentUser.account
        def role = springSecurityService.getPrincipal().getAuthorities()    
        finalInvoiceList = Invoice.findAllWhere(account: account, status: InvoiceStatus.values()[7])
        def dateFormatValue = configService.getDateFormat();    
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatValue);
        def invoiceListItems = Invoice.createCriteria()
        if((accountId == "null" || accountId == null)) {              
                if(role.iterator().next() == "ROLE_ADMIN" ) {
                } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {
                    def admininvoiceCriteria = Invoice.createCriteria()                    
                    finalInvoiceList = admininvoiceCriteria.list {
                        eq("account", account)
                        and {
                            eq("status", InvoiceStatus.values()[7])                            
                        }
                        order("id", "desc")
                    }                                       
                }   
            } else if((accountId != "null" || accountId != null)) {                
                if(role.iterator().next() == "ROLE_ADMIN" ) {
                    def givenAccount = Account.get(accountId)
                    finalInvoiceList = invoiceListItems.list {
                        eq("account", Account.get(accountId))
                        order("id", "desc")
                    }
                } 
            } 
        ArrayList<ArrayList<String>> invoiceList = new ArrayList<ArrayList<String>>();            
        for(int i=0; i < finalInvoiceList.size(); i++) { 
            def item = finalInvoiceList[i]; 
            HashMap<String,String> invoice = new HashMap<String,String>();                 
                invoice.put("id", item.id);
                invoice.put("payments", item.payment);
                invoice.put("previousInvoiceDate", dateFormat.format(item.previousInvoiceDate).toString());
                invoice.put("invoiceDate", dateFormat.format(item.date).toString());
                invoice.put("totalAmount", item.totalAmount);
                invoice.put("previousBalance", item.previousBalance);
                invoice.put("referenceNumber", item.referenceNumber);
                invoice.put("currentDue", item.currentDue);
                invoice.put("status", item.status.name());
                
                invoiceList.add(invoice);
        }
        return invoiceList;
    
    }
    
    
    def dailyUsageChartData() {
        
        ArrayList<ArrayList<String>> usageItemList = new ArrayList<ArrayList<String>>();
        HashMap item = new HashMap();
        def dateFormatValue = configService.getInvoiceDateFormat();    
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatValue);
        
        Calendar currMonDateCalendar = Calendar.getInstance();
        currMonDateCalendar.set(Calendar.DATE, 1) 
        currMonDateCalendar.set(Calendar.HOUR_OF_DAY, 00);
        currMonDateCalendar.set(Calendar.MINUTE, 00);
        currMonDateCalendar.set(Calendar.SECOND, 00);
        currMonDateCalendar.set(Calendar.MILLISECOND, 00);  
        Date curMonStartDate = currMonDateCalendar.getTime()

        Calendar currDateCalendar = Calendar.getInstance();
        currDateCalendar.set(Calendar.HOUR_OF_DAY, 23);
        currDateCalendar.set(Calendar.MINUTE, 59);
        currDateCalendar.set(Calendar.SECOND, 59);
        currDateCalendar.set(Calendar.MILLISECOND, 999);  
        
        Date curMonToDate = currDateCalendar.getTime()
        def dailyUsageCostCriteria = DailyUsageCost.createCriteria()
        def dailyTable = dailyUsageCostCriteria.list { 
            ge("date", curMonStartDate) and { le("date", curMonToDate) } 
            order("date", "asc")
        }
        
        ArrayList usageItemDateList = new ArrayList();
        ArrayList usageItemCostList = new ArrayList();
        ArrayList refundCostList = new ArrayList();
        for(def dailyItem: dailyTable) {
                        
            Calendar cal = Calendar.getInstance();
            cal.setTime(dailyItem.date);
            def day = cal.get(Calendar.DAY_OF_MONTH);

            usageItemDateList.add(day.toString());
            usageItemCostList.add(dailyItem.cost)
            refundCostList.add(dailyItem.refund)
        }
        
        item.put("usageItemDateList", usageItemDateList)
        item.put("usageItemCostList", usageItemCostList)
        item.put("refundCostList", refundCostList) 
        usageItemList.add(item)
        
        return usageItemList;
        
        
    }
    
    def incomeForecast() {
        def currency = ApplicationHolder.getApplication().config.billing.defaultCurrency
        
        Calendar previousMonthStartDateCalendar = Calendar.getInstance();
        previousMonthStartDateCalendar.add(Calendar.MONTH, -1);
        // set DATE to 1, so first date of previous month
        previousMonthStartDateCalendar.set(Calendar.DATE, 1);
        previousMonthStartDateCalendar.set(Calendar.HOUR_OF_DAY, 00);
        previousMonthStartDateCalendar.set(Calendar.MINUTE, 00);
        previousMonthStartDateCalendar.set(Calendar.SECOND, 00);
        previousMonthStartDateCalendar.set(Calendar.MILLISECOND, 00);  
        Date preFromDate = previousMonthStartDateCalendar.getTime()

         Calendar previousMonthEndDateCalendar = Calendar.getInstance();
        // set actual maximum date of previous month
        previousMonthEndDateCalendar.add(Calendar.MONTH, -1);
        previousMonthEndDateCalendar.set(Calendar.DATE, previousMonthStartDateCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        previousMonthEndDateCalendar.set(Calendar.HOUR_OF_DAY, 23);
        previousMonthEndDateCalendar.set(Calendar.MINUTE, 59);
        previousMonthEndDateCalendar.set(Calendar.SECOND, 59);
        previousMonthEndDateCalendar.set(Calendar.MILLISECOND, 999);  
        
        Date preToDate = previousMonthEndDateCalendar.getTime()
        def pteInvoiceCriteria = Invoice.createCriteria()
        def previousMonth = pteInvoiceCriteria.list { 
            eq("status", InvoiceStatus.values()[7])
            and {
                ge("date", preFromDate) and { le("date", preToDate) } 
            }
            projections {
                sum("totalAmount")
            }
        }
        
        def amount = 0
                
        if(previousMonth[0] == "null" || previousMonth[0] == null) {
               amount = 0;
        } else {
           amount = previousMonth[0];  
        } 
        
        ArrayList<ArrayList<String>> incomeForecastList = new ArrayList<ArrayList<String>>();
        HashMap item = new HashMap();
        
        item.put("currency", currency)
        item.put("preMonth", Math.ceil(amount * 100d) / 100d)
        item.put("qut", Math.ceil((amount * 4) * 100d) / 100d)
        item.put("half", Math.ceil((amount * 6) * 100d) / 100d) 
        item.put("year", Math.ceil((amount * 12) * 100d) / 100d) 
        incomeForecastList.add(item)
        
        return incomeForecastList;
    }
    
    
    def billableItemChartData() {
        def currency = ApplicationHolder.getApplication().config.billing.defaultCurrency
        ArrayList<ArrayList<String>> billableItemList = new ArrayList<ArrayList<String>>();
        HashMap item = new HashMap();
        
        Calendar currMonDateCalendar = Calendar.getInstance();
        currMonDateCalendar.set(Calendar.DATE, 1) 
        currMonDateCalendar.set(Calendar.HOUR_OF_DAY, 00);
        currMonDateCalendar.set(Calendar.MINUTE, 00);
        currMonDateCalendar.set(Calendar.SECOND, 00);
        currMonDateCalendar.set(Calendar.MILLISECOND, 00);  
        Date curMonStartDate = currMonDateCalendar.getTime()

        Calendar currDateCalendar = Calendar.getInstance();
        currDateCalendar.set(Calendar.HOUR_OF_DAY, 23);
        currDateCalendar.set(Calendar.MINUTE, 59);
        currDateCalendar.set(Calendar.SECOND, 59);
        currDateCalendar.set(Calendar.MILLISECOND, 999);           
        
        Date curMonToDate = currDateCalendar.getTime()
        def InvoiceCriteria = Invoice.createCriteria()
        def curMonth = InvoiceCriteria.list { 
            ge("date", curMonStartDate) and { le("date", curMonToDate) } 
        }

        def compute = 0
        def disk = 0
        def snapshot = 0
        def template = 0
        def band = 0
        def recurring = 0
        
        def mVm = 0
        def mDisk = 0
        def mSnap = 0
        
        def vmSnap = 0
        def mVmSnap = 0
        
        def custom = 0
        
        for(def curMonInvoice: curMonth) {
            def invoiceItemCriteria = InvoiceItem.createCriteria()
            def invoiceItem = invoiceItemCriteria.list {
                eq("invoice", curMonInvoice)     
                projections {
                    groupProperty "billableItem"
                    sum "totalAmount"
                }
            }
            
            for(def bItem: invoiceItem) {
               
                if(bItem[0].name.toString() == BillableItem.get(1).name.toString()) { 
                    compute = compute + bItem[1]
                } else if(bItem[0].name.toString() == BillableItem.get(2).name.toString()) {
                    disk = disk + bItem[1] 
                } else if(bItem[0].name.toString() == BillableItem.get(3).name.toString()) {
                    snapshot = snapshot + bItem[1]
                } else if(bItem[0].name.toString() == BillableItem.get(4).name.toString()) {
                    template = template + bItem[1]
                } else if(bItem[0].name.toString() == BillableItem.get(5).name.toString()) {
                    band = band + bItem[1]
                } else if(bItem[0].name.toString() == BillableItem.get(8).name.toString()) {
                    custom = custom + bItem[1]
                } else if(bItem[0].name.toString() == BillableItem.get(9).name.toString()) {
                    recurring = recurring + bItem[1]   
                } else if(bItem[0].name.toString() == BillableItem.get(13).name.toString()) {
                    mVm = mVm + bItem[1]   
                } else if(bItem[0].name.toString() == BillableItem.get(14).name.toString()) {
                    mDisk = mDisk + bItem[1]   
                } else if(bItem[0].name.toString() == BillableItem.get(16).name.toString()) {
                    mVmSnap = mVmSnap + bItem[1]   
                } else if(bItem[0].name.toString() == BillableItem.get(17).name.toString()) {
                    vmSnap = vmSnap + bItem[1]   
                } 
            }
        }
        item.put("computeOffer", compute);        
        item.put("diskOffer", disk);   
        item.put("snapshot", snapshot);     
        item.put("template", template);   
        item.put("bandWidth", band);   
        item.put("custom", recurring);   
        item.put("recurring", custom);
        
        item.put("mVM", mVm);
        item.put("mDisk", mDisk);
        item.put("mSnap", mSnap);
        
        item.put("mVmSnap", mVmSnap);
        item.put("vmSnap", vmSnap);
        
        item.put("currency", currency);   
        billableItemList.add(item);
        
        return billableItemList;
    }
    
    def count(status) {
        
        def currency = ApplicationHolder.getApplication().config.billing.defaultCurrency
        try {
            def previousMonth;
            def daily;
            def monthly;
            def dateFormatValue = configService.getDateFormat();    
            SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatValue);
            ArrayList countList = new ArrayList(); 
            HashMap countItem = new HashMap();  

            Config billingTypeFixedEnabled =  Config.findByName(Config.BILLING_CYCLE_FIXED_ENABLED);
            if(billingTypeFixedEnabled.value == "true" || billingTypeFixedEnabled.value == true) {
            Config billingPeriodfixedDay =  Config.findByName(Config.BILLING_PERIOD_FIXED_DAY);
                def invoiceCriteria = Invoice.createCriteria()
                monthly = invoiceCriteria.list { 
                    eq("status", InvoiceStatus.values()[6])
                    projections {
                        sum("totalAmount")
                    }
                }
                Calendar currentMonthStartDateCalendar = Calendar.getInstance();
                currentMonthStartDateCalendar.set(Calendar.DATE, 1);
                
                Calendar currentMonthEndDateCalendar = Calendar.getInstance(); 
                currentMonthEndDateCalendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(billingPeriodfixedDay.value));
                       
                Date fromDate = currentMonthStartDateCalendar.getTime()
                Date toDate = currentMonthEndDateCalendar.getTime()
                
                
                Calendar previousMonthStartDateCalendar = Calendar.getInstance();
                previousMonthStartDateCalendar.add(Calendar.MONTH, -1);
                previousMonthStartDateCalendar.set(Calendar.HOUR_OF_DAY, 00);
                previousMonthStartDateCalendar.set(Calendar.MINUTE, 00);
                previousMonthStartDateCalendar.set(Calendar.SECOND, 00);
                previousMonthStartDateCalendar.set(Calendar.MILLISECOND, 00);  
                
                Calendar previousMonthEndDateCalendar = Calendar.getInstance(); 
                previousMonthEndDateCalendar.add(Calendar.MONTH, -1);
                previousMonthEndDateCalendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(billingPeriodfixedDay.value));   
                previousMonthEndDateCalendar.set(Calendar.HOUR_OF_DAY, 23);
                previousMonthEndDateCalendar.set(Calendar.MINUTE, 59);
                previousMonthEndDateCalendar.set(Calendar.SECOND, 59);
                previousMonthEndDateCalendar.set(Calendar.MILLISECOND, 999);  
                
                Date preFromDate = previousMonthStartDateCalendar.getTime()
                Date preToDate = previousMonthEndDateCalendar.getTime() 
               
                def finalInvoiceCriteria = Invoice.createCriteria()
                previousMonth = finalInvoiceCriteria.list { 
                    eq("status", InvoiceStatus.values()[7])
                    and {
                       ge("date", preFromDate) and { le("date", preToDate) }  
                    }
                    projections {
                        sum("totalAmount")
                    }
                }
                countItem.put("currMonth", dateFormat.format(fromDate).toString() + " - " + dateFormat.format(toDate).toString());
                countItem.put("preMonth", dateFormat.format(preFromDate).toString() + " - " + dateFormat.format(preToDate).toString());

                if(status == "DRAFT") {
                    ArrayList<ArrayList<String>> invoiceArrayList = new ArrayList<ArrayList<String>>();     
                    def draftInvoiceCriteriaList = Invoice.createCriteria()
                    def invoiceList = draftInvoiceCriteriaList.list { 
                        eq("status", InvoiceStatus.values()[6])
                        order("id", "desc")
//                        and {
//                            ge("date", fromDate) and { le("date", toDate) } 
//                        }
                    }
                    
                    for(int i=0; i < invoiceList.size(); i++) { 
                        def item = invoiceList[i]; 
                        HashMap<String,String> invoice = new HashMap<String,String>();                 
                        invoice.put("id", item.id);
                        invoice.put("payments", item.payment);
                        invoice.put("previousInvoiceDate", dateFormat.format(item.previousInvoiceDate).toString());
                        invoice.put("invoiceDate", dateFormat.format(item.date).toString());
                        invoice.put("totalAmount", item.totalAmount);
                        invoice.put("previousBalance", item.previousBalance);
                        invoice.put("referenceNumber", item.referenceNumber);
                        invoice.put("currentDue", item.currentDue);
                        invoice.put("status", item.status.name()); 
                        invoice.put("accountName", item.account.firstName); 
                        invoiceArrayList.add(invoice);                
                    }   
                    countItem.put("invoiceList", invoiceArrayList);
                } else if(status == "FINAL") {
                    ArrayList<ArrayList<String>> invoiceArrayList = new ArrayList<ArrayList<String>>();       
                    def finalInvoiceCriteriaList = Invoice.createCriteria()
                     def invoiceList = finalInvoiceCriteriaList.list { 
                        eq("status", InvoiceStatus.values()[7])
                        order("id", "desc")
//                        and {
//                            ge("date", preFromDate) and { le("date", preToDate) } 
//                        }
                    }
                    for(int i=0; i < invoiceList.size(); i++) { 
                    def item = invoiceList[i]; 
                    HashMap<String,String> invoice = new HashMap<String,String>();                 
                        invoice.put("id", item.id);
                        invoice.put("payments", item.payment);
                        invoice.put("previousInvoiceDate", dateFormat.format(item.previousInvoiceDate).toString());
                        invoice.put("invoiceDate", dateFormat.format(item.date).toString());
                        invoice.put("totalAmount", item.totalAmount);
                        invoice.put("previousBalance", item.previousBalance);
                        invoice.put("referenceNumber", item.referenceNumber);
                        invoice.put("currentDue", item.currentDue);
                        invoice.put("status", item.status.name()); 
                        invoice.put("accountName", item.account.firstName); 
                        invoiceArrayList.add(invoice);                
                    }
                    countItem.put("invoiceList", invoiceArrayList);
                }
            } else if(billingTypeFixedEnabled.value == "false" || billingTypeFixedEnabled.value == false) {
                Calendar startDateCalendar = Calendar.getInstance();
                startDateCalendar.set(Calendar.DAY_OF_MONTH, 1); 
                startDateCalendar.set(Calendar.HOUR_OF_DAY, 00);
                startDateCalendar.set(Calendar.MINUTE, 00);
                startDateCalendar.set(Calendar.SECOND, 00);
                startDateCalendar.set(Calendar.MILLISECOND, 00);  
                DateFormat formater = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy"); 
                Calendar currDateCalendar = Calendar.getInstance();
                currDateCalendar.set(Calendar.HOUR_OF_DAY, 23);
                currDateCalendar.set(Calendar.MINUTE, 59);
                currDateCalendar.set(Calendar.SECOND, 59);
                currDateCalendar.set(Calendar.MILLISECOND, 999);  
                Date fromDate = startDateCalendar.getTime()
                Date toDate = currDateCalendar.getTime()
                def invoiceCriteria = Invoice.createCriteria()
                monthly = invoiceCriteria.list {
                    eq("status", InvoiceStatus.values()[6])
                    and {
                        ge("date", fromDate) and { le("date", toDate) }  
                    }
                    projections {
                        sum("totalAmount")
                    }
                }
                
                Calendar previousMonthStartDateCalendar = Calendar.getInstance();
                previousMonthStartDateCalendar.add(Calendar.MONTH, -1);
                previousMonthStartDateCalendar.set(Calendar.HOUR_OF_DAY, 00);
                previousMonthStartDateCalendar.set(Calendar.MINUTE, 00);
                previousMonthStartDateCalendar.set(Calendar.SECOND, 00);
                previousMonthStartDateCalendar.set(Calendar.MILLISECOND, 00);  
                
                // set DATE to 1, so first date of previous month
                previousMonthStartDateCalendar.set(Calendar.DATE, 1);
                Date preFromDate = previousMonthStartDateCalendar.getTime()
                
                 Calendar previousMonthEndDateCalendar = Calendar.getInstance();
                // set actual maximum date of previous month
                previousMonthEndDateCalendar.add(Calendar.MONTH, -1);
                previousMonthEndDateCalendar.set(Calendar.DATE, previousMonthStartDateCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
                previousMonthEndDateCalendar.set(Calendar.HOUR_OF_DAY, 23);
                previousMonthEndDateCalendar.set(Calendar.MINUTE, 59);
                previousMonthEndDateCalendar.set(Calendar.SECOND, 59);
                previousMonthEndDateCalendar.set(Calendar.MILLISECOND, 999);  
                
                Date preToDate = previousMonthEndDateCalendar.getTime()
                def pteInvoiceCriteria = Invoice.createCriteria()
                previousMonth = pteInvoiceCriteria.list { 
                    eq("status", InvoiceStatus.values()[7])
                    and {
                        ge("date", preFromDate) and { le("date", preToDate) } 
                    }
                    projections {
                        sum("totalAmount")
                    }
                }
                countItem.put("currMonth", dateFormat.format(fromDate).toString() + " - " + dateFormat.format(toDate).toString());
                countItem.put("preMonth", dateFormat.format(preFromDate).toString() + " - " + dateFormat.format(preToDate).toString());
                
                if(status == "DRAFT") {
                    ArrayList<ArrayList<String>> invoiceArrayList = new ArrayList<ArrayList<String>>();     
                    def draftInvoiceCriteriaList = Invoice.createCriteria()
                    def invoiceList = draftInvoiceCriteriaList.list { 
                        eq("status", InvoiceStatus.values()[6])
                        order("id", "desc")
//                        and {
//                            ge("date", fromDate) and { le("date", toDate) }  
//                        }
                    }
                    for(int i=0; i < invoiceList.size(); i++) { 
                        def item = invoiceList[i]; 
                        HashMap<String,String> invoice = new HashMap<String,String>();                 
                        invoice.put("id", item.id);
                        invoice.put("payments", item.payment);
                        invoice.put("previousInvoiceDate", dateFormat.format(item.previousInvoiceDate).toString());
                        invoice.put("invoiceDate", dateFormat.format(item.date).toString());
                        invoice.put("totalAmount", item.totalAmount);
                        invoice.put("previousBalance", item.previousBalance);
                        invoice.put("referenceNumber", item.referenceNumber);
                        invoice.put("currentDue", item.currentDue);
                        invoice.put("status", item.status.name()); 
                        invoice.put("accountName", item.account.firstName); 
                        invoiceArrayList.add(invoice);                
                    } 
                    countItem.put("invoiceList", invoiceArrayList);
                } else if(status == "FINAL") {
                    ArrayList<ArrayList<String>> invoiceArrayList = new ArrayList<ArrayList<String>>();       
                    def finalInvoiceCriteriaList = Invoice.createCriteria()
                     def invoiceList = finalInvoiceCriteriaList.list { 
                        eq("status", InvoiceStatus.values()[7])
                        order("id", "desc")
//                        and {
//                          ge("date", preFromDate) and { le("date", preToDate) }    
//                        }
                    }
                    for(int i=0; i < invoiceList.size(); i++) { 
                    def item = invoiceList[i]; 
                    HashMap<String,String> invoice = new HashMap<String,String>();                 
                        invoice.put("id", item.id);
                        invoice.put("payments", item.payment);
                        invoice.put("previousInvoiceDate", dateFormat.format(item.previousInvoiceDate).toString());
                        invoice.put("invoiceDate", dateFormat.format(item.date).toString());
                        invoice.put("totalAmount", item.totalAmount);
                        invoice.put("previousBalance", item.previousBalance);
                        invoice.put("referenceNumber", item.referenceNumber);
                        invoice.put("currentDue", item.currentDue);
                        invoice.put("status", item.status.name()); 
                        invoice.put("accountName", item.account.firstName); 
                        invoiceArrayList.add(invoice);                
                    }
                    countItem.put("invoiceList", invoiceArrayList);
                }
            
            }
            Calendar startDateCalendar = Calendar.getInstance();
            startDateCalendar.add(Calendar.DATE, -1); 
            startDateCalendar.set(Calendar.HOUR_OF_DAY, 00);
            startDateCalendar.set(Calendar.MINUTE, 00);
            startDateCalendar.set(Calendar.SECOND, 00);
            startDateCalendar.set(Calendar.MILLISECOND, 00);  
            Calendar currDateCalendar = Calendar.getInstance();
            currDateCalendar.set(Calendar.HOUR_OF_DAY, 23);
            currDateCalendar.set(Calendar.MINUTE, 59);
            currDateCalendar.set(Calendar.SECOND, 59);
            currDateCalendar.set(Calendar.MILLISECOND, 999);  
            Date fromDate = startDateCalendar.getTime()
            Date toDate = currDateCalendar.getTime()
            
            def offeringUsageCriteria = OfferingUsage.createCriteria()
            daily = offeringUsageCriteria.get {         
                and { ge("startDate", fromDate) and { le("startDate", toDate) } }
                projections {
                    sum("cost")
                }
            }
            countItem.put("dailyDate", dateFormat.format(fromDate).toString() + " - " + dateFormat.format(toDate).toString());
            
            if(monthly[0] == "null" || monthly[0] == null) {
                countItem.put("monthly", 0); 
            } else {
                countItem.put("monthly", monthly[0]);  
            }
            if(previousMonth[0] == "null" || previousMonth[0] == null) {
                countItem.put("previousMonth", 0); 
            } else {
                countItem.put("previousMonth", previousMonth[0]);  
            } 
            if(daily == "null" ||daily == null) {
                countItem.put("daily", 0);
            } else {
                countItem.put("daily", daily);  
            } 
            countItem.put("currency", currency);  
            countList.add(countItem);
            
            return countList;
        } catch (Exception ex) { 
            ex.printStackTrace(System.err);
            throw ex;
        }
        
    }
    
    def customItem() {
        def currency = ApplicationHolder.getApplication().config.billing.defaultCurrency
        try {
            
            ArrayList countList = new ArrayList();  
            HashMap countItem = new HashMap();
            def customItems;
            def customItemsAmount;
            def customItemsTotalAmount = 0;
            def recurringItemTotalAmount = 0;
            ArrayList<ArrayList<String>> customItemArrayList = new ArrayList<ArrayList<String>>();              
            def dateFormatValue = configService.getDateFormat(); 
            SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatValue);
            Config billingTypeFixedEnabled =  Config.findByName(Config.BILLING_CYCLE_FIXED_ENABLED);
            if(billingTypeFixedEnabled.value == "true" || billingTypeFixedEnabled.value == true) {
            Config billingPeriodfixedDay =  Config.findByName(Config.BILLING_PERIOD_FIXED_DAY);
                def invoiceCriteria = Invoice.createCriteria()
                def monthlyInvoiceList = invoiceCriteria.list { 
                    eq("status", InvoiceStatus.values()[6])
                    
                }
                 
                for(def invoice: monthlyInvoiceList) {
                    def invoiceItemCriteriaCustom = InvoiceItem.createCriteria()
                    customItems = invoiceItemCriteriaCustom.list {
                        eq("billableItem", BillableItem.get(8))
                        and {
                            eq("invoice", invoice) 
                        }
                    }
                    
                    for(int i=0; i < customItems.size(); i++) { 
                    def item = customItems[i]; 
                    HashMap<String,String> cus = new HashMap<String,String>();                 
                        cus.put("id", item.id);
                        cus.put("account", item.invoice.account.accountIdentifier);
                        cus.put("amount", item.usageAmount);
                        cus.put("name", item.referenceItemName);
                        cus.put("taxAmount", item.taxAmount);
                        cus.put("totalAmount", item.totalAmount);
                        customItemArrayList.add(cus);                
                    }
                    def invoiceItemCriteriaCustomAmt1 = InvoiceItem.createCriteria()
                    customItemsAmount = invoiceItemCriteriaCustomAmt1.list {
                        eq("billableItem", BillableItem.get(8))
                        and {
                            eq("invoice", invoice) 
                        }
                        projections {
                            sum("totalAmount")
                        }
                    }
                    if(customItemsAmount[0] == null || customItemsAmount[0] == "null") {
                        customItemsTotalAmount = customItemsTotalAmount + 0
                    } else {
                       customItemsTotalAmount = customItemsTotalAmount + customItemsAmount[0]
                    }
                    
                    
                    def recurringItemCriteria = InvoiceItem.createCriteria()
                    def recurringItemsTotalAmount = recurringItemCriteria.list {
                        eq("billableItem", BillableItem.get(9))
                        and {
                            eq("invoice", invoice) 
                        }
                        projections {
                            sum("totalAmount")
                        }
                    }
                    if(recurringItemsTotalAmount[0] == null || recurringItemsTotalAmount[0] == "null") {
                        recurringItemTotalAmount = recurringItemTotalAmount + 0;
                    } else {
                        recurringItemTotalAmount = recurringItemTotalAmount +recurringItemsTotalAmount[0];
                       
                    }
                }
                
                Calendar currentMonthStartDateCalendar = Calendar.getInstance();
                currentMonthStartDateCalendar.set(Calendar.DATE, 1);
                currentMonthStartDateCalendar.set(Calendar.HOUR_OF_DAY, 00);
                currentMonthStartDateCalendar.set(Calendar.MINUTE, 00);
                currentMonthStartDateCalendar.set(Calendar.SECOND, 00);
                currentMonthStartDateCalendar.set(Calendar.MILLISECOND, 00);  
                
                Calendar currentMonthEndDateCalendar = Calendar.getInstance(); 
                currentMonthEndDateCalendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(billingPeriodfixedDay.value));
                currentMonthEndDateCalendar.set(Calendar.HOUR_OF_DAY, 23);
                currentMonthEndDateCalendar.set(Calendar.MINUTE, 59);
                currentMonthEndDateCalendar.set(Calendar.SECOND, 59);
                currentMonthEndDateCalendar.set(Calendar.MILLISECOND, 999);  
                                
                Date fromDate = currentMonthStartDateCalendar.getTime()
                Date toDate = currentMonthEndDateCalendar.getTime()
                                                
                countItem.put("currMonth", dateFormat.format(fromDate).toString() + " - " + dateFormat.format(toDate).toString());
                
            } else if(billingTypeFixedEnabled.value == "false" || billingTypeFixedEnabled.value == false) {
                Calendar startDateCalendar = Calendar.getInstance();
                startDateCalendar.set(Calendar.DAY_OF_MONTH, 1); 
                startDateCalendar.set(Calendar.HOUR_OF_DAY, 00);
                startDateCalendar.set(Calendar.MINUTE, 00);
                startDateCalendar.set(Calendar.SECOND, 00);
                startDateCalendar.set(Calendar.MILLISECOND, 00);  
                Date fromDate = startDateCalendar.getTime()
                
                Calendar currDateCalendar = Calendar.getInstance();
                currDateCalendar.set(Calendar.HOUR_OF_DAY, 23);
                currDateCalendar.set(Calendar.MINUTE, 59);
                currDateCalendar.set(Calendar.SECOND, 59);
                currDateCalendar.set(Calendar.MILLISECOND, 999);   
                Date toDate = currDateCalendar.getTime()
                
                def invoiceCriteria = Invoice.createCriteria()
                def monthlyInvoiceList = invoiceCriteria.list { 
                        order("id", "desc")
                }
                  
                for(def invoice: monthlyInvoiceList) {
                    def invoiceItemCriteriaCustom = InvoiceItem.createCriteria()
                    customItems = invoiceItemCriteriaCustom.list {
                        eq("billableItem", BillableItem.get(8))
                        and {
                            eq("invoice", invoice) 
                        }
                    }
                    
                    for(int i=0; i < customItems.size(); i++) { 
                    def item = customItems[i]; 
                    HashMap<String,String> cus = new HashMap<String,String>();                 
                        cus.put("id", item.id);
                        cus.put("account", item.invoice.account.accountIdentifier);
                        cus.put("amount", item.usageAmount);
                        cus.put("name", item.referenceItemName);
                        cus.put("taxAmount", item.taxAmount);
                        cus.put("totalAmount", item.totalAmount);
                        customItemArrayList.add(cus);                
                    }
                    def invoiceItemCriteriaCustomAmt = InvoiceItem.createCriteria()
                    customItemsAmount = invoiceItemCriteriaCustomAmt.list {
                        eq("billableItem", BillableItem.get(8))
                        and {
                            eq("invoice", invoice) 
                        }
                        projections {
                            sum("totalAmount")
                        }
                    }
                    if(customItemsAmount[0] == null || customItemsAmount[0] == "null") {
                        customItemsTotalAmount = customItemsTotalAmount + 0
                    } else {
                       customItemsTotalAmount = customItemsTotalAmount + customItemsAmount[0]
                    }
                    
                    
                    def recurringItemCriteria = InvoiceItem.createCriteria()
                    def recurringItemsTotalAmount = recurringItemCriteria.list {
                        eq("billableItem", BillableItem.get(9))
                        and {
                            eq("invoice", invoice) 
                        }
                        projections {
                            sum("totalAmount")
                        }
                    }
                    if(recurringItemsTotalAmount[0] == null || recurringItemsTotalAmount[0] == "null") {
                        recurringItemTotalAmount = recurringItemTotalAmount + 0;
                    } else {
                        recurringItemTotalAmount = recurringItemTotalAmount +recurringItemsTotalAmount[0];
                       
                    }
                }
                countItem.put("currMonth", dateFormat.format(fromDate).toString() + " - " + dateFormat.format(toDate).toString());

            }
            countItem.put("recurringItemsTotalAmount", Math.ceil((recurringItemTotalAmount) * 100d) / 100d);                         
            countItem.put("customItemList", customItemArrayList);  
            countItem.put("currency", currency);  
            countItem.put("customItemsTotalAmount", Math.ceil((customItemsTotalAmount) * 100d) / 100d);  
             
            countList.add(countItem);
            return countList;
        
        } catch (Exception ex) { 
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
    
    def chartData(accountId) { 
        ArrayList<ArrayList<String>> invoiceListArray = new ArrayList<ArrayList<String>>(); 
        def invoiceCriteria = Invoice.createCriteria()
        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS"); 
        
        def invoiceList
        def creditlimit;
        def user = springSecurityService.currentUser
        def role = springSecurityService.getPrincipal().getAuthorities()    
        if(role.iterator().next() == "ROLE_ADMIN" ) {
            invoiceList = invoiceCriteria.list {
                eq("account", Account.get(accountId)) 
                order("date", "desc")
                maxResults(6)
            }
            creditlimit = Account.get(accountId).creditLimit
        } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {
            invoiceList = invoiceCriteria.list {
                eq("account", user.account) 
                order("date", "desc")
                maxResults(6)
            } 
            creditlimit = user.account.creditLimit
        }
        
        
        for(int i=0; i < 6; i++) { 
            def item = invoiceList[i]; 
            if(item) {
                Date inputdate = formater2.parse(item.date.toString());
                Calendar cal = Calendar.getInstance();
                cal.setTime(inputdate)

                def invoiceItemCriteria = InvoiceItem.createCriteria()
                def totalAmount = invoiceItemCriteria.list {
                    eq("invoice", item)
                    and {
                        eq("billableItem", BillableItem.get(9))
                    }
                    projections {
                         sum("totalAmount")
                    }
                }
                def invoiceItemCriteriaCustom = InvoiceItem.createCriteria()
                def customTotalAmount = invoiceItemCriteriaCustom.list {
                    eq("invoice", item)
                    and {
                        eq("billableItem", BillableItem.get(8))
                    }
                    projections {
                         sum("totalAmount")
                    }
                }
                
                HashMap invoice = new HashMap();                
                invoice.put("id", item.id);
                invoice.put("monthNo", cal.get(Calendar.MONTH));
                invoice.put("payment", item.payment);
                invoice.put("month", new SimpleDateFormat("MMM").format(cal.getTime()));
                invoice.put("totalAmount", item.currentDue);
                invoice.put("creditLimit", item.account.creditLimit);
                if(totalAmount[0] == null || totalAmount[0] == "null" || totalAmount[0] == 0) {
                    invoice.put("recurringItemTotal", 0);
                } else {
                   invoice.put("recurringItemTotal", totalAmount[0]);         
                }
                if(customTotalAmount[0] == null || customTotalAmount[0] == "null" || customTotalAmount[0] == 0) {
                    invoice.put("customItemTotal", 0);
                } else {
                   invoice.put("customItemTotal", customTotalAmount[0]);         
                }
                invoiceListArray.add(invoice) 
            } else {
                HashMap invoice = new HashMap();                
                invoice.put("id", i);
                invoice.put("monthNo", i);
                invoice.put("payment", 0);
                invoice.put("month", 0);
                invoice.put("totalAmount", 0);
                invoice.put("creditLimit", creditlimit);
                invoice.put("recurringItemTotal", 0);
                invoice.put("customItemTotal", 0);
                invoiceListArray.add(invoice) 
            }

        }
        Collections.reverse(invoiceListArray)
                
        return invoiceListArray;
    }
    
    def getInvoice(id) {
        def invoiceItem = Invoice.get(id); 
        def dateFormatValue = configService.getInvoiceDateFormat();   
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatValue);
            ArrayList<ArrayList<String>> invoiceList = new ArrayList<ArrayList<String>>();
            HashMap<String,String> invoice = new HashMap<String,String>();                 
            invoice.put("id", invoiceItem.id);
            invoice.put("payments", invoiceItem.payment);
            invoice.put("previousInvoiceDate", dateFormat.format(invoiceItem.previousInvoiceDate).toString());
            invoice.put("invoiceDate", dateFormat.format(invoiceItem.date).toString());
            invoice.put("totalAmount", invoiceItem.totalAmount);
            invoice.put("previousBalance", invoiceItem.previousBalance);
            invoice.put("referenceNumber", invoiceItem.referenceNumber);
            invoice.put("currentDue", invoiceItem.currentDue);
            invoice.put("status", invoiceItem.status.name());

            invoiceList.add(invoice);
       
        return invoiceList;
        
    }

    def generateInvoice() {
        
            Calendar fromDateCalendar = Calendar.getInstance(); 
            fromDateCalendar.add(Calendar.DATE, -1);
            fromDateCalendar.set(Calendar.HOUR_OF_DAY, 00);
            fromDateCalendar.set(Calendar.MINUTE, 00);
            fromDateCalendar.set(Calendar.SECOND, 00);
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
                eq("status", InvoiceStatus.values()[6]) 
                and {
                   ge("date", fromDate) and { le("date", toDate) }   
                }
                
            }
            
            for(def invoice: invoiceList) {
                try {
                     generateActiveInvoice(invoice.account, invoice)
                } catch (Exception ex) {
                    log.info("InvoiceGenerate Failed For Account ${invoice.account.id} dew to ${ex}");
                    Console.print(ex);
                }
            }
            log.info("InvoiceGenerate Cron Completed ${new Date()}");
    }
    
    def generateInvoice(account) {
        
        def invoice = Invoice.findWhere(account: account, status: InvoiceStatus.values()[6])
        if(invoice) {
            generateActiveInvoice(account, invoice)
        }
    }
        
    def generateActiveInvoice(account, invoice) {
        
            def discount = null; // removed discount
            def billableItem = BillableItem.get(1)
            def invoiceItemList = InvoiceItem.findAllWhere(invoice: invoice, billableItem: billableItem)
            for(int o=0; o < invoiceItemList.size(); o++) {
               def invoiceItem = invoiceItemList[o]; 
               def computeOffer = ComputingOffer.findByOfferReferenceId(invoiceItem.referencePlanId)
    //           discount = getDiscount(account, invoice, computeOffer);
               updateInvoiceItem(discount, invoice, computeOffer, billableItem)
            }
            addInstanceMinCost(invoice, account)
        }
    
//    def getDiscount(account, invoice, computeOffer) {
//        
//        def billableItem = BillableItem.findByReferenceItemName("computingOffer")   
//        def discountCriteria = Discount.createCriteria()
//        def discountList = discountCriteria.list {
//            eq("subType", "INVOICE_DISCOUNT")
//            and {
//              le("startDate", invoice.date) and { ge("endDate", invoice.date)  } 
//              and{
//                eq("deleted", false)  
//              }
//            }
//        }
//        if(discountList.size() == 0) {
//            return null
//        } else {
//            for(int i=0; i < discountList.size(); i++) {
//                def discount = discountList[i]; 
//                if(discount.isAll == true || discount.isAll == "true") {
//                    return discount
////                    updateInvoiceItem("ALL",invoice, discount, null, null)
////                    break
//                } else {
//                    if(discount.isAllPlan == true || discount.isAllPlan == "true") {
//                        for(Iterator l = discountItem.accounts.iterator();l.hasNext();) { 
//                            def discountAccount = l.next(); 
//                            if(discountAccount == account) {
//                                return discount
////                                updateInvoiceItem("ALL",invoice, discount, null, null)
////                                break 
//                            }
//                        }
//                    } else {
//                        if(discount.isAllUser == true || discount.isAllUser == "true") {
//                            return discount                           
//                        } else {
//                            for(Iterator m = discount.computingOffers.iterator();m.hasNext();) { 
//                                def discountComputerOffer = m.next();
//                                if(discountComputerOffer == computeOffer) {
//                                    for(Iterator u = discountItem.accounts.iterator();u.hasNext();) { 
//                                        def discountAccount = u.next(); 
//                                        if(discountAccount == account) {
//                                            return discount
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//                } 
//            }
//        }
//    }
    
    def updateInvoiceItem(discount, invoice, plan, billableItem) {
        
        if(discount == null || discount == "null") { 
        
        } else { 
            
            def invoiceItemList = InvoiceItem.findAllWhere(invoice: invoice, billableItem: billableItem, referencePlanId: plan.offerReferenceId)

            for(int o=0; o < invoiceItem.size(); o++) {
               def invoiceItem = invoiceItemList[o]; 

                // sub total
                double subTotal =  Math.ceil(invoiceItem.usageAmount * 100d) / 100d //- discountAmount

                // tax amount
                double taxAmount = (subTotal/100)* invoiceItem.taxPercent
                invoiceItem.taxAmount =  Math.ceil(taxAmount * 100d) / 100d;     

                //total amount
                invoiceItem.totalAmount = Math.ceil((subTotal + invoiceItem.taxAmoun) * 100d) / 100d;
                invoiceItem.save(flush: true);
            }
        }
    }
    
    def addInstanceMinCost(invoice, account) {
        Config minHour =  Config.findByName(Config.INSTANCE_MINIMUM_COST_APPLICABLE_HOUR);
        def billableItem = BillableItem.get(1)
        def computeInvoiceItemList = InvoiceItem.findAllWhere(invoice: invoice, billableItem: billableItem) 
        for(def invoiceItem : computeInvoiceItemList ) {
            def offeringUsageCriteria = OfferingUsage.createCriteria()
            def results = offeringUsageCriteria.get {
                eq("usageType", UsageType.values()[2])
                and {
                  eq("offeringId", invoiceItem.referencePlanId)  and { eq("virtualMachineId", invoiceItem.referenceItemId) }
                  and { ge("startDate", invoice.previousInvoiceDate) and { le("startDate", invoice.date) } }
                }
                projections {
                    sum("hours")
                }
            }
            
            if(results == null || results == "null") {
                
            } else {
                double hours = results - invoiceItem.usageUnits;
                if(hours >= Double.parseDouble(minHour.value)) {
                    
                    def stopVmBillableItem = BillableItem.get(10)
                    if(stopVmBillableItem.enabled == true || stopVmBillableItem.enabled == "true") {
                        def computingOffer = ComputingOffer.findByOfferReferenceId(invoiceItem.referencePlanId);
                        def vm = VirtualMachine.findByReferenceId(invoiceItem.referenceItemId)
                        if(ComputingOfferZoneCost.findWhere(computingOffer: computingOffer, zone:vm.zone).minimumCost != 0) {
                            def newInvoiceItem = new InvoiceItem()
                            newInvoiceItem.billableItem = stopVmBillableItem
                            newInvoiceItem.invoice = invoice
                            newInvoiceItem.taxPercent = stopVmBillableItem.tax.percentage
                            newInvoiceItem.referencePlanId = invoiceItem.referencePlanId
                            newInvoiceItem.zone = vm.zone
                            newInvoiceItem.usageUnitPrice = ComputingOfferZoneCost.findWhere(computingOffer: computingOffer, zone:vm.zone).minimumCost
                            newInvoiceItem.usageUnits = Math.ceil(hours * 100d) / 100d;
                            double usageAmount = hours * newInvoiceItem.usageUnitPrice                
                            newInvoiceItem.usageAmount = Math.ceil(usageAmount * 100d) / 100d;   
                            double taxAmount = (newInvoiceItem.usageAmount/100)* newInvoiceItem.taxPercent
                            newInvoiceItem.taxAmount = Math.ceil(taxAmount * 100d) / 100d;     
                            newInvoiceItem.totalAmount = Math.ceil((newInvoiceItem.usageAmount + newInvoiceItem.taxAmount) * 100d) / 100d;     
                            newInvoiceItem.referenceItemName = "StoppedInstance"
                            newInvoiceItem.referenceItemId = invoiceItem.referenceItemId 
                            newInvoiceItem.save(flush: true);  
                            log.info("StoppedInstance Cost Added For Account:${account.id} by InvoiceGenerate Cron");
                        }                     
                    }
                }
            }
            addBandwidthCost(invoiceItem.referenceItemId, invoice, account)
        }
        updateInvoice(invoice, account);
    }
    
    def addBandwidthCost(referenceItemId, invoice, account) {
        def virtualMachine = VirtualMachine.findByReferenceId(referenceItemId);
       
        if(virtualMachine.zone.networkType == "Basic") {
            
            double readGB = virtualMachine.networkRead / 1048576
            double writeGB = virtualMachine.networkWrite / 1048576

            double totalGB = writeGB;

            double planBandwidth = 1.0 * virtualMachine.computingOffer.bandWidth
            if(totalGB > planBandwidth) {

                def bandWidthBillableItem = BillableItem.get(5)
                def computingOffer = virtualMachine.computingOffer
                if(bandWidthBillableItem.enabled == true || bandWidthBillableItem.enabled == "true") {
                    def bandwidthInvoiceItem = InvoiceItem.findWhere(invoice: invoice, referenceItemId:referenceItemId, referencePlanId:computingOffer.offerReferenceId);
                    if(bandwidthInvoiceItem) {
                       def newInvoiceItem = new InvoiceItem()
                        newInvoiceItem.billableItem = bandWidthBillableItem
                        newInvoiceItem.invoice = invoice
                        newInvoiceItem.taxPercent = bandWidthBillableItem.tax.percentage
                        newInvoiceItem.referencePlanId = computingOffer.offerReferenceId
                        newInvoiceItem.zone = virtualMachine.zone
                        def bandWidth = MiscellaneousOffer.get(1);
                        def bandWidthCost = MiscellaneousOfferZoneCost.findWhere(miscellaneousOffer :bandWidth, zone :virtualMachine.zone);
                        newInvoiceItem.usageUnitPrice = bandWidthCost.cost
                        newInvoiceItem.usageUnits = totalGB - planBandwidth
                        double usageAmount = newInvoiceItem.usageUnits * newInvoiceItem.usageUnitPrice                
                        newInvoiceItem.usageAmount = Math.ceil(usageAmount * 100d) / 100d;
                        double taxAmount = (newInvoiceItem.usageAmount/100)* newInvoiceItem.taxPercent
                        newInvoiceItem.taxAmount = Math.ceil(taxAmount * 100d) / 100d;    
                        newInvoiceItem.totalAmount = Math.ceil((newInvoiceItem.usageAmount + newInvoiceItem.taxAmount) * 100d) / 100d;   
                        newInvoiceItem.referenceItemName = "Bandwidth"
                        newInvoiceItem.referenceItemId = referenceItemId 
                        newInvoiceItem.save(flush: true); 
                        log.info("Bandwidth Cost Added For Account:${account.id} by InvoiceGenerate Cron");
                    }
                }
            }
        }
        virtualMachine.networkRead = 0.0
        virtualMachine.networkWrite = 0.0
        virtualMachine.save(flush: true);
    }
    
//    def addDiscountInvoiceItem(discount) {
//        def invoiceItem = new InvoiceItem()
//        invoiceItem.billableItem = computingBillableItem
//        invoiceItem.invoice = invoice
//        invoiceItem.taxPercent = computingBillableItem.tax.percentage
//        invoiceItem.referencePlanId = newVirtualMachine.computingOffer.offerReferenceId
//        invoiceItem.discount = discount;
//        invoiceItem.discountPercent = discount.value;
//        
//        invoiceItem.usageUnitPrice = ComputingOfferZoneCost.findWhere(computingOffer: newVirtualMachine.computingOffer).cost
//        invoiceItem.referenceItemName = "VirtualMachine"
//        invoiceItem.referenceItemId = newVirtualMachine.referenceId
//        invoiceItem.save(flush: true); 
//        
//        
//        
//    }

    
    def updateInvoice(invoice, account) {
       
         def invoiceItemList = InvoiceItem.findAllWhere(invoice : invoice)
            
            def c = InvoiceItem.createCriteria()
            def results = c.list {
                eq("invoice", invoice)
                projections {
                     sum("totalAmount")
                 }
            }
            
            if(results[0] == null || results[0] == "null") {
                invoice.currentDue =  0
            } else {
                invoice.currentDue =  Math.ceil(results[0] * 100d) / 100d
            }
            double subTotal = (invoice.currentDue + invoice.previousBalance + invoice.refundAmount) - (invoice.payment + account.credit)
            
            invoice.totalAmount =  Math.ceil(subTotal * 100d) / 100d;
            invoice.status = InvoiceStatus.values()[7]
            invoice.credit = account.credit
            
            Config dueDays =  Config.findByName(Config.PAYMENT_DUE_DAYS);
        
            def dateFormatValue = configService.getInvoiceDateFormat();   
            SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatValue);
        
            Calendar due = Calendar.getInstance();
            Date date = new Date();
            Date curDate =  new SimpleDateFormat(dateFormatValue)
              .parse(dateFormat.format(due.getTime()));
        
            due.setTime(curDate)
            due.add(Calendar.DATE, Integer.parseInt(dueDays.value));
            due.set(Calendar.HOUR_OF_DAY, 23);
            due.set(Calendar.MINUTE, 59);
            due.set(Calendar.SECOND, 59);
            due.set(Calendar.MILLISECOND, 990);  
            invoice.dueDate = due.getTime()
        
            account.totalPayable = Math.ceil(invoice.totalAmount * 100d) / 100d;  
            account.previousBalance = Math.ceil(invoice.totalAmount * 100d) / 100d;
            account.credit = 0
            account.totalAmount = Math.ceil((account.totalAmount + invoice.currentDue) * 100d) / 100d;
            
            invoice.save(flush: true);
            log.info("Final InvoiceGenerate For Account:${account.id} by InvoiceGenerate Cron");
                        
//            account.save(flush: true);
//            if (!account.save()) {
////                account.errors.allErrors.each { Console.print(it) }
//            }
            createNewDraftInvoice(account, invoice)
            
//            checkCardValidity(account, invoice)
    }
        
    def checkCardValidity(newInvoice, account) {
        
        Date date = new Date()
        def time = new Timestamp(date.getTime())
        
        Calendar invoiceDate = Calendar.getInstance();
        invoiceDate.setTime(newInvoice.date)
                
        int year = invoiceDate.get(Calendar.YEAR);
        int month = invoiceDate.get(Calendar.MONTH); 
        
        if(account.accountType.name() != "TRIAL") {
            
            Config creditCardEnabled =  Config.findByName(Config.CARD_PROCESSING_ENABLED);
            if(account.cardVerified == true || account.cardVerified == "true") {
                        
                if((creditCardEnabled.value == "true" || creditCardEnabled.value == true) && PaymentGateways.get(1).status == "ENABLE") {
                    
                    if(((account.cardExpiryMonth - 1.0) == Double.parseDouble(Integer.toString(month)))  && (account.cardExpiryYear == Double.parseDouble(Integer.toString(year)))) {

                        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
                        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
                        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret  


                        CloudStackServer server =
                                    new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);
                        CSAccountService csAccService = new CSAccountService(server);

                        def response = csAccService.disableAccount("false", optional)
                        account.cardVerified = false
                        account.status = Status.values()[3]
                        account.save(flush: true);

                        def dbLog = new Log()
                        dbLog.account = account
                        dbLog.user = User.findByUsername(account.accountIdentifier)
                        dbLog.date = time
                        dbLog.type = LogType.values()[2]
                        dbLog.mailSend = true
                        dbLog.description = "Account " + account.accountIdentifier + "is disabled due your credit card is about to expire"
                        dbLog.save(flush: true); 
                        log.info("Account  ${account.id} is disabled due your credit card is about to expire by InvoiceGenerate Cron");
                        
                        Map tempalteMap = notificationService.getDefaultMailTemplateMap()
                        tempalteMap.put("user", User.findByUsername(account.accountIdentifier))
                        notificationService.send(account.email.toString(), "cardValidityCheck.subject.ftl", tempalteMap, "cardValidityCheck.ftl")    
                        
                        
                } 
            }
        }
    }       
    addVirtualMachineInvoiceItem(newInvoice, account)
}
    
    
    def createNewDraftInvoice(account, invoice) {
        
        if(account.status.name() == "CANCELED" || account.status.name() == "CLOSED") {
                   
        } else {
            
            Date date = new Date();
            def currentTime = new Timestamp(date.getTime())

            Calendar c = Calendar.getInstance();
            //get billing type from config
            Config billingTypeFixedEnabled =  Config.findByName(Config.BILLING_CYCLE_FIXED_ENABLED);
            Config dueDays =  Config.findByName(Config.PAYMENT_DUE_DAYS);
            def invoiceDate;
            if(billingTypeFixedEnabled.value == "true" || billingTypeFixedEnabled.value == true) {
                
                Config billingPeriodfixedDay =  Config.findByName(Config.BILLING_PERIOD_FIXED_DAY);
                c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(billingPeriodfixedDay.value));   
                if(date.getDate() >= Integer.parseInt(billingPeriodfixedDay.value)) {
                    c.add(Calendar.MONTH, 1); 
                    c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(billingPeriodfixedDay.value)); 
                    invoiceDate = c.getTime()
                } else {
                    invoiceDate = c.getTime()
                }

            } else if(billingTypeFixedEnabled.value == "false" || billingTypeFixedEnabled.value == false) {

                Config billingPeriodDays =  Config.findByName(Config.BILLING_PERIOD_DAYS);
                // Adding days
                Calendar newDate = Calendar.getInstance();
                newDate.setTime(invoice.date)
                newDate.add(Calendar.DATE, Integer.parseInt(billingPeriodDays.value));
                newDate.set(Calendar.HOUR_OF_DAY, 23);
                newDate.set(Calendar.MINUTE, 59);
                newDate.set(Calendar.SECOND, 59);
                newDate.set(Calendar.MILLISECOND, 999);              
                invoiceDate = newDate.getTime()
            }

            Invoice newInvoice = new Invoice()
            newInvoice.status = InvoiceStatus.values()[6]
            newInvoice.account = account
            newInvoice.previousBalance = invoice.totalAmount
            newInvoice.previousInvoiceId = invoice.id
            newInvoice.referenceNumber = account.id + date.toString()
            newInvoice.date = invoiceDate
            // previous invoice date
            Calendar previousInvoiceDate = Calendar.getInstance();
            previousInvoiceDate.setTime(invoice.date)
            previousInvoiceDate.add(Calendar.DATE, 1);
            previousInvoiceDate.set(Calendar.HOUR_OF_DAY, 0);
            previousInvoiceDate.set(Calendar.MINUTE, 0);
            previousInvoiceDate.set(Calendar.SECOND, 0);
            previousInvoiceDate.set(Calendar.MILLISECOND, 000);    
            newInvoice.previousInvoiceDate = previousInvoiceDate.getTime();
            newInvoice.currency = ApplicationHolder.getApplication().config.billing.defaultCurrency
            Calendar due = Calendar.getInstance();
            Date invoiceDateString = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy")
                  .parse(invoiceDate.toString());

            due.setTime(invoiceDateString)
            due.add(Calendar.DATE, Integer.parseInt(dueDays.value));

    //        newInvoice.dueDate = due.getTime()
            newInvoice.customerName = account.firstName
            def address1 = new Address()
            address1.street = account.streetAddress
            address1.state = account.state.stateName
            address1.country = account.country.countryName
            address1.city = account.city
            address1.zip = account.zip
            newInvoice.billingAddress = address1
            def address = new Address()
            address.street = Config.findByName(Config.ORGANISATION_ADDRESS).value
            address.state = Config.findByName(Config.ORGANISATION_ADDRESS_STATE).value
            address.country = Config.findByName(Config.ORGANISATION_ADDRESS_COUNTRY).value
            address.city = Config.findByName(Config.ORGANISATION_ADDRESS_CITY).value
            address.zip = Config.findByName(Config.ORGANISATION_ADDRESS_ZIP).value             
            newInvoice.organisationAddress = address
            newInvoice.save(flush: true); 
            if (newInvoice.hasErrors()) {
                 newInvoice.errors.allErrors.each { Console.print(it) }
            }
            account.nextBillingData = invoiceDate
            account.lastBillingData = previousInvoiceDate.getTime()
            account.lastUsageRunDate = previousInvoiceDate.getTime()
            account.save(flush: true);
            log.info("New Draft Invoice Generated for account:${account.id} by InvoiceGenerate Cron");
            Calendar mailCal = Calendar.getInstance();                      
            Map tempalteMap = notificationService.getDefaultMailTemplateMap()
            tempalteMap.put("invoiceGeneratedDate", mailCal.getTime().toString())
            tempalteMap.put("invoiceAmount", invoice.totalAmount.toString())
            tempalteMap.put("nextBillingDate", account.nextBillingData.toString())
            tempalteMap.put("user", User.findByUsername(account.email))
            notificationService.send(account.email.toString(), "generateInvoice.subject.ftl", tempalteMap, "generateInvoice.ftl")     
                       
            
            def discountBillableItem = BillableItem.get(6)
            if(discountBillableItem.enabled == true || discountBillableItem.enabled == "true") {
                
                def discountItem = InvoiceItem.findWhere(billableItem:BillableItem.get(6), invoice:invoice)
                
                if(discountItem) {
                    
                    def discountUsedCount =  InvoiceItem.executeQuery("select count(invoiceItem.id) from Invoice as invoice, InvoiceItem as invoiceItem where invoiceItem.discount=? and invoice.account=? and invoice = invoiceItem.invoice", [discountItem.discount, newInvoice.account]);
                    
                    if(discountUsedCount[0] < discountItem.discount.billingCycles && discountItem.discount.deleted == false) {
                        
                        def invoiceItem = new InvoiceItem()
                        invoiceItem.billableItem = discountBillableItem
                        invoiceItem.invoice = newInvoice
                        invoiceItem.taxPercent = 0.0
                        invoiceItem.discount = discountItem.discount
        //                                invoiceItem.discountPercent = userDiscount.value
                        invoiceItem.usageUnitPrice = discountItem.discount.value

                        invoiceItem.referenceItemId = discountItem.discount.id.toString()
                        invoiceItem.referenceItemName = discountItem.discount.discountName
                        invoiceItem.save()
                    }
                }
            }
            checkCardValidity(newInvoice, account)
        }
    }
    
    def getDiscount(vm, newInvoice) {
        def invoiceItemCre = InvoiceItem.createCriteria()
        
        def oldInvoice = Invoice.get(newInvoice.previousInvoiceId);
        def invoiceItem = InvoiceItem.findWhere(referencePlanId: vm.computingOffer.offerReferenceId, referenceItemId: vm.referenceId, invoice: oldInvoice)
       
        if(invoiceItem) {
            
            if(invoiceItem.discount) {
                def discountUsedCount =  InvoiceItem.executeQuery("select count(invoiceItem.id) from Invoice as invoice, InvoiceItem as invoiceItem where invoiceItem.discount=? and invoice.account=? and invoice = invoiceItem.invoice", [invoiceItem.discount, oldInvoice.account]);
            
                if(discountUsedCount[0] < invoiceItem.discount.billingCycles) {
                    return invoiceItem.discount;
                }
            } else {
                return null;
            }
            
        } else {
            return null;
        }
        
    }
    
    def addVirtualMachineInvoiceItem(newInvoice, account) {
        
        def vmList = VirtualMachine.findAllWhere(deleted: false, owner: account); 
        for(int v=0; v < vmList.size(); v++) {
            def vm = vmList[v];   
            def computingBillableItem
            if(vm.billingType == "hourly") { 
                computingBillableItem = BillableItem.get(1) 
            } else {
                computingBillableItem = BillableItem.get(13) 
            }
            if(computingBillableItem.enabled == true || computingBillableItem.enabled == "true") {
                def invoiceItem = new InvoiceItem()
                invoiceItem.billableItem = computingBillableItem
                invoiceItem.invoice = newInvoice
                invoiceItem.taxPercent = computingBillableItem.tax.percentage
                invoiceItem.referencePlanId = vm.computingOffer.offerReferenceId
                
                def discount = getDiscount(vm, newInvoice);
                if(discount == null) {
                } else {
                    invoiceItem.discount = discount;
                    invoiceItem.discountPercent = discount.value; 
                }
                
                if(vm.billingType == "monthly") { 
                    
                    double monthlyAmount = ComputingOfferZoneCost.findWhere(computingOffer: vm.computingOffer, zone:vm.zone).cost * 720.00
                            
                    invoiceItem.usageUnitPrice = Math.ceil(monthlyAmount * 100d) / 100d;   
                    
                    invoiceItem.usageUnits = 1.0
                    invoiceItem.usageAmount = ComputingOfferZoneCost.findWhere(computingOffer: vm.computingOffer, zone:vm.zone).cost * 720.00
                    double taxAmount = (invoiceItem.usageAmount/100)* invoiceItem.taxPercent
                    invoiceItem.taxAmount = Math.ceil(taxAmount * 100d) / 100d;   

                    double disAmount = (invoiceItem.usageAmount/100)* invoiceItem.discountPercent
                    invoiceItem.discountAmount = Math.ceil(disAmount * 100d) / 100d;   

                    double totalAmt =  (invoiceItem.usageAmount + invoiceItem.taxAmount) - invoiceItem.discountAmount

                    invoiceItem.totalAmount = Math.ceil(totalAmt * 100d) / 100d;    
                                        
                    
                } else {            
                     invoiceItem.usageUnitPrice = ComputingOfferZoneCost.findWhere(computingOffer: vm.computingOffer, zone:vm.zone).cost 
                }
                invoiceItem.referenceItemName = "VirtualMachine"
                invoiceItem.referenceItemId = vm.referenceId
                invoiceItem.zone = vm.zone
                if(vm.template.cost != 0) {
                    if(vm.template.oneTimeChargeable == false) {
                        if(BillableItem.get(4).enabled == true || BillableItem.get(4).enabled == "true") {
                            def templateInvoiceItem = addTemplateCost(newInvoice, vm)
                            templateInvoiceItem.save(flush: true); 
                            if (!templateInvoiceItem.save()) {
                //                  setupCostInvoiceItem.errors.allErrors.each { Console.print(it) }
                            }
                        }
                    }
                }
                invoiceItem.save(flush: true);                 
            }
        }
        addIpInvoiceItem(newInvoice, account)
    }
    
    
    def addIpInvoiceItem(newInvoice, account) {
        
        def ipList = UserIPAddress.findAllWhere(account: account, removed: false, isSourceNat: false) 
        def ipOffer = MiscellaneousOffer.get(2);
        def ipBillableItem = BillableItem.get(12)
        
        if(ipBillableItem.enabled == true || ipBillableItem.enabled == "true") {
            for(def ip : ipList) {
                def newInvoiceItem = new InvoiceItem()
                newInvoiceItem.billableItem = ipBillableItem
                newInvoiceItem.invoice = newInvoice
                newInvoiceItem.taxPercent = ipBillableItem.tax.percentage
                newInvoiceItem.zone = ip.zone
                newInvoiceItem.usageUnitPrice = MiscellaneousOfferZoneCost.findWhere(miscellaneousOffer :ipOffer, zone :ip.zone).cost;
                newInvoiceItem.usageUnits = 1.0;
                double usageAmount = newInvoiceItem.usageUnitPrice                
                newInvoiceItem.usageAmount = Math.ceil(usageAmount * 100d) / 100d;   
                double taxAmount = (newInvoiceItem.usageAmount/100)* newInvoiceItem.taxPercent
                newInvoiceItem.taxAmount = Math.ceil(taxAmount * 100d) / 100d;     
                newInvoiceItem.totalAmount = Math.ceil((newInvoiceItem.usageAmount + newInvoiceItem.taxAmount) * 100d) / 100d;     
                newInvoiceItem.referenceItemName = "SecondaryIPCost"
                newInvoiceItem.referenceItemId = ip.publicIPAddress
                newInvoiceItem.save(flush: true); 

            }
        }
        addLoadBalancerInvoiceItem(newInvoice, account)
    }
    
    def addLoadBalancerInvoiceItem(newInvoice, account) {
        
        def lbOffer = MiscellaneousOffer.get(5);
        def lbBillableItem = BillableItem.get(18)
        
        if(lbBillableItem.enabled == true || lbBillableItem.enabled == "true") {

            def loadBalancerList = LoadBalancer.findAllWhere(account: account)
            for(def lb: loadBalancerList) {
                def newInvoiceItem = new InvoiceItem()
                newInvoiceItem.billableItem = lbBillableItem
                newInvoiceItem.invoice = newInvoice
                newInvoiceItem.taxPercent = lbBillableItem.tax.percentage
                newInvoiceItem.zone = lb.zone
                def offerCost =  MiscellaneousOfferZoneCost.findWhere(miscellaneousOffer :lbOffer, zone :lb.zone)
                newInvoiceItem.usageUnitPrice = offerCost ? offerCost.cost : 0.0;
                newInvoiceItem.usageUnits = 1.0;
                double usageAmount = newInvoiceItem.usageUnitPrice                
                newInvoiceItem.usageAmount = Math.ceil(usageAmount * 100d) / 100d;   
                double taxAmount = (newInvoiceItem.usageAmount/100)* newInvoiceItem.taxPercent
                newInvoiceItem.taxAmount = Math.ceil(taxAmount * 100d) / 100d;     
                newInvoiceItem.totalAmount = Math.ceil((newInvoiceItem.usageAmount + newInvoiceItem.taxAmount) * 100d) / 100d;     
                newInvoiceItem.referenceItemName = lb.name
                newInvoiceItem.referenceItemId = lb.referenceId
                newInvoiceItem.save(flush: true); 

            }   
        }
        addPortForwardingInvoiceItem(newInvoice, account)
    }
    
    def addPortForwardingInvoiceItem(newInvoice, account) {
        
        def pfOffer = MiscellaneousOffer.get(6);
        def pfBillableItem = BillableItem.get(19)
        
        if(pfBillableItem.enabled == true || pfBillableItem.enabled == "true") {

            def portForwardingList = PortForwarding.findAllWhere(account: account)
            for(def pf: portForwardingList) {
                def newInvoiceItem = new InvoiceItem()
                newInvoiceItem.billableItem = pfBillableItem
                newInvoiceItem.invoice = newInvoice
                newInvoiceItem.taxPercent = pfBillableItem.tax.percentage
                newInvoiceItem.zone = pf.zone
                def offerCost =  MiscellaneousOfferZoneCost.findWhere(miscellaneousOffer :pfOffer, zone :pf.zone)
                newInvoiceItem.usageUnitPrice = offerCost ? offerCost.cost : 0.0;
                newInvoiceItem.usageUnits = 1.0;
                double usageAmount = newInvoiceItem.usageUnitPrice                
                newInvoiceItem.usageAmount = Math.ceil(usageAmount * 100d) / 100d;   
                double taxAmount = (newInvoiceItem.usageAmount/100)* newInvoiceItem.taxPercent
                newInvoiceItem.taxAmount = Math.ceil(taxAmount * 100d) / 100d;     
                newInvoiceItem.totalAmount = Math.ceil((newInvoiceItem.usageAmount + newInvoiceItem.taxAmount) * 100d) / 100d;     
                newInvoiceItem.referenceItemName = pf.name
                newInvoiceItem.referenceItemId = pf.referenceId
                newInvoiceItem.save(flush: true); 

            }   
        }
        addVPNConnectionInvoiceItem(newInvoice, account)
        
    }
    
    def addVPNConnectionInvoiceItem(newInvoice, account) {
        
        def vpnOffer = MiscellaneousOffer.get(7);
        def vpnBillableItem = BillableItem.get(20)
        if(vpnBillableItem.enabled == true || vpnBillableItem.enabled == "true") {
            
            def vpnConnectionList = VPNConnection.findAllWhere(deleted: false, account:account);
            
            for(def vpnConnection: vpnConnectionList) {
                 
                def newInvoiceItem = new InvoiceItem()
                newInvoiceItem.billableItem = vpnBillableItem
                newInvoiceItem.invoice = newInvoice
                newInvoiceItem.taxPercent = vpnBillableItem.tax.percentage
                newInvoiceItem.zone = vpnConnection.zone
                def offerCost = MiscellaneousOfferZoneCost.findWhere(miscellaneousOffer :vpnOffer, zone :vpnConnection.zone)
                newInvoiceItem.usageUnitPrice = offerCost ? offerCost.cost : 0.0;
                newInvoiceItem.usageUnits = 1.0;
                double usageAmount = newInvoiceItem.usageUnits                
                newInvoiceItem.usageAmount = Math.ceil(usageAmount * 100d) / 100d;   
                double taxAmount = (newInvoiceItem.usageAmount/100)* newInvoiceItem.taxPercent
                newInvoiceItem.taxAmount = Math.ceil(taxAmount * 100d) / 100d;     
                newInvoiceItem.totalAmount = Math.ceil((newInvoiceItem.usageAmount + newInvoiceItem.taxAmount) * 100d) / 100d;     
                newInvoiceItem.referenceItemName = "VPN Connection"
                newInvoiceItem.referenceItemId = vpnConnection.referenceId
                newInvoiceItem.save(flush: true); 
            }

        }
        addVMSnapshotInvoiceItem(newInvoice, account)
    }
        
    def addVMSnapshotInvoiceItem(newInvoice, account) {
        
        def vmList = VirtualMachine.findAllWhere(deleted: false, owner: account); 
        
        for(def VMSnap: vmList) {
            
            def vmSnapshotBillableItem = BillableItem.get(17) 
//            if(VMSnap.billingType == "monthly") { 
//                vmSnapshotBillableItem = BillableItem.get(17) 
//            } else {
//                vmSnapshotBillableItem= BillableItem.get(17) 
//            }

            if(vmSnapshotBillableItem.enabled == true || vmSnapshotBillableItem.enabled == "true") {
                def VMSnapList = VMSnapshot.findAllWhere(virtualMachine:VMSnap, deleted: false);
                if(VMSnapList) {
                    def invoiceItem = new InvoiceItem()
                    invoiceItem.billableItem = vmSnapshotBillableItem
                    invoiceItem.taxPercent = vmSnapshotBillableItem.tax.percentage
                    invoiceItem.zone = VMSnap.zone
                    if(VMSnap.billingType == "monthly") {

    //                    double monthlyAmount =  MiscellaneousOfferZoneCost.findWhere(miscellaneousOffer: MiscellaneousOffer.get(4), zone: VMSnap.zone).cost * 720.00
    //
    //                    invoiceItem.usageUnitPrice = Math.ceil(monthlyAmount * 100d) / 100d;   
    //                    invoiceItem.usageUnits = 1.0
    //                    invoiceItem.usageAmount = Math.ceil(monthlyAmount * 100d) / 100d;   
    //                    double taxAmount = (invoiceItem.usageAmount/100)* invoiceItem.taxPercent
    //                    invoiceItem.taxAmount = Math.ceil(taxAmount * 100d) / 100d;   
    //
    //                    double totalAmt =  (invoiceItem.usageAmount + invoiceItem.taxAmount)
    //
    //                    invoiceItem.totalAmount = Math.ceil(totalAmt * 100d) / 100d;   

                         invoiceItem.usageUnitPrice = MiscellaneousOfferZoneCost.findWhere(miscellaneousOffer: MiscellaneousOffer.get(4), zone: VMSnap.zone).cost   

                    } else {
                         invoiceItem.usageUnitPrice = MiscellaneousOfferZoneCost.findWhere(miscellaneousOffer: MiscellaneousOffer.get(4), zone: VMSnap.zone).cost
                    }

                    invoiceItem.invoice = newInvoice
                    invoiceItem.referenceItemName = "VM Snapshot"
                    invoiceItem.referenceItemId = VMSnap.referenceId
                    invoiceItem.save(flush: true);
                }
            }
        }
        addVolumeInvoiceItem(newInvoice, account)
    }
    
    def addTemplateCost(invoice, newVirtualMachine) {
        
        if(newVirtualMachine.template.cost != 0) {
            def invoiceItem = new InvoiceItem()
            def templateBillableItem = BillableItem.get(4)
            invoiceItem.billableItem = templateBillableItem
            invoiceItem.invoice = invoice
            invoiceItem.taxPercent = templateBillableItem.tax.percentage
            invoiceItem.referencePlanId = newVirtualMachine.referenceId
            invoiceItem.usageUnits = 1.0
            invoiceItem.usageUnitPrice = newVirtualMachine.template.cost;
            invoiceItem.usageAmount = invoiceItem.usageUnitPrice
            invoiceItem.zone = newVirtualMachine.zone
            double taxAmount = (invoiceItem.usageAmount/100)* invoiceItem.taxPercent
            invoiceItem.taxAmount = Math.ceil(taxAmount * 100d) / 100d;     
            invoiceItem.totalAmount = Math.ceil((invoiceItem.usageAmount + invoiceItem.taxAmount) * 100d) / 100d; 
            invoiceItem.referenceItemName = "Template"
            invoiceItem.referenceItemId = newVirtualMachine.template.templateReferenceId;

            return invoiceItem;
        }
    }
    
    
    def addVolumeInvoiceItem(newInvoice, account) {
        
        def volumeList = Volume.findAllWhere(deleted: false, owner: account);
        
        for(int v=0; v < volumeList.size(); v++) {
                def volume = volumeList[v]; 
                def diskBillableItem
                if(volume.billingType == "monthly") { 
                    diskBillableItem = BillableItem.get(14) 
                } else {
                    diskBillableItem= BillableItem.get(2) 
                }
                
                 if(diskBillableItem.enabled == true || diskBillableItem.enabled == "true") {
                    if(volume.type == "DATADISK") {
                        def invoiceItem = new InvoiceItem()
                        invoiceItem.billableItem = diskBillableItem
                        invoiceItem.taxPercent = diskBillableItem.tax.percentage                  
                        invoiceItem.invoice = newInvoice
                        invoiceItem.zone = volume.zone
                                            
                        if(volume.billingType == "monthly") {
                                        
                            double volsize = Double.parseDouble(volume.customDiskSize) 

                            double monthlyAmount = DiskOfferZoneCost.findWhere(diskOffer: volume.diskOffer, zone: volume.zone).cost * 720.00 * volsize

                            invoiceItem.usageUnitPrice = Math.ceil(monthlyAmount * 100d) / 100d;   
                            invoiceItem.usageUnits = 1.0
                            invoiceItem.usageAmount = Math.ceil(monthlyAmount * 100d) / 100d;   
                            double taxAmount = (invoiceItem.usageAmount/100)* invoiceItem.taxPercent
                            invoiceItem.taxAmount = Math.ceil(taxAmount * 100d) / 100d;   

                            double totalAmt =  (invoiceItem.usageAmount + invoiceItem.taxAmount)

                            invoiceItem.totalAmount = Math.ceil(totalAmt * 100d) / 100d;   
                        } else {
                            invoiceItem.usageUnitPrice = DiskOfferZoneCost.findWhere(diskOffer: volume.diskOffer, zone: volume.zone).cost
                        }
                        invoiceItem.referenceItemName = "Volume"
                        invoiceItem.referenceItemId = volume.volumeReferenceId
                        invoiceItem.referencePlanId = volume.diskOffer.diskOfferReferenceId
                        invoiceItem.save(flush: true);    
                    }
                 }
            }
        addSnapshotInvoiceItem(newInvoice, account)
    }
    
    def addSnapshotInvoiceItem(newInvoice, account) {
        
        def user = User.findByAccount(account)
        def snapshotList = Snapshot.findAllWhere(deleted: false, user: user);  
        for(int v=0; v < snapshotList.size(); v++) {
            def snapshot = snapshotList[v]; 


            def snapShotBillableItem
            if(snapshot.billingType == "monthly") { 
                snapShotBillableItem= BillableItem.get(15) 
            } else {
                snapShotBillableItem= BillableItem.get(3) 
            }

            if(snapShotBillableItem.enabled == true || snapShotBillableItem.enabled == "true") {
                def invoiceItem = new InvoiceItem()
                invoiceItem.billableItem = snapShotBillableItem
                invoiceItem.taxPercent = snapShotBillableItem.tax.percentage
                
                if(snapshot.billingType == "monthly") {

                    double volsize = snapshot.size

                    double monthlyAmount =  invoiceItem.usageUnitPrice = MiscellaneousOfferZoneCost.findWhere(miscellaneousOffer: MiscellaneousOffer.get(3), zone: snapshot.volume.zone).cost * 720.00 * volsize

                    invoiceItem.usageUnitPrice = Math.ceil(monthlyAmount * 100d) / 100d;   
                    invoiceItem.usageUnits = 1.0
                    invoiceItem.usageAmount = Math.ceil(monthlyAmount * 100d) / 100d;   
                    double taxAmount = (invoiceItem.usageAmount/100)* invoiceItem.taxPercent
                    invoiceItem.taxAmount = Math.ceil(taxAmount * 100d) / 100d;   

                    double totalAmt =  (invoiceItem.usageAmount + invoiceItem.taxAmount)

                    invoiceItem.totalAmount = Math.ceil(totalAmt * 100d) / 100d;   
                } else {
                     invoiceItem.usageUnitPrice = MiscellaneousOfferZoneCost.findWhere(miscellaneousOffer: MiscellaneousOffer.get(3), zone: snapshot.volume.zone).cost
                }
                
                invoiceItem.invoice = newInvoice
                invoiceItem.zone = snapshot.volume.zone
                invoiceItem.referenceItemName = "Snapshot"
                invoiceItem.referenceItemId = snapshot.snapshotReferenceId
                invoiceItem.save(flush: true);  
            }
        } 
        addRecurringItemsForNextInvoice(newInvoice, account)
    }
    
    def addRecurringItemsForNextInvoice(newInvoice, account) {
        
        def recurringItemList = RecurringItem.findAllByAccount(account)
        def billableItem = BillableItem.get(9)
        def invoice = Invoice.findWhere(account: account, status: InvoiceStatus.values()[6])
        if(billableItem.enabled == true || billableItem.enabled == "true") {
            for(int recurring = 0; recurring < recurringItemList.size(); recurring++) {
                def recurringItem = recurringItemList[recurring]; 

                if(recurringItem.billingCycles == 0) {
                    def invoiceItem = new InvoiceItem() 
                    invoiceItem.billableItem = billableItem
                    invoiceItem.referenceItemName = recurringItem.name
                    invoiceItem.referenceItemId = Long.toString(recurringItem.id) 
                    invoiceItem.invoice = invoice
                    invoiceItem.usageUnits = 1.0
                    invoiceItem.usageUnitPrice = recurringItem.amount
                    invoiceItem.usageAmount = recurringItem.amount
                    invoiceItem.taxPercent = recurringItem.taxPercent
                    invoiceItem.taxAmount = Math.ceil(recurringItem.taxAmount * 100d) / 100d;     
                    invoiceItem.totalAmount = Math.ceil(recurringItem.totalAmount * 100d) / 100d;       
                    invoiceItem.save(flush: true);
                    recurringItem.uses = recurringItem.uses + 1  
                    recurringItem.save(flush: true);
                } else {
                    if(recurringItem.uses < recurringItem.billingCycles) {
                        def invoiceItem = new InvoiceItem() 
                        invoiceItem.billableItem = billableItem
                        invoiceItem.referenceItemName = recurringItem.name
                        invoiceItem.referenceItemId = Long.toString(recurringItem.id) 
                        invoiceItem.invoice = invoice
                        invoiceItem.usageUnits = 1.0
                        invoiceItem.usageUnitPrice = recurringItem.amount
                        invoiceItem.usageAmount = recurringItem.amount
                        invoiceItem.taxPercent = recurringItem.taxPercent
                        invoiceItem.taxAmount = Math.ceil(recurringItem.taxAmount * 100d) / 100d;     
                        invoiceItem.totalAmount = Math.ceil(recurringItem.totalAmount * 100d) / 100d;      
                        invoiceItem.save(flush: true);
                        recurringItem.uses = recurringItem.uses + 1  
                        recurringItem.save(flush: true);
                    }
                }
            }
        }
        updateDraftInvoice(newInvoice, account)
    }
    
    def addManualItemForAccount(account, itemData) {
//        def account = Account.get(itemData.account)
        def billableItem = BillableItem.get(8)
        def invoice = Invoice.findWhere(account: account, status: InvoiceStatus.values()[6])
        if(billableItem.enabled == true || billableItem.enabled == "true") {
            if(invoice) {
                def invoiceItem = new InvoiceItem() 
                invoiceItem.billableItem = billableItem
                invoiceItem.referenceItemName = itemData.name
                invoiceItem.referenceItemId = itemData.name 
                invoiceItem.invoice = invoice
                invoiceItem.usageUnits = 1.0
                invoiceItem.usageUnitPrice = itemData.amount
                invoiceItem.usageAmount = itemData.amount
                double taxPercetage = 0.0
                if(itemData.taxable == true || itemData.taxable == "true") {
                    taxPercetage = itemData.taxPercentage
                }
                invoiceItem.taxPercent = taxPercetage
                double taxAmount = (invoiceItem.usageAmount/100)* invoiceItem.taxPercent
                invoiceItem.taxAmount = Math.ceil(taxAmount * 100d) / 100d;     
                invoiceItem.totalAmount = Math.ceil((invoiceItem.usageAmount + invoiceItem.taxAmount) * 100d) / 100d;         
                invoiceItem.save(flush: true); 
                log.info("Added Manual Invoice Item for account: ${account.id} amount: ${itemData.amount}") 
                
                //send Mail Menthod
                Calendar mailCal = Calendar.getInstance();                
                Map tempalteMap = notificationService.getDefaultMailTemplateMap()
                tempalteMap.put("itemName", invoiceItem.referenceItemName)
                tempalteMap.put("amount", invoiceItem.totalAmount.toString())
                tempalteMap.put("date", mailCal.getTime().toString())
                tempalteMap.put("user", User.findByUsername(account.email))
                notificationService.send(account.email.toString(), "customItemAdded.subject.ftl", tempalteMap, "customItemAdded.ftl")    
                
                updateDraftInvoice(invoice, account)
            }
        }
    }
    
    def addManualItem(itemData) {
        
        licenseValidationService.authorizeAction(FogPanelService.INVOICE_ITEM_ADD)
        
        
        if(itemData.isRecurring == true || itemData.isRecurring == "true") {
            addRecurringItem(itemData)
        } else if(itemData.isRecurring == false || itemData.isRecurring == "false") {
            if(itemData.isAllAccount == "ALL") {
                def accountList = Account.findAll()
                for(Account account : accountList ){
                    addManualItemForAccount(account, itemData) 
                }
                
            } else if(itemData.isAllAccount == "SELECTIVE") {
                def accounts = itemData.account.split(",");
                for(def i=0; i < accounts.length; i++) {
                    def account = Account.get(Integer.parseInt(accounts[i]))
                    addManualItemForAccount(account, itemData)   
                }
            }
        }
    }
    
    def addRecurringItem(itemData) {
             
        if(itemData.isAllAccount == "ALL") {
            def accountList = Account.findAll()
            for(Account account : accountList ){
                addRecurringItemForAccount(account, itemData) 
            }
                
        } else if(itemData.isAllAccount == "SELECTIVE") {
            def accounts = itemData.account.split(",");
            for(def i=0; i < accounts.length; i++) {
                def account = Account.get(Integer.parseInt(accounts[i]))
                addRecurringItemForAccount(account, itemData)   
            }
        }
    }
    
    def addRecurringItemForAccount(account, itemData) {
        
        
        def invoice = Invoice.findWhere(account: account, status: InvoiceStatus.values()[6])
        if(invoice) {
            def billableItem = BillableItem.get(9)
            if(billableItem.enabled == true || billableItem.enabled == "true") {
                def recurringItem = new RecurringItem()
                recurringItem.billableItem = billableItem
                recurringItem.account = account
                recurringItem.amount = itemData.amount
                double taxPercetage = 0.0
                if(itemData.taxable == true || itemData.taxable == "true") {
                    taxPercetage = itemData.taxPercentage
                }
                recurringItem.taxPercent = taxPercetage
                double taxAmount = (recurringItem.amount/100)* recurringItem.taxPercent
                recurringItem.taxAmount = Math.ceil(taxAmount * 100d) / 100d;     
                recurringItem.totalAmount = Math.ceil((recurringItem.amount + recurringItem.taxAmount) * 100d) / 100d;        
                recurringItem.name = itemData.name
                recurringItem.description = itemData.name
                recurringItem.billingCycles = itemData.billingCycles
                recurringItem.save(flush: true); 
                log.info("Added RecurringItem for account: ${account.id} amount: ${itemData.amount}") 
               
                addRecurringInvoiceItem(recurringItem)
            }
        }
    }
    
    def addRecurringInvoiceItem(recurringItem) {
        def account = recurringItem.account
        def billableItem = BillableItem.get(9)
        def invoice = Invoice.findWhere(account: account, status: InvoiceStatus.values()[6])
        if(billableItem.enabled == true || billableItem.enabled == "true") {
            if(invoice) {
                def invoiceItem = new InvoiceItem() 
                invoiceItem.billableItem = billableItem
                invoiceItem.referenceItemName = recurringItem.name
                invoiceItem.referenceItemId = Long.toString(recurringItem.id) 
                invoiceItem.invoice = invoice
                invoiceItem.usageUnits = 1.0
                invoiceItem.usageUnitPrice = recurringItem.amount
                invoiceItem.usageAmount = recurringItem.amount
                invoiceItem.taxPercent = recurringItem.taxPercent
                invoiceItem.taxAmount = Math.ceil(recurringItem.taxAmount * 100d) / 100d;     
                invoiceItem.totalAmount =  Math.ceil(recurringItem.totalAmount * 100d) / 100d;        
                invoiceItem.save(flush: true);  
                recurringItem.uses = recurringItem.uses + 1  
                recurringItem.save(flush: true);
                log.info("Added InvoiceItem for RecurringItem to account: ${account.id} amount: ${recurringItem.amount}")
                Calendar mailCal = Calendar.getInstance();                
                Map tempalteMap = notificationService.getDefaultMailTemplateMap()
                tempalteMap.put("itemName", invoiceItem.referenceItemName)
                tempalteMap.put("amount", invoiceItem.totalAmount.toString())
                tempalteMap.put("date", mailCal.getTime().toString())
                tempalteMap.put("user", User.findByUsername(account.email))
                notificationService.send(account.email.toString(), "recurringItem.subject.ftl", tempalteMap, "recurringItem.ftl")    
                              
                updateDraftInvoice(invoice, account)
            }
        }
    }
    
    def addLateFee() {        
                
        Calendar fromDateCalendar = Calendar.getInstance(); 
        fromDateCalendar.add(Calendar.DATE, -1);
        fromDateCalendar.set(Calendar.HOUR_OF_DAY, 01);
        fromDateCalendar.set(Calendar.MINUTE, 00);
        fromDateCalendar.set(Calendar.SECOND, 00);
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
            eq("status", InvoiceStatus.values()[7]) 
            and {
               ge("dueDate", fromDate) and { le("dueDate", toDate) }   
            }
        }   
                                
        for(def dueDateInvoice : invoiceList) {
                        
            def draftInvoice = Invoice.findWhere(account: dueDateInvoice.account, status: InvoiceStatus.values()[6])
            
            def billableItem = BillableItem.get(7)
            
            if(billableItem.enabled == true || billableItem.enabled == "true") {
                def invoiceItem = new InvoiceItem() 
                invoiceItem.billableItem = billableItem
                invoiceItem.referenceItemName = "Late Fee"
                invoiceItem.referenceItemId = "Late Fee"
                invoiceItem.invoice = draftInvoice
                invoiceItem.usageUnits = 1.0
                def paymentsCriteria = Payments.createCriteria()
                def paymentAmmount = paymentsCriteria.list {
                    eq("account", draftInvoice.account)
                    and{
                        ge("paymentDate", dueDateInvoice.date) and { le("paymentDate", dueDateInvoice.dueDate) } 

                    }
                    and {
                        eq("paymentStatus", GeneralConstants.PAYMENT_RESULT_SUCCESS)
                    }
                    projections {
                         sum("totalAmount")
                     }
                }
                double payment;
                if(paymentAmmount[0] == null || paymentAmmount[0] == "null") {
                    payment =  0
                } else {
                    payment =  paymentAmmount[0]
                }
                Config lateFeeApplicableAmount = Config.findByName(Config.LATE_FEE_APPLICABLE_AMOUNT);
                Config lateFeeMinimumAmount =  Config.findByName(Config.LATE_FEE_MINIMUM_AMOUNT);            
                Config lateFeePercentage =  Config.findByName(Config.LATE_FEE_PERCENTAGE);
                double balanceAmount = draftInvoice.previousBalance - payment
                if(balanceAmount > Double.parseDouble(lateFeeApplicableAmount.value)) {

                    double lateFeeAmount = (balanceAmount/100)* Double.parseDouble(lateFeePercentage.value)
                    double maxFee = Math.max(lateFeeAmount, Double.parseDouble(lateFeeMinimumAmount.value));
                    invoiceItem.usageUnitPrice = maxFee
                    invoiceItem.usageAmount = maxFee
                    invoiceItem.taxPercent = billableItem.tax.percentage
                    double taxAmount = (invoiceItem.usageAmount/100)* invoiceItem.taxPercent
                    invoiceItem.taxAmount =  Math.ceil(taxAmount * 100d) / 100d;  
                    invoiceItem.totalAmount = Math.ceil((invoiceItem.usageAmount + invoiceItem.taxAmount) * 100d) / 100d;        
                    invoiceItem.save(flush: true); 
                    log.info("Added Late fee for account: ${dueDateInvoice.account.id} amount: ${maxFee}")
                }
            }
        }
        log.info("Late Fee Cron Completed");
    }
    
    // update dreft invoice for account
    def updateFinalInvoice(invoice, account) {
        
        double subTotal = (invoice.currentDue + invoice.previousBalance + invoice.refundAmount) - (invoice.payment - invoice.credit)
        invoice.totalAmount =  Math.ceil(subTotal * 100d) / 100d;
        invoice.save(flush: true);
        account.totalPayable = invoice.totalAmount;
        account.save(flush: true);
        
    }
    
    
    // update dreft invoice for account
    def updateDraftInvoice(invoice, account) {
        
        def invoiceItemList = InvoiceItem.findAllWhere(invoice : invoice)
        def c = InvoiceItem.createCriteria()
        def results = c.list {
            eq("invoice", invoice)
            and {
                ne("billableItem", BillableItem.get(6))
            }
            projections {
                 sum("totalAmount")
             }
        }  
        if(results[0] == null || results[0] == "null") {
            invoice.currentDue =  0
        } else {
            invoice.currentDue =  Math.ceil(results[0] * 100d) / 100d
        }
        
        //add discount for user
        def discountBillableItem = InvoiceItem.findWhere(billableItem:BillableItem.get(6), invoice:invoice)
        
        if(discountBillableItem) {

            if(invoice.currentDue > 0) {
                discountBillableItem.usageUnits = invoice.currentDue;

                double discountAmount = (discountBillableItem.usageUnits/100)* discountBillableItem.usageUnitPrice
                discountBillableItem.usageAmount = Math.ceil(-1 * discountAmount * 100d) / 100d;     

                discountBillableItem.totalAmount = Math.ceil(discountBillableItem.usageAmount * 100d) / 100d;  

                discountBillableItem.save(flush: true);
                
                double subTotal = (invoice.currentDue + invoice.previousBalance + invoice.refundAmount) - (invoice.payment - invoice.credit)

                invoice.totalAmount =  Math.ceil((subTotal + discountBillableItem.totalAmount) * 100d) / 100d;
                account.totalPayable = invoice.totalAmount
            } else {
                discountBillableItem.usageUnits = 0;

                double discountAmount = (discountBillableItem.usageUnits/100)* discountBillableItem.usageUnitPrice
                discountBillableItem.usageAmount = Math.ceil(-1 * discountAmount * 100d) / 100d;     

                discountBillableItem.totalAmount = Math.ceil(discountBillableItem.usageAmount * 100d) / 100d;  

                discountBillableItem.save(flush: true);


                double subTotal = (invoice.currentDue + invoice.previousBalance + invoice.refundAmount) - (invoice.payment - invoice.credit)

                invoice.totalAmount =  Math.ceil((subTotal + discountBillableItem.totalAmount) * 100d) / 100d;
                account.totalPayable = invoice.totalAmount
            }
        } else {
            double subTotal = (invoice.currentDue + invoice.previousBalance + invoice.refundAmount) - (invoice.payment - invoice.credit)

            invoice.totalAmount =  Math.ceil(subTotal * 100d) / 100d;
            account.totalPayable = invoice.totalAmount
        }
        if(account.status.name() == "CANCELED") {
            if(invoice.totalAmount == 0) {
                account.status = Status.values()[7]
                account.save(flush: true);
                invoice.save(flush: true);
                accountService.sendMail(account, "accountClose")
            }
        } else {
            account.save(flush: true);
            invoice.save(flush: true);
        }
        processForCreditLimit(account);
    } 
    
    
    
    def sendBillingMailForAllAccount() {
        
        def applicationUrl = ApplicationHolder.getApplication().config.cloudstack.applicationUrl
        def mailLogList = Log.findAllWhere(mailSend: false)
       
        //send Mail Menthod
//        def mailtemplate;
//        def headerTemplate = MailTemplate.findByName("header");
//        def footerTemplate = MailTemplate.findByName("footer");
//        def finalMessage;
//        def logoConfig = Config.findByName("organisation.billing.logo")
//        def signature = Config.findByName("organisation.billing.signature");
//
//        def hasHeader
//        def hasFooter                    
//
//        // Header Content
//        def logoUrl = ""
//        if(logoConfig.value == "") {                    
//            logoUrl = applicationUrl.toString() + "/images/fog_logo.png"
//        } else {
//            logoUrl =  logoConfig.value;
//        }  
//        def logoContent = '<img style="height: 30px; width: 100px"  src = '+logoUrl+' alt="progress" height="9" width="100">'
//        def headerContent = logoContent + headerTemplate.content;
//        
//        // Footer content
//        def footerContent = footerTemplate.content;
        
        for (def dbLog : mailLogList) {
           if(dbLog.type.name() == "DISABLED") {
                // Disable Mail
                dbLog.mailSend = true
                dbLog.save(flush: true);
                
//                mailtemplate = MailTemplate.findByName("accountDisabled")
//                hasHeader = mailtemplate.hasHeader
//                hasFooter = mailtemplate.hasFooter   
//                def userName = mailtemplate.content.replaceAll("\\[userName\\]", dbLog.account.firstName)
//                def message = userName.replaceAll("\\[signature\\]", signature.value);   
//                if((hasHeader == true || hasHeader == 'true') && (hasFooter == true || hasFooter == 'true')) {
//                    finalMessage = "<html><body>" + headerContent.toString() + message.toString() + footerContent.toString() + "</body></html>";
//                } else if((hasHeader == true || hasHeader == 'true') && (hasFooter == false || hasFooter == 'false')) {
//                    finalMessage = "<html><body>" + headerContent.toString() + message.toString() + "</body></html>";
//                } else if((hasHeader == false || hasHeader == 'false') && (hasFooter == true || hasFooter == 'true')) {                       
//                    finalMessage = "<html><body>" + message.toString() + footerContent.toString() + "</body></html>";
//                } else if((hasHeader == true || hasHeader == 'true') && (hasFooter == false || hasFooter == 'false')) {                       
//                    finalMessage = "<html><body>" + headerContent.toString() + message.toString() + "</body></html>";
//                } else {
//                    finalMessage = "<html><body>" + message.toString() + "</body></html>";
//                }
                
                
                Map tempalteMap = notificationService.getDefaultMailTemplateMap()
                tempalteMap.put("user", User.findByUsername(dbLog.account.email))
                notificationService.send(dbLog.account.email.toString(), "accountDisabled.subject.ftl", tempalteMap, "accountDisabled.ftl")  
                       
                
           } else if(dbLog.type.name() == "OVERLIMITALERT") {
                // Credit Limit mail
                dbLog.mailSend = true
                dbLog.save(flush: true);
//                mailtemplate = MailTemplate.findByName("creditLimit")
//                hasHeader = mailtemplate.hasHeader
//                hasFooter = mailtemplate.hasFooter   
//                def setUserName = mailtemplate.content.replaceAll("\\[userName\\]", dbLog.account.firstName)
//                def percentage = setUserName.replaceAll("\\[creditLimit\\]", dbLog.creditLimitPercentage + "% of credit limit")
//                def createdDate = percentage.replaceAll("\\[createdDate\\]", dbLog.date.toString())
//                def message = createdDate.replaceAll("\\[signature\\]", signature.value)
//                if((hasHeader == true || hasHeader == 'true') && (hasFooter == true || hasFooter == 'true')) {
//                    finalMessage = "<html><body>" + headerContent.toString() + message.toString() + footerContent.toString() + "</body></html>";
//                } else if((hasHeader == true || hasHeader == 'true') && (hasFooter == false || hasFooter == 'false')) {
//                    finalMessage = "<html><body>" + headerContent.toString() + message.toString() + "</body></html>";
//                } else if((hasHeader == false || hasHeader == 'false') && (hasFooter == true || hasFooter == 'true')) {                       
//                    finalMessage = "<html><body>" + message.toString() + footerContent.toString() + "</body></html>";
//                } else if((hasHeader == true || hasHeader == 'true') && (hasFooter == false || hasFooter == 'false')) {                       
//                    finalMessage = "<html><body>" + headerContent.toString() + message.toString() + "</body></html>";
//                } else {
//                    finalMessage = "<html><body>" + message.toString() + "</body></html>";
//                }
                
                
                Map tempalteMap = notificationService.getDefaultMailTemplateMap()
                tempalteMap.put("creditLimit", dbLog.creditLimitPercentage + " % of credit limit")
                tempalteMap.put("date", dbLog.date.toString())
                tempalteMap.put("user", User.findByUsername(dbLog.account.email))
                notificationService.send(dbLog.account.email.toString(), "creditLimitReached.subject.ftl", tempalteMap, "creditLimitReached.ftl")                    
            }
        }        
        log.info("CreditLimitNotification Cron Completed");
        
    }
    
    // process fo credit limit check for given account
    def processForCreditLimit(account) { 
        try { 
            Date date = new Date()
            def time = new Timestamp(date.getTime())
            def applicationUrl = ApplicationHolder.getApplication().config.cloudstack.applicationUrl
            def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
            def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
            def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret

            CloudStackServer server =
                    new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);
            CSAccountService csAccService = new CSAccountService(server);
            HashMap<String,String> optional = new HashMap<String,String>();
            optional.put("account", new String(account.accountIdentifier));
            optional.put("domainid", account.domain.referenceId);
            
            if((account.totalPayable - account.credit) >= account.creditLimit) {
                if(account.status.name().toString() == "ACTIVE") {
                        def response = csAccService.disableAccount("false", optional)
                       account.status = Status.values()[3]
                       account.save(flush: true);
                       def dbLog = new Log()
                       dbLog.account = account
                       dbLog.user = User.findByUsername(account.accountIdentifier)
                       dbLog.date = time
                       dbLog.type = LogType.values()[2]
                       dbLog.mailSend = false
                       dbLog.description = "Account " + account.accountIdentifier + " is Disabled due to over credit limit"
                       dbLog.save(flush: true);
                       log.info("Account disabled for account: ${account.id} by cron jobs") 
//                    def mailtemplate = MailTemplate.findByName("suspended")
//                    def setUserName = mailtemplate.content.replaceAll("userName", account.accountIdentifier)
//                    def message = setUserName.replaceAll("applicationUrl", applicationUrl.value)
//                    notificationService.send(account.email.toString(), "Invoice Notification", message.toString())
                    }
            } else {
                if(account.status.name().toString() == "DISABLED") {             
                                    
                    if(account.accountType.name() == "TRIAL") {
                        
                        
                    } else {
                        def response = csAccService.enableAccount(optional)
                        account.status = Status.values()[0]
                        account.save(flush: true);
                        log.info("Account Enabled for account: ${account.id} by cron jobs")                         
                        Map tempalteMap = notificationService.getDefaultMailTemplateMap()          
                        tempalteMap.put("firstName", account.firstName)         
                        notificationService.send(account.email.toString(), "accountEnabled.subject.ftl", tempalteMap, "accountEnabled.ftl")  
                    }                   
                }
            }
            creditLimitNotification(account);
        } catch (RuntimeException ex) {
            Console.print(ex)
        }   
        
 
    }
    
    //credit limit notification allert for given account
    def creditLimitNotification(account) {
        try { 
            
            Date date = new Date()
            def time = new Timestamp(date.getTime())
            Config applicationUrl =  Config.findByName(Config.APPLICATION_URL);
            Config creditLimitNotificationLevel1Percentage =  Config.findByName(Config.CREDIT_LIMIT_NOTIFICATION_LEVEL_1);
            Config creditLimitNotificationLevel2Percentage =  Config.findByName(Config.CREDIT_LIMIT_NOTIFICATION_LEVEL_2);
            Config creditLimitNotificationLevel3Percentage =  Config.findByName(Config.CREDIT_LIMIT_NOTIFICATION_LEVEL_3);

            double level1Amount = (account.creditLimit /100)*Double.parseDouble(creditLimitNotificationLevel1Percentage.value)
            double level2Amount = (account.creditLimit /100)*Double.parseDouble(creditLimitNotificationLevel2Percentage.value)
            double level3Amount = (account.creditLimit /100)*Double.parseDouble(creditLimitNotificationLevel3Percentage.value)
            def message;
//            def mailtemplate = MailTemplate.findByName("creditLimit")
            def dbLog = new Log()
            dbLog.account = account
            dbLog.user = User.findByUsername(account.accountIdentifier)
            dbLog.date = time
            dbLog.type = LogType.values()[1]
                        
            if(account.creditLimitLevel == "1") {

                if(account.totalPayable >= level2Amount && account.totalPayable <= level3Amount) {

//                    def setUserName = mailtemplate.content.replaceAll("userName", account.accountIdentifier)
//                    def url = setUserName.replaceAll("applicationUrl", applicationUrl)
//                    message = url.replaceAll("creditLimit", creditLimitNotificationLevel2Percentage.value + "% of credit limit")
//                    notificationService.send(account.email.toString(), "Invoice Notification", message.toString())
                    dbLog.creditLimitPercentage = creditLimitNotificationLevel2Percentage.value
                    dbLog.mailSend = false
                    dbLog.description = "Account " + account.accountIdentifier + " is reached " + creditLimitNotificationLevel2Percentage.value + "% of credit limit";
                    dbLog.save(flush: true);
                    account.creditLimitLevel = "2"
                    account.save(flush: true);
                    log.info("Credit limit level 2 reached for account: ${account.id} by cron jobs")
                } else if(account.totalPayable >= level3Amount && account.totalPayable <= account.creditLimit) {

//                    def setUserName = mailtemplate.content.replaceAll("userName", account.accountIdentifier)
//                    def url = setUserName.replaceAll("applicationUrl", applicationUrl)
//                    message = url.replaceAll("creditLimit", creditLimitNotificationLevel3Percentage.value + "% of credit limit")
//                    notificationService.send(account.email.toString(), "Invoice Notification", message.toString())
                    dbLog.creditLimitPercentage = creditLimitNotificationLevel3Percentage.value
                    dbLog.mailSend = false
                    dbLog.description = "Account " + account.accountIdentifier + " is reached " + creditLimitNotificationLevel3Percentage.value + "% of credit limit";
                    dbLog.save(flush: true);
                    account.creditLimitLevel = "3"
                    account.save(flush: true);
                    log.info("Credit limit level 3 reached for account: ${account.id} by cron jobs")
                } 
            } else if(account.creditLimitLevel == "2") {
                if(account.totalPayable >= level3Amount && account.totalPayable <= account.creditLimit) {

//                    def setUserName = mailtemplate.content.replaceAll("userName", account.accountIdentifier)
//                    def url = setUserName.replaceAll("applicationUrl", applicationUrl)
//                    message = url.replaceAll("creditLimit", creditLimitNotificationLevel3Percentage.value + "% of credit limit")
//                    notificationService.send(account.email.toString(), "Invoice Notification", message.toString())
                    dbLog.creditLimitPercentage = creditLimitNotificationLevel3Percentage.value
                    dbLog.mailSend = false
                    dbLog.description = "Account " + account.accountIdentifier + " is reached " + creditLimitNotificationLevel3Percentage.value + "% of credit limit";
                    dbLog.save(flush: true);
                    account.creditLimitLevel = "3"
                    account.save(flush: true);
                    log.info("Credit limit level 3 reached for account: ${account.id} by cron jobs")
                } 
            } else if(account.creditLimitLevel == "3") {

            } else {
                if(account.totalPayable >= level1Amount && account.totalPayable <= level2Amount) {
//                    def setUserName = mailtemplate.content.replaceAll("userName", account.accountIdentifier)
//                    def url = setUserName.replaceAll("applicationUrl", applicationUrl)
//                    message = url.replaceAll("creditLimit", creditLimitNotificationLevel1Percentage.value + "% of credit limit")
//                    notificationService.send(account.email.toString(), "Invoice Notification", message.toString())
                    dbLog.creditLimitPercentage = creditLimitNotificationLevel1Percentage.value
                    dbLog.description = "Account " + account.accountIdentifier + " is reached " + creditLimitNotificationLevel1Percentage.value + "% of credit limit";
                    dbLog.save(flush: true);
                    account.creditLimitLevel = "1"
                    account.save(flush: true);
                    log.info("Credit limit level 1 reached for account: ${account.id} by cron jobs")

                } else if(account.totalPayable >= level2Amount && account.totalPayable <= level3Amount) {

//                    def setUserName = mailtemplate.content.replaceAll("userName", account.accountIdentifier)
//                    def url = setUserName.replaceAll("applicationUrl", applicationUrl)
//                    message = url.replaceAll("creditLimit", creditLimitNotificationLevel2Percentage.value + "% of credit limit")
//                    notificationService.send(account.email.toString(), "Invoice Notification", message.toString())
                    dbLog.creditLimitPercentage = creditLimitNotificationLevel2Percentage.value
                    dbLog.description = "Your account " + account.accountIdentifier + " is reached " + creditLimitNotificationLevel2Percentage.value + "% of credit limit";
                    dbLog.description = "Account " + account.accountIdentifier + " is reached " + creditLimitNotificationLevel2Percentage.value + "% of credit limit";
                    dbLog.save(flush: true);
                    account.creditLimitLevel = "2"
                    account.save(flush: true);
                    log.info("Credit limit level 2 reached for account: ${account.id} by cron jobs")
                } else if(account.totalPayable >= level3Amount && account.totalPayable <= account.creditLimit) {

//                    def setUserName = mailtemplate.content.replaceAll("userName", account.accountIdentifier)
//                    def url = setUserName.replaceAll("applicationUrl", applicationUrl)
//                    message = url.replaceAll("creditLimit", creditLimitNotificationLevel3Percentage.value + "% of credit limit")
//                    notificationService.send(account.email.toString(), "Invoice Notification", message.toString())
                    dbLog.creditLimitPercentage = creditLimitNotificationLevel3Percentage.value
                    dbLog.description = "Account " + account.accountIdentifier + " is reached " + creditLimitNotificationLevel3Percentage.value + "% of credit limit";
                    dbLog.save(flush: true);
                    account.creditLimitLevel = "3"
                    account.save(flush: true);
                    log.info("Credit limit level 3 reached for account: ${account.id} by cron jobs")
                } 
            }
        } catch (RuntimeException ex) {
            Console.print(ex)
        }
     }
     
    
    def addRefundAmount(requestBody) {
        
        try { 
            
            ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();            
            HashMap<String,String> item = new HashMap<String,String>();  
            
            Date date = new Date();
            def time = new Timestamp(date.getTime())

            // convert string to json object
            def requestData = JSON.parse(requestBody)


            def account = Account.get(requestData.accountId)

            def invoiceCriteria = Invoice.createCriteria()

            def invoiceList = invoiceCriteria.list {
                eq("account", account) 
                order("id", "desc")
            }  

            def invoice = invoiceList[0]

            Refund refund = new Refund()
            refund.account = account
            refund.invoice = invoice
            refund.amount = requestData.amount
            refund.description = requestData.description
            refund.date = time
            refund.save(flush: true);                        
            
            def invoiceItemList = InvoiceItem.findAllWhere(invoice : invoice)
            def c = InvoiceItem.createCriteria()
            def results = c.list {
                eq("invoice", invoice)
                projections {
                     sum("totalAmount")
                 }
            }  
            if(results[0] == null || results[0] == "null") {
                invoice.currentDue =  0
            } else {
                invoice.currentDue =  Math.ceil(results[0] * 100d) / 100d
            }
            invoice.refundAmount += requestData.amount
            double subTotal = (invoice.currentDue + invoice.previousBalance + invoice.refundAmount) - invoice.payment

            invoice.totalAmount =  Math.ceil(subTotal * 100d) / 100d;
            account.totalPayable = invoice.totalAmount
            if(account.totalPayable == 0) {
                account.status = Status.values()[7]
            }
            account.save(flush: true);
            invoice.save(flush: true);
            
            log.info("refund amount added for account: ${account.id} amount: ${requestData.amount}")
                           
            Map tempalteMap = notificationService.getDefaultMailTemplateMap()
            tempalteMap.put("amount", requestData.amount.toString())
            tempalteMap.put("message", requestData.description)
            tempalteMap.put("user", User.findByUsername(account.accountIdentifier))
            notificationService.send(account.email.toString(), "refund.subject.ftl", tempalteMap, "refund.ftl")  
           
            item.put("result",  GeneralConstants.RESULT_SUCCESS);
            result.add(item);
            
            return result
       
        } catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    // for prepaid invoice
    def createNewPrepaidInvoice() {
        try {
            def user = springSecurityService.currentUser


            Date date = Calendar.getInstance().getTime()
            def currentTime = new Timestamp(date.getTime())

            Calendar c = Calendar.getInstance();
            //get billing type from config
            Config billingTypeFixedEnabled =  Config.findByName(Config.BILLING_CYCLE_FIXED_ENABLED);
            Config dueDays =  Config.findByName(Config.PAYMENT_DUE_DAYS);
            def invoiceDate;
            if(billingTypeFixedEnabled.value == "true" || billingTypeFixedEnabled.value == true) {
                Config billingPeriodfixedDay =  Config.findByName(Config.BILLING_PERIOD_FIXED_DAY);
                c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(billingPeriodfixedDay.value));   
                if(date.getDate() >= Integer.parseInt(billingPeriodfixedDay.value)) {
                    c.add(Calendar.MONTH, 1); 
                    c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(billingPeriodfixedDay.value)); 
                    invoiceDate = c.getTime()
                } else {
                    invoiceDate = c.getTime()
                }

            } else if(billingTypeFixedEnabled.value == "false" || billingTypeFixedEnabled.value == false) {
                Config billingPeriodDays =  Config.findByName(Config.BILLING_PERIOD_DAYS);
                // Adding days
                c.add(Calendar.DATE, Integer.parseInt(billingPeriodDays.value));
                invoiceDate = c.getTime()
            }
            def discountList;
            def discountCriteria = Discount.createCriteria()
            def dateFormatValue = configService.getInvoiceDateFormat();   
            DateFormat formater2 = new SimpleDateFormat(dateFormatValue); 
            Date curDate = Calendar.getInstance().getTime()
            def today = formater2.format(curDate)
            Date currentDate = formater2.parse(today);
            def account = user.account
            def invoice = Invoice.findWhere(account: account, status: InvoiceStatus.values()[6])

            if(!invoice) {

                account.nextBillingData = invoiceDate
                account.lastBillingData = currentTime   
                account.save(flush: true); 
                invoice = new Invoice()
                invoice.status = InvoiceStatus.values()[6]
                invoice.account = account
                invoice.referenceNumber = account.id + date.toString()
                invoice.date = invoiceDate
                invoice.previousInvoiceDate = currentTime
                invoice.currency = ApplicationHolder.getApplication().config.billing.defaultCurrency

                Calendar due = Calendar.getInstance();

                Date invoiceDateString = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy")
                      .parse(invoiceDate.toString());

                due.setTime(invoiceDateString)
                due.add(Calendar.DATE, Integer.parseInt(dueDays.value));

    //                invoice.dueDate = due.getTime()
                invoice.customerName = account.firstName
                def address1 = new Address()
                address1.street = account.streetAddress
                address1.state = account.state.stateName
                address1.country = account.country.countryName
                address1.city = account.city
                address1.zip = account.zip
                invoice.billingAddress = address1
                def address = new Address()
                address.street = Config.findByName(Config.ORGANISATION_ADDRESS).value
                address.state = Config.findByName(Config.ORGANISATION_ADDRESS_STATE).value
                address.country = Config.findByName(Config.ORGANISATION_ADDRESS_COUNTRY).value
                address.city = Config.findByName(Config.ORGANISATION_ADDRESS_CITY).value
                address.zip = Config.findByName(Config.ORGANISATION_ADDRESS_ZIP).value             
                invoice.organisationAddress = address                    
                invoice.save(flush: true); 
                log.info("Invoice generated for account: ${account.id}") 
                if (invoice.hasErrors()) {
                    throw new ValidationException(invoice.errors.allErrors);
                }
//                def discountBillableItem = BillableItem.get(6)
//                if(discountBillableItem.enabled == true || discountBillableItem.enabled == "true") {
//                    def userDiscountCriteria = Discount.createCriteria()
//                    def userDiscountList = userDiscountCriteria.list {
//                        eq("subType", "CREATE_VM")
//                        eq("type", DiscountType.values()[0])
//                        and{
//                          le("startDate", currentDate) and { ge("endDate", currentDate)  } 
//                          and{
//                            eq("deleted", false)  
//                          }
//                        }
//                    }
//
//                    for(def userDiscount : userDiscountList) {
//
//                        if(userDiscount.isAll == true) {
//                            def invoiceItem = new InvoiceItem()
//                            invoiceItem.billableItem = discountBillableItem
//                            invoiceItem.invoice = invoice
//                            invoiceItem.taxPercent = 0.0
//                            invoiceItem.discount = userDiscount
//    //                                invoiceItem.discountPercent = userDiscount.value
//                            invoiceItem.usageUnitPrice = userDiscount.value
//
//                            invoiceItem.referenceItemId = userDiscount.id.toString()
//                            invoiceItem.referenceItemName = userDiscount.discountName
//                            invoiceItem.save()
//                            log.info("Discount:${userDiscount.id} added for account: ${account.id}") 
//                            break
//                        } else {
//                            for(Iterator m = userDiscount.accounts.iterator();m.hasNext();) { 
//                                def discountAccount = m.next();
//                                if(discountAccount.id == invoice.account.id) {
//                                    def invoiceItem = new InvoiceItem()
//                                    invoiceItem.billableItem = discountBillableItem
//                                    invoiceItem.invoice = invoice
//                                    invoiceItem.taxPercent = 0.0
//                                    invoiceItem.discount = userDiscount
//    //                                invoiceItem.discountPercent = userDiscount.value
//                                    invoiceItem.usageUnitPrice = userDiscount.value
//
//                                    invoiceItem.referenceItemId = userDiscount.id.toString()
//                                    invoiceItem.referenceItemName = userDiscount.discountName
//                                    invoiceItem.save()
//                                    log.info("Discount:${userDiscount.id} added for account: ${account.id}") 
//                                    break
//                                }
//
//                            }
//                        }
//                    } 
//                }
            }
        }catch (Exception ex) {
            throw ex;
        }  
    }
}
