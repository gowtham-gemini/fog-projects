package com.assistanz.cloud.cloudstack.ssh;

/**
 * 
 * @author Gowtham
 *
 */
public class DeleteSSHKeyPairResponse {
	
	
	/**
	 * any text associated with the success or failure on Delete SSHKeyPair
	 */
	String displaytext;	
	
	/**
	 * true if operation is executed successfully on Delete SSHKeyPair
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
