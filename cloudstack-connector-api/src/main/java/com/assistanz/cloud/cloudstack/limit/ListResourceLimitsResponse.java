package com.assistanz.cloud.cloudstack.limit;

import java.util.List;

/**
 *
 * @author Gowtham
 *
 */
public class ListResourceLimitsResponse {

    /**
     * Lists resource limits
     */
    private List<ResourceLimitResponse> resourceLimits;

    public List<ResourceLimitResponse> getResourceLimits() {
        return resourceLimits;
    }

    public void setResourceLimits(List<ResourceLimitResponse> resourceLimits) {
        this.resourceLimits = resourceLimits;
    }
}
