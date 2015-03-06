package com.assistanz.cloud.cloudstack.vmgroup;

import java.util.List;

/**
 *
 * @author Gowtham
 *
 */
public class ListInstanceGroupsResponse {

    /**
     * Lists vm groups
     */
    private List<InstanceGroupResponse> instanceGroups;

    public List<InstanceGroupResponse> getInstanceGroups() {
        return instanceGroups;
    }

    public void setInstanceGroups(List<InstanceGroupResponse> instanceGroups) {
        this.instanceGroups = instanceGroups;
    }

}
