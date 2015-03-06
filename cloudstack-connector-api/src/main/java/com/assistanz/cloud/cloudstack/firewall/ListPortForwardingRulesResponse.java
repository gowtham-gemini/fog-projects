package com.assistanz.cloud.cloudstack.firewall;

import java.util.List;

/**
 *
 * @author Gowtham
 *
 */
public class ListPortForwardingRulesResponse {

    /**
     * Lists all port forwarding rules for an IP address
     */
    private List<PortForwardingRulesResponse> portForwardingRules;

    public List<PortForwardingRulesResponse> getPortForwardingRules() {
        return portForwardingRules;
    }

    public void setPortForwardingRules(List<PortForwardingRulesResponse> portForwardingRules) {
        this.portForwardingRules = portForwardingRules;
    }

}
