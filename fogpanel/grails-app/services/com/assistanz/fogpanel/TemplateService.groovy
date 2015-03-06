package com.assistanz.fogpanel

import com.assistanz.cloud.cloudstack.CloudStackServer
import com.assistanz.cloud.cloudstack.template.CSTemplateService
import com.assistanz.cloud.cloudstack.template.TemplateResponse
import groovy.json.*
import java.util.Iterator;
import grails.converters.deep.JSON
import org.codehaus.groovy.grails.commons.ApplicationHolder
import org.apache.commons.logging.LogFactory;
import com.assistanz.fogpanel.PullTemplateJob

class TemplateService {
    
    def springSecurityService;
    private static final log = LogFactory.getLog(this)
    def templateServer() {
        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret

        CloudStackServer server =
                new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);

        CSTemplateService cSTemplateService = new CSTemplateService(server);
        
        return cSTemplateService;
        
    }
    def userCount() {    
        try {
            def linuxTotal = 0;
            def windowsTotal = 0;
            def appCount = 0;
            ArrayList countList = new ArrayList(); 
            HashMap countItem = new HashMap();                                      
            def zoneList = Zone.findAll();
            for(int i = 0; i < zoneList.size(); i++) {            
                def currentZone = zoneList[i];                               
                for(Iterator k = currentZone.templates.iterator();k.hasNext();) {
                    def template = k.next();                                                  
                    if(template.deleted == false && template.appTemplate == false &&  template.myTemplate == false && template.baseOs == "Linux" && template.isReady == true) {                                                                
                        linuxTotal = linuxTotal + 1;
                    } else if(template.deleted == false && template.appTemplate == false &&  template.myTemplate == false && template.baseOs == "Windows" && template.isReady == true) {                    
                        windowsTotal = windowsTotal + 1;
                    } else if(template.deleted == false && template.appTemplate == true &&  template.myTemplate == false && template.isReady == true) {
                        appCount = appCount + 1;
                    }                    
                }
            }       
            countItem.put("linuxTotal", linuxTotal);
            countItem.put("windowsTotal", windowsTotal);     
            countItem.put("appCount", appCount);                             
            countList.add(countItem);
            return countList;
        } catch (Exception ex) { 
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
    
    
    def pullTemplateFromCloudStackJobStart() {
        
        PullTemplateJob.triggerNow([id:"template"])
            
        return "OK"
        
    }    
    
    def pullDiskOfferFromCloudStack(jobid) {
        
        def job = AsynchronousJobs.get(jobid)
        try { 
                        
            job.status = JobStatus.valueOf("RUNNING")
            job.startedAt = new Date()
            job.save(flush: true)

            def response = templateServer().listTemplates("featured", null)        

            for(Iterator<TemplateResponse> iter = response.templates.iterator(); iter.hasNext();) {
                def data = iter.next();
                Template template = Template.findByTemplateReferenceId(data.id);
                def aviliableZone = Zone.findByReferenceZoneId(data.zoneId)
                if(!template && aviliableZone) {

                    template  = new Template();            
                    template.name = data.name;
                    template.description = data.name;
                    template.url = "From Cloud Stack";
                    template.format = data.format;
                    template.hypervisor = data.hypervisor
                    template.extractable = new Boolean(data.isExtractable);                                        
                    template.featured = new Boolean(data.isFeatured);
                    template.isPublic = new Boolean(data.isPublic);
                    template.detailedDescription = data.displayText;
                    template.osReferenceURL = "";
                    template.myTemplate = false;    
                    template.appTemplate = false; 
                    template.isReady = new Boolean(data.isReady);
                    template.path = "";     
                    template.architecture = "64bit";                 
                    template.oneTimeChargeable = false; 
                    template.status = data.status; 
                    template.cost = 0;  
                    if(data.size == null || data.size == "null") {     
                        template.size = 0
                    } else {
                        def tempsize = Double.parseDouble(data.size);
                        def volumeSize = tempsize / (double) 1073741824;
                        template.size = Math.round(volumeSize * 100d) / 100d;
                    }
                    template.passwordEnabled = new Boolean(data.passwordEnabled);
                    def osType = OsType.findByReferenceOsTypeId(data.osTypeId);
                    template.osType = osType;
                    template.osCategory = osType.osCategory;  
                    template.baseOs = osType.osCategory.baseOs; 
                    template.addToZones(Zone.findByReferenceZoneId(data.zoneId));
                    template.templateReferenceId = data.id; 
                    template.deleted = false;  
                    //save new template
                    template.save(flush: true); 
                } else if(template) {
                    
                    template.isReady = new Boolean(data.isReady);
                    template.status = data.status; 
                    template.name = data.name;
                    
                    HashMap<String,String> temZoneList = new HashMap<String,String>(); 
                    template.zones.each {
                        temZoneList.put(it.referenceZoneId ,"referenceZoneId");
                    }
                    boolean blnExists = temZoneList.containsKey(data.zoneId);
                    if(blnExists.toString() == "false" || blnExists == false) {
                        def zone = Zone.findWhere(referenceZoneId:data.zoneId) 
                        if(zone) {
                            template.addToZones(zone);      
                        }  
                    }
                    template.save(flush: true); 
                }
            }   
            
            job.status = JobStatus.valueOf("COMPLETED")
            job.completedAt = new Date()
            job.save(flush: true)
            
        } catch (Exception ex) {
            
            job.status = JobStatus.valueOf("ERROR")
            job.completedAt = new Date()
            job.save(flush: true)
            
            ex.printStackTrace(System.err);
            throw ex;
        }
        
    }  
     
    def count(zoneReferenceId, appTemplate, myTemplate, baseOs) {
        try {                 
            def count = 0;       
            def linuxTotal = 0;
            def windowsTotal = 0;
            def size = 0;
            def appCount = 0;
            ArrayList countList = new ArrayList(); 
            HashMap countItem = new HashMap();  
            def currency = ApplicationHolder.getApplication().config.billing.defaultCurrency
            def response = templateServer().listTemplates("all", null)           
            for(Iterator<TemplateResponse> iter = response.templates.iterator(); iter.hasNext();) {
                def data = iter.next();
                Template template = Template.findByTemplateReferenceId(data.id);
                if(template) {
                    HashMap<String,String> temZoneList = new HashMap<String,String>(); 
                    def tempsize; 
                    if(data.size == null || data.size == "null") {                        
                        template.size = 0
                        if(data.status == "" || data.status == "null" || data.status == null) {
                          template.status = "-";      
                        } else {
                            template.status = data.status;    
                        }
                        template.zones.each {
                            temZoneList.put(it.referenceZoneId ,"referenceZoneId");
                        }
                        boolean blnExists = temZoneList.containsKey(data.zoneId);
                        if(blnExists.toString() == "false" || blnExists == false) {
                            def zone = Zone.findWhere(referenceZoneId:data.zoneId) 
                            if(zone) {
                                template.addToZones(zone);      
                            }  
                        }
                        template.isReady = Boolean.parseBoolean(data.isReady); 
                        template.save(flush: true);
                    } else {
                        tempsize = Double.parseDouble(data.size);
                        def volumeSize = tempsize / (double) 1073741824;
                        template.size = Math.round(volumeSize * 100d) / 100d;
                        if(data.status == "" || data.status == "null" || data.status == null) {
                          template.status = "-";      
                        } else {
                            template.status = data.status;    
                        }
                        template.zones.each {
                            temZoneList.put(it.referenceZoneId ,"referenceZoneId");
                        }
                        boolean blnExists = temZoneList.containsKey(data.zoneId);
                        if(blnExists.toString() == "false" || blnExists == false) {
                            def zone = Zone.findWhere(referenceZoneId:data.zoneId) 
                            if(zone) {
                                template.addToZones(zone);      
                            }  
                        }
                        template.isReady = Boolean.parseBoolean(data.isReady); 
                        template.save(flush: true);   
                        
                        if (!template.save()) {
                            template.errors.allErrors.each { println it }
                        }                        
                    }                      
                }
            } 
            if((zoneReferenceId == null || zoneReferenceId == "null")&&(appTemplate == "null" || appTemplate == null) && (myTemplate == "null" || myTemplate == null) && (baseOs == "null" || baseOs == null)) {      
                def zoneList = Zone.findAll();
                ArrayList<ArrayList<String>> templateList = new ArrayList<ArrayList<String>>();
                 
                for(int i = 0; i < zoneList.size(); i++) {
                def currentZone = zoneList[i];            
                    
                for(Iterator k = currentZone.templates.iterator();k.hasNext();) {
                    def template = k.next(); 
                    
                    if(template.deleted == false && template.appTemplate == false &&  template.myTemplate == false && template.baseOs == "Linux") {
                        linuxTotal = linuxTotal + 1;
                    } else if(template.deleted == false && template.appTemplate == false &&  template.myTemplate == false && template.baseOs == "Windows"){
                        windowsTotal = windowsTotal + 1;
                    } else if(template.deleted == false && template.appTemplate == true &&  template.myTemplate == false) {
                        appCount = appCount + 1;
                    }
                    
                    if(template.deleted == false && template.appTemplate == false &&  template.myTemplate == false) {
                        HashMap<String,String> temp = new HashMap<String,String>();   
                        temp.put("zoneName", currentZone.aliasName);
                        temp.put("id", template.id);
                        temp.put("baseOs", template.baseOs);                            
                        temp.put("name", template.name);
                        temp.put("referenceZoneId", currentZone.referenceZoneId);
                        temp.put("referenceId", template.templateReferenceId);
                        temp.put("description", template.description);
                        temp.put("format", template.format);
                        temp.put("hypervisor", template.hypervisor);
                        temp.put("status", template.status);
                        temp.put("featured", template.featured);
                        temp.put("isPublic", template.isPublic);
                        temp.put("currency",  currency);
                        temp.put("cost", template.cost);
                        temp.put("passwordEnabled", template.passwordEnabled);
                        temp.put("osType", template.osType.name);
                        temp.put("osTypeReferenceId", template.osType.referenceOsTypeId);
                        temp.put("osCategory", template.osCategory.name);
                        temp.put("extractable", template.extractable);  
                        temp.put("size",template.size);  
                        temp.put("isReady",template.isReady);  
                        temp.put("path",template.path);  
                        temp.put("oneTimeCharge",template.oneTimeChargeable);                                                  
                        templateList.add(temp);  
                        count = templateList.size();
                    }                        
                }               
            }   
            countItem.put("tempData", templateList); 
            } else if((zoneReferenceId != null || zoneReferenceId != "null")&&(appTemplate == "null" || appTemplate == null) && (myTemplate == "null" || myTemplate == null) && (baseOs == "null" || baseOs == null)) {
                def role = springSecurityService.getPrincipal().getAuthorities()
                def zoneList = Zone.findByReferenceZoneId(zoneReferenceId); 
                def template = "";
                ArrayList<ArrayList<String>> templateList = new ArrayList<ArrayList<String>>();
                if(role.iterator().next() == "ROLE_ADMIN" ) {                      
                    for(Iterator k = zoneList.templates.iterator();k.hasNext();) {                     
                        template = k.next();   
                        if(template.baseOs == "Linux" && template.appTemplate == false && template.myTemplate == false && template.deleted == false) {
                            linuxTotal = linuxTotal + 1;
                        } else if(template.baseOs == "Windows" && template.appTemplate == false && template.myTemplate == false && template.deleted == false) {
                            windowsTotal = windowsTotal + 1;
                        }                                                         
                        if(template.appTemplate == false &&  template.myTemplate == false && template.deleted == false) {
                            HashMap<String,String> tempItem = new HashMap<String,String>();
                            tempItem.put("zoneName", zoneList.aliasName);
                            tempItem.put("id", template.id);
                            tempItem.put("baseOs", template.baseOs);                            
                            tempItem.put("name", template.name);
                            tempItem.put("referenceZoneId", zoneList.referenceZoneId);
                            tempItem.put("referenceId", template.templateReferenceId);
                            tempItem.put("description", template.description);
                            tempItem.put("format", template.format);
                            tempItem.put("hypervisor", template.hypervisor);
                            tempItem.put("status", template.status);
                            tempItem.put("featured", template.featured);
                            tempItem.put("isPublic", template.isPublic);
                            tempItem.put("cost", template.cost);
                            tempItem.put("currency",  currency);
                            tempItem.put("osCategory", template.osCategory.name);
                            tempItem.put("passwordEnabled", template.passwordEnabled);
                            tempItem.put("osType", template.osType.name);
                            tempItem.put("osTypeReferenceId", template.osType.referenceOsTypeId);
                            tempItem.put("extractable", template.extractable);   
                            tempItem.put("size", template.size);   
                            tempItem.put("isReady",template.isReady); 
                            tempItem.put("path",template.path);  
                            tempItem.put("oneTimeCharge",template.oneTimeChargeable);                          
                            templateList.add(tempItem);  
                            count = count + 1;                        
                        }                   
                        countItem.put("tempData", templateList);              
                    }
                } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {                     
                    for(Iterator k = zoneList.templates.iterator();k.hasNext();) {                      
                        template = k.next();   
                        if(template.baseOs == "Linux" && template.appTemplate == false && template.myTemplate == false && template.deleted == false && template.isReady == true) {
                            linuxTotal = linuxTotal + 1;                            
                        } else if(template.baseOs == "Windows" && template.appTemplate == false && template.myTemplate == false && template.deleted == false && template.isReady == true) {
                            windowsTotal = windowsTotal + 1;
                        }                                    
                        if(template.appTemplate == false &&  template.myTemplate == false && template.deleted == false && template.isReady == true) {
                            HashMap<String,String> tempItem = new HashMap<String,String>();
                            tempItem.put("zoneName", zoneList.aliasName);
                            tempItem.put("id", template.id);
                            tempItem.put("baseOs", template.baseOs);                            
                            tempItem.put("name", template.name);
                            tempItem.put("referenceZoneId", zoneList.referenceZoneId);
                            tempItem.put("referenceId", template.templateReferenceId);
                            tempItem.put("description", template.description);
                            tempItem.put("format", template.format);
                            tempItem.put("hypervisor", template.hypervisor);
                            tempItem.put("status", template.status);
                            tempItem.put("featured", template.featured);
                            tempItem.put("isPublic", template.isPublic);
                            tempItem.put("cost", template.cost);
                            tempItem.put("currency",  currency);
                            tempItem.put("osCategory", template.osCategory.name);
                            tempItem.put("passwordEnabled", template.passwordEnabled);
                            tempItem.put("osType", template.osType.name);
                            tempItem.put("osTypeReferenceId", template.osType.referenceOsTypeId);
                            tempItem.put("extractable", template.extractable);   
                            tempItem.put("size", template.size);   
                            tempItem.put("isReady",template.isReady); 
                            tempItem.put("path",template.path);  
                            tempItem.put("oneTimeCharge",template.oneTimeChargeable);      
                            templateList.add(tempItem);  
                            count = count + 1;                        
                        }                   
                        countItem.put("tempData", templateList);                          
                    }
                }                                                                                                          
            } else if((zoneReferenceId == null || zoneReferenceId == "null")&&(appTemplate == "null" || appTemplate == null) && (myTemplate == "null" || myTemplate == null) && (baseOs != "null" || baseOs != null)) {
                count = Template.findAllWhere(deleted: false, appTemplate: false, myTemplate: false).size();
                linuxTotal = Template.findAllWhere(deleted: false, baseOs: "Linux", appTemplate: false, myTemplate: false).size();
                windowsTotal = Template.findAllWhere(deleted: false, baseOs: "Windows", appTemplate: false, myTemplate: false).size();  
                def zoneList = Zone.findAll();
               
                 ArrayList<ArrayList<String>> templateList = new ArrayList<ArrayList<String>>();
                 
                for(int i = 0; i < zoneList.size(); i++) {
                def currentZone = zoneList[i];            
                   
                for(Iterator k = currentZone.templates.iterator();k.hasNext();) {
                     
                     def template = k.next(); 
                      
                      
                     if(template.baseOs == baseOs && template.appTemplate == false &&  template.myTemplate == false && template.deleted == false) {
                        HashMap<String,String> temp = new HashMap<String,String>();   
                       temp.put("zoneName", currentZone.aliasName);
                        temp.put("id", template.id);
                        temp.put("baseOs", template.baseOs);                            
                        temp.put("name", template.name);
                        temp.put("referenceZoneId", currentZone.referenceZoneId);
                        temp.put("referenceId", template.templateReferenceId);
                        temp.put("description", template.description);
                        temp.put("format", template.format);
                        temp.put("hypervisor", template.hypervisor);
                        temp.put("status", template.status);                        
                        temp.put("featured", template.featured);
                        temp.put("isPublic", template.isPublic);
                        temp.put("cost", template.cost);
                        temp.put("currency",  currency);
                        temp.put("currency",  currency);
                        temp.put("osCategory", template.osCategory.name);
                        temp.put("passwordEnabled", template.passwordEnabled);
                        temp.put("osType", template.osType.name);
                        temp.put("osTypeReferenceId", template.osType.referenceOsTypeId);
                        temp.put("extractable", template.extractable);  
                        temp.put("isReady",template.isReady); 
                        temp.put("path",template.path);  
                        temp.put("oneTimeCharge",template.oneTimeChargeable);      
                        templateList.add(temp);  
                     }                    
                }               
            }   
            countItem.put("tempData", templateList); 
            
            }  
            else if((zoneReferenceId != null || zoneReferenceId != "null")&&(appTemplate == "null" || appTemplate == null) && (myTemplate == "null" || myTemplate == null) && (baseOs != "null" || baseOs != null)) {
                def zoneList = Zone.findAllWhere(referenceZoneId: zoneReferenceId);  
              
                 ArrayList<ArrayList<String>> templateList = new ArrayList<ArrayList<String>>();
                   
                for(int i = 0; i < zoneList.size(); i++) {
                def currentZone = zoneList[i];            
                   
                for(Iterator k = currentZone.templates.iterator();k.hasNext();) {
                    
                     def template = k.next(); 
                     if(template.baseOs == "Linux" && template.appTemplate == false && template.myTemplate == false) {
                         linuxTotal = linuxTotal + 1;
                        } else if(template.baseOs == "Windows" && template.appTemplate == false && template.myTemplate == false) {
                            windowsTotal = windowsTotal + 1;
                        }
                        if(template.appTemplate == false && template.myTemplate == false) {
                            count = count + 1;
                        }
                     
                     
                     HashMap<String,String> temp = new HashMap<String,String>(); 
                     if(template.baseOs == baseOs && template.appTemplate == false &&  template.myTemplate == false && template.deleted == false) {                        
                         
                        
                        temp.put("zoneName", currentZone.aliasName);
                        temp.put("id", template.id);
                        temp.put("baseOs", template.baseOs);                            
                        temp.put("name", template.name);
                        temp.put("referenceZoneId", currentZone.referenceZoneId);
                        temp.put("referenceId", template.templateReferenceId);
                        temp.put("description", template.description);
                        temp.put("format", template.format);
                        temp.put("status", template.status);
                        temp.put("osCategory", template.osCategory.name);
                        temp.put("hypervisor", template.hypervisor);
                        temp.put("featured", template.featured);
                        temp.put("isPublic", template.isPublic);
                        temp.put("cost", template.cost);
                        temp.put("currency",  currency);
                        temp.put("passwordEnabled", template.passwordEnabled);
                        temp.put("osType", template.osType.name);
                        temp.put("osTypeReferenceId", template.osType.referenceOsTypeId);
                        temp.put("extractable", template.extractable); 
                        temp.put("isReady",template.isReady); 
                        temp.put("path",template.path);
                        temp.put("oneTimeCharge",template.oneTimeChargeable);    
                        templateList.add(temp);  
                        countItem.put("tempData", templateList);   
                     }
                    
                }               
            }   
                       
            }    
            //end of Os temp
            //app tem begin
            else if((zoneReferenceId == null || zoneReferenceId == "null") && (appTemplate != "null" || appTemplate != null) && (myTemplate == "null" || myTemplate == null) && (baseOs == "null" || baseOs == null)) {
                count = 0;
                linuxTotal = 0;
                windowsTotal = 0; 
                
                def zoneList = Zone.findAll();
               
                 ArrayList<ArrayList<String>> templateList = new ArrayList<ArrayList<String>>();
                 
                for(int i = 0; i < zoneList.size(); i++) {
                def currentZone = zoneList[i];            
                   
                for(Iterator k = currentZone.templates.iterator();k.hasNext();) {
                     
                     def template = k.next(); 
                     
                    if(template.deleted == false && template.appTemplate == true &&  template.myTemplate == false && template.baseOs == "Linux") {
                        linuxTotal = linuxTotal + 1;
                    } else if(template.deleted == false && template.appTemplate == true &&  template.myTemplate == false && template.baseOs == "Windows") {
                        windowsTotal = windowsTotal + 1;
                    }
                     if(template.deleted == false && template.appTemplate == true &&  template.myTemplate == false) {
                         HashMap<String,String> temp = new HashMap<String,String>();   
                        temp.put("zoneName", currentZone.aliasName);
                        temp.put("id", template.id);
                        temp.put("baseOs", template.baseOs);                            
                        temp.put("name", template.name);
                        temp.put("referenceZoneId", currentZone.referenceZoneId);
                        temp.put("referenceId", template.templateReferenceId);
                        temp.put("description", template.description);
                        temp.put("format", template.format);
                        temp.put("hypervisor", template.hypervisor);
                        temp.put("status", template.status);
                        temp.put("featured", template.featured);
                        temp.put("isPublic", template.isPublic);
                        temp.put("cost", template.cost);
                        temp.put("currency",  currency);
                        temp.put("passwordEnabled", template.passwordEnabled);
                        temp.put("osType", template.osType.name);
                        temp.put("osTypeReferenceId", template.osType.referenceOsTypeId);
                        temp.put("osCategory", template.osCategory.name);
                        temp.put("extractable", template.extractable);  
                        temp.put("isReady",template.isReady); 
                        temp.put("path",template.path);
                        temp.put("oneTimeCharge",template.oneTimeChargeable);    
                        templateList.add(temp);    
                        count = templateList.size();
                    }                        
                }               
            }   
            countItem.put("tempData", templateList); 
            } else if((zoneReferenceId != null || zoneReferenceId != "null")&&(appTemplate != "null" || appTemplate != null) && (myTemplate == "null" || myTemplate == null) && (baseOs == "null" || baseOs == null)) {
                def zoneList = Zone.findByReferenceZoneId(zoneReferenceId);                
                 ArrayList<ArrayList<String>> templateList = new ArrayList<ArrayList<String>>();
                                
                   
                for(Iterator k = zoneList.templates.iterator();k.hasNext();) { 
                    
                        def template = k.next();   
                        if(template.appTemplate == true &&  template.myTemplate == false && template.deleted == false) {
                             HashMap<String,String> tempItem = new HashMap<String,String>();
                            tempItem.put("zoneName", zoneList.aliasName);
                            tempItem.put("id", template.id);
                            tempItem.put("baseOs", template.baseOs);                            
                            tempItem.put("name", template.name);
                            tempItem.put("referenceZoneId", zoneList.referenceZoneId);
                            tempItem.put("referenceId", template.templateReferenceId);
                            tempItem.put("description", template.description);
                            tempItem.put("format", template.format);
                            tempItem.put("hypervisor", template.hypervisor);
                            tempItem.put("status", template.status);
                            tempItem.put("featured", template.featured);
                            tempItem.put("isPublic", template.isPublic);
                            tempItem.put("cost", template.cost);
                            tempItem.put("currency",  currency);
                            tempItem.put("osCategory", template.osCategory.name);
                            tempItem.put("passwordEnabled", template.passwordEnabled);
                            tempItem.put("osType", template.osType.name);
                            tempItem.put("osTypeReferenceId", template.osType.referenceOsTypeId);
                            tempItem.put("extractable", template.extractable);   
                            tempItem.put("isReady",template.isReady); 
                            tempItem.put("path",template.path);
                            tempItem.put("oneTimeCharge",template.oneTimeChargeable);    
                            templateList.add(tempItem);  
                        
                         if(template.baseOs == "Linux") {
                         linuxTotal = linuxTotal + 1;
                     } else if(template.baseOs == "Windows") {
                         windowsTotal = windowsTotal + 1;
                     }   
                    }   
                count = templateList.size();    
                countItem.put("tempData", templateList);             
            }  
            } else if((zoneReferenceId == null || zoneReferenceId == "null")&&(appTemplate != "null" || appTemplate != null) && (myTemplate == "null" || myTemplate == null) && (baseOs != "null" || baseOs != null)) {
                
                
                count = Template.findAllWhere(deleted: false, appTemplate: true, myTemplate: false).size();
                linuxTotal = Template.findAllWhere(deleted: false, baseOs: "Linux", appTemplate: true, myTemplate: false).size();
                windowsTotal = Template.findAllWhere(deleted: false, baseOs: "Windows", appTemplate: true, myTemplate: false).size();   
                
                def zoneList = Zone.findAll();
               
                 ArrayList<ArrayList<String>> templateList = new ArrayList<ArrayList<String>>();
                 
                for(int i = 0; i < zoneList.size(); i++) {
                def currentZone = zoneList[i];            
                   
                for(Iterator k = currentZone.templates.iterator();k.hasNext();) {
                     
                     def template = k.next(); 
                                       
                     if(template.baseOs == baseOs && template.appTemplate == true &&  template.myTemplate == false && template.deleted == false) {
                     
                        
                        HashMap<String,String> temp = new HashMap<String,String>();   
//                        count = count + 1;
                       temp.put("zoneName", currentZone.aliasName);
                        temp.put("id", template.id);
                        temp.put("baseOs", template.baseOs);                            
                        temp.put("name", template.name);
                        temp.put("referenceZoneId", currentZone.referenceZoneId);
                        temp.put("referenceId", template.templateReferenceId);
                        temp.put("description", template.description);
                        temp.put("format", template.format);
                        temp.put("hypervisor", template.hypervisor);
                        temp.put("status", template.status);                        
                        temp.put("featured", template.featured);
                        temp.put("isPublic", template.isPublic);
                        temp.put("cost", template.cost);
                        temp.put("currency",  currency);
                        temp.put("osCategory", template.osCategory.name);
                        temp.put("passwordEnabled", template.passwordEnabled);
                        temp.put("osType", template.osType.name);
                        temp.put("osTypeReferenceId", template.osType.referenceOsTypeId);
                        temp.put("extractable", template.extractable);  
                        temp.put("isReady",template.isReady); 
                        temp.put("path",template.path);
                        temp.put("oneTimeCharge",template.oneTimeChargeable);    
                        templateList.add(temp);  
                     }
                    
                }               
            }   
            countItem.put("tempData", templateList);             
            }  
            else if((zoneReferenceId != null || zoneReferenceId != "null") && (appTemplate != "null" || appTemplate != null) && (myTemplate == "null" || myTemplate == null) && (baseOs != "null" || baseOs != null)) {
               
                def zoneList = Zone.findAllWhere(referenceZoneId: zoneReferenceId);  
              
                 ArrayList<ArrayList<String>> templateList = new ArrayList<ArrayList<String>>();
                   
                for(int i = 0; i < zoneList.size(); i++) {
                def currentZone = zoneList[i];            
                   
                for(Iterator k = currentZone.templates.iterator();k.hasNext();) {
                    
                     def template = k.next();                      
                     if(template.baseOs == "Linux" && template.appTemplate == true) {
                         linuxTotal = linuxTotal + 1;
                     } else if(template.baseOs == "Windows" && template.appTemplate == true) {
                         windowsTotal = windowsTotal + 1;
                     }  
                     if( template.appTemplate == true &&  template.myTemplate == false) {
                         count = count + 1;
                     }
                     HashMap<String,String> temp = new HashMap<String,String>(); 
                     if(template.baseOs == baseOs && template.appTemplate == true &&  template.myTemplate == false && template.deleted == false) {                        
                        temp.put("zoneName", currentZone.aliasName);
                        temp.put("id", template.id);
                        temp.put("baseOs", template.baseOs);                            
                        temp.put("name", template.name);
                        temp.put("referenceZoneId", currentZone.referenceZoneId);
                        temp.put("referenceId", template.templateReferenceId);
                        temp.put("description", template.description);
                        temp.put("format", template.format);
                        temp.put("status", template.status);
                        temp.put("osCategory", template.osCategory.name);
                        temp.put("hypervisor", template.hypervisor);
                        temp.put("featured", template.featured);
                        temp.put("isPublic", template.isPublic);
                        temp.put("cost", template.cost);
                        temp.put("currency",  currency);
                        temp.put("passwordEnabled", template.passwordEnabled);
                        temp.put("osType", template.osType.name);
                        temp.put("osTypeReferenceId", template.osType.referenceOsTypeId);
                        temp.put("extractable", template.extractable);  
                        temp.put("isReady",template.isReady); 
                        temp.put("path",template.path);
                        temp.put("oneTimeCharge",template.oneTimeChargeable);  
                        templateList.add(temp);  
                        countItem.put("tempData", templateList); 
                        
                     }
                    
                }               
            }                          
            } 
            // end of app temp
            // my temp begin
            else if((zoneReferenceId == null || zoneReferenceId == "null") && (appTemplate == "null" || appTemplate == null) && (myTemplate != "null" || myTemplate != null) && (baseOs == "null" || baseOs == null)) {
                
                count = 0;
                linuxTotal = 0;
                windowsTotal = 0;
                
                def zoneList = Zone.findAll();
               
                 ArrayList<ArrayList<String>> templateList = new ArrayList<ArrayList<String>>();
                 
                for(int i = 0; i < zoneList.size(); i++) {
                def currentZone = zoneList[i];            
                   
                for(Iterator k = currentZone.templates.iterator();k.hasNext();) {
                     
                     def template = k.next(); 
                     
                        if(template.deleted == false && template.appTemplate == false &&  template.myTemplate == true && template.baseOs == "Linux") {
                            linuxTotal = linuxTotal + 1;
                        } else if(template.deleted == false && template.appTemplate == false &&  template.myTemplate == true && template.baseOs == "Windows") {
                            windowsTotal = windowsTotal + 1;
                        }
                     if(template.deleted == false && template.appTemplate == false &&  template.myTemplate == true) {
                         HashMap<String,String> temp = new HashMap<String,String>();   
                        temp.put("zoneName", currentZone.aliasName);
                        temp.put("id", template.id);
                        temp.put("baseOs", template.baseOs);                            
                        temp.put("name", template.name);
                        temp.put("referenceZoneId", currentZone.referenceZoneId);
                        temp.put("referenceId", template.templateReferenceId);
                        temp.put("description", template.description);
                        temp.put("format", template.format);
                        temp.put("hypervisor", template.hypervisor);
                        temp.put("status", template.status);
                        temp.put("featured", template.featured);
                        temp.put("isPublic", template.isPublic);
                        temp.put("cost", template.cost);
                        temp.put("currency",  currency);
                        temp.put("passwordEnabled", template.passwordEnabled);
                        temp.put("osType", template.osType.name);
                        temp.put("osTypeReferenceId", template.osType.referenceOsTypeId);
                        temp.put("osCategory", template.osCategory.name);
                        temp.put("extractable", template.extractable);  
                        temp.put("isReady",template.isReady); 
                        temp.put("path",template.path);
                        temp.put("oneTimeCharge",template.oneTimeChargeable);  
                        templateList.add(temp);  
                        count = templateList.size();
                    }                        
                }               
            }   
            countItem.put("tempData", templateList); 
            } else if((zoneReferenceId != null || zoneReferenceId != "null")&&(appTemplate == "null" || appTemplate == null) && (myTemplate != "null" || myTemplate != null) && (baseOs == "null" || baseOs == null)) {
                
                def zoneList = Zone.findByReferenceZoneId(zoneReferenceId);                
                 ArrayList<ArrayList<String>> templateList = new ArrayList<ArrayList<String>>();
                                
                   
                for(Iterator k = zoneList.templates.iterator();k.hasNext();) { 
                    
                        def template = k.next();  
                        if(template.appTemplate == false &&  template.myTemplate == true && template.deleted == false) {
                            count = count + 1;
                        }
                        if(template.appTemplate == false &&  template.myTemplate == true && template.deleted == false) {
                             HashMap<String,String> tempItem = new HashMap<String,String>();
                            tempItem.put("zoneName", zoneList.aliasName);
                            tempItem.put("id", template.id);
                            tempItem.put("baseOs", template.baseOs);                            
                            tempItem.put("name", template.name);
                            tempItem.put("referenceZoneId", zoneList.referenceZoneId);
                            tempItem.put("referenceId", template.templateReferenceId);
                            tempItem.put("description", template.description);
                            tempItem.put("format", template.format);
                            tempItem.put("hypervisor", template.hypervisor);
                            tempItem.put("status", template.status);
                            tempItem.put("featured", template.featured);
                            tempItem.put("isPublic", template.isPublic);
                            tempItem.put("cost", template.cost);
                            tempItem.put("currency",  currency);
                            tempItem.put("osCategory", template.osCategory.name);
                            tempItem.put("passwordEnabled", template.passwordEnabled);
                            tempItem.put("osType", template.osType.name);
                            tempItem.put("osTypeReferenceId", template.osType.referenceOsTypeId);
                            tempItem.put("extractable", template.extractable);   
                            tempItem.put("isReady",template.isReady); 
                            tempItem.put("path",template.path);
                            tempItem.put("oneTimeCharge",template.oneTimeChargeable);  
                            templateList.add(tempItem);  
                        
                         if(template.baseOs == "Linux" && template.myTemplate == true) {
                         linuxTotal = linuxTotal + 1;
                     } else if(template.baseOs == "Windows" && template.myTemplate == true) {
                         windowsTotal = windowsTotal + 1;
                     }   
                    }   
                
                countItem.put("tempData", templateList);        
                count = templateList.size();
            }                       
            } else if((zoneReferenceId == null || zoneReferenceId == "null")&&(appTemplate == "null" || appTemplate == null) && (myTemplate != "null" || myTemplate != null) && (baseOs != "null" || baseOs != null)) {
                
                
                count = Template.findAllWhere(deleted: false, appTemplate: false, myTemplate: true).size();
                linuxTotal = Template.findAllWhere(deleted: false, baseOs: "Linux", appTemplate: false, myTemplate: true).size();
                windowsTotal = Template.findAllWhere(deleted: false, baseOs: "Windows", appTemplate: false, myTemplate: true).size();   
                
                def zoneList = Zone.findAll();
               
                 ArrayList<ArrayList<String>> templateList = new ArrayList<ArrayList<String>>();
                 
                for(int i = 0; i < zoneList.size(); i++) {
                def currentZone = zoneList[i];            
                   
                for(Iterator k = currentZone.templates.iterator();k.hasNext();) {
                     
                     def template = k.next();                      
                   
                     if(template.baseOs == baseOs && template.appTemplate == false &&  template.myTemplate == true && template.deleted == false) {                    
                        
                        HashMap<String,String> temp = new HashMap<String,String>();   
                        
                       temp.put("zoneName", currentZone.aliasName);
                        temp.put("id", template.id);
                        temp.put("baseOs", template.baseOs);                            
                        temp.put("name", template.name);
                        temp.put("referenceZoneId", currentZone.referenceZoneId);
                        temp.put("referenceId", template.templateReferenceId);
                        temp.put("description", template.description);
                        temp.put("format", template.format);
                        temp.put("hypervisor", template.hypervisor);
                        temp.put("status", template.status);                        
                        temp.put("featured", template.featured);
                        temp.put("isPublic", template.isPublic);
                        temp.put("cost", template.cost);
                        temp.put("currency",  currency);
                        temp.put("osCategory", template.osCategory.name);
                        temp.put("passwordEnabled", template.passwordEnabled);
                        temp.put("osType", template.osType.name);
                        temp.put("osTypeReferenceId", template.osType.referenceOsTypeId);
                        temp.put("extractable", template.extractable);  
                        temp.put("isReady",template.isReady);
                        temp.put("oneTimeCharge",template.oneTimeChargeable);  
                        temp.put("path",template.path);
                        templateList.add(temp)
                        
                     }
                    
                }               
            }   
            countItem.put("tempData", templateList); 
            
            } else if((zoneReferenceId != null || zoneReferenceId != "null")&&(appTemplate == "null" || appTemplate == null) && (myTemplate != "null" || myTemplate != null) && (baseOs != "null" || baseOs != null)) {
                
                def zoneList = Zone.findAllWhere(referenceZoneId: zoneReferenceId);  
              
                 ArrayList<ArrayList<String>> templateList = new ArrayList<ArrayList<String>>();
                   
                for(int i = 0; i < zoneList.size(); i++) {
                def currentZone = zoneList[i];            
                   
                for(Iterator k = currentZone.templates.iterator();k.hasNext();) {
                    
                     def template = k.next(); 
                     
                     if(template.baseOs == "Linux" && template.myTemplate == true) {
                         linuxTotal = linuxTotal + 1;
                     } else if(template.baseOs == "Windows" && template.myTemplate == true) {
                         windowsTotal = windowsTotal + 1;
                     } 
                     
                     if(template.myTemplate == true) {
                         count = count+1;
                     }
                     HashMap<String,String> temp = new HashMap<String,String>(); 
                     if(template.baseOs == baseOs && template.appTemplate == false &&  template.myTemplate == true && template.deleted == false) {                        
                        temp.put("zoneName", currentZone.aliasName);
                        temp.put("id", template.id);
                        temp.put("baseOs", template.baseOs);                            
                        temp.put("name", template.name);
                        temp.put("referenceZoneId", currentZone.referenceZoneId);
                        temp.put("referenceId", template.templateReferenceId);
                        temp.put("description", template.description);
                        temp.put("format", template.format);
                        temp.put("status", template.status);
                        temp.put("osCategory", template.osCategory.name);
                        temp.put("hypervisor", template.hypervisor);
                        temp.put("featured", template.featured);
                        temp.put("isPublic", template.isPublic);
                        temp.put("cost", template.cost);
                        temp.put("currency",  currency);
                        temp.put("passwordEnabled", template.passwordEnabled);
                        temp.put("osType", template.osType.name);
                        temp.put("osTypeReferenceId", template.osType.referenceOsTypeId);
                        temp.put("extractable", template.extractable);  
                        temp.put("isReady",template.isReady); 
                        temp.put("path",template.path);
                        temp.put("oneTimeCharge",template.oneTimeChargeable);
                        templateList.add(temp);  
                        countItem.put("tempData", templateList);                         
                    }                    
                }               
            }               
        } 
            //end of my temp   
            
            countItem.put("totalTemplates", count);
            countItem.put("linuxTotal", linuxTotal);
            countItem.put("windowsTotal", windowsTotal);     
            countItem.put("appCount", appCount);     
            
            
            countList.add(countItem);
            return countList;
        }  catch (Exception ex) { 
            ex.printStackTrace(System.err);
            throw ex;
        }        
    }
    def listTemplateByZone(tempId, zoneId) {
        try {
            def currency = ApplicationHolder.getApplication().config.billing.defaultCurrency
            ArrayList zoneListArray = new ArrayList();
            ArrayList tempList = new ArrayList();
            def currentZone = Zone.get(zoneId);            
            HashMap zoneItem = new HashMap();
            zoneItem.put("referenceId", currentZone.referenceZoneId);  
            zoneItem.put("name", currentZone.aliasName);  
            zoneItem.put("networkType", currentZone.networkType);              
            zoneListArray.add(zoneItem)            
            for(Iterator k = currentZone.templates.iterator();k.hasNext();) {
                def tempData = k.next();                 
                if(tempData.id.toString() == tempId.toString()) {                    
                    HashMap temp = new HashMap();
                    temp.put("id", tempData.id);  
                    temp.put("architecture", tempData.architecture);          
                    temp.put("osCategoryId",tempData.osCategoryId);          
                    temp.put("currency", currency);                      
                    temp.put("baseOs", tempData.baseOs);     
                    temp.put("isReady", tempData.isReady);             
                    temp.put("zone", zoneListArray);     
                    temp.put("url", tempData.url);           
                    temp.put("name", tempData.name);                
                    temp.put("referenceId", tempData.templateReferenceId);
                    temp.put("description", tempData.description);
                    temp.put("format", tempData.format);
                    temp.put("hypervisor", tempData.hypervisor);
                    temp.put("featured", tempData.featured);
                    temp.put("isPublic", tempData.isPublic);
                    temp.put("status", tempData.status);
                    temp.put("cost", tempData.cost);
                    temp.put("passwordEnabled", tempData.passwordEnabled);
                    temp.put("osType", tempData.osType.name);
                    temp.put("osCategory", tempData.osCategory.name);
                    temp.put("extractable", tempData.extractable);   
                    temp.put("osTypeReferenceId", tempData.osType.referenceOsTypeId);
                    temp.put("size",tempData.size);  
                    temp.put("minimumCpu",tempData.minimumCpu);  
                    temp.put("minimumRam",tempData.minimumRam);  
                    temp.put("oneTimeChargeable",tempData.oneTimeChargeable); 
                    temp.put("referelUrl",tempData.osReferenceURL); 
                    temp.put("detailedDescription",tempData.detailedDescription);         
                    temp.put("oneTimeChargeable", tempData.oneTimeChargeable); 
                    tempList.add(temp)
                }
            }
            Console.print("tempList" + tempList)
            return tempList
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
        
    }
    def getTemplateById(id) {        
        def tempData = Template.get(id);
        def count = 0;
        def size = 0;       
        def currency = ApplicationHolder.getApplication().config.billing.defaultCurrency
        ArrayList zoneListArray = new ArrayList();
        for(Iterator k = tempData.zones.iterator();k.hasNext();) {            
            def zone = k.next();             
            HashMap zoneItem = new HashMap();
            zoneItem.put("referenceId", zone.referenceZoneId);  
            zoneItem.put("name", zone.aliasName);  
            zoneItem.put("networkType", zone.networkType);  
            zoneItem.put("zoneId", zone.id);  
            
            zoneListArray.add(zoneItem)            
        }
        ArrayList tempList = new ArrayList();
        HashMap temp = new HashMap();
        temp.put("id", tempData.id);  
        temp.put("architecture", tempData.architecture);          
        temp.put("osCategoryId",tempData.osCategoryId);          
        temp.put("currency", currency);                      
        temp.put("baseOs", tempData.baseOs);     
        temp.put("isReady", tempData.isReady);             
        temp.put("zone", zoneListArray);     
        temp.put("url", tempData.url);           
        temp.put("name", tempData.name);                
        temp.put("referenceId", tempData.templateReferenceId);
        temp.put("description", tempData.description);
        temp.put("format", tempData.format);
        temp.put("hypervisor", tempData.hypervisor);
        temp.put("featured", tempData.featured);
        temp.put("isPublic", tempData.isPublic);
        temp.put("status", tempData.status);
        temp.put("cost", tempData.cost);
        temp.put("passwordEnabled", tempData.passwordEnabled);
        temp.put("osType", tempData.osType.name);
        temp.put("osCategory", tempData.osCategory.name);
        temp.put("extractable", tempData.extractable);   
        temp.put("osTypeReferenceId", tempData.osType.referenceOsTypeId);
        temp.put("size",tempData.size);  
        temp.put("minimumCpu",tempData.minimumCpu);  
        temp.put("minimumRam",tempData.minimumRam);  
        temp.put("oneTimeChargeable",tempData.oneTimeChargeable); 
        temp.put("referelUrl",tempData.osReferenceURL); 
        temp.put("detailedDescription",tempData.detailedDescription);         
        temp.put("oneTimeChargeable", tempData.oneTimeChargeable);    
        temp.put("AppTempImageUrl", tempData.path);    
        
        tempList.add(temp)
        return tempList;       
    }
    
    def list(String searchName, String baseOs, String myTemplate, String appTemplate, String templateReferenceId) {
        try {            
            def response = templateServer().listTemplates("all", null)            
            for(Iterator<TemplateResponse> iter = response.templates.iterator(); iter.hasNext();) {
                def data = iter.next();

                Template template = Template.findByTemplateReferenceId(data.templateId);
                if (!template) {                    
//                    template  = new Template();
//                    template.templateReferenceId = data.templateId;
//                    template.name = data.templateName;
//                    template.description = data.templateDisplayText;
//                    template.format = data.templateFormat;
//                    template.hypervisor = Hypervisor.findByZone(Zone.findByReferenceZoneId(data.templateZoneId));
//                    template.extractable = Boolean.parseBoolean(data.isExtractable);                    
//                    template.featured = Boolean.parseBoolean(data.isFeatured);
//                    template.isPublic = Boolean.parseBoolean(data.isExtractable);
//                    template.passwordEnabled = Boolean.parseBoolean(data.passwordEnabled);
//                    template.url = "default";
//                    template.zone = Zone.findByReferenceZoneId(data.templateZoneId);
//                    def osType = OsType.findByReferenceOsTypeId(data.osTypeId);
//                    template.osType =  osType;
//                    template.osCategory = osType.osCategory;
//                    template.deleted = false;     
//                    template.myTemplate = false;    
//                    template.appTemplate = false;    
//                    template.save(flush: true);                    
                } else if(template) {
                   
                     def tempsize;
                    
                    if(data.templateSize == null || data.templateSize == "null") {
                        
                        template.size = 0
                        template.status = data.templateStatus;    
                        template.save(flush: true);
                    } else {
                        tempsize = Double.parseDouble(data.templateSize);
                        def volumeSize = tempsize / (double) 1073741824;
                        template.size = Math.round(volumeSize * 100d) / 100d;
                        template.status = data.templateStatus;    
                        template.save(flush: true);
                    }
                      
                }
            } 
             ArrayList<ArrayList<String>> templateList = new ArrayList<ArrayList<String>>();    
             if((searchName == "null" || searchName == null) && (appTemplate == "null" || appTemplate == null) && (baseOs == "null" || baseOs == null) && (myTemplate == "null" || myTemplate == null) && (templateReferenceId == null || templateReferenceId == "null")) {
                def result = Zone.findAll();             
                for(int i=0; i < result.size(); i++) { 
                    def item = result[i]; 
                    for(Iterator k = item.templates.iterator();k.hasNext();) {
                        def template = k.next(); 
                        def tempData = Template.findWhere(id:template.id, deleted: false);
                        if(tempData != null) {
                            HashMap<String,String> temp = new HashMap<String,String>();  
                            temp.put("zoneName", item.aliasName);
                            temp.put("id", tempData.id);
                            temp.put("baseOs", tempData.baseOs);                            
                            temp.put("name", tempData.name);
                            temp.put("referenceZoneId", item.referenceZoneId);
                            temp.put("referenceId", tempData.templateReferenceId);
                            temp.put("description", tempData.description);
                            temp.put("format", tempData.format);
                            temp.put("hypervisor", tempData.hypervisor);
                            temp.put("featured", tempData.featured);
                            temp.put("isPublic", tempData.isPublic);
                            temp.put("status", tempData.status);
                            temp.put("cost", tempData.cost);
                            temp.put("passwordEnabled", tempData.passwordEnabled);
                            temp.put("osType", tempData.osType.name);
                            temp.put("osCategory", tempData.osCategory.name);                            
                            temp.put("osTypeReferenceId", tempData.osType.referenceOsTypeId);
                            temp.put("extractable", tempData.extractable);  
                            
                            templateList.add(temp);  
                        }
                    }
                }
            } else if((searchName != "null" || searchName != null) && (appTemplate != "null" || appTemplate != null) && (baseOs == "null" || baseOs == null) && (myTemplate == "null" || myTemplate == null) && (templateReferenceId == null || templateReferenceId == "null")) {
                def tempCriteria = Template.createCriteria()
                def tempData = tempCriteria.list {
                    like("name", "%" + searchName + "%")
                    and {
                        eq("deleted", false)
                        and {
                            eq("appTemplate", true)
                        } 
                    } 

                }
                for(int te=0; te < tempData.size(); te++) {
                    def tempDataItem = tempData[te]; 
                        def zones = Zone.findAll();             
                        for(int i=0; i < zones.size(); i++) { 
                            def zone = zones[i]; 
                            for(Iterator k = zone.templates.iterator();k.hasNext();) {
                                def template = k.next(); 
                                if(tempDataItem.templateReferenceId == template.templateReferenceId) {
                                    HashMap<String,String> temp = new HashMap<String,String>();
                                    temp.put("osType", tempDataItem.osType.name);
                                    temp.put("id", tempData.id);
                                    temp.put("baseOs", tempData.baseOs);
                                    temp.put("osCategory", tempDataItem.osCategory.name);
                                    temp.put("zone", zone.aliasName);
                                    temp.put("referenceZoneId", zone.referenceZoneId);
                                    temp.put("status", tempDataItem.status);
                                    temp.put("templateName", tempDataItem.name);
                                    temp.put("cost", tempDataItem.cost);
                                    temp.put("description", tempDataItem.description);
                                    temp.put("templateReferenceId", tempDataItem.templateReferenceId);
                                    temp.put("myTemplate", tempDataItem.myTemplate);
                                    temp.put("appTemplate", tempDataItem.appTemplate);
                                    templateList.add(temp);  
                                }
                        
                            }
                        }
                }
            } else if((searchName != "null" || searchName != null) && (myTemplate != "null" || myTemplate != null) && (appTemplate == "null" || appTemplate == null) && (baseOs == "null" || baseOs == null) && (templateReferenceId == null || templateReferenceId == "null")) {
                def tempCriteria = Template.createCriteria()
                def tempData = tempCriteria.list {
                    like("name", "%" + searchName + "%")
                    and {
                        eq("deleted", false)
                        and {
                            eq("myTemplate", true)
                        }
                    } 

                }
                for(int te=0; te < tempData.size(); te++) {
                    def tempDataItem = tempData[te]; 
                        def zones = Zone.findAll();             
                        for(int i=0; i < zones.size(); i++) { 
                            def zone = zones[i]; 
                            for(Iterator k = zone.templates.iterator();k.hasNext();) {
                                def template = k.next(); 
                                if(tempDataItem.templateReferenceId == template.templateReferenceId) {
                                    HashMap<String,String> temp = new HashMap<String,String>();
                                    temp.put("osType", tempDataItem.osType.name);
                                    temp.put("zone", zone.aliasName);
                                    temp.put("id", tempData.id);
                                    temp.put("baseOs", tempData.baseOs);
                                    temp.put("osCategory", tempDataItem.osCategory.name);
                                    temp.put("referenceZoneId", zone.referenceZoneId);
                                    temp.put("templateName", tempDataItem.name);
                                    temp.put("status", tempDataItem.status);
                                    temp.put("cost", tempDataItem.cost);
                                    temp.put("description", tempDataItem.description);
                                    temp.put("templateReferenceId", tempDataItem.templateReferenceId);
                                    temp.put("myTemplate", tempDataItem.myTemplate);
                                    temp.put("appTemplate", tempDataItem.appTemplate);
                                    templateList.add(temp);  
                                }
                        
                            }
                        }
                }
            } else if((searchName != "null" || searchName != null) && (baseOs != "null" || baseOs != null) && (myTemplate == "null" || myTemplate == null) && (appTemplate == "null" || appTemplate == null) && (templateReferenceId == null || templateReferenceId == "null")) {
                def tempCriteria = Template.createCriteria()
                def tempData = tempCriteria.list {
                    like("name", "%" + searchName + "%")
                    and {
                        eq("deleted", false)
                        and {
                            eq("baseOs", baseOs)
                        }
                    } 

                }
                for(int te=0; te < tempData.size(); te++) {
                    def tempDataItem = tempData[te]; 
                    def zones = Zone.findAll();             
                    for(int i=0; i < zones.size(); i++) { 
                        def zone = zones[i]; 
                        for(Iterator k = zone.templates.iterator();k.hasNext();) {
                            def template = k.next(); 
                            if(tempDataItem.templateReferenceId == template.templateReferenceId) {
                                def currentTemp = Template.findWhere(templateReferenceId:tempDataItem.templateReferenceId, baseOs:baseOs, deleted: false, appTemplate:false, myTemplate:false);
                                if(currentTemp != null) {
                                    HashMap<String,String> temp = new HashMap<String,String>();
                                    temp.put("osType", currentTemp.osType.name);
                                    temp.put("zone", zone.aliasName);
                                    temp.put("id", tempData.id);
                                    temp.put("baseOs", tempData.baseOs);
                                    temp.put("osCategory", currentTemp.osCategory.name);
                                    temp.put("referenceZoneId", zone.referenceZoneId);
                                    temp.put("templateName", currentTemp.name);
                                    temp.put("status", tempDataItem.status);
                                    temp.put("cost", currentTemp.cost);
                                    temp.put("description", currentTemp.description);
                                    temp.put("templateReferenceId", currentTemp.templateReferenceId);
                                    temp.put("myTemplate", currentTemp.myTemplate);
                                    temp.put("appTemplate", currentTemp.appTemplate);
                                    templateList.add(temp); 
                                }

                            }

                        }
                    }
                }
            } else if((templateReferenceId != null || templateReferenceId != "null") && (searchName != "null" || searchName != null) && (baseOs != "null" || baseOs != null) && (myTemplate == "null" || myTemplate == null) && (appTemplate == "null" || appTemplate == null)) {
                def currentTemp =  Template.findByTemplateReferenceId(templateReferenceId)
                if(currentTemp) {
                    HashMap<String,String> temp = new HashMap<String,String>();
                    temp.put("osType", currentTemp.osType.name);
                    temp.put("baseOs", tempData.baseOs);
                    temp.put("osCategory", currentTemp.osCategory.name);
                    temp.put("templateName", currentTemp.name);
                    temp.put("status", currentTemp.status);
                    temp.put("cost", currentTemp.cost);
                    temp.put("description", currentTemp.description);
                    temp.put("templateReferenceId", currentTemp.templateReferenceId);
                    temp.put("myTemplate", currentTemp.myTemplate);
                    temp.put("appTemplate", currentTemp.appTemplate);
                    templateList.add(temp); 
                }
            }

            return templateList;
        } catch (Exception ex) { 
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
def filterTemplate (zoneReferenceId, hypervisor, osType, architecture, name) {
    try {        
        def currency = ApplicationHolder.getApplication().config.billing.defaultCurrency
        ArrayList<ArrayList<String>> templateList = new ArrayList<ArrayList<String>>();          
        def response = templateServer().listTemplates("all", null)   
        def zoneResult = ""
        def zoneItem = ""
        def template = ""
        def tempData = ""
        for(Iterator<TemplateResponse> iter = response.templates.iterator(); iter.hasNext();) {
            def data = iter.next();
            Template newTemplate = Template.findByTemplateReferenceId(data.templateId);
            if(newTemplate) {                   
                def tempsize;                    
                if(data.templateSize == null || data.templateSize == "null") {                        
                    newTemplate.size = 0
                    newTemplate.status = data.templateStatus;    
                    newTemplate.save(flush: true);
                } else {
                    tempsize = Double.parseDouble(data.templateSize);
                    def volumeSize = tempsize / (double) 1073741824;
                    newTemplate.size = Math.round(volumeSize * 100d) / 100d;
                    newTemplate.status = data.templateStatus;    
                    newTemplate.save(flush: true);
                }                      
            }
        } 
        if(zoneReferenceId == null || zoneReferenceId == "null") {            
            zoneResult =  Zone.findAll();                    
            for(int zone=0; zone < zoneResult.size(); zone++) {
                zoneItem = zoneResult[zone];
                for(Iterator k = zoneItem.templates.iterator();k.hasNext();) {
                    template = k.next(); 
                    tempData = Template.withCriteria {
                        eq("id", template.id)                    
                        if(hypervisor != "null") {
                            eq("hypervisor", hypervisor)
                        }
                        if(osType != "null") {
                            eq("osCategory",  OsCategory.findByOsCategoryId(osType))
                        }
                        if(architecture != "null") {
                            eq("architecture", architecture)
                        }
                        if(name != "null") {
                            ilike("name", "%" + name + "%")  
                        }
                    } 
                    if(tempData[0] != null) {                  
                        HashMap<String,String> temp = new HashMap<String,String>();   
                        temp.put("osCategory", tempData[0].osCategory.name);
                        temp.put("id", tempData[0].id);     
                        temp.put("zoneId", zoneItem.id);                                   
                        temp.put("osType", tempData[0].osType.name);
                        temp.put("templateName", tempData[0].name);
                        temp.put("currency", currency);
                        temp.put("isReady", tempData[0].isReady);                                
                        temp.put("cost", tempData[0].cost);
                        temp.put("architecture", tempData[0].architecture);                        
                        temp.put("referenceZoneId", zoneItem.referenceZoneId);
                        temp.put("description", tempData[0].description);
                        temp.put("templateReferenceId", tempData[0].templateReferenceId);
                        temp.put("zone", zoneItem.aliasName);
                        temp.put("referenceZoneId", zoneItem.referenceZoneId);
                        temp.put("myTemplate", tempData[0].myTemplate);
                        temp.put("appTemplate", tempData[0].appTemplate);
                        temp.put("imagePath", tempData[0].path);
                        temp.put("baseOs", tempData[0].baseOs);     
                        temp.put("oneTimeChargeable", tempData[0].oneTimeChargeable);                                
                        templateList.add(temp);                        
                    }
                }                
            }
            return templateList;
        } else {            
            zoneResult =  Zone.findAllWhere(referenceZoneId: zoneReferenceId);
            for(int zone=0; zone < zoneResult.size(); zone++) {
                zoneItem = zoneResult[zone];
                for(Iterator k = zoneItem.templates.iterator();k.hasNext();) {
                    template = k.next(); 
                    tempData = Template.withCriteria {
                        eq("id", template.id)                    
                        if(hypervisor != "null") {
                            eq("hypervisor", hypervisor)
                        }
                        if(osType != "null") {
                           eq("osCategory",  OsCategory.findByOsCategoryId(osType))
                        }
                        if(architecture != "null") {
                            eq("architecture", architecture)
                        }
                        if(name != "null") {
                            ilike("name", "%" + name + "%")  
                        }
                    }  
                    if(tempData[0] != null) {                  
                        HashMap<String,String> temp = new HashMap<String,String>();   
                        temp.put("osCategory", tempData[0].osCategory.name);                        
                        temp.put("id", tempData[0].id);     
                        temp.put("zoneId", zoneItem.id);         
                        temp.put("architecture", tempData[0].architecture);    
                        temp.put("osType", tempData[0].osType.name);
                        temp.put("templateName", tempData[0].name);
                        temp.put("currency", currency);
                        temp.put("isReady", tempData[0].isReady);                                
                        temp.put("cost", tempData[0].cost);
                        temp.put("referenceZoneId", zoneItem.referenceZoneId);
                        temp.put("description", tempData[0].description);
                        temp.put("templateReferenceId", tempData[0].templateReferenceId);
                        temp.put("zone", zoneItem.aliasName);
                        temp.put("referenceZoneId", zoneItem.referenceZoneId);
                        temp.put("myTemplate", tempData[0].myTemplate);
                        temp.put("appTemplate", tempData[0].appTemplate);
                        temp.put("imagePath", tempData[0].path);
                        temp.put("baseOs", tempData[0].baseOs);     
                        temp.put("oneTimeChargeable", tempData[0].oneTimeChargeable);                                
                        templateList.add(temp);                        
                    }
                }                 
            }
            return templateList;
        }                    
    } catch (Exception ex) { 
        ex.printStackTrace(System.err);
        throw ex;    
    }     
}
def templatesPagination(pageNo, zoneReferenceId, hypervisor, osType, architecture, name) {
    try {           
        def resultTemplates = "";
        def currency = ApplicationHolder.getApplication().config.billing.defaultCurrency
        def currentPageNo = "";
        def currentRecordPerPage = 8;
        def noOfPages = "";
        def minLimit = 0;
        def maxLimit = 0
        def currentTemp = ""
        if(pageNo) {
            if(pageNo == 0) {
                currentPageNo = 1                
            } else {
                currentPageNo = Integer.parseInt(pageNo);
            }            
        } else {
            currentPageNo = 1;
        }  
        ArrayList<ArrayList<String>> templateList = new ArrayList<ArrayList<String>>();   
        ArrayList<ArrayList<String>> resultTemplate = new ArrayList<ArrayList<String>>();           
        ArrayList<ArrayList<String>> pageArrayList = new ArrayList<ArrayList<String>>();           
        HashMap currentPage = new HashMap();  
        if(zoneReferenceId == "null" || zoneReferenceId == null) {             
            def zoneResult =  Zone.findAll();
            for(int i=0; i < zoneResult.size(); i++) { 
                def zoneItem = zoneResult[i];                
                resultTemplates = Template.withCriteria {                                        
                    zones { 
                        eq("referenceZoneId", zoneItem.referenceZoneId) 
                    }                                           
                    if(hypervisor == "null" || hypervisor == null) {                            
                    } else {
                        eq("hypervisor", hypervisor)
                    }
                    if(osType == "null" || osType == null) {                       
                    } else {
                        eq("osCategory",  OsCategory.findByOsCategoryId(osType))
                    }
                    if(architecture == "null" || architecture == null) {                        
                    } else {
                        eq("architecture", architecture)
                    }
                    if(name == "null" || name == null) {                         
                    } else {
                        ilike("name", "%" + name + "%") 
                    }
                    eq("deleted", false)
                    eq("isReady", true)
                }        
                for(int allTempItem = 0; allTempItem < resultTemplates.size(); allTempItem++) {
                    def tempData = resultTemplates[allTempItem];
                    HashMap<String,String> temp = new HashMap<String,String>();                     
                    temp.put("osCategory", tempData.osCategory.name);
                    temp.put("id", tempData.id);     
                    temp.put("currency", currency);                                             
                    temp.put("zoneId", zoneItem.id);                                   
                    temp.put("osType", tempData.osType.name);
                    temp.put("templateName", tempData.name);
                    temp.put("architecture", tempData.architecture);                       
                    temp.put("isReady", tempData.isReady);                                
                    temp.put("cost", tempData.cost);
                    temp.put("referenceZoneId", zoneItem.referenceZoneId);
                    temp.put("description", tempData.description);
                    temp.put("templateReferenceId", tempData.templateReferenceId);
                    temp.put("zone", zoneItem.aliasName);
                    temp.put("referenceZoneId", zoneItem.referenceZoneId);
                    temp.put("myTemplate", tempData.myTemplate);
                    temp.put("appTemplate", tempData.appTemplate);
                    temp.put("imagePath", tempData.path);
                    temp.put("baseOs", tempData.baseOs);     
                    temp.put("oneTimeChargeable", tempData.oneTimeChargeable);    
                    templateList.add(temp); 
                }                
            }
            currentPage.put("page", currentPageNo)
            currentPage.put("recordPerPage", currentRecordPerPage)                  
            noOfPages = Math.ceil(templateList.size() / currentRecordPerPage);            
            def pages = Math.round(noOfPages);            
            currentPage.put("totalPages", pages)     
            currentPage.put("totalRecords", templateList.size())   
            if(currentPageNo <= pages) {
                minLimit = (currentPageNo * currentRecordPerPage) - currentRecordPerPage;
                maxLimit = minLimit + currentRecordPerPage;                  
                if(templateList) {
                    for(int tempItem = minLimit; tempItem < maxLimit; tempItem++) {
                        currentTemp = templateList[tempItem];                        
                        if(currentTemp) {
                            HashMap<String,String> currentTempItem = new HashMap<String,String>(); 
                            currentTempItem.put("osCategory", currentTemp.osCategory);
                            currentTempItem.put("currency", currency); 
                            currentTempItem.put("id", currentTemp.id);     
                            currentTempItem.put("zoneId", currentTemp.zoneId);                                   
                            currentTempItem.put("osType", currentTemp.osType);
                            currentTempItem.put("templateName", currentTemp.templateName);
                            currentTempItem.put("architecture", currentTemp.architecture);                       
                            currentTempItem.put("isReady", currentTemp.isReady);                                
                            currentTempItem.put("cost", currentTemp.cost);
                            currentTempItem.put("referenceZoneId", currentTemp.referenceZoneId);
                            currentTempItem.put("description", currentTemp.description);
                            currentTempItem.put("templateReferenceId", currentTemp.templateReferenceId);
                            currentTempItem.put("zone", currentTemp.zone);
                            currentTempItem.put("referenceZoneId", currentTemp.referenceZoneId);
                            currentTempItem.put("myTemplate", currentTemp.myTemplate);
                            currentTempItem.put("appTemplate", currentTemp.appTemplate);
                            currentTempItem.put("imagePath", currentTemp.imagePath);
                            currentTempItem.put("baseOs", currentTemp.baseOs);     
                            currentTempItem.put("oneTimeChargeable", currentTemp.oneTimeChargeable);    
                            resultTemplate.add(currentTempItem);                             
                        }                        
                    }
                    currentPage.put("templateInfo", resultTemplate);
                    pageArrayList.add(currentPage);         
                }
            }
               
        } else if(zoneReferenceId != "null" || zoneReferenceId != null) {
            def zone = Zone.findByReferenceZoneId(zoneReferenceId);                                        
            resultTemplates = Template.withCriteria {  
                eq("deleted", false)
                eq("isReady", true)
                zones { 
                    eq("referenceZoneId", zone.referenceZoneId) 
                }
                if(hypervisor == "null" || hypervisor == null) {                            
                } else {
                    eq("hypervisor", hypervisor)
                }
                if(osType == "null" || osType == null) {                       
                } else {
                    eq("osCategory",  OsCategory.findByOsCategoryId(osType))
                }
                if(architecture == "null" || architecture == null) {                        
                } else {
                    eq("architecture", architecture)
                }
                if(name == "null" || name == null) {                         
                } else {
                    ilike("name", "%" + name + "%") 
                }              
            }                          
            currentPage.put("page", currentPageNo)
            currentPage.put("recordPerPage", currentRecordPerPage)                  
            noOfPages = Math.ceil(resultTemplates.size() / currentRecordPerPage);            
            def pages = Math.round(noOfPages);            
            currentPage.put("totalPages", pages)     
            currentPage.put("totalRecords", templateList.size())
            if(currentPageNo <= pages) {
                minLimit = (currentPageNo * currentRecordPerPage) - currentRecordPerPage;
                maxLimit = minLimit + currentRecordPerPage;                  
                if(resultTemplates) {
                    for(int tempItem = minLimit; tempItem < maxLimit; tempItem++) {
                        currentTemp = resultTemplates[tempItem];                        
                        if(currentTemp) {
                            HashMap<String,String> currentTempItem = new HashMap<String,String>(); 
                            currentTempItem.put("osCategory", currentTemp.osCategory.name);
                            currentTempItem.put("id", currentTemp.id);     
                            currentTempItem.put("zoneId", zone.id);  
                            currentTempItem.put("currency", currency); 
                            currentTempItem.put("osType", currentTemp.osType.name);
                            currentTempItem.put("templateName", currentTemp.name);
                            currentTempItem.put("architecture", currentTemp.architecture);                       
                            currentTempItem.put("isReady", currentTemp.isReady);                                
                            currentTempItem.put("cost", currentTemp.cost);
                            currentTempItem.put("referenceZoneId", zone.referenceZoneId);
                            currentTempItem.put("description", currentTemp.description);
                            currentTempItem.put("templateReferenceId", currentTemp.templateReferenceId);
                            currentTempItem.put("zone", zone.aliasName);                            
                            currentTempItem.put("myTemplate", currentTemp.myTemplate);
                            currentTempItem.put("appTemplate", currentTemp.appTemplate);
                            currentTempItem.put("imagePath", currentTemp.path);
                            currentTempItem.put("baseOs", currentTemp.baseOs);     
                            currentTempItem.put("oneTimeChargeable", currentTemp.oneTimeChargeable); 
                            resultTemplate.add(currentTempItem);                             
                        }                        
                    }
                    currentPage.put("templateInfo", resultTemplate);
                    pageArrayList.add(currentPage);         
                }
            }            
        }
        return pageArrayList;
    } catch(Exception ex) {
        ex.printStackTrace(System.err);
        throw ex;
    }
}
def templatesByCategory(zoneReferenceId, hypervisor, osType, architecture) {
    try {            
        def zoneResult = ""
        def zoneItem = ""
        def template = ""
        def tempData = ""        
        ArrayList<ArrayList<String>> templateList = new ArrayList<ArrayList<String>>();            
        def currency = ApplicationHolder.getApplication().config.billing.defaultCurrency
        if((zoneReferenceId == "null" || zoneReferenceId == null) && (hypervisor == "null" || hypervisor == null) && (osType == "null" || osType == null) && (architecture == "null" || architecture == null)) {
            zoneResult =  Zone.findAll();                    
            for(int zone=0; zone < zoneResult.size(); zone++) {
            zoneItem = zoneResult[zone];
            for(Iterator k = zoneItem.templates.iterator();k.hasNext();) {
                template = k.next(); 
                tempData = Template.findWhere(id:template.id, deleted: false, isReady: true);                 
                if(tempData != null) {                  
                    HashMap<String,String> temp = new HashMap<String,String>();   
                    temp.put("osCategory", tempData.osCategory.name);
                    temp.put("id", tempData.id);     
                    temp.put("zoneId", zoneItem.id);                                   
                    temp.put("osType", tempData.osType.name);
                    temp.put("templateName", tempData.name);
                    temp.put("architecture", tempData.architecture);    
                    temp.put("currency", currency);
                    temp.put("isReady", tempData.isReady);                                
                    temp.put("cost", tempData.cost);
                    temp.put("referenceZoneId", zoneItem.referenceZoneId);
                    temp.put("description", tempData.description);
                    temp.put("templateReferenceId", tempData.templateReferenceId);
                    temp.put("zone", zoneItem.aliasName);
                    temp.put("zoneId", zoneItem.id);                    
                    temp.put("referenceZoneId", zoneItem.referenceZoneId);
                    temp.put("myTemplate", tempData.myTemplate);
                    temp.put("appTemplate", tempData.appTemplate);
                    temp.put("imagePath", tempData.path);
                    temp.put("baseOs", tempData.baseOs);     
                    temp.put("oneTimeChargeable", tempData.oneTimeChargeable);                                
                    templateList.add(temp); 
                }
            }
        }       
        return templateList;
        } else if((zoneReferenceId == "null" || zoneReferenceId == null) && (hypervisor == "null" || hypervisor == null) && (osType != "null" || osType != null) && (architecture == "null" || architecture == null)) {
            zoneResult =  Zone.findAll();                    
            for(int zone=0; zone < zoneResult.size(); zone++) {
            zoneItem = zoneResult[zone];
            for(Iterator k = zoneItem.templates.iterator();k.hasNext();) {
                template = k.next(); 
                tempData = Template.findWhere(id:template.id, deleted: false, isReady: true, osCategory: OsCategory.findByName(osType));                 
                if(tempData != null) {                  
                    HashMap<String,String> temp = new HashMap<String,String>();   
                    temp.put("osCategory", tempData.osCategory.name);
                    temp.put("id", tempData.id);     
                    temp.put("zoneId", zoneItem.id);         
                    temp.put("architecture", tempData.architecture);    
                    temp.put("osType", tempData.osType.name);
                    temp.put("templateName", tempData.name);
                    temp.put("currency", currency);
                    temp.put("zoneId", zoneItem.id);  
                    temp.put("isReady", tempData.isReady);                                
                    temp.put("cost", tempData.cost);
                    temp.put("referenceZoneId", zoneItem.referenceZoneId);
                    temp.put("description", tempData.description);
                    temp.put("templateReferenceId", tempData.templateReferenceId);
                    temp.put("zone", zoneItem.aliasName);
                    temp.put("referenceZoneId", zoneItem.referenceZoneId);
                    temp.put("myTemplate", tempData.myTemplate);
                    temp.put("appTemplate", tempData.appTemplate);
                    temp.put("imagePath", tempData.path);
                    temp.put("baseOs", tempData.baseOs);     
                    temp.put("oneTimeChargeable", tempData.oneTimeChargeable);                                
                    templateList.add(temp); 
                }
            }
        }       
        return templateList;
        } else if((zoneReferenceId == "null" || zoneReferenceId == null) && (hypervisor == "null" || hypervisor == null) && (osType != "null" || osType != null) && (architecture != "null" || architecture != null)) {
            zoneResult =  Zone.findAll();                    
            for(int zone=0; zone < zoneResult.size(); zone++) {
            zoneItem = zoneResult[zone];
            for(Iterator k = zoneItem.templates.iterator();k.hasNext();) {
                template = k.next(); 
                tempData = Template.findWhere(id:template.id, deleted: false, isReady: true, architecture: architecture, osCategory: OsCategory.findByOsCategoryId(osType));                 
                if(tempData != null) {                  
                    HashMap<String,String> temp = new HashMap<String,String>();   
                    temp.put("osCategory", tempData.osCategory.name);
                    temp.put("id", tempData.id);     
                    temp.put("zoneId", zoneItem.id);                                   
                    temp.put("osType", tempData.osType.name);
                    temp.put("templateName", tempData.name);
                    temp.put("currency", currency);
                    temp.put("zoneId", zoneItem.id);  
                    temp.put("isReady", tempData.isReady);                                
                    temp.put("cost", tempData.cost);
                    temp.put("architecture", tempData.architecture);    
                    temp.put("referenceZoneId", zoneItem.referenceZoneId);
                    temp.put("description", tempData.description);
                    temp.put("templateReferenceId", tempData.templateReferenceId);
                    temp.put("zone", zoneItem.aliasName);
                    temp.put("referenceZoneId", zoneItem.referenceZoneId);
                    temp.put("myTemplate", tempData.myTemplate);
                    temp.put("appTemplate", tempData.appTemplate);
                    temp.put("imagePath", tempData.path);
                    temp.put("baseOs", tempData.baseOs);     
                    temp.put("oneTimeChargeable", tempData.oneTimeChargeable);                                
                    templateList.add(temp); 
                }
            }
        }       
        return templateList;
        } else if((zoneReferenceId == "null" || zoneReferenceId == null) && (hypervisor != "null" || hypervisor != null) && (osType == "null" || osType == null) && (architecture == "null" || architecture == null)) {
            zoneResult =  Zone.findAll();                          
            for(int zone=0; zone < zoneResult.size(); zone++) {
            zoneItem = zoneResult[zone];
            for(Iterator k = zoneItem.templates.iterator();k.hasNext();) {
                template = k.next(); 
                tempData = Template.findWhere(id:template.id, deleted: false,isReady: true, hypervisor: hypervisor);                 
                if(tempData != null) {                  
                    HashMap<String,String> temp = new HashMap<String,String>();   
                    temp.put("osCategory", tempData.osCategory.name);
                    temp.put("id", tempData.id);     
                    temp.put("zoneId", zoneItem.id);                                   
                    temp.put("osType", tempData.osType.name);
                    temp.put("templateName", tempData.name);
                    temp.put("currency", currency);
                    temp.put("architecture", tempData.architecture);    
                    temp.put("isReady", tempData.isReady);                                
                    temp.put("cost", tempData.cost);
                    temp.put("referenceZoneId", zoneItem.referenceZoneId);
                    temp.put("description", tempData.description);
                    temp.put("templateReferenceId", tempData.templateReferenceId);
                    temp.put("zone", zoneItem.aliasName);
                    temp.put("referenceZoneId", zoneItem.referenceZoneId);
                    temp.put("myTemplate", tempData.myTemplate);
                    temp.put("appTemplate", tempData.appTemplate);
                    temp.put("imagePath", tempData.path);
                    temp.put("baseOs", tempData.baseOs);     
                    temp.put("oneTimeChargeable", tempData.oneTimeChargeable);                                
                    templateList.add(temp); 
                }
            }
        }       
        return templateList;
        }else if((zoneReferenceId == "null" || zoneReferenceId == null) && (hypervisor != "null" || hypervisor != null) && (osType != "null" || osType != null) && (architecture != "null" || architecture != null)) {
            zoneResult =  Zone.findAll();                          
            for(int zone=0; zone < zoneResult.size(); zone++) {
            zoneItem = zoneResult[zone];
            for(Iterator k = zoneItem.templates.iterator();k.hasNext();) {
                template = k.next(); 
                tempData = Template.findWhere(id:template.id, deleted: false,isReady: true, hypervisor: hypervisor, architecture: architecture, osCategory: OsCategory.findByOsCategoryId(osType));                 
                if(tempData != null) {                  
                    HashMap<String,String> temp = new HashMap<String,String>();   
                    temp.put("osCategory", tempData.osCategory.name);
                    temp.put("id", tempData.id);     
                    temp.put("zoneId", zoneItem.id);    
                    temp.put("architecture", tempData.architecture);    
                    temp.put("osType", tempData.osType.name);
                    temp.put("templateName", tempData.name);
                    temp.put("currency", currency);
                    temp.put("isReady", tempData.isReady);                                
                    temp.put("cost", tempData.cost);
                    temp.put("referenceZoneId", zoneItem.referenceZoneId);
                    temp.put("description", tempData.description);
                    temp.put("templateReferenceId", tempData.templateReferenceId);
                    temp.put("zone", zoneItem.aliasName);
                    temp.put("referenceZoneId", zoneItem.referenceZoneId);
                    temp.put("myTemplate", tempData.myTemplate);
                    temp.put("appTemplate", tempData.appTemplate);
                    temp.put("imagePath", tempData.path);
                    temp.put("baseOs", tempData.baseOs);     
                    temp.put("oneTimeChargeable", tempData.oneTimeChargeable);                                
                    templateList.add(temp); 
                }
            }
        }       
        return templateList;
        } else if((zoneReferenceId != "null" || zoneReferenceId != null) && (hypervisor == "null" || hypervisor == null) && (osType == "null" || osType == null) && (architecture == "null" || architecture == null)) {
            zoneResult =  Zone.findAllWhere(referenceZoneId: zoneReferenceId);                    
            for(int zone=0; zone < zoneResult.size(); zone++) {
            zoneItem = zoneResult[zone];
            for(Iterator k = zoneItem.templates.iterator();k.hasNext();) {
                template = k.next(); 
                tempData = Template.findWhere(id:template.id, deleted: false,isReady: true);                 
                if(tempData != null) {                  
                    HashMap<String,String> temp = new HashMap<String,String>();   
                    temp.put("osCategory", tempData.osCategory.name);
                    temp.put("id", tempData.id);     
                    temp.put("zoneId", zoneItem.id);                                   
                    temp.put("osType", tempData.osType.name);
                    temp.put("templateName", tempData.name);
                    temp.put("currency", currency);
                    temp.put("architecture", tempData.architecture);    
                    temp.put("isReady", tempData.isReady);                                
                    temp.put("cost", tempData.cost);
                    temp.put("referenceZoneId", zoneItem.referenceZoneId);
                    temp.put("description", tempData.description);
                    temp.put("templateReferenceId", tempData.templateReferenceId);
                    temp.put("zone", zoneItem.aliasName);
                    temp.put("referenceZoneId", zoneItem.referenceZoneId);
                    temp.put("myTemplate", tempData.myTemplate);
                    temp.put("appTemplate", tempData.appTemplate);
                    temp.put("imagePath", tempData.path);
                    temp.put("baseOs", tempData.baseOs);     
                    temp.put("oneTimeChargeable", tempData.oneTimeChargeable);                                
                    templateList.add(temp); 
                }
            }
        }       
        return templateList;
        }else if((zoneReferenceId != "null" || zoneReferenceId != null) && (hypervisor == "null" || hypervisor == null) && (osType != "null" || osType != null) && (architecture != "null" || architecture != null)) {
            zoneResult =  Zone.findAllWhere(referenceZoneId: zoneReferenceId);                    
            for(int zone=0; zone < zoneResult.size(); zone++) {
            zoneItem = zoneResult[zone];
            for(Iterator k = zoneItem.templates.iterator();k.hasNext();) {
                template = k.next(); 
                tempData = Template.findWhere(id:template.id, deleted: false, isReady: true, architecture: architecture, osCategory: OsCategory.findByOsCategoryId(osType));                 
                if(tempData != null) {                  
                    HashMap<String,String> temp = new HashMap<String,String>();   
                    temp.put("osCategory", tempData.osCategory.name);
                    temp.put("id", tempData.id);     
                    temp.put("zoneId", zoneItem.id);                                   
                    temp.put("osType", tempData.osType.name);
                    temp.put("templateName", tempData.name);
                    temp.put("currency", currency);
                    temp.put("isReady", tempData.isReady);
                    temp.put("architecture", tempData.architecture);    
                    temp.put("cost", tempData.cost);
                    temp.put("referenceZoneId", zoneItem.referenceZoneId);
                    temp.put("description", tempData.description);
                    temp.put("templateReferenceId", tempData.templateReferenceId);
                    temp.put("zone", zoneItem.aliasName);
                    temp.put("referenceZoneId", zoneItem.referenceZoneId);
                    temp.put("myTemplate", tempData.myTemplate);
                    temp.put("appTemplate", tempData.appTemplate);
                    temp.put("imagePath", tempData.path);
                    temp.put("baseOs", tempData.baseOs);     
                    temp.put("oneTimeChargeable", tempData.oneTimeChargeable);                                
                    templateList.add(temp); 
                }
            }
        }       
        return templateList;
        } else if((zoneReferenceId != "null" || zoneReferenceId != null) && (hypervisor != "null" || hypervisor != null) && (osType == "null" || osType == null) && (architecture == "null" || architecture == null)) {
            zoneResult =  Zone.findAllWhere(referenceZoneId: zoneReferenceId);                    
            for(int zone=0; zone < zoneResult.size(); zone++) {
            zoneItem = zoneResult[zone];
            for(Iterator k = zoneItem.templates.iterator();k.hasNext();) {
                template = k.next(); 
                tempData = Template.findWhere(id:template.id, deleted: false, hypervisor: hypervisor, isReady: true);                 
                if(tempData != null) {                  
                    HashMap<String,String> temp = new HashMap<String,String>();   
                    temp.put("osCategory", tempData.osCategory.name);
                    temp.put("id", tempData.id);     
                    temp.put("zoneId", zoneItem.id);                                   
                    temp.put("osType", tempData.osType.name);
                    temp.put("templateName", tempData.name);
                    temp.put("currency", currency);
                    temp.put("isReady", tempData.isReady);                                
                    temp.put("cost", tempData.cost);
                    temp.put("referenceZoneId", zoneItem.referenceZoneId);
                    temp.put("description", tempData.description);
                    temp.put("templateReferenceId", tempData.templateReferenceId);
                    temp.put("zone", zoneItem.aliasName);
                    temp.put("referenceZoneId", zoneItem.referenceZoneId);
                    temp.put("myTemplate", tempData.myTemplate);
                    temp.put("appTemplate", tempData.appTemplate);
                    temp.put("imagePath", tempData.path);
                    temp.put("architecture", tempData.architecture);    
                    temp.put("baseOs", tempData.baseOs);     
                    temp.put("oneTimeChargeable", tempData.oneTimeChargeable);                                
                    templateList.add(temp); 
                }
            }
        }       
        return templateList;
        }  else if((zoneReferenceId != "null" || zoneReferenceId != null) && (hypervisor != "null" || hypervisor != null) && (osType != "null" || osType != null) && (architecture != "null" || architecture != null)) {
            zoneResult =  Zone.findAllWhere(referenceZoneId: zoneReferenceId);                    
            for(int zone=0; zone < zoneResult.size(); zone++) {
            zoneItem = zoneResult[zone];
            for(Iterator k = zoneItem.templates.iterator();k.hasNext();) {
                template = k.next(); 
                tempData = Template.findWhere(id:template.id, deleted: false, hypervisor: hypervisor, isReady: true, architecture: architecture, osCategory: OsCategory.findByOsCategoryId(osType));                 
                if(tempData != null) {                  
                    HashMap<String,String> temp = new HashMap<String,String>();   
                    temp.put("osCategory", tempData.osCategory.name);
                    temp.put("id", tempData.id);     
                    temp.put("zoneId", zoneItem.id);                                   
                    temp.put("osType", tempData.osType.name);
                    temp.put("templateName", tempData.name);
                    temp.put("currency", currency);
                    temp.put("isReady", tempData.isReady);                                
                    temp.put("cost", tempData.cost);
                    temp.put("referenceZoneId", zoneItem.referenceZoneId);
                    temp.put("description", tempData.description);
                    temp.put("templateReferenceId", tempData.templateReferenceId);
                    temp.put("zone", zoneItem.aliasName);
                    temp.put("referenceZoneId", zoneItem.referenceZoneId);
                    temp.put("myTemplate", tempData.myTemplate);
                    temp.put("appTemplate", tempData.appTemplate);
                    temp.put("imagePath", tempData.path);
                    temp.put("architecture", tempData.architecture);    
                    temp.put("baseOs", tempData.baseOs);     
                    temp.put("oneTimeChargeable", tempData.oneTimeChargeable);                                
                    templateList.add(temp); 
                }
            }
        }       
        return templateList;
        }
        
    } catch (Exception ex) { 
        ex.printStackTrace(System.err);
        throw ex;
    }
}
def listByOs(String zoneReferenceId, String baseOs, String myTemplate, String appTemplate) {
    try {            
        def osCategories = OsCategory.findAll(); 
        ArrayList<ArrayList<String>> templateList = new ArrayList<ArrayList<String>>();            
        def currency = ApplicationHolder.getApplication().config.billing.defaultCurrency
        for(int i=0; i < osCategories.size(); i++) { 
            def item = osCategories[i]; 
            HashMap tempItem = new HashMap(); 
            tempItem.put("osCategory", item.name);
            if((zoneReferenceId == "null" || zoneReferenceId == null) && (baseOs == "null" || baseOs == null) && (myTemplate == "null" || myTemplate == null) && (appTemplate == "null" || appTemplate == null)) {    
                def zoneResult = Zone.findAll();    
                ArrayList<ArrayList<String>> zoneTemplateList = new ArrayList<ArrayList<String>>();            
                for(int zone=0; zone < zoneResult.size(); zone++) {
                    def zoneItem = zoneResult[zone]; 
                    for(Iterator k = zoneItem.templates.iterator();k.hasNext();) {                        
                        def template = k.next();                         
                        def tempData = Template.findWhere(id:template.id, deleted: false, osCategory: item, isReady: true);
                        if(tempData != null) {
                            HashMap<String,String> temp = new HashMap<String,String>();                               
                            temp.put("osCategory", item.name);
                            temp.put("id", tempData.id);     
                            temp.put("zoneId", zoneItem.id);                                   
                            temp.put("osType", tempData.osType.name);
                            temp.put("templateName", tempData.name);
                            temp.put("currency", currency);
                            temp.put("isReady", tempData.isReady);                                
                            temp.put("cost", tempData.cost);
                            temp.put("referenceZoneId", zoneItem.referenceZoneId);
                            temp.put("description", tempData.description);
                            temp.put("templateReferenceId", tempData.templateReferenceId);
                            temp.put("zone", zoneItem.aliasName);
                            temp.put("referenceZoneId", zoneItem.referenceZoneId);
                            temp.put("myTemplate", tempData.myTemplate);
                            temp.put("appTemplate", tempData.appTemplate);
                            temp.put("imagePath", tempData.path);
                            temp.put("baseOs", tempData.baseOs);     
                            temp.put("oneTimeChargeable", tempData.oneTimeChargeable);     
                            
                            zoneTemplateList.add(temp);  
                        }
                    }
                }
                tempItem.put("templates", zoneTemplateList);                       
            } else if((zoneReferenceId != "null" || zoneReferenceId != null) && (baseOs == "null" || baseOs == null) && (myTemplate == "null" || myTemplate == null) && (appTemplate == "null" || appTemplate == null)) {
                def zoneResult = Zone.findAllWhere(referenceZoneId : zoneReferenceId);   
                ArrayList<ArrayList<String>> zoneTemplateList = new ArrayList<ArrayList<String>>();            
                for(int zone=0; zone < zoneResult.size(); zone++) {
                    def zoneItem = zoneResult[zone]; 
                    for(Iterator k = zoneItem.templates.iterator();k.hasNext();) {
                        def template = k.next(); 
                        def tempData = Template.findWhere(id:template.id, deleted: false, osCategory: item, appTemplate:false, myTemplate:false);
                        if(tempData != null) {
                            HashMap<String,String> temp = new HashMap<String,String>(); 
                            temp.put("osCategory", item.name);
                            temp.put("id", tempData.id);  
                            temp.put("zoneId", zoneItem.id);   
                            temp.put("isReady", tempData.isReady);           
                            temp.put("currency", currency);
                            temp.put("osType", tempData.osType.name);
                            temp.put("templateName", tempData.name);
                            temp.put("cost", tempData.cost);
                            temp.put("description", tempData.description);
                            temp.put("templateReferenceId", tempData.templateReferenceId);
                            temp.put("zone", zoneItem.aliasName);
                            temp.put("referenceZoneId", zoneItem.referenceZoneId);
                            temp.put("myTemplate", tempData.myTemplate);
                            temp.put("appTemplate", tempData.appTemplate);
                            temp.put("imagePath", tempData.path);
                            temp.put("baseOs", tempData.baseOs);
                            temp.put("oneTimeChargeable", tempData.oneTimeChargeable);     
                            zoneTemplateList.add(temp);  
                        }
                    }
                }   
                tempItem.put("templates", zoneTemplateList);                         
            } else if((zoneReferenceId == "null" || zoneReferenceId == null) && (baseOs != "null" || baseOs != null)&& (myTemplate == "null" || myTemplate == null) && (appTemplate == "null" || appTemplate == null)) {
                def zoneResult = Zone.findAll();     
                ArrayList<ArrayList<String>> zoneTemplateList = new ArrayList<ArrayList<String>>();            
                for(int zone=0; zone < zoneResult.size(); zone++) {
                    def zoneItem = zoneResult[zone]; 
                    for(Iterator k = zoneItem.templates.iterator();k.hasNext();) {
                        def template = k.next(); 
                        def tempData = Template.findWhere(id:template.id, deleted: false, osCategory: item, baseOs:baseOs, appTemplate:false, myTemplate:false);
                        if(tempData != null) {
                            HashMap<String,String> temp = new HashMap<String,String>(); 
                            temp.put("osCategory", item.name);
                            temp.put("id", tempData.id);      
                            temp.put("zoneId", tempData.id); 
                            temp.put("currency", currency);
                            temp.put("isReady", tempData.isReady);           
                            temp.put("osType", tempData.osType.name);
                            temp.put("templateName", tempData.name);
                            temp.put("cost", tempData.cost);
                            temp.put("description", tempData.description);
                            temp.put("templateReferenceId", tempData.templateReferenceId);
                            temp.put("zone", zoneItem.aliasName);
                            temp.put("referenceZoneId", zoneItem.referenceZoneId);
                            temp.put("myTemplate", tempData.myTemplate);
                            temp.put("appTemplate", tempData.appTemplate);
                            temp.put("imagePath", tempData.path);
                            temp.put("baseOs", tempData.baseOs);
                            temp.put("oneTimeChargeable", tempData.oneTimeChargeable);     
                            zoneTemplateList.add(temp);  
                        }
                    }
                }
                tempItem.put("templates", zoneTemplateList);                                          
            } else if((zoneReferenceId == "null" || zoneReferenceId == null) && (baseOs == "null" || baseOs == null) && (myTemplate != "null" || myTemplate != null) && (appTemplate == "null" || appTemplate == null)) {               
                def zoneResult = Zone.findAll();     
                ArrayList<ArrayList<String>> zoneTemplateList = new ArrayList<ArrayList<String>>();            
                        for(int zone=0; zone < zoneResult.size(); zone++) {
                            def zoneItem = zoneResult[zone]; 
                            for(Iterator k = zoneItem.templates.iterator();k.hasNext();) {
                                def template = k.next(); 
                                def tempData = Template.findWhere(id:template.id, deleted: false, osCategory: item, appTemplate:false, myTemplate:true);
                                if(tempData != null) {
                                    HashMap<String,String> temp = new HashMap<String,String>(); 
                                    temp.put("osCategory", item.name);
                                    temp.put("osCategory", item.id);
                                    temp.put("id", tempData.id);      
                                    temp.put("zoneId", tempData.id);    
                                    temp.put("isReady", tempData.isReady);           
                                    temp.put("osType", tempData.osType.name);
                                    temp.put("templateName", tempData.name);
                                    temp.put("cost", tempData.cost);
                                    temp.put("currency", currency);
                                    temp.put("description", tempData.description);
                                    temp.put("templateReferenceId", tempData.templateReferenceId);
                                    temp.put("zone", zoneItem.aliasName);
                                    temp.put("referenceZoneId", zoneItem.referenceZoneId);
                                    temp.put("myTemplate", tempData.myTemplate);
                                    temp.put("appTemplate", tempData.appTemplate);
                                    temp.put("imagePath", tempData.path);
                                    temp.put("baseOs", tempData.baseOs);
                                    temp.put("oneTimeChargeable", tempData.oneTimeChargeable);     
                                    zoneTemplateList.add(temp);  
                                }
                            }
                        }
                        tempItem.put("templates", zoneTemplateList);                        
                    } else if((zoneReferenceId == "null" || zoneReferenceId == null) && (baseOs == "null" || baseOs == null) && (myTemplate == "null" || myTemplate == null) && (appTemplate != "null" || appTemplate != null)) {    
                        def zoneResult = Zone.findAll();     
                        ArrayList<ArrayList<String>> zoneTemplateList = new ArrayList<ArrayList<String>>();            
                        for(int zone=0; zone < zoneResult.size(); zone++) {
                            def zoneItem = zoneResult[zone]; 
                            for(Iterator k = zoneItem.templates.iterator();k.hasNext();) {
                                def template = k.next(); 
                                def tempData = Template.findWhere(id:template.id, deleted: false, osCategory: item, appTemplate:true, myTemplate:false);
                                if(tempData != null) {
                                    HashMap<String,String> temp = new HashMap<String,String>(); 
                                    temp.put("osCategory", item.name);
                                    temp.put("id", tempData.id);   
                                    temp.put("zoneId", item.id);
                                    temp.put("osType", tempData.osType.name);
                                    temp.put("isReady", tempData.isReady);           
                                    temp.put("templateName", tempData.name);
                                    temp.put("cost", tempData.cost);
                                    temp.put("currency", currency);
                                    temp.put("description", tempData.description);
                                    temp.put("templateReferenceId", tempData.templateReferenceId);
                                    temp.put("zone", zoneItem.aliasName);
                                    temp.put("referenceZoneId", zoneItem.referenceZoneId);
                                    temp.put("myTemplate", tempData.myTemplate);
                                    temp.put("appTemplate", tempData.appTemplate);
                                    temp.put("imagePath", tempData.path);
                                    temp.put("baseOs", tempData.baseOs);
                                    temp.put("oneTimeChargeable", tempData.oneTimeChargeable);     
                                    zoneTemplateList.add(temp);  
                                }
                            }
                        }
                        tempItem.put("templates", zoneTemplateList);                                              
                    } else if((zoneReferenceId != "null" || zoneReferenceId != null) && (baseOs != "null" || baseOs != null) && (myTemplate == "null" || myTemplate == null) && (appTemplate == "null" || appTemplate == null)) {    
                        def zoneResult = Zone.findAllWhere(referenceZoneId : zoneReferenceId); 
                        ArrayList<ArrayList<String>> zoneTemplateList = new ArrayList<ArrayList<String>>();            
                        for(int zone=0; zone < zoneResult.size(); zone++) {
                            def zoneItem = zoneResult[zone]; 
                            for(Iterator k = zoneItem.templates.iterator();k.hasNext();) {
                                def template = k.next(); 
                                def tempData = Template.findWhere(id:template.id, deleted: false, osCategory: item, baseOs:baseOs, appTemplate:false, myTemplate:false);
                                if(tempData != null) {
                                    HashMap<String,String> temp = new HashMap<String,String>(); 
                                    temp.put("osCategory", item.name);
                                    temp.put("id", tempData.id);      
                                    temp.put("zoneId", zoneItem.id);                                          
                                    temp.put("currency", currency);
                                    temp.put("osType", tempData.osType.name);
                                    temp.put("templateName", tempData.name);
                                    temp.put("cost", tempData.cost);
                                    temp.put("isReady", tempData.isReady);           
                                    temp.put("description", tempData.description);
                                    temp.put("templateReferenceId", tempData.templateReferenceId);
                                    temp.put("zone", zoneItem.aliasName);
                                    temp.put("referenceZoneId", zoneItem.referenceZoneId);
                                    temp.put("myTemplate", tempData.myTemplate);
                                    temp.put("appTemplate", tempData.appTemplate);
                                    temp.put("imagePath", tempData.path);
                                    temp.put("baseOs", tempData.baseOs);
                                    temp.put("oneTimeChargeable", tempData.oneTimeChargeable);     
                                    zoneTemplateList.add(temp);  
                                }
                            }
                        }
                        tempItem.put("templates", zoneTemplateList);
                    } else if((zoneReferenceId != "null" || zoneReferenceId != null) && (myTemplate != "null" || myTemplate != null) && (baseOs == "null" || baseOs == null) && (appTemplate == "null" || appTemplate == null)) {    
                        def zoneResult = Zone.findAllWhere(referenceZoneId : zoneReferenceId); 
                        def user = springSecurityService.currentUser
                        ArrayList<ArrayList<String>> zoneTemplateList = new ArrayList<ArrayList<String>>();            
                        for(int zone=0; zone < zoneResult.size(); zone++) {
                            def zoneItem = zoneResult[zone]; 
                            for(Iterator k = zoneItem.templates.iterator();k.hasNext();) {
                                def template = k.next(); 
                                def tempData = Template.findWhere(id:template.id, deleted: false, osCategory: item, appTemplate:false, myTemplate:true, user : user);
                                if(tempData != null) {
                                    HashMap<String,String> temp = new HashMap<String,String>(); 
                                    temp.put("osCategory", item.name);
                                    temp.put("id", tempData.id); 
                                    temp.put("zoneId", zoneItem.id);                                     
                                    temp.put("osType", tempData.osType.name);
                                    temp.put("isReady", tempData.isReady);           
                                    temp.put("templateName", tempData.name);
                                    temp.put("cost", tempData.cost);
                                    temp.put("currency", currency);
                                    temp.put("description", tempData.description);
                                    temp.put("templateReferenceId", tempData.templateReferenceId);
                                    temp.put("zone", zoneItem.aliasName);
                                    temp.put("referenceZoneId", zoneItem.referenceZoneId);
                                    temp.put("myTemplate", tempData.myTemplate);
                                    temp.put("appTemplate", tempData.appTemplate);
                                    temp.put("imagePath", tempData.path);
                                    temp.put("baseOs", tempData.baseOs);
                                    temp.put("oneTimeChargeable", tempData.oneTimeChargeable);     
                                    zoneTemplateList.add(temp);  
                                }
                            }
                        }
                        tempItem.put("templates", zoneTemplateList);
                    } else if((zoneReferenceId != "null" || zoneReferenceId != null) && (appTemplate != "null" || appTemplate != null) && (myTemplate == "null" || myTemplate == null) && (baseOs == "null" || baseOs == null)) {                           
                        def zoneResult = Zone.findAllWhere(referenceZoneId : zoneReferenceId); 
                        ArrayList<ArrayList<String>> zoneTemplateList = new ArrayList<ArrayList<String>>();            
                        for(int zone=0; zone < zoneResult.size(); zone++) {
                            def zoneItem = zoneResult[zone]; 
                            for(Iterator k = zoneItem.templates.iterator();k.hasNext();) {
                                def template = k.next(); 
                                def tempData = Template.findWhere(id:template.id, deleted: false, osCategory: item, appTemplate:true, myTemplate:false);
                                if(tempData != null) {
                                    HashMap<String,String> temp = new HashMap<String,String>(); 
                                    temp.put("osCategory", item.name);
                                    temp.put("id", tempData.id);
                                    temp.put("zoneId", zoneItem.id);                                    
                                    temp.put("currency", currency);
                                    temp.put("isReady", tempData.isReady);           
                                    temp.put("osType", tempData.osType.name);
                                    temp.put("templateName", tempData.name);
                                    temp.put("cost", tempData.cost);
                                    temp.put("description", tempData.description);
                                    temp.put("templateReferenceId", tempData.templateReferenceId);
                                    temp.put("zone", zoneItem.aliasName);
                                    temp.put("referenceZoneId", zoneItem.referenceZoneId);
                                    temp.put("myTemplate", tempData.myTemplate);
                                    temp.put("appTemplate", tempData.appTemplate);
                                    temp.put("imagePath", tempData.path);
                                    temp.put("baseOs", tempData.baseOs);
                                    temp.put("oneTimeChargeable", tempData.oneTimeChargeable);     
                                    zoneTemplateList.add(temp);  
                                }
                            }
                        }
                        tempItem.put("templates", zoneTemplateList);
                    } 
                    templateList.add(tempItem);
                }
                return templateList;
            } catch (Exception ex) {
                ex.printStackTrace(System.err);
                throw ex;
            }
        }
    
    def copyTemplate(String requestBody) {
        def requestData = JSON.parse(requestBody);
        def response = templateServer().copyTemplate(requestData.templateReferenceId, requestData.destZoneId, requestData.sourceZoneId);
        def oldTemp = Template.findByTemplateReferenceId(requestData.templateReferenceId);
        def zone = Zone.findByReferenceZoneId(requestData.destZoneId)
        oldTemp.job = response.jobId;
        oldTemp.addToZones(zone)
        oldTemp.save(flush: true)            
        if (oldTemp.hasErrors()) {
            throw new ValidationException(oldTemp.errors.allErrors);
        }
        ArrayList<ArrayList<String>> temp = new ArrayList<ArrayList<String>>();            
        HashMap<String,String> item = new HashMap<String,String>(); 
        item.put("result", GeneralConstants.RESULT_SUCCESS);
        item.put("jobId", response.jobId);
        temp.add(item);
           
        return temp;
    }
    
    def create(String requestBody) {
        try {    
            def zoneid;
            def templateType = "";
            // convert string to json object
            def requestData = JSON.parse(requestBody)
            def urlPath;
            if(requestData.appTemplate == true || requestData.appTemplate == 'true') {
                urlPath = requestData.path;
                templateType = "App Template"
            } else {
                urlPath = "";
                templateType = "OS Template"
            }
            Template newTemplate  = new Template();            
            newTemplate.name = requestData.name;
            newTemplate.description = requestData.description;
            newTemplate.url = requestData.url;
            newTemplate.format = requestData.format;
            newTemplate.hypervisor = requestData.hypervisor
            newTemplate.extractable = requestData.extractable;                    
            newTemplate.featured = requestData.featured;
            newTemplate.isPublic = requestData.isPublic;
            newTemplate.detailedDescription = requestData.detailedDesc;
            newTemplate.osReferenceURL = requestData.referenceURL;
            newTemplate.myTemplate = requestData.myTemplate;    
            newTemplate.appTemplate = requestData.appTemplate; 
            newTemplate.isReady = false; 
            newTemplate.path = urlPath;     
            newTemplate.architecture = requestData.architecture;                 
            newTemplate.minimumCpu = requestData.minCpu; 
            newTemplate.minimumRam = requestData.minRam; 
            newTemplate.oneTimeChargeable = requestData.oneTimeChargeable; 
            newTemplate.status = "Downloading"; 
            if(requestData.cost == "" || requestData.cost == null || requestData.cost == "null") {
                newTemplate.cost = 0;  
            } else {
               newTemplate.cost = requestData.cost;   
            }
            newTemplate.passwordEnabled = requestData.passwordEnabled;
            def osType = OsType.findByReferenceOsTypeId(requestData.osType);
            newTemplate.osType = osType;
            newTemplate.osCategory = osType.osCategory;  
            newTemplate.baseOs = osType.osCategory.baseOs; 
            HashMap<String,String> optional = new HashMap<String,String>();           
            optional.put("isfeatured", new Boolean(newTemplate.featured).toString());
            optional.put("isextractable", new Boolean(newTemplate.extractable).toString());
            optional.put("ispublic", new Boolean(newTemplate.isPublic).toString());
            optional.put("passwordenabled", new Boolean(newTemplate.passwordEnabled).toString());
            if(requestData.zone == "-1") {
                zoneid = "-1";
                def result = Zone.findAllWhere(networkType: GeneralConstants.ZONE_TYPE_BASIC);                   
                for(int i=0; i < result.size(); i++) {
                    def item = result[i]; 
                    newTemplate.addToZones(item);
                }
            } else {
                def zone = Zone.findByReferenceZoneId(requestData.zone);
                zoneid = requestData.zone;
                newTemplate.addToZones(zone);                
            }
            def response = templateServer().registerTemplate(newTemplate.description, newTemplate.format,
            requestData.hypervisor, newTemplate.name, requestData.osType, newTemplate.url, zoneid, optional)
            newTemplate.templateReferenceId = response.templateId; 
            newTemplate.deleted = false;  
            //save new template
            newTemplate.save(flush: true);    
            log.info("${templateType} created successfully, template id: ${newTemplate.templateReferenceId}")   
            if (newTemplate.hasErrors()) {
                throw new ValidationException(newTemplate.errors.allErrors);
            }
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }        
    }
    
    def update(String requestBody) {        
        try {     
            
            // convert string to json object
            def requestData = JSON.parse(requestBody)
//            Console.print("ex" + requestData.extractable)
            def oldTemplate = Template.get(requestData.id);
            
            HashMap<String,String> optional = new HashMap<String,String>();     
            optional.put("name", new String(requestData.name));
            optional.put("displaytext", new String(requestData.description));
            optional.put("isfeatured",  new Boolean(requestData.featured).toString());
            optional.put("isextractable", new Boolean(requestData.extractable).toString());
            optional.put("ispublic", new Boolean(requestData.isPublic).toString());
            optional.put("passwordenabled", new Boolean(requestData.passwordEnabled).toString());
            
            def response = templateServer().updateTemplate(oldTemplate.templateReferenceId, optional)            
            oldTemplate.name = response.name;
            oldTemplate.description = response.displayText;
            oldTemplate.extractable = requestData.extractable;                  
            oldTemplate.featured = response.isFeatured;
            oldTemplate.isPublic = response.isPublic;
            oldTemplate.passwordEnabled = requestData.passwordEnabled;
            oldTemplate.cost = requestData.cost;
            oldTemplate.detailedDescription = requestData.detailedDescription;
            if(requestData.appTempImageUrl) {
                oldTemplate.path = requestData.appTempImageUrl;
            }  
            if(requestData.tempType) {
                if(requestData.tempType == "APP") {
                    oldTemplate.appTemplate = true;
                } else {
                    oldTemplate.appTemplate = false;
                }                
            }              
            oldTemplate.architecture = requestData.architecture;                 
            oldTemplate.oneTimeChargeable = requestData.oneTimeChargeable; 
            oldTemplate.osReferenceURL = requestData.referenceURL;
            if(requestData.minCpu) {
                oldTemplate.minimumCpu = requestData.minCpu; 
            }
            if(requestData.minRam) {
                oldTemplate.minimumRam = requestData.minRam; 
            }
            //save template
            oldTemplate.save(flush: true);  
            def templateType = "";
            if(oldTemplate.appTemplate == "true") {
                templateType = "App Template"
            } else {
                templateType = "OS Template"
            }
            log.info("${templateType} : ${oldTemplate.templateReferenceId} updated successfully")   
            if (oldTemplate.hasErrors()) {
                throw new ValidationException(oldTemplate.errors.allErrors);
            }
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
    }
    
    def job(String requestBody) {
        try {
            
            // convert string to json object
            def requestData = JSON.parse(requestBody)
            ArrayList<ArrayList<String>> templateResult = new ArrayList<ArrayList<String>>();  
            Template template = Template.findByJob(requestData.jobId);
            def jobResponse = templateServer().templateJobResult(requestData.jobId);    
            if(jobResponse.asychronousJobStatus == "0") {
                template.templateReferenceId = jobResponse.asychronousJobInstanceId;
                template.save(flush: true)  
                log.info("Job : ${requestData.jobId} for template : ${template.templateReferenceId} is pending")   
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.JOB_RESULT_PENDING);
                item.put("jobId", jobResponse.asychronousJobInstanceId);
                templateResult.add(item);
            } else if(jobResponse.asychronousJobStatus == "1") {                        
                    HashMap<String,String> templateItem = new HashMap<String,String>();                
                    templateItem.put("referenceId", template.templateReferenceId);
                    templateItem.put("jobResult", GeneralConstants.RESULT_SUCCESS);
                    templateItem.put("name", template.name);
                    templateItem.put("description", template.description);
                    templateItem.put("format", template.format);
                    templateItem.put("hypervisor", template.hypervisor);
                    templateItem.put("featured", template.featured);
                    templateItem.put("isPublic", template.isPublic);
                    templateItem.put("passwordEnabled", template.passwordEnabled);
                    templateItem.put("zoneName", template.zone.aliasName);
                    templateItem.put("osType", template.osType.name);
                    templateItem.put("osTypeReferenceId", template.osType.referenceOsTypeId);
                    templateItem.put("extractable", template.extractable);                                 
                    templateResult.add(templateItem);   
                    log.info("Job : ${requestData.jobId} for template : ${template.templateReferenceId} is success")   
            } else if(jobResponse.asychronousJobStatus == "2") {
                template.deleted = true;  
                template.save(flush: true);  
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.RESULT_FAILURE);
                item.put("message", jobResponse.errorText);
                templateResult.add(item);      
                log.info("Job : ${requestData.jobId} for template : ${template.templateReferenceId} is failed")   
            }
            return templateResult; 
            
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
            
    } 
    
    def delete(String requestBody) {
        try {              
            def requestData = JSON.parse(requestBody)
            ArrayList<ArrayList<String>> temp = new ArrayList<ArrayList<String>>();         
            Template template = Template.get(requestData.tempId);            
            def response = templateServer().deleteTemplate(template.templateReferenceId, null);          
            template.job = response.jobId;
            template.save(flush: true)            
            if (template.hasErrors()) {
                throw new ValidationException(template.errors.allErrors);
            }                  
            HashMap<String,String> item = new HashMap<String,String>(); 
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            item.put("jobId", response.jobId);
            temp.add(item);
            log.info("Template : ${requestData.tempId} deleted and returned job")   
            return temp;          
            } catch(Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }               
    }
    
    def deleteJob(String requestBody) {
        try {            
            // convert string to json object
            def requestData = JSON.parse(requestBody)
            ArrayList<ArrayList<String>> templateResult = new ArrayList<ArrayList<String>>();  
            Template template = Template.findByJob(requestData.jobId);
            def jobResponse = templateServer().templateJobResult(requestData.jobId);    
            if(jobResponse.asychronousJobStatus == "0") {
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.JOB_RESULT_PENDING);
                item.put("jobId", jobResponse.asychronousJobInstanceId);
                templateResult.add(item);
                log.info("Job for template : ${template.templateReferenceId} is pending")   
            } else if(jobResponse.asychronousJobStatus == "1") {      
                template.deleted = true;
                template.save(flush: true);
                HashMap<String,String> templateItem = new HashMap<String,String>();         
                templateItem.put("jobResult", GeneralConstants.RESULT_SUCCESS);                  
                templateResult.add(templateItem); 
                log.info("Job for template : ${template.templateReferenceId} is success") 
            } else if(jobResponse.asychronousJobStatus == "2") {
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.RESULT_FAILURE);
                item.put("message", jobResponse.errorText);
                templateResult.add(item); 
                log.info("Job for template : ${template.templateReferenceId} is failed") 
            }
            return templateResult;                         
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }             
    }        
}   
