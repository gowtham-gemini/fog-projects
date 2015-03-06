package com.assistanz.cloud.cloudstack.storagepool;

import java.util.List;

/**
 *
 * @author Santhosh
 *
 */
public class ListStorageProvidersResponse {

    /**
     * Lists storage pools
     */
    private List<StorageProviderResponse> storageProviders;

    public List<StorageProviderResponse> getStorageProviders() {
        return storageProviders;
    }

    public void setStorageProviders(List<StorageProviderResponse> storageProviders) {
        this.storageProviders = storageProviders;
    }

}
