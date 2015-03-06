package com.assistanz.cloud.cloudstack.zone;

import java.util.List;
import com.assistanz.cloud.cloudstack.CapacityResponse;

/**
 *
 * @author Gowtham
 *
 */
public class CreateZoneResponse {

    /**
     * Zone id
     */
    private String id;

    /**
     * the UUID of the affinity group associated, null for public zones
     */
    private String affinityGroupId;

    /**
     * the allocation state of the cluster
     */
    private String allocationState;

    /**
     * Zone description
     */
    private String description;

    /**
     * the dhcp Provider for the Zone
     */
    private String dhcpProvider;

    /**
     * the display text of the zone
     */
    private String displayText;

    /**
     * the first DNS for the Zone
     */
    private String dns1;

    /**
     * the second DNS for the Zone
     */
    private String dns2;

    /**
     * Network domain name for the networks in the zone
     */
    private String domain;

    /**
     * the ID of the containing domain, null for public zones
     */
    private String domainId;

    /**
     * the name of the containing domain, null for public zones
     */
    private String domainName;

    /**
     * the guest CIDR address for the Zone
     */
    private String guestCidrAddress;

    /**
     * the first internal DNS for the Zone
     */
    private String internalDns1;

    /**
     * the second internal DNS for the Zone
     */
    private String internalDns2;

    /**
     * the first IPv6 DNS for the Zone
     */
    private String ip6Dns1;

    /**
     * the Second IPv6 DNS for the Zone
     */
    private String ip6Dns2;

    /**
     * true if local storage offering enabled, false otherwise
     */
    private String localStorageEnabled;

    /**
     * Zone name
     */
    private String name;

    /**
     * the network type of the zone; can be Basic or Advanced
     */
    private String networkType;

    /**
     * true if security groups support is enabled, false otherwise
     */
    private String securityGroupsEnabled;

    /**
     * the vlan range of the zone
     */
    private String vlan;

    /**
     * Zone Token
     */
    private String zoneToken;

    /**
     * the capacity of the Zone
     */
    private List<CapacityResponse> zoneCapacities;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAffinityGroupId() {
        return affinityGroupId;
    }

    public void setAffinityGroupId(String affinityGroupId) {
        this.affinityGroupId = affinityGroupId;
    }

    public String getAllocationState() {
        return allocationState;
    }

    public void setAllocationState(String allocationState) {
        this.allocationState = allocationState;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDhcpProvider() {
        return dhcpProvider;
    }

    public void setDhcpProvider(String dhcpProvider) {
        this.dhcpProvider = dhcpProvider;
    }

    public String getDisplayText() {
        return displayText;
    }

    public void setDisplayText(String displayText) {
        this.displayText = displayText;
    }

    public String getDns1() {
        return dns1;
    }

    public void setDns1(String dns1) {
        this.dns1 = dns1;
    }

    public String getDns2() {
        return dns2;
    }

    public void setDns2(String dns2) {
        this.dns2 = dns2;
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

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getGuestCidrAddress() {
        return guestCidrAddress;
    }

    public void setGuestCidrAddress(String guestCidrAddress) {
        this.guestCidrAddress = guestCidrAddress;
    }

    public String getInternalDns1() {
        return internalDns1;
    }

    public void setInternalDns1(String internalDns1) {
        this.internalDns1 = internalDns1;
    }

    public String getInternalDns2() {
        return internalDns2;
    }

    public void setInternalDns2(String internalDns2) {
        this.internalDns2 = internalDns2;
    }

    public String getIp6Dns1() {
        return ip6Dns1;
    }

    public void setIp6Dns1(String ip6Dns1) {
        this.ip6Dns1 = ip6Dns1;
    }

    public String getIp6Dns2() {
        return ip6Dns2;
    }

    public void setIp6Dns2(String ip6Dns2) {
        this.ip6Dns2 = ip6Dns2;
    }

    public String getLocalStorageEnabled() {
        return localStorageEnabled;
    }

    public void setLocalStorageEnabled(String localStorageEnabled) {
        this.localStorageEnabled = localStorageEnabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNetworkType() {
        return networkType;
    }

    public void setNetworkType(String networkType) {
        this.networkType = networkType;
    }

    public String getSecurityGroupsEnabled() {
        return securityGroupsEnabled;
    }

    public void setSecurityGroupsEnabled(String securityGroupsEnabled) {
        this.securityGroupsEnabled = securityGroupsEnabled;
    }

    public String getVlan() {
        return vlan;
    }

    public void setVlan(String vlan) {
        this.vlan = vlan;
    }

    public String getZoneToken() {
        return zoneToken;
    }

    public void setZoneToken(String zoneToken) {
        this.zoneToken = zoneToken;
    }

    public List<CapacityResponse> getZoneCapacities() {
        return zoneCapacities;
    }

    public void setZoneCapacities(List<CapacityResponse> zoneCapacities) {
        this.zoneCapacities = zoneCapacities;
    }

}
