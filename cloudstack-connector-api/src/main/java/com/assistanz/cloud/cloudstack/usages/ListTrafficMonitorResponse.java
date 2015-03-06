package com.assistanz.cloud.cloudstack.usages;

import java.util.List;

/**
 *
 * @author Gowtham
 *
 */
public class ListTrafficMonitorResponse {

    /**
     * List traffic monitor Hosts
     */
    private List<TrafficMonitorResponse> trafficMonitors;

    public List<TrafficMonitorResponse> getTrafficMonitors() {
        return trafficMonitors;
    }

    public void setTrafficMonitors(List<TrafficMonitorResponse> trafficMonitors) {
        this.trafficMonitors = trafficMonitors;
    }

}
