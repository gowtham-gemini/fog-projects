package com.assistanz.cloud.cloudstack.networkdevice;

import java.util.List;

/**
 *
 * @author Santhosh
 *
 */
public class ListNetworkDeviceResponse {

    /**
     * List network devices
     */
    private List<NetworkDeviceResponse> networkDevices;

    public List<NetworkDeviceResponse> getNetworkDevices() {
        return networkDevices;
    }

    public void setNetworkDevices(List<NetworkDeviceResponse> networkDevices) {
        this.networkDevices = networkDevices;
    }

}
