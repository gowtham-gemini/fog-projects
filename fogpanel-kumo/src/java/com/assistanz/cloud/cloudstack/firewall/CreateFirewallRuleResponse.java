package com.assistanz.cloud.cloudstack.firewall;

/**
 * 
 * @author Gowtham
 *
 */
public class CreateFirewallRuleResponse {
	
	/**
	 * the ID of the firewall rule
	 */
	String firewallRuleId;
	
	/**
	 * the cidr list to forward traffic from
	 */
	String firewallRuleCidrList;
	
	/**
	 * the ending port of firewall rule's port range
	 */
	String firewallRuleEndPort;	
	
	/**
	 * error code for this icmp message
	 */
	String firewallRuleIcmpCode;
	
	/**
	 * type of the icmp message being sent
	 */
	String firewallRuleIcmpType;
	
	/**
	 * the public ip address for the port forwarding rule
	 */
	String firewallRuleIpAddress;
	
	/**
	 * the public ip address id for the port forwarding rule
	 */
	String firewallRuleIpAddressId;	
	
	/**
	 * the protocol of the firewall rule
	 */
	String firewallRuleProtocol;
	
	/**
	 * the starting port of firewall rule's port range
	 */
	String firewallRuleStartPort;	
	
	/**
	 * the state of the rule
	 */
	String firewallRuleState;
        
        String jobId;

        public String getJobId() {
            return jobId;
        }

        public void setJobId(String jobId) {
            this.jobId = jobId;
        }

	public String getFirewallRuleId() {
		return firewallRuleId;
	}

	public void setFirewallRuleId(String firewallRuleId) {
		this.firewallRuleId = firewallRuleId;
	}

	public String getFirewallRuleCidrList() {
		return firewallRuleCidrList;
	}

	public void setFirewallRuleCidrList(String firewallRuleCidrList) {
		this.firewallRuleCidrList = firewallRuleCidrList;
	}

	public String getFirewallRuleEndPort() {
		return firewallRuleEndPort;
	}

	public void setFirewallRuleEndPort(String firewallRuleEndPort) {
		this.firewallRuleEndPort = firewallRuleEndPort;
	}

	public String getFirewallRuleIcmpCode() {
		return firewallRuleIcmpCode;
	}

	public void setFirewallRuleIcmpCode(String firewallRuleIcmpCode) {
		this.firewallRuleIcmpCode = firewallRuleIcmpCode;
	}

	public String getFirewallRuleIcmpType() {
		return firewallRuleIcmpType;
	}

	public void setFirewallRuleIcmpType(String firewallRuleIcmpType) {
		this.firewallRuleIcmpType = firewallRuleIcmpType;
	}

	public String getFirewallRuleIpAddress() {
		return firewallRuleIpAddress;
	}

	public void setFirewallRuleIpAddress(String firewallRuleIpAddress) {
		this.firewallRuleIpAddress = firewallRuleIpAddress;
	}

	public String getFirewallRuleIpAddressId() {
		return firewallRuleIpAddressId;
	}

	public void setFirewallRuleIpAddressId(String firewallRuleIpAddressId) {
		this.firewallRuleIpAddressId = firewallRuleIpAddressId;
	}

	public String getFirewallRuleProtocol() {
		return firewallRuleProtocol;
	}

	public void setFirewallRuleProtocol(String firewallRuleProtocol) {
		this.firewallRuleProtocol = firewallRuleProtocol;
	}

	public String getFirewallRuleStartPort() {
		return firewallRuleStartPort;
	}

	public void setFirewallRuleStartPort(String firewallRuleStartPort) {
		this.firewallRuleStartPort = firewallRuleStartPort;
	}

	public String getFirewallRuleState() {
		return firewallRuleState;
	}

	public void setFirewallRuleState(String firewallRuleState) {
		this.firewallRuleState = firewallRuleState;
	}
	
	

}
