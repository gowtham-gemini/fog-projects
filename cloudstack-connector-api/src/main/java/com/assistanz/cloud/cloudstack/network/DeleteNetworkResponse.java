package com.assistanz.cloud.cloudstack.network;

/**
 *
 * @author Gowtham
 *
 */
public class DeleteNetworkResponse {

    /**
     * any text associated with the success or failure on Delete Network
     */
    private String displaytext;

    /**
     * true if operation is executed successfully on Delete Network
     */
    private String success;
    
    String jobId;

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
    
    public String getJobId() {
            return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

}
