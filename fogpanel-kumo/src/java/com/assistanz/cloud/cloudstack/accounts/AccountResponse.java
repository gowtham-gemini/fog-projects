package com.assistanz.cloud.cloudstack.accounts;

import com.assistanz.cloud.cloudstack.UserResponse;
import java.util.List;

/**
 *
 * @author Gowtham
 */
class AccountResponse {
    
       /**
     * The id of The account
     */
    private String accountId;
    
    /**
     * details for The account
     */
    private String accountDetails;
    
    /**
     * account type (admin, domain-admin, user)
     */
    private String accountType;
    
    /**
     * name of The Domain The account belongs too
     */
    private String DomainName;
    
    /**
     * id of The Domain The account belongs too
     */
    private String DomainId;
    
    /**
     * The total number of public IP addresses available for this account to acquire
     */
    private String ipAvailable;	
    
    /**
     * The total number of public IP addresses this account can acquire
     */
    private String ipLimit;
    
    /**
     * The total number of public IP addresses allocated for this account
     */
    private String ipTotal;
    
    /**
     * true if The account requires cleanup
     */
    private String isCleanUpRequired;	
    
    /**
     * The name of The account
     */        
    private String accountName;
    
    /**
     * The network domain
     */
    private String networkDomain;
    
    /**
     * The total number of network traffic bytes received
     */
    private String receivedBytes;
    
    /**
     * The total number of network traffic bytes sent
     */
    private String sentBytes;
    
    /**
     * The total number of snapshots available for this account
     */
    private String snapshotAvailable;
    
    /**
     * The total number of snapshots which can be stored by this account
     */
    private String snapshotLimit;
    
    /**
     * The total number of snapshots stored by this account
     */
    private String snapshotTotal;
    
    /**
     * The state of The account
     */
    private String accountState;
    
    /**
     * The total number of templates available to be created by this account
     */
    private String templateAvailable;
    
    /**
     * The total number of templates which can be created by this account
     */
    private String templateLimit;
    
    /**
     * The total number of templates which have been created by this account
     */
    private String templateTotal;
    
    /**
     * The total number of virtual machines available for this account to acquire
     */
    private String vmAvailable;
    
    /**
     * The total number of virtual machines that can be deployed by this account
     */
    private String vmLimit;
    
    /**
     * The total number of virtual machines running for this account
     */
    private String vmRunning;
    
    /**
     * The total number of virtual machines stopped for this account
     */
    private String vmStopped;
    
    /**
     * The total number of virtual machines deployed by this account
     */
    private String vmTotal;
    
    /**
     * The total volume available for this account
     */
    private String volumeAvailable;
    
    /**
     * The total volume which can be used by this account
     */
    private String volumeLimit;
    
    /**
     * The total volume being used by this account
     */
    private String volumeTotal;	
    
    /**
     * list of user for this account
     */
    private List<UserResponse> users;

    public List<UserResponse> getUsers() {
        return users;
    }

    public void setUsers(List<UserResponse> users) {
        this.users = users;
    }

    public String getDomainId() {
        return DomainId;
    }

    public void setDomainId(String DomainId) {
        this.DomainId = DomainId;
    }

    public String getDomainName() {
        return DomainName;
    }

    public void setDomainName(String DomainName) {
        this.DomainName = DomainName;
    }

    public String getAccountDetails() {
        return accountDetails;
    }

    public void setAccountDetails(String accountDetails) {
        this.accountDetails = accountDetails;
    }

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

    public String getAccountState() {
        return accountState;
    }

    public void setAccountState(String accountState) {
        this.accountState = accountState;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getIpAvailable() {
        return ipAvailable;
    }

    public void setIpAvailable(String ipAvailable) {
        this.ipAvailable = ipAvailable;
    }

    public String getIpLimit() {
        return ipLimit;
    }

    public void setIpLimit(String ipLimit) {
        this.ipLimit = ipLimit;
    }

    public String getIpTotal() {
        return ipTotal;
    }

    public void setIpTotal(String ipTotal) {
        this.ipTotal = ipTotal;
    }

    public String getIsCleanUpRequired() {
        return isCleanUpRequired;
    }

    public void setIsCleanUpRequired(String isCleanUpRequired) {
        this.isCleanUpRequired = isCleanUpRequired;
    }    

    public String getNetworkDomain() {
        return networkDomain;
    }

    public void setNetworkDomain(String networkDomain) {
        this.networkDomain = networkDomain;
    }

    public String getReceivedBytes() {
        return receivedBytes;
    }

    public void setReceivedBytes(String receivedBytes) {
        this.receivedBytes = receivedBytes;
    }

    public String getSentBytes() {
        return sentBytes;
    }

    public void setSentBytes(String sentBytes) {
        this.sentBytes = sentBytes;
    }

    public String getSnapshotAvailable() {
        return snapshotAvailable;
    }

    public void setSnapshotAvailable(String snapshotAvailable) {
        this.snapshotAvailable = snapshotAvailable;
    }

    public String getSnapshotLimit() {
        return snapshotLimit;
    }

    public void setSnapshotLimit(String snapshotLimit) {
        this.snapshotLimit = snapshotLimit;
    }

    public String getSnapshotTotal() {
        return snapshotTotal;
    }

    public void setSnapshotTotal(String snapshotTotal) {
        this.snapshotTotal = snapshotTotal;
    }

    public String getTemplateAvailable() {
        return templateAvailable;
    }

    public void setTemplateAvailable(String templateAvailable) {
        this.templateAvailable = templateAvailable;
    }

    public String getTemplateLimit() {
        return templateLimit;
    }

    public void setTemplateLimit(String templateLimit) {
        this.templateLimit = templateLimit;
    }

    public String getTemplateTotal() {
        return templateTotal;
    }

    public void setTemplateTotal(String templateTotal) {
        this.templateTotal = templateTotal;
    }

    public String getVmAvailable() {
        return vmAvailable;
    }

    public void setVmAvailable(String vmAvailable) {
        this.vmAvailable = vmAvailable;
    }

    public String getVmLimit() {
        return vmLimit;
    }

    public void setVmLimit(String vmLimit) {
        this.vmLimit = vmLimit;
    }

    public String getVmRunning() {
        return vmRunning;
    }

    public void setVmRunning(String vmRunning) {
        this.vmRunning = vmRunning;
    }

    public String getVmStopped() {
        return vmStopped;
    }

    public void setVmStopped(String vmStopped) {
        this.vmStopped = vmStopped;
    }

    public String getVmTotal() {
        return vmTotal;
    }

    public void setVmTotal(String vmTotal) {
        this.vmTotal = vmTotal;
    }

    public String getVolumeAvailable() {
        return volumeAvailable;
    }

    public void setVolumeAvailable(String volumeAvailable) {
        this.volumeAvailable = volumeAvailable;
    }

    public String getVolumeLimit() {
        return volumeLimit;
    }

    public void setVolumeLimit(String volumeLimit) {
        this.volumeLimit = volumeLimit;
    }

    public String getVolumeTotal() {
        return volumeTotal;
    }

    public void setVolumeTotal(String volumeTotal) {
        this.volumeTotal = volumeTotal;
    }   
    
}
