package com.assistanz.cloud.cloudstack.users;

/**
 * 
 * @author Gowtham
 *
 */
public class ListUserResponse {
	
	/**
     * 	The user ID
     */
    private String userId;
    
    /**
     * The account name of the user
     */
    private String accountName;
    
    /**
     * The accountID of the user
     */
    private String accountId;
    
    /**
     * The account type of the user
     */
    private String accountType;
    
    /**
     * The APIkey of the user
     */
    private String apiKey;
    
    /**
     * the date and time the user account was created
     */
    private String created;

    /**
     * the domain name of the user
     */
    private String domainName;
    
    /**
     * the domain ID of the user
     */
    private String domainId;
    
    /*
     * the user email ID
     */
    private String emailId;
    
    /**
     * the user first name 
     */
    private String firstName;
    
    /**
     * the user last name
     */
    private String lastName;
    
    /**
     * the secret key of the user
     */
    private String secretKey;
    
    /**
     * the user state
     */
    private String state;
    
    /**
     * the TimeZone user was created in
     */
    private String timeZone;
    
    /**
     * the user name
     */
    private String userName;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


}
