package com.assistanz.cloud.cloudstack.loadbalancer;


/**
 * 
 * @author Gowtham
 *
 */
public class DeleteF5LoadBalancerResponse {
	
	/**
	 * any text associated with the success or failure on Delete F5Load Balancer
	 */
	String displaytext;	
	
	/**
	 * true if operation is executed successfully on Delete F5Load Balancer
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
