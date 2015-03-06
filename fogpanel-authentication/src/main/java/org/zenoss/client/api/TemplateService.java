
package org.zenoss.client.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.zenoss.client.common.ApplicationException;
import org.zenoss.client.modal.entities.device.Dictionary;

public interface TemplateService extends ZenossService {
    
     /**
     * Get a list of available threshold types
     * 
     * @return 
     */
    public List<String> getThresholdTypes()throws ApplicationException;
    
    /**
     * Copy a template to a device or device class.
     * 
     * @param templateId Unique ID of the template to copy
     * @param deviceId Unique ID of the device or device class to bind to template
     * @return
     * @throws ApplicationException 
     */
    public String copyTemplate(String templateId, String deviceId) throws ApplicationException;
    
    
    /**
     * Get the thresholds for a template.
     * 
     * @param templateUid : Unique ID of a template
     * @return
     * @throws ApplicationException 
     */
    public List<Dictionary> getThresholds(String templateUid) throws ApplicationException;
    
    
    /**
     * Remove a threshold
     * 
     * @param thresholdUid : Unique identifier of threshold to remove
     * @return
     * @throws ApplicationException 
     */
    public String removeThreshold(String thresholdUid) throws ApplicationException;
    
    /**
     * Add a threshold
     * 
     * @param map contains uid, thresholdType, thresholdId, dataPoints
     * @return
     * @throws ApplicationException 
     */
    public String addThreshold(Map map) throws ApplicationException;
    
    /**
     * List of templates
     * 
     * @param uid : Identifier for the object we want templates on
     * @return
     * @throws ApplicationException 
     */
    public List<Dictionary> getObjTemplates(String uid) throws ApplicationException;
    
    
    /**
     * After successful local copy creation it will return success
     * 
     * @param uid : Identifer of the obj we wish to make the template local for
     * @param templateName : identifier of the template
     * @return
     * @throws ApplicationException 
     */
    public String makeLocalRRDTemplate(String uid, String templateName) throws ApplicationException;
    
    
    
    /**
     * Set attributes on an object. This method accepts any keyword argument for
     *   the property that you wish to set. The only required property is ”uid”.
     * @param map
     * @return
     * @throws ApplicationException 
     */
    public Dictionary setInfoForAddedThreshold(Map map) throws ApplicationException;



    
    

    
}
