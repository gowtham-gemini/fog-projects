package com.assistanz.cloud.cloudstack.network;

import java.util.List;

/**
 *
 * @author Santhosh
 */
public class ListNetworkIsolationMethodsResponse {

    /**
     * Lists network that are using a nicira nvp device
     */
    private List<NetworkIsolationMethodResponse> networkIsolationMethods;

    public List<NetworkIsolationMethodResponse> getNetworkIsolationMethods() {
        return networkIsolationMethods;
    }

    public void setNetworkIsolationMethods(List<NetworkIsolationMethodResponse> networkIsolationMethods) {
        this.networkIsolationMethods = networkIsolationMethods;
    }
}
