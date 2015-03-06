package com.assistanz.fogpanel

import grails.transaction.Transactional
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsWebRequest
import org.codehaus.groovy.grails.web.util.WebUtils
import grails.transaction.Transactional;
import sun.misc.BASE64Encoder;
import java.util.Random;
import org.codehaus.groovy.grails.commons.ApplicationHolder;
import grails.converters.deep.JSON
import org.springframework.web.client.RestTemplate

@Transactional
class CloudEngineService {
    
    def springSecurityService; 

    def list() {
        
        ArrayList<ArrayList<String>> cloudEngineList = new ArrayList<ArrayList<String>>();  
        
        def list = CloudEngine.withCriteria {
            eq('deleted', false)            
        }
        
        for (def cloudEngine : list) { 
            
            HashMap item = new HashMap();
            item.put("id", cloudEngine.id);
            item.put("engineId", cloudEngine.engineId);
            item.put("name", cloudEngine.name);
            item.put("type", cloudEngine.type);
            item.put("url", cloudEngine.url);
            item.put("status", (cloudEngine?.status?.equals(true)) ? "Yes" : "No");
            
            cloudEngineList.add(item);      
                                    
        }
        return cloudEngineList
        
    }
    def create(requestData) {
        
        
        try {
            
            
            ArrayList<ArrayList<String>> responseText = new ArrayList<ArrayList<String>>();
            def findTypeAlreadyExist = CloudEngine.findByTypeAndDeleted(requestData.engineType,false)
            if(findTypeAlreadyExist) {
                   
                HashMap<String,String> item = new HashMap<String,String>(); 
                item.put("error", "Already Exist");
                responseText.add(item)
                
            } else {
                def cloudEngine = new CloudEngine();
                cloudEngine.name = requestData.name;
                cloudEngine.type = requestData.engineType;
                cloudEngine.url = requestData.cloudEngineUrl;

                // cloudengine id tobe refactored with 
                cloudEngine.engineId = requestData.engineType
                cloudEngine.deleted = false
                cloudEngine.save(flush:true);
                if (!cloudEngine.save()) {
                    cloudEngine.errors.allErrors.each { println it }
                }
                 // response message
                HashMap<String,String> item = new HashMap<String,String>(); 
                item.put("result", GeneralConstants.RESULT_SUCCESS);
                responseText.add(item)

                
            }
            
            return responseText
            // saving CloudEngine;
            
        } catch (Exception ex) {
               throw ex;
        }

        
        
    }
    

    def getCloudEngineDetails(type) {
        
        try {
            def cloudEngine = CloudEngine.findById(type)
            
            
            ArrayList cloudEngineDetailsList = new ArrayList()
            
            ArrayList engineTypeList = new ArrayList()
            HashMap item = new HashMap();
            item.put("id", cloudEngine.id);
            item.put("engineId", cloudEngine.engineId);
            item.put("name", cloudEngine.name);
            
                HashMap item1 = new HashMap();
                item1.put("id", "FOG-OS");
                item1.put("name", "FOG-OS");
                engineTypeList.add(item1)

                // item 2
                HashMap item2 = new HashMap();
                item2.put("id", "FOG-AWS");
                item2.put("name", "FOG-AWS");
                engineTypeList.add(item2)

                // item 3
                HashMap item3 = new HashMap();
                item3.put("id", "FOG-CS");
                item3.put("name", "FOG-CS");
                engineTypeList.add(item3)
            
            
                
            item.put("type", engineTypeList);
            item.put("url", cloudEngine.url);
            item.put("status", (cloudEngine?.status?.equals(true)) ? "Yes" : "No");
            
            cloudEngineDetailsList.add(item)
            
            return cloudEngineDetailsList
            
        } catch(Exception ex) {
            throw ex
        }
    }
    
    def updateCloudEngine(requestData) {
        
        try {
            
            ArrayList<ArrayList<String>> responseText = new ArrayList<ArrayList<String>>();
            def currentCloudEngine = CloudEngine.findById(requestData.id)
            def alreadyTypeExist = CloudEngine.findByTypeAndDeleted(requestData.type,false)
            if(currentCloudEngine.type == requestData.type) {
                currentCloudEngine.name = requestData.name
                currentCloudEngine.url = requestData.url
                currentCloudEngine.type = requestData.type
                currentCloudEngine.engineId = requestData.type
                currentCloudEngine.save(flush:true)
                HashMap<String,String> item = new HashMap<String,String>(); 
                item.put("result", "OK");
                responseText.add(item)
            } else if(alreadyTypeExist) {
                HashMap<String,String> item = new HashMap<String,String>(); 
                item.put("error", "Already Exist");
                responseText.add(item)
            } else {
                def cloudEngine = CloudEngine.findById(requestData.id)
                cloudEngine.name = requestData.name
                cloudEngine.url = requestData.url
                cloudEngine.type = requestData.type
                cloudEngine.engineId = cloudEngine.type
                cloudEngine.save(flush:true)
                HashMap<String,String> item = new HashMap<String,String>(); 
                item.put("result", "OK");
                responseText.add(item)
            }
            return responseText
//            
        } catch(Exception  ex) {
            throw ex
        }
        
    }
    
    def deleteCloudEngine(id) {
        try {
            def cloudEngine = CloudEngine.findById(id)
            ArrayList<ArrayList<String>> responseText = new ArrayList<ArrayList<String>>();
            HashMap<String,String> item = new HashMap<String,String>();
            if(cloudEngine) {
                cloudEngine.deleted = true
                cloudEngine.save(flush:true)
                item.put("result", GeneralConstants.RESULT_SUCCESS);
            } else {
                item.put("error", "ERROR");
            }
            responseText.add(item);
            return responseText
            
        }catch (Exception ex) {
            throw ex
        }
       
    }

