package com.assistanz.cloud.cloudstack.volume;

/**
 * 
 * @author Gowtham
 *
 */
public class ListVolumesOnFilerResponse {
	
	/**
	 * volume id
	 */
	private String volumeId;
	
	/**
	 * Aggregate name
	 */
	private String aggregateName;
	
	/**
	 * ip address
	 */
	private String ipAddress;
	
	/**
	 * pool name
	 */
	private String poolName;
	
	/**
	 * volume size
	 */
	private String volumeSize;
	
	/**
	 * snapshot policy
	 */
	private String snapShotPolicy;
	
	/**
	 * snapshot reservation
	 */
	private String snapShotReservation;
	
	/**
	 * Volume name
	 */
	private String volumeName;

	public String getVolumeId() {
		return volumeId;
	}

	public void setVolumeId(String volumeId) {
		this.volumeId = volumeId;
	}

	public String getAggregateName() {
		return aggregateName;
	}

	public void setAggregateName(String aggregateName) {
		this.aggregateName = aggregateName;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getPoolName() {
		return poolName;
	}

	public void setPoolName(String poolName) {
		this.poolName = poolName;
	}

	public String getVolumeSize() {
		return volumeSize;
	}

	public void setVolumeSize(String volumeSize) {
		this.volumeSize = volumeSize;
	}

	public String getSnapShotPolicy() {
		return snapShotPolicy;
	}

	public void setSnapShotPolicy(String snapShotPolicy) {
		this.snapShotPolicy = snapShotPolicy;
	}

	public String getSnapShotReservation() {
		return snapShotReservation;
	}

	public void setSnapShotReservation(String snapShotReservation) {
		this.snapShotReservation = snapShotReservation;
	}

	public String getVolumeName() {
		return volumeName;
	}

	public void setVolumeName(String volumeName) {
		this.volumeName = volumeName;
	}	
	
	

}
