package com.assistanz.cloud.cloudstack.volume;

/**
 *
 * @author Gowtham
 *
 */
public class ExtractVolumeResponse {

    /**
     * the id of extracted object
     */
    private String id;

    /**
     * the account id to which the extracted object belongs
     */
    private String accountId;

    /**
     * the time and date the object was created
     */
    private String created;

    /**
     * the upload id of extracted object
     */
    private String extractId;

    /**
     * the mode of extraction - upload or download
     */
    private String extractMode;

    /**
     * the name of the extracted object
     */
    private String name;

    /**
     * the state of the extracted object
     */
    private String state;
    /**
     * the status of the extraction
     */
    private String status;

    /**
     * type of the storage
     */
    private String storageType;

    /**
     * the percentage of the entity uploaded to the specified location
     */
    private String uploadPercentage;
    /**
     * if mode = upload then url of the uploaded entity if mode = download the url from which the entity can be
     * downloaded
     */
    private String url;

    /**
     * zone ID the object was extracted from
     */
    private String zoneId;

    /**
     * zone name the object was extracted from
     */
    private String zoneName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getExtractId() {
        return extractId;
    }

    public void setExtractId(String extractId) {
        this.extractId = extractId;
    }

    public String getExtractMode() {
        return extractMode;
    }

    public void setExtractMode(String extractMode) {
        this.extractMode = extractMode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStorageType() {
        return storageType;
    }

    public void setStorageType(String storageType) {
        this.storageType = storageType;
    }

    public String getUploadPercentage() {
        return uploadPercentage;
    }

    public void setUploadPercentage(String uploadPercentage) {
        this.uploadPercentage = uploadPercentage;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

}
