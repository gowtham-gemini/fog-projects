package com.assistanz.cloud.cloudstack.accounts;

import com.assistanz.cloud.cloudstack.UserResponse;
import java.util.List;

/**
 *
 * @author Gowtham
 */
public class CreateAccountResponse {

    /**
     * the user ID
     */
    private String id;

    /**
     * details for the account
     */
    private String accountDetails;

    /**
     * account type (admin, domain-admin, user)
     */
    private String accountType;

    /**
     * the total number of cpu cores available to be created for this account
     */
    private String cpuAvailable;

    /**
     * the total number of cpu cores the account can own
     */
    private String cpuLimit;

    /**
     * the total number of cpu cores owned by account
     */
    private String cpuTotal;

    /**
     * the default zone of the account
     */
    private String defaultZoneId;

    /**
     * the domain name of the user
     */
    private String domain;

    /*
     * id of the Domain the account belongs too
     */
    private String domainId;

    /**
     * the total number of public ip addresses available for this account to acquire
     */
    private String ipAvailable;

    /**
     * the total number of public ip addresses this account can acquire
     */
    private String ipLimit;

    /**
     * the total number of public ip addresses allocated for this account
     */
    private String ipTotal;

    /**
     * true if the account requires cleanup
     */
    private String isCleanupRequired;

    /**
     * true if account is default, false otherwise
     */
    private String isDefault;

    /**
     * the total memory (in MB) available to be created for this account
     */
    private String memoryAvailable;

    /**
     * the total memory (in MB) the account can own
     */
    private String memoryLimit;

    /**
     * the total memory (in MB) owned by account
     */
    private String memoryTotal;

    /**
     * the name of the account
     */
    private String name;

    /**
     * the total number of networks available to be created for this account
     */
    private String networkAvailable;

    /**
     * the network domain
     */
    private String networkDomain;

    /**
     * the total number of networks the account can own
     */
    private String networkLimit;

    /**
     * the total number of networks owned by account
     */
    private String networkTotal;

    /**
     * the total primary storage space (in GiB) available to be used for this account
     */
    private String primaryStorageAvailable;

    /**
     * the total primary storage space (in GiB) the account can own
     */
    private String primaryStorageLimit;

    /**
     * the total primary storage space (in GiB) owned by account
     */
    private String primaryStorageTotal;

    /**
     * the total number of projects available for administration by this account
     */
    private String projectAvailable;

    /**
     * the total number of projects the account can own
     */
    private String projectLimit;

    /**
     * the total number of projects being administrated by this account
     */
    private String projectTotal;

    /**
     * the total number of network traffic bytes received
     */
    private String receivedBytes;

    /**
     * the total secondary storage space (in GiB) available to be used for this account
     */
    private String secondaryStorageAvailable;

    /**
     * the total secondary storage space (in GiB) the account can own
     */
    private String secondaryStorageLimit;

    /**
     * the total secondary storage space (in GiB) owned by account
     */
    private String secondaryStorageTotal;

    /**
     * the total number of network traffic bytes sent
     */
    private String sentBytes;

    /**
     * the total number of snapshots available for this account
     */
    private String snapshotAvailable;

    /**
     * the total number of snapshots which can be stored by this account
     */
    private String snapshotLimit;

    /**
     * the total number of snapshots stored by this account
     */
    private String snapshotTotal;

    /**
     * the state of the account
     */
    private String state;

    /**
     * the total number of templates available to be created by this account
     */
    private String templateAvailable;

    /**
     * the total number of templates which can be created by this account
     */
    private String templateLimit;

    /**
     * the total number of templates which have been created by this account
     */
    private String templateTotal;

    /**
     * the total number of virtual machines available for this account to acquire
     */
    private String vmAvailable;

    /**
     * the total number of virtual machines that can be deployed by this account
     */
    private String vmLimit;

    /**
     * the total number of virtual machines running for this account
     */
    private String vmRunning;

    /**
     * the total number of virtual machines stopped for this account
     */
    private String vmStopped;

    /**
     * the total number of virtual machines deployed by this account
     */
    private String vmTotal;

    /**
     * the total volume available for this account
     */
    private String volumeAvailable;

    /**
     * the total volume which can be used by this account
     */
    private String volumeLimit;

    /**
     * the total volume being used by this account
     */
    private String volumeTotal;

    /**
     * the total number of vpcs available to be created for this account
     */
    private String vpcAvailable;

    /**
     * the total number of vpcs the account can own
     */
    private String vpcLimit;

    /**
     * the total number of vpcs owned by account
     */
    private String vpcTotal;

    private List<UserResponse> users;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountDetails() {
        return accountDetails;
    }

