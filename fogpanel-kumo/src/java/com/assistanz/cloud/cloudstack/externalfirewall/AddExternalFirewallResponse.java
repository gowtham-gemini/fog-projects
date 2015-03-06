package com.assistanz.cloud.cloudstack.externalfirewall;

/**
 * 
 * @author Gowtham
 *
 */
public class AddExternalFirewallResponse {
	
	/**
	 * the ID of the external firewall
	 */
	String externalFirewallId;
	
	/**
	 * the management IP address of the external firewall
	 */
	String externalFirewallIpAddress;
	
	/**
	 * the number of times to retry requests to the external firewall
	 */
	String numRetriesExternalFirewall;
	
	/**
	 * the private interface of the external firewall
	 */
	String externalFirewallPrivateInterface;
	
	/**
	 * 	the private security zone of the external firewall
	 */
	String externalFirewallPrivateZone;
	
	/**
	 * the public interface of the external firewall
	 */
	String externalFirewallPublicInterface;	
	
	/**
	 * the public security zone of the external firewall
	 */
	String externalFirewallPublicZone;
	
	/**
	 * the timeout (in seconds) for requests to the external firewall
	 */
	String externalFirewallTimeout;	
	
	/**
	 * the usage interface of the external firewall
	 */
	String externalFirewallUsageInterface;	
	
	/**
	 * the username that's used to log in to the external firewall
	 */
	String externalFirewallUserName;
	
	/**
	 * the zone ID of the external firewall
	 */
	String externalFirewallZoneId;

	public String getExternalFirewallId() {
		return externalFirewallId;
	}

	public void setExternalFirewallId(String externalFirewallId) {
		this.externalFirewallId = externalFirewallId;
	}

	public String getExternalFirewallIpAddress() {
		return externalFirewallIpAddress;
	}

	public void setExternalFirewallIpAddress(String externalFirewallIpAddress) {
		this.externalFirewallIpAddress = externalFirewallIpAddress;
	}

	
	public String getNumRetriesExternalFirewall() {
		return numRetriesExternalFirewall;
	}

	public void setNumRetriesExternalFirewall(String numRetriesExternalFirewall) {
		this.numRetriesExternalFirewall = numRetriesExternalFirewall;
	}

	public String getExternalFirewallPrivateInterface() {
		return externalFirewallPrivateInterface;
	}

	public void setExternalFirewallPrivateInterface(
			String externalFirewallPrivateInterface) {
		this.externalFirewallPrivateInterface = externalFirewallPrivateInterface;
	}

	public String getExternalFirewallPrivateZone() {
		return externalFirewallPrivateZone;
	}

	public void setExternalFirewallPrivateZone(String externalFirewallPrivateZone) {
		this.externalFirewallPrivateZone = externalFirewallPrivateZone;
	}

	public String getExternalFirewallPublicInterface() {
		return externalFirewallPublicInterface;
	}

	public void setExternalFirewallPublicInterface(
			String externalFirewallPublicInterface) {
		this.externalFirewallPublicInterface = externalFirewallPublicInterface;
	}

	public String getExternalFirewallPublicZone() {
		return externalFirewallPublicZone;
	}

	public void setExternalFirewallPublicZone(String externalFirewallPublicZone) {
		this.externalFirewallPublicZone = externalFirewallPublicZone;
	}

	public String getExternalFirewallTimeout() {
		return externalFirewallTimeout;
	}

	public void setExternalFirewallTimeout(String externalFirewallTimeout) {
		this.externalFirewallTimeout = externalFirewallTimeout;
	}

	public String getExternalFirewallUsageInterface() {
		return externalFirewallUsageInterface;
	}

	public void setExternalFirewallUsageInterface(
			String externalFirewallUsageInterface) {
		this.externalFirewallUsageInterface = externalFirewallUsageInterface;
	}

	public String getExternalFirewallUserName() {
		return externalFirewallUserName;
	}

	public void setExternalFirewallUserName(String externalFirewallUserName) {
		this.externalFirewallUserName = externalFirewallUserName;
	}

	public String getExternalFirewallZoneId() {
		return externalFirewallZoneId;
	}

	public void setExternalFirewallZoneId(String externalFirewallZoneId) {
		this.externalFirewallZoneId = externalFirewallZoneId;
	}	
	
	

}
