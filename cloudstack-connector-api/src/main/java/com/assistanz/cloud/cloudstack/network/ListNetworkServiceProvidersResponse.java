package com.assistanz.cloud.cloudstack.network;

import java.util.List;

/**
 *
 * @author Gowtham
 *
 */
public class ListNetworkServiceProvidersResponse {

    /**
     * Lists network service providers for a given physical network
     */
    private List<NetworkServiceProviderResponse> networkServiceProviders;

    public List<NetworkServiceProviderResponse> getNetworkServiceProviders() {
        return networkServiceProviders;
    }

    public void setNetworkServiceProviders(List<NetworkServiceProviderResponse> networkServiceProviders) {
        this.networkServiceProviders = networkServiceProviders;
    }

}
