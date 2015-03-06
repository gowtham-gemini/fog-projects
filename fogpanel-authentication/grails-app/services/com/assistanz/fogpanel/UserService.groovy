package com.assistanz.fogpanel

import groovy.json.JsonBuilder
import grails.converters.deep.JSON
import grails.plugin.springsecurity.SpringSecurityService
import java.util.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import com.assistanz.fogpanel.NotificationService
import com.assistanz.openstack.OpenStackServer
import com.auth.uitls.LdapUtils
import com.assistanz.cloud.config.ConfigLoader

import com.assistanz.fogpanel.GeneralConstants;
import org.apache.commons.logging.LogFactory;
import grails.transaction.Transactional
import org.codehaus.groovy.grails.commons.ApplicationHolder


@Transactional
class UserService {
    
    private static final log = LogFactory.getLog(this)
    def springSecurityService;
    NotificationService notificationService
    
    LdapUtils ldapUtils    
    
    
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
                    userItem.put("username", item.username);
                    userItem.put("email", item.email);
                    userItem.put("contactNumber", item.email);
                    userItem.put("enabled", item.enabled);
                    userItem.put("uuid", item.uuid);
                    
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
                        
            
            ConfigLoader configLoader = ConfigLoader.getInstance();

            Properties props = configLoader.getProperties();

            OpenStackServer server = new OpenStackServer(props.get(Config.OPENSTACK_ENDPOINT_URL), props.get(Config.OPENSTACK_ADMIN_UUID), props.get(Config.OPENSTACK_ADMIN_PASSWORD), null);

            //            OSClient os = server.authenticate();        
                                   
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

            //            OSClient os = server.authenticate();
            //            
            //            List<? extends OSUser> users = os.identity().users().list();
            //            
            //            for (OSUser openStackUser : users) {
            //                 
            //                //                println("openStackUser"+openStackUser.toString());
            //                
            //                User user = User.findByUuid(openStackUser.id);
            //                
            //                if(!user) {
            //                    user = new User();
            //                    user.uuid = openStackUser.id;
            //                    user.account = Account.findByUuid(openStackUser.tenantId)
            //                    //user.apiKey = data.apiKey;
            //                    user.domain = Domain.findByReferenceId(openStackUser.domainId)
            //                    user.username = openStackUser.name;
            //                    user.email = openStackUser.email;
            //                    user.firstName = openStackUser.name;
            //                    user.lastName = openStackUser.name;
            //                    
            //                    // password will be dummy 
            //                    user.password = openStackUser.name;
            //                    
            //                    //user.secretKey = data.secretKey;
            //                    user.failureCount = 0;
            //                    user.token = "0";
            //                   
            //                   
            //                    user.enabled = openStackUser.enabled;
            //                    user.accountExpired = false;
            //                    user.accountLocked = false;
            //                    user.passwordExpired = false;
            //                    
            //                    if (!user.save(flush: true)) {
            //                        user.errors.allErrors.each { println it }
            //                    } else {
            //                        
            //                        def role = Role.findByAuthority("ROLE_PAID_USER");
            //                        UserRole userRole = new UserRole();
            //                        userRole.user = user;
            //                        userRole.role = role;
            //                        userRole.save(flush:true, failOnError:true);  
            //                    }
            //                    
            //                } else {
            //                    
            //                    user.username = openStackUser.name;
            //                    user.account.accountIdentifier = openStackUser.name;
            //                    user.email = openStackUser.email;
            //                    user.firstName = openStackUser.name;
            //                    user.lastName = openStackUser.name;
            //                    if (!user.save(flush: true)) {
            //                        user.errors.allErrors.each { println it }
            //                    }
            //                    
            //                }
            //                
            //
            //            }  
            
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
        
        def applicationUrl = ApplicationHolder.getApplication().config.faas.applicationUrl
        //save default domain
        //        def defaultDomain = Domain.findByIsDefault(true)
        ConfigLoader configLoader = ConfigLoader.getInstance();
        
        Properties props = configLoader.getProperties();
        
