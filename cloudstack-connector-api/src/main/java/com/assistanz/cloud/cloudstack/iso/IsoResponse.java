package com.assistanz.cloud.cloudstack.iso;

import com.assistanz.cloud.cloudstack.TagsResponse;
import java.util.List;

/**
 *
 * @author Gowtham
 */
class IsoResponse {

    /**
     * the iso ID
     */
    private String id;

    /**
     * the account name to which the iso belongs
     */
    private String account;

    /**
     * the account id to which the iso belongs
     */
    private String accountId;

    /**
     * true if the ISO is bootable, false otherwise
     */
    private String bootable;

    /**
     * checksum of the iso
     */
    private String checksum;

    /**
     * the date this iso was created
     */
    private String isoCreated;

    /**
     * true if the iso is managed across all Zones, false otherwise
     */
    private String crossZones;

    /**
     * additional key/value details tied with iso
     */
    private String details;

    /**
     * the iso display text
     */
    private String displayText;

    /**
     * the name of the domain to which the iso belongs
     */
    private String domain;

    /**
     * the ID of the domain to which the iso belongs
     */
    private String domainId;

    /**
     * the format of the iso.
     */
    private String format;

    /**
     * the ID of the secondary storage host for the iso
     */
    private String hostId;

    /**
     * the name of the secondary storage host for the iso
     */
    private String hostName;

    /**
     * the hypervisor on which the iso runs
     */
    private String hypervisor;

    /**
     * true if template contains XS/VMWare tools
     */
    private String isDynamicallyScalable;

    /**
     * true if the iso is extractable, false otherwise
     */
    private String isExtractable;

    /**
     * true if this iso is a featured iso, false otherwise
     */
    private String isFeatured;

    /**
     * true if this iso is a public iso, false otherwise
     */
    private String isPublic;

    /**
     * true if the iso is ready to be deployed from, false otherwise.
     */
    private String isReady;

    /**
     * the template name
     */
    private String name;

    /**
     * the ID of the OS type for this iso.
     */
    private String osTypeId;

    /**
     * the name of the OS type for this iso.
     */
    private String osTypeName;

    /**
     * true if the reset password feature is enabled, false otherwise
     */
    private String passwordEnabled;

    /**
     * the project name of the iso
     */
    private String project;

    /**
     * the project id of the iso
     */
    private String projectId;

    /**
     * the date this iso was removed
     */
    private String removed;

    /**
     * the size of the iso
     */
    private String size;

    /**
     * the template ID of the parent template if present
     */
    private String sourceTemplateId;

    /**
     * true if template is sshkey enabled, false otherwise
     */
    private String sshKeyEnabled;

    /**
     * the status of the template
     */
    private String status;

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
    private String zoneId;

    /**
     * the name of the zone for this template
     */
    private String zoneName;

    /**
     * the list of resource tags associated with template
     */
    private List<TagsResponse> tagss;

    /**
     * the ID of the latest async job acting on this object
     */
    private String jobId;

    /**
     * the current status of the latest async job acting on this object
     */
    private String jobStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
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

    public String getIsoCreated() {
        return isoCreated;
    }

    public void setIsoCreated(String isoCreated) {
        this.isoCreated = isoCreated;
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

    public String getDisplayText() {
        return displayText;
    }

    public void setDisplayText(String displayText) {
        this.displayText = displayText;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
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

    public String getIsDynamicallyScalable() {
        return isDynamicallyScalable;
    }

    public void setIsDynamicallyScalable(String isDynamicallyScalable) {
        this.isDynamicallyScalable = isDynamicallyScalable;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getRemoved() {
        return removed;
    }

    public void setRemoved(String removed) {
        this.removed = removed;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSourceTemplateId() {
        return sourceTemplateId;
    }

    public void setSourceTemplateId(String sourceTemplateId) {
        this.sourceTemplateId = sourceTemplateId;
    }

    public String getSshKeyEnabled() {
        return sshKeyEnabled;
    }

    public void setSshKeyEnabled(String sshKeyEnabled) {
        this.sshKeyEnabled = sshKeyEnabled;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public List<TagsResponse> getTagss() {
        return tagss;
    }

    public void setTagss(List<TagsResponse> tagss) {
        this.tagss = tagss;
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
