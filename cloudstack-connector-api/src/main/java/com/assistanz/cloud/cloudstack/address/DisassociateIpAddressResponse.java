package com.assistanz.cloud.cloudstack.address;

/**
 *
 * @author Gowtham
 *
 */
public class DisassociateIpAddressResponse {

    /**
     * any text associated with the success or failure on disassociate public ip address
     */
    private String displaytext;

    /**
     * true if operation is executed successfully on disassociate public ip addressl
     */
    private String success;
    
    /**
     * jobid for this operation
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
