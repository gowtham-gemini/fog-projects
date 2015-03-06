package com.assistanz.cloud.cloudstack.vpc;

import com.assistanz.cloud.cloudstack.TagsResponse;
import com.assistanz.cloud.cloudstack.NetworkResponse; 
import com.assistanz.cloud.cloudstack.CapabilityResponse;
import com.assistanz.cloud.cloudstack.ServiceResponse;
import com.assistanz.cloud.cloudstack.ProviderResponse;
import java.util.List;

/**
 *
 * @author developer
 */
public class CreateVpcResponse {

    /**
     * the id of the VPC
     */
    private String vpcId;

    /**
     * the owner of the VPC
     */
    private String accountName;

    /**
     * the cidr the VPC
     */
    private String cidr;

    /**
     * the date this VPC was created
     */
    private String created;

    /**
     * an alternate display text of the VPC
     */
    private String displayText;

    /**
     * the domain name of the owner
     */
    private String domainName;

    /**
     * the domain id of the VPC owner
     */
    private String domainId;

    /**
     * the name of the VPC
     */
    private String vpcName;

    /**
     * the network domain of the VPC
     */
    private String networkDomain;

    /**
     * the project name of the VPC
     */
    private String project;

    /**
     * the project id of the VPC
     */
    private String projectId;

    /**
     * true VPC requires restart
     */
    private String restartRequired;

    /**
     * state of the VPC, Can be Inactive/Enabled
     */
    private String state;

    /**
     * the ID of the domain in which the virtual machine exists
     */
    private String vpcOfferingId;

    /**
     * zone id of the vpc
     */
    private String zoneId;

    /**
     * the name of the zone the VPC belongs to
     */
    private String zoneName;
    
    private String jobId;

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    /**
     * the list of networks belonging to the VPC
     */
    private List<NetworkResponse> networks;

    /**
     * the list of supported services
     */
    private List<ServiceResponse> services;

    /**
     * the list of capabilities
     */
    private List<CapabilityResponse> capabilities;

    /**
     * the service provider name
     */
    private List<ProviderResponse> providers;

    /**
     * list of tags associated with the virtual machine
     */
    private List<TagsResponse> tagss;

    public String getVpcId() {
        return vpcId;
    }

    public void setVpcId(String vpcId) {
        this.vpcId = vpcId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getCidr() {
        return cidr;
    }

    public void setCidr(String cidr) {
        this.cidr = cidr;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getDisplayText() {
        return displayText;
    }

    public void setDisplayText(String displayText) {
        this.displayText = displayText;
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

    public String getVpcName() {
        return vpcName;
    }

    public void setVpcName(String vpcName) {
        this.vpcName = vpcName;
    }

    public String getNetworkDomain() {
        return networkDomain;
    }

    public void setNetworkDomain(String networkDomain) {
        this.networkDomain = networkDomain;
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

    public String getRestartRequired() {
        return restartRequired;
    }

    public void setRestartRequired(String restartRequired) {
        this.restartRequired = restartRequired;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getVpcOfferingId() {
        return vpcOfferingId;
    }

    public void setVpcOfferingId(String vpcOfferingId) {
        this.vpcOfferingId = vpcOfferingId;
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

    public List<NetworkResponse> getNetworks() {
        return networks;
    }

    public void setNetworks(List<NetworkResponse> networks) {
        this.networks = networks;
    }

    public List<ServiceResponse> getServices() {
        return services;
    }

    public void setServices(List<ServiceResponse> services) {
        this.services = services;
    }

    public List<CapabilityResponse> getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(List<CapabilityResponse> capabilities) {
        this.capabilities = capabilities;
    }

    public List<ProviderResponse> getProviders() {
        return providers;
    }

    public void setProviders(List<ProviderResponse> providers) {
        this.providers = providers;
    }

    public List<TagsResponse> getTagss() {
        return tagss;
    }

    public void setTagss(List<TagsResponse> tagss) {
        this.tagss = tagss;
    }

}
