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
    String getFlavorRepresentation(@QueryParam("referenceId") String referenceId) {

        flavorService.listFlavor(referenceId) as JSON
    }
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/pullFromOpenstack")
    def pullFlavorFromOpenstack() {
        try { 
            flavorService.pullFlavorFromOpenstack()
            ["OK"] as JSON;
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
    @Produces(MediaType.APPLICATION_JSON)
    def createFlavors(@RequestBody String requestBody) {
        try { 
             
            def requestData = JSON.parse(requestBody)
            
            flavorService.createFlavors(requestData) as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def updateFlavors(@RequestBody String requestBody) {
        try { 
             
            def requestData = JSON.parse(requestBody)
            
            flavorService.updateFlavors(requestData) as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def delete(@PathParam("id") String id) {        
        try {
            flavorService.deleteFlavor(id) as JSON
            return "OK";
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
    }
    
    
}
