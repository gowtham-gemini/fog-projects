package com.assistanz.cloud.cloudstack.securitygroup;

import java.util.List;
import com.assistanz.cloud.cloudstack.SecurityGroupResponse;
import com.assistanz.cloud.cloudstack.TagsResponse;

/**
 *
 * @author Gowtham
 *
 */
public class ListSecurityGroupsResponse {

    /**
     * List of Security groups
     */
    private List<SecurityGroupResponse> securityGroups;

    /**
     * the list of resource tags associated with the rule
     */
    private List<TagsResponse> tagss;

    public List<SecurityGroupResponse> getSecurityGroups() {
        return securityGroups;
    }

    public void setSecurityGroups(List<SecurityGroupResponse> securityGroups) {
        this.securityGroups = securityGroups;
    }

    public List<TagsResponse> getTagss() {
        return tagss;
    }

    public void setTagss(List<TagsResponse> tagss) {
        this.tagss = tagss;
    }

}
