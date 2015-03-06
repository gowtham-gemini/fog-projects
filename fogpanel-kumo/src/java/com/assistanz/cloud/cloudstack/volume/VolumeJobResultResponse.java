package com.assistanz.cloud.cloudstack.volume;

/**
 *
 * @author Gowtham
 */
class VolumeJobResultResponse {
    
    /**
     * the account that executed the async command
     */
    String asychronousAccountId;

    /**
     * 	the async command executed
     */
    String asychronousCmd;

    /**
     * the created date of the job
     */
    String asychronousCreated;	

    /**
     * the unique ID of the instance/entity object related to the job
     */
    String asychronousJobInstanceId;

    /**
     * 	the instance/entity object related to the job
     */
    String asychronousJobInstanceType;

    /**
     * the progress information of the PENDING job
     */
    String asychronousJobProgressStatus;

    /**
     * the result reason
     */
    String asychronousJobResult;

    /**
     * the result code for the job
     */
    String asychronousJobResultCode;

    /**
     * the result type
     */
    String asychronousJobResultType;

    /**
     * the current job status-should be 0 for PENDING
     */
    String asychronousJobStatus;

    /**
     * the user that executed the async command
     */
    String asychronousUserId;

    /**
     * the ID of the async job
     */
    String asychronousJobId;

    public String getAsychronousAccountId() {
            return asychronousAccountId;
    }

    public void setAsychronousAccountId(String asychronousAccountId) {
            this.asychronousAccountId = asychronousAccountId;
    }

    public String getAsychronousCmd() {
            return asychronousCmd;
    }

    public void setAsychronousCmd(String asychronousCmd) {
            this.asychronousCmd = asychronousCmd;
    }

    public String getAsychronousCreated() {
            return asychronousCreated;
    }

    public void setAsychronousCreated(String asychronousCreated) {
            this.asychronousCreated = asychronousCreated;
    }

    public String getAsychronousJobInstanceId() {
            return asychronousJobInstanceId;
    }

    public void setAsychronousJobInstanceId(String asychronousJobInstanceId) {
            this.asychronousJobInstanceId = asychronousJobInstanceId;
    }

    public String getAsychronousJobInstanceType() {
            return asychronousJobInstanceType;
    }

    public void setAsychronousJobInstanceType(String asychronousJobInstanceType) {
            this.asychronousJobInstanceType = asychronousJobInstanceType;
    }

    public String getAsychronousJobProgressStatus() {
            return asychronousJobProgressStatus;
    }

    public void setAsychronousJobProgressStatus(String asychronousJobProgressStatus) {
            this.asychronousJobProgressStatus = asychronousJobProgressStatus;
    }

    public String getAsychronousJobResult() {
            return asychronousJobResult;
    }

    public void setAsychronousJobResult(String asychronousJobResult) {
            this.asychronousJobResult = asychronousJobResult;
    }

    public String getAsychronousJobResultCode() {
            return asychronousJobResultCode;
    }

    public void setAsychronousJobResultCode(String asychronousJobResultCode) {
            this.asychronousJobResultCode = asychronousJobResultCode;
    }

    public String getAsychronousJobResultType() {
            return asychronousJobResultType;
    }

    public void setAsychronousJobResultType(String asychronousJobResultType) {
            this.asychronousJobResultType = asychronousJobResultType;
    }

    public String getAsychronousJobStatus() {
            return asychronousJobStatus;
    }

    public void setAsychronousJobStatus(String asychronousJobStatus) {
            this.asychronousJobStatus = asychronousJobStatus;
    }

    public String getAsychronousUserId() {
            return asychronousUserId;
    }

    public void setAsychronousUserId(String asychronousUserId) {
            this.asychronousUserId = asychronousUserId;
    }

    public String getAsychronousJobId() {
            return asychronousJobId;
    }

    public void setAsychronousJobId(String asychronousJobId) {
            this.asychronousJobId = asychronousJobId;
    }	
    
    //volume
    
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
     * the status of the disk volume
     */
    private String diskVolumeStatus;

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
     * the current job status-should be 0 for PENDING
     */
    private String jobStatus;
    
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
    
    public String getDiskVolumeId() {
            return diskVolumeId;
    }

    public String getDiskVolumeStatus() {
        return diskVolumeStatus;
    }

    public void setDiskVolumeStatus(String diskVolumeStatus) {
        this.diskVolumeStatus = diskVolumeStatus;
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
