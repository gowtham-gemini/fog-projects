package com.assistanz.cloud.cloudstack.pod;

import java.util.List;

/**
 * 
 * @author Gowtham
 *
 */
public class ListPodsResponse {
	
    /**
     * List of pods
     */
    private List<PodResponse> Pods;

    public List<PodResponse> getPods() {
        return Pods;
    }

    public void setPods(List<PodResponse> Pods) {
        this.Pods = Pods;
    }
}
