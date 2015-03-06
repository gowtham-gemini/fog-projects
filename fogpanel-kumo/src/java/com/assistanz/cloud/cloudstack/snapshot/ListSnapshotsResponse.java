package com.assistanz.cloud.cloudstack.snapshot;

import java.util.List;

/**
 * 
 * @author Gowtham
 *
 */
public class ListSnapshotsResponse {
	
   /**
     * The list of Snap Shot
     */
    private List<SnapShotResponse> snapShots;

    public List<SnapShotResponse> getSnapShots() {
        return snapShots;
    }

    public void setSnapShots(List<SnapShotResponse> snapShots) {
        this.snapShots = snapShots;
    }
}
