/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assistanz.cloud.cloudstack.volume;

/**
 *
 * @author nandhini
 */
public class ResizeVolumeResponse {
    
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

    /**
     * @return the diskVolumeId
     */
    public String getDiskVolumeId() {
        return diskVolumeId;
    }

    /**
     * @param diskVolumeId the diskVolumeId to set
     */
    public void setDiskVolumeId(String diskVolumeId) {
        this.diskVolumeId = diskVolumeId;
    }

    /**
     * @return the diskVolumeAccount
     */
    public String getDiskVolumeAccount() {
        return diskVolumeAccount;
    }

    /**
     * @param diskVolumeAccount the diskVolumeAccount to set
     */
    public void setDiskVolumeAccount(String diskVolumeAccount) {
        this.diskVolumeAccount = diskVolumeAccount;
    }

    /**
     * @return the diskVolumeAttachedDate
     */
    public String getDiskVolumeAttachedDate() {
        return diskVolumeAttachedDate;
    }

    /**
     * @param diskVolumeAttachedDate the diskVolumeAttachedDate to set
     */
    public void setDiskVolumeAttachedDate(String diskVolumeAttachedDate) {
        this.diskVolumeAttachedDate = diskVolumeAttachedDate;
    }

    /**
     * @return the diskVolumeCreatedDate
     */
    public String getDiskVolumeCreatedDate() {
        return diskVolumeCreatedDate;
    }

    /**
     * @param diskVolumeCreatedDate the diskVolumeCreatedDate to set
     */
    public void setDiskVolumeCreatedDate(String diskVolumeCreatedDate) {
        this.diskVolumeCreatedDate = diskVolumeCreatedDate;
    }

    /**
     * @return the diskVolumeDestroyedState
     */
    public String getDiskVolumeDestroyedState() {
        return diskVolumeDestroyedState;
    }

    /**
     * @param diskVolumeDestroyedState the diskVolumeDestroyedState to set
     */
    public void setDiskVolumeDestroyedState(String diskVolumeDestroyedState) {
        this.diskVolumeDestroyedState = diskVolumeDestroyedState;
    }

    /**
     * @return the diskVolumeDeviceId
     */
    public String getDiskVolumeDeviceId() {
        return diskVolumeDeviceId;
    }

    /**
     * @param diskVolumeDeviceId the diskVolumeDeviceId to set
     */
    public void setDiskVolumeDeviceId(String diskVolumeDeviceId) {
        this.diskVolumeDeviceId = diskVolumeDeviceId;
    }

    /**
     * @return the diskOfferingDisplayText
     */
    public String getDiskOfferingDisplayText() {
        return diskOfferingDisplayText;
    }

    /**
     * @param diskOfferingDisplayText the diskOfferingDisplayText to set
     */
    public void setDiskOfferingDisplayText(String diskOfferingDisplayText) {
        this.diskOfferingDisplayText = diskOfferingDisplayText;
    }

    /**
     * @return the diskOfferingId
     */
    public String getDiskOfferingId() {
        return diskOfferingId;
    }

    /**
     * @param diskOfferingId the diskOfferingId to set
     */
    public void setDiskOfferingId(String diskOfferingId) {
        this.diskOfferingId = diskOfferingId;
    }

    /**
     * @return the diskOfferingName
     */
    public String getDiskOfferingName() {
        return diskOfferingName;
    }

    /**
     * @param diskOfferingName the diskOfferingName to set
     */
    public void setDiskOfferingName(String diskOfferingName) {
        this.diskOfferingName = diskOfferingName;
    }

    /**
     * @return the diskVolumeDomain
     */
    public String getDiskVolumeDomain() {
        return diskVolumeDomain;
    }

    /**
     * @param diskVolumeDomain the diskVolumeDomain to set
     */
    public void setDiskVolumeDomain(String diskVolumeDomain) {
        this.diskVolumeDomain = diskVolumeDomain;
    }

    /**
     * @return the diskVolumeDomainId
     */
    public String getDiskVolumeDomainId() {
        return diskVolumeDomainId;
    }

    /**
     * @param diskVolumeDomainId the diskVolumeDomainId to set
     */
    public void setDiskVolumeDomainId(String diskVolumeDomainId) {
        this.diskVolumeDomainId = diskVolumeDomainId;
    }

    /**
     * @return the volumeHypervisor
     */
    public String getVolumeHypervisor() {
        return volumeHypervisor;
    }

    /**
     * @param volumeHypervisor the volumeHypervisor to set
     */
    public void setVolumeHypervisor(String volumeHypervisor) {
        this.volumeHypervisor = volumeHypervisor;
    }

    /**
     * @return the diskVolumeIsExtractable
     */
    public String getDiskVolumeIsExtractable() {
        return diskVolumeIsExtractable;
    }

    /**
     * @param diskVolumeIsExtractable the diskVolumeIsExtractable to set
     */
    public void setDiskVolumeIsExtractable(String diskVolumeIsExtractable) {
        this.diskVolumeIsExtractable = diskVolumeIsExtractable;
    }

    /**
     * @return the diskVolumeName
     */
    public String getDiskVolumeName() {
        return diskVolumeName;
    }

    /**
     * @param diskVolumeName the diskVolumeName to set
     */
    public void setDiskVolumeName(String diskVolumeName) {
        this.diskVolumeName = diskVolumeName;
    }

    /**
     * @return the diskVolumeProjectName
     */
    public String getDiskVolumeProjectName() {
        return diskVolumeProjectName;
    }

    /**
     * @param diskVolumeProjectName the diskVolumeProjectName to set
     */
    public void setDiskVolumeProjectName(String diskVolumeProjectName) {
        this.diskVolumeProjectName = diskVolumeProjectName;
    }

