package com.assistanz.cloud.cloudstack.vpn;

/**
 *
 * @author Santhosh
 *
 */
public class VpnUserResponse {

    /**
     * the vpn userID
     */
    private String id;

    /**
     * the account of the remote access vpn
     */
    private String account;

    /**
     * the domain name of the account of the remote access vpn
     */
    private String domain;

    /**
     * the domain id of the account of the remote access vpn
     */
    private String domainId;

    /**
     * the project name of the vpn
     */
    private String project;

    /**
     * the project id of the vpn
     */
    private String projectId;

    /**
     * the state of the Vpn User
     */
    private String state;

    /**
     * the username of the vpn user
     */
    private String userName;

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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
