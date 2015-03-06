
import com.assistanz.fogpanel.*
import com.assistanz.fogpanel.AppVersion
import com.assistanz.fogpanel.LicenseValidatorJob
import com.assistanz.fogpanel.SecurityGroupCleanJob
import com.assistanz.fogpanel.LateFeeJob
import com.assistanz.fogpanel.DailyUsageCheckJob
import com.assistanz.fogpanel.DailyUsageCalcJob
import com.assistanz.fogpanel.CreditLimitNotificationJob
import com.assistanz.fogpanel.CloudMaintainanceJob
import com.assistanz.fogpanel.CardValidityJob
import com.assistanz.fogpanel.CancelAccountCleanJob
import com.assistanz.fogpanel.BillingJob
import com.assistanz.fogpanel.BandwidthCalcJob
import com.assistanz.fogpanel.AutoPaymentJob
import com.assistanz.fogpanel.AccountUsageSynchJob
import com.assistanz.fogpanel.UnLockAccountJob
import com.assistanz.fogpanel.VolumeJob
import com.assistanz.fogpanel.VirtualMachineJob
import grails.plugins.springsecurity.SpringSecurityService
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import grails.converters.JSON
import java.util.Date;
import java.sql.Timestamp;
import org.codehaus.groovy.grails.commons.ApplicationHolder

class BootStrap { 
    
    SpringSecurityService springSecurityService
    def currency = ApplicationHolder.getApplication().config.billing.defaultCurrency
    
