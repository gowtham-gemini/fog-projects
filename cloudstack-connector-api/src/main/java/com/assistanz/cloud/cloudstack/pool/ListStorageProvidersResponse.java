package com.assistanz.cloud.cloudstack.pool;

import java.util.List;

/**
 *
 * @author Gowtham
 *
 */
public class ListStorageProvidersResponse {

    /**
     * the list of StoragePools
     */
    private List<StorageProviderResponse> storageProviders;

    public List<StorageProviderResponse> getStorageProviders() {
        return storageProviders;
    }

    public void setStorageProviders(List<StorageProviderResponse> storageProviders) {
        this.storageProviders = storageProviders;
    }
}
