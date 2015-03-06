package com.assistanz.cloud.cloudstack;

/**
 *
 * @author Santhosh
 */
public class ProviderResponse {

    /**
     * uuid of the network provider
     */
    private String id;

    /**
     * true if individual services can be enabled/disabled
     */
    private String canEnableIndividualService;

    /**
     * the destination physical network
     */
    private String destinationPhysicalNetworkId;

    /**
     * the provider name
     */
    private String name;

    /**
     * the physical network this belongs to
     */
    private String physicalNetworkId;

    /**
     * services for this provider
     */
    private String serviceList;

    /**
     * state of the network provider
     */
    private String state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCanEnableIndividualService() {
        return canEnableIndividualService;
    }

    public void setCanEnableIndividualService(String canEnableIndividualService) {
        this.canEnableIndividualService = canEnableIndividualService;
    }

    public String getDestinationPhysicalNetworkId() {
        return destinationPhysicalNetworkId;
    }

    public void setDestinationPhysicalNetworkId(String destinationPhysicalNetworkId) {
        this.destinationPhysicalNetworkId = destinationPhysicalNetworkId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhysicalNetworkId() {
        return physicalNetworkId;
    }

    public void setPhysicalNetworkId(String physicalNetworkId) {
        this.physicalNetworkId = physicalNetworkId;
    }

    public String getServiceList() {
        return serviceList;
    }

    public void setServiceList(String serviceList) {
        this.serviceList = serviceList;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
