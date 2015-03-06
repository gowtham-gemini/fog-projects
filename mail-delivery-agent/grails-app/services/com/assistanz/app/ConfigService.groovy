package com.assistanz.app

import grails.transaction.Transactional
import com.assistanz.app.Config
import com.assistanz.app.MailManager

@Transactional
class ConfigService {

    def serviceMethod() {

    }
    
    def setSaveConfig() {
        
        Properties props = new Properties()
        
        // checking save config before saving
        Config configInstance = Config.findByName(AppConstant.SAVE_CONFIG)
        
        if(configInstance?.value?.equals(AppConstant.STORE_ALL_MAILS)) {
            props.put("SAVE_ALL_CONFIG_FLAG", "true");
        } else {
            props.put("SAVE_ALL_CONFIG_FLAG", "false");
        }
        
        return props
    }
    
    def get(String x) {
        
    }
    
    def setMailConfig() {
        
        List<Config> configs = Config.list()
        Map<String,String> map = new HashMap<String,String>();
        
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
    
    def varifyMailConfig() {
        try {
            MailManager mailManager = MailManager.getInstance(); 
            mailManager.sendMail("balajim0788@gmail.com", "Config test", "this is send from fog panel mail manager")
            
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }    
        
    }
    
    def testMailWithNewConfig(def jsonString) {
        
        // setting mail manager configs
        Properties mailManagerProps = new Properties();
        mailManagerProps.put("mail.smtp.host",jsonString.host);
        mailManagerProps.put("mail.smtp.auth", "true");
        mailManagerProps.put("mail.smtp.starttls.enable","true");
        
        //for SSL enabled
        if (jsonString.secureConnection != null &&
                !jsonString.secureConnection.empty &&
                jsonString.secureConnection == "SSL") {
            
            System.out.println("inside SSL");
            
            mailManagerProps.put("mail.smtp.socketFactory.port", "465");
            mailManagerProps.put("mail.smtp.socketFactory.class",
                    "javax.net.ssl.SSLSocketFactory");
            mailManagerProps.put("mail.smtp.socketFactory.fallback", "false");
        }
        
        mailManagerProps.put("mail.smtp.port", jsonString.port);
        
        MailManager mailManager = MailManager.getInstance();      
//        mailManager.setConfig(jsonString.userName, 
//                jsonString.password, 
//                mailManagerProps);
        
        mailManager.sendMail(jsonString.from, "Config test", "this is send from fog panel mail manager")
        
        
    }
     
}
