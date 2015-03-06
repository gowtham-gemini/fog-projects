package com.assistanz.cloud.cloudstack.usages;

/**
 * 
 * @author Gowtham
 *
 */
public class DeleteTrafficMonitorResponse {
	
	/**
	 * Any text associated with the success or failure on deleting traffic monitor from account
	 */
	private String displayText;
	
	/**
	 * True if operation is executed successfully on deleting traffic monitor from account
	 */
	private String success;

	public String getDisplayText() {
		return displayText;
	}

	public void setDisplayText(String displayText) {
		this.displayText = displayText;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

}
