package com.assistanz.cloud.cloudstack;

import java.util.List;

/**
 * 
 * @author Gowtham
 *
 */
public class ServiceResponse {
	
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
    
    /**
     * the service providers name
     */
    private List<ProviderResponse> providers;

    public List<CapabilityResponse> getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(List<CapabilityResponse> capabilities) {
        this.capabilities = capabilities;
    }

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

    public List<ProviderResponse> getProviders() {
        return providers;
    }

    public void setProviders(List<ProviderResponse> providers) {
        this.providers = providers;
    }
    
    

}
