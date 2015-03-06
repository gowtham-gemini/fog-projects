package com.assistanz.cloud.cloudstack.host;

/**
 * 
 * @author Gowtham
 *
 */
public class DeleteHostResponse {
	
    /**
     * Any text associated with the success or failure on Deleting a host
     */
    private String displayText;

   /**
    * Return true if Delete host operation is executed successfully
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
