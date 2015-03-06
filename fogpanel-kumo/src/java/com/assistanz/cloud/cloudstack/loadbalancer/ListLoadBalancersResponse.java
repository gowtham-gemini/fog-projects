/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.assistanz.cloud.cloudstack.loadbalancer;

import java.util.List;

/**
 *
 * @author gowtham
 */
class ListLoadBalancersResponse {
    
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
