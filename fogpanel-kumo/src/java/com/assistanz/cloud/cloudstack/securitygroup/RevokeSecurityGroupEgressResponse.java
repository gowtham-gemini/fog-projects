package com.assistanz.cloud.cloudstack.securitygroup;

/**
 * 
 * @author Gowtham
 *
 */
public class RevokeSecurityGroupEgressResponse {
	
	/**
	 * any text associated with the success or failure on revoke Security Group Egress
	 */
	String displaytext;	
	
	/**
	 * true if operation is executed successfully on revoke Security Group Egress
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
