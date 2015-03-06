package com.assistanz.cloud.cloudstack.address;

/**
 * 
 * @author Gowtham
 *
 */
public class AssociateIpAddressResponse {
	
	/**
	 * public IP address id
	 */
	String publicIpAddressId;
	
	/**
	 * the account the public IP address is associated with
	 */
	String publicIpAddressAccount;	
	
	/**
	 * date the public IP address was acquired
	 */
	String publicIpAddressAllocated;
	
	/**
	 * the ID of the Network associated with the IP address
	 */
	String publicIpAddressAssociatedNetworkid;	
	
	/**
	 * the domain the public IP address is associated with
	 */
	String publicIpAddressDomain;	
	
	/**
	 * the domain ID the public IP address is associated with
	 */
	String publicIpAddressDomainId;
	
	/**
	 * the virtual network for the IP address
	 */
	String publicIpAddressForVirtualnetwork;
	
	/**
	 * public IP address
	 */
	String publicIpAddress;
	
	/**
	 * true if the IP address is a source nat address, false otherwise
	 */
	String publicIpAddressIsSourceNat;	
	
	/**
	 * true if this ip is for static nat, false otherwise
	 */
	String publicIpAddressIsStaticNat;
	
	/**
	 * true if this ip is system ip (was allocated as a part of deployVm or createLbRule)
	 */
	String publicIpAddressIsSystem;	
	
	/**
	 * the ID of the Network where ip belongs to
	 */
	String publicIpAddressNetworkId;
	
	/**
	 * the physical network this belongs to
	 */
	String publicIpAddressPhysicalNetworkId;
	
	/**
	 * the project name of the address
	 */
	String publicIpAddressProjectName;	
	
	/**
	 * 	the project id of the ipaddress
	 */
	String publicIpAddressProjectId;
	
	/**
	 * purpose of the IP address. In Acton this value is not null for Ips with isSystem=true, and can have either StaticNat or LB value
	 */
	String publicIpAddressPurpose;	
	
	/**
	 * State of the ip address. Can be: Allocatin, Allocated and Releasing
	 */
	String publicIpAddressState;
	
	/**
	 * 	virutal machine display name the ip address is assigned to (not null only for static nat Ip)
	 */
	String publicIpAddressVirtualMachineDisplayName;
	
	/**
	 * virutal machine id the ip address is assigned to (not null only for static nat Ip)
	 */
	String publicIpAddressVirtualMachineId;
	
	/**
	 * virutal machine name the ip address is assigned to (not null only for static nat Ip)
	 */
	String publicIpAddressVirtualMachineName;	
	
	/**
	 * the ID of the VLAN associated with the IP address
	 */
	String publicIpAddressVlanId;	
	
	/**
	 * the VLAN associated with the IP address
	 */
	String publicIpAddressVlanName;
	
	/**
	 * the ID of the zone the public IP address belongs to
	 */
	String publicIpAddressZoneId;
	
	/**
	 * the name of the zone the public IP address belongs to
	 */
	String publicIpAddressZoneName;
	
	/**
	 * the ID of the latest async job acting on this object
	 */
	String publicIpAddressJobId;	
	
	/**
	 * the current status of the latest async job acting on this object
	 */
	String publicIpAddressJobStatus;

	public String getPublicIpAddressId() {
		return publicIpAddressId;
	}

	public void setPublicIpAddressId(String publicIpAddressId) {
		this.publicIpAddressId = publicIpAddressId;
	}

	public String getPublicIpAddressAccount() {
		return publicIpAddressAccount;
	}

	public void setPublicIpAddressAccount(String publicIpAddressAccount) {
		this.publicIpAddressAccount = publicIpAddressAccount;
	}

	public String getPublicIpAddressAllocated() {
		return publicIpAddressAllocated;
	}

	public void setPublicIpAddressAllocated(String publicIpAddressAllocated) {
		this.publicIpAddressAllocated = publicIpAddressAllocated;
	}

	public String getPublicIpAddressAssociatedNetworkid() {
		return publicIpAddressAssociatedNetworkid;
	}

	public void setPublicIpAddressAssociatedNetworkid(
			String publicIpAddressAssociatedNetworkid) {
		this.publicIpAddressAssociatedNetworkid = publicIpAddressAssociatedNetworkid;
	}

	public String getPublicIpAddressDomain() {
		return publicIpAddressDomain;
	}

	public void setPublicIpAddressDomain(String publicIpAddressDomain) {
		this.publicIpAddressDomain = publicIpAddressDomain;
	}

	public String getPublicIpAddressDomainId() {
		return publicIpAddressDomainId;
	}

	public void setPublicIpAddressDomainId(String publicIpAddressDomainId) {
		this.publicIpAddressDomainId = publicIpAddressDomainId;
	}

	public String getPublicIpAddressForVirtualnetwork() {
		return publicIpAddressForVirtualnetwork;
	}

	public void setPublicIpAddressForVirtualnetwork(
			String publicIpAddressForVirtualnetwork) {
		this.publicIpAddressForVirtualnetwork = publicIpAddressForVirtualnetwork;
	}

