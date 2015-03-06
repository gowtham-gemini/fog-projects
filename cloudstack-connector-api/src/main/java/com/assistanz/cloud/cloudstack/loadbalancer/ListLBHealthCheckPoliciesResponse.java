package com.assistanz.cloud.cloudstack.loadbalancer;

import com.assistanz.cloud.cloudstack.HealthCheckPolicyResponse;
import java.util.List;

/**
 *
 * @author Santhosh
 *
 */
public class ListLBHealthCheckPoliciesResponse {

    /**
     * Lists LBStickiness policies
     */
    private List<LBHealthCheckPolicyResponse> lbHealthCheckPolicies;

    /**
     * List of stickiness policies
     */
    private List<HealthCheckPolicyResponse> healthCheckPolicies;

    public List<LBHealthCheckPolicyResponse> getLbHealthCheckPolicies() {
        return lbHealthCheckPolicies;
    }

    public void setLbHealthCheckPolicies(List<LBHealthCheckPolicyResponse> lbHealthCheckPolicies) {
        this.lbHealthCheckPolicies = lbHealthCheckPolicies;
    }

    public List<HealthCheckPolicyResponse> getHealthCheckPolicies() {
        return healthCheckPolicies;
    }

    public void setHealthCheckPolicies(List<HealthCheckPolicyResponse> healthCheckPolicies) {
        this.healthCheckPolicies = healthCheckPolicies;
    }

}
