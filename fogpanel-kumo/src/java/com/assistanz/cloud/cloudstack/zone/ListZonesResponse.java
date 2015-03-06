package com.assistanz.cloud.cloudstack.zone;

import java.util.List;
import com.assistanz.cloud.cloudstack.CapacityResponse;
import com.assistanz.cloud.cloudstack.ZoneResponse;


/**
 * 
 * @author Gowtham
 *
 */
public class ListZonesResponse {
	
    /**
     * The list of Zones
     */
    private List<ZoneResponse> zones;

    /**
     * the capacity of the Zone
     */
    private List<CapacityResponse> zoneCapacities;

    public List<ZoneResponse> getZones() {
        return zones;
    }

    public void setZones(List<ZoneResponse> zones) {
        this.zones = zones;
    }

 
    public List<CapacityResponse> getZoneCapacities() {
        return zoneCapacities;
    }

    public void setZoneCapacities(List<CapacityResponse> zoneCapacities) {
        this.zoneCapacities = zoneCapacities;
    }
    
    
}
