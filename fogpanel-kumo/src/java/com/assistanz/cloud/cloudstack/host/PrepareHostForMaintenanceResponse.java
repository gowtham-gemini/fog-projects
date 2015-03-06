package com.assistanz.cloud.cloudstack.host;

/**
 * 
 * @author Gowtham
 *
 */
public class PrepareHostForMaintenanceResponse {
	
	/**
	 * the ID of the host
	 */
	private String hostId;
	
	/**
	 * the cpu average load on the host
	 */
	private String hostAverageLoad;	
	
	/**
	 * capabilities of the host
	 */
	private String hostCapabilities;
	
	/**
	 * the cluster ID of the host
	 */
	private String hostClusterId;
	
	/**
	 * the cluster name of the host
	 */
	private String HostClusterName;

	/**
	 * the cluster type of the cluster that host belongs to
	 */
	private String hostClusterType;
	
	/**
	 * the amount of the host's CPU currently allocated
	 */
	private String hostCpuAllocated;
	
	/**
	 * the CPU number of the host
	 */
	private String hostCpuNumber;
	
	/**
	 * the CPU speed of the host
	 */
	private String hostCpuSpeed;
	
	/**
	 * the amount of the host's CPU currently used
	 */
	private String hostCpuUsed;	
	
	/**
	 * the amount of the host's CPU after applying the cpu.overprovisioning.factor
	 */
	private String hostCpuWithOverProvisioning;	
	
	/**
	 * the date and time the host was created
	 */
	private String hostCreatedDate;	
	
	/**
	 * true if the host is disconnected. False otherwise.
	 */
	private String hostDisconnected;
	
	/**
	 * the host's currently allocated disk size
	 */
	private String hostDiskSizeAllocated;
	
	/**
	 * the total disk size of the host
	 */
	private String hostDiskSizeTotal;
	
	/**
	 * events available for the host
	 */
	private String hostEvents;	
	
	/**
	 * true if this host has enough CPU and RAM capacity to migrate a VM to it, false otherwise
	 */
	private String hasEnoughCapacity;
	
	/**
	 * comma-separated list of tags for the host
	 */
	private String hostTags;
	
	/**
	 * the host hypervisor
	 */
	private String hostHypervisor;
	
	/**
	 * the hypervisor version
	 */
	private String hypervisorVersion;
	
	/**
	 * the IP address of the host
	 */
	private String hostIpAddress;
	
	/**
	 * true if local storage is active, false otherwise
	 */
	private String isLocalStorageActive;
	
	/**
	 * the date and time the host was last pinged
	 */
	private String hostLastPinged;
	
	/**
	 * the management server ID of the host
	 */
	private String hostManagementServerId;
	
	/**
	 * the amount of the host's memory currently allocated
	 */
	private String hostMemoryAllocated;	
	
	/**
	 * the memory total of the host
	 */
	private String hostMemoryTotal;	
	
	/**
	 * the amount of the host's memory currently used
	 */
	private String hostMemoryUsed;
	
	/**
	 * the name of the host
	 */
	private String hostName;
	
	/**
	 * the incoming network traffic on the host
	 */
	private String hostNetworKbbsRead;
	
	/**
	 * the outgoing network traffic on the host
	 */
	private String hostNetworKbbsWrite;	
	
	/**
	 * the OS category ID of the host
	 */
	private String hostOsCategoryId;
	
	/**
	 * the OS category name of the host
	 */
	private String hostOsCategoryName;	
	
	/**
	 * the Pod ID of the host
	 */
	private String hostPodId;
	
	/**
	 * the Pod name of the host
	 */
	private String hostPodName;	
	
	/**
	 * the date and time the host was removed
	 */
	private String hostRemovedDate;
	
	/**
	 * the resource state of the host
	 */
	private String hostResourceState;
	
	/**
	 * the state of the host
	 */
	private String hostSate;	
	
	/**
	 * true if this host is suitable(has enough capacity and satisfies all conditions like hosttags, max guests vm limit etc) to migrate a VM to it , false otherwise
	 */
	private String suitableForMigration;
	
	/**
	 * the host type
	 */
	private String hostType;
	
	/**
	 * the host version
	 */
	private String hostVersion;
	
