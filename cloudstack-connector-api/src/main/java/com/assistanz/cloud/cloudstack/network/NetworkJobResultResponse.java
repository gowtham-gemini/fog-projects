package com.assistanz.cloud.cloudstack.network;

import com.assistanz.cloud.cloudstack.TagsResponse;
import java.util.List;

/**
 *
 * @author gowtham
 */
class NetworkJobResultResponse {

    /**
     * the account that executed the async command
     */
    private String asynchronousAccountId;

    /**
     * the async command executed
     */
    private String asynchronousCmd;

    /**
     * the created date of the job
     */
    private String asynchronousCreated;

    /**
     * the unique ID of the instance/entity object related to the job
     */
    private String asynchronousJobInstanceId;

    /**
     * the instance/entity object related to the job
     */
    private String asynchronousJobInstanceType;

    /**
     * the progress information of the PENDING job
     */
    private String asynchronousJobProgressStatus;

    /**
     * the result reason
     */
    private String asynchronousJobResult;

    /**
     * the result code for the job
     */
    private String asynchronousJobResultCode;

    /**
     * the result type
     */
    private String asynchronousJobResultType;

    /**
     * the current job status-should be 0 for PENDING
     */
    private String asynchronousJobStatus;

    /**
     * the user that executed the async command
     */
    private String asynchronousUserId;

    /**
     * the ID of the async job
     */
    private String asynchronousJobId;

    public String getAsynchronousAccountId() {
        return asynchronousAccountId;
    }

    public void setAsynchronousAccountId(String asynchronousAccountId) {
        this.asynchronousAccountId = asynchronousAccountId;
    }

    public String getAsynchronousCmd() {
        return asynchronousCmd;
    }

    public void setAsynchronousCmd(String asynchronousCmd) {
        this.asynchronousCmd = asynchronousCmd;
    }

    public String getAsynchronousCreated() {
        return asynchronousCreated;
    }

    public void setAsynchronousCreated(String asynchronousCreated) {
        this.asynchronousCreated = asynchronousCreated;
    }

    public String getAsynchronousJobInstanceId() {
        return asynchronousJobInstanceId;
    }

    public void setAsynchronousJobInstanceId(String asynchronousJobInstanceId) {
        this.asynchronousJobInstanceId = asynchronousJobInstanceId;
    }

    public String getAsynchronousJobInstanceType() {
        return asynchronousJobInstanceType;
    }

    public void setAsynchronousJobInstanceType(String asynchronousJobInstanceType) {
        this.asynchronousJobInstanceType = asynchronousJobInstanceType;
    }

    public String getAsynchronousJobProgressStatus() {
        return asynchronousJobProgressStatus;
    }

    public void setAsynchronousJobProgressStatus(String asynchronousJobProgressStatus) {
        this.asynchronousJobProgressStatus = asynchronousJobProgressStatus;
    }

    public String getAsynchronousJobResult() {
        return asynchronousJobResult;
    }

    public void setAsynchronousJobResult(String asynchronousJobResult) {
        this.asynchronousJobResult = asynchronousJobResult;
    }

    public String getAsynchronousJobResultCode() {
        return asynchronousJobResultCode;
    }

    public void setAsynchronousJobResultCode(String asynchronousJobResultCode) {
        this.asynchronousJobResultCode = asynchronousJobResultCode;
    }

    public String getAsynchronousJobResultType() {
        return asynchronousJobResultType;
    }

    public void setAsynchronousJobResultType(String asynchronousJobResultType) {
        this.asynchronousJobResultType = asynchronousJobResultType;
    }

    public String getAsynchronousJobStatus() {
        return asynchronousJobStatus;
    }

    public void setAsynchronousJobStatus(String asynchronousJobStatus) {
        this.asynchronousJobStatus = asynchronousJobStatus;
    }

    public String getAsynchronousUserId() {
        return asynchronousUserId;
    }

    public void setAsynchronousUserId(String asynchronousUserId) {
        this.asynchronousUserId = asynchronousUserId;
    }

    public String getAsynchronousJobId() {
        return asynchronousJobId;
    }

    public void setAsynchronousJobId(String asynchronousJobId) {
        this.asynchronousJobId = asynchronousJobId;
    }
    
    /**
     * the current error Code of the latest async job acting on this object
     */
    private String errorCode;
    /**
     * the current error Text of the latest async job acting on this object
     */
    private String errorText;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorText() {
        return errorText;
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }

    /**
     * public IP address id
     */
    private String id;

    /**
     * the account the public IP address is associated with
     */
    private String account;

    /**
     * date the public IP address was acquired
     */
    private String allocated;

    /**
     * date the public IP address was acquired
     */
    private String associated;

    /**
     * the ID of the Network associated with the IP address
     */
    private String associatedNetworkId;

    /**
     * the name of the Network associated with the IP address
     */
    private String associatedNetworkName;

    /**
     * the domain the public IP address is associated with
     */
    private String domain;

    /**
     * the domain ID the public IP address is associated with
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
     * is public IP portable across the zones
     */
    private String isPortable;

    /**
     * true if the IP address is a source nat address, false otherwise
     */
    private String isSourceNat;

    /**
     * true if this ip is for static nat, false otherwise
     */
    private String isStaticNat;

    /**
     * true if this ip is system ip (was allocated as a part of deployVm or createLbRule)
     */
    private String isSystem;

    /**
     * the ID of the Network where ip belongs to
     */
    private String networkId;

    /**
     * the physical network this belongs to
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
     * purpose of the IP address
     */
    private String purpose;
    
    /**
     * state of the IP address
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
     * virtual machine (dnat) ip address
     */
    private String vmIpAddress;
    
    /**
     * VPC the ip belongs to
     */
    private String vpcId;
    
    /**
     * the ID of the zone the public IP address belongs to
     */
    private String zoneId;
    
    /**
     * the name of the zone the public IP address belongs to
     */
    private String zoneName;
    
    /**
     * the list of resource tags associated with ip address
     */
    private List<TagsResponse> tagss;

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

    public String getAssociated() {
        return associated;
    }

    public void setAssociated(String associated) {
        this.associated = associated;
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

    public List<TagsResponse> getTagss() {
        return tagss;
    }

    public void setTagss(List<TagsResponse> tagss) {
        this.tagss = tagss;
    }

}
