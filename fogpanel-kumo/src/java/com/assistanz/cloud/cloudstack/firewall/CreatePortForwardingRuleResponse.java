package com.assistanz.cloud.cloudstack.firewall;

/**
 * 
 * @author Gowtham
 *
 */
public class CreatePortForwardingRuleResponse {
	
	/**
	 * the ID of the port forwarding rule
	 */
	String portForwardingRuleId;
	
	/**
	 * the cidr list to forward traffic from
	 */
	String portForwardingRuleCidrList;
	
	/**
	 * the public ip address for the port forwarding rule
	 */
	String 	portForwardingRuleIpAddress;	
	
	/**
	 * the public ip address id for the port forwarding rule
	 */
	String portForwardingRuleIpAddressId;	
	
	/**
	 * the ending port of port forwarding rule's private port range
	 */
	String portForwardingRulePrivateEndPort;	
	
	/**
	 * the starting port of port forwarding rule's private port range
	 */
	String portForwardingRulePrivateStartPort;
	
	/**
	 * the protocol of the port forwarding rule
	 */
	String portForwardingRuleProtocol;
	
	/**
	 * the ending port of port forwarding rule's private port range
	 */
	String portForwardingRulePublicEndPort;
	
	/**
	 * the starting port of port forwarding rule's public port range
	 */
	String portForwardingRulePublicStartPort;
	
	/**
	 * the state of the rule
	 */
	String portForwardingRuleState;
	
	/**
	 * the VM display name for the port forwarding rule
	 */
	String portForwardingRuleVirtualMachineDisplayName;
	
	/**
	 * the VM ID for the port forwarding rule
	 */
	String portForwardingRuleVirtualMachineId;
	
	/**
	 * the VM name for the port forwarding rule
	 */
	String portForwardingRuleVirtualMachineName;
        
        String jobId;

        public String getJobId() {
            return jobId;
        }

        public void setJobId(String jobId) {
            this.jobId = jobId;
        }
        
	public String getPortForwardingRuleId() {
		return portForwardingRuleId;
	}

	public void setPortForwardingRuleId(String portForwardingRuleId) {
		this.portForwardingRuleId = portForwardingRuleId;
	}

	public String getPortForwardingRuleCidrList() {
		return portForwardingRuleCidrList;
	}

	public void setPortForwardingRuleCidrList(String portForwardingRuleCidrList) {
		this.portForwardingRuleCidrList = portForwardingRuleCidrList;
	}

	public String getPortForwardingRuleIpAddress() {
		return portForwardingRuleIpAddress;
	}

	public void setPortForwardingRuleIpAddress(String portForwardingRuleIpAddress) {
		this.portForwardingRuleIpAddress = portForwardingRuleIpAddress;
	}

	public String getPortForwardingRuleIpAddressId() {
		return portForwardingRuleIpAddressId;
	}

	public void setPortForwardingRuleIpAddressId(
			String portForwardingRuleIpAddressId) {
		this.portForwardingRuleIpAddressId = portForwardingRuleIpAddressId;
	}

	public String getPortForwardingRulePrivateEndPort() {
		return portForwardingRulePrivateEndPort;
	}

	public void setPortForwardingRulePrivateEndPort(
			String portForwardingRulePrivateEndPort) {
		this.portForwardingRulePrivateEndPort = portForwardingRulePrivateEndPort;
	}

	public String getPortForwardingRulePrivateStartPort() {
		return portForwardingRulePrivateStartPort;
	}

	public void setPortForwardingRulePrivateStartPort(
			String portForwardingRulePrivateStartPort) {
		this.portForwardingRulePrivateStartPort = portForwardingRulePrivateStartPort;
	}

	public String getPortForwardingRuleProtocol() {
		return portForwardingRuleProtocol;
	}

	public void setPortForwardingRuleProtocol(String portForwardingRuleProtocol) {
		this.portForwardingRuleProtocol = portForwardingRuleProtocol;
	}

	public String getPortForwardingRulePublicEndPort() {
		return portForwardingRulePublicEndPort;
	}

	public void setPortForwardingRulePublicEndPort(
			String portForwardingRulePublicEndPort) {
		this.portForwardingRulePublicEndPort = portForwardingRulePublicEndPort;
	}

	public String getPortForwardingRulePublicStartPort() {
		return portForwardingRulePublicStartPort;
	}

	public void setPortForwardingRulePublicStartPort(
			String portForwardingRulePublicStartPort) {
		this.portForwardingRulePublicStartPort = portForwardingRulePublicStartPort;
	}

	public String getPortForwardingRuleState() {
		return portForwardingRuleState;
	}

	public void setPortForwardingRuleState(String portForwardingRuleState) {
		this.portForwardingRuleState = portForwardingRuleState;
	}

	public String getPortForwardingRuleVirtualMachineDisplayName() {
		return portForwardingRuleVirtualMachineDisplayName;
	}

	public void setPortForwardingRuleVirtualMachineDisplayName(
			String portForwardingRuleVirtualMachineDisplayName) {
		this.portForwardingRuleVirtualMachineDisplayName = portForwardingRuleVirtualMachineDisplayName;
	}

	public String getPortForwardingRuleVirtualMachineId() {
		return portForwardingRuleVirtualMachineId;
	}

	public void setPortForwardingRuleVirtualMachineId(
			String portForwardingRuleVirtualMachineId) {
		this.portForwardingRuleVirtualMachineId = portForwardingRuleVirtualMachineId;
	}

	public String getPortForwardingRuleVirtualMachineName() {
		return portForwardingRuleVirtualMachineName;
	}

	public void setPortForwardingRuleVirtualMachineName(
			String portForwardingRuleVirtualMachineName) {
		this.portForwardingRuleVirtualMachineName = portForwardingRuleVirtualMachineName;
	}

}
