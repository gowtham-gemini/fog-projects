package com.assistanz.fogpanel

import com.assistanz.fogpanel.paymentgateway.*;
import com.assistanz.cloud.cloudstack.accounts.CSAccountService;
import com.assistanz.cloud.cloudstack.CloudStackServer;
import com.assistanz.cloud.cloudstack.accounts.CreateAccountResponse;
import grails.converters.deep.JSON
import java.util.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import com.assistanz.fogpanel.GeneralConstants;
import com.assistanz.fogpanel.MailTemplateManager;
import com.assistanz.fogpanel.Account;
import com.assistanz.cloud.cloudstack.address.CSAddressService
import com.assistanz.cloud.cloudstack.limit.CSLimitService
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import com.assistanz.fogpanel.MailManager;
import com.assistanz.fogpanel.NotificationService
import java.text.SimpleDateFormat
import java.util.regex.Pattern
import java.util.regex.Matcher
import com.assistanz.cloud.cloudstack.accounts.AccountResponse
import org.codehaus.groovy.grails.commons.ApplicationHolder
import com.assistanz.cloud.cloudstack.virtualmachine.CSVirtualMachineService
import com.assistanz.cloud.cloudstack.volume.CSVolumeService
import com.assistanz.cloud.cloudstack.snapshot.CSSnapshotService
import com.assistanz.cloud.cloudstack.ssh.CSSSHService
import com.assistanz.cloud.cloudstack.users.CSUserService
import com.assistanz.fogpanel.PaymentService;
import org.springframework.context.MessageSource
import org.apache.commons.logging.LogFactory;
import grails.transaction.Transactional
import com.assistanz.fogpanel.PullAccountJob;

@Transactional
class AccountService {

    MessageSource messageSource
    def springSecurityService; 
    def paymentService
    NotificationService notificationService
    LicenseValidationService licenseValidationService
    ConfigService configService;
    
    private static final log = LogFactory.getLog(this)
    
    def accountServer() {
        
        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret

        CloudStackServer server =
                 new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);

        CSAccountService csAccountService = new CSAccountService(server);
        
