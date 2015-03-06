package com.assistanz.cloud.cloudstack.autoscale;

import java.util.List;

/**
 *
 * @author Santhosh
 *
 */
public class ListAutoScaleVmProfilesResponse {

    /**
     * Lists autoscale vm profiles
     */
    private List<AutoScaleVmProfileResponse> autoScaleVmProfiles;

    public List<AutoScaleVmProfileResponse> getAutoScaleVmProfiles() {
        return autoScaleVmProfiles;
    }

    public void setAutoScaleVmProfiles(List<AutoScaleVmProfileResponse> autoScaleVmProfiles) {
        this.autoScaleVmProfiles = autoScaleVmProfiles;
    }

}
