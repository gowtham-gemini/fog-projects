package com.assistanz.cloud.cloudstack.resourcemetadata;


import java.util.List;

/**
 *
 * @author Santhosh
 */
public class ListResourceDetailsResponse {

    /*
     * List resource detail(s)
     */
    private List<ResourceDetailResponse> resourceDetails;

    public List<ResourceDetailResponse> getResourceDetails() {
        return resourceDetails;
    }

    public void setResourceDetails(List<ResourceDetailResponse> resourceDetails) {
        this.resourceDetails = resourceDetails;
    }

}
