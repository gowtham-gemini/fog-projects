package com.assistanz.cloud.cloudstack.baremetal;

import java.util.List;

/**
 *
 * @author Gowtham
 *
 */
public class ListBaremetalDhcpsResponse {

    /**
     * list baremetal dhcp servers
     */
    private List<BaremetalDhcpResponse> baremetalDhcps;

    public List<BaremetalDhcpResponse> getBaremetalDhcps() {
        return baremetalDhcps;
    }

    public void setBaremetalDhcps(List<BaremetalDhcpResponse> baremetalDhcps) {
        this.baremetalDhcps = baremetalDhcps;
    }
}
