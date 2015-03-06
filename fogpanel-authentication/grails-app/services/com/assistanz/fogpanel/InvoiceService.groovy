package com.assistanz.fogpanel

import java.text.SimpleDateFormat
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.text.DateFormat
import java.util.Collections;
import org.codehaus.groovy.grails.commons.ApplicationHolder
import grails.converters.deep.JSON
import org.apache.commons.logging.LogFactory;
import grails.transaction.Transactional
import com.assistanz.cloud.config.ConfigLoader;
import com.assistanz.fogpanel.paymentgateway.Address

@Transactional
class InvoiceService {
    
    private static final log = LogFactory.getLog(this)
    def springSecurityService;
    NotificationService notificationService
    AccountService accountService
    ConfigService configService;
    //    LicenseValidationService licenseValidationService
    
    // List all aviliable invoice 
    def list(accountId) {
        
        def finalInvoiceList;
        def account = springSecurityService.currentUser.account
        def role = springSecurityService.getPrincipal().getAuthorities()    
        finalInvoiceList = Invoice.findAllWhere(account: account, status: InvoiceStatus.values()[7])
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        def invoiceListItems = Invoice.createCriteria()
        if((accountId == "null" || accountId == null)) {              
            if(role.iterator().next() == "ROLE_ADMIN" ) {
                
            } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {
                finalInvoiceList = finalInvoiceList = invoiceListItems.list {
                    eq("account", account)
                    eq("status", InvoiceStatus.values()[7])
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
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        
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
            
            //            def offeringUsageCriteria = OfferingUsage.createCriteria()
            //            daily = offeringUsageCriteria.get {         
            //                and { ge("startDate", fromDate) and { le("startDate", toDate) } }
            //                projections {
            //                    sum("cost")
            //                }
            //            }
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
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
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
        addRecurringItemsForNextInvoice(newInvoice, account)
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
     
    def sendBillingMailForAllAccount() {
        
        def applicationUrl = ApplicationHolder.getApplication().config.faas.applicationUrl
        def mailLogList = Log.findAllWhere(mailSend: false)
       
        for (def dbLog : mailLogList) {
            if(dbLog.type.name() == "DISABLED") {
                // Disable Mail
                dbLog.mailSend = true
                dbLog.save(flush: true);
                                
                Map tempalteMap = notificationService.getDefaultMailTemplateMap()
                tempalteMap.put("user", User.findByUsername(dbLog.account.email))
                notificationService.send(dbLog.account.email.toString(), "accountDisabled.subject.ftl", tempalteMap, "accountDisabled.ftl")  
                       
                
            } else if(dbLog.type.name() == "OVERLIMITALERT") {
                // Credit Limit mail
                dbLog.mailSend = true
                dbLog.save(flush: true);
                
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
            def applicationUrl = ApplicationHolder.getApplication().config.faas.applicationUrl
           
            HashMap<String,String> optional = new HashMap<String,String>();
            optional.put("account", new String(account.accountIdentifier));
            optional.put("domainid", account.domain.referenceId);
            
            if((account.totalPayable - account.credit) >= account.creditLimit) {
                if(account.status.name().toString() == "ACTIVE") {

//                        def response = csAccService.disableAccount("false", optional)
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
            tempalteMap.put("user", User.findByUsername(account.email))
            notificationService.send(account.accountIdentifier.toString(), "refund.subject.ftl", tempalteMap, "refund.ftl")  
           
            item.put("result",  GeneralConstants.RESULT_SUCCESS);
            result.add(item);
            
            return result
       
        } catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    // for prepaid invoice
    def createNewPrepaidInvoice() {
          
    }
    
    
    // update given invoice
    def updateInvoice(invoice) {
        
        try {
            
            def account = invoice.account
            
            def invoiceItemList = InvoiceItem.findAllWhere(invoice : invoice)
            def invoiceCriteria = InvoiceItem.createCriteria()
            def results = invoiceCriteria.list {
                eq("invoice", invoice)
//                and {
//                    ne("billableItem", BillableItem.get(6))
//                }
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
//            if(account.status.name() == "CANCELED") {
//                if(invoice.totalAmount == 0) {
//                    account.status = Status.values()[7]
//                    account.save(flush: true);
//                    invoice.save(flush: true);
//                    accountService.sendMail(account, "accountClose")
//                }
//            } else {
                account.save(flush: true);
                invoice.save(flush: true);
//            }
        
        } catch (Exception ex) {
            Console.print(ex); //throw ex;
        }  
        
    } 
    
    //create a new draft invoice for account
    def createDraftInvoice(account) {
        
        try {
            
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
                        
            DateFormat formater2 = new SimpleDateFormat("dd/MM/yyyy"); 
            Date curDate = Calendar.getInstance().getTime()
            def today = formater2.format(curDate)
            Date currentDate = formater2.parse(today);
           
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
                
                return invoice;
                
            }
        } catch (Exception ex) {
            Console.print(ex); //throw ex;
        }  
    }
        
    //create a new invoice item
    def addInvoiceItem(invoice, billableItem, billingType, referenceItemName, referenceItemId, planId, price, zone, discount) {
        
        try {
            if(billableItem.enabled == true || billableItem.enabled == "true") {
                def invoiceItem = new InvoiceItem()
                invoiceItem.billableItem = billableItem
                invoiceItem.invoice = invoice
                invoiceItem.taxPercent = billableItem.tax.percentage
                invoiceItem.referencePlanId = planId
                if(discount != null) {
                    invoiceItem.discount = discount;
                    invoiceItem.discountPercent = discount?.value;  
                }              
                if(billingType == "monthly") { 
                    double monthlyAmount = price ? price : 0       
                    invoiceItem.usageUnitPrice = Math.ceil(monthlyAmount * 100d) / 100d;   
                    invoiceItem.usageUnits = 1.0
                    invoiceItem.usageAmount = invoiceItem.usageUnitPrice                   
                    double taxAmount = (invoiceItem.usageAmount/100)* invoiceItem.taxPercent
                    invoiceItem.taxAmount = Math.ceil(taxAmount * 100d) / 100d;   
                    double disAmount = (invoiceItem.usageAmount/100)* invoiceItem.discountPercent
                    invoiceItem.discountAmount = Math.ceil(disAmount * 100d) / 100d;   
                    double totalAmt =  (invoiceItem.usageAmount + invoiceItem.taxAmount) - invoiceItem.discountAmount
                    invoiceItem.totalAmount = Math.ceil(totalAmt * 100d) / 100d;    
                } else { 
                    invoiceItem.usageUnitPrice = price ? price : 0
                }
                invoiceItem.referenceItemName = referenceItemName
                invoiceItem.referenceItemId = referenceItemId
                invoiceItem.zone = zone
                
                invoiceItem.save(flush: true);        
                if (!invoiceItem.save()) {
                    invoiceItem.errors.allErrors.each { Console.print(it) }
                }
                
                return invoiceItem;
            }
        
        } catch (Exception ex) {
            Console.print(ex); //throw ex;
        } 
    }    
    
    //Update usage details for invoice item
    def updateUsageDataForInvoiceItem(invoice, hours, referenceItemId, planId, serviceType, billableItem) {
        try {                       
            ConfigLoader configLoader = ConfigLoader.getInstance();
            Properties props = configLoader.getProperties();
            def usageCalcType =  props.get(Config.USAGE_BILLING_CALC_TYPE)
            def invoiceItem = InvoiceItem.findWhere(invoice:invoice, billableItem:billableItem,referenceItemId:referenceItemId, referencePlanId:planId)
            if(invoiceItem) {
                if(usageCalcType == "ACTUAL") {
                    invoiceItem.usageUnits = invoiceItem.usageUnits + hours;
                } else {
                    invoiceItem.usageUnits = invoiceItem.usageUnits + Math.ceil(hours);
                }
                if(serviceType == "volume") {
//                    Volume volume = Volume.findByReferenceId(invoiceItem.referenceItemId);

                    //            if(volume.billingType == "hourly") {
                    double usage = invoiceItem.usageUnitPrice * volume.size * invoiceItem.usageUnits;
                    double subtotalUsageCost = usage

                    //usage amount
                    invoiceItem.usageAmount =   Math.ceil((subtotalUsageCost) * 100d) / 100d;
                    //            }

                } else if(serviceType == "snapshot") {
//                    VolumeSnapshot volumeSnapshot = VolumeSnapshot.findByReferenceId(invoiceItem.referenceItemId)
                    double usage = invoiceItem.usageUnitPrice * volumeSnapshot.size * invoiceItem.usageUnits;
                    double subtotalUsageCost = usage
                    //new usage calc
                    invoiceItem.usageAmount =   Math.ceil((subtotalUsageCost) * 100d) / 100d;               
                } else if(serviceType == "instance") {
//                    VirtualMachine virtualMachine = VirtualMachine.findByReferenceId(invoiceItem.referenceItemId);
                    
                    //            if(volume.billingType == "hourly") {
                    double usage = invoiceItem.usageUnitPrice * invoiceItem.usageUnits;
                    double subtotalUsageCost = usage
                    //usage amount
                    invoiceItem.usageAmount =  Math.ceil((subtotalUsageCost) * 100d) / 100d;
                    //            }
           
                } else if(serviceType == "image") {
//                    Images images = Images.findByReferenceId(invoiceItem.referenceItemId)
                    double size = images.size / 1073741824d
                    double usage = invoiceItem.usageUnitPrice * size * invoiceItem.usageUnits;
                    double subtotalUsageCost = usage
                    //new usage calc
                    invoiceItem.usageAmount =   Math.ceil((subtotalUsageCost) * 100d) / 100d;
                } else {
                    double usage = invoiceItem.usageUnitPrice * invoiceItem.usageUnits
                    double subtotalUsageCost = usage               
                    //new usage calc
                    invoiceItem.usageAmount =  Math.ceil((subtotalUsageCost) * 100d) / 100d; 
                }
                //discount amount
                double discountAmount = (invoiceItem.usageAmount/100)*invoiceItem.discountPercent
                invoiceItem.discountAmount = discountAmount;
                // sub total
                double subTotal = invoiceItem.usageAmount - discountAmount
                // tax amount
                double taxAmount = (subTotal/100)*invoiceItem.taxPercent
                invoiceItem.taxAmount = taxAmount;
                //total amount
                invoiceItem.totalAmount = subTotal + invoiceItem.taxAmount;        
                invoiceItem.save(flush: true); 
                if (!invoiceItem.save()) {
                    invoiceItem.errors.allErrors.each { Console.print(it) }
                }
            }
        
        } catch (Exception ex) {
            Console.print(ex); //throw ex;
        } 
    }
    
    //add generate invoice logic
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
                updateInvoice(invoice)
                def finalInvoice = finilizeInvoice(invoice)
                def draftInvoice = createDraftInvoice(invoice.account)
                
                draftInvoice.previousBalance = finalInvoice.totalAmount
                draftInvoice.save(flush: true)
                
                addAviliableInstanceItemToDraftInvoice(draftInvoice)
                addAviliableStorageItemToDraftInvoice(draftInvoice)
                addAviliableSnapshotItemToDraftInvoice(draftInvoice)
                addAviliableNetworkItemToDraftInvoice(draftInvoice)
                addAviliableVMSnapshotItemToDraftInvoice(draftInvoice)
                addAviliableRecurringItemToDraftInvoice(draftInvoice)
//                addAviliableFloatingIPItemToDraftInvoice(draftInvoice)
                
                
                Console.print("Invoice Generate For Account" + invoice.account.id);
            } catch (Exception ex) {
                log.info("InvoiceGenerate Failed For Account ${invoice.account.id} dew to ${ex}");
                Console.print(ex);
            }
        }
        log.info("InvoiceGenerate Cron Completed ${new Date()}");
    }
        
    //change draft invoice to final invoice
    def finilizeInvoice(invoice) {
        
        try { 
            def account = invoice.account
            
            def invoiceCriteria = InvoiceItem.createCriteria()
            def results = invoiceCriteria.list {
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
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Calendar due = Calendar.getInstance();
            Date date = new Date();
            Date curDate =  new SimpleDateFormat("yyyy-MM-dd")
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
            
            return invoice;
        } catch (Exception ex) {
            Console.print(ex); //throw ex;
        }  
    }
    
    def addAviliableInstanceItemToDraftInvoice(invoice) {
        
        def vmList = VirtualMachine.findAllWhere(account:invoice.account, deleted:false)
        for(def virtualMachine: vmList) {
           try {  
                def price = FlavorCostInfo.findWhere(zone:virtualMachine.zone, flavor:virtualMachine.flavor)?.runningCost
                def invoiceItem = addInvoiceItem(invoice, BillableItem.get(1), virtualMachine.billingType, virtualMachine.name, virtualMachine.referenceId, virtualMachine.flavor.referenceId, price, virtualMachine.zone, null)
                if(virtualMachine.image.oneTimeChargeable == false) {
                    def imageInvoiceItem = addInvoiceItem(invoice, BillableItem.get(10), "monthly", virtualMachine.image.name, virtualMachine.image.referenceId, null, virtualMachine.image.cost, null, null) 
                }
            } catch (Exception ex) {
                Console.print(ex); //throw ex;
            } 
        }
    }
    
    
    def addAviliableStorageItemToDraftInvoice(invoice) {
        
        def volumeList = Volume.findAllWhere(account:invoice.account, deleted:false)               
        for(def volume: volumeList) {
            try {  
                def price = VolumeTypeZoneCost.findWhere(zone:volume.zone, volumeType:volume.volumeType)?.cost
                def invoiceItem = addInvoiceItem(invoice, BillableItem.get(2), volume.billingType, volume.name, volume.referenceId, volume.volumeType.referenceId, price, volume.zone, null)
            } catch (Exception ex) {
                Console.print(ex); //throw ex;
            } 
        }
    }
    
    def addAviliableSnapshotItemToDraftInvoice(invoice) {
        
        def snapshotList = VolumeSnapshot.findAllWhere(account:invoice.account, deleted:false)
        for(def snapshot: snapshotList) {
           try {  
                def price = 0 //MiscellaneousOfferZoneCost.findWhere(zone:snapshot.zone, miscellaneousOffer:MiscellaneousOffer.get(2))?.cost
                def invoiceItem = addInvoiceItem(invoice, BillableItem.get(3), "hourly", snapshot.name, snapshot.referenceId, snapshot.volume.referenceId, price, snapshot.zone, null)
            } catch (Exception ex) {
                Console.print(ex); //throw ex;
            } 
            
        }
    }
    
    def addAviliableNetworkItemToDraftInvoice(invoice) {
        
        def networkList = Network.findAllWhere(account:invoice.account, deleted:false)
        for(def network: networkList) {
            try {  
                 def price = MiscellaneousOffer.get(3)?.cost
                 def bandwidthReadPrice = MiscellaneousOffer.get(4)?.cost
                 def bandwidthWritePrice = MiscellaneousOffer.get(5)?.cost
                //for network
                addInvoiceItem(invoice, BillableItem.get(4), network.billingType, network.name, network.referenceId, null, price, null, null)              
                def read = InvoiceItem.findWhere(invoice:invoice, billableItem:BillableItem.get(11), referenceItemId:invoice.account.uuid)
                def write = InvoiceItem.findWhere(invoice:invoice, billableItem:BillableItem.get(12), referenceItemId:invoice.account.uuid)
                if (!read) {
                    // for bandwith read
                    addInvoiceItem(invoice, BillableItem.get(11), "hourly", "Bandwith Read", invoice.account.uuid, null, bandwidthReadPrice, null, null)
                }                
                if (!write) {
                    // for bandwith write
                    addInvoiceItem(invoice, BillableItem.get(12), "hourly", "Bandwith Write", invoice.account.uuid, null, bandwidthWritePrice, null, null)
                }
             } catch (Exception ex) {
                 Console.print(ex); //throw ex;
             } 
        }
    }
    
    
    def addAviliableFloatingIPItemToDraftInvoice(invoice) {         
        
        def floatingIPList = FloatingIP.findAllWhere(account:invoice.account, deleted:false)
        for(def floatingIP: floatingIPList) {
            try {  
                 def price = MiscellaneousOffer.get(6)?.cost        
                //for floating IP
                 addInvoiceItem(invoice, BillableItem.get(14), floatingIP.billingType, floatingIP.name, floatingIP.referenceId, null, price, null, null)
             } catch (Exception ex) {
                 Console.print(ex); //throw ex;
             } 
        }
    }
    
    def addAviliableVMSnapshotItemToDraftInvoice(invoice) {
        
        def vmSnapList = Images.findAllWhere(account:invoice.account, deleted:false, isVMSnapshot: true)
        for(def vmSnap: vmSnapList) {
            try {  
                def price = 0 //MiscellaneousOfferRegionCost.findWhere(miscellaneousOffer:MiscellaneousOffer.get(1), region:region)?.cost
                //for vm snap
                addInvoiceItem(invoice, BillableItem.get(13), vmSnap.billingType, vmSnap.name, vmSnap.referenceId, null, price, null, null)
             } catch (Exception ex) {
                 Console.print(ex); //throw ex;
             } 
        }
    }
    
    def addAviliableRecurringItemToDraftInvoice(invoice) {
        
        def recurringItemList = RecurringItem.findAllByAccount(invoice.account)
        for(def recurringItem : recurringItemList) {
            try {  
                if((recurringItem.billingCycles == 0)  || (recurringItem.uses < recurringItem.billingCycles)) {
                    //add recurring item to invoice
                    addInvoiceItem(invoice, BillableItem.get(9), "monthly", recurringItem.name, recurringItem.id.toString(), null, recurringItem.amount, null, null)
                    recurringItem.uses = recurringItem.uses + 1 
                    recurringItem.save(flush: true)
                }
            } catch (Exception ex) {
                 Console.print(ex); //throw ex;
            } 
        }
        
    }
    
    def addManualItem(requestData) {
                
        try { 
            if(requestData.isAllAccount == "ALL") {
                def accountList = Account.findAll()
                for(Account account : accountList ) {
                    
                    def invoice = Invoice.findWhere(account: account, status: InvoiceStatus.values()[6])
                    if(invoice) {
                        
                        if(requestData.isRecurring == true || requestData.isRecurring == "true") {
                            def recurringItem = addRecurringItemForAccount(account, requestData.name, requestData.amount, requestData.billingCycles)                            
                            //add manual item
                            addInvoiceItem(invoice, BillableItem.get(9), "monthly", requestData.name, requestData.name, null, requestData.amount, null, null)
                            recurringItem.uses = recurringItem.uses + 1 
                            recurringItem.save(flush: true)
                            updateInvoice(invoice)
                        } else {
                            //add manual item
                            addInvoiceItem(invoice, BillableItem.get(8), "monthly", requestData.name, requestData.name, null, requestData.amount, null, null)
                            updateInvoice(invoice)
                        }
                    }                    
                }
            } else if(requestData.isAllAccount == "SELECTIVE") {
                def accounts = requestData.accountId.split(",");
                for(def i=0; i < accounts.length; i++) {
                    def account = Account.get(Integer.parseInt(accounts[i]))
                    def invoice = Invoice.findWhere(account: account, status: InvoiceStatus.values()[6])
                    if(invoice) {                       
                        if(requestData.isRecurring == true || requestData.isRecurring == "true") {
                            def recurringItem = addRecurringItemForAccount(account, requestData.name, requestData.amount, requestData.billingCycles)
                            //add manual item
                            addInvoiceItem(invoice, BillableItem.get(9), "monthly", requestData.name, requestData.name, null, requestData.amount, null, null)
                            recurringItem.uses = recurringItem.uses + 1 
                            recurringItem.save(flush: true)
                            updateInvoice(invoice)
                        } else {
                            //add manual item
                            addInvoiceItem(invoice, BillableItem.get(8), "monthly", requestData.name, requestData.name, null, requestData.amount, null, null)
                            updateInvoice(invoice)
                        }
                    }
                }
            }
            
        } catch (Exception ex) {
            Console.print(ex); //throw ex;
        } 
    }
    
    
    def addRecurringItemForAccount(account, name, amount, billingCycles) {
        
        def billableItem = BillableItem.get(9)
        if(billableItem.enabled == true || billableItem.enabled == "true") {
            def recurringItem = new RecurringItem()
            recurringItem.billableItem = billableItem
            recurringItem.account = account
            recurringItem.amount = amount
            recurringItem.taxPercent = billableItem.tax.percentage
            double taxAmount = (recurringItem.amount/100)* recurringItem.taxPercent
            recurringItem.taxAmount = Math.ceil(taxAmount * 100d) / 100d;     
            recurringItem.totalAmount = Math.ceil((recurringItem.amount + recurringItem.taxAmount) * 100d) / 100d;        
            recurringItem.name = name
            recurringItem.description = name
            recurringItem.billingCycles = billingCycles
            recurringItem.save(flush: true); 
                        
            return recurringItem           
        }
    }
} 
