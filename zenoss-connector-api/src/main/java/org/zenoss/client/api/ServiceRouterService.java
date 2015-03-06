
package org.zenoss.client.api;

import java.util.HashMap;
import java.util.List;
import org.zenoss.client.common.ApplicationException;
import org.zenoss.client.modal.entities.device.Dictionary;


public interface ServiceRouterService extends ZenossService {
    
    /**
     * Get a list of instances for a service UID.
     * 
     * @param hashMap uid:Service UID to get instances
     * 
     * @return 
     * @throws org.zenoss.client.common.ApplicationException 
     */
    List<Dictionary> getInstances(HashMap hashMap) throws ApplicationException;
    
    
    
    /**
     * Add a new service class.
     * 
     * @param hashMap
     * @return
     * @throws ApplicationException 
     */
    String addClass(HashMap hashMap) throws ApplicationException;
    
    
    /**
     * Set attributes on a service. This method accepts any keyword argument for
     *  the property that you wish to set. The only required property is ”uid”.
     * 
     * @param hashMap
     * @return
     * @throws ApplicationException 
     */
    String setInfoForAddedClass(HashMap hashMap) throws ApplicationException;
    
    /**
     * Get the list of registered service classes
     * 
     * @param hashMap
     * @return
     * @throws ApplicationException 
     */
    List<Dictionary> getClassNames(HashMap hashMap) throws ApplicationException;
}
