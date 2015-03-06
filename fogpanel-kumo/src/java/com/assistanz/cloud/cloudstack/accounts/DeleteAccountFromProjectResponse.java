package com.assistanz.cloud.cloudstack.accounts;

/**
 *
 * @author Gowtham
 */
public class DeleteAccountFromProjectResponse {
    
     /**
     * Any text associated with the success or failure on Deleting Account from Project
     */
    private String displayText;

   /**
    * Return true if Delete Account from Project operation is executed successfully
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