        OpenStackServer server = new OpenStackServer(props.get(Config.OPENSTACK_ENDPOINT_URL), props.get(Config.OPENSTACK_ADMIN_UUID), props.get(Config.OPENSTACK_ADMIN_PASSWORD), null);
        
        //        OSClient os = server.authenticate();
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
            //            user.domain = defaultDomain;
            
            if (user.validate()) {
                
                //create user to OpenStack
                //def userPassword = springSecurityService.encodePassword(requestData.password);
                                                
                //                OpenstackUser openstackUser = os.identity().users().create(Builders.user().tenantId(account.uuid)
                //                    .domainId(defaultDomain.referenceId) 
                //                    .name(requestData.userName)
                //                    .password(requestData.password).email(requestData.email).enabled(true).build());
                        
                def openStackRoleName = "_member_";
                //                OpenstackRole currentRole = os.identity().roles().getByName(openStackRoleName); 
                def roleId = currentRole.getId();
                        
                //Assign role to current user
                //                os.identity().roles().addUserRole(account.uuid, openstackUser.getId(), roleId);
                //                        
                //                user.uuid = openstackUser.getId();
                
                
                if (!user.save(flush:true, failOnError:true)) {
                    println("inside error");
                    user.errors.allErrors.each { println it }
                } else {
                     
                    println(" user saved")
                     
                    // update account identifier in Account 
                    account.accountIdentifier =  requestData.userName
                    account.save(flush:true)
                        
                    server = new OpenStackServer(props.get(Config.OPENSTACK_ENDPOINT_URL), user.uuid, requestData.password, null);
                    //                    OSClient osUser = server.authenticate();  
                        
                    GenerateKeyPair generateKeyPair = new GenerateKeyPair();
                    def generatedPrivateKey = generateKeyPair.getPrivateKey();
                    def generatedPublicKey = generateKeyPair.getPublicKey();
                        
                    //                    Keypair kp = osUser.compute().keypairs().create("default_keyPair", generatedPublicKey);
                    //                      
                    //                    def sshKeys = new SSHKeys();
                    //                    sshKeys.name = "default_keyPair";
                    //                    sshKeys.isDefault = true;
                    //                    sshKeys.publicKey = generatedPublicKey;
                    //                    sshKeys.privateKey = generatedPrivateKey;
                    //                    sshKeys.fingerprint = kp.getFingerprint();
                    //                    sshKeys.account = account;
                    //                    sshKeys.user = user;
                    //                    sshKeys.createdAt = new Date();
                    //                    sshKeys.save(flush:true);
                    //                        
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
    
    
      
    /**
     *  syncLdapUsers
     *  
     *  This method is used to sync LDAP users with Faas users.
     *  If the user doesnot exist it will create a new entry if the user exist 
     *  the data will be updated.Here the user is identified by the UUID of the 
     *  LDAP.
     */
    def syncLdapUsers(){        
        
        try{
            println("in sync users")
            //get the users from LDAP        
            List<User> users = ldapUtils.getAllUsers()   
//            HashMap<String,String> ldapUserList = new HashMap<String,String>();

            println("in sync users")
            //Iterate the list 
            for(User user : users ) {  
                
//                ldapUserList.put(user.uuid, "userUuid")

                println("in sync users  ********** "  +user.username)
                //In the iteration check if the user exist by uuid
                // if user exist update else create a new entry                
                // check if the user is admin  if so update user-role as well                                                
                
                if(User.findByUuid(user.uuid)  != null ){                
                    User appUser = this.copy(user,User.findByUuid(user.uuid)) 
                    appUser.save(flush:true)                                        
                    
                } else {                                                                                                                       
                    User appUser = new User()
                    appUser.enabled = true;                    
                    appUser.accountExpired = false;
                    appUser.accountLocked = false;
                    appUser.passwordExpired = false;
                    
                    appUser = this.copy(user,appUser )                                             
                        
                    if (!appUser.save(flush:true)) {
                        appUser.errors.allErrors.each { println it }
                    }                    
                }                       
            }
            
//            def oldUsers = User.findAllWhere(enabled: true);
//           
//            for(def oldUser: oldUsers) {
//                
//                boolean userExists = ldapUserList.containsKey(oldUser.uuid);
//                
//                println("userExists: "+userExists)
//
//                if(userExists.toString() == "false" || userExists == false) {
//                    //To disable non-LDAP users
//                    oldUser.enabled = false;
//                    oldUser.save(flush:true)
//                }
//
//            }
            
        } catch (Exception ex){            
            
            println(" Exception while syncing ")
            ex.printStackTrace();
            throw ex;
        }
        
    }
    
    /**
     *  copy
     *  
     *  This method copies the ldap users with the existing users in the 
     *  database or  new users.
     *  
     *  returns appUser.
     */
    User copy(User ldapUser,User appUser){       
        try{           
            
            
            //TODO need to add more fields according to each LDAP server. 
            appUser.uuid = ldapUser.uuid
            appUser.username = ldapUser.uuid
            appUser.email =ldapUser.email
            appUser.contactNumber = ldapUser.contactNumber                                       
            
            appUser.firstName = ldapUser.firstName//cn
            appUser.lastName = ldapUser.lastName //sn
            
            return appUser 
                                            
            
        }catch(Exception ex){
            println(" Exception while syncing ")
            ex.printStackTrace();
            throw ex;            
        }
    }            
    
    //TODO need to remove 
    def assignAdminRole(){                                
        println(" ************** assign admin ***************") 
        LdapGroup group = ldapUtils.findGroupByCn("admins")        
        Role role =  Role.findByAuthority("ROLE_ADMIN")                       
        LdapGroup appGroup = LdapGroup.findByName(group.name)                
        List<User> users                       
        if(appGroup != null ){                                   
            
            def groupCriteria = LdapGroupUser.createCriteria()
            def  groupList = groupCriteria.list {
                eq("group", appGroup)                               
            }                                    
            
            if(groupList != null ){
                for(LdapGroupUser gu : groupList){
                    UserRole  userRole = UserRole.get(gu.getUser().id ,role.id)                                
                    if(userRole == null ){
                        
                        UserRole.create(gu.getUser(),role)                    
                    }
                    //TODO update sync when users are deleted.
                }
            }                     
        }
    }
    
    def listUsers() {
        
        def users = User.findAll()
        
        ArrayList list = new ArrayList()
        for(def user : users) {
            def checkIsAdmin = UserRole.findByUser(user)?.role
            if(checkIsAdmin != null) {
                if(checkIsAdmin.authority != "ROLE_ADMIN") {
                    HashMap map = new HashMap()
                    map.put("id",user.id)
                    map.put("username",user.username)
                    map.put("enabled",user.enabled)
                    map.put("accountLocked",user.accountLocked)
                    map.put("firstName",user.firstName)
                    map.put("lastName",user.lastName)
                    map.put("email",user.email)
                    map.put("uuid",user.uuid)
                    map.put("apiKey",user.apiKey)
                    list.add(map)
                }
            }
            
        }
        
        return list
    }
    
    def viewUser(id) {
        try{
            ArrayList list = new ArrayList()
            def userDetails = User.findById(id)
            HashMap map = new HashMap()
            map.put("id",userDetails.id)
            map.put("name",userDetails.username)
            map.put("firstName",userDetails.firstName)
            map.put("lastName",userDetails.lastName)
            map.put("email",userDetails.email)
            map.put("accountLocked",userDetails.accountLocked)
            map.put("enabled",userDetails.enabled)
            list.add(map)
            return list
        } catch(Exception ex) {
            throw ex
        }
        
    }   
    
    
    
    /**
     *  assignUserRole
     *  
     *  This method is used  assign paidf user role to other users.
     */
    def assignUserRole(){                                              
        
        Role paidRole =  Role.findByAuthority("ROLE_PAID_USER")                               
        def users = User.executeQuery("select u from User u where u.id not  in (select ur.user.id from UserRole ur  where ur.role.id = 1 )  ")                
        
        if(users != null ){                                                                      
            for(User user : users){                                                
                //Assigning paid role for all users
                UserRole  userRole = UserRole.get(user.id ,paidRole.id)     
                if(userRole ==  null){
                    UserRole.create(user,paidRole)                                                                        
                }                               
            }                                  
        }        
    }        
    
}
