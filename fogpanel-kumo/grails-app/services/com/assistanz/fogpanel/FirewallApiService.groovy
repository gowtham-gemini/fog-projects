package com.assistanz.fogpanel

class FirewallApiService {

    def getFirewall(baseOs) {
        try {
            ArrayList<ArrayList<String>> securityGroupTemplate = new ArrayList<ArrayList<String>>();   
            def baseOsNo = "";
            if(baseOs == "Linux") {
                baseOsNo = 0;
            } else {
                baseOsNo = 1;
            }     
            def securityGroupData =  SecurityGroupTemplate.withCriteria {                                              
                eq("disabled", false)                                                  
                if(baseOs) {
                    eq("baseOs", BaseOs.values()[baseOsNo])  
                }  
            }
            for(int index=0; index < securityGroupData.size(); index++) {
                def securityGroup = securityGroupData[index];
                if(securityGroup) {                    
                    HashMap<String,String> securityGroupTemplateItem = new HashMap<String,String>();                                                
                    securityGroupTemplateItem.put("securityGroupId", securityGroup.id);
                    securityGroupTemplateItem.put("securityGroupName", securityGroup.name);
                    securityGroupTemplateItem.put("description", securityGroup.description);
                    securityGroupTemplateItem.put("disabled", securityGroup.disabled);  
                    securityGroupTemplateItem.put("baseOS", securityGroup.baseOs.name());  
                    
                    securityGroupTemplate.add(securityGroupTemplateItem);
                }
            }      
            return securityGroupTemplate;
        } catch(Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }        
    }
}
