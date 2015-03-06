package com.assistanz.cloud.cloudstack.firewall;

/**
 *
 * @author Gowtham
 *
 */
public class DeletePortForwardingRuleResponse {

    /**
     * any text associated with the success or failure on Delete PortForwarding Rule
     */
    private String displaytext;

    /**
     * true if operation is executed successfully on Delete PortForwarding Rule
     */
    private String success;
    
    /**
     * returns jobid if operation executed successfully on Delete PortForwarding Rule
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