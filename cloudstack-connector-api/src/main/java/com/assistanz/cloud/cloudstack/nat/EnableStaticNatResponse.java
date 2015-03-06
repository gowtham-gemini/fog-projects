package com.assistanz.cloud.cloudstack.nat;

/**
 *
 * @author Gowtham
 *
 */
public class EnableStaticNatResponse {

    /**
     * any text associated with the success or failure on enable StaticNat
     */
    private String displaytext;

    /**
     * true if operation is executed successfully on enable StaticNat
     */
    private String success;
    
    /**
     * return jobid if operation is executed successfully on enable StaticNat
     */
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
