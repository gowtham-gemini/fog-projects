package com.assistanz.cloud.cloudstack.usages;

/**
 * 
 * @author Gowtham
 *
 */
public class GenerateUsageRecordsResponse {
	
	/**
	 * Any text associated with the success or failure on generating usage record
	 */
	private String displayText;
	
	/**
	 * True if operation is executed successfully on generating usage record
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
