package com.assistanz.cloud.cloudstack.network;

/**
 *
 * @author Gowtham
 *
 */
public class UpdateStorageNetworkIpRangeResponse {

    /**
     * the uuid of storage network IP range
     */
    private String id;

    /**
     * the end ip of the storage network IP range
     */
    private String endIp;

    /**
     * the gateway of the storage network IP range
     */
    private String gateway;

    /**
     * the netmask of the storage network IP range
     */
    private String netmask;

    /**
     * the network uuid of storage network IP range
     */
    private String networkId;

    /**
     * the Pod uuid for the storage network IP range
     */
    private String podId;

    /**
     * the start ip of the storage network IP range
     */
    private String startIp;

    /**
     * the ID or VID of the VLAN.
     */
    private String vlan;

    /**
     * the Zone uuid of the storage network IP range
     */
    private String zoneId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEndIp() {
        return endIp;
    }

    public void setEndIp(String endIp) {
        this.endIp = endIp;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
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

    public String getPodId() {
        return podId;
    }

    public void setPodId(String podId) {
        this.podId = podId;
    }

    public String getStartIp() {
        return startIp;
    }

    public void setStartIp(String startIp) {
        this.startIp = startIp;
    }

    public String getVlan() {
        return vlan;
    }

    public void setVlan(String vlan) {
        this.vlan = vlan;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

}
