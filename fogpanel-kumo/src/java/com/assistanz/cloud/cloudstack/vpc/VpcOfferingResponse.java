package com.assistanz.cloud.cloudstack.vpc;

import com.assistanz.cloud.cloudstack.CapabilityResponse;
import com.assistanz.cloud.cloudstack.ProviderResponse;
import com.assistanz.cloud.cloudstack.ServiceResponse;
import java.util.List;

/**
 *
 * @author Santhosh
 */
public class VpcOfferingResponse {

    /**
     * the id of the vpc offering
     */
    private String vpcId;

    /**
     * the date this vpc offering was created
     */
    private String created;

    /**
     * an alternate display text of the vpc offering
     */
    private String displayText;

    /**
     * true if vpc offering is default, false otherwise
     */
    private String isDefault;

    /**
     * the name of the vpc offering
     */
    private String vpcOfferingName;

    /**
     * state of the vpc offering Can be Disabled/Enabled
     */
    private String state;

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

    public String getVpcId() {
        return vpcId;
    }

    public void setVpcId(String vpcId) {
        this.vpcId = vpcId;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getDisplayText() {
        return displayText;
    }

    public void setDisplayText(String displayText) {
        this.displayText = displayText;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    public String getVpcOfferingName() {
        return vpcOfferingName;
    }

    public void setVpcOfferingName(String vpcOfferingName) {
        this.vpcOfferingName = vpcOfferingName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

}
