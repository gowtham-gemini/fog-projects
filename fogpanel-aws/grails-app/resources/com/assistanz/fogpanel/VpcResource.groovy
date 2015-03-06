package com.assistanz.fogpanel

import com.assistanz.fogpanel.VpcService
import grails.converters.JSON
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import javax.ws.rs.PathParam
import javax.ws.rs.core.Response
import javax.ws.rs.core.Request
import javax.ws.rs.core.MediaType

@Path('/api/vpc')
class VpcResource {
    
    VpcService vpcService
    
    @GET
    @Path("/pullFromAws")
    @Produces(MediaType.APPLICATION_JSON)
    def pullVpcFromAws () {
        try {
            vpcService.pullVpcJobStart()
            ["OK"] as JSON;
        } catch (Exception ex) {
            [ex] as JSON
        }
    }
    
    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    String getVpcList(@QueryParam("currentRegionId") String currentRegionId) {
        try {
           vpcService.getVpcList(currentRegionId) as JSON
        } catch (Exception ex) {
            [ex] as JSON
        }
        
    }
    
    
    @GET
    @Path("/stat")
    @Produces(MediaType.APPLICATION_JSON)
    String getVpcStat(@QueryParam("regionId") String regionId) {
        try {
           vpcService.getVpcStat(regionId) as JSON
        } catch (Exception ex) {
            [ex] as JSON
        }
        
    }
    
    @GET
    @Path("/view")
    @Produces(MediaType.APPLICATION_JSON)
    String viewVpc(@QueryParam("id") String id) {
        try {
           vpcService.viewVpc(id) as JSON
        } catch (Exception ex) {
            [ex] as JSON
        }
        
    }
    
    
    
    
}
