package com.assistanz.cloud.cloudstack.network;

import java.util.List;

/**
 * 
 * @author Gowtham
 *
 */
public class ListPhysicalNetworksResponse {
	
	/**
     * Lists physical networks
     */
    private List<PhysicalNetworkResponse> physicalNetworks;

    public List<PhysicalNetworkResponse> getPhysicalNetworks() {
        return physicalNetworks;
    }

    public void setPhysicalNetworks(List<PhysicalNetworkResponse> physicalNetworks) {
        this.physicalNetworks = physicalNetworks;
    }	

}
