/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.assistanz.cloud.cloudstack.vpn;

import java.util.List;

/**
 *
 * @author gowtham
 */
class ListVpnGatewaysResponse {
    
     /**
     * Lists site to site VPN gateways
     */
    private List<VpnGatewayResponse> vpnGateways;

    public List<VpnGatewayResponse> getVpnGateways() {
        return vpnGateways;
    }

    public void setVpnGateways(List<VpnGatewayResponse> vpnGateways) {
        this.vpnGateways = vpnGateways;
    }
}