        return csAccountService;
        
    }

    def virtualMachineServer() {
        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret

        CloudStackServer server =
                 new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);
                
        CSVirtualMachineService csVirtualMachineService = new CSVirtualMachineService(server);
        
        return csVirtualMachineService;
    }
    def validateUser(String userName) {
        try {
            
           // def result = Account.findByEmail(email);
           def invitation = Invitation.findByName(userName);
            def userNamePatern = "^[a-zA-Z0-9-@.]{1,200}";                
            Pattern pattern = Pattern.compile(userNamePatern)
            Matcher matchContent = pattern.matcher(userName);              
            def matchResult = matchContent.matches();
            ArrayList<ArrayList<String>> arrayList = new ArrayList<ArrayList<String>>(); 
            HashMap<String,String> item = new HashMap<String,String>();    
            if(invitation) {
                item.put("result", "exist");
                arrayList.add(item);
            } else if(matchResult == false || matchResult == "false") {
                 item.put("result", "invalid");
                 arrayList.add(item);
            } else if (matchResult == true || matchResult == "true") {
                item.put("result", "OK");
                arrayList.add(item);
            }                                                                            
            return arrayList;
        } catch (ValidationException ex) {
            throw ex                
        } catch (LicenseExpiredException ex) {
            throw ex
        } catch (NullPointerException ex){
            throw ex
            Console.print(ex);
        } catch (Exception ex){
            throw ex
        }
    }
    def getInvitation() {
        try {
            ArrayList<ArrayList<String>> arrayList = new ArrayList<ArrayList<String>>(); 
            def invotationList = Invitation.findAll();
            def dateFormatValue = configService.getDateFormat();    
            SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatValue);
            for(int i = 0; i < invotationList.size(); i++) {
                def currentItem = invotationList[i]
                HashMap<String,String> item = new HashMap<String,String>();                
                item.put("id", currentItem.id);
                item.put("name", currentItem.name);                
                item.put("email", currentItem.email);
                item.put("status", currentItem.status);
                item.put("createdDate", dateFormat.format(currentItem.createdDate).toString());
                item.put("type", currentItem.type);
                item.put("name", currentItem.name);   
                arrayList.add(item)
            } 
            return arrayList;
        } catch (ValidationException ex) {
            Console.print(ex);
                [ex] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (NullPointerException ex){
             Console.print(ex);
                [ex] as JSON
        } catch (Exception ex){
             Console.print(ex);
                [ex] as JSON
        }
    }
    def updateInvitation(String requestBody) {
        try {
            def requestData = JSON.parse(requestBody)  
            def invitation = Invitation.get(requestData.invitationId);
            if(invitation) {                                               
                ArrayList<ArrayList<String>> arrayList = new ArrayList<ArrayList<String>>();                                
                def signupType = ""
                if(invitation.type == "retail") {                    
                    signupType = "retailSignup"    
                } else {
                    signupType = "trialSignup"                    
                } 
                Date date = new Date();
                def time = new Timestamp(date.getTime())                                                                                                         
                def applicationUrl = ApplicationHolder.getApplication().config.cloudstack.applicationUrl
                def verifyLink = applicationUrl + "/account/invitationsignup" + "?" + "invitationEmail=" + invitation.email + "&" +"invitationName="+invitation.name;                
                invitation.link = verifyLink;                
                invitation.createdDate = time;                
                invitation.save(flush:true);
                if (!invitation.save()) {
                    throw new ValidationException(invitation.errors.allErrors)                                          
                }   
                def orgName = Config.findByName("organisation.name").value;
                Map tempalteMap = notificationService.getDefaultMailTemplateMap()
                tempalteMap.put("name" , invitation.name)
                tempalteMap.put("verifyLink" , verifyLink)              
                notificationService.send(invitation.email.toString(), "invitation.subject.ftl", tempalteMap, "invitation.ftl")                                                        

                HashMap<String,String> item = new HashMap<String,String>();
                item.put("result", "OK");
                arrayList.add(item);
                return arrayList
            }
        } catch (ValidationException ex) {
                throw ex                
        } catch (LicenseExpiredException ex) {
                throw ex
        } catch (NullPointerException ex){
                throw ex
                Console.print(ex);
        } catch (Exception ex){
                throw ex
        }
        
    }
    def sendSignupInvitation(String requestBody) {
        try {               
            def requestData = JSON.parse(requestBody)                                    
            ArrayList<ArrayList<String>> arrayList = new ArrayList<ArrayList<String>>();                                
            def signupType = ""
            Date date = new Date();
            def time = new Timestamp(date.getTime())  
            def promoCode = Promotion.findAll();
            for(int i = 0; i < requestData.invitationInfo.length(); i++) {                
                def currentRequistItem =  requestData.invitationInfo[i]
                if(currentRequistItem.signupType == "retail") {                    
                    signupType = "retailSignup"    
                } else {
                    if(!promoCode) {
                        throw new Exception("promoCodeMissing")
                    } else {
                        signupType = "trialSignup"       
                    }                   
                } 
                def invitation = Invitation.findByName(currentRequistItem.userName);
                if(invitation) {                    
                    throw new Exception("duplicateEmail")
                } else {                                    
                    def applicationUrl = ApplicationHolder.getApplication().config.cloudstack.applicationUrl
                    def verifyLink = applicationUrl + "/account/invitationsignup" + "?" + "invitationEmail=" + currentRequistItem.email + "&" +"invitationName="+currentRequistItem.userName;                
                    def newInvitation = new Invitation()
                    newInvitation.email = currentRequistItem.email;                
                    newInvitation.link = verifyLink;
                    newInvitation.status = "in progress";
                    newInvitation.createdDate = time;
                    newInvitation.type = currentRequistItem.signupType;
                    newInvitation.name = currentRequistItem.userName;
                    newInvitation.save(flush:true);
                    if (!newInvitation.save()) {
                        throw new ValidationException(newInvitation.errors.allErrors)                                          
                    }    
                    def orgName = Config.findByName("organisation.name").value;
                    Map tempalteMap = notificationService.getDefaultMailTemplateMap()
                    tempalteMap.put("name" , currentRequistItem.userName)
                    tempalteMap.put("verifyLink" , verifyLink)   
                    notificationService.send(currentRequistItem.email.toString(), "invitation.subject.ftl", tempalteMap, "invitation.ftl") 
                }     
            }
            HashMap<String,String> item = new HashMap<String,String>();
            item.put("result", "OK");
            arrayList.add(item);
            return arrayList
            
        } catch (ValidationException ex) {
                throw ex                
        } catch (LicenseExpiredException ex) {
                throw ex
        } catch (NullPointerException ex){
                throw ex
                Console.print(ex);
        } catch (Exception ex){
                throw ex
        }
    }
    def registerAccount(String requestBody) {
        try {
            
//            licenseValidationService.authorizeAction(FogPanelService.ACCOUNT_SIGNUP)
            
            def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
            def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
            def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret
            
            def applicationUrl = ApplicationHolder.getApplication().config.cloudstack.applicationUrl
                                                                       
                       
            Config billingPeriodDays =  Config.findByName(Config.BILLING_PERIOD_DAYS);
            Config signupCardVerificationEnabled =  Config.findByName(Config.SIGNUP_CARD_VERIFICATION_ENABLED);
            
            Config creditCardProcessing =  Config.findByName(Config.CARD_PROCESSING_ENABLED);
    
            Config vmLimitNo =  Config.findByName(Config.INSTANCE_LIMIT);
            Config storageLimitNo =  Config.findByName(Config.STORAGE_LIMIT);
            Config snapLimitNo =  Config.findByName(Config.SNAPSHOT_LIMIT);
            Config bandLimitNo =  Config.findByName(Config.BANDWIDTH_LIMIT);
            
            
            Config retailInstanceLimit =  Config.findByName(Config.RETAIL_INSTANCE_LIMIT);
            Config enableThresholdLimit =  Config.findByName(Config.RETAIL_ENABLE_THRESHOLDLIMIT);            
            Config retailStorageLimit =  Config.findByName(Config.RETAIL_STORAGE_LIMIT);
            Config retailSnapshotLimit =  Config.findByName(Config.RETAIL_SNAPSHOT_LIMIT);
            Config retailPublicIPLimit =  Config.findByName(Config.RETAIL_PUBLIC_IP_LIMIT);
            Config retailVpcLimit =  Config.findByName(Config.RETAIL_VPC_LIMIT);
            Config networkLimit =  Config.findByName(Config.RETAIL_NETWORK_LIMIT);
            Config retailCpuLimit =  Config.findByName(Config.RETAIL_CUP_LIMIT);
            Config retailMemoryLimit =  Config.findByName(Config.RETAIL_MEMORY_LIMIT);
            Config primaryStorageLimit =  Config.findByName(Config.RETAIL_PRIMARY_STORAGE_LIMIT);
            Config secondaryStorageLimit =  Config.findByName(Config.RETAIL_SECONDARY_STORAGE_LIMIT);
            
            CloudStackServer server = new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey)
            CSAccountService csAccService = new CSAccountService(server);
            
            CSLimitService csLimitService = new CSLimitService(server); 
            
            // convert string to json object
            def requestData = JSON.parse(requestBody)  
            Date date = new Date();
            def time = new Timestamp(date.getTime())
            def token = requestData.userName + date.toString()
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(token.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            
            
            Account.withTransaction {
                def account = new Account()
                account.firstName = requestData.firstName
                account.lastName = requestData.lastName
                account.email = requestData.email
                account.accountType = requestData.accountType;  
                account.uuid = "creating";  
                def defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
                account.preferredLanguage = defaultLanguage.value
                if(requestData.accountType == "RETAIL") { 
                    Config retailCreditLimit =  Config.findByName(Config.SIGNUP_TYPE_RETAIL_CREDIT_LIMIT);
                    account.creditLimit = Double.parseDouble(retailCreditLimit.value); 
                    account.promotionalCode = requestData.promotionalCode
                    def promotionalCode = Promotion.findByCode(requestData.promotionalCode);  
                    if(promotionalCode) {
                        account.credit = promotionalCode.value
                    } else {
                        account.credit = 0
                    }
                } else if(requestData.accountType == "TRIAL") {
                    Config trialCreditLimit =  Config.findByName(Config.SIGNUP_TYPE_TRIAL_CREDIT_LIMIT);
                    account.creditLimit = Double.parseDouble(trialCreditLimit.value);
                    
                    account.vmLimit = vmLimitNo.value
                    account.storageLimit = storageLimitNo.value
                    account.snapshotLimit = snapLimitNo.value
                    account.bandwidthLimit = bandLimitNo.value
                    
                    def promotionalCode = Promotion.findByCode(requestData.promotionalCode);  
                    if(promotionalCode) {
                        account.promotionalCode = requestData.promotionalCode
                        account.credit = promotionalCode.value
                    } else {                        
                        throw new InvalidPromotionalCodeException("invalid promotional code"); 
                    } 
                }
                account.accountIdentifier =  requestData.accountIdentifier
                account.email =  requestData.email
                Calendar vmsnapdate = Calendar.getInstance();
                vmsnapdate.add(Calendar.DATE, -1);
                account.signUpDate = time
                account.vmSnapUsageDate = vmsnapdate.getTime()
                account.streetAddress = requestData.address
                account.streetAddress1 = requestData.address1
                account.city = requestData.city
                account.zip = requestData.zip
                account.state = State.findById(requestData.state)
                account.country = Country.findById(requestData.country)
                account.companyName =requestData.companyName
                account.phoneNumber = requestData.phoneNumber
                account.billingStreetAddress = requestData.address
                account.billingCity = requestData.city
                account.billingZip = requestData.zip
                account.billingState = State.findById(requestData.state)
                account.billingCountry = Country.findById(requestData.country)
                account.billingPhoneNumber = requestData.phoneNumber
                account.totalAmount = 0
                account.totalPayable = 0    
                account.totalPaid = 0
                account.previousPaid = 0
                account.lateFee = false
                account.penaltyFee = 0
                account.previousBalance = 0 
                account.status = "NOT_VERIFIED"
                
                if(enableThresholdLimit.value == "true" && requestData.accountType == "RETAIL") {
                   
                    account.vmLimit = retailInstanceLimit.value
                    account.storageLimit = retailStorageLimit.value
                    account.snapshotLimit = retailSnapshotLimit.value

                    account.publicIPLimit = retailPublicIPLimit.value
                    account.vpcLimit = retailVpcLimit.value
                    account.networkLimit = networkLimit.value
                    account.cpuLimit = retailCpuLimit.value
                    account.memoryLimit = retailMemoryLimit.value
                    account.primaryStorageLimit = primaryStorageLimit.value
                    account.secondaryStorageLimit = secondaryStorageLimit.value
                } 
                
                if(requestData.accountType == "RETAIL") {
                    if(creditCardProcessing.value =="true" && signupCardVerificationEnabled.value == "true") {
                        def card = saveCard("CARD-A"+requestData.email ,requestData.cardNumber, Integer.parseInt(requestData.expiryMonth), requestData.expiryYear, requestData.cardType, requestData.cvv)                           
                        account.cardExpiryMonth = Double.parseDouble(requestData.expiryMonth)
                        account.cardExpiryYear = Double.parseDouble(requestData.expiryYear.toString())
                        account.defaultCard = card.UUID
                        account.cardVerified = true  
                    }
                }
                if (account.validate()) {
                    account.domain = Domain.findByIsDefault(true);
                    account.save(flush:true, failOnError:true);
                    if (!account.save()) {
                        throw new ValidationException(account.errors.allErrors);
                    }
                    if(requestData.accountType == "RETAIL") {
                        def promotionalCode = Promotion.findByCode(requestData.promotionalCode);  
                        if(promotionalCode) {
                            Credit credit = new Credit();    
                            credit.account = account;
                            credit.date = time;
                            credit.description = "Retail signup credit";
                            credit.amount = promotionalCode.value;
                            credit.relId = "0"
                            credit.save(flush:true, failOnError:true); 
                        }
                    } else if(requestData.accountType == "TRIAL") {
                        Config trialCreditLimit =  Config.findByName(Config.SIGNUP_TYPE_TRIAL_CREDIT_LIMIT);
                        def promotionalCode = Promotion.findByCode(requestData.promotionalCode);  
                        Credit credit = new Credit();    
                        credit.account = account;
                        credit.date = time;
                        credit.description = "free signup credit";
                        credit.amount = promotionalCode.value; 
                        credit.relId = "0"
                        credit.save(flush:true, failOnError:true); 
                    }
                    Calendar calendar = Calendar.getInstance(); 
                    Config linkActiveTime =  Config.findByName(Config.LINK_ACTIVE_TIME);
                    calendar.add(Calendar.HOUR, Integer.parseInt(linkActiveTime.value));
                    User user = new User();
                    user.account = account;
                    user.token = hashtext.toString();
                    user.username = account.accountIdentifier;  
                    user.failureCount = 0;
                    user.password = requestData.password; 
                    user.enabled = true;
                    user.tokenExpiryDate = calendar.getTime() 
                    user.accountExpired = false;
                    user.domain = Domain.findByIsDefault(true);
                    user.accountLocked = false;
                    user.passwordExpired = false;
                    user.firstName = requestData.firstName
                    user.lastName = requestData.lastName
                    user.email = requestData.email
                    if (user.validate()) {
                        
                        HashMap<String,String> createAccountOption = new HashMap<String,String>();  
                        createAccountOption.put("domainid", Domain.findByIsDefault(true).referenceId);
                        
                        def response =
                                csAccService.createAccount("0", account.email, account.firstName,
                                        account.lastName, account.accountIdentifier, requestData.password, createAccountOption);
                        
                        user.uuid = response.id;
                        user.save(flush:true, failOnError:true); 
                        def role = Role.findByAuthority("ROLE_PAID_USER");
                        UserRole userRole = new UserRole();
                        userRole.user = user;
                        userRole.role = role;
                        userRole.save(flush:true, failOnError:true);                           
                        def verifyLink = applicationUrl + "/account/confirmAccount/" + hashtext.toString()
                         
                        Map tempalteMap = notificationService.getDefaultMailTemplateMap()
                        tempalteMap.put("userMail" , account.accountIdentifier)       
                        tempalteMap.put("firstName" , account.firstName)                               
                        tempalteMap.put("verifyLink" , verifyLink)                                                                        
                                     
                        if(requestData.accountType == "RETAIL" && enableThresholdLimit.value == "true") { 
                                  
                            HashMap<String,String> pStorageLimitoptional = new HashMap<String,String>();  
                            pStorageLimitoptional.put("account", new String(account.accountIdentifier));
                            pStorageLimitoptional.put("domainid", new String(account.domain.referenceId));                      
                            pStorageLimitoptional.put("max", primaryStorageLimit.value);
                            def pStorageLimit = csLimitService.updateResourceLimit("10", pStorageLimitoptional)
                            
                            HashMap<String,String> sStorgeLimitoptional = new HashMap<String,String>();  
                            sStorgeLimitoptional.put("account", new String(account.accountIdentifier));
                            sStorgeLimitoptional.put("domainid", new String(account.domain.referenceId));                      
                            sStorgeLimitoptional.put("max", secondaryStorageLimit.value);
                            def sStorgeLimit = csLimitService.updateResourceLimit("11", sStorgeLimitoptional)
                            
                            HashMap<String,String> memoryLimitoptional = new HashMap<String,String>();  
                            memoryLimitoptional.put("account", new String(account.accountIdentifier));
                            memoryLimitoptional.put("domainid", new String(account.domain.referenceId));                      
                            memoryLimitoptional.put("max", retailMemoryLimit.value);
                            def memoryLimit = csLimitService.updateResourceLimit("9", memoryLimitoptional)
                            
                            HashMap<String,String> cpuLimitoptional = new HashMap<String,String>();  
                            cpuLimitoptional.put("account", new String(account.accountIdentifier));
                            cpuLimitoptional.put("domainid", new String(account.domain.referenceId));                      
                            cpuLimitoptional.put("max", retailCpuLimit.value);
                            def cpuLimit = csLimitService.updateResourceLimit("8", cpuLimitoptional)
                                                                                    
                            HashMap<String,String> vpcLimitoptional = new HashMap<String,String>();  
                            vpcLimitoptional.put("account", new String(account.accountIdentifier));
                            vpcLimitoptional.put("domainid", new String(account.domain.referenceId));                      
                            vpcLimitoptional.put("max", retailVpcLimit.value);
                            def vpcLimit = csLimitService.updateResourceLimit("7", vpcLimitoptional)
                            
                            HashMap<String,String> networkLimitoptional = new HashMap<String,String>();  
                            networkLimitoptional.put("account", new String(account.accountIdentifier));
                            networkLimitoptional.put("domainid", new String(account.domain.referenceId));                      
                            networkLimitoptional.put("max", networkLimit.value);
                            def networkLimitAPI = csLimitService.updateResourceLimit("6", networkLimitoptional)
                                                       
                            HashMap<String,String> VMoptional = new HashMap<String,String>();  
                            VMoptional.put("account", new String(account.accountIdentifier));
                            VMoptional.put("domainid", new String(account.domain.referenceId));
                            VMoptional.put("max", retailInstanceLimit.value);
                            
                            HashMap<String,String> stoOptional = new HashMap<String,String>();  
                            stoOptional.put("account", new String(account.accountIdentifier));
                            stoOptional.put("domainid", new String(account.domain.referenceId));
                            stoOptional.put("max", retailStorageLimit.value);                            
                            
                            HashMap<String,String> snapOptional = new HashMap<String,String>();  
                            snapOptional.put("account", new String(account.accountIdentifier));
                            snapOptional.put("domainid", new String(account.domain.referenceId));
                            snapOptional.put("max", retailSnapshotLimit.value);
                            
                            def vmLimit = csLimitService.updateResourceLimit("0", VMoptional)
                            def storageLimit = csLimitService.updateResourceLimit("2", stoOptional)
                            def snapshotLimit = csLimitService.updateResourceLimit("3", snapOptional)  
                            
                            HashMap<String,String> publicIPLimitoptional = new HashMap<String,String>();  
                            publicIPLimitoptional.put("account", new String(account.userName));
                            publicIPLimitoptional.put("domainid", new String(account.domain.referenceId));                      
                            publicIPLimitoptional.put("max", retailPublicIPLimit.value);
                            def retailPublicIP = csLimitService.updateResourceLimit("1", publicIPLimitoptional)
                            
                            
                        } else if(requestData.accountType == "TRIAL") {                                                                                                            
                            HashMap<String,String> optional = new HashMap<String,String>();  
                            optional.put("account", new String(account.accountIdentifier));
                            optional.put("domainid", new String(account.domain.referenceId));
                            optional.put("max", "-1");
                            
                            HashMap<String,String> VMoptional = new HashMap<String,String>();  
                            VMoptional.put("account", new String(account.accountIdentifier));
                            VMoptional.put("domainid", new String(account.domain.referenceId));
                            VMoptional.put("max", vmLimitNo.value);
                            
                            HashMap<String,String> stoOptional = new HashMap<String,String>();  
                            stoOptional.put("account", new String(account.accountIdentifier));
                            stoOptional.put("domainid", new String(account.domain.referenceId));
                            stoOptional.put("max", storageLimitNo.value);                            
                            
                            HashMap<String,String> snapOptional = new HashMap<String,String>();  
                            snapOptional.put("account", new String(account.accountIdentifier));
                            snapOptional.put("domainid", new String(account.domain.referenceId));
                            snapOptional.put("max", snapLimitNo.value);
                            
                            def vmLimit = csLimitService.updateResourceLimit("0", VMoptional)
                            def storageLimit = csLimitService.updateResourceLimit("2", stoOptional)
                            def snapshotLimit = csLimitService.updateResourceLimit("3", snapOptional)                            
                            
                            def pStorageLimit = csLimitService.updateResourceLimit("10", optional)
                            def sStorgemLimit = csLimitService.updateResourceLimit("11", optional)
                        }
                        
                        CSSSHService cSSSHService = new CSSSHService(server)

                        HashMap<String,String> optional = new HashMap<String,String>();
                        optional.put("account", account.accountIdentifier);
                        optional.put("domainid", account.domain.referenceId);

                        def res = cSSSHService.createSSHKeyPair(account.accountIdentifier+"-default", optional) 
                        
                        SSHKeys sshKey = new SSHKeys()
                        sshKey.fingerprint = res.fingerPrint
                        sshKey.name = res.name
                        sshKey.privatekey = res.privateKey
                        sshKey.isDefault = true
                        sshKey.account = account
                        sshKey.save(flush:true); 
                        
                        def promotionalCode = Promotion.findByCode(requestData.promotionalCode);  
                        if(promotionalCode) {
                            promotionalCode.uses = promotionalCode.uses +1;
                            promotionalCode.save(flush:true, failOnError:true);
                        }                               

                        notificationService.send(requestData.email.toString(), "accountActiviation.subject.ftl", tempalteMap, "accountActiviation.ftl")                                                  
                        
                    } else {
                        throw new ValidationException(user.errors.allErrors);
                    }
                } else {
                    throw new ValidationException(account.errors.allErrors);
                }
                log.info("Account: ${account.id} is signed up")
            }  
        } catch (CardValidationException ex) {
            Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
            def language = defaultLanguage.value                        
            throw new CardValidationException(messageSource.getMessage('signup.card.validationfailed', null, new Locale(language)));        
        } catch (InvalidPromotionalCodeException ex) {            
            Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
            def language = defaultLanguage.value                        
            throw new InvalidPromotionalCodeException(messageSource.getMessage('signup.promotion.invalid', null, new Locale(language)));        
        } catch (Exception ex) {
            throw ex;            
        }          
    }
    
    
    def saveCard(uuid ,cardNumber, expiryMonth, expiryYear, cardType, cvv) { 
        try {
            def card =  paymentService.saveCard(uuid , cardNumber, expiryMonth, expiryYear, cardType, cvv)
            return card
        } catch (Exception ex) {
            throw ex;
            
                Console.print(ex);
            
            throw new CardValidationException("CARD_VERIFICATION_FAILED");
        }
    } 
    
    def updateCreditLimit (String requestBody) {
        try { 
            def requestData = JSON.parse(requestBody)
            def account = Account.get(requestData.accountId);
            Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
            ArrayList<ArrayList<String>> accountList = new ArrayList<ArrayList<String>>();                
            HashMap<String,String> resultItem = new HashMap<String,String>();            
            if(account) {
                if(account.accountType == AccountType.values()[0]) {
                    if(Double.parseDouble(requestData.newLimit) < account.totalPayable) {
                        def language = defaultLanguage.value               
                        resultItem.put("result", "Failed")
                        resultItem.put("message", messageSource.getMessage('account.update.creditLimit.error', null, new Locale(language)))                    
                        accountList.add(resultItem);
                    } else {
                        account.creditLimit = Double.parseDouble(requestData.newLimit);  
                        account.save(flush: true);
                        if (account.hasErrors()) {
                            throw new ValidationException(account.errors.allErrors);
                        } 
                        resultItem.put("result", "OK")
                        accountList.add(resultItem);                    
                    }
                } else {
                    def language = defaultLanguage.value               
                    resultItem.put("result", "Failed")
                    resultItem.put("message", messageSource.getMessage('common.update.creditLimit.message', null, new Locale(language)))                    
                    accountList.add(resultItem);
                }              
            } else {
                def language = defaultLanguage.value               
                resultItem.put("result", "Failed")
                resultItem.put("message", messageSource.getMessage('common.noUserMsg', null, new Locale(language)))                    
                accountList.add(resultItem);
            }
            return accountList;
        } catch (CardValidationException ex) {
            Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
            def language = defaultLanguage.value                        
            throw new Exception(messageSource.getMessage('account.update.creditLimit.error', null, new Locale(language)));        
        } catch (InvalidPromotionalCodeException ex) {            
            Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
            def language = defaultLanguage.value                        
            throw new InvalidPromotionalCodeException(messageSource.getMessage('common.noUserMsg', null, new Locale(language)));        
        } catch (Exception ex) {
            throw ex;            
        }       
    }
    
    def count() {
        try {                 
            def user = springSecurityService.currentUser
            def role = springSecurityService.getPrincipal().getAuthorities()    
            def totalAccount = 0;
            def retailAccount = 0;
            def trialAccount = 0;                
            if(role.iterator().next() == "ROLE_ADMIN" ) {                       
                totalAccount = Account.withCriteria {               
                    ne("accountType", AccountType.values()[2])                        
                }     
                retailAccount = Account.findAllWhere(accountType: AccountType.values()[1]);    
                trialAccount =  Account.findAllWhere(accountType: AccountType.values()[0]);            
                ArrayList<ArrayList<String>> accountList = new ArrayList<ArrayList<String>>();                
                HashMap<String,String> account = new HashMap<String,String>();
                account.put("totalAccount", totalAccount.size());
                account.put("retailAccount", retailAccount.size());
                account.put("trialAccount", trialAccount.size());   
                accountList.add(account);                 
                return accountList
            }           
        } catch(Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }        
    }
    
    def list(String email, String userName) {
         try {  
             def currency = ApplicationHolder.getApplication().config.billing.defaultCurrency
            
            if((email == "null" || email == null) && (userName == "null" || userName == null)) {                                      
                def user = springSecurityService.currentUser
                def role = springSecurityService.getPrincipal().getAuthorities()    
                if(role.iterator().next() == "ROLE_ADMIN" ) {
                   def result = Account.findAll();                       
                    ArrayList<ArrayList<String>> accountList = new ArrayList<ArrayList<String>>();            
                    for(int i=0; i < result.size(); i++) {
                        def item = result[i]; 
                        if(item.accountIdentifier != user.account.accountIdentifier) {
                            HashMap account = new HashMap();
                            account.put("id", item.id);
                            account.put("userName", item.accountIdentifier);
                            account.put("lastName", item.lastName);
                            account.put("firstName", item.firstName);
                            account.put("domainName", item.domain?.name);
                            account.put("city", item.city);
                            account.put("streetAddress", item.streetAddress);
                            account.put("streetAddress2", item.streetAddress1);
                            account.put("state", item.state.stateName);
                            account.put("zip", item.zip);
                            account.put("country", item.country.countryName);
                            account.put("billingCountry", item.billingCountry.countryName);
                            account.put("billingCity", item.billingCity);
                            account.put("billingAddress", item.billingStreetAddress);
                            account.put("companyName", item.companyName);
                            account.put("billingState", item.billingState.stateName);
                            account.put("billingZip", item.billingZip);
                            account.put("phoneNumber", item.phoneNumber);
                            account.put("billingPhoneNumber", item.billingPhoneNumber);
                            account.put("email", item.email);
                            account.put("cardVerified", item.cardVerified);
                            account.put("accountType", item.accountType);
                            account.put("status", item.status); 
                            account.put("totalPaid", item.totalPaid);
                            account.put("totalPayable", item.totalPayable);
                            account.put("creditLimit", item.creditLimit);
                            account.put("currency", currency);  
                                                     
                            def invoice = Invoice.findWhere(account: item, status: InvoiceStatus.values()[6])
                            if(invoice) {
                                 account.put("invoice", "yes"); 
                            } else {
                                 account.put("invoice", "no"); 
                            }
                            def lockuser = User.findByUsername(item.accountIdentifier)
                            if(lockuser) {
                                account.put("locked", lockuser.accountLocked); 
                            } else {
                                 account.put("locked", false); 
                            }
                            
                            accountList.add(account);
                        }                        
                        
                    }
                    return accountList
                }
            } else if((email != "null" || email != null) && (userName == "null" || userName == null)) {                
               // def result = Account.findByEmail(email);
                def emailPatern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})";
                
                Pattern pattern = Pattern.compile(emailPatern)
                Matcher matchContent = pattern.matcher(email);              
                def matchResult = matchContent.matches();
                
//                if(result) {
//                    return [GeneralConstants.RESULT_FAILURE]
//                } else 
                if(matchResult == false || matchResult == "false") {
                     return [GeneralConstants.RESULT_FALSE]
                } else if (matchResult == true || matchResult == "true") {
                    return [GeneralConstants.RESULT_TRUE]
                }
            } else if((email == "null" || email == null) && (userName != "null" || userName != null)) {
                
                def defaultDomain = Domain.findByIsDefault(true);
                def result = Account.findWhere(accountIdentifier: userName, domain: defaultDomain);
                def validationStatus = userName.matches("[a-zA-Z0-9-@. ]*");
                Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
                ArrayList<ArrayList<String>> userExists = new ArrayList<ArrayList<String>>();  
                HashMap<String,String> item = new HashMap<String,String>();
                Object[] domainArgs = new Object[2];
                domainArgs[0] = defaultDomain.name
                
                if(result) {
                    item.put("result", GeneralConstants.RESULT_FAILURE);
                    item.put("msg", messageSource.getMessage('common.username.alreadyexists.in.domain', domainArgs, new Locale(defaultLanguage.value)));
                    userExists.add(item);
                    return userExists
                } else if(validationStatus == false || validationStatus == "false" || userName.size() < 5) {
                    item.put("result", GeneralConstants.RESULT_FALSE);
                    userExists.add(item);
                    return userExists
                } else if(validationStatus == true || validationStatus == "true" && userName.size() >= 5) {
                    item.put("result", GeneralConstants.RESULT_TRUE);
                    userExists.add(item);
                    return userExists
                }
            }
            
         } catch (Exception ex) {
            throw ex;
        }         
    }
    
    def getAccountFromId(id) {
        
        def currency = ApplicationHolder.getApplication().config.billing.defaultCurrency
        
        def account = Account.get(id);
        ArrayList<ArrayList<String>> accountInfo = new ArrayList<ArrayList<String>>();
        def dateFormatValue = configService.getDateFormat();    
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatValue);
            HashMap accountItem = new HashMap();
                accountItem.put("id", account.id);
                accountItem.put("userName", account.accountIdentifier);
                accountItem.put("accountIdentifier", account.accountIdentifier);
                accountItem.put("activationDate", account.activationDate);
                accountItem.put("signUpDate", account.signUpDate);
                accountItem.put("status", "active");
                accountItem.put("city", account.city);
                accountItem.put("streetAddress", account.streetAddress);
                accountItem.put("streetAddress2", account.streetAddress1);
                accountItem.put("state", account.state.stateName);
                accountItem.put("zip", account.zip);
                accountItem.put("country", account.country.countryName);
                accountItem.put("countryId", account.country.id);
                accountItem.put("stateId", account.state.id);
                accountItem.put("lastName", account.lastName);
                accountItem.put("email", account.email); 
                accountItem.put("firstName", account.firstName);
                accountItem.put("companyName", account.companyName);
                accountItem.put("billingCountry", account.billingCountry.countryName);
                accountItem.put("billingCity", account.billingCity);
                accountItem.put("billingAddress", account.billingStreetAddress);
                accountItem.put("billingState", account.billingState.stateName);
                accountItem.put("billingZip", account.billingZip);
                accountItem.put("phoneNumber", account.phoneNumber);
                accountItem.put("billingPhoneNumber", account.billingPhoneNumber);
                accountItem.put("credit", account.credit);
                accountItem.put("accountType", account.accountType);   
                accountItem.put("status", account.status); 
                accountItem.put("domainName", account.domain?.name); 
                if(account.nextBillingData == null || account.nextBillingData == "null" || account.nextBillingData == "") {
                     accountItem.put("nextBillingData", "-"); 
                } else {
                    accountItem.put("nextBillingData", dateFormat.format(account.nextBillingData).toString()); 
                }
                accountItem.put("signUpDate", dateFormat.format(account.signUpDate).toString());
                accountItem.put("totalPaid", account.totalPaid);
                accountItem.put("totalPayable", account.totalPayable);
                accountItem.put("creditLimit", account.creditLimit);
                accountItem.put("cardVerified", account.cardVerified);
                accountItem.put("currency", currency); 
                
        
                def refundCriteria = Refund.createCriteria()
                def ref = refundCriteria.get {
                    and{
                      eq("account", account)  
                    }                              
                    projections {
                        sum("amount")
                    } 
                }
                
                if(ref == "null" || ref == null) {
                    accountItem.put("refundAmount", 0);
                } else {
                    accountItem.put("refundAmount", ref); 
                } 
                
                def payCountCriteria = Payments.createCriteria()
                def payCount = payCountCriteria.get {
                    and{
                      eq("account", account)  
                    }  
                    and {
                        eq("paymentStatus", GeneralConstants.PAYMENT_RESULT_SUCCESS)
                    }
                    projections {
                        count("id")
                    } 
                }
                accountItem.put("payCount", payCount); 
        
                Calendar startDateCalendar = Calendar.getInstance();
                startDateCalendar.add(Calendar.DATE, -1);   
                startDateCalendar.set(Calendar.HOUR_OF_DAY, 00);
                startDateCalendar.set(Calendar.MINUTE, 00);
                startDateCalendar.set(Calendar.SECOND, 00);
                startDateCalendar.set(Calendar.MILLISECOND, 00);   
                
                Calendar toDateCalendar = Calendar.getInstance();
                toDateCalendar.add(Calendar.DATE, -1);
                toDateCalendar.set(Calendar.HOUR_OF_DAY, 23);
                toDateCalendar.set(Calendar.MINUTE, 59);
                toDateCalendar.set(Calendar.SECOND, 59);
                toDateCalendar.set(Calendar.MILLISECOND, 999);          
            
                Date fromDate = startDateCalendar.getTime()
                Date toDate = toDateCalendar.getTime()
                
                def daily;
                def offeringUsageCriteria = OfferingUsage.createCriteria()
                daily = offeringUsageCriteria.get {
                    eq("usageType", UsageType.values()[1])
                    and { ge("startDate", fromDate) and { le("startDate", toDate) } 
                        and {
                            eq("account", account)
                        }
                    }
                    projections {
                        sum("cost")
                    } 
                }
                                            
                if(daily == "null" ||daily == null) {
                    accountItem.put("daily", 0);
                } else {
                    accountItem.put("daily",  Math.ceil(daily * 100d) / 100d);  
                } 
                
                def invoice = Invoice.findWhere(account: account, status: InvoiceStatus.values()[6])
                
                def currentVmList = InvoiceItem.findAllWhere(invoice: invoice, billableItem:BillableItem.get(1))
                def totalBandwidth = 0;
                def totalBandwidthCost = 0;
                def runningVmCost = 0;
                def stopVmCost = 0;
                def totalDiskCost = 0;
                def totalSnapCost = 0;
            
                def bandwidthCostCriteria = InvoiceItem.createCriteria()
                def bandCost = bandwidthCostCriteria.list {
                    eq("invoice", invoice)
                    and {
                        eq("billableItem", BillableItem.get(5))
                    }
                }
                for(def bandItem: bandCost) {
                    totalBandwidth += bandItem.usageUnits
                    totalBandwidthCost += bandItem.totalAmount
                }
                
                def diskCostCriteria = InvoiceItem.createCriteria()
                def diskCost = diskCostCriteria.list {
                    eq("invoice", invoice)
                    and {
                        eq("billableItem", BillableItem.get(2))
                    }
                    projections {
                         sum("totalAmount")
                     }
                }
                
                if(diskCost[0] == null || diskCost[0] == "null") {
                    totalDiskCost =  0
                } else {
                    totalDiskCost =  diskCost[0]
                }  
                                
                def snapCriteria = InvoiceItem.createCriteria()
                def snapCost = snapCriteria.list {
                    eq("invoice", invoice)
                    and {
                        eq("billableItem", BillableItem.get(3))
                    }
                    projections {
                         sum("totalAmount")
                     }
                }
                
                
                if(snapCost[0] == null || snapCost[0] == "null") {
                    totalSnapCost =  0
                } else {
                    totalSnapCost =  snapCost[0]
                }
                Config minHour =  Config.findByName(Config.INSTANCE_MINIMUM_COST_APPLICABLE_HOUR);
                HashMap<String,String> planList = new HashMap<String,String>(); 
