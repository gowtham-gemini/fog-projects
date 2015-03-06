package com.assistanz.cloud.cloudstack.storage;

/**
 * 
 * @author Gowtham
 *
 */
public class AssociateLunResponse {
	
	/**
	 * the LUN id
	 */
	String lunId;
	
	/**
	 * the IP address of lun
	 */
	String lunIpAddress;
	
	/**
	 * the target IQN
	 */
	String targetIqn;

	public String getLunId() {
		return lunId;
	}

	public void setLunId(String lunId) {
		this.lunId = lunId;
	}

	public String getLunIpAddress() {
		return lunIpAddress;
	}

	public void setLunIpAddress(String lunIpAddress) {
		this.lunIpAddress = lunIpAddress;
	}

	public String getTargetIqn() {
		return targetIqn;
	}

	public void setTargetIqn(String targetIqn) {
		this.targetIqn = targetIqn;
	}	
	
}
