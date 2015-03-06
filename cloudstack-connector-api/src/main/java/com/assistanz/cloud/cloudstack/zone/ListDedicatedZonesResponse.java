package com.assistanz.cloud.cloudstack.zone;

import java.util.List;

/**
 *
 * @author Gowtham
 *
 */
public class ListDedicatedZonesResponse {

    /**
     * List dedicated zones
     */
    private List<DedicatedZoneResponse> dedicatedZones;

    public List<DedicatedZoneResponse> getDedicatedZones() {
        return dedicatedZones;
    }

    public void setDedicatedZones(List<DedicatedZoneResponse> dedicatedZones) {
        this.dedicatedZones = dedicatedZones;
    }

}
