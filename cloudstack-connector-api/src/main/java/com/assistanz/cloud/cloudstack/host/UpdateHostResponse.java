package com.assistanz.cloud.cloudstack.host;

/**
 *
 * @author Gowtham
 *
 */
public class UpdateHostResponse {

    /**
     * the ID of the host
     */
    private String id;

    /**
     * the cpu average load on the host
     */
    private String averageLoad;

    /**
     * capabilities of the host
     */
    private String capabilities;

    /**
     * the cluster ID of the host
     */
    private String clusterId;

    /**
     * the cluster name of the host
     */
    private String clusterName;

    /**
     * the cluster type of the cluster that host belongs to
     */
    private String clusterType;

    /**
     * the amount of the host's CPU currently allocated
     */
    private String cpuAllocated;

    /**
     * the CPU number of the host
     */
    private String cpuNumber;

    /**
     * the CPU speed of the host
     */
    private String cpuSpeed;

    /**
     * the amount of the host's CPU currently used
     */
    private String cpuUsed;

    /**
     * the amount of the host's CPU after applying the cpu.overprovisioning.factor
     */
    private String cpuWithOverProvisioning;

    /**
     * the date and time the host was created
     */
    private String created;

    /**
     * true if the host is disconnected. False otherwise.
     */
    private String disconnected;

    /**
     * the host's currently allocated disk size
     */
    private String diskSizeAllocated;

    /**
     * the total disk size of the host
     */
    private String diskSizeTotal;

    /**
     * events available for the host
     */
    private String events;

    /**
     * true if this host has enough CPU and RAM capacity to migrate a VM to it, false otherwise
     */
    private String hasEnoughCapacity;

    /**
     * comma-separated list of tags for the host
     */
    private String tags;

    /**
     * the host hypervisor
     */
    private String hypervisor;

    /**
     * the hypervisor version
     */
    private String hypervisorVersion;

    /**
     * the IP address of the host
     */
    private String ipAddress;

    /**
     * true if local storage is active, false otherwise
     */
    private String isLocalStorageActive;

    /**
     * the date and time the host was last pinged
     */
    private String lastPinged;

    /**
     * the management server ID of the host
     */
    private String managementServerId;

    /**
     * the amount of the host's memory currently allocated
     */
    private String memoryAllocated;

    /**
     * the memory total of the host
     */
    private String memoryTotal;

    /**
     * the amount of the host's memory currently used
     */
    private String memoryUsed;

    /**
     * the name of the host
     */
    private String name;

    /**
     * the incoming network traffic on the host
     */
    private String networKbsRead;

    /**
     * the outgoing network traffic on the host
     */
    private String networKbsWrite;

    /**
     * the OS category ID of the host
     */
    private String osCategoryId;

    /**
     * the OS category name of the host
     */
    private String osCategoryName;

    /**
     * the Pod ID of the host
     */
    private String podId;

    /**
     * the Pod name of the host
     */
    private String podName;

    /**
     * the date and time the host was removed
     */
    private String removed;

    /**
     * the resource state of the host
     */
    private String resourceState;

    /**
     * the state of the host
     */
    private String state;

    /**
     * true if this host is suitable(has enough capacity and satisfies all conditions like hosttags, max guests vm limit
     * etc) to migrate a VM to it , false otherwise
     */
    private String suitableForMigration;

    /**
     * the host type
     */
    private String type;

    /**
     * the host version
     */
    private String version;

    /**
     * the Zone ID of the host
     */
    private String zoneId;

    /**
     * the Zone name of the host
     */
    private String zoneName;

    /**
     * the ID of the latest async job acting on this object
     */
    private String jobId;

    /**
     * the current status of the latest async job acting on this object
     */
    private String jobStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAverageLoad() {
        return averageLoad;
    }

    public void setAverageLoad(String averageLoad) {
        this.averageLoad = averageLoad;
    }

    public String getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(String capabilities) {
        this.capabilities = capabilities;
    }

    public String getClusterId() {
        return clusterId;
    }

