package com.assistanz.cloud.cloudstack.autoscale;

import java.util.List;

/**
 *
 * @author Santhosh
 *
 */
public class ListAutoScaleVmGroupsResponse {

    /**
     * Lists autoscale vm groups
     */
    private List<AutoScaleVmGroupResponse> autoScaleVmGroups;

    public List<AutoScaleVmGroupResponse> getAutoScaleVmGroups() {
        return autoScaleVmGroups;
    }

    public void setAutoScaleVmGroups(List<AutoScaleVmGroupResponse> autoScaleVmGroups) {
        this.autoScaleVmGroups = autoScaleVmGroups;
    }

}
