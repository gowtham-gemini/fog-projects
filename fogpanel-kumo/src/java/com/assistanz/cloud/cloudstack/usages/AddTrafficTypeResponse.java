package com.assistanz.cloud.cloudstack.usages;

/**
 * 
 * @author Gowtham
 *
 */
public class AddTrafficTypeResponse {
	
	/**
	 * The id of the network provider
	 */
	private String networkProviderId;
	
	/**
	 * 	The network name label of the physical device dedicated to this traffic on a KVM host
	 */
	private String kvmNetworkLabel;
	
	/**
	 * The physical network this belongs to
	 */
	private String physicalnetworkid;
	
	/**
	 * The trafficType to be added to the physical network
	 */
	private String traffictype;
	
	/**
	 * 	The network name label of the physical device dedicated to this traffic on a VMware host
	 */
	private String vmwarenetworklabel;
	
	/**
	 * The network name label of the physical device dedicated to this traffic on a XenServer host
	 */
	private String xennetworklabel;

	public String getNetworkProviderId() {
		return networkProviderId;
	}

	public void setNetworkProviderId(String networkProviderId) {
		this.networkProviderId = networkProviderId;
	}

	public String getKvmNetworkLabel() {
		return kvmNetworkLabel;
	}

	public void setKvmNetworkLabel(String kvmNetworkLabel) {
		this.kvmNetworkLabel = kvmNetworkLabel;
	}

	public String getPhysicalnetworkid() {
		return physicalnetworkid;
	}

	public void setPhysicalnetworkid(String physicalnetworkid) {
		this.physicalnetworkid = physicalnetworkid;
	}

	public String getTraffictype() {
		return traffictype;
	}

	public void setTraffictype(String traffictype) {
		this.traffictype = traffictype;
	}

	public String getVmwarenetworklabel() {
		return vmwarenetworklabel;
	}

	public void setVmwarenetworklabel(String vmwarenetworklabel) {
		this.vmwarenetworklabel = vmwarenetworklabel;
	}

	public String getXennetworklabel() {
		return xennetworklabel;
	}

	public void setXennetworklabel(String xennetworklabel) {
		this.xennetworklabel = xennetworklabel;
	}
	
	
}
