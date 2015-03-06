package com.assistanz.cloud.cloudstack.guestos;

import java.util.List;
import com.assistanz.cloud.cloudstack.guestos.OsTypesResponse;

/**
 * 
 * @author Gowtham
 *
 */
public class ListOsTypesResponse {
    
    /**
     * The list of OS types
     */
    private List<OsTypesResponse> osTypes;

    public List<OsTypesResponse> getOsTypes() {
        return osTypes;
    }

    public void setOsTypes(List<OsTypesResponse> osTypes) {
        this.osTypes = osTypes;
    }    
}
 
