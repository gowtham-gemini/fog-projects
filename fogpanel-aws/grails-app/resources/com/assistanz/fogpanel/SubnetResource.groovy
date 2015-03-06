package com.assistanz.fogpanel

import grails.converters.JSON
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import javax.ws.rs.core.MediaType;

@Path('/api/subnet')
class SubnetResource {
    
    
    SubnetService subnetService
    
    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    String getSubnetList(@QueryParam("currentRegionId") String currentRegionId, 
                         @QueryParam("vpcId") String vpcId) {
        try {
           subnetService.getSubnetListByRegionAndVPC(currentRegionId, vpcId) as JSON
        } catch (Exception ex) {
            [ex] as JSON
        }
        
    }
}
