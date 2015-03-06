package com.assistanz.cloud.cloudstack;

import java.util.List;

/**
 * 
 * @author Gowtham
 *
 */
public class SecurityGroupResponse {
	
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
	 * the list of egress rules associated with the security group
	 */
	private List<EgressRuleResponse> egressRules;
	
	/**
	 * the list of ingress Rules associated with the security group
	 */
	private List<IngressRuleResponse> ingressRules;
	
	/**
	 * the ID of the latest async job acting on this object
	 */
	private String jobId;

	/**
	 * the current status of the latest async job acting on this object
	 */
	private String jobStatus;	

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

	
}
