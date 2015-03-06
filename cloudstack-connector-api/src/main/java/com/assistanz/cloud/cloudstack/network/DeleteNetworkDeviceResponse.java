package com.assistanz.cloud.cloudstack.network;

/**
 *
 * @author Gowtham
 *
 */
public class DeleteNetworkDeviceResponse {

    /**
     * any text associated with the success or failure on Delete Physical Network
     */
    private String displaytext;

    /**
     * true if operation is executed successfully on Delete Physical Network
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
