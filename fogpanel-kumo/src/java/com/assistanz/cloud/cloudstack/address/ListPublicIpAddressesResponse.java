package com.assistanz.cloud.cloudstack.address;

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

    public List<IpAddressResponse> getIpAddresses() {
        return ipAddresses;
    }

    public void setIpAddresses(List<IpAddressResponse> ipAddresses) {
        this.ipAddresses = ipAddresses;
    }
}
