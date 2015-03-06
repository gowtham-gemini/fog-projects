/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assistanz.openstack;

import com.assistanz.cloud.config.ConfigLoader;
import java.util.Properties;
import org.openstack4j.api.OSClient;
import org.openstack4j.openstack.OSFactory;
import com.assistanz.fogpanel.Config;

/**
 *
 * @author gowtham
 */
public class OpenStackServer {
    
    
    private String endpoint;
    private String username;
    private String password;
    private String domainName;
    
    /**
     * Creates a new Server Instance that could connect to the openstack , with the provided details
     * 
     * @param endpoint
     * @param username
     * @param password
     * @param domainName 
     */
    public OpenStackServer(String endpoint, String username, String password, String domainName) {
        this.endpoint = endpoint;
        this.username = username;
        this.password = password;
        this.domainName = domainName;
    }
    
     /* 
    This methods returns instance of openstack server
    with needed props
    */
    public static OpenStackServer getInstance( ) {
        
        ConfigLoader configLoader = ConfigLoader.getInstance();

        Properties props = configLoader.getProperties();
        
        return new OpenStackServer(props.get(Config.OPENSTACK_ENDPOINT_URL).toString()
                                , props.get(Config.OPENSTACK_ADMIN_UUID).toString(), 
                                props.get(Config.OPENSTACK_ADMIN_PASSWORD).toString(), null);    
    }
    
    /**
     * Authenticate user to OpenStack server
     * 
     * @return  
     */
    public OSClient authenticate() {
        
        OSClient os = OSFactory.builderV3()
                       .endpoint(endpoint)
                       .credentials(username, password)
                       .domainName(domainName)
                       .authenticate();
     
        return os;
    }
    
}
