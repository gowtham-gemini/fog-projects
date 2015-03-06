package com.assistanz.cloud.cloudstack.systemvm;

/**
 * 
 * @author Gowtham
 *
 */
public class MigrateSystemVmResponse {
	
	/**
	 * the ID of the system VM
	 */
	String  systemVirtualMachineId;	
	
	/**
	 * the host ID for the system VM
	 */
	String  systemVirtualMachineHostId;
	
	/**
	 * the name of the system VM
	 */
	String  systemVirtualMachineName;
	
	/**
	 * the role of the system VM
	 */
	String  systemVirtualMachineRole;
	
	/**
	 * the state of the system VM
	 */
	String  systemVirtualMachineState;
	
	/**
	 * the system VM type
	 */
	String  systemVirtualMachineType;

	public String getSystemVirtualMachineId() {
		return systemVirtualMachineId;
	}

	public void setSystemVirtualMachineId(String systemVirtualMachineId) {
		this.systemVirtualMachineId = systemVirtualMachineId;
	}

	public String getSystemVirtualMachineHostId() {
		return systemVirtualMachineHostId;
	}

	public void setSystemVirtualMachineHostId(String systemVirtualMachineHostId) {
		this.systemVirtualMachineHostId = systemVirtualMachineHostId;
	}

	public String getSystemVirtualMachineName() {
		return systemVirtualMachineName;
	}

	public void setSystemVirtualMachineName(String systemVirtualMachineName) {
		this.systemVirtualMachineName = systemVirtualMachineName;
	}

	public String getSystemVirtualMachineRole() {
		return systemVirtualMachineRole;
	}

	public void setSystemVirtualMachineRole(String systemVirtualMachineRole) {
		this.systemVirtualMachineRole = systemVirtualMachineRole;
	}

	public String getSystemVirtualMachineState() {
		return systemVirtualMachineState;
	}

	public void setSystemVirtualMachineState(String systemVirtualMachineState) {
		this.systemVirtualMachineState = systemVirtualMachineState;
	}

	public String getSystemVirtualMachineType() {
		return systemVirtualMachineType;
	}

	public void setSystemVirtualMachineType(String systemVirtualMachineType) {
		this.systemVirtualMachineType = systemVirtualMachineType;
	}
}
