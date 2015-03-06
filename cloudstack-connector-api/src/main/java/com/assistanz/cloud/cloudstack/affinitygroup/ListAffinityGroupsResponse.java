package com.assistanz.cloud.cloudstack.affinitygroup;

import java.util.List;

/**
 *
 * @author Santhosh
 *
 */
public class ListAffinityGroupsResponse {

    /**
     * Lists affinity groups
     */
    private List<AffinityGroupResponse> affinityGroups;

    public List<AffinityGroupResponse> getAffinityGroups() {
        return affinityGroups;
    }

    public void setAffinityGroups(List<AffinityGroupResponse> affinityGroups) {
        this.affinityGroups = affinityGroups;
    }
}
