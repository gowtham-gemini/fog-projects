package com.assistanz.cloud.cloudstack.portableip;

import java.util.List;

/**
 *
 * @author Santhosh
 */
public class ListPortableIpRangesResponse {

    /*
     * list portable IP ranges
     */
    private List<PortableIpRangeResponse> portableIpRanges;

    public List<PortableIpRangeResponse> getPortableIpRanges() {
        return portableIpRanges;
    }

    public void setPortableIpRanges(List<PortableIpRangeResponse> portableIpRanges) {
        this.portableIpRanges = portableIpRanges;
    }

}
