package com.assistanz.cloud.cloudstack.project;

/**
 * 
 * @author Gowtham
 *
 */
public class DeleteProjectInvitationResponse {
	
    /**
     * Any text associated with the success or failure on deleting project invitation
     */
    private String displayText;

   /**
    * Return true if delete project invitation operation is executed successfully
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
