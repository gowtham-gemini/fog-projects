package com.assistanz.cloud.cloudstack.limit;

/**
 *
 * @author Gowtham
 *
 */
public class ResetApiLimitResponse {

    /**
     * the account name of the api remaining count
     */
    private String account;

    /**
     * the account uuid of the api remaining count
     */
    private String accountId;

    /**
     * currently allowed number of apis
     */
    private String apiAllowed;

    /**
     * number of api already issued
     */
    private String apiIssued;

    /**
     * seconds left to reset counters
     */
    private String expireAfter;

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

    public String getApiAllowed() {
        return apiAllowed;
    }

    public void setApiAllowed(String apiAllowed) {
        this.apiAllowed = apiAllowed;
    }

    public String getApiIssued() {
        return apiIssued;
    }

    public void setApiIssued(String apiIssued) {
        this.apiIssued = apiIssued;
    }

    public String getExpireAfter() {
        return expireAfter;
    }

    public void setExpireAfter(String expireAfter) {
        this.expireAfter = expireAfter;
    }

}
