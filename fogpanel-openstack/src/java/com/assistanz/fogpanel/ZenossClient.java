/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.assistanz.fogpanel;


import com.assistanz.cloud.config.ConfigLoader;
import java.util.logging.Logger;
import org.apache.commons.logging.LogFactory;
import org.zenoss.client.ZenossFacade;
import org.zenoss.client.common.ApplicationException;

/**
 *
 * @author developer
 */
public class ZenossClient {
    
    private static ZenossFacade zenossFacade = null;
    
    
    private static final Logger LOGGER
            = Logger.getLogger(ZenossClient.class.getName());
    
    /* Static 'instance' method */
    public static ZenossFacade getZenossFacadeInstance(String url, String username, String password) throws ApplicationException {
       
        
       zenossFacade =  new ZenossFacade(url, username, password);
       LOGGER.info("************** initial ZenossFacade Connection Request **************");
       return zenossFacade;
    }
    
}