//                 double readGB = virtualMachine.networkRead / 1048576
                for(def currentVm: currentVmList) {
                    
                    def computingOffer = ComputingOffer.findByOfferReferenceId(currentVm.referencePlanId)
                    def virtualMachine = VirtualMachine.findWhere(referenceId: currentVm.referenceItemId, computingOffer: computingOffer);
                    
                    if(virtualMachine) {
                        boolean blnExists = planList.containsKey(currentVm.referenceItemId);
                        if(blnExists.toString() == "false" || blnExists == false) {
                            if(virtualMachine.zone.networkType == "Basic") {
                                double planBandwidth = 1.0 * virtualMachine.computingOffer.bandWidth

                                def bandWidthCost = MiscellaneousOfferZoneCost.findWhere(miscellaneousOffer :MiscellaneousOffer.get(1), zone :virtualMachine.zone);  
                                def bandWidthCostValue = bandWidthCost ? bandWidthCost.cost : 0
                                
                                def usageUnits = (virtualMachine.networkWrite / 1048576) - planBandwidth
                                def vmband = virtualMachine.networkWrite / 1048576
                                if(vmband > planBandwidth) {
                                    double usageAmount = usageUnits * bandWidthCostValue                          
                                    totalBandwidthCost = totalBandwidthCost + usageAmount
                                    totalBandwidth = totalBandwidth + usageUnits
                                }
                            }
                        }
                        planList.put(currentVm.referenceItemId ,"referenceId");
                    }
                    
                    def oldComputeroffer = ComputingOffer.findByOfferReferenceId(currentVm.referencePlanId)
                
                    def stopInstanceUsageCriteria = OfferingUsage.createCriteria()
                        def stopInstanceHours = stopInstanceUsageCriteria.get {
                            eq("usageType", UsageType.values()[2])
                            and {
                              eq("offeringId", currentVm.referencePlanId)  and { eq("virtualMachineId", currentVm.referenceItemId) }
                              and { ge("startDate", invoice.previousInvoiceDate) and { le("startDate", invoice.date) } }
                            }
                            projections {
                                sum("hours")
                            }
                        }
                        if(stopInstanceHours == null || stopInstanceHours == "null") {

                        } else {
                            double hours = stopInstanceHours - currentVm.usageUnits;
                            if(hours >= Double.parseDouble(minHour.value)) {
                                def mincost = ComputingOfferZoneCost.findWhere(computingOffer: oldComputeroffer).minimumCost 
                                stopVmCost = stopVmCost + (hours * mincost)
                            }
                        }
                
                        runningVmCost = runningVmCost + currentVm.totalAmount
                }
                
                accountItem.put("totalBandwidth", Math.round(totalBandwidth*100)/100);
                accountItem.put("runningVmCost", Math.ceil(runningVmCost * 100d) / 100d);
                accountItem.put("totalBandwidthCost",  Math.ceil(totalBandwidthCost * 100d) / 100d);
                accountItem.put("totalDiskCost", Math.ceil(totalDiskCost * 100d) / 100d);
                accountItem.put("totalSnapCost", Math.ceil(totalSnapCost * 100d) / 100d);
                accountItem.put("stopVmCost", Math.ceil(stopVmCost * 100d) / 100d);
                
                if(invoice) {
                    accountItem.put("invoice", "yes");
                    accountItem.put("nextBillingData", dateFormat.format(account.nextBillingData).toString()); 
                    accountItem.put("invoiceId", invoice.id);
                    accountItem.put("currentDue", invoice.currentDue);
                    accountItem.put("totalAmount", invoice.totalAmount);
                    accountItem.put("previousBalance", invoice.previousBalance);
                    accountItem.put("payments", invoice.payment);
                    accountItem.put("credit", account.credit);
                    accountItem.put("dailyDate", dateFormat.format(fromDate).toString());
                    accountItem.put("paymentPeriod", dateFormat.format(invoice.previousInvoiceDate).toString() + " - " + dateFormat.format(invoice.date).toString());
                } else {
                    accountItem.put("nextBillingData", "-"); 
                    accountItem.put("invoice", "no");
                    accountItem.put("invoiceId", "-");
                    accountItem.put("currentDue", 0);
                    accountItem.put("totalAmount", 0);
                    accountItem.put("previousBalance", 0);
                    accountItem.put("payments", 0);
                    accountItem.put("credit", account.credit);
                    accountItem.put("paymentPeriod", "-");
                    accountItem.put("dailyDate", "-");
                }
        
                if(account.totalPayable < 0) {
                  accountItem.put("refund", "yes");   
                } else {
                   accountItem.put("refund", "no");    
                }
                accountInfo.add(accountItem)
            return accountInfo;             
    }
    
    def getCurrentAccount() {       
       def currency = ApplicationHolder.getApplication().config.billing.defaultCurrency
        try {
            def result = springSecurityService.currentUser 
            def caccount = result.account                   
         
            
//            def result = Account.findByAccountIdentifier(name);
            def userDateFormat = ""
            if(result.dateFormat) {
                userDateFormat = result.dateFormat
            } else {
                Config dateFormat =  Config.findByName(Config.DATE_FORMAT);
                userDateFormat = dateFormat.value
            }            
            SimpleDateFormat dateFormat = new SimpleDateFormat(userDateFormat);
            
            
            ArrayList<ArrayList<String>> account = new ArrayList<ArrayList<String>>();
            HashMap accountItem = new HashMap();            
                accountItem.put("id", result.account.id);
                accountItem.put("dateFormat", result.dateFormat);                
                accountItem.put("userName", result.account.accountIdentifier);
                accountItem.put("domainName", result.account.domain?.name);
                accountItem.put("activationDate", result.account.activationDate);
                accountItem.put("signUpDate", dateFormat.format(result.account.signUpDate).toString());
                accountItem.put("status", "active");
                accountItem.put("city", result.account.city);
                accountItem.put("streetAddress", result.account.streetAddress);
                accountItem.put("streetAddress2", result.account.streetAddress1);
                accountItem.put("state", result.account.state.stateName);
                accountItem.put("zip", result.account.zip);
                accountItem.put("country", result.account.country.countryName);
                accountItem.put("countryId", result.account.country.id);
                accountItem.put("countryCallingCode", result.account.country.callingCode);
                accountItem.put("singleSignOnUrl", ApplicationHolder.getApplication().config.cloudstack.singleSignOnUrl);
                
                accountItem.put("stateId", result.account.state.id);
                accountItem.put("lastName", result.account.lastName);
                accountItem.put("email", result.account.email); 
                accountItem.put("firstName", result.account.firstName);
                accountItem.put("companyName", result.account.companyName);
                accountItem.put("billingCountry", result.account.billingCountry.countryName);
                accountItem.put("billingCountryId", result.account.billingCountry.id);
                accountItem.put("billingCountryCallingCode", result.account.billingCountry.callingCode);
                
                accountItem.put("billingCity", result.account.billingCity);
                accountItem.put("billingAddress", result.account.billingStreetAddress);
                accountItem.put("billingAddress2", result.account.billingStreetAddress1);
                
                accountItem.put("billingState", result.account.billingState.stateName);
                accountItem.put("billingStateId", result.account.billingState.id);
                accountItem.put("accountType", result.account.accountType.name());
               
                def cloudHealth = getCloudHealth(result);
                
                accountItem.put("cloudHealth", cloudHealth);
                accountItem.put("billingZip", result.account.billingZip);
                accountItem.put("phoneNumber", result.account.phoneNumber);
                accountItem.put("billingPhoneNumber", result.account.billingPhoneNumber);
                accountItem.put("cardVerified", result.account.cardVerified);
                accountItem.put("currency", currency);
               
                
                Calendar startDateCalendar = Calendar.getInstance();
                startDateCalendar.add(Calendar.DATE, -1); 
                startDateCalendar.set(Calendar.HOUR_OF_DAY, 00);
                startDateCalendar.set(Calendar.MINUTE, 00);
                startDateCalendar.set(Calendar.SECOND, 00);
                startDateCalendar.set(Calendar.MILLISECOND, 00);   
                
                Calendar toDateCalendar = Calendar.getInstance();
                toDateCalendar.add(Calendar.DATE, -1);
                toDateCalendar.set(Calendar.HOUR_OF_DAY, 23);
                toDateCalendar.set(Calendar.MINUTE, 59);
                toDateCalendar.set(Calendar.SECOND, 59);
                toDateCalendar.set(Calendar.MILLISECOND, 999);          
            
                Date fromDate = startDateCalendar.getTime()
                Date toDate = toDateCalendar.getTime()
                
                def daily;
                def offeringUsageCriteria = OfferingUsage.createCriteria()
                daily = offeringUsageCriteria.get {
                    and { ge("startDate", fromDate) and { le("startDate", toDate) } 
                        and {
                            eq("account", result.account)
                        }
                    }
                    projections {
                        sum("cost")
                    } 
                }
                                            
                if(daily == "null" ||daily == null) {
                    accountItem.put("daily", 0);
                } else {
                    accountItem.put("daily",  Math.ceil(daily * 100d) / 100d);  
                } 
                
                def invoice = Invoice.findWhere(account: result.account, status: InvoiceStatus.values()[6])
                                
                def currentVmList = InvoiceItem.findAllWhere(invoice: invoice, billableItem:BillableItem.get(1))
                def totalBandwidth = 0;
                def totalBandwidthCost = 0;
                def runningVmCost = 0;
                def stopVmCost = 0;
                def totalDiskCost = 0;
                def totalSnapCost = 0;
            
                def bandwidthCostCriteria = InvoiceItem.createCriteria()
                def bandCost = bandwidthCostCriteria.list {
                    eq("invoice", invoice)
                    and {
                        eq("billableItem", BillableItem.get(5))
                    }
                }
                for(def bandItem: bandCost) {
                    totalBandwidth += bandItem.usageUnits
                    totalBandwidthCost += bandItem.totalAmount
                }
                
                def diskCostCriteria = InvoiceItem.createCriteria()
                def diskCost = diskCostCriteria.list {
                    eq("invoice", invoice)
                    and {
                        eq("billableItem", BillableItem.get(2))
                    }
                    projections {
                         sum("totalAmount")
                     }
                }
                
                if(diskCost[0] == null || diskCost[0] == "null") {
                    totalDiskCost =  0
                } else {
                    totalDiskCost =  diskCost[0]
                }
                                
                def snapCriteria = InvoiceItem.createCriteria()
                def snapCost = snapCriteria.list {
                    eq("invoice", invoice)
                    and {
                        eq("billableItem", BillableItem.get(3))
                    }
                    projections {
                         sum("totalAmount")
                     }
                }
                
                
                if(snapCost[0] == null || snapCost[0] == "null") {
                    totalSnapCost =  0
                } else {
                    totalSnapCost =  snapCost[0]
                }
                Config minHour =  Config.findByName(Config.INSTANCE_MINIMUM_COST_APPLICABLE_HOUR);
                HashMap<String,String> planList = new HashMap<String,String>(); 
//                 double readGB = virtualMachine.networkRead / 1048576
                for(def currentVm: currentVmList) {
                    
                    def computingOffer = ComputingOffer.findByOfferReferenceId(currentVm.referencePlanId)
                    def virtualMachine = VirtualMachine.findWhere(referenceId: currentVm.referenceItemId, computingOffer: computingOffer);
                    
                    if(virtualMachine) {
                        boolean blnExists = planList.containsKey(currentVm.referenceItemId);
                        if(blnExists.toString() == "false" || blnExists == false) {
                            
                            if(virtualMachine.zone.networkType == "Basic") {
                                double planBandwidth = 1.0 * virtualMachine.computingOffer.bandWidth

                                def bandWidthCost = MiscellaneousOfferZoneCost.findWhere(miscellaneousOffer :MiscellaneousOffer.get(1), zone :virtualMachine.zone);  
                                def bandWidthCostValue = bandWidthCost ? bandWidthCost.cost : 0
                                                            
                                def usageUnits = (virtualMachine.networkWrite / 1048576) - planBandwidth
                                def vmband = virtualMachine.networkWrite / 1048576
                                if(vmband > planBandwidth) {
                                    double usageAmount = usageUnits * bandWidthCostValue                          
                                    totalBandwidthCost = totalBandwidthCost + usageAmount
                                    totalBandwidth = totalBandwidth + usageUnits
                                }
                            }
                        }
                        planList.put(currentVm.referenceItemId ,"referenceId");
                    }
                    
                    def oldComputeroffer = ComputingOffer.findByOfferReferenceId(currentVm.referencePlanId)
                
                    def stopInstanceUsageCriteria = OfferingUsage.createCriteria()
                        def stopInstanceHours = stopInstanceUsageCriteria.get {
                            eq("usageType", UsageType.values()[2])
                            and {
                              eq("offeringId", currentVm.referencePlanId)  and { eq("virtualMachineId", currentVm.referenceItemId) }
                              and { ge("startDate", invoice.previousInvoiceDate) and { le("startDate", invoice.date) } }
                            }
                            projections {
                                sum("hours")
                            }
                        }
                        if(stopInstanceHours == null || stopInstanceHours == "null") {

                        } else {
                            double hours = stopInstanceHours - currentVm.usageUnits;
                            if(hours >= Double.parseDouble(minHour.value)) {
                                def mincost = ComputingOfferZoneCost.findWhere(computingOffer: oldComputeroffer).minimumCost 
                                stopVmCost = stopVmCost + (hours * mincost)
                            }
                        }
                
                        runningVmCost = runningVmCost + currentVm.totalAmount
                }
                
                accountItem.put("totalBandwidth", Math.round(totalBandwidth*100)/100);
                accountItem.put("runningVmCost", Math.ceil(runningVmCost * 100d) / 100d);
                accountItem.put("totalBandwidthCost",  Math.ceil(totalBandwidthCost * 100d) / 100d);
                accountItem.put("totalDiskCost", Math.ceil(totalDiskCost * 100d) / 100d);
                accountItem.put("totalSnapCost", Math.ceil(totalSnapCost * 100d) / 100d);
                accountItem.put("stopVmCost", Math.ceil(stopVmCost * 100d) / 100d);
                
                // add credit limit 0 condition 
                
                accountItem.put("maxCreditLimit", result.account.creditLimit);
                if(result.account.creditLimit == 0) {
                    def creditLimit = result.account.totalPayable / result.account.creditLimit
                    accountItem.put("creditLimit", 0);
                } else {
                    def creditLimit = result.account.totalPayable / result.account.creditLimit
                    accountItem.put("creditLimit", Math.ceil(creditLimit * 100));
                }
                
                if(invoice) {
                    accountItem.put("invoice", "yes");
                    accountItem.put("nextBillingData", dateFormat.format(result.account.nextBillingData).toString()); 
                    accountItem.put("invoiceId", invoice.id);
                    accountItem.put("currentDue", invoice.currentDue);
                    accountItem.put("totalAmount", invoice.totalAmount);
                    accountItem.put("previousBalance", invoice.previousBalance);
                    accountItem.put("payments", invoice.payment);
                    accountItem.put("credit", result.account.credit);
                    accountItem.put("dailyDate", dateFormat.format(fromDate).toString());
                    accountItem.put("paymentPeriod", dateFormat.format(invoice.previousInvoiceDate).toString() + " - " + dateFormat.format(invoice.date).toString());
                } else {
                    
                    def invoiceCriteria = Invoice.createCriteria()
                    def invoiceList = invoiceCriteria.list {
                        eq("account", result.account) 
                        order("id", "desc")
                    }  
                    def finalInvoice = invoiceList[0]
                
                    if(result.account.status.name() == "CANCELED") {
                        accountItem.put("invoice", "yes");
                        accountItem.put("nextBillingData", ""); 
                        accountItem.put("invoiceId", finalInvoice.id);
                        accountItem.put("currentDue", finalInvoice.currentDue);
                        accountItem.put("totalAmount", finalInvoice.totalAmount);
                        accountItem.put("previousBalance", finalInvoice.previousBalance);
                        accountItem.put("payments", finalInvoice.payment);
                        accountItem.put("credit", result.account.credit);
                        accountItem.put("dailyDate", "");
                        accountItem.put("paymentPeriod", dateFormat.format(finalInvoice.previousInvoiceDate).toString() + " - " + dateFormat.format(finalInvoice.date).toString());
                    } else {
                
                        accountItem.put("nextBillingData", ""); 
                        accountItem.put("invoice", "no");
                        accountItem.put("invoiceId", "-");
                        accountItem.put("currentDue", 0);
                        accountItem.put("totalAmount", 0);
                        accountItem.put("previousBalance", 0);
                        accountItem.put("payments", 0);
                        accountItem.put("credit", result.account.credit);
                        accountItem.put("paymentPeriod", "");
                        accountItem.put("dailyDate", "");
                    }
                }
                account.add(accountItem)
            return account;                
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }    
    }
    
    def getCloudHealth(user) {
        
        Calendar fromDateCalendar = Calendar.getInstance(); 
        fromDateCalendar.set(Calendar.HOUR_OF_DAY, 00);
        fromDateCalendar.set(Calendar.MINUTE, 00);
        fromDateCalendar.set(Calendar.SECOND, 00);
        fromDateCalendar.set(Calendar.MILLISECOND, 000);                   
        Date fromDate = fromDateCalendar.getTime()

        Calendar toDateCalendar = Calendar.getInstance(); 
        toDateCalendar.set(Calendar.HOUR_OF_DAY, 23);
        toDateCalendar.set(Calendar.MINUTE, 59);
        toDateCalendar.set(Calendar.SECOND, 59);
        toDateCalendar.set(Calendar.MILLISECOND, 999);                   
        Date toDate = toDateCalendar.getTime()
        
     
        def cloudMaintenanceCriteria = CloudMaintenance.createCriteria()
        def cloudMaintenance = cloudMaintenanceCriteria.list {
            ge("date", fromDate)
            and { 
                le("date", toDate) 
                and {
                    eq("deleted", false) 
                }
            } 
        }
        if(!cloudMaintenance) {
            return "false"
        }
        def vmList = VirtualMachine.findAllWhere(owner: user.account, deleted:false)
        
        if(vmList.size() == 0) {
            return "no vm";
        }
        for(def maintenceDate: cloudMaintenance) {
            for(def vm: vmList) {
                for(Iterator k = maintenceDate.zones.iterator();k.hasNext();) {
                    def zone = k.next();
                    if(vm.zone.referenceZoneId == zone.referenceZoneId) {
                        return "true";
                    }     
                }
            }
        }
        return "false";
        
    }
    
    def resendconfirmMail(requestBody) {
        ArrayList<ArrayList<String>> forgotPassword = new ArrayList<ArrayList<String>>();  
        HashMap<String,String> item = new HashMap<String,String>(); 
        try {
            def applicationUrl = ApplicationHolder.getApplication().config.cloudstack.applicationUrl
            def requestData = JSON.parse(requestBody);
            Date date = new Date();                            
            def time = new Timestamp(date.getTime())
            def token = requestData.email + date.toString()
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(token.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            } 
                        
            def user = User.findByUsername(requestData.email)
            user.token = hashtext.toString();
            Calendar calendar = Calendar.getInstance(); 
            Config linkActiveTime =  Config.findByName(Config.LINK_ACTIVE_TIME);
            calendar.add(Calendar.HOUR, Integer.parseInt(linkActiveTime.value));
            user.tokenExpiryDate = calendar.getTime() 
            user.save(flush:true); 
            
            def verifyLink = applicationUrl + "/account/confirmAccount/" + hashtext.toString()
            Map tempalteMap = notificationService.getDefaultMailTemplateMap()
            tempalteMap.put("userName" , user.account.firstName)
            tempalteMap.put("verifyLink" , verifyLink)

            notificationService.send(user.account.email.toString(), "verificationEmail.subject.ftl", tempalteMap, "verificationEmail.ftl")
            item.put("Result",  GeneralConstants.RESULT_SUCCESS);
            forgotPassword.add(item);
            return forgotPassword
        } catch(Exception ex) {
             ex.printStackTrace(System.err);
            throw ex;
         }
        
    }
     def updateMailTemplate(String requestBody) {
        try {     
            def requestData = JSON.parse(requestBody);
            def mailTemplate =  MailTemplate.get(requestData.id);    
            ArrayList<ArrayList<String>> mailTemplateList = new ArrayList<ArrayList<String>>();            
            HashMap<String,String> item = new HashMap<String,String>(); 
            mailTemplate.title = requestData.title;
            mailTemplate.content = requestData.content;
            mailTemplate.subject = requestData.subject;
            mailTemplate.hasHeader = requestData.hasHeader;
            mailTemplate.hasFooter = requestData.hasFooter;
//            mailTemplate.hasSignature = requestData.hasSignature;
            
            mailTemplate.save(flush: true);
            if (mailTemplate.hasErrors()) {
                throw new ValidationException(mailTemplate.errors.allErrors);
            }  
            item.put("Result",  GeneralConstants.RESULT_SUCCESS);
            item.put("title",  mailTemplate.title);
            
            mailTemplateList.add(item); 
            return mailTemplateList;
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }    
        
    }  
    def forgotPasswordMail(String requestBody) {
        try {          
            def requestData = JSON.parse(requestBody);
            def applicationUrl = ApplicationHolder.getApplication().config.cloudstack.applicationUrl
            
            def defaultDomain = Domain.findByIsDefault(true);
            User user = User.findWhere(username: requestData.email, domain: defaultDomain);
            def language = "";
            if(user) {
                if(user.account.preferredLanguage == "" || user.account.preferredLanguage == "null" || user.account.preferredLanguage == null) {
                    Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
                    language = defaultLanguage.value
                } else {
                    language = user.account.preferredLanguage
                }
            } else {
                Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
                language = defaultLanguage.value
            }            
            
            ArrayList<ArrayList<String>> forgotPassword = new ArrayList<ArrayList<String>>();            
            HashMap<String,String> item = new HashMap<String,String>(); 
            if(user) {
                if(user.account.status.name() == "ACTIVE") {
                    Date date = new Date();                            
                    def time = new Timestamp(date.getTime())
                    def token = requestData.email + date.toString()
                    MessageDigest md = MessageDigest.getInstance("MD5");
                    byte[] messageDigest = md.digest(token.getBytes());
                    BigInteger number = new BigInteger(1, messageDigest);
                    String hashtext = number.toString(16);
                    // Now we need to zero pad it if you actually want the full 32 chars.
                    while (hashtext.length() < 32) {
                        hashtext = "0" + hashtext;
                    }                
                    user.token = hashtext.toString();
                    Calendar calendar = Calendar.getInstance(); 
                    Config linkActiveTime =  Config.findByName(Config.LINK_ACTIVE_TIME);
                    calendar.add(Calendar.HOUR, Integer.parseInt(linkActiveTime.value));
                    user.tokenExpiryDate = calendar.getTime() 
                    user.save(flush:true); 
                    
                    def verifyLink = applicationUrl + "/account/resetPassword/" + hashtext.toString()
                    Map tempalteMap = notificationService.getDefaultMailTemplateMap()
                    tempalteMap.put("userName" , user.account.firstName)
                    tempalteMap.put("verifyLink" , verifyLink)
                              
                    notificationService.send(user.account.email.toString(), "forgotPassword.subject.ftl", tempalteMap, "forgotPassword.ftl")
                    item.put("Result",  messageSource.getMessage('common.ok', null, new Locale(language)));
                    forgotPassword.add(item);                    
                } else {
                    item.put("Result",  messageSource.getMessage('common.account.resetPassword.error', null, new Locale(language)));
                    forgotPassword.add(item);
                }                
            } else {
                item.put("Result", messageSource.getMessage('common.account.resetPassword.noUser', null, new Locale(language)));
                forgotPassword.add(item);
            }
            return forgotPassword;
            
        } catch(Exception ex) {
             ex.printStackTrace(System.err);
            throw ex;
         }
    }
    def sendSettingsInfoMail(String requestBody) {
        try {        
            def requestData = JSON.parse(requestBody);
            def user = springSecurityService.currentUser
            def role = springSecurityService.getPrincipal().getAuthorities() 
            def applicationUrl = ApplicationHolder.getApplication().config.cloudstack.applicationUrl
            def templateName = ""
            def subject = ""
            
            ArrayList<ArrayList<String>> sendMailResultList = new ArrayList<ArrayList<String>>();            
            HashMap<String,String> item = new HashMap<String,String>(); 
            if(user) {  
                Map tempalteMap = notificationService.getDefaultMailTemplateMap()                                
                if(requestData.type == "accountSettingGeneralInfoUpdate") {                    
                    tempalteMap.put("firstName",  requestData.firstName)
                    tempalteMap.put("lastName", requestData.lastName)
                    tempalteMap.put("address1", requestData.streetAddress)
                    tempalteMap.put("address2",requestData.streetAddress1)
                    tempalteMap.put("city", requestData.city)
                    tempalteMap.put("country", requestData.country)
                    tempalteMap.put("stat", requestData.state)
                    tempalteMap.put("phone", requestData.phoneNumber)
                    tempalteMap.put("zip", requestData.zip)     
                    tempalteMap.put("userName", user.username)     
                    templateName = "accountSettingGeneralInfoUpdate.ftl"
                    subject = "accountSettingGeneralInfoUpdate.subject.ftl"
                } else if(requestData.type == "accountSettingBillingInfoUpdate") {                     
                    tempalteMap.put("address1", requestData.billingStreetAddress)
                    tempalteMap.put("address2",requestData.billingStreetAddress1)
                    tempalteMap.put("email", requestData.email)
                    tempalteMap.put("city", requestData.city)
                    tempalteMap.put("country", requestData.country)
                    tempalteMap.put("stat", requestData.billingState)
                    tempalteMap.put("phone", requestData.billingPhoneNumber)
                    tempalteMap.put("zip", requestData.billingZip)                          
                    templateName = "accountSettingBillingInfoUpdate.ftl"
                    subject = "accountSettingBillingInfoUpdate.subject.ftl"
                }                                                  
                
                notificationService.send(user.account.email.toString(), subject, tempalteMap, templateName)
                item.put("Result",  GeneralConstants.RESULT_SUCCESS);
                sendMailResultList.add(item);                              
            } else {
                item.put("Result",  "No user found");
                sendMailResultList.add(item);
            }
            return sendMailResultList;            
        } catch(Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }    
    }
    def update(String requestBody) {
        try { 
            def requestData = JSON.parse(requestBody)
            def user = springSecurityService.currentUser
            ArrayList<ArrayList<String>> AccountResultList = new ArrayList<ArrayList<String>>();  
            HashMap<String,String> optional = new HashMap<String,String>();
            def oldAccount = Account.findById(requestData.id); 
            if(requestData.type == "general") {                                
                oldAccount.firstName = requestData.firstName;
                oldAccount.lastName = requestData.lastName;
                
                user.dateFormat = requestData.dateFormat
                user.save(flush: true)             
                if (user.hasErrors()) {
                    throw new ValidationException(user.errors.allErrors);
                }
                if(!requestData.userType) {
                    oldAccount.streetAddress = requestData.street                
                    oldAccount.streetAddress1 = requestData.street1    
                    oldAccount.city = requestData.city                
                    oldAccount.country = Country.findById(requestData.country)            
                    oldAccount.state = State.findById(requestData.state)                   
                    oldAccount.phoneNumber = requestData.phone
                    oldAccount.zip = requestData.zip                       
                    optional.put("firstName",  oldAccount.firstName);
                    optional.put("lastName",  oldAccount.lastName);
                   
                    optional.put("streetAddress",  oldAccount.streetAddress);
                    optional.put("streetAddress1",  oldAccount.streetAddress1);
                    optional.put("city",  oldAccount.city);
                    optional.put("country",  oldAccount.country);
                    optional.put("state",  oldAccount.state);                
                    optional.put("phoneNumber",  oldAccount.phoneNumber);      
                    optional.put("zip",  oldAccount.zip);  
                }                                                
            } else if(requestData.type == "billingInfo") {                
                oldAccount.billingStreetAddress = requestData.street
                oldAccount.billingStreetAddress1 = requestData.street1                
                oldAccount.companyName = requestData.companyName;
                oldAccount.billingCity = requestData.city
                oldAccount.billingZip = requestData.zip       
                oldAccount.billingPhoneNumber = requestData.phone                       
                oldAccount.billingState = State.findById(requestData.state)
                oldAccount.billingCountry = Country.findById(requestData.country)
                oldAccount.email = requestData.email;
                
                optional.put("billingStreetAddress",  oldAccount.billingStreetAddress);
                optional.put("billingStreetAddress1",  oldAccount.billingStreetAddress1);
                optional.put("billingCity",  oldAccount.billingCity);
                optional.put("billingZip",  oldAccount.billingZip);
                optional.put("billingPhoneNumber",  oldAccount.billingPhoneNumber);
                optional.put("billingState",  oldAccount.billingState);
                optional.put("billingCountry",  oldAccount.billingCountry);
                optional.put("billingCompanyName",  oldAccount.companyName);  
                optional.put("email",  oldAccount.email);
            } else {
                throw new AccountValidationException("failed");  
            }         
             oldAccount.save(flush: true)
             
            if (oldAccount.hasErrors()) {
                throw new ValidationException(oldAccount.errors.allErrors);
            }
            optional.put("result",  "OK");
            AccountResultList.add(optional)
            
            log.info("${requestData.type} info update for account ${oldAccount.id}") 
                        
            return AccountResultList;
        } catch (AccountValidationException ex) {
            def user = springSecurityService.currentUser
            def language;
            def role = springSecurityService.getPrincipal().getAuthorities()                
            if(user.account.preferredLanguage == "" || user.account.preferredLanguage == "null" || user.account.preferredLanguage == null) {
                Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
                language = defaultLanguage.value
            } else {
                language = user.account.preferredLanguage
            }                                            
            throw new AccountValidationException(messageSource.getMessage('signup.accountType.invalid', null, new Locale(language)));
         } catch(Exception ex) {
             ex.printStackTrace(System.err);
            throw ex;
         }
     }
     
    def cardVerified() {
        def currentUser = springSecurityService.currentUser 
        def role = springSecurityService.getPrincipal().getAuthorities()

        ArrayList<ArrayList<String>> cardVerification = new ArrayList<ArrayList<String>>();            
        HashMap<String,String> item = new HashMap<String,String>(); 

        if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {
            if(currentUser.account.cardVerified == false || currentUser.account.cardVerified == "false") {
                if(currentUser.account.accountType == AccountType.values()[0]) {
                    item.put("cardVerified",  GeneralConstants.RESULT_SUCCESS);
                    item.put("defaultCardEnabled", currentUser.account.cardVerified.toString());
                    cardVerification.add(item);
                } else {
                    item.put("cardVerified",  GeneralConstants.RESULT_FAILURE);
                    item.put("defaultCardEnabled", currentUser.account.cardVerified.toString());
                    cardVerification.add(item); 
                } 
            } else if(currentUser.account.cardVerified == true || currentUser.account.cardVerified == "true") {
                item.put("cardVerified",  GeneralConstants.RESULT_SUCCESS);
                item.put("defaultCardEnabled", currentUser.account.cardVerified.toString());
                cardVerification.add(item);
            } 
            return cardVerification;
         }
    }
    
    
    def enableAccount(requestBody) {
        
        ArrayList<ArrayList<String>> enableAccount = new ArrayList<ArrayList<String>>();            
        HashMap<String,String> item = new HashMap<String,String>(); 
        Date date = new Date()
        def time = new Timestamp(date.getTime())               
        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret
        
        def requestData = JSON.parse(requestBody)
        
        def account = Account.get(Integer.parseInt(requestData.accountId));
        def applicationUrl = ApplicationHolder.getApplication().config.cloudstack.applicationUrl
        try {  
            def user = springSecurityService.currentUser 
            def role = springSecurityService.getPrincipal().getAuthorities()
            // convert string to json object
            

            if(role.iterator().next() == "ROLE_ADMIN" ) {
                
                CloudStackServer server =
                        new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);
                CSAccountService csAccService = new CSAccountService(server);
                
                HashMap<String,String> optional = new HashMap<String,String>();
                optional.put("account", new String(account.accountIdentifier));
                optional.put("domainid", account.domain.referenceId);
                def response = csAccService.enableAccount(optional)
                account.status = Status.values()[0]
                account.save(flush: true);
                                
                def log = new Log()
                log.account = account
                log.user = User.findByUsername(account.accountIdentifier)
                log.date = time
                log.type = LogType.values()[3]
                log.mailSend = true
                log.description = "Account " + account.accountIdentifier + " is enabled"
                log.save(flush: true);               
                Map tempalteMap = notificationService.getDefaultMailTemplateMap()       
                tempalteMap.put("firstName", account.firstName)                      
                notificationService.send(account.email.toString(), "accountEnabled.subject.ftl", tempalteMap, "accountEnabled.ftl")                                  
                item.put("result",  GeneralConstants.RESULT_SUCCESS);
                enableAccount.add(item);
                
            } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {
               throw new Exception("access denied");
            }
            log.info("Account: ${account.id} is enabled by user ${user.id}")
            return enableAccount
        } catch (Exception ex) {
            log.info("Account: ${requestData.accountId}  enable failed due to ${ex}")
             throw ex;   
        } 
    }
    
    
    def suspendAccount(requestBody) {
        
        ArrayList<ArrayList<String>> suspendAccount = new ArrayList<ArrayList<String>>();            
        HashMap<String,String> item = new HashMap<String,String>(); 
        Date date = new Date()
        def time = new Timestamp(date.getTime())              
        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret
        def applicationUrl = ApplicationHolder.getApplication().config.cloudstack.applicationUrl
        try {  
            def user = springSecurityService.currentUser 
            def role = springSecurityService.getPrincipal().getAuthorities()
            // convert string to json object
            def requestData = JSON.parse(requestBody)
            
            def account = Account.get(Integer.parseInt(requestData.accountId));

            if(role.iterator().next() == "ROLE_ADMIN" ) {
                
                CloudStackServer server =
                        new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);
                CSAccountService csAccService = new CSAccountService(server);
                
                HashMap<String,String> optional = new HashMap<String,String>();
                optional.put("account", new String(account.accountIdentifier));
                optional.put("domainid", account.domain.referenceId);
                def response = csAccService.disableAccount("false", optional)
                account.status = Status.values()[5]
                account.save(flush: true);
                
                def log = new Log()
                log.account = account
                log.user = User.findByUsername(account.accountIdentifier)
                log.date = time
                log.type = LogType.values()[0]
                log.mailSend = true
                log.description = "Account " + account.accountIdentifier + "is suspended due to" + requestData.suspendTextContent
                log.save(flush: true);                                                
                Map tempalteMap = notificationService.getDefaultMailTemplateMap()    
                tempalteMap.put("firstName", account.firstName)
                tempalteMap.put("message", requestData.suspendTextContent)                
                notificationService.send(account.email, "suspendPage.subject.ftl", tempalteMap, "suspendPage.ftl")  
                item.put("result",  GeneralConstants.RESULT_SUCCESS);
                suspendAccount.add(item);
                
            } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {
               throw new Exception("access denied");
            }
            log.info("Account: ${account.id} is suspended by user ${user.id}")
            return suspendAccount
        } catch (Exception ex) {
             throw ex;   
        } 
    }
    
    def disableAccount(requestBody) {
        
        ArrayList<ArrayList<String>> disableAccount = new ArrayList<ArrayList<String>>();            
        HashMap<String,String> item = new HashMap<String,String>(); 
        Date date = new Date()
        def time = new Timestamp(date.getTime())              
        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret
        
        try {  
            def user = springSecurityService.currentUser 
            def role = springSecurityService.getPrincipal().getAuthorities()
            // convert string to json object
            def requestData = JSON.parse(requestBody)
            
            def account = Account.get(Integer.parseInt(requestData.accountId));

            if(role.iterator().next() == "ROLE_ADMIN" ) {
                
                CloudStackServer server =
                        new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);
                CSAccountService csAccService = new CSAccountService(server);
                
                HashMap<String,String> optional = new HashMap<String,String>();
                optional.put("account", new String(account.accountIdentifier));
                optional.put("domainid", account.domain.referenceId);
                def response = csAccService.disableAccount("false", optional)
                account.status = Status.values()[3]
                account.save(flush: true);
                
                notificationService.send(account.email.toString(), "Account Notification", "<h1>Your account is Disabled</h1>");
                                
                def log = new Log()
                log.account = account
                log.user = User.findByUsername(account.accountIdentifier)
                log.date = time
                log.type = LogType.values()[2]
                log.mailSend = false
                log.description = "Account " + account.accountIdentifier + "is disabled"
                log.save(flush: true);
                
                
                item.put("result",  GeneralConstants.RESULT_SUCCESS);
                disableAccount.add(item);
                
            } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {
               throw new Exception("access denied");
            }
            log.info("Account: ${account.id} is disabled by user ${user.id}")    
            return disableAccount
        } catch (Exception ex) {
             throw ex;   
        } 
    }
    
    
    def cancelAccount(requestBody) {
       
       try {  
            def user = springSecurityService.currentUser 
            def role = springSecurityService.getPrincipal().getAuthorities()
            // convert string to json object
            def requestData = JSON.parse(requestBody)

            if(role.iterator().next() == "ROLE_ADMIN" ) {
                def account = Account.get(Integer.parseInt(requestData.accountId));
                return processForCancelAccount(account)
            } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {
                return processForCancelAccount(user.account)
            }
        
        }catch (Exception ex){
             throw ex;   
        } 
    }
    
    def processForCancelAccount(account) { 
        def user = springSecurityService.currentUser 
        ArrayList<ArrayList<String>> calcelAccount = new ArrayList<ArrayList<String>>();            
        HashMap<String,String> item = new HashMap<String,String>(); 
        Date date = new Date()
        def time = new Timestamp(date.getTime())        
        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret
        
        try {  
           
            CloudStackServer server =
                    new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);
            CSAccountService csAccService = new CSAccountService(server);
            HashMap<String,String> optional = new HashMap<String,String>();
            optional.put("account", new String(account.accountIdentifier));
            optional.put("domainid", account.domain.referenceId);
            def response = csAccService.disableAccount("false", optional)
                                    
            def invoice = Invoice.findWhere(account: account, status: InvoiceStatus.values()[6])
            
            if(invoice) {
                
                invoice.date = time
                invoice.save(flush: true)
                
                if(invoice.totalAmount == 0) {
                    account.totalPayable = invoice.totalAmount
                    account.nextBillingData = time
                    account.status = Status.values()[7]
                    account.save(flush: true);
                    log.info("Account: ${account.id} is closed by user ${user.id}")
                    sendMail(account, "accountClose")
                } else if(invoice.totalAmount < 0) {
                    account.totalPayable = invoice.totalAmount
                    account.nextBillingData = time
                    account.status = Status.values()[6]
                    account.save(flush: true);
                    log.info("Account: ${account.id} is cancelled by user ${user.id}")
                    sendMail(account, "accountCancelled")
                } else {
                    account.totalPayable = invoice.totalAmount
                    account.nextBillingData = time
                    account.status = Status.values()[6]
                    account.save(flush: true);
                    log.info("Account: ${account.id} is cancelled by user ${user.id}")
                    sendMail(account, "accountCancelled")
                }
            } else {
                account.nextBillingData = time
                account.status = Status.values()[7]
                account.save(flush: true);
                log.info("Account: ${account.id} is closed by user ${user.id}")
                sendMail(account, "accountClose")
            }
            
            def log = new Log()
            log.account = account
            log.user = User.findByUsername(account.accountIdentifier)
            log.date = time
            log.type = LogType.values()[3]
            log.mailSend = true
            log.description = "Account " + account.accountIdentifier + " is cancelled"
            log.save(flush: true);                                    
            item.put("result",  GeneralConstants.RESULT_SUCCESS);
            calcelAccount.add(item);
            deleteCancelAccountResources(account)
            return calcelAccount
            
        }catch (Exception ex){
             throw ex;   
        }
    }
    def sendMail(account, emailTemplateName) {        
        def template = ""
        def subject = ""
        if(emailTemplateName == "accountCancelled") {
            template = "accountCancelled.ftl"
            subject = "accountCancelled.subject.ftl"
        } else {
            template = "accountClose.ftl"
            subject = "accountClose.subject.ftl"

        }        
        
        Map tempalteMap = notificationService.getDefaultMailTemplateMap() 
        def user = User.findByAccount(account);
        tempalteMap.put("user", user)
        notificationService.send(account.email, subject, tempalteMap, template)                    
    }            
    def cleanCancelAccount() {   
        def accountList = Account.findAll()
        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret
        
        CloudStackServer server =
                 new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);
        
        CSVolumeService csVolumeService = new CSVolumeService(server);
        Calendar date = Calendar.getInstance();
        
        for(def account: accountList) { 
            
            if(account.status.name() == "CANCELED" || account.status.name() == "CLOSED") {
                
                def diskList = Volume.findAllWhere(deleted: false, type:"DATADISK", owner:account);

                for(def vol: diskList) {
                    if(vol.virtualMachine) {
                        try {
                            HashMap<String,String> optional = new HashMap<String,String>();
                            optional.put("id", vol.volumeReferenceId);
                            def response = csVolumeService().detachVolume(optional); 
                            log.info("Volume: ${vol.id} detached for closed account:${account.id} by user CancelAccountClean Cron")
                        } catch (Exception ex) {
                            log.info("Volume: ${vol.id} detached failed for closed account:${account.id} due to ${ex} by user CancelAccountClean Cron")
                        }  
                    }
                }
               removeVMForClosedAccount(account) 
            }
        }
        log.info("CancelAccountClean Cron Executed");
    }
    
    def deleteCancelAccountResources(account) {
        def vmList = VirtualMachine.findAllWhere(owner: account, deleted: false)
        def user = User.findByUsername(account.accountIdentifier)
        def snapList = Snapshot.findAllWhere(user: user, deleted: false)
        
        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret
        
        CloudStackServer server =
                 new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);
                
        CSVirtualMachineService csVirtualMachineService = new CSVirtualMachineService(server);
        
        CSSnapshotService csSnapshotService = new CSSnapshotService(server);
        
        for(def vm: vmList) {
            
            vm.deleted = true
            vm.save(flush:true);
            def response =  csVirtualMachineService.destroyVirtualMachine(vm.referenceId); 
        }
        
        for(def snap: snapList) {
            
            snap.deleted = true
            snap.save(flush:true);
            def response =  csSnapshotService.deleteSnapshot(snap.snapshotReferenceId);  
        }
        
        def ipList = VmIp.findAllWhere(account: account, inUse: true) 
        
        for(def ip : ipList) {
            ip.inUse = false
            ip.save(flush:true);
        }
        
        
    }
    
    def removeVMForClosedAccount(account) {
        
        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret
        
        CloudStackServer server =
                 new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);
                
        CSVirtualMachineService csVirtualMachineService = new CSVirtualMachineService(server);
        
        def vmList = VirtualMachine.findAllWhere(owner:account);
        
        for(def vm: vmList) {
            try {
                def response =  csVirtualMachineService.destroyVirtualMachine(vm.referenceId);
               log.info("Cancel account vm:${vm.id} deleted")
            } catch (Exception ex) {
                log.info("Cancel account instance clean failed due to ${ex}")
            } 
        }
        removeDiskForClosedAccount(account)
    }
    
    def removeDiskForClosedAccount(account) {
        
        
        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret
        
        CloudStackServer server =
                 new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);
                 
        
        CSVolumeService csVolumeService = new CSVolumeService(server);
        
        Date date = new Date()
        
        def diskList = Volume.findAllWhere(deleted: false, type:"DATADISK", owner:account);

        for(def vol: diskList) {
            try {
                vol.deleted = true
                vol.save(flush:true);
                def response = csVolumeService.deleteVolume(vol.volumeReferenceId);  
            } catch (Exception ex) {
                 log.info("Cancel account volume clean failed due to ${ex}")
            } 
        }
        log.info("Cancel Account Clean last run for account:${account.accountIdentifier}");
    }
            
    def resetPasswordForAccount(requestBody) {
       
        ArrayList<ArrayList<String>> passAccount = new ArrayList<ArrayList<String>>();            
        HashMap<String,String> item = new HashMap<String,String>();  
        
        try {  
            def currentAcc = springSecurityService.currentUser 
            def role = springSecurityService.getPrincipal().getAuthorities()
            // convert string to json object
            def requestData = JSON.parse(requestBody)

            if(role.iterator().next() == "ROLE_ADMIN" ) {

                def account = Account.get(Integer.parseInt(requestData.accountId));
                
//                account.password = requestData.confirmPassword;
//                account.save(flush:true);
                
                def user = User.findByUsername(account.accountIdentifier)
                
                def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
                def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
                def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret

                CloudStackServer server = new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey)
                CSUserService csUserService = new CSUserService(server);
                
                // forming key value optional pairs
                HashMap<String,String> userParams = new HashMap<String,String>();
                userParams.put("password", requestData.confirmPassword)
                
                csUserService.updateUser(user.uuid, userParams);
                
                user.password = requestData.confirmPassword;
                user.save(flush:true);
                log.info("Reset pasword for account:${account.id} by user ${currentAcc.id}")
                item.put("result",  GeneralConstants.RESULT_SUCCESS);
                passAccount.add(item);
                
                return passAccount
                
            } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {
                throw new Exception("access denied");
            }
           
        } catch (Exception ex){
             throw ex;   
        } 
    }
    
    
    def resetPasswordForAdminUser(requestBody) {
        
        ArrayList<ArrayList<String>> passAccount = new ArrayList<ArrayList<String>>();            
        HashMap<String,String> item = new HashMap<String,String>();  
        
        try {  
            def currentAcc = springSecurityService.currentUser 
            def role = springSecurityService.getPrincipal().getAuthorities()
            // convert string to json object
            def requestData = JSON.parse(requestBody)

            if(role.iterator().next() == "ROLE_ADMIN" ) {

                               
                def user = User.get(Long.parseLong(requestData.userId))
                user.password = requestData.confirmPassword;
                user.save(flush:true);
                log.info("Reset pasword for admin:${user.id} by user ${currentAcc.id}")
                item.put("result",  GeneralConstants.RESULT_SUCCESS);
                passAccount.add(item);
                
                return passAccount
                
            } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {
                throw new Exception("access denied");
            }
           
        }catch (Exception ex){
             throw ex;   
        } 
    }
    
    def lockAccount(requestBody) {
        
        ArrayList<ArrayList<String>> lockAccount = new ArrayList<ArrayList<String>>();            
        HashMap<String,String> item = new HashMap<String,String>();  
//        def currentUser = springSecurityService.currentUser 
        def role = springSecurityService.getPrincipal().getAuthorities()
            // convert string to json object
        def requestData = JSON.parse(requestBody)
        Date date = new Date()
        def time = new Timestamp(date.getTime())     
        if(role.iterator().next() == "ROLE_ADMIN" ) {
            
            def account = Account.get(Integer.parseInt(requestData.accountId));
            
            def user = User.findByUsername(account.accountIdentifier)
            user.accountLocked = true
            user.save(flush: true);
            
            notificationService.send(account.email.toString(), "Account Notification", "<h1>Your account is Locked</h1>");

            def log = new Log()
            log.account = account
            log.user = User.findByUsername(account.accountIdentifier)
            log.date = time
            log.type = LogType.values()[3]
            log.mailSend = false
            log.description = "Account " + account.accountIdentifier + " is locked"
            log.save(flush: true);
               
            item.put("result",  GeneralConstants.RESULT_SUCCESS);
            lockAccount.add(item);
            
            return lockAccount
                
        } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {
           throw new Exception("access denied");
        }
        
        
    }
    
    def unLockAccount(requestBody) {
        
        ArrayList<ArrayList<String>> unLockAccount = new ArrayList<ArrayList<String>>();            
        HashMap<String,String> item = new HashMap<String,String>();  
//        def currentUser = springSecurityService.currentUser 
        def role = springSecurityService.getPrincipal().getAuthorities()
        def applicationUrl = ApplicationHolder.getApplication().config.cloudstack.applicationUrl
        // convert string to json object
        def requestData = JSON.parse(requestBody)
        Date date = new Date()
        def time = new Timestamp(date.getTime())     
        if(role.iterator().next() == "ROLE_ADMIN" ) {
            
            def account = Account.get(Integer.parseInt(requestData.accountId));
            
            def user = User.findByUsername(account.accountIdentifier)
            user.accountLocked = false
            user.save(flush: true);                        
            Map tempalteMap = notificationService.getDefaultMailTemplateMap()   
            tempalteMap.put("user", user)
            notificationService.send(account.email.toString(), "loginUnlock.subject.ftl", tempalteMap, "loginUnlock.ftl")              
            def log = new Log()
            log.account = account
            log.user = User.findByUsername(account.accountIdentifier)
            log.date = time
            log.type = LogType.values()[3]
            log.mailSend = true
            log.description = "Account " + account.accountIdentifier + " is UnLocked"
            log.save(flush: true);
               
            item.put("result",  GeneralConstants.RESULT_SUCCESS);
            unLockAccount.add(item);
            
            return unLockAccount
                
        } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {
           throw new Exception("access denied");
        }
    }
    
    def listAdminUser() {
        def role = springSecurityService.getPrincipal().getAuthorities()
        def currentUser = springSecurityService.currentUser 
        
        ArrayList<ArrayList<String>> adminUsers = new ArrayList<ArrayList<String>>();            
        
        
        
        if(role.iterator().next() == "ROLE_ADMIN" ) {
             
            def userRoleList = UserRole.findAllByRole(Role.get(1))
            
            for(def userRole: userRoleList) {
                
                def adminUser = userRole.user
                if(adminUser.accountExpired == false) {
                    HashMap<String,String> item = new HashMap<String,String>();  
                    item.put("id",  adminUser.id);
                    item.put("userName",  adminUser.username);
                    if(currentUser == adminUser) {
                        item.put("currentAadmin",  "true");
                    } else {
                        item.put("currentAadmin",  "false"); 
                    }
                    def apiUser = ApiUser.findWhere(user: adminUser, enabled:true) 
                    if(apiUser) {
                        item.put("apiAccess",  "true");
                    } else {
                        item.put("apiAccess",  "false");
                    }
                    adminUsers.add(item);
                }
                
            }
            return adminUsers
            
        } else {
           throw new Exception("access denied");
        }
    }
    
    def addAdminUser(requestBody) {
        try {  
            // convert string to json object
            def requestData = JSON.parse(requestBody)
            
            Calendar calendar = Calendar.getInstance(); 
            
            def currentAcc = springSecurityService.currentUser 
            
            User user = new User();
            user.account = Account.get(10001);
            user.token = "added by admin"
            user.username = requestData.userName;  
            user.failureCount = 0;
            user.password = requestData.password; 
            user.domain = user.account.domain
            user.enabled = true;
            user.tokenExpiryDate = calendar.getTime() 
            user.accountExpired = false;
            user.accountLocked = false;
            user.passwordExpired = false;
            user.save(flush:true, failOnError:true); 

            def role = Role.findByAuthority("ROLE_ADMIN");
            UserRole userRole = new UserRole();
            userRole.user = user;
            userRole.role = role;
            userRole.save(flush:true, failOnError:true);   
            
            log.info("Admin user:${user.id} added by user ${currentAcc.id}")
            
        } catch (Exception ex){
             throw ex;   
        } 
    }
    
    def getAPIkey(userId) {
        
        long id = Long.parseLong(userId)
        
        def user = User.get(id.toLong())
        
        def apikey = ApiUser.findWhere(user: user, enabled:true)
        
        ArrayList<ArrayList<String>> apiKeyArray = new ArrayList<ArrayList<String>>();       
        HashMap<String,String> item = new HashMap<String,String>();  
        item.put("id",  apikey.id);
        item.put("apiKey",  apikey.apiKey);
        item.put("secret",  apikey.secret);
        
        apiKeyArray.add(item);
        
        return apiKeyArray;
        
    }
    
    def revokeAPIKey(userId) {
        try {
            
            def currentAcc = springSecurityService.currentUser 
            
            long id = Long.parseLong(userId)

            def user = User.get(id.toLong())

            def apikey = ApiUser.findWhere(user: user, enabled:true)
            apikey.delete();
            log.info("Revoke api key access for user:${user.id} by user ${currentAcc.id}")
            
        } catch (Exception ex) {
             throw ex;   
        } 
    }
    
    def resetAPIKey(userId) {
        
        try {
            
            def currentAcc = springSecurityService.currentUser 
            
            long id = Long.parseLong(userId)

            def user = User.get(id.toLong())

            def apikey = ApiUser.findWhere(user: user, enabled:true)

            Date date = new Date();
            def time = new Timestamp(date.getTime())
          
            def apiKeyData = "Fog-API"+ user.username + date.toString()
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] messageDigest = md.digest(apiKeyData.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String apiHashText = number.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            while (apiHashText.length() < 32) {
                apiHashText = "0" + apiHashText;
            }


            def secretKeyData = "Fog-secret" + user.username + date.toString() + apiHashText.toString()
            MessageDigest md2 = MessageDigest.getInstance("SHA-512");
            byte[] messageDigest2 = md2.digest(secretKeyData.getBytes());
            BigInteger number2 = new BigInteger(1, messageDigest2);
            String secretHashText = number2.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            while (secretHashText.length() < 32) {
                secretHashText = "0" + secretHashText;
            }
            
            apikey.apiKey = apiHashText.toString();
            apikey.secret = secretHashText.toString();
            apikey.save()
            log.info("Reset api key access for user:${user.id} by user ${currentAcc.id}")
            
        } catch (Exception ex) { 
             throw ex;   
        } 
    } 
    
    def grantAPIKey(userId) {
        try {
            
            def currentAcc = springSecurityService.currentUser 
            
            long id = Long.parseLong(userId)

            def user = User.get(id.toLong())
            
            Date date = new Date();
            def time = new Timestamp(date.getTime())
            
            def apikey = new ApiUser()
            apikey.enabled = true;
            apikey.createdDate = time;
            apikey.user = user;

            def apiKeyData = "Fog-API"+ user.username + date.toString()
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] messageDigest = md.digest(apiKeyData.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String apiHashText = number.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            while (apiHashText.length() < 32) {
                apiHashText = "0" + apiHashText;
            }


            def secretKeyData = "Fog-secret" + user.username + date.toString() + apiHashText.toString()
            MessageDigest md2 = MessageDigest.getInstance("SHA-512");
            byte[] messageDigest2 = md2.digest(secretKeyData.getBytes());
            BigInteger number2 = new BigInteger(1, messageDigest2);
            String secretHashText = number2.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            while (secretHashText.length() < 32) {
                secretHashText = "0" + secretHashText;
            }

            apikey.apiKey = apiHashText.toString();
            apikey.secret = secretHashText.toString();
            apikey.save()
            log.info("Grant API access for user:${user.id} added by user ${currentAcc.id}")
            if (!apikey.save()) {
                apikey.errors.allErrors.each { println it }
            }
            
        } catch (Exception ex) {
             throw ex;   
        } 
    }
    
    def validateAdminUserName(userName) {
        
        def result = User.findByUsername(userName);
        def validationStatus = userName.matches("[a-zA-Z0-9]*");
        
        if(result) {
            return [GeneralConstants.RESULT_FAILURE]
        } else if(validationStatus == false || validationStatus == "false" || userName.size() < 5) {
            return [GeneralConstants.RESULT_FALSE]
        } else if(validationStatus == true || validationStatus == "true" && userName.size() >= 5) {
            return [GeneralConstants.RESULT_TRUE]
        }
    }
    def deleteAdminUser(requestBody) {
        try {  
            
            def currentAcc = springSecurityService.currentUser 
            
            // convert string to json object
            def requestData = JSON.parse(requestBody)
            
            def user = User.get(requestData.id)
            
            user.accountExpired = true
            user.save(flush:true, failOnError:true); 
            log.info("Delete Admin user:${user.id} by user ${currentAcc.id}")
                    
        } catch (Exception ex){
             throw ex;   
        } 
    }
     
    def creditLimitStatus() {
        
        def currency = ApplicationHolder.getApplication().config.billing.defaultCurrency
        ArrayList<ArrayList<String>> creditLimitAccount = new ArrayList<ArrayList<String>>();   
        def accountList = Account.findAll()
        for(def account: accountList) {

            if(account.totalPayable > account.creditLimit) {
                HashMap item = new HashMap();  
                item.put("accountId",  account.id);
                item.put("account",  account.firstName);
                item.put("amount",  Math.ceil((account.totalPayable - account.creditLimit) * 100d) / 100d);
                item.put("currency", currency);      
                creditLimitAccount.add(item)
                 
            }
        }
        return creditLimitAccount;
    }
    
    def resourceLimit() {
       
        def user = springSecurityService.currentUser 
                
        ArrayList<ArrayList<String>> account = new ArrayList<ArrayList<String>>();
        HashMap<String,String> accountItem = new HashMap<String,String>();
        accountItem.put("accountType", user.account.accountType.name());
        accountItem.put("vmLimitNo", user.account.vmLimit);
        accountItem.put("storageLimitNo", user.account.storageLimit);
        accountItem.put("snapLimitNo", user.account.snapshotLimit);
        
        def vmCount = VirtualMachine.findAllWhere(deleted: false, user: user); 
        def volumes = Volume.findAllWhere(deleted: false, owner: user.account); 
        def snap = Snapshot.findAllWhere(deleted: false, user: user);  
        
        accountItem.put("vmCount", vmCount.size());
        accountItem.put("volumeCount", volumes.size());
        accountItem.put("snapCount", snap.size());
        account.add(accountItem);
        return account
    }
    
    
     def listSSHKey() {
        
        try { 
            
            def sshkeyList;
            def user = springSecurityService.currentUser
            def role = springSecurityService.getPrincipal().getAuthorities()       
                
            if(role.iterator().next() == "ROLE_ADMIN" ) {
                sshkeyList = SSHKeys.findAll()
            } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {               
                    def SSHCreate = SSHKeys.createCriteria()
                    sshkeyList = SSHCreate.list {
                        eq("account", user.account)
                        and {
                            eq("deleted", false)
                        }
                        order("id", "desc")
                    }
            }
            ArrayList<ArrayList<String>> keyList = new ArrayList<ArrayList<String>>();     
            for(def key: sshkeyList) {
                HashMap<String,String> keyData = new HashMap<String,String>();
                keyData.put("id", key.id);
                keyData.put("fingerprint", key.fingerprint);
                keyData.put("name", key.name);
                keyData.put("isDefault", key.isDefault);  
                keyData.put("account", key.account.firstName); 
                keyData.put("privateKey", key.privatekey); 
                keyList.add(keyData);                 
                
            }
            return keyList
            
        } catch(Exception ex) {
             ex.printStackTrace(System.err);
            throw ex;
        }
    }
    
    
    def createSSHKey(requestBody) {
        
        try {
            
            licenseValidationService.authorizeAction(FogPanelService.SSH_KEY_CREATE)
            
            // convert string to json object
            def requestData = JSON.parse(requestBody)
        
            def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
            def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
            def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret

            CloudStackServer server = new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey)

            CSSSHService cSSSHService = new CSSSHService(server)

            def user = springSecurityService.currentUser 

            HashMap<String,String> optional = new HashMap<String,String>();
            optional.put("account", user.account.accountIdentifier);
            optional.put("domainid", user.account.domain.referenceId);
            ArrayList<ArrayList<String>> resultList = new ArrayList<ArrayList<String>>();            
            HashMap<String,String> item = new HashMap<String,String>(); 
            
            def oldSSHKey = SSHKeys.findByName(requestData.name)
            
            def language = "";
            if(user.account.preferredLanguage == "" || user.account.preferredLanguage == "null" || user.account.preferredLanguage == null) {
                Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
                language = defaultLanguage.value
            } else {
                language = user.account.preferredLanguage
            }
            if(oldSSHKey) {
                item.put("message", messageSource.getMessage('user.sshKey.error.alreadyHasName', null, new Locale(language)));
                item.put("result", GeneralConstants.RESULT_FAILURE);
                resultList.add(item);
                return resultList;
            } else {
                def res = cSSSHService.createSSHKeyPair(requestData.name, optional)    
                
                SSHKeys sshKey = new SSHKeys()
                sshKey.fingerprint = res.fingerPrint
                sshKey.name = res.name
                sshKey.privatekey = res.privateKey
                sshKey.isDefault = false
                sshKey.deleted = false
                sshKey.account = user.account
                sshKey.save(flush:true); 
                log.info("SSH key created for user:${user.id}")
                item.put("privateKey", res.privateKey);
                item.put("result", GeneralConstants.RESULT_SUCCESS);
                resultList.add(item);
                return resultList;
            }                                                               
        } catch(Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }        
    def resetSSHKeyForVirtualMachine(requestBody) {     
        
        licenseValidationService.authorizeAction(FogPanelService.SSH_KEY_RESET)
        
        def user = springSecurityService.currentUser 
        def requestData = JSON.parse(requestBody)

        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret
        CloudStackServer server = new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey)
        CSSSHService cSSSHService = new CSSSHService(server)            
        def language = "";
        if(user.account.preferredLanguage == "" || user.account.preferredLanguage == "null" || user.account.preferredLanguage == null) {
            Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
            language = defaultLanguage.value
        } else {
            language = user.account.preferredLanguage
        }            
        VirtualMachine virtualMachine = VirtualMachine.findByReferenceId(requestData.virtualMachineReferenceId);
        def sshKey = SSHKeys.findByName(requestData.SSHKeyId);
        ArrayList<ArrayList<String>> resultList = new ArrayList<ArrayList<String>>();            
        HashMap<String,String> item = new HashMap<String,String>();                    
        if (virtualMachine.state == GeneralConstants.VM_RUNNING_STATE || virtualMachine.state == GeneralConstants.VM_STOPPING_STATE) {
            item.put("result", GeneralConstants.RESULT_FAILURE);
            item.put("message", messageSource.getMessage('user.sshKey.error.stopVM', null, new Locale(language)));
            resultList.add(item);
            return resultList;
        } else if(virtualMachine.sshKey == sshKey) {
            item.put("result", GeneralConstants.RESULT_FAILURE);
            item.put("message", messageSource.getMessage('user.sshKey.error.alreadyHasPlan', null, new Locale(language))+ "(" + sshKey.name + ")");
            resultList.add(item);
            return resultList;
        }              
        HashMap<String,String> optional = new HashMap<String,String>();
        optional.put("account", user.account.accountIdentifier);
        optional.put("domainid", user.account.domain.referenceId);        
        def response = cSSSHService.resetSSHKeyForVirtualMachine(requestData.virtualMachineReferenceId, requestData.SSHKeyId, optional)
        
        virtualMachine.sshKey = sshKey
        virtualMachine.save(flush: true); 
        if (virtualMachine.hasErrors()) {
            throw new ValidationException(virtualMachine.errors.allErrors);
        }            
        item.put("sshKey", virtualMachine.sshKey.name);
        item.put("result", GeneralConstants.RESULT_SUCCESS);
        resultList.add(item);
        log.info("SSH key reseted for user:${user.id}")
        return resultList;
    }        
    
    
    def deleteSSHKey(requestBody) {   
        
        licenseValidationService.authorizeAction(FogPanelService.SSH_KEY_DELETE)
        
        def requestData = JSON.parse(requestBody)
        def user = springSecurityService.currentUser 
        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret
        CloudStackServer server = new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey)
        CSSSHService cSSSHService = new CSSSHService(server)                 
        HashMap<String,String> optional = new HashMap<String,String>();
        optional.put("account", user.account.accountIdentifier);
        optional.put("domainid", user.account.domain.referenceId);        
        def sshKey = SSHKeys.get(Long.parseLong(requestData.sshId));        
        def vm = VirtualMachine.findAllBySshKey(sshKey)                
        ArrayList<ArrayList<String>> resultList = new ArrayList<ArrayList<String>>();            
        HashMap<String,String> item = new HashMap<String,String>();                      
        def language = "";
        if(user.account.preferredLanguage == "" || user.account.preferredLanguage == "null" || user.account.preferredLanguage == null) {
            Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
            language = defaultLanguage.value
        } else {
            language = user.account.preferredLanguage
        }
        
        if(vm) {
            item.put("result", GeneralConstants.RESULT_FAILURE);
            item.put("message", messageSource.getMessage('user.sshKey.error.deleteKeyError', null, new Locale(language)));
            resultList.add(item);
            return resultList;
        } else {                        
            def response = cSSSHService.deleteSSHKeyPair(sshKey.name, optional)                        
            sshKey.deleted = true
            sshKey.save(flush:true);               
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            resultList.add(item);
            log.info("SSH key deleted for user:${user.id}")
            return resultList;
        }        
    }
    
    
    
    def getCloudResourceStat(id) {
                
        def user;
        def account
        def role = springSecurityService.getPrincipal().getAuthorities()  
       
        if(role.iterator().next() == "ROLE_ADMIN" ) {
            account = Account.get(id);
            user = User.findByUsername(account.accountIdentifier)
        } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {               
             user = springSecurityService.currentUser
             account = user.account
        }
        
        ArrayList<ArrayList<String>> accountInfo = new ArrayList<ArrayList<String>>();
                
        HashMap item = new HashMap();
        
        item.put("vmLimit", account.vmLimit);
        item.put("storageLimit", account.storageLimit);
        item.put("snapshotLimit", account.snapshotLimit);
        
        def vmList =  VirtualMachine.findAllWhere(owner: account, deleted:false)
        
        item.put("vmUsed", vmList.size());
        item.put("storageUsed", Volume.findAllWhere(deleted: false, owner:account).size());
        item.put("snapshotUsed", Snapshot.findAllWhere(user: user, deleted: false).size());
        item.put("vmSnapshotUsed", VMSnapshot.findAllWhere(account: account, deleted: false).size());
                        
        item.put("bandwidthLimit", account.bandwidthLimit);
                
        item.put("publicIPLimit", account.publicIPLimit);
        item.put("vpcLimit", account.vpcLimit);
                
        item.put("publicIPUsed", UserIPAddress.findAllWhere(account: account, removed:false).size());
        item.put("vpcUsed", VPC.findAllWhere(account: account, deleted:false).size());
                
        item.put("cpuLimit", account.cpuLimit);
        
        
        def vCPU = 0
        def memory = 0
        
        for(def vm: vmList) {
            
            vCPU += vm.computingOffer.cpuNumber
            memory += vm.computingOffer.memory
            
        }
       
        
        item.put("cpuUsed", vCPU);        
        item.put("memoryLimit", account.memoryLimit);
        item.put("memoryUsed", memory);  
                
        def primaryStorageCriteria = Volume.createCriteria()
        def primaryStorageSize = primaryStorageCriteria.get {
            and {
              eq("owner", account)  
            }                              
            projections {
                sum("customDiskSize")
            } 
        }        
        item.put("primaryStorageLimit", account.primaryStorageLimit);
        if(primaryStorageSize == "null" || primaryStorageSize == null) {
            item.put("primaryStorageLimitUsage", 0);
        } else {
            item.put("primaryStorageLimitUsage", primaryStorageSize); 
        }
        
        item.put("secondaryStorageLimit", account.secondaryStorageLimit);
        def secondaryStorageCriteria = Snapshot.createCriteria()
        def secondaryStorageSize = secondaryStorageCriteria.get {
            and {
              eq("user", user)  
            }                              
            projections {
                sum("size")
            } 
        }  
                
        if(secondaryStorageSize == "null" || secondaryStorageSize == null) {
            item.put("secondaryStorageLimitUsage", 0);
        } else {
            item.put("secondaryStorageLimitUsage", secondaryStorageSize); 
        } 
        
        item.put("networkLimit", account.networkLimit);
        item.put("networkUsed", Network.findAllWhere(deleted: false, account:account).size());
        
        accountInfo.add(item)
        
        return accountInfo
    }
        
    def updateResourceLimit(requestData) {
        
        def account = Account.get(requestData.accountId)
        
        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret

        CloudStackServer server = new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey)
            
        CSLimitService csLimitService = new CSLimitService(server); 
        
        HashMap<String,String> pStorageLimitoptional = new HashMap<String,String>();  
        pStorageLimitoptional.put("account", new String(account.accountIdentifier));
        pStorageLimitoptional.put("domainid", new String(account.domain.referenceId));                      
        pStorageLimitoptional.put("max", requestData.primaryStorageLimit);
        def pStorageLimit = csLimitService.updateResourceLimit("10", pStorageLimitoptional)

        HashMap<String,String> sStorgeLimitoptional = new HashMap<String,String>();  
        sStorgeLimitoptional.put("account", new String(account.accountIdentifier));
        sStorgeLimitoptional.put("domainid", new String(account.domain.referenceId));                      
        sStorgeLimitoptional.put("max", requestData.secondaryStorageLimit);
        def sStorgeLimit = csLimitService.updateResourceLimit("11", sStorgeLimitoptional)

        HashMap<String,String> memoryLimitoptional = new HashMap<String,String>();  
        memoryLimitoptional.put("account", new String(account.accountIdentifier));
        memoryLimitoptional.put("domainid", new String(account.domain.referenceId));                      
        memoryLimitoptional.put("max", requestData.retailMemoryLimit);
        def memoryLimit = csLimitService.updateResourceLimit("9", memoryLimitoptional)

        HashMap<String,String> cpuLimitoptional = new HashMap<String,String>();  
        cpuLimitoptional.put("account", new String(account.accountIdentifier));
        cpuLimitoptional.put("domainid", new String(account.domain.referenceId));                      
        cpuLimitoptional.put("max", requestData.retailCpuLimit);
        def cpuLimit = csLimitService.updateResourceLimit("8", cpuLimitoptional)
                
        HashMap<String,String> networkLimitoptional = new HashMap<String,String>();  
        networkLimitoptional.put("account", new String(account.accountIdentifier));
        networkLimitoptional.put("domainid", new String(account.domain.referenceId));                      
        networkLimitoptional.put("max", requestData.retailNetworkLimit);
        def networkLimitAPI = csLimitService.updateResourceLimit("6", networkLimitoptional)

        HashMap<String,String> vpcLimitoptional = new HashMap<String,String>();  
        vpcLimitoptional.put("account", new String(account.accountIdentifier));
        vpcLimitoptional.put("domainid", new String(account.domain.referenceId));                      
        vpcLimitoptional.put("max", requestData.retailVpcLimit);
        def vpcLimit = csLimitService.updateResourceLimit("7", vpcLimitoptional)

        HashMap<String,String> VMoptional = new HashMap<String,String>();  
        VMoptional.put("account", new String(account.accountIdentifier));
        VMoptional.put("domainid", new String(account.domain.referenceId));
        VMoptional.put("max", requestData.retailInstanceLimit);

        HashMap<String,String> stoOptional = new HashMap<String,String>();  
        stoOptional.put("account", new String(account.accountIdentifier));
        stoOptional.put("domainid", new String(account.domain.referenceId));
        stoOptional.put("max", requestData.retailStorageLimit);                            

        HashMap<String,String> snapOptional = new HashMap<String,String>();  
        snapOptional.put("account", new String(account.accountIdentifier));
        snapOptional.put("domainid", new String(account.domain.referenceId));
        snapOptional.put("max", requestData.retailSnapshotLimit);

        def vmLimit = csLimitService.updateResourceLimit("0", VMoptional)
        def storageLimit = csLimitService.updateResourceLimit("2", stoOptional)
        def snapshotLimit = csLimitService.updateResourceLimit("3", snapOptional)  
        
        
        HashMap<String,String> publicIPLimitoptional = new HashMap<String,String>();  
        publicIPLimitoptional.put("account", new String(account.userName));
        publicIPLimitoptional.put("domainid", new String(account.domain.referenceId));                      
        publicIPLimitoptional.put("max", requestData.retailPublicIPLimit);
        def retailPublicIP = csLimitService.updateResourceLimit("1", publicIPLimitoptional)
                
        account.vmLimit = requestData.retailInstanceLimit
        account.storageLimit = requestData.retailStorageLimit
        account.snapshotLimit = requestData.retailSnapshotLimit

        account.publicIPLimit = requestData.retailPublicIPLimit
        account.vpcLimit = requestData.retailVpcLimit
        account.cpuLimit = requestData.retailCpuLimit
        account.memoryLimit = requestData.retailMemoryLimit
        account.networkLimit = requestData.retailNetworkLimit
        account.primaryStorageLimit = requestData.primaryStorageLimit
        account.secondaryStorageLimit = requestData.secondaryStorageLimit
        
        account.save(flush: true);
        
        ArrayList<ArrayList<String>> resultList = new ArrayList<ArrayList<String>>();    
        HashMap item = new HashMap();
        item.put("result", GeneralConstants.RESULT_SUCCESS);
        resultList.add(item)
        
        return resultList
        
    }
     
