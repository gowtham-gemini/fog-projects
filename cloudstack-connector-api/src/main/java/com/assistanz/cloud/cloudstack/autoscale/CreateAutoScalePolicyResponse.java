package com.assistanz.cloud.cloudstack.autoscale;

/**
 *
 * @author Santhosh
 *
 */
public class CreateAutoScalePolicyResponse {

    /**
     * the autoscale policy ID
     */
    private String id;

    /**
     * the account owning the autoscale policy
     */
    private String account;

    /**
     * the action to be executed in the specified duration
     */
    private String action;

    /**
     * the list of IDs of the conditions that are being evaluated on every interval
     */
    private String conditions;

    /**
     * the domain name of the autoscale policy
     */
    private String domain;

    /**
     * the domain id of the autoscale policy
     */
    private String domainId;

    /**
     * the duration for which the conditions have to be true before action is taken
     */
    private String duration;

    /**
     * the project name of the autoscale policy
     */
    private String project;

    /**
     * the project id of the autoscale policy
     */
    private String projectId;

    /**
     * the cool down period for the policy
     */
    private String quietTime;

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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
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

    public String getQuietTime() {
        return quietTime;
    }

    public void setQuietTime(String quietTime) {
        this.quietTime = quietTime;
    }

}
