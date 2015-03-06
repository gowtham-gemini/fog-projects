package com.assistanz.cloud.cloudstack.network;

import java.util.List;

/**
 *
 * @author Gowtham
 *
 */
public class ListNetworksResponse {

    /**
     * the list of supported services
     */
    private List<NetworkResponse> networks;

    public List<NetworkResponse> getListNetworks() {
        return networks;
    }

    public void setListNetworks(List<NetworkResponse> networks) {
        this.networks = networks;
    }

}
