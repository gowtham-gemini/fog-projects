package com.assistanz.cloud.cloudstack.iso;

import com.assistanz.cloud.cloudstack.TagsResponse;
import java.util.List;

/**
 *
 * @author Gowtham
 */
public class ListIsoResponse {

    /**
     * The list of Templates
     */
    private List<IsoResponse> isos;

    /**
     * list of tags associated with the virtual machine
     */
    private List<TagsResponse> tagss;

    public List<IsoResponse> getIsos() {
        return isos;
    }

    public void setIsos(List<IsoResponse> isos) {
        this.isos = isos;
    }

    public List<TagsResponse> getTagss() {
        return tagss;
    }

    public void setTagss(List<TagsResponse> tagss) {
        this.tagss = tagss;
    }

}
