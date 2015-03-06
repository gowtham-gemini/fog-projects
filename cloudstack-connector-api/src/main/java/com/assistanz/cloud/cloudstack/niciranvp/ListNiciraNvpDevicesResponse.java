package com.assistanz.cloud.cloudstack.niciranvp;

import java.util.List;

/**
 *
 * @author Santhosh
 *
 */
public class ListNiciraNvpDevicesResponse {

    /**
     * Lists Nicira NVP devices
     */
    private List<NiciraNvpDeviceResponse> niciraNvpDevices;

    public List<NiciraNvpDeviceResponse> getNiciraNvpDevices() {
        return niciraNvpDevices;
    }

    public void setNiciraNvpDevices(List<NiciraNvpDeviceResponse> niciraNvpDevices) {
        this.niciraNvpDevices = niciraNvpDevices;
    }
}
