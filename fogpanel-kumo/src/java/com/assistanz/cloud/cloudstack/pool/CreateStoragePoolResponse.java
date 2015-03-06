package com.assistanz.cloud.cloudstack.pool;

/**
 * 
 * @author Gowtham
 *
 */
public class CreateStoragePoolResponse {
	
	/**
	 * the ID of the storage pool
	 */
	String storagePoolId;
	
	/**
	 * the ID of the cluster for the storage pool
	 */
	String clusterId;
	
	/**
	 * the name of the cluster for the storage pool
	 */
	String clusterName;	
	
	/**
	 * the date and time the storage pool was created
	 */
	String created;	
	
	/**
	 * the host's currently allocated disk size
	 */
	String diskSizeAllocated;
	
	/**
	 * the total disk size of the storage pool
	 */
	String diskSizeTotal;
	
	/**
	 * the host's currently used disk size
	 */
	String diskSizeUsed;
	
	/**
	 * the IP address of the storage pool
	 */
	String storagePoolIpAddress;
	
	/**
	 * the name of the storage pool
	 */
	String storagePoolName;
	
	/**
	 * the storage pool path
	 */
	String storagePoolPath;
	
	/**
	 * the Pod ID of the storage pool
	 */
	String podId;
	
	/**
	 * the Pod name of the storage pool
	 */
	String podName;
	
	/**
	 * the state of the storage pool
	 */
	String storagePoolState;
	
	/**
	 * the tags for the storage pool
	 */
	String storagePoolTags;
	
	/**
	 * the storage pool type
	 */
	String storagePoolType;
	
	/**
	 * the Zone ID of the storage pool
	 */
	String storagePoolZoneId;
	
	/**
	 * the Zone name of the storage pool
	 */
	String storagePoolZoneName;
	
	/**
	 * the ID of the latest async job acting on this object
	 */
	String jobId;
	
	/**
	 * the current status of the latest async job acting on this object

	 */
	String jobStatus;

	public String getStoragePoolId() {
		return storagePoolId;
	}

	public void setStoragePoolId(String storagePoolId) {
		this.storagePoolId = storagePoolId;
	}

	public String getClusterId() {
		return clusterId;
	}

	public void setClusterId(String clusterId) {
		this.clusterId = clusterId;
	}

	public String getClusterName() {
		return clusterName;
	}

	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getDiskSizeAllocated() {
		return diskSizeAllocated;
	}

	public void setDiskSizeAllocated(String diskSizeAllocated) {
		this.diskSizeAllocated = diskSizeAllocated;
	}

	public String getDiskSizeTotal() {
		return diskSizeTotal;
	}

	public void setDiskSizeTotal(String diskSizeTotal) {
		this.diskSizeTotal = diskSizeTotal;
	}

	public String getDiskSizeUsed() {
		return diskSizeUsed;
	}

	public void setDiskSizeUsed(String diskSizeUsed) {
		this.diskSizeUsed = diskSizeUsed;
	}

	public String getStoragePoolIpAddress() {
		return storagePoolIpAddress;
	}

	public void setStoragePoolIpAddress(String storagePoolIpAddress) {
		this.storagePoolIpAddress = storagePoolIpAddress;
	}

	public String getStoragePoolName() {
		return storagePoolName;
	}

	public void setStoragePoolName(String storagePoolName) {
		this.storagePoolName = storagePoolName;
	}

	public String getStoragePoolPath() {
		return storagePoolPath;
	}

	public void setStoragePoolPath(String storagePoolPath) {
		this.storagePoolPath = storagePoolPath;
	}

	public String getPodId() {
		return podId;
	}

	public void setPodId(String podId) {
		this.podId = podId;
	}

	public String getPodName() {
		return podName;
	}

	public void setPodName(String podName) {
		this.podName = podName;
	}

	public String getStoragePoolState() {
		return storagePoolState;
	}

	public void setStoragePoolState(String storagePoolState) {
		this.storagePoolState = storagePoolState;
	}

	public String getStoragePoolTags() {
		return storagePoolTags;
	}

	public void setStoragePoolTags(String storagePoolTags) {
		this.storagePoolTags = storagePoolTags;
	}

	public String getStoragePoolType() {
		return storagePoolType;
	}

	public void setStoragePoolType(String storagePoolType) {
		this.storagePoolType = storagePoolType;
	}

	public String getStoragePoolZoneId() {
		return storagePoolZoneId;
	}

	public void setStoragePoolZoneId(String storagePoolZoneId) {
		this.storagePoolZoneId = storagePoolZoneId;
	}

	public String getStoragePoolZoneName() {
		return storagePoolZoneName;
	}

	public void setStoragePoolZoneName(String storagePoolZoneName) {
		this.storagePoolZoneName = storagePoolZoneName;
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
