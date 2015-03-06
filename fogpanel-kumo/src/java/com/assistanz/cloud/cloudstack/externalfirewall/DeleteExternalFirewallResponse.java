package com.assistanz.cloud.cloudstack.externalfirewall;

/**
 * 
 * @author Gowtham
 *
 */
public class DeleteExternalFirewallResponse {
	
	/**
	 * any text associated with the success or failure on Delete external firewall 
	 */
	String displaytext;	
	
	/**
	 * true if operation is executed successfully on Delete external firewall 
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
