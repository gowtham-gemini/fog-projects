package com.assistanz.cloud.cloudstack.volume;

import com.assistanz.cloud.cloudstack.TagsResponse;
import java.util.List;

/**
 *
 * @author Gowtham
 */
class VolumeResponse {

    /**
     * ID of the disk volume
     */
    private String id;

    /**
     * the account associated with the disk volume
     */
    private String account;

    /**
     * the date the volume was attached to a VM instance
     */
    private String attached;

    /**
     * the date the disk volume was created
     */
    private String created;

    /**
     * the boolean state of whether the volume is destroyed or not
     */
    private String destroyed;

    /**
     * the ID of the device on user vm the volume is attached to. This tag is not returned when the volume is detached.
     */
    private String deviceId;

    /**
     * bytes read rate of the disk volume
     */
    private String diskBytesReadRate;

    /**
     * bytes write rate of the disk volume
     */
    private String diskBytesWriteRate;

    /**
     * io requests read rate of the disk volume
     */
    private String diskIopsReadRate;

    /**
     * io requests read rate of the disk volume
     */
    private String diskIopsWriteRate;

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
     * an optional field whether to the display the volume to the end user or not
     */
    private String displayVolume;

    /**
     * the domain associated with the disk volume
     */
    private String domain;

    /**
     * the ID of the domain associated with the disk volume
     */
    private String domainId;

    /**
     * Hypervisor the volume belongs to
     */
    private String hypervisor;

    /**
     * true if the volume is extractable, false otherwise
     */
    private String isExtractable;

    /**
     * max iops of the disk volume
     */
    private String maxIops;

    /**
     * min iops of the disk volume
     */
    private String minIops;

    /**
     * name of the disk volume
     */
    private String name;

    /**
     * the project name of the vpn
     */
    private String project;

    /**
     * the project id of the vpn
     */
    private String projectId;

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
    private String size;

    /**
     * ID of the snapshot from which this volume was created
     */
    private String snapshotId;

    /**
     * the state of the disk volume
     */
    private String state;

    /**
     * the status of the disk volume
     */
    private String status;

    /**
     * name of the primary storage hosting the disk volume
     */
    private String storage;

    /**
     * shared or local storage
     */
    private String storageType;

    /**
     * type of the disk volume (ROOT or DATADISK)
     */
    private String type;

    /**
     * id of the virtual machine
     */
    private String virtualMachineId;

    /**
     * display name of the virtual machine
     */
    private String vmDisplayName;

    /**
     * name of the virtual machine
     */
    private String vmName;

    /**
     * state of the virtual machine
     */
    private String vmState;

    /**
     * ID of the availability zone
     */
    private String zoneId;

    /**
     * name of the availability zone
     */
    private String zoneName;

    /**
     * list of tags associated with the volume
     */
    private List<TagsResponse> tagss;

    /**
     * the ID of the latest async job acting on this object
     */
    private String jobId;

    /**
     * the current status of the latest async job acting on this object
     */
    private String jobStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAttached() {
        return attached;
    }

    public void setAttached(String attached) {
        this.attached = attached;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getDestroyed() {
        return destroyed;
    }

    public void setDestroyed(String destroyed) {
        this.destroyed = destroyed;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDiskBytesReadRate() {
        return diskBytesReadRate;
    }

    public void setDiskBytesReadRate(String diskBytesReadRate) {
        this.diskBytesReadRate = diskBytesReadRate;
    }

    public String getDiskBytesWriteRate() {
        return diskBytesWriteRate;
    }

    public void setDiskBytesWriteRate(String diskBytesWriteRate) {
        this.diskBytesWriteRate = diskBytesWriteRate;
    }

    public String getDiskIopsReadRate() {
        return diskIopsReadRate;
    }

    public void setDiskIopsReadRate(String diskIopsReadRate) {
        this.diskIopsReadRate = diskIopsReadRate;
    }

    public String getDiskIopsWriteRate() {
        return diskIopsWriteRate;
    }

    public void setDiskIopsWriteRate(String diskIopsWriteRate) {
        this.diskIopsWriteRate = diskIopsWriteRate;
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

    public String getDisplayVolume() {
        return displayVolume;
    }

    public void setDisplayVolume(String displayVolume) {
        this.displayVolume = displayVolume;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

    public String getHypervisor() {
        return hypervisor;
    }

    public void setHypervisor(String hypervisor) {
        this.hypervisor = hypervisor;
    }

    public String getIsExtractable() {
        return isExtractable;
    }

    public void setIsExtractable(String isExtractable) {
        this.isExtractable = isExtractable;
    }

    public String getMaxIops() {
        return maxIops;
    }

    public void setMaxIops(String maxIops) {
        this.maxIops = maxIops;
    }

    public String getMinIops() {
        return minIops;
    }

    public void setMinIops(String minIops) {
        this.minIops = minIops;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSnapshotId() {
        return snapshotId;
    }

    public void setSnapshotId(String snapshotId) {
        this.snapshotId = snapshotId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getStorageType() {
        return storageType;
    }

    public void setStorageType(String storageType) {
        this.storageType = storageType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVirtualMachineId() {
        return virtualMachineId;
    }

    public void setVirtualMachineId(String virtualMachineId) {
        this.virtualMachineId = virtualMachineId;
    }

    public String getVmDisplayName() {
        return vmDisplayName;
    }

    public void setVmDisplayName(String vmDisplayName) {
        this.vmDisplayName = vmDisplayName;
    }

    public String getVmName() {
        return vmName;
    }

    public void setVmName(String vmName) {
        this.vmName = vmName;
    }

    public String getVmState() {
        return vmState;
    }

    public void setVmState(String vmState) {
        this.vmState = vmState;
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

    public List<TagsResponse> getTagss() {
        return tagss;
    }

    public void setTagss(List<TagsResponse> tagss) {
        this.tagss = tagss;
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