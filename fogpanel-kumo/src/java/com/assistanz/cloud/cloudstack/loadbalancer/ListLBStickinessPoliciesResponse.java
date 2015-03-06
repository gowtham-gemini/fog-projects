package com.assistanz.cloud.cloudstack.loadbalancer;

import java.util.List;

import com.assistanz.cloud.cloudstack.StickinessPolicyResponse;

/**
 * 
 * @author Gowtham
 *
 */
public class ListLBStickinessPoliciesResponse {
	
	
	/**
	 * the account of the Stickiness policy
	 */
	String stickinessPolicyAccount;	
	
	/**
	 * the description of the Stickiness policy
	 */
	String stickinessPolicyDescription;	
	
	/**
	 * the domain of the Stickiness policy
	 */
	String stickinessPolicyDomain;
	
	/**
	 * the domain ID of the Stickiness policy
	 */
	String stickinessPolicyDomainId;
	
	/**
	 * the LB rule ID
	 */
	String lbRuleId;
	
	/**
	 * the name of the Stickiness policy
	 */
	String stickinessPolicyName;
	
	/**
	 * the state of the policy
	 */
	String stickinessPolicyState;
	
	/**
	 * the id of the zone the Stickiness policy belongs to
	 */
	String stickinessPolicyZoneId;	
	
	private List<StickinessPolicyResponse> LbStickinessPolicys;

	public String getStickinessPolicyAccount() {
		return stickinessPolicyAccount;
	}

	public void setStickinessPolicyAccount(String stickinessPolicyAccount) {
		this.stickinessPolicyAccount = stickinessPolicyAccount;
	}

	public String getStickinessPolicyDescription() {
		return stickinessPolicyDescription;
	}

	public void setStickinessPolicyDescription(String stickinessPolicyDescription) {
		this.stickinessPolicyDescription = stickinessPolicyDescription;
	}

	public String getStickinessPolicyDomain() {
		return stickinessPolicyDomain;
	}

	public void setStickinessPolicyDomain(String stickinessPolicyDomain) {
		this.stickinessPolicyDomain = stickinessPolicyDomain;
	}

	public String getStickinessPolicyDomainId() {
		return stickinessPolicyDomainId;
	}

	public void setStickinessPolicyDomainId(String stickinessPolicyDomainId) {
		this.stickinessPolicyDomainId = stickinessPolicyDomainId;
	}

	public String getLbRuleId() {
		return lbRuleId;
	}

	public void setLbRuleId(String lbRuleId) {
		this.lbRuleId = lbRuleId;
	}

	public String getStickinessPolicyName() {
		return stickinessPolicyName;
	}

	public void setStickinessPolicyName(String stickinessPolicyName) {
		this.stickinessPolicyName = stickinessPolicyName;
	}

	public String getStickinessPolicyState() {
		return stickinessPolicyState;
	}

	public void setStickinessPolicyState(String stickinessPolicyState) {
		this.stickinessPolicyState = stickinessPolicyState;
	}

	public String getStickinessPolicyZoneId() {
		return stickinessPolicyZoneId;
	}

	public void setStickinessPolicyZoneId(String stickinessPolicyZoneId) {
		this.stickinessPolicyZoneId = stickinessPolicyZoneId;
	}

	public List<StickinessPolicyResponse> getLbStickinessPolicys() {
		return LbStickinessPolicys;
	}

	public void setLbStickinessPolicys(
			List<StickinessPolicyResponse> lbStickinessPolicys) {
		LbStickinessPolicys = lbStickinessPolicys;
	}

}