	public String getPublicIpAddress() {
		return publicIpAddress;
	}

	public void setPublicIpAddress(String publicIpAddress) {
		this.publicIpAddress = publicIpAddress;
	}

	public String getPublicIpAddressIsSourceNat() {
		return publicIpAddressIsSourceNat;
	}

	public void setPublicIpAddressIsSourceNat(String publicIpAddressIsSourceNat) {
		this.publicIpAddressIsSourceNat = publicIpAddressIsSourceNat;
	}

	public String getPublicIpAddressIsStaticNat() {
		return publicIpAddressIsStaticNat;
	}

	public void setPublicIpAddressIsStaticNat(String publicIpAddressIsStaticNat) {
		this.publicIpAddressIsStaticNat = publicIpAddressIsStaticNat;
	}

	public String getPublicIpAddressIsSystem() {
		return publicIpAddressIsSystem;
	}

	public void setPublicIpAddressIsSystem(String publicIpAddressIsSystem) {
		this.publicIpAddressIsSystem = publicIpAddressIsSystem;
	}

	public String getPublicIpAddressNetworkId() {
		return publicIpAddressNetworkId;
	}

	public void setPublicIpAddressNetworkId(String publicIpAddressNetworkId) {
		this.publicIpAddressNetworkId = publicIpAddressNetworkId;
	}

	public String getPublicIpAddressPhysicalNetworkId() {
		return publicIpAddressPhysicalNetworkId;
	}

	public void setPublicIpAddressPhysicalNetworkId(
			String publicIpAddressPhysicalNetworkId) {
		this.publicIpAddressPhysicalNetworkId = publicIpAddressPhysicalNetworkId;
	}

	public String getPublicIpAddressProjectName() {
		return publicIpAddressProjectName;
	}

	public void setPublicIpAddressProjectName(String publicIpAddressProjectName) {
		this.publicIpAddressProjectName = publicIpAddressProjectName;
	}

	public String getPublicIpAddressProjectId() {
		return publicIpAddressProjectId;
	}

	public void setPublicIpAddressProjectId(String publicIpAddressProjectId) {
		this.publicIpAddressProjectId = publicIpAddressProjectId;
	}

	public String getPublicIpAddressPurpose() {
		return publicIpAddressPurpose;
	}

	public void setPublicIpAddressPurpose(String publicIpAddressPurpose) {
		this.publicIpAddressPurpose = publicIpAddressPurpose;
	}

	public String getPublicIpAddressState() {
		return publicIpAddressState;
	}

	public void setPublicIpAddressState(String publicIpAddressState) {
		this.publicIpAddressState = publicIpAddressState;
	}

	public String getPublicIpAddressVirtualMachineDisplayName() {
		return publicIpAddressVirtualMachineDisplayName;
	}

	public void setPublicIpAddressVirtualMachineDisplayName(
			String publicIpAddressVirtualMachineDisplayName) {
		this.publicIpAddressVirtualMachineDisplayName = publicIpAddressVirtualMachineDisplayName;
	}

	public String getPublicIpAddressVirtualMachineId() {
		return publicIpAddressVirtualMachineId;
	}

	public void setPublicIpAddressVirtualMachineId(
			String publicIpAddressVirtualMachineId) {
		this.publicIpAddressVirtualMachineId = publicIpAddressVirtualMachineId;
	}

	public String getPublicIpAddressVirtualMachineName() {
		return publicIpAddressVirtualMachineName;
	}

	public void setPublicIpAddressVirtualMachineName(
			String publicIpAddressVirtualMachineName) {
		this.publicIpAddressVirtualMachineName = publicIpAddressVirtualMachineName;
	}

	public String getPublicIpAddressVlanId() {
		return publicIpAddressVlanId;
	}

	public void setPublicIpAddressVlanId(String publicIpAddressVlanId) {
		this.publicIpAddressVlanId = publicIpAddressVlanId;
	}

	public String getPublicIpAddressVlanName() {
		return publicIpAddressVlanName;
	}

	public void setPublicIpAddressVlanName(String publicIpAddressVlanName) {
		this.publicIpAddressVlanName = publicIpAddressVlanName;
	}

	public String getPublicIpAddressZoneId() {
		return publicIpAddressZoneId;
	}

	public void setPublicIpAddressZoneId(String publicIpAddressZoneId) {
		this.publicIpAddressZoneId = publicIpAddressZoneId;
	}

	public String getPublicIpAddressZoneName() {
		return publicIpAddressZoneName;
	}

	public void setPublicIpAddressZoneName(String publicIpAddressZoneName) {
		this.publicIpAddressZoneName = publicIpAddressZoneName;
	}

	public String getPublicIpAddressJobId() {
		return publicIpAddressJobId;
	}

	public void setPublicIpAddressJobId(String publicIpAddressJobId) {
		this.publicIpAddressJobId = publicIpAddressJobId;
	}

	public String getPublicIpAddressJobStatus() {
		return publicIpAddressJobStatus;
	}

	public void setPublicIpAddressJobStatus(String publicIpAddressJobStatus) {
		this.publicIpAddressJobStatus = publicIpAddressJobStatus;
	}	
	
}
