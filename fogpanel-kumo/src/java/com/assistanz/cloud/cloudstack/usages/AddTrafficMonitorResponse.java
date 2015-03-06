package com.assistanz.cloud.cloudstack.usages;

/**
 * 
 * @author Gowtham
 *
 */
public class AddTrafficMonitorResponse {
	
	/**
	 * the ID of the external firewall
	 */
	private String externalFirewallId;
	
	/**
	 * the management IP address of the external firewall
	 */
	private String externalFirewallIpAddress;
	
	/**
	 * the number of times to retry requests to the external firewall
	 */
	private String numberOfRetries;
	
	/**
	 * the private interface of the external firewall
	 */
	private String privateInterface;
	
	/**
	 * the private security zone of the external firewall
	 */
	private String privateZone;
	
	/**
	 * the public interface of the external firewall
	 */
	private String publicInterface;
	
	/**
	 * the public security zone of the external firewall
	 */
	private String publicZone;
	
	/**
	 * the timeout (in seconds) for requests to the external firewall
	 */
	private String timeout;
	
	/**
	 * the usage interface of the external firewall
	 */
	private String usageInterface;
	
	/**
	 * the username that's used to log in to the external firewall
	 */
	private String userName;
	
	/**
	 * the zone ID of the external firewall
	 */
	private String zoneId;

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

	
	public String getNumberOfRetries() {
		return numberOfRetries;
	}

	public void setNumberOfRetries(String numberOfRetries) {
		this.numberOfRetries = numberOfRetries;
	}

	public String getPrivateInterface() {
		return privateInterface;
	}

	public void setPrivateInterface(String privateInterface) {
		this.privateInterface = privateInterface;
	}

	public String getPrivateZone() {
		return privateZone;
	}

	public void setPrivateZone(String privateZone) {
		this.privateZone = privateZone;
	}

	public String getPublicInterface() {
		return publicInterface;
	}

	public void setPublicInterface(String publicInterface) {
		this.publicInterface = publicInterface;
	}

	public String getPublicZone() {
		return publicZone;
	}

	public void setPublicZone(String publicZone) {
		this.publicZone = publicZone;
	}

	public String getTimeout() {
		return timeout;
	}

	public void setTimeout(String timeout) {
		this.timeout = timeout;
	}

	public String getUsageInterface() {
		return usageInterface;
	}

	public void setUsageInterface(String usageInterface) {
		this.usageInterface = usageInterface;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getZoneId() {
		return zoneId;
	}

	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}	
	
	

}
