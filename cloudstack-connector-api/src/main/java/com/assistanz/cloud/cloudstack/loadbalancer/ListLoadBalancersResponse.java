package com.assistanz.cloud.cloudstack.loadbalancer;

import java.util.List;

/**
 *
 * @author Santhosh
 *
 */
public class ListLoadBalancersResponse {

    /**
     * Lists load balancers
     */
    private List<LoadBalancersResponse> loadBalancerLists;

    public List<LoadBalancersResponse> getLoadBalancerLists() {
        return loadBalancerLists;
    }

    public void setLoadBalancerLists(List<LoadBalancersResponse> loadBalancerLists) {
        this.loadBalancerLists = loadBalancerLists;
    }

}
