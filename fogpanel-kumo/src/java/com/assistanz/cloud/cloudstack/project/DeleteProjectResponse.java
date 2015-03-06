package com.assistanz.cloud.cloudstack.project;

/**
 * 
 * @author Gowtham
 *
 */
public class DeleteProjectResponse {
	
    /**
     * Any text associated with the success or failure on Deleting a project
     */
    private String displayText;

   /**
    * Return true if Delete project operation is executed successfully
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
