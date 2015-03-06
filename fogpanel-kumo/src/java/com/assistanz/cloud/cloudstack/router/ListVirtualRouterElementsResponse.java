package com.assistanz.cloud.cloudstack.router;

/**
 * 
 * @author Gowtham
 *
 */
public class ListVirtualRouterElementsResponse {
	
	/**
	 * the id of the router
	 */
	String routerId;	
	
	/**
	 * the account associated with the provider
	 */
	String routerAccount;
	
	/**
	 * the domain associated with the provider
	 */
	String routerDomain;
	
	/**
	 * the domain ID associated with the provider
	 */
	String routerDomainId;
	
	/**
	 * Enabled/Disabled the service provider
	 */
	String routerEnabled;
	
	/**
	 * the physical network service provider id of the provider
	 */
	String networkServiceProviderId;
	
	/**
	 * the project name of the address
	 */
	String addressProjectName;
	
	/**
	 * the project id of the ipaddress
	 */
	String IpaddressProjectId;

	public String getRouterId() {
		return routerId;
	}

	public void setRouterId(String routerId) {
		this.routerId = routerId;
	}

	public String getRouterAccount() {
		return routerAccount;
	}

	public void setRouterAccount(String routerAccount) {
		this.routerAccount = routerAccount;
	}

	public String getRouterDomain() {
		return routerDomain;
	}

	public void setRouterDomain(String routerDomain) {
		this.routerDomain = routerDomain;
	}

	public String getRouterDomainId() {
		return routerDomainId;
	}

	public void setRouterDomainId(String routerDomainId) {
		this.routerDomainId = routerDomainId;
	}

	public String getRouterEnabled() {
		return routerEnabled;
	}

	public void setRouterEnabled(String routerEnabled) {
		this.routerEnabled = routerEnabled;
	}

	public String getNetworkServiceProviderId() {
		return networkServiceProviderId;
	}

	public void setNetworkServiceProviderId(String networkServiceProviderId) {
		this.networkServiceProviderId = networkServiceProviderId;
	}

	public String getAddressProjectName() {
		return addressProjectName;
	}

	public void setAddressProjectName(String addressProjectName) {
		this.addressProjectName = addressProjectName;
	}

	public String getIpaddressProjectId() {
		return IpaddressProjectId;
	}

	public void setIpaddressProjectId(String ipaddressProjectId) {
		IpaddressProjectId = ipaddressProjectId;
	}	

}
