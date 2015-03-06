package com.assistanz.cloud.cloudstack.template;

/**
 * 
 * @author Gowtham
 *
 */
public class CopyTemplateResponse {
	
	/**
	 * the template ID
	 */
	private String templateId;
	
	/**
	 * the account name to which the template belongs
	 */
	private String accountName;
	
	/**
	 * the account id to which the template belongs
	 */
	private String accountId;
	
	/**
	 * true if the ISO is bootable, false otherwise
	 */
	private String bootable;
	
	/**
	 * checksum of the template
	 */
	private String checksum;
	
	/**
	 * the date this template was created
	 */
	private String templateCreated;
	
	/**
	 * true if the template is managed across all Zones, false otherwise
	 */
	private String crossZones;
	
	/**
	 * additional key/value details tied with template
	 */
	private String details;
	
	/**
	 * the template display text
	 */
	private String templateDisplayText;
	
	/**
	 * the name of the domain to which the template belongs
	 */
	private String domainName;
	
	/**
	 * the ID of the domain to which the template belongs
	 */
	private String domainId;
	
	/**
	 * the format of the template.
	 */
	private String templateFormat;
	
	/**
	 * the ID of the secondary storage host for the template
	 */
	private String hostId;
	
	/**
	 * the name of the secondary storage host for the template
	 */
	private String hostName;
	
	/**
	 * the hypervisor on which the template runs
	 */
	private String hypervisor;
	
	/**
	 * true if the template is extractable, false otherwise
	 */
	private String isExtractable;
	
	/**
	 * true if this template is a featured template, false otherwise
	 */
	private String isFeatured;
	
	/**
	 * true if this template is a public template, false otherwise
	 */
	private String isPublic;
	
	/**
	 * true if the template is ready to be deployed from, false otherwise.
	 */
	private String isReady;	
	
	/**
	 * the template name
	 */
	private String templateName; 
	
	/**
	 * the ID of the OS type for this template.
	 */
	private String osTypeId	;
	
	/**
	 * the name of the OS type for this template.
	 */
	private String osTypeName;
	
	/**
	 * true if the reset password feature is enabled, false otherwise
	 */
	private String passwordEnabled;
	
	/**
	 * 	the project name of the template
	 */
	private String templateProjectName;
	
	/**
	 * the project id of the template
	 */
	private String templateProjectId;
	
	/**
	 * 	the date this template was removed
	 */
	private String removed;
	
	/**
	 * the size of the template
	 */
	private String templateSize;
	
	/**
	 * the template ID of the parent template if present
	 */
	private String sourceTemplateId;
	
	/**
	 * the status of the template
	 */
	private String templateStatus;
	
	/**
	 * the tag of this template
	 */
	private String templateTag;	
	
	/**
	 * the type of the template
	 */
	private String templateType;
	
	/**
	 * the ID of the zone for this template
	 */
	private String templateZoneId;
	
	/**
	 * the name of the zone for this template
	 */
	private String templateZoneName;
	
	/**
	 * the ID of the latest async job acting on this object
	 */
	private String jobId;
	
	/**
	 * the current status of the latest async job acting on this object
	 */
	private String jobStatus;

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getBootable() {
		return bootable;
	}

	public void setBootable(String bootable) {
		this.bootable = bootable;
	}

	public String getChecksum() {
		return checksum;
	}

	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}

	public String getTemplateCreated() {
		return templateCreated;
	}

	public void setTemplateCreated(String templateCreated) {
		this.templateCreated = templateCreated;
	}

	public String getCrossZones() {
		return crossZones;
	}

	public void setCrossZones(String crossZones) {
		this.crossZones = crossZones;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getTemplateDisplayText() {
		return templateDisplayText;
	}

	public void setTemplateDisplayText(String templateDisplayText) {
		this.templateDisplayText = templateDisplayText;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public String getDomainId() {
		return domainId;
	}

	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}

	public String getTemplateFormat() {
		return templateFormat;
	}

	public void setTemplateFormat(String templateFormat) {
		this.templateFormat = templateFormat;
	}

	public String getHostId() {
		return hostId;
	}

	public void setHostId(String hostId) {
		this.hostId = hostId;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getHypervisor() {
		return hypervisor;
	}

	public void setHypervisor(String hypervisor) {
		this.hypervisor = hypervisor;
	}

	public String getIsExtractable() {
		return isExtractable;
	}

	public void setIsExtractable(String isExtractable) {
		this.isExtractable = isExtractable;
	}

	public String getIsFeatured() {
		return isFeatured;
	}

	public void setIsFeatured(String isFeatured) {
		this.isFeatured = isFeatured;
	}

	public String getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(String isPublic) {
		this.isPublic = isPublic;
	}

	public String getIsReady() {
		return isReady;
	}

	public void setIsReady(String isReady) {
		this.isReady = isReady;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getOsTypeId() {
		return osTypeId;
	}

	public void setOsTypeId(String osTypeId) {
		this.osTypeId = osTypeId;
	}

	public String getOsTypeName() {
		return osTypeName;
	}

	public void setOsTypeName(String osTypeName) {
		this.osTypeName = osTypeName;
	}

	public String getPasswordEnabled() {
		return passwordEnabled;
	}

	public void setPasswordEnabled(String passwordEnabled) {
		this.passwordEnabled = passwordEnabled;
	}

	public String getTemplateProjectName() {
		return templateProjectName;
	}

	public void setTemplateProjectName(String templateProjectName) {
		this.templateProjectName = templateProjectName;
	}

	public String getTemplateProjectId() {
		return templateProjectId;
	}

	public void setTemplateProjectId(String templateProjectId) {
		this.templateProjectId = templateProjectId;
	}

	public String getRemoved() {
		return removed;
	}

	public void setRemoved(String removed) {
		this.removed = removed;
	}

	public String getTemplateSize() {
		return templateSize;
	}

	public void setTemplateSize(String templateSize) {
		this.templateSize = templateSize;
	}

	public String getSourceTemplateId() {
		return sourceTemplateId;
	}

	public void setSourceTemplateId(String sourceTemplateId) {
		this.sourceTemplateId = sourceTemplateId;
	}

	public String getTemplateStatus() {
		return templateStatus;
	}

	public void setTemplateStatus(String templateStatus) {
		this.templateStatus = templateStatus;
	}

	public String getTemplateTag() {
		return templateTag;
	}

	public void setTemplateTag(String templateTag) {
		this.templateTag = templateTag;
	}

	public String getTemplateType() {
		return templateType;
	}

	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}

	public String getTemplateZoneId() {
		return templateZoneId;
	}

	public void setTemplateZoneId(String templateZoneId) {
		this.templateZoneId = templateZoneId;
	}

	public String getTemplateZoneName() {
		return templateZoneName;
	}

	public void setTemplateZoneName(String templateZoneName) {
		this.templateZoneName = templateZoneName;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

}
