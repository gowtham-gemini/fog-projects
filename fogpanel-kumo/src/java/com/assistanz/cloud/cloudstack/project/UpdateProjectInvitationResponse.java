package com.assistanz.cloud.cloudstack.project;

/**
 * 
 * @author Gowtham
 *
 */
public class UpdateProjectInvitationResponse {
	
    /**
     * Any text associated with the success or failure on updating project invitation
     */
    private String displayText;

   /**
    * Return true if update project invitation operation is executed successfully
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
