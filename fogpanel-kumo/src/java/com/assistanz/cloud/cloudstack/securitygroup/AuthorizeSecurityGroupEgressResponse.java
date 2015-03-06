package com.assistanz.cloud.cloudstack.securitygroup;

/**
 * 
 * @author Gowtham
 *
 */
public class AuthorizeSecurityGroupEgressResponse {
	
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
