package com.assistanz.fogpanel

import com.assistanz.cloud.cloudstack.CloudStackServer
import com.assistanz.cloud.cloudstack.accounts.CSAccountService
import com.assistanz.cloud.cloudstack.ssh.CSSSHService
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.security.MessageDigest
import com.assistanz.cloud.cloudstack.limit.CSLimitService
import org.codehaus.groovy.grails.commons.ApplicationHolder
import java.util.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import org.apache.commons.logging.LogFactory;
import grails.transaction.Transactional

@Transactional
class AccountApiService {
    
    static transactional = true
    private static final log = LogFactory.getLog(this) 
    
    /**
     * Create Account  
     * 
     */
    def createAccount(username, firstname, lastname, password, street, city, country, state, zip, phonenumber, streetExtension) {        
        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret
        
        CloudStackServer server = new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey)
        CSAccountService csAccService = new CSAccountService(server)                        
        
        Date date = new Date();
        def time = new Timestamp(date.getTime()) 
        def token = username + date.toString()
        MessageDigest md = MessageDigest.getInstance("MD5");  
        byte[] messageDigest = md.digest(token.getBytes());
        BigInteger number = new BigInteger(1, messageDigest);
        String hashtext = number.toString(16);
        // Now we need to zero pad it if you actually want the full 32 chars.
        while (hashtext.length() < 32) {
            hashtext = "0" + hashtext;
        }

        def cloudPasswordString = password + date.toString()
        byte[] cloudPasswordDigest = md.digest(cloudPasswordString.getBytes());
        BigInteger cloudPaaswordNumber = new BigInteger(1, cloudPasswordDigest);
        String  cloudPasswordHashtext = cloudPaaswordNumber.toString(16);
        while (cloudPasswordHashtext.length() < 32) {
            cloudPasswordHashtext = "0" + cloudPasswordHashtext;
        }                        
        
