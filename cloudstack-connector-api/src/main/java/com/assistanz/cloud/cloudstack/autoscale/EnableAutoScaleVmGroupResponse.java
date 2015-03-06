package com.assistanz.cloud.cloudstack.autoscale;

/**
 *
 * @author Santhosh
 *
 */
public class EnableAutoScaleVmGroupResponse {

    /**
     * the autoscale vm group ID
     */
    private String id;

    /**
     * the account owning the instance group
     */
    private String account;

    /**
     * the domain name of the vm profile
     */
    private String domain;

    /**
     * the domain id of the vm profile
     */
    private String domainId;

    /**
     * the frequency at which the conditions have to be evaluated
     */
    private String interval;

    /**
     * the load balancer rule ID
     */
    private String lbRuleId;

    /**
     * the maximum number of members in the vmgroup
     */
    private String maxMembers;

    /**
     * the minimum number of members in the vmgroup
     */
    private String minMembers;

    /**
     * the project name of the vm profile
     */
    private String project;

    /**
     * the project id of the vm profile
     */
    private String projectId;

    /**
     * the list of scaledown autoscale policies
     */
    private String scaleDownPolicies;

    /**
     * the list of scaleup autoscale policies
     */
    private String scaleUpPolicies;

    /**
     * the AutoScale Vm Group
     */
    private String state;

    /**
     * the autoscale profile that contains information about the vms in the vm group
     */
    private String vmProfileId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public String getLbRuleId() {
        return lbRuleId;
    }

    public void setLbRuleId(String lbRuleId) {
        this.lbRuleId = lbRuleId;
    }

    public String getMaxMembers() {
        return maxMembers;
    }

    public void setMaxMembers(String maxMembers) {
        this.maxMembers = maxMembers;
    }

    public String getMinMembers() {
        return minMembers;
    }

    public void setMinMembers(String minMembers) {
        this.minMembers = minMembers;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getScaleDownPolicies() {
        return scaleDownPolicies;
    }

    public void setScaleDownPolicies(String scaleDownPolicies) {
        this.scaleDownPolicies = scaleDownPolicies;
    }

    public String getScaleUpPolicies() {
        return scaleUpPolicies;
    }

    public void setScaleUpPolicies(String scaleUpPolicies) {
        this.scaleUpPolicies = scaleUpPolicies;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getVmProfileId() {
        return vmProfileId;
    }

    public void setVmProfileId(String vmProfileId) {
        this.vmProfileId = vmProfileId;
    }

}
