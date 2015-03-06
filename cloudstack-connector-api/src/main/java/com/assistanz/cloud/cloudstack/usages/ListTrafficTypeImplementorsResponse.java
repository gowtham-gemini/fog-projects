package com.assistanz.cloud.cloudstack.usages;

import java.util.List;

/**
 *
 * @author Gowtham
 *
 */
public class ListTrafficTypeImplementorsResponse {

    /**
     * Lists implementors of implementor of a network traffic type or implementors of all network traffic types
     */
    private List<TrafficTypeImplementorResponse> trafficTypeImplementors;

    public List<TrafficTypeImplementorResponse> getTrafficTypeImplementors() {
        return trafficTypeImplementors;
    }

    public void setTrafficTypeImplementors(List<TrafficTypeImplementorResponse> trafficTypeImplementors) {
        this.trafficTypeImplementors = trafficTypeImplementors;
    }

}
