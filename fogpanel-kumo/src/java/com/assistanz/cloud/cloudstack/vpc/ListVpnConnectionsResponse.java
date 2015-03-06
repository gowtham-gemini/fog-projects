/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.assistanz.cloud.cloudstack.vpc;

import java.util.List;

/**
 *
 * @author gowtham
 */
class ListVpnConnectionsResponse {
    
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
