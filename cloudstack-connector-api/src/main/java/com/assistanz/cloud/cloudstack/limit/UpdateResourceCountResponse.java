package com.assistanz.cloud.cloudstack.limit;

/**
 *
 * @author Gowtham
 *
 */
public class UpdateResourceCountResponse {

    /**
     * the account of the resource limit
     */
    private String account;

    /**
     * the domain name of the resource limit
     */
    private String domain;

    /**
     * the domain ID of the resource limit
     */
    private String domainId;

    /**
     * the project name of the resource limit
     */
    private String project;

    /**
     * the project id of the resource limit
     */
    private String projectId;

    /**
     * the resource count
     */
    private String resourceCount;

    /**
     * the resource type. Values include 0, 1, 2, 3, 4. See the resourceType parameter for more information on these
     * values.
     */
    private String resourceType;

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

    public String getResourceCount() {
        return resourceCount;
    }

    public void setResourceCount(String resourceCount) {
        this.resourceCount = resourceCount;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }
 
}
