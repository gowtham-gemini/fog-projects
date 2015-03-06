package com.assistanz.cloud.cloudstack.registration;

/**
 * 
 * @author Gowtham
 *
 */
public class RegistrationServiceResponse {
	
	/**
	 * Finger print of the public key
	 */
	private String fingerPrint;
	
	/**
	 * Name of the keypair
	 */
	private String keyPairName;
	
	/**
	 * Private key
	 */
	private String privateKey;

	public String getFingerPrint() {
		return fingerPrint;
	}

	public void setFingerPrint(String fingerPrint) {
		this.fingerPrint = fingerPrint;
	}

	public String getKeyPairName() {
		return keyPairName;
	}

	public void setKeyPairName(String keyPairName) {
		this.keyPairName = keyPairName;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

}
