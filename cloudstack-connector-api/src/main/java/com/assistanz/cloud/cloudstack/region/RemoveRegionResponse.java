package com.assistanz.cloud.cloudstack.region;

/**
 *
 * @author Gowtham
 *
 */
public class RemoveRegionResponse {

    /**
     * Any text associated with the success or failure
     */
    private String displayText;

    /**
     * Return true if the operation is executed successfully
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
