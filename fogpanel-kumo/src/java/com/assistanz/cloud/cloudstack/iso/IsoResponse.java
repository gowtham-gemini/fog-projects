package com.assistanz.cloud.cloudstack.iso;

/**
 *
 * @author Gowtham
 */
class IsoResponse {
    
    /**
     * the iso ID
     */
    private String isoId;

    /**
     * the account name to which the iso belongs
     */
    private String accountName;

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
    private String isoDisplayText;

    /**
     * the name of the domain to which the iso belongs
     */
    private String domainName;

    /**
     * the ID of the domain to which the iso belongs
     */
    private String domainId;

    /**
     * the format of the iso.
     */
    private String isoFormat;

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
     * the iso name
     */
    private String isoName; 

    /**
     * the ID of the OS type for this iso.
     */
    private String osTypeId	;

    /**
     * the name of the OS type for this iso.
     */
    private String osTypeName;

    /**
     * true if the reset password feature is enabled, false otherwise
     */
    private String passwordEnabled;

    /**
     * 	the project name of the iso
     */
    private String isoProjectName;

    /**
     * the project id of the iso
     */
    private String isoProjectId;

    /**
     * 	the date this iso was removed
     */
    private String removed;

    /**
     * the size of the iso
     */
    private String isoSize;

    /**
     * the iso ID of the parent iso if present
     */
    private String sourceIsoId;

    /**
     * the status of the iso
     */
    private String isoStatus;

    /**
     * the tag of this iso
     */
    private String isoTag;	

    /**
     * the type of the iso
     */
    private String isoType;

    /**
     * the ID of the zone for this iso
     */
    private String isoZoneId;

    /**
     * the name of the zone for this iso
     */
    private String isoZoneName;

    /**
     * the ID of the latest async job acting on this object
     */
    private String jobId;

    /**
     * the current status of the latest async job acting on this object
     */
    private String jobStatus;

    public String getIsoId() {
        return isoId;
    }

    public void setIsoId(String isoId) {
        this.isoId = isoId;
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

    public String getIsoDisplayText() {
        return isoDisplayText;
    }

    public void setIsoDisplayText(String isoDisplayText) {
        this.isoDisplayText = isoDisplayText;
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

    public String getIsoFormat() {
        return isoFormat;
    }

    public void setIsoFormat(String isoFormat) {
        this.isoFormat = isoFormat;
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

    public String getIsoName() {
        return isoName;
    }

    public void setIsoName(String isoName) {
        this.isoName = isoName;
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

    public String getIsoProjectName() {
        return isoProjectName;
    }

    public void setIsoProjectName(String isoProjectName) {
        this.isoProjectName = isoProjectName;
    }

    public String getIsoProjectId() {
        return isoProjectId;
    }

    public void setIsoProjectId(String isoProjectId) {
        this.isoProjectId = isoProjectId;
    }

    public String getRemoved() {
        return removed;
    }

    public void setRemoved(String removed) {
        this.removed = removed;
    }

    public String getIsoSize() {
        return isoSize;
    }

    public void setIsoSize(String isoSize) {
        this.isoSize = isoSize;
    }

    public String getSourceIsoId() {
        return sourceIsoId;
    }

    public void setSourceIsoId(String sourceIsoId) {
        this.sourceIsoId = sourceIsoId;
    }

    public String getIsoStatus() {
        return isoStatus;
    }

    public void setIsoStatus(String isoStatus) {
        this.isoStatus = isoStatus;
    }

    public String getIsoTag() {
        return isoTag;
    }

    public void setIsoTag(String isoTag) {
        this.isoTag = isoTag;
    }

    public String getIsoType() {
        return isoType;
    }

    public void setIsoType(String isoType) {
        this.isoType = isoType;
    }

    public String getIsoZoneId() {
        return isoZoneId;
    }

    public void setIsoZoneId(String isoZoneId) {
        this.isoZoneId = isoZoneId;
    }

    public String getIsoZoneName() {
        return isoZoneName;
    }

    public void setIsoZoneName(String isoZoneName) {
        this.isoZoneName = isoZoneName;
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
