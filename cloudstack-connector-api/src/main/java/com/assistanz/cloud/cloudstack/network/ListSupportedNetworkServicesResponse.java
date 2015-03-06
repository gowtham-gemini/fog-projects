package com.assistanz.cloud.cloudstack.network;

import java.util.List;

/**
 *
 * @author Santhosh
 *
 */
public class ListSupportedNetworkServicesResponse {

    /**
     * Lists all network services provided by CloudStack or for the given Provider
     */
    private List<NetworkServicesResponse> networkServices;

    public List<NetworkServicesResponse> getNetworkServices() {
        return networkServices;
    }

    public void setNetworkServices(List<NetworkServicesResponse> networkServices) {
        this.networkServices = networkServices;
    }

}
