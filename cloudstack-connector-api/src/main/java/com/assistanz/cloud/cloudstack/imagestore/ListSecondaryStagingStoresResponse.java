package com.assistanz.cloud.cloudstack.imagestore;

import java.util.List;

/**
 *
 * @author Gowtham
 *
 */
public class ListSecondaryStagingStoresResponse {

    /**
     * Lists secondary staging stores
     */
    private List<SecondaryStagingStoreResponse> secondaryStagingStores;

    public List<SecondaryStagingStoreResponse> getSecondaryStagingStores() {
        return secondaryStagingStores;
    }

    public void setSecondaryStagingStores(List<SecondaryStagingStoreResponse> secondaryStagingStores) {
        this.secondaryStagingStores = secondaryStagingStores;
    }

}
