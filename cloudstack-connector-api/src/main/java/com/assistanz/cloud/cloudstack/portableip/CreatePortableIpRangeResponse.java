package com.assistanz.cloud.cloudstack.portableip;

import java.util.List;

/**
 *
 * @author Gowtham
 *
 */
public class CreatePortableIpRangeResponse {

    /**
     * portable IP range ID
     */
    private String id;

    /**
     * the end ip of the portable IP range
     */
    private String endIp;

    /**
     * the gateway of the VLAN IP range
     */
    private String gateway;

    /**
     * the netmask of the VLAN IP range
     */
    private String netmask;

    /**
     * the Region Id in which portable ip range is provisioned
     */
    private String regionId;

    /**
     * the start ip of the portable IP range
     */
    private String startIp;

    /**
     * the ID or VID of the VLAN
     */
    private String vlan;

    /**
     * List of portable IP and association with zone/network/vpc details that are part of GSLB rule
     */
    private List<PortableIpAddressResponse> portableIpAddresses;

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

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
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

    public List<PortableIpAddressResponse> getPortableIpAddresses() {
        return portableIpAddresses;
    }

    public void setPortableIpAddresses(List<PortableIpAddressResponse> portableIpAddresses) {
        this.portableIpAddresses = portableIpAddresses;
    }

}
