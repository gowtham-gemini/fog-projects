package com.assistanz.cloud.cloudstack.router;

/**
 *
 * @author Gowtham
 *
 */
public class VirtualRouterElementResponse {

    /**
     * the id of the router
     */
    private String id;

    /**
     * the account associated with the provider
     */
    private String account;

    /**
     * the domain associated with the provider
     */
    private String domain;

    /**
     * the domain ID associated with the provider
     */
    private String domainId;

    /**
     * Enabled/Disabled the service provider
     */
    private String enabled;

    /**
     * the physical network service provider id of the provider
     */
    private String nspId;

    /**
     * the project name of the address
     */
    private String project;

    /**
     * the project id of the ipaddress
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

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public String getNspId() {
        return nspId;
    }

    public void setNspId(String nspId) {
        this.nspId = nspId;
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
