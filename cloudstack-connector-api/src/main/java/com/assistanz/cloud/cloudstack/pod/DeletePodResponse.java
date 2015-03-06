package com.assistanz.cloud.cloudstack.pod;

/**
 *
 * @author Gowtham
 *
 */
public class DeletePodResponse {

    /**
     * any text associated with the success or failure on deleting pod
     */
    private String displaytext;

    /**
     * true if operation is executed successfully on deleting pod
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
