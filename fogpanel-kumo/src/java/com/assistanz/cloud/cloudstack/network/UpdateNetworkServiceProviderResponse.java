package com.assistanz.cloud.cloudstack.network;

/**
 * 
 * @author Gowtham
 *
 */
public class UpdateNetworkServiceProviderResponse {
	
	/**
	 * uuid of the network provider
	 */
	String networkProviderId;	
	
	/**
	 * 	true if individual services can be enabled/disabled
	 */
	String canEnableIndividualService;
	
	/**
	 * the destination physical network
	 */
	String destinationPhysicalNetworkid;
	
	/**
	 * the provider name
	 */
	String providerName;
	
	/**
	 * the physical network this belongs to
	 */
	String physicalNetworkId;
	
	/**
	 * services for this provider
	 */
	String providerServiceList;	
	
	/**
	 * state of the network provider
	 */
	String networkProviderState;

	public String getNetworkProviderId() {
		return networkProviderId;
	}

	public void setNetworkProviderId(String networkProviderId) {
		this.networkProviderId = networkProviderId;
	}

	public String getCanEnableIndividualService() {
		return canEnableIndividualService;
	}

	public void setCanEnableIndividualService(String canEnableIndividualService) {
		this.canEnableIndividualService = canEnableIndividualService;
	}

	public String getDestinationPhysicalNetworkid() {
		return destinationPhysicalNetworkid;
	}

	public void setDestinationPhysicalNetworkid(String destinationPhysicalNetworkid) {
		this.destinationPhysicalNetworkid = destinationPhysicalNetworkid;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public String getPhysicalNetworkId() {
		return physicalNetworkId;
	}

	public void setPhysicalNetworkId(String physicalNetworkId) {
		this.physicalNetworkId = physicalNetworkId;
	}

	public String getProviderServiceList() {
		return providerServiceList;
	}

	public void setProviderServiceList(String providerServiceList) {
		this.providerServiceList = providerServiceList;
	}

	public String getNetworkProviderState() {
		return networkProviderState;
	}

	public void setNetworkProviderState(String networkProviderState) {
		this.networkProviderState = networkProviderState;
	}	

}
