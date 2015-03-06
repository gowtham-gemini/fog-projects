package com.assistanz.cloud.cloudstack.vlan;

/**
 *
 * @author Gowtham
 *
 */
public class DedicatedGuestVlanRangeResponse {

    /**
     * the ID of the guest VLAN range
     */
    private String id;

    /**
     * the account of the guest VLAN range
     */
    private String account;

    /**
     * the domain name of the guest VLAN range
     */
    private String domain;

    /**
     * the domain ID of the guest VLAN range
     */
    private String domainId;

    /**
     * the guest VLAN range
     */
    private String guestVlanRange;

    /**
     * the physical network of the guest vlan range
     */
    private String physicalNetworkId;

    /**
     * the project name of the guest vlan range
     */
    private String project;

    /**
     * the project id of the guest vlan range
     */
    private String projectId;

    /**
     * the zoneid of the guest vlan range
     */
    private String zoneId;

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

    public String getGuestVlanRange() {
        return guestVlanRange;
    }

    public void setGuestVlanRange(String guestVlanRange) {
        this.guestVlanRange = guestVlanRange;
    }

    public String getPhysicalNetworkId() {
        return physicalNetworkId;
    }

    public void setPhysicalNetworkId(String physicalNetworkId) {
        this.physicalNetworkId = physicalNetworkId;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

}
