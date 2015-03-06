package com.assistanz.cloud.cloudstack.snapshot;

/**
 *
 * @author Gowtham
 *
 */
public class DeleteSnapshotResponse {

    /**
     * any text associated with the success or failure on deleting snapshot
     */
    private String displaytext;

    /**
     * true if operation is executed successfully on deleting snapshot
     */
    private String success;
    
    
    /**
     * returns jobid if operation is executed successfully on deleting snapshot
     */
    private String jobId;

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    
    public String getDisplaytext() {
        return displaytext;
    }

    public void setDisplaytext(String displaytext) {
        this.displaytext = displaytext;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

}
