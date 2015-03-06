package com.assistanz.cloud.cloudstack.cloudidentifier;

/**
 * 
 * @author Gowtham
 *
 */
public class CloudIdentifierResponse {

	/**
	 * the cloud identifier
	 */
	private String cloudIdentifier;
	
	/**
	 * the signed response for the cloud identifier
	 */
	private String signature;
	
	/**
	 * the user ID for the cloud identifier
	 */
	private String userId;

	public String getCloudIdentifier() {
		return cloudIdentifier;
	}

	public void setCloudIdentifier(String cloudIdentifier) {
		this.cloudIdentifier = cloudIdentifier;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
