package com.assistanz.cloud.cloudstack.configuration;

import java.util.List;

public class ListCapabilitiesResponse {

    /**
     * Lists capabilities
     */
    private List<CapabilitiesResponse> capabilitiess;

    public List<CapabilitiesResponse> getCapabilitiess() {
        return capabilitiess;
    }

    public void setCapabilitiess(List<CapabilitiesResponse> capabilitiess) {
        this.capabilitiess = capabilitiess;
    }

}