	/**
	 * the Zone ID of the host
	 */
	private String hostZoneId;
	
	/**
	 * the Zone name of the host
	 */
	private String hostZoneName;
	
	/**
	 * the ID of the latest async job acting on this object
	 */
	private String jobId;
	
	/**
	 * the current status of the latest async job acting on this object
	 */
	private String jobStatus;

	public String getHostId() {
		return hostId;
	}

	public void setHostId(String hostId) {
		this.hostId = hostId;
	}

	public String getHostAverageLoad() {
		return hostAverageLoad;
	}

	public void setHostAverageLoad(String hostAverageLoad) {
		this.hostAverageLoad = hostAverageLoad;
	}

	public String getHostCapabilities() {
		return hostCapabilities;
	}

	public void setHostCapabilities(String hostCapabilities) {
		this.hostCapabilities = hostCapabilities;
	}

	public String getHostClusterId() {
		return hostClusterId;
	}

	public void setHostClusterId(String hostClusterId) {
		this.hostClusterId = hostClusterId;
	}
	
	public String getHostClusterName() {
		return HostClusterName;
	}

	public void setHostClusterName(String hostClusterName) {
		HostClusterName = hostClusterName;
	}

	public String getHostClusterType() {
		return hostClusterType;
	}

	public void setHostClusterType(String hostClusterType) {
		this.hostClusterType = hostClusterType;
	}

	public String getHostCpuAllocated() {
		return hostCpuAllocated;
	}

	public void setHostCpuAllocated(String hostCpuAllocated) {
		this.hostCpuAllocated = hostCpuAllocated;
	}

	public String getHostCpuNumber() {
		return hostCpuNumber;
	}

	public void setHostCpuNumber(String hostCpuNumber) {
		this.hostCpuNumber = hostCpuNumber;
	}

	public String getHostCpuSpeed() {
		return hostCpuSpeed;
	}

	public void setHostCpuSpeed(String hostCpuSpeed) {
		this.hostCpuSpeed = hostCpuSpeed;
	}

	public String getHostCpuUsed() {
		return hostCpuUsed;
	}

	public void setHostCpuUsed(String hostCpuUsed) {
		this.hostCpuUsed = hostCpuUsed;
	}

	public String getHostCpuWithOverProvisioning() {
		return hostCpuWithOverProvisioning;
	}

	public void setHostCpuWithOverProvisioning(String hostCpuWithOverProvisioning) {
		this.hostCpuWithOverProvisioning = hostCpuWithOverProvisioning;
	}

	public String getHostCreatedDate() {
		return hostCreatedDate;
	}

	public void setHostCreatedDate(String hostCreatedDate) {
		this.hostCreatedDate = hostCreatedDate;
	}

	public String getHostDisconnected() {
		return hostDisconnected;
	}

	public void setHostDisconnected(String hostDisconnected) {
		this.hostDisconnected = hostDisconnected;
	}

	public String getHostDiskSizeAllocated() {
		return hostDiskSizeAllocated;
	}

	public void setHostDiskSizeAllocated(String hostDiskSizeAllocated) {
		this.hostDiskSizeAllocated = hostDiskSizeAllocated;
	}

	public String getHostDiskSizeTotal() {
		return hostDiskSizeTotal;
	}

	public void setHostDiskSizeTotal(String hostDiskSizeTotal) {
		this.hostDiskSizeTotal = hostDiskSizeTotal;
	}

	public String getHostEvents() {
		return hostEvents;
	}

	public void setHostEvents(String hostEvents) {
		this.hostEvents = hostEvents;
	}

	public String getHasEnoughCapacity() {
		return hasEnoughCapacity;
	}

	public void setHasEnoughCapacity(String hasEnoughCapacity) {
		this.hasEnoughCapacity = hasEnoughCapacity;
	}

	public String getHostTags() {
		return hostTags;
	}

	public void setHostTags(String hostTags) {
		this.hostTags = hostTags;
	}

	public String getHostHypervisor() {
		return hostHypervisor;
	}

	public void setHostHypervisor(String hostHypervisor) {
		this.hostHypervisor = hostHypervisor;
	}

	public String getHypervisorVersion() {
		return hypervisorVersion;
	}

