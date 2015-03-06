package com.assistanz.cloud.cloudstack.bigswitchvns;

import java.util.List;

/**
 *
 * @author Santhosh
 *
 */
public class ListBigSwitchVnsDevicesResponse {

    /**
     * Lists BigSwitch Vns devices
     */
    private List<BigSwitchVnsDeviceResponse> bigSwitchVnsDevices;

    public List<BigSwitchVnsDeviceResponse> getBigSwitchVnsDevices() {
        return bigSwitchVnsDevices;
    }

    public void setBigSwitchVnsDevices(List<BigSwitchVnsDeviceResponse> bigSwitchVnsDevices) {
        this.bigSwitchVnsDevices = bigSwitchVnsDevices;
    }

}
