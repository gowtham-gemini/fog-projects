package com.assistanz.cloud.cloudstack.vpn;

import java.util.List;

/**
 *
 * @author Gowtham
 *
 */
public class ListVpnConnectionsResponse {

    /**
     * Lists site to site VPN connection gateways
     */
    private List<VpnConnectionResponse> vpnConnections;

    public List<VpnConnectionResponse> getVpnConnections() {
        return vpnConnections;
    }

    public void setVpnConnections(List<VpnConnectionResponse> vpnConnections) {
        this.vpnConnections = vpnConnections;
    }

}
