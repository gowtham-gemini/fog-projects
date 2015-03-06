package com.assistanz.cloud.cloudstack.volume;

/**
 * 
 * @author Gowtham
 *
 */
public class MigrateVolumeResponse {
	
	/**
	 * ID of the disk volume
	 */
	private String diskVolumeId;
	
	/**
	 * the account associated with the disk volume
	 */
	private String diskVolumeAccount;
	
	/**
	 * the date the volume was attached to a VM instance
	 */
	private String diskVolumeAttachedDate;
	
	/**
	 * the date the disk volume was created
	 */
	private String diskVolumeCreatedDate;
	
	/**
	 * the boolean state of whether the volume is destroyed or not
	 */
	private String diskVolumeDestroyedState;
	
	/**
	 * the ID of the device on user vm the volume is attached to. 
	 * This tag is not returned when the volume is detached.
	 */
	private String diskVolumeDeviceId;
	
	/**
	 * the display text of the disk offering
	 */
	private String diskOfferingDisplayText;
	
	/**
	 * ID of the disk offering
	 */
	private String diskOfferingId;
	
	/**
	 * name of the disk offering
	 */
	private String diskOfferingName;
	
	/**
	 * the domain associated with the disk volume
	 */
	private String diskVolumeDomain;
	
	/**
	 * the ID of the domain associated with the disk volume
	 */
	private String diskVolumeDomainId;
	
	/**
	 * Hypervisor the volume belongs to
	 */
	private String volumeHypervisor;
	
	/**
	 * true if the volume is extractable, false otherwise
	 */
	private String diskVolumeIsExtractable;
	
	/**
	 * name of the disk volume
	 */
	private String diskVolumeName;
	
	/**
	 * the project name of the vpn
	 */
	private String diskVolumeProjectName;
	
	/**
	 * the project id of the vpn
	 */
	private String diskVolumeProjectId;
	
	/**
	 * the display text of the service offering for root disk
	 */
	private String serviceOfferingDisplayText;
	
	/**
	 * ID of the service offering for root disk
	 */
	private String serviceOfferingId;
	
	/**
	 * name of the service offering for root disk
	 */
	private String serviceOfferingName;
	
	/**
	 * size of the disk volume
	 */
	private String diskVolumeSize;
	
	/**
	 * ID of the snapshot from which this volume was created
	 */
	private String diskVolumeSnapShotId;
	
	/**
	 * the state of the disk volume
	 */
	private String diskVolumeState;
	
	/**
	 * name of the primary storage hosting the disk volume
	 */
	private String diskVolumeStorage;
	
	/**
	 * shared or local storage
	 */
	private String diskVolumeStorageType;
	
	/**
	 * type of the disk volume (ROOT or DATADISK)
	 */
	private String diskVolumeType;
	
	/**
	 * id of the virtual machine
	 */
	private String virtualMachineId;
	
	/**
	 * display name of the virtual machine
	 */
	private String virtualMachineDisplayName;
	
	/**
	 * name of the virtual machine
	 */
	private String virtualMachineName;
	
	/**
	 * state of the virtual machine
	 */
	private String virtualMachineState;	
	
	/**
	 * ID of the availability zone
	 */
	private String zoneId;
	
	/**
	 * name of the availability zone
	 */
	private String zoneName;
	
	/**
	 * the ID of the latest async job acting on this object
	 */
	private String jobId;
	
	/**
	 * the current status of the latest async job acting on this object
	 */
	private String jobStatus;

	public String getDiskVolumeId() {
		return diskVolumeId;
	}

	public void setDiskVolumeId(String diskVolumeId) {
		this.diskVolumeId = diskVolumeId;
	}

	public String getDiskVolumeAccount() {
		return diskVolumeAccount;
	}

	public void setDiskVolumeAccount(String diskVolumeAccount) {
		this.diskVolumeAccount = diskVolumeAccount;
	}

	public String getDiskVolumeAttachedDate() {
		return diskVolumeAttachedDate;
	}

	public void setDiskVolumeAttachedDate(String diskVolumeAttachedDate) {
		this.diskVolumeAttachedDate = diskVolumeAttachedDate;
	}

	public String getDiskVolumeCreatedDate() {
		return diskVolumeCreatedDate;
	}

	public void setDiskVolumeCreatedDate(String diskVolumeCreatedDate) {
		this.diskVolumeCreatedDate = diskVolumeCreatedDate;
	}

	public String getDiskVolumeDestroyedState() {
		return diskVolumeDestroyedState;
	}

	public void setDiskVolumeDestroyedState(String diskVolumeDestroyedState) {
		this.diskVolumeDestroyedState = diskVolumeDestroyedState;
	}

	public String getDiskVolumeDeviceId() {
		return diskVolumeDeviceId;
	}

