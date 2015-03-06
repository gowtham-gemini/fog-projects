package com.assistanz.cloud.cloudstack.vpn;

import java.util.List;

/**
 *
 * @author Gowtham
 *
 */
public class ListRemoteAccessVpnsResponse {

    /**
     * list of tags associated with the virtual machine
     */
    private List<RemoteAccessVpnResponse> remoteAccessVpns;

    public List<RemoteAccessVpnResponse> getRemoteAccessVpns() {
        return remoteAccessVpns;
    }

    public void setRemoteAccessVpns(List<RemoteAccessVpnResponse> remoteAccessVpns) {
        this.remoteAccessVpns = remoteAccessVpns;
    }

}
