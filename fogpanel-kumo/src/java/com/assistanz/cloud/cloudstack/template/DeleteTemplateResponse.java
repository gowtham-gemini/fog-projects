package com.assistanz.cloud.cloudstack.template;

/**
 * 
 * @author Gowtham
 *
 */
public class DeleteTemplateResponse {
	
    /**
     * Any text associated with the success or failure on Deleting a template
     */
    private String displayText;

   /**
    * Return true if Delete Template operation is executed successfully
    */
   private String success;
   
   
   private String jobId;

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }
    
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
