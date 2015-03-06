package com.assistanz.cloud.cloudstack.vpc;

import java.util.List;

/**
 *
 * @author Santhosh
 */
public class ListPrivateGatewaysResponse {

    /**
     * the List private gateways
     */
    private List<PrivateGatewayResponse> privateGateways;

    public List<PrivateGatewayResponse> getPrivateGateways() {
        return privateGateways;
    }

    public void setPrivateGateways(List<PrivateGatewayResponse> privateGateways) {
        this.privateGateways = privateGateways;
    }

}
