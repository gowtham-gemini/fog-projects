package com.assistanz.cloud.cloudstack;

/**
 *
 * @author Santhosh
 */
public class TagsResponse {

    /**
     * the account associated with the tag
     */
    private String account;

    /**
     * customer associated with the tag
     */
    private String customer;

    /**
     * the domain associated with the tag
     */
    private String domain;

    /**
     * the ID of the domain associated with the tag
     */
    private String domainId;

    /**
     * tag key name
     */
    private String key;

    /**
     * the project name where tag belongs to
     */
    private String project;

    /**
     * the project id the tag belongs to
     */
    private String projectId;

    /**
     * id of the resource
     */
    private String resourceId;

    /**
     * resource type
     */
    private String resourceType;

    /**
     * tag value
     */
    private String value;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
