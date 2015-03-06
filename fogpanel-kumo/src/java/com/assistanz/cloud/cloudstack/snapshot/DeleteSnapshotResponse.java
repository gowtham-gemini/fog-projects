package com.assistanz.cloud.cloudstack.snapshot;

/**
 * 
 * @author Gowtham
 *
 */
public class DeleteSnapshotResponse {
	
	/**
	 * any text associated with the success or failure on deleting snapshot
	 */
	String displaytext;	
	
	/**
	 * true if operation is executed successfully on deleting snapshot
	 */
	String success;
        
        String jobid;

        public String getJobid() {
            return jobid;
        }

        public void setJobid(String jobid) {
            this.jobid = jobid;
        }

	public String getDisplaytext() {
		return displaytext;
	}

	public void setDisplaytext(String displaytext) {
		this.displaytext = displaytext;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

}
