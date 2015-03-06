package com.assistanz.cloud.cloudstack.volume;

import java.util.List;

/**
 * 
 * @author Gowtham
 *
 */
public class ListVolumeResponse {
    
    /**
     * The list of volumes
     */
    private List<VolumeResponse> volumes;

    public List<VolumeResponse> getVolumes() {
        return volumes;
    }

    public void setVolumes(List<VolumeResponse> volumes) {
        this.volumes = volumes;
    }
}
