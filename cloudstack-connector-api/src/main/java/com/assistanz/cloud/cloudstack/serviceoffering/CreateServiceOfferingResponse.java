package com.assistanz.cloud.cloudstack.serviceoffering;

public class CreateServiceOfferingResponse {

    /**
     * the id of the service offering
     */
    private String id;

    /**
     * the number of CPU needed
     */
    private String cpuNumber;

    /**
     * the clock rate of CPU speed in MHz
     */
    private String cpuSpeed;

    /**
     * the date this service offering was created
     */
    private String created;

    /**
     * is this a default system Virtual Machine offering
     */
    private String defaultUse;

    /**
     * the deploymentPlanner for the service offering
     */
    private String deploymentPlanner;

    /**
     * the bytesReadRate for the service offering
     */
    private String diskBytesReadRate;

    /**
     * the bytesWriteRate for the service offering
     */
    private String diskBytesWriteRate;

    /**
     * the iopsReadRate for the service offering
     */
    private String diskIopsReadRate;

    /**
     * the iopsWriteRate for the service offering
     */
    private String diskIopsWriteRate;

    /**
     * an alternate display text of the service offering.
     */
    private String displayText;

    /**
     * Domain name for the Service Offering
     */
    private String domain;

    /**
     * the domain id of the service offering
     */
    private String domainid;

    /**
     * the hostTags for the service offering
     */
    private String hostTags;

    /**
     * is this a system vm offering
     */
    private String isSystem;

    /**
     * the isVolatile for the service offering
     */
    private String isVolatile;

    /**
     * restrict the CPU usage to committed service offering
     */
    private String limitCpuUse;

    /**
     * the memory in MB
     */
    private String memory;

    /**
     * the name of the service offering
     */
    private String name;

    /**
     * data transfer rate in megabits per second allowed.
     */
    private String networkRate;

    /**
     * the ha support in the service offering
     */
    private String offerHa;

    /**
     * the serviceOfferingDetails for the service offering
     */
    private String serviceOfferingDetails;

    /**
     * the storage type for this service offering
     */
    private String storageType;

    /**
     * is this a the system Virtual Machine type for system vm offering
     */
    private String systemVmType;

    /**
     * the tags for the service offering
     */
    private String tags;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getDefaultUse() {
        return defaultUse;
    }

    public void setDefaultUse(String defaultUse) {
        this.defaultUse = defaultUse;
    }

    public String getDeploymentPlanner() {
        return deploymentPlanner;
    }

    public void setDeploymentPlanner(String deploymentPlanner) {
        this.deploymentPlanner = deploymentPlanner;
    }

    public String getDiskBytesReadRate() {
        return diskBytesReadRate;
    }

    public void setDiskBytesReadRate(String diskBytesReadRate) {
        this.diskBytesReadRate = diskBytesReadRate;
    }

    public String getDiskBytesWriteRate() {
        return diskBytesWriteRate;
    }

    public void setDiskBytesWriteRate(String diskBytesWriteRate) {
        this.diskBytesWriteRate = diskBytesWriteRate;
    }

    public String getDiskIopsReadRate() {
        return diskIopsReadRate;
    }

    public void setDiskIopsReadRate(String diskIopsReadRate) {
        this.diskIopsReadRate = diskIopsReadRate;
    }

    public String getDiskIopsWriteRate() {
        return diskIopsWriteRate;
    }

    public void setDiskIopsWriteRate(String diskIopsWriteRate) {
        this.diskIopsWriteRate = diskIopsWriteRate;
    }

    public String getDisplayText() {
        return displayText;
    }

    public void setDisplayText(String displayText) {
        this.displayText = displayText;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getDomainid() {
        return domainid;
    }

    public void setDomainid(String domainid) {
        this.domainid = domainid;
    }

    public String getHostTags() {
        return hostTags;
    }

    public void setHostTags(String hostTags) {
        this.hostTags = hostTags;
    }

    public String getIsSystem() {
        return isSystem;
    }

    public void setIsSystem(String isSystem) {
        this.isSystem = isSystem;
    }

    public String getIsVolatile() {
        return isVolatile;
    }

    public void setIsVolatile(String isVolatile) {
        this.isVolatile = isVolatile;
    }

    public String getLimitCpuUse() {
        return limitCpuUse;
    }

    public void setLimitCpuUse(String limitCpuUse) {
        this.limitCpuUse = limitCpuUse;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNetworkRate() {
        return networkRate;
    }

    public void setNetworkRate(String networkRate) {
        this.networkRate = networkRate;
    }

    public String getOfferHa() {
        return offerHa;
    }

    public void setOfferHa(String offerHa) {
        this.offerHa = offerHa;
    }

    public String getServiceOfferingDetails() {
        return serviceOfferingDetails;
    }

    public void setServiceOfferingDetails(String serviceOfferingDetails) {
        this.serviceOfferingDetails = serviceOfferingDetails;
    }

    public String getStorageType() {
        return storageType;
    }

    public void setStorageType(String storageType) {
        this.storageType = storageType;
    }

    public String getSystemVmType() {
        return systemVmType;
    }

    public void setSystemVmType(String systemVmType) {
        this.systemVmType = systemVmType;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

}
