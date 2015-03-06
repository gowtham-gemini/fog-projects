package com.assistanz.cloud.cloudstack.diskoffering;

/**
 *
 * @author Gowtham
 */
public class DiskOfferingResponse {
    
    /**
     * unique ID of the disk offering
     */
    private String diskOfferingId;

    /**
     * the date this disk offering was created
     */
    private String created;

    /**
     * the size of the disk offering in GB
     */
    private String diskSize;

    /**
     * an alternate display text of the disk offering.
     */
    private String displayText;

    /**
     * the domain name this disk offering belongs to. 
     * Ignore this information as it is not currently applicable.
     */
    private String diskOfferingDomain;

    /**
     * the domain ID this disk offering belongs to. 
     * Ignore this information as it is not currently applicable.
     */
    private String diskOfferingDomainId;	

    /**
     * true if disk offering uses custom size, false otherwise
     */
    private String isCustomized;

    /**
     * the name of the disk offering
     */
    private String diskOfferingName;

    /**
     * the tags for the disk offering
     */
    private String diskOfferingTags;
    
    /**
     * the storage type for this service offering
     */
    private String storageType;
        
    String diskReadRateBPS;	
    String diskWriteRateBPS;	
    String diskReadRateIOPS;	
    String diskWriteRateIOPS;	
    
    String minIOPS;	
    String maxIOPS;	

    public String getStorageType() {
        return storageType;
    }

    public void setStorageType(String storageType) {
        this.storageType = storageType;
    }

    public String getDiskReadRateBPS() {
        return diskReadRateBPS;
    }

    public void setDiskReadRateBPS(String diskReadRateBPS) {
        this.diskReadRateBPS = diskReadRateBPS;
    }

    public String getDiskWriteRateBPS() {
        return diskWriteRateBPS;
    }

    public void setDiskWriteRateBPS(String diskWriteRateBPS) {
        this.diskWriteRateBPS = diskWriteRateBPS;
    }

    public String getDiskReadRateIOPS() {
        return diskReadRateIOPS;
    }

    public void setDiskReadRateIOPS(String diskReadRateIOPS) {
        this.diskReadRateIOPS = diskReadRateIOPS;
    }

    public String getDiskWriteRateIOPS() {
        return diskWriteRateIOPS;
    }

    public void setDiskWriteRateIOPS(String diskWriteRateIOPS) {
        this.diskWriteRateIOPS = diskWriteRateIOPS;
    }

    public String getMinIOPS() {
        return minIOPS;
    }

    public void setMinIOPS(String minIOPS) {
        this.minIOPS = minIOPS;
    }

    public String getMaxIOPS() {
        return maxIOPS;
    }

    public void setMaxIOPS(String maxIOPS) {
        this.maxIOPS = maxIOPS;
    }
    
    

    public String getDiskOfferingId() {
            return diskOfferingId;
    }

    public void setDiskOfferingId(String diskOfferingId) {
            this.diskOfferingId = diskOfferingId;
    }

    public String getCreated() {
            return created;
    }

    public void setCreated(String created) {
            this.created = created;
    }

    public String getDiskSize() {
            return diskSize;
    }

    public void setDiskSize(String diskSize) {
            this.diskSize = diskSize;
    }

    public String getDisplayText() {
            return displayText;
    }

    public void setDisplayText(String displayText) {
            this.displayText = displayText;
    }

    public String getDiskOfferingDomain() {
            return diskOfferingDomain;
    }

    public void setDiskOfferingDomain(String diskOfferingDomain) {
            this.diskOfferingDomain = diskOfferingDomain;
    }

    public String getDiskOfferingDomainId() {
            return diskOfferingDomainId;
    }

    public void setDiskOfferingDomainId(String diskOfferingDomainId) {
            this.diskOfferingDomainId = diskOfferingDomainId;
    }

    public String getIsCustomized() {
            return isCustomized;
    }

    public void setIsCustomized(String isCustomized) {
            this.isCustomized = isCustomized;
    }

    public String getDiskOfferingName() {
            return diskOfferingName;
    }

    public void setDiskOfferingName(String diskOfferingName) {
            this.diskOfferingName = diskOfferingName;
    }

    public String getDiskOfferingTags() {
            return diskOfferingTags;
    }

    public void setDiskOfferingTags(String diskOfferingTags) {
            this.diskOfferingTags = diskOfferingTags;
    }
    
}
