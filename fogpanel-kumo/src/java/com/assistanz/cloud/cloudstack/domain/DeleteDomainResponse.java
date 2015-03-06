package com.assistanz.cloud.cloudstack.domain;

/**
 * 
 * @author Gowtham
 *
 */
public class DeleteDomainResponse {
	
	/**
	 * any text associated with the success or failure on deleting domain
	 */
	String displaytext;	
	
	/**
	 * true if operation is executed successfully on deleting domain
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
