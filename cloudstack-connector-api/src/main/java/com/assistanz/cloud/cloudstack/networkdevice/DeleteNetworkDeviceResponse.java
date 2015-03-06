package com.assistanz.cloud.cloudstack.networkdevice;

/**
 *
 * @author Santhosh
 *
 */
public class DeleteNetworkDeviceResponse {

    /**
     * any text associated with the success or failure on Delete
     */
    private String displayText;

    /**
     * true if operation is executed successfully on Delete
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
