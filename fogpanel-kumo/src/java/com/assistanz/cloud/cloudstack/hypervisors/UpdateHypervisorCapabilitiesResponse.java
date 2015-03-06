package com.assistanz.cloud.cloudstack.hypervisors;

/**
 * 
 * @author Gowtham
 *
 */
public class UpdateHypervisorCapabilitiesResponse {
	
	/**
	 * the id of the service offering
	 */
	private String serviceOfferingId;
	
	/**
	 * the number of CPU needed
	 */
	private String cpuNumber;
	
	/**
	 * the clock  rate of CPU speed in MHz
	 */
	private String cpuSpeed	;
	
	/**
	 * the date this service offering was created
	 */
	private String created;
	
	/**
	 * is this a default system Virtual Machine offering
	 */
	private String defaultUse;
	
	/**
	 * an alternate display text of the service offering.
	 */
	private String displayText;
	
	/**
	 * Domain name for the Service Offering
	 */
	private String serviceOfferingDomain;
	
	/**
	 * the domain id of the service offering
	 */
	private String serviceOfferingDomainid;
	
	/**
	 * the host tag for the service offering
	 */
	private String serviceOfferingHostTags;
	
	/**
	 * is this a system vm offering
	 */
	private String isSystem;
	
	/**
	 * restrict the CPU usage to committed service offering
	 */
	private String limitCpuUse;
	
	/**
	 * the memory in MB
	 */
	private String memory;

	/**
	 * the name of the service offering
	 */
	private String serviceOfferingName;
	
	/**
	 * data transfer rate in megabits per second allowed.
	 */
	private String serviceOfferingNetworkRate;	
	
	/**
	 * the ha support in the service offering
	 */
	private String serviceOfferingHa;
	
	/**
	 * the storage type for this service offering
	 */
	private String storageType;
	
	/**
	 * is this a the system Virtual Machine type for system vm offering
	 */
	private String systemVirtualMachineType;
	
	/**
	 * the tags for the service offering
	 */
	private String serviceOfferingTags;

	public String getServiceOfferingId() {
		return serviceOfferingId;
	}

	public void setServiceOfferingId(String serviceOfferingId) {
		this.serviceOfferingId = serviceOfferingId;
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

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getDefaultUse() {
		return defaultUse;
	}

	public void setDefaultUse(String defaultUse) {
		this.defaultUse = defaultUse;
	}

	public String getDisplayText() {
		return displayText;
	}

	public void setDisplayText(String displayText) {
		this.displayText = displayText;
	}

	public String getServiceOfferingDomain() {
		return serviceOfferingDomain;
	}

	public void setServiceOfferingDomain(String serviceOfferingDomain) {
		this.serviceOfferingDomain = serviceOfferingDomain;
	}

	public String getServiceOfferingDomainid() {
		return serviceOfferingDomainid;
	}

	public void setServiceOfferingDomainid(String serviceOfferingDomainid) {
		this.serviceOfferingDomainid = serviceOfferingDomainid;
	}

	public String getServiceOfferingHostTags() {
		return serviceOfferingHostTags;
	}

	public void setServiceOfferingHostTags(String serviceOfferingHostTags) {
		this.serviceOfferingHostTags = serviceOfferingHostTags;
	}

	public String getIsSystem() {
		return isSystem;
	}

	public void setIsSystem(String isSystem) {
		this.isSystem = isSystem;
	}

	public String getLimitCpuUse() {
		return limitCpuUse;
	}

	public void setLimitCpuUse(String limitCpuUse) {
		this.limitCpuUse = limitCpuUse;
	}

	public String getMemory() {
		return memory;
	}

	public void setMemory(String memory) {
		this.memory = memory;
	}

	public String getServiceOfferingName() {
		return serviceOfferingName;
	}

	public void setServiceOfferingName(String serviceOfferingName) {
		this.serviceOfferingName = serviceOfferingName;
	}

	public String getServiceOfferingNetworkRate() {
		return serviceOfferingNetworkRate;
	}

	public void setServiceOfferingNetworkRate(String serviceOfferingNetworkRate) {
		this.serviceOfferingNetworkRate = serviceOfferingNetworkRate;
	}

	public String getServiceOfferingHa() {
		return serviceOfferingHa;
	}

	public void setServiceOfferingHa(String serviceOfferingHa) {
		this.serviceOfferingHa = serviceOfferingHa;
	}

	public String getStorageType() {
		return storageType;
	}

	public void setStorageType(String storageType) {
		this.storageType = storageType;
	}

	public String getSystemVirtualMachineType() {
		return systemVirtualMachineType;
	}

	public void setSystemVirtualMachineType(String systemVirtualMachineType) {
		this.systemVirtualMachineType = systemVirtualMachineType;
	}

	public String getServiceOfferingTags() {
		return serviceOfferingTags;
	}

	public void setServiceOfferingTags(String serviceOfferingTags) {
		this.serviceOfferingTags = serviceOfferingTags;
	}	

}
