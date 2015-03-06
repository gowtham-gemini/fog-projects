package com.assistanz.cloud.cloudstack.zone;

/***
 * 
 * @author Gowtham
 *
 */
public class DeleteZoneResponse {
	
	/**
	 * any text associated with the success or failure on deleting zone
	 */
	String displaytext;	
	
	/**
	 * true if operation is executed successfully on deleting zone
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
