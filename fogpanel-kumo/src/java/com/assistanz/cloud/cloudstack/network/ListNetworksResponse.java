package com.assistanz.cloud.cloudstack.network;

import java.util.List;

import com.assistanz.cloud.cloudstack.CapabilityResponse;
import com.assistanz.cloud.cloudstack.ServiceResponse;

/**
 * 
 * @author Gowtham
 *
 */
public class ListNetworksResponse {
	
    
    /**
     * List of Network Offerings
     */
    private List<NetworkResponse> networks;

    public List<NetworkResponse> getNetworks() {
        return networks;
    }

    public void setNetworks(List<NetworkResponse> networks) {
        this.networks = networks;
    }
}
