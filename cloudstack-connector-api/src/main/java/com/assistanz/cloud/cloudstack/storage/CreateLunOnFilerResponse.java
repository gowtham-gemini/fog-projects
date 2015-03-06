package com.assistanz.cloud.cloudstack.storage;

/**
 *
 * @author Gowtham
 *
 */
public class CreateLunOnFilerResponse {

    /**
     * the storage ip address
     */
    private String storageIpAddress;

    /**
     * ISCSI Qualified name
     */
    private String iscsiQualifiedName;

    /**
     * the storage pool path
     */
    private String storagePoolPath;

    public String getStorageIpAddress() {
        return storageIpAddress;
    }

    public void setStorageIpAddress(String storageIpAddress) {
        this.storageIpAddress = storageIpAddress;
    }

    public String getIscsiQualifiedName() {
        return iscsiQualifiedName;
    }

    public void setIscsiQualifiedName(String iscsiQualifiedName) {
        this.iscsiQualifiedName = iscsiQualifiedName;
    }

    public String getStoragePoolPath() {
        return storagePoolPath;
    }

    public void setStoragePoolPath(String storagePoolPath) {
        this.storagePoolPath = storagePoolPath;
    }

}
