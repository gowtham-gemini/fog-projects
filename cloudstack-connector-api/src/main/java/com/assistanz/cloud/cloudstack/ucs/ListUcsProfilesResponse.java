package com.assistanz.cloud.cloudstack.ucs;

import java.util.List;

/**
 *
 * @author Santhosh
 *
 */
public class ListUcsProfilesResponse {

    /**
     * List ucs managers
     */
    private List<UcsProfileResponse> ucsProfiles;

    public List<UcsProfileResponse> getUcsProfiles() {
        return ucsProfiles;
    }

    public void setUcsProfiles(List<UcsProfileResponse> ucsProfiles) {
        this.ucsProfiles = ucsProfiles;
    }

}
