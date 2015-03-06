package com.assistanz.cloud.cloudstack.networkoffering;

import com.assistanz.cloud.cloudstack.CapabilityResponse;
import com.assistanz.cloud.cloudstack.ServiceResponse;
import com.assistanz.cloud.cloudstack.ProviderResponse;
import java.util.List;

/**
 *
 * @author Santhosh
 */
public class CreateNetworkOfferingResponse {

    /**
     * the id of the network offering
     */
    private String id;

    /**
     * availability of the network offering
     */
    private String availability;

    /**
     * true if network offering is IP conserve mode enabled
     */
    private String conserveMode;

    /**
     * the date this network offering was created
     */
    private String created;

    /**
     * additional key/value details tied with network offering
     */
    private String details;

    /**
     * displaytext from XML and set the displaytext of the zone
     */
    private String displayText;

    /**
     * true if network offering supports persistent networks, false otherwise
     */
    private String egressDefaultPolicy;

    /**
     * true if network offering can be used by VPC networks only
     */
    private String forVpc;

    /**
     * guestiptype from XML and set guest type of the network offering, can be Shared or Isolated
     */
    private String guestIpType;

    /**
     * true if network offering is default, false otherwise
     */
    private String isDefault;

    /**
     * true if network offering supports persistent networks, false otherwise
     */
    private String isPersistent;

    /**
     * maximum number of concurrents connections to be handled by lb
     */
    private String maxConnections;

    /**
     * the name of the network offering
     */
    private String name;

    /**
     * data transfer rate in megabits per second allowed
     */
    private String networkRate;

    /**
     * the ID of the service offering used by virtual router provider
     */
    private String serviceOfferingId;

    /**
     * true if network offering supports specifying ip ranges, false otherwise
     */
    private String specifyIpRanges;

    /**
     * true if network offering supports specifying ip ranges, false otherwise
     */
    private String specifyVlan;

    /**
     * state of the network offering Can be Disabled/Enabled/Inactive
     */
    private String state;

    /**
     * the tags for the network offering
     */
    private String tags;

    /**
     * the traffic type for the network offering
     */
    private String trafficType;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getConserveMode() {
        return conserveMode;
    }

    public void setConserveMode(String conserveMode) {
        this.conserveMode = conserveMode;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDisplayText() {
        return displayText;
    }

    public void setDisplayText(String displayText) {
        this.displayText = displayText;
    }

    public String getEgressDefaultPolicy() {
        return egressDefaultPolicy;
    }

    public void setEgressDefaultPolicy(String egressDefaultPolicy) {
        this.egressDefaultPolicy = egressDefaultPolicy;
    }

    public String getForVpc() {
        return forVpc;
    }

    public void setForVpc(String forVpc) {
        this.forVpc = forVpc;
    }

    public String getGuestIpType() {
        return guestIpType;
    }

    public void setGuestIpType(String guestIpType) {
        this.guestIpType = guestIpType;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    public String getIsPersistent() {
        return isPersistent;
    }

    public void setIsPersistent(String isPersistent) {
        this.isPersistent = isPersistent;
    }

    public String getMaxConnections() {
        return maxConnections;
    }

    public void setMaxConnections(String maxConnections) {
        this.maxConnections = maxConnections;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getSpecifyIpRanges() {
        return specifyIpRanges;
    }

    public void setSpecifyIpRanges(String specifyIpRanges) {
        this.specifyIpRanges = specifyIpRanges;
    }

    public String getSpecifyVlan() {
        return specifyVlan;
    }

    public void setSpecifyVlan(String specifyVlan) {
        this.specifyVlan = specifyVlan;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getTrafficType() {
        return trafficType;
    }

    public void setTrafficType(String trafficType) {
        this.trafficType = trafficType;
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
