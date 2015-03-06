package com.assistanz.fogpanel

import groovy.json.JsonBuilder
import grails.converters.deep.JSON
import java.util.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import com.assistanz.fogpanel.NotificationService
import com.assistanz.fogpanel.GeneralConstants;
import org.apache.commons.logging.LogFactory;
import grails.transaction.Transactional
import org.codehaus.groovy.grails.commons.ApplicationHolder

@Transactional
class UserService {
    
    private static final log = LogFactory.getLog(this)
    def springSecurityService;
    NotificationService notificationService
    def list() {
        try {
            def user = springSecurityService.currentUser
            def role = springSecurityService.getPrincipal().getAuthorities()    
            if(role.iterator().next() == "ROLE_ADMIN" ) {
                def result = User.findAll();                       
                ArrayList<ArrayList<String>> userList = new ArrayList<ArrayList<String>>();            
                for(int i=0; i < result.size(); i++) {
                    def item = result[i]; 
                    HashMap<String,String> userItem = new HashMap<String,String>();
                        userItem.put("id", item.id);
                        userItem.put("userName", item.username);
                        userItem.put("accountName", item.account.userName);
                        userItem.put("apiKey", item.apiKey);
                        userItem.put("secretKey", item.secretKey);
                        userList.add(userItem);
                }
                return userList
            } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {
                throw new Exception("access denied");
            }
         } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
    }
    
    def getCurrentUser() {
        try {
             def result = springSecurityService.currentUser 
            ArrayList<ArrayList<String>> user = new ArrayList<ArrayList<String>>();  
            HashMap<String,String> userItem = new HashMap<String,String>();
                userItem.put("id", result.id);
                userItem.put("userName", result.username);
                userItem.put("accountName", result.account.userName);
                userItem.put("apiKey", result.apiKey);
                userItem.put("secretKey", result.secretKey);
                user.add(userItem)
            return user;                
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }    
    }
    
    def resetPassword(String requestBody) {
        try {
            def requestData = JSON.parse(requestBody)    
            def user = springSecurityService.currentUser 
            def oldPassword = springSecurityService.encodePassword(requestData.oldPassword)
            
            
            if(oldPassword == user.password) {    
                if(requestData.newPassword == requestData.comPassword) {                   
                    user.password = requestData.newPassword;
                    def account = user.account
                    account.password = requestData.newPassword;
                    account.save(flush: true);
                    user.save(flush: true);
                    log.info("Password reset for user:${user.id}");                    
                    Map tempalteMap = notificationService.getDefaultMailTemplateMap()                             
                    notificationService.send(user.username.toString(), "accountPasswordReset.subject.ftl", tempalteMap, "accountPasswordReset.ftl")                                                                  
                    return [GeneralConstants.RESULT_SUCCESS]
                } else {
                    return ["PASSWORD DONOT MATCH"]
                }
            } else {
                return ["OLD PASSWORD DONOT MATCH"]
            }
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }  
    }
    
     def unLock() {
      
        Config unLockTimeValue =  Config.findByName(Config.ACCOUNT_UNLOCK_TIME); 
        if(unLockTimeValue.value != "") {
            def time = Integer.parseInt(unLockTimeValue.value)
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MINUTE, -time);
            Date date = cal.getTime(); 
            def unLockTime = new Timestamp(date.getTime())
            def userQuery = EventLogIpAddress.createCriteria()
            def userResult;
            userResult = userQuery.list {
                eq("isLocked", true)
                and {
                 le("lockTime", unLockTime)
                }
            }
            for(int i=0; i < userResult.size(); i++) {
                def item = userResult[i]; 
                item.isLocked = false
                item.lockTime = null
                item.failureCount = 0
                item.save(flush: true)                
                
                Map tempalteMap = notificationService.getDefaultMailTemplateMap()        
                tempalteMap.put("user", item.user)
                notificationService.send(item.user.username.toString(), "loginUnlock.subject.ftl", tempalteMap, "loginUnlock.ftl")                
            }
        }
        
     }
     
    def confirmAccount(String token) {
        try {             
            Date date = new Date()
            def time = new Timestamp(date.getTime())
            
            def user = User.findByToken(token)
            def account = user.account
            user.accountLocked = false
            user.account.activationDate = time
            user.save(flush: true)
            account.status = "ACTIVE"
            account.save(flush: true)
            return "OK"
            
         } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }  
        
    }
    
}
