/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.assistanz.cloud.cloudstack.vpn;

/**
 *
 * @author gowtham
 */
class CreateVpnGatewayResponse {
 
    
    /**
     * the vpn gateway ID
     */
    private String vpnGatewayId;

    /**
     * the owner
     */
    private String account;

    /**
     * the guest cidr list of the customer gateway
     */
    private String cidrList;

    /**
     * the domain name of the owner
     */
    private String domain;

    /**
     * the domain id of the owner
     */
    private String domainId;

    /**
     * if DPD is enabled for customer gateway
     */
    private String dpd;

    /**
     * Lifetime of ESP SA of customer gateway
     */
    private String espLifetime;

    /**
     * IPsec policy of customer gateway
     */
    private String espPolicy;

    /**
     * the public ip address id of the customer gateway
     */
    private String gateway;

    /**
     * the Lifetime of IKE SA of customer gateway
     */
    private String ikeLifetime;

    /**
     * the IKE policy of customer gateway
     */
    private String ikePolicy;

    /**
     * the guest ip of the customer gateway
     */
    private String ipAddress;

    /**
     * the IPsec preshared-key of customer gateway
     */
    private String ipSecPsk;

    /**
     * the name of the customer gateway
     */
    private String name;

    /**
     * the project name
     */
    private String project;

    /**
     * the project is
     */
    private String projectId;

    /**
     * the date and time the host was removed
     */
    private String removed;

    /**
     * the vpc id of this gateway
     */
    private String vpcId;
    
    private String jobId;

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getVpnGatewayId() {
        return vpnGatewayId;
    }

    public void setVpnGatewayId(String vpnGatewayId) {
        this.vpnGatewayId = vpnGatewayId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getCidrList() {
        return cidrList;
    }

    public void setCidrList(String cidrList) {
        this.cidrList = cidrList;
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

    public String getDpd() {
        return dpd;
    }

    public void setDpd(String dpd) {
        this.dpd = dpd;
    }

    public String getEspLifetime() {
        return espLifetime;
    }

    public void setEspLifetime(String espLifetime) {
        this.espLifetime = espLifetime;
    }

    public String getEspPolicy() {
        return espPolicy;
    }

    public void setEspPolicy(String espPolicy) {
        this.espPolicy = espPolicy;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    public String getIkeLifetime() {
        return ikeLifetime;
    }

    public void setIkeLifetime(String ikeLifetime) {
        this.ikeLifetime = ikeLifetime;
    }

    public String getIkePolicy() {
        return ikePolicy;
    }

    public void setIkePolicy(String ikePolicy) {
        this.ikePolicy = ikePolicy;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getIpSecPsk() {
        return ipSecPsk;
    }

    public void setIpSecPsk(String ipSecpsk) {
        this.ipSecPsk = ipSecpsk;
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
