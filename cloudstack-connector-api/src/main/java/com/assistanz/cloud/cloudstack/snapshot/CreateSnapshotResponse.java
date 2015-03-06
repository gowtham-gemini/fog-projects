package com.assistanz.cloud.cloudstack.snapshot;

import com.assistanz.cloud.cloudstack.TagsResponse;
import java.util.List;

/**
 *
 * @author Gowtham
 *
 */
public class CreateSnapshotResponse {

    /**
     * ID of the snapshot
     */
    private String id;

    /**
     * the account associated with the snapshot
     */
    private String account;

    /**
     * the date the snapshot was created
     */
    private String created;

    /**
     * the domain name of the snapshot's account
     */
    private String domain;

    /**
     * the domain ID of the snapshot's account
     */
    private String domainId;

    /**
     * valid types are hourly, daily, weekly, monthy, template, and none.
     */
    private String intervalType;

    /**
     * name of the snapshot
     */
    private String name;

    /**
     * the project name of the snapshot
     */
    private String project;

    /**
     * the project id of the snapshot
     */
    private String projectId;

    /**
     * the type of the snapshot
     */
    private String snapshotType;

    /**
     * the state of the snapshot. BackedUp means that snapshot is ready to be used; Creating - the snapshot is being
     * allocated on the primary storage; BackingUp - the snapshot is being backed up on secondary storage
     */
    private String state;

    /**
     * ID of the disk volume
     */
    private String volumeId;

    /**
     * name of the disk volume
     */
    private String volumeName;

    /**
     * type of the disk volume
     */
    private String volumeType;

    /**
     * id of the availability zone
     */
    private String zoneId;

    /**
     * list of tags associated with the snapshot
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

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
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

    public String getIntervalType() {
        return intervalType;
    }

    public void setIntervalType(String intervalType) {
        this.intervalType = intervalType;
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

    public String getSnapshotType() {
        return snapshotType;
    }

    public void setSnapshotType(String snapshotType) {
        this.snapshotType = snapshotType;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getVolumeId() {
        return volumeId;
    }

    public void setVolumeId(String volumeId) {
        this.volumeId = volumeId;
    }

    public String getVolumeName() {
        return volumeName;
    }

    public void setVolumeName(String volumeName) {
        this.volumeName = volumeName;
    }

    public String getVolumeType() {
        return volumeType;
    }

    public void setVolumeType(String volumeType) {
        this.volumeType = volumeType;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
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
