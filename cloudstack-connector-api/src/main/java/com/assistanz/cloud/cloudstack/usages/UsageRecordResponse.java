package com.assistanz.cloud.cloudstack.usages;

/**
 *
 * @author Gowtham
 */
class UsageRecordResponse {

    /**
     * the user account name
     */
    private String account;

    /**
     * the user account Id
     */
    private String accountId;

    /**
     * description of the usage record
     */
    private String description;

    /**
     * the domain the resource is associated with account
     */
    private String domain;

    /**
     * the domain ID
     */
    private String domainId;

    /**
     * end date of the usage record
     */
    private String endDate;

    /**
     * True if the resource is default
     */
    private String isDefault;

    /**
     * True if the IPAddress is source NAT
     */
    private String isSourceNat;

    /**
     * True if the IPAddress is system IP - allocated during vm deploy or lb rule create
     */
    private String isSystem;

    /**
     * virtual machine name
     */
    private String name;

    /**
     * id of the network
     */
    private String networkId;

    /**
     * offering ID
     */
    private String offeringId;

    /**
     * the project name of the resource
     */
    private String project;

    /**
     * the project id of the resource
     */
    private String projectId;

    /**
     * raw usage in hours
     */
    private String rawUsage;

    /**
     * resource size
     */
    private String size;

    /**
     * start date of the usage record
     */
    private String startDate;

    /**
     * template ID
     */
    private String templateId;

    /**
     * resource type
     */
    private String type;

    /**
     * usage in hours
     */
    private String usage;

    /**
     * id of the resource
     */
    private String usageId;

    /**
     * usage type ID
     */
    private String usageType;

    /**
     * virtual machine ID
     */
    private String virtualMachineId;
    
    /**
     * virtual size of resource
     */
    private String virtualSize;
    
    /**
     * the zone ID
     */
    private String zoneId;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    public String getIsSourceNat() {
        return isSourceNat;
    }

    public void setIsSourceNat(String isSourceNat) {
        this.isSourceNat = isSourceNat;
    }

    public String getIsSystem() {
        return isSystem;
    }

    public void setIsSystem(String isSystem) {
        this.isSystem = isSystem;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNetworkId() {
        return networkId;
    }

    public void setNetworkId(String networkId) {
        this.networkId = networkId;
    }

    public String getOfferingId() {
        return offeringId;
    }

    public void setOfferingId(String offeringId) {
        this.offeringId = offeringId;
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

    public String getRawUsage() {
        return rawUsage;
    }

    public void setRawUsage(String rawUsage) {
        this.rawUsage = rawUsage;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public String getUsageId() {
        return usageId;
    }

    public void setUsageId(String usageId) {
        this.usageId = usageId;
    }

    public String getUsageType() {
        return usageType;
    }

    public void setUsageType(String usageType) {
        this.usageType = usageType;
    }

    public String getVirtualMachineId() {
        return virtualMachineId;
    }

    public void setVirtualMachineId(String virtualMachineId) {
        this.virtualMachineId = virtualMachineId;
    }

    public String getVirtualSize() {
        return virtualSize;
    }

    public void setVirtualSize(String virtualSize) {
        this.virtualSize = virtualSize;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

}
