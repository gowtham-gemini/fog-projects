/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.assistanz.cloud.cloudstack.firewall;

import java.util.List;

/**
 *
 * @author gowtham
 */
class ListEgressFirewallRules {
    
    private List<EgressRuleResponse> egressRules;

    public List<EgressRuleResponse> getEgressRules() {
        return egressRules;
    }

    public void setEgressRules(List<EgressRuleResponse> egressRules) {
        this.egressRules = egressRules;
    }
    
}
