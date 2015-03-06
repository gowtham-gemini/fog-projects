package com.assistanz.cloud.cloudstack.bigswitchvns;

/**
 *
 * @author Gowtham
 *
 */
public class DeleteBigSwitchVnsDeviceResponse {

    /**
     * any text associated with the success or failure
     */
    private String displayText;

    /**
     * true if operation is executed successfully
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
