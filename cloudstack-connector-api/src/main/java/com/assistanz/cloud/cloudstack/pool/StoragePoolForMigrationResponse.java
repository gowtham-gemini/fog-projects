package com.assistanz.cloud.cloudstack.pool;

/**
 *
 * @author Gowtham
 */
class StoragePoolForMigrationResponse {

    /**
     * the ID of the storage pool
     */
    private String id;

    /**
     * IOPS CloudStack can provision from this storage pool
     */
    private String capacityIops;

    /**
     * the ID of the cluster for the storage pool
     */
    private String clusterId;

    /**
     * the name of the cluster for the storage pool
     */
    private String clusterName;

    /**
     * the date and time the storage pool was created
     */
    private String created;

    /**
     * the host's currently allocated disk size
     */
    private String diskSizeAllocated;

    /**
     * the total disk size of the storage pool
     */
    private String diskSizeTotal;

    /**
     * the host's currently used disk size
     */
    private String diskSizeUsed;
    
    /**
     * the hypervisor type of the storage pool
     */
    private String hypervisor;

    /**
     * the IP address of the storage pool
     */
    private String ipAddress;

    /**
     * the name of the storage pool
     */
    private String name;

    /**
     * the storage pool path
     */
    private String path;

    /**
     * the Pod ID of the storage pool
     */
    private String podId;

    /**
     * the Pod name of the storage pool
     */
    private String podName;
    
    /**
     * the scope of the storage pool
     */
    private String scope;

    /**
     * the state of the storage pool
     */
    private String state;
    
    /**
     * true if this pool is suitable to migrate a volume, false otherwise
     */
    private String suitableForMigration;

    /**
     * the tags for the storage pool
     */
    private String tags;

    /**
     * the storage pool type
     */
    private String type;

    /**
     * the Zone ID of the storage pool
     */
    private String zoneId;

    /**
     * the Zone name of the storage pool
     */
    private String zoneName;

    /**
     * the ID of the latest async job acting on this object
     */
    private String jobId;

    /**
     * the current status of the latest async job acting on this object
     *
     */
    private String jobStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCapacityIops() {
        return capacityIops;
    }

    public void setCapacityIops(String capacityIops) {
        this.capacityIops = capacityIops;
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

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
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

    public String getDiskSizeUsed() {
        return diskSizeUsed;
    }

    public void setDiskSizeUsed(String diskSizeUsed) {
        this.diskSizeUsed = diskSizeUsed;
    }

    public String getHypervisor() {
        return hypervisor;
    }

    public void setHypervisor(String hypervisor) {
        this.hypervisor = hypervisor;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
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

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
