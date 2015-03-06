package com.assistanz.fogpanel

import groovy.json.JsonBuilder
import grails.converters.deep.JSON
import java.util.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import com.assistanz.fogpanel.NotificationService
import com.assistanz.cloud.cloudstack.CloudStackServer
import com.assistanz.cloud.cloudstack.users.CSUserService
import com.assistanz.cloud.cloudstack.users.UserResponse
import com.assistanz.fogpanel.GeneralConstants;
import org.apache.commons.logging.LogFactory;
import grails.transaction.Transactional
import org.codehaus.groovy.grails.commons.ApplicationHolder
import com.assistanz.fogpanel.PullUserJob;

@Transactional
class UserService {
    
    private static final log = LogFactory.getLog(this)
    def springSecurityService;
    NotificationService notificationService
    def userServer() {
        
        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret

        CloudStackServer server =
                 new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);

        CSUserService csUserService = new CSUserService(server);
        
        return csUserService;
        
    }
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
                        userItem.put("accountName", item.account.accountIdentifier);
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
                userItem.put("accountName", result.account.accountIdentifier);
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
            def role = springSecurityService.getPrincipal().getAuthorities()
            
            if(oldPassword == user.password) {    
                if(requestData.newPassword == requestData.comPassword) { 
                    if(role.iterator().next() == "ROLE_ADMIN" ) {                        
                    } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") { 
                        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
                        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
                        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret

                        CloudStackServer server = new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey)
                        CSUserService csUserService = new CSUserService(server);
                        
                        // forming key value optional pairs
                        HashMap<String,String> userParams = new HashMap<String,String>();
                        userParams.put("password", requestData.newPassword)

                        csUserService.updateUser(user.uuid, userParams);
                    }                                                            
                    user.password = requestData.newPassword;
                    user.save(flush: true);
                    log.info("Password reset for user:${user.id}");                    
                    Map tempalteMap = notificationService.getDefaultMailTemplateMap()                             
                    notificationService.send(user.account.email.toString(), "accountPasswordReset.subject.ftl", tempalteMap, "accountPasswordReset.ftl")                                                                  
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
                notificationService.send(item.user.account.email.toString(), "loginUnlock.subject.ftl", tempalteMap, "loginUnlock.ftl")                
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
    def pullUserFromCloudStackJobStart() {
        
        PullUserJob.triggerNow([id:"user"])
        return "OK"
        
    }
    def pullUserFromCloudStack(def jobid){
        
        def job = AsynchronousJobs.get(jobid)
        try { 
                        
            job.status = JobStatus.valueOf("RUNNING")
            job.startedAt = new Date()
            job.save(flush: true)
            
            HashMap<String,String> optional = new HashMap<String,String>();
            optional.put("listall", "true");

            def response = userServer().listUsers(optional);  
         
                
            for(Iterator<UserResponse> iter = response.listUsers.iterator(); iter.hasNext();) {
                
                def data = iter.next();
                
                
                User user = User.findByUuid(data.id);
                
                if(!user) {
                    
                    user = new User();
                    user.uuid = data.id;
                    user.account = Account.findByUuid(data.accountId);
                    user.apiKey = data.apiKey;
                    user.domain = Domain.findByReferenceId(data.domainId);
                    user.username = data.userName;
                    user.email = data.email;
                    user.firstName = data.firstName;
                    user.lastName = data.lastName;
                    
                    // password will be dummy 
                    user.password = data.userName;
                    
                    user.secretKey = data.secretKey;
                    user.failureCount = 0;
                    user.token = "0";
                   
                   
                    user.enabled = true;
                    user.accountExpired = false;
                    user.accountLocked = false;
                    user.passwordExpired = false;
                    
                    if (!user.save(flush: true)) {
                        user.errors.allErrors.each { println it }
                    } else {
                        
                        def role = Role.findByAuthority("ROLE_PAID_USER");
                        UserRole userRole = new UserRole();
                        userRole.user = user;
                        userRole.role = role;
                        userRole.save(flush:true, failOnError:true);  
                    }
                    
                } else {
                    
                    user.username = data.userName;
                    user.account.accountIdentifier = data.userName;
                    user.email = data.email;
                    user.firstName = data.firstName;
                    user.lastName = data.lastName;
                    if (!user.save(flush: true)) {
                        user.errors.allErrors.each { println it }
                    }
                    
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

    
}
