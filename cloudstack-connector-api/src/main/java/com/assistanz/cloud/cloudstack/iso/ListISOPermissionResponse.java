package com.assistanz.cloud.cloudstack.iso;

import java.util.List;

/**
 *
 * @author Santhosh
 *
 */
public class ListISOPermissionResponse {

    /**
     * List iso visibility and all accounts that have permissions to view this iso
     */
    private List<IsoPermissionResponse> isoPermissions;

    public List<IsoPermissionResponse> getIsoPermissions() {
        return isoPermissions;
    }

    public void setIsoPermissions(List<IsoPermissionResponse> isoPermissions) {
        this.isoPermissions = isoPermissions;
    }

}
