package com.assistanz.cloud.cloudstack.address;

import com.assistanz.cloud.cloudstack.TagsResponse;
import java.util.List;

/**
 *
 * @author Gowtham
 *
 */
public class ListPublicIpAddressesResponse {

    /**
     * The list of Ip address
     */
    private List<IpAddressResponse> ipAddresses;

    /**
     * the list of resource tags associated with ip address
     */
    private List<TagsResponse> tagss;

    public List<IpAddressResponse> getIpAddresses() {
        return ipAddresses;
    }

    public void setIpAddresses(List<IpAddressResponse> ipAddresses) {
        this.ipAddresses = ipAddresses;
    }

    public List<TagsResponse> getTagss() {
        return tagss;
    }

    public void setTagss(List<TagsResponse> tagss) {
        this.tagss = tagss;
    }
}