    /**
     * @return the diskVolumeProjectId
     */
    public String getDiskVolumeProjectId() {
        return diskVolumeProjectId;
    }

    /**
     * @param diskVolumeProjectId the diskVolumeProjectId to set
     */
    public void setDiskVolumeProjectId(String diskVolumeProjectId) {
        this.diskVolumeProjectId = diskVolumeProjectId;
    }

    /**
     * @return the serviceOfferingDisplayText
     */
    public String getServiceOfferingDisplayText() {
        return serviceOfferingDisplayText;
    }

    /**
     * @param serviceOfferingDisplayText the serviceOfferingDisplayText to set
     */
    public void setServiceOfferingDisplayText(String serviceOfferingDisplayText) {
        this.serviceOfferingDisplayText = serviceOfferingDisplayText;
    }

    /**
     * @return the serviceOfferingId
     */
    public String getServiceOfferingId() {
        return serviceOfferingId;
    }

    /**
     * @param serviceOfferingId the serviceOfferingId to set
     */
    public void setServiceOfferingId(String serviceOfferingId) {
        this.serviceOfferingId = serviceOfferingId;
    }

    /**
     * @return the serviceOfferingName
     */
    public String getServiceOfferingName() {
        return serviceOfferingName;
    }

    /**
     * @param serviceOfferingName the serviceOfferingName to set
     */
    public void setServiceOfferingName(String serviceOfferingName) {
        this.serviceOfferingName = serviceOfferingName;
    }

    /**
     * @return the diskVolumeSize
     */
    public String getDiskVolumeSize() {
        return diskVolumeSize;
    }

    /**
     * @param diskVolumeSize the diskVolumeSize to set
     */
    public void setDiskVolumeSize(String diskVolumeSize) {
        this.diskVolumeSize = diskVolumeSize;
    }

    /**
     * @return the diskVolumeSnapShotId
     */
    public String getDiskVolumeSnapShotId() {
        return diskVolumeSnapShotId;
    }

    /**
     * @param diskVolumeSnapShotId the diskVolumeSnapShotId to set
     */
    public void setDiskVolumeSnapShotId(String diskVolumeSnapShotId) {
        this.diskVolumeSnapShotId = diskVolumeSnapShotId;
    }

    /**
     * @return the diskVolumeState
     */
    public String getDiskVolumeState() {
        return diskVolumeState;
    }

    /**
     * @param diskVolumeState the diskVolumeState to set
     */
    public void setDiskVolumeState(String diskVolumeState) {
        this.diskVolumeState = diskVolumeState;
    }

    /**
     * @return the diskVolumeStorage
     */
    public String getDiskVolumeStorage() {
        return diskVolumeStorage;
    }

    /**
     * @param diskVolumeStorage the diskVolumeStorage to set
     */
    public void setDiskVolumeStorage(String diskVolumeStorage) {
        this.diskVolumeStorage = diskVolumeStorage;
    }

    /**
     * @return the diskVolumeStorageType
     */
    public String getDiskVolumeStorageType() {
        return diskVolumeStorageType;
    }

    /**
     * @param diskVolumeStorageType the diskVolumeStorageType to set
     */
    public void setDiskVolumeStorageType(String diskVolumeStorageType) {
        this.diskVolumeStorageType = diskVolumeStorageType;
    }

    /**
     * @return the diskVolumeType
     */
    public String getDiskVolumeType() {
        return diskVolumeType;
    }

    /**
     * @param diskVolumeType the diskVolumeType to set
     */
    public void setDiskVolumeType(String diskVolumeType) {
        this.diskVolumeType = diskVolumeType;
    }

    /**
     * @return the virtualMachineId
     */
    public String getVirtualMachineId() {
        return virtualMachineId;
    }

    /**
     * @param virtualMachineId the virtualMachineId to set
     */
    public void setVirtualMachineId(String virtualMachineId) {
        this.virtualMachineId = virtualMachineId;
    }

    /**
     * @return the virtualMachineDisplayName
     */
    public String getVirtualMachineDisplayName() {
        return virtualMachineDisplayName;
    }

    /**
     * @param virtualMachineDisplayName the virtualMachineDisplayName to set
     */
    public void setVirtualMachineDisplayName(String virtualMachineDisplayName) {
        this.virtualMachineDisplayName = virtualMachineDisplayName;
    }

    /**
     * @return the virtualMachineName
     */
    public String getVirtualMachineName() {
        return virtualMachineName;
    }

    /**
     * @param virtualMachineName the virtualMachineName to set
     */
    public void setVirtualMachineName(String virtualMachineName) {
        this.virtualMachineName = virtualMachineName;
    }

    /**
     * @return the virtualMachineState
     */
    public String getVirtualMachineState() {
        return virtualMachineState;
    }

    /**
     * @param virtualMachineState the virtualMachineState to set
     */
    public void setVirtualMachineState(String virtualMachineState) {
        this.virtualMachineState = virtualMachineState;
    }

    /**
     * @return the zoneId
     */
    public String getZoneId() {
        return zoneId;
    }

    /**
     * @param zoneId the zoneId to set
     */
    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    /**
     * @return the zoneName
     */
    public String getZoneName() {
        return zoneName;
    }

    /**
     * @param zoneName the zoneName to set
     */
    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    /**
     * @return the jobId
     */
    public String getJobId() {
        return jobId;
    }

    /**
     * @param jobId the jobId to set
     */
    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    /**
     * @return the jobStatus
     */
    public String getJobStatus() {
        return jobStatus;
    }

    /**
     * @param jobStatus the jobStatus to set
     */
    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }
    
}
