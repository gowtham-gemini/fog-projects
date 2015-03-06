package com.assistanz.cloud.cloudstack.asyncjob;

/**
 * 
 * @author Gowtham
 *
 */
public class QueryAsyncJobResultResponse {
	
	/**
	 * the account that executed the async command
	 */
	String asychronousAccountId;
        
        String id;
	
	/**
	 * the async command executed
	 */
	String asychronousCmd;
	
	/**
	 * the created date of the job
	 */
	String asychronousCreated;	
	
	/**
	 * the unique ID of the instance/entity object related to the job
	 */
	String asychronousJobInstanceId;
	
	/**
	 * 	the instance/entity object related to the job
	 */
	String asychronousJobInstanceType;
	
	/**
	 * the progress information of the PENDING job
	 */
	String asychronousJobProgressStatus;
	
	/**
	 * the result reason
	 */
	String asychronousJobResult;
	
	/**
	 * the result code for the job
	 */
	String asychronousJobResultCode;
	
	/**
	 * the result type
	 */
	String asychronousJobResultType;
	
	/**
	 * the current job status-should be 0 for PENDING
	 */
	String asychronousJobStatus;
	
	/**
	 * the user that executed the async command
	 */
	String asychronousUserId;
	
	/**
	 * the ID of the async job
	 */
	String asychronousJobId;
        
        String errorCode;
        
        String errorText;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(String errorCode) {
            this.errorCode = errorCode;
        }

        public String getErrorText() {
            return errorText;
        }

        public void setErrorText(String errorText) {
            this.errorText = errorText;
        }

	public String getAsychronousAccountId() {
		return asychronousAccountId;
	}

	public void setAsychronousAccountId(String asychronousAccountId) {
		this.asychronousAccountId = asychronousAccountId;
	}

	public String getAsychronousCmd() {
		return asychronousCmd;
	}

	public void setAsychronousCmd(String asychronousCmd) {
		this.asychronousCmd = asychronousCmd;
	}

	public String getAsychronousCreated() {
		return asychronousCreated;
	}

	public void setAsychronousCreated(String asychronousCreated) {
		this.asychronousCreated = asychronousCreated;
	}

	public String getAsychronousJobInstanceId() {
		return asychronousJobInstanceId;
	}

	public void setAsychronousJobInstanceId(String asychronousJobInstanceId) {
		this.asychronousJobInstanceId = asychronousJobInstanceId;
	}

	public String getAsychronousJobInstanceType() {
		return asychronousJobInstanceType;
	}

	public void setAsychronousJobInstanceType(String asychronousJobInstanceType) {
		this.asychronousJobInstanceType = asychronousJobInstanceType;
	}

	public String getAsychronousJobProgressStatus() {
		return asychronousJobProgressStatus;
	}

	public void setAsychronousJobProgressStatus(String asychronousJobProgressStatus) {
		this.asychronousJobProgressStatus = asychronousJobProgressStatus;
	}

	public String getAsychronousJobResult() {
		return asychronousJobResult;
	}

	public void setAsychronousJobResult(String asychronousJobResult) {
		this.asychronousJobResult = asychronousJobResult;
	}

	public String getAsychronousJobResultCode() {
		return asychronousJobResultCode;
	}

	public void setAsychronousJobResultCode(String asychronousJobResultCode) {
		this.asychronousJobResultCode = asychronousJobResultCode;
	}

	public String getAsychronousJobResultType() {
		return asychronousJobResultType;
	}

	public void setAsychronousJobResultType(String asychronousJobResultType) {
		this.asychronousJobResultType = asychronousJobResultType;
	}

	public String getAsychronousJobStatus() {
		return asychronousJobStatus;
	}

	public void setAsychronousJobStatus(String asychronousJobStatus) {
		this.asychronousJobStatus = asychronousJobStatus;
	}

	public String getAsychronousUserId() {
		return asychronousUserId;
	}

	public void setAsychronousUserId(String asychronousUserId) {
		this.asychronousUserId = asychronousUserId;
	}

	public String getAsychronousJobId() {
		return asychronousJobId;
	}

	public void setAsychronousJobId(String asychronousJobId) {
		this.asychronousJobId = asychronousJobId;
	}	
	
}
