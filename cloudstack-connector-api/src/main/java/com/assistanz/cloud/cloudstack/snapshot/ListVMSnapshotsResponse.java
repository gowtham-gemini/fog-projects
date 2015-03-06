package com.assistanz.cloud.cloudstack.snapshot;

import java.util.List;

/**
 *
 * @author gowtham
 */
class ListVMSnapshotsResponse {

    /**
     * The list of Snap Shot
     */
    private List<VMSnapShotResponse> vmsnapShots;

    public List<VMSnapShotResponse> getVmsnapShots() {
        return vmsnapShots;
    }

    public void setVmsnapShots(List<VMSnapShotResponse> vmsnapShots) {
        this.vmsnapShots = vmsnapShots;
    }

}
