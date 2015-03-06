package com.assistanz.cloud.cloudstack.storagepool;

/**
 *
 * @author Santhosh
 *
 */
public class StorageProviderResponse {

    /**
     * the name of the storage provider
     */
    private String name;

    /**
     * the type of the storage provider
     */
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
