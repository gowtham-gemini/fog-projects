package com.assistanz.cloud.cloudstack.nat;

/**
 * 
 * @author Gowtham
 *
 */
public class DeleteIpForwardingRuleResponse {
	
	/**
	 * any text associated with the success or failure on Delete Ip Forwarding Rule 
	 */
	String displaytext;	
	
	/**
	 * true if operation is executed successfully on Delete Ip Forwarding Rule 
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