	public void setHypervisorVersion(String hypervisorVersion) {
		this.hypervisorVersion = hypervisorVersion;
	}

	public String getHostIpAddress() {
		return hostIpAddress;
	}

	public void setHostIpAddress(String hostIpAddress) {
		this.hostIpAddress = hostIpAddress;
	}

	public String getIsLocalStorageActive() {
		return isLocalStorageActive;
	}

	public void setIsLocalStorageActive(String isLocalStorageActive) {
		this.isLocalStorageActive = isLocalStorageActive;
	}

	public String getHostLastPinged() {
		return hostLastPinged;
	}

	public void setHostLastPinged(String hostLastPinged) {
		this.hostLastPinged = hostLastPinged;
	}

	public String getHostManagementServerId() {
		return hostManagementServerId;
	}

	public void setHostManagementServerId(String hostManagementServerId) {
		this.hostManagementServerId = hostManagementServerId;
	}

	public String getHostMemoryAllocated() {
		return hostMemoryAllocated;
	}

	public void setHostMemoryAllocated(String hostMemoryAllocated) {
		this.hostMemoryAllocated = hostMemoryAllocated;
	}

	public String getHostMemoryTotal() {
		return hostMemoryTotal;
	}

	public void setHostMemoryTotal(String hostMemoryTotal) {
		this.hostMemoryTotal = hostMemoryTotal;
	}

	public String getHostMemoryUsed() {
		return hostMemoryUsed;
	}

	public void setHostMemoryUsed(String hostMemoryUsed) {
		this.hostMemoryUsed = hostMemoryUsed;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getHostNetworKbbsRead() {
		return hostNetworKbbsRead;
	}

	public void setHostNetworKbbsRead(String hostNetworKbbsRead) {
		this.hostNetworKbbsRead = hostNetworKbbsRead;
	}

	public String getHostNetworKbbsWrite() {
		return hostNetworKbbsWrite;
	}

	public void setHostNetworKbbsWrite(String hostNetworKbbsWrite) {
		this.hostNetworKbbsWrite = hostNetworKbbsWrite;
	}

	public String getHostOsCategoryId() {
		return hostOsCategoryId;
	}

	public void setHostOsCategoryId(String hostOsCategoryId) {
		this.hostOsCategoryId = hostOsCategoryId;
	}

	public String getHostOsCategoryName() {
		return hostOsCategoryName;
	}

	public void setHostOsCategoryName(String hostOsCategoryName) {
		this.hostOsCategoryName = hostOsCategoryName;
	}

	public String getHostPodId() {
		return hostPodId;
	}

	public void setHostPodId(String hostPodId) {
		this.hostPodId = hostPodId;
	}

	public String getHostPodName() {
		return hostPodName;
	}

	public void setHostPodName(String hostPodName) {
		this.hostPodName = hostPodName;
	}

	public String getHostRemovedDate() {
		return hostRemovedDate;
	}

	public void setHostRemovedDate(String hostRemovedDate) {
		this.hostRemovedDate = hostRemovedDate;
	}

	public String getHostResourceState() {
		return hostResourceState;
	}

	public void setHostResourceState(String hostResourceState) {
		this.hostResourceState = hostResourceState;
	}

	public String getHostSate() {
		return hostSate;
	}

	public void setHostSate(String hostSate) {
		this.hostSate = hostSate;
	}

	public String getSuitableForMigration() {
		return suitableForMigration;
	}

	public void setSuitableForMigration(String suitableForMigration) {
		this.suitableForMigration = suitableForMigration;
	}

	public String getHostType() {
		return hostType;
	}

	public void setHostType(String hostType) {
		this.hostType = hostType;
	}

	public String getHostVersion() {
		return hostVersion;
	}

	public void setHostVersion(String hostVersion) {
		this.hostVersion = hostVersion;
	}

	public String getHostZoneId() {
		return hostZoneId;
	}

	public void setHostZoneId(String hostZoneId) {
		this.hostZoneId = hostZoneId;
	}

	public String getHostZoneName() {
		return hostZoneName;
	}

	public void setHostZoneName(String hostZoneName) {
		this.hostZoneName = hostZoneName;
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
