package com.assistanz.cloud.cloudstack.pool;

import java.util.List;

/**
 * 
 * @author Gowtham
 *
 */
public class ListStoragePoolsResponse {
    
    /**
     * the list of StoragePools
     */
    private List<StoragePoolResponse> storagePools;

    public List<StoragePoolResponse> getStoragePools() {
        return storagePools;
    }

    public void setStoragePools(List<StoragePoolResponse> storagePools) {
        this.storagePools = storagePools;
    }
}
