package com.assistanz.fogpanel

import com.assistanz.cloud.cloudstack.CloudStackServer
import com.assistanz.cloud.cloudstack.securitygroup.CSSecurityGroupService
import com.assistanz.cloud.cloudstack.SecurityGroupResponse
import com.assistanz.cloud.cloudstack.EgressRuleResponse
import com.assistanz.cloud.cloudstack.IngressRuleResponse
import com.assistanz.fogpanel.GeneralConstants;
import grails.converters.deep.JSON
import org.springframework.context.MessageSource
import org.apache.commons.logging.LogFactory;
import grails.transaction.Transactional

@Transactional
class SecurityGroupTemplateService {
    MessageSource messageSource
    private static final log = LogFactory.getLog(this)
    def list(String tempId, String rule, String baseOs, String disabled) {
        
        def securityGroupTempQuery = SecurityGroupTemplate.createCriteria()
        def securityGroupTempResult; 
        
        ArrayList<ArrayList<String>> ruleTypeList = new ArrayList<ArrayList<String>>(); 
        ArrayList<ArrayList<String>> securityGroupTemplate = new ArrayList<ArrayList<String>>();   
        if((tempId == null||tempId == "null") && (rule == null||rule == "null") && (baseOs == null || baseOs == "null") && (disabled == null || disabled == "null")) {
//            def result = Zone.findAll();
//            for(int index=0; index < result.size(); index++) {
//                def zoneItem = result[index];
//                for(Iterator k = zoneItem.securityGroupTemplates.iterator();k.hasNext();) {
//                    def securityGroup = k.next();
                    def securityGroupData = SecurityGroupTemplate.findAllWhere(disabled: false);
                    for(int index=0; index < securityGroupData.size(); index++) {
                        def securityGroup = securityGroupData[index];
                        if(securityGroupData != "null") {
                        HashMap<String,String> securityGroupTemplateItem = new HashMap<String,String>();                                                
                        securityGroupTemplateItem.put("securityGroupId", securityGroup.id);
                        securityGroupTemplateItem.put("securityGroupName", securityGroup.name);
                        securityGroupTemplateItem.put("description", securityGroup.description);
                        securityGroupTemplateItem.put("disabled", securityGroup.disabled);   
                        securityGroupTemplateItem.put("baseOs", securityGroup.baseOs);   
                        
                        securityGroupTemplate.add(securityGroupTemplateItem);
                    }
                    }
                    
//                }             
//            } 
            return securityGroupTemplate
        } else if((tempId != null || tempId != "null") && (rule != null || rule != "null") && (baseOs == null || baseOs == "null") && (disabled == null || disabled == "null")) {            
            def result = SecurityGroupTemplateRule.findAllWhere(securityGroupTemplate: SecurityGroupTemplate.get(tempId), securityGroupType :GeneralConstants.SECURITY_GROUP_TYPE.valueOf(rule))
            for(int index = 0; index< result.size(); index++) {
                def item = result[index];              
                
                HashMap<String,String> ruleType = new HashMap<String,String>();
                ruleType.put("cidr", item.cidr);
                ruleType.put("endPort", item.endPort);
                ruleType.put("startPort", item.startPort);
                ruleType.put("securityGroupPort", item.securityGroupPort);
                ruleType.put("id", item.id);
                ruleType.put("securityGroupRuleType", item.securityGroupType);
                ruleType.put("securityGroupTemplateID", item.securityGroupTemplate.id);
                ruleType.put("securityGroupTemplateName", item.securityGroupTemplate.name);
                ruleType.put("securityGroupTemplatedescription", item.securityGroupTemplate.description);                
                ruleType.put("securityGroupTemplateReferenceId", item.securityGroupTemplate.securityGroupTemplateReferenceId);
                ruleType.put("disabled", item.securityGroupTemplate.disabled);                     
                ruleTypeList.add(ruleType);               
            } 
            return ruleTypeList;
        } else if((baseOs != null || baseOs != "null") && (disabled != null || disabled != "null") && (tempId == null || tempId == "null") && (rule == null || rule == "null")) {            
//            Console.print("valaal" + baseOs)
            def result = SecurityGroupTemplate.findAllWhere(disabled: Boolean.valueOf(disabled), baseOs: GeneralConstants.SECURITY_GROUP_BASE_OS.valueOf(baseOs));
            for(int index=0; index < result.size(); index++) {
                def securityGroup = result[index];
//                for(Iterator k = zoneItem.securityGroupTemplates.iterator();k.hasNext();) {
//                    def securityGroup = k.next();
//                    def securityGroupData = SecurityGroupTemplate.findWhere(id:securityGroup.id, disabled: false);
                    if(securityGroup != "null") {
                        HashMap<String,String> securityGroupTemplateItem = new HashMap<String,String>();
                        
                        securityGroupTemplateItem.put("securityGroupId", securityGroup.id);
                        securityGroupTemplateItem.put("securityGroupName", securityGroup.name);
                        securityGroupTemplateItem.put("description", securityGroup.description);
                        securityGroupTemplateItem.put("disabled", securityGroup.disabled);
                        securityGroupTemplateItem.put("baseOs", securityGroup.baseOs);                                       
                        securityGroupTemplate.add(securityGroupTemplateItem);
                    }
//                }             
            } 
            return securityGroupTemplate
        }
    }
    
