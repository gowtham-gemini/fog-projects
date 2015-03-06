package com.assistanz.fogpanel

import com.assistanz.cloud.cloudstack.CloudStackServer
import com.assistanz.cloud.cloudstack.guestos.CSGuestOSService
import com.assistanz.cloud.cloudstack.guestos.OsTypesResponse
import com.assistanz.cloud.cloudstack.guestos.OsCategoryResponse
import com.assistanz.fogpanel.GeneralConstants;
import org.codehaus.groovy.grails.commons.ApplicationHolder
import java.util.Map.Entry


class OsTypeService {
    
    def grailsApplication
    def list() {
        try {  
            def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
            def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
            def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret
    
            CloudStackServer server =
                    new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);
            
            CSGuestOSService oSService = new CSGuestOSService(server);
             HashMap<String, String> BASE_OS = new HashMap<String, String>();
             BASE_OS.put("CentOS", GeneralConstants.BASE_OS_LINUX);
                BASE_OS.put("Debian", GeneralConstants.BASE_OS_LINUX);
                BASE_OS.put("Oracle", GeneralConstants.BASE_OS_LINUX);
                BASE_OS.put("RedHat", GeneralConstants.BASE_OS_LINUX);
                BASE_OS.put("SUSE", GeneralConstants.BASE_OS_LINUX);
                BASE_OS.put("Windows", GeneralConstants.BASE_OS_WINDOWS);
                BASE_OS.put("Other", GeneralConstants.BASE_OS_LINUX);
                BASE_OS.put("Novel", GeneralConstants.BASE_OS_LINUX);
                BASE_OS.put("Unix", GeneralConstants.BASE_OS_LINUX);
                BASE_OS.put("Ubuntu", GeneralConstants.BASE_OS_LINUX);
                BASE_OS.put("None", GeneralConstants.BASE_OS_LINUX);             
              
            
            def osCategoryResponse = oSService.listOsCategories();
            for(Iterator<OsCategoryResponse> iter = osCategoryResponse.osCategories.iterator(); iter.hasNext();) {
                def categoryData = iter.next();
                OsCategory osCategory = OsCategory.findByOsCategoryId(categoryData.OsCategoryId);
                if (!osCategory) {
                    osCategory = new OsCategory();                    
                    osCategory.osCategoryId = categoryData.OsCategoryId;
                    osCategory.name = categoryData.OsCategoryName; 
                    for (Entry<String, String> entry : BASE_OS.entrySet()) {
                        if(entry.getKey() == categoryData.OsCategoryName) {
                            osCategory.baseOs = entry.getValue()
                           
                        }                  
                     }
                    osCategory.save(flush: true);
                }
            }  
            def response = oSService.listOsTypes();
            for(Iterator<OsTypesResponse> iter = response.osTypes.iterator(); iter.hasNext();) {
                def data = iter.next();
                
                OsType osType = OsType.findByReferenceOsTypeId(data.osTypeId);
                if (!osType) {
                    osType  = new OsType();                    
                    osType.referenceOsTypeId = data.osTypeId;
                    osType.name = data.osTypeDescription; 
                    osType.osCategory = OsCategory.findByOsCategoryId(data.osCategoryId); 
                    osType.save(flush: true);                                       
                }                
            } 
            def osCategories = OsCategory.findAll(); 
            ArrayList<ArrayList<String>> osCategoryList = new ArrayList<ArrayList<String>>(); 
            for(int i=0; i < osCategories.size(); i++) { 
                def currentOsCategory = osCategories[i];  
                HashMap<String,String>  osCategoryItem = new HashMap<String,String>(); 
                osCategoryItem.put("osCategoryName", currentOsCategory.name);   
                osCategoryItem.put("osCategoryId", currentOsCategory.osCategoryId);
                osCategoryItem.put("baseOs", currentOsCategory.baseOs);
                
                osCategoryList.add(osCategoryItem);
            }
            return osCategoryList   
                             
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
    
    
//     def categorylist() {
//        try { 
//            ArrayList<ArrayList<String>> osList = new ArrayList<ArrayList<String>>(); 
//            def osCategories = OsCategory.findAll(); 
//            for(int i=0; i < osCategories.size(); i++) { 
//                def currentOsCategory = osCategories[i]; 
//                HashMap<String,ArrayList<ArrayList<String>>> osCategory = new HashMap<String,HashMap<String,String>>();    
//                    ArrayList<ArrayList<String>> osCategoryList = new ArrayList<ArrayList<String>>(); 
//                        HashMap<String,String>  osCategoryItem = new HashMap<String,String>(); 
//                            osCategoryItem.put("name", currentOsCategory.name);   
//                            osCategoryItem.put("id", currentOsCategory.osCategoryId);
//                            osCategoryList.add(osCategoryItem);
//
//                    ArrayList<ArrayList<String>> osTypeList = new ArrayList<ArrayList<String>>(); 
//                    def osType = OsType.findAllWhere(osCategory: currentOsCategory)
//                    for(int j=0; j < osType.size(); j++) { 
//                         def currentOs= osType[j];    
//                     HashMap<String,String>  os = new HashMap<String,String>(); 
//                        os.put("osName",currentOs.name)
//                        os.put("osId",currentOs.referenceOsTypeId)
//                        os.put("categoryId",currentOs.osCategory.osCategoryId)
//                        osTypeList.add(os); 
//                    }
//                osCategory.put("osList", osTypeList)    
//                osCategory.put("osCategory", osCategoryList)
//                osList.add(osCategory); 
//            }
//            return osList   
//        
//        } catch (Exception ex) {
//            ex.printStackTrace(System.err);
//            throw ex;
//        }
//        
//        
//        
//     }
     
    
//    def categorylist() {
//        try { 
//            ArrayList<ArrayList<String>> osCategoryList = new ArrayList<ArrayList<String>>(); 
//            def osCategories = OsCategory.findAll(); 
//            for(int i=0; i < osCategories.size(); i++) { 
//                def currentOsCategory = osCategories[i];  
//                HashMap<String,String>  osCategoryItem = new HashMap<String,String>(); 
//                osCategoryItem.put("name", currentOsCategory.name);   
//                osCategoryItem.put("id", currentOsCategory.osCategoryId);
//                osCategoryList.add(osCategoryItem);
//            }
//            return osCategoryList   
//        
//        } catch (Exception ex) {
//            ex.printStackTrace(System.err);
//            throw ex;
//        }
//     }
     
    def osListByCategory(String osCategoryId) {
        try { 
            OsCategory osCategory = OsCategory.findByOsCategoryId(osCategoryId)
            
            ArrayList<ArrayList<String>> osTypeList = new ArrayList<ArrayList<String>>(); 
            def osType = OsType.findAllWhere(osCategory: osCategory)
            for(int j=0; j < osType.size(); j++) { 
                 def currentOs= osType[j];    
                HashMap<String,String>  os = new HashMap<String,String>(); 
                os.put("osName",currentOs.name)
                os.put("osId",currentOs.referenceOsTypeId)
                os.put("categoryId",currentOs.osCategory.osCategoryId)
                osTypeList.add(os); 
            }
            return osTypeList  
        
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }   
    }
    
}
