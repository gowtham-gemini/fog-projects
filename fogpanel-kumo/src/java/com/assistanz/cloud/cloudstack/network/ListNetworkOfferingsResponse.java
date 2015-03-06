package com.assistanz.cloud.cloudstack.network;

import java.util.List;


/**
 * 
 * @author Gowtham
 *
 */
public class ListNetworkOfferingsResponse {
    
    /**
     * List of Network Offerings
     */
    private List<NetworkOfferingResponse> networkOfferings;

    public List<NetworkOfferingResponse> getNetworkOfferings() {
        return networkOfferings;
    }

    public void setNetworkOfferings(List<NetworkOfferingResponse> networkOfferings) {
        this.networkOfferings = networkOfferings;
    }
 
}
