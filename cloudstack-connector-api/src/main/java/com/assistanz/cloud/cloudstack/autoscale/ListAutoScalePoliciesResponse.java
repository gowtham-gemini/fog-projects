package com.assistanz.cloud.cloudstack.autoscale;

import java.util.List;

/**
 *
 * @author Santhosh
 *
 */
public class ListAutoScalePoliciesResponse {

    /**
     * Lists autoscale policies
     */
    private List<AutoScalePolicyResponse> autoScalePolicies;

    public List<AutoScalePolicyResponse> getAutoScalePolicies() {
        return autoScalePolicies;
    }

    public void setAutoScalePolicies(List<AutoScalePolicyResponse> autoScalePolicies) {
        this.autoScalePolicies = autoScalePolicies;
    }

}
