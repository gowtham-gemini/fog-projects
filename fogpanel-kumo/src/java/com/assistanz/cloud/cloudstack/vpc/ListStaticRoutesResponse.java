package com.assistanz.cloud.cloudstack.vpc;

import com.assistanz.cloud.cloudstack.TagsResponse;
import java.util.List;

/**
 *
 * @author Santhosh
 */
public class ListStaticRoutesResponse {

    /**
     * Lists all static routes
     */
    private List<StaticRouteResponse> staticRoutes;

    /**
     * the list of resource tags associated with static route
     */
    private List<TagsResponse> tagss;

    public List<StaticRouteResponse> getStaticRoutes() {
        return staticRoutes;
    }

    public void setStaticRoutes(List<StaticRouteResponse> staticRoutes) {
        this.staticRoutes = staticRoutes;
    }

    public List<TagsResponse> getTagss() {
        return tagss;
    }

    public void setTagss(List<TagsResponse> tagss) {
        this.tagss = tagss;
    }

}
