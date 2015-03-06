package com.assistanz.cloud.cloudstack.hypervisors;

import java.util.List;

/**
 * 
 * @author Gowtham
 *
 */
public class ListHypervisorsResponse {
    
    /**
     * The list of Hypervisor
     */
    private List<HypervisorResponse> hypervisors;

    public List<HypervisorResponse> getHypervisors() {
        return hypervisors;
    }

    public void setHypervisors(List<HypervisorResponse> hypervisors) {
        this.hypervisors = hypervisors;
    }
}
