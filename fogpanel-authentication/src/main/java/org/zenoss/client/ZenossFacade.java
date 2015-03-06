package org.zenoss.client;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.binary.Base64;
import org.zenoss.client.api.DeviceService;
import org.zenoss.client.api.JobService;
import org.zenoss.client.api.ServiceRouterService;
import org.zenoss.client.api.TemplateService;
import org.zenoss.client.common.ApplicationException;
import org.zenoss.client.common.RestServiceHandler;
import org.zenoss.client.modal.entities.device.Device;
import org.zenoss.client.modal.entities.device.DeviceResponse;
import org.zenoss.client.modal.entities.device.GraphDef;

import org.zenoss.client.modal.entities.device.Dictionary;
import org.zenoss.client.modal.entities.device.Direction;
import org.zenoss.client.modal.entities.device.GraphDef;
import org.zenoss.client.modal.entities.device.GraphDefType;
import org.zenoss.client.modal.entities.device.IPService;
import org.zenoss.client.modal.entities.device.MetaType;
import org.zenoss.client.modal.entities.device.Threshold;
import org.zenoss.client.modal.service.DeviceServiceImpl;
import org.zenoss.client.modal.service.JobServiceImpl;
import org.zenoss.client.modal.service.ServiceRouterServiceImpl;
import org.zenoss.client.modal.service.TemplateServiceImpl;

/**
 * Hides the complexities of Zenoss API's for monitoring tasks
 *
 * @author developer
 */
public class ZenossFacade {

    private DeviceService deviceService;
    private TemplateService templateService;
    private JobService jobService;
    private ServiceRouterService routerService;

    private static final String OS_TYPE_WINDOWS = "WINDOWS";
    private static final String OS_TYPE_LINUX = "LINUX";
    private static final String ALARM_TYPE_CPU_LINUX_SYSTEM = "CPU_LINUX_SYSTEM";
    private static final String ALARM_TYPE_CPU_LINUX_USER = "CPU_LINUX_USER";
    private static final String ALARM_TYPE_CPU_WINDOWS = "CPU_WINDOWS";
    private static final String ALARM_TYPE_MEMORY_WINDOWS = "MEMORY_WINDOWS";
    private static final String ALARM_TYPE_MEMORY_LINUX = "MEMORY_LINUX";
    private static final String ALARM_TYPE_DISK_IO_READ_LINUX = "DISK_IO_READ_LINUX";
    private static final String ALARM_TYPE_DISK_IO_WRITE_LINUX = "DISK_IO_WRITE_LINUX";
    private static final String ALARM_TYPE_DISK_IO_READ_WINDOWS = "DISK_IO_READ_WINDOWS";
    private static final String ALARM_TYPE_DISK_IO_WRITE_WINDOWS = "DISK_IO_WRITE_WINDOWS";
    private static final String ALARM_TYPE_FILE_WINDOWS_READ = "FILE_WINDOWS_READ";
    private static final String ALARM_TYPE_FILE_WINDOWS_WRITE = "FILE_WINDOWS_WRITE";
    private static final String ALARM_TYPE_FILE_LINUX = "FILE_LINUX";

    public ZenossFacade(RestServiceHandler handler) throws ApplicationException {

        ZenosServicesRegistry registry = ZenosServicesRegistry.getInstance(handler);

        deviceService = (DeviceService) registry.getService(DeviceServiceImpl.class);
        templateService = (TemplateService) registry.getService(TemplateServiceImpl.class);
        jobService = (JobService) registry.getService(JobServiceImpl.class);
        routerService = (ServiceRouterService) registry.getService(ServiceRouterServiceImpl.class);

    }

