package com.assistanz.cloud.cloudstack.users;

/**
 *
 * @author Gowtham
 *
 */
public class EnableUserResponse {

    /**
     * The user ID
     */
    private String id;

    /**
     * The account name of the user
     */
    private String account;

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
    private String domain;

    /**
     * the domain ID of the user
     */
    private String domainId;

    /*
     * the user email ID
     */
    private String email;

    /**
     * the user first name
     */
    private String firstName;

    /**
     * the boolean value representing if the updating target is in caller's child domain
     */
    private String isCallerChildDomain;

    /**
     * true if user is default, false otherwise
     */
    private String isDefault;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
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

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getIsCallerChildDomain() {
        return isCallerChildDomain;
    }

    public void setIsCallerChildDomain(String isCallerChildDomain) {
        this.isCallerChildDomain = isCallerChildDomain;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
