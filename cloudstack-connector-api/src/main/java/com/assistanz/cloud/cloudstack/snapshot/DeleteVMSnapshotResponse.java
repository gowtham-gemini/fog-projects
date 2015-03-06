package com.assistanz.cloud.cloudstack.snapshot;

/**
 *
 * @author gowtham
 */
class DeleteVMSnapshotResponse {

    /**
     * any text associated with the success or failure on deleting snapshot
     */
    private String displaytext;

    /**
     * true if operation is executed successfully on deleting snapshot
     */
    private String success;
    
    /**
     * returns jobid if operation is executed successfully 
     */
    private String jobid;

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

    public String getJobid() {
        return jobid;
    }

    public void setJobid(String jobid) {
        this.jobid = jobid;
    }

}