    public DeviceResponse enable(Device device) throws ApplicationException {

        DeviceResponse deviceResponse = new DeviceResponse();

        System.out.println(" Enabling Device");
        String uuid = "";
        try {

            // add device
            if (device != null) {

                uuid = this.addDevice(device);

                deviceResponse.setJobId(uuid);

            } else {
                throw new ApplicationException("Device cannot be null");
            }

            // get the added device
            String deviceId = "/zport/dmd/Devices" + device.getDeviceClass() + "/devices/" + device.getDeviceName();

            deviceResponse.setDeviceId(deviceId);

            // getting current status of device
            deviceResponse.setJobStatus(jobService.userjobs(uuid));

        } catch (ApplicationException applicationException) {

            System.out.println("Exception: " + applicationException.getMessage());
            throw applicationException;
        }

        return deviceResponse;

    }

    public void overWriteDefaultTemplates(Device addedDevice) throws ApplicationException {

        Dictionary dictionary = this.getAddedDevice(addedDevice);

        // reassigning the deviceId once its created with success
        String deviceId = addedDevice.getUid();

        // overwrite all the default monitoring templates for the device 
        this.templateOverwritten(deviceId);

        // delete all thresholds assosciated with monitoring templates
        this.removeThresholds(deviceId);

        // list all components and create a local copy for those templates assosciated with each component
        this.createLocalCopyByComponentType(deviceId);

        // delete all thresholds assosciated with components templates
        this.removeTemplateByComponentType(deviceId);
    }

    private String addDevice(Device device) throws ApplicationException {

        if (device != null && device.getOsType() != null && !device.getOsType().isEmpty()) {

            if (device.getOsType().equals(OS_TYPE_LINUX)) {
                device.setDeviceClass("/Server/Linux");
            } else if (device.getOsType().equals(OS_TYPE_WINDOWS)) {
                device.setDeviceClass("/Server/Windows");
            }
        }
        return deviceService.addDevice(device);
    }

    private Dictionary getAddedDevice(Device deviceAdded) throws ApplicationException {

        Dictionary addedDic = null;

        List<Dictionary> listDic = deviceService.getDevices(deviceAdded);

        if (listDic != null && !listDic.isEmpty()) {

            addedDic = listDic.get(0);
        }
        if (addedDic == null) {
            throw new ApplicationException("Device not yet created");
        }
        System.out.println("" + addedDic.getName() + " created sucess");

        return addedDic;
    }

    private void templateOverwritten(String deviceId) throws ApplicationException {

        // get templates by device ID
        List<Dictionary> templates = deviceService.getTemplates(deviceId);

        //overwrite templates
        for (Dictionary template : templates) {

            System.out.println(template.getText() + " overwritten " + templateService.copyTemplate(template.getUid(), deviceId));

        }
    }

    private void removeThresholds(String deviceId) throws ApplicationException {

        // get overwritten templates by device ID
        List<Dictionary> overWrittenTemplates = deviceService.getTemplates(deviceId);

        // iterrate the overwritten templates and get thresholds for the same
        for (Dictionary template : overWrittenTemplates) {

            List<Dictionary> thresholds = templateService.getThresholds(template.getUid());

            for (Dictionary threshold : thresholds) {

                System.out.println("" + threshold.getName() + " removed " + templateService.removeThreshold(threshold.getUid()));

            }
        }

    }

    private void createLocalCopyByComponentType(String deviceId) throws ApplicationException {

        List<Dictionary> componentsList = deviceService.getComponents(deviceId);

        // list all components and create a local copy for those templates assosciated with each component
        for (Dictionary component : componentsList) {

            List<Dictionary> objectTemplates = templateService.getObjTemplates(component.getUid());

            for (Dictionary objectTemplate : objectTemplates) {

                System.out.println("" + objectTemplate.getName() + " : " + component.getName() + " local copy created  " + templateService.makeLocalRRDTemplate(component.getUid(), objectTemplate.getName()));
            }

        }

    }

    private void removeTemplateByComponentType(String deviceId) throws ApplicationException {

        List<Dictionary> componentsList = deviceService.getComponents(deviceId);

        for (Dictionary component : componentsList) {

            List<Dictionary> objectTemplates = templateService.getObjTemplates(component.getUid());

            for (Dictionary objectTemplate : objectTemplates) {

                List<Dictionary> thresholds = templateService.getThresholds(objectTemplate.getUid());

                for (Dictionary threshold : thresholds) {

                    System.out.println("" + threshold.getName() + " removed " + templateService.removeThreshold(threshold.getUid()));

                }

            }
        }

    }

