package org.zenoss.client;

import org.zenoss.client.api.ZenossService;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import org.zenoss.client.common.ApplicationException;
import org.zenoss.client.common.RestServiceHandler;

/**
 * Registry to service all the Zenoss API services
 * 
 * @author developer
 */
public class ZenosServicesRegistry {
    
    private static ZenosServicesRegistry registry = new ZenosServicesRegistry();
    private static RestServiceHandler handler;
     
    private Map<Class<? extends ZenossService>, ZenossService> map = 
            new HashMap<Class<? extends ZenossService>, ZenossService>();
    
    private ZenosServicesRegistry() {}
    
    /**
     * 
     * 
     * @param zenosURL
     * @param username
     * @param password
     * @return 
     * @throws org.zenoss.client.common.ApplicationException 
     */
    public static ZenosServicesRegistry getInstance(RestServiceHandler restServiceHandler) 
            throws ApplicationException {
        
         // registry has to to be reset for every new instance
        registry = new ZenosServicesRegistry();
        handler = restServiceHandler;
        return registry;
    }
    
    /**
     * 
     * @param cls
     * @return
     * @throws ApplicationException 
     */
    public ZenossService getService(Class<? extends ZenossService> cls) throws ApplicationException {
        
        if(map.containsKey(cls)) {
            return map.get(cls);
        }
        else {
            
            try {
                Class<?> c = Class.forName(cls.getCanonicalName());
                Constructor<?> cons = c.getConstructor(RestServiceHandler.class);
                ZenossService service = (ZenossService) cons.newInstance(handler);
                map.put(cls, service);
                return service;
            } 
            catch (ClassNotFoundException ex) {
                throw new ApplicationException(ex.getMessage());
            } 
            catch (NoSuchMethodException ex) {
                throw new ApplicationException(ex.getMessage());
            }
            catch (SecurityException ex) {
                throw new ApplicationException(ex.getMessage());
            }
            catch (InstantiationException ex) {
                throw new ApplicationException(ex.getMessage());
            }
            catch (IllegalAccessException ex) {
                throw new ApplicationException(ex.getMessage());
            }
            catch (IllegalArgumentException ex) {
                throw new ApplicationException(ex.getMessage());
            }
            catch (InvocationTargetException ex) {
                throw new ApplicationException(ex.getMessage());
            }
        }        
    }
    
}
