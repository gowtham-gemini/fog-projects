package com.assistanz.fogpanel

import grails.converters.deep.JSON
import com.assistanz.fogpanel.GeneralConstants;
import org.apache.commons.logging.LogFactory;

class TaxService {
    
    def springSecurityService;
    private static final log = LogFactory.getLog(this)
    
    def list() {
        
        return Tax.findAll();
                
//        def taxCriteria = Tax.createCriteria()
//        def taxList = taxCriteria.list {
//           eq("invoice", invoice)
//           
//       }
        
    }
    
    def createTax(String requestBody) {
        try {
            
            def user = springSecurityService.currentUser
            
            def requestData = JSON.parse(requestBody)
            def newTax = new Tax();
            newTax.name = requestData.name
            newTax.description = requestData.description
            newTax.percentage = new Double(requestData.percentage)   
            newTax.save(flush: true);
            log.info("New Tax:${newTax.id} added by user${user.id}");
            if (newTax.hasErrors()) {
                throw new ValidationException(newTax.errors.allErrors);
            }
            ArrayList<ArrayList<String>> taxResultList = new ArrayList<ArrayList<String>>();       
            HashMap<String,String> taxItem = new HashMap<String,String>();
            taxItem.put("result", "OK");
            taxItem.put("id", newTax.id);
            taxItem.put("name", newTax.name);
            taxItem.put("description", newTax.description);
            taxItem.put("percentage", newTax.percentage);
            taxResultList.add(taxItem);
            return taxResultList;
        } catch(Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
        
    }
    def updateTax(String requestBody) {
        try {   
            
            def user = springSecurityService.currentUser
            
            def requestData = JSON.parse(requestBody)
            def oldTax = Tax.get(requestData.id)
            oldTax.name = requestData.name;
            oldTax.description = requestData.description;
            oldTax.percentage = requestData.percentage;

            oldTax.save(flush: true)    
            log.info("Tax:${oldTax.id} updated to${requestBody} by user${user.id}");
            if (oldTax.hasErrors()) {
                 throw new ValidationException(oldTax.errors.allErrors);
             }
             ArrayList<ArrayList<String>> taxList = new ArrayList<ArrayList<String>>();   
             HashMap<String,String> optional = new HashMap<String,String>(); 
             optional.put("result", "OK");
             optional.put("id", oldTax.id);
             optional.put("name", oldTax.name);
             optional.put("description", oldTax.description);
             optional.put("percentage", oldTax.percentage);

             taxList.add(optional)
             return taxList;                     
        } catch(Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
    
    def deleteTax(String id) {
        try {
            def user = springSecurityService.currentUser
            def tax = Tax.get(id);
            def billableItem =  BillableItem.findAllWhere(tax: tax)
            if(billableItem.size() == 0) {
                tax.delete(flush: true);
                log.info("Tax:${tax.id} deleted by user${user.id}");
                return GeneralConstants.RESULT_SUCCESS
            } else {
               return GeneralConstants.RESULT_FAILURE 
            }
        } catch(Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
        
    }
}
