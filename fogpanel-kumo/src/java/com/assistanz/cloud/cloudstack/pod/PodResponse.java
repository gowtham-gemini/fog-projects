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
    String podId;

    /**
     * the allocation state of the Pod
     */
    String podAllocationState;	

    /**
     * the ending IP for the Pod
     */
    String podEndIp;

    /**
     * 	the gateway of the Pod
     */
    String podGateway;

    /**
     * the name of the Pod
     */
    String podName;

    /**
     * the net mask of the Pod
     */
    String podNetMask;

    /**
     * the starting IP for the Pod
     */
    String podStartIp;

    /**
     * the Zone ID of the Pod
     */
    String podZoneId;

    /**
     * the Zone name of the Pod
     */
    String podZoneName;

    /**
     * the capacity of the Pod
     */
    private List<CapacityResponse> capacitys;

    public List<CapacityResponse> getCapacitys() {
        return capacitys;
    }

    public void setCapacitys(List<CapacityResponse> capacitys) {
        this.capacitys = capacitys;
    }

    public String getPodId() {
            return podId;
    }

    public void setPodId(String podId) {
            this.podId = podId;
    }

    public String getPodAllocationState() {
            return podAllocationState;
    }

    public void setPodAllocationState(String podAllocationState) {
            this.podAllocationState = podAllocationState;
    }

    public String getPodEndIp() {
            return podEndIp;
    }

    public void setPodEndIp(String podEndIp) {
            this.podEndIp = podEndIp;
    }

    public String getPodGateway() {
            return podGateway;
    }

    public void setPodGateway(String podGateway) {
            this.podGateway = podGateway;
    }

    public String getPodName() {
            return podName;
    }

    public void setPodName(String podName) {
            this.podName = podName;
    }

    public String getPodNetMask() {
            return podNetMask;
    }

    public void setPodNetMask(String podNetMask) {
            this.podNetMask = podNetMask;
    }

    public String getPodStartIp() {
            return podStartIp;
    }

    public void setPodStartIp(String podStartIp) {
            this.podStartIp = podStartIp;
    }

    public String getPodZoneId() {
            return podZoneId;
    }

    public void setPodZoneId(String podZoneId) {
            this.podZoneId = podZoneId;
    }

    public String getPodZoneName() {
            return podZoneName;
    }

    public void setPodZoneName(String podZoneName) {
            this.podZoneName = podZoneName;
    }

}
