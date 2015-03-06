package com.assistanz.cloud.cloudstack.storagepool;

import java.util.List;

/**
 *
 * @author Santhosh
 *
 */
public class ListStoragePoolsResponse {

    /**
     * Lists storage pools
     */
    private List<StoragePoolResponse> storagePools;

    public List<StoragePoolResponse> getStoragePools() {
        return storagePools;
    }

    public void setStoragePools(List<StoragePoolResponse> storagePools) {
        this.storagePools = storagePools;
    }

}
