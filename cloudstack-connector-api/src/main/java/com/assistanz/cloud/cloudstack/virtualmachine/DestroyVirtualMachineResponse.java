package com.assistanz.cloud.cloudstack.virtualmachine;

import com.assistanz.cloud.cloudstack.AffinityGroupResponse;
import java.util.List;

import com.assistanz.cloud.cloudstack.EgressRuleResponse;
import com.assistanz.cloud.cloudstack.IngressRuleResponse;
import com.assistanz.cloud.cloudstack.NetworkInterfaceCardResponse;
import com.assistanz.cloud.cloudstack.SecurityGroupResponse;
import com.assistanz.cloud.cloudstack.TagsResponse;

/**
 *
 * @author Gowtham
 *
 */
public class DestroyVirtualMachineResponse {

    /**
     * the ID of the virtual machine
     */
    private String id;

    /**
     * the account associated with the virtual machine
     */
    private String account;

    /**
     * the number of cpu this virtual machine is running with
     */
    private String cpuNumber;

    /**
     * the speed of each cpu
     */
    private String cpuSpeed;

    /**
     * the amount of the vm's CPU currently used
     */
    private String cpuUsed;

    /**
     * the date when this virtual machine was created
     */
    private String created;

    /**
     * the read (io) of disk on the vm
     */
    private String diskIoRead;

    /**
     * the write (io) of disk on the vm
     */
    private String diskIoWrite;

    /**
     * the read (bytes) of disk on the vm
     */
    private String diskKbsRead;

    /**
     * the write (bytes) of disk on the vm
     */
    private String diskKbsWrite;

    /**
     * user generated name. The name of the virtual machine is returned if no display name exists.
     *
     */
    private String displayName;

    /**
     * an optional field whether to the display the vm to the end user or not
     */
    private String displayVm;

    /**
     * the name of the domain in which the virtual machine exists
     */
    private String domain;

    /**
     * the ID of the domain in which the virtual machine exists
     */
    private String domainId;

    /**
     * the virtual network for the service offering
     */
    private String forVirtualNetwork;

    /**
     * the group name of the virtual machine
     */
    private String group;

    /**
     * the group ID of the virtual machine
     */
    private String groupId;

    /**
     * Os type ID of the virtual machine
     */
    private String guestOsId;

    /**
     * true if high-availability is enabled, false otherwise
     */
    private String haEnable;

    /**
     * the ID of the host for the virtual machine
     */
    private String hostId;

    /**
     * the name of the host for the virtual machine
     */
    private String hostName;

    /**
     * the hyper visor on which the template runs
     */
    private String hyperVisor;

    /**
     * instance name of the user vm; this parameter is returned to the ROOT admin only
     */
    private String instanceName;

    /**
     * true if vm contains XS/VMWare tools inorder to support dynamic scaling of VM cpu/memory
     */
    private String isDynamicallyScalable;

    /**
     * an alternate display text of the ISO attached to the virtual machine
     */
    private String isoDisplayText;

    /**
     * the ID of the ISO attached to the virtual machine
     */
    private String isoId;

    /**
     * the name of the ISO attached to the virtual machine
     */
    private String isoName;

    /**
     * the ssh key-pair of the virtual machine
     */
    private String keyPair;

    /**
     * the memory allocated for the virtual machine
     */
    private String memory;

    /**
     * the name of the virtual machine
     */
    private String name;

    /**
     * the incoming network traffic on the virtual machine
     */
    private String networkKbbsRead;

    /**
     * the outgoing network traffic on the host
     */
    private String networkKbbsWrite;

    /**
     * the password (if exists) of the virtual machine
     */
    private String password;

    /**
     * true if the password rest feature is enabled, false otherwise
     */
    private String passwordEnabled;

    /**
     * the project name of the virtual machine
     */
    private String project;

    /**
     * the project id of the virtual machine
     */
    private String projectId;

    /**
     * public IP address id associated with virtual machine via Static nat rule
     */
    private String publicIp;

    /**
     * public IP address id associated with virtual machine via Static nat rule
     */
    private String publicIpId;

    /**
     * device ID of the root volume
     */
    private String rootDeviceId;

    /**
     * device type of the root volume
     */
    private String rootDeviceType;

    /**
     * the ID of the service offering of the virtual machine
     */
    private String serviceOfferingId;

    /**
     * the name of the service offering of the virtual machine
     */
    private String serviceOfferingName;

    /**
     * State of the Service from LB rule
     */
    private String serviceState;

    /**
     * the state of the virtual machine
     */
    private String state;

    /**
     * an alternate display text of the template for the virtual machine
     */
    private String templateDisplayText;

    /**
     * the ID of the template for the virtual machine, A -1 is returned if the virtual machine was created from an ISO
     * file
     *
     */
    private String templateId;

    /**
     * the name of the template for the virtual machine
     */
    private String templateName;

    /**
     * the ID of the availablility zone for the virtual machine
     */
    private String zoneId;

    /**
     * the name of the availability zone for the virtual machine
     */
    private String zoneName;

    /**
     * the list of AffinityGroups associated with virtual machine
     */
    private List<AffinityGroupResponse> affinityGroups;

    /**
     * the list of NetworkInterfaceCards associated with virtual machine
     */
    private List<NetworkInterfaceCardResponse> networkInterfaceCards;

    /**
     * list of security groups associated with the virtual machine
     */
    private List<SecurityGroupResponse> securityGroups;

    /**
     * the list of egress rules associated with the security group
     */
    private List<EgressRuleResponse> egressRules;

    /**
     * the list of ingress Rules associated with the security group
     */
    private List<IngressRuleResponse> ingressRules;

