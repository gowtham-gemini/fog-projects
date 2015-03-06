package com.assistanz.cloud.cloudstack.usages;

import java.util.List;

/**
 *
 * @author Gowtham
 *
 */
public class ListTrafficTypesResponse {

    /**
     * Lists traffic types of a given physical network
     */
    private List<TrafficTypeResponse> trafficTypes;

    public List<TrafficTypeResponse> getTrafficTypes() {
        return trafficTypes;
    }

    public void setTrafficTypes(List<TrafficTypeResponse> trafficTypes) {
        this.trafficTypes = trafficTypes;
    }

}
