package com.assistanz.cloud.cloudstack.domain;

/**
 * 
 * @author Gowtham
 *
 */
public class ListDomainChildrenResponse {
	
	/**
	 * the ID of the domain
	 */
	String domainId;
	
	/**
	 * whether the domain has one or more sub-domains
	 */
	String domainHasChild;
	
	/**
	 * the level of the domain
	 */
	String domainLevel;
	
	/**
	 * the name of the domain
	 */
	String domainName;
	
	/**
	 * the network domain
	 */
	String networkDomain;
	
	/**
	 * the domain ID of the parent domain
	 */
	String parentDomainId;	
	
	/**
	 * the domain name of the parent domain
	 */
	String parentDomainName;
	
	/**
	 * the path of the domain
	 */
	String domainPath;

	public String getDomainId() {
		return domainId;
	}

	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}

	public String getDomainHasChild() {
		return domainHasChild;
	}

	public void setDomainHasChild(String domainHasChild) {
		this.domainHasChild = domainHasChild;
	}

	public String getDomainLevel() {
		return domainLevel;
	}

	public void setDomainLevel(String domainLevel) {
		this.domainLevel = domainLevel;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public String getNetworkDomain() {
		return networkDomain;
	}

	public void setNetworkDomain(String networkDomain) {
		this.networkDomain = networkDomain;
	}

	public String getParentDomainId() {
		return parentDomainId;
	}

	public void setParentDomainId(String parentDomainId) {
		this.parentDomainId = parentDomainId;
	}

	public String getParentDomainName() {
		return parentDomainName;
	}

	public void setParentDomainName(String parentDomainName) {
		this.parentDomainName = parentDomainName;
	}

	public String getDomainPath() {
		return domainPath;
	}

	public void setDomainPath(String domainPath) {
		this.domainPath = domainPath;
	}	

}
