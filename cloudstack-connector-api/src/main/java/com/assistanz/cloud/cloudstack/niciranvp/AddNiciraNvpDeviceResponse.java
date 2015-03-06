package com.assistanz.cloud.cloudstack.niciranvp;

/**
 *
 * @author Santhosh
 *
 */
public class AddNiciraNvpDeviceResponse {

    /**
     * the controller Ip address
     */
    private String hostName;

    /**
     * this L3 gateway service Uuid
     */
    private String l3GatewayServiceUuid;

    /**
     * device name
     */
    private String niciraDeviceName;

    /**
     * device id of the Nicire Nvp
     */
    private String nvpDeviceId;

    /**
     * the physical network to which this Nirica Nvp belongs to
     */
    private String physicalNetworkId;

    /**
     * name of the provider
     */
    private String provider;

    /**
     * the transport zone Uuid
     */
    private String transportZoneUuid;

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getL3GatewayServiceUuid() {
        return l3GatewayServiceUuid;
    }

    public void setL3GatewayServiceUuid(String l3GatewayServiceUuid) {
        this.l3GatewayServiceUuid = l3GatewayServiceUuid;
    }

    public String getNiciraDeviceName() {
        return niciraDeviceName;
    }

    public void setNiciraDeviceName(String niciraDeviceName) {
        this.niciraDeviceName = niciraDeviceName;
    }

    public String getNvpDeviceId() {
        return nvpDeviceId;
    }

    public void setNvpDeviceId(String nvpDeviceId) {
        this.nvpDeviceId = nvpDeviceId;
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

    public String getTransportZoneUuid() {
        return transportZoneUuid;
    }

    public void setTransportZoneUuid(String transportZoneUuid) {
        this.transportZoneUuid = transportZoneUuid;
    }

}
