package com.assistanz.cloud.cloudstack.ssh;

/**
 * 
 * @author Gowtham
 *
 */
public class ListSSHKeyPairsResponse {
	
	/**
	 * Fingerprint of the public key
	 */
	String fingerPrint;
	
	/**
	 * Name of the keypair
	 */
	String keyPairName;
	
	/**
	 * Private key
	 */
	String privateKey;

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
