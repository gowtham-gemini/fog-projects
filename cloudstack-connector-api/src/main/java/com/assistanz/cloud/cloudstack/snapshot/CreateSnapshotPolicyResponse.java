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
    private String id;

    /**
     * the interval type of the snapshot policy
     */
    private String intervalType;

    /**
     * maximum number of snapshots retained
     */
    private String maxSnaps;

    /**
     * time the snapshot is scheduled to be taken.
     */
    private String schedule;

    /**
     * the time zone of the snapshot policy
     */
    private String timeZone;

    /**
     * the ID of the disk volume
     */
    private String volumeId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIntervalType() {
        return intervalType;
    }

    public void setIntervalType(String intervalType) {
        this.intervalType = intervalType;
    }

    public String getMaxSnaps() {
        return maxSnaps;
    }

    public void setMaxSnaps(String maxSnaps) {
        this.maxSnaps = maxSnaps;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getVolumeId() {
        return volumeId;
    }

    public void setVolumeId(String volumeId) {
        this.volumeId = volumeId;
    }

}
