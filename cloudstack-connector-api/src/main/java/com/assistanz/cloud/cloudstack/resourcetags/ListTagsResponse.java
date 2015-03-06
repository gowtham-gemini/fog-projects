package com.assistanz.cloud.cloudstack.resourcetags;

import com.assistanz.cloud.cloudstack.TagsResponse;

import java.util.List;

/**
 *
 * @author Santhosh
 */
public class ListTagsResponse {

    /*
     * List resource tag(s)
     */
    private List<TagsResponse> tagss;

    public List<TagsResponse> getTagss() {
        return tagss;
    }

    public void setTagss(List<TagsResponse> tagss) {
        this.tagss = tagss;
    }

}
