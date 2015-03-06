package com.assistanz.cloud.cloudstack.pool;

import java.util.List;

/**
 *
 * @author Gowtham
 *
 */
public class FindStoragePoolsForMigrationResponse {

    /**
     * the list of StoragePools
     */
    private List<StoragePoolForMigrationResponse> storagePools;

    public List<StoragePoolForMigrationResponse> getStoragePools() {
        return storagePools;
    }

    public void setStoragePools(List<StoragePoolForMigrationResponse> storagePools) {
        this.storagePools = storagePools;
    }
}
