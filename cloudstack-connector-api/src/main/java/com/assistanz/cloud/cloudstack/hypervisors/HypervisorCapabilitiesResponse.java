package com.assistanz.cloud.cloudstack.hypervisors;

/**
 *
 * @author Santhosh
 *
 */
public class HypervisorCapabilitiesResponse {

    /**
     * the ID of the hypervisor capabilities row
     */
    private String id;

    /**
     * the hypervisor type
     */
    private String hypervisor;

    /**
     * the hypervisor version
     */
    private String hypervisorVersion;

    /**
     * the maximum number of Data Volumes that can be attached for this hypervisor
     */
    private String maxDataVolumesLimit;

    /**
     * the maximum number of guest vms recommended for this hypervisor
     */
    private String maxGuestsLimit;

    /**
     * the maximum number of Hosts per cluster for this hypervisor
     */
    private String maxHostsPerCluster;

    /**
     * true if security group is supported
     */
    private String securityGroupEnabled;

    /**
     * true if storage motion is supported
     */
    private String storageMotionEnabled;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHypervisor() {
        return hypervisor;
    }

    public void setHypervisor(String hypervisor) {
        this.hypervisor = hypervisor;
    }

    public String getHypervisorVersion() {
        return hypervisorVersion;
    }

    public void setHypervisorVersion(String hypervisorVersion) {
        this.hypervisorVersion = hypervisorVersion;
    }

    public String getMaxDataVolumesLimit() {
        return maxDataVolumesLimit;
    }

    public void setMaxDataVolumesLimit(String maxDataVolumesLimit) {
        this.maxDataVolumesLimit = maxDataVolumesLimit;
    }

    public String getMaxGuestsLimit() {
        return maxGuestsLimit;
    }

    public void setMaxGuestsLimit(String maxGuestsLimit) {
        this.maxGuestsLimit = maxGuestsLimit;
    }

    public String getMaxHostsPerCluster() {
        return maxHostsPerCluster;
    }

    public void setMaxHostsPerCluster(String maxHostsPerCluster) {
        this.maxHostsPerCluster = maxHostsPerCluster;
    }

    public String getSecurityGroupEnabled() {
        return securityGroupEnabled;
    }

    public void setSecurityGroupEnabled(String securityGroupEnabled) {
        this.securityGroupEnabled = securityGroupEnabled;
    }

    public String getStorageMotionEnabled() {
        return storageMotionEnabled;
    }

    public void setStorageMotionEnabled(String storageMotionEnabled) {
        this.storageMotionEnabled = storageMotionEnabled;
    }

}
