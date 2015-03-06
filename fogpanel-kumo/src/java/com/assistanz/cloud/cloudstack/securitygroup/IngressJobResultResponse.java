package com.assistanz.cloud.cloudstack.securitygroup;

/**
 *
 * @author Gowtham
 */
class IngressJobResultResponse {
    
    /**
     * the account that executed the async command
     */
    String asychronousAccountId;

    /**
     * 	the async command executed
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
    

    /**
     * account owning the security group rule
     */
    String securityGroupAccount;

    /**
     * the CIDR notation for the base IP address of the security group rule
     */
    String securityGroupCidr;

    /**
     * the ending IP of the security group rule
     */
    String securityGroupEndport;	

    /**
     * the code for the ICMP message response
     */
    String securityGroupIcmpCode;

    /**
     * the type of the ICMP message response
     */
    String securityGroupIcmpType;

    /**
     * the protocol of the security group rule
     */
    String securityGroupProtocol;

    /**
     * the id of the security group rule
     */
    String securityGroupRuleId;

    /**
     * security group name
     */
    String securityGroupName;

    /**
     * the starting IP of the security group rule
     */
    String securityGroupStartport;
    
    /**
     * the jobId
     */
    String jobId;

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }
    
    public String getSecurityGroupAccount() {
            return securityGroupAccount;
    }

    public void setSecurityGroupAccount(String securityGroupAccount) {
            this.securityGroupAccount = securityGroupAccount;
    }

    public String getSecurityGroupCidr() {
            return securityGroupCidr;
    }

    public void setSecurityGroupCidr(String securityGroupCidr) {
            this.securityGroupCidr = securityGroupCidr;
    }

    public String getSecurityGroupEndport() {
            return securityGroupEndport;
    }

    public void setSecurityGroupEndport(String securityGroupEndport) {
            this.securityGroupEndport = securityGroupEndport;
    }

    public String getSecurityGroupIcmpCode() {
            return securityGroupIcmpCode;
    }

    public void setSecurityGroupIcmpCode(String securityGroupIcmpCode) {
            this.securityGroupIcmpCode = securityGroupIcmpCode;
    }

    public String getSecurityGroupIcmpType() {
            return securityGroupIcmpType;
    }

    public void setSecurityGroupIcmpType(String securityGroupIcmpType) {
            this.securityGroupIcmpType = securityGroupIcmpType;
    }

    public String getSecurityGroupProtocol() {
            return securityGroupProtocol;
    }

    public void setSecurityGroupProtocol(String securityGroupProtocol) {
            this.securityGroupProtocol = securityGroupProtocol;
    }

    public String getSecurityGroupRuleId() {
            return securityGroupRuleId;
    }

    public void setSecurityGroupRuleId(String securityGroupRuleId) {
            this.securityGroupRuleId = securityGroupRuleId;
    }

    public String getSecurityGroupName() {
            return securityGroupName;
    }

    public void setSecurityGroupName(String securityGroupName) {
            this.securityGroupName = securityGroupName;
    }

    public String getSecurityGroupStartport() {
            return securityGroupStartport;
    }

    public void setSecurityGroupStartport(String securityGroupStartport) {
            this.securityGroupStartport = securityGroupStartport;
    }	
    
    
}
