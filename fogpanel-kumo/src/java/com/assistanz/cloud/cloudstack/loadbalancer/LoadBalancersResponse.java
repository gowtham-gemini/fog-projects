/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.assistanz.cloud.cloudstack.loadbalancer;

import com.assistanz.cloud.cloudstack.TagsResponse;
import java.util.List;

/**
 *
 * @author gowtham
 */
class LoadBalancersResponse {
    /**
     * the load balancer rule ID
     */
    private String lbRuleId;

    /**
     * the account of the load balancer rule
     */
    private String accountName;

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
    private String domainName;

    /**
     * the domain ID of the load balancer rule
     */
    private String domainId;

    /**
     * the name of the load balancer
     */
    private String loadBalancerName;

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
     * the Load Balancer source ip
     */
    private String sourceIpAddress;

    /**
     * the Load Balancer source ip network id
     */
    private String sourceIpAddressNetworkId;

    /**
     * the list of instances associated with the Load Balancer
     */
    private List<LoadBalancerInstanceResponse> loadBalancerInstances;

    /**
     * the list of rules associated with the Load Balancer
     */
    private List<LoadBalancerResponse> loadBalancers;

    /**
     * the list of resource tags associated with load balancer
     */
    private List<TagsResponse> tagss;

    public String getLbRuleId() {
        return lbRuleId;
    }

    public void setLbRuleId(String lbRuleId) {
        this.lbRuleId = lbRuleId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
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

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

    public String getLoadBalancerName() {
        return loadBalancerName;
    }

    public void setLoadBalancerName(String loadBalancerName) {
        this.loadBalancerName = loadBalancerName;
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

    public String getSourceIpAddress() {
        return sourceIpAddress;
    }

    public void setSourceIpAddress(String sourceIpAddress) {
        this.sourceIpAddress = sourceIpAddress;
    }

    public String getSourceIpAddressNetworkId() {
        return sourceIpAddressNetworkId;
    }

    public void setSourceIpAddressNetworkId(String sourceIpAddressNetworkId) {
        this.sourceIpAddressNetworkId = sourceIpAddressNetworkId;
    }

    public List<LoadBalancerInstanceResponse> getLoadBalancerInstances() {
        return loadBalancerInstances;
    }

    public void setLoadBalancerInstances(List<LoadBalancerInstanceResponse> loadBalancerInstances) {
        this.loadBalancerInstances = loadBalancerInstances;
    }

    public List<LoadBalancerResponse> getLoadBalancers() {
        return loadBalancers;
    }

    public void setLoadBalancers(List<LoadBalancerResponse> loadBalancers) {
        this.loadBalancers = loadBalancers;
    }

    public List<TagsResponse> getTagss() {
        return tagss;
    }

    public void setTagss(List<TagsResponse> tagss) {
        this.tagss = tagss;
    }
}
