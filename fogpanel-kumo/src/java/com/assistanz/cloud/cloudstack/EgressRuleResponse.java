package com.assistanz.cloud.cloudstack;

/**
 * 
 * @author Gowtham
 *
 */
public class EgressRuleResponse {
	
	/**
	 * account owning the security group rule
	 */
	private String securityGroupRuleAccount;
	
	/**
	 * the CIDR notation for the base IP address of the security group rule
	 */
	private String cidr;
	
	/**
	 * the ending IP of the security group rule
	 */
	private String endPort;	
	
	/**
	 * the code for the ICMP message response
	 */
	private String icmpCode;
	
	/**
	 * the type of the ICMP message response
	 */
	private String icmpType;
	
	/**
	 * the protocol of the security group rule
	 */
	private String protocol;
	
	/**
	 * the id of the security group rule
	 */
	private String  securityGroupRuleId;
	
	/**
	 * security group name
	 */
	private String securityGroupName;
	
	/**
	 * the starting IP of the security group rule
	 */
	private String startPort;

	public String getSecurityGroupRuleAccount() {
		return securityGroupRuleAccount;
	}

	public void setSecurityGroupRuleAccount(String securityGroupRuleAccount) {
		this.securityGroupRuleAccount = securityGroupRuleAccount;
	}

	public String getCidr() {
		return cidr;
	}

	public void setCidr(String cidr) {
		this.cidr = cidr;
	}

	public String getEndPort() {
		return endPort;
	}

	public void setEndPort(String endPort) {
		this.endPort = endPort;
	}

	public String getIcmpCode() {
		return icmpCode;
	}

	public void setIcmpCode(String icmpCode) {
		this.icmpCode = icmpCode;
	}

	public String getIcmpType() {
		return icmpType;
	}

	public void setIcmpType(String icmpType) {
		this.icmpType = icmpType;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
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

	public String getStartPort() {
		return startPort;
	}

	public void setStartPort(String startPort) {
		this.startPort = startPort;
	}
	
	
}
