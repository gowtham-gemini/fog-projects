package com.assistanz.cloud.cloudstack.loadbalancer;

import java.util.List;

import com.assistanz.cloud.cloudstack.HealthCheckPolicyResponse;

/**
 *
 * @author Santhosh
 *
 */
public class CreateLBHealthCheckPolicyResponse {

    /**
     * the account of the Stickiness policy
     */
    private String account;

    /**
     * the domain of the Stickiness policy
     */
    private String domain;

    /**
     * the domain ID of the Stickiness policy
     */
    private String domainId;

    /**
     * the lbrule ID of the Stickiness policy
     */
    private String lbRuleId;

    /**
     * the id of the zone the rule belongs to
     */
    private String zoneId;

    /**
     * list of health check policies
     */
    private List<HealthCheckPolicyResponse> healthCheckPolicies;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

    public String getLbRuleId() {
        return lbRuleId;
    }

    public void setLbRuleId(String lbRuleId) {
        this.lbRuleId = lbRuleId;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    public List<HealthCheckPolicyResponse> getHealthCheckPolicies() {
        return healthCheckPolicies;
    }

    public void setHealthCheckPolicies(List<HealthCheckPolicyResponse> healthCheckPolicies) {
        this.healthCheckPolicies = healthCheckPolicies;
    }

}
