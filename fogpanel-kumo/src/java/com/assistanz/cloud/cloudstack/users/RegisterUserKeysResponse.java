package com.assistanz.cloud.cloudstack.users;

/**
 * 
 * @author Gowtham
 *
 */
public class RegisterUserKeysResponse {
	
    /**
     * The APIkey of the user
     */
    private String apiKey;
    
    /**
     * the secret key of the user
     */
    private String secretKey;

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
} 
