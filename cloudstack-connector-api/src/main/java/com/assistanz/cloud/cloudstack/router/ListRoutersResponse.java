package com.assistanz.cloud.cloudstack.router;

import java.util.List;

/**
 *
 * @author Gowtham
 *
 */
public class ListRoutersResponse {

    /**
     * List routers
     */
    private List<RouterResponse> routers;

    public List<RouterResponse> getRouters() {
        return routers;
    }

    public void setRouters(List<RouterResponse> routers) {
        this.routers = routers;
    }

}