    public String jobStatus(String uuid) throws ApplicationException {

        return jobService.userjobs(uuid);
    }

    public String disable(Device device) throws ApplicationException {

        System.out.println("Disabling device ");
        String status = "";

        if (device != null && device.getUid() != null && !device.getUid().isEmpty()) {

            status = deviceService.removeDevice(device);
        }
        return status;

    }

    public String getGraph(Device device, GraphDefType graphDefType, Long dateRange,
            Long width) throws ApplicationException {

        try {
            InputStream stream = null;

            for (GraphDef def : deviceService.getGraphDefs(device)) {
                if (def.getType().equals(graphDefType)) {
                    stream = deviceService.getGraph(def, width, dateRange, "", "");
                    break;
                }
            }

            int len;
            int size = 1024;
            byte[] buf;

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            buf = new byte[size];
            int count = 0;

            while ((len = stream.read(buf, 0, size)) != -1) {
                count++;
                bos.write(buf, 0, len);
            }

            bos.flush();
            buf = bos.toByteArray();
            bos.close();

            String base64String = Base64.encodeBase64String(buf);

            return base64String;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ApplicationException(ex.getMessage());
        }
    }

    public HashMap getCollectors() throws ApplicationException {

        return deviceService.getCollectors();
    }

    public Dictionary addAlarm(Device device, Threshold threshold) throws ApplicationException {

        if (device != null && device.getUid() != null && !device.getUid().isEmpty()
                && threshold.getAlarmType() != null && !threshold.getAlarmType().isEmpty()) {

            // add thresholds for that template
            if ((threshold.getAlarmType().equals(ALARM_TYPE_DISK_IO_READ_WINDOWS)
                    || threshold.getAlarmType().equals(ALARM_TYPE_DISK_IO_READ_WINDOWS))) {

                this.addThresholdForWindowsHardDisk(device.getUid(), threshold);

                // gnerate a map for updating threshod
                Map thresholdUpdationMap = new HashMap();
                this.generateThresholdForWindowsMap(thresholdUpdationMap, device, threshold);

                //update the threshold with min max values
                return templateService.setInfoForAddedThreshold(thresholdUpdationMap);

            } else if (threshold.getAlarmType().equals(ALARM_TYPE_FILE_LINUX)
                    || threshold.getAlarmType().equals(ALARM_TYPE_FILE_WINDOWS_READ)
                    || threshold.getAlarmType().equals(ALARM_TYPE_FILE_WINDOWS_WRITE)) {

                this.addThresholdForFileSystem(device.getUid(), threshold);

                // gnerate a map for updating threshod
                Map thresholdUpdationMap = new HashMap();
                this.generateThresholdForWindowsMap(thresholdUpdationMap, device, threshold);

                //update the threshold with min max values
                return templateService.setInfoForAddedThreshold(thresholdUpdationMap);

            } else {

                this.addThreshold(device.getUid(), threshold);

                // gnerate a map for updating threshod
                Map thresholdUpdationMap = new HashMap();
                this.generateThresholdMap(thresholdUpdationMap, device, threshold);

                //update the threshold with min max values
                return templateService.setInfoForAddedThreshold(thresholdUpdationMap);
            }
        }
        return null;
    }

    private void addThresholdForWindowsHardDisk(String deviceId, Threshold threshold) throws ApplicationException {

        HashMap hashMap = new HashMap();
        hashMap.put("uid", deviceId);
        hashMap.put("meta_type", "HardDisk");

        List<Dictionary> components = deviceService.getComponents(hashMap);

        for (Dictionary component : components) {

            List<Dictionary> objectTemplates = templateService.getObjTemplates(component.getUid());

            for (Dictionary objectTemplate : objectTemplates) {

                System.out.println("uid" + objectTemplate.getUid());

                threshold.setUid(objectTemplate.getUid());

                templateService.addThreshold(threshold.toHashMap());

            }
        }

    }

