/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.assistanz.cloud.cloudstack.nat;

/**
 *
 * @author gowtham
 */
class DisableStaticNatResponse {
    
        /**
	 * any text associated with the success or failure on Disable Ip Forwarding Rule t
	 */
	String displaytext;	
	
	/**
	 * true if operation is executed successfully on Disable Ip Forwarding Rule 
	 */
	String success;
        
        String jobId;

        public String getJobId() {
            return jobId;
        }

        public void setJobId(String jobId) {
            this.jobId = jobId;
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
