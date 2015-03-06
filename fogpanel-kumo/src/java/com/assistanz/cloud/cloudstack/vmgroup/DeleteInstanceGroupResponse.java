package com.assistanz.cloud.cloudstack.vmgroup;

/**
 * 
 * @author Gowtham
 *
 */
public class DeleteInstanceGroupResponse {
	
	/**
	 * any text associated with the success or failure on Delete Instance Group
	 */
	String displaytext;	
	
	/**
	 * true if operation is executed successfully on Delete Instance Group 
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
