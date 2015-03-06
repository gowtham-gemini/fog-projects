package com.assistanz.cloud.cloudstack.loadbalancer;

import java.util.List;

/**
 *
 * @author Gowtham
 *
 */
public class ListLoadBalancerRuleInstancesResponse {

    /**
     * List all virtual machine instances that are assigned to a load balancer rule
     */
    private List<LoadBalancerRuleInstanceResponse> loadBalancerRuleInstances;

    public List<LoadBalancerRuleInstanceResponse> getLoadBalancerRuleInstances() {
        return loadBalancerRuleInstances;
    }

    public void setLoadBalancerRuleInstances(List<LoadBalancerRuleInstanceResponse> loadBalancerRuleInstances) {
        this.loadBalancerRuleInstances = loadBalancerRuleInstances;
    }

}
