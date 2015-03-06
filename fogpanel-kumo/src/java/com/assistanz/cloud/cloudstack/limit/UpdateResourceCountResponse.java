package com.assistanz.cloud.cloudstack.limit;

/**
 * 
 * @author Gowtham
 *
 */
public class UpdateResourceCountResponse {
	
	/**
	 * the account of the resource limit
	 */
	String resourceLimitAccount; 
	
	/**
	 * the domain name of the resource limit
	 */
	String resourceLimitDomain;	
	
	/**
	 * the domain ID of the resource limit
	 */
	String resourceLimitDomainId;
	
	/**
	 * the maximum number of the resource. A -1 means the resource currently has no limit.
	 */
	String resourceLimitMax;
	
	/**
	 * the project name of the resource limit
	 */
	String resourceLimitProjectName;
	
	/**
	 * the project id of the resource limit
	 */
	String resourceLimitProjectId;
	
	/**
	 * the resource type. 
	 * Values include 0, 1, 2, 3, 4. 
	 * See the resourceType parameter for more information on these values.
	 */
	String resourcetype;

	public String getResourceLimitAccount() {
		return resourceLimitAccount;
	}

	public void setResourceLimitAccount(String resourceLimitAccount) {
		this.resourceLimitAccount = resourceLimitAccount;
	}

	public String getResourceLimitDomain() {
		return resourceLimitDomain;
	}

	public void setResourceLimitDomain(String resourceLimitDomain) {
		this.resourceLimitDomain = resourceLimitDomain;
	}

	public String getResourceLimitDomainId() {
		return resourceLimitDomainId;
	}

	public void setResourceLimitDomainId(String resourceLimitDomainId) {
		this.resourceLimitDomainId = resourceLimitDomainId;
	}

	public String getResourceLimitMax() {
		return resourceLimitMax;
	}

	public void setResourceLimitMax(String resourceLimitMax) {
		this.resourceLimitMax = resourceLimitMax;
	}

	public String getResourceLimitProjectName() {
		return resourceLimitProjectName;
	}

	public void setResourceLimitProjectName(String resourceLimitProjectName) {
		this.resourceLimitProjectName = resourceLimitProjectName;
	}

	public String getResourceLimitProjectId() {
		return resourceLimitProjectId;
	}

	public void setResourceLimitProjectId(String resourceLimitProjectId) {
		this.resourceLimitProjectId = resourceLimitProjectId;
	}

	public String getResourcetype() {
		return resourcetype;
	}

	public void setResourcetype(String resourcetype) {
		this.resourcetype = resourcetype;
	}

}
