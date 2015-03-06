package com.assistanz.cloud.cloudstack.volume;

import com.assistanz.cloud.cloudstack.TagsResponse;
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

    /**
     * list of tags associated with the virtual machine
     */
    private List<TagsResponse> tagss;

    public List<VolumeResponse> getVolumes() {
        return volumes;
    }

    public void setVolumes(List<VolumeResponse> volumes) {
        this.volumes = volumes;
    }

    public List<TagsResponse> getTagss() {
        return tagss;
    }

    public void setTagss(List<TagsResponse> tagss) {
        this.tagss = tagss;
    }

}
