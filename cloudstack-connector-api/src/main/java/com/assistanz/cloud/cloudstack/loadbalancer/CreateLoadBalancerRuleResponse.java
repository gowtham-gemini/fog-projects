package com.assistanz.cloud.cloudstack.loadbalancer;

import java.util.List;

import com.assistanz.cloud.cloudstack.TagsResponse;

/**
 *
 * @author Santhosh
 *
 */
public class CreateLoadBalancerRuleResponse {

    /**
     * the load balancer rule ID
     */
    private String id;

    /**
     * the account of the load balancer rule
     */
    private String account;

    /**
     * the load balancer algorithm
     */
    private String algorithm;

    /**
     * the cidr list to forward traffic from
     */
    private String cidrList;

    /**
     * the description of the load balancer
     */
    private String description;

    /**
     * the domain of the load balancer rule
     */
    private String domain;

    /**
     * the domain ID of the load balancer rule
     */
    private String domainId;

    /**
     * the name of the load balancer
     */
    private String name;

    /**
     * the id of the guest network the lb rule belongs to
     */
    private String networkId;

    /**
     * the private port
     */
    private String privatePort;

    /**
     * the project name of the load balancer
     */
    private String project;

    /**
     * the project id of the load balancer
     */
    private String projectId;

    /**
     * the public ip address
     */
    private String publicIp;

    /**
     * the public ip address id
     */
    private String publicIpId;

    /**
     * the public port
     */
    private String publicPort;

    /**
     * the state of the rule
     */
    private String state;

    /**
     * the id of the zone the rule belongs to
     */
    private String zoneId;

    /**
     * list of resource tags associated with load balancer
     */
    private List<TagsResponse> tagss;

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

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public String getCidrList() {
        return cidrList;
    }

    public void setCidrList(String cidrList) {
        this.cidrList = cidrList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getNetworkId() {
        return networkId;
    }

    public void setNetworkId(String networkId) {
        this.networkId = networkId;
    }

    public String getPrivatePort() {
        return privatePort;
    }

    public void setPrivatePort(String privatePort) {
        this.privatePort = privatePort;
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

    public String getPublicPort() {
        return publicPort;
    }

    public void setPublicPort(String publicPort) {
        this.publicPort = publicPort;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    public List<TagsResponse> getTagss() {
        return tagss;
    }

    public void setTagss(List<TagsResponse> tagss) {
        this.tagss = tagss;
    }

}
