package com.assistanz.cloud.cloudstack.loadbalancer;

import com.assistanz.cloud.cloudstack.TagsResponse;
import java.util.List;

/**
 *
 * @author Santhosh
 *
 */
public class LoadBalancersResponse {

    /**
     * the load balancer rule ID
     */
    private String id;

    /**
     * the account of the load balancer rule
     */
    private String account;
    
    /**
     * the cidr list to forward traffic from
     */
    private String cidr;
    
    /**
     * the private port
     */
    private String privatePort;
    
    /**
     * the load balancer algorithm
     */
    private String algorithm;

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

    public String getCidr() {
        return cidr;
    }

    public void setCidr(String cidr) {
        this.cidr = cidr;
    }

    public String getPrivatePort() {
        return privatePort;
    }

    public void setPrivatePort(String privatePort) {
        this.privatePort = privatePort;
    }

}
