package com.assistanz.cloud.cloudstack.ucs;

import java.util.List;

/**
 *
 * @author Santhosh
 *
 */
public class ListUcsBladesResponse {

    /**
     * List ucs blades
     */
    private List<UcsBladeResponse> ucsBlades;

    public List<UcsBladeResponse> getUcsBlades() {
        return ucsBlades;
    }

    public void setUcsBlades(List<UcsBladeResponse> ucsBlades) {
        this.ucsBlades = ucsBlades;
    }

}
