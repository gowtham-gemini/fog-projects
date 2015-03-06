/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.assistanz.cloud.cloudstack.snapshot;

/**
 *
 * @author gowtham
 */
class VMSnapshotJobResultResponse {
    
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
     * ID of the virtual machine
     */
    String virtualMachineId;

    /**
     * ID of the zone
     */
    String zoneId;

     /**
     * ID of the VMSnapshot
     */
    String VMSnapshotId;

    /**
     * ID of the VMSnapshot description
     */
    String VMSnapshotDescription;

    /**
     * ID of the VMSnapshot display name
     */
    String VMSnapshotDisplayName;

    /**
     * the account associated with the VMSnapshot
     */
    String VMSnapshotAccount;	

    /**
     * the date the VMSnapshot was created
     */
    String VMSnapshotCreated;	

    /**
     * the domain name of the VMSnapshot's account
     */
    String VMSnapshotDomain;

    /**
     * the domain ID of the VMSnapshot's account
     */
    String VMSnapshotDomainId;

    /**
     * name of the VMSnapshot
     */
    String VMSnapshotName;

    /**
     * the project name of the VMSnapshot
     */
    String VMSnapshotProjectName;	

    /**
     * the project id of the VMSnapshot
     */
    String VMSnapshotProjectId;

    /**
     * the project name of the VMSnapshot parent
     */
    String VMSnapshotParentName;	

    /**
     * the project id of the VMSnapshot parent
     */
    String VMSnapshotParentId;

    /**
     * is this VMSnapshot is current or not
     */
    String VMSnapshotCurrent;

    /**
     * the type of the VMSnapshot
     */
    String VMSnapshotType;

    /**
     * the state of the VMSnapshot. 
     * BackedUp means that VMSnapshot is ready to be used; 
     * Creating - the VMSnapshot is being allocated on the primary storage; 
     * BackingUp - the VMSnapshot is being backed up on secondary storage
     */
    String VMSnapshotState;

    /**
     * the ID of the latest async job acting on this object
     */
    String jobid;	

    /**
     * the current status of the latest async job acting on this object
     */
    String jobStatus;

    public String getVirtualMachineId() {
        return virtualMachineId;
    }

    public void setVirtualMachineId(String virtualMachineId) {
        this.virtualMachineId = virtualMachineId;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    public String getVMSnapshotId() {
        return VMSnapshotId;
    }

    public void setVMSnapshotId(String VMSnapshotId) {
        this.VMSnapshotId = VMSnapshotId;
    }

    public String getVMSnapshotDescription() {
        return VMSnapshotDescription;
    }

    public void setVMSnapshotDescription(String VMSnapshotDescription) {
        this.VMSnapshotDescription = VMSnapshotDescription;
    }

    public String getVMSnapshotDisplayName() {
        return VMSnapshotDisplayName;
    }

    public void setVMSnapshotDisplayName(String VMSnapshotDisplayName) {
        this.VMSnapshotDisplayName = VMSnapshotDisplayName;
    }

    public String getVMSnapshotAccount() {
        return VMSnapshotAccount;
    }

    public void setVMSnapshotAccount(String VMSnapshotAccount) {
        this.VMSnapshotAccount = VMSnapshotAccount;
    }

    public String getVMSnapshotCreated() {
        return VMSnapshotCreated;
    }

    public void setVMSnapshotCreated(String VMSnapshotCreated) {
        this.VMSnapshotCreated = VMSnapshotCreated;
    }

    public String getVMSnapshotDomain() {
        return VMSnapshotDomain;
    }

    public void setVMSnapshotDomain(String VMSnapshotDomain) {
        this.VMSnapshotDomain = VMSnapshotDomain;
    }

    public String getVMSnapshotDomainId() {
        return VMSnapshotDomainId;
    }

    public void setVMSnapshotDomainId(String VMSnapshotDomainId) {
        this.VMSnapshotDomainId = VMSnapshotDomainId;
    }

    public String getVMSnapshotName() {
        return VMSnapshotName;
    }

    public void setVMSnapshotName(String VMSnapshotName) {
        this.VMSnapshotName = VMSnapshotName;
    }

    public String getVMSnapshotProjectName() {
        return VMSnapshotProjectName;
    }

    public void setVMSnapshotProjectName(String VMSnapshotProjectName) {
        this.VMSnapshotProjectName = VMSnapshotProjectName;
    }

    public String getVMSnapshotProjectId() {
        return VMSnapshotProjectId;
    }

    public void setVMSnapshotProjectId(String VMSnapshotProjectId) {
        this.VMSnapshotProjectId = VMSnapshotProjectId;
    }

    public String getVMSnapshotParentName() {
        return VMSnapshotParentName;
    }

    public void setVMSnapshotParentName(String VMSnapshotParentName) {
        this.VMSnapshotParentName = VMSnapshotParentName;
    }

    public String getVMSnapshotParentId() {
        return VMSnapshotParentId;
    }

    public void setVMSnapshotParentId(String VMSnapshotParentId) {
        this.VMSnapshotParentId = VMSnapshotParentId;
    }

    public String getVMSnapshotCurrent() {
        return VMSnapshotCurrent;
    }

    public void setVMSnapshotCurrent(String VMSnapshotCurrent) {
        this.VMSnapshotCurrent = VMSnapshotCurrent;
    }

    public String getVMSnapshotType() {
        return VMSnapshotType;
    }

    public void setVMSnapshotType(String VMSnapshotType) {
        this.VMSnapshotType = VMSnapshotType;
    }

    public String getVMSnapshotState() {
        return VMSnapshotState;
    }

    public void setVMSnapshotState(String VMSnapshotState) {
        this.VMSnapshotState = VMSnapshotState;
    }

    public String getJobid() {
        return jobid;
    }

    public void setJobid(String jobid) {
        this.jobid = jobid;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }
    
}
