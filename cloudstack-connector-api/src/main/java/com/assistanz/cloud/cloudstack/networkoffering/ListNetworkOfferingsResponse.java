package com.assistanz.cloud.cloudstack.networkoffering;

import java.util.List;

/**
 *
 * @author Gowtham
 *
 */
public class ListNetworkOfferingsResponse {

    /**
     * Lists all available network offerings
     */
    private List<NetworkOfferingResponse> networkOfferings;

    public List<NetworkOfferingResponse> getNetworkOfferings() {
        return networkOfferings;
    }

    public void setNetworkOfferings(List<NetworkOfferingResponse> networkOfferings) {
        this.networkOfferings = networkOfferings;
    }

}
