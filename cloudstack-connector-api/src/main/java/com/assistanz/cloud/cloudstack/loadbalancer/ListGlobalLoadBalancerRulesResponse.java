package com.assistanz.cloud.cloudstack.loadbalancer;

import com.assistanz.cloud.cloudstack.TagsResponse;
import java.util.List;

/**
 *
 * @author Santhosh
 *
 */
public class ListGlobalLoadBalancerRulesResponse {

    /**
     * Lists load balancer rules
     */
    private List<GlobalLoadBalancerRuleResponse> globalLoadBalancerRules;

    /**
     * Lists the resource tags associated with load balancer
     */
    private List<TagsResponse> tagss;

    public List<GlobalLoadBalancerRuleResponse> getGlobalLoadBalancerRules() {
        return globalLoadBalancerRules;
    }

    public void setGlobalLoadBalancerRules(List<GlobalLoadBalancerRuleResponse> globalLoadBalancerRules) {
        this.globalLoadBalancerRules = globalLoadBalancerRules;
    }

    public List<TagsResponse> getTagss() {
        return tagss;
    }

    public void setTagss(List<TagsResponse> tagss) {
        this.tagss = tagss;
    }

}
