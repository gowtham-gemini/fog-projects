package com.assistanz.fogpanel

import grails.converters.JSON
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import javax.ws.rs.core.MediaType;

@Path('/api/internetGateway')
class InternetGatewayResource {
    
    
    InternetGatewayService internetGatewayService
    
    @GET
    @Path('/list')    
    @Produces(MediaType.APPLICATION_JSON)
    def getInternetGatewayList(@QueryParam("id") String id) {
        try {
            
            internetGatewayService.getList(id) as JSON
            
            
        } catch(Exception ex) {
            throw ex
        }
        
    }
}