	public void setDiskVolumeDeviceId(String diskVolumeDeviceId) {
		this.diskVolumeDeviceId = diskVolumeDeviceId;
	}

	public String getDiskOfferingDisplayText() {
		return diskOfferingDisplayText;
	}

	public void setDiskOfferingDisplayText(String diskOfferingDisplayText) {
		this.diskOfferingDisplayText = diskOfferingDisplayText;
	}

	public String getDiskOfferingId() {
		return diskOfferingId;
	}

	public void setDiskOfferingId(String diskOfferingId) {
		this.diskOfferingId = diskOfferingId;
	}

	public String getDiskOfferingName() {
		return diskOfferingName;
	}

	public void setDiskOfferingName(String diskOfferingName) {
		this.diskOfferingName = diskOfferingName;
	}

	public String getDiskVolumeDomain() {
		return diskVolumeDomain;
	}

	public void setDiskVolumeDomain(String diskVolumeDomain) {
		this.diskVolumeDomain = diskVolumeDomain;
	}

	public String getDiskVolumeDomainId() {
		return diskVolumeDomainId;
	}

	public void setDiskVolumeDomainId(String diskVolumeDomainId) {
		this.diskVolumeDomainId = diskVolumeDomainId;
	}

	public String getVolumeHypervisor() {
		return volumeHypervisor;
	}

	public void setVolumeHypervisor(String volumeHypervisor) {
		this.volumeHypervisor = volumeHypervisor;
	}

	public String getDiskVolumeIsExtractable() {
		return diskVolumeIsExtractable;
	}

	public void setDiskVolumeIsExtractable(String diskVolumeIsExtractable) {
		this.diskVolumeIsExtractable = diskVolumeIsExtractable;
	}

	public String getDiskVolumeName() {
		return diskVolumeName;
	}

	public void setDiskVolumeName(String diskVolumeName) {
		this.diskVolumeName = diskVolumeName;
	}

	public String getDiskVolumeProjectName() {
		return diskVolumeProjectName;
	}

	public void setDiskVolumeProjectName(String diskVolumeProjectName) {
		this.diskVolumeProjectName = diskVolumeProjectName;
	}

	public String getDiskVolumeProjectId() {
		return diskVolumeProjectId;
	}

	public void setDiskVolumeProjectId(String diskVolumeProjectId) {
		this.diskVolumeProjectId = diskVolumeProjectId;
	}

	public String getServiceOfferingDisplayText() {
		return serviceOfferingDisplayText;
	}

	public void setServiceOfferingDisplayText(String serviceOfferingDisplayText) {
		this.serviceOfferingDisplayText = serviceOfferingDisplayText;
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

	public String getDiskVolumeSize() {
		return diskVolumeSize;
	}

	public void setDiskVolumeSize(String diskVolumeSize) {
		this.diskVolumeSize = diskVolumeSize;
	}

	public String getDiskVolumeSnapShotId() {
		return diskVolumeSnapShotId;
	}

	public void setDiskVolumeSnapShotId(String diskVolumeSnapShotId) {
		this.diskVolumeSnapShotId = diskVolumeSnapShotId;
	}

	public String getDiskVolumeState() {
		return diskVolumeState;
	}

	public void setDiskVolumeState(String diskVolumeState) {
		this.diskVolumeState = diskVolumeState;
	}

	public String getDiskVolumeStorage() {
		return diskVolumeStorage;
	}

	public void setDiskVolumeStorage(String diskVolumeStorage) {
		this.diskVolumeStorage = diskVolumeStorage;
	}

	public String getDiskVolumeStorageType() {
		return diskVolumeStorageType;
	}

	public void setDiskVolumeStorageType(String diskVolumeStorageType) {
		this.diskVolumeStorageType = diskVolumeStorageType;
	}

	public String getDiskVolumeType() {
		return diskVolumeType;
	}

	public void setDiskVolumeType(String diskVolumeType) {
		this.diskVolumeType = diskVolumeType;
	}

	public String getVirtualMachineId() {
		return virtualMachineId;
	}

	public void setVirtualMachineId(String virtualMachineId) {
		this.virtualMachineId = virtualMachineId;
	}

	public String getVirtualMachineDisplayName() {
		return virtualMachineDisplayName;
	}

	public void setVirtualMachineDisplayName(String virtualMachineDisplayName) {
		this.virtualMachineDisplayName = virtualMachineDisplayName;
	}

	public String getVirtualMachineName() {
		return virtualMachineName;
	}

	public void setVirtualMachineName(String virtualMachineName) {
		this.virtualMachineName = virtualMachineName;
	}

	public String getVirtualMachineState() {
		return virtualMachineState;
	}

	public void setVirtualMachineState(String virtualMachineState) {
		this.virtualMachineState = virtualMachineState;
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
