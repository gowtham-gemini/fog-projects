package com.assistanz.fogpanel

import com.assistanz.fogpanel.MailManager
import grails.converters.deep.JSON
import com.assistanz.cloud.config.ConfigLoader
import org.codehaus.groovy.grails.commons.ApplicationHolder
import org.apache.commons.logging.LogFactory;
import com.assistanz.licensor.License;
import com.assistanz.openstack.OpenStackServer
import org.springframework.security.core.AuthenticationException

class ConfigService {
    
    def springSecurityService;
    private static final log = LogFactory.getLog(this) 
    def licenseValidationService

    def cloudStackConfiguration() {
//        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
//        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
//        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret
//    
//        CloudStackServer server =
//        new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);
//                
//        CSConfigurationService cSConfigurationService = new CSConfigurationService(server);
        def cSConfigurationService = "null";   
        return cSConfigurationService;
    }
    
    def getDateFormat() {
        try {    
            def user = springSecurityService.currentUser
            def role = springSecurityService.getPrincipal().getAuthorities()    
            Config configDateFormat =  Config.findByName(Config.DATE_FORMAT);            
            def userDateFormat = "";
//            if(user.dateFormat) {
//                userDateFormat = user.dateFormat
//            } else {            
//                userDateFormat = configDateFormat.value
//            }
            return userDateFormat;
        } catch(Exception ex) {
            ex.printStackTrace(System.err);           
            throw ex;
        }
    }
    
    def getInvoiceDateFormat() {
        try {                
            Config configDateFormat =  Config.findByName(Config.DATE_FORMAT);                             
            def dateFormat = configDateFormat.value            
            return dateFormat;
        } catch(Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }   
    }
    
