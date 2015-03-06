package org.zenoss.client.api;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import org.zenoss.client.common.ApplicationException;
import org.zenoss.client.modal.entities.device.Device;
import org.zenoss.client.modal.entities.device.Dictionary;
import org.zenoss.client.modal.entities.device.Direction;
import org.zenoss.client.modal.entities.device.GraphDef;
import org.zenoss.client.modal.entities.device.IPService;
import org.zenoss.client.modal.entities.device.LocationNode;
import org.zenoss.client.modal.entities.device.MetaType;

/**
 * Operations for DeviceService Organizers and Devices.
 *
 * Available at: /zport/dmd/device router
 */
public interface DeviceService extends ZenossService {

    /**
     * Adds a new location organizer specified by the parameter id to the parent organizer specified by contextUid.
     *
     * @return
     */
    public Dictionary addLocationNode(LocationNode locationNode) throws ApplicationException;
    
    
    /**
     * Returns the list of all device classes.
     * 
     * @return 
     */
    public List<String> getDeviceClasses()throws ApplicationException;
    
    
    /**
     * Get a list of all device classes.
     * 
     * @return
     * @throws ApplicationException 
     */
    public HashMap getProductionStates()throws ApplicationException;
    
    /**
     * Get a list of available device priorities.
     * 
     * @return
     * @throws ApplicationException 
     */
    public HashMap getPriorities()throws ApplicationException;
    
    
    /**
     * Get a list of available collectors.
     * 
     * @return
     * @throws ApplicationException 
     */
    public HashMap getCollectors() throws ApplicationException;
    
    
    /**
     * Add a device.
     * 
     * @param device
     * @return uuid
     * @throws org.zenoss.client.common.ApplicationException
     */
    public String addDevice(Device device) throws ApplicationException;   
    
    /**
     * Remove a device.
     * 
     * @param device
     * @return 
     * @throws org.zenoss.client.common.ApplicationException
     */
    public String removeDevice(Device device) throws ApplicationException;   
    
    /**
     * This method returns either individual device info by passing device 
     * if null it will returns list of devices
     * 
     * @param device
     * @return
     * @throws ApplicationException 
     */
    public List<Dictionary> getDevices(Device device) throws ApplicationException;
    
    
    /**
     * Get all the graphs 
     * 
     * @param device
     * @return
     * @throws ApplicationException 
     */
    public List<GraphDef> getGraphDefs(Device device) throws ApplicationException;                            
    
    
    /**
     * Get the graph image
     * 
     * @param graphDef
     * @return
     * @throws ApplicationException 
     */

    public InputStream getGraph(GraphDef graphDef, Long width, Long dateRange,
            String start, String end) throws ApplicationException;                                

    
      /**
     * Get a list of available templates for a device.
     * @param deviceId
     * @return 
     * @throws org.zenoss.client.common.ApplicationException
     */    
    public List<Dictionary> getTemplates(String deviceId) throws ApplicationException;
    
    /**
     * Retrieves all of the components at a given UID
     * @param deviceUid : Unique identifier of the device 
     * @return
     * @throws ApplicationException 
     */
    public List<Dictionary> getComponents(String deviceUid) throws ApplicationException;
    
    
     /**
     * Retrieves all of the components at a given UID
     * 
     * @param hashMap contains uid,meta_type, etc
     * @return
     * @throws ApplicationException 
     */
    public List<Dictionary> getComponents(HashMap hashMap) throws ApplicationException;
    
    /**
     * Adds an Ip Service
     * 
     * @param hashMap
     * @return
     * @throws ApplicationException 
     */
    Boolean addIpService(HashMap hashMap) throws ApplicationException;

    /**
     * Retrieves all of the components at a given UID. This method allows for pagination.
     * 
     * @param devive
     * @param type (optional) The meta type of the components to be retrieved (default: None)
     * @param keys
     * @param start
     * @param limit
     * @param sortName
     * @param dir
     * @param filter
     * 
     * @return 
     * @throws org.zenoss.client.common.ApplicationException 
     */
    public List<IPService> getComponents(Device devive, MetaType type, List<String> keys, Long start, Long limit, 
            String sortName, Direction dir, String filter) throws ApplicationException;
    
    
    /**
     * Set the monitoring flag for component(s)
     * 
     * @param hashMap
     * @return
     * @throws ApplicationException 
     */
    public Boolean setComponentsMonitored(HashMap hashMap) throws ApplicationException;
}
