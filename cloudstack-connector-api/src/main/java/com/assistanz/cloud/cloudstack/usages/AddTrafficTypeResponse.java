package com.assistanz.cloud.cloudstack.usages;

/**
 *
 * @author Gowtham
 *
 */
public class AddTrafficTypeResponse {

    /**
     * The id of the network provider
     */
    private String id;

    /**
     * The network name label of the physical device dedicated to this traffic on a KVM host
     */
    private String kvmNetworkLabel;

    /**
     * The physical network this belongs to
     */
    private String physicalNetworkId;

    /**
     * The trafficType to be added to the physical network
     */
    private String trafficType;

    /**
     * The network name label of the physical device dedicated to this traffic on a VMware host
     */
    private String vmwareNetworkLabel;

    /**
     * The network name label of the physical device dedicated to this traffic on a XenServer host
     */
    private String xenNetworkLabel;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKvmNetworkLabel() {
        return kvmNetworkLabel;
    }

    public void setKvmNetworkLabel(String kvmNetworkLabel) {
        this.kvmNetworkLabel = kvmNetworkLabel;
    }

    public String getPhysicalNetworkId() {
        return physicalNetworkId;
    }

    public void setPhysicalNetworkId(String physicalNetworkId) {
        this.physicalNetworkId = physicalNetworkId;
    }

    public String getTrafficType() {
        return trafficType;
    }

    public void setTrafficType(String trafficType) {
        this.trafficType = trafficType;
    }

    public String getVmwareNetworkLabel() {
        return vmwareNetworkLabel;
    }

    public void setVmwareNetworkLabel(String vmwareNetworkLabel) {
        this.vmwareNetworkLabel = vmwareNetworkLabel;
    }

    public String getXenNetworkLabel() {
        return xenNetworkLabel;
    }

    public void setXenNetworkLabel(String xenNetworkLabel) {
        this.xenNetworkLabel = xenNetworkLabel;
    }
 
}