    private void addThresholdForFileSystem(String deviceId, Threshold threshold) throws ApplicationException {

        List<Dictionary> templateList = templateService.getObjTemplates(threshold.getPartitionName());

        if (templateList != null && !templateList.isEmpty()) {

            for (Dictionary dictionary : templateList) {

                threshold.setUid(dictionary.getUid());

                templateService.addThreshold(threshold.toHashMap());
            }

        }
    }

    private void addThreshold(String deviceId, Threshold threshold) throws ApplicationException {

        // get device templates
        List<Dictionary> templateList = deviceService.getTemplates(deviceId);

        for (Dictionary template : templateList) {

            // filter only device template
            if (template.getPath() != null && !template.getPath().isEmpty()
                    && template.getUid() != null && !template.getUid().isEmpty()) {

                // TODO to be refactored with generalised solution
                if (template.getPath().equals("Locally Defined") && template.getUid().endsWith("Device")) {

                    //updating threshold uid ie template of overwritten template
                    threshold.setUid(deviceId + "/Device");

                    System.out.println(threshold.getThresholdId() + " created " + templateService.addThreshold(threshold.toHashMap()));
                }

            }
        }

    }

    private void generateThresholdMap(Map thresholdUpdationMap, Device device, Threshold threshold) throws ApplicationException {

        //getting added threshold
        thresholdUpdationMap.put("uid", device.getUid() + "/Device/thresholds/" + threshold.getThresholdId());

        thresholdUpdationMap.put("minval", threshold.getMinval());
        thresholdUpdationMap.put("maxval", threshold.getMaxval());
        thresholdUpdationMap.put("severity", 5);

        if (device.getOsType() != null && !device.getOsType().isEmpty()
                && threshold.getAlarmType() != null && !threshold.getAlarmType().isEmpty()) {

            //Linux - WINDOWS with datasource name changes
            if (device.getOsType().equals(OS_TYPE_LINUX)) {

                if (threshold.getAlarmType().equals(ALARM_TYPE_CPU_LINUX_SYSTEM)) {
                    thresholdUpdationMap.put("dsnames", "ssCpuSystem_ssCpuSystem");
                } else if (threshold.getAlarmType().equals(ALARM_TYPE_CPU_LINUX_USER)) {
                    thresholdUpdationMap.put("dsnames", "ssCpuUser_ssCpuUser");
                } else if (threshold.getAlarmType().equals(ALARM_TYPE_MEMORY_LINUX)) {
                    thresholdUpdationMap.put("dsnames", "memAvailReal_memAvailReal");
                } else if (threshold.getAlarmType().equals(ALARM_TYPE_DISK_IO_READ_LINUX)) {
                    thresholdUpdationMap.put("dsnames", "ssIORawReceived_ssIORawReceived");
                } else if (threshold.getAlarmType().equals(ALARM_TYPE_DISK_IO_WRITE_LINUX)) {
                    thresholdUpdationMap.put("dsnames", "ssIORawSent_ssIORawSent");
                }

            } else if (device.getOsType().equals(OS_TYPE_WINDOWS)) {

                if (threshold.getAlarmType().equals(ALARM_TYPE_CPU_WINDOWS)) {
                    thresholdUpdationMap.put("dsnames", "cpuPercentProcessorTime_cpuPercentProcessorTime");
                } else if (threshold.getAlarmType().equals(ALARM_TYPE_MEMORY_WINDOWS)) {
                    thresholdUpdationMap.put("dsnames", "memoryAvailableKBytes_memoryAvailableKBytes");
                } else if (threshold.getAlarmType().equals(ALARM_TYPE_DISK_IO_READ_WINDOWS)) {
                    thresholdUpdationMap.put("dsnames", "lDiskDiskReadBytesPerSec_lDiskDiskReadBytesPerSec");
                } else if (threshold.getAlarmType().equals(ALARM_TYPE_DISK_IO_WRITE_WINDOWS)) {
                    thresholdUpdationMap.put("dsnames", "lDiskDiskWriteBytesPerSec_lDiskDiskWriteBytesPerSec");
                }

            }

        }

    }

