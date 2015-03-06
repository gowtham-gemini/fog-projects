
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
