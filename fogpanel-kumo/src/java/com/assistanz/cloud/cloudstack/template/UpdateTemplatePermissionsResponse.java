package com.assistanz.cloud.cloudstack.template;

/**
 * 
 * @author Gowtham
 *
 */
public class UpdateTemplatePermissionsResponse {
	
	/**
     * Any text associated with the success or failure on updating a template
     */
    private String displayText;

   /**
    * Return true if update Template operation is executed successfully
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
