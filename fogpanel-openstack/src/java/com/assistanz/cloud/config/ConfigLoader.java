/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assistanz.cloud.config;

import grails.util.Holders;
import java.util.Properties;
import com.assistanz.fogpanel.ConfigService;

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
                 
        // setting Config properties
        props.putAll((Properties)configService.setAllConfig());
        
//        System.out.println("props"+props);

    }
    
    public static Properties getProperties() {
        return props;
    }
       
}
