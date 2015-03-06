package com.assistanz.cloud.cloudstack.network;

import java.util.List;

import com.assistanz.cloud.cloudstack.CapabilityResponse;

/**
 * 
 * @author Gowtham
 *
 */
public class ListSupportedNetworkServicesResponse {
	
	/**
	 * the service name
	 */
	String serviceName;
	
	/**
	 * the service provider name
	 */
	String serviceProvider;
	
	/**
	 * 	the list of capabilities
	 */
	private List<CapabilityResponse> capabilities;

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceProvider() {
		return serviceProvider;
	}

	public void setServiceProvider(String serviceProvider) {
		this.serviceProvider = serviceProvider;
	}

        public List<CapabilityResponse> getCapabilities() {
            return capabilities;
        }

        public void setCapabilities(List<CapabilityResponse> capabilities) {
            this.capabilities = capabilities;
        }

	
}
