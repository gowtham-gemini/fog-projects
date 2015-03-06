package com.assistanz.cloud.cloudstack.accounts;

/**
 *
 * @author Gowtham
 */
public class AddAccountToProjectResponse {
    
    /**
     * Any text associated with the success or failure on Adding Account to Project
     */
    private String displayText;

   /**
    * Return true if Adding Account to Project operation is executed successfully
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
