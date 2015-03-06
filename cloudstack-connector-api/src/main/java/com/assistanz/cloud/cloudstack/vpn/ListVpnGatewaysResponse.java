package com.assistanz.cloud.cloudstack.vpn;

import java.util.List;

/**
 *
 * @author Gowtham
 *
 */
public class ListVpnGatewaysResponse {

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
