package com.assistanz.cloud.cloudstack.firewall;

import java.util.List;

/**
 * 
 * @author Gowtham
 *
 */
public class ListPortForwardingRulesResponse {
	
    
    private List<PortForwardingRules> portForwardingRules;

    public List<PortForwardingRules> getPortForwardingRules() {
        return portForwardingRules;
    }

    public void setPortForwardingRules(List<PortForwardingRules> portForwardingRules) {
        this.portForwardingRules = portForwardingRules;
    }
	
}
