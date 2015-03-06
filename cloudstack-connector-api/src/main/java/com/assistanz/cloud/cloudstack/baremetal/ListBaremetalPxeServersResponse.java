package com.assistanz.cloud.cloudstack.baremetal;

import java.util.List;

/**
 *
 * @author Gowtham
 *
 */
public class ListBaremetalPxeServersResponse {

    /**
     * list baremetal pxe server
     */
    private List<BaremetalPxeServerResponse> baremetalPxeServers;

    public List<BaremetalPxeServerResponse> getBaremetalPxeServers() {
        return baremetalPxeServers;
    }

    public void setBaremetalPxeServers(List<BaremetalPxeServerResponse> baremetalPxeServers) {
        this.baremetalPxeServers = baremetalPxeServers;
    }
}
