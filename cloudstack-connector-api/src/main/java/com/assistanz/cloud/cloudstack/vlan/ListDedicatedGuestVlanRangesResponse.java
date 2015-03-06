package com.assistanz.cloud.cloudstack.vlan;

import java.util.List;

/**
 *
 * @author Santhosh Bheeman
 */
public class ListDedicatedGuestVlanRangesResponse {

    /*
     * Lists dedicated guest vlan ranges
     */
    private List<DedicatedGuestVlanRangeResponse> dedicatedGuestVlanRanges;

    public List<DedicatedGuestVlanRangeResponse> getDedicatedGuestVlanRanges() {
        return dedicatedGuestVlanRanges;
    }

    public void setDedicatedGuestVlanRanges(List<DedicatedGuestVlanRangeResponse> dedicatedGuestVlanRanges) {
        this.dedicatedGuestVlanRanges = dedicatedGuestVlanRanges;
    }
}
