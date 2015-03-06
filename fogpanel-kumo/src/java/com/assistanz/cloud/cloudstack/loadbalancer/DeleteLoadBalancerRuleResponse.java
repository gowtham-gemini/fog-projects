package com.assistanz.cloud.cloudstack.loadbalancer;

/**
 * 
 * @author Gowtham
 *
 */
public class DeleteLoadBalancerRuleResponse {
	

	/**
	 * any text associated with the success or failure on Delete Load Balancer Rule
	 */
	String displaytext;	
	
	/**
	 * true if operation is executed successfully on Delete Load Balancer Rule
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
