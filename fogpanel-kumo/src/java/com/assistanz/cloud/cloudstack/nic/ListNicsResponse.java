/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.assistanz.cloud.cloudstack.nic;

import java.util.List;

/**
 *
 * @author gowtham
 */
class ListNicsResponse {
    
    
    /**
    * the list of nic
    */
    private List<NicResponse> nic;

    public List<NicResponse> getNic() {
        return nic;
    }

    public void setNic(List<NicResponse> nic) {
        this.nic = nic;
    }
    
    
    
    
    
}