    private void generateThresholdForWindowsMap(Map thresholdUpdationMap, Device device, Threshold threshold) throws ApplicationException {

        //getting added threshold
        thresholdUpdationMap.put("uid", threshold.getUid() + "/thresholds/" + threshold.getThresholdId());

        thresholdUpdationMap.put("minval", threshold.getMinval());
        thresholdUpdationMap.put("maxval", threshold.getMaxval());
        thresholdUpdationMap.put("severity", 5);

        if (device.getOsType() != null && !device.getOsType().isEmpty()
                && threshold.getAlarmType() != null && !threshold.getAlarmType().isEmpty()) {

            if (device.getOsType().equals(OS_TYPE_WINDOWS)) {

                if (threshold.getAlarmType().equals(ALARM_TYPE_DISK_IO_READ_WINDOWS)) {
                    thresholdUpdationMap.put("dsnames", "lDiskDiskReadBytesPerSec_lDiskDiskReadBytesPerSec");
                } else if (threshold.getAlarmType().equals(ALARM_TYPE_DISK_IO_WRITE_WINDOWS)) {
                    thresholdUpdationMap.put("dsnames", "lDiskDiskWriteBytesPerSec_lDiskDiskWriteBytesPerSec");
                } else if (threshold.getAlarmType().equals(ALARM_TYPE_FILE_WINDOWS_READ)) {
                    thresholdUpdationMap.put("dsnames", "lDiskDiskReadBytesPerSec_lDiskDiskReadBytesPerSec");
                } else if (threshold.getAlarmType().equals(ALARM_TYPE_FILE_WINDOWS_WRITE)) {
                    thresholdUpdationMap.put("dsnames", "lDiskDiskWriteBytesPerSec_lDiskDiskWriteBytesPerSec");
                }
            } else if (device.getOsType().equals(OS_TYPE_LINUX)) {

                if (threshold.getAlarmType().equals(ALARM_TYPE_FILE_LINUX)) {
                    thresholdUpdationMap.put("dsnames", "usedBlocks_usedBlocks");
                }
            }

        }

    }

