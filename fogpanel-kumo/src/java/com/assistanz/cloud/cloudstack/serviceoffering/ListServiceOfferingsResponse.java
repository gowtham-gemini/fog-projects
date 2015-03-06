package com.assistanz.cloud.cloudstack.serviceoffering;

import com.assistanz.cloud.cloudstack.ServiceOfferingResponse;
import java.util.List;

public class ListServiceOfferingsResponse {
    
    /**
     * the list of Service Offerings
     */
    private List<ServiceOfferingResponse> serviceOfferings;

    public List<ServiceOfferingResponse> getServiceOfferings() {
        return serviceOfferings;
    }

    public void setServiceOfferings(List<ServiceOfferingResponse> serviceOfferings) {
        this.serviceOfferings = serviceOfferings;
    }
}
