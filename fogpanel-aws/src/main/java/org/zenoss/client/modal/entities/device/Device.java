package org.zenoss.client.modal.entities.device;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Device {

    private  String deviceName;
    private String deviceClass;
    private String title;
    private String snmpCommunity;
    private Integer snmpPort = 161;
    private String manageIp;
    private String locationPath;
    private List<String> systemPaths; 
    private List<String> groupPaths;
    private Boolean model = false;
    private String collector = "localhost";
    private String rackSlot;
    private Integer productionState = 1000;
    private String comments;
    private  String hwManufacturer;
    private String hwProductName;
    private String osManufacturer;
    private String osProductName;
    private Integer priority; 
    private String uid;
    private String osType;
    private String port;
    

    
    public Device(Builder builder) {
        
        this.deviceName = builder.deviceName;
        this.deviceClass = builder.deviceClass;
        this.title = builder.title;
        this.snmpCommunity = builder.snmpCommunity;
        this.manageIp = builder.manageIp;
        this.locationPath = builder.locationPath;
        this.systemPaths = builder.systemPaths;
        this.groupPaths = builder.groupPaths;
        this.rackSlot = builder.rackSlot;
        this.comments = builder.comments;
        this.hwManufacturer = builder.hwManufacturer;
        this.hwProductName = builder.hwProductName;
        this.osManufacturer = builder.osManufacturer;
        this.osProductName = builder.osProductName;
        
        this.snmpPort = builder.snmpPort;
        this.model = builder.model;
        this.collector = builder.collector;
        this.productionState = builder.productionState;
        this.priority = builder.priority;
        this.uid = builder.uid;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
    
    
    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceClass() {
        return deviceClass;
    }

    public void setDeviceClass(String deviceClass) {
        this.deviceClass = deviceClass;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSnmpCommunity() {
        return snmpCommunity;
    }

    public void setSnmpCommunity(String snmpCommunity) {
        this.snmpCommunity = snmpCommunity;
    }

    public Integer getSnmpPort() {
        return snmpPort;
    }

    public void setSnmpPort(Integer snmpPort) {
        this.snmpPort = snmpPort;
    }

    public String getManageIp() {
        return manageIp;
    }

    public void setManageIp(String manageIp) {
        this.manageIp = manageIp;
    }

    public String getLocationPath() {
        return locationPath;
    }

    public void setLocationPath(String locationPath) {
        this.locationPath = locationPath;
    }

    public List<String> getSystemPaths() {
        return systemPaths;
    }

    public void setSystemPaths(List<String> systemPaths) {
        this.systemPaths = systemPaths;
    }

    public List<String> getGroupPaths() {
        return groupPaths;
    }

    public void setGroupPaths(List<String> groupPaths) {
        this.groupPaths = groupPaths;
    }

    public Boolean isModel() {
        return model;
    }

    public void setModel(Boolean model) {
        this.model = model;
    }

    public String getCollector() {
        return collector;
    }

    public void setCollector(String collector) {
        this.collector = collector;
    }

    public String getRackSlot() {
        return rackSlot;
    }

    public void setRackSlot(String rackSlot) {
        this.rackSlot = rackSlot;
    }

    public Integer getProductionState() {
        return productionState;
    }

    public void setProductionState(Integer productionState) {
        this.productionState = productionState;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getHwManufacturer() {
        return hwManufacturer;
    }

    public void setHwManufacturer(String hwManufacturer) {
        this.hwManufacturer = hwManufacturer;
    }

    public String getHwProductName() {
        return hwProductName;
    }

    public void setHwProductName(String hwProductName) {
        this.hwProductName = hwProductName;
    }

    public String getOsManufacturer() {
        return osManufacturer;
    }

    public void setOsManufacturer(String osManufacturer) {
        this.osManufacturer = osManufacturer;
    }

    public String getOsProductName() {
        return osProductName;
    }

    public void setOsProductName(String osProductName) {
        this.osProductName = osProductName;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getOsType() {
        return osType;
    }

    public void setOsType(String osType) {
        this.osType = osType;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
    
    
    
    public static class Builder {

        private String deviceName;
        private String deviceClass;
        private String title;
        private String snmpCommunity;
        private Integer snmpPort = 161;
        private String manageIp;
        private String locationPath;
        private List<String> systemPaths;
        private List<String> groupPaths;
        private Boolean model = false;
        private String collector = "localhost";
        private String rackSlot;
        private Integer productionState = 1000;
        private String comments;
        private String hwManufacturer;
        private String hwProductName;
        private String osManufacturer;
        private String osProductName;
        private Integer priority;
        private String uid; 

        public Builder(String deviceName, String title, String snmpCommunity) {
            this.deviceName = deviceName;
            this.title = title;
            this.snmpCommunity = snmpCommunity;
        }
        
        public Builder(String uid) {
            this.uid = uid;
        }
        
        public Builder deviceClass(String deviceClass) {
            this.deviceClass = deviceClass;
            return this;
        }
        
        public Builder uid(String uid) {
            this.uid = uid;
            return this;
        }

        public Builder snmpPort(Integer snmpPort) {
            this.snmpPort = snmpPort;
            return this;
        }
        
        public Builder manageIp(String manageIp) {
            this.manageIp = manageIp;
            return this;
        }
        
        public Builder locationPath(String locationPath) {
            this.locationPath = locationPath;
            return this;
        }
        
        public Builder systemPaths(List<String> systemPaths) {
            this.systemPaths = systemPaths;
            return this;
        }
        
        public Builder groupPaths(List<String> groupPaths) {
            this.systemPaths = groupPaths;
            return this;
        }
        
        public Builder model(Boolean model) {
            this.model = model;
            return this;
        }
        
        public Builder collector(String collector) {
            this.collector = collector;
            return this;
        }
        
        public Builder rackSlot(String rackSlot) {
            this.rackSlot = rackSlot;
            return this;
        }
        
        public Builder productionState(Integer productionState) {
            this.productionState = productionState;
            return this;
        }
        
        public Builder comments(String comments) {
            this.comments = comments;
            return this;
        }
        
        public Builder hwManufacturer(String hwManufacturer) {
            this.hwManufacturer = hwManufacturer;
            return this;
        }
        
        public Builder hwProductName(String hwProductName) {
            this.hwProductName = hwProductName;
            return this;
        }
        
        public Builder osManufacturer(String osManufacturer) {
            this.osManufacturer = osManufacturer;
            return this;
        }
        
        public Builder osProductName(String osProductName) {
            this.osProductName = osProductName;
            return this;
        }
        
        public Builder priority(Integer priority) {
            this.priority = priority;
            return this;
        }
        
        public Device build() {
            return new Device(this);
        }
    }
    
    public Map toHashMap() {
        
        Map map = new HashMap();
        
        map.put("deviceName", this.deviceName);
        map.put("deviceClass", this.deviceClass);
        map.put("title", this.title);
        map.put("snmpCommunity", this.snmpCommunity);
//        map.put("manageIp", this.manageIp);
//        map.put("locationPath", this.locationPath);
//        map.put("systemPaths", this.systemPaths);
//        map.put("groupPaths", this.groupPaths);
//        map.put("rackSlot", this.rackSlot);
//        map.put("comments", this.comments);
//        map.put("hwManufacturer", this.hwManufacturer);
//        map.put("hwProductName", this.hwProductName);
//        map.put("osManufacturer", this.osManufacturer);
//        map.put("osProductName", this.osProductName);
        
        map.put("snmpPort", this.snmpPort);
        map.put("model", this.model);
        map.put("collector", this.collector);
        map.put("productionState", this.productionState);
        map.put("priority", this.priority);

        return map;
    }
    
    

}
