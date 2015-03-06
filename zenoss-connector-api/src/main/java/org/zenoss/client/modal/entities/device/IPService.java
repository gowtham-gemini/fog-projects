package org.zenoss.client.modal.entities.device;

import java.util.List;
import org.json.simple.JSONObject;


public class IPService {
    
    private String description;
    private List<String> ipAddresses;    
    private Boolean monitor;
    private String monitored;
    private String name;
    private String port;
    private String protocol;
    private String serviceClassUid;
    private String severity;
    private String status;
    private String uid;
    private Boolean usesMonitorAttribute;
    private String uuid;        
    
    private IPService(Builder builder) {
        
        this.description = builder.description;
        this.ipAddresses = builder.ipAddresses;
        this.monitor = builder.monitor;
        this.monitored = builder.monitored;
        this.name = builder.name;
        this.port = builder.port;
        this.protocol = builder.protocol;
        this.serviceClassUid = builder.serviceClassUid;
        this.severity = builder.severity;
        this.status = builder.status;
        this.uid = builder.uid;
        this.usesMonitorAttribute = builder.usesMonitorAttribute;
        this.uuid = builder.uuid;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getIpAddresses() {
        return ipAddresses;
    }

    public void setIpAddresses(List<String> ipAddresses) {
        this.ipAddresses = ipAddresses;
    }

    public Boolean getMonitor() {
        return monitor;
    }

    public void setMonitor(Boolean monitor) {
        this.monitor = monitor;
    }

    public String getMonitored() {
        return monitored;
    }

    public void setMonitored(String monitored) {
        this.monitored = monitored;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getServiceClassUid() {
        return serviceClassUid;
    }

    public void setServiceClassUid(String serviceClassUid) {
        this.serviceClassUid = serviceClassUid;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Boolean getUsesMonitorAttribute() {
        return usesMonitorAttribute;
    }

    public void setUsesMonitorAttribute(Boolean usesMonitorAttribute) {
        this.usesMonitorAttribute = usesMonitorAttribute;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }    
    
    public static class Builder {
        
        private String description;
        private List<String> ipAddresses;    
        private Boolean monitor;
        private String monitored;
        private String name;
        private String port;
        private String protocol;
        private String serviceClassUid;
        private String severity;
        private String status;
        private String uid;
        private Boolean usesMonitorAttribute;
        private String uuid;    
        
        public Builder(String uid) {            
            this.uid = uid;
        }
        
        public Builder description(String description) {
            this.description = description;
            return this;
        }   
         
        public Builder ipAddresses(List<String> ipAddresses) {
            this.ipAddresses = ipAddresses;
            return this;
        }
        
        public Builder monitor(Boolean monitor) {
            this.monitor = monitor;
            return this;
        }
        
        public Builder monitored(String monitored) {
            this.monitored = monitored;
            return this;
        }
        public Builder name(String name) {
            this.name = name;
            return this;
        }
        public Builder port(String port) {
            this.port = port;
            return this;
        }
        public Builder protocol(String protocol) {
            this.protocol = protocol;
            return this;
        }
        public Builder serviceClassUid(String serviceClassUid) {
            this.serviceClassUid = serviceClassUid;
            return this;
        }
        public Builder severity(String severity) {
            this.severity = severity;
            return this;
        }
        public Builder status(String status) {
            this.status = status;
            return this;
        }
        public Builder uid(String uid) {
            this.uid = uid;
            return this;
        }
        public Builder usesMonitorAttribute(Boolean usesMonitorAttribute) {
            this.usesMonitorAttribute = usesMonitorAttribute;
            return this;
        }
        
        public Builder uuid(String uuid) {
            this.uuid = uuid;
            return this;
        }
        
        public IPService build() {
            return new IPService(this);
        }
    }
    
    /**
     * ...
     * 
     * @param jsonObject
     * @return 
     */
    public static IPService convert(JSONObject jsonObject) {
        
        String description = (String) jsonObject.get("description");        
        List<String> ipAddresses = (List<String>) jsonObject.get("ipaddresses");            
        Boolean monitor = (Boolean) jsonObject.get("monitor");
        String monitored = "";
        String port = "";
        
        if(jsonObject.get("monitored") != null) {
            monitored = jsonObject.get("monitored").toString();
        }
        if(jsonObject.get("port") != null) {
            port = jsonObject.get("port").toString();
        }
        String name = (String) jsonObject.get("name");         
        String protocol = (String) jsonObject.get("protocol");
        String serviceClassUid = (String) jsonObject.get("serviceClassUid"); 
        String status = (String) jsonObject.get("status");
        String uid = (String) jsonObject.get("uid");
        Boolean usesMonitorAttribute = (Boolean) jsonObject.get("usesMonitorAttribute");
        String uuid = (String) jsonObject.get("uuid");    
        String severity = "";
        // type cast long to string
        if(jsonObject.get("severity") != null ) {
            severity = jsonObject.get("severity").toString();
        }  
        
        return new Builder(uid).description(description).ipAddresses(ipAddresses).monitor(monitor).monitored(monitored).
                name(name).port(port).protocol(protocol).
                serviceClassUid(serviceClassUid).
                status(status).               
                usesMonitorAttribute(usesMonitorAttribute).
                uid(uid).
                uuid(uuid).
                severity(severity).
                build();
    }    
}
