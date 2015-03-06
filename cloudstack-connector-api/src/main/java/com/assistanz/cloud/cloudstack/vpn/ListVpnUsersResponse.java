package com.assistanz.cloud.cloudstack.vpn;

import java.util.List;

/**
 *
 * @author Gowtham
 *
 */
public class ListVpnUsersResponse {

    /**
     * list of tags associated with the virtual machine
     */
    private List<VpnUserResponse> vpnUsers;

    public List<VpnUserResponse> getVpnUsers() {
        return vpnUsers;
    }

    public void setVpnUsers(List<VpnUserResponse> vpnUsers) {
        this.vpnUsers = vpnUsers;
    }

}
