package com.assistanz.cloud.cloudstack;

/**
 * 
 * @author Gowtham
 *
 */
public class NetworkInterfaceCardResponse {
	
	/**
	 * the ID of the NetworkInterfaceCard
	 */
	private String networkInterfaceCardId;	
	
	/**
	 * the broadcast uri of the NetworkInterfaceCard
	 */
	private String broadcastUri;
	
	/**
	 * the gateway of the NetworkInterfaceCard
	 */
	private String gateway;
	
	/**
	 * the ip address of the NetworkInterfaceCard
	 */
	private String ipAddress;
	
	/**
	 * true if NetworkInterfaceCard is default, false otherwise
	 */
	private String isDefault;
	
	/**
	 * the isolation uri of the NetworkInterfaceCard
	 */
	private String isolationUri;
	
	/**
	 * true if NetworkInterfaceCard is default, false otherwise
	 */
	private String macAddress;
	
	/**
	 * the netmask of the NetworkInterfaceCard
	 */
	private String netMask;
	
	public String getNetworkInterfaceCardId() {
		return networkInterfaceCardId;
	}

	public void setNetworkInterfaceCardId(String networkInterfaceCardId) {
		this.networkInterfaceCardId = networkInterfaceCardId;
	}

	public String getBroadcastUri() {
		return broadcastUri;
	}

	public void setBroadcastUri(String broadcastUri) {
		this.broadcastUri = broadcastUri;
	}

	public String getGateway() {
		return gateway;
	}

	public void setGateway(String gateway) {
		this.gateway = gateway;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	public String getIsolationUri() {
		return isolationUri;
	}

	public void setIsolationUri(String isolationUri) {
		this.isolationUri = isolationUri;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public String getNetMask() {
		return netMask;
	}

	public void setNetMask(String netMask) {
		this.netMask = netMask;
	}

	public String getNetworkId() {
		return networkId;
	}

	public void setNetworkId(String networkId) {
		this.networkId = networkId;
	}

	public String getTrafficType() {
		return trafficType;
	}

	public void setTrafficType(String trafficType) {
		this.trafficType = trafficType;
	}

	public String getNetworkInterfaceCardType() {
		return NetworkInterfaceCardType;
	}

	public void setNetworkInterfaceCardType(String networkInterfaceCardType) {
		NetworkInterfaceCardType = networkInterfaceCardType;
	}

	/**
	 * the ID of the corresponding network
	 */
	private String networkId;
	
	/**
	 * the traffic type of the NetworkInterfaceCard
	 */
	private String trafficType;
	
	/**
	 * the type of the NetworkInterfaceCard
	 */
	private String NetworkInterfaceCardType;	

}
