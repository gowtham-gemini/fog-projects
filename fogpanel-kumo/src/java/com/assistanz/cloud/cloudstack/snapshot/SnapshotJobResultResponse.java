/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assistanz.cloud.cloudstack.snapshot;

/**
 *
 * @author Az-PHP
 */
class SnapshotJobResultResponse {
    
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
    
    
    //snapshot
    
    /**
     * ID of the snapshot
     */
    String snapshotId;	

    /**
     * the account associated with the snapshot
     */
    String snapshotAccount;	

    /**
     * the date the snapshot was created
     */
    String snapshotCreated;	

    /**
     * the domain name of the snapshot's account
     */
    String snapshotDomain;

    /**
     * the domain ID of the snapshot's account
     */
    String snapshotDomainId;

    /**
     * valid types are hourly, daily, weekly, monthy, template, and none.
     */
    String intervalType;

    /**
     * name of the snapshot
     */
    String snapshotName;

    /**
     * the project name of the snapshot
     */
    String snapshotProjectName;	

    /**
     * the project id of the snapshot
     */
    String snapshotProjectId;

    /**
     * the type of the snapshot
     */
    String snapshotType;

    /**
     * the state of the snapshot. 
     * BackedUp means that snapshot is ready to be used; 
     * Creating - the snapshot is being allocated on the primary storage; 
     * BackingUp - the snapshot is being backed up on secondary storage
     */
    String snapshotState;

    /**
     * ID of the disk volume
     */
    String diskVolumeId;

    /**
     * name of the disk volume
     */
    String diskVolumeName;

    /**
     * type of the disk volume
     */
    String diskVolumeType;

    /**
     * the ID of the latest async job acting on this object
     */
    String jobid;	

    /**
     * the current status of the latest async job acting on this object
     */
    String jobStatus;

    public String getSnapshotId() {
            return snapshotId;
    }

    public void setSnapshotId(String snapshotId) {
            this.snapshotId = snapshotId;
    }

    public String getSnapshotAccount() {
            return snapshotAccount;
    }

    public void setSnapshotAccount(String snapshotAccount) {
            this.snapshotAccount = snapshotAccount;
    }

    public String getSnapshotCreated() {
            return snapshotCreated;
    }

    public void setSnapshotCreated(String snapshotCreated) {
            this.snapshotCreated = snapshotCreated;
    }

    public String getSnapshotDomain() {
            return snapshotDomain;
    }

    public void setSnapshotDomain(String snapshotDomain) {
            this.snapshotDomain = snapshotDomain;
    }

    public String getSnapshotDomainId() {
            return snapshotDomainId;
    }

    public void setSnapshotDomainId(String snapshotDomainId) {
            this.snapshotDomainId = snapshotDomainId;
    }

    public String getIntervalType() {
            return intervalType;
    }

    public void setIntervalType(String intervalType) {
            this.intervalType = intervalType;
    }

    public String getSnapshotName() {
            return snapshotName;
    }

    public void setSnapshotName(String snapshotName) {
            this.snapshotName = snapshotName;
    }

    public String getSnapshotProjectName() {
            return snapshotProjectName;
    }

    public void setSnapshotProjectName(String snapshotProjectName) {
            this.snapshotProjectName = snapshotProjectName;
    }

    public String getSnapshotProjectId() {
            return snapshotProjectId;
    }

    public void setSnapshotProjectId(String snapshotProjectId) {
            this.snapshotProjectId = snapshotProjectId;
    }

    public String getSnapshotType() {
            return snapshotType;
    }

    public void setSnapshotType(String snapshotType) {
            this.snapshotType = snapshotType;
    }

    public String getSnapshotState() {
            return snapshotState;
    }

    public void setSnapshotState(String snapshotState) {
            this.snapshotState = snapshotState;
    }

    public String getDiskVolumeId() {
            return diskVolumeId;
    }

    public void setDiskVolumeId(String diskVolumeId) {
            this.diskVolumeId = diskVolumeId;
    }

    public String getDiskVolumeName() {
            return diskVolumeName;
    }

    public void setDiskVolumeName(String diskVolumeName) {
            this.diskVolumeName = diskVolumeName;
    }

    public String getDiskVolumeType() {
            return diskVolumeType;
    }

    public void setDiskVolumeType(String diskVolumeType) {
            this.diskVolumeType = diskVolumeType;
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
