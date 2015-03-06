package com.assistanz.cloud.cloudstack.pod;

import java.util.List;

/**
 *
 * @author Gowtham
 *
 */
public class ListDedicatedPodsResponse {

    /**
     * Lists dedicated pods
     */
    private List<DedicatedPodResponse> dedicatedPods;

    public List<DedicatedPodResponse> getDedicatedPods() {
        return dedicatedPods;
    }

    public void setDedicatedPods(List<DedicatedPodResponse> dedicatedPods) {
        this.dedicatedPods = dedicatedPods;
    }
}
