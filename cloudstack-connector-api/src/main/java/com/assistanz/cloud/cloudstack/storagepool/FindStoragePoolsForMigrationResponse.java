package com.assistanz.cloud.cloudstack.storagepool;

import java.util.List;

/**
 *
 * @author Santhosh
 *
 */
public class FindStoragePoolsForMigrationResponse {

    /**
     * Lists storage pools
     */
    private List<FindStoragePoolForMigrationResponse> findStoragePoolForMigrations;

    public List<FindStoragePoolForMigrationResponse> getFindStoragePoolForMigrations() {
        return findStoragePoolForMigrations;
    }

    public void setFindStoragePoolForMigrations(List<FindStoragePoolForMigrationResponse> findStoragePoolForMigrations) {
        this.findStoragePoolForMigrations = findStoragePoolForMigrations;
    }

}
