package com.assistanz.cloud.cloudstack.vpc;

import com.assistanz.cloud.cloudstack.CapabilityResponse;
import com.assistanz.cloud.cloudstack.NetworkResponse;
import com.assistanz.cloud.cloudstack.ProviderResponse;
import com.assistanz.cloud.cloudstack.ServiceResponse;
import com.assistanz.cloud.cloudstack.TagsResponse;
import java.util.List;

/**
 *
 * @author Santhosh
 */
public class ListVpcsResponse {

    /**
     * Lists available VPCs
     */
    private List<VpcResponse> vpcs;

    /**
     * the list of networks belonging to the VPC
     */
    private List<NetworkResponse> networks;

    /**
     * the list of supported services
     */
    private List<ServiceResponse> services;

    /**
     * the list of capabilities
     */
    private List<CapabilityResponse> capabilities;

    /**
     * the service provider name
     */
    private List<ProviderResponse> providers;

    /**
     * list of tags associated with the virtual machine
     */
    private List<TagsResponse> tagss;

    public List<VpcResponse> getVpcs() {
        return vpcs;
    }

    public void setVpcs(List<VpcResponse> vpcs) {
        this.vpcs = vpcs;
    }

    public List<NetworkResponse> getNetworks() {
        return networks;
    }

    public void setNetworks(List<NetworkResponse> networks) {
        this.networks = networks;
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

    public List<ProviderResponse> getProviders() {
        return providers;
    }

    public void setProviders(List<ProviderResponse> providers) {
        this.providers = providers;
    }

    public List<TagsResponse> getTagss() {
        return tagss;
    }

    public void setTagss(List<TagsResponse> tagss) {
        this.tagss = tagss;
    }

}
