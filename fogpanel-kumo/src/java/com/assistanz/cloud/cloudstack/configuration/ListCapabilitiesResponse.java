package com.assistanz.cloud.cloudstack.configuration;

/**
 * 
 * @author Gowtham
 *
 */
public class ListCapabilitiesResponse {
	
	/**
	 * true if regular user is allowed to create projects
	 */
	private String allowUserCreateProjects;
	
	/**
	 * version of the cloud stack
	 */
	private String cloudStackVersion;
        
        /**
	 * maximum size that can be specified when create disk from disk offering with custom size
	 */
	private String customDiskofferingMaxSize;
	
	/**
	 * If invitation confirmation is required when add account to project
	 */
	private String projectInviteRequired;
	
	/**
	 * true if security groups support is enabled, false otherwise
	 */
	private String securityGroupsEnabled;
	
	/**
	 * true if region supports elastic load balancer on basic zones
	 */
	private String supportELB;
	
	/**
	 * true if user and domain admins can set templates to be shared, false otherwise
	 */
	private String userPublicTemplateEnabled;

	public String getAllowUserCreateProjects() {
		return allowUserCreateProjects;
	}

	public void setAllowUserCreateProjects(String allowUserCreateProjects) {
		this.allowUserCreateProjects = allowUserCreateProjects;
	}

	public String getCloudStackVersion() {
		return cloudStackVersion;
	}

	public void setCloudStackVersion(String cloudStackVersion) {
		this.cloudStackVersion = cloudStackVersion;
	}

	public String getProjectInviteRequired() {
		return projectInviteRequired;
	}

	public void setProjectInviteRequired(String projectInviteRequired) {
		this.projectInviteRequired = projectInviteRequired;
	}

	public String getSecurityGroupsEnabled() {
		return securityGroupsEnabled;
	}

	public void setSecurityGroupsEnabled(String securityGroupsEnabled) {
		this.securityGroupsEnabled = securityGroupsEnabled;
	}

	public String getSupportELB() {
		return supportELB;
	}

	public void setSupportELB(String supportELB) {
		this.supportELB = supportELB;
	}

	public String getUserPublicTemplateEnabled() {
		return userPublicTemplateEnabled;
	}

	public void setUserPublicTemplateEnabled(String userPublicTemplateEnabled) {
		this.userPublicTemplateEnabled = userPublicTemplateEnabled;
	}	

        public String getCustomDiskofferingMaxSize() {
            return customDiskofferingMaxSize;
        }

        public void setCustomDiskofferingMaxSize(String customDiskofferingMaxSize) {
            this.customDiskofferingMaxSize = customDiskofferingMaxSize;
        }

        
}
