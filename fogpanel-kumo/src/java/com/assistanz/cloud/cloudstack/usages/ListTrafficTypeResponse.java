package com.assistanz.cloud.cloudstack.usages;

/**
 * 
 * @author Gowtham
 *
 */
public class ListTrafficTypeResponse {
	
	/**
	 * The Id of the network provider
	 */
	private String NetworkProviderId;
	
	/**
	 * true if individual services can be enabled/disabled
	 */
	private String canEnableIndividualService;
	
	/**
	 * the destination physical network
	 */
	private String destinationPhysicalNetworkId;
	
	/**
	 * the provider name
	 */
	private String providerName;
	
	/**
	 * the physical network this belongs to
	 */
	private String physicalNetworkId;
	
	/**
	 * 	services for this provider
	 */
	private String serviceList;
	
	/**
	 * 	state of the network provider
	 */
	private String state;
	
	public String getNetworkProviderId() {
		return NetworkProviderId;
	}

	public void setNetworkProviderId(String networkProviderId) {
		NetworkProviderId = networkProviderId;
	}

	public String getCanEnableIndividualService() {
		return canEnableIndividualService;
	}

	public void setCanEnableIndividualService(String canEnableIndividualService) {
		this.canEnableIndividualService = canEnableIndividualService;
	}

	public String getDestinationPhysicalNetworkId() {
		return destinationPhysicalNetworkId;
	}

	public void setDestinationPhysicalNetworkId(String destinationPhysicalNetworkId) {
		this.destinationPhysicalNetworkId = destinationPhysicalNetworkId;
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

	public String getServiceList() {
		return serviceList;
	}

	public void setServiceList(String serviceList) {
		this.serviceList = serviceList;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
