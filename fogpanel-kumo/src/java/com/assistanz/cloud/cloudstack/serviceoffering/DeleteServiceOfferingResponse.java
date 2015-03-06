package com.assistanz.cloud.cloudstack.serviceoffering;

public class DeleteServiceOfferingResponse {
	
	/**
     * Any text associated with the success or failure on Deleting a account
     */
    private String displayText;

   /**
    * Return true if Delete Account operation is executed successfully
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
