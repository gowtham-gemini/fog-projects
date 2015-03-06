package com.assistanz.cloud.cloudstack.baremetal;

/**
 *
 * @author Santhosh
 *
 */
public class AddBaremetalDhcpResponse {

    /**
     * device id of
     */
    private String id;

    /**
     * name of the provider
     */
    private String dhcpServerType;

    /**
     * the physical network to which this external dhcp device belongs to
     */
    private String physicalNetworkId;

    /**
     * name of the provider
     */
    private String provider;

    /**
     * url of baremetal dhcp server
     */
    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDhcpServerType() {
        return dhcpServerType;
    }

    public void setDhcpServerType(String dhcpServerType) {
        this.dhcpServerType = dhcpServerType;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
