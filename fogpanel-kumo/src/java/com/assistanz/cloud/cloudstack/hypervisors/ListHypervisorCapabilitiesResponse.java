package com.assistanz.cloud.cloudstack.hypervisors;

/**
 * 
 * @author Gowtham
 *
 */
public class ListHypervisorCapabilitiesResponse {
	
	/**
	 * the ID of the hypervisor capabilities row
	 */
	String hypervisorCapabilitiesId;	
	
	/**
	 * the hypervisor type
	 */
	String hypervisorType;
	
	/**
	 * the hypervisor version
	 */
	String hypervisorVersion;
	
	/**
	 * the maximum number of guest vms recommended for this hypervisor
	 */
	String maxGuestLimit;
	
	/**
	 * true if security group is supported
	 */
	String securityGroupEnabled;

	public String getHypervisorCapabilitiesId() {
		return hypervisorCapabilitiesId;
	}

	public void setHypervisorCapabilitiesId(String hypervisorCapabilitiesId) {
		this.hypervisorCapabilitiesId = hypervisorCapabilitiesId;
	}

	public String getHypervisorType() {
		return hypervisorType;
	}

	public void setHypervisorType(String hypervisorType) {
		this.hypervisorType = hypervisorType;
	}

	public String getHypervisorVersion() {
		return hypervisorVersion;
	}

	public void setHypervisorVersion(String hypervisorVersion) {
		this.hypervisorVersion = hypervisorVersion;
	}

	public String getMaxGuestLimit() {
		return maxGuestLimit;
	}

	public void setMaxGuestLimit(String maxGuestLimit) {
		this.maxGuestLimit = maxGuestLimit;
	}

	public String getSecurityGroupEnabled() {
		return securityGroupEnabled;
	}

	public void setSecurityGroupEnabled(String securityGroupEnabled) {
		this.securityGroupEnabled = securityGroupEnabled;
	}
	
}
