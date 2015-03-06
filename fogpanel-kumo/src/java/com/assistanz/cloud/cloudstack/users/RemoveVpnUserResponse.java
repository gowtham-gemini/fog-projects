package com.assistanz.cloud.cloudstack.users;

/**
 * 
 * @author Gowtham
 *
 */
public class RemoveVpnUserResponse {
	
    /**
     * Any text associated with the success or failure on Removing Vpn
     */
    private String displayText;

   /**
    * Return true if remove Vpn operation is executed successfully
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
