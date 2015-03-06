package com.assistanz.cloud.cloudstack.vpn;

/**
 * 
 * @author Gowtham
 *
 */
public class CreateRemoteAccessVpnResponse {
	
	/**
	 * the account of the remote access vpn
	 */
	String remoteAccessVpnAccount;	
	
	/**
	 * the domain name of the account of the remote access vpn
	 */
	String remoteAccessVpnDomain;	
	
	/**
	 * the domain id of the account of the remote access vpn
	 */
	String remoteAccessVpnDomainId;	
	
	/**
	 * the range of ips to allocate to the clients
	 */
	String remoteAccessVpnIpRange;
	
	/**
	 * the ipsec preshared key
	 */
	String remoteAccessVpnPresharedKey;
	
	/**
	 * the project name of the vpn
	 */
	String vpnProjectName;	
	
	/**
	 * the project id of the vpn
	 */
	String vpnProjectId;

	/**
	 * the public ip address of the vpn server
	 */
	String vpnPublicIp;	
	
	/**
	 * the public ip address of the vpn server
	 */
	String vpnPublicipId;
	
	/**
	 * the state of the rule
	 */
	String ruleState;
        
        String jobId;

        public String getJobId() {
            return jobId;
        }

        public void setJobId(String jobId) {
            this.jobId = jobId;
        }

	public String getRemoteAccessVpnAccount() {
		return remoteAccessVpnAccount;
	}

	public void setRemoteAccessVpnAccount(String remoteAccessVpnAccount) {
		this.remoteAccessVpnAccount = remoteAccessVpnAccount;
	}

	public String getRemoteAccessVpnDomain() {
		return remoteAccessVpnDomain;
	}

	public void setRemoteAccessVpnDomain(String remoteAccessVpnDomain) {
		this.remoteAccessVpnDomain = remoteAccessVpnDomain;
	}

	public String getRemoteAccessVpnDomainId() {
		return remoteAccessVpnDomainId;
	}

	public void setRemoteAccessVpnDomainId(String remoteAccessVpnDomainId) {
		this.remoteAccessVpnDomainId = remoteAccessVpnDomainId;
	}

	public String getRemoteAccessVpnIpRange() {
		return remoteAccessVpnIpRange;
	}

	public void setRemoteAccessVpnIpRange(String remoteAccessVpnIpRange) {
		this.remoteAccessVpnIpRange = remoteAccessVpnIpRange;
	}

	public String getRemoteAccessVpnPresharedKey() {
		return remoteAccessVpnPresharedKey;
	}

	public void setRemoteAccessVpnPresharedKey(String remoteAccessVpnPresharedKey) {
		this.remoteAccessVpnPresharedKey = remoteAccessVpnPresharedKey;
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

	public String getVpnPublicIp() {
		return vpnPublicIp;
	}

	public void setVpnPublicIp(String vpnPublicIp) {
		this.vpnPublicIp = vpnPublicIp;
	}

	public String getVpnPublicipId() {
		return vpnPublicipId;
	}

	public void setVpnPublicipId(String vpnPublicipId) {
		this.vpnPublicipId = vpnPublicipId;
	}

	public String getRuleState() {
		return ruleState;
	}

	public void setRuleState(String ruleState) {
		this.ruleState = ruleState;
	}	
	
}