    def get(Long currentId) {
        def securityGroup = SecurityGroupTemplate.get(currentId)
        ArrayList<ArrayList<String>> securityGroupList = new ArrayList<ArrayList<String>>(); 
        HashMap<String,String> securityGroupTemplateItem = new HashMap<String,String>();
        securityGroupTemplateItem.put("securityGroupId", securityGroup.id);
        securityGroupTemplateItem.put("securityGroupName", securityGroup.name);
        securityGroupTemplateItem.put("description", securityGroup.description);
        securityGroupTemplateItem.put("disabled", securityGroup.disabled);
               
        securityGroupList.add(securityGroupTemplateItem);
        return securityGroupList;
    }
    
    def createSecurityGroup(String requestBody) {        
        try {           
            def requestData = JSON.parse(requestBody)
            def newSecurityGroupTemplate = new SecurityGroupTemplate();
            newSecurityGroupTemplate.name = requestData.name
            newSecurityGroupTemplate.description = requestData.description
            newSecurityGroupTemplate.baseOs = requestData.baseOs
            newSecurityGroupTemplate.disabled = false 
            
            newSecurityGroupTemplate.save(flush: true);
            if (newSecurityGroupTemplate.hasErrors()) {
                throw new ValidationException(newSecurityGroupTemplate.errors.allErrors);
            }            
            ArrayList<ArrayList<String>> securityGroupTemplate = new ArrayList<ArrayList<String>>();                                           
            HashMap<String,String> securityGroupTemplateItem = new HashMap<String,String>();
            securityGroupTemplateItem.put("result", "OK");
            securityGroupTemplateItem.put("securityGroupId", newSecurityGroupTemplate.id);
            securityGroupTemplateItem.put("securityGroupName", newSecurityGroupTemplate.name);
            securityGroupTemplateItem.put("description", newSecurityGroupTemplate.description);
            securityGroupTemplateItem.put("disabled", newSecurityGroupTemplate.disabled);
            securityGroupTemplate.add(securityGroupTemplateItem);     
            log.info("Admin security group: ${newSecurityGroupTemplate.id} created successfully") 
            return securityGroupTemplate; 
        } catch(Exception ex) {
            [ex] as JSON            
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (NullPointerException ex) {
                [ex] as JSON
        }    
    }
    def createRule(String requestBody) {
        try {
            def requestData = JSON.parse(requestBody)
            def ruleNo;
            def ruleTypeNo;
            if(requestData.protocol == "TCP") {
                ruleNo = 0;
            } else if(requestData.protocol == "UDP") {
                ruleNo = 1;
            } else {
                ruleNo = 2;
            }
            
            if(requestData.ruleType == "INGRESS") {
                ruleTypeNo = 0;
            } else {
                ruleTypeNo = 1;
            }
            def securityGroupRule = SecurityGroupTemplateRule.findAllWhere(securityGroupType: SecurityGroupRule.values()[ruleTypeNo], securityGroupPort: SecurityProtocol.values()[ruleNo], startPort: requestData.startPort, endPort: requestData.endPort, cidr: requestData.cidr)
            if(!securityGroupRule) {
                def newSecurityGroupTemplateRule = new SecurityGroupTemplateRule();
                newSecurityGroupTemplateRule.securityGroupPort = requestData.protocol
                newSecurityGroupTemplateRule.startPort = requestData.startPort
                newSecurityGroupTemplateRule.endPort = requestData.endPort
                newSecurityGroupTemplateRule.cidr = requestData.cidr
                newSecurityGroupTemplateRule.securityGroupType = requestData.ruleType

                newSecurityGroupTemplateRule.securityGroupTemplate = SecurityGroupTemplate.get(requestData.securityGroupId); 
                newSecurityGroupTemplateRule.save(flush: true);
                if (newSecurityGroupTemplateRule.hasErrors()) {
                    throw new ValidationException(newSecurityGroupTemplateRule.errors.allErrors);
                }
                ArrayList<ArrayList<String>> securityGroupTemplateRule = new ArrayList<ArrayList<String>>();   
                HashMap<String,String> securityGroupTemplateRuleItem = new HashMap<String,String>();
                securityGroupTemplateRuleItem.put("result", "OK");
                securityGroupTemplateRuleItem.put("ruleId", newSecurityGroupTemplateRule.id);            
                securityGroupTemplateRuleItem.put("securityGroupTemplate", SecurityGroupTemplate.get(requestData.securityGroupId));
                securityGroupTemplateRuleItem.put("securityGroupPort", newSecurityGroupTemplateRule.securityGroupPort);
                securityGroupTemplateRuleItem.put("startPort", newSecurityGroupTemplateRule.startPort);
                securityGroupTemplateRuleItem.put("endPort", newSecurityGroupTemplateRule.endPort);
                securityGroupTemplateRuleItem.put("cidr", newSecurityGroupTemplateRule.cidr);
                securityGroupTemplateRuleItem.put("ruleType", newSecurityGroupTemplateRule.securityGroupType);
                securityGroupTemplateRule.add(securityGroupTemplateRuleItem);
                log.info("Admin : ${requestData.ruleType} rule added for admin firewall: ${requestData.securityGroupId}") 
                return securityGroupTemplateRule; 
            } else {
                Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
                def language = defaultLanguage.value
                throw new Exception(messageSource.getMessage('firewall.rule.alreadyExist', null, new Locale(language)));
            }
            
        } catch(Exception ex) {
            [ex] as JSON
        }
        
    }
   def disableSecurityGroup(String requestBody) {
       try {           
           def requestData = JSON.parse(requestBody)
           def oldSecurityGroup = SecurityGroupTemplate.get(requestData.id)
           oldSecurityGroup.disabled = requestData.disabled;
           oldSecurityGroup.save(flush: true)    
           if (oldSecurityGroup.hasErrors()) {
                throw new ValidationException(oldSecurityGroup.errors.allErrors);
            }
            ArrayList<ArrayList<String>> securityGroupTemplate = new ArrayList<ArrayList<String>>();   
            HashMap<String,String> optional = new HashMap<String,String>(); 
            optional.put("result", "OK");
            optional.put("disabled", oldSecurityGroup.disabled);
            securityGroupTemplate.add(optional)
            log.info("Admin firewall: ${requestData.id} is disabled sucessfully") 
            return securityGroupTemplate;
        } catch(Exception ex) {
            [ex] as JSON
        }
   }
   def deleteFirewal(id) {
       try {  
           def securityGroup = SecurityGroupTemplate.get(id);
            securityGroup.delete(flush: true);
            if (securityGroup.hasErrors()) {
                throw new ValidationException(securityGroup.errors.allErrors);
            }
            log.info("Admin firewall: ${id} is deleted sucessfully") 
            return "OK"
        } catch(Exception ex) {
            [ex] as JSON            
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex) {
            [ex] as JSON
        } 
   }
   
    def deleterule(id) {
        try {  
            def securityGroupRule = SecurityGroupTemplateRule.get(id);
            securityGroupRule.delete(flush: true);
            if (securityGroupRule.hasErrors()) {
                throw new ValidationException(securityGroupRule.errors.allErrors);
            }
            log.info("Admin ${securityGroupRule.securityGroupType} rule type is deleted sucessfully") 
            return "OK"
        } catch(Exception ex) {
            [ex] as JSON            
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex) {
            [ex] as JSON
        } 
    }
    
}