        def account = new Account()
        account.firstName = firstname
        account.lastName = lastname
        account.email = username
        account.accountType = "RETAIL"
        account.status = "ACTIVE"
        account.uuid = "api-creating";  
        account.userName =  username
        account.signUpDate = time
        account.password = "xxxxxxxx"
        account.cloudPassword = cloudPasswordHashtext.toString(); 
        account.preferredLanguage = "en-US"
        account.streetAddress = street
        account.streetAddress1 = streetExtension
        account.city = city
        account.zip = zip
        account.state = State.findById(state)
        account.country = Country.findById(country)
        account.companyName = ""
        account.phoneNumber = phonenumber
        account.billingStreetAddress = street
        account.billingCity = city
        account.billingZip = zip
        account.billingState = State.findById(state)
        account.billingCountry = Country.findById(country)
        account.billingPhoneNumber = phonenumber
        account.totalAmount = 0
        account.totalPayable = 0    
        account.totalPaid = 0
        account.previousPaid = 0
        account.lateFee = false
        account.penaltyFee = 0
        account.previousBalance = 0 
        Config retailCreditLimit =  Config.findByName(Config.SIGNUP_TYPE_RETAIL_CREDIT_LIMIT);
        account.creditLimit = Double.parseDouble(retailCreditLimit.value); 
        account.credit = 0
        if (account.validate()) {
            account.domain = Domain.findByLevel(0);
            account.save(flush:true, failOnError:true);
            if (!account.save()) {
                throw new InvalidFieldException("{'errorCode': 2000 ,'message': Invalid field values}");
            }
            Calendar calendar = Calendar.getInstance(); 
            Config linkActiveTime =  Config.findByName(Config.LINK_ACTIVE_TIME);
            calendar.add(Calendar.HOUR, Integer.parseInt(linkActiveTime.value));
            User user = new User();
            user.account = account;
            user.token = hashtext.toString();
            user.username = account.userName;  
            user.failureCount = 0;
            user.password = password; 
            user.enabled = true;
            user.tokenExpiryDate = calendar.getTime() 
            user.accountExpired = false;
            user.accountLocked = false;
            user.passwordExpired = false;
            
            if (user.validate()) {
                user.save(flush:true, failOnError:true); 
                def role = Role.findByAuthority("ROLE_PAID_USER");
                UserRole userRole = new UserRole();
                userRole.user = user;
                userRole.role = role;
                userRole.save(flush:true, failOnError:true);
            } else {
                throw new InvalidFieldException("{'errorCode':'2000' ,'message':'Invalid field values'}");
            }                                    
            def response = csAccService.createAccount("0", account.email, account.firstName,
                account.lastName, account.userName, cloudPasswordHashtext.toString(), null);            
            HashMap<String,String> resourceoptional = new HashMap<String,String>();  
            resourceoptional.put("account", new String(account.userName));
            resourceoptional.put("domainid", new String(account.domain.referenceId));
            resourceoptional.put("max", "-1");
            
            CSLimitService csLimitService = new CSLimitService(server); 
            
            def pStorageLimit = csLimitService.updateResourceLimit("10", resourceoptional)
            def sStorgemLimit = csLimitService.updateResourceLimit("11", resourceoptional)
            
            CSSSHService cSSSHService = new CSSSHService(server)

            HashMap<String,String> optional = new HashMap<String,String>();
            optional.put("account", account.userName);
            optional.put("domainid", account.domain.referenceId);

            def res = cSSSHService.createSSHKeyPair(account.userName+"-default", optional) 

            SSHKeys sshKey = new SSHKeys()
            sshKey.fingerprint = res.fingerPrint
            sshKey.name = res.keyPairName
            sshKey.privatekey = res.privateKey
            sshKey.isDefault = true
            sshKey.account = account
            sshKey.save(flush:true); 
            
            ArrayList<ArrayList<String>> accountInfo = new ArrayList<ArrayList<String>>();
            HashMap accountItem = new HashMap();                
            accountItem.put("result", "success");
            accountItem.put("userName", account.userName);
            accountItem.put("accountId", account.id);
            accountInfo.add(accountItem)   
            
            log.info("Account: ${account.id} is signed up by api service")
            
            return accountInfo            
        } else {
            throw new InvalidFieldException("{'errorCode':'2000' ,'message':'Invalid field values'}");
        }        
    }
    
    def listAccount(status, fromdate, todate, pageNo, recordPerPage) {                                  
        def currency = ApplicationHolder.getApplication().config.billing.defaultCurrency            
        ArrayList<ArrayList<String>> pageArrayList = new ArrayList<ArrayList<String>>();   
        HashMap currentPage = new HashMap();                         
        ArrayList<ArrayList<String>> accountArrayList = new ArrayList<ArrayList<String>>();             
        def statusNo = "";     
        def currentPageNo = "";
        def currentRecordPerPage = "";
        if(status == GeneralConstants.ACCOUNT_STATUS_ACTIVE) {
            statusNo = 0;                
        } else if(status == GeneralConstants.ACCOUNT_STATUS_BLOCKED) {
            statusNo = 1
        } else if(status == GeneralConstants.ACCOUNT_STATUS_LOCKED) {
            statusNo = 2
        } else if(status == GeneralConstants.ACCOUNT_STATUS_DISABLED) {
            statusNo = 3
        } else if(status == GeneralConstants.ACCOUNT_STATUS_NOT_VERIFIED) {
            statusNo = 4
        } else if(status == GeneralConstants.ACCOUNT_STATUS_SUSPENDED) {
            statusNo = 5
        } else if(status == GeneralConstants.ACCOUNT_STATUS_CANCELED) {
            statusNo = 6
        } else if(status == GeneralConstants.ACCOUNT_STATUS_CLOSED) {
            statusNo = 7
        } else {
            statusNo = "";
        }                    
        
        if(pageNo) {
            if(pageNo == 0) {
                currentPageNo = 1
            } else {
                currentPageNo = Integer.parseInt(pageNo)
            }            
        } else {
            currentPageNo = 1;
        }                  
        if(recordPerPage) {            
            currentRecordPerPage = Integer.parseInt(recordPerPage)
        } else {
            currentRecordPerPage = 100;
        }                                
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat givenDateFormater = new SimpleDateFormat("yyyy-MM-dd");
        Date fDate;
        Date tDate;            
        
        if(fromdate) {
            fDate = givenDateFormater.parse(fromdate);
        }                    
        
        if(todate) { 
            Date givenToDate = givenDateFormater.parse(todate);
            Calendar toDateCalendar = Calendar.getInstance(); 
            toDateCalendar.setTime(givenToDate);
            toDateCalendar.set(Calendar.HOUR_OF_DAY, 23);
            toDateCalendar.set(Calendar.MINUTE, 59);
            toDateCalendar.set(Calendar.SECOND, 59);
            toDateCalendar.set(Calendar.MILLISECOND, 999);                   
            tDate = toDateCalendar.getTime() 
        }              
        
        def accountList = Account.withCriteria {               
            ne("accountType", AccountType.values()[2])
            if(status) {
                eq("status", Status.values()[statusNo]) 
            }
            and {
                if(fromdate) {
                    ge("signUpDate", fDate)  
                }
                and { 
                    if(todate) {
                        le("signUpDate", tDate)  
                    }                     
                }                                                                                                                  
            }
            order("signUpDate", "desc")
        }         
        currentPage.put("page", currentPageNo)
        currentPage.put("recordPerPage", currentRecordPerPage)                  
        def noOfPages = Math.ceil(accountList.size() / currentRecordPerPage);            
        def pages = Math.round(noOfPages);            
        currentPage.put("totalPages", pages)     
        currentPage.put("totalRecords", accountList.size())     
        if(currentPageNo <= pages) {
            def minLimit = (currentPageNo * currentRecordPerPage) - currentRecordPerPage;
            def maxLimit = minLimit + currentRecordPerPage;  
            if(accountList.size() != 0) {            
                for(int i=minLimit; i < maxLimit; i++) {                 
                    def item = accountList[i]; 
                    if(item) {                                                           
                        HashMap account = new HashMap();                         
                        account.put("id", item.id);
                        account.put("userName", item.userName);
                        account.put("lastName", item.lastName);
                        account.put("firstName", item.firstName);
                        account.put("city", item.city);
                        account.put("streetAddress", item.streetAddress);
                        account.put("extendedAddress", item.streetAddress1);
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
                        account.put("accountType", item.accountType.name());
                        account.put("status", item.status.name()); 
                        account.put("totalPaid", item.totalPaid);
                        account.put("totalPayable", item.totalPayable);
                        account.put("creditLimit", item.creditLimit);
                        account.put("currency", currency);     
                        account.put("signupDate", dateFormat.format(item.signUpDate).toString());                                 
                        accountArrayList.add(account);                                                                                  
                    }                                  
                }                                
            } else {
                currentPage.put("accounts", accountArrayList) 
            }  
        } else {
            currentPage.put("accounts", accountArrayList) 
        }                  
        currentPage.put("accounts", accountArrayList)                              
        pageArrayList.add(currentPage);
        return pageArrayList;                
    }
}
