package com.assistanz.cloud.cloudstack.nic;

/**
 *
 * @author gowtham
 */
class RemoveIpFromNicResponse {

    /**
     * Any text associated with the success or failure on Deleting a IP
     */
    private String displayText;

    /**
     * Return true if Delete IP operation is executed successfully
     */
    private String success;

    /**
     * Return the jobId if the operation is executed successfully
     */
    private String jobId;
    
    public String getDisplayText() {
        return displayText;
    }

    public void setDisplayText(String displayText) {
        this.displayText = displayText;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
    
    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

}