    /**
     * list of tags associated with the virtual machine
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

    public String getCpuNumber() {
        return cpuNumber;
    }

    public void setCpuNumber(String cpuNumber) {
        this.cpuNumber = cpuNumber;
    }

    public String getCpuSpeed() {
        return cpuSpeed;
    }

    public void setCpuSpeed(String cpuSpeed) {
        this.cpuSpeed = cpuSpeed;
    }

    public String getCpuUsed() {
        return cpuUsed;
    }

    public void setCpuUsed(String cpuUsed) {
        this.cpuUsed = cpuUsed;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getDiskIoRead() {
        return diskIoRead;
    }

    public void setDiskIoRead(String diskIoRead) {
        this.diskIoRead = diskIoRead;
    }

    public String getDiskIoWrite() {
        return diskIoWrite;
    }

    public void setDiskIoWrite(String diskIoWrite) {
        this.diskIoWrite = diskIoWrite;
    }

    public String getDiskKbsRead() {
        return diskKbsRead;
    }

    public void setDiskKbsRead(String diskKbsRead) {
        this.diskKbsRead = diskKbsRead;
    }

    public String getDiskKbsWrite() {
        return diskKbsWrite;
    }

    public void setDiskKbsWrite(String diskKbsWrite) {
        this.diskKbsWrite = diskKbsWrite;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayVm() {
        return displayVm;
    }

    public void setDisplayVm(String displayVm) {
        this.displayVm = displayVm;
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

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGuestOsId() {
        return guestOsId;
    }

    public void setGuestOsId(String guestOsId) {
        this.guestOsId = guestOsId;
    }

    public String getHaEnable() {
        return haEnable;
    }

    public void setHaEnable(String haEnable) {
        this.haEnable = haEnable;
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

    public String getHyperVisor() {
        return hyperVisor;
    }

    public void setHyperVisor(String hyperVisor) {
        this.hyperVisor = hyperVisor;
    }

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    public String getIsDynamicallyScalable() {
        return isDynamicallyScalable;
    }

    public void setIsDynamicallyScalable(String isDynamicallyScalable) {
        this.isDynamicallyScalable = isDynamicallyScalable;
    }

    public String getIsoDisplayText() {
        return isoDisplayText;
    }

    public void setIsoDisplayText(String isoDisplayText) {
        this.isoDisplayText = isoDisplayText;
    }

    public String getIsoId() {
        return isoId;
    }

    public void setIsoId(String isoId) {
        this.isoId = isoId;
    }

    public String getIsoName() {
        return isoName;
    }

    public void setIsoName(String isoName) {
        this.isoName = isoName;
    }

    public String getKeyPair() {
        return keyPair;
    }

    public void setKeyPair(String keyPair) {
        this.keyPair = keyPair;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNetworkKbsRead() {
        return networkKbbsRead;
    }

    public void setNetworkKbsRead(String networkKbbsRead) {
        this.networkKbbsRead = networkKbbsRead;
    }

    public String getNetworkKbsWrite() {
        return networkKbbsWrite;
    }

    public void setNetworkKbsWrite(String networkKbbsWrite) {
        this.networkKbbsWrite = networkKbbsWrite;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getPublicIp() {
        return publicIp;
    }

    public void setPublicIp(String publicIp) {
        this.publicIp = publicIp;
    }

    public String getPublicIpId() {
        return publicIpId;
    }

    public void setPublicIpId(String publicIpId) {
        this.publicIpId = publicIpId;
    }

    public String getRootDeviceId() {
        return rootDeviceId;
    }

    public void setRootDeviceId(String rootDeviceId) {
        this.rootDeviceId = rootDeviceId;
    }

    public String getRootDeviceType() {
        return rootDeviceType;
    }

    public void setRootDeviceType(String rootDeviceType) {
        this.rootDeviceType = rootDeviceType;
    }

    public String getServiceOfferingId() {
        return serviceOfferingId;
    }

    public void setServiceOfferingId(String serviceOfferingId) {
        this.serviceOfferingId = serviceOfferingId;
    }

    public String getServiceOfferingName() {
        return serviceOfferingName;
    }

    public void setServiceOfferingName(String serviceOfferingName) {
        this.serviceOfferingName = serviceOfferingName;
    }

    public String getServiceState() {
        return serviceState;
    }

    public void setServiceState(String serviceState) {
        this.serviceState = serviceState;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTemplateDisplayText() {
        return templateDisplayText;
    }

    public void setTemplateDisplayText(String templateDisplayText) {
        this.templateDisplayText = templateDisplayText;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
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

    public List<AffinityGroupResponse> getAffinityGroups() {
        return affinityGroups;
    }

    public void setAffinityGroups(List<AffinityGroupResponse> affinityGroups) {
        this.affinityGroups = affinityGroups;
    }

    public List<NetworkInterfaceCardResponse> getNetworkInterfaceCards() {
        return networkInterfaceCards;
    }

    public void setNetworkInterfaceCards(List<NetworkInterfaceCardResponse> networkInterfaceCards) {
        this.networkInterfaceCards = networkInterfaceCards;
    }

    public List<SecurityGroupResponse> getSecurityGroups() {
        return securityGroups;
    }

    public void setSecurityGroups(List<SecurityGroupResponse> securityGroups) {
        this.securityGroups = securityGroups;
    }

    public List<EgressRuleResponse> getEgressRules() {
        return egressRules;
    }

    public void setEgressRules(List<EgressRuleResponse> egressRules) {
        this.egressRules = egressRules;
    }

    public List<IngressRuleResponse> getIngressRules() {
        return ingressRules;
    }

    public void setIngressRules(List<IngressRuleResponse> ingressRules) {
        this.ingressRules = ingressRules;
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