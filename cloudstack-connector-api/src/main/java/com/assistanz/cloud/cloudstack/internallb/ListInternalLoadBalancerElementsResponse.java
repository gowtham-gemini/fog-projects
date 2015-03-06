package com.assistanz.cloud.cloudstack.internallb;

import java.util.List;

/**
 *
 * @author Santhosh
 *
 */
public class ListInternalLoadBalancerElementsResponse {

    /**
     * Lists all available Internal Load Balancer elements
     */
    private List<InternalLoadBalancerElementResponse> internalLoadBalancerElements;

    public List<InternalLoadBalancerElementResponse> getInternalLoadBalancerElements() {
        return internalLoadBalancerElements;
    }

    public void setInternalLoadBalancerElements(List<InternalLoadBalancerElementResponse> internalLoadBalancerElements) {
        this.internalLoadBalancerElements = internalLoadBalancerElements;
    }

}
