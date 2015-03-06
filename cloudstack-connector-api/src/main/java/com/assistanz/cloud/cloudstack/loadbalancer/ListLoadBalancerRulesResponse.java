package com.assistanz.cloud.cloudstack.loadbalancer;

import com.assistanz.cloud.cloudstack.TagsResponse;
import java.util.List;

/**
 *
 * @author Santhosh
 *
 */
public class ListLoadBalancerRulesResponse {

    /**
     * Lists the resource tags associated with load balancer
     */
    private List<TagsResponse> tagss;

    /**
     * Lists load balancer rules
     */
    private List<LoadBalancerRuleResponse> loadBalancerRules;

    public List<LoadBalancerRuleResponse> getLoadBalancerRules() {
        return loadBalancerRules;
    }

    public void setLoadBalancerRules(List<LoadBalancerRuleResponse> loadBalancerRules) {
        this.loadBalancerRules = loadBalancerRules;
    }

    public List<TagsResponse> getTagss() {
        return tagss;
    }

    public void setTagss(List<TagsResponse> tagss) {
        this.tagss = tagss;
    }

}
