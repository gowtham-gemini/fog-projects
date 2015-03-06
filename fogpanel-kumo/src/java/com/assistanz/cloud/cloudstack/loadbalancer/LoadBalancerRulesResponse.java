/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.assistanz.cloud.cloudstack.loadbalancer;

/**
 *
 * @author gowtham
 */
class LoadBalancerRulesResponse {
    
    
        /**
	 * the load balancer rule ID
	 */
	String loadBalancerId;	
        
         /**
	 * the load balancer rule ID
	 */
	String networkId;	
	
	/**
	 * the account of the load balancer rule
	 */
	String loadBalancerAccount;	
	
	/**
	 * the load balancer algorithm (source, roundrobin, leastconn)
	 */
	String loadBalancerAlgorithm;
	
	/**
	 * the cidr list to forward traffic from
	 */
	String loadBalancerCidrList;
	
	/**
	 * the description of the load balancer
	 */
	String loadBalancerDescription;	
	
	/**
	 * the domain of the load balancer rule
	 */
	String loadBalancerDomain;	
	
	/**
	 * the domain ID of the load balancer rule
	 */
	String loadBalancerDomainId;
	
	/**
	 * the name of the load balancer
	 */
	String loadBalancerName;
	
	/**
	 * the private port
	 */
	String loadBalancerPrivatePort;	
	
	/**
	 * the project name of the load balancer
	 */
	String loadBalancerProject;	
	
	/**
	 * the project id of the load balancer
	 */
	String loadBalancerProjectId;
	
	/**
	 * the public ip address
	 */
	String loadBalancerPublicIp;
	
	/**
	 * the public ip address id
	 */
	String loadBalancerPublicIpId;
	
	/**
	 * the public port
	 */
	String loadBalancerPublicPort;
	
	/**
	 * the state of the rule
	 */
	String loadBalancerState;	
	
	/**
	 * the id of the zone the rule belongs to
	 */
	String loadBalancerZoneId;

        public String getNetworkId() {
            return networkId;
        }

        public void setNetworkId(String networkId) {
            this.networkId = networkId;
        }

        

	public String getLoadBalancerId() {
		return loadBalancerId;
	}

	public void setLoadBalancerId(String loadBalancerId) {
		this.loadBalancerId = loadBalancerId;
	}

	public String getLoadBalancerAccount() {
		return loadBalancerAccount;
	}

	public void setLoadBalancerAccount(String loadBalancerAccount) {
		this.loadBalancerAccount = loadBalancerAccount;
	}

	public String getLoadBalancerAlgorithm() {
		return loadBalancerAlgorithm;
	}

	public void setLoadBalancerAlgorithm(String loadBalancerAlgorithm) {
		this.loadBalancerAlgorithm = loadBalancerAlgorithm;
	}

	public String getLoadBalancerCidrList() {
		return loadBalancerCidrList;
	}

	public void setLoadBalancerCidrList(String loadBalancerCidrList) {
		this.loadBalancerCidrList = loadBalancerCidrList;
	}

	public String getLoadBalancerDescription() {
		return loadBalancerDescription;
	}

	public void setLoadBalancerDescription(String loadBalancerDescription) {
		this.loadBalancerDescription = loadBalancerDescription;
	}

	public String getLoadBalancerDomain() {
		return loadBalancerDomain;
	}

	public void setLoadBalancerDomain(String loadBalancerDomain) {
		this.loadBalancerDomain = loadBalancerDomain;
	}

	public String getLoadBalancerDomainId() {
		return loadBalancerDomainId;
	}

	public void setLoadBalancerDomainId(String loadBalancerDomainId) {
		this.loadBalancerDomainId = loadBalancerDomainId;
	}

	public String getLoadBalancerName() {
		return loadBalancerName;
	}

	public void setLoadBalancerName(String loadBalancerName) {
		this.loadBalancerName = loadBalancerName;
	}

	public String getLoadBalancerPrivatePort() {
		return loadBalancerPrivatePort;
	}

	public void setLoadBalancerPrivatePort(String loadBalancerPrivatePort) {
		this.loadBalancerPrivatePort = loadBalancerPrivatePort;
	}

	public String getLoadBalancerProject() {
		return loadBalancerProject;
	}

	public void setLoadBalancerProject(String loadBalancerProject) {
		this.loadBalancerProject = loadBalancerProject;
	}

	public String getLoadBalancerProjectId() {
		return loadBalancerProjectId;
	}

	public void setLoadBalancerProjectId(String loadBalancerProjectId) {
		this.loadBalancerProjectId = loadBalancerProjectId;
	}

	public String getLoadBalancerPublicIp() {
		return loadBalancerPublicIp;
	}

	public void setLoadBalancerPublicIp(String loadBalancerPublicIp) {
		this.loadBalancerPublicIp = loadBalancerPublicIp;
	}

	public String getLoadBalancerPublicIpId() {
		return loadBalancerPublicIpId;
	}

	public void setLoadBalancerPublicIpId(String loadBalancerPublicIpId) {
		this.loadBalancerPublicIpId = loadBalancerPublicIpId;
	}

	public String getLoadBalancerPublicPort() {
		return loadBalancerPublicPort;
	}

	public void setLoadBalancerPublicPort(String loadBalancerPublicPort) {
		this.loadBalancerPublicPort = loadBalancerPublicPort;
	}

	public String getLoadBalancerState() {
		return loadBalancerState;
	}

	public void setLoadBalancerState(String loadBalancerState) {
		this.loadBalancerState = loadBalancerState;
	}

	public String getLoadBalancerZoneId() {
		return loadBalancerZoneId;
	}

	public void setLoadBalancerZoneId(String loadBalancerZoneId) {
		this.loadBalancerZoneId = loadBalancerZoneId;
	}
    
}
