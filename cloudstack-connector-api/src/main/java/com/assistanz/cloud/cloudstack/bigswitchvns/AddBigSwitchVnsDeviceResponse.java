package com.assistanz.cloud.cloudstack.bigswitchvns;

/**
 *
 * @author Santhosh
 *
 */
public class AddBigSwitchVnsDeviceResponse {

    /**
     * device name
     */
    private String bigSwitchDeviceName;

    /**
     * the controller Ip address
     */
    private String hostName;

    /**
     * the physical network to which this BigSwitch Vns belongs to
     */
    private String physicalNetworkId;

    /**
     * name of the provider
     */
    private String provider;

    /**
     * device id of the BigSwitch Vns
     */
    private String vnsDeviceId;

    public String getBigSwitchDeviceName() {
        return bigSwitchDeviceName;
    }

    public void setBigSwitchDeviceName(String bigSwitchDeviceName) {
        this.bigSwitchDeviceName = bigSwitchDeviceName;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getPhysicalNetworkId() {
        return physicalNetworkId;
    }

    public void setPhysicalNetworkId(String physicalNetworkId) {
        this.physicalNetworkId = physicalNetworkId;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getVnsDeviceId() {
        return vnsDeviceId;
    }

    public void setVnsDeviceId(String vnsDeviceId) {
        this.vnsDeviceId = vnsDeviceId;
    }

}
