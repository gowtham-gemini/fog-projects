package com.assistanz.cloud.cloudstack.network;

import java.util.List;

/**
 *
 * @author Gowtham
 *
 */
public class ListStorageNetworkIpRangeResponse {

    /**
     * List a storage network IP range
     */
    private List<StorageNetworkIpRangeResponse> storageNetworkIpRanges;

    public List<StorageNetworkIpRangeResponse> getStorageNetworkIpRanges() {
        return storageNetworkIpRanges;
    }

    public void setStorageNetworkIpRanges(List<StorageNetworkIpRangeResponse> storageNetworkIpRanges) {
        this.storageNetworkIpRanges = storageNetworkIpRanges;
    }

}
