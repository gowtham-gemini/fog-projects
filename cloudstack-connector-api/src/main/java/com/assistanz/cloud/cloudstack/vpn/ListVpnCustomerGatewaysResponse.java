package com.assistanz.cloud.cloudstack.vpn;

import java.util.List;

/**
 *
 * @author Gowtham
 *
 */
public class ListVpnCustomerGatewaysResponse {

    /**
     * list of tags associated with the virtual machine
     */
    private List<VpnCustomerGatewayResponse> vpnCustomerGateways;

    public List<VpnCustomerGatewayResponse> getVpnCustomerGateways() {
        return vpnCustomerGateways;
    }

    public void setVpnCustomerGateways(List<VpnCustomerGatewayResponse> vpnCustomerGateways) {
        this.vpnCustomerGateways = vpnCustomerGateways;
    }

}
