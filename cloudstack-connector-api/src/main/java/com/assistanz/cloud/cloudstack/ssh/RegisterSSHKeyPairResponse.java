package com.assistanz.cloud.cloudstack.ssh;

/**
 *
 * @author Gowtham
 *
 */
public class RegisterSSHKeyPairResponse {

    /**
     * Fingerprint of the public key
     */
    private String fingerPrint;

    /**
     * Name of the keypair
     */
    private String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

}
