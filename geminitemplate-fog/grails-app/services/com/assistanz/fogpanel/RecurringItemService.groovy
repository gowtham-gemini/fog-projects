package com.assistanz.fogpanel

import grails.converters.deep.JSON
import java.text.SimpleDateFormat
import java.text.DateFormat
import org.codehaus.groovy.grails.commons.ApplicationHolder
import org.apache.commons.logging.LogFactory;

class RecurringItemService {

    def springSecurityService; 
    private static final log = LogFactory.getLog(this)
//    LicenseValidationService licenseValidationService
    
    def count() {
        def currency = ApplicationHolder.getApplication().config.billing.defaultCurrency
        try {
            
            ArrayList countList = new ArrayList();  
            HashMap countItem = new HashMap();
            def customItems;
            def customItemsAmount;
            def customItemsTotalAmount = 0;
            ArrayList<ArrayList<String>> customItemArrayList = new ArrayList<ArrayList<String>>();  
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
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
                        eq("billableItem", BillableItem.get(9))
                        and {
                            eq("invoice", invoice) 
                        }
                    }
                    
                    for(int i=0; i < customItems.size(); i++) { 
                    def item = customItems[i]; 
                    HashMap<String,String> cus = new HashMap<String,String>();                 
                        def recurringItem = RecurringItem.get(Long.parseLong(item.id))
                        cus.put("id", recurringItem.id);
                        cus.put("account", recurringItem.account.accountIdentifier);
                        cus.put("taxPercent", recurringItem.taxPercent);
                        cus.put("amount", recurringItem.amount);
                        cus.put("taxAmount", recurringItem.taxAmount);
                        cus.put("totalAmount", recurringItem.totalAmount);
                        cus.put("name", recurringItem.name);
                        cus.put("description", recurringItem.description);
                        cus.put("cycle", recurringItem.billingCycles);
                        cus.put("uses", recurringItem.uses);
                        customItemArrayList.add(cus);                
                    }
                    def invoiceItemCriteriaCustomAmt1 = InvoiceItem.createCriteria()
                    customItemsAmount = invoiceItemCriteriaCustomAmt1.list {
                        eq("billableItem", BillableItem.get(9))
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
                    
                }
                
                Calendar currentMonthStartDateCalendar = Calendar.getInstance();
                currentMonthStartDateCalendar.set(Calendar.DATE, 1);
                
                Calendar currentMonthEndDateCalendar = Calendar.getInstance(); 
                currentMonthEndDateCalendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(billingPeriodfixedDay.value));
                       
                Date fromDate = currentMonthStartDateCalendar.getTime()
                Date toDate = currentMonthEndDateCalendar.getTime()
                                                
