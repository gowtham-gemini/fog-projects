package com.assistanz.cloud.cloudstack.affinitygroup;

import java.util.List;

/**
 *
 * @author Santhosh
 *
 */
public class ListAffinityGroupTypesResponse {

    /**
     * Lists affinity group types available
     */
    private List<AffinityGroupTypeResponse> affinityGroupTypes;

    public List<AffinityGroupTypeResponse> getAffinityGroupTypes() {
        return affinityGroupTypes;
    }

    public void setAffinityGroupTypes(List<AffinityGroupTypeResponse> affinityGroupTypes) {
        this.affinityGroupTypes = affinityGroupTypes;
    }
}
