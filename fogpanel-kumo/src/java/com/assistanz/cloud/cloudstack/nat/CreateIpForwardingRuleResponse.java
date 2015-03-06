package com.assistanz.cloud.cloudstack.nat;

/**
 * 
 * @author Gowtham
 *
 */
public class CreateIpForwardingRuleResponse {
	
	/**
	 * the ID of the port forwarding rule
	 */
	String portForwardingRuleId;	
	
	/**
	 * the cidr list to forward traffic from
	 */
	String cidrList;
	
	/**
	 * the public ip address for the port forwarding rule
	 */
	String portForwardingRuleIpAddress;
	
	/**
	 * 	the public ip address id for the port forwarding rule
	 */
	String portForwardingRuleIpaddressId;
	
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
	String portForwardingRuleVirtualmachineDisplayName;
	
	/**
	 * the VM ID for the port forwarding rule
	 */
	String portForwardingRuleVirtualMachineId; 
	
	/**
	 * the VM name for the port forwarding rule
	 */
	String portForwardingRuleVirtualMachineName;

	public String getPortForwardingRuleId() {
		return portForwardingRuleId;
	}

	public void setPortForwardingRuleId(String portForwardingRuleId) {
		this.portForwardingRuleId = portForwardingRuleId;
	}

	public String getCidrList() {
		return cidrList;
	}

	public void setCidrList(String cidrList) {
		this.cidrList = cidrList;
	}

	public String getPortForwardingRuleIpAddress() {
		return portForwardingRuleIpAddress;
	}

	public void setPortForwardingRuleIpAddress(String portForwardingRuleIpAddress) {
		this.portForwardingRuleIpAddress = portForwardingRuleIpAddress;
	}

	public String getPortForwardingRuleIpaddressId() {
		return portForwardingRuleIpaddressId;
	}

	public void setPortForwardingRuleIpaddressId(
			String portForwardingRuleIpaddressId) {
		this.portForwardingRuleIpaddressId = portForwardingRuleIpaddressId;
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

	public String getPortForwardingRuleVirtualmachineDisplayName() {
		return portForwardingRuleVirtualmachineDisplayName;
	}

	public void setPortForwardingRuleVirtualmachineDisplayName(
			String portForwardingRuleVirtualmachineDisplayName) {
		this.portForwardingRuleVirtualmachineDisplayName = portForwardingRuleVirtualmachineDisplayName;
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
