package com.assistanz.cloud.cloudstack.nat;

import java.util.List;

/**
 *
 * @author Gowtham
 *
 */
public class ListIpForwardingRulesResponse {

    /**
     * List the IP forwarding rules
     */
    private List<IpForwardingRuleResponse> ipForwardingRules;

    public List<IpForwardingRuleResponse> getIpForwardingRules() {
        return ipForwardingRules;
    }

    public void setIpForwardingRules(List<IpForwardingRuleResponse> ipForwardingRules) {
        this.ipForwardingRules = ipForwardingRules;
    }

}
