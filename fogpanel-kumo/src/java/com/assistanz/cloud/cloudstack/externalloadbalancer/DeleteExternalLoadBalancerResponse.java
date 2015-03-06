package com.assistanz.cloud.cloudstack.externalloadbalancer;

/**
 * 
 * @author Gowtham
 *
 */
public class DeleteExternalLoadBalancerResponse {
	
	/**
	 * any text associated with the success or failure on Delete  external load balancer
	 */
	String displaytext;	
	
	/**
	 * true if operation is executed successfully on Delete  external load balancer
	 */
	String success;

	public String getDisplaytext() {
		return displaytext;
	}

	public void setDisplaytext(String displaytext) {
		this.displaytext = displaytext;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

}
