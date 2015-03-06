package com.assistanz.cloud.cloudstack.network;

import com.assistanz.cloud.cloudstack.CapabilityResponse;
import com.assistanz.cloud.cloudstack.ProviderResponse;
import java.util.List;

/**
 *
 * @author Santhosh
 */
public class NetworkServicesResponse {

    /**
     * the service name
     */
    private String serviceName;

    /**
     * the list of capabilities
     */
    private List<CapabilityResponse> capabilities;

    /**
     * the service provider name
     */
    private List<ProviderResponse> providers;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
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

    public void setProviders(List<ProviderResponse> providers) {
        this.providers = providers;
    }

}
