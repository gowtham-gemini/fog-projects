package com.assistanz.cloud.cloudstack.pod;

import com.assistanz.cloud.cloudstack.CapacityResponse;
import java.util.List;

/**
 *
 * @author Gowtham
 */
class PodResponse {

    /**
     * the ID of the Pod
     */
    private String id;

    /**
     * the allocation state of the Pod
     */
    private String allocationState;

    /**
     * the ending IP for the Pod
     */
    private String endIp;

    /**
     * the gateway of the Pod
     */
    private String gateway;

    /**
     * the name of the Pod
     */
    private String name;

    /**
     * the net mask of the Pod
     */
    private String netMask;

    /**
     * the starting IP for the Pod
     */
    private String startIp;

    /**
     * the Zone ID of the Pod
     */
    private String zoneId;

    /**
     * the Zone name of the Pod
     */
    private String zoneName;

    /**
     * the capacity of the Pod
     */
    private List<CapacityResponse> capacitys;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAllocationState() {
        return allocationState;
    }

    public void setAllocationState(String allocationState) {
        this.allocationState = allocationState;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNetMask() {
        return netMask;
    }

    public void setNetMask(String netMask) {
        this.netMask = netMask;
    }

    public String getStartIp() {
        return startIp;
    }

    public void setStartIp(String startIp) {
        this.startIp = startIp;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public List<CapacityResponse> getCapacitys() {
        return capacitys;
    }

    public void setCapacitys(List<CapacityResponse> capacitys) {
        this.capacitys = capacitys;
    }

}