                countItem.put("currMonth", dateFormat.format(fromDate).toString() + " - " + dateFormat.format(toDate).toString());
                
            } else if(billingTypeFixedEnabled.value == "false" || billingTypeFixedEnabled.value == false) {
                Calendar startDateCalendar = Calendar.getInstance();
                startDateCalendar.set(Calendar.DAY_OF_MONTH, 1); 
                
                Calendar currDateCalendar = Calendar.getInstance();
                Date fromDate = startDateCalendar.getTime()
                Date toDate = currDateCalendar.getTime()
                def invoiceCriteria = Invoice.createCriteria()
                def monthlyInvoiceList = invoiceCriteria.list { 
                    ge("date", fromDate) and { le("date", toDate) }  
                    projections {
                        sum("totalAmount")
                    }
                }
                  
                for(def invoice: monthlyInvoiceList) {
                    def invoiceItemCriteriaCustom = InvoiceItem.createCriteria()
                    customItems = invoiceItemCriteriaCustom.list {
                        eq("billableItem", BillableItem.get(9))
                        and {
                            eq("invoice", invoice) 
                        }
                    }
                    
                    for(int i=0; i < customItems.size(); i++) { 
                    def item = customItems[i]; 
                    HashMap<String,String> cus = new HashMap<String,String>();                 
                        def recurringItem = RecurringItem.get(Long.parseLong(item.id))
                        cus.put("id", recurringItem.id);
                        cus.put("account", recurringItem.account.accountIdentifier);
                        cus.put("taxPercent", recurringItem.taxPercent);
                        cus.put("amount", recurringItem.amount);
                        cus.put("taxAmount", recurringItem.taxAmount);
                        cus.put("totalAmount", recurringItem.totalAmount);
                        cus.put("name", recurringItem.name);
                        cus.put("description", recurringItem.description);
                        cus.put("cycle", recurringItem.billingCycles);
                        cus.put("uses", recurringItem.uses);
                        customItemArrayList.add(cus);               
                    }
                    def invoiceItemCriteriaCustomAmt = InvoiceItem.createCriteria()
                    customItemsAmount = invoiceItemCriteriaCustomAmt.list {
                        eq("billableItem", BillableItem.get(9))
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
                    
                }
                
                countItem.put("currMonth", dateFormat.format(fromDate).toString() + " - " + dateFormat.format(toDate).toString());
               
            }
            
            countItem.put("recurringItemList", customItemArrayList);  
            countItem.put("currency", currency);  
            countItem.put("recurringItemTotalAmount", customItemsTotalAmount);  
             
            countList.add(countItem);
            return countList;
        
        } catch (Exception ex) { 
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
    
    def list(accountId) {
        def recurringItemList = RecurringItem.findAllWhere(deleted: false)
        if((accountId == "null" || accountId == null)) {
            recurringItemList = RecurringItem.findAllWhere(deleted: false)
        } else if((accountId != "null" || accountId != null)) {
            recurringItemList = RecurringItem.findAllWhere(account:Account.get(accountId), deleted: false)
        }
        ArrayList<ArrayList<String>> recurringArrayList = new ArrayList<ArrayList<String>>();
        for(int recurring = 0; recurring < recurringItemList.size(); recurring++) {
            def recurringItem = recurringItemList[recurring]; 
              
            if(recurringItem.billingCycles == recurringItem.uses) {
                recurringItem.deleted = true
                recurringItem.save(flush: true); 
            }
            
            HashMap<String,String> item = new HashMap<String,String>();
            item.put("id", recurringItem.id);
            item.put("account", recurringItem.account.accountIdentifier);
            item.put("taxPercent", recurringItem.taxPercent);
            item.put("amount", recurringItem.amount);
            item.put("taxAmount", recurringItem.taxAmount);
            item.put("totalAmount", recurringItem.totalAmount);
            item.put("name", recurringItem.name);
            item.put("description", recurringItem.description);
            item.put("cycle", recurringItem.billingCycles);
            item.put("uses", recurringItem.uses);
            item.put("type", "Recurring");
            recurringArrayList.add(item);        
        }
        def account = Account.get(accountId)
        def invoice = Invoice.findWhere(account: account, status: InvoiceStatus.values()[6])
        
        def invoiceItemCriteriaCustom = InvoiceItem.createCriteria()
        def customItems = invoiceItemCriteriaCustom.list {
            eq("billableItem", BillableItem.get(8))
            and {
                eq("invoice", invoice) 
            }
        }
        
        for(def cusItem: customItems) {
            HashMap<String,String> cus = new HashMap<String,String>();
            cus.put("id", cusItem.id);
            cus.put("account", account.accountIdentifier);
            cus.put("amount", cusItem.usageAmount);
            cus.put("name", cusItem.referenceItemName);
            cus.put("taxAmount", cusItem.taxAmount);
            cus.put("taxPercent", cusItem.taxPercent);
            cus.put("totalAmount", cusItem.totalAmount);
            cus.put("name", cusItem.referenceItemName);
            cus.put("description", cusItem.referenceItemName);
            cus.put("type", "Custom");
            cus.put("cycle", 1);
            cus.put("uses", 1);
            recurringArrayList.add(cus);
        }
        
        
        return recurringArrayList;
    }
    def getCurrentData(id) {
       def recurringItemList = RecurringItem.get(id);
       ArrayList<ArrayList<String>> recurringArrayList = new ArrayList<ArrayList<String>>();
        
            
            HashMap<String,String> item = new HashMap<String,String>();
            item.put("id", recurringItemList.id);
            item.put("account", recurringItemList.account.accountIdentifier);
            item.put("taxPercent", recurringItemList.taxPercent);
            item.put("amount", recurringItemList.amount);
            item.put("taxAmount", recurringItemList.taxAmount);
            item.put("totalAmount", recurringItemList.totalAmount);
            item.put("name", recurringItemList.name);
            item.put("description", recurringItemList.description);
            recurringArrayList.add(item);        
        
        
        return recurringArrayList;
    }
    
    
    def getCurrentUserRecuringItem() {
        def currency = ApplicationHolder.getApplication().config.billing.defaultCurrency
        def result = springSecurityService.currentUser
         
        ArrayList countList = new ArrayList();  
        HashMap countItem = new HashMap();
        def recuringItemsAmount = 0;
        def customItemsTotalAmount = 0;
        ArrayList<ArrayList<String>> customItemArrayList = new ArrayList<ArrayList<String>>();   
        
        def invoice = Invoice.findWhere(account: result.account, status: InvoiceStatus.values()[6])
        
        
        if(!invoice) {
            countItem.put("currMonth", "");
            countItem.put("recurringItemTotalAmount", currency +" "+ 0);  
            countItem.put("customItemTotalAmount", currency  + " " + 0);  
            countItem.put("currency", currency);  
            countItem.put("customItemCost", 0);  
            countItem.put("recurringItemCost", 0);
            
            countItem.put("recurringItemList", "");
            countItem.put("customItemList", "");  
             

            countList.add(countItem);  
        
            return countList;           
        }
        
        
        def invoiceItemCriteriaRecAmt = InvoiceItem.createCriteria()
            def recItemAmount = invoiceItemCriteriaRecAmt.list {
                eq("billableItem", BillableItem.get(9))
                and {
                    eq("invoice", invoice) 
                }
                projections {
                    sum("totalAmount")
                }
            }
        if(recItemAmount[0] == null || recItemAmount[0] == "null") {
            recuringItemsAmount = 0
        } else {
           recuringItemsAmount = recuringItemsAmount + recItemAmount[0]
        }
        
        def invoiceItemCriteriaCusAmt = InvoiceItem.createCriteria()
            def cusItemAmount = invoiceItemCriteriaCusAmt.list {
                eq("billableItem", BillableItem.get(8))
                and {
                    eq("invoice", invoice) 
                }
                projections {
                    sum("totalAmount")
                }
            }
        if(cusItemAmount[0] == null || cusItemAmount[0] == "null") {
            customItemsTotalAmount = 0
        } else {
           customItemsTotalAmount = customItemsTotalAmount + cusItemAmount[0]
        }
        
        
        def invoiceItemCriteriaCustom = InvoiceItem.createCriteria()
            def customItems = invoiceItemCriteriaCustom.list {
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
        
        
        def recurringItemList = RecurringItem.findAllWhere(account:result.account, deleted: false)
        
        ArrayList<ArrayList<String>> recurringArrayList = new ArrayList<ArrayList<String>>();
        for(int recurring = 0; recurring < recurringItemList.size(); recurring++) {
            def recurringItem = recurringItemList[recurring]; 
              
            if(recurringItem.billingCycles == recurringItem.uses) {
                recurringItem.deleted = true
                recurringItem.save(flush: true); 
            }
            
            HashMap<String,String> item = new HashMap<String,String>();
            item.put("id", recurringItem.id);
            item.put("account", recurringItem.account.accountIdentifier);
            item.put("taxPercent", recurringItem.taxPercent);
            item.put("amount", recurringItem.amount);
            item.put("taxAmount", recurringItem.taxAmount);
            item.put("totalAmount", recurringItem.totalAmount);
            item.put("name", recurringItem.name);
            item.put("description", recurringItem.description);
            item.put("cycle", recurringItem.billingCycles);
            item.put("uses", recurringItem.uses);
            recurringArrayList.add(item);        
        }
        
        DateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
        Date fromDate = formater2.parse(invoice.previousInvoiceDate.toString());
        Date toDate = formater2.parse(invoice.date.toString());
        
        countItem.put("currMonth", formater.format(fromDate).toString() + " - " + formater.format(toDate).toString());
        
        countItem.put("recurringItemList", recurringArrayList);  
        countItem.put("customItemList", customItemArrayList);  
        countItem.put("recurringItemTotalAmount", currency +" "+ recuringItemsAmount);  
        countItem.put("customItemTotalAmount", currency + " " + customItemsTotalAmount);  
        
        countItem.put("currency", currency);  
        countItem.put("customItemCost", customItemsTotalAmount);  
        countItem.put("recurringItemCost", recuringItemsAmount);  
        
             
        countList.add(countItem);  
        
        return countList;
        
    }
    
    // update for recurring item
    def update(recurringItemData) {
        
        
//        licenseValidationService.authorizeAction(FogPanelService.RECURRING_TIEM_UPDATE)
        
        def user = springSecurityService.currentUser
        
        def recurringItem = RecurringItem.get(Long.parseLong(recurringItemData.id))
        recurringItem.name = recurringItemData.name
        recurringItem.description = recurringItemData.name
        recurringItem.taxPercent = recurringItemData.taxPercent
        recurringItem.amount = recurringItemData.amount
        recurringItem.taxPercent = recurringItemData.taxPercentage
        double taxAmount = (recurringItem.amount/100)* recurringItem.taxPercent
        recurringItem.taxAmount = taxAmount;     
        recurringItem.totalAmount = recurringItem.amount + recurringItem.taxAmount       
        recurringItem.save(flush: true); 
        log.info("Recurring Item updated for item: ${recurringItem.id} to : ${recurringItemData} by user ${user}")
        
        ArrayList<ArrayList<String>> recurringArrayList = new ArrayList<ArrayList<String>>();
        HashMap<String,String> item = new HashMap<String,String>();
        item.put("result", GeneralConstants.RESULT_SUCCESS);
        item.put("id", recurringItem.id);
        item.put("account", recurringItem.account.accountIdentifier);
        item.put("taxPercent", recurringItem.taxPercent);
        item.put("amount", recurringItem.amount);
        item.put("taxAmount", recurringItem.taxAmount);
        item.put("totalAmount", recurringItem.totalAmount);
        item.put("name", recurringItem.name);
        item.put("description", recurringItem.description);
        recurringArrayList.add(item);
        
        return recurringArrayList;
    }
    
    def delete(id) {
      try {
//            licenseValidationService.authorizeAction(FogPanelService.RECURRING_ITEM_DELETE)
            
            def recurringItem = RecurringItem.get(id);

            recurringItem.deleted = true
            recurringItem.save(flush: true); 
            if (recurringItem.hasErrors()) {
                throw new ValidationException(recurringItem.errors.allErrors);
            }
        } catch(Exception ex) {
            [ex] as JSON            
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex) {
            [ex] as JSON
        } 
    }
}
