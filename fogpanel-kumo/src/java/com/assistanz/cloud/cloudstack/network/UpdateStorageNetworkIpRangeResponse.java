package com.assistanz.cloud.cloudstack.network;

/**
 * 
 * @author Gowtham
 *
 */
public class UpdateStorageNetworkIpRangeResponse {
	
	/**
	 * the uuid of storage network IP range.
	 */
	String storageNetworkId;	
	
	/**
	 * the end ip of the storage network IP range
	 */
	String storageNetworkEndIp;
	
	/**
	 * the gateway of the storage network IP range
	 */
	String storageNetworkGateway;	
	
	/**
	 * the netmask of the storage network IP range
	 */
	String storageNetworkNetmask;	
	
	/**
	 * the network uuid of storage network IP range
	 */
	String networkId;
	
	/**
	 * the Pod uuid for the storage network IP range
	 */
	String storageNetworkPodId;
	
	/**
	 * the start ip of the storage network IP range
	 */
	String storageNetworkStartIp;
		
	/**
	 * the ID or VID of the VLAN.
	 */
	String storageNetworkVlan;
	
	/**
	 * the Zone uuid of the storage network IP range
	 */
	String storageNetworkZoneId;

	public String getStorageNetworkId() {
		return storageNetworkId;
	}

	public void setStorageNetworkId(String storageNetworkId) {
		this.storageNetworkId = storageNetworkId;
	}
	
	public String getNetworkId() {
		return networkId;
	}

	public void setNetworkId(String networkId) {
		this.networkId = networkId;
	}

	
	public String getStorageNetworkEndIp() {
		return storageNetworkEndIp;
	}

	public void setStorageNetworkEndIp(String storageNetworkEndIp) {
		this.storageNetworkEndIp = storageNetworkEndIp;
	}

	public String getStorageNetworkGateway() {
		return storageNetworkGateway;
	}

	public void setStorageNetworkGateway(String storageNetworkGateway) {
		this.storageNetworkGateway = storageNetworkGateway;
	}

	public String getStorageNetworkNetmask() {
		return storageNetworkNetmask;
	}

	public void setStorageNetworkNetmask(String storageNetworkNetmask) {
		this.storageNetworkNetmask = storageNetworkNetmask;
	}

	
	public String getStorageNetworkPodId() {
		return storageNetworkPodId;
	}

	public void setStorageNetworkPodId(String storageNetworkPodId) {
		this.storageNetworkPodId = storageNetworkPodId;
	}

	public String getStorageNetworkStartIp() {
		return storageNetworkStartIp;
	}

	public void setStorageNetworkStartIp(String storageNetworkStartIp) {
		this.storageNetworkStartIp = storageNetworkStartIp;
	}

	public String getStorageNetworkVlan() {
		return storageNetworkVlan;
	}

	public void setStorageNetworkVlan(String storageNetworkVlan) {
		this.storageNetworkVlan = storageNetworkVlan;
	}

	public String getStorageNetworkZoneId() {
		return storageNetworkZoneId;
	}

	public void setStorageNetworkZoneId(String storageNetworkZoneId) {
		this.storageNetworkZoneId = storageNetworkZoneId;
	}	

}
