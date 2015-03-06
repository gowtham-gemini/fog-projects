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
    private String id;

    /**
     * the broadcast uri of the nic
     */
    private String broadcastUri;

    /**
     * the gateway of the nic
     */
    private String gateway;

    /**
     * the IPv6 address of network
     */
    private String ip6Address;

    /**
     * the cidr of IPv6 network
     */
    private String ip6Cidr;

    /**
     * the gateway of IPv6 network
     */
    private String ip6Gateway;

    /**
     * the ip address of the nic
     */
    private String ipAddress;

    /**
     * true if nic is default, false otherwise
     */
    private String isDefault;

    /**
     * the isolation uri of the nic
     */
    private String isolationUri;

    /**
     * true if nic is default, false otherwise
     */
    private String macAddress;

    /**
     * the netmask of the nic
     */
    private String netmask;

    /**
     * the ID of the corresponding network
     */
    private String networkId;

    /**
     * the name of the corresponding network
     */
    private String networkName;

    
    private String secondaryip;

    /**
     * the traffic type of the nic
     */
    private String trafficType;

    /**
     * the type of the nic
     */
    private String type;
    
    /**
     * the Secondary ipv4 addr of nic
     */
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

    public String getBroadcastUri() {
        return broadcastUri;
    }

    public void setBroadcastUri(String broadcastUri) {
        this.broadcastUri = broadcastUri;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    public String getIp6Address() {
        return ip6Address;
    }

    public void setIp6Address(String ip6Address) {
        this.ip6Address = ip6Address;
    }

    public String getIp6Cidr() {
        return ip6Cidr;
    }

    public void setIp6Cidr(String ip6Cidr) {
        this.ip6Cidr = ip6Cidr;
    }

    public String getIp6Gateway() {
        return ip6Gateway;
    }

    public void setIp6Gateway(String ip6Gateway) {
        this.ip6Gateway = ip6Gateway;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    public String getIsolationUri() {
        return isolationUri;
    }

    public void setIsolationUri(String isolationUri) {
        this.isolationUri = isolationUri;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getNetmask() {
        return netmask;
    }

    public void setNetmask(String netmask) {
        this.netmask = netmask;
    }

    public String getNetworkId() {
        return networkId;
    }

    public void setNetworkId(String networkId) {
        this.networkId = networkId;
    }

    public String getNetworkName() {
        return networkName;
    }

    public void setNetworkName(String networkName) {
        this.networkName = networkName;
    }   

    public String getTrafficType() {
        return trafficType;
    }

    public void setTrafficType(String trafficType) {
        this.trafficType = trafficType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSecondaryip() {
        return secondaryip;
    }

    public void setSecondaryip(String secondaryip) {
        this.secondaryip = secondaryip;
    }

}
