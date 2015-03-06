package com.assistanz.cloud.cloudstack.router;

import java.util.List;

/**
 *
 * @author Gowtham
 *
 */
public class ListVirtualRouterElementsResponse {

    /**
     * Lists all available virtual router elements
     */
    private List<VirtualRouterElementResponse> virtualRouterElements;

    public List<VirtualRouterElementResponse> getVirtualRouterElements() {
        return virtualRouterElements;
    }

    public void setVirtualRouterElements(List<VirtualRouterElementResponse> virtualRouterElements) {
        this.virtualRouterElements = virtualRouterElements;
    }

}
