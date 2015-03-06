package com.assistanz.cloud.cloudstack.autoscale;

/**
 *
 * @author Santhosh
 *
 */
public class AutoScaleVmProfileResponse {

    /**
     * the autoscale vm profile ID
     */
    private String id;

    /**
     * the account owning the instance group
     */
    private String account;

    /**
     * he ID of the user used to launch and destroy the VMs
     */
    private String autoScaleUserId;

    /**
     * the list of IDs of the time to get closed before a vm is destroyed
     */
    private String destroyVmGracePeriod;

    /**
     * the domain name of the vm profile
     */
    private String domain;

    /**
     * the domain id of the vm profile
     */
    private String domainId;

    /**
     * parameters other than zoneId/serviceOfferringId/templateId
     */
    private String otherDeployParams;

    /**
     * the project name of the vm profile
     */
    private String project;

    /**
     * the project id of the vm profile
     */
    private String projectId;

    /**
     * the service offering to be used while deploying a virtual machine
     */
    private String serviceOfferingId;

    /**
     * the template to be used while deploying a virtual machine
     */
    private String templateId;

    /**
     * the availability zone to be used while deploying a virtual machine
     */
    private String zoneId;

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

    public String getAutoScaleUserId() {
        return autoScaleUserId;
    }

    public void setAutoScaleUserId(String autoScaleUserId) {
        this.autoScaleUserId = autoScaleUserId;
    }

    public String getDestroyVmGracePeriod() {
        return destroyVmGracePeriod;
    }

    public void setDestroyVmGracePeriod(String destroyVmGracePeriod) {
        this.destroyVmGracePeriod = destroyVmGracePeriod;
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

    public String getOtherDeployParams() {
        return otherDeployParams;
    }

    public void setOtherDeployParams(String otherDeployParams) {
        this.otherDeployParams = otherDeployParams;
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

    public String getServiceOfferingId() {
        return serviceOfferingId;
    }

    public void setServiceOfferingId(String serviceOfferingId) {
        this.serviceOfferingId = serviceOfferingId;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

}
