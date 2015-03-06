package com.assistanz.cloud.cloudstack.router;

/**
 * 
 * @author Gowtham
 *
 */
public class StartRouterResponse {
	
	/**
	 * the id of the router
	 */
	String routerId;
	
	/**
	 * the account associated with the router
	 */
	String routerAccount;	
	
	/**
	 * the date and time the router was created
	 */
	String routerCreated;	
	
	/**
	 * the first DNS for the router
	 */
	String routerDnsFirst;
	
	/**
	 * the second DNS for the router
	 */
	String routerDnsSecond;
	
	/**
	 * the domain associated with the router
	 */
	String routerDomain;	
	
	/**
	 * the domain ID associated with the router
	 */
	String routerDomainid;
	
	/**
	 * the gateway for the router
	 */
	String routerGateway;	
	
	/**
	 * the guest IP address for the router
	 */
	String routerGuestIpAddress;	
	
	/**
	 * the guest MAC address for the router
	 */
	String routerGuestMacAddress;	
	
	/**
	 * the guest netmask for the router
	 */
	String routerGuestNetmask;
	
	/**
	 * 	the ID of the corresponding guest network
	 */
	String routerGuestNetworkId;
	
	/**
	 * the host ID for the router
	 */
	String routerHostId;
	
	/**
	 * the hostname for the router
	 */
	String routerHostName;
	
	/**
	 * if this router is an redundant virtual router
	 */
	String routerIsRedundant;	
	
	/**
	 * the link local IP address for the router
	 */
	String routerLinkLocalIp;	
	
	/**
	 * 	the link local MAC address for the router
	 */
	String routerLinkLocalMacAddress;
	
	/**
	 * the link local netmask for the router
	 */
	String routerLinkLocalNetmask;
	
	/**
	 * 	the ID of the corresponding link local network
	 */
	String routerLinkLocalNetworkId;
	
	/**
	 * the name of the router
	 */
	String routerName;
	
	/**
	 * the network domain for the router
	 */
	String routerNetworkDomain;
	
	/**
	 * the Pod ID for the router
	 */
	String routerPodId;
	
	/**
	 * the project name of the address
	 */
	String routerProject;	
	
	/**
	 * the project id of the ipaddress
	 */
	String routerProjectId;
	
	/**
	 * the public IP address for the router
	 */
	String routerPublicIp;
	
	/**
	 * the public MAC address for the router
	 */
	String routerPublicMacAddress;	
	
	/**
	 * the public netmask for the router
	 */
	String routerPublicNetmask;	
	
	/**
	 * the ID of the corresponding public network
	 */
	String routerPublicNetworkId;	
	
	/**
	 * the state of redundant virtual router
	 */
	String routerRedundantState;
	
	/**
	 * the version of scripts
	 */
	String routerScriptsVersion;
	
	/**
	 * the ID of the service offering of the virtual machine
	 */
	String routerServiceOfferingId;	
	
	/**
	 * the name of the service offering of the virtual machine
	 */
	String routerServiceOfferingName;	
	
	/**
	 * the state of the router
	 */
	String routerState;	
	
	/**
	 * the template ID for the router
	 */
	String routerTemplateId;	
	
	/**
	 * the version of template
	 */
	String routerTemplateVersion;	
	
	/**
	 * the Zone ID for the router
	 */
	String routerZoneId;	
	
	/**
	 * the Zone name for the router
	 */
	String routerZoneName;

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

	public String getRouterCreated() {
		return routerCreated;
	}

	public void setRouterCreated(String routerCreated) {
		this.routerCreated = routerCreated;
	}

	public String getRouterDnsFirst() {
		return routerDnsFirst;
	}

	public void setRouterDnsFirst(String routerDnsFirst) {
		this.routerDnsFirst = routerDnsFirst;
	}

	public String getRouterDnsSecond() {
		return routerDnsSecond;
	}

	public void setRouterDnsSecond(String routerDnsSecond) {
		this.routerDnsSecond = routerDnsSecond;
	}

	public String getRouterDomain() {
		return routerDomain;
	}

	public void setRouterDomain(String routerDomain) {
		this.routerDomain = routerDomain;
	}

	public String getRouterDomainid() {
		return routerDomainid;
	}

	public void setRouterDomainid(String routerDomainid) {
		this.routerDomainid = routerDomainid;
	}

	public String getRouterGateway() {
		return routerGateway;
	}

	public void setRouterGateway(String routerGateway) {
		this.routerGateway = routerGateway;
	}

	public String getRouterGuestIpAddress() {
		return routerGuestIpAddress;
	}

	public void setRouterGuestIpAddress(String routerGuestIpAddress) {
		this.routerGuestIpAddress = routerGuestIpAddress;
	}

	public String getRouterGuestMacAddress() {
		return routerGuestMacAddress;
	}

	public void setRouterGuestMacAddress(String routerGuestMacAddress) {
		this.routerGuestMacAddress = routerGuestMacAddress;
	}

	public String getRouterGuestNetmask() {
		return routerGuestNetmask;
	}

	public void setRouterGuestNetmask(String routerGuestNetmask) {
		this.routerGuestNetmask = routerGuestNetmask;
	}

	public String getRouterGuestNetworkId() {
		return routerGuestNetworkId;
	}

