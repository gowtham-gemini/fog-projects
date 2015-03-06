package com.assistanz.cloud.cloudstack.vlan;

import java.util.List;

/**
 *
 * @author Santhosh Bheeman
 */
public class ListVlanIpRangesResponse {

    /*
     * Lists all VLAN IP ranges
     */
    private List<VlanIpRangeResponse> vlanIpRanges;

    public List<VlanIpRangeResponse> getVlanIpRanges() {
        return vlanIpRanges;
    }

    public void setVlanIpRanges(List<VlanIpRangeResponse> vlanIpRanges) {
        this.vlanIpRanges = vlanIpRanges;
    }
}
