package com.assistanz.cloud.cloudstack.volume;

/**
 * 
 * @author Gowtham
 *
 */
public class DeleteVolumeResponse {
	
    /**
     * Any text associated with the success or failure on Deleting a volume
     */
    private String displayText;

   /**
    * Return true if Delete volume operation is executed successfully
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
