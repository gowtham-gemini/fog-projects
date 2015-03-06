package com.assistanz.cloud.cloudstack.systemcapacity;

import java.util.List;

/**
 * 
 * @author Gowtham
 *
 */
public class ListCapacityServiceResponse {
	
    
    /**
     * the list of Disk Offerings
     */
    private List<CapacityResponse> capacities;

    public List<CapacityResponse> getCapacities() {
        return capacities;
    }

    public void setCapacities(List<CapacityResponse> capacities) {
        this.capacities = capacities;
    }
    
}
