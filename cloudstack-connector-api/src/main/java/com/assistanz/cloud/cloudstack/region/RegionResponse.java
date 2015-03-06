package com.assistanz.cloud.cloudstack.region;

/**
 *
 * @author Santhosh
 */
class RegionResponse {

    /**
     * the ID of the region
     */
    private String id;

    /**
     * the end point of the region
     */
    private String endPoint;

    /**
     * true if GSLB service is enabled in the region, false otherwise
     */
    private String gslbServiceEnabled;

    /**
     * the name of the region
     */
    private String name;

    /**
     * true if security groups support is enabled, false otherwise
     */
    private String portableIpServiceEnabled;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getGslbServiceEnabled() {
        return gslbServiceEnabled;
    }

    public void setGslbServiceEnabled(String gslbServiceEnabled) {
        this.gslbServiceEnabled = gslbServiceEnabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPortableIpServiceEnabled() {
        return portableIpServiceEnabled;
    }

    public void setPortableIpServiceEnabled(String portableIpServiceEnabled) {
        this.portableIpServiceEnabled = portableIpServiceEnabled;
    }

}
