package com.assistanz.cloud.cloudstack.asynchronousjob;

/**
 * 
 * @author Gowtham
 *
 */
public class QueryAsynchronousJobResultResponse {
	
	/**
	 * the account that executed the async command
	 */
	private String accountId;
	
	/**
	 * the async command executed
	 */
	private String cmd;	
	
	/**
	 * the created date of the job
	 */
	private String created;
	
	/**
	 * the unique ID of the instance/entity object related to the job
	 */
	private String jobInstanceId;
	
	/**
	 * the instance/entity object related to the job
	 */
	private String jobInstanceType;
	
	/**
	 * the progress information of the PENDING job
	 */
	private String jobProgressStatus;
	
	/**
	 * the result reason
	 */
	private String jobResult;
	
	/**
	 * the result code for the job
	 */
	private String jobResultCode;
	
	/**
	 * the result type
	 */
	private String jobResultType;
	
	/**
	 * the current job status-should be 0 for PENDING
	 */
	private String jobStatus;
	
	/**
	 * the user that executed the async command
	 */
	private String userId;
	
	/**
	 * the ID of the async job
	 */
	private String jobId;

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getJobInstanceId() {
		return jobInstanceId;
	}

	public void setJobInstanceId(String jobInstanceId) {
		this.jobInstanceId = jobInstanceId;
	}

	public String getJobInstanceType() {
		return jobInstanceType;
	}

	public void setJobInstanceType(String jobInstanceType) {
		this.jobInstanceType = jobInstanceType;
	}

	public String getJobProgressStatus() {
		return jobProgressStatus;
	}

	public void setJobProgressStatus(String jobProgressStatus) {
		this.jobProgressStatus = jobProgressStatus;
	}

	public String getJobResult() {
		return jobResult;
	}

	public void setJobResult(String jobResult) {
		this.jobResult = jobResult;
	}

	public String getJobResultCode() {
		return jobResultCode;
	}

	public void setJobResultCode(String jobResultCode) {
		this.jobResultCode = jobResultCode;
	}

	public String getJobResultType() {
		return jobResultType;
	}

	public void setJobResultType(String jobResultType) {
		this.jobResultType = jobResultType;
	}

	public String getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	
}
