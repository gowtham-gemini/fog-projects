/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.assistanz.cloud.cloudstack.vpn;

import java.util.List;

/**
 *
 * @author gowtham
 */
class ListVpnUsersResponse {
    
    
    /**
    * List of Network Offerings
    */
    private List<VpnUserResponse> vpnUsers;

    public List<VpnUserResponse> getVpnUsers() {
        return vpnUsers;
    }

    public void setVpnUsers(List<VpnUserResponse> vpnUsers) {
        this.vpnUsers = vpnUsers;
    }
}