//    def getIpAddressesList() {
//        try {
//            HashMap<String,String> optional = new HashMap<String,String>();
//            optional.put("account", "testuser");
//            optional.put("domainid", "f0daed5b-3fa7-4ef7-af3d-180761f97d15");
//            
//            def response = ipAddressService.listPublicIpAddresses(optional);            
//         } catch(Exception ex) {
//             ex.printStackTrace(System.err);
//            throw ex;
//         }
//        
//    }    
    
    def pullAccountFromCloudStackJobStart() {
        
        PullAccountJob.triggerNow([id:"account"])
            
        return "OK"
        
    }
    def pullAccountFromCloudStack(jobid) {
        
        def job = AsynchronousJobs.get(jobid)
        try { 
                        
            job.status = JobStatus.valueOf("RUNNING")
            job.startedAt = new Date()
            job.save(flush: true)
            
            HashMap<String,String> optional = new HashMap<String,String>();
            optional.put("listall", "true");

            def response = accountServer().listAccounts(optional);  
            
            //default country
            Country country = Country.findByCountryCode(356);
            
            //default state
            State state = State.findByStateName("TAMIL NADU");
            
            
            //default language        
            def defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
                
            for(Iterator<AccountResponse> iter = response.listAccounts.iterator(); iter.hasNext();) {
                
                def data = iter.next();
                Account account = Account.findByUuid(data.id);
                
                
                if(!account) {
                    
                    account = new Account();
                    account.uuid = data.id;
                    account.accountType = data?.accountType == "1" ? AccountType.valueOf("ADMIN") : AccountType.valueOf("RETAIL");
                    account.cpuLimit = data.cpuLimit == "Unlimited" ? "-1" : data.cpuLimit;
                    account.domain = Domain.findByReferenceId(data.domainId);
                    account.publicIPLimit = data.ipLimit == "Unlimited" ? "-1" : data.ipLimit;
                    account.memoryLimit = data.memoryLimit == "Unlimited" ? "-1" : data.memoryLimit;
                    account.email = "XXXXX@dummy.com";
                    account.networkLimit = data.networkLimit == "Unlimited" ? "-1" : data.networkLimit;
                    account.primaryStorageLimit = data.primaryStorageLimit == "Unlimited" ? "-1" : data.primaryStorageLimit;
                    account.secondaryStorageLimit = data.secondaryStorageLimit == "Unlimited" ? "-1" : data.secondaryStorageLimit;
                    account.snapshotLimit = data.snapshotLimit == "Unlimited" ? "-1" : data.snapshotLimit;
                    account.status = data?.state == "enabled" ? Status.valueOf("ACTIVE") : Status.valueOf("DISABLED");
                  
                    account.vmLimit = data.vmLimit == "Unlimited" ? "-1" : data.vmLimit;
                    account.storageLimit = data.volumeLimit == "Unlimited" ? "-1" : data.volumeLimit;
                    account.vpcLimit= data.vpcLimit == "Unlimited" ? "-1" : data.vpcLimit;
                    account.accountIdentifier=data.name                    
                    account.billingCity = "XXXXX"
                    account.billingCountry=country
                    account.billingPhoneNumber="0000000000"
                    account.billingState=state
                    account.billingStreetAddress="Administratoraddress"
                    account.billingZip="00000"
                    account.cardVerified=0
                    account.city="00000"
                    account.country=country
                    account.credit=0
                    account.creditLimit=0
                    account.firstName="Administrator"
                    account.lastName="Administrator"
                    account.phoneNumber="0000000000"
                    account.state=state
                    account.streetAddress="Administratoraddress"
                 
                    account.zip="00000"
                    account.streetAddress1="xxx"
                    account.billingStreetAddress1="xxx"
                   
                    account.preferredLanguage = defaultLanguage.value
                    
                    account.signUpDate=new Date();
                    account.lateFee=false;
                    account.totalAmount=0;
                    account.totalPayable=0;
                    account.totalPaid=0;
                    account.previousPaid=0;
                    account.penaltyFee=0;

                    
                    // setting is_completed flag false since billing info not yet updated
                    account.isCompleted = false
                    
                    if (!account.save(flush: true)) {
                        account.errors.allErrors.each { println it }
                    }
                    
                    
                } else {
                    
                    account.cpuLimit = data.cpuLimit == "Unlimited" ? "-1" : data.cpuLimit;
                    account.publicIPLimit = data.ipLimit == "Unlimited" ? "-1" : data.ipLimit;
                    account.memoryLimit = data.memoryLimit == "Unlimited" ? "-1" : data.memoryLimit;
                    account.networkLimit = data.networkLimit == "Unlimited" ? "-1" : data.networkLimit;
                    account.primaryStorageLimit = data.primaryStorageLimit == "Unlimited" ? "-1" : data.primaryStorageLimit;
                    account.secondaryStorageLimit = data.secondaryStorageLimit == "Unlimited" ? "-1" : data.secondaryStorageLimit;
                    account.snapshotLimit = data.snapshotLimit == "Unlimited" ? "-1" : data.snapshotLimit;

                    account.status = data?.state == "enabled" ? Status.valueOf("ACTIVE") : Status.valueOf("DISABLED");

                    account.vmLimit = data.vmLimit == "Unlimited" ? "-1" : data.vmLimit;
                    account.storageLimit = data.volumeLimit == "Unlimited" ? "-1" : data.volumeLimit;
                    account.vpcLimit= data.vpcLimit == "Unlimited" ? "-1" : data.vpcLimit;                                        
                    account.accountIdentifier=data.name
                                        
                    if (!account.save(flush: true)) {
                        account.errors.allErrors.each { println it }
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
