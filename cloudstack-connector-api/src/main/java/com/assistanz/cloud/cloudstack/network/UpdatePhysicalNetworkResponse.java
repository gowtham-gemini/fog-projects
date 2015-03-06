package com.assistanz.cloud.cloudstack.network;

/**
 *
 * @author Gowtham
 *
 */
public class UpdatePhysicalNetworkResponse {

    /**
     * the uuid of the physical network
     */
    private String id;

    /**
     * Broadcast domain range of the physical network
     */
    private String broadcastDomainRange;

    /**
     * the domain id of the physical network owner
     */
    private String domainId;

    /**
     * isolation methods
     */
    private String isolationMethods;

    /**
     * name of the physical network
     */
    private String name;

    /**
     * the speed of the physical network
     */
    private String networkSpeed;

    /**
     * state of the physical network
     */
    private String state;

    /**
     * comma separated tag
     */
    private String tags;

    /**
     * the vlan of the physical network
     */
    private String vlan;

    /**
     * zone id of the physical network
     */
    private String zoneId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBroadcastDomainRange() {
        return broadcastDomainRange;
    }

    public void setBroadcastDomainRange(String broadcastDomainRange) {
        this.broadcastDomainRange = broadcastDomainRange;
    }

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

    public String getIsolationMethods() {
        return isolationMethods;
    }

    public void setIsolationMethods(String isolationMethods) {
        this.isolationMethods = isolationMethods;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNetworkSpeed() {
        return networkSpeed;
    }

    public void setNetworkSpeed(String networkSpeed) {
        this.networkSpeed = networkSpeed;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
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
