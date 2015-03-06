package com.assistanz.cloud.cloudstack.firewall;

import java.util.List;

/**
 * 
 * @author Gowtham
 *
 */
public class ListFirewallRulesResponse {
	
    private List<FirewallRuleResponse> firewallRules;

    public List<FirewallRuleResponse> getFirewallRules() {
        return firewallRules;
    }

    public void setFirewallRules(List<FirewallRuleResponse> firewallRules) {
        this.firewallRules = firewallRules;
    }

}
