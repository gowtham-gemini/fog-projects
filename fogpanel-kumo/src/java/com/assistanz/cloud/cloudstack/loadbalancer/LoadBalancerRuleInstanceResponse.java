/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.assistanz.cloud.cloudstack.loadbalancer;

import com.assistanz.cloud.cloudstack.EgressRuleResponse;
import com.assistanz.cloud.cloudstack.IngressRuleResponse;
import com.assistanz.cloud.cloudstack.NetworkInterfaceCardResponse;
import com.assistanz.cloud.cloudstack.SecurityGroupResponse;
import java.util.List;

/**
 *
 * @author gowtham
 */
class LoadBalancerRuleInstanceResponse {
    
        /**
	 * the ID of the virtual machine
	 */
	private String virtualMachineId;
	
	/**
	 * the account associated with the virtual machine
	 */
	private String accountName;
	
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
	 * user generated name. 
	 * The name of the virtual machine is returned if no display name exists.
	 * 
	 */
	private String displayName;
	
	/**
	 * the name of the domain in which the virtual machine exists
	 */
	private String domainName;
	
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
	private String highEnable;
	
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
	 * the memory allocated for the virtual machine
	 */
	private String memory;
	
	/**
	 * the name of the virtual machine
	 */
	private String virtualMachinename;	
	
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
	private String projectName;
	
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
	private String publicipId;
	
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
	 * the state of the virtual machine
	 */
	private String virtualMachinestate;
	
	/**
	 * an alternate display text of the template for the virtual machine
	 */
	private String templateDisplayText;
	
	/**
	 * the ID of the template for the virtual machine. 
	 * A -1 is returned if the virtual machine was created from an ISO file.
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
	 * 
	 */
	private List<IngressRuleResponse> ingressRules;
	
	/**
	 * the ID of the latest async job acting on this object
	 */
	private String jobId;

	/**
	 * the current status of the latest async job acting on this object
	 */
	private String jobStatus;	
			
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
			
	public List<IngressRuleResponse> getIngressRules() {
		return ingressRules;
	}

	public void setIngressRules(List<IngressRuleResponse> ingressRules) {
		this.ingressRules = ingressRules;
	}

	public List<EgressRuleResponse> getEgressRules() {
		return egressRules;
	}

	public void setEgressRules(List<EgressRuleResponse> egressRules) {
		this.egressRules = egressRules;
	}

	public List<SecurityGroupResponse> getSecurityGroups() {
		return securityGroups;
	}

	public void setSecurityGroups(List<SecurityGroupResponse> securityGroups) {
		this.securityGroups = securityGroups;
	}

	public String getVirtualMachineId() {
		return virtualMachineId;
	}

	public void setVirtualMachineId(String virtualMachineId) {
		this.virtualMachineId = virtualMachineId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
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

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
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

	public String getHighEnable() {
		return highEnable;
	}

	public void setHighEnable(String highEnable) {
		this.highEnable = highEnable;
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

	public String getMemory() {
		return memory;
	}

	public void setMemory(String memory) {
		this.memory = memory;
	}

	public String getVirtualMachinename() {
		return virtualMachinename;
	}

	public void setVirtualMachinename(String virtualMachinename) {
		this.virtualMachinename = virtualMachinename;
	}

	public String getNetworkKbbsRead() {
		return networkKbbsRead;
	}

	public void setNetworkKbbsRead(String networkKbbsRead) {
		this.networkKbbsRead = networkKbbsRead;
	}

	public String getNetworkKbbsWrite() {
		return networkKbbsWrite;
	}

	public void setNetworkKbbsWrite(String networkKbbsWrite) {
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

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
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

	public String getPublicipId() {
		return publicipId;
	}

	public void setPublicipId(String publicipId) {
		this.publicipId = publicipId;
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

	public String getVirtualMachinestate() {
		return virtualMachinestate;
	}

	public void setVirtualMachinestate(String virtualMachinestate) {
		this.virtualMachinestate = virtualMachinestate;
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

	public List<NetworkInterfaceCardResponse> getNetworkInterfaceCards() {
		return networkInterfaceCards;
	}

	public void setNetworkInterfaceCards(
			List<NetworkInterfaceCardResponse> networkInterfaceCards) {
		this.networkInterfaceCards = networkInterfaceCards;
	}
}
