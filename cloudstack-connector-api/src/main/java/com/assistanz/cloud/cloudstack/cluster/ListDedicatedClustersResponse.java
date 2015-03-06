package com.assistanz.cloud.cloudstack.cluster;

import java.util.List;

/**
 *
 * @author Santhosh
 *
 */
public class ListDedicatedClustersResponse {

    /**
     * Lists dedicated pods
     */
    private List<DedicatedClusterResponse> dedicatedClusters;

    public List<DedicatedClusterResponse> getDedicatedClusters() {
        return dedicatedClusters;
    }

    public void setDedicatedClusters(List<DedicatedClusterResponse> dedicatedClusters) {
        this.dedicatedClusters = dedicatedClusters;
    }
}
