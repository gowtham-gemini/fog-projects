package com.assistanz.cloud.cloudstack.cluster;

import java.util.List;

/**
 * 
 * @author Gowtham
 *
 */
public class ListClustersResponse {
	
    /**
     * List of Clusters
     */
    private List<ClusterResponse> clusters;

    public List<ClusterResponse> getClusters() {
        return clusters;
    }

    public void setClusters(List<ClusterResponse> clusters) {
        this.clusters = clusters;
    }
}
