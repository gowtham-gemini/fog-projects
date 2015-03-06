package com.assistanz.cloud.cloudstack.snapshot;

/**
 * 
 * @author Gowtham
 *
 */
public class CreateSnapshotPolicyResponse {
	
	/**
	 * the ID of the snapshot Policy
	 */
	String snapshotPolicyId;	
	
	/**
	 * the interval type of the snapshot policy
	 */
	String snapshotPolicyIntervalType;
	
	/**
	 * maximum number of snapshots retained
	 */
	String maxsnaps;
	
	/**
	 * time the snapshot is scheduled to be taken.
	 */
	String snapshotSchedule;
	
	/**
	 * the time zone of the snapshot policy
	 */
	String snapshotPolicyTimeZone;
	
	/**
	 * the ID of the disk volume
	 */
	String diskVolumeId;

	
	public String getSnapshotPolicyId() {
		return snapshotPolicyId;
	}

	public void setSnapshotPolicyId(String snapshotPolicyId) {
		this.snapshotPolicyId = snapshotPolicyId;
	}

	public String getSnapshotPolicyIntervalType() {
		return snapshotPolicyIntervalType;
	}

	public void setSnapshotPolicyIntervalType(String snapshotPolicyIntervalType) {
		this.snapshotPolicyIntervalType = snapshotPolicyIntervalType;
	}

	public String getMaxsnaps() {
		return maxsnaps;
	}

	public void setMaxsnaps(String maxsnaps) {
		this.maxsnaps = maxsnaps;
	}

	public String getSnapshotSchedule() {
		return snapshotSchedule;
	}

	public void setSnapshotSchedule(String snapshotSchedule) {
		this.snapshotSchedule = snapshotSchedule;
	}

	public String getSnapshotPolicyTimeZone() {
		return snapshotPolicyTimeZone;
	}

	public void setSnapshotPolicyTimeZone(String snapshotPolicyTimeZone) {
		this.snapshotPolicyTimeZone = snapshotPolicyTimeZone;
	}

	public String getDiskVolumeId() {
		return diskVolumeId;
	}

	public void setDiskVolumeId(String diskVolumeId) {
		this.diskVolumeId = diskVolumeId;
	}
	
}
