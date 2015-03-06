package com.assistanz.cloud.cloudstack.host;

import java.util.List;

/**
 * 
 * @author Gowtham
 *
 */
public class ListHostsResponse {
	
    /**
     * the list of hosts
     */
    private List<HostResponse> hosts;

    public List<HostResponse> getHosts() {
        return hosts;
    }

    public void setHosts(List<HostResponse> hosts) {
        this.hosts = hosts;
    }
}
