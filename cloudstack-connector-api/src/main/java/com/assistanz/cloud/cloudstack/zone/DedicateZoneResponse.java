package com.assistanz.cloud.cloudstack.zone;

/**
 *
 * @author Santhosh
 *
 */
public class DedicateZoneResponse {

    /**
     * the ID of the dedicated resource
     */
    private String id;

    /**
     * the the Account Id to which the Zone is dedicated
     */
    private String accountId;

    /**
     * the Dedication Affinity Group ID of the zone
     */
    private String affinityGroupId;

    /**
     * the domain ID to which the Zone is dedicated
     */
    private String domainId;

    /**
     * the ID of the Zone
     */
    private String zoneId;

    /**
     * the Name of the Zone
     */
    private String zoneName;

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

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

}
