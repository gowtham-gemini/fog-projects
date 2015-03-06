package com.assistanz.cloud.cloudstack.loadbalancer;

import java.util.List;

import com.assistanz.cloud.cloudstack.EgressRuleResponse;
import com.assistanz.cloud.cloudstack.IngressRuleResponse;
import com.assistanz.cloud.cloudstack.NetworkInterfaceCardResponse;
import com.assistanz.cloud.cloudstack.SecurityGroupResponse;

/**
 * 
 * @author Gowtham
 *
 */
public class ListLoadBalancerRuleInstancesResponse {
	
	
    private List<LoadBalancerRuleInstanceResponse> loadBalancerRuleInstances;

    public List<LoadBalancerRuleInstanceResponse> getLoadBalancerRuleInstances() {
        return loadBalancerRuleInstances;
    }

    public void setLoadBalancerRuleInstances(List<LoadBalancerRuleInstanceResponse> loadBalancerRuleInstances) {
        this.loadBalancerRuleInstances = loadBalancerRuleInstances;
    }

}
