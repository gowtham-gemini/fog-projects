package com.assistanz.cloud.cloudstack.vpn;

/**
 *
 * @author Gowtham
 *
 */
public class CreateRemoteAccessVpnResponse {

    /**
     * the id of the remote access vpn
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
     * the range of ips to allocate to the clients
     */
    private String ipRange;

    /**
     * the ipsec preshared key
     */
    private String presharedKey;

    /**
     * the project name of the vpn
     */
    private String project;

    /**
     * the project id of the vpn
     */
    private String projectId;

    /**
     * the public ip address of the vpn server
     */
    private String publicIp;

    /**
     * the public ip address of the vpn server
     */
    private String publicIpId;

    /**
     * the state of the rule
     */
    private String state;
    
    /**
     * the jobid of the rule
     */
    private String jobId;

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

    public String getIpRange() {
        return ipRange;
    }

    public void setIpRange(String ipRange) {
        this.ipRange = ipRange;
    }

    public String getPresharedKey() {
        return presharedKey;
    }

    public void setPresharedKey(String presharedKey) {
        this.presharedKey = presharedKey;
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

    public String getPublicIpId() {
        return publicIpId;
    }

    public void setPublicIpId(String publicIpId) {
        this.publicIpId = publicIpId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

}
