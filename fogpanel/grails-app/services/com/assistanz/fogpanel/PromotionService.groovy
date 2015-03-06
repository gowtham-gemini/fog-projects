package com.assistanz.fogpanel

import grails.converters.deep.JSON
import com.assistanz.fogpanel.GeneralConstants;
import java.text.DateFormat
import java.text.SimpleDateFormat
import org.apache.commons.logging.LogFactory;
import org.apache.commons.lang.StringEscapeUtils;
import org.codehaus.groovy.grails.commons.ApplicationHolder

class PromotionService {
    ConfigService configService;
    def springSecurityService;
    private static final log = LogFactory.getLog(this) 
    
    def list() {
        def promotion = Promotion.findAll();
        def currency = ApplicationHolder.getApplication().config.billing.defaultCurrency
        def dateFormatValue = configService.getDateFormat();         
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatValue);
        ArrayList<ArrayList<String>> promotionList = new ArrayList<ArrayList<String>>();
        for(int i=0; i < promotion.size(); i++) { 
            def data = promotion[i]; 
            HashMap<String,String> item = new HashMap<String,String>(); 
            item.put("id", data.id);
            item.put("code", data.code);
            item.put("promotionValue", data.value);
            item.put("startDate", dateFormat.format(data.startDate).toString());
            item.put("endDate", dateFormat.format(data.endDate).toString());
            item.put("maxUses", data.maxUses);
            item.put("uses", data.uses);
            item.put("notes", StringEscapeUtils.escapeHtml(data.notes));
            item.put("type", data.type.name());
            item.put("currency", currency);
            
            promotionList.add(item); 
        }
        return promotionList;
    }
    
    def create(String requestBody) {
        try {  
            
            def user = springSecurityService.currentUser
            
            def requestData = JSON.parse(requestBody)
            def promotion = new Promotion();
            promotion.code = requestData.code;
            promotion.value = requestData.promotionValue;
            DateFormat formater = new SimpleDateFormat("dd/MM/yyyy"); 
            if(requestData.endDate == "NaN/NaN/0NaN") {
                
            } else {
                Date endDate = formater.parse(requestData.endDate);
                promotion.endDate = endDate;
            }
            if(requestData.startDate == "NaN/NaN/0NaN") {
               
            } else {
                Date startDate = formater.parse(requestData.startDate);
                promotion.startDate = startDate;
            }
            promotion.notes = requestData.notes;
            promotion.type = requestData.type;
            if(requestData.maxUses.toString() == null || requestData.maxUses.toString() == "" || requestData.maxUses.toString() == "null") {
               promotion.maxUses = 0;
            } else {
               promotion.maxUses = requestData.maxUses;
            }
            promotion.uses = 0;
            promotion.save(flush: true);
            if (promotion.hasErrors()) {
                throw new ValidationException(promotion.errors.allErrors);
            }
            
            ArrayList<ArrayList<String>> promotionList = new ArrayList<ArrayList<String>>();       
                
            HashMap<String,String> item = new HashMap<String,String>(); 
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            item.put("id", promotion.id);
            item.put("code", promotion.code);
            item.put("promotionValue", promotion.value);
            item.put("startDate", promotion.startDate);
            item.put("endDate", promotion.endDate);
            item.put("maxUses", promotion.maxUses);
            item.put("uses", promotion.uses);
            item.put("notes", promotion.notes);
            item.put("type", promotion.type.name());
            promotionList.add(item); 
            log.info("Promotion:${promotion.id} added  by user${user.id}");                         
            return promotionList; 
        } catch(Exception ex) {
            [ex] as JSON            
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (NullPointerException ex) {
                [ex] as JSON
        }   
    }
    
    def update() {

    }
    
    def deletePromotionCode(String id) {
        try {
            
            def user = springSecurityService.currentUser
            
            def promotion = Promotion.get(id)
            promotion.delete(flush: true);
            log.info("Promotion:${promotion.id} added  by user${user.id}"); 
            return GeneralConstants.RESULT_SUCCESS
        } catch(Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
        
    }
    
    def validate(String code) {
        def promotionalCode = Promotion.findByCode(code);  
        Date date = new Date();
        if(promotionalCode) {
            def promotionalCodeUses = promotionalCode.uses + 1;
            if(promotionalCode.maxUses == 0 && (promotionalCode.startDate == null && promotionalCode.endDate == null)) {
                return [GeneralConstants.RESULT_SUCCESS]
            } else if(promotionalCode.maxUses == 0 && (promotionalCode.startDate != null && promotionalCode.endDate == null)) {
                if(date.getTime() <= promotionalCode.startDate.getTime()) {
                    return [GeneralConstants.RESULT_EXPIRED]
                } else {
                    return [GeneralConstants.RESULT_SUCCESS]
                }
            } else if(promotionalCode.maxUses == 0 && (promotionalCode.startDate == null && promotionalCode.endDate != null)) {
                if(date.getTime() >= promotionalCode.endDate.getTime()) {
                    return [GeneralConstants.RESULT_EXPIRED]
                } else {
                    return [GeneralConstants.RESULT_SUCCESS]
                }
            } else if(promotionalCode.maxUses == 0 && (promotionalCode.startDate != null && promotionalCode.endDate != null)) {
                if((date.getTime() >= promotionalCode.startDate.getTime()) && (date.getTime() <= promotionalCode.endDate.getTime())) {
                    return [GeneralConstants.RESULT_SUCCESS]
                } else {
                    return [GeneralConstants.RESULT_EXPIRED]
                }
            } else if(promotionalCode.maxUses != 0 && (promotionalCode.startDate == null && promotionalCode.endDate == null)) {
                if(promotionalCodeUses >= promotionalCode.maxUses) {
                    return [GeneralConstants.RESULT_EXPIRED]
                } else {
                    return [GeneralConstants.RESULT_SUCCESS]
                }
            } else if(promotionalCode.maxUses != 0 && (promotionalCode.startDate != null && promotionalCode.endDate == null)) {
                if((date.getTime() <= promotionalCode.startDate.getTime()) || (promotionalCodeUses >= promotionalCode.maxUses)) {
                    return [GeneralConstants.RESULT_EXPIRED]
                } else {
                    return [GeneralConstants.RESULT_SUCCESS]
                }
            } else if(promotionalCode.maxUses != 0 && (promotionalCode.endDate != null && promotionalCode.startDate == null)) {
                if((date.getTime() >= promotionalCode.endDate.getTime()) || (promotionalCodeUses >= promotionalCode.maxUses)) {
                    return [GeneralConstants.RESULT_EXPIRED]
                } else {
                    return [GeneralConstants.RESULT_SUCCESS]
                }
            } else if(promotionalCode.maxUses != 0 && (promotionalCode.startDate != null && promotionalCode.endDate != null)) {
                if(((date.getTime() >= promotionalCode.startDate.getTime()) && (date.getTime() <= promotionalCode.endDate.getTime())) && (promotionalCodeUses <= promotionalCode.maxUses)) {
                    return [GeneralConstants.RESULT_SUCCESS]
                } else {
                    return [GeneralConstants.RESULT_EXPIRED]
                }
            }    
            
        } else {
            return [GeneralConstants.RESULT_FAILURE] 
        }
    }
}
