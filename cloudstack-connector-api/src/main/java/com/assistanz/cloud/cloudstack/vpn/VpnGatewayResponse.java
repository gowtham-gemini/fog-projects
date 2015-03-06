package com.assistanz.cloud.cloudstack.vpn;

/**
 *
 * @author Santhosh
 *
 */
public class VpnGatewayResponse {

    /**
     * the vpn gateway ID
     */
    private String id;

    /**
     * the owner
     */
    private String account;

    /**
     * the domain name of the owner
     */
    private String domain;

    /**
     * the domain id of the owner
     */
    private String domainId;

    /**
     * the project name
     */
    private String project;

    /**
     * the project is
     */
    private String projectId;

    /**
     * the public IP address
     */
    private String publicIp;

    /**
     * the date and time the host was removed
     */
    private String removed;

    /**
     * the vpc id of this gateway
     */
    private String vpcId;

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

    public String getPublicIp() {
        return publicIp;
    }

    public void setPublicIp(String publicIp) {
        this.publicIp = publicIp;
    }

    public String getRemoved() {
        return removed;
    }

    public void setRemoved(String removed) {
        this.removed = removed;
    }

    public String getVpcId() {
        return vpcId;
    }

    public void setVpcId(String vpcId) {
        this.vpcId = vpcId;
    }

}
