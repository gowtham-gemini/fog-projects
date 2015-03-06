package com.assistanz.cloud.cloudstack.virtualmachine;

/**
 *
 * @author developer
 */
public class CleanVMReservationsResponse {

    /**
     * Any VM reservations in the database will be cleaned
     */
    private String displayText;

    /**
     * Return true if CleanVMReservations is executed successfully
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
