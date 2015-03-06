package com.assistanz.cloud.cloudstack.users;

/**
 * 
 * @author Gowtham
 *
 */
public class AddVpnUserResponse {
	
	/**
	 * the vpn userID
	 */
	private String vpnUserId;

	/**
	 * 	the account of the remote access vpn
	 */
	private String vpnAccount;
	
	/**
	 * the domain name of the account of the remote access vpn
	 */
	private String vpnDomainName;
	
	/**
	 * the domain id of the account of the remote access vpn
	 */
	private String vpnDomainId;
	
	/**
	 * the project name of the vpn
	 */
	private String vpnProjectName;
	
	/**
	 * the project id of the vpn
	 */
	private String vpnProjectId;
	
	/**
	 * the username of the vpn user
	 */
	private String VpnUserName;

	public String getVpnUserId() {
		return vpnUserId;
	}

	public void setVpnUserId(String vpnUserId) {
		this.vpnUserId = vpnUserId;
	}

	public String getVpnAccount() {
		return vpnAccount;
	}

	public void setVpnAccount(String vpnAccount) {
		this.vpnAccount = vpnAccount;
	}

	public String getVpnDomainName() {
		return vpnDomainName;
	}

	public void setVpnDomainName(String vpnDomainName) {
		this.vpnDomainName = vpnDomainName;
	}

	public String getVpnDomainId() {
		return vpnDomainId;
	}

	public void setVpnDomainId(String vpnDomainId) {
		this.vpnDomainId = vpnDomainId;
	}

	public String getVpnProjectName() {
		return vpnProjectName;
	}

	public void setVpnProjectName(String vpnProjectName) {
		this.vpnProjectName = vpnProjectName;
	}

	public String getVpnProjectId() {
		return vpnProjectId;
	}

	public void setVpnProjectId(String vpnProjectId) {
		this.vpnProjectId = vpnProjectId;
	}

	public String getVpnUserName() {
		return VpnUserName;
	}

	public void setVpnUserName(String vpnUserName) {
		VpnUserName = vpnUserName;
	}
}