    public void setAccountDetails(String accountDetails) {
        this.accountDetails = accountDetails;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getCpuAvailable() {
        return cpuAvailable;
    }

    public void setCpuAvailable(String cpuAvailable) {
        this.cpuAvailable = cpuAvailable;
    }

    public String getCpuLimit() {
        return cpuLimit;
    }

    public void setCpuLimit(String cpuLimit) {
        this.cpuLimit = cpuLimit;
    }

    public String getCpuTotal() {
        return cpuTotal;
    }

    public void setCpuTotal(String cpuTotal) {
        this.cpuTotal = cpuTotal;
    }

    public String getDefaultZoneId() {
        return defaultZoneId;
    }

    public void setDefaultZoneId(String defaultZoneId) {
        this.defaultZoneId = defaultZoneId;
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

    public String getIsCleanupRequired() {
        return isCleanupRequired;
    }

    public void setIsCleanupRequired(String isCleanupRequired) {
        this.isCleanupRequired = isCleanupRequired;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    public String getMemoryAvailable() {
        return memoryAvailable;
    }

    public void setMemoryAvailable(String memoryAvailable) {
        this.memoryAvailable = memoryAvailable;
    }

    public String getMemoryLimit() {
        return memoryLimit;
    }

    public void setMemoryLimit(String memoryLimit) {
        this.memoryLimit = memoryLimit;
    }

    public String getMemoryTotal() {
        return memoryTotal;
    }

    public void setMemoryTotal(String memoryTotal) {
        this.memoryTotal = memoryTotal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNetworkAvailable() {
        return networkAvailable;
    }

    public void setNetworkAvailable(String networkAvailable) {
        this.networkAvailable = networkAvailable;
    }

    public String getNetworkDomain() {
        return networkDomain;
    }

    public void setNetworkDomain(String networkDomain) {
        this.networkDomain = networkDomain;
    }

    public String getNetworkLimit() {
        return networkLimit;
    }

    public void setNetworkLimit(String networkLimit) {
        this.networkLimit = networkLimit;
    }

    public String getNetworkTotal() {
        return networkTotal;
    }

    public void setNetworkTotal(String networkTotal) {
        this.networkTotal = networkTotal;
    }

    public String getPrimaryStorageAvailable() {
        return primaryStorageAvailable;
    }

    public void setPrimaryStorageAvailable(String primaryStorageAvailable) {
        this.primaryStorageAvailable = primaryStorageAvailable;
    }

    public String getPrimaryStorageLimit() {
        return primaryStorageLimit;
    }

    public void setPrimaryStorageLimit(String primaryStorageLimit) {
        this.primaryStorageLimit = primaryStorageLimit;
    }

    public String getPrimaryStorageTotal() {
        return primaryStorageTotal;
    }

    public void setPrimaryStorageTotal(String primaryStorageTotal) {
        this.primaryStorageTotal = primaryStorageTotal;
    }

    public String getProjectAvailable() {
        return projectAvailable;
    }

    public void setProjectAvailable(String projectAvailable) {
        this.projectAvailable = projectAvailable;
    }

    public String getProjectLimit() {
        return projectLimit;
    }

    public void setProjectLimit(String projectLimit) {
        this.projectLimit = projectLimit;
    }

    public String getProjectTotal() {
        return projectTotal;
    }

    public void setProjectTotal(String projectTotal) {
        this.projectTotal = projectTotal;
    }

    public String getReceivedBytes() {
        return receivedBytes;
    }

    public void setReceivedBytes(String receivedBytes) {
        this.receivedBytes = receivedBytes;
    }

    public String getSecondaryStorageAvailable() {
        return secondaryStorageAvailable;
    }

    public void setSecondaryStorageAvailable(String secondaryStorageAvailable) {
        this.secondaryStorageAvailable = secondaryStorageAvailable;
    }

    public String getSecondaryStorageLimit() {
        return secondaryStorageLimit;
    }

    public void setSecondaryStorageLimit(String secondaryStorageLimit) {
        this.secondaryStorageLimit = secondaryStorageLimit;
    }

    public String getSecondaryStorageTotal() {
        return secondaryStorageTotal;
    }

    public void setSecondaryStorageTotal(String secondaryStorageTotal) {
        this.secondaryStorageTotal = secondaryStorageTotal;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public String getVpcAvailable() {
        return vpcAvailable;
    }

    public void setVpcAvailable(String vpcAvailable) {
        this.vpcAvailable = vpcAvailable;
    }

    public String getVpcLimit() {
        return vpcLimit;
    }

    public void setVpcLimit(String vpcLimit) {
        this.vpcLimit = vpcLimit;
    }

    public String getVpcTotal() {
        return vpcTotal;
    }

    public void setVpcTotal(String vpcTotal) {
        this.vpcTotal = vpcTotal;
    }

    public List<UserResponse> getUsers() {
        return users;
    }

    public void setUsers(List<UserResponse> users) {
        this.users = users;
    }

}
