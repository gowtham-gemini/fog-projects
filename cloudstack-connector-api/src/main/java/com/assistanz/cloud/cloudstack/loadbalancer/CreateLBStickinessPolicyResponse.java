package com.assistanz.cloud.cloudstack.loadbalancer;

import java.util.List;

import com.assistanz.cloud.cloudstack.StickinessPolicyResponse;

/**
 *
 * @author Santhosh
 *
 */
public class CreateLBStickinessPolicyResponse {

    /**
     * the account of the Stickiness policy
     */
    private String account;

    /**
     * the description of the Stickiness policy
     */
    private String description;

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
     * the name of the Stickiness policy
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
     * list of stickinesspolicies
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