    def cloudEngineDetail(engineId) {
        try {
            
            ArrayList<ArrayList<String>> cloudEngineInfo = new ArrayList<ArrayList<String>>();        
            HashMap<String,String> item = new HashMap<String,String>();
            
            def cloudEngine = CloudEngine.get(engineId);
            
            BASE64Encoder encoder = new BASE64Encoder();
            byte[] salt = new byte[8];
            Random rand = new Random((new Date()).getTime());
            rand.nextBytes(salt);
            
            def cloudEngineId = cloudEngine.engineId;
            
            item.put("name", cloudEngine.name)
            item.put("url", cloudEngine.url)
            item.put("type", cloudEngine.type)
            item.put("status", cloudEngine.status)
            item.put("engineId", cloudEngine.engineId)
            item.put("cloudEngineId", encoder.encode(salt) + encoder.encode(cloudEngineId.getBytes()))
            cloudEngineInfo.add(item)
            
            return cloudEngineInfo;
            
        } catch (Exception ex) {
               throw ex;
        }
    }
    
    def getSessionDetails() {
        def user = springSecurityService.currentUser
        
        ArrayList<ArrayList<String>> sessionInfo = new ArrayList<ArrayList<String>>();
        
        HashMap item = new HashMap();
        
         //getting request object
        GrailsWebRequest webUtils = WebUtils.retrieveGrailsWebRequest()
        def httpRequest = webUtils.getCurrentRequest()  
        
        //getting session
        def session = httpRequest.getSession(false)
        String password = (String)session.getAttribute("password");
        
        def sessionId = session.getId()
        def userName = user.username
        println("sessionId: "+sessionId)
        
        BASE64Encoder encoder = new BASE64Encoder();
        byte[] salt = new byte[8];
        Random rand = new Random((new Date()).getTime());
        rand.nextBytes(salt);
        
        
        def encryptedSessionId = encoder.encode(salt) + encoder.encode(sessionId.getBytes())
        def encryptedUserName = encoder.encode(salt) + encoder.encode(userName.getBytes())
        def encryptedPassword = encoder.encode(salt) + encoder.encode(password.getBytes())
        println("session encrypt: "+encoder.encode(salt) + encoder.encode(sessionId.getBytes()))
        
//        def openstackUrl = ApplicationHolder.getApplication().config.openstack.applicationUrl
        
        def cloudEngine = CloudEngine.findByType("FOG-OS");
        
        def openstackUrl = cloudEngine.url;
        
        println("cloudEngine.url: "+cloudEngine.url)
         
        item.put("sessionId", encryptedSessionId)
        item.put("openstackUrl", openstackUrl)
        item.put("userName", encryptedUserName)
        item.put("password", encryptedPassword)
   
        sessionInfo.add(item);
        
        return sessionInfo;
    }
    
    def getUserDetails (sessionId) {
        try {
            
            ArrayList<ArrayList<String>> userDetail = new ArrayList<ArrayList<String>>();
        
            HashMap item = new HashMap();
            
            PersistentSession  persistentSession = PersistentSession.get(sessionId)
            String encodedUserDetail = persistentSession.data;
            
            def decodedUserDetail = new String(encodedUserDetail.decodeBase64())
            
            println("decoded user detail:"+decodedUserDetail)
            def requestData = JSON.parse(decodedUserDetail)
             println("requestData user detail:"+requestData.data.uid)
             
            def userName = requestData.data.uid
            //encode username
            BASE64Encoder encoder = new BASE64Encoder();
            byte[] salt = new byte[8];
            Random rand = new Random((new Date()).getTime());
            rand.nextBytes(salt);
            
            def encryptedUserName = encoder.encode(salt) + encoder.encode(userName.getBytes())
            
            item.put("userName", encryptedUserName)
            userDetail.add(item);
            
            return userDetail
        } catch (Exception ex){
                throw ex;
        }
    }
    
    def verifyCloudEngineUrl(cloudEngineId) {
        try {
            
            ArrayList<ArrayList<String>> cloudEngineInfo = new ArrayList<ArrayList<String>>();        
            HashMap<String,String> item = new HashMap<String,String>();
            
            def cloudEngine = CloudEngine.get(cloudEngineId);
            
            def engineId = cloudEngine.engineId;
            
            BASE64Encoder encoder = new BASE64Encoder();
            byte[] salt = new byte[8];
            Random rand = new Random((new Date()).getTime());
            rand.nextBytes(salt);
            
            def encryptedEngineId = encoder.encode(salt) + encoder.encode(engineId.getBytes());
            
            def applicationUrl = cloudEngine.url + "/login/verifyLink?engineId="+encryptedEngineId;
            
            println("applicationUrl: "+applicationUrl)
            
            RestTemplate rest = new RestTemplate();
            String response = rest.getForObject(applicationUrl,String.class);
            
            def requestData = JSON.parse(response);
            
            return requestData;
            
            
        } catch (Exception ex){
                throw ex;
        }
    }
    
    def changeCloudEngineStatus(cloudEngineId) {
        
        try {
            
            ArrayList<ArrayList<String>> cloudEngineInfo = new ArrayList<ArrayList<String>>();        
            HashMap<String,String> item = new HashMap<String,String>();
            println("subdsfs")
            
            def cloudEngine = CloudEngine.get(cloudEngineId);
            cloudEngine.status = true
            cloudEngine.save(flush: true);
            
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            cloudEngineInfo.add(item);
            
            return cloudEngineInfo;
    
        } catch (Exception ex){
                throw ex;
        }
    }
    
      

}
