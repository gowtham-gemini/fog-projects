package com.assistanz.cloud.cloudstack.loadbalancer;

/**
 * 
 * @author Gowtham
 *
 */
public class ListNetscalerLoadBalancersResponse {
	
	/**
	 * true if device is inline with firewall device
	 */
	String firewallInlineDevice;
	
	/**
	 * the management IP address of the external load balancer
	 */
	String externalLoadBalancerIpaddress;	
	
	/**
	 * device capacity
	 */
	String loadBalancerDeviceCapacity;
	
	/**
	 * true if device is dedicated for an account
	 */
	String loadBalancerDeviceDedicated;
	
	/**
	 * device id of the netscaler  load balancer
	 */
	String loadBalancerDeviceId;
	
	/**
	 * device name
	 */
	String loadBalancerDeviceName;	
	
	/**
	 * 	device state
	 */
	String loadBalancerDeviceState;
	
	/**
	 * the physical network to which this netscaler  device belongs to
	 */
	String loadBalancerPhysicalNetworkId;
	
	/**
	 * the private interface of the load balancer
	 */
	String loadBalancerPrivateInterface;
	
	/**
	 * name of the provider
	 */
	String loadBalancerProvider;	
	
	/**
	 * the public interface of the load balancer
	 */
	String loadBalancerPublicInterface;

	public String getFirewallInlineDevice() {
		return firewallInlineDevice;
	}

	public void setFirewallInlineDevice(String firewallInlineDevice) {
		this.firewallInlineDevice = firewallInlineDevice;
	}

	public String getExternalLoadBalancerIpaddress() {
		return externalLoadBalancerIpaddress;
	}

	public void setExternalLoadBalancerIpaddress(
			String externalLoadBalancerIpaddress) {
		this.externalLoadBalancerIpaddress = externalLoadBalancerIpaddress;
	}

	public String getLoadBalancerDeviceCapacity() {
		return loadBalancerDeviceCapacity;
	}

	public void setLoadBalancerDeviceCapacity(String loadBalancerDeviceCapacity) {
		this.loadBalancerDeviceCapacity = loadBalancerDeviceCapacity;
	}

	public String getLoadBalancerDeviceDedicated() {
		return loadBalancerDeviceDedicated;
	}

	public void setLoadBalancerDeviceDedicated(String loadBalancerDeviceDedicated) {
		this.loadBalancerDeviceDedicated = loadBalancerDeviceDedicated;
	}

	public String getLoadBalancerDeviceId() {
		return loadBalancerDeviceId;
	}

	public void setLoadBalancerDeviceId(String loadBalancerDeviceId) {
		this.loadBalancerDeviceId = loadBalancerDeviceId;
	}

	public String getLoadBalancerDeviceName() {
		return loadBalancerDeviceName;
	}

	public void setLoadBalancerDeviceName(String loadBalancerDeviceName) {
		this.loadBalancerDeviceName = loadBalancerDeviceName;
	}

	public String getLoadBalancerPhysicalNetworkId() {
		return loadBalancerPhysicalNetworkId;
	}

	public void setLoadBalancerPhysicalNetworkId(
			String loadBalancerPhysicalNetworkId) {
		this.loadBalancerPhysicalNetworkId = loadBalancerPhysicalNetworkId;
	}

	public String getLoadBalancerPrivateInterface() {
		return loadBalancerPrivateInterface;
	}

	public void setLoadBalancerPrivateInterface(String loadBalancerPrivateInterface) {
		this.loadBalancerPrivateInterface = loadBalancerPrivateInterface;
	}

	public String getLoadBalancerProvider() {
		return loadBalancerProvider;
	}

	public void setLoadBalancerProvider(String loadBalancerProvider) {
		this.loadBalancerProvider = loadBalancerProvider;
	}

	public String getLoadBalancerPublicInterface() {
		return loadBalancerPublicInterface;
	}

	public void setLoadBalancerPublicInterface(String loadBalancerPublicInterface) {
		this.loadBalancerPublicInterface = loadBalancerPublicInterface;
	}

	public String getLoadBalancerDeviceState() {
		return loadBalancerDeviceState;
	}

	public void setLoadBalancerDeviceState(String loadBalancerDeviceState) {
		this.loadBalancerDeviceState = loadBalancerDeviceState;
	}

}
