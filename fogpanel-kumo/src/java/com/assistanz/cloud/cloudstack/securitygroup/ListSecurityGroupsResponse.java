package com.assistanz.cloud.cloudstack.securitygroup;

import java.util.List;
import com.assistanz.cloud.cloudstack.SecurityGroupResponse;

/**
 * 
 * @author Gowtham
 *
 */
public class ListSecurityGroupsResponse {
	
    /**
     * List of Security groups
     */
    private List<SecurityGroupResponse> securityGroups;

    public List<SecurityGroupResponse> getSecurityGroups() {
        return securityGroups;
    }

    public void setSecurityGroups(List<SecurityGroupResponse> securityGroups) {
        this.securityGroups = securityGroups;
    }
}
