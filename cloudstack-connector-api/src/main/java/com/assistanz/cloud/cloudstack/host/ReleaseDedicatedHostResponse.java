package com.assistanz.cloud.cloudstack.host;

/**
 *
 * @author Santhosh
 *
 */
public class ReleaseDedicatedHostResponse {

    /**
     * Any text associated with the success or failure on updating a host password
     */
    private String displayText;

    /**
     * Return true if update a host password operation is executed successfully
     */
    private String success;

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

}