package com.assistanz.cloud.cloudstack.network;

import java.util.List;
import com.assistanz.cloud.cloudstack.CapabilityResponse;
import com.assistanz.cloud.cloudstack.ServiceResponse;

/**
 * 
 * @author Gowtham
 *
 */
public class UpdateNetworkOfferingResponse {
	
	/**
	 * the id of the network offering
	 */
	String  networkOfferingId;
	
	/**
	 * availability of the network offering
	 */
	String  networkOfferingAvailability;
	
	/**
	 * true if network offering is ip conserve mode enabled
	 */
	String  networkOfferingConserveMode;
	
	/**
	 * 	the date this network offering was created
	 */
	String  networkOfferingCreated;
	
	/**
	 * an alternate display text of the network offering.
	 */
	String  networkOfferingDisplayText;	
	
	/**
	 * guest type of the network offering, can be Shared or Isolated
	 */
	String  networkOfferingGuestIpType;
	
	/**
	 * true if network offering is default, false otherwise
	 */
	String  networkOfferingIsDefault;
	
	/**
	 * the name of the network offering
	 */
	String  networkOfferingName;
	
	/**
	 * 	data transfer rate in megabits per second allowed.
	 */
	String networkRate;
	
	/**
	 * the ID of the service offering used by virtual router provider
	 */
	String serviceOfferingId;
	
	/**
	 * true if network offering supports specifying ip ranges, false otherwise
	 */
	String  networkOfferingSpecifyIpRanges;	
	
	/**
	 * 	true if network offering supports vlans, false otherwise
	 */
	String  networkOfferingSpecifyVlan;
	
	/**
	 * state of the network offering. Can be Disabled/Enabled/Inactive
	 */
	String  networkOfferingState;
	
	/**
	 * the tags for the network offering
	 */
	String  networkOfferingTags;
        
        	
	/**
	 * 	the traffic type for the network offering, supported types are Public, Management, Control, Guest, Vlan or Storage.
	 */
	String  networkOfferingTrafficType;
	
	/**
	 * 	the list of supported services
	 */
	private List<ServiceResponse> services;
	
	/**
	 * 	the list of capabilities
	 */
	private List<CapabilityResponse> capabilities;

	public String getNetworkOfferingId() {
		return networkOfferingId;
	}

	public void setNetworkOfferingId(String networkOfferingId) {
		this.networkOfferingId = networkOfferingId;
	}

	public String getNetworkOfferingAvailability() {
		return networkOfferingAvailability;
	}

	public void setNetworkOfferingAvailability(String networkOfferingAvailability) {
		this.networkOfferingAvailability = networkOfferingAvailability;
	}

	public String getNetworkOfferingConserveMode() {
		return networkOfferingConserveMode;
	}

	public void setNetworkOfferingConserveMode(String networkOfferingConserveMode) {
		this.networkOfferingConserveMode = networkOfferingConserveMode;
	}

	public String getNetworkOfferingCreated() {
		return networkOfferingCreated;
	}

	public void setNetworkOfferingCreated(String networkOfferingCreated) {
		this.networkOfferingCreated = networkOfferingCreated;
	}

	public String getNetworkOfferingDisplayText() {
		return networkOfferingDisplayText;
	}

	public void setNetworkOfferingDisplayText(String networkOfferingDisplayText) {
		this.networkOfferingDisplayText = networkOfferingDisplayText;
	}

	public String getNetworkOfferingGuestIpType() {
		return networkOfferingGuestIpType;
	}

	public void setNetworkOfferingGuestIpType(String networkOfferingGuestIpType) {
		this.networkOfferingGuestIpType = networkOfferingGuestIpType;
	}

	public String getNetworkOfferingIsDefault() {
		return networkOfferingIsDefault;
	}

	public void setNetworkOfferingIsDefault(String networkOfferingIsDefault) {
		this.networkOfferingIsDefault = networkOfferingIsDefault;
	}

	public String getNetworkOfferingName() {
		return networkOfferingName;
	}

	public void setNetworkOfferingName(String networkOfferingName) {
		this.networkOfferingName = networkOfferingName;
	}

	public String getNetworkRate() {
		return networkRate;
	}

	public void setNetworkRate(String networkRate) {
		this.networkRate = networkRate;
	}

	public String getServiceOfferingId() {
		return serviceOfferingId;
	}

	public void setServiceOfferingId(String serviceOfferingId) {
		this.serviceOfferingId = serviceOfferingId;
	}

	public String getNetworkOfferingSpecifyIpRanges() {
		return networkOfferingSpecifyIpRanges;
	}

	public void setNetworkOfferingSpecifyIpRanges(
			String networkOfferingSpecifyIpRanges) {
		this.networkOfferingSpecifyIpRanges = networkOfferingSpecifyIpRanges;
	}

	public String getNetworkOfferingSpecifyVlan() {
		return networkOfferingSpecifyVlan;
	}

	public void setNetworkOfferingSpecifyVlan(String networkOfferingSpecifyVlan) {
		this.networkOfferingSpecifyVlan = networkOfferingSpecifyVlan;
	}

	public String getNetworkOfferingState() {
		return networkOfferingState;
	}

	public void setNetworkOfferingState(String networkOfferingState) {
		this.networkOfferingState = networkOfferingState;
	}

	public String getNetworkOfferingTags() {
		return networkOfferingTags;
	}

	public void setNetworkOfferingTags(String networkOfferingTags) {
		this.networkOfferingTags = networkOfferingTags;
	}
        
       	public String getNetworkOfferingTrafficType() {
		return networkOfferingTrafficType;
	}

	public void setNetworkOfferingTrafficType(String networkOfferingTrafficType) {
		this.networkOfferingTrafficType = networkOfferingTrafficType;
	}

	
	public List<ServiceResponse> getServices() {
		return services;
	}

	public void setServices(List<ServiceResponse> services) {
		this.services = services;
	}

        public List<CapabilityResponse> getCapabilities() {
            return capabilities;
        }

        public void setCapabilities(List<CapabilityResponse> capabilities) {
            this.capabilities = capabilities;
        }


}
