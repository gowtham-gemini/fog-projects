package com.assistanz.cloud.cloudstack;

/**
 * 
 * @author Gowtham
 *
 */
public class StickinessPolicyResponse {
	
	/**
	 * the LB Stickiness policy ID
	 */
	String lbStickinessPolicyId;	
	
	/**
	 * the description of the Stickiness policy
	 */
	String stickinessPolicyDescription;	
	
	/**
	 * the method name of the Stickiness policy
	 */
	String stickinessPolicyMethodName;
	
	/**
	 * the name of the Stickiness policy
	 */
	String stickinessPolicyName;
	
	/**
	 * the params of the policy
	 */
	String stickinessPolicyParams;
	
	/**
	 * the state of the policy
	 */
	String stickinessPolicyState;
	

	public String getLbStickinessPolicyId() {
		return lbStickinessPolicyId;
	}

	public void setLbStickinessPolicyId(String lbStickinessPolicyId) {
		this.lbStickinessPolicyId = lbStickinessPolicyId;
	}

	public String getStickinessPolicyDescription() {
		return stickinessPolicyDescription;
	}

	public void setStickinessPolicyDescription(String stickinessPolicyDescription) {
		this.stickinessPolicyDescription = stickinessPolicyDescription;
	}

	public String getStickinessPolicyMethodName() {
		return stickinessPolicyMethodName;
	}

	public void setStickinessPolicyMethodName(String stickinessPolicyMethodName) {
		this.stickinessPolicyMethodName = stickinessPolicyMethodName;
	}

	public String getStickinessPolicyName() {
		return stickinessPolicyName;
	}

	public void setStickinessPolicyName(String stickinessPolicyName) {
		this.stickinessPolicyName = stickinessPolicyName;
	}

	public String getStickinessPolicyParams() {
		return stickinessPolicyParams;
	}

	public void setStickinessPolicyParams(String stickinessPolicyParams) {
		this.stickinessPolicyParams = stickinessPolicyParams;
	}

	public String getStickinessPolicyState() {
		return stickinessPolicyState;
	}

	public void setStickinessPolicyState(String stickinessPolicyState) {
		this.stickinessPolicyState = stickinessPolicyState;
	}	
	
	

}
