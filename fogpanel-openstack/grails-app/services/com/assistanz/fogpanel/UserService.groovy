package com.assistanz.fogpanel

import groovy.json.JsonBuilder
import grails.converters.deep.JSON
import grails.plugin.springsecurity.SpringSecurityService
import java.util.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import com.assistanz.fogpanel.NotificationService
import com.assistanz.openstack.OpenStackServer
import com.assistanz.cloud.config.ConfigLoader
import org.openstack4j.api.OSClient;
import org.openstack4j.model.identity.User as OSUser;
import com.assistanz.fogpanel.GeneralConstants;
import org.apache.commons.logging.LogFactory;
import grails.transaction.Transactional
import org.codehaus.groovy.grails.commons.ApplicationHolder
import org.openstack4j.model.identity.User as OpenstackUser;
import org.openstack4j.model.identity.Role as OpenstackRole;
import org.openstack4j.model.compute.Keypair;
import org.openstack4j.api.Builders;
import com.fogpanel.openstackuserapi.HttpConnector

@Transactional
class UserService {
    
    private static final log = LogFactory.getLog(this)
    def springSecurityService;
    NotificationService notificationService
    OpenStackAuthService openStackAuthService
    
    def list() {
        try {
            def user = springSecurityService.currentUser
            def role = springSecurityService.getPrincipal().getAuthorities()    
            if(role.iterator().next().toString().equals("ROLE_ADMIN") ) {
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
            
            HttpConnector userAPIConnector = new HttpConnector()
            
            ConfigLoader configLoader = ConfigLoader.getInstance();

            Properties props = configLoader.getProperties();

            OpenStackServer server = new OpenStackServer(props.get(Config.OPENSTACK_ENDPOINT_URL), props.get(Config.OPENSTACK_ADMIN_UUID), props.get(Config.OPENSTACK_ADMIN_PASSWORD), null);

            OSClient os = server.authenticate();        
                                   
            def requestData = JSON.parse(requestBody)    
            def user = springSecurityService.currentUser 
            def oldPassword = springSecurityService.encodePassword(requestData.oldPassword)
                        
            if(oldPassword == user.password) {    
                if(requestData.newPassword == requestData.comPassword) {                   
                    
                    String data = '{ "user": {"password": "'+ requestData.newPassword +'"}}'
                    
                    def response = userAPIConnector.simplePatch(new URL(props.get(Config.OPENSTACK_ENDPOINT_URL)+"/users/"+user.uuid), data, os.getToken().getId())
                    
                    if(response == "Success") {
                        user.password = requestData.newPassword;
                        user.save(flush: true);
                        
                        log.info("Password reset for user:${user.id}");                    
                        Map tempalteMap = notificationService.getDefaultMailTemplateMap()                             
                        notificationService.send(user.account.email.toString(), "accountPasswordReset.subject.ftl", tempalteMap, "accountPasswordReset.ftl")                 
                        
                        return [GeneralConstants.RESULT_SUCCESS]
                        
                    } else {
                        return response     
                    }                 
                                                                 
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
    
    def userSetupVerfication() {
        try {             
             
            def user = springSecurityService.currentUser;
            
            ArrayList<ArrayList<String>> setupEmptyList = new ArrayList<ArrayList<String>>();
            HashMap emptyItem = new HashMap();
            
            def openstackSetup = "true";
            
            def networkList = Network.findAllWhere(user: user, deleted: false);
            emptyItem.put("isNetworkEmpty", networkList ? "true" : "false");   
            
            def securityGroupList = SecurityGroup.findAllWhere(user: user, deleted: false);
            emptyItem.put("isSecurityGroupEmpty", securityGroupList ? "true" : "false"); 
            
            if(!networkList || !securityGroupList) {
                openstackSetup = "false";
            } 
            
            emptyItem.put("openstackSetup",openstackSetup);
            
            setupEmptyList.add(emptyItem);
            
            return setupEmptyList;
            
        } catch (Exception ex) {
            throw ex;
        }  
    }
    def pullUserFromOpenStackJobStart() {
        
        PullUserJob.triggerNow([id:"user"])
        return "OK"
        
    }
    def pullUserFromOpenStack(def jobid){
        
        def job = AsynchronousJobs.get(jobid)
        try { 
                        
            job.status = JobStatus.valueOf("RUNNING")
            job.startedAt = new Date()
            job.save(flush: true)
                
            ConfigLoader configLoader = ConfigLoader.getInstance();
           
            Properties props = configLoader.getProperties();

            OpenStackServer server = new OpenStackServer(props.get(Config.OPENSTACK_ENDPOINT_URL), props.get(Config.OPENSTACK_ADMIN_UUID), props.get(Config.OPENSTACK_ADMIN_PASSWORD), null);

            OSClient os = server.authenticate();
            
            List<? extends OSUser> users = os.identity().users().list();
            
            for (OSUser openStackUser : users) {
                 
                //                println("openStackUser"+openStackUser.toString());
                
                User user = User.findByUuid(openStackUser.id);
                
                if(!user) {
                    user = new User();
                    user.uuid = openStackUser.id;
                    user.account = Account.findByUuid(openStackUser.tenantId)
                    //user.apiKey = data.apiKey;
                    user.domain = Domain.findByReferenceId(openStackUser.domainId)
                    user.username = openStackUser.name;
                    user.email = openStackUser.email;
                    user.firstName = openStackUser.name;
                    user.lastName = openStackUser.name;
                    
                    // password will be dummy 
                    user.password = openStackUser.name;
                    
                    //user.secretKey = data.secretKey;
                    user.failureCount = 0;
                    user.token = "0";
                   
                   
                    user.enabled = openStackUser.enabled;
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
                    
                    user.username = openStackUser.name;
                    user.account.accountIdentifier = openStackUser.name;
                    user.email = openStackUser.email;
                    user.firstName = openStackUser.name;
                    user.lastName = openStackUser.name;
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
    def getUsersByAccountId(String accountId) {
        
        def userList = User.withCriteria {
            if(accountId != null && !accountId.empty) {
                
                eq("account", Account.findById(accountId))
            }
        }
        ArrayList<ArrayList<String>> userArrayList = new ArrayList<ArrayList<String>>(); 
        
        for(int i = 0; i < userList.size(); i++) {
            
            def currentItem = userList[i]
            HashMap<String,String> item = new HashMap<String,String>();                
            item.put("id", currentItem.id);
            item.put("username", currentItem.username);                
            item.put("email", currentItem.email);
            userArrayList.add(item)
        } 
        
        return userArrayList;
        
    }
    
    def create(requestData) {
        
        def applicationUrl = ApplicationHolder.getApplication().config.openstack.applicationUrl
        //save default domain
        def defaultDomain = Domain.findByIsDefault(true)
        ConfigLoader configLoader = ConfigLoader.getInstance();
        
        Properties props = configLoader.getProperties();
        
        OpenStackServer server = new OpenStackServer(props.get(Config.OPENSTACK_ENDPOINT_URL), props.get(Config.OPENSTACK_ADMIN_UUID), props.get(Config.OPENSTACK_ADMIN_PASSWORD), null);
        
        OSClient os = server.authenticate();
        def hashtext = Util.getToken(requestData.userName)
       
        
        try { 
            
            def account = Account.findById(requestData.accountId)
            Calendar calendar = Calendar.getInstance(); 
            Config linkActiveTime =  Config.findByName(Config.LINK_ACTIVE_TIME);
            calendar.add(Calendar.HOUR, Integer.parseInt(linkActiveTime.value));
            User user = new User();
            user.account = account;
            user.token = hashtext;
            user.username = requestData.userName;  
            user.failureCount = 0;
            user.password = requestData.password; 
            user.enabled = true;
            user.tokenExpiryDate = calendar.getTime() 
            user.accountExpired = false;
            user.accountLocked = false;
            user.passwordExpired = false;
            user.firstName = requestData.userName
            user.lastName = requestData.userName
            user.email = requestData.email
                    
            // save default domain
            user.domain = defaultDomain;
            
            if (user.validate()) {
                
                //create user to OpenStack
                //def userPassword = springSecurityService.encodePassword(requestData.password);
                                                
                OpenstackUser openstackUser = os.identity().users().create(Builders.user().tenantId(account.uuid)
                    .domainId(defaultDomain.referenceId) 
                    .name(requestData.userName)
                    .password(requestData.password).email(requestData.email).enabled(true).build());
                        
                def openStackRoleName = "_member_";
                OpenstackRole currentRole = os.identity().roles().getByName(openStackRoleName); 
                def roleId = currentRole.getId();
                        
                //Assign role to current user
                os.identity().roles().addUserRole(account.uuid, openstackUser.getId(), roleId);
                        
                user.uuid = openstackUser.getId();
                
                
                if (!user.save(flush:true, failOnError:true)) {
                    println("inside error");
                    user.errors.allErrors.each { println it }
                } else {
                     
                    println(" user saved")
                     
                    // update account identifier in Account 
                    account.accountIdentifier =  requestData.userName
                    account.save(flush:true)
                        
                    server = new OpenStackServer(props.get(Config.OPENSTACK_ENDPOINT_URL), user.uuid, requestData.password, null);
                    OSClient osUser = server.authenticate();  
                        
                    GenerateKeyPair generateKeyPair = new GenerateKeyPair();
                    def generatedPrivateKey = generateKeyPair.getPrivateKey();
                    def generatedPublicKey = generateKeyPair.getPublicKey();
                        
                    Keypair kp = osUser.compute().keypairs().create("defaultkeypair", generatedPublicKey);
                    //                      
                    def sshKeys = new SSHKeys();
                    sshKeys.name = "defaultkeypair";
                    sshKeys.isDefault = true;
                    sshKeys.publicKey = generatedPublicKey;
                    sshKeys.privateKey = generatedPrivateKey;
                    sshKeys.fingerprint = kp.getFingerprint();
                    sshKeys.account = account;
                    sshKeys.user = user;
                    sshKeys.createdAt = new Date();
                    sshKeys.save(flush:true);
                        
                    def role = Role.findByAuthority("ROLE_PAID_USER");
                    UserRole userRole = new UserRole();
                    userRole.user = user;
                    userRole.role = role;
                    userRole.save(flush:true, failOnError:true);                           
                    def verifyLink = applicationUrl + "/account/confirmAccount/" + hashtext
                         
                    Map tempalteMap = notificationService.getDefaultMailTemplateMap()
                    tempalteMap.put("userMail" , account.accountIdentifier)       
                    tempalteMap.put("firstName" , account.firstName)                               
                    tempalteMap.put("verifyLink" , verifyLink) 
                
                    notificationService.send(requestData.email.toString(), "accountActiviation.subject.ftl", tempalteMap, "accountActiviation.ftl")                                                  
                }
               
                
                ArrayList<ArrayList<String>> createResponse = new ArrayList<ArrayList<String>>();        
                HashMap<String,String> item = new HashMap<String,String>(); 
                item.put("result", GeneralConstants.RESULT_SUCCESS);
                createResponse.add(item)
            
                return createResponse

            } else {
                throw new ValidationException(user.errors.allErrors);
            }
            
            
        }catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    } 
    
}
