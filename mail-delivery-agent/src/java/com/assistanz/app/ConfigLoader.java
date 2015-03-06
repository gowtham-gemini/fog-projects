/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assistanz.app;

import grails.util.Holders;
import java.util.Properties;

import org.springframework.context.ApplicationContext;

/**
 *
 * @author ds
 */
public class ConfigLoader {
    ConfigService configService;
    
    private static ConfigLoader configLoader = new ConfigLoader();
    
    public static Properties props = new Properties();
    
    //By default isConfigModified flag is false
    public static Boolean isConfigModified = false;
    
    /* Static 'instance' method */
    public static ConfigLoader getInstance( ) {
       return configLoader;
    }
    
    static{
        System.out.println("******** Config loader");
    }
    
    protected void loadProperties() throws Exception {
        
        ApplicationContext ctx = Holders.getGrailsApplication().getMainContext();
        configService = (ConfigService)ctx.getBean("configService"); 
         
        // setting saveConfig properties
        props.putAll((Properties)configService.setSaveConfig());
        
        // setting mailConfig properties
        props.putAll((Properties)configService.setMailConfig());
        
        System.out.println("props"+props);
        
        // setting mail manager configs
        Properties mailManagerProps = new Properties();
        mailManagerProps.put("mail.smtp.host", props.getProperty(AppConstant.MAIL_HOST));
        mailManagerProps.put("mail.smtp.auth", "true");
        mailManagerProps.put("mail.smtp.starttls.enable","true");
        
        //for SSL enabled
        if (props.getProperty(AppConstant.MAIL_SECURECONNECTION) != null &&
                !props.getProperty(AppConstant.MAIL_SECURECONNECTION).isEmpty() &&
                props.getProperty(AppConstant.MAIL_SECURECONNECTION).equals("SSL")) {
            
            System.out.println("inside SSL");
            
            mailManagerProps.put("mail.smtp.socketFactory.port", "465");
            mailManagerProps.put("mail.smtp.socketFactory.class",
                    "javax.net.ssl.SSLSocketFactory");
            mailManagerProps.put("mail.smtp.socketFactory.fallback", "false");
        }
        
        mailManagerProps.put("mail.smtp.port", props.getProperty(AppConstant.MAIL_PORT));
        
        MailManager mailManager = MailManager.getInstance();      
        mailManager.setConfig(props.getProperty(AppConstant.MAIL_USERNAME), 
                props.getProperty(AppConstant.MAIL_PASSWORD), 
                mailManagerProps);
    }
    
    public static Properties getProperties() {
        return props;
    }
    
}
