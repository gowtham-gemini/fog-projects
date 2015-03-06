package com.assistanz.cloud.cloudstack.ldapconfig;

/**
 * 
 * @author Gowtham
 *
 */
public class LDAPConfigServiceResponse {
	
	/**
	 * Specify the distinguished name of a user with the search permission on the directory
	 */
	private String bindDistinguishedName;
	
	/**
	 * DN password
	 */
	private String bindDistinguishedNamePass;
	
	/**
	 * Hostname or ip address of the ldap server eg: my.ldap.com
	 */
	private String hostName;
	
	/**
	 * Specify the LDAP port if required, default is 389
	 */
	private String LdpaPort;
	
	/**
	 * Check Use SSL if the external LDAP server is configured for LDAP over SSL
	 */
	private String SslPort;
	
	/**
	 * You specify a query filter here, which narrows down the users, who can be part of this domain
	 */
	private String queryFilter;	
	
	/**
	 * The search base defines the starting point for the search in the directory tree Example: dc=cloud,dc=com
	 */
	private String searchBase;

	public String getBindDistinguishedName() {
		return bindDistinguishedName;
	}

	public void setBindDistinguishedName(String bindDistinguishedName) {
		this.bindDistinguishedName = bindDistinguishedName;
	}

	public String getBindDistinguishedNamePass() {
		return bindDistinguishedNamePass;
	}

	public void setBindDistinguishedNamePass(String bindDistinguishedNamePass) {
		this.bindDistinguishedNamePass = bindDistinguishedNamePass;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getLdpaPort() {
		return LdpaPort;
	}

	public void setLdpaPort(String ldpaPort) {
		LdpaPort = ldpaPort;
	}

	public String getSslPort() {
		return SslPort;
	}

	public void setSslPort(String sslPort) {
		SslPort = sslPort;
	}

	public String getQueryFilter() {
		return queryFilter;
	}

	public void setQueryFilter(String queryFilter) {
		this.queryFilter = queryFilter;
	}

	public String getSearchBase() {
		return searchBase;
	}

	public void setSearchBase(String searchBase) {
		this.searchBase = searchBase;
	}
}
