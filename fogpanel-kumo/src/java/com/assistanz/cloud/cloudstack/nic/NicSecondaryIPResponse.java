/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.assistanz.cloud.cloudstack.nic;

/**
 *
 * @author gowtham
 */
class NicSecondaryIPResponse {
    
    String secIpId;
    
    String ipAddress;  

    public String getSecIpId() {
        return secIpId;
    }

    public void setSecIpId(String secIpId) {
        this.secIpId = secIpId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
