package com.assistanz.cloud.cloudstack.internallb;

import java.util.List;

/**
 *
 * @author Santhosh
 *
 */
public class ListInternalLoadBalancerVMsResponse {

    /**
     * List internal LB VMs
     */
    private List<InternalLoadBalancerVMResponse> internalLoadBalancerVMs;

    public List<InternalLoadBalancerVMResponse> getInternalLoadBalancerVMs() {
        return internalLoadBalancerVMs;
    }

    public void setInternalLoadBalancerVMs(List<InternalLoadBalancerVMResponse> internalLoadBalancerVMs) {
        this.internalLoadBalancerVMs = internalLoadBalancerVMs;
    }

}
