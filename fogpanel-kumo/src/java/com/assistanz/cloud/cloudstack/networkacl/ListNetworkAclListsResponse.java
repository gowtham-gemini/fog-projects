package com.assistanz.cloud.cloudstack.networkacl;

import java.util.List;

/**
 *
 * @author Santhosh
 *
 */
public class ListNetworkAclListsResponse {

    String count;
    
    /**
     * Lists all network ACLs
     */
    private List<NetworkAclListResponse> networkAclLists;

    public List<NetworkAclListResponse> getNetworkAclLists() {
        return networkAclLists;
    }

    public void setNetworkAclLists(List<NetworkAclListResponse> networkAclLists) {
        this.networkAclLists = networkAclLists;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
    
    

}
