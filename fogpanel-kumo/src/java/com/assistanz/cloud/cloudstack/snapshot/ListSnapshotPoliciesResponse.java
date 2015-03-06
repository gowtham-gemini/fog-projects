package com.assistanz.cloud.cloudstack.snapshot;

import java.util.List;

/**
 * 
 * @author Gowtham
 *
 */
public class ListSnapshotPoliciesResponse {
	
   /**
     * The list of Snap Shot policies
     */
    private List<SnapShotPolicyResponse> snapShotPolicies;

    public List<SnapShotPolicyResponse> getSnapShotPolicies() {
        return snapShotPolicies;
    }

    public void setSnapShotPolicies(List<SnapShotPolicyResponse> snapShotPolicies) {
        this.snapShotPolicies = snapShotPolicies;
    }
}
