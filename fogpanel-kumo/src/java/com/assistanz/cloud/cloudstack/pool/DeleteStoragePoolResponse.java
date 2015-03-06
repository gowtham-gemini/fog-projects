package com.assistanz.cloud.cloudstack.pool;

/**
 * 
 * @author Gowtham
 *
 */
public class DeleteStoragePoolResponse {
	
    
    /**
     * Any text associated with the success or failure on Deleting a StoragePool
     */
    private String displayText;

   /**
    * Return true if Delete StoragePool operation is executed successfully
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
