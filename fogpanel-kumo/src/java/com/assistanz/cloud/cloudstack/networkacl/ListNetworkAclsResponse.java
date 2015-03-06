package com.assistanz.cloud.cloudstack.networkacl;

import java.util.List;

/**
 *
 * @author Santhosh
 *
 */
public class ListNetworkAclsResponse {

    /**
     * Lists all network ACL items
     */
    private List<NetworkAclResponse> networkAcls;

    public List<NetworkAclResponse> getNetworkAcls() {
        return networkAcls;
    }

    public void setNetworkAcls(List<NetworkAclResponse> networkAcls) {
        this.networkAcls = networkAcls;
    }

}
