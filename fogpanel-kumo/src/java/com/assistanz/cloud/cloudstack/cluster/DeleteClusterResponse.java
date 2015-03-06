package com.assistanz.cloud.cloudstack.cluster;

/**
 * 
 * @author Gowtham
 *
 */
public class DeleteClusterResponse {
	
	/**
	 * any text associated with the success or failure on Delete cluster
	 */
	String displaytext;	
	
	/**
	 * true if operation is executed successfully on Delete cluster 
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
