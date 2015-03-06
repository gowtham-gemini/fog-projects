package com.assistanz.fogpanel

import grails.converters.JSON
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import javax.ws.rs.core.MediaType;

@Path('/api/routeTable')
class RouteTableResource {
    
    
    RouteTableService routeTableService
    
    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    String getRouteTableList(@QueryParam("currentRegionId") String currentRegionId, 
                         @QueryParam("vpcId") String vpcId) {
        try {
           routeTableService.getRouteTableList(currentRegionId, vpcId) as JSON
        } catch (Exception ex) {
            [ex] as JSON
        }
        
    }
    
    @GET
    @Path("/view")
    @Produces(MediaType.APPLICATION_JSON)
    String getRouteTableView(@QueryParam("id") String id) {
        try {
           routeTableService.getRouteTableView(id) as JSON
        } catch (Exception ex) {
            [ex] as JSON
        }
        
    }
}
