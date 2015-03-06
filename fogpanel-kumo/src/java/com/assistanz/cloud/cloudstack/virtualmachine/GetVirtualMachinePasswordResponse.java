package com.assistanz.cloud.cloudstack.virtualmachine;

/**
 * 
 * @author Gowtham
 *
 */
public class GetVirtualMachinePasswordResponse {
	
	/**
	 * The encrypted password of the virtual machine
	 */
	private String encryptedPassword;

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}
	

}
