package com.assistanz.fogpanel

class TemplateApiService {
    def getTemplates (baseOs, appTemplate, pageNo, recordPerPage) {
        try {                            
            ArrayList<ArrayList<String>> pageArrayList = new ArrayList<ArrayList<String>>();   
            HashMap currentPage = new HashMap();      
            ArrayList<ArrayList<String>> templateList = new ArrayList<ArrayList<String>>();      
            
            def currentPageNo = "";
            def currentRecordPerPage = "";
            
            if(pageNo) {
                if(pageNo == 0) {
                    currentPageNo = 1
                } else {
                    currentPageNo = Integer.parseInt(pageNo);
                }            
            } else {
                currentPageNo = 1;
            }            

            if(recordPerPage) {            
                currentRecordPerPage = Integer.parseInt(recordPerPage);
            } else {
                currentRecordPerPage = 100;
            } 
            
            def currentTemplateList =  Template.withCriteria {                                                
                eq("deleted", false)                             
                and {
                    eq("isReady", true) 
                }
                if(baseOs) {
                    eq("baseOs", baseOs)  
                }
                if(appTemplate) {
                    eq("appTemplate", new Boolean(appTemplate))  
                } else {
                    eq("appTemplate", false)  
                }          
            } 
            
            currentPage.put("page", currentPageNo)
            currentPage.put("recordPerPage", currentRecordPerPage)                  
            def noOfPages = Math.ceil(currentTemplateList.size() / currentRecordPerPage);            
            def pages = Math.round(noOfPages);            
            currentPage.put("totalPages", pages)     
            currentPage.put("totalRecords", currentTemplateList.size())     
            if(currentPageNo <= pages) {
                def minLimit = (currentPageNo * currentRecordPerPage) - currentRecordPerPage;
                def maxLimit = minLimit + currentRecordPerPage;  
                if(currentTemplateList.size() != 0) {            
                    for(int i=minLimit; i < maxLimit; i++) {                 
                        def item = currentTemplateList[i]; 
                        if(item) {                                                           
                            HashMap temp = new HashMap();                     
                            temp.put("id", item.id);
                            temp.put("baseOS", item.baseOs);                            
                            temp.put("name", item.name);                                       
                            temp.put("referenceId", item.templateReferenceId);
                            temp.put("description", item.description);
                            temp.put("format", item.format);
                            temp.put("hypervisor", item.hypervisor);
                            temp.put("featured", item.featured);
                            temp.put("isPublic", item.isPublic);
                            temp.put("status", item.status);
                            temp.put("cost", item.cost);
                            temp.put("passwordEnabled", item.passwordEnabled);
                            temp.put("osType", item.osType.name);
                            temp.put("osCategory", item.osCategory.name);                            
                            temp.put("osTypeReferenceId", item.osType.referenceOsTypeId);
                            temp.put("extractable", item.extractable);  
                            temp.put("appTemplate", item.appTemplate);  
                            temp.put("isReady", item.isReady);    

                            ArrayList<ArrayList<String>> tempZoneList = new ArrayList<ArrayList<String>>(); 
                            for(def z : item.zones) {
                                HashMap zoneItem = new HashMap(); 
                                zoneItem.put("zoneId", z.id);
                                zoneItem.put("zoneName", z.aliasName);
                                zoneItem.put("referenceZoneId", z.referenceZoneId); 
                                tempZoneList.add(zoneItem)
                            }
                            temp.put("zones", tempZoneList);                                   
                            templateList.add(temp);                                                                                  
                        }                                  
                    }
                } else {
                    currentPage.put("templates", templateList) 
                }  
            } else {
                currentPage.put("templates", templateList) 
            }                  
            currentPage.put("templates", templateList)                              
            pageArrayList.add(currentPage);
            return pageArrayList; 
        } catch(Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }        
    }
}
