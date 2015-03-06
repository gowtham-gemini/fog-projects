package com.assistanz.cloud.cloudstack.diskoffering;

/**
 *
 * @author Gowtham
 *
 */
public class CreateDiskOfferingResponse {

    /**
     * unique ID of the disk offering
     */
    private String id;

    /**
     * the date this disk offering was created
     */
    private String created;

    /**
     * the bytes read rate of the disk offering
     */
    private String diskBytesReadRate;

    /**
     * the bytes write rate of the disk offering
     */
    private String diskBytesWriteRate;

    /**
     * io requests read rate of the disk offering
     */
    private String diskIopsReadRate;

    /**
     * io requests write rate of the disk offering
     */
    private String diskIopsWriteRate;

    /**
     * the size of the disk offering in GB
     */
    private String diskSize;

    /**
     * whether to display the offering to the end user or not
     */
    private String displayOffering;

    /**
     * an alternate display text of the disk offering.
     */
    private String displayText;

    /**
     * the domain name this disk offering belongs to. Ignore this information as it is not currently applicable.
     */
    private String domain;

    /**
     * the domain ID this disk offering belongs to. Ignore this information as it is not currently applicable.
     */
    private String domainId;

    /**
     * true if disk offering uses custom size, false otherwise
     */
    private String isCustomized;

    /**
     * true if disk offering uses custom iops, false otherwise
     */
    private String isCustomizedIops;

    /**
     * the max iops of the disk offering
     */
    private String maxIops;

    /**
     * the min iops of the disk offering
     */
    private String minIops;

    /**
     * the name of the disk offering
     */
    private String name;

    /**
     * the storage type for this disk offering
     */
    private String storageType;

    /**
     * the tags for the disk offering
     */
    private String tags;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
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

    public String getDiskSize() {
        return diskSize;
    }

    public void setDiskSize(String diskSize) {
        this.diskSize = diskSize;
    }

    public String getDisplayOffering() {
        return displayOffering;
    }

    public void setDisplayOffering(String displayOffering) {
        this.displayOffering = displayOffering;
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

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

    public String getIsCustomized() {
        return isCustomized;
    }

    public void setIsCustomized(String isCustomized) {
        this.isCustomized = isCustomized;
    }

    public String getIsCustomizedIops() {
        return isCustomizedIops;
    }

    public void setIsCustomizedIops(String isCustomizedIops) {
        this.isCustomizedIops = isCustomizedIops;
    }

    public String getMaxIops() {
        return maxIops;
    }

    public void setMaxIops(String maxIops) {
        this.maxIops = maxIops;
    }

    public String getMinIops() {
        return minIops;
    }

    public void setMinIops(String minIops) {
        this.minIops = minIops;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStorageType() {
        return storageType;
    }

    public void setStorageType(String storageType) {
        this.storageType = storageType;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

}
