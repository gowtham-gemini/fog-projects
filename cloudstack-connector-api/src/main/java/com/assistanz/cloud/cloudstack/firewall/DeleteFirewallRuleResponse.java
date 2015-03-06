package com.assistanz.cloud.cloudstack.firewall;

/**
 *
 * @author Gowtham
 *
 */
public class DeleteFirewallRuleResponse {

    /**
     * any text associated with the success or failure on Delete Firewall Rule
     */
    private String displaytext;

    /**
     * true if operation is executed successfully on Delete Firewall Rule
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
