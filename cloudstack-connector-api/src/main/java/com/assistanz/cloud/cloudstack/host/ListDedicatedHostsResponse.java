package com.assistanz.cloud.cloudstack.host;

import java.util.List;

/**
 *
 * @author Santhosh
 *
 */
public class ListDedicatedHostsResponse {

    /**
     * Lists dedicated hosts
     */
    private List<DedicatedHostResponse> dedicatedHosts;

    public List<DedicatedHostResponse> getDedicatedHosts() {
        return dedicatedHosts;
    }

    public void setDedicatedHosts(List<DedicatedHostResponse> dedicatedHosts) {
        this.dedicatedHosts = dedicatedHosts;
    }
}
