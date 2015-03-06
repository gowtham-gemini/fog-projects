package com.assistanz.cloud.cloudstack.snapshot;

/**
 * 
 * @author Gowtham
 *
 */
public class DeleteSnapshotPoliciesResponse {

	
	/**
	 * any text associated with the success or failure on deleting snapshot Policies
	 */
	String displaytext;	
	
	/**
	 * true if operation is executed successfully on deleting snapshot Policies
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
