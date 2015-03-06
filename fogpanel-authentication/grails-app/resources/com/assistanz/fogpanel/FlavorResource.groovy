package com.assistanz.fogpanel

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import org.springframework.web.bind.annotation.RequestBody;
import javax.ws.rs.GET
import javax.ws.rs.POST;
import javax.ws.rs.Path
import javax.ws.rs.PUT
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType;
import javax.ws.rs.DELETE
import javax.ws.rs.PathParam
import javax.ws.rs.core.Response
import javax.ws.rs.core.Request
import javax.ws.rs.QueryParam
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import grails.converters.deep.JSON

@Path('/api/flavor')
class FlavorResource {
    
    FlavorService flavorService

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    String getFlavorRepresentation() {

        flavorService.listFlavor() as JSON
    }
                
    @GET
    @Path("/count")
    @Produces(MediaType.APPLICATION_JSON)
    def flavorCount() {
        try {
            flavorService.count() as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }   
    
    @POST
    @Path("/status")
    @Produces(MediaType.APPLICATION_JSON)
    def updateStat(@RequestBody String requestBody) {
        try {
            // convert string to json object
            def requestData = JSON.parse(requestBody)
            
            flavorService.updateFlavorStatus(requestData)                
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (NullPointerException ex){
                [ex] as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    @GET
    @Path("/details")
    @Produces(MediaType.APPLICATION_JSON)
    String getFlavorDetails(@QueryParam("id") String id) {

        flavorService.getFlavorDetails(id) as JSON
    }
    
    
}
