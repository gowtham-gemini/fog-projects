package com.assistanz.cloud.cloudstack.loadbalancer;

/**
 * 
 * @author Gowtham
 *
 */
public class DeleteLBStickinessPolicyResponse {
	

	/**
	 * any text associated with the success or failure on Delete LB stickiness policy
	 */
	String displaytext;	
	
	/**
	 * true if operation is executed successfully on Delete LB stickiness policy
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
