package com.assistanz.cloud.cloudstack.externalloadbalancer;

/**
 * 
 * @author Gowtham
 *
 */
public class AddExternalLoadBalancerResponse {
	
	/**
	 * the ID of the external load balancer
	 */
	String externalLoadBalancerId;	
	/**
	 * the management IP address of the external load balancer
	 */
	String externalLoadBalancerIpAddress;
	
	/**
	 * the number of times to retry requests to the external load balancer
	 */
	String numRetriesExternalLoadBalancer;	
	
	/**
	 * the private interface of the external load balancer
	 */
	String externalLoadBalancerPrivateInterface;
	
	/**
	 * the public interface of the external load balancer
	 */
	String externalLoadBalancerPublicInterface;	
	
	/**
	 * the username that's used to log in to the external load balancer
	 */
	String externalLoadBalancerUserName;
	
	/**
	 * the zone ID of the external load balancer
	 */
	String externalLoadBalancerZoneId;

	public String getExternalLoadBalancerId() {
		return externalLoadBalancerId;
	}

	public void setExternalLoadBalancerId(String externalLoadBalancerId) {
		this.externalLoadBalancerId = externalLoadBalancerId;
	}

	public String getExternalLoadBalancerIpAddress() {
		return externalLoadBalancerIpAddress;
	}

	public void setExternalLoadBalancerIpAddress(
			String externalLoadBalancerIpAddress) {
		this.externalLoadBalancerIpAddress = externalLoadBalancerIpAddress;
	}

	
	public String getNumRetriesExternalLoadBalancer() {
		return numRetriesExternalLoadBalancer;
	}

	public void setNumRetriesExternalLoadBalancer(
			String numRetriesExternalLoadBalancer) {
		this.numRetriesExternalLoadBalancer = numRetriesExternalLoadBalancer;
	}

	public String getExternalLoadBalancerPrivateInterface() {
		return externalLoadBalancerPrivateInterface;
	}

	public void setExternalLoadBalancerPrivateInterface(
			String externalLoadBalancerPrivateInterface) {
		this.externalLoadBalancerPrivateInterface = externalLoadBalancerPrivateInterface;
	}

	public String getExternalLoadBalancerPublicInterface() {
		return externalLoadBalancerPublicInterface;
	}

	public void setExternalLoadBalancerPublicInterface(
			String externalLoadBalancerPublicInterface) {
		this.externalLoadBalancerPublicInterface = externalLoadBalancerPublicInterface;
	}

	public String getExternalLoadBalancerUserName() {
		return externalLoadBalancerUserName;
	}

	public void setExternalLoadBalancerUserName(String externalLoadBalancerUserName) {
		this.externalLoadBalancerUserName = externalLoadBalancerUserName;
	}

	public String getExternalLoadBalancerZoneId() {
		return externalLoadBalancerZoneId;
	}

	public void setExternalLoadBalancerZoneId(String externalLoadBalancerZoneId) {
		this.externalLoadBalancerZoneId = externalLoadBalancerZoneId;
	}	

}
