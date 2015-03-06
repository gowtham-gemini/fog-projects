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
    private String name;

    /**
     * the service providers name
     */
    private List<ProviderResponse> providers;

    /**
     * the list of capabilities
     */
    private List<CapabilityResponse> capabilities;

    /**
     * the service provider name
     */
    private String serviceProvider;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CapabilityResponse> getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(List<CapabilityResponse> capabilities) {
        this.capabilities = capabilities;
    }

    public List<ProviderResponse> getProviders() {
        return providers;
    }

    public void setProviders(List<ProviderResponse> capabilities) {
        this.providers = providers;
    }

    public String getServiceProvider() {
        return serviceProvider;
    }

    public void setServiceProvider(String serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

}
