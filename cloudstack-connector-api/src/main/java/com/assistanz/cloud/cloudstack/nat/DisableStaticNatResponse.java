package com.assistanz.cloud.cloudstack.nat;

/**
 *
 * @author Gowtham
 *
 */
public class DisableStaticNatResponse {

    /**
     * any text associated with the success or failure on Disable Ip Forwarding Rule t
     */
    private String displaytext;

    /**
     * true if operation is executed successfully on Disable Ip Forwarding Rule
     */
    private String success;
    
    
    private String jobId;
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
