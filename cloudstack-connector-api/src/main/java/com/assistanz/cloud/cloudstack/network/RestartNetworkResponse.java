package com.assistanz.cloud.cloudstack.network;

import java.util.List;

import com.assistanz.cloud.cloudstack.CapabilityResponse;
import com.assistanz.cloud.cloudstack.ServiceResponse;
import com.assistanz.cloud.cloudstack.TagsResponse;

/**
 *
 * @author Santhosh
 *
 */
public class RestartNetworkResponse {

    /**
     * the id of public IP address
     */
    private String id;

    /**
     * owner of the network
     */
    private String account;

    /**
     * date the public IP address was acquired
     */
    private String allocated;

    /**
     * the ID of the Network associated with the IP address
     */
    private String associatedNetworkId;

    /**
     * the name of the Network associated with the IP address
     */
    private String associatedNetworkName;

    /**
     * the domain name of the network owner
     */
    private String domain;

    /**
     * the domain id of the network owner
     */
    private String domainId;

    /**
     * the virtual network for the IP address
     */
    private String forVirtualNetwork;

    /**
     * public IP address
     */
    private String ipAddress;

    /**
     * public IP portable across the zones
     */
    private String isPortable;

    /**
     * the IP address is a source nat address, false otherwise
     */
    private String isSourceNat;

    /**
     * true if this ip is for static nat, false otherwise
     */
    private String isStaticNat;

    /**
     * true if network is system, false otherwise
     */
    private String isSystem;

    /**
     * the ID of the Network where ip belongs to
     */
    private String networkId;

    /**
     * the physical network id
     */
    private String physicalNetworkId;

    /**
     * the project name of the address
     */
    private String project;

    /**
     * the project id of the ipaddress
     */
    private String projectId;

    /**
     * the purpose of the IP address
     */
    private String purpose;

    /**
     * state of the network
     */
    private String state;

    /**
     * virtual machine display name the ip address is assigned to
     */
    private String virtualMachineDisplayName;

    /**
     * virtual machine id the ip address is assigned to
     */
    private String virtualMachineId;

    /**
     * virtual machine name the ip address is assigned to
     */
    private String virtualMachineName;

    /**
     * the ID of the VLAN associated with the IP address
     */
    private String vlanId;

    /**
     * the VLAN associated with the IP address
     */
    private String vlanName;

    /**
     * the virtual machine IP address
     */
    private String vmIpAddress;

    /**
     * VPC the IP belongs to
     */
    private String vpcId;

    /**
     * zone id of the network
     */
    private String zoneId;

    /**
     * the name of the zone the network belongs to
     */
    private String zoneName;

    /**
     * the list of supported services
     */
    private List<ServiceResponse> services;

    /**
     * the list of capabilities
     */
    private List<CapabilityResponse> capabilities;

    /**
     * list of tags associated with IP address
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

    public String getAllocated() {
        return allocated;
    }

    public void setAllocated(String allocated) {
        this.allocated = allocated;
    }

    public String getAssociatedNetworkId() {
        return associatedNetworkId;
    }

    public void setAssociatedNetworkId(String associatedNetworkId) {
        this.associatedNetworkId = associatedNetworkId;
    }

    public String getAssociatedNetworkName() {
        return associatedNetworkName;
    }

    public void setAssociatedNetworkName(String associatedNetworkName) {
        this.associatedNetworkName = associatedNetworkName;
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

    public String getForVirtualNetwork() {
        return forVirtualNetwork;
    }

    public void setForVirtualNetwork(String forVirtualNetwork) {
        this.forVirtualNetwork = forVirtualNetwork;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getIsPortable() {
        return isPortable;
    }

    public void setIsPortable(String isPortable) {
        this.isPortable = isPortable;
    }

    public String getIsSourceNat() {
        return isSourceNat;
    }

    public void setIsSourceNat(String isSourceNat) {
        this.isSourceNat = isSourceNat;
    }

    public String getIsStaticNat() {
        return isStaticNat;
    }

    public void setIsStaticNat(String isStaticNat) {
        this.isStaticNat = isStaticNat;
    }

    public String getIsSystem() {
        return isSystem;
    }

    public void setIsSystem(String isSystem) {
        this.isSystem = isSystem;
    }

    public String getNetworkId() {
        return networkId;
    }

    public void setNetworkId(String networkId) {
        this.networkId = networkId;
    }

    public String getPhysicalNetworkId() {
        return physicalNetworkId;
    }

    public void setPhysicalNetworkId(String physicalNetworkId) {
        this.physicalNetworkId = physicalNetworkId;
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

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getVirtualMachineDisplayName() {
        return virtualMachineDisplayName;
    }

    public void setVirtualMachineDisplayName(String virtualMachineDisplayName) {
        this.virtualMachineDisplayName = virtualMachineDisplayName;
    }

    public String getVirtualMachineId() {
        return virtualMachineId;
    }

    public void setVirtualMachineId(String virtualMachineId) {
        this.virtualMachineId = virtualMachineId;
    }

    public String getVirtualMachineName() {
        return virtualMachineName;
    }

    public void setVirtualMachineName(String virtualMachineName) {
        this.virtualMachineName = virtualMachineName;
    }

    public String getVlanId() {
        return vlanId;
    }

    public void setVlanId(String vlanId) {
        this.vlanId = vlanId;
    }

    public String getVlanName() {
        return vlanName;
    }

    public void setVlanName(String vlanName) {
        this.vlanName = vlanName;
    }

    public String getVmIpAddress() {
        return vmIpAddress;
    }

    public void setVmIpAddress(String vmIpAddress) {
        this.vmIpAddress = vmIpAddress;
    }

    public String getVpcId() {
        return vpcId;
    }

    public void setVpcId(String vpcId) {
        this.vpcId = vpcId;
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
