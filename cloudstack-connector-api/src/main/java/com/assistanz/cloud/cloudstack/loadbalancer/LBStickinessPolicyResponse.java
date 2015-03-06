package com.assistanz.cloud.cloudstack.loadbalancer;

import com.assistanz.cloud.cloudstack.StickinessPolicyResponse;
import java.util.List;

/**
 *
 * @author Santhosh
 *
 */
public class LBStickinessPolicyResponse {

    /**
     * the account of the load balancer rule
     */
    private String account;

    /**
     * the description of the load balancer
     */
    private String description;

    /**
     * the domain of the load balancer rule
     */
    private String domain;

    /**
     * the domain ID of the load balancer rule
     */
    private String domainId;

    /**
     * the load balancer rule ID
     */
    private String lbRuleId;

    /**
     * the name of the load balancer
     */
    private String name;

    /**
     * the state of the rule
     */
    private String state;

    /**
     * the id of the zone the rule belongs to
     */
    private String zoneId;

    /**
     * List of stickiness policies
     */
    private List<StickinessPolicyResponse> stickinessPolicies;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    public List<StickinessPolicyResponse> getStickinessPolicies() {
        return stickinessPolicies;
    }

    public void setStickinessPolicies(List<StickinessPolicyResponse> stickinessPolicies) {
        this.stickinessPolicies = stickinessPolicies;
    }

}
