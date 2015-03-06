package com.assistanz.cloud.cloudstack.firewall;

import java.util.List;

/**
 *
 * @author Gowtham
 *
 */
public class ListEgressFirewallRulesResponse {

    /**
     * Lists all egress firewall rules for network id
     */
    private List<EgressRuleResponse> egressFirewallRules;

    public List<EgressRuleResponse> getEgressFirewallRules() {
        return egressFirewallRules;
    }

    public void setEgressFirewallRules(List<EgressRuleResponse> egressFirewallRules) {
        this.egressFirewallRules = egressFirewallRules;
    }

}
