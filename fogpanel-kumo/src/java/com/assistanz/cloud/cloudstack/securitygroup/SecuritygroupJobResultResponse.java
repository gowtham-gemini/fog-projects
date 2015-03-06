/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assistanz.cloud.cloudstack.securitygroup;

import com.assistanz.cloud.cloudstack.EgressRuleResponse;
import com.assistanz.cloud.cloudstack.IngressRuleResponse;
import java.util.List;

/**
 *
 * @author nandhini
 */
class SecuritygroupJobResultResponse {
    
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
     * the error text of the job
     */
    String errortext;	

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

    public String getErrortext() {
        return errortext;
    }

    public void setErrortext(String errortext) {
        this.errortext = errortext;
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
    
    
    
    //Securitygroup
    
    
    	/**
	 * the ID of the security group
	 */
	private String securityGroupId;
	
	/**
	 * the account owning the security group
	 */
	private String securityGroupAccount;
	
	/**
	 * the description of the security group
	 */
	private String description;
	/**
	 * the domain name of the security group
	 */
	private String securityGroupDomainName;
	
	/**
	 * the domain ID of the security group
	 */
	private String securityGroupDomainId;
	
	/**
	 * the name of the security group
	 */
	private String securityGroupName;
	
	/**
	 * the project name of the group
	 */
	private String securityGroupProjectName;
	
	/**
	 * the project id of the group
	 */
	private String securityGroupProjectId;
	
	/**
	 * the ID of the latest async job acting on this object
	 */
	private String jobId;

	/**
	 * the current status of the latest async job acting on this object
	 */
	private String jobStatus;	
	
	/**
	 * the list of egress rules associated with the security group
	 */
	private List<EgressRuleResponse> egressRules;
	
	/**
	 * 	the list of ingress rules associated with the security group
	 */
	private List<IngressRuleResponse> ingressRules;

	public String getSecurityGroupId() {
		return securityGroupId;
	}

	public void setSecurityGroupId(String securityGroupId) {
		this.securityGroupId = securityGroupId;
	}

	public String getSecurityGroupAccount() {
		return securityGroupAccount;
	}

	public void setSecurityGroupAccount(String securityGroupAccount) {
		this.securityGroupAccount = securityGroupAccount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSecurityGroupDomainName() {
		return securityGroupDomainName;
	}

	public void setSecurityGroupDomainName(String securityGroupDomainName) {
		this.securityGroupDomainName = securityGroupDomainName;
	}

	public String getSecurityGroupDomainId() {
		return securityGroupDomainId;
	}

	public void setSecurityGroupDomainId(String securityGroupDomainId) {
		this.securityGroupDomainId = securityGroupDomainId;
	}

	public String getSecurityGroupName() {
		return securityGroupName;
	}

	public void setSecurityGroupName(String securityGroupName) {
		this.securityGroupName = securityGroupName;
	}

	
	public String getSecurityGroupProjectName() {
		return securityGroupProjectName;
	}

	public void setSecurityGroupProjectName(String securityGroupProjectName) {
		this.securityGroupProjectName = securityGroupProjectName;
	}

	public String getSecurityGroupProjectId() {
		return securityGroupProjectId;
	}

	public void setSecurityGroupProjectId(String securityGroupProjectId) {
		this.securityGroupProjectId = securityGroupProjectId;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	public List<EgressRuleResponse> getEgressRules() {
		return egressRules;
	}

	public void setEgressRules(List<EgressRuleResponse> egressRules) {
		this.egressRules = egressRules;
	}

	public List<IngressRuleResponse> getIngressRules() {
		return ingressRules;
	}

	public void setIngressRules(List<IngressRuleResponse> ingressRules) {
		this.ingressRules = ingressRules;
	}
    
    
}
