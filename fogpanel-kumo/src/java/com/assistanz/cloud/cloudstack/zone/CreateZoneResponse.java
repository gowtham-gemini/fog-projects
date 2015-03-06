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
	String zoneId;
	
	/**
	 * the allocation state of the cluster
	 */
	String allocationState;	
	
	/**
	 * Zone description
	 */
	String zoneDescription;
	
	/**
	 * the dhcp Provider for the Zone
	 */
	String dhcpProvider;
	
	/**
	 * the display text of the zone
	 */
	String zoneDisplayText;
	
	/**
	 * the first DNS for the Zone
	 */
	String dnsFirst;
	
	/**
	 * the second DNS for the Zone
	 */
	String dnsSecond;
	
	/**
	 * Network domain name for the networks in the zone
	 */
	String zoneDomain;
	
	/**
	 * the ID of the containing domain, null for public zones
	 */
	String zoneDomainId;
	
	/**
	 * the name of the containing domain, null for public zones
	 */
	String zoneDomainName;	
	
	/**
	 * the guest CIDR address for the Zone
	 */
	String guestCidrAddress;
	
	/**
	 * the first internal DNS for the Zone
	 */
	String internalDnsFirst;
	
	/**
	 * the second internal DNS for the Zone
	 */
	String internalDnsSecond;
	
	/**
	 * Zone name
	 */
	String zoneName;
	
	/**
	 * the network type of the zone; can be Basic or Advanced
	 */
	String zoneNetworkType;	
	
	/**
	 * true if security groups support is enabled, false otherwise
	 */
	String securityGroupsEnabled;
	
	/**
	 * the vlan range of the zone
	 */
	String vlan;
	
	/**
	 * Zone Token
	 */
	String zoneToken;
	
	/**
	 * the capacity of the Zone
	 */
	private List<CapacityResponse> zoneCapacities;
	
	
	public String getAllocationState() {
		return allocationState;
	}

	public void setAllocationState(String allocationState) {
		this.allocationState = allocationState;
	}

	
	public String getDhcpProvider() {
		return dhcpProvider;
	}

	public void setDhcpProvider(String dhcpProvider) {
		this.dhcpProvider = dhcpProvider;
	}

	
	public String getDnsFirst() {
		return dnsFirst;
	}

	public void setDnsFirst(String dnsFirst) {
		this.dnsFirst = dnsFirst;
	}

	public String getDnsSecond() {
		return dnsSecond;
	}

	public void setDnsSecond(String dnsSecond) {
		this.dnsSecond = dnsSecond;
	}

	public String getZoneDomain() {
		return zoneDomain;
	}

	public void setZoneDomain(String zoneDomain) {
		this.zoneDomain = zoneDomain;
	}

	public String getZoneDomainId() {
		return zoneDomainId;
	}

	public void setZoneDomainId(String zoneDomainId) {
		this.zoneDomainId = zoneDomainId;
	}

	public String getZoneDomainName() {
		return zoneDomainName;
	}

	public void setZoneDomainName(String zoneDomainName) {
		this.zoneDomainName = zoneDomainName;
	}

	public String getGuestCidrAddress() {
		return guestCidrAddress;
	}

	public void setGuestCidrAddress(String guestCidrAddress) {
		this.guestCidrAddress = guestCidrAddress;
	}

	public String getInternalDnsFirst() {
		return internalDnsFirst;
	}

	public void setInternalDnsFirst(String internalDnsFirst) {
		this.internalDnsFirst = internalDnsFirst;
	}

	public String getInternalDnsSecond() {
		return internalDnsSecond;
	}

	public void setInternalDnsSecond(String internalDnsSecond) {
		this.internalDnsSecond = internalDnsSecond;
	}

	public String getZoneName() {
		return zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}

	public String getZoneNetworkType() {
		return zoneNetworkType;
	}

	public void setZoneNetworkType(String zoneNetworkType) {
		this.zoneNetworkType = zoneNetworkType;
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

        
	public String getZoneId() {
		return zoneId;
	}

	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}

	public String getZoneDescription() {
		return zoneDescription;
	}

	public void setZoneDescription(String zoneDescription) {
		this.zoneDescription = zoneDescription;
	}

	public String getZoneDisplayText() {
		return zoneDisplayText;
	}

	public void setZoneDisplayText(String zoneDisplayText) {
		this.zoneDisplayText = zoneDisplayText;
	}
	
	

}
