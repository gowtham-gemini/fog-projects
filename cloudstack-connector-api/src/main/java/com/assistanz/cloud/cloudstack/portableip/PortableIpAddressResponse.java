package com.assistanz.cloud.cloudstack.portableip;

/**
 *
 * @author Santhosh
 *
 */
public class PortableIpAddressResponse {

    /**
     * the account ID the portable IP address is associated with
     */
    private String accountId;

    /**
     * date the portal IP address was acquired
     */
    private String allocated;

    /**
     * the domain ID the portable IP address is associated with
     */
    private String domainId;

    /**
     * public IP address
     */
    private String ipAddress;

    /**
     * the ID of the Network where ip belongs to
     */
    private String networkId;

    /**
     * the physical network this belongs to
     */
    private String physicalNetworkId;

    /**
     * Region Id in which global load balancer is created
     */
    private String regionId;

    /**
     * State of the ip address. Can be: Allocatin, Allocated and Releasing
     */
    private String state;

    /**
     * VPC the ip belongs to
     */
    private String vpcId;

    /**
     * the ID of the zone the public IP address belongs to
     */
    private String zoneId;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAllocated() {
        return allocated;
    }

    public void setAllocated(String allocated) {
        this.allocated = allocated;
    }

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getNetworkId() {
        return networkId;
    }

    public void setNetworkId(String networkId) {
        this.networkId = networkId;
    }

    public String getPhysicalNetworkId() {
        return physicalNetworkId;
    }

    public void setPhysicalNetworkId(String physicalNetworkId) {
        this.physicalNetworkId = physicalNetworkId;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getVpcId() {
        return vpcId;
    }

    public void setVpcId(String vpcId) {
        this.vpcId = vpcId;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

}