    def init = { servletContext ->	
        User admin = User.findByUsername("admin");
        if (admin && admin.password == "passw0rd") {
            admin.password = "password"
            admin.save();
            
            Date date = new Date();
            def time = new Timestamp(date.getTime()) 
            
            def account = admin.account
            account.accountType = "ADMIN";
            account.signUpDate = time
            account.preferredLanguage = "en"
            account.activationDate = time
            account.cloudPassword = "xxxxxxxxxxxxx"
            account.save()   
        }
        
        def grailsApplication = ApplicationHolder.application
        
        def appVersionInfo = AppVersion.findByFogVersion(grailsApplication.metadata.'app.version')
        
        Calendar initialize_date = Calendar.getInstance()
        
        if(!appVersionInfo) {
            
            def newAppVersion =  new AppVersion()
            newAppVersion.fogVersion = grailsApplication.metadata.'app.version'
            newAppVersion.initializeDate = initialize_date.getTime();
            
            def data = newAppVersion.fogVersion + newAppVersion.initializeDate;
            
            try {
                
                StringBuilder digestBuffer = new StringBuilder();
                
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                md.update(data.getBytes());
                byte[] digestBytes = md.digest();

                for (int i = 0; i < digestBytes.length; i++) {
                    digestBuffer.append(Integer.toHexString(0xFF & digestBytes[i]));
                }

                newAppVersion.checksum= digestBuffer.toString();
                newAppVersion.save(flush:true);   
            } catch (NoSuchAlgorithmException ex) { 
                throw ex
            }
        }
        
        def fogInstanceId =  Config.findByName(Config.FOG_INSTANCE_ID).value;
        def licenseeEmail=  Config.findByName(Config.LICENSEE_EMAIL).value;

        if(fogInstanceId !="" && licenseeEmail !="") {
            LicenseValidatorJob.schedule(21600000);  //21600000
        }
         UnLockAccountJob.schedule(60000); // for 60 sec
//        def unlockTime =  Config.findByName(Config.ACCOUNT_UNLOCK_TIME).value;
//        if(unlockTime !="" && unlockTime !="") {
//             //By Config
//        } else {
//            UnLockAccountJob.schedule(60000);  //10 min default
//        }
        
//        AccountUsageSynchJob.triggerNow()
//        AutoPaymentJob.triggerNow()
//        BandwidthCalcJob.triggerNow()
//        BillingJob.triggerNow()
//        CancelAccountCleanJob.triggerNow()
//        CardValidityJob.triggerNow()
//        CloudMaintainanceJob.triggerNow()
//        CreditLimitNotificationJob.triggerNow()
//        DailyUsageCalcJob.triggerNow()
//        DailyUsageCheckJob.triggerNow()
//        LateFeeJob.triggerNow()
//        SecurityGroupCleanJob.triggerNow()
//        UnLockAccountJob.triggerNow()
//        VirtualMachineJob.triggerNow()
//        VolumeJob.triggerNow()

        Properties props = new Properties()
        Config host =  Config.findByName(Config.MAIL_CONFIG_HOST);
        Config port =  Config.findByName(Config.MAIL_CONFIG_PORT);
        Config userName =  Config.findByName(Config.MAIL_CONFIG_USER_NAME);
        Config from =  Config.findByName(Config.MAIL_CONFIG_FROM);
        Config ssl =  Config.findByName(Config.MAIL_CONFIG_SSL);
        Config password =  Config.findByName(Config.MAIL_CONFIG_PASSWORD);        
        
        if((from.value != "" || from.value != null)&& (host.value != "" || host.value != null) && (userName.value != "" || userName.value != null) && (ssl.value != "" || ssl.value != null)) {
            
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
            
            
        }    
        
        JSON.registerObjectMarshaller(ComputingOffer) {
            def returnArray = [:]
            returnArray['id'] = it.id
            returnArray['available'] = it.available
            returnArray['bandWidth'] = it.bandWidth
            returnArray['baseOs'] = it.baseOs
            returnArray['cluster'] = it.cluster
            returnArray['pod'] = it.pod
            returnArray['zone'] = it.zone                
            returnArray['computingOfferZoneCosts'] = it.computingOfferZoneCosts
            returnArray['coreCpuSpeed'] = it.coreCpuSpeed
            returnArray['cpuNumber'] = it.cpuNumber
            returnArray['defaultUse'] = it.defaultUse
            returnArray['deleted'] = it.deleted
            returnArray['description'] = it.description
            returnArray['hostTags'] = it.hostTags
            returnArray['hypervisor'] = it.hypervisor
               
            returnArray['diskReadRateBPS'] = it.diskReadRateBPS
            returnArray['diskWriteRateBPS'] = it.diskWriteRateBPS
            returnArray['diskReadRateIOPS'] = it.diskReadRateIOPS
            returnArray['diskWriteRateIOPS'] = it.diskWriteRateIOPS
            returnArray['diskIO'] = it.diskIO
            
            returnArray['isSystem'] = it.isSystem
            returnArray['limitCpuUse'] = it.limitCpuUse
            returnArray['memory'] = it.memory
            returnArray['name'] = it.name
            returnArray['networkRate'] = it.networkRate
            returnArray['offerHA'] = it.offerHA
            returnArray['offerReferenceId'] = it.offerReferenceId
            returnArray['orderNo'] = it.orderNo
            returnArray['memory'] = it.memory            
            returnArray['storageType'] = it.storageType            
            returnArray['tags'] = it.tags           
            returnArray['currency'] = currency         
            return returnArray
        }
            
        JSON.registerObjectMarshaller(DiskOffer) {

                def diskArray = [:]
                diskArray['id'] = it.id      
                diskArray['name'] = it.name  
                diskArray['available'] = it.available  
                diskArray['currency'] = currency       
                diskArray['cluster'] = it.cluster
                diskArray['pod'] = it.pod
                diskArray['zone'] = it.zone    
                diskArray['qoSType'] = it.qoSType;
                diskArray['isCustomizedIops'] = it.isCustomizedIops;
                diskArray['minIOPS'] = it.minIOPS;
                diskArray['maxIOPS'] = it.maxIOPS;
                diskArray['isPublic'] = it.isPublic;
                if(it.domain) {
                    diskArray['domainId'] = it.domainId;
                } else {
                    diskArray['domainId'] = "";
                }
                diskArray['hypervisorSnapReserve'] = it.hypervisorSnapReserve;
                diskArray['diskReadRateBPS'] = it.diskReadRateBPS
                diskArray['diskWriteRateBPS'] = it.diskWriteRateBPS
                diskArray['diskReadRateIOPS'] = it.diskReadRateIOPS
                diskArray['diskWriteRateIOPS'] = it.diskWriteRateIOPS
                diskArray['diskOfferZoneCosts'] = it.diskOfferZoneCosts
                diskArray['diskOfferReferenceId'] = it.diskOfferReferenceId
                diskArray['minSize'] = it.minSize
                diskArray['storageType'] = it.storageType;
                diskArray['maxSize'] = it.maxSize
                diskArray['deleted'] = it.deleted
                diskArray['description'] = it.description
                diskArray['size'] = it.size
                diskArray['tags'] = it.tags                
                diskArray['diskReadRateIOPS'] = it.diskReadRateIOPS
                diskArray['diskWriteRateIOPS'] = it.diskWriteRateIOPS
                
                diskArray['customized'] = it.customized
                return diskArray
        }    
	
        JSON.registerObjectMarshaller(MiscellaneousOffer) {
            def MiscellaneousArray = [:]                                 
            MiscellaneousArray['id'] = it.id      
            MiscellaneousArray['name'] = it.name                               
            MiscellaneousArray['miscellaneousOfferZoneCosts'] = it.miscellaneousOfferZoneCosts
            MiscellaneousArray['description'] = it.description
            MiscellaneousArray['unit'] = it.unit       
            return MiscellaneousArray
        }
        
	JSON.registerObjectMarshaller(MiscellaneousOfferZoneCost) {
            def MiscellaneousOfferZoneCostArray = [:]
            MiscellaneousOfferZoneCostArray['id'] = it.id      
            MiscellaneousOfferZoneCostArray['cluster'] = it.cluster                               
            MiscellaneousOfferZoneCostArray['zone'] = it.zone
            MiscellaneousOfferZoneCostArray['pod'] = it.pod
            MiscellaneousOfferZoneCostArray['cost'] = it.cost
            MiscellaneousOfferZoneCostArray['miscellaneousOffer'] = it.miscellaneousOffer
            MiscellaneousOfferZoneCostArray['currency'] = currency   
            return MiscellaneousOfferZoneCostArray
        } 
        JSON.registerObjectMarshaller(Tax) {
            def TaxArray = [:]
            TaxArray['name'] = it.name      
            TaxArray['description'] = it.description                               
            TaxArray['percentage'] = it.percentage
            return TaxArray
        }
        
        JSON.registerObjectMarshaller(MailTemplate) {
            def MailTemplateArray = [:]
            MailTemplateArray['name'] = it.name      
            MailTemplateArray['description'] = it.content                               
            MailTemplateArray['subject'] = it.subject
            MailTemplateArray['subject'] = it.subject
            MailTemplateArray['title'] = it.title
            MailTemplateArray['hasHeader'] = it.hasHeader
            MailTemplateArray['hasFooter'] = it.hasFooter
            MailTemplateArray['hasSignature'] = it.hasSignature
            
            return MailTemplateArray
        }
        
        
    }
    
    def destroy = {
    }
}
