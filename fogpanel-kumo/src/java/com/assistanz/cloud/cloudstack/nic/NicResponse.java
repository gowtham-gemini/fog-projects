package com.assistanz.cloud.cloudstack.nic;

import java.util.List;

/**
 *
 * @author gowtham
 */
class NicResponse {
    
    /**
     * the ID of the nic
     */
    String id;	
            
    /**
     * the broadcast uri of the nic
     */
    String broadcasturi;
    
    /**
     * the gateway of the nic
     */
    String gateway;	
            
    /**
     * the IPv6 address of network
     */
    String ip6address;	
            
    /**
     * the cidr of IPv6 network
     */
    String ip6cidr;	
            
    /**
     * the gateway of IPv6 network
     */
    String ip6gateway;	
            
    /**
     * the ip address of the nic
     */
    String ipaddress;	
            
    /**
     * true if nic is default, false otherwise
     */
    String isdefault;
            
    /**
     * the isolation uri of the nic
     */
    String isolationuri;
           
    /**
     * true if nic is default, false otherwise
     */
    String macaddress;	
            
    /**
     * the netmask of the nic
     */
    String netmask;	
            
            
    /**
     * the ID of the corresponding network
     */
    String networkid;	
            
    /**
     * the name of the corresponding network
     */
    String networkname;	
            
    /**
     * the Secondary ipv4 addr of nic
     */
    String secondaryip;	
            
            
    /**
     * the traffic type of the nic
     */
    String traffictype;	
            
    /**
     * the type of the nic
     */
    String type;
    
        
    private List<NicSecondaryIPResponse> secondaryIp;	

    public List<NicSecondaryIPResponse> getSecondaryIp() {
        return secondaryIp;
    }

    public void setSecondaryIp(List<NicSecondaryIPResponse> secondaryIp) {
        this.secondaryIp = secondaryIp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBroadcasturi() {
        return broadcasturi;
    }

    public void setBroadcasturi(String broadcasturi) {
        this.broadcasturi = broadcasturi;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    public String getIp6address() {
        return ip6address;
    }

    public void setIp6address(String ip6address) {
        this.ip6address = ip6address;
    }

    public String getIp6cidr() {
        return ip6cidr;
    }

    public void setIp6cidr(String ip6cidr) {
        this.ip6cidr = ip6cidr;
    }

    public String getIp6gateway() {
        return ip6gateway;
    }

    public void setIp6gateway(String ip6gateway) {
        this.ip6gateway = ip6gateway;
    }

    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

    public String getIsdefault() {
        return isdefault;
    }

    public void setIsdefault(String isdefault) {
        this.isdefault = isdefault;
    }

    public String getIsolationuri() {
        return isolationuri;
    }

    public void setIsolationuri(String isolationuri) {
        this.isolationuri = isolationuri;
    }

    public String getMacaddress() {
        return macaddress;
    }

    public void setMacaddress(String macaddress) {
        this.macaddress = macaddress;
    }

    public String getNetmask() {
        return netmask;
    }

    public void setNetmask(String netmask) {
        this.netmask = netmask;
    }

    public String getNetworkid() {
        return networkid;
    }

    public void setNetworkid(String networkid) {
        this.networkid = networkid;
    }

    public String getNetworkname() {
        return networkname;
    }

    public void setNetworkname(String networkname) {
        this.networkname = networkname;
    }

    public String getSecondaryip() {
        return secondaryip;
    }

    public void setSecondaryip(String secondaryip) {
        this.secondaryip = secondaryip;
    }

    public String getTraffictype() {
        return traffictype;
    }

    public void setTraffictype(String traffictype) {
        this.traffictype = traffictype;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
