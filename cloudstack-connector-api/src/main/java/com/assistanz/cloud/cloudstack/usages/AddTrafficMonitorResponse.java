package com.assistanz.cloud.cloudstack.usages;

/**
 *
 * @author Gowtham
 *
 */
public class AddTrafficMonitorResponse {

    /**
     * the ID of the external firewall
     */
    private String id;

    /**
     * the management IP address of the external firewall
     */
    private String ipAddress;

    /**
     * the number of times to retry requests to the external firewall
     */
    private String numRetries;

    /**
     * the timeout (in seconds) for requests to the external firewall
     */
    private String timeout;

    /**
     * the zone ID of the external firewall
     */
    private String zoneId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getNumRetries() {
        return numRetries;
    }

    public void setNumRetries(String numRetries) {
        this.numRetries = numRetries;
    }

    public String getTimeout() {
        return timeout;
    }

    public void setTimeout(String timeout) {
        this.timeout = timeout;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

}
