
import com.assistanz.fogpanel.*
import com.assistanz.fogpanel.AppVersion
import com.assistanz.fogpanel.LicenseValidatorJob
//import com.assistanz.fogpanel.LateFeeJob
//import com.assistanz.fogpanel.CreditLimitNotificationJob
//import com.assistanz.fogpanel.CloudMaintainanceJob
//import com.assistanz.fogpanel.CardValidityJob
//import com.assistanz.fogpanel.CancelAccountCleanJob
//import com.assistanz.fogpanel.BillingJob
//import com.assistanz.fogpanel.AutoPaymentJob
import com.assistanz.fogpanel.UnLockAccountJob
import com.assistanz.cloud.config.ConfigLoader
import grails.plugin.springsecurity.SpringSecurityService
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
        
        //      create admin user
        User admin = User.findByUsername("admin");
        if (admin == null ) {                        
            admin.username = "admin"          
            admin.uuid= "admin"          
            admin.enabled= true          
            admin.save();
        }
        
        //create admin and user role
        def adminRole = Role.findByAuthority('ROLE_ADMIN') ?: new Role(authority: 'ROLE_ADMIN').save()
        def userRole = Role.findByAuthority('ROLE_USER') ?: new Role(authority: 'ROLE_USER').save()        
        User user =User.findByUsername("admin")
        if(UserRole.findByUser(user) == null){
            //set admin role for admin user.        
            UserRole.create(admin,adminRole,true)                            
        }
                          
        // instantiate static instance for ConfigLoader class
        ConfigLoader configLoader = ConfigLoader.getInstance();
        configLoader.loadProperties();
        
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
        
        JSON.registerObjectMarshaller(Tax) {
            def TaxArray = [:]
            TaxArray['name'] = it.name      
            TaxArray['description'] = it.description                               
            TaxArray['percentage'] = it.percentage
            return TaxArray
        }       
        
    }
    
    def destroy = {
    }
}
