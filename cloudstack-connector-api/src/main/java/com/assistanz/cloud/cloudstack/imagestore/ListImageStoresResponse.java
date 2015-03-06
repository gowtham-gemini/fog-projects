package com.assistanz.cloud.cloudstack.imagestore;

import java.util.List;

/**
 *
 * @author Gowtham
 *
 */
public class ListImageStoresResponse {

    /**
     * List image stores
     */
    private List<ImageStoreResponse> imageStores;

    public List<ImageStoreResponse> getImageStores() {
        return imageStores;
    }

    public void setImageStores(List<ImageStoreResponse> imageStores) {
        this.imageStores = imageStores;
    }

}
