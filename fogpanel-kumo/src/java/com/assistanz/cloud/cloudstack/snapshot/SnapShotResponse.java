
package com.assistanz.cloud.cloudstack.snapshot;

/**
 *
 * @author Gowtham
 */
class SnapShotResponse {
    
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
