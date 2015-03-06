package com.assistanz.cloud.cloudstack.network;

/**
 * 
 * @author Gowtham
 *
 */
public class DeleteStorageNetworkIpRangeResponse {
	
	/**
	 * any text associated with the success or failure on Delete Storage Network Ip Range 
	 */ 
	String displaytext;	
	
	/**
	 * true if operation is executed successfully on Delete Storage Network Ip Range  
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