    public Boolean checkComponentsAvailable(Device device) throws ApplicationException {

        if (device != null && device.getUid() != null && !device.getUid().isEmpty()) {

            List<Dictionary> componentsList = deviceService.getComponents(device.getUid());

            if (componentsList == null || componentsList.isEmpty()) {

                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;

    }

    public String deleteAlarm(Dictionary dictionary) throws ApplicationException {

        return templateService.removeThreshold(dictionary.getId());

    }

    public void getAlarms() throws ApplicationException {

    }

    /**
     * ...
     *
     * @param device
     * @return
     * @throws ApplicationException
     */
    public List<IPService> getPortList(Device device) throws ApplicationException {

        //Conver the paging parameters
        //Create the keys

        //Call the device service        
       
        return deviceService.getComponents(device, MetaType.IpService, null, null, null, null, 
                null, null);

    }

    public List<List<String>> getPartitionTemplates(HashMap hashMap) throws ApplicationException {
        
        List<Dictionary> components  = deviceService.getComponents(hashMap);
        
        List<List<String>> partitionList = new ArrayList<List<String>>();

        for(Dictionary dictionary : components) {
            
            List<Dictionary> list = templateService.getObjTemplates(dictionary.getUid());

            
            if(list != null && !list.isEmpty() && list.get(0) != null) {
                
                HashMap templateMap =  new HashMap();
                List<String> innerValue = new ArrayList<String>();
                innerValue.add(list.get(0).getUid());
                innerValue.add(dictionary.getName());
                partitionList.add(innerValue);

            }
        }
        return partitionList;
    }

    public Boolean addIpService(Device device, IPService iPService) throws ApplicationException {

        // check if the requested port registered already 
        Dictionary dictionary = this.portAlreadyExistsInServiceClass(iPService);

        // if exists add ip service         
        if (dictionary != null) {

            if (dictionary.getName() != null && !dictionary.getName().isEmpty()) {

                return this.createIpService(device.getUid(), dictionary.getName());
            }

        } else {

             // else add the device class for requested port then follow above step
            System.out.println(" port not found");
            HashMap hashMap = new HashMap();
            hashMap.put("contextUid", "/zport/dmd/Services/IpService");
            hashMap.put("id", "fogpanel-" + iPService.getPort());
            String addClassSuccess = routerService.addClass(hashMap);

            if (addClassSuccess.equals("SUCCESS")) {

                hashMap = new HashMap();
                hashMap.put("uid", "/zport/dmd/Services/IpService/serviceclasses/fogpanel-" + iPService.getPort());
                hashMap.put("name", "fogpanel-" + iPService.getPort());
                hashMap.put("port", iPService.getPort());

                if (routerService.setInfoForAddedClass(hashMap).equals("SUCCESS")) {

                    Dictionary addedService = this.portAlreadyExistsInServiceClass(iPService);

                    if (addedService != null && addedService.getName() != null && !addedService.getName().isEmpty()) {

                        return this.createIpService(device.getUid(), addedService.getName());
                    }
                }
            } else {

                return false;
            }
        }

        return false;
    }

    private Dictionary portAlreadyExistsInServiceClass(IPService iPService) throws ApplicationException {

        HashMap hashMap = new HashMap();
        hashMap.put("uid", "/zport/dmd/Services/IpService");

        if (iPService != null && iPService.getPort() != null && !iPService.getPort().isEmpty()) {

            List<Dictionary> instanceList = routerService.getInstances(hashMap);

            for (Dictionary dictionary : instanceList) {

                // check if exists
                if (iPService.getPort().equals(dictionary.getPort())) {
                    return dictionary;
                }
            }
        }
        return null;
    }

    private String getExistingClassName(String className) throws ApplicationException {

        HashMap hashMap = new HashMap();
        hashMap.put("uid", "/zport/dmd/Services/IpService");
        List<Dictionary> classNames = routerService.getClassNames(hashMap);

        for (Dictionary dictionary : classNames) {

            // check if exists
            if (className.equals(dictionary.getName())) {
                return dictionary.getText();
            }
        }
        return "";
    }

    private Boolean createIpService(String deviceId, String serviceClass) throws ApplicationException {

        HashMap map = new HashMap();
        map.put("newClassName", this.getExistingClassName(serviceClass));
        // monitoring supports only for tcp
        map.put("protocol", "tcp");
        map.put("uid", deviceId);
        map.put("userCreated", "true");
        
        try {
            return deviceService.addIpService(map);
        } catch (ApplicationException applicationException) {
            return false;
        }
     
    }

    /**
     * Get IPService by given port
     *
     * @param device
     * @return
     * @throws ApplicationException
     */
    public IPService getIpServiceByPort(Device device) throws ApplicationException {

        List<IPService> iPServices = deviceService.getComponents(device, MetaType.IpService, null, null, null, null,
                null, null);

        if (iPServices != null && !iPServices.isEmpty()) {

            for (IPService iPService : iPServices) {

                if (iPService.getPort() != null && !iPService.getPort().isEmpty()
                        && iPService.getPort().equals(device.getPort())) {
                                      
                    return iPService;
                }
            }

        }

        return null;
    }
    
    public Boolean setMonitoringFlag(IPService iPService) {
        
        try {
            HashMap hashMap = new HashMap();
            List<String> uids = new ArrayList<String>();
            uids.add(iPService.getUid());
            hashMap.put("uids", uids);
            hashMap.put("hashcheck", null);
            hashMap.put("monitor",iPService.getMonitor());
            System.out.println("Enable port monitoring call: "+deviceService.setComponentsMonitored(hashMap));
            System.out.println(" port is monitored : "+iPService.getMonitored());
            
            return deviceService.setComponentsMonitored(hashMap);
        } catch (ApplicationException ex) {
            return false;
        }
    }
    
}

