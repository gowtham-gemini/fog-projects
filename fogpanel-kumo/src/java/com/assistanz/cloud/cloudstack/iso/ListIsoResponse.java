
package com.assistanz.cloud.cloudstack.iso;

import java.util.List;

/**
 *
 * @author Gowtham
 */
public class ListIsoResponse {
        
    /**
     * The list of Templates
     */
    private List<IsoResponse> isos;

    public List<IsoResponse> getIsos() {
        return isos;
    }

    public void setIsos(List<IsoResponse> isos) {
        this.isos = isos;
    }
    
}
