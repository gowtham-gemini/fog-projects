package com.assistanz.cloud.cloudstack.ucs;

import java.util.List;

/**
 *
 * @author Santhosh
 *
 */
public class ListUcsManagersResponse {

    /**
     * List ucs managers
     */
    private List<UcsManagerResponse> ucsManagers;

    public List<UcsManagerResponse> getUcsManagers() {
        return ucsManagers;
    }

    public void setUcsManagers(List<UcsManagerResponse> ucsManagers) {
        this.ucsManagers = ucsManagers;
    }

}
