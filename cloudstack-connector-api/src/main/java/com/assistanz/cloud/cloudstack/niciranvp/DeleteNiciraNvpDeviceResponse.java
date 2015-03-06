package com.assistanz.cloud.cloudstack.niciranvp;

/**
 *
 * @author Santhosh
 *
 */
public class DeleteNiciraNvpDeviceResponse {

    /**
     * any text associated with the success or failure on deleting pod
     */
    private String displayText;

    /**
     * true if operation is executed successfully on deleting pod
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
