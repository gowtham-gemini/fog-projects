package com.assistanz.cloud.cloudstack;

/**
 * 
 * @author Gowtam
 *
 */
public class CapabilityResponse {
	
	/**
	 * can this service capability value can be choosable while creatine network offerings
	 */
	String canChooseServiceCapability;	
	
	/**
	 * 	the capability name
	 */
	String capabilityName;
	
	/**
	 * the capability value
	 */
	String capabilityValue;

	public String getCanChooseServiceCapability() {
		return canChooseServiceCapability;
	}

	public void setCanChooseServiceCapability(String canChooseServiceCapability) {
		this.canChooseServiceCapability = canChooseServiceCapability;
	}

	public String getCapabilityName() {
		return capabilityName;
	}

	public void setCapabilityName(String capabilityName) {
		this.capabilityName = capabilityName;
	}

	public String getCapabilityValue() {
		return capabilityValue;
	}

	public void setCapabilityValue(String capabilityValue) {
		this.capabilityValue = capabilityValue;
	}	
	
	

}
