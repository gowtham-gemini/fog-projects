package com.assistanz.fogpanel

import java.util.HashMap;
import org.springframework.context.MessageSource
import org.apache.commons.logging.LogFactory;
import grails.converters.deep.JSON;
import grails.transaction.Transactional
import com.assistanz.fogpanel.GeneralConstants;

@Transactional
class BillableItemService {
    MessageSource messageSource;
    def springSecurityService;
    //list all billable items
    
    private static final log = LogFactory.getLog(this)
    
    def list() {
        
        def user = springSecurityService.currentUser
        def language = "";
        if(user.account.preferredLanguage == "" || user.account.preferredLanguage == "null" || user.account.preferredLanguage == null) {
            Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
            language = defaultLanguage.value
        } else {
            language = user.account.preferredLanguage
        }
        //find all billable item
        def billableItems = BillableItem.findAll();
        
        //stores billable item 
        ArrayList<ArrayList<String>> billableItemList = new ArrayList<ArrayList<String>>();     
        for(int i=0; i < billableItems.size(); i++) { 
            def item = billableItems[i]; 
                HashMap<String,String> billableItem = new HashMap<String,String>();   
                billableItem.put("id", item.id);
                billableItem.put("name", messageSource.getMessage(item.name, null, new Locale(language)));
                billableItem.put("nameKey", item.name);
                billableItem.put("enabled", item.enabled);
                billableItem.put("customized", item.customized);
                billableItem.put("taxPercentage", item.tax.percentage);
                billableItem.put("taxName", item.tax.name);
                billableItem.put("taxId", item.tax.id);
                billableItem.put("isDiscountable", item.discountable);
                billableItem.put("hasZone", item.hasZone);
                // add to billable list
                billableItemList.add(billableItem);
        }
        
        return billableItemList;
    }
    
    def getBillableItem(id) {
        
        def user = springSecurityService.currentUser
        def language = "";
        if(user.account.preferredLanguage == "" || user.account.preferredLanguage == "null" || user.account.preferredLanguage == null) {
            Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
            language = defaultLanguage.value
        } else {
            language = user.account.preferredLanguage
        }
        
        def billableItem = BillableItem.get(id)
        
        ArrayList<ArrayList<String>> billableItemList = new ArrayList<ArrayList<String>>();     
        
        HashMap item = new HashMap();   
        item.put("id", billableItem.id);
        item.put("name", messageSource.getMessage(billableItem.name, null, new Locale(language)));
        item.put("nameKey", billableItem.name);
        item.put("enabled", billableItem.enabled);
        item.put("customized", billableItem.customized);
        item.put("taxPercentage", billableItem.tax.percentage);
        item.put("taxName", billableItem.tax.name);
        item.put("taxId", billableItem.tax.id);
        item.put("isDiscountable", billableItem.discountable);
        item.put("tax", billableItem.tax);
        item.put("hasZone", billableItem.hasZone);
        
        billableItemList.add(item)
        
        return billableItemList;
    }
    
   def update(String requestBody) {
       
        try {
            
            def user = springSecurityService.currentUser
            
             // convert string to json object
            def requestData = JSON.parse(requestBody)
            BillableItem billableItem = BillableItem.get(requestData.id); 
            Tax tax = Tax.get(requestData.taxId);
            billableItem.tax = tax;
            billableItem.enabled = requestData.enabled;
            billableItem.discountable = requestData.isDiscountable;
//            billableItem.hasZone = requestData.hasZone;
            billableItem.save(flush: true);     
            
            if (billableItem.hasErrors()) {
                throw new ValidationException(billableItem.errors.allErrors);
            }        
            
            ArrayList<ArrayList<String>> billableItemList = new ArrayList<ArrayList<String>>();            
            HashMap<String,String> item = new HashMap<String,String>();                
            item.put("id", billableItem.id);
            item.put("name", billableItem.name);
            item.put("enabled", billableItem.enabled);
            item.put("customized", billableItem.customized);
            item.put("taxPercentage", billableItem.tax.percentage);
            item.put("taxId", billableItem.tax.id);
            item.put("taxName", billableItem.tax.name);
//            item.put("hasZone", billableItem.hasZone);
            item.put("isDiscountable", billableItem.discountable);
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            billableItemList.add(item);
                        
            log.info("billableitem ${billableItem.id} is update by user ${user.id} to ${requestData}")   
            
           return billableItemList;
       } catch (Exception ex) {
           ex.printStackTrace(System.err);
           throw ex;
       } 
        
        
   }
    
}