	public void setRouterGuestNetworkId(String routerGuestNetworkId) {
		this.routerGuestNetworkId = routerGuestNetworkId;
	}

	public String getRouterHostId() {
		return routerHostId;
	}

	public void setRouterHostId(String routerHostId) {
		this.routerHostId = routerHostId;
	}

	public String getRouterHostName() {
		return routerHostName;
	}

	public void setRouterHostName(String routerHostName) {
		this.routerHostName = routerHostName;
	}

	public String getRouterIsRedundant() {
		return routerIsRedundant;
	}

	public void setRouterIsRedundant(String routerIsRedundant) {
		this.routerIsRedundant = routerIsRedundant;
	}

	public String getRouterLinkLocalIp() {
		return routerLinkLocalIp;
	}

	public void setRouterLinkLocalIp(String routerLinkLocalIp) {
		this.routerLinkLocalIp = routerLinkLocalIp;
	}



	public String getRouterLinkLocalMacAddress() {
		return routerLinkLocalMacAddress;
	}

	public void setRouterLinkLocalMacAddress(String routerLinkLocalMacAddress) {
		this.routerLinkLocalMacAddress = routerLinkLocalMacAddress;
	}

	public String getRouterLinkLocalNetmask() {
		return routerLinkLocalNetmask;
	}

	public void setRouterLinkLocalNetmask(String routerLinkLocalNetmask) {
		this.routerLinkLocalNetmask = routerLinkLocalNetmask;
	}

	public String getRouterLinkLocalNetworkId() {
		return routerLinkLocalNetworkId;
	}

	public void setRouterLinkLocalNetworkId(String routerLinkLocalNetworkId) {
		this.routerLinkLocalNetworkId = routerLinkLocalNetworkId;
	}

	public String getRouterName() {
		return routerName;
	}

	public void setRouterName(String routerName) {
		this.routerName = routerName;
	}

	public String getRouterNetworkDomain() {
		return routerNetworkDomain;
	}

	public void setRouterNetworkDomain(String routerNetworkDomain) {
		this.routerNetworkDomain = routerNetworkDomain;
	}

	public String getRouterPodId() {
		return routerPodId;
	}

	public void setRouterPodId(String routerPodId) {
		this.routerPodId = routerPodId;
	}

	public String getRouterProject() {
		return routerProject;
	}

	public void setRouterProject(String routerProject) {
		this.routerProject = routerProject;
	}

	
	public String getRouterProjectId() {
		return routerProjectId;
	}

	public void setRouterProjectId(String routerProjectId) {
		this.routerProjectId = routerProjectId;
	}

	public String getRouterPublicIp() {
		return routerPublicIp;
	}

	public void setRouterPublicIp(String routerPublicIp) {
		this.routerPublicIp = routerPublicIp;
	}

	public String getRouterPublicMacAddress() {
		return routerPublicMacAddress;
	}

	public void setRouterPublicMacAddress(String routerPublicMacAddress) {
		this.routerPublicMacAddress = routerPublicMacAddress;
	}

	public String getRouterPublicNetmask() {
		return routerPublicNetmask;
	}

	public void setRouterPublicNetmask(String routerPublicNetmask) {
		this.routerPublicNetmask = routerPublicNetmask;
	}

	public String getRouterPublicNetworkId() {
		return routerPublicNetworkId;
	}

	public void setRouterPublicNetworkId(String routerPublicNetworkId) {
		this.routerPublicNetworkId = routerPublicNetworkId;
	}

	public String getRouterRedundantState() {
		return routerRedundantState;
	}

	public void setRouterRedundantState(String routerRedundantState) {
		this.routerRedundantState = routerRedundantState;
	}

	public String getRouterScriptsVersion() {
		return routerScriptsVersion;
	}

	public void setRouterScriptsVersion(String routerScriptsVersion) {
		this.routerScriptsVersion = routerScriptsVersion;
	}

	public String getRouterServiceOfferingId() {
		return routerServiceOfferingId;
	}

	public void setRouterServiceOfferingId(String routerServiceOfferingId) {
		this.routerServiceOfferingId = routerServiceOfferingId;
	}

	public String getRouterServiceOfferingName() {
		return routerServiceOfferingName;
	}

	public void setRouterServiceOfferingName(String routerServiceOfferingName) {
		this.routerServiceOfferingName = routerServiceOfferingName;
	}

	public String getRouterState() {
		return routerState;
	}

	public void setRouterState(String routerState) {
		this.routerState = routerState;
	}

	public String getRouterTemplateId() {
		return routerTemplateId;
	}

	public void setRouterTemplateId(String routerTemplateId) {
		this.routerTemplateId = routerTemplateId;
	}

	public String getRouterTemplateVersion() {
		return routerTemplateVersion;
	}

	public void setRouterTemplateVersion(String routerTemplateVersion) {
		this.routerTemplateVersion = routerTemplateVersion;
	}

	public String getRouterZoneId() {
		return routerZoneId;
	}

	public void setRouterZoneId(String routerZoneId) {
		this.routerZoneId = routerZoneId;
	}

	public String getRouterZoneName() {
		return routerZoneName;
	}

	public void setRouterZoneName(String routerZoneName) {
		this.routerZoneName = routerZoneName;
	}	
	
	

}
