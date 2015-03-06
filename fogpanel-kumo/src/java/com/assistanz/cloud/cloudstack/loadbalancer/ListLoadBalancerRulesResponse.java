package com.assistanz.cloud.cloudstack.loadbalancer;

import java.util.List;

/**
 * 
 * @author Gowtham
 *
 */
public class ListLoadBalancerRulesResponse {
	
    
    private List<LoadBalancerRulesResponse> loadBalancerRules;

    public List<LoadBalancerRulesResponse> getLoadBalancerRules() {
        return loadBalancerRules;
    }

    public void setLoadBalancerRules(List<LoadBalancerRulesResponse> loadBalancerRules) {
        this.loadBalancerRules = loadBalancerRules;
    }

}
