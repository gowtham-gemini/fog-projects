package com.assistanz.cloud.cloudstack.region;

import java.util.List;

/**
 *
 * @author Santhosh
 */
public class ListRegionsResponse {

    /*
     * Lists Regions
     */
    private List<RegionResponse> regions;

    public List<RegionResponse> getRegions() {
        return regions;
    }

    public void setRegions(List<RegionResponse> regions) {
        this.regions = regions;
    }

}
