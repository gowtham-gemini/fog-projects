package com.assistanz.cloud.cloudstack.hypervisors;

import java.util.List;

/**
 *
 * @author Gowtham
 *
 */
public class ListHypervisorCapabilitiesResponse {

    /**
     * Lists all hypervisor capabilities
     */
    private List<HypervisorCapabilitiesResponse> hypervisorCapabilitiess;

    public List<HypervisorCapabilitiesResponse> getHypervisorCapabilitiess() {
        return hypervisorCapabilitiess;
    }

    public void setHypervisorCapabilitiess(List<HypervisorCapabilitiesResponse> hypervisorCapabilitiess) {
        this.hypervisorCapabilitiess = hypervisorCapabilitiess;
    }
}
