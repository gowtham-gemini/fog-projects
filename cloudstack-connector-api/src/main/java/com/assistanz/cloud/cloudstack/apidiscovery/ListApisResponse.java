package com.assistanz.cloud.cloudstack.apidiscovery;

import java.util.List;

/**
 *
 * @author Santhosh
 *
 */
public class ListApisResponse {

    /**
     * lists all available apis on the server, provided by the Api Discovery plugin
     */
    private List<ApiListResponse> apiLists;

    public List<ApiListResponse> getApiLists() {
        return apiLists;
    }

    public void setApiLists(List<ApiListResponse> apiLists) {
        this.apiLists = apiLists;
    }

}