    public void setClusterId(String clusterId) {
        this.clusterId = clusterId;
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public String getClusterType() {
        return clusterType;
    }

    public void setClusterType(String clusterType) {
        this.clusterType = clusterType;
    }

    public String getCpuAllocated() {
        return cpuAllocated;
    }

    public void setCpuAllocated(String cpuAllocated) {
        this.cpuAllocated = cpuAllocated;
    }

    public String getCpuNumber() {
        return cpuNumber;
    }

    public void setCpuNumber(String cpuNumber) {
        this.cpuNumber = cpuNumber;
    }

    public String getCpuSpeed() {
        return cpuSpeed;
    }

    public void setCpuSpeed(String cpuSpeed) {
        this.cpuSpeed = cpuSpeed;
    }

    public String getCpuUsed() {
        return cpuUsed;
    }

    public void setCpuUsed(String cpuUsed) {
        this.cpuUsed = cpuUsed;
    }

    public String getCpuWithOverProvisioning() {
        return cpuWithOverProvisioning;
    }

    public void setCpuWithOverProvisioning(String cpuWithOverProvisioning) {
        this.cpuWithOverProvisioning = cpuWithOverProvisioning;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getDisconnected() {
        return disconnected;
    }

    public void setDisconnected(String disconnected) {
        this.disconnected = disconnected;
    }

    public String getDiskSizeAllocated() {
        return diskSizeAllocated;
    }

    public void setDiskSizeAllocated(String diskSizeAllocated) {
        this.diskSizeAllocated = diskSizeAllocated;
    }

    public String getDiskSizeTotal() {
        return diskSizeTotal;
    }

    public void setDiskSizeTotal(String diskSizeTotal) {
        this.diskSizeTotal = diskSizeTotal;
    }

    public String getEvents() {
        return events;
    }

    public void setEvents(String events) {
        this.events = events;
    }

    public String getHasEnoughCapacity() {
        return hasEnoughCapacity;
    }

    public void setHasEnoughCapacity(String hasEnoughCapacity) {
        this.hasEnoughCapacity = hasEnoughCapacity;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
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

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getIsLocalStorageActive() {
        return isLocalStorageActive;
    }

    public void setIsLocalStorageActive(String isLocalStorageActive) {
        this.isLocalStorageActive = isLocalStorageActive;
    }

    public String getLastPinged() {
        return lastPinged;
    }

    public void setLastPinged(String lastPinged) {
        this.lastPinged = lastPinged;
    }

    public String getManagementServerId() {
        return managementServerId;
    }

    public void setManagementServerId(String managementServerId) {
        this.managementServerId = managementServerId;
    }

    public String getMemoryAllocated() {
        return memoryAllocated;
    }

    public void setMemoryAllocated(String memoryAllocated) {
        this.memoryAllocated = memoryAllocated;
    }

    public String getMemoryTotal() {
        return memoryTotal;
    }

    public void setMemoryTotal(String memoryTotal) {
        this.memoryTotal = memoryTotal;
    }

    public String getMemoryUsed() {
        return memoryUsed;
    }

    public void setMemoryUsed(String memoryUsed) {
        this.memoryUsed = memoryUsed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNetworKbsRead() {
        return networKbsRead;
    }

    public void setNetworKbsRead(String networKbsRead) {
        this.networKbsRead = networKbsRead;
    }

    public String getNetworKbsWrite() {
        return networKbsWrite;
    }

    public void setNetworKbsWrite(String networKbsWrite) {
        this.networKbsWrite = networKbsWrite;
    }

    public String getOsCategoryId() {
        return osCategoryId;
    }

    public void setOsCategoryId(String osCategoryId) {
        this.osCategoryId = osCategoryId;
    }

    public String getOsCategoryName() {
        return osCategoryName;
    }

    public void setOsCategoryName(String osCategoryName) {
        this.osCategoryName = osCategoryName;
    }

    public String getPodId() {
        return podId;
    }

    public void setPodId(String podId) {
        this.podId = podId;
    }

    public String getPodName() {
        return podName;
    }

    public void setPodName(String podName) {
        this.podName = podName;
    }

    public String getRemoved() {
        return removed;
    }

    public void setRemoved(String removed) {
        this.removed = removed;
    }

    public String getResourceState() {
        return resourceState;
    }

    public void setResourceState(String resourceState) {
        this.resourceState = resourceState;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSuitableForMigration() {
        return suitableForMigration;
    }

    public void setSuitableForMigration(String suitableForMigration) {
        this.suitableForMigration = suitableForMigration;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
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

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

}
