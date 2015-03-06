package com.assistanz.cloud.cloudstack.vmgroup;

/**
 *
 * @author Gowtham
 *
 */
public class CreateInstanceGroupResponse {

    /**
     * the id of the instance group
     */
    private String id;

    /**
     * the account owning the instance group
     */
    private String account;

    /**
     * time and date the instance group was created
     */
    private String created;

    /**
     * the domain name of the instance group
     */
    private String domain;

    /**
     * the domain ID of the instance group
     */
    private String domainId;

    /**
     * the name of the instance group
     */
    private String name;

    /**
     * the project name of the group
     */
    private String project;

    /**
     * the project id of the group
     */
    private String projectId;

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

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

}
