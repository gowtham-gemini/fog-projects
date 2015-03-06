package com.assistanz.cloud.cloudstack.network;

import java.util.List;

/**
 *
 * @author Santhosh
 */
public class ListNiciraNvpDeviceNetworksResponse {

    /**
     * Lists network that are using a nicira nvp device
     */
    private List<NiciraNvpDeviceNetworkResponse> niciraNvpDeviceNetworks;

    public List<NiciraNvpDeviceNetworkResponse> getNiciraNvpDeviceNetworks() {
        return niciraNvpDeviceNetworks;
    }

    public void setNiciraNvpDeviceNetworks(List<NiciraNvpDeviceNetworkResponse> niciraNvpDeviceNetworks) {
        this.niciraNvpDeviceNetworks = niciraNvpDeviceNetworks;
    }

}
