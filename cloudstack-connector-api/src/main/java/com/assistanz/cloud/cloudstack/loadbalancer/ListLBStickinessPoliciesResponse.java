package com.assistanz.cloud.cloudstack.loadbalancer;

import com.assistanz.cloud.cloudstack.StickinessPolicyResponse;
import java.util.List;

/**
 *
 * @author Santhosh
 *
 */
public class ListLBStickinessPoliciesResponse {

    /**
     * Lists LBStickiness policies
     */
    private List<LBStickinessPolicyResponse> lbStickinessPolicies;

    /**
     * List of stickiness policies
     */
    private List<StickinessPolicyResponse> stickinessPolicies;

    public List<LBStickinessPolicyResponse> getLbStickinessPolicies() {
        return lbStickinessPolicies;
    }

    public void setLbStickinessPolicies(List<LBStickinessPolicyResponse> lbStickinessPolicies) {
        this.lbStickinessPolicies = lbStickinessPolicies;
    }

    public List<StickinessPolicyResponse> getStickinessPolicies() {
        return stickinessPolicies;
    }

    public void setStickinessPolicies(List<StickinessPolicyResponse> stickinessPolicies) {
        this.stickinessPolicies = stickinessPolicies;
    }

}
