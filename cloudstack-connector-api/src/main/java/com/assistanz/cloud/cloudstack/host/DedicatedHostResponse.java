package com.assistanz.cloud.cloudstack.host;

/**
 *
 * @author Santhosh
 *
 */
public class DedicatedHostResponse {

    /**
     * the ID of the dedicated resource
     */
    private String id;

    /**
     * the Account ID of the host
     */
    private String accountId;

    /**
     * the Dedication Affinity Group ID of the host
     */
    private String affinityGroupId;

    /**
     * the domain ID of the host
     */
    private String domainId;

    /**
     * the ID of the host
     */
    private String hostId;

    /**
     * the name of the host
     */
    private String hostName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAffinityGroupId() {
        return affinityGroupId;
    }

    public void setAffinityGroupId(String affinityGroupId) {
        this.affinityGroupId = affinityGroupId;
    }

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

    public String getHostId() {
        return hostId;
    }

    public void setHostId(String hostId) {
        this.hostId = hostId;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

}
