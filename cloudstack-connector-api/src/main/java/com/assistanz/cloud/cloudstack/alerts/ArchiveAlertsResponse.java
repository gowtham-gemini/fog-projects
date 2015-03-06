package com.assistanz.cloud.cloudstack.alerts;

/**
 *
 * @author Gowtham
 *
 */
public class ArchiveAlertsResponse {

    /**
     * any text associated with the success or failure
     */
    private String displaytext;

    /**
     * true if operation is executed successfully
     */
    private String success;

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
