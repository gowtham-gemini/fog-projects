package com.assistanz.cloud.cloudstack.configuration;

/**
 *
 * @author Gowtham
 *
 */
public class CapabilitiesResponse {

    /**
     * true if regular user is allowed to create projects
     */
    private String allowUserCreateProjects;

    /**
     * time interval (in seconds) to reset api count
     */
    private String apiLimitInterval;

    /**
     * Max allowed number of api requests within the specified interval
     */
    private String apiLimitMax;

    /**
     * version of the cloud stack
     */
    private String cloudstackVersion;

    /**
     * maximum size that can be specified when create disk from disk offering with custom size
     */
    private String customDiskOfferingMaxSize;

    /**
     * true if snapshot is supported for KVM host, false otherwise
     */
    private String kvmSnapshotEnabled;

    /**
     * If invitation confirmation is required when add account to project
     */
    private String projectInviteRequired;

    /**
     * true if region wide secondary is enabled, false otherwise
     */
    private String regionSecondaryEnabled;

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

    public String getApiLimitInterval() {
        return apiLimitInterval;
    }

    public void setApiLimitInterval(String apiLimitInterval) {
        this.apiLimitInterval = apiLimitInterval;
    }

    public String getApiLimitMax() {
        return apiLimitMax;
    }

    public void setApiLimitMax(String apiLimitMax) {
        this.apiLimitMax = apiLimitMax;
    }

    public String getCloudstackVersion() {
        return cloudstackVersion;
    }

    public void setCloudstackVersion(String cloudstackVersion) {
        this.cloudstackVersion = cloudstackVersion;
    }

    public String getCustomDiskOfferingMaxSize() {
        return customDiskOfferingMaxSize;
    }

    public void setCustomDiskOfferingMaxSize(String customDiskOfferingMaxSize) {
        this.customDiskOfferingMaxSize = customDiskOfferingMaxSize;
    }

    public String getKVMSnapshotEnabled() {
        return kvmSnapshotEnabled;
    }

    public void setKVMSnapshotEnabled(String kvmSnapshotEnabled) {
        this.kvmSnapshotEnabled = kvmSnapshotEnabled;
    }

    public String getProjectInviteRequired() {
        return projectInviteRequired;
    }

    public void setProjectInviteRequired(String projectInviteRequired) {
        this.projectInviteRequired = projectInviteRequired;
    }

    public String getRegionSecondaryEnabled() {
        return regionSecondaryEnabled;
    }

    public void setRegionSecondaryEnabled(String regionSecondaryEnabled) {
        this.regionSecondaryEnabled = regionSecondaryEnabled;
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

}