    def getBillingType() {
        try {
            Console.print("calling")
            ArrayList configArrayList = new ArrayList(); 
            HashMap item = new HashMap(); 
            Config monthlyEnabled =  Config.findByName(Config.MONTHLY_BILLING_ENABLE);
            item.put("monthlyBillingEnabled", monthlyEnabled.value)
            Console.print("array" + monthlyEnabled.value )
            configArrayList.add(item)
            return configArrayList
        } catch(Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
        
    }
    
    def setBillingType(String requestBody) {
        try {
            
            def user = springSecurityService.currentUser
            
            def requestData = JSON.parse(requestBody)

            Config monthlyEnabled =  Config.findByName(Config.MONTHLY_BILLING_ENABLE);
                    
            monthlyEnabled.value = requestData.monthlyEnabled;
            monthlyEnabled.save(flush: true);
            
            Config usageCalcType =  Config.findByName(Config.USAGE_BILLING_CALC_TYPE);      
            usageCalcType.value = requestData.usageCalcType;
            usageCalcType.save(flush: true);            
            
            ConfigLoader.isConfigModified = true
            
            log.info("Billing Type Setting updated to :${requestBody} by user${user.id}");  
            
            
        } catch(Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
    
    def setDefaultLanguage(String requestBody) {
        try {
            def requestData = JSON.parse(requestBody)

            Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);            
            
            def user = springSecurityService.currentUser
            
            defaultLanguage.value = requestData.language;
            defaultLanguage.save(flush: true);                        
            
            def account = user.account
            if(requestData.dateFormat) {
                user.dateFormat = requestData.dateFormat
                user.save();
            }
            
            account.preferredLanguage = requestData.language;
            account.save();
            
            ConfigLoader.isConfigModified = true
            
            log.info("Default Language changed to :${requestData.language} by user${user.id}");  
        
        } catch(Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
    
    def setSignupDefaultSetting(String requestBody) { 
        try {
            def requestData = JSON.parse(requestBody)

            Config signupSetting =  Config.findByName(Config.SIGNUP_SETTING);
            
            def user = springSecurityService.currentUser
            
            signupSetting.value = requestData.signupSettingValue;
            signupSetting.save(flush: true);
            
            ConfigLoader.isConfigModified = true
                                    
            log.info("Default Language changed to :${requestData.language} by user${user.id}");  
        
        } catch(Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
    
    
    def setPaymentCardConfig(String requestBody) {
        try {
            def requestData = JSON.parse(requestBody)
            def user = springSecurityService.currentUser
            Config signupCardVerificationEnabled =  Config.findByName(Config.SIGNUP_CARD_VERIFICATION_ENABLED);
            signupCardVerificationEnabled.value = requestData.signupCardVerificationEnabled;
            signupCardVerificationEnabled.save(flush: true);
            
            Config creditCardEnabled =  Config.findByName(Config.CARD_PROCESSING_ENABLED);
            creditCardEnabled.value = requestData.creditCardEnabled;
            creditCardEnabled.save(flush: true);
            
            Config vmCardVerificationEnabled =  Config.findByName(Config.CARD_PROCESSING_ON_CREATE_VM_ENABLED);
            vmCardVerificationEnabled.value = requestData.vmCardVerificationEnabled;
            vmCardVerificationEnabled.save(flush: true);
            
            ConfigLoader.isConfigModified = true
            
            log.info("Payment Card Config changed to :${requestBody} by user${user.id}");
                     
        } catch(Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
    
    def configTest() {
        try {
//            def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
//            def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
//            def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret
//
//            CloudStackServer server =
//            new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);
//            if(cloudStackUrl == "" || cloudStackApiKey == "" || cloudStackSecretKey == "") {
//                return "FAILED"
//            }  else {
////                zoneService.list()
//                return "OK"
//            }
            return "FAILED"
        } catch(Exception ex){
            [ex] as JSON
        }
    }
    
    //add billing config values to database
    def paymentConfig(String requestBody) {
        try {   
            //convert requestBody to json
            def requestData = JSON.parse(requestBody)
            def user = springSecurityService.currentUser
            Config processingFeeAmount =  Config.findByName(Config.PAYMENT_PROCESSING_FEE_AMOUNT);
            processingFeeAmount.value = requestData.processingFeeAmount;
            processingFeeAmount.save(flush: true);
                            
            Config paymentFeePercentage =  Config.findByName(Config.PAYMENT_PROCESSING_FEE_PERCENTAGE);
            paymentFeePercentage.value = requestData.processingFeePercentage;
            paymentFeePercentage.save(flush: true);
                        
            Config paymentFeeType =  Config.findByName(Config.PAYMENT_PROCESSING_FEE_TYPE);
            paymentFeeType.value = requestData.processingFeeType;
            paymentFeeType.save(flush: true);
                
            log.info("Payment Processing Fee Config changed to :${requestBody} by user${user.id}");
            
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }    
    }
    def varifyMailConfig(String requestBody) {
        try {
            def requestData = JSON.parse(requestBody)
            Properties props = new Properties()
            if(requestData.ssl == true || requestData.ssl == "true") {
                props.put("mail.smtp.socketFactory.port", "465");
                props.put("mail.smtp.socketFactory.class",
                                        "javax.net.ssl.SSLSocketFactory");
                props.put("mail.smtp.socketFactory.fallback", "false");
            }
            props.put("mail.smtp.host", requestData.host);
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", requestData.port);
            MailManager mailManager = MailManager.getInstance();      
            mailManager.setConfig(requestData.from, requestData.host,  requestData.userName, requestData.password, props)
            mailManager.sendMail(requestData.from, "Config test", "this is send from fog panel") 
            
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }    
        
    }
    def setLoginSecurity(String requestBody) {
        try {
            def requestData = JSON.parse(requestBody)
            def user = springSecurityService.currentUser
            Config perAccount =  Config.findByName(Config.MAX_LOGIN_FAILURE);
            perAccount.value = requestData.perAccount
            perAccount.save(flush: true)
                
            Config perIp =  Config.findByName(Config.MAX_LOGIN_FAILURE_PER_IP);
            perIp.value = requestData.perIp
            perIp.save(flush: true) 
                
            Config time =  Config.findByName(Config.ACCOUNT_UNLOCK_TIME);
            time.value = requestData.time
            time.save(flush: true) 
                
            Config linkActiveTime =  Config.findByName(Config.LINK_ACTIVE_TIME);
            linkActiveTime.value = requestData.linkActiveTime
            linkActiveTime.save(flush: true) 
            
            ConfigLoader.isConfigModified = true
                
            log.info("Login Security Config changed to :${requestBody} by user${user.id}");
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }   
    }
    
    def getMailConfig() {
        ArrayList mailConfigList = new ArrayList(); 
        HashMap item = new HashMap();  
        
        item.put("applicationUrl", ApplicationHolder.getApplication().config.faas.applicationUrl)
        item.put("host", ApplicationHolder.getApplication().config.grails.mail.host)
        item.put("from", ApplicationHolder.getApplication().config.grails.mail.'default'.from)
        item.put("password", ApplicationHolder.getApplication().config.grails.mail.password)
        item.put("ssl", "true")
        item.put("port", ApplicationHolder.getApplication().config.grails.mail.port)
        item.put("username", ApplicationHolder.getApplication().config.grails.mail.username)
        item.put("senderName", ApplicationHolder.getApplication().config.fogPanelMail.senderName.toString())
        
        mailConfigList.add(item)
        
        return mailConfigList
    }
    
    
    
    def setMailConfig(String requestBody) {
        try {   

            Properties props = new Properties()
            def requestData = JSON.parse(requestBody)
            Config host =  Config.findByName(Config.MAIL_CONFIG_HOST);
            host.value = requestData.host
            host.save(flush: true)
            Config port =  Config.findByName(Config.MAIL_CONFIG_PORT);
            port.value = requestData.port
            port.save(flush: true)
            Config userName =  Config.findByName(Config.MAIL_CONFIG_USER_NAME);
            userName.value = requestData.userName
            userName.save(flush: true)  
            Config from =  Config.findByName(Config.MAIL_CONFIG_FROM);
            from.value = requestData.from
            from.save(flush: true)  
            Config ssl =  Config.findByName(Config.MAIL_CONFIG_SSL);
            ssl.value = requestData.ssl
            ssl.save(flush: true)
            Config password =  Config.findByName(Config.MAIL_CONFIG_PASSWORD);
            password.value = requestData.password
            password.save(flush: true)
            Config applicationUrl =  Config.findByName(Config.APPLICATION_URL);
            applicationUrl.value = requestData.applicationUrl
            applicationUrl.save(flush: true)                   
            
           
            if(ssl.value == true || ssl.value == "true") {
                props.put("mail.smtp.socketFactory.port", "465");
                props.put("mail.smtp.socketFactory.class",
                                        "javax.net.ssl.SSLSocketFactory");
                props.put("mail.smtp.socketFactory.fallback", "false");
            }
            props.put("mail.smtp.host", host.value);
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", port.value);
            MailManager mailManager = MailManager.getInstance();      
            mailManager.setConfig(from.value, host.value,  userName.value, password.value, props)
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }    
    }
    
    
    def testMailConfig() {          
        try {                     
            MailManager mailManager = MailManager.getInstance();  
            Config from =  Config.findByName(Config.MAIL_CONFIG_FROM);
            mailManager.sendMail(from.value, "Config test", "this is send from fog panel") 
            
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }           
    }
     
    
    def setTrialManagementConfig(requestBody) {
                        
        try {
            
            def user = springSecurityService.currentUser
            
            def requestData = JSON.parse(requestBody)
            Config trialEnabled =  Config.findByName(Config.SIGNUP_TYPE_TRIAL_ENABLED);
            trialEnabled.value = requestData.trialEnabled;
            trialEnabled.save(flush: true);
            
            Config trialCreditLimit =  Config.findByName(Config.SIGNUP_TYPE_TRIAL_CREDIT_LIMIT);
            if(requestData.trialEnabled == "true" || requestData.trialEnabled == true) {
                trialCreditLimit.value = requestData.trialCreditLimit;
                trialCreditLimit.save(flush: true);
            } 
                        
            Config instanceLimit =  Config.findByName(Config.INSTANCE_LIMIT);
            instanceLimit.value = requestData.instanceLimit;
            instanceLimit.save(flush: true);
            
            Config snapshotLimit =  Config.findByName(Config.SNAPSHOT_LIMIT);
            snapshotLimit.value = requestData.snapshotLimit;
            snapshotLimit.save(flush: true);
            
            Config storageLimit =  Config.findByName(Config.STORAGE_LIMIT);
            storageLimit.value = requestData.storageLimit;
            storageLimit.save(flush: true);
            
            Config bandwidthLimit =  Config.findByName(Config.BANDWIDTH_LIMIT);
            bandwidthLimit.value = requestData.bandwidthLimit;
            bandwidthLimit.save(flush: true);
            
            ConfigLoader.isConfigModified = true
            
            log.info("Trial Management Config changed to :${requestBody} by user${user.id}");
            
        } catch(Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
        
    }
    
    def setRetailManagementConfig(String requestBody) {
        try {
            def requestData = JSON.parse(requestBody)
            def user = springSecurityService.currentUser            
            Config retailCreditLimit =  Config.findByName(Config.SIGNUP_TYPE_RETAIL_CREDIT_LIMIT);
            retailCreditLimit.value = requestData.retailCreditLimit;
            retailCreditLimit.save(flush: true);
            
            ConfigLoader.isConfigModified = true
            
            log.info("Retail Management Config changed to :${requestBody} by user${user.id}");
        } catch(Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
    
    
    def setCreditLimitNotification(requestBody) {
        try { 
        
            def requestData = JSON.parse(requestBody)
            def user = springSecurityService.currentUser
                                    
            Config creditLimitNotificationLevel1 =  Config.findByName(Config.CREDIT_LIMIT_NOTIFICATION_LEVEL_1);
            creditLimitNotificationLevel1.value = requestData.creditLimitLevel1;
            creditLimitNotificationLevel1.save(flush: true);

            Config creditLimitNotificationLevel2 =  Config.findByName(Config.CREDIT_LIMIT_NOTIFICATION_LEVEL_2);
            creditLimitNotificationLevel2.value = requestData.creditLimitLevel2;
            creditLimitNotificationLevel2.save(flush: true);

            Config creditLimitNotificationLevel3 =  Config.findByName(Config.CREDIT_LIMIT_NOTIFICATION_LEVEL_3);
            creditLimitNotificationLevel3.value = requestData.creditLimitLevel3;
            creditLimitNotificationLevel3.save(flush: true);
            
            ConfigLoader.isConfigModified = true
            
            log.info("Credit Limit Notification Config changed to :${requestBody} by user${user.id}");
        
        } catch(Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
            
        
    }
    
    
    
    def setInvoiceConfig(String requestBody) {
        try {
            def user = springSecurityService.currentUser
            def requestData = JSON.parse(requestBody)
            Config billingPeriodFixedEnabled =  Config.findByName(Config.BILLING_CYCLE_FIXED_ENABLED);
            billingPeriodFixedEnabled.value = requestData.fixedEnabled;
            billingPeriodFixedEnabled.save(flush: true);
                
            Config billingPeriodFixedDay =  Config.findByName(Config.BILLING_PERIOD_FIXED_DAY);
            if(requestData.fixedEnabled == "true" || requestData.fixedEnabled == true) {
                billingPeriodFixedDay.value = requestData.billingPeriodFixedDay;
                billingPeriodFixedDay.save(flush: true);
            } 
            Config dueDays =  Config.findByName(Config.PAYMENT_DUE_DAYS);
            dueDays.value = requestData.dueDays;
            dueDays.save(flush: true);
            
            
            Config billingPeriodDays =  Config.findByName(Config.BILLING_PERIOD_DAYS);
            billingPeriodDays.value = requestData.billingPeriodDays;
            billingPeriodDays.save(flush: true);
                
            log.info("Invoice Config changed to :${requestBody} by user${user.id}");
        }  catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }    
    }
    //add billing config values to database
    def setBillingConfig(String requestBody) {
        try {   
            //convert requestBody to json
            def requestData = JSON.parse(requestBody)
            def user = springSecurityService.currentUser
            Config billingPeriodFixedEnabled =  Config.findByName(Config.BILLING_CYCLE_FIXED_ENABLED);
            billingPeriodFixedEnabled.value = requestData.fixedEnabled;
            billingPeriodFixedEnabled.save(flush: true);
                
            Config billingPeriodFixedDay =  Config.findByName(Config.BILLING_PERIOD_FIXED_DAY);
            if(requestData.fixedEnabled == "true" || requestData.fixedEnabled == true) {
                billingPeriodFixedDay.value = requestData.billingPeriodFixedDay;
                billingPeriodFixedDay.save(flush: true);
            } 
            
            if(requestData.dateFormat) {
                Config dateFormat =  Config.findByName(Config.DATE_FORMAT);
                dateFormat.value = requestData.dateFormat;
                dateFormat.save(flush: true);
            }
            
            Config dueDays =  Config.findByName(Config.PAYMENT_DUE_DAYS);
            dueDays.value = requestData.dueDays;
            dueDays.save(flush: true);
            
            
            Config billingPeriodDays =  Config.findByName(Config.BILLING_PERIOD_DAYS);
            billingPeriodDays.value = requestData.billingPeriodDays;
            billingPeriodDays.save(flush: true);
            
            ConfigLoader.isConfigModified = true
                
            log.info("Invoice Date Config changed to :${requestBody} by user${user.id}");   
                
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }    
    }
    def setLateFeeConfig(String requestBody) {
        try {  
            
            def user = springSecurityService.currentUser
            
            def requestData = JSON.parse(requestBody)
            Config lateFeeAmount =  Config.findByName(Config.LATE_FEE_MINIMUM_AMOUNT);
            lateFeeAmount.value = requestData.lateFeeAmount;
            lateFeeAmount.save(flush: true);
            
            Config lateFeePercentage =  Config.findByName(Config.LATE_FEE_PERCENTAGE);
            lateFeePercentage.value = requestData.lateFeePercentage;
            lateFeePercentage.save(flush: true);
                        
            Config lateFeeApplicableAmount =  Config.findByName(Config.LATE_FEE_APPLICABLE_AMOUNT);
            lateFeeApplicableAmount.value = requestData.lateFeeApplicableAmount;
            lateFeeApplicableAmount.save(flush: true);
            
            ConfigLoader.isConfigModified = true
            
            log.info("Late Fee Config changed to :${requestBody} by user${user.id}");   
        }  catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }    
    }
    
    //add billing config values to database
    def setGeneralConfig(String requestBody) {
        try {
            
            def user = springSecurityService.currentUser
            
            //convert requestBody to json
            def requestData = JSON.parse(requestBody)
            
            Config trialEnabled =  Config.findByName(Config.SIGNUP_TYPE_TRIAL_ENABLED);
            trialEnabled.value = requestData.trialEnabled;
            trialEnabled.save(flush: true);
            
            Config trialCreditLimit =  Config.findByName(Config.SIGNUP_TYPE_TRIAL_CREDIT_LIMIT);
            if(requestData.trialEnabled == "false" || requestData.trialEnabled == false) {
                trialCreditLimit.value = requestData.trialCreditLimit;
                trialCreditLimit.save(flush: true);
            } 
            
            Config signupCardVerificationEnabled =  Config.findByName(Config.SIGNUP_CARD_VERIFICATION_ENABLED);
            signupCardVerificationEnabled.value = requestData.signupCardVerificationEnabled;
            signupCardVerificationEnabled.save(flush: true);
            
            Config signupCardVerificationValue =  Config.findByName(Config.SIGNUP_CARD_VERIFICATION_VALUE);
            signupCardVerificationValue.value = requestData.signupCardVerificationValue;
            signupCardVerificationValue.save(flush: true);
            
            Config retailCreditLimit =  Config.findByName(Config.SIGNUP_TYPE_RETAIL_CREDIT_LIMIT);
            retailCreditLimit.value = requestData.retailCreditLimit;
            retailCreditLimit.save(flush: true);
            
            log.info("Trial Enable Config changed to :${requestBody} by user${user.id}");
                        
            
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }    
    }
    
             
    def setOrganisationConfig(String requestBody) {
        try {
            
            def user = springSecurityService.currentUser
            
            //convert requestBody to json
            def requestData = JSON.parse(requestBody)
            //            Console.print("city  " + requestData.city);
            Config organizationName =  Config.findByName(Config.ORGANISATION_NAME);
            organizationName.value = requestData.name;
            organizationName.save(flush: true);

            Config organizationEmail =  Config.findByName(Config.ORGANISATION_EMAIL);
            organizationEmail.value = requestData.email;
            organizationEmail.save(flush: true);
            
            Config organizationAddress =  Config.findByName(Config.ORGANISATION_ADDRESS);
            organizationAddress.value = requestData.address;
            organizationAddress.save(flush: true);
            
            Config organizationExtensionAddress =  Config.findByName(Config.ORGANISATION_ADDRESS_EXTENSION);
            organizationExtensionAddress.value = requestData.extensionAddress;
            organizationExtensionAddress.save(flush: true);
            
            Config organizationCity=  Config.findByName(Config.ORGANISATION_ADDRESS_CITY);
            organizationCity.value = requestData.city;
            organizationCity.save(flush: true);
            
            Config organizationCountry=  Config.findByName(Config.ORGANISATION_ADDRESS_COUNTRY);
            organizationCountry.value = requestData.country;
            organizationCountry.save(flush: true);
            
            Config organizationState=  Config.findByName(Config.ORGANISATION_ADDRESS_STATE);
            organizationState.value = requestData.state;
            organizationState.save(flush: true);
            
            Config organizationZip=  Config.findByName(Config.ORGANISATION_ADDRESS_ZIP);
            organizationZip.value = requestData.zip;
            organizationZip.save(flush: true);
            
            
            Config organizationPhone1 =  Config.findByName(Config.ORGANISATION_BILLING_PHONE_NUMBER1);
            organizationPhone1.value = requestData.phone1;
            organizationPhone1.save(flush: true);
            
            Config organizationPhone2 =  Config.findByName(Config.ORGANISATION_BILLING_PHONE_NUMBER2);
            organizationPhone2.value = requestData.phone2;
            organizationPhone2.save(flush: true);
            
            Config organizationPhone3 =  Config.findByName(Config.ORGANISATION_BILLING_PHONE_NUMBER3);
            organizationPhone3.value = requestData.phone3;
            organizationPhone3.save(flush: true);
            
            Config organizationFax1 =  Config.findByName(Config.ORGANISATION_BILLING_FAX_NUMBER1);
            organizationFax1.value = requestData.fax1;
            organizationFax1.save(flush: true);
            
            Config organizationFax2 =  Config.findByName(Config.ORGANISATION_BILLING_FAX_NUMBER2);
            organizationFax2.value = requestData.fax2;
            organizationFax2.save(flush: true);
            
            Config organizationFax3 =  Config.findByName(Config.ORGANISATION_BILLING_FAX_NUMBER3);
            organizationFax3.value = requestData.fax3;
            organizationFax3.save(flush: true);
            
            Config organizationLogoUrl =  Config.findByName(Config.ORGANISATION_BILLING_LOGO);
            organizationLogoUrl.value = requestData.logoUrl;
            organizationLogoUrl.save(flush: true);
            
            Config organizationSignature =  Config.findByName(Config.ORGANISATION_BILLING_SINGNATURE);
            organizationSignature.value = requestData.signature;
            organizationSignature.save(flush: true);    
            
            Config organizationTermsCondition =  Config.findByName(Config.ORGANISATION_BILLING_TERMS_CONDITIONS);
            organizationTermsCondition.value = requestData.termsCondition;
            organizationTermsCondition.save(flush: true);   
            
            Config organizationBgnImgUrl =  Config.findByName(Config.ORGANISATION_BACKGROUND_IMG_URL);
            organizationBgnImgUrl.value = requestData.bgnImgUrl;
            organizationBgnImgUrl.save(flush: true); 
            
            ConfigLoader.isConfigModified = true
            
            log.info("Organisation Config changed to :${requestBody} by user${user.id}");
           
        } catch(Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
        
    }
    
    def setPaypalConfig(String requestBody) {
        try {
            //convert requestBody to json
            def requestData = JSON.parse(requestBody)
            
            def user = springSecurityService.currentUser
                       
            Config connectionTimeOut =  Config.findByName(Config.PAYMENT_GW_PAYPAL_HTTP_TIMEOUT);
            connectionTimeOut.value = requestData.connectionTimeOut;
            connectionTimeOut.save(flush: true);

            Config httpRetry =  Config.findByName(Config.PAYMENT_GW_PAYPAL_HTTP_RETRY);
            httpRetry.value = requestData.httpRetry;
            httpRetry.save(flush: true);
            
            Config readTimeOut =  Config.findByName(Config.PAYMENT_GW_PAYPAL_HTTP_READ_TIME_OUT);
            readTimeOut.value = requestData.readTimeOut;
            readTimeOut.save(flush: true);
            
            Config maxConnection =  Config.findByName(Config.PAYMENT_GW_PAYPAL_HTTP_MAX_CONNECTION);
            maxConnection.value = requestData.maxConnection;
            maxConnection.save(flush: true);
            
            Config proxyPort=  Config.findByName(Config.PAYMENT_GW_PAYPAL_HTTP_PROXY_PORT);
            proxyPort.value = requestData.proxyPort;
            proxyPort.save(flush: true);
            
            Config proxyHost=  Config.findByName(Config.PAYMENT_GW_PAYPAL_HTTP_PROXY_HOST);
            proxyHost.value = requestData.proxyHost;
            proxyHost.save(flush: true);
            
            Config useProxy=  Config.findByName(Config.PAYMENT_GW_PAYPAL_HTTP_USE_PROXY);
            useProxy.value = requestData.useProxy;
            useProxy.save(flush: true);
            
            Config proxyUserName=  Config.findByName(Config.PAYMENT_GW_PAYPAL_HTTP_USER);
            proxyUserName.value = requestData.proxyUserName;
            proxyUserName.save(flush: true);
                        
            Config proxyPassword =  Config.findByName(Config.PAYMENT_GW_PAYPAL_HTTP_PASSWORD);
            proxyPassword.value = requestData.proxyPassword;
            proxyPassword.save(flush: true);
            
            Config googleAppEngine =  Config.findByName(Config.PAYMENT_GW_PAYPAL_HTTP_APP_ENGINE);
            googleAppEngine.value = requestData.phone2;
            googleAppEngine.save(flush: true);
            
            Config serviceEndPoint =  Config.findByName(Config.PAYMENT_GW_PAYPAL_SERVICE_ENDPOINT);
            serviceEndPoint.value = requestData.serviceEndPoint;
            serviceEndPoint.save(flush: true);
            
            Config clientID =  Config.findByName(Config.PAYMENT_GW_PAYPAL_CLIENT_ID);
            clientID.value = requestData.clientID;
            clientID.save(flush: true);
            
            Config clientSecret =  Config.findByName(Config.PAYMENT_GW_PAYPAL_CLIENT_SECRET);
            clientSecret.value = requestData.clientSecret;
            clientSecret.save(flush: true);    
            
            log.info("Paypal Config changed to :${requestBody} by user${user.id}");
            
        } catch(Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
        
    }
    
    def setAuthorizeNetConfig(String requestBody) {
        try {
            //convert requestBody to json
            def requestData = JSON.parse(requestBody)
                 
            Config apiKey =  Config.findByName(Config.PAYMENT_GW_AUTHORIZENET_API_KEY);
            apiKey.value = requestData.apiKey;
            apiKey.save(flush: true);

            Config apiSecret =  Config.findByName(Config.PAYMENT_GW_AUTHORIZENET_API_SECRET);
            apiSecret.value = requestData.apiSecret;
            apiSecret.save(flush: true);
            
            Config environment =  Config.findByName(Config.PAYMENT_GW_AUTHORIZENET_ENVIRONMENT);
            environment.value = requestData.environment;
            environment.save(flush: true);
            
        } catch(Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
        
    }
    
    def listCSCapabilities() {
        
        def response = cloudStackConfiguration().listCapabilities();

        ArrayList<ArrayList<String>> capabilityList = new ArrayList<ArrayList<String>>(); 

        HashMap<String,String> item = new HashMap<String,String>();
        item.put("customDiskMaxSize", Integer.parseInt(response.customDiskofferingMaxSize));
        capabilityList.add(item);

        return capabilityList;
    }
    
    
    def getCSConfig(String name) {
        
        
        HashMap<String,String> optional = new HashMap<String,String>();
        optional.put("name", name);
       
        def response = cloudStackConfiguration().listConfigurations(optional);

        ArrayList<ArrayList<String>> config= new ArrayList<ArrayList<String>>(); 

        HashMap<String,String> item = new HashMap<String,String>();
        item.put("name", response.configurationName);
        item.put("value", response.configurationValue);
        item.put("description", response.configurationDescription);
        config.add(item);

        return config;
        
    }
    
    
    //    def getPaypalConfig() {
    //        def paypalConfig = new Properties();
    //        //paypalConfig.findByCr
    //        def paypalRecords = Config.findAllByNameLike("payment.gateway.paypal.%");
    //        paypalRecords.each {
    //            def name = it.name.replaceAll("payment.gateway.paypal.", "");
    //            paypalConfig.setProperty(name.trim(), it.value);
    //        }
    //        
    //        return paypalConfig; 
    //    }
    //    
    //    def getPaypalMap() {
    //        def paypalConfig = new java.util.HashMap<String, String>();
    //        //paypalConfig.findByCr
    //        def paypalRecords = Config.findAllByNameLike("payment.gateway.paypal.%");
    //        paypalRecords.each {
    //            def name = it.name.replaceAll("payment.gateway.paypal.", "");
    //            paypalConfig.put(name.trim(), it.value);
    //            
    ////            log.error name + ":" + it.value
    //        }
    //                
    //        return paypalConfig;
    //    }
    
    def systemOverview() {
        def grailsApplication = ApplicationHolder.application
        
        ArrayList<ArrayList<String>> systemInfoList = new ArrayList<ArrayList<String>>();
        HashMap item = new HashMap();
        
        def license = licenseValidationService.getLicense()        
        //        item.put("availableHost", license.sockets)
        item.put("licenseStatus", licenseValidationService.getLicenseStatus())
        item.put("version", grailsApplication.metadata.'app.version')
        item.put("activeSockets", licenseValidationService.getCloudStackHostCount())
        item.put("instanceID", Config.findByName(Config.FOG_INSTANCE_ID).value)
        item.put("licenseeEmail", Config.findByName(Config.LICENSEE_EMAIL).value)
        systemInfoList.add(item)
        
        return systemInfoList;
        
        //        //Print application version
        //        println grailsApplication.metadata.'app.version'
        //
        //        //Print application name
        //        println grailsApplication.metadata.'app.name'
        //
        //        //Print expected Grails version
        //        println grailsApplication.metadata.'app.grails.version'
        
    }
    def setCcAvenueConfig(String requestBody) {
        try {
            //convert requestBody to json
            def requestData = JSON.parse(requestBody)
            
            def user = springSecurityService.currentUser
                       
            Config merchantId =  Config.findByName(Config.PAYMENT_GW_CCAVENUE_MERCHANT);
            merchantId.value = requestData.merchantId;
            merchantId.save(flush: true);

            Config workingKey =  Config.findByName(Config.PAYMENT_GW_CCAVENUE_WORKING_KEY);
            workingKey.value = requestData.workingKey;
            workingKey.save(flush: true);
            
//            PaymentGateways ccAvenueGateway = PaymentGateways.findByGatewayName(Config.PAYMENT_GW_CCAVENUE);
//            ccAvenueGateway.status = requestData.gatewayStatus;
//            ccAvenueGateway.save(flush: true);            
                                
            log.info("(Test)CCAvenue Config changed to :${requestBody} by user${user.id}");
            
        } catch(Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
        
    }
    
    
    def getCCAvenueConfig(){

//        Config merchantId =  Config.findByName(Config.PAYMENT_GW_CCAVENUE_MERCHANT);
//        
//        Config workingKey =  Config.findByName(Config.PAYMENT_GW_CCAVENUE_WORKING_KEY);
//        
//        PaymentGateways gatewayStatus = PaymentGateways.findByGatewayName(Config.PAYMENT_GW_CCAVENUE);
//        
//        
//        ArrayList<ArrayList<String>> ccAvenueConfigList = new ArrayList<ArrayList<String>>();
//        HashMap item = new HashMap();        
//        System.out.println("Gateway Type"+gatewayStatus.gatewayType)
//        item.put("merchantId", merchantId.value)
//        item.put("workingKey", workingKey.value)
//        item.put("gatewayStatus", gatewayStatus.status)
//        item.put("gatewayType",gatewayStatus.gatewayType)
//       
//        ccAvenueConfigList.add(item)
        
//        return ccAvenueConfigList;
        
    }
    
    def getEnabledPaymentGateway(){
        def gatewayList = PaymentGateways.findAllByStatus("ENABLE");
        
        System.out.println(gatewayList);
        ArrayList paymentGatewayList = new ArrayList();
                
        for(def gateway:gatewayList){
            HashMap item = new HashMap();
            item.put("gatewayName", gateway.gatewayName)
            paymentGatewayList.add(item)
        }
        
        System.out.println("Enabled Payment Gateways:":paymentGatewayList);
        return paymentGatewayList;
    } 
            
    def setAllConfig() {
       
        List<Config> configs = Config.list()
        Map map = new HashMap();
        
        /**
         * iterating configs list and assign to map having 
         * key as setting's config 
         * and 
         * value as setting's config value
        **/
        if(configs != null && !configs.empty) {
            
            for (Config config : configs) map.put(config.name, config.value);
        }
        
        Properties props = new Properties()
        props.putAll(map)
        return props
    }
    
    def updateOpenStackConfiguration(requestData) {
        try {

//            Config endpointUrl =  Config.findByName(Config.OPENSTACK_ENDPOINT_URL);
//            endpointUrl.value = requestData.endpointUrl
//            endpointUrl.save(flush: true)
//            
//            Config adminUuid =  Config.findByName(Config.OPENSTACK_ADMIN_UUID);
//            adminUuid.value = requestData.adminUuid
//            adminUuid.save(flush: true)
//            
//            Config adminPassword =  Config.findByName(Config.OPENSTACK_ADMIN_PASSWORD);
//            adminPassword.value = requestData.adminPassword
//            adminPassword.save(flush: true)
//            
//            ConfigLoader.isConfigModified = true
//            
            return ["OK"];
 
        } catch(Exception ex){
            [ex] as JSON
        }
    }
    
    def reloadConfigurationProperty() {
        
        ConfigLoader configLoader = ConfigLoader.getInstance();
        configLoader.loadProperties();

        Properties props = configLoader.getProperties();
        
        // after sync gets over, change the isConfigModified flag to false
        ConfigLoader.isConfigModified = false
        
        return "OK";
        
    }
    
    def checkOpenStackConfigured() {

        ConfigLoader configLoader = ConfigLoader.getInstance();
           
        Properties props = configLoader.getProperties();
        
//        if((props.get(Config.OPENSTACK_ENDPOINT_URL)) == "" || (props.get(Config.OPENSTACK_ENDPOINT_URL)) == null) {
//            
//            return "false";
//        } else {
//            return "true";
//        }

    }
    
    def checkOpenStackAuthenticated(endpointUrl, adminUuid, adminPassword) {
        
        try {

            ConfigLoader configLoader = ConfigLoader.getInstance(); 
            Properties props = configLoader.getProperties();

            OpenStackServer server = new OpenStackServer(endpointUrl, adminUuid, adminPassword, null);

//            OSClient os = server.authenticate();
   
            return "true";

        } catch (AuthenticationException ex) {
            System.out.println(ex);

        } catch(Exception ex){
            [ex] as JSON
        }
        
    }
    
    def userRoleCreation() {
        try{
            
            ConfigLoader configLoader = ConfigLoader.getInstance();

            Properties props = configLoader.getProperties();
            
            def openStackURL = Config.findByName(Config.OPENSTACK_ENDPOINT_URL).value;
            def adminUUID = Config.findByName(Config.OPENSTACK_ADMIN_UUID).value;
            def adminPassword = Config.findByName(Config.OPENSTACK_ADMIN_PASSWORD).value;

            OpenStackServer server = new OpenStackServer(openStackURL, adminUUID, adminPassword, null);

//            OSClient os = server.authenticate();
            
            def openStackRoleName = "_member_";
            
            Role role = os.identity().roles().getByName(openStackRoleName);

            if(role == null) {

                os.identity().roles().create(openStackRoleName)
            }
            
            return "true";
        } catch(Exception ex){
            [ex] as JSON
        }
    }
    
}
